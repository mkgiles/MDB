package view;


import java.io.IOException;
import java.time.LocalDate;
import java.util.function.BiPredicate;

import controller.API;
import controller.Ndb;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.*;
import structures.DataList;
import structures.Link;
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
	
	@FXML private ListView<Movie> listviewMovies;
	@FXML private ListView<Actor> listviewActors;
	@FXML private ListView<Movie> actorSearchMovies;
	@FXML private ListView<Actor> movieSearchActors;
	
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
	
	@FXML private TextField actorEditDayTextField;
	@FXML private TextField actorEditMonthTextField;
	@FXML private TextField actorEditYearTextField;
	@FXML private TextField actorEditNationalityTextField;
	@FXML private TextField actorEditGenderTextField;
	
	@FXML private TextField actorRemovalNameTextField;
	@FXML private TextField movieRemovalTitleTextField;
	
	@FXML private TextField loadNDBTextField;
	@FXML private TextField saveNDBTextField;
	private BiPredicate<Actor, Actor> movieSearchPredicate = (a,b) -> a.getName().compareTo(b.getName())<=0;
	
	@FXML private TextField actorAddRoleNameTextField;
	@FXML private TextField movieAddRoleTitleTextField;
	@FXML private TextField actorAddRoleCharacterTextField;
	
	@FXML private TextField actorRemoveRoleNameTextField;
	@FXML private TextField movieRemoveRoleTitleTextField;
	@FXML private TextField actorRemoveRoleCharacterTextField;
	private BiPredicate<Movie, Movie> actorSearchPredicate = (a,b) -> a.getTitle().compareTo(b.getTitle())<=0;
	
	private static Movie movieToBeEdited;
	private static Actor actorToBeEdited;

	
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
            	actorToBeEdited = listviewActors.getSelectionModel().getSelectedItem();
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
            	movieToBeEdited = listviewMovies.getSelectionModel().getSelectedItem(); 
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
            
          //Changes scene to removeActors
            if(buttonPressed.equals("removeActors"))
            {
            		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            		stage.close();
            		
            		try {
    					changeScene("Remove Actors", "removeActor.fxml");
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				};
            }
            
          //Changes scene to removeMovies
            if(buttonPressed.equals("removeMovies"))
            {
            		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            		stage.close();
            		
            		try {
    					changeScene("Remove Movies", "removeMovie.fxml");
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				};
            }
            
          //Changes scene to addRoles
            if(buttonPressed.equals("addRoles"))
            {
            		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            		stage.close();
            		
            		try {
    					changeScene("Add Roles", "addRole.fxml");
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				};
            }
            
          //Changes scene to removeRoles
            if(buttonPressed.equals("removeRoles"))
            {
            		Stage stage = (Stage)((Button) event.getSource()).getScene().getWindow();
            		stage.close();
            		
            		try {
    					changeScene("Remove Roles", "removeRole.fxml");
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
            	listviewMovies.getItems().clear();
            	for(int i = 0; i<API.listMovies().length();i++)
            		listviewMovies.getItems().add(API.listMovies().get(i));
			}
            //Shows actor list, populated with all actors.
            if(buttonPressed.equals("showActorList")) 
			{	
            	listviewActors.getItems().clear();
            	for(int i = 0; i<API.listActors().length();i++)
            		listviewActors.getItems().add(API.listActors().get(i));
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
        	 	
        	 	DataList<Actor> list = API.listActors();
        	 	
        	 	
            	if(actorNameBool == true)
            	{
            		System.out.println("Name Checkbox selected.");
            		System.out.println(actorNameInput);
            		list = list.getSubList(p -> p.getName().equals(actorNameInput));
            	}
            	if(actorDobBool == true)
            	{
            		System.out.println("Date of Birth Checkbox selected.");
            		System.out.println(actorDayInput + "/" + actorMonthInput + "/" + actorYearInput);
            		list = list.getSubList(p -> p.getDob().equals(LocalDate.of(Integer.parseInt(actorYearInput), Integer.parseInt(actorMonthInput), Integer.parseInt(actorDayInput))));
            	}
            	if(actorNationalityBool == true)
            	{
            		System.out.println("Nationality Checkbox selected.");
            		System.out.println(actorNationalityInput);
            		list = list.getSubList(p -> p.getNationality().equals(actorNationalityInput));
            	}
            	list = list.sort(movieSearchPredicate);
            	movieSearchActors.getItems().clear();
            	for(int i=0; i<list.length();i++) {
            		movieSearchActors.getItems().add(list.get(i));
            	}
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
            	 	
            	 	DataList<Movie> list = API.listMovies();
            	 	
            	if(movieTitleBool == true)
            	{
            		System.out.println("Title Checkbox selected.");
            		System.out.println(movieTitleInput);
            		list = list.getSubList(p -> p.getTitle().equals(movieTitleInput));
            	}
            	if(movieDorBool == true)
            	{
            		System.out.println("Date of Release Checkbox selected.");
            		System.out.println(movieDayInput + "/" + movieMonthInput + "/" + movieYearInput);
            		list = list.getSubList(p -> p.getDor().equals(LocalDate.of(Integer.parseInt(movieYearInput), Integer.parseInt(movieMonthInput), Integer.parseInt(movieDayInput))));
            		
            	}
            	if(movieGenreBool == true)
            	{
            		System.out.println("Genre Checkbox selected.");
            		System.out.println(movieGenreInput);
            		list = list.getSubList(p -> p.getGenre().equals(movieGenreInput));
            	}
            	if(movieDurationBool == true)
            	{
            		System.out.println("Duration Checkbox selected.");
            		System.out.println(movieDurationInput);
            		list = list.getSubList(p -> p.getRunningTime() == Integer.parseInt(movieDurationInput));
            	}
            	list = list.sort(actorSearchPredicate);
            	actorSearchMovies.getItems().clear();
            	for(int i=0; i<list.length();i++) {
            		actorSearchMovies.getItems().add(list.get(i));
            	}
			}
            
            //Used to pass information from movie clicked on in listView to window on the right.
            if(buttonPressed.equals("inspectMovie"))
            		{
            	Movie currentSelection = listviewMovies.getSelectionModel().getSelectedItem();
            	System.out.println(currentSelection);
            	//Put function here to match title in selection with title of movie.
            	//Fill other fields with other criteria (Title, Running Time, Genre.)
            	movieTitle.setText(currentSelection.getTitle());
            	movieDor.setText(currentSelection.getDor().toString());
            	movieRunningTime.setText("" + currentSelection.getRunningTime());
            	movieGenre.setText(currentSelection.getGenre());
            	movieDescription.setText(currentSelection.getDescription());
            		}
            
          //Used to pass information from Actor clicked on in listView to window on the right.
            if(buttonPressed.equals("inspectActor"))
    		{
            	Actor currentSelection = listviewActors.getSelectionModel().getSelectedItem();
            	//Put function here to match name in selection with name of actor.
            	//Fill other fields with other criteria (Name, Gender, DoB, etc.)
            	System.out.println(currentSelection);
            	actorName.setText(currentSelection.getName());
            	actorGender.setText(currentSelection.getGender()?"female":"male");
            	actorDob.setText(currentSelection.getDob().toString());
            	actorNationality.setText(currentSelection.getNationality());
    		}
            
            //Used to add actors to system, based on what is read from the TextFields.

            if(buttonPressed.equals("addActorToSystem") && !actorAddNameTextField.getText().equals("") 
                	&& !actorAddDayTextField.getText().equals("") && !actorAddMonthTextField.getText().equals("")
                	&& !actorAddYearTextField.getText().equals("") && !actorAddGenderTextField.getText().equals("")
                	&& !actorAddNationalityTextField.getText().equals("")) 
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
            	
            	API.addActor(new Actor(actorAddNameInput, actorAddGenderInput.toLowerCase().equals("female"),actorAddNationalityInput,Integer.parseInt(actorAddYearInput),Integer.parseInt(actorAddMonthInput),Integer.parseInt(actorAddDayInput)));
            	
            	actorAddNameTextField.setText("");
            	actorAddDayTextField.setText("");
            	actorAddMonthTextField.setText("");
            	actorAddYearTextField.setText("");
            	actorAddGenderTextField.setText("");
            	actorAddNationalityTextField.setText("");
    		}
            
            
          //Used to add Movies to system, based on what is read from the TextFields.
            if(buttonPressed.equals("addMovieToSystem") && !movieAddTitleTextField.getText().equals("") &&
            		!movieAddDayTextField.getText().equals("") && !movieAddMonthTextField.getText().equals("")
            		&& !movieAddYearTextField.getText().equals("") && !movieAddDurationTextField.getText().equals("")
            		&& !movieAddGenreTextField.getText().equals("") && !movieAddDescriptionTextField.getText().equals("")
            		&& !movieAddURLTextField.getText().equals(""))
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
            	
            	API.addMovie(new Movie(movieAddTitleInput, Integer.parseInt(movieAddDurationInput), movieAddGenreInput, movieAddDescriptionInput, movieAddURLInput, Integer.parseInt(movieAddYearInput), Integer.parseInt(movieAddMonthInput), Integer.parseInt(movieAddDayInput)));
            	
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
            if(buttonPressed.equals("confirmMovieEdit")
            		&& !movieEditDayTextField.getText().equals("")&& !movieEditMonthTextField.getText().equals("")
            		&& !movieEditYearTextField.getText().equals("")&& !movieEditDurationTextField.getText().equals("")
            		&& !movieEditGenreTextField.getText().equals("")&& !movieEditDescriptionTextField.getText().equals("")
            		&& !movieEditURLTextField.getText().equals(""))
            		{

            			API.getMovie(movieToBeEdited.getTitle()).setDor(Integer.parseInt(movieEditYearTextField.getText()),Integer.parseInt(movieEditMonthTextField.getText()),Integer.parseInt(movieEditDayTextField.getText()));
            			API.getMovie(movieToBeEdited.getTitle()).setRunningTime(Integer.parseInt(movieEditDurationTextField.getText()));
            			API.getMovie(movieToBeEdited.getTitle()).setGenre(movieEditGenreTextField.getText());
            			API.getMovie(movieToBeEdited.getTitle()).setDescription(movieEditDescriptionTextField.getText());
            			API.getMovie(movieToBeEdited.getTitle()).setPosterURL(movieEditURLTextField.getText());

            			
                    	movieEditDayTextField.setText("");
                    	movieEditMonthTextField.setText("");
                    	movieEditYearTextField.setText("");
                    	movieEditDurationTextField.setText("");
                    	movieEditGenreTextField.setText("");
                    	movieEditDescriptionTextField.setText("");
                    	movieEditURLTextField.setText("");
            		}
          //Used to confirm the changing of information about an actor, using input from the textfields.
            if(buttonPressed.equals("confirmActorEdit")
            		&& !actorEditDayTextField.getText().equals("") && !actorEditMonthTextField.getText().equals("")
            		 && !actorEditYearTextField.getText().equals("") && !actorEditGenderTextField.getText().equals("")
            		 && !actorEditNationalityTextField.getText().equals(""))
    		{

            	API.getActor(actorToBeEdited.getName()).setDob(Integer.parseInt(actorEditYearTextField.getText()),Integer.parseInt(actorEditMonthTextField.getText()),Integer.parseInt(actorEditDayTextField.getText()));
            	API.getActor(actorToBeEdited.getName()).setGender(actorEditGenderTextField.getText().toLowerCase()=="female");
            	API.getActor(actorToBeEdited.getName()).setNationality(actorEditNationalityTextField.getText());

            	
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
            	actorEditDayTextField.setText("" + actorToBeEdited.getDob().getDayOfMonth());
            	actorEditMonthTextField.setText("" + actorToBeEdited.getDob().getMonthValue());
            	actorEditYearTextField.setText("" + actorToBeEdited.getDob().getYear());
            	actorEditGenderTextField.setText(actorToBeEdited.getGender()?"female":"male");
            	actorEditNationalityTextField.setText(actorToBeEdited.getNationality());

    		}
          //Gets information already existing on a movie and puts it into the textfields for editing.
            if(buttonPressed.equals("getFieldsMovie"))
    		{
            	System.out.println(movieToBeEdited);

            	movieEditDayTextField.setText("" + movieToBeEdited.getDor().getDayOfMonth());
            	movieEditMonthTextField.setText("" + movieToBeEdited.getDor().getMonthValue());
            	movieEditYearTextField.setText("" + movieToBeEdited.getDor().getYear());
            	movieEditDurationTextField.setText("" + movieToBeEdited.getRunningTime());
            	movieEditGenreTextField.setText(movieToBeEdited.getGenre());
            	movieEditDescriptionTextField.setText(movieToBeEdited.getDescription());
            	movieEditURLTextField.setText(movieToBeEdited.getPosterURL());

    		}
            
            if(buttonPressed.equals("loadNDB"))
    		{
            	//loadNDBTextField is our text field.
            	try {
					Ndb.parse(loadNDBTextField.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
            
            if(buttonPressed.equals("saveNDB"))
    		{
            	//saveNDBTextField is our text field.
            	try {
					Ndb.save(saveNDBTextField.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
            
            if(buttonPressed.equals("confirmActorRemoval") && !actorRemovalNameTextField.equals(""))
    		{
            	API.dropActor(actorRemovalNameTextField.getText());
            	actorRemovalNameTextField.setText("");
    		}
            
            if(buttonPressed.equals("confirmMovieRemoval") && !movieRemovalTitleTextField.equals(""))
    		{
            	API.dropMovie(movieRemovalTitleTextField.getText());
            	movieRemovalTitleTextField.setText("");	  	
    		}
            
            if(buttonPressed.equals("movieActorSort"))
    		{
            	Button btn = ((Button) event.getSource());
				String buttonText = ((Button) event.getSource()).getText();
				if(buttonText.equals("Name (Ascending)")) 
				{
				btn.setText("Name (Descending)");
				movieSearchPredicate = (a,b) -> a.getName().compareTo(b.getName())>=0;
				}
				if(buttonText.equals("Name (Descending)")) 
				{
				btn.setText("DOB (Ascending)");
				movieSearchPredicate = (a,b) -> a.getDob().compareTo(b.getDob())<=0;
				}
				if(buttonText.equals("DOB (Ascending)"))
				{
				btn.setText("DOB (Descending)");
				movieSearchPredicate = (a,b) -> a.getDob().compareTo(b.getDob())>=0;
				}
				if(buttonText.equals("DOB (Descending)")) 
				{
				btn.setText("Nationality (Ascending)");
				movieSearchPredicate = (a,b) -> a.getNationality().compareTo(b.getNationality())<=0;
				}
				if(buttonText.equals("Nationality (Ascending)")) 
				{
				btn.setText("Nationality (Descending)");
				movieSearchPredicate = (a,b) -> a.getNationality().compareTo(b.getNationality())>=0;
				}
				if(buttonText.equals("Nationality (Descending)")) 
				{
				btn.setText("Name (Ascending)");
				movieSearchPredicate = (a,b) -> a.getName().compareTo(b.getName())<=0;
				}
    		}
            if(buttonPressed.equals("actorMovieSort"))
    		{
            	Button btn = ((Button) event.getSource());
				String buttonText = ((Button) event.getSource()).getText();
				if(buttonText.equals("Title (Ascending)")) 
				{
				btn.setText("Title (Descending)");
				actorSearchPredicate = (a,b) -> a.getTitle().compareTo(b.getTitle())>=0;
				}
				if(buttonText.equals("Title (Descending)")) 
				{
				btn.setText("Duration (Ascending)");
				actorSearchPredicate = (a,b) -> a.getRunningTime()-b.getRunningTime()<=0;
				}
				if(buttonText.equals("Duration (Ascending)")) 
				{
				btn.setText("Duration (Descending)");
				actorSearchPredicate = (a,b) -> a.getRunningTime()-b.getRunningTime()>=0;
				}
				if(buttonText.equals("Duration (Descending)")) 
				{
				btn.setText("DOR (Ascending)");
				actorSearchPredicate = (a,b) -> a.getDor().compareTo(b.getDor())<=0;
				}
				if(buttonText.equals("DOR (Ascending)"))
				{
				btn.setText("DOR (Descending)");
				actorSearchPredicate = (a,b) -> a.getDor().compareTo(b.getDor())>=0;
				}
				if(buttonText.equals("DOR (Descending)")) 
				{
				btn.setText("Genre (Ascending)");
				actorSearchPredicate = (a,b) -> a.getGenre().compareTo(b.getGenre())<=0;
				}
				if(buttonText.equals("Genre (Ascending)")) 
				{
				btn.setText("Genre (Descending)");
				actorSearchPredicate = (a,b) -> a.getGenre().compareTo(b.getGenre())>=0;
				}
				if(buttonText.equals("Genre (Descending)")) 
				{
				btn.setText("Title (Ascending)");
				actorSearchPredicate = (a,b) -> a.getTitle().compareTo(b.getTitle())<=0;
				}
    		}
            
            if(buttonPressed.equals("confirmRoleAdd") && !actorAddRoleNameTextField.getText().equals("")
            		&& !movieAddRoleTitleTextField.getText().equals("") && !actorAddRoleCharacterTextField.getText().equals(""))
    		{
            	API.addRole(new Link<Actor, String, Movie>(API.getActor(actorAddRoleNameTextField.getText()),actorAddRoleCharacterTextField.getText(),API.getMovie(movieAddRoleTitleTextField.getText())));
            	actorAddRoleNameTextField.setText("");
            	actorAddRoleCharacterTextField.setText("");
            	movieAddRoleTitleTextField.setText("");
    		}
            
            if(buttonPressed.equals("confirmRoleRemove") && !actorRemoveRoleNameTextField.getText().equals("")
            		&& !movieRemoveRoleTitleTextField.getText().equals("") && !actorRemoveRoleCharacterTextField.getText().equals(""))
    		{
            	API.dropRole(actorRemoveRoleCharacterTextField.getText());
            	actorRemoveRoleNameTextField.setText("");
            	actorRemoveRoleCharacterTextField.setText("");
            	movieRemoveRoleTitleTextField.setText("");
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