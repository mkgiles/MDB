package model;
import java.time.*;
import structures.DataList;
import structures.Link;
import structures.Pair;

public class Actor {
	private static int uuid = 0;
	private int uid;
	private LocalDate dob;
	private int gender;
	private String nationality;
	
	
	public Actor() {
		// TODO Auto-generated constructor stub
	}

	public static void ndb(DataList<Pair<String, String>> object) {
		// TODO Auto-generated method stub
		
	}

	public void viewActor()
	{
		System.out.print("Showing actors");
	}
	
	public void setDob(int yearNum, int monthNum, int dayNum)
	{
		dob = LocalDate.of(yearNum, Month.of(monthNum), dayNum);
	}
	
	public void getDob()
	{
		System.out.println("Date of Birth: " + dob );
	}
	
}
