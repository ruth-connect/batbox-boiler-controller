package uk.me.ruthmills.batbox.boiler.service.impl;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import uk.me.ruthmills.batbox.boiler.service.BoilerService;

@Service
public class BoilerServiceImpl implements BoilerService {
	
	private GpioController gpio;
	private GpioPinDigitalOutput upLed;
	private GpioPinDigitalOutput hotWater;
	private GpioPinDigitalOutput heating;
	
	private boolean hotWaterOn = false;
	private boolean heatingOn = false;
	
	private long lastCommandReceivedTime = 0L;
	
	@PostConstruct
	public void initialise() {
		gpio = GpioFactory.getInstance();
		
		upLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "Up LED", PinState.HIGH);
		upLed.setShutdownOptions(true, PinState.HIGH);
		
		hotWater = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09, "Hot Water", PinState.HIGH);
		hotWater.setShutdownOptions(true, PinState.HIGH);
		
		heating = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "Heating", PinState.HIGH);
		heating.setShutdownOptions(true, PinState.HIGH);
	}
	
	@PreDestroy
	public void shutdown() {
		gpio.shutdown();
	}

	@Override
	public void off() {
		updateLastCommandReceivedTime();
		hotWaterOn = false;
		heatingOn = false;
	}
	
	@Override
	public void hotWaterOnly() {
		updateLastCommandReceivedTime();
		hotWaterOn = true;
		heatingOn = false;
	}
	
	@Override
	public void heatingAndHotWater() {
		updateLastCommandReceivedTime();
		hotWaterOn = true;
		heatingOn = true;
	}

	@Override
	public void updateStatus() {
		if (new Date().getTime() - lastCommandReceivedTime > 315000L) {
			upLed.high();
			hotWater.high();
			heating.high();
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
			}
		} else {
			if (hotWaterOn || heatingOn) {
				hotWater.low();
			} else {
				hotWater.high();
			}
			
			if (heatingOn) {
				heating.low();
			} else {
				heating.high();
			}
		}
		upLed.low();
	}
	
	private void updateLastCommandReceivedTime() {
		lastCommandReceivedTime = new Date().getTime();		
	}
}
