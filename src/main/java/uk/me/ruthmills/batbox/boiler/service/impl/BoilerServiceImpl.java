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
	private GpioPinDigitalOutput powerLed;
	private GpioPinDigitalOutput hotWater;
	private GpioPinDigitalOutput heating;
	
	@PostConstruct
	public void initialise() {
		gpio = GpioFactory.getInstance();
		
		powerLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Power LED", PinState.HIGH);
		powerLed.setShutdownOptions(true, PinState.LOW);
		
		hotWater = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Hot Water", PinState.LOW);
		hotWater.setShutdownOptions(true, PinState.LOW);
		
		heating = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Heating", PinState.LOW);
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
}
