package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.API;
import controller.Ndb;
import model.Actor;
import model.Movie;
import structures.Link;

class NdbTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		API.clear();
	}

	@Test
	final void testParse() throws Exception {
		Ndb.parse("hanks.ndb");
		assertFalse(API.getActor("Tom Hanks").getGender());
		assertEquals(143, API.getMovie("Cast Away").getRunningTime());
		assertEquals(API.getActor("Tom Hanks"), API.getRole("Chuck Noland").source());
	}

	@Test
	final void testSave() throws Exception {
		API.addActor(new Actor("Matt Damon", false, "USA", 1970, 10, 8));
		API.addMovie(new Movie("The Martian", 144, "Comedy (apparently)", "During a manned mission to Mars...", "image.jpg", 2015, 10, 2));
		API.addRole(new Link<Actor, String, Movie>(API.getActor("Matt Damon"), "Mark Watney", API.getMovie("The Martian")));
		Ndb.save("test.ndb");
		API.clear();
		Ndb.parse("test.ndb");
		assertNotNull(API.getMovie("The Martian"));
		assertNotNull(API.getActor("Matt Damon"));
		assertEquals("Mark Watney", API.getRole("Mark Watney").path());
		assertEquals(API.getActor("Matt Damon"), API.getRole("Mark Watney").source());
		assertEquals(API.getMovie("The Martian"), API.getRole("Mark Watney").dest());
	}

}
