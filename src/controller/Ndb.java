package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import model.*;
import structures.*;
//Class for parsing .ndb files and constructing a pseudo-database out of them.
public class Ndb {
	//List of references built up in phase one for phase two.
	private static DataList<Link<Integer, String, Integer>> references = null;
	//phase one: reads the file and creates objects from the entries. Entries containing references to other entities are stored as links in the references list.
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
	//Phase two: converts all links in references list to corresponding references in the pseudo-database.
	private static <A,B> void link(HashList<A> sources, HashList<B> dests) {
		for(Node<A> source = sources.head; source != null; source = source.next) {
		}
		references = null;
	}
	//function for extracting values for fields, used by NDB conversion procedures.
	public static String extract(DataList<Pair<String, String>> object, String field) {
		return object.get(p -> p.car() == field).cdr();
	}

}
