package my.app;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class SimpleController implements OnClickListener {

	private final Activity activity;
	private final SimpleView view;
	private final SimpleModel model;
	
	public SimpleController(Activity a, SimpleModel m, SimpleView v) {
		this.activity = a;
		this.model = m;
		this.view = v;
	}

	@Override
	public void onClick(View view) {
		if (this.view.getButton() == view) {
			this.model.startStop();
			this.view.renderStartStop();
			Log.v("", "onClick");
			if (this.model.isStarted()) {
				Log.v("","start service");
				this.startPressureService();
			} else {
				Log.v("", "stop service");
				this.endPressureService();
			}
		} 
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

	
}
