package com.stiven.util;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;

import java.io.IOException;
//import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/iniciar.do")
public class SesionCrear extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String correo = request.getParameter("correo");
			String pass = request.getParameter("pass");
			
			if(usuarioExiste(correo, pass))
			{
				HttpSession session= request.getSession(true);
				session.setAttribute("correoAvanzada", correo);
				
				response.sendRedirect("producto.jsp");
			}
			else
			{
				response.sendRedirect("error.jsp");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
			System.out.println(e.getMessage());
		}
	}
	
	public boolean usuarioExiste(String correo, String pass)
	{
		DBConexion conexion = DBConexion.getConexion();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "";
		
		Seguridad encriptar;
		
		try
		{
			conexion.conectar();
			encriptar = new Seguridad(pass);
			
	        sql = "SELECT * FROM persona WHERE correo=? AND pass=?";
	        st = conexion.conectar().prepareStatement(sql);
	        
	        st.setString(1, correo);
	        st.setString(2, encriptar.getEncriptada());

	        rs = st.executeQuery();
	        
	        while(rs.next()) return true;
	        return false;
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
		return false;
	}
}
