package controller;

import java.util.function.Predicate;

import model.*;
import structures.*;

public class Ndb {
	private static DataList<Link<Integer, String, Integer>> references = null;
	public static void parse(String filename) {
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
		
	}

}
