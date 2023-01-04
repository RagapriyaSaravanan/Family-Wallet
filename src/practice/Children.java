package practice;

// Class for actor - Child

public class Children {
	
	public double limitPerDay = 50.00;
	public boolean blockStatus = false;
	public int numberOfUses = 1;
	public boolean flagRequest = false; 
	// Only one request can be entered at a time. The child has to wait until Mom / Dad has 
	//decided on their request to place another request since hashmap is used.
	
	
	public double getLimitPerDay() {
		return limitPerDay;
	}

	public boolean isBlockStatus() {
		return blockStatus;
	}

	public int getNumberOfUses() {
		return numberOfUses;
	}
	public boolean isFlagRequest() {
		return flagRequest;
	}

	public void setLimitPerDay(double limitPerDay) {
		this.limitPerDay = limitPerDay;
	}

	public void setBlockStatus(boolean blockStatus) {
		this.blockStatus = blockStatus;
	}

	public void setNumberOfUses(int numberOfUses) {
		this.numberOfUses = numberOfUses;
	}
	
	public void setFlagRequest(boolean flagRequest) {
		this.flagRequest = flagRequest;
	}

	
}
