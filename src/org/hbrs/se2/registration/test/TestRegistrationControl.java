package org.hbrs.se2.registration.test;

import org.hbrs.se2.registration.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestRegistrationControl {
	
	private RegistrationControl regc = null;
	
	@BeforeEach
	public void setUp() throws Exception {
		regc = new RegistrationControl();
	}

	@Test
	public void testEasy() {
		RegistrationResult result = regc.registerUser(new UserDTO());
		assertNotNull(result);

	}
	
	@Test
	public void testWithFactory() {
		// Sehr lange Methoden
		UserDTO dto = UserFactory.createDefaultUserWithNoPassword();
		RegistrationResult result = regc.registerUser( dto );
		
		assertFalse( result.getResult()  );
		assertEquals(  RegistrationResult.PASSWORD_MISSING, result.getReason() );

		// Diskussion Methoden:
        // createDefaultUser -  WithNoPassword - And - NoAddress- And - WithNOEmail

	}

	@Test
	public void testDiverseUsers() {
		// Test Builder Pattern:
		UserDTO dto = UserBuilder.please().createDefaultUser().withNoAddress().done();
		dto = UserBuilder.please().createDefaultUser().withNoPassword().and().withUserID("abc").thatsall();
		dto = UserBuilder.please().createDefaultUser().withNoPassword().but().withGebDatum("1.1.2000").done();
		dto = UserBuilder.please().createDefaultUser().withUserID("2").and().withGebDatum("dndn").but().withNoPassword().thatsall();

		RegistrationResult result = regc.registerUser( dto );

	}

	
	@Test
	public void testNoPassword() {
		
		// Prototype-User erzeugen 
		UserDTO dto = UserBuilder.please().createDefaultUser().thatsall();
		RegistrationResult result = this.regc.registerUser(dto);
		assertTrue( result.getResult() );
		assertEquals( result.getReason() , RegistrationResult.REGISTRATION_SUCCESSFULL );
		System.out.println( "1:" + dto );
		
		// Password auslassen
		dto = UserBuilder.please().createDefaultUser().but().withNoPassword().done();
		result = regc.registerUser(dto);
		assertFalse( result.getResult()  );
		assertEquals(  result.getReason() , RegistrationResult.PASSWORD_MISSING );
		System.out.println( dto );
		
		// Zwei gleiche User erstellen und vergleichen
		dto = UserBuilder.please().createNewUser().withUserName("sascha").and().withPassword("abc").thatsall();
		UserDTO dto2 = UserBuilder.please().createNewUser().withPassword("abc").and().withUserName("sascha").thatsall();
		System.out.println( dto );
		assertEquals( dto , dto2 );
		
		// Optionales Feld auslassen
		dto2 = UserBuilder.please().createDefaultUser().but().withNoAddress().thatsall();
		result = this.regc.registerUser(dto2);
		assertTrue( result.getResult() );
		assertEquals( result.getReason() , RegistrationResult.REGISTRATION_SUCCESSFULL );
		
		// Weitere Kombinationen
		dto2 = UserBuilder.please().createDefaultUser().but().withNoAddress().and().withNoPassword().done();
		System.out.println( dto2 );
	}

}
