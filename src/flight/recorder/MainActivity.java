package flight.recorder;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import flight.recorder.SimpleService.Meassure;

public class MainActivity extends Activity{
	
	private SimpleModel model;
	private SimpleView view;
	private SimpleController controller;
	private PressureBroadcastReceiver pressureReceiver;

	private static final int RESULT_SETTINGS = 1;
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.v("", "Creating MainActivity");
        
        model = new SimpleModel(); 
        view = new SimpleView(this, model);
        controller = new SimpleController(this, model, view);
        pressureReceiver = new PressureBroadcastReceiver(model);
        findViewById(R.id.buttonOn).setOnClickListener(controller);
        findViewById(R.id.buttonOff).setOnClickListener(controller);
        findViewById(R.id.buttonCall).setOnClickListener(controller);
        
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.settings , menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menu_settings:
			Intent i = new Intent(this, UserSettingActivity.class);
			startActivityForResult(i, RESULT_SETTINGS);
			break;

		}

		return true;
	}

	
		
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SETTINGS:
			setUserSettings();
			break;

		}

	}

	private void setUserSettings() {
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

		
		String upd_phone_number = (String) sharedPrefs.getString("prefUserCall", "NULL");
		System.out.println(upd_phone_number);
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
