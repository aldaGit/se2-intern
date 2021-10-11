package org.hbrs.se2.registration;

public class UserBuilder {
	
	public static UserBuilder build = null;
	
	private UserDTO userDTO = null;
	
	public UserBuilder() {
	}
	
	public UserBuilder createNewUser() {
		userDTO = new UserDTO();
		return build;
	}
	
	public static UserBuilder please() {
		build =  new UserBuilder();
		return build;
	}
	
	public UserBuilder withNoPassword() {
		this.userDTO.setPassword("");
		return build;
	}
	
	public UserBuilder withNoAddress() {
		this.userDTO.setAddress("");
		return build;
	}
	
	public UserDTO done() {
		return this.userDTO;
	}
	
	public UserDTO thatsall() {
		return this.userDTO;
	}
	
	public UserBuilder withUserName( String name) {
		this.userDTO.setName(name);;
		return build;
	}
	
	public UserBuilder withUserID( String id ) {
		this.userDTO.setUserID(id);
		return build;
	}
	
	public UserBuilder withPassword( String password ) {
		this.userDTO.setPassword(password);
		return build;
	}
	
	public UserBuilder withAddress( String address ) {
		this.userDTO.setAddress( address );
		return build;
	}
	
	public UserBuilder withGebDatum( String gebDatum ) {
		this.userDTO.setGebDatum(gebDatum);
		return build;
	}
	
	public UserBuilder and() {
		return build;
	}
	
	public UserBuilder but() {
		return build;
	}
	
	/**
	 * Anwendung des Prototype Pattern. Ein regulaerer Benutzer wird zurueck geliefert.
	 * @return
	 */
	public UserBuilder createDefaultUser() {
		userDTO = new UserDTO();
		this.userDTO.setGebDatum("21.8.1979");
		this.userDTO.setName("Alexander Mayer");
		this.userDTO.setPassword("abc99");
		this.userDTO.setUserID("alex");
		this.userDTO.setAddress("Heimweg 22, 53111 Bonn");
		return build;
	}

}
