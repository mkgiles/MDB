package model;

import structures.DataList;
import structures.Link;
import structures.Pair;

public class Movie {
	private static int uuid = 0;
	private int uid;
	private String title;
	private int runningTime;
	private String genre;
	private String description;
	private String posterURL;
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}

	public static void ndb(DataList<Pair<String, String>> object) {
		// TODO Auto-generated method stub
		
	}
	
	public void viewMovie()
	{
		System.out.print("Showing movies");
	}

}
