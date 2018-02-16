package application;


import java.io.Serializable;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application implements Serializable{

	static AnimalList shelterAnimals = AnimalList.getInstance();
	static PersonList people;

	private Controller control;
	Stage window;
	Button button;
	private static  Button      lostAnimal,foundAnimal,adoptedAnimal,removeAnimal,listAllAnimals,quit;
	private static  TextArea    details;


	public void start(Stage primaryStage) {

		window = primaryStage;
		control = new Controller();
		control.setPersonlist(people);
		window.setTitle("Animal Shelter");
		control.readFromFile();

		MenuBar menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
		MenuItem load = new MenuItem("Load");
		MenuItem saveTo = new MenuItem("Save"); 
		MenuItem exit = new MenuItem("Exit");

		menuFile.getItems().addAll(load,saveTo,exit);

		menuBar.getMenus().addAll(menuFile);


		lostAnimal = new Button ("Lost Animal");
		lostAnimal.setOnAction(e -> control.newLostAnimal("Lost Animal Details",0));
		foundAnimal = new Button ("Found Animal");
		foundAnimal.setOnAction(e -> control.newLostAnimal("Found Animal Details",1));

		adoptedAnimal = new Button ("Animal Adoption");
		adoptedAnimal.setOnAction(e -> control.newAdoptedAnimal());
		details = new TextArea("This is where info will be shown");

		removeAnimal = new Button ("Remove Animal");
		removeAnimal.setOnAction(e -> control.removeAnimal());
		listAllAnimals = new Button ("Display all the animals");
		listAllAnimals.setOnAction(e ->details.setText(shelterAnimals.toString()));
		quit = new Button ("Quit");
		quit.setOnAction(e->System.exit(0));
		saveTo.setOnAction(e-> control.save());
		exit.setOnAction(e->System.exit(0));


		VBox v1 = new VBox();
		v1.getChildren().addAll(menuBar);


		HBox r1 = new HBox(20);
		r1.getChildren().addAll(lostAnimal,foundAnimal,adoptedAnimal,removeAnimal,listAllAnimals,quit);

		VBox layout = new VBox(30);
		layout.setPadding(new Insets(0, 0, 0, 10));
		layout.getChildren().addAll(v1,r1);

		HBox r2 = new HBox();
		r2.getChildren().addAll(details);
		layout.getChildren().addAll(r2);

		Scene scene = new Scene(layout, 950, 650);
		window.setScene(scene);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.show();}


	public static void main(String[] args) {
		people = new PersonList();
		
		Person p1 =new Person ("Monica","Wicklow","monika@cit.ie",11);
		Person p2 =new Person ("Joe","Arklow","joe@cit.ie",12);
		Person p3 =new Person ("Michelle","Cork","michelle@cit.ie",13);

		people.addPerson(p1);
		people.addPerson(p2);
		people.addPerson(p3);
		
		launch(args);
	}
}
