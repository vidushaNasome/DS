/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TemperatureMonitor_Code;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author vnaso
 * This doesn't have a main method
 * This can  called by Desktop class After login as an admin.
 */
public class SensorApp extends javax.swing.JFrame implements Runnable{
    
   // Use this con variable to start the connection in update method.
    private static HttpURLConnection con;
   
     
     Boolean checkStart=false;
    

    /**
     * Creates new form frame1
     */
    public SensorApp() {
        initComponents();
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        details = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        location123 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        det = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panel.setBackground(new java.awt.Color(0, 255, 204));

        location123.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                location123ActionPerformed(evt);
            }
        });

        jButton1.setText("Add Location and Start Sensor");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(location123, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(location123, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(102, 255, 204));

        det.setBackground(new java.awt.Color(255, 51, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(det, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(det, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jLabel1.setText("Sensor App");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(91, 91, 91)
                        .addComponent(details, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(details, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Calling the Add Sensor Method
        addSensorMethod("active",3,3,location123.getText());
        panel.setVisible(false);
        
       
        
        
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void location123ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_location123ActionPerformed
        
        
    }//GEN-LAST:event_location123ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel det;
    private javax.swing.JLabel details;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField location123;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
       
        String activity="active";
        String location=location123.getText();
        //Initially adding values to smokelevel and CO2.
        //This values will be changing in every 40s with using random number genarator and a simple logic.
        int smokeLevel=4;
        int co2=1;
        
        Random r=new Random();
        
        this.setVisible(true);
        
        for(;;){
       int num = r.nextInt();
      
       if(smokeLevel>0 &&smokeLevel<11){
            if(num%2==0)
            {
                if(smokeLevel!=10){
                smokeLevel=smokeLevel+1;
                }
            }else{
                if(smokeLevel!=1){
                smokeLevel=smokeLevel-1;
                }
            }
       }else{
           smokeLevel=4;
       }
       
       if(co2>0 &&co2<11){
            if(num%2==0)
            {
                if(co2!=10){
                co2=co2+1;
                }
            }else{
                if(co2!=1){
                co2=co2-1;
                }
            }
       }else{
           co2=2;
       }
       //CheckStart variable is used to stop calling UpdateSensorMethod and Displaysensor method before adding sensor details to the api and database.
       if(checkStart==true){
       System.out.println("#######"+location+" "+activity+" "+" Smokelevel:"+smokeLevel+" Co2level:"+co2+"####"+"\n");
       UpdateSensorMethod(activity, smokeLevel, co2, location);
        DisplaySensorMethod();
        
       }
           try {
               Thread.sleep(10000);
           } catch (InterruptedException ex) {
               Logger.getLogger(SensorApp.class.getName()).log(Level.SEVERE, null, ex);
           }
       
       }
    }
    //Using API add sensor details.
    private void addSensorMethod(String ac,int sm,int c,String loc){
    
        try {
            
            URL myurl = new URL("http://localhost:8080/sensor/add");
            HttpURLConnection con12 = (HttpURLConnection)myurl.openConnection();
		con12.setRequestMethod("POST");
		
		con12.setRequestProperty("Content-Type", "application/json; utf-8");
		con12.setRequestProperty("Accept", "application/json");
		
		con12.setDoOutput(true);
            
            
           String passingData = "{\"location\": \""+loc+"\", \"activity\": \""+ac+"\",\"smokelevel\": \""+sm+"\",\"co2\": \""+c+"\"}";
           
            try(OutputStream os = con12.getOutputStream()) {
            byte[] input = passingData.getBytes("utf-8");
            os.write(input, 0, input.length);           
            }
            
            int code = con12.getResponseCode();
		System.out.println(code);
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(con12.getInputStream(), "utf-8"))){
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			System.out.println(response.toString());
		}
          
            checkStart=true;
            
        } catch (IOException ex) {
            Logger.getLogger(SensorApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //Using API updating sensor datails.
    private void UpdateSensorMethod(String ac,int sm,int c,String loc){
    
        try {
            
            URL myurl = new URL("http://localhost:8080/sensor/add");
            HttpURLConnection con12 = (HttpURLConnection)myurl.openConnection();
		con12.setRequestMethod("POST");
		
		con12.setRequestProperty("Content-Type", "application/json; utf-8");
		con12.setRequestProperty("Accept", "application/json");
		
		con12.setDoOutput(true);
                String locNew=location123.getText().toString();
            
            
           String passingData = "{\"location\": \""+locNew+"\", \"activity\": \""+ac+"\",\"smokelevel\": \""+sm+"\",\"co2\": \""+c+"\"}";
           
            try(OutputStream os = con12.getOutputStream()) {
            byte[] input = passingData.getBytes("utf-8");
            os.write(input, 0, input.length);           
            }
            
            int code = con12.getResponseCode();
		System.out.println(code);
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(con12.getInputStream(), "utf-8"))){
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			System.out.println(response.toString());
		}
          
            
            
        } catch (IOException ex) {
            Logger.getLogger(SensorApp.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
        
    }
    //This method is used to call to display sensor details in sensorApp UI.Sensor data is retrived from API.
    private void DisplaySensorMethod(){
       
        try {
            
            URL myurl = new URL("http://localhost:8080/sensor/"+location123.getText());
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");
            System.out.println("showing updating.....1");
            
            if(con.getResponseCode()==200){
                        System.out.println("showing updating.....2");
                        InputStream im=con.getInputStream();
                         StringBuffer sb=new StringBuffer();
                         BufferedReader br=new BufferedReader(new InputStreamReader(im));
                        String line=br.readLine();
                         while(line != null){
                        System.out.println("showing updating....."+line);
                        JSONObject jsonObj = new JSONObject(line);
                          String location=jsonObj.getString("location");
                         String activity=jsonObj.getString("activity");
                         int smokelevel=jsonObj.getInt("smokelevel");
                         int co2=jsonObj.getInt("co2");
                         det.setText("#######"+location+"**  **"+activity+"**  **"+" Smokelevel:"+smokelevel+" Co2level:"+co2+"####"+"\n");
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
        
    
         
    }
    
    
    

    
    
    
}
