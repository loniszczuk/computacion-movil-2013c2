package flight.recorder;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class SimpleController implements OnClickListener {

	private final Activity activity;
	private final SimpleView view;
	private final SimpleModel model;
	private Timer updateGUITimer = new Timer();

	
	
	public String phone_number;

	
	public SimpleController(Activity a, SimpleModel m, SimpleView v) {
		this.activity = a;
		this.model = m;
		this.view = v;

	}

	@Override
	public void onClick(View view) {
		if (this.view.getButtonOn() == view) {
			this.model.start();
			this.view.switchButton();
			this.updateView();
			Log.v("","Start service");
			this.startPressureService();
		} else if (this.view.getButtonOff() == view) {
			Log.v("", "Stop service");
			this.endPressureService();
			this.model.stop();
			this.cancelUpdateView();
			this.showResults();
		} else if(this.view.getButtonCall() == view){
			this.call();
		}
	}
	
	private void updateView() {
        final Runnable updateGUIRunnable = new Runnable() {
        	public void run() {
        		Log.v("", "Updating view");
        		SimpleController.this.view.renderAltitude();
        		SimpleController.this.view.renderSpeed();
        	}
        };
        
        final Handler myHandler = new Handler();
        

        updateGUITimer.schedule( new TimerTask() {
			
			@Override
			public void run() {
				myHandler.post(updateGUIRunnable);
			}
		}, 0, 1000);
	}
	
	private void cancelUpdateView() {
		this.updateGUITimer.cancel();
	}

	public void onAltitudeChanged() {
		this.view.renderAltitude();
	}
	
	public void startPressureService() {
		this.activity.startService(new Intent(this.activity, SimpleService.class));
	}


	public void endPressureService() {
		this.activity.stopService(new Intent(this.activity, SimpleService.class));
	}
	
	public void showResults(){
		Intent intent = new Intent(this.activity, ResultsActivity.class);
		intent.putExtra("ALTITUDES", toArray(this.model.getAltitudes()));
		intent.putExtra("SPEEDS", toArray(this.model.getSpeeds()));
		//Start new activity
		this.activity.startActivity(intent);
		
	}

	private void call() {
		
        try {
    		SharedPreferences Settings = PreferenceManager.getDefaultSharedPreferences(this.activity);
    		phone_number = Settings.getString("prefUserCall", "911");
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phone_number));
            System.out.println(phone_number);
            this.activity.startActivity(callIntent);
        } catch (ActivityNotFoundException e) {
            Log.e(" Dialing ", "Call failed", e);
        }
    }
	

	private double[] toArray(List<Double> altitudes) {
		double[] ret = new double[altitudes.size()];
		int i = 0;
		for(double d : altitudes){
			ret[i++] = d;
		}
		return ret;
	}


	
}
