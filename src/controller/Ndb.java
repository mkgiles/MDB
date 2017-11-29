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
			reference(object);
		}
		else {
			throw(new Exception("Invalid type."));
		}
	}
	private static void reference(DataList<Pair<String, String>> object) {
		
	}
	private static String extract(DataList<Pair<String, String>> object, String field) {
		return object.get(p -> p.car() == field).cdr();
	}
	private static void actor(DataList<Pair<String, String>> object) throws Exception {
		String name = "", nation = "";
		int year = 0, month = 0, day = 0;
		Boolean gender = false;
		for(int i=0; i<object.length();i++) {
			switch(object.get(i).car()) {
			case "type":
				if(!(extract(object,"type").equals("actor")))
					throw (new Exception("Non-Actor object was parsed by Actor method."));
				break;
			case "name":
				name = extract(object,"name");
				break;
			case "gender":
				gender = extract(object,"gender")=="female"?true:false;
				break;
			case "nation":
				nation = extract(object,"nation");
				break;
			case "dob":
				String[] dob =extract(object,"dob").split("-");
				year = Integer.parseInt(dob[0]);
				month = Integer.parseInt(dob[1]);
				day = Integer.parseInt(dob[2]);
				break;
			}
		}
		new Actor(name, gender, nation, year, month, day);
	}
	private static void movie(DataList<Pair<String, String>> object) throws Exception {
		
		for(int i=0; i<object.length();i++) {
			switch(object.get(i).car()) {
			case "type":
				if(!(extract(object,"type").equals("movie")))
					throw (new Exception("Non-Movie object was parsed by Movie method."));
				break;
			}
		}
	}
}
