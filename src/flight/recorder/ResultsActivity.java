package flight.recorder;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;

public class ResultsActivity extends Activity{
	
	private ResultsModel model;
	private ResultsView view;
	private ResultsController controller;

//	ImageView image;
	
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
        

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);

          
        
        //Generate Altitude Graphic
        GraphViewSeriesStyle styleA = new GraphViewSeriesStyle(Color.rgb(200, 100, 0), 3);
        GraphViewSeries altitudSeries = createSerie(altitudes, styleA);
        GraphView altitudesGraph = new LineGraphView(this, "Altitudes");
        altitudesGraph.addSeries( altitudSeries );
        altitudesGraph.getGraphViewStyle().setTextSize(20);
        LinearLayout layoutA = (LinearLayout) findViewById(R.id.graph1);
		layoutA.addView(altitudesGraph);
        
		//Generate Speed Graphic
		GraphViewSeriesStyle styleS = new GraphViewSeriesStyle(Color.rgb(100, 200, 50), 3);
		GraphViewSeries speedSeries = createSerie(speeds, styleS);
		GraphView speedsGraph = new LineGraphView(this, "Speeds");
		speedsGraph.addSeries(speedSeries);
		speedsGraph.getGraphViewStyle().setTextSize(15);
		LinearLayout layoutS = (LinearLayout) findViewById(R.id.graph2);
		layoutS.addView(speedsGraph);


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

    private GraphViewSeries createSerie(double[] data, GraphViewSeriesStyle style){
    	GraphViewData[] data_time = new GraphViewData[data.length];  
		for(int i = 0; i < data.length; i++){
			data_time[i] = new GraphViewData(i, data[i]);
		}
    		
		return new GraphViewSeries("", style, data_time);
    }
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
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
