package TemperatureMonitor_Code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
*This is RMI server.Connected with API and providing sevices to Desktop
*/

public class TemperatureSensorServer extends UnicastRemoteObject implements
		TemperatureSensor, Runnable {

	private static HttpURLConnection con;
	//private ArrayList<TemperatureListener> list = new ArrayList<TemperatureListener>();
        //Make ArrayList Synchronized inorder to add or get arraylist objects.
        List<TemperatureListener> list = Collections.synchronizedList(new ArrayList<TemperatureListener>());
        
        private ArrayList<String> sensorslist = new ArrayList<>();

	public TemperatureSensorServer() throws java.rmi.RemoteException {
		
	}
        //Notify about the connection with rmi server
	public String getConnection() throws java.rmi.RemoteException {
		return "Connnected to rmi server";
	}
        //Add a listener to arralist 
	public void addTemperatureListener(TemperatureListener listener)
			throws java.rmi.RemoteException {
		System.out.println("adding listener -" + listener);
		list.add(listener);
	}
        //Remove an listener
	public void removeTemperatureListener(TemperatureListener listener)
			throws java.rmi.RemoteException {
		System.out.println("removing listener -" + listener);
		list.remove(listener);
	}
        //To check login with provided username and password and return "logged" if credentials are true
        @Override
        public String login(String un,String pw) throws RemoteException {
            System.out.println("Inside login method"+un+"\n\n\n");
             try {

            URL myurl = new URL("http://localhost:8080/user/"+un);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");
            
            
            if(con.getResponseCode()==200){
                         
                        InputStream im=con.getInputStream();
                         StringBuffer sb=new StringBuffer();
                         BufferedReader br=new BufferedReader(new InputStreamReader(im));
                        String line=br.readLine();
                         while(line != null){
                        System.out.println("displayig line....."+line);
                        JSONObject jsonObj = new JSONObject(line);
                         String getpw=jsonObj.getString("password");
                         if(getpw.equals(pw)){
                             return "logged";
                         }
                         line=br.readLine();
                        }
                       
            
            }
                      
             }   catch (MalformedURLException ex) {
                Logger.getLogger(TemperatureSensorServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ProtocolException ex) {
                Logger.getLogger(TemperatureSensorServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TemperatureSensorServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(TemperatureSensorServer.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

            con.disconnect();
        }
            return "incorrect";
        }

        //This is the main thread
	public void run() {
            try {
                System.out.println("starting.........");
                //this is run after every 5 s
                //retrieve the sensor detalis and notify if exceeded
                for(;;){
                    sensorslist.clear();
                    System.out.println("Refresh Sensor Data.........");
                     URL u=new URL("http://localhost:8080/sensor");
                     HttpURLConnection hr=(HttpURLConnection) u.openConnection();
                     if(hr.getResponseCode()==200){
                        InputStream im=hr.getInputStream();
                         StringBuffer sb=new StringBuffer();
                         BufferedReader br=new BufferedReader(new InputStreamReader(im));
                        String line=br.readLine();
                        while(line != null){
                            //System.out.println("displayig line....."+line);
                            
                            JSONArray jsonArr = new JSONArray(line);

                            for (int i = 0; i < jsonArr.length(); i++)
                            {
                                JSONObject jsonObj = jsonArr.getJSONObject(i);

                                System.out.println("json object"+jsonObj);
                                sensor ob=new sensor();
                                ob.setLocation(jsonObj.getString("location"));
                                ob.setActivity(jsonObj.getString("activity"));
                                ob.setCo2(jsonObj.getInt("co2"));
                                ob.setSmokelevel(jsonObj.getInt("smokelevel"));
                                
                                if(jsonObj.getInt("co2")>=5 || jsonObj.getInt("smokelevel")>=5 ){
                                    if(jsonObj.getInt("co2")>=5 && jsonObj.getInt("smokelevel")>=5 ){
                                    ob.setHigh("Your co2 and smoke level is above or equals to 5.");
                                    this.notifyListeners( ob.getLocation());
                                    }else if(jsonObj.getInt("co2")>=5){
                                        ob.setHigh("Your co2  is above or equals to 5.");
                                        this.notifyListeners(ob.getLocation());
                                    }else{
                                        ob.setHigh("Your smoke level is above or equals to 5.");
                                        this.notifyListeners(ob.getLocation());
                                    }
                                }/*else{
                                     this.notifyListeners("not_exceeded");
                                }*/
                                
                                System.out.println("data"+ob);
                                String toStringVal=ob.toString();
                                sensorslist.add(toStringVal);
                            }
                            
                            
                            line=br.readLine();
                            }
                        }
                    
                    
                 
                 Thread.sleep(5000);
                
                }
                
            } catch (Exception ex) {
                Logger.getLogger(TemperatureSensorServer.class.getName()).log(Level.SEVERE, null, ex);
            }
                
	}
        //notify if exceeded
         private void  notifyListeners(String loc) {
            
		try{
                  
		for (TemperatureListener t:list){
                        
			t.temperatureChanged(loc);
                        
		}
                
		}catch(RemoteException e){}
		
	}
        @Override
        public String displaySensors() throws java.rmi.RemoteException{
            //System.out.println("sensor list:"+sensorslist);
            String listString = String.join(", ", sensorslist);
            return listString;
        
        }

	public static void main(String[] args) {
            //To start the rmi server
	   System.setProperty("java.security.policy", "file:allowall.policy");
 

		System.out.println("Loading temperature service");

		try {
			TemperatureSensorServer sensor = new TemperatureSensorServer();
			
                        Registry reg=LocateRegistry.createRegistry(4444);
                        reg.rebind("welcome", sensor);
                        System.out.println("server is ready.........");

			Thread thread = new Thread(sensor);
			thread.start();
		} catch (RemoteException re) {
			System.err.println("Remote Error - " + re);
		} catch (Exception e) {
			System.err.println("Error - " + e);
		}

	}

    

}