package com.javargon.rotamatic.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

	public static List<String> readFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);

		if (Files.exists(path)) {
			return Files.readAllLines(path);
		}
		return null;
	}
	
	public static void writeListToFile(String filePath, List<String> dataList) throws IOException {
		Path path = Paths.get(filePath);

		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			for (String data : dataList) {
				writer.write(data+"\n");
			}
		}
	}
	
	public static void clearFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);

		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write("");
		}
	}
}
