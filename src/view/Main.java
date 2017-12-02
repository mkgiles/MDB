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
	FXMLLoader loader;
	
	//Initialization of FXID's.
	
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
	
	@FXML private TextField movieAddDurationTextField;
	@FXML private TextField movieAddGenreTextField;
	@FXML private TextField movieAddDayTextField;
	@FXML private TextField movieAddMonthTextField;
	@FXML private TextField movieAddYearTextField;
	@FXML private TextField movieAddTitleTextField;
	@FXML private TextField movieAddDescriptionTextField;
	@FXML private TextField movieAddURLTextField;
	
	@FXML private TextField movieEditDurationTextField;
	@FXML private TextField movieEditGenreTextField;
	@FXML private TextField movieEditDayTextField;
	@FXML private TextField movieEditMonthTextField;
	@FXML private TextField movieEditYearTextField;
	@FXML private TextField movieEditTitleTextField;
	@FXML private TextField movieEditDescriptionTextField;
	@FXML private TextField movieEditURLTextField;
	
	@FXML private TextField actorNameTextField;
	@FXML private TextField actorDayTextField;
	@FXML private TextField actorMonthTextField;
	@FXML private TextField actorYearTextField;
	@FXML private TextField actorNationalityTextField;
	@FXML private TextField actorGenderTextField;
	
	@FXML private TextField actorAddNameTextField;
	@FXML private TextField actorAddDayTextField;
	@FXML private TextField actorAddMonthTextField;
	@FXML private TextField actorAddYearTextField;
	@FXML private TextField actorAddNationalityTextField;
	@FXML private TextField actorAddGenderTextField;
	
	@FXML private TextField actorEditNameTextField;
	@FXML private TextField actorEditDayTextField;
	@FXML private TextField actorEditMonthTextField;
	@FXML private TextField actorEditYearTextField;
	@FXML private TextField actorEditNationalityTextField;
	@FXML private TextField actorEditGenderTextField;
	
	@FXML private TextField loadNDBTextField;
	@FXML private TextField saveNDBTextField;
	
	private static String movieToBeEdited;
	private static String actorToBeEdited;
	
	//Initialization of primary scene as well as stage.
	
	@Override
	public void start(Stage primaryStage) {		
		try {
			window = primaryStage;
			loader = new FXMLLoader(getClass().getResource("MDB.fxml"));
			
			Pane root =  (Pane)loader.load();
			Scene scene =new Scene(root,600,400);	
			
			window.setMinWidth(610);
			window.setMinHeight(440);
			window.setMaxWidth(610);
			window.setMaxHeight(440);
			window.setScene(scene);
			window.setTitle("MDB (Written by Conor Giles and Andrew Bates)");
			window.show();
			
			}
		catch(IOException e){}
		}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	//Action event handler, reads the FXID's of each action source and uses them to determine what buttons they belong to
	//as well as what actions should be taken based on those buttons.
	
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
            //Returns to viewMovies from editing
            if(buttonPressed.equals("editMovieGoBack"))
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
            //Returns to viewActors from editing.
            if(buttonPressed.equals("editActorGoBack"))
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
            //Changes scene to viewActors
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
            //Changes scene to editActor only if there is an actor selected in the listview to edit.
            if(buttonPressed.equals("editActor") && listviewActors.getSelectionModel().getSelectedItem() != null)
            {
            	actorToBeEdited = (String) listviewActors.getSelectionModel().getSelectedItem();
            	Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        		stage.close();
            	try {
					changeScene("Edit Actor", "editActor.fxml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
            //Changes scene to editMovie only if there is a movie selected in the listview to edit.
            if(buttonPressed.equals("editMovie") && listviewMovies.getSelectionModel().getSelectedItem() != null)
            {
            	movieToBeEdited = (String) listviewMovies.getSelectionModel().getSelectedItem(); 
            	Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        		stage.close();
            	try {
					changeScene("Edit Movie", "editMovie.fxml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
            //Changes scene to AddMovies
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
            
            //Changes scene to AddActors
            if(buttonPressed.equals("addActors"))
            {
            	Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
        		stage.close();
            	try {
					changeScene("Add Actors", "AddActors.fxml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
            //Returns back to first scene (MDB)
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
            
            //Used to determine whether an argument is greater than or less than in regards to a date.
            
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
            
            //Shows movie list, populated with all movies.
            if(buttonPressed.equals("showMovieList")) 
			{	
            	listviewMovies.getItems().addAll("List Object");
			}
            //Shows actor list, populated with all actors.
            if(buttonPressed.equals("showActorList")) 
			{	
            	listviewActors.getItems().addAll("List Object");
			}
            
            //Shows results of search. Each bool determines whether search criteria is used or not.
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
            
            //Shows results of search. Each bool determines whether search criteria is used or not.
            //Need to add roles here, too.
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
            
            //Used to pass information from movie clicked on in listView to window on the right.
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
            
          //Used to pass information from Actor clicked on in listView to window on the right.
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
            
            //Used to add actors to system, based on what is read from the TextFields.
            if(buttonPressed.equals("addActorToSystem"))
    		{
            	
            	String actorAddNameInput = actorAddNameTextField.getText();
            	String actorAddDayInput = actorAddDayTextField.getText();
            	String actorAddMonthInput = actorAddMonthTextField.getText();
            	String actorAddYearInput = actorAddYearTextField.getText();
            	String actorAddGenderInput = actorAddGenderTextField.getText();
            	String actorAddNationalityInput = actorAddNationalityTextField.getText();
            	
            	System.out.println(actorAddNameInput);
            	System.out.println(actorAddDayInput + "/" + actorAddMonthInput + "/" + actorAddYearInput);
            	System.out.println(actorAddGenderInput);
            	System.out.println(actorAddNationalityInput);
            	
            	actorAddNameTextField.setText("");
            	actorAddDayTextField.setText("");
            	actorAddMonthTextField.setText("");
            	actorAddYearTextField.setText("");
            	actorAddGenderTextField.setText("");
            	actorAddNationalityTextField.setText("");
    		}
            
            
          //Used to add Movies to system, based on what is read from the TextFields.
            if(buttonPressed.equals("addMovieToSystem"))
    		{
            	
            	String movieAddTitleInput = movieAddTitleTextField.getText();
            	String movieAddDayInput = movieAddDayTextField.getText();
            	String movieAddMonthInput = movieAddMonthTextField.getText();
            	String movieAddYearInput = movieAddYearTextField.getText();
            	String movieAddDurationInput = movieAddDurationTextField.getText();
            	String movieAddGenreInput = movieAddGenreTextField.getText();
            	String movieAddDescriptionInput = movieAddDescriptionTextField.getText();
            	String movieAddURLInput = movieAddURLTextField.getText();
            	
            	System.out.println(movieAddTitleInput);
            	System.out.println(movieAddDayInput + "/" + movieAddMonthInput + "/" + movieAddYearInput);
            	System.out.println(movieAddDurationInput);
            	System.out.println(movieAddGenreInput);
            	System.out.println(movieAddDescriptionInput);
            	System.out.println(movieAddURLInput);
            	
            	movieAddTitleTextField.setText("");
            	movieAddDayTextField.setText("");
            	movieAddMonthTextField.setText("");
            	movieAddYearTextField.setText("");
            	movieAddDurationTextField.setText("");
            	movieAddGenreTextField.setText("");
            	movieAddDescriptionTextField.setText("");
            	movieAddURLTextField.setText("");
            	
            	
    		}
            
            //Used to confirm the changing of information about a movie, using input from the textfields.
            if(buttonPressed.equals("confirmMovieEdit"))
            		{
            			String movieEditTitleInput = movieEditTitleTextField.getText();
            			String movieEditDayInput = movieEditDayTextField.getText();
            			String movieEditMonthInput = movieEditMonthTextField.getText();
            			String movieEditYearInput = movieEditYearTextField.getText();
            			String movieEditDurationInput = movieEditDurationTextField.getText();
            			String movieEditGenreInput = movieEditGenreTextField.getText();
            			String movieEditDescriptionInput = movieEditDescriptionTextField.getText();
            			String movieEditURLInput = movieEditURLTextField.getText();
            			
            			movieEditTitleTextField.setText("");
                    	movieEditDayTextField.setText("");
                    	movieEditMonthTextField.setText("");
                    	movieEditYearTextField.setText("");
                    	movieEditDurationTextField.setText("");
                    	movieEditGenreTextField.setText("");
                    	movieEditDescriptionTextField.setText("");
                    	movieEditURLTextField.setText("");
            		}
          //Used to confirm the changing of information about an actor, using input from the textfields.
            if(buttonPressed.equals("confirmActorEdit"))
    		{
            	String actorEditNameInput = actorEditNameTextField.getText();
            	String actorEditDayInput = actorEditDayTextField.getText();
            	String actorEditMonthInput = actorEditMonthTextField.getText();
            	String actorEditYearInput = actorEditYearTextField.getText();
            	String actorEditGenderInput = actorEditGenderTextField.getText();
            	String actorEditNationalityInput = actorEditNationalityTextField.getText();
            	
            	actorEditNameTextField.setText("");
            	actorEditDayTextField.setText("");
            	actorEditMonthTextField.setText("");
            	actorEditYearTextField.setText("");
            	actorEditGenderTextField.setText("");
            	actorEditNationalityTextField.setText("");
    		}
            //Gets information already existing on an actor and puts it into the textfields for editing.
            if(buttonPressed.equals("getFieldsActor"))
    		{
            	System.out.println(actorToBeEdited);
            	actorEditNameTextField.setText(actorToBeEdited);
            	actorEditDayTextField.setText("");
            	actorEditMonthTextField.setText("");
            	actorEditYearTextField.setText("");
            	actorEditGenderTextField.setText("");
            	actorEditNationalityTextField.setText("");
    		}
          //Gets information already existing on a movie and puts it into the textfields for editing.
            if(buttonPressed.equals("getFieldsMovie"))
    		{
            	System.out.println(movieToBeEdited);
            	movieEditTitleTextField.setText(movieToBeEdited);
            	movieEditDayTextField.setText("");
            	movieEditMonthTextField.setText("");
            	movieEditYearTextField.setText("");
            	movieEditDurationTextField.setText("");
            	movieEditGenreTextField.setText("");
            	movieEditDescriptionTextField.setText("");
            	movieEditURLTextField.setText("");
    		}
            
            if(buttonPressed.equals("loadNDB"))
    		{
            	//loadNDBTextField is our text field.
    		}
            
            if(buttonPressed.equals("saveNDB"))
    		{
            	//saveNDBTextField is our text field.
    		}
            
            
            }

	
	//Changes scene, based on the FXML name that is fed into method. The Title is just used for presentation purposes.
	
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