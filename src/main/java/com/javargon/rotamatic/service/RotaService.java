package com.javargon.rotamatic.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.javargon.rotamatic.model.Row;
import com.javargon.rotamatic.model.SupportPerson;

/**
 * 
 * @author vivek
 *
 */
public class RotaService {

	private static List<Row> rows = new ArrayList<>();
	
	private static void makeRota(List<String> allTeamMates, LocalDate[] localDates) {
		resetRows();
		
		if(allTeamMates.size() != localDates.length) {
			throw new IllegalArgumentException("You shall not passsssssss!!!!!!");
		}
		
		int i=0;
		for(String name : allTeamMates) {
			SupportPerson p = SupportPersonService.getPerson(name);
			rows.add(new Row(localDates[i++], name, p.getMobNo(),
					p.getOfficeNo()));
		}
	}

	private static void printRota() {
		rows.forEach(row -> System.out.println(row+"\n"));
	}
	
	private static void resetRows() {
		rows.clear();
	}

	public static void prepareRota() throws IOException {
		SupportPersonService.loadAllSupportPersons();
		
		List<String> supportPersonsList = SupportPersonService.getAllSupportPersons();
		
		int noOfSupportDays = supportPersonsList.size();
		LocalDate[] supportDaysDuration = DateSpanService.getSupportSpan(noOfSupportDays);
		
		makeRota(supportPersonsList, supportDaysDuration);
		printRota();
		
		SupportPersonService.updateCurrentCycleList(supportPersonsList);
	}
}
