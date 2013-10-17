package flight.recorder;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
 
public class CallActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        call();
    }
 
    private void call() {
	    //try {
	        Intent callIntent = new Intent(Intent.ACTION_CALL);
	        callIntent.setData(Uri.parse("tel:123456789"));
	        startActivity(callIntent);
	  //  } catch (ActivityNotFoundException activityException) {
	//         Log.e("helloandroid dialing example", "Call failed", e);
//	    }
	}
 
}
