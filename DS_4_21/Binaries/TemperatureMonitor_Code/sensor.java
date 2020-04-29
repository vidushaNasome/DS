package TemperatureMonitor_Code;

public class sensor {
	
	
	private String location;
	
	
	private String activity;
	
	
	private int smokelevel;
	
	
	private int co2;
        
        private String high;

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }
	
	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public int getSmokelevel() {
		return smokelevel;
	}

	public void setSmokelevel(int smokelevel) {
		this.smokelevel = smokelevel;
	}

	public int getCo2() {
		return co2;
	}

	public void setCo2(int co2) {
		this.co2 = co2;
	}

    @Override
    public String toString() {
        return "sensor{" + "location=" + location + ", activity=" + activity + ", smokelevel=" + smokelevel + ", co2=" + co2 + ", high=" + high + '}';
    }

   
	

}
