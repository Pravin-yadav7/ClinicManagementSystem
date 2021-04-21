package clinic.management.system;
/**
 * this class is used for perform CRUD operations on Doctor
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.InsertUpdateDelete;
import connection.Select;

public class DoctorOperate {
	
	int doctorId;
	String doctorName;
	String email;
	String degree;
	String specialization;
	Integer fee;
	String address;
	Long contactNo;
	String Query;

	/**
	 * this method take choice from admin and
	 * perform operations like add doctor,
	 * delete doctor, update doctor and see doctor list
	 */

	public void doctorCRUDOperations() {
		
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.FINE);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.FINE);
		logger.addHandler(handler);
		
		
		Scanner in = new Scanner(System.in);
		System.out.println("1. Add Doctor\n2. Update Doctor data\n3. See Doctor List\n4. Delete Doctor\n5. Back");
		System.out.println("Enter choice:");

		int choice = in.nextInt();
		switch(choice){
		
		
		case 1:
			System.out.println("Enter Doctor Name:");
			doctorName = in.next();

			System.out.println("Enter Email Id:");
			email = in.next();

			System.out.println("Enter Degree Name:");
			degree = in.next();

			System.out.println("Enter Specialization of docctor:");
			specialization = in.next();

			System.out.println("Enter fees of docctor:");
			fee = in.nextInt();
			
			System.out.println("Enter address of docctor:");
			address = in.next();
			
			System.out.println("Enter Contact Number:");
			contactNo = in.nextLong();
			
			if (doctorName.equals("") || email.equals("") || degree.equals("") || specialization.equals("") || fee.equals("") || address.equals("")
					|| contactNo.equals("")) {

				System.out.println("All fields are mandadatary");
				
			}else{
				
				try{
				
				String Query;
				Query = "insert into doctor values(doctorId,'" + doctorName + "', '" + email + "', '" + degree + "', '"+ specialization + "', '"+ fee + "','"+ address + "', '" + contactNo + "')";
				InsertUpdateDelete.setData(Query, "Doctor data added succefully");
				
				}catch(Exception e){
					
					logger.log(Level.FINE, e.getMessage());
				}
			}
			
			DoctorOperate dO = new DoctorOperate();
			dO.doctorCRUDOperations();
			
			 break;
			 
		case 2:
			System.out.println("Enter id:");
			doctorId = in.nextInt();
			
			System.out.println("Enter Doctor Name:");
			doctorName = in.next();

			System.out.println("Enter Email Id:");
			email = in.next();

			System.out.println("Enter Degree Name:");
			degree = in.next();

			System.out.println("Enter Specialization of docctor:");
			specialization = in.next();

			System.out.println("Enter fees of docctor:");
			fee = in.nextInt();
			
			System.out.println("Enter address of docctor:");
			address = in.next();
			
			System.out.println("Enter Contact Number:");
			contactNo = in.nextLong();
			
			try{
				
			Query = "update doctor set doctorName='" + doctorName + "', email='" + email + "', degree='" + degree + "', specialization='"+ specialization + "', fee='"+ fee + "', address='"+ address + "', contactNo='" + contactNo + "' where doctorId='"+doctorId+"' ";
			InsertUpdateDelete.setData(Query, "Doctor data updated succefully");
			
			}catch(Exception e){
				
				logger.log(Level.FINE, e.getMessage());
			}
			
			dO = new DoctorOperate();
			dO.doctorCRUDOperations();
			break;


		case 3:
			System.out.println("Data of Doctors");
			System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s%-22s%-22s\n", "DoctorId", "DoctorName", "email","Degree","specialization","Fee","Address", "ContactNo");


			ResultSet rs = Select.getData("select * from doctor");
			try {
				while (rs.next()) {

					doctorId = rs.getInt(1);
					doctorName = rs.getString("doctorName");
					email = rs.getString("email");
					degree = rs.getString("degree");
					specialization = rs.getString("specialization");
					fee = rs.getInt("fee");
					address = rs.getString("address");
					contactNo = rs.getLong("contactNo");
					
					System.out.printf("%-22d%-22s%-22s%-22s%-22s%-22d%-22s%-22d\n", doctorId,doctorName,email,degree,specialization,fee,address,contactNo);


				}
			} catch (SQLException e) {
				
				logger.log(Level.FINE, e.getMessage());
			}
			
			dO = new DoctorOperate();
			dO.doctorCRUDOperations();
			break;
		
		case 4:
			System.out.println("Enter id:");
			doctorId = in.nextInt();
			
			System.out.println("Do you really want to delete doctor data \n press Y for yes and N for no:");
			char ch = in.next().charAt(0);
			if(ch=='Y'){
				
				try{
			
				Query = "delete from doctor where doctorId = '"+doctorId+"'";
				InsertUpdateDelete.setData(Query, "Doctor data deleted");
				
				}catch(Exception e){
					
					logger.log(Level.FINE, e.getMessage());
				}
				
			}
			dO = new DoctorOperate();
			dO.doctorCRUDOperations();
			break;
			
		case 5:
			Admin a = new Admin();
			a.adminOperations();
			break;


		}

		
	}

}
