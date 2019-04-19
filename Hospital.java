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


public class Hospital extends Application {

	public void start(Stage primaryStage) throws Exception{
		
		Patient a = new Patient("jonh",15,"male","bleeding",9);
		Patient b = new Patient("mary",16,"female","broken arm",5);
		Patient c = new Patient("bob",25,"male","fatigued",1);

		HospitalQueue queue = new HospitalQueue();
		queue.enQueue(a,10);
		queue.enQueue(b,2);
		queue.enQueue(c,8);
		

		//GUI//
		primaryStage.setTitle("HospitalSimulation");

		Text patientInfo = new Text(a.toString());
		patientInfo.setFill(Color.RED);

		TextField textField = new TextField();
		Button submit = new Button("Submit");

		Text room1 = new Text("room1");
		room1.setFill(Color.GREEN);

		Text room2 = new Text("room2");
		room2.setFill(Color.GREEN);

		Text room3 = new Text("room3");
		room3.setFill(Color.GREEN);

		//layout
		VBox layout = new VBox(patientInfo,textField,submit,room1,room2,room3);
		Scene scene = new Scene(layout,300,400);
		//end layout


		//show frame
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	public static void main(String[] args) {
        Application.launch(args);
    }
}