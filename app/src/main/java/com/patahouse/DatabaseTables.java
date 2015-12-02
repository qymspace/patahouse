package com.patahouse;

public class DatabaseTables {

	public DatabaseTables() {
		// TODO Auto-generated constructor stub
	}
	static class buildings{
		int id;
		int ownerId;
		String buildingName;
		double locationLatitude;
		double locationLongitude;
	}
	static class owners{
		int id;
		String firstName;
		String lastName;
		int phoneNumber;
		
		
	}
	static class rooms{
		int id;
		int buildingId;
		boolean occupied;
		
		public void setRoom(int id, int buildingId, boolean occupied){
			this.id=id;
			this.buildingId=buildingId;
			this.occupied=occupied;
		}
	}

}
