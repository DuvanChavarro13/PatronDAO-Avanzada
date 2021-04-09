package com.stiven.controller.impl;
import javax.ws.rs.Produces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.stiven.model.Producto;
import com.stiven.util.DBConexion;

public class ProductoImplA{
	
		public String adicionar(Producto producto, String correo)
		{
			DBConexion conexion = DBConexion.getConexion();
			
			PreparedStatement st = null, st2 = null;
			ResultSet rs = null, rs2 = null;
			String sql = "", sql2 = "";
			
			int id = 0;
			
			String mensaje = "";
			int insertados = 0;
			
			try 
			{
				conexion.conectar().setAutoCommit(false);
				
				sql2 = "SELECT id FROM persona WHERE correo= ?";
				
				st2 = conexion.conectar().prepareStatement(sql2);
				st2.setString(1, correo);
				
				rs2 = st2.executeQuery();
				
				while (rs2.next())
				{
					id = rs2.getInt("id");
				}
				
				sql = "INSERT INTO producto (nombre, serie, valor, descripcion, descuento, proveedor, url, cantidad, id_persona)";
				sql = sql + "VALUES(?,?,?,?,?,?,?,?,?)";
		
				st = conexion.conectar().prepareStatement(sql);
				
				st.setString(1, producto.getNombre());
				st.setString(2, producto.getSerie());
				st.setDouble(3, producto.getValor());
				st.setString(4, producto.getDescripcion());
				st.setDouble(5, producto.getDescuento());
				st.setString(6, producto.getProveedor());
				st.setString(7, producto.getUrl());
				st.setInt(8, producto.getCantidad());
				st.setInt(9, id);
				
				insertados = st.executeUpdate();
			} 
			catch (Exception e) 
			{
				System.out.println("Error: " + e.toString());
				System.out.println(e.getMessage());
	            try {
	                conexion.conectar().setAutoCommit(false);
	                conexion.conectar().rollback();
	            } catch (SQLException ex1) {
	                System.out.println(ex1.getMessage());
	            }
			}

			if (insertados > 0) 
			{
				mensaje = "1";
			} 
			else 
			{
				mensaje = "0";
			}
			return mensaje;
		}
		
		public String modificar(Producto producto, int id)
		{
			
			DBConexion conexion = DBConexion.getConexion();
			
			PreparedStatement st = null;
			String sql = "";
			String mensaje = "";
			int modificados = 0;
			/*----------------------------------------------------------------------------*/
			try 
			{
				conexion.conectar().setAutoCommit(false);
				sql = "UPDATE producto SET nombre=?, serie=?, valor=?, descripcion=?, descuento=?, proveedor=?, url=?, cantidad=?";
				sql = sql + " WHERE id=?";
				
				st = conexion.conectar().prepareStatement(sql);

				st.setString(1, producto.getNombre());
				st.setString(2, producto.getSerie());
				st.setDouble(3, producto.getValor());
				st.setString(4, producto.getDescripcion());
				st.setDouble(5, producto.getDescuento());
				st.setString(6, producto.getProveedor());
				st.setString(7, producto.getUrl());
				st.setInt(8, producto.getCantidad());
				st.setInt(9, id);
				
				modificados = st.executeUpdate();
			}
			catch (Exception e) 
			{
				System.out.println("Error: " + e.toString());
			}
			
			if (modificados > 0) 
			{
				mensaje = "1";
				return mensaje;
			} 
			else 
			{
				mensaje = "0";
				return mensaje;
			}
		}
		
		public String eliminar(int id)
		{
			DBConexion conexion = DBConexion.getConexion();
			
			PreparedStatement st = null;
			String sql = "";
			String mensaje = "";
			int eliminados = 0;
			try 
			{
				conexion.conectar().setAutoCommit(false);
				sql = "DELETE FROM producto WHERE id=?";

				st = conexion.conectar().prepareStatement(sql);
				st.setInt(1, id);

				eliminados = st.executeUpdate();
			} 
			catch (Exception e) 
			{

				System.out.println("Error: " + e.toString());
			}
			if (eliminados > 0) 
			{
				mensaje = "1";
				return mensaje;
			} 
			else 
			{
				mensaje = "0";
				return mensaje;
			}
		}
		
		@Produces("application/json")
		public ProductoLista getProductos(String correo)
		{
			ArrayList lista = new ArrayList();
			
			DBConexion conexion = DBConexion.getConexion();
			
			
			PreparedStatement st = null, st2 = null;
			ResultSet rs = null, rs2 = null;
			String sql = "", sql2 = "";
			
			int id = 0;
			
			try 
			{
				conexion.conectar();
				
				sql2 = "SELECT id FROM persona WHERE correo= ?";
				
				st2 = conexion.conectar().prepareStatement(sql2);
				st2.setString(1, correo);
				
				rs2 = st2.executeQuery();
				
				while (rs2.next())
				{
					id = rs2.getInt("id");
				}
				//----------------------------------------------------
				
				sql = "SELECT * FROM producto WHERE id_persona = ?";
				
				st = conexion.conectar().prepareStatement(sql);
				
				st.setInt(1, id);
				
				rs = st.executeQuery();

				while (rs.next()) {
					
					Producto producto = new Producto();

					producto.setId(rs.getInt("id"));
					producto.setNombre(rs.getString("nombre"));
					producto.setSerie(rs.getString("serie"));
					producto.setValor(rs.getDouble("valor"));
					producto.setDescripcion(rs.getString("descripcion"));
					producto.setDescuento(rs.getDouble("descuento"));
					producto.setProveedor(rs.getString("proveedor"));
					producto.setUrl(rs.getString("url"));
					producto.setCantidad(rs.getInt("cantidad"));

					lista.add(producto);
				}
			}
			catch (Exception e)
			{
				System.out.println("Error: " + e.toString());
			}
			return new ProductoLista(lista);
		}
		
		public static void main(String[] args)
		{
			ProductoImplA adicionar = new ProductoImplA();
			
			System.out.println(adicionar.getProductos("duvanchulupo@gmail.com"));
		}
}
