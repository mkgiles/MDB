package controller;

import structures.*;
import model.*;

public class API {
	private static DataList<Actor> actors = null;
	private static DataList<Movie> movies = null;
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
}
