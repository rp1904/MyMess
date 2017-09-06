package com.byb.bhojan.util;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class Scheduler {

	// @Autowired
	// private MealServices mealServices;

	// @Scheduled(cron = "0 2 0 1/1 * ? *")
	// public void closeAllOpenMeals() {
	// mealServices.closeAllOpenedMeals();
	// }

	// @Scheduled(cron = "10 * * * * *")
	// public void cronTask() {
	// System.out.println(new Date() + " This runs in a cron schedule");
	// }

}
