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

		Text patientInfo = new Text(line.get(0).toString());
		patientInfo.setFill(Color.RED);

		TextField textField = new TextField();
		Button submit = new Button("Submit");
		//submit.setId("button");
		//submit.getStylesheets().add("CalcStyle.css");

		Text room1Text = new Text(room1.toString());
		room1Text.setFill(Color.GREEN);

		Text room2Text = new Text(room2.toString());
		room2Text.setFill(Color.GREEN);

		Text room3Text = new Text(room3.toString());
		room3Text.setFill(Color.GREEN);



		//layout
		HBox roomHbox = new HBox(room1Text,room2Text,room3Text);
		VBox layout = new VBox(patientInfo,textField,submit,roomHbox);
		Scene scene = new Scene(layout,300,400);
		//end layout

		//buttons
		submit.setOnAction(actionEvent -> {
	        checkInPatient(line, queue, roomHbox, layout, textField, submit, room1, room2, room3, room1Text, room2Text, room3Text, patientInfo);
			updateInfo(line, queue, roomHbox, layout, textField, submit, room1Text, room2Text, room3Text, patientInfo);
		});

		//show frame
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	public static void main(String[] args) {
        Application.launch(args);
    }

    

    //checkInPatient
    public void checkInPatient(ArrayList<Patient> p, HospitalQueue queue, HBox hbox, VBox vbox, TextField textField, Button submit, Room room1, Room room2, Room room3, Text room1Text, Text room2Text, Text room3Text, Text patientInfo){
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

    //updateInfo
    public void updateInfo(ArrayList<Patient> p,HospitalQueue queue, HBox hbox, VBox vbox, TextField textField, Button submit, Text room1Text, Text room2Text, Text room3Text, Text patientInfo){
    	
    	hbox.getChildren().clear();
    	hbox.getChildren().addAll(room1Text, room2Text, room3Text);

    	
    	if(p.size() < 1){
    		patientInfo.setText("line is empty.");
    	} else {
    		patientInfo.setText(p.get(0).toString());
    	}
    	vbox.getChildren().clear();
    	vbox.getChildren().addAll(patientInfo,textField,submit,hbox);
    }
}