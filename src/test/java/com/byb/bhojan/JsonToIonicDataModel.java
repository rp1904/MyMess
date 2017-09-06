package com.byb.bhojan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonToIonicDataModel {

	private static final String FILENAME = "G:\\Roshan\\Projects\\json.txt";

	public static void main(String[] args) {

		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null) {
				int firstQuoteIndex = sCurrentLine.indexOf("\"");
				int lastQuoteIndex = sCurrentLine.lastIndexOf("\"");
				if (sCurrentLine.contains("\"")) {

					String key = sCurrentLine.substring(firstQuoteIndex + 1, lastQuoteIndex);

					if (sCurrentLine.contains("{")) {
						System.out.println("\n");
						System.out.println("//***********************************");
						String className = key.substring(0, 1).toUpperCase() + key.substring(1);

						System.out
								.println("private " + key + ": " + className + "Model = new " + className + "Model();");
						System.out.println("public get $" + key + "(): " + className + "Model { \n \t return this."
								+ key + ";\n}");
						System.out.println("public set $" + key + "(value: " + className + "Model) { \n \t this." + key
								+ "=value;\n}\n");

					} else {
						System.out.println("private " + key + ": string;");
						System.out.println("public get $" + key + "(): string { \n \t return this." + key + ";\n}");
						System.out
								.println("public set $" + key + "(value: string) { \n \t this." + key + "=value;\n}\n");
					}

					if (sCurrentLine.contains("{") || sCurrentLine.contains("}")) {
						System.out.println("//-------------------------------");
					}
				}

				if (sCurrentLine.contains("},")) {
					System.out.println("//***********************************");
					System.out.println("\n");
				}
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

}
