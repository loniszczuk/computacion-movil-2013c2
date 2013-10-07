package flight.recorder;

import flight.recorder.R;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;	

public class SimpleView extends View {
	
	private final SimpleModel model;
	private final Activity activity;
	
	private final TextView textView;
	private final TextView pressureView;
	private final TextView speedView;
	
	public final static String EXTRA_MESSAGE = "com.my.app.MESSAGE";
	
	public SimpleView(Activity a, SimpleModel m) {
		super(a, null);
		this.model = m;
		this.activity = a;
		
		activity.setContentView(R.layout.activity_main);
		textView = (TextView) activity.findViewById(R.id.messageId);
		pressureView = (TextView) activity.findViewById(R.id.pressureViewId);
		speedView = (TextView) activity.findViewById(R.id.speedViewId);
	}

	public void renderOnOff() {
		textView.setText(model.isOn()? "Started": "Stoppped");
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
	
	public void renderSpeed(float p) {
		speedView.setText(String.valueOf(p));
		
	}
	
	public void switchButton(){
		getButtonOn().setVisibility(GONE);
		getButtonOff().setVisibility(VISIBLE);
		
	}

	public void showResults(){
		//TODO nueva activity -> mostrar grafico y publicar en fb
		Intent intent = new Intent(this.activity, ResultsActivity.class);
		//Empiezo la nueva activity
		this.activity.startActivity(intent);
		
	}
}
