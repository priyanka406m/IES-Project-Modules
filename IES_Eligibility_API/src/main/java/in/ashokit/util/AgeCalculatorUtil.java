package in.ashokit.util;

import java.time.Period;

import org.springframework.stereotype.Component;

@Component
public class AgeCalculatorUtil {
	public static String formatAge(Period age) {
		int years = age.getYears();
		int months = age.getMonths();
		int days = age.getDays();

		if (years == 0 && months == 0) {
			return days + " days";
		} else if (years == 0 && days == 0) {
			return months + " months";
		} else if (months == 0 && days == 0) {
			return years + " years";
		} else if (years == 0) {
			return months + " months and " + days + " days";
		} else if (months == 0) {
			return years + " years and " + days + " days";
		} else if (days == 0) {
			return years + " years and " + months + " months";
		} else {
			return years + " years, " + months + " months, and " + days + " days";
		}
	}

}
