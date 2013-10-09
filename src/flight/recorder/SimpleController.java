package flight.recorder;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class SimpleController implements OnClickListener {

	private final Activity activity;
	private final SimpleView view;
	private final SimpleModel model;
	private Timer updateGUITimer = new Timer();
	
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
		//TODO nueva activity -> mostrar grafico y publicar en fb
		Intent intent = new Intent(this.activity, ResultsActivity.class);
		intent.putExtra("ALTITUDES", toArray(this.model.getAltitudes()));
		intent.putExtra("SPEEDS", toArray(this.model.getSpeeds()));
		//Empiezo la nueva activity
		this.activity.startActivity(intent);
		
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
