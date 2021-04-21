package clinic.management.system;
/**
 * this class is used to Book Appointment for Patient
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import connection.InsertUpdateDelete;
import connection.Select;

public class MakeAppoinment {

	private String patientName;
	private String doctorName;
	private String Disease;
	private String specialization;
	private int doctorFee;
	private String dateOfAppointment;
	private String timeOfAppointment;
	Scanner in = new Scanner(System.in);

	/**
	 * this method take patient name and doctor name from admin and book
	 * appointment for that patient
	 */

	public void bookAppointment() {

		System.out.println("Make appointment for Pateint");
		System.out.println("enter Patient Name:");
		patientName = in.next();

		System.out.println("enter doctor Name");
		doctorName = in.next();

		System.out.println("Enter Date of appointment");
		dateOfAppointment = in.next();

		System.out.println("Enter Time of appointment");
		timeOfAppointment = in.next();

		String Query;
		Query = " select patientName, Disease from patient where patientName='" + patientName + "'";
		ResultSet rs = Select.getData(Query);
		try {
			while (rs.next()) {

				patientName = rs.getString("patientName");
				Disease = rs.getString("Disease");

			}
		} catch (SQLException e) {
			System.out.println(e);

		}

		Query = " select doctorName, specialization, fee from doctor where doctorName='" + doctorName + "'";
		rs = Select.getData(Query);
		try {
			while (rs.next()) {

				doctorName = rs.getString("doctorName");
				specialization = rs.getString("specialization");
				doctorFee = rs.getInt("fee");

			}
		} catch (SQLException e) {

			System.out.println(e);
		}

		Query = "insert into appointment values(appointId, '" + patientName + "', '" + Disease + "', '" + doctorName
				+ "',  '" + specialization + "', '" + doctorFee + "', '" + dateOfAppointment + "', '"
				+ timeOfAppointment + "')";
		InsertUpdateDelete.setData(Query, "Appointment Booked");

		MakeAppoinment ma = new MakeAppoinment();
		ma.repeateOpearation();

	}

	private void repeateOpearation() {


		System.out.println(
				"\nAre you want to appointment more patient:\nIf yes then press 'Y' or if no then press 'N'\n");
		char ch = in.next().charAt(0);
		if (ch == 'Y') {

			MakeAppoinment ma = new MakeAppoinment();
			ma.bookAppointment();

		} else if (ch == 'N') {

			Admin a = new Admin();
			a.adminOperations();

		} else {

			System.out.println("Please Enter Valid choice");
			MakeAppoinment ma = new MakeAppoinment();
			ma.repeateOpearation();

		}

	}

}
