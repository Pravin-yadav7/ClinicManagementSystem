package clinic.management.system;
/**
 * this class is used for perform CRUD operations on Patient
 */

import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.*;

import java.sql.*;

public class Patient {

	int id;
	String patientName;
	String address;
	String Disease;
	Integer age;
	Long contactNo;
	String Query;

	/**
	 * this method take choice from admin and perform operations like add
	 * patient, delete patient, update patient and see patient list
	 */

	public void patientCRUDOperations() {

		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.FINE);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.FINE);
		logger.addHandler(handler);

		Patient p = new Patient();
		Scanner in = new Scanner(System.in);
		System.out.println("1. Add Patient\n2. Update Patient data\n3. See Patient List\n4. Delete Patient\n5. Back");
		System.out.println("Enter choice:");

		int choice = in.nextInt();

		switch (choice) {

		case 1:
			System.out.println("Enter Name:");
			patientName = in.next();

			System.out.println("Enter Address:");
			address = in.next();

			System.out.println("Enter Disease:");
			Disease = in.next();

			System.out.println("Enter Age:");
			age = in.nextInt();

			System.out.println("Enter Contact Number:");
			contactNo = in.nextLong();

			if (patientName.equals("") || address.equals("") || Disease.equals("") || age.equals("")
					|| contactNo.equals("")) {

				System.out.println("All fields are mandadatary");
			} else {
				try {

					Query = "insert into patient values(id,'" + patientName + "', '" + address + "', '" + Disease
							+ "', '" + age + "', '" + contactNo + "')";
					InsertUpdateDelete.setData(Query, "Patient data added succefully");
				} catch (Exception e) {

					logger.log(Level.FINE, e.getMessage());
				}
			}

			p.patientCRUDOperations();
			break;

		case 2:
			System.out.println("Enter id:");
			id = in.nextInt();

			System.out.println("Enter Name:");
			patientName = in.next();

			System.out.println("Enter Address:");
			address = in.next();

			System.out.println("Enter Disease:");
			Disease = in.next();

			System.out.println("Enter Age:");
			age = in.nextInt();

			System.out.println("Enter Contact Number:");
			contactNo = in.nextLong();

			try {
				Query = "update patient set patientName='" + patientName + "', address='" + address + "', Disease='"
						+ Disease + "', age='" + age + "', contactNo='" + contactNo + "' where id='" + id + "'";
				InsertUpdateDelete.setData(Query, "Patient data updated  succefully");

			} catch (Exception e) {

				logger.log(Level.FINE, e.getMessage());
			}

			p.patientCRUDOperations();

			break;

		case 3:

			System.out.println("data of patient");
			System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n", "Id", "PatientName", "Address", "Disease", "Age",
					"ContactNo");
			System.out.printf(
					"-----------------------------------------------------------------------------------------------------------------------------------");

			System.out.println("\n");

			ResultSet rs = Select.getData("select * from patient");
			try {
				while (rs.next()) {

					int id = rs.getInt("id");
					patientName = rs.getString("patientName");
					address = rs.getString("address");
					Disease = rs.getString("Disease");
					age = rs.getInt(7);
					contactNo = rs.getLong("contactNo");

					System.out.printf("%-22d%-22s%-22s%-22s%-22d%-22d\n", id, patientName, address, Disease, age,
							contactNo);

				}
			} catch (SQLException e) {

				// System.out.println(e);
				logger.log(Level.FINE, e.getMessage());
			}
			System.out.println("\n");
			System.out.printf(
					"-----------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n");

			p = new Patient();
			p.patientCRUDOperations();
			break;

		case 4:
			System.out.println("Enter id:");
			id = in.nextInt();

			System.out.println("Do you really want to delete patient data \n press Y for yes and N for no:");
			char ch = in.next().charAt(0);
			if (ch == 'Y') {
				try {

					Query = "delete from patient where id = '" + id + "'";
					InsertUpdateDelete.setData(Query, "Patient data deleted");

				} catch (Exception e) {

					logger.log(Level.FINE, e.getMessage());
				}

			}
			p.patientCRUDOperations();
			break;

		case 5:

			Admin a = new Admin();
			a.adminOperations();
			break;

		default:
			System.out.println("Please Enter Valid choice");
			p.patientCRUDOperations();

		}
	}

}
