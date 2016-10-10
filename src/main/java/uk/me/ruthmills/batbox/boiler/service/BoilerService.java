package uk.me.ruthmills.batbox.boiler.service;

public interface BoilerService {

	public void off();
	
	public void hotWaterOnly();
	
	public void heatingAndHotWater();
	
	public void updateStatus();
}
