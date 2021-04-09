package com.stiven.pruebas;

import com.stiven.controller.impl.*;
import com.stiven.model.Producto;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ProductoImplTest {
	
	@Test
	public void adicionarTest()
	{
		Producto producto = new Producto();
		producto.setNombre("Anything");
		producto.setSerie("utf-9");
		producto.setValor(18599);
		producto.setDescripcion("Prueba");
		producto.setDescuento(20);
		producto.setUrl("http://www.promacohn.com/wp-content/uploads/2018/04/DSC00150.jpg");
		producto.setProveedor("Fercet");
		producto.setCantidad(80);
		ProductoImplA adicionar = new ProductoImplA();
		String resultado = adicionar.adicionar(producto, "duvanchulupo@gmail.com");
		String esperado= "1";
		
		assertEquals(esperado, resultado);
	}

	@Test
	public void modificarTest()
	{
		Producto producto = new Producto();
		producto.setNombre("Omardfdssdfwefvvesito");
		producto.setSerie("utf-9");
		producto.setValor(18599);
		producto.setDescripcion("Es un chiquillo");
		producto.setDescuento(2);
		producto.setUrl("https://www.loencuentras.com.co/1142-large_default/alicate-de-corte-frio.jpg");
		producto.setProveedor("Fercet");
		producto.setCantidad(80);
		ProductoImplA modificar = new ProductoImplA();
		String resultado = modificar.modificar(producto, 9);
		String esperado= "1";
		
		assertEquals(esperado, resultado);
	}
	
	@Test
	public void eliminarTest() 
	{
		ProductoImplA producto = new ProductoImplA();
		String resultado = producto.eliminar(21);
		String esperado= "1";
		
		assertEquals(esperado, resultado);
	}
	
}
