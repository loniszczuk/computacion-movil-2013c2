package flight.recorder;

public class ResultsModel {

    private Boolean on = Boolean.FALSE;
    
    public Boolean isOn() {
        return on;
    }
    
    public void turnOn() {
        this.on = Boolean.TRUE;
    }
    
    public void turnOff() {
        this.on = Boolean.FALSE;
    }
}