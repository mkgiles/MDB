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
	public static Movie getMovie(String title) 
	{
		return movies.get(p -> p.getTitle().equals(title));
	}
	
	//Used to get Actor through the use of Names.
	public static Actor getActor(String name) 
	{
		return actors.get(p -> p.getName().equals(name));
	}
	public static void addRole(Link<Actor, String, Movie> link) {
		if(roles==null)
			roles=new DataList<Link<Actor,String,Movie>>(link);
		else
			roles.append(link);
	}
	public static DataList<Actor> listActors(){
		return actors;
	}
	public static DataList<Movie> listMovies(){
		return movies;
	}
	public static DataList<Link<Actor,String,Movie>> listRoles(){
		return roles;
	}
	public static Link<Actor, String, Movie> getRole(int index){
		return roles.get(index);
	}
}