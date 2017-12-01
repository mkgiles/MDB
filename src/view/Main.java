package view;


import java.io.IOException;

import controller.API;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
	
	@FXML private Label actorName;
	@FXML private Label actorDob;
	@FXML private Label actorGender;
	@FXML private Label actorNationality;
	
	@FXML private Label movieTitle;
	@FXML private Label movieDor;
	@FXML private Label movieRunningTime;
	@FXML private Label movieGenre;
	@FXML private Label movieDescription;
	
	@FXML private CheckBox actorNameCB;
	@FXML private CheckBox actorDobCB;
	@FXML private CheckBox actorNationalityCB;
	
	@FXML private CheckBox movieTitleCB;
	@FXML private CheckBox movieDurationCB;
	@FXML private CheckBox movieGenreCB;
	@FXML private CheckBox movieDorCB;
	
	@FXML private TextField movieDurationTextField;
	@FXML private TextField movieGenreTextField;
	@FXML private TextField movieDayTextField;
	@FXML private TextField movieMonthTextField;
	@FXML private TextField movieYearTextField;
	@FXML private TextField movieTitleTextField;
	
	@FXML private TextField actorNameTextField;
	@FXML private TextField actorDayTextField;
	@FXML private TextField actorMonthTextField;
	@FXML private TextField actorYearTextField;
	@FXML private TextField actorNationalityTextField;
	
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
            
            if(buttonPressed.equals("dispActorSearch")) 
			{  
            	Boolean actorNameBool = actorNameCB.isSelected();
            	Boolean actorDobBool = actorDobCB.isSelected();
            	Boolean actorNationalityBool = actorNationalityCB.isSelected();
            	
            	String actorNameInput = actorNameTextField.getText();
        	 	String actorDayInput = actorDayTextField.getText();
        	 	String actorMonthInput = actorMonthTextField.getText();
        	 	String actorYearInput = actorYearTextField.getText();
        	 	String actorNationalityInput = actorNationalityTextField.getText();
        	 	
        	 	
            	if(actorNameBool == true)
            	{
            		System.out.println("Name Checkbox selected.");
            		System.out.println(actorNameInput);
            	}
            	if(actorDobBool == true)
            	{
            		System.out.println("Date of Birth Checkbox selected.");
            		System.out.println(actorDayInput + "/" + actorMonthInput + "/" + actorYearInput);
            	}
            	if(actorNationalityBool == true)
            	{
            		System.out.println("Nationality Checkbox selected.");
            		System.out.println(actorNationalityInput);
            	}
            	movieSearchActors.getItems().setAll();
            	movieSearchActors.getItems().addAll("List Object");
			}
            
            if(buttonPressed.equals("dispMovieSearch")) 
			{  
            	
            	 	Boolean movieTitleBool = movieTitleCB.isSelected();
            	 	Boolean movieDurationBool = movieDurationCB.isSelected();
            	 	Boolean movieGenreBool = movieGenreCB.isSelected();
            	 	Boolean movieDorBool = movieDorCB.isSelected();
            	 	
            	 	String movieTitleInput = movieTitleTextField.getText();
            	 	String movieDayInput = movieDayTextField.getText();
            	 	String movieMonthInput = movieMonthTextField.getText();
            	 	String movieYearInput = movieYearTextField.getText();
            	 	String movieGenreInput = movieGenreTextField.getText();
            	 	String movieDurationInput = movieDurationTextField.getText();
            	 	
            	if(movieTitleBool == true)
            	{
            		System.out.println("Title Checkbox selected.");
            		System.out.println(movieTitleInput);
            	}
            	if(movieDorBool == true)
            	{
            		System.out.println("Date of Release Checkbox selected.");
            		System.out.println(movieDayInput + "/" + movieMonthInput + "/" + movieYearInput);
            	}
            	if(movieGenreBool == true)
            	{
            		System.out.println("Genre Checkbox selected.");
            		System.out.println(movieGenreInput);
            	}
            	if(movieDurationBool == true)
            	{
            		System.out.println("Duration Checkbox selected.");
            		System.out.println(movieDurationInput);
            	}
            	actorSearchMovies.getItems().setAll();
            	actorSearchMovies.getItems().addAll("List Object");
			}
            if(buttonPressed.equals("inspectMovie"))
            		{
            	String currentSelection = (String) listviewMovies.getSelectionModel().getSelectedItem();
            	System.out.println(currentSelection);
            	//Put function here to match title in selection with title of movie.
            	//Fill other fields with other criteria (Title, Running Time, Genre.)
            	movieTitle.setText(currentSelection);
            	movieDor.setText(currentSelection);
            	movieRunningTime.setText(currentSelection);
            	movieGenre.setText(currentSelection);
            	movieDescription.setText(currentSelection);
            		}
            if(buttonPressed.equals("inspectActor"))
    		{
            	String currentSelection = (String) listviewActors.getSelectionModel().getSelectedItem();
            	//Put function here to match name in selection with name of actor.
            	//Fill other fields with other criteria (Name, Gender, DoB, etc.)
            	System.out.println(currentSelection);
            	actorName.setText(currentSelection);
            	actorGender.setText(currentSelection);
            	actorDob.setText(currentSelection);
            	actorNationality.setText(currentSelection);
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
    	stage.setMinWidth(610);
		stage.setMinHeight(440);
		stage.setMaxWidth(610);
		stage.setMaxHeight(440);
    	stage.setTitle(title);
    	stage.setScene(scene);
    	stage.show();   	
	}
}