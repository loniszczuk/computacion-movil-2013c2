package my.app;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SimpleView extends View {
	
	private final SimpleModel model;
	private final Activity activity;
	
	private final Button button;
	private final TextView altitudeView;
	
	
	public SimpleView(Activity a, SimpleModel m) {
		super(a, null);
		this.model = m;
		this.activity = a;
		
		activity.setContentView(R.layout.activity_main);
		button = (Button) activity.findViewById(R.id.button);
		altitudeView = (TextView) activity.findViewById(R.id.altitudeViewId);
	}

	public View getButton() {
		return this.button;
	}
	
	public MainActivity getActivity() {
		return (MainActivity) this.activity;
	}
	
	public void renderStartStop() {
		button.setText(model.isStarted() ? "STOP" : "START");
	}

	public void renderAltitude() {
		altitudeView.setText("Altitud actual: " + String.valueOf(model.getCurrentAltitude()));
	}

}
