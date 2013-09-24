package my.app;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;

public class MainActivity extends Activity{
	
	private SimpleModel model;
	private SimpleView view;
	private SimpleController controller;
	
	private BroadcastReceiver broadcastReceiver ;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        model = new SimpleModel();
        view = new SimpleView(this, model);
        controller = new SimpleController(model, view);
        broadcastReceiver = new PressureBroadcastReceiver(controller);
        
        findViewById(R.id.buttonOn).setOnClickListener(controller);
        findViewById(R.id.buttonOff).setOnClickListener(controller);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onResume() {
    	this.registerReceiver(this.broadcastReceiver, new IntentFilter(SimpleService.PRESSURE_SERVICE));
    }

    @Override
    protected void onPause() {
    	this.unregisterReceiver(this.broadcastReceiver);
    }
    
    public static class PressureBroadcastReceiver extends BroadcastReceiver {

    	private SimpleController controller;

		public PressureBroadcastReceiver(SimpleController controller) {
    		this.controller = controller;
    	}
    	
		@Override
		public void onReceive(Context context, Intent intent) {
			controller.onPressureChanged(context, intent);
			
		}
    	
    }
    
    
}
