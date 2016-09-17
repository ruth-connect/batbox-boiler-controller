package uk.me.ruthmills.batbox.boiler.service.impl;

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
	
	@PostConstruct
	public void initialise() {
		gpio = GpioFactory.getInstance();
		
		upLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "Up LED", PinState.HIGH);
		upLed.setShutdownOptions(true, PinState.LOW);
		
		hotWater = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09, "Hot Water", PinState.LOW);
		hotWater.setShutdownOptions(true, PinState.LOW);
		
		heating = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "Heating", PinState.LOW);
		heating.setShutdownOptions(true, PinState.LOW);
	}
	
	@PreDestroy
	public void shutdown() {
		gpio.shutdown();
	}

	@Override
	public void off() {
		hotWater.low();
		heating.low();
	}
	
	@Override
	public void hotWaterOnly() {
		hotWater.high();
		heating.low();
	}
	
	@Override
	public void heatingAndHotWater() {
		hotWater.high();
		heating.high();
	}
	
	@Override()
	public void upLedOff() {
		upLed.low();
	}

	@Override
	public void upLedOn() {
		upLed.high();
	}
}
