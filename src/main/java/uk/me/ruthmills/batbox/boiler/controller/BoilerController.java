package uk.me.ruthmills.batbox.boiler.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import uk.me.ruthmills.batbox.boiler.service.BoilerService;

@Controller
@RequestMapping("/boiler")
public class BoilerController {
	
	@Autowired
	private BoilerService boilerService;

	@RequestMapping(value = "/off", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void off() throws IOException {
		boilerService.off();
	}

	@RequestMapping(value = "/hotWaterOnly", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void hotWaterOnly() throws IOException {
		boilerService.hotWaterOnly();
	}
	
	@RequestMapping(value = "/heatingAndHotWater", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void heatingAndHotWater() throws IOException {
		boilerService.heatingAndHotWater();
	}
	
	@RequestMapping(value = "/upLedOff", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void upLedOff() throws IOException {
		boilerService.upLedOff();
	}
	
	@RequestMapping(value = "/upLedOn", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void upLedOn() throws IOException {
		boilerService.upLedOn();
	}
}
