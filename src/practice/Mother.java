package practice;


import java.util.HashMap;

//Class for actor - Mom

public class Mother {
	
	public String password = "Mother";
	public boolean blockStatus = false;
	public HashMap<Integer,String> requests = new HashMap<Integer,String>();
	
	String getPassword () {
		
		return this.password;
	}
	
	public boolean isBlockStatus() {
		return blockStatus;
	}
	
	public void setBlockStatus(boolean blockStatus) {
		this.blockStatus = blockStatus;
	}
	
	void addRequest(Integer identity,String value) {
		
		this.requests.put(identity, value);		
	}
		
}
