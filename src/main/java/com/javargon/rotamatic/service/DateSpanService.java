package com.javargon.rotamatic.service;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * 
 * @author vivek
 *
 */
public class DateSpanService {

	public static LocalDate[] getSupportSpan(int noOfSupportDays) throws IOException {
		LocalDate today = LocalDate.now();
		LocalDate[] supportDays = new LocalDate[noOfSupportDays];
		supportDays[0] = today;

		for (int i = 1; i < noOfSupportDays; i++) {
			LocalDate temp = today.plusDays(1);
			while (shouldSkip(temp)) {
				temp = temp.plusDays(1);
			}
			supportDays[i] = temp;
			today = temp;
		}
		return supportDays;
	}

	private static boolean isWeekend(LocalDate date) {
		return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY ? true : false;
	}
	
	private static boolean isHoliday(LocalDate date) {
		//TODO
		return false;
	}
	
	private static boolean shouldSkip(LocalDate date) {
		return isWeekend(date) || isHoliday(date);
	}
}
