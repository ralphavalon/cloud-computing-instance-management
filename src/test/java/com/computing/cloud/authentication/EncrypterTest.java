package com.computing.cloud.authentication;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.computing.cloud.exception.ForbiddenException;
import com.computing.cloud.exception.InternalServerException;
import com.computing.cloud.exception.NotFoundException;
import com.computing.cloud.utils.DateHelper;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DateHelper.class})
@PowerMockIgnore({"javax.crypto.*" })
public class EncrypterTest {

	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Test
	public void encrypter_password() {
		assertEquals( "ff8b98bae95b79500f0485c9654e8ac6e3a7bae8535381ad475ee1a2d5a20c10", Encrypter.encryptPassword("123456") );
	}
	
	@Test
	public void encrypter_id() {
		PowerMockito.stub(PowerMockito.method(DateHelper.class, "format", DateTime.class)).toReturn( Optional.of("25/10/2014") );
		
		assertEquals( "gBYlXv5_vGXCJP06Iry89Q==", Encrypter.encryptToken("3") );
		
		PowerMockito.stub(PowerMockito.method(DateHelper.class, "format", DateTime.class)).toReturn( Optional.of("25/10/2019") );
		
		assertEquals( "HhCj62eJ7idFAaNDSJrboA==", Encrypter.encryptToken("10") );
	}
	
	@Test
	public void decrypter_token() {
		assertEquals( "3", Encrypter.decryptToken("l8gZ7XCKkJ1d9HFXEqf0RA==") );
	}
	
	@Test
	public void encrypt_blank_id() {
		expected.expect(InternalServerException.class);
		Encrypter.encryptToken(null);
	}
	
	@Test
	public void decrypt_blank_id() {
		expected.expect(NotFoundException.class);
		Encrypter.decryptToken(null);
	}
	
	@Test(expected=ForbiddenException.class)
	public void decrypt_expired_token() {
		expected.expect(ForbiddenException.class);
		String token25102014 = "gBYlXv5_vGXCJP06Iry89Q==";
		
		Encrypter.decryptToken(token25102014);
	}
	
}
