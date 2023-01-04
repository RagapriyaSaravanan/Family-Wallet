package practice;


import java.util.HashMap;

// Class for Actor - Dad

public class Father{
	
	public String password = "Father";
	public HashMap<Integer,String> requests = new HashMap<Integer,String>();
	
	String getPassword () {
		
		return this.password;
	}
	
	void addRequest(Integer identity,String value) {
		
		this.requests.put(identity, value);		
	}
	
	
	
}

