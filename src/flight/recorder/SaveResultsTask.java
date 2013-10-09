package flight.recorder;

import android.os.AsyncTask;

public class SaveResultsTask extends AsyncTask<ResultsController, Void, String>{
 
	
	@Override
	protected String doInBackground(ResultsController... params) {
		ResultsController controller = params[0];
		controller.getModel().saveResults();		
		return null;
	}

}
