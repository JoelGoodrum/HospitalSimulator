/*
	//top page page//
	patiant comes in with
	name
	age
	sex
	what happened

	user assigns priority

	//bottom page
	red rooms mean empty
	green rooms mean available

	red rooms will have patiants info
	it takes time to clear a room
	//

	patients need
	String name, int age, String sex, String condition, int oddsOfDying
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


public class Hospital extends Application {

	public void start(Stage primaryStage) throws Exception{
		
		Patient a = new Patient("jonh",15,"male","bleeding",9);
		Patient b = new Patient("mary",16,"female","broken arm",5);
		Patient c = new Patient("bob",25,"male","fatigued",1);

		ArrayList<Patient> line = new ArrayList<Patient>();
		line.add(a);
		line.add(b);
		line.add(c);

		HospitalQueue queue = new HospitalQueue();

		//room objects
		Room room1 = new Room(1);
		Room room2 = new Room(2);
		Room room3 = new Room(1);
		

		//GUI//
		primaryStage.setTitle("HospitalSimulation");

		//patient input
		Text name = new Text("name: ");
		TextField nameInput = new TextField();

		Text age = new Text("age: ");
		TextField ageInput = new TextField();

		Text sex = new Text("sex: ");
		ToggleGroup sexInput = new ToggleGroup();
		RadioButton male = new RadioButton("male");
		male.setToggleGroup(sexInput);
		RadioButton female = new RadioButton("female");
		female.setToggleGroup(sexInput);
		


		Text condition = new Text("condition: ");
		TextField conditionInput = new TextField();

		ToggleGroup priorityInput = new ToggleGroup();
		Text priority = new Text("priority: ");
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

		HBox priorityBtnBox = new HBox(one,two,three,four,five,six,seven,eight,nine,ten);
		VBox patientInput = new VBox(name,nameInput,age,ageInput,sex,male,female,condition,conditionInput,priorityBtnBox);
		//end patient input

		TextField textField = new TextField();
		Button submit = new Button("Submit");
		//submit.setId("button");
		//submit.getStylesheets().add("CalcStyle.css");

		Text room1Text = new Text(room1.toString());
		room1Text.setFill(Color.GREEN);
		Button clearRoom1 = new Button("clear");
		Button fillRoom1 = new Button("fill");

		Text room2Text = new Text(room2.toString());
		room2Text.setFill(Color.GREEN);
		Button clearRoom2 = new Button("clear");
		Button fillRoom2 = new Button("fill");

		Text room3Text = new Text(room3.toString());
		room3Text.setFill(Color.GREEN);
		Button clearRoom3 = new Button("clear");
		Button fillRoom3 = new Button("fill");



		//layout
		HBox roomHbox = new HBox(room1Text,room2Text,room3Text);
		HBox clearBtns = new HBox(clearRoom1, clearRoom2, clearRoom3);
		HBox fillBtns = new HBox(fillRoom1, fillRoom2, fillRoom3);
		VBox layout = new VBox(patientInput,textField,submit,roomHbox,clearBtns,fillBtns);
		Scene scene = new Scene(layout,300,400);
		//end layout

		//buttons

		//submit button
		submit.setOnAction(actionEvent -> {
	        checkInPatient(line, queue, roomHbox, layout, textField, submit, room1, room2, room3, room1Text, room2Text, room3Text);
			updateInfo(room1Text, room2Text, room3Text, room1, room2, room3);
		});

		//clear room btn
		clearRoom1.setOnAction(actionEvent -> {
	        room1.makeVacant(); 
	        updateInfo(room1Text, room2Text, room3Text, room1, room2, room3);
		});

		//clear room btn
		clearRoom2.setOnAction(actionEvent -> {
	        room2.makeVacant(); 
	        	updateInfo(room1Text, room2Text, room3Text, room1, room2, room3);
		});

		//clear room btn
		clearRoom3.setOnAction(actionEvent -> {
	        room3.makeVacant(); 
	        	updateInfo(room1Text, room2Text, room3Text, room1, room2, room3);
		});

		//fill button
		fillRoom1.setOnAction(actionEvent -> {
	        fillRoom(queue, room1, room1Text); 
	        updateInfo(room1Text, room2Text, room3Text, room1, room2, room3);
		});

		//fill button
		fillRoom2.setOnAction(actionEvent -> {
	        fillRoom(queue, room2, room2Text); 
	        updateInfo(room1Text, room2Text, room3Text, room1, room2, room3);
		});

		//fill button
		fillRoom3.setOnAction(actionEvent -> {
	        fillRoom(queue, room3, room2Text); 
	        updateInfo(room1Text, room2Text, room3Text, room1, room2, room3);
		});

		//show frame
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	public static void main(String[] args) {
        Application.launch(args);
    }

    

    //checkInPatient
    public void checkInPatient(ArrayList<Patient> p, HospitalQueue queue, HBox hbox, VBox vbox, TextField textField, Button submit, Room room1, Room room2, Room room3, Text room1Text, Text room2Text, Text room3Text){
    	queue.enQueue(p.remove(0), 5);

    	if(room1.isVacant()){
    		room1.addPatient(queue.deQueue());
    		room1Text.setText(room1.toString());
    		
    	}

    	else if(room2.isVacant()){
    		room2.addPatient(queue.deQueue());
    		room2Text.setText(room2.toString());
    		
    	}

    	else if(room3.isVacant()){
    		room3.addPatient(queue.deQueue());
    		room3Text.setText(room3.toString());
    		
    	}
    	
    }

    //fill room
    public void fillRoom(HospitalQueue queue, Room room, Text roomText){
    	if(room.isVacant()){
    		room.addPatient(queue.deQueue());
    		roomText.setText(room.toString());
    		
    	}
    }

    

    //updateInfo
    public void updateInfo(Text room1Text, Text room2Text, Text room3Text, Room room1, Room room2, Room room3){
    	
    	room1Text.setText(room1.toString());
    	room2Text.setText(room2.toString());
    	room3Text.setText(room3.toString());
    }

}