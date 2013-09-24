package my.app;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class SimpleView extends View {
	
	private final SimpleModel model;
	private final Activity activity;
	
	private final TextView textView;
	private final TextView pressureView;
	
	public SimpleView(Activity a, SimpleModel m) {
		super(a, null);
		this.model = m;
		this.activity = a;
		
		activity.setContentView(R.layout.activity_main);
		textView = (TextView) activity.findViewById(R.id.messageId);
		pressureView = (TextView) activity.findViewById(R.id.pressureViewId);
	}

	public void renderOnOff() {
		textView.setText(model.isOn()? "ON": "OFF");
	}

	public View getButtonOn() {
		return activity.findViewById(R.id.buttonOn);
	}

	public View getButtonOff() {
		return activity.findViewById(R.id.buttonOff);
	}

	public void renderPressure(float p) {
		pressureView.setText(String.valueOf(p));
		
	}

}
