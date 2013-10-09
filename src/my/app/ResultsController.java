package my.app;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class ResultsController implements OnClickListener {

	private final ResultsView view;
	private final ResultsModel model;
	
	
	public ResultsController(ResultsModel m, ResultsView v) {
		this.model = m;
		this.view = v;
	}

	@Override
	public void onClick(View view) {
		//TODO
	}
	
}
