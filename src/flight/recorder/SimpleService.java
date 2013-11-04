package flight.recorder;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

public class SimpleService extends Service implements SensorEventListener {

	public static final String PRESSURE_SERVICE = "PRESSURE_SERVICE";
	public static final String PRESSURE_NOTIFICATION = "PRESSURE_NOTIFICATION";
	public static final String PRESSURE = "PRESSURE";
	public static final String TIMESTAMP = "TIMESTAMP";
	
	private Timer t;
		
	public SimpleService() {
	}
	
	@Override
	public void onCreate() {
		Log.v("", "onCreate service");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		Log.v("", "onStartCommand service");
		
		SensorManager s = (SensorManager) getSystemService(SENSOR_SERVICE);
		Sensor sensor = s.getDefaultSensor(Sensor.TYPE_PRESSURE);
		
		s.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
		
		return START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		//t.cancel();
		super.onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	} 
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		Meassure meassure = new Meassure();
		meassure.timestamp = System.currentTimeMillis();
		
		meassure.value = event.values[0];
		sendNotification(meassure);
	}
	
	private void sendNotification(Meassure m) {
		Intent intent = new Intent(PRESSURE_NOTIFICATION); 
		intent.putExtra(PRESSURE,m.value);
		intent.putExtra(TIMESTAMP, m.timestamp);
		sendBroadcast(intent);
	}
	
			
	public static class Meassure {
		public long timestamp;
		public float value;
	}

}
