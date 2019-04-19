import java.util.*;
//room class

class Room<Patient> {

	//variables
	int roomNumb;
	Patient patient;

	//constructor
	public Room(int roomNumb){
		this.roomNumb = roomNumb;
		this.patient = null;
	}

	//methods

	//isVacant
	public boolean isVacant(){
		return patient.equals(null);
	}

	public void addPatient(Patient patient){
		this.patient = patient;
	}


}