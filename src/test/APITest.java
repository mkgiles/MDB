package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.API;
import controller.Ndb;
import model.Actor;
import model.Movie;
import structures.Link;

class APITest {

	@BeforeEach
	void setUp() throws Exception {
		Ndb.parse("hanks.ndb");
	}

	@AfterEach
	void tearDown() throws Exception {
		API.clear();
	}

	@Test
	final void testAddActor() {
		API.addActor(new Actor("Matt Damon", false, "USA", 1970, 10, 8));
		assertNotNull(API.getActor("Matt Damon"));
		assertFalse(API.getActor("Matt Damon").getGender());
		assertEquals(LocalDate.of(1970, 10, 8),API.getActor("Matt Damon").getDob());
		assertEquals("USA", API.getActor("Matt Damon").getNationality());
	}

	@Test
	final void testAddMovie() {
		API.addMovie(new Movie("The Martian", 144, "Comedy (apparently)", "During a manned mission to Mars...", "image.jpg", 2015, 10, 2));
		assertNotNull(API.getMovie("The Martian"));
		assertEquals(144, API.getMovie("The Martian").getRunningTime());
		assertEquals("image.jpg", API.getMovie("The Martian").getPosterURL());
		assertEquals(LocalDate.of(2015, 10, 2),API.getMovie("The Martian").getDor());
	}

	@Test
	final void testGetMovie() {
		assertNotNull(API.getMovie("Cast Away"));
		assertNull(API.getMovie("A Movie."));
	}

	@Test
	final void testGetActor() {
		assertNotNull(API.getActor("Tom Hanks"));
		assertNull(API.getActor("John Doe"));
	}

	@Test
	final void testAddRole() {
		API.addActor(new Actor("Matt Damon", false, "USA", 1970, 10, 8));
		API.addMovie(new Movie("The Martian", 144, "Comedy (apparently)", "During a manned mission to Mars...", "image.jpg", 2015, 10, 2));
		API.addRole(new Link<Actor, String, Movie>(API.getActor("Matt Damon"), "Mark Watney", API.getMovie("The Martian")));
		assertNotNull(API.getRole("Mark Watney"));
		assertEquals(API.getActor("Matt Damon"), API.getRole("Mark Watney").source());
		assertEquals(API.getMovie("The Martian"), API.getRole("Mark Watney").dest());
		assertEquals("Mark Watney", API.getRole("Mark Watney").path());
		
	}

	@Test
	final void testGetRole() {
		Link<Actor,String,Movie> temp = API.getRole("Chuck Noland");
		assertEquals("Chuck Noland", temp.path());
		assertEquals("Tom Hanks", temp.source().getName());
		assertEquals("Cast Away", temp.dest().getTitle());
	}

	@Test
	final void testDropActor() {
		API.dropActor("Tom Hanks");
		assertNull(API.getRole("Chuck Noland"));
		assertNull(API.getActor("Tom Hanks"));
	}

	@Test
	final void testDropMovie() {
		API.dropMovie("Cast Away");
		assertNull(API.getRole("Chuck Noland"));
		assertNull(API.getMovie("Cast Away"));
	}

	@Test
	final void testGetActorRole() throws Exception {
		assertEquals("Chuck Noland", API.getActorRole(API.getActor("Tom Hanks")).get(0).car());
		assertEquals("Cast Away", API.getActorRole(API.getActor("Tom Hanks")).get(0).cdr().getTitle());
	}
	@Test
	final void testGetMovieRole(){
		assertEquals("Chuck Noland", API.getMovieRole(API.getMovie("Cast Away")).get(p -> p.cdr().equals("Chuck Noland")).cdr());
		assertEquals("Tom Hanks", API.getMovieRole(API.getMovie("Cast Away")).get(p->p.cdr().equals("Chuck Noland")).car().getName());
	}

}
