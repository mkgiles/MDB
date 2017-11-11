package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import model.*;
import structures.*;

public class Ndb {
	private static DataList<Link<Integer, String, Integer>> references = null;
	public static void parse(String filename) throws Exception {
		BufferedReader file = new BufferedReader(new FileReader(filename));
		file.mark(1024);
		String args = "";
		String line = file.readLine();
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
		}
		link();
	}
	private static void convert(DataList<Pair<String, String>> object) throws Exception {
		Pair<String, String> type = object.search((Pair<String, String> a) -> {return a.car().equals("type");});
		if(type.cdr().equals("Actor")) {
			Actor.ndb(object);
		}
		else if(type.cdr().equals("Movie")) {
			Movie.ndb(object);
		}
		else {
			throw(new Exception("Invalid type."));
		}
	}
	private static void link() {
		references = null;
	}

}
