package my.app;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;

public class SimpleService extends Service implements SensorEventListener {

	public static final String PRESSURE_SERVICE = "PRESSURE_SERVICE";

	public static final String PRESSURE = "PRESSURE";
	
	private long startTimestamp;
	private List<Meassure> meassures = new ArrayList<Meassure>();
	private MyServiceBinder binder = new MyServiceBinder();
	
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		SensorManager s = (SensorManager) getSystemService(SENSOR_SERVICE);
		Sensor sensor = s.getDefaultSensor(Sensor.TYPE_PRESSURE);
		
		s.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

		this.startTimestamp = System.currentTimeMillis();
		
		return START_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	} 
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		Meassure meassure = new Meassure();
		meassure.timestamp = event.timestamp;
		meassure.value = event.values[0];
		this.meassures.add(meassure);
	}
	
	
	public Meassure getLastMeassure() {
		return this.meassures.get(this.meassures.size()-1);
	}
	
	public class MyServiceBinder extends Binder {
		public SimpleService getService() {
			return SimpleService.this;
		}
	}
	
	public static class Meassure {
		public long timestamp;
		public float value;
	}

}
