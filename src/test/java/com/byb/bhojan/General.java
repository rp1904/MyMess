package com.byb.bhojan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class General {
	
	private static final String FILE_NAME = "/home/akshay/Desktop/test.xlsx";

	public static void main(String[] args) {

//		Random r = new Random();
//		Set<String> nos = new HashSet<String>();
//
//		int min = 71;
//		int max = 99;
//
//		int min2 = 10000000;
//		int max2 = 99999999;
//
//		while (nos.size() < 100) {
//
//			String str = String.valueOf(r.nextInt((max - min) + 1) + min);
//			String str2 = String.valueOf(r.nextInt((max2 - min2) + 1) + min2);
//			nos.add(str + str2);
//		}
//
//		System.out.println(getNewMobileNumber(nos, 500));
		
		readExcelFile();
	}

	public static Set<String> getNewMobileNumber(Set<String> existingNos, int limit) {

		Random r = new Random();
		Set<String> nos = new HashSet<String>();
		Set<String> all = new HashSet<String>();

		all.addAll(existingNos);

		int min = 71;
		int max = 99;

		int min2 = 10000000;
		int max2 = 99999999;

		while (nos.size() < limit) {

			String str = String.valueOf(r.nextInt((max - min) + 1) + min);
			String str2 = String.valueOf(r.nextInt((max2 - min2) + 1) + min2);
			String finalStr = str + str2;
			if (all.add(finalStr)) {
				nos.add(finalStr);
			}
		}

		System.out.println("Existing Nos : " + existingNos.size());
		System.out.println("New Nos : " + nos.size());
		System.out.println("All : " + all.size());

		return nos;
	}

	public static void readExcelFile() {
		try {

			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {

				    Cell currentCell = cellIterator.next();

				    if (currentCell.getCellTypeEnum() == CellType.STRING) {
		                        System.out.print(currentCell.getStringCellValue() + "--");
		                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
		                        System.out.print(currentCell.getNumericCellValue() + "--");
		                    }
					
				}
				System.out.println();

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
