package view;

import java.io.IOException;

import controller.API;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;



public class Main extends Application implements EventHandler<ActionEvent> {
	
	Stage window;
	Scene scene1, scene2, scene3, scene4, scene5;
	Boolean closeNS = false;
	Stage previousStage;
	FXMLLoader loader;
	Scene currentScene;
	
	@FXML private ListView listviewMovies;
	@FXML private ListView listviewActors;
	@FXML private ListView actorSearchMovies;
	@FXML private ListView movieSearchActors;
	
	@Override
	public void start(Stage primaryStage) {		
		try {
			window = primaryStage;
			loader = new FXMLLoader(getClass().getResource("MDB.fxml"));
			
			Pane root =  (Pane)loader.load();
			scene1 =new Scene(root,600,400);	
			
			window.setMinWidth(610);
			window.setMinHeight(440);
			window.setMaxWidth(610);
			window.setMaxHeight(440);
			window.setScene(scene1);
			window.setTitle("MDB (Written by Conor Giles and Andrew Bates)");
			window.show();
			setPreviousStage(window);
			
			}
		catch(IOException e){}
		}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void handle(ActionEvent event) {
            
			String buttonPressed = ((Button) event.getSource()).getId();
			
            if(buttonPressed.equals("viewMovies"))
            {
            	
            	Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        		stage.close();
            	try {
					changeScene("View Movies", "ViewMovies.fxml");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}        	
            }
            if(buttonPressed.equals("viewActors"))
            {
            	Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        		stage.close();
            	try {
					changeScene("View Actors", "ViewActors.fxml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            if(buttonPressed.equals("addMovies"))
            {
            	Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        		stage.close();
            	try {
					changeScene("Add Movies", "AddMovies.fxml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
            }
            if(buttonPressed.equals("addActors"))
            {
            	Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        		stage.close();
            	try {
					changeScene("View Actors", "AddActors.fxml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
            if(buttonPressed.equals("closeScene"))
            {
            		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            		stage.close();
            		
            		try {
    					changeScene("MDB (Written by Conor Giles and Andrew Bates)", "MDB.fxml");
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				};
            }
            
            if(buttonPressed.equals("GTLT")) 
			{
				Button btn = ((Button) event.getSource());
				String buttonText = ((Button) event.getSource()).getText();
				if(buttonText.equals("<")) 
				{
				btn.setText(">");
				}
				else 
				{
					btn.setText("<");
				}
			}
            if(buttonPressed.equals("showMovieList")) 
			{	
            	listviewMovies.getItems().addAll("List Object");
			}
            
            if(buttonPressed.equals("showActorList")) 
			{	
            	listviewActors.getItems().addAll("List Object");
			}
            if(buttonPressed.equals("dispMovieSearch")) 
			{	
            	actorSearchMovies.getItems().addAll("List Object");
			}
            if(buttonPressed.equals("dispActorSearch")) 
			{	
            	movieSearchActors.getItems().addAll("List Object");
			}
            
            
            }
	
	public void setPreviousStage(Stage stage) 
	{
		
		previousStage = stage;
	}
	
	public void changeScene(String title, String fxml) throws IOException
	{
		loader = new FXMLLoader(getClass().getResource(fxml));
		Pane root =  (Pane)loader.load();
		Scene scene =new Scene(root,600,400);	
    	Stage stage = new Stage();
    	stage.setTitle(title);
    	stage.setScene(scene);
    	stage.show();   	
	}
}