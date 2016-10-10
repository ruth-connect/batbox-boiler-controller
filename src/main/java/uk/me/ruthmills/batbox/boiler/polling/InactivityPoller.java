package uk.me.ruthmills.batbox.boiler.polling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import uk.me.ruthmills.batbox.boiler.service.BoilerService;

@Component
public class InactivityPoller {

	@Autowired
	private BoilerService boilerService;

	@Scheduled(cron = "*/1 * * * * *")
	public void checkInactivity() {
		boilerService.checkInactivity();
	}
}
