package flight.recorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;
import flight.recorder.SimpleService.Meassure;


public class SimpleModel {

	private long lastUpdate = 0;
	private Boolean started = Boolean.FALSE;	
	private List<Double> altitudes = new ArrayList<Double>();
	private List<Double> speeds = new ArrayList<Double>();
	
	
	public Boolean isStarted() {
		return this.started;
	}
	
	public void start() {
		if (!this.started) {
			this.lastUpdate = System.currentTimeMillis();
			this.started = true;
		} 
	}

	public void stop() {
		if (this.started) {
			this.started = false;
		} 
	}

	public double getCurrentAltitude() {
		return altitudes.isEmpty()? 0 : altitudes.get(altitudes.size() - 1);
	}
	
	public List<Double> getAltitudes() { 
		return this.altitudes;
	}

	public double getCurrentSpeed() {
		return speeds.isEmpty()? 0 : speeds.get(altitudes.size() - 1);
	}

	public List<Double> getSpeeds() {
		return this.speeds;
	}

	
	public void registerNewPressure(Meassure meassure) {
		Log.v("", "New pressure registered");
		Random r = new Random();
		long current = meassure.timestamp;
		Log.v("", "current timestamp " + current);
		if (this.lastUpdate + 1000 < current) {
			Log.v("", "not too old: last " + lastUpdate + " current " + current);
			Double altitude = fromPressureToAltitude(meassure.value);
			for(long i = this.lastUpdate+1000; i < current; i += 1000) {
				altitudes.add(altitude);
				speeds.add(Double.valueOf(r.nextInt(60)));
			}
			this.lastUpdate = current;
		}
	}
	
	private double fromPressureToAltitude(double pressure) {
		return 44.3308 - 4.94654 * Math.pow(pressure, 0.190263);
	}

}
