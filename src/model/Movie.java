package model;

import java.time.LocalDate;

import structures.DataList;
import structures.Link;
import structures.Pair;

public class Movie {
	private static int uuid = 0;
	private int uid;
	private String title;
	private int runningTime;
	private LocalDate dor;
	private String genre;
	private String description;
	private String posterURL;
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}

	Movie(String title, int runningTime, String genre, String description, String posterURL, int yearNum, int monthNum, int dayNum)
	{
		this.uid = uuid;
		uuid = uuid+1;
		setTitle(title);
		setRunningTime(runningTime);
		setGenre(genre);
		setDescription(description);
		setPosterURL(posterURL);
		setDor(yearNum, monthNum, dayNum);
	}

	public static void ndb(DataList<Pair<String, String>> object) {
		// TODO Auto-generated method stub
		
	}
	
	public void viewMovie()
	{
		System.out.print("Showing movies");
	}
	
	//SETTERS//
	public void setPosterURL(String posterURL) 
	{
	this.posterURL = posterURL;		
	}
	public void setDescription(String description)
	{
		this.description = description;	
	}
	public void setGenre(String genre) 
	{
		this.genre = genre;	
	}
	public void setRunningTime(int runningTime) 
	{
		this.runningTime = runningTime;		
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	public void setDor(int yearNum, int monthNum, int dayNum)
	{
		this.dor = LocalDate.of(yearNum,monthNum, dayNum);
	}
	
	//GETTERS//
	public String getPosterURL()
	{
		return posterURL;	
	}
	public String getDescription()
	{
		return description;
	}
	public String getGenre()
	{
		return genre;
	}
	public int getRunningTime()
	{
		return runningTime;
	}
	public String getTitle()
	{
		return title;
	}
	public LocalDate getDor()
	{
		return dor;
	}
}
