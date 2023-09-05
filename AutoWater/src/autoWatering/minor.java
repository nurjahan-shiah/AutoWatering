package autoWatering;

import org.firmata4j.*;
import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.ssd1306.SSD1306;
import java.io.IOException;
import java.util.Timer;


public class minor {

    public static void main(String[] args)
            throws IOException, InterruptedException {

        String portID = "COM3";
        IODevice arduinoObject = new FirmataDevice(portID);

        arduinoObject.start();
        arduinoObject.ensureInitializationIsDone();



        var i2cObject = arduinoObject.getI2CDevice((byte) 0x3C);
        SSD1306 oledScreen = new SSD1306(i2cObject, SSD1306.Size.SSD1306_128_64);
        oledScreen.init();

        var moistureSensor = arduinoObject.getPin(15);
        moistureSensor.setMode(Pin.Mode.ANALOG);
        var waterPump = arduinoObject.getPin(2);
        waterPump.setMode(Pin.Mode.OUTPUT);


        var Task = new task(moistureSensor, oledScreen, waterPump);


        Timer timerObject = new Timer();
        timerObject.schedule(Task, 0, 1000);
    }

}

