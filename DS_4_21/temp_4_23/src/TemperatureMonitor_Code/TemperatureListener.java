package TemperatureMonitor_Code;

interface TemperatureListener extends java.rmi.Remote
{
	public void temperatureChanged(String loc) throws 	java.rmi.RemoteException;
}
