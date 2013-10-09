package my.app;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

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
		if (this.view.getButtonOn() == view) {
			this.model.start();
			this.view.switchButton();
			Log.v("","Start service");
			this.startPressureService();
		} else if (this.view.getButtonOff() == view) {
			Log.v("", "Stop service");
			this.model.stop();
			this.endPressureService();
			this.showResults();
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
	
	public void showResults(){
		//TODO nueva activity -> mostrar grafico y publicar en fb
		Intent intent = new Intent(this.activity, ResultsActivity.class);
		//Empiezo la nueva activity
		this.activity.startActivity(intent);
		
	}


	
}
