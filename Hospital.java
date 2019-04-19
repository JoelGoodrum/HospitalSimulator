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

public class Hospital {

	public static void main(String[] args){
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

	}
}