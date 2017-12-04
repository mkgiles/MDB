package controller;

import structures.*;
import model.*;

/**
 * The Class API.
 */
public class API {
	
	/** The actors. */
	// Lists composing the pseudo-database.
	private static HashList<Actor> actors = null;
	
	/** The movies. */
	private static HashList<Movie> movies = null;
	
	/** The roles. */
	private static DataList<Link<Actor, String, Movie>> roles = null;

	/**
	 * Adds the actor.
	 *
	 * @param actor
	 *            the actor
	 */
	public static void addActor(Actor actor) {
		if (actors == null)
			actors = new HashList<Actor>(1024);
		actors.put(actor.getName(), actor);
	}

	/**
	 * Adds the movie.
	 *
	 * @param movie
	 *            the movie
	 */
	public static void addMovie(Movie movie) {
		if (movies == null)
			movies = new HashList<Movie>(1024);
		movies.put(movie.getTitle(), movie);
	}

	/**
	 * Gets the movie.
	 *
	 * @param title
	 *            the title
	 * @return the movie
	 */
	// Used to get Movies through the use of UID's?
	public static Movie getMovie(String title) {
		return movies.get(title);
	}

	/**
	 * Gets the actor.
	 *
	 * @param name
	 *            the name
	 * @return the actor
	 */
	// Used to get Actor through the use of Names.
	public static Actor getActor(String name) {
		return actors.get(name);
	}

	/**
	 * Adds the role.
	 *
	 * @param link
	 *            the link
	 */
	public static void addRole(Link<Actor, String, Movie> link) {
		if (roles == null)
			roles = new DataList<Link<Actor, String, Movie>>(link);
		else
			roles.append(link);
	}

	/**
	 * List actors.
	 *
	 * @return the data list
	 */
	public static DataList<Actor> listActors() {
		return actors == null ? null : actors.list();
	}

	/**
	 * List movies.
	 *
	 * @return the data list
	 */
	public static DataList<Movie> listMovies() {
		return movies == null ? null : movies.list();
	}

	/**
	 * List roles.
	 *
	 * @return the data list
	 */
	public static DataList<Link<Actor, String, Movie>> listRoles() {
		return roles;
	}

	/**
	 * Gets the role.
	 *
	 * @param role
	 *            the role
	 * @return the role
	 */
	public static Link<Actor, String, Movie> getRole(String role) {
		return roles.get(p -> p.path().equals(role));
	}

	/**
	 * Drop actor.
	 *
	 * @param name
	 *            the name
	 */
	public static void dropActor(String name) {
		roles.drop(p -> p.source().equals(getActor(name)));
		actors.drop(name);
	}

	/**
	 * Drop movie.
	 *
	 * @param title
	 *            the title
	 */
	public static void dropMovie(String title) {
		roles.drop(p -> p.dest().equals(getMovie(title)));
		movies.drop(title);
	}

	/**
	 * Drop role.
	 *
	 * @param role
	 *            the role
	 */
	public static void dropRole(String role) {
		roles.drop(p -> p.path().equals(role));
	}

	/**
	 * Gets the roles of the actor.
	 *
	 * @param actor
	 *            the actor
	 * @return the actor role
	 */
	public static DataList<Pair<Movie, String>> getActorRole(Actor actor) {
		DataList<Link<Actor, String, Movie>> temp = roles.getSubList(p -> p.source().equals(actor));
		DataList<Pair<Movie, String>> result = temp
				.filter(f -> new Pair<Movie, String>(f.dest(), f.path()));
		return result;
	}

	/**
	 * Gets the roles in the movie.
	 *
	 * @param movie
	 *            the movie
	 * @return the movie role
	 */
	public static DataList<Pair<Actor, String>> getMovieRole(Movie movie) {
		DataList<Link<Actor, String, Movie>> temp = roles.getSubList(p -> p.dest().equals(movie));
		DataList<Pair<Actor, String>> result = temp
				.filter(f -> new Pair<Actor, String>(f.source(), f.path()));
		return result;
	}

	/**
	 * Clears the database.
	 */
	public static void clear() {
		actors = null;
		movies = null;
		roles = null;
	}
}