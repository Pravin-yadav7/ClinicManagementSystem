package clinic.management.system;
/**
 * this class is used for perform CRUD operations on Lab Test
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.InsertUpdateDelete;
import connection.Select;

public class LabTest {

	int testId;
	String testName;
	Integer testRate;
	String Query;

	/**
	 * this method take choice from admin and perform operations like add Lab
	 * Test, delete Lab Test, update Lab Test and see Lab Test list
	 */

	public void labTestCRUDOperations() {

		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.FINE);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.FINE);
		logger.addHandler(handler);

		Scanner in = new Scanner(System.in);
		System.out.println("1. Add Test\n2. Update Test data\n3. See Test List\n4. Delete Test Data\n5. Back");
		System.out.println("Enter choice:");

		int choice = in.nextInt();
		switch (choice) {

		case 1:
			System.out.println("Enter Test Name:");
			testName = in.next();

			System.out.println("Enter Rate of Test:");
			testRate = in.nextInt();

			if (testName.equals("") || testRate.equals("")) {

				System.out.println("All fields are mandadatary");

			} else {

				try {

					String Query;
					Query = "insert into labtest values(testId, '" + testName + "', '" + testRate + "')";
					InsertUpdateDelete.setData(Query, "Lab Test Data added successfully");

				} catch (Exception e) {

					logger.log(Level.FINE, e.getMessage());
				}
			}

			LabTest lt = new LabTest();
			lt.labTestCRUDOperations();
			break;

		case 2:
			System.out.println("Enter Test id:");
			testId = in.nextInt();

			System.out.println("Enter Test Name:");
			testName = in.next();

			System.out.println("Enter Rate of Test:");
			testRate = in.nextInt();

			try {

				Query = "update labtest set testName='" + testName + "', testRate='" + testRate + "' where testId='"
						+ testId + "'";
				InsertUpdateDelete.setData(Query, "Test data updated successfully");

			} catch (Exception e) {

				logger.log(Level.FINE, e.getMessage());
			}

			lt = new LabTest();
			lt.labTestCRUDOperations();

			break;

		case 3:
			System.out.println("Data of Doctors");
			System.out.printf("%-22s%-22s%-22s\n", "TestId", "TestName", "TestRate");

			ResultSet rs = Select.getData("select * from labtest");
			try {
				while (rs.next()) {

					testId = rs.getInt(1);
					testName = rs.getString(2);
					testRate = rs.getInt(3);

					System.out.printf("%-22d%-22s%-22d\n", testId, testName, testRate);

				}
			} catch (SQLException e) {

				logger.log(Level.FINE, e.getMessage());
			}

			lt = new LabTest();
			lt.labTestCRUDOperations();
			break;

		case 4:
			System.out.println("Enter Test id:");
			testId = in.nextInt();

			System.out.println("Do you really want to delete test data \n press Y for yes and N for no:");
			char ch = in.next().charAt(0);
			if (ch == 'Y') {

				try {

					Query = "delete from labtest where testId = '" + testId + "'";
					InsertUpdateDelete.setData(Query, "Test data deleted");

				} catch (Exception e) {

					logger.log(Level.FINE, e.getMessage());
				}

			}

			lt = new LabTest();
			lt.labTestCRUDOperations();
			break;

		case 5:
			Admin a = new Admin();
			a.adminOperations();
			break;

		}

	}

}
