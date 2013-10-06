package my.app;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;


import my.app.SimpleService.Meassure;


public class SimpleModel {

	private Boolean started = Boolean.FALSE;	
	private long startTimestamp;
	private long lastTimestamp;
	private List<Double> altitudes = new ArrayList<Double>();
	
	public Boolean isStarted() {
		return this.started;
	}
	
	public void startStop() {
		this.started = !this.started;
		if (this.started) {
			this.startTimestamp = System.currentTimeMillis();
			this.lastTimestamp = this.startTimestamp;
		} 
	}
	
	public double getCurrentAltitude() {
		return altitudes.isEmpty()? 0 : altitudes.get(altitudes.size() - 1);
	}
	
	public List<Double> getAltitudes() { 
		return this.altitudes;
	}
	
	public long getStartTimestamp() {
		return this.startTimestamp;
	}

	public void registerNewPressure(Meassure meassure) {
		Log.v("", "New pressure registered");
		long current = meassure.timestamp;
		Double altitude = fromPressureToAltitude(meassure.value);
		for(long i = this.lastTimestamp; i < current; i += 1000) {
			// for each second between last timestamp and now
			altitudes.add(altitude);
		}
	}
	
	private double fromPressureToAltitude(double pressure) {
		return 44.3308 - 4.94654 * Math.pow(pressure, 0.190263);
	}
}
