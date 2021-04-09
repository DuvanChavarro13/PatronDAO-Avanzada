package com.stiven.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Seguridad
{
	private String passLetra;
	
	public Seguridad(String texto)
	{
		String textoEncriptadoConMD5=DigestUtils.md5Hex(texto);
		this.passLetra = textoEncriptadoConMD5;
	}
	public String getEncriptada()
	{
		return this.passLetra;
	}
	/*
	public static void main(String[] args)
	{
		String pass = "duvan123";
		Seguridad obj = new Seguridad(pass);
		
		System.out.println(obj.getEncriptada());;
	}
	*/
}