package model;
import java.time.*;
import structures.DataList;
import structures.Link;
import structures.Pair;

public class Actor {
	private static int uuid = 0;
	private String name;
	private int uid;
	private LocalDate dob;
	private Boolean gender;
	private String nationality;
	
	public Actor(String name, Boolean gender, String nationality, int yearNum, int monthNum, int dayNum)
	{
		this.uid = uuid;
		uuid = uuid+1;
		setName(name);
		setGender(gender);
		setNationality(nationality);
		setDob(yearNum, monthNum, dayNum);
	}

	public void viewActor()
	{
		System.out.print("Showing actors");
	}
	
	
	//SETTERS//
	public void setName(String name)
	{
		 this.name = name;
	}
	public void setGender(Boolean gender)
	{
		 this.gender = gender;
	}
	public void setNationality(String nationality)
	{
		this.nationality = nationality;
	}
	public void setDob(int yearNum, int monthNum, int dayNum)
	{
		this.dob = LocalDate.of(yearNum,monthNum, dayNum);
	}

	
	//GETTERS//
	public String getName()
	{
		return name;
	}
	public Boolean getGender()
	{
		return gender;
	}
	public String getNationality()
	{
		return nationality;
	}
	public LocalDate getDob()
	{
		return dob;
	}
}
