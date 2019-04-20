//parient class
class Patient {

	//patients variable
	private String name;
	private String age;
	private String sex;
	private String condition;
	private int priority;
	
	public Patient(){
		this.name = null;
		this.age = null;
		this.sex = null;
		this.condition = null;

	}

	//patient constructor
	public Patient(String name, String age, String sex, String condition){
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.condition = condition;
	}

	//view patient status
	public String toString(){
		return "name: " + name + "\n" +
			   "age: " + age + "\n" +
			   "sex: " + sex + "\n" +
			   "cond: " + condition + "\n" +
			   "priority: " + priority;
	}

	//change name
	public void name(String name){
		this.name = name;
	}

	//change age
	public void age(String age){
		this.age = age;
	}

	//change sex
	public void sex(String sex){
		this.sex = sex;
	}

	//change condition
	public void condition(String condition){
		this.condition = condition;
	}

	//change priority
	public void priority(int priority){
		this.priority = priority;
	}

	public int getPriority(){
		return priority;
	}


}