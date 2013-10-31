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
        //Open Google Maps
		double latitude = -34.5430098;
		double longitude =-58.4410214;
		String label = "Estas Aquí";
		String uriBegin = "geo:" + latitude + "," + longitude;//String.format(Locale.ENGLISH, "geo:%f,%f", latitud, longitud	);
		String query = latitude + "," + longitude + "(" + label + ")";
		String encodedQuery = Uri.encode(query);
		String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
		Uri uri = Uri.parse(uriString);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		activity.startActivity(intent);
	}

	
	public void connectFb(){
		Intent fb_intent = new Intent(this.activity, FacebookActivity.class);
		//Empiezo la nueva activity
		this.activity.startActivity(fb_intent);
	
	}

	

	

}
