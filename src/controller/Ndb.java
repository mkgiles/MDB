package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import model.*;
import structures.*;
//Class for parsing .ndb files and constructing a pseudo-database out of them.
public class Ndb {
	//phase one: reads the file and creates objects from the entries.
	public static void parse(String filename) throws Exception {
		BufferedReader file = new BufferedReader(new FileReader(filename));
		String args = "";
		String line = file.readLine();
		while(line != null) {
			if(line.startsWith(" ")) {
				args = args.concat(line);
			}
			else {
				args = line;
				String[] params = args.split("\\s+");
				DataList<Pair<String,String>> object = new DataList<Pair<String, String>>(new Pair<String,String>(params[0].split("=")[0],params[0].split("=")[1]));
				for(int i=1;i<params.length;i++) {
					System.out.println(params[i]);
					Pair<String, String> temp = new Pair<String, String>(params[i].split("=")[0],params[i].split("=")[1]);
					object.append(temp);
				}
				convert(object);
				args = "";
			}
			line = file.readLine();
		}
		file.close();
	}
	//method which parses the type of the entry and calls the corresponding conversion procedure.
	private static void convert(DataList<Pair<String, String>> object) throws Exception {
		String type = extract(object, "type");
		if(type.equals("actor")) {
			actor(object);
		}
		else if(type.equals("movie")) {
			movie(object);
		}
		else if(type.equals("reference")) {
			role(object);
		}
		else {
			throw(new Exception("Invalid type."));
		}
	}
	private static void role(DataList<Pair<String, String>> object) {
		Actor actor = API.getActor(extract(object,"actor"));
		Movie movie = API.getMovie(extract(object, "movie"));
		String role = extract(object, "role");
		API.addRole(new Link<Actor, String, Movie>(actor, movie, role));
		
	}
	private static String extract(DataList<Pair<String, String>> object, String field) {
		return object.get(p -> p.car().equals(field)).cdr();
	}
	private static void actor(DataList<Pair<String, String>> object) throws Exception {
		String name = "", nation = "";
		int year = 0, month = 0, day = 0;
		Boolean gender = false;
		if(!(extract(object,"type").equals("actor")))
			throw (new Exception("Non-Actor object was parsed by Actor method."));
		name = extract(object,"name").replace("_", " ");
		gender = extract(object,"gender")=="female"?true:false;
		nation = extract(object,"nation");
		String[] dob =extract(object,"dob").split("-");
		year = Integer.parseInt(dob[0]);
		month = Integer.parseInt(dob[1]);
		day = Integer.parseInt(dob[2]);
		API.addActor(new Actor(name, gender, nation, year, month, day));
	}
	private static void movie(DataList<Pair<String, String>> object) throws Exception {
		String title = "", genre = "", description = "", posterURL = "";
		int runtime = 0, year = 0, month = 0, day = 0;
		if(!(extract(object,"type").equals("movie")))
			throw (new Exception("Non-Movie object was parsed by Movie method."));
		title = extract(object, "title").replace("_", " ");
		runtime = Integer.parseInt(extract(object,"runtime"));
		genre = extract(object, "genre");
		description = extract(object, "description").replace("_", " ");
		posterURL = extract(object, "poster");
		String[] release =extract(object,"release").split("-");
		year = Integer.parseInt(release[0]);
		month = Integer.parseInt(release[1]);
		day = Integer.parseInt(release[2]);
		API.addMovie(new Movie(title, runtime, genre, description, posterURL, year, month, day));
	}
	public static void save(String filename) throws Exception {
		File afile = new File(filename);
		afile.delete();
		afile.createNewFile();
		BufferedWriter file = new BufferedWriter(new FileWriter(afile));
		for(Node<Actor> temp = API.listActors().getNode(0);temp!=null;temp=temp.next) {
			Actor actor = temp.data;
			file.write("type=actor");
			file.write(" name=" + actor.getName().replace(" ", "_"));
			file.write(" gender=" + (actor.getGender()?"female":"male"));
			file.write(" nation=" + actor.getNationality());
			file.write(" dob=" + actor.getDob().getYear() + "-" + actor.getDob().getMonthValue() + "-" + actor.getDob().getDayOfMonth());
			file.newLine();
		}
		for(Node<Movie> temp = API.listMovies().getNode(0);temp!=null;temp=temp.next) {
			Movie movie =  temp.data;
			file.write("type=movie");
			file.write(" title=" + movie.getTitle().replace(" ", "_"));
			file.write(" runtime=" + movie.getRunningTime());
			file.write(" genre=" + movie.getGenre().replace(" ", "_"));
			file.write(" description=" + movie.getDescription().replace(" ", "_"));
			file.write(" poster=" + movie.getPosterURL());
			file.write(" release=" + movie.getDor().getYear() + "-" + movie.getDor().getMonthValue() + "-" + movie.getDor().getDayOfMonth());
			file.newLine();
		}
		file.close();
	}
}
