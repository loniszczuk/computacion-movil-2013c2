package flight.recorder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity {

  private GoogleMap map;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map);
    
    // Check status of Google Play Services
    int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

    // Check Google Play Service Available
    if (status != ConnectionResult.SUCCESS) { //Google Play Service is not Available
        	//Open Google Maps via URi Intent
    		double latitude = -34.5430098;
    		double longitude =-58.4410214;
    		String label = "Estas Aquí";
    		String uriBegin = "geo:" + latitude + "," + longitude;
    		String query = latitude + "," + longitude + "(" + label + ")";
    		String encodedQuery = Uri.encode(query);
    		String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
    		Uri uri = Uri.parse(uriString);
    		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
    		this.startActivity(intent);
    		
    		finish();
    		
    }else{
    	  	LatLng LOCATION = new LatLng(53.558, 9.927);
    	
        	map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        	MarkerOptions marker = new MarkerOptions().position(LOCATION).title("Estas Aquí");
            
            Marker mark = map.addMarker(new MarkerOptions());
            
            // Move the camera instantly with a zoom of 15.
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LOCATION, 15));

            // Zoom in, animating the camera.
            map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        	
    }
    
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.settings, menu);
    return true;
  }

} 