package com.javargon.rotamatic.service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.javargon.rotamatic.model.SupportPerson;
import com.javargon.rotamatic.util.FileUtils;

/**
 * 
 * @author vivek
 *
 */
public class SupportPersonService {

	private static Map<String, SupportPerson> allSupportPersons;

	public static void loadAllSupportPersons() throws IOException {
		List<String> lines = FileUtils.readFile("/home/vivek/Downloads/supportPersons.csv");

		allSupportPersons = lines.stream().map(line -> line.split(","))
				.map(tempArr -> new SupportPerson(tempArr[0], tempArr[1], tempArr[2]))
				.collect(Collectors.toMap(t -> t.getName(), Function.identity(),
						(t1, t2) -> t1.getName().length() > t2.getName().length() ? t1 : t2, LinkedHashMap::new));
	}

	public static SupportPerson getPerson(String name) {
		return allSupportPersons.get(name);
	}

	public static List<String> excludeSupportPersons(List<String> supportPersonsList) throws IOException {
		List<String> updatedList = updateCurrentCycle(supportPersonsList);
		List<String> toBeRemovedFromCurrentCycle = getExcludedFromCurrentCycle();
		for (String person : toBeRemovedFromCurrentCycle) {
			updatedList.remove(person);
		}
		updateForNextCycle(toBeRemovedFromCurrentCycle);

		return updatedList;
	}

	private static List<String> updateCurrentCycle(List<String> supportPersonsList) throws IOException {
		List<String> mustDoNextCycleList = getMustDoNextCycle();

		for (String name : supportPersonsList) {
			mustDoNextCycleList.add(name);
		}
		return mustDoNextCycleList;
	}

	private static void updateForNextCycle(List<String> toBeRemovedFromCurrentCycle) throws IOException {
		FileUtils.writeListToFile("/home/vivek/Downloads/mustDoNextCycle.text", toBeRemovedFromCurrentCycle);
		FileUtils.clearFile("/home/vivek/Downloads/canNotDoCurrentCycle.text");
	}

	public static void updateCurrentCycleList(List<String> supportPersonsList) throws IOException {
		FileUtils.writeListToFile("/home/vivek/Downloads/currentCycle.txt", supportPersonsList);
	}

	private static List<String> getExcludedFromCurrentCycle() throws IOException {
		return FileUtils.readFile("/home/vivek/Downloads/canNotDoCurrentCycle.text");
	}

	private static List<String> getMustDoNextCycle() throws IOException {
		return FileUtils.readFile("/home/vivek/Downloads/mustDoNextCycle.text");
	}

	public static List<String> getAllSupportPersons() throws IOException {
		return readAll();
	}

	private static List<String> readAll() throws IOException {
		List<String> all = FileUtils.readFile("/home/vivek/Downloads/currentCycle.txt");
		return excludeSupportPersons(all);
	}
}
