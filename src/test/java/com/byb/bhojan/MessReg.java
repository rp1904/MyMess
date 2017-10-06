package com.byb.bhojan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.byb.bhojan.model.CreatedUpdated;
import com.byb.bhojan.model.MealCoupen;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.model.UserProfile;
import com.byb.bhojan.services.MealCoupenServices;
import com.byb.bhojan.services.MessServices;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.util.ProjectConstant;
import com.byb.bhojan.util.RandomStringGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/mvc-dispatcher-servlet.xml", "classpath:/spring-email.xml" })
@ComponentScan(basePackages = { "com.byb.bhojan" })
@WebAppConfiguration
public class MessReg {

	@Autowired
	public UserServices userServices;

	@Autowired
	public MessServices messServices;

	@Autowired
	private MealCoupenServices mealCoupenServices;

	private static final String FILE_NAME = "G:\\Roshan\\Projects\\My mess docs\\Messes.xlsx";

	@Test
	public void test() {

		try {

			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			int j = 0;
			LinkedList<Mess> messes = new LinkedList<Mess>();
			while (iterator.hasNext()) {

				Mess mess = new Mess();
				User messOwner = new User();
				UserProfile messOwnerProfile = new UserProfile();

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				int i = 0;
				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();

					System.out.println(currentCell.toString());

					if (i == 0) {
						messOwnerProfile.setFirstName(currentCell.toString());
					}

					if (i == 1) {
						messOwnerProfile.setLastName(currentCell.toString());
					}

					if (i == 2) {
						mess.setMessName(currentCell.toString());
					}
					i++;
				}

				messOwner.setEmail(RandomStringGenerator.getNewEmail(messOwnerProfile));
				messOwner.setMobileNumber(RandomStringGenerator.getNewMobileNumber());
				messOwner.setUserProfile(messOwnerProfile);
				mess.setMessOwner(messOwner);

				messes.add(mess);
			}

			//			messes.removeLast();

			for (Mess mess : messes) {

				mess.setMessId(RandomStringGenerator.getMessCode(mess.getMessName()));
				mess.getMessOwner().setUserRole(userServices.getUserRoleById(ProjectConstant.USER_ROLE_ID_MESS));
				CreatedUpdated createdUpdated = new CreatedUpdated(ProjectConstant.CREATEDBY_SELF);
				mess.setCreatedUpdated(createdUpdated);
				mess.getMessOwner().setCreatedUpdated(createdUpdated);
				mess.setDaysRemaining(15);
				messServices.saveMess(mess);

				MealCoupen mealCoupen = new MealCoupen();
				mealCoupen.setAmount(1200);
				mealCoupen.setNoOfMeals(30);
				mealCoupen.setValidity(35);
				mealCoupen.setMess(mess);
				mealCoupen.setCreatedUpdated(new CreatedUpdated(mess.getMessOwner().getUserIdPk()));
				mealCoupenServices.saveMealCoupen(mealCoupen);

				MealCoupen mealCoupen2 = new MealCoupen();
				mealCoupen2.setAmount(2000);
				mealCoupen2.setNoOfMeals(60);
				mealCoupen2.setValidity(65);
				mealCoupen2.setMess(mess);
				mealCoupen2.setCreatedUpdated(new CreatedUpdated(mess.getMessOwner().getUserIdPk()));
				mealCoupenServices.saveMealCoupen(mealCoupen2);
			}

			System.out.println("Total Mess Inserted: " + messes.size());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
