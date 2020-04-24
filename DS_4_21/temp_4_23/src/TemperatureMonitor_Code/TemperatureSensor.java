package TemperatureMonitor_Code;

import java.util.ArrayList;

interface TemperatureSensor extends java.rmi.Remote
{
	public String getConnection() throws
		java.rmi.RemoteException;
	public void addTemperatureListener
		(TemperatureListener listener )
		throws java.rmi.RemoteException;
	public void removeTemperatureListener
		(TemperatureListener listener )
		throws java.rmi.RemoteException;
        public String displaySensors() throws java.rmi.RemoteException;
        public String login(String un,String pw) throws java.rmi.RemoteException;
}
