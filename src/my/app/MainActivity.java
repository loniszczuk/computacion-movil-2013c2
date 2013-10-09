package my.app;

import java.util.Timer;
import java.util.TimerTask;

import my.app.SimpleService.Meassure;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity{
	
	private SimpleModel model;
	private SimpleView view;
	private SimpleController controller;
	private PressureBroadcastReceiver pressureReceiver;
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        model = new SimpleModel(); 
        view = new SimpleView(this, model);
        controller = new SimpleController(this, model, view);
        pressureReceiver = new PressureBroadcastReceiver(model);
        findViewById(R.id.buttonOn).setOnClickListener(controller);
        findViewById(R.id.buttonOff).setOnClickListener(controller);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        
        
        final Runnable updateGUIRunnable = new Runnable() {        	
        	public void run() {
        		Log.v("", "Updating view");
        		MainActivity.this.view.renderAltitude();
        	}
        };
        
        final Handler myHandler = new Handler();

        
        Timer updateGUITimer = new Timer();
        updateGUITimer.schedule( new TimerTask() {
			
			@Override
			public void run() {
				myHandler.post(updateGUIRunnable);
			}
		}, 0, 1000);
        
        return true;
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	this.registerReceiver(this.pressureReceiver, new IntentFilter(SimpleService.PRESSURE_NOTIFICATION));
    }

    @Override
    protected void onPause() {
    	super.onPause();
    	this.unregisterReceiver(this.pressureReceiver);
    }
 
    public static class PressureBroadcastReceiver extends BroadcastReceiver {
    	
    	private final SimpleModel model;
    	
    	public PressureBroadcastReceiver(SimpleModel m) {
    		model = m; 
    	}

		@Override
		public void onReceive(Context arg0, Intent i) {
			
			Meassure m = new Meassure();
			m.timestamp = i.getExtras().getLong(SimpleService.TIMESTAMP);
			m.value = i.getExtras().getFloat(SimpleService.PRESSURE);
			
			this.model.registerNewPressure(m);
		}
    }
        
}
