package clinic.management.system;
/**
 * this class is used for perform CRUD operations on Drugs
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.InsertUpdateDelete;
import connection.Select;

public class Drugs {

	int drugId;
	String drugName;
	String drugWeight;
	String drugContent;
	String expiryDate;
	String brandName;
	Integer drugMRP;
	String Query;
	
	/**
	 *  this method take choice from admin and
	 * perform operations like add drug,
	 * delete drug, update drug and see drug list
	 */

	public void drugsCRUDOperations() {
		
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.FINE);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.FINE);
		logger.addHandler(handler);
		

		Scanner in = new Scanner(System.in);
		System.out.println("1. Add Drug\n2. Update Drug data\n3. See Drug List\n4. Delete Drug\n5. Back");
		System.out.println("Enter choice:");

		int choice = in.nextInt();
		switch (choice) {

		case 1:
			System.out.println("Enter Drug Name:");
			drugName = in.next();

			System.out.println("Enter Weight of Drug:");
			drugWeight = in.next();

			System.out.println("Enter Drug Content:");
			drugContent = in.next();

			System.out.println("Enter expiry date of drug:");
			expiryDate = in.next();

			System.out.println("Enter Brand Name of Drug:");
			brandName = in.next();

			System.out.println("Enter MRP of drug:");
			drugMRP = in.nextInt();

			if (drugName.equals("") || drugWeight.equals("") || drugContent.equals("") || expiryDate.equals("")
					|| brandName.equals("") || drugMRP.equals("")) {

				System.out.println("All fields are mandadatary");

			} else {
				
				try{

				String Query;
				Query = "insert into drug values(drugId,'" + drugName + "', '" + drugWeight + "', '" + drugContent
						+ "', '" + expiryDate + "', '" + brandName + "','" + drugMRP + "')";
				InsertUpdateDelete.setData(Query, "Drug data added succefully");
				
				}catch(Exception e){
					
					logger.log(Level.FINE, e.getMessage());
				}

			}
			
			Drugs ds = new Drugs();
			ds.drugsCRUDOperations();

			break;

		case 2:
			System.out.println("Enter drug id:");
			drugId = in.nextInt();

			System.out.println("Enter Drug Name:");
			drugName = in.next();

			System.out.println("Enter Weight of Drug:");
			drugWeight = in.next();

			System.out.println("Enter Drug Content:");
			drugContent = in.next();

			System.out.println("Enter expiry date of drug:");
			expiryDate = in.next();

			System.out.println("Enter Brand Name of Drug:");
			brandName = in.next();

			System.out.println("Enter MRP of drug:");
			drugMRP = in.nextInt();

			try{
				
			Query = "update drug set drugName='" + drugName + "', drugWeight='" + drugWeight + "', drugContent='"
					+ drugContent + "', expiryDate='" + expiryDate + "', brandName='" + brandName + "', drugMRP='"
					+ drugMRP + "' where drugId='" + drugId + "' ";
			InsertUpdateDelete.setData(Query, "Drug data Updated succefully");
			
			}catch(Exception e){
				
				logger.log(Level.FINE, e.getMessage());
			}
			
			ds = new Drugs();
			ds.drugsCRUDOperations();

			break;

		case 3:
			System.out.println("Data of Doctors");
			System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s%-22s\n", "DrugId", "DrugName", "DrugWeight","DrugContent","expiryDate","BrandName","DrugMRP");

			ResultSet rs = Select.getData("select * from drug");
			try {
				while (rs.next()) {

					drugId = rs.getInt(1);
					drugName = rs.getString("drugName");
					drugWeight = rs.getString("drugWeight");
					drugContent = rs.getString("drugContent");
					expiryDate = rs.getString("expiryDate");
					brandName = rs.getString(6);
					drugMRP = rs.getInt(7);

					
					System.out.printf("%-22d%-22s%-22s%-22s%-22s%-22s%-22d\n", drugId,drugName,drugWeight,drugContent,expiryDate,brandName,drugMRP);

				}
			} catch (SQLException e) {
				
				logger.log(Level.FINE, e.getMessage());
			}
			
			ds = new Drugs();
			ds.drugsCRUDOperations();
			break;
			
		case 4:
			System.out.println("Enter drug id:");
			drugId = in.nextInt();
			
			System.out.println("Do you really want to delete drugs data \n press Y for yes and N for no:");
			char ch = in.next().charAt(0);
			if(ch=='Y'){
				
				try{
					
				Query = "delete from drug where drugId = '"+drugId+"'";
				InsertUpdateDelete.setData(Query, "Drug data deleted");
				
				}catch(Exception e){
					
					logger.log(Level.FINE, e.getMessage());
				}
				
			}
			
			ds = new Drugs();
			ds.drugsCRUDOperations();
			break;
			
		case 5:
			Admin a = new Admin();
			a.adminOperations();
			break;


		}

	}

}
