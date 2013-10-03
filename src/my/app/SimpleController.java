package my.app;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class SimpleController implements OnClickListener {

	private final SimpleView view;
	private final SimpleModel model;


	public SimpleController(SimpleModel m, SimpleView v) {
		this.model = m;
		this.view = v;
	}

	@Override
	public void onClick(View view) {
		if (this.view.getButtonOn() == view) {
			this.model.turnOn();
			this.view.switchButton();
		} else if (this.view.getButtonOff() == view) {
			this.model.turnOff();
			this.view.showResults();

		}
		
		this.view.renderOnOff();
	}
	
	
	public void onPressureChanged(Context context, Intent intent) {
		float p = intent.getExtras().getFloat(SimpleService.PRESSURE);
		
		this.view.renderPressure(p);
	}
	
}
