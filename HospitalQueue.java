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

	//peek  //see end with highest priority
	public PandP peek(){
		if(queue.isEmpty() == true){
			throw new NoSuchElementException();
		}
		else{
			int highestPriority = -1;
			int indexOfPriority = -1;
			for(int i = queue.size() - 1; i > -1; i--){
				if(queue.get(i).priority >= highestPriority){
					highestPriority = queue.get(i).priority;
					indexOfPriority = i;
				}
			}
			return queue.get(indexOfPriority);
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
			Patient temp = peek().patient;
			queue.remove(peek());
			return temp;
		}
	}

}