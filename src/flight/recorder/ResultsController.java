package flight.recorder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class ResultsController implements OnClickListener {

	private final ResultsView view;
	private final ResultsModel model;
	
	/*facebook
	// application id from facebook.com/developers
	public static final String APP_ID = "536274329783175";
	// log tag for any log.x statements
	public static final String TAG = "FACEBOOK CONNECT";
	// permissions array
	private static final String[] PERMS = new String[] { "user_events" };
	// facebook vars
	private Facebook mFacebook;
	private AsyncFacebookRunner mAsyncRunner;
	// id text view
	private TextView mText;
 */
	
	public ResultsController(ResultsModel m, ResultsView v) {
		this.model = m;
		this.view = v;
	}

	@Override
	public void onClick(View view) {
		if (this.view.getButtonMap() == view) {
			this.view.showMap();
		}
	}
	
}
