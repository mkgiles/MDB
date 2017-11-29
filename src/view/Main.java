package view;

import java.io.IOException;

import controller.API;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.*;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


public class Main extends Application implements EventHandler<ActionEvent> {

	@Override
	public void start(Stage primaryStage) {

		
		try {
			Pane root =
			FXMLLoader.load(getClass().getResource("MDB.fxml"));
			Scene s=new Scene(root,600,400);
			primaryStage.setMinWidth(610);
			primaryStage.setMinHeight(440);
			primaryStage.setMaxWidth(610);
			primaryStage.setMaxHeight(440);
			primaryStage.setScene(s);
			primaryStage.setTitle("MDB (Written by Conor Giles and Andrew Bates)");
			primaryStage.show();
			}catch(IOException e){}
		}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void handle(ActionEvent event) {
            System.out.println("Test");     
	}
	
	public void handleViewMovie(ActionEvent event) {
        System.out.println("View Movies");     
}
	
	public void handleViewActor(ActionEvent event) {
    System.out.println("View Actors");     
}
	
	//This is how movies are added? Requires Movie be public.
	public void handleAddMovie(ActionEvent event) {
        System.out.println("Add Movies");
       // Movie movie = new Movie("Title of Movie", 1, "Horror", "A Horror movie", "URL of Poster");
       //API.addMovie(movie);  
}
	public void handleAddActors(ActionEvent event) {
        System.out.println("Add Actors");    
	}
}