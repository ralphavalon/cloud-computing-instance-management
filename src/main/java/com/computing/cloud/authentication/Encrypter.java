package com.computing.cloud.authentication;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

import com.computing.cloud.exception.CipherException;
import com.computing.cloud.exception.ForbiddenException;
import com.computing.cloud.exception.InternalServerException;
import com.computing.cloud.exception.InvalidTokenException;
import com.computing.cloud.exception.NotFoundException;
import com.computing.cloud.utils.DateHelper;

public class Encrypter {

	private static final String PASSWORD_SALT = "[&AI0I3J&+}";

	private static String cryptKey = "CB21BFF0096D08CA20FBC1EF0B78AC03";
	private static String vector = "3DEC7C9ECC1ADA1EE9AA7AFB2F9CDE80";
	
	private static final String SEPARATOR = "#";

	private static Cipher decrypter;
	private static Cipher crypter;

	public static String encryptPassword( String password ) {
		password = PASSWORD_SALT + password;
		MessageDigest messageDigest = DigestUtils.getSha256Digest();
		byte[] digest = messageDigest.digest( password.getBytes() );
		return Hex.encodeHexString( digest );
	}

	public static String encryptToken( String id ) {
		DateTime expirationDate = new DateTime().plusDays(20);
		String stringExpirationDate = DateHelper.format(expirationDate).get();
		String value = id + SEPARATOR + stringExpirationDate;

		try {
			if (id == null) {
				throw new IllegalArgumentException("Dados inválidos para gerar o token.");
			}

			byte[] encryptedByteArray = getCrypter().doFinal( value.getBytes() );

			return changeToUrlSafeEncode( new String( Base64.encodeBase64(encryptedByteArray) ) );
		} catch (Exception e) {
			throw new InternalServerException("Nao foi possivel criptografar o id [" + id + "]", e);
		}
	}

	public static String decryptToken( String hash ) {
		try {
			if (hash == null) {
				throw new IllegalArgumentException("Dados inválidos para descriptografar o token.");
			}

			byte[] decodedHashBytes = Base64.decodeBase64( changeFromUrlSafeEncode(hash).getBytes() );
			byte[] decryptedByteArray = getDecrypter().doFinal( decodedHashBytes );

			String[] token = new String(decryptedByteArray).split(SEPARATOR);
			
			DateTime expirationDate = DateHelper.toDate(token[1]).get();
			
			if( expirationDate.isBeforeNow() ) {
				throw new InvalidTokenException("O Token ["+hash+"] foi expirado em ["+token[1]+"] e não será possível executar a autenticação.");
			}
			
			return token[0];
		} catch( InvalidTokenException e ) {
			throw new ForbiddenException();
		} catch (Exception e) {
			throw new NotFoundException("Hash inválido, não foi possível efetuar a descriptografia ["+hash+"]", e);
		}
	}
	
	private static String changeToUrlSafeEncode(String urlSafeToken) {
		urlSafeToken = urlSafeToken.replaceAll("\\+", "-");
		urlSafeToken = urlSafeToken.replaceAll("\\/", "_");

		return urlSafeToken;
	}

	private static Cipher getCrypter() throws CipherException {
		if ( crypter == null ) {
			crypter = getCipher(Cipher.ENCRYPT_MODE);
		}

		return crypter;
	}

	private static Cipher getDecrypter() throws CipherException {
		if ( decrypter == null ) {
			decrypter = getCipher(Cipher.DECRYPT_MODE);
		}

		return decrypter;
	}

	private static Cipher getCipher(int mode) throws CipherException {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(mode , new SecretKeySpec(hexStringToByteArray(cryptKey), "AES"), new IvParameterSpec(hexStringToByteArray(vector)));
			return cipher;
		} catch (Exception e) {
			throw new CipherException("Nao foi possivel obter instancia do Cipher", e);
		}
	}

	private static byte[] hexStringToByteArray(String s) {
		int len = s.length();

		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}

		return data;
	}

	private static String changeFromUrlSafeEncode(String urlUnsafeToken) {
		urlUnsafeToken = urlUnsafeToken.replaceAll("-", "+");
		urlUnsafeToken = urlUnsafeToken.replaceAll("_", "/");

		return urlUnsafeToken;
	}
	
}
