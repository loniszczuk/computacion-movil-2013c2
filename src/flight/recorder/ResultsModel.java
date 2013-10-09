package flight.recorder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ResultsModel {
	
	private List<Double> altitudes;
	private List<Double> speeds;
	
	public ResultsModel(List<Double> altitudes, List<Double> speeds) {
		this.altitudes = altitudes;
		this.speeds = speeds;
	}

	public void saveResults() {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL("http://64.62.188.108/");
			connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    
		    byte[] data = ("altitudes: " + altitudes.toString() + " speeds: " + speeds.toString()).getBytes("UTF-8");
		    connection.setRequestProperty("Content-Length", String.valueOf(data.length));
		    OutputStream out = connection.getOutputStream();
		    out.write(data);
		    out.flush();
		    out.close();
		    
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer(); 
			while((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
	    
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	

}