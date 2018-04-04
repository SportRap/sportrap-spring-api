package br.com.sportrap.api.utils;

import org.jasypt.util.text.StrongTextEncryptor;

public class Criptografia {

	private static String chave = "SPORTRAP-APP-KEY";
	private static StrongTextEncryptor encriptador = new StrongTextEncryptor();

	public static String encriptar(String palavra) {
		encriptador.setPassword(chave);
		return encriptador.encrypt(palavra);
	}
	
	public static String encriptar(long palavra) {		
		encriptador.setPassword(chave);
		return encriptador.encrypt(Long.toString(palavra));
	}
	
	public static String decriptar(String palavra) {
		encriptador.setPassword(chave);
		return encriptador.decrypt(palavra);
	}

}
