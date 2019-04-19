import java.util.*;
//hospital queue
class HospitalQueue<Patient> {

	//variables
	ArrayList<PandP> queue = new ArrayList<PandP>();

	//hospital node
	class PandP {

		//patientAndPriority variables
		Patient patient;
		int priority;

		//constructor  PAndP (Patient and Priority)
		public PandP(Patient patient, int priority){
			this.patient = patient;
			this.priority = priority;
		}
	}

	//hospital queue methods

	//isEmpty
	public boolean isEmpty(){
		return queue.size() == 0;
	}

	//peek  //see end
	public Patient peek(){
		if(queue.isEmpty() == true){
			throw new NoSuchElementException();
		}
		else{
			return queue.get(queue.size() - 1).patient;
		}
	}

	//enQueue  //add at the end
	public void enQueue(Patient patient, int priority){
		PandP registeredPatient = new PandP(patient, priority);
		queue.add(registeredPatient);
	}

	//deQueue  //remove begenning
	public Patient deQueue(){
		if(queue.isEmpty() == true){
			throw new NoSuchElementException();
		}
		else{
			return queue.remove(0).patient;
		}
	}

}