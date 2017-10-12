package com.byb.bhojan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
import com.byb.bhojan.model.MemberMealCoupen;
import com.byb.bhojan.model.MembershipRequest;
import com.byb.bhojan.model.Mess;
import com.byb.bhojan.model.User;
import com.byb.bhojan.model.UserProfile;
import com.byb.bhojan.services.MealCoupenServices;
import com.byb.bhojan.services.MemberMealCoupenHistoryServices;
import com.byb.bhojan.services.MemberMealCoupenServices;
import com.byb.bhojan.services.MembershipRequestServices;
import com.byb.bhojan.services.MessServices;
import com.byb.bhojan.services.UserServices;
import com.byb.bhojan.util.DateUtils;
import com.byb.bhojan.util.ProjectConstant;
import com.byb.bhojan.util.RandomStringGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/mvc-dispatcher-servlet.xml", "classpath:/spring-email.xml" })
@ComponentScan(basePackages = { "com.byb.bhojan" })
@WebAppConfiguration
public class MemberReg {

	@Autowired
	public UserServices userServices;

	@Autowired
	public MessServices messServices;

	@Autowired
	public MealCoupenServices mealCoupenServices;

	@Autowired
	public MemberMealCoupenServices memberMealCoupenServices;

	@Autowired
	private MembershipRequestServices membershipRequestServices;

	@Autowired
	private MemberMealCoupenHistoryServices memberMealCoupenHistoryServices;

	private static final String FILE_NAME_MESS = "G:\\Roshan\\Projects\\My mess docs\\Messes1.xlsx";

	private static final String FILE_NAME_MEMBERS = "G:\\Roshan\\Projects\\My mess docs\\Members1.xlsx";

	@Test
	public void test() {

		insertMess();
		insertMembers();

	}

	void insertMembers() {
		try {

			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_MEMBERS));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			List<Mess> messes = messServices.getAllMessess();
			LinkedList<User> members = new LinkedList<User>();
			while (iterator.hasNext()) {

				try {

					User member = new User();
					UserProfile memberProfile = new UserProfile();

					Row currentRow = iterator.next();
					Iterator<Cell> cellIterator = currentRow.iterator();
					int i = 0;
					while (cellIterator.hasNext()) {

						Cell currentCell = cellIterator.next();
						currentCell.setCellType(Cell.CELL_TYPE_STRING);

						System.out.println(currentCell.toString());

						if (i == 0) {
							member.setEmail(currentCell.toString());
						}

						if (i == 1) {
							memberProfile.setFirstName(currentCell.toString());
						}

						if (i == 2) {
							memberProfile.setLastName(currentCell.toString());
						}

						if (i == 3) {
							member.setMobileNumber(currentCell.toString());
						}

						i++;
					}

					member.setUserProfile(memberProfile);
					members.add(member);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			Collections.shuffle(members);

			int mn = 0;

			System.out.println("Total Members: " + members.size());
			for (Mess mess : messes) {

				System.out.println("Total Members - mn: " + (members.size() - mn));

				Random r = new Random();
				int Low = 35;
				int High = 80;
				int newID = r.nextInt(High - Low) + Low;
				System.out.println("Length: " + newID);
				for (int i = 0; i < newID; i++) {

					try {
						User user = members.get(mn);
						user.setUserRole(userServices.getUserRoleById(ProjectConstant.USER_ROLE_ID_MEMBER));
						user.setCreatedUpdated(new CreatedUpdated(ProjectConstant.CREATEDBY_SELF));
						user.setMess(mess);
						User savedMember = userServices.saveUser(user);

						MembershipRequest newMembershipRequest = new MembershipRequest();
						newMembershipRequest.setMember(savedMember);
						newMembershipRequest.setMess(mess);
						newMembershipRequest.setRequestStatus(ProjectConstant.MEMBERSHIP_REQUEST_ACCEPTED);
						newMembershipRequest.setCreatedUpdated(new CreatedUpdated(savedMember.getUserIdPk()));

						membershipRequestServices.saveMembershipRequests(newMembershipRequest);

						List<MealCoupen> mealCoupens = mealCoupenServices.getMealCoupensByMess(mess);

						int Low1 = 0;
						int High1 = 1;
						int newID1 = r.nextInt(High1 - Low1) + Low1;

						MealCoupen mealCoupen = mealCoupens.get(newID1);

						MemberMealCoupen memberMealCoupen = new MemberMealCoupen();
						memberMealCoupen.setMember(user);
						memberMealCoupen.setMealCoupen(mealCoupen);
						memberMealCoupen.setExpiryDate(DateUtils.getDateAfterDays(new Date(), mealCoupen.getValidity()));
						memberMealCoupen.setNoOfMeals(mealCoupen.getNoOfMeals());
						memberMealCoupen.setRemainingMealCount(mealCoupen.getNoOfMeals());
						memberMealCoupen.setStatus(ProjectConstant.MEAL_COUPEN_STATUS_ACTIVE);
						memberMealCoupen.setCreatedUpdated(new CreatedUpdated(mess.getMessOwner().getUserIdPk()));

						memberMealCoupenServices.saveMemberMealCoupen(memberMealCoupen);
						memberMealCoupenHistoryServices.saveMemberMealCoupen(savedMember.getUserIdPk(), mealCoupen.getCoupenId());

						System.out.println("Member :" + mn);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
					mn++;
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void insertMess() {
		try {

			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME_MESS));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			int j = 0;
			LinkedList<Mess> messes = new LinkedList<Mess>();
			while (iterator.hasNext()) {
				try {
					Mess mess = new Mess();
					User messOwner = new User();
					UserProfile messOwnerProfile = new UserProfile();

					Row currentRow = iterator.next();
					Iterator<Cell> cellIterator = currentRow.iterator();
					int i = 0;
					while (cellIterator.hasNext()) {

						Cell currentCell = cellIterator.next();

						currentCell.setCellType(Cell.CELL_TYPE_STRING);

						System.out.println(currentCell.toString());

						if (i == 0) {
							messOwner.setEmail(currentCell.toString());
						}

						if (i == 1) {
							messOwnerProfile.setFirstName(currentCell.toString());
						}

						if (i == 2) {
							messOwnerProfile.setLastName(currentCell.toString());
						}

						if (i == 3) {
							messOwner.setMobileNumber(currentCell.toString());
						}

						if (i == 4) {
							mess.setMessName(currentCell.toString());
						}
						i++;
					}

					messOwner.setUserProfile(messOwnerProfile);
					mess.setMessOwner(messOwner);
					System.out.println(mess);
					messes.add(mess);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			//			messes.removeLast();

			for (Mess mess : messes) {

				try {
					mess.setMessId(RandomStringGenerator.getMessCode(mess.getMessName()));
					mess.getMessOwner().setUserRole(userServices.getUserRoleById(ProjectConstant.USER_ROLE_ID_MESS));
					CreatedUpdated createdUpdated = new CreatedUpdated(ProjectConstant.CREATEDBY_SELF);
					mess.setCreatedUpdated(createdUpdated);
					mess.getMessOwner().setCreatedUpdated(createdUpdated);
					mess.setDaysRemaining(5);
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
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			System.out.println("Total Mess Inserted: " + messes.size());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
