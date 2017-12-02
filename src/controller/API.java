package controller;

import structures.*;
import model.*;

public class API {
	//Lists composing the pseudo-database.
	private static HashList<Actor> actors = null;
	private static HashList<Movie> movies = null;
	private static DataList<Link<Actor,String,Movie>> roles = null;
	public static void addActor(Actor actor) {
		if(actors==null)
			actors = new HashList<Actor>(1024);
		actors.put(actor.getName(),actor);
	}
	public static void addMovie(Movie movie) {
		if(movies==null)
			movies = new HashList<Movie>(1024);
		movies.put(movie.getTitle(),movie);
	}
	
	
	//Used to get Movies through the use of UID's? 
	public static Movie getMovie(String title) 
	{
		return movies.get(title);
	}
	
	//Used to get Actor through the use of Names.
	public static Actor getActor(String name)
	{
		return actors.get(name);
	}
	public static void addRole(Link<Actor, String, Movie> link) {
		if(roles==null)
			roles=new DataList<Link<Actor,String,Movie>>(link);
		else
			roles.append(link);
	}
	public static DataList<Actor> listActors(){
		return actors==null?null:actors.list();
	}
	public static DataList<Movie> listMovies(){
		return movies==null?null:movies.list();
	}
	public static DataList<Link<Actor,String,Movie>> listRoles(){
		return roles;
	}
	public static Link<Actor, String, Movie> getRole(String role){
		return roles.get(p -> p.path().equals(role));
	}
	public static void dropActor(String name) {
		roles.drop(p -> p.source().equals(getActor(name)));
		actors.drop(name);
	}
	public static void dropMovie(String title) {
		roles.drop(p -> p.dest().equals(getMovie(title)));
		movies.drop(title);
	}
	public static void dropRole(String role) {
		roles.drop(p -> p.path().equals(role));
	}
	public static DataList<Pair<String, Movie>> getActorRole(Actor actor){
		DataList<Link<Actor,String,Movie>> temp = roles.getSubList(p -> p.source().equals(actor));
		DataList<Pair<String,Movie>> result = (DataList<Pair<String, Movie>>) temp.filter(f -> new Pair<String, Movie>(f.path(),f.dest()));
		return result;
	}
	public static DataList<Pair<Actor, String>> getMovieRole(Movie movie){
		DataList<Link<Actor,String,Movie>> temp = roles.getSubList(p -> p.dest().equals(movie));
		DataList<Pair<Actor,String>> result = (DataList<Pair<Actor, String>>) temp.filter(f -> new Pair<Actor, String>(f.source(),f.path()));
		return result;
	}
	public static void clear() {
		actors = null;
		movies = null;
		roles = null;
	}
}