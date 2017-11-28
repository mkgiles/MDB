package controller;

import structures.*;
import model.*;

public class API {
	//Lists composing the pseudo-database.
	private static DataList<Actor> actors = null;
	private static DataList<Movie> movies = null;
	private static DataList<Link<Actor,String,Movie>> roles = null;
	public static void addActor(Actor actor) {
		if(actors==null) {
			actors = new DataList<Actor>(actor);
		}
		else
			actors.append(actor);
	}
	public static void addMovie(Movie movie) {
		if(movies==null) {
			movies = new DataList<Movie>(movie);
		}
		else
			movies.append(movie);
	}
	
	
	//Used to get Movies through the use of UID's? 
	public static void getMovie(int actorUID) 
	{
		
	}
	
	//Used to get Actor through the use of UID's?
	public static Actor getActor(String name) 
	{
		return actors.get(p -> {return p.getName()==name;});
	}
}
