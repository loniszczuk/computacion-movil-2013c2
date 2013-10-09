package flight.recorder;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultsActivity extends Activity{
	
	private ResultsModel model;
	private ResultsView view;
	private ResultsController controller;

	ImageView image;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = this.getIntent();
        double[] altitudes = i.getExtras().getDoubleArray("ALTITUDES");
        double[] speeds = i.getExtras().getDoubleArray("SPEEDS");
        
        model = new ResultsModel(toList(altitudes), toList(speeds));
        view = new ResultsView(this, model);
        controller = new ResultsController(model, view);
        
        SaveResultsTask task = new SaveResultsTask();
        task.execute(this.controller);

        // Set the text view as the activity layout
        setContentView(R.layout.activity_results);
        
        //String message = intent.getStringExtra(SimpleView.EXTRA_MESSAGE);

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        //textView.setText(message);

		image = (ImageView) findViewById(R.id.imageView1);
		image.setImageResource(R.drawable.ej_chart);


        findViewById(R.id.buttonFb).setOnClickListener(controller);
        findViewById(R.id.buttonMap).setOnClickListener(controller);
    }


    private List<Double> toList(double[] array) {
    	List<Double> ret = new ArrayList<Double>();
    	for( double d: array) {
    		ret.add(d);
    	}
		return ret;
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    }

    @Override
    protected void onPause() {
    	super.onPause();
    }
    
    
    
}
