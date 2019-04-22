/**
	Hospital Priority Queue Simulation
	Joel Goodrum
	CSC 2720
	4/22/2019

	Objective of the program is to take data from patients 
	and assign them to a queue with a priority.
	User will have three operation rooms.
	User can assignt vacant operation room with a patient at 
	the peek of the priority queue.

	This class is responsible for 
	 launching the gui
	 taking data from patients
	 giving action listener to submit, fill and clear buttons
*/

import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.stage.Stage;  
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text; 
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.*;

public class HospitalMain extends Application {

	public void start(Stage primaryStage) throws Exception{

		//hospital queue, where patiant info is stored
		HospitalQueue queue = new HospitalQueue();

		//room objects
		Room room1 = new Room(1);
		Room room2 = new Room(2);
		Room room3 = new Room(3);

		//GUI//
		primaryStage.setTitle("HospitalSimulation");

		//patient input
		Text name = new Text("name: ");
		TextField nameInput = new TextField();

		Text age = new Text("age: ");
		TextField ageInput = new TextField();
	
		Text sex = new Text("sex: ");
		RadioButton male = new RadioButton("male");
		RadioButton female = new RadioButton("female");

		//toggle group allows only one radio button to be pressed in a group
		ToggleGroup sexInput = new ToggleGroup();
		male.setToggleGroup(sexInput);
		female.setToggleGroup(sexInput);

		Text condition = new Text("condition: ");
		TextField conditionInput = new TextField();


		ToggleGroup priorityInput = new ToggleGroup();
		Text priorityText = new Text("priority: ");

		RadioButton one = new RadioButton("1");
		RadioButton two = new RadioButton("2");
		RadioButton three = new RadioButton("3");
		RadioButton four = new RadioButton("4");
		RadioButton five = new RadioButton("5");
		RadioButton six = new RadioButton("6");
		RadioButton seven = new RadioButton("7");
		RadioButton eight = new RadioButton("8");
		RadioButton nine = new RadioButton("9");
		RadioButton ten = new RadioButton("10");

		one.setToggleGroup(priorityInput);
		two.setToggleGroup(priorityInput);
		three.setToggleGroup(priorityInput);
		four.setToggleGroup(priorityInput);
		five.setToggleGroup(priorityInput);
		six.setToggleGroup(priorityInput);
		seven.setToggleGroup(priorityInput);
		eight.setToggleGroup(priorityInput);
		nine.setToggleGroup(priorityInput);
		ten.setToggleGroup(priorityInput);

		HBox priorityBtnBox = new HBox(5,one,two,three,four,five,six,seven,eight,nine,ten);
		VBox patientInput = new VBox(5,name,nameInput,age,ageInput,sex,male,female,condition,conditionInput,priorityText,priorityBtnBox);
		//end patient input

		Button submit = new Button("Submit");
		submit.getStyleClass().add("submit-button");
		submit.getStylesheets().add("Hospital.css");

		//room1
		Text room1Text = new Text(room1.toString());
		room1Text.setFill(Color.DARKGREEN);

		Button clearRoom1 = new Button("clear");
		Button fillRoom1 = new Button("fill");

		//room2
		Text room2Text = new Text(room2.toString());
		room2Text.setFill(Color.DARKGREEN);
		Button clearRoom2 = new Button("clear");
		Button fillRoom2 = new Button("fill");

		//room3
		Text room3Text = new Text(room3.toString());
		room3Text.setFill(Color.DARKGREEN);
		Button clearRoom3 = new Button("clear");
		Button fillRoom3 = new Button("fill");
		
		//tells user how may people waiting in queue
		Text waitingInQueue = new Text("waiting in queue: " + queue.size());

		//layout
		VBox room1Vbox = new VBox(5, fillRoom1, clearRoom1, room1Text);
		VBox room2Vbox = new VBox(5, fillRoom2, clearRoom2, room2Text);
		VBox room3Vbox = new VBox(5, fillRoom3, clearRoom3, room3Text);
		HBox roomHbox = new HBox(10, room1Vbox,room2Vbox,room3Vbox);

		//stylize room layout
		roomHbox.getStylesheets().add("Hospital.css");
		roomHbox.getStyleClass().add("hbox");
		
		//layout all objects of software
		VBox layout = new VBox(8, patientInput, submit, roomHbox, waitingInQueue);
		layout.setPadding(new Insets(5,5,5,5));
		layout.getStyleClass().add("layout");
		layout.getStylesheets().add("Hospital.css");
		//end layout

		//scene object
		Scene scene = new Scene(layout,350,500);
		//END GUI//
		

		//buttons

		//submit button
		submit.setOnAction(actionEvent -> {

			//get all info
			Patient temp = new Patient();

			//name/age
            temp.name(nameInput.getText());
            temp.age(ageInput.getText());

            //sex
        	if(male.isSelected()){temp.sex("male");}
			if(female.isSelected()){temp.sex("female");}

			//condition
			temp.condition(conditionInput.getText());

			//priority
			if(one.isSelected()){temp.priority(1);}
			if(two.isSelected()){temp.priority(2);}
			if(three.isSelected()){temp.priority(3);}
			if(four.isSelected()){temp.priority(4);}
			if(five.isSelected()){temp.priority(5);}
			if(six.isSelected()){temp.priority(6);}
			if(seven.isSelected()){temp.priority(7);}
			if(eight.isSelected()){temp.priority(8);}
			if(nine.isSelected()){temp.priority(9);}
			if(ten.isSelected()){temp.priority(10);}
			//end pation info

			//add patient
			queue.enQueue(temp, temp.getPriority());
			updateInfo(room1Text, room2Text, room3Text, room1, room2, room3, waitingInQueue, queue);
			
			//clear info
			nameInput.clear();
			ageInput.clear();

			//clear radio buttons
			male.setSelected(false);
			female.setSelected(false);
			one.setSelected(false);
			two.setSelected(false);
			three.setSelected(false);
			four.setSelected(false);
			five.setSelected(false);
			six.setSelected(false);
			seven.setSelected(false);
			eight.setSelected(false);
			nine.setSelected(false);
			ten.setSelected(false);

			//clear condition
			conditionInput.clear();
		});

		//clear room btn
		clearRoom1.setOnAction(actionEvent -> {
	        room1.makeVacant(); 
	        updateInfo(room1Text, room2Text, room3Text, room1, room2, room3, waitingInQueue, queue);
	        room1Text.setFill(Color.DARKGREEN);
		});

		//clear room btn
		clearRoom2.setOnAction(actionEvent -> {
	        room2.makeVacant(); 
	        updateInfo(room1Text, room2Text, room3Text, room1, room2, room3, waitingInQueue, queue);
	        room2Text.setFill(Color.DARKGREEN);
		});

		//clear room btn
		clearRoom3.setOnAction(actionEvent -> {
	        room3.makeVacant(); 
	        updateInfo(room1Text, room2Text, room3Text, room1, room2, room3, waitingInQueue, queue);
	        room3Text.setFill(Color.DARKGREEN);
		});

		//fill button
		fillRoom1.setOnAction(actionEvent -> {
	        fillRoom(queue, room1, room1Text); 
	        updateInfo(room1Text, room2Text, room3Text, room1, room2, room3, waitingInQueue, queue);
		});

		//fill button
		fillRoom2.setOnAction(actionEvent -> {
	        fillRoom(queue, room2, room2Text); 
	        updateInfo(room1Text, room2Text, room3Text, room1, room2, room3, waitingInQueue, queue);
		});

		//fill button
		fillRoom3.setOnAction(actionEvent -> {
	        fillRoom(queue, room3, room3Text); 
	        updateInfo(room1Text, room2Text, room3Text, room1, room2, room3, waitingInQueue, queue);
		});

		//show frame
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	//launch app
	public static void main(String[] args) {
        Application.launch(args);
    }
    
   
    //fill room
    public void fillRoom(HospitalQueue queue, Room room, Text roomText){
    	
    	if(room.isVacant()){
    		room.addPatient(queue.deQueue());
    		roomText.setText(room.toString());
    		roomText.setFill(Color.RED);	
    	}

    }

    //updateInfo
    public void updateInfo(Text room1Text, Text room2Text, Text room3Text, Room room1, Room room2, Room room3, Text waitingInQueue, HospitalQueue queue){
    	
    	room1Text.setText(room1.toString());
    	room2Text.setText(room2.toString());
    	room3Text.setText(room3.toString());
    	waitingInQueue.setText("waiting in queue: " + queue.size());

    }

}