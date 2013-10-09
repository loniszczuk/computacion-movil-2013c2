package flight.recorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FacebookActivity extends Activity{
	
	//private FacebookModel model;
	//private FacebookView view;
	//private FacebookController controller;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //model = new ResultsModel();
        //view = new ResultsView(this, model);
        //controller = new ResultsController(model, view);

        // Set the text view as the activity layout
        setContentView(R.layout.activity_facebook);
        
        Intent intent = getIntent();

        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
	        public void onClick(View view) {
	        	//TODO
	        }
	    });


                
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
