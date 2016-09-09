package uk.me.ruthmills.batbox.boiler.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/boiler")
public class BoilerController {

	@RequestMapping(value = "/off", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void off() throws IOException {
	}

	@RequestMapping(value = "/hotWaterOnly", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void hotWaterOnly() throws IOException {
	}
	
	@RequestMapping(value = "/heatingAndHotWater", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void heatingAndHotWater() throws IOException {
	}
}
