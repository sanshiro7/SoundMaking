/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.subro.soundmaking.db.test;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.subro.soundmaking.db.Loginuser;
import org.subro.soundmaking.db.LoginuserService;
import org.subro.soundmaking.user.OriginalEncriptPassword;

/**
 *
 * @author subro
 */
public class LoginUserServiceTest {
    
@Test
	void testGetLoginuserById() {
		LoginuserService loginuserservice = new LoginuserService();
		
		Loginuser loginuser = loginuserservice.getLoginuserById(1);
		
		OriginalEncriptPassword oep = new OriginalEncriptPassword();
		
		assertEquals(loginuser.getId(), 1);
		assertEquals(loginuser.getUsername(), "subro");
		assertEquals(oep.getPassword(loginuser.getPassword()), "password");
		assertEquals(loginuser.getGroupname(), "admin");
		
	}
	
	@Test
	void testGetLoginuserAll() {
		LoginuserService loginuserservice = new LoginuserService();
		
		List<Loginuser> loginusers = loginuserservice.getLoginuserAll();
		
		assertEquals(loginusers.size(), 2);
		
		for(Loginuser loginuser : loginusers) {
			assertEquals(loginuser instanceof Loginuser, true);
		}
		
	}
	
	@Test
	void testGetLoginuserByUsername() {
		LoginuserService loginuserservice = new LoginuserService();
		
		Loginuser loginuser = loginuserservice.getLoginuserByUsername("subro");
		
		OriginalEncriptPassword oep = new OriginalEncriptPassword();
		
		assertEquals(loginuser.getId(), 1);
		assertEquals(loginuser.getUsername(), "subro");
		assertEquals(oep.getPassword(loginuser.getPassword()), "password");
		assertEquals(loginuser.getGroupname(), "admin");
	}

	
	@Test
	void testCheckLoginuserByUsernameTrue() {
		LoginuserService loginuserservice = new LoginuserService();
		
		boolean result = loginuserservice.checkLoginuserByUsername("subro");
		
		assertEquals(result, true);

	}
	
	@Test
	void testCheckLoginuserByUsernameFalse() {
		LoginuserService loginuserservice = new LoginuserService();
		
		boolean result = loginuserservice.checkLoginuserByUsername("fukushim");
		
		assertEquals(result, false);
	}
	
	@Test
	void test_Create_Update_Delete_Loginuser() {
		LoginuserService loginuserservice = new LoginuserService();
		loginuserservice.createLoginuser("12345678901234567890", "23456789012345678901", "user5");
	
		Loginuser loginuser1 = loginuserservice.getLoginuserByUsername("12345678901234567890");
		
		assertEquals("12345678901234567890", loginuser1.getUsername());
		assertEquals("23456789012345678901", loginuser1.getPassword());
		assertEquals("user5", loginuser1.getGroupname());
	
		loginuser1.setUsername("abcdefghijklmnopqrst");
		loginuser1.setPassword("bcdefghijklmnopqrstu");
		loginuser1.setGroupname("admin");
		loginuserservice.updateLoginuser(loginuser1);
	
		Loginuser loginuser2 = loginuserservice.getLoginuserByUsername("abcdefghijklmnopqrst");
		
		loginuserservice.deleteLoginuser(loginuser2.getId());
		assertEquals(false, loginuserservice.checkLoginuserByUsername("abcdefghijklmnopqrst"));
	}
	
	@Test
	void testCreateLoginuser_Fail() {
		LoginuserService loginuserservice = new LoginuserService();
		
		loginuserservice.createLoginuser("123456789012345678901", "23456789012345678901", "user5");
		Loginuser loginuser1 = loginuserservice.getLoginuserByUsername("123456789012345678901");
		assertNull(loginuser1);
		
		loginuserservice.createLoginuser("12345678901234567890", "234567890123456789012", "user5");
		Loginuser loginuser2 = loginuserservice.getLoginuserByUsername("12345678901234567890");
		assertNull(loginuser2);
		
		loginuserservice.createLoginuser("12345678901234567890", "23456789012345678901", "user56");
		Loginuser loginuser3 = loginuserservice.getLoginuserByUsername("12345678901234567890");
		assertNull(loginuser3);
		
	}
}
