package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {

	private Stage primaryStage;
	private AnimalList al;
	private PersonList pl;

	//The constructor creates controller object which controls the functionality of the program
	//during run time. It gets the singleton instance of the animalList and also creates the stage screen
	//which we use for lost,adoption and found
	//All classes can share it because it always gets the object in use
	public Controller() {
		al = AnimalList.getInstance();
		primaryStage = new Stage();
	}

	
	//This method adds an animal to our animal list. We pass in the parameters for the animal 
	//and then create a new animal object and then add it to our animal list
	public void addAnimal(String name, int age, boolean gender, String description, String aType, String breed,
			String colour) {
		Animal a = new Animal(name, age, gender, description, aType, breed, colour);
		al.addAnimal(a);
		System.out.println(name + " is now added to  the list!");
	}
	
	//pass in the person list created in main
	public void setPersonlist(PersonList pl){
		this.pl = pl;
	}
	
	//This method takes in a String value and and then calls the method removeAnimalByName from AnimalList class
	//It check if there is ananial with the name, returns true and removes the animal.
	//If no animal is found then no animal is removed and it returns false.
	//It is not really used because it is replaced by removeAnimal 
	public void removeAnimal(String name) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter the name of the dog  : ");
		name = sc.nextLine();
		al.removeAnimalByName(name);
		System.out.println(name + " is now removed from our list");

		sc.close();
	}

	//This method creates new screens depending on what is pressed.
	// if i = 0 then its a lost screen and object
	// if i = 1 then its a found object
	//It is controlled by main buttons and their actions.
	//Line 50 to 53 illustrates the call
	//I only used one method because Lost and found have similar functionalities and it reduces redundancy
	//The only difference is that you create new Categories and then I add it to the animal.
	//line 236	 
	public void newLostAnimal(String title, int i) {

		// Setup a new GridPane
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(5, 5, 5, 5)); // distance between items
		Scene scene = new Scene(pane, 600, 475); // height and width of new
		// window
		primaryStage.setScene(scene);

		// set the title
		// each item added to the grid pane at column,row
		Text sceneTitle = new Text(title);
		sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		pane.add(sceneTitle, 0, 0, 1, 1);
		Label name = new Label("Name:");
		pane.add(name, 0, 1);// add to column 0 and row 1. this adds to the
		// first column on the second row of the grid
		final TextField nameField = new TextField();
		pane.add(nameField, 1, 1);
		Label age = new Label("Age:");
		final TextField ageField = new TextField();
		pane.add(age, 0, 2);
		pane.add(ageField, 1, 2);

		// A CheckBox without a caption
		CheckBox gender = new CheckBox();
		gender.setText("Gender");
		gender.setSelected(true);
		pane.add(gender, 1, 3);

		Label colour = new Label("Colour: ");
		pane.add(colour, 0, 4);
		final TextField colourField = new TextField();
		pane.add(colourField, 1, 4);

		Label breed = new Label("Breed: ");
		pane.add(breed, 0, 5);
		final TextField breedField = new TextField();
		pane.add(breedField, 1, 5);

		Label description = new Label("Description: ");
		pane.add(description, 0, 6);
		final TextField descriptionField = new TextField();
		pane.add(descriptionField, 1, 6); // adds item to 2nd column and 7th row
		// of the grid pane

		Label type = new Label("Type: ");
		pane.add(type, 0, 7);
		final TextField typeField = new TextField();
		pane.add(typeField, 1, 7);

		Label location = new Label("Location: ");
		pane.add(location, 0, 8);
		final TextField locationField = new TextField();
		pane.add(locationField, 1, 8);

		Label error = new Label("");
		pane.add(error, 0, 9);

		Button addAnimalButton = new Button("Add Animal");
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		// set the button to appear on the
		// bottom right of the pane
		hbox.getChildren().add(addAnimalButton);
		pane.add(hbox, 1, 10);
		primaryStage.setTitle("New Animal");
		scene.getStylesheets().add(getClass().getResource("otherFunctions.css").toExternalForm());
		primaryStage.show();

		// event handler for found/lost
		addAnimalButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				// get all the TextBox values
				// boolean add will be false if some details provided are incorrect
				boolean add = true;
				String errorMessage = "";
				String name = nameField.getText();
				String age = ageField.getText();
				String animalColour = colourField.getText();
				String animalBreed = breedField.getText();
				String desc = descriptionField.getText();
				String animalType = typeField.getText();
				String loc = locationField.getText();

				// if no name provided then set add to false and set the
				// errormessage
				// same for all items below also
				if (name.equals("")) {
					add = false;
					errorMessage = "Enter animal Name";
				}

				if (animalColour.equals("")) {
					add = false;
					errorMessage = "Enter animal Colour";
				}

				if (animalBreed.equals("")) {
					add = false;
					errorMessage = "Enter animal Breed";
				}

				if (desc.equals("")) {
					add = false;
					errorMessage = "Enter animal Description";
				}

				// check has the user entered an animal type
				// we only want cat or dog
				animalType = animalType.toUpperCase();
				if (animalType.equals("") || !(animalType.equals("CAT") || animalType.equals("DOG"))) {
					add = false;
					errorMessage = "Enter animal Type(cat or dog)";
				}

				// get the gender selected
				//If selected - male
				boolean gen = gender.isSelected();

				// get the animal age
				// we are using a TextBox so we need to parse the value into an int
				// if it is not an int then we display the error
				int animalAge = 0;
				try {
					animalAge = Integer.parseInt(age);
				} catch (NumberFormatException e) {
					add = false;
					errorMessage = "Please enter a numeric age";
				}

				// if add is true then all fields entered correctly
				if (add) {
					Category c;
					// get the date
					Calendar d = Calendar.getInstance();
					Date date = d.getTime();
					// date.setDate(d.get);
					// check whether the category should be found or lost
					// based on int value provided
					d.setTime(date);
					if (i == 0) {
						c = new Lost(d, loc);
					} else {
						c = new Found(d, loc);
					}
					// create a hardocded new person
					Person p = new Person("test", "Test", "Test", 123456);
					// add the person to the category
					c.setPerson(p);
					// create the new animal
					Animal newAnimal = new Animal(name, animalAge, gen, desc, animalType, animalBreed, animalColour);
					// add the category to the list
					newAnimal.setCategory(c);
					// add the animal to the list
					al.addAnimal(newAnimal);
					error.setText("Animal added with name: " + name);

					Alert a = new Alert(AlertType.INFORMATION);
					a.setTitle("Animal added");
					a.setHeaderText(newAnimal.getName()+" has been added");
					a.setResizable(false);
					a.setContentText(newAnimal.getName()+" is now on the !"+newAnimal.getCategory().getType()+" list!");
					a.showAndWait();
					//The above code creates the category based on the int passed in. You can see previously 
					//that if we want a found window we pass in 1 and a lost window we pass in 0. 
					//This is then used to determine which category we are creating the animal under.

					// close the window
					primaryStage.close();
				} else {
					// some details entered incorrectly, show the errormessage
					// by setting the error label text
					error.setText(errorMessage);
				}
			}
		});

	}

	
	//This method creates new adoption window. No adopted animals are created. They get moved from 
	//either lost or found.
	//To do this we need to be able to select the animal from a dropdown.
	//To do this we created a new method in the AnimalList class called 
	//public ArrayList<String> getAnimalsForComboBox()
	//It returns and arrayList of strings with the names of all the animals
	//It is then used to populate the dropdown by adding each name from the arrayList to the dropdown.
	@SuppressWarnings("unchecked")
	public void newAdoptedAnimal() {
		// Setup a new gridpane
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(5, 5, 5, 5)); // distance between items
		Scene scene = new Scene(pane, 600, 475); // height and width of new
		// window
		primaryStage.setScene(scene);

		Text sceneTitle = new Text("Add Animal for Adoption");
		sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		pane.add(sceneTitle, 0, 0, 1, 1);
		Label name = new Label("Select Animal:");
		pane.add(name, 0, 1);// add to column 0 and row 1. this adds to the
		// first column on the second row of the grid

		final ComboBox<String> animalComboBox = new ComboBox();
		animalComboBox.getItems().addAll(al.getAnimalsForComboBox());
		pane.add(animalComboBox, 1, 1);

		//I use checkboxes to generate the Booleans required for neutered and reserved ect.. 
		//If selected then the boolean will be true and if not then it will be false. 
		//We can get this value by checking the checkbox.isSelected() method which returns a Boolean value. 
		
		// A checkbox without a caption
		CheckBox neutered = new CheckBox();
		neutered.setText("Neutered");
		neutered.setSelected(false);
		pane.add(neutered, 1, 2);

		// A checkbox without a caption
		CheckBox chipped = new CheckBox();
		chipped.setText("Chipped");
		chipped.setSelected(false);
		pane.add(chipped, 1, 3);

		// A checkbox without a caption
		CheckBox vaccinated = new CheckBox();
		vaccinated.setText("Vaccinated");
		vaccinated.setSelected(false);
		pane.add(vaccinated, 1, 4);

		// A checkbox without a caption
		CheckBox reserved = new CheckBox();
		reserved.setText("Reserved");
		reserved.setSelected(false);
		pane.add(reserved, 1, 5);

		Label status = new Label("Animal Status:");
		pane.add(status, 0, 6);// add to column 0 and row 1. this adds to the
		// first column on the second row of the grid

		//the selected status
		//Very similar to the animal drop down and has 3 strings in it that the users selects.
		final ComboBox<String> statusComboBox = new ComboBox();
		statusComboBox.getItems().addAll(
				"inTraining","Ready","Not Ready"
				);

		pane.add(statusComboBox, 1, 6);
		
		


		//Add interested people for the adopted animal. I used multi select combobox
		//It is a menu button with checkmenuitems that allows users to select more than 1 items. 
		//Selected icon appears next to the selected option. It is removed when selected again
		//From google example with few changes
		//I used people from person list created in Main.
		//Take the id and name of each person and add them to the list
		final MenuButton interested = new MenuButton("Interested People");  
		final ArrayList<CheckMenuItem> selectedPeople = new ArrayList<CheckMenuItem>();
		for(Person p:pl.people){
			CheckMenuItem i = new CheckMenuItem(p.getName()+" - "+p.getPhoneNumber());
			selectedPeople.add(i);
		}
		interested.getItems().addAll(selectedPeople);  
		
		//Listview of Strings. It adds or removes items from the list if selected or not from menubutton
		// If a value changed it was either previousl selected or is now 
		//selected and is either added or removed from the list based on this. 
		//Google exmaple
		final ListView<String> selectedItems = new ListView<>();  
		for (final CheckMenuItem item : selectedPeople) {  
			item.selectedProperty().addListener(new ChangeListener<Boolean>() {  
				@Override  
				public void changed(ObservableValue<? extends Boolean> obs,  
						Boolean wasPreviouslySelected, Boolean isNowSelected) {  
					if (isNowSelected) {  
						selectedItems.getItems().add(item.getText());  
					} else {  
						selectedItems.getItems().remove(item.getText());  
					}  
				}  
			});  
		}  
		//label for the multi select
		Label interestedParties = new Label("People Interested(Multi-Select):");
		pane.add(interestedParties, 0, 7);// add to column 0 and row 1. this adds to the		

		pane.add(interested, 1, 7);

		Label dateLabel = new Label("Adoption Date: ");
		pane.add(dateLabel, 1, 9);// add to column 0 and row 1. this adds to the


		//datePicker again differs from lost./found. It allows the user to pick a date from a popup(built in feature)
		//included is a label that we will store the date selected on the window so the user can see.
		//Could be used in other windows to to display the category date rather than the current date.
		Label selectedDate = new Label("");
		pane.add(selectedDate, 2, 9);// add to column 0 and row 1. this adds to the

		final DatePicker datePicker = new DatePicker();
		datePicker.setOnAction(new EventHandler() {
			public void handle(Event t) {
				LocalDate date = datePicker.getValue();
				selectedDate.setText("" + date);
			}
		});
		pane.add(datePicker,0,9);

		//label for any error messages
		Label errorLabel = new Label("");
		pane.add(errorLabel, 0, 10);

		Button addAdoptionButton = new Button("Add To Adopted List");

		// event handler for found/lost
		addAdoptionButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				errorLabel.setText("");
				Animal adoptedAnimal = null;
				boolean add = true;
				//check if user selected an animal
				//if no animal is selected then it will return a
				//null pointer exception when getting the value
				try{
					String animalName = (String) animalComboBox.getSelectionModel().getSelectedItem().toString(); 
					//since we are using a javafx observable list
					//we need to loop through and get the index as the animals were added
					//to the list by name in the index from the list
					//animal name added at position 0 in the combobox is the animal in position 0
					//of the animal list
					int i = 0;
					for(String s:animalComboBox.getItems()){
						if(s.equals(animalName)){
							adoptedAnimal = al.getAnimal(i);
						}
						i++;
					}

					System.out.println(adoptedAnimal.getName());

				}				
				catch(NullPointerException e){
					errorLabel.setText("Please Select an Animal for Adoption");
					add = false;
				}

				//get the checkbox values
				boolean isNeutered = neutered.isSelected();
				boolean isVaccinated = vaccinated.isSelected();
				boolean isChipped = chipped.isSelected();
				boolean isReserved = reserved.isSelected();

				//check if user selected an animal status
                //if no status is selected then it will return a
                //null pointer exception when getting the value
                String animalStatus = "";
                try{
                    animalStatus = (String) statusComboBox.getSelectionModel().getSelectedItem().toString(); 
                }                
                catch(NullPointerException e){
                    errorLabel.setText("Please Select an Animal Status for Adoption");
                    add = false;
                }
                
                //This is where the date is used. Check if date is being selected.
                //If not set errorLabel to inform them to select a date.
                //If selected, we need to parse the value into a date object
                //I used simpleDateFormatter to do that.(From String to date object)
                //Use that date to create a calendar event for the adoption
                Calendar adoptionDate = Calendar.getInstance();
				if(selectedDate.getText().equals("")){
					add = false;
					errorLabel.setText("Please select an adoption date");
				}else{

					SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
					Date d;
					try {
						d = format1.parse(selectedDate.getText());
						adoptionDate.setTime(d); 
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				PersonList interestedPeople = new PersonList();
				//get the interested parties
				//need to loop through selected items and
				//match to person in the person list
				for(String people: selectedItems.getItems()){
					for(Person p:pl.people){
						String valueToMatch = p.getName()+" - "+p.getPhoneNumber();
						if(valueToMatch.equals(people)){
							interestedPeople.addPerson(p);
						}
					}
				}
				//no errors
				if(add){
					Adoption adoption = new Adoption(adoptionDate);
					adoption.setChipped(isChipped);
					adoption.setNeutered(isNeutered);
					adoption.setInterested(interestedPeople);
					adoption.setReserved(isReserved);
					adoption.setStatus(animalStatus);
					adoption.setVaccinated(isVaccinated);
					//set the animal in the list to now have a category of adopted
					al.getAnimal(al.getAnimalbyPosition(adoptedAnimal.getID())).setCategory(adoption);

					Alert a = new Alert(AlertType.INFORMATION);
					a.setTitle("Animal updated");
					a.setHeaderText(adoptedAnimal.getName()+" has been updated");
					a.setResizable(false);
					a.setContentText(adoptedAnimal.getName()+" is now available for adoption!");
					a.showAndWait();
					//close the window
					primaryStage.close();
				}
			}
		});

		pane.add(addAdoptionButton, 1, 11);

		primaryStage.setTitle("Adoption");
		scene.getStylesheets().add(getClass().getResource("otherFunctions.css").toExternalForm());
		primaryStage.show();

	}

	// save the running lists in the system - Serialization 
	public void save() {
		SaveAndLoad save = new SaveAndLoad("shelter.ser");
		save.saveToFile(al);
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("Congratulations");
		a.setHeaderText("List of animals is now saved!");
		a.showAndWait();
	}

	// set the list in the AnimalList class to be the arraylist that is returned from the read method. 
	//Deserialization
	public void readFromFile(){
		SaveAndLoad load = new SaveAndLoad("shelter.ser");
		al.setList(load.readFromFile());
//		Alert a = new Alert(AlertType.INFORMATION);
//		a.setTitle("Congratulations");
//		a.setHeaderText("You have successfully loaded the list of animals!");
//		a.showAndWait();
	}

	//This method creates a new window where we can enter a name of an animal and remove them.
	//Make user user has entered a value and call removeAnimalByName method. Returns true/false value
	//If name is matched and animal is removed we display an alert to confirm
	public void removeAnimal() {
		// same as add with the grid
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(5, 5, 5, 5));
		Scene scene = new Scene(pane, 600, 475);
		primaryStage.setScene(scene);

		Text sceneTitle = new Text("Remove animal from shelter");
		Label name = new Label("Animal Name: ");
		pane.add(name, 0, 1);
		final TextField nameField = new TextField();
		pane.add(nameField, 1, 1);

		Button searchButton = new Button("Remove Animal by Name");
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.getChildren().add(searchButton);
		pane.add(hbox, 2, 1);

		Label error = new Label("");
		pane.add(error, 0, 6);
		// actionlistener for remove
		searchButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				// get the name to be searched
				String search = nameField.getText();
				// check if value has been provided
				if (search.equals("")) {
					error.setText("Please enter a name to search");
				} else {
					// check if animal removal by name is successful
					boolean success = al.removeAnimalByName(search);
					if (success) {
						Alert a = new Alert(AlertType.INFORMATION);
						a.setTitle("Animal updated");
						a.setHeaderText(search+" has been removed");
						a.setResizable(false);
						a.setContentText(search+" is no longer on the system!");
						a.showAndWait();
						// close the window
						primaryStage.close();
					} else {
						// not successful
						error.setText(search + " not found");
					}
				}
			}

		});

		final ComboBox<String> animalComboBox = new ComboBox();
		animalComboBox.getItems().addAll(al.getAnimalsForComboBox());
		pane.add(animalComboBox, 1, 10);

		Label selectanimal = new Label("Select Animal:");
		pane.add(selectanimal, 0, 10);

		// add a list to the item below
		Button removeButton = new Button("Remove selected Animal");
		removeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				Animal removedAnimal = null;
				boolean add = true;
				//check if user selected an animal
				//if no animal is selected then it will return a
				//null pointer exception when getting the value
				try{
					String animalName = (String) animalComboBox.getSelectionModel().getSelectedItem().toString(); 
					//since we are using a javafx observable list
					//we need to loop through and get the index as the animals were added
					//to the list by name in the index from the list
					//animal name added at position 0 in the combobox is the animal in position 0
					//of the animal list
					int i = 0;
					for(String s:animalComboBox.getItems()){
						if(s.equals(animalName)){
							removedAnimal = al.getAnimal(i);
						}
						i++;
					}

					// check if animal removal by name is successful
					boolean success = al.removeAnimalByName(removedAnimal.getName());
					if (success) {
						Alert a = new Alert(AlertType.INFORMATION);
						a.setTitle("Animal updated");
						a.setHeaderText(removedAnimal.getName()+" has been removed");
						a.setResizable(false);
						a.setContentText(removedAnimal.getName()+" is no longer on the system!");
						a.showAndWait();
						 //close the window
						primaryStage.close();
					} else {
						// not successful
						error.setText(removedAnimal.getName() + " not removed");
					}

				}				
				catch(NullPointerException e){
					error.setText("Please Select an Animal to be removed");
					add = false;
				}


			}

		});
		HBox hbox2 = new HBox(10);
		hbox2.setAlignment(Pos.BOTTOM_RIGHT);
		hbox2.getChildren().add(removeButton);
		pane.add(hbox2, 2, 10);
		primaryStage.setTitle("Remove Animal");
		scene.getStylesheets().add(getClass().getResource("otherFunctions.css").toExternalForm());
		primaryStage.show();
	}
}
