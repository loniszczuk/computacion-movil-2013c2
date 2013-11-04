package flight.recorder;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

public class ResultsView extends View {
	
	private final ResultsModel model;
	private final Activity activity;
	
	private final TextView textView;


	public ResultsView(Activity a, ResultsModel m) {
		super(a, null);
		this.model = m;
		this.activity = a;
		
		activity.setContentView(R.layout.activity_results);
		textView = (TextView) activity.findViewById(R.id.messageId);

	}

	public View getButtonMap() {
		return activity.findViewById(R.id.buttonMap);
	}
	
	public View getButtonFb() {
		return activity.findViewById(R.id.buttonFb);
	}

	
	public void showMap() {
		
		Intent intent = new Intent(this.activity, MapActivity.class);
		//Start new activity
		this.activity.startActivity(intent);
	
	}

	
	public void share(){
		Intent sharingIntent = new Intent(Intent.ACTION_SEND);
		sharingIntent.setType("text/plain");
		sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "New amazing flight!");
		sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "just landed :)");
		this.activity.startActivity(Intent.createChooser(sharingIntent, "Share using"));
	
	}


}
