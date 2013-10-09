package flight.recorder;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;


public class SimpleView extends View {
	
	private final SimpleModel model;
	private final Activity activity;
	
	private final TextView altitudeView;
	private final TextView speedView;
	
	public final static String EXTRA_MESSAGE = "com.my.app.MESSAGE";
	
	public SimpleView(Activity a, SimpleModel m) {
		super(a, null);
		this.model = m;
		this.activity = a;
		
		activity.setContentView(R.layout.activity_main);
		altitudeView = (TextView) activity.findViewById(R.id.pressureViewId);
		speedView = (TextView) activity.findViewById(R.id.speedViewId);

	}

	public MainActivity getActivity() {
		return (MainActivity) this.activity;
	}

	public View getButtonOn() {
		return activity.findViewById(R.id.buttonOn);
	}

	public View getButtonOff() {
		//mostrar mediciones
		return activity.findViewById(R.id.buttonOff);
	}

	public void renderAltitude() {
		altitudeView.setText("Altitud actual: " + String.valueOf(model.getCurrentAltitude()));
	}
	
	public void renderSpeed() {
		speedView.setText("Velocidad actual: " + String.valueOf(model.getCurrentSpeed()));
	}
	
	public void switchButton(){
		getButtonOn().setVisibility(GONE);
		getButtonOff().setVisibility(VISIBLE);
	}
}