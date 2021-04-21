package clinic.management.system;
/**
 * this class is used for take choice from admin for  CRUD operations
 * on patient, doctor ,drugs and lab test
 * and also for book appointment
 */

import java.util.Scanner;

public class Admin {
	
	/**
	 * this method take choice from admin
	 * and turn them into specific panel
	 */
	
	public void adminOperations(){
		
		Scanner in = new Scanner(System.in);
		System.out.println("------------------------Admin Panel-------------------------");
		System.out.println("1. Patient\n2. Doctor\n3. Drugs\n4. Lab Test\n5. Make appoinment\n6. Go back to Login pannel");
		int choice =  in.nextInt();
		while(true){
		switch(choice){
		
		case 1:
			Patient p = new Patient();
			p.patientCRUDOperations();
			break;
			
		case 2:
			DoctorOperate dO = new DoctorOperate();
			dO.doctorCRUDOperations();
			break;
			
		case 3:
			Drugs ds = new Drugs();
			ds.drugsCRUDOperations();
			break;
			
		case 4:
			LabTest lt =new LabTest();
			lt.labTestCRUDOperations();
			break;
			
		case 5:
			MakeAppoinment ma = new MakeAppoinment();
			ma.bookAppointment();
			break;
			
		case 6:
			Login l = new Login();
			l.login();
			break;
			
		default :
			System.out.println("Please Enter Valid choice");
			Admin a = new Admin();
			a.adminOperations();
			break;
			
			
		}
		in.close();
	}
	}	

}
