package com.byb.bhojan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class General {

	private static final String FILE_NAME = "G:\\Roshan\\Projects\\My mess docs\\NewMesses.xlsx";

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
		List<String> names = new ArrayList<String>(Arrays.asList("Vithu Mauli Mess", "Vahini's Mess", "Andhra Mess", "Kerala Mess Service", "Badshahi Restaurant", "Satvik Mess", "Aware Maratha Khanawal", "MONTHLY MESS", "Mamta Mess", "RAI MONTHLY MESS", "Dining Hall",
				"Murlidhar Mess", "sharvari caterers", "Mahalaxmi Mess", "Kulkarni Mess", "Neelam Restaurant", "Andhra Mess", "trupti mess", "New Milan Khanawala", "Annapurna Pure", "Rasoi Mess", "Kerala Fast Food", "Anuradha Mess", "Punjabi Mess",
				"Reddys Andhra Meals", "Zakas Food", "Shri Murlidhar Mess", "Krushnai Night Kitchen", "Ruchira", "Vasant Utsav Hotel", "Monthly Mess", "Annapurna Mess", "Bedekar Mess", "Velhal Mess", "Sai Mess", "Vhatkar Mess", "Sai Mess", "HARDIK MESS",
				"Mugdar Mess", "Mama Mami Mess", "Mayur Sai Mess", "Sujit Rohit Mess", "Avanti Food Point", "Atharv Mess", "Tulasi Mess", "Naik Mess", "Shree Gurudev Mess", "My Tiffin Service", "Samarth Mess", "Delhi Mess", "Mahaveer Mess", "Charaka Mess",
				"Manisha Mess", "Lakshmi Mess", "Annapurneshwari Thali", "Padmashree Mess", "Hotel Shreya", "Laxmi Mess", "Anand Mess", "Sai Mess", "Delhi Mess", "Vijay Mess", "Shree Jagdamba Mess", "Guru Krupa Mess", "Sai Mess", "Annapurna Mess", "Sangam Mess",
				"Rahul Mess", "Kanchan Mess", "Chowgule Mess", "Samadhan Mess", "Mangal Mess", "Laxmi Mess", "OM Mess", "Annapurna Mess", "Deepa Mess", "Suruchi Mess", "Atithi Mess", "Sai Mess", "Patil`s Mess", "Shree Gajanan Mess", "Shree Banshankari Mess",
				"shree basaveshwar mess", "Shri Renuka Mess", "Majukar Mess", "Sai Bhojnalay Mess", "Hotel Shri Laxmi", "Annapurneshwari Thali Restaurant", "Renu Mess", "South Indian Mess", "Neelam Mess", "Anesha Mess", "Rajkamal Mess", "LAXMI RESTAURANT",
				"Royal Mess", "Paliwal Mess", "Bansod Mess", "PRANAY MESS CENTER", "Jagdip Mess Bhojnalay", "Maa Durga Mess", "Manjari Panhalkar Mess", "Nikhath Mess Center", "Mega Mess", "Suder Mess", "Shree Mess", "Shree Sai Samarth Mess", "Anshul Mess",
				"Kathole Bro's Mess", "Chetna Mess", "S.K MESS", "R.D. Mess", "Jai Shri Krishna Mess", "SUDHA MESS", "Tannu Mess", "Deshmukh Mess", "Suhana Mess", "Mahalaxmi Mess", "Parate's Mess", "Rohini mess", "Lucky Mess", "Soni Mess", "Gurumauli Mess",
				"Bhole Mess", "Andhra Mess", "Shiva Mess", "Ma Gayatri Mess"));
		for (String s : names) {
			System.out.println("-->" + s);
		}
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
			int i = 0;
			StringBuilder names = new StringBuilder();
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					currentCell.setCellType(Cell.CELL_TYPE_STRING);
					System.out.println(currentCell.toString());
					names.append("\"").append(currentCell.toString()).append("\",");
				}
				i++;
			}

			System.out.println(names.toString());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
