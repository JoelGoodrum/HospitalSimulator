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

		RadioButton selectedSex = (RadioButton) sexInput.getSelectedToggle();
		
		
		


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


		HBox priorityBtnBox = new HBox(one,two,three,four,five,six,seven,eight,nine,ten);
		VBox patientInput = new VBox(name,nameInput,age,ageInput,sex,male,female,condition,conditionInput,priorityText,priorityBtnBox);
		//end patient input

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

		Text waitingInQueue = new Text("waiting in queue: " + queue.size());



		//layout
		HBox roomHbox = new HBox(room1Text,room2Text,room3Text);
		HBox clearBtns = new HBox(clearRoom1, clearRoom2, clearRoom3);
		HBox fillBtns = new HBox(fillRoom1, fillRoom2, fillRoom3);
		VBox layout = new VBox(patientInput,submit,roomHbox,clearBtns,fillBtns, waitingInQueue);
		Scene scene = new Scene(layout,300,400);
		//end layout

		//buttons

		//submit button
		submit.setOnAction(actionEvent -> {

			//get all info
			Patient temp = new Patient();
			//name/age//
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

			//

			//add patient
			queue.enQueue(temp, temp.getPriority());
	        checkInPatient(queue, roomHbox, layout, submit, room1, room2, room3, room1Text, room2Text, room3Text);
			updateInfo(room1Text, room2Text, room3Text, room1, room2, room3, waitingInQueue, queue);
			
		});

		//clear room btn
		clearRoom1.setOnAction(actionEvent -> {
	        room1.makeVacant(); 
	        updateInfo(room1Text, room2Text, room3Text, room1, room2, room3, waitingInQueue, queue);
		});

		//clear room btn
		clearRoom2.setOnAction(actionEvent -> {
	        room2.makeVacant(); 
	        updateInfo(room1Text, room2Text, room3Text, room1, room2, room3, waitingInQueue, queue);
		});

		//clear room btn
		clearRoom3.setOnAction(actionEvent -> {
	        room3.makeVacant(); 
	        updateInfo(room1Text, room2Text, room3Text, room1, room2, room3, waitingInQueue, queue);
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
	        fillRoom(queue, room3, room2Text); 
	        updateInfo(room1Text, room2Text, room3Text, room1, room2, room3, waitingInQueue, queue);
		});

		//show frame
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	public static void main(String[] args) {
        Application.launch(args);
    }

    

    //checkInPatient
    public void checkInPatient(HospitalQueue queue, HBox hbox, VBox vbox, Button submit, Room room1, Room room2, Room room3, Text room1Text, Text room2Text, Text room3Text){
  

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
    public void updateInfo(Text room1Text, Text room2Text, Text room3Text, Room room1, Room room2, Room room3, Text waitingInQueue, HospitalQueue queue){
    	
    	room1Text.setText(room1.toString());
    	room2Text.setText(room2.toString());
    	room3Text.setText(room3.toString());
    	waitingInQueue.setText("waiting in queue: " + queue.size());
    }

}