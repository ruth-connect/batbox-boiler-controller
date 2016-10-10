package uk.me.ruthmills.batbox.boiler.polling;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import uk.me.ruthmills.batbox.boiler.service.BoilerService;

@Component
public class InactivityPoller {
	
	private static final Logger LOGGER = Logger.getLogger(InactivityPoller.class);

	@Autowired
	private BoilerService boilerService;

	@Scheduled(cron = "*/1 * * * * *")
	public void checkInactivity() {
		LOGGER.info("CHECK INACTIVITY");
		boilerService.checkInactivity();
	}
}
