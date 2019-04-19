//parient class
class Patient {

	//patients variable
	private String name;
	private int age;
	private String sex;
	private String condition;
	private int oddsOfDying; //this is an unknown variable

	//patient constructor
	public Patient(String name, int age, String sex, String condition, int oddsOfDying){
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.condition = condition;
	}

	//view patient status
	public String viewStatus(){
		return "name: " + name + "\n" +
			   "age: " + age + "\n" +
			   "sex: " + sex + "\n" +
			   "condition: " + condition;	   
	}

	public String toString(){
		return "name: " + name;
	}

}