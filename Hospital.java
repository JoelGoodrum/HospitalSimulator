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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Hospital extends Application {

	public void start(Stage primaryStage) throws Exception{
		
		Patient a = new Patient("jonh",15,"male","bleeding",9);
		Patient b = new Patient("mary",16,"female","broken arm",5);
		Patient c = new Patient("bob",25,"male","fatigued",1);

		HospitalQueue queue = new HospitalQueue();
		queue.enQueue(a,10);
		queue.enQueue(b,2);
		queue.enQueue(c,8);
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());

		//GUI//
		primaryStage.setTitle("HospitalSimulation");
		Label label = new Label("Hello World, JavaFX !");
		Scene scene = new Scene(label,400,200);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	public static void main(String[] args) {
        Application.launch(args);
    }
}