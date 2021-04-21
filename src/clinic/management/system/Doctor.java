package clinic.management.system;

/**
 * this class is used for take choice from doctor and perform specific operations
 */

import java.io.PrintStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.InsertUpdateDelete;
import connection.Select;

public class Doctor {

	private long contactNo;
	private String patientName;
	private String address;
	private String Disease;
	private int age;
	private String dateOfAppointment;
	private String timeOfAppointment;
	private String patientDisease;
	private String prescription;
	private String notes;
	private String date;

	/**
	 * this method take choice from doctor and perform operations like See
	 * Patient List, Add prescription and notes, See appointed Patient and Check
	 * Patient History
	 * 
	 * @param doctorId
	 * @param doctorName
	 */

	public void doctorOperations(int doctorId, String doctorName) {
		
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.FINE);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.FINE);
		logger.addHandler(handler);


		Scanner in = new Scanner(System.in);
		System.out.println("welcome doctor");
		System.out.println("-----------------------Doctor Pannel-------------------------------------------");
		System.out.println(
				"1. List of Patient\n2. ADD prescription and notes for patient\n3. See Appointed Patient\n4. check Patient history and prescription\n5. Back To Login Pannel");

		int choice = in.nextInt();
		switch (choice) {

		case 1:
			System.out.println("data of patient");
			System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n", "Id", "PatientName", "Address", "Disease", "Age",
					"ContactNo");

			ResultSet rs = Select.getData("select * from patient");
			try {
				while (rs.next()) {

					int id = rs.getInt(1);
					patientName = rs.getString(2);
					address = rs.getString(3);
					Disease = rs.getString(4);
					age = rs.getInt(5);
					contactNo = rs.getLong(6);

					System.out.printf("%-22d%-22s%-22s%-22s%-22d%-22d\n", id, patientName, address, Disease, age,
							contactNo);

				}
			} catch (SQLException e) {
					
				//sSystem.out.println(e);
				logger.log(Level.FINE, e.toString());
			}
			Doctor d = new Doctor();
			d.doctorOperations(doctorId,doctorName);
			break;

		case 2:
			System.out.println("-------Add prescription and note for patient---------");
			System.out.println("Enter  Pateint Name to add prescription and notes");
			patientName = in.next();

			rs = Select.getData("select * from patient where patientName='" + patientName + "'");
			try {
				while (rs.next()) {

					Disease = rs.getString("patientDisease");
				}
			} catch (SQLException e) {
				
				//System.out.println(e);
				logger.log(Level.FINE, e.toString());
			}

			System.out.println("Add Prescription");
			prescription = in.next();

			System.out.println("Add notes for Patient");
			notes = in.next();

			System.out.println("enter date of Prescription:");
			date = in.next();

			String Query;
			Query = " insert into doctorprescription values('" + doctorId + "','" + doctorName + "','" + patientName
					+ "','" + Disease + "','" + prescription + "','" + notes + "','" + date + "')";
			InsertUpdateDelete.setData(Query, "Prescription and Notes Added Successfully");
			
			d = new Doctor();
			d.doctorOperations(doctorId,doctorName);

			break;

		case 3:
			System.out.println("");
			System.out.printf("%-22s%-22s%-22s%-22s%-22s\n", "PatientName", "PatientDisease", "DoctorName",
					"DateOfAppointment", "TimeOfAppointment");
			rs = Select.getData("select * from appointment where doctorName='" + doctorName + "' ");
			try {
				while (rs.next()) {

					patientName = rs.getString("patientName");
					patientDisease = rs.getString("patientDisease");
					doctorName = rs.getString("doctorName");
					dateOfAppointment = rs.getString("dateOfAppointment");
					timeOfAppointment = rs.getString("timeOfAppointment");

					System.out.printf("%-22s%-22s%-22s%-22s%-22s\n", patientName, patientDisease, doctorName,
							dateOfAppointment, timeOfAppointment);

				}
			} catch (SQLException e) {
			
				//System.out.println(e);
				logger.log(Level.FINE, e.toString());
			}
			
			d = new Doctor();
			d.doctorOperations(doctorId,doctorName);

			break;

		case 4:
			System.out.println("---------------------------------History of Patient---------------------------------");
			System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n", "Appointed Doctor", "PatientName", "PatientDisease",
					"Prescription", "Notes", "Date");
			rs = Select.getData("select * from doctorprescription");
			try {
				while (rs.next()) {

					doctorName = rs.getString(2);
					patientName = rs.getString(3);
					patientDisease = rs.getString(4);
					prescription = rs.getString("prescription");
					notes = rs.getString("notes");
					date = rs.getString("prescriptionDate");

					System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n", doctorName, patientName, patientDisease,
							prescription, notes, date);

				}
			} catch (SQLException e) {
				
				//System.out.println(e);
				logger.log(Level.FINE, e.toString());
			}
			
			d = new Doctor();
			d.doctorOperations(doctorId,doctorName);
			
			break;
			
		case 5:
			Login l = new Login();
			l.login();

		}
	}

}
