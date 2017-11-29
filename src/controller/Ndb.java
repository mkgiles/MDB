package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import model.*;
import structures.*;
//Class for parsing .ndb files and constructing a pseudo-database out of them.
public class Ndb {
	//phase one: reads the file and creates objects from the entries.
	public static void parse(String filename) throws Exception {
		BufferedReader file = new BufferedReader(new FileReader(filename));
		file.mark(1024);
		String args = "";
		String line = file.readLine();
		while(line != null) {
			if(line.startsWith(" ")) {
				args = args.concat(line);
			}
			else {
				file.reset();
				String[] params = args.split("//s+");
				DataList<Pair<String,String>> object = new DataList<Pair<String, String>>(new Pair<String,String>(params[0].split("=")[0],params[0].split("=")[1]));
				for(int i=1;i<params.length;i++) {
					Pair<String, String> temp = new Pair<String, String>(params[i].split("=")[0],params[i].split("=")[1]);
					object.append(temp);
				}
				convert(object);
				args = "";
				file.mark(1024);
			}
			line = file.readLine();
		}
		file.close();
	}
	//method which parses the type of the entry and calls the corresponding conversion procedure.
	private static void convert(DataList<Pair<String, String>> object) throws Exception {
		Pair<String, String> type = object.get((Pair<String, String> p) -> {return p.car().equals("type");});
		if(type.cdr().equals("actor")) {
			actor(object);
		}
		else if(type.cdr().equals("movie")) {
			movie(object);
		}
		else if(type.cdr().equals("reference")) {
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
		return object.get(p -> p.car() == field).cdr();
	}
	private static void actor(DataList<Pair<String, String>> object) throws Exception {
		String name = "", nation = "";
		int year = 0, month = 0, day = 0;
		Boolean gender = false;
		if(!(extract(object,"type").equals("actor")))
			throw (new Exception("Non-Actor object was parsed by Actor method."));
		name = extract(object,"name");
		gender = extract(object,"gender")=="female"?true:false;
		nation = extract(object,"nation");
		String[] dob =extract(object,"dob").split("-");
		year = Integer.parseInt(dob[0]);
		month = Integer.parseInt(dob[1]);
		day = Integer.parseInt(dob[2]);
		API.addActor(new Actor(name, gender, nation, year, month, day));
	}
	private static void movie(DataList<Pair<String, String>> object) throws Exception {
		String name = "", genre = "", description = "", posterURL = "";
		int runtime = 0, year = 0, month = 0, day = 0;
		if(!(extract(object,"type").equals("movie")))
			throw (new Exception("Non-Movie object was parsed by Movie method."));
		name = extract(object, "name");
		runtime = Integer.parseInt(extract(object,"runtime"));
		genre = extract(object, "genre");
		description = extract(object, "description");
		posterURL = extract(object, "poster");
		String[] release =extract(object,"dob").split("-");
		year = Integer.parseInt(release[0]);
		month = Integer.parseInt(release[1]);
		day = Integer.parseInt(release[2]);
		API.addMovie(new Movie(name, runtime, genre, description, posterURL, year, month, day));
	}
}
