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
		
		upLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "Up LED", PinState.LOW);
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
		hotWater.high();
		heating.high();
	}
	
	@Override
	public void hotWaterOnly() {
		hotWater.low();
		heating.high();
	}
	
	@Override
	public void heatingAndHotWater() {
		hotWater.low();
		heating.low();
	}
}
