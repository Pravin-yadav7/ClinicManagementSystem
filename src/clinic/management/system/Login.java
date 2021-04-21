package clinic.management.system;
/**
 * this class is used for login purpose
 */

import java.io.Console;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.Select;

public class Login {

	String userName;
	String password;
	
	/**
	 * this method take input as userName and password from 
	 * ADMIN and DOCTOR and login them into corresponding panels
	 */

	public void login() {

		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.FINE);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.FINE);
		logger.addHandler(handler);
		
		
		Scanner in = new Scanner(System.in);
		System.out.println("------------------------Login Pannel---------------------------------");
		System.out.println("1. Admin\n2. Doctor\n3. Exit");

		int choice = in.nextInt();
		while(true){
		switch (choice) {

		case 1:

			System.out.println("Enter Username:");
			userName = in.next();

			System.out.println("Enter Password:");
			password = in.next();

			if (userName.equals("pravin") && password.equals("1234")) {

				System.out.println("Login Succefully");
				Admin a = new Admin();
				a.adminOperations();
			} else {
				System.out.println("Invalid username or passsword\nPlease Enter Valid username and password:");
			}

			break;

		case 2:
			// System.out.println("Doctor Login Succefully");

			System.out.println("Enter Username:");
			userName = in.next();

			System.out.println("Enter Password:");
			password = in.next();

			int check = 0;

			ResultSet rs = Select.getData("select * from doctor");
			try {
				while (rs.next()) {

					int doctorId = rs.getInt(1);
					String doctorName = rs.getString(2);
					String email = rs.getString(3);
					

					if (userName.equals(doctorName) && password.equals(email)) {

						check = 1;
						Doctor d = new Doctor();
						d.doctorOperations(doctorId,doctorName);

					}
				}
			} catch (SQLException e) {
				
				//System.out.println(e);
				logger.log(Level.FINE, e.toString());
			}
			if(check==0){
				System.out.println("Invalid username or passsword\nPlease Enter Valid username and password:");
			}

			break;

		case 3:
			System.exit(0);
		}

	}
	}	

}
