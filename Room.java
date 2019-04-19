import java.util.*;
//room class

class Room<Patient> {

	//variables
	int roomNumb;
	Patient patient;
	boolean vacant;

	//constructor
	public Room(int roomNumb){
		this.roomNumb = roomNumb;
		patient = null;
		vacant = true;
	}

	//methods

	//isVacant
	public boolean isVacant(){
		return vacant;
	}

	//add patiant
	public void addPatient(Patient patient){
		this.patient = patient;
		vacant = false;
	}

	//room status
	public String toString(){
		if(isVacant() == true){
			return "Room:#"+roomNumb +"\n"+ "vacant";
		}
		else{
			return "Room:#"+roomNumb +"\n"+ patient.toString();	
		}
	}


}