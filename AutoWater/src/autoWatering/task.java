package autoWatering;

import org.firmata4j.Pin;
import org.firmata4j.ssd1306.SSD1306;
import java.util.TimerTask;

public class task extends TimerTask {

    private final Pin sensor;
    private	final SSD1306 screen;
    private final Pin pump;

    task(Pin sensor, SSD1306 screen, Pin pump) {
        this.sensor = sensor;
        this.screen = screen;
        this.pump = pump;
    }


    @Override
    public void run() {
        String valueString = String.valueOf(sensor.getValue());
        screen.getCanvas().clear();
        screen.getCanvas().drawString(0, 0, valueString);
        screen.display();
        var value = (double) sensor.getValue();

        try {
            if(value > 600) {
                pump.setValue(1);
            System.out.println("Pumping water");}
            else {
                pump.setValue(0);
           System.out.println("soil is wet");}
        }

        catch (Exception exception) {
            System.out.println("An error has occurred.");
        }

    }
}
