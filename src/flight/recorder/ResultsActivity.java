package flight.recorder;

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
        
        model = new ResultsModel();
        view = new ResultsView(this, model);
        controller = new ResultsController(model, view);

        // Set the text view as the activity layout
        setContentView(R.layout.activity_results);
        
        Intent intent = getIntent();
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
