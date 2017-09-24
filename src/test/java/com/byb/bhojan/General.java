package com.byb.bhojan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.byb.bhojan.model.User;
import com.byb.bhojan.model.UserProfile;
import com.byb.bhojan.util.RandomStringGenerator;

public class General {

	private static final String FILE_NAME = "G:\\Roshan\\Projects\\My mess docs\\Members.xlsx";

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
			int j = 0;
			LinkedList<User> messOwners = new LinkedList<User>();
			while (iterator.hasNext()) {

				System.out.println(j++);

				User messOwner = new User();
				UserProfile messOwnerProfile = new UserProfile();

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				int i = 0;
				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();

					if (i == 0) {
						messOwnerProfile.setFirstName(currentCell.toString());
					}

					if (i == 1) {
						messOwnerProfile.setLastName(currentCell.toString());
					}
					i++;
				}

				messOwner.setEmail(RandomStringGenerator.getNewEmail(messOwnerProfile));
				messOwner.setMobileNumber(RandomStringGenerator.getNewMobileNumber());
				messOwner.setUserProfile(messOwnerProfile);

				messOwners.add(messOwner);
			}

			messOwners.removeLast();

			System.out.println(messOwners);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
