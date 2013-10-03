package my.app;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class ResultsView extends View {
	
	private final ResultsModel model;
	private final Activity activity;
	
	private final TextView textView;
	//TODO grafico

	public ResultsView(Activity a, ResultsModel m) {
		super(a, null);
		this.model = m;
		this.activity = a;
		
		activity.setContentView(R.layout.activity_results);
		textView = (TextView) activity.findViewById(R.id.messageId);

	}

	public void renderOnOff() {
		textView.setText(model.isOn()? "Started": "Stoppped");
	}

	public View getButtonOn() {
		//mostrar mediciones
		return activity.findViewById(R.id.buttonOn);
	}

	public View getButtonOff() {
		return activity.findViewById(R.id.buttonOff);
	}

	

}
