package com.stiven.controller.impl;

import com.stiven.controller.dao.IPersonaDAO;
import com.stiven.model.Persona;
import com.stiven.util.DBConexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PersonaImpl implements IPersonaDAO {

    @Override
    public boolean guardarPersona(Persona persona) {
        DBConexion conexion = DBConexion.getConexion();

        try {

            conexion.conectar().setAutoCommit(false);

            String sql = "insert into persona (nombre, apellido, identificacion, telefono, correo, pass) "
                    + "values (?,?,?,?,?)";

            PreparedStatement st = conexion.conectar().prepareStatement(sql);
            st.setString(0, persona.getNombre());
            st.setString(1, persona.getApellido());
            st.setString(2, persona.getIdentificacion());
            st.setString(3, persona.getTelefono());
            st.setString(4, persona.getCorreo());
            st.setString(5, persona.getPass());

            st.execute();
            conexion.conectar().commit();
            return true;

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
            try {
                conexion.conectar().setAutoCommit(false);
                conexion.conectar().rollback();
            } catch (SQLException ex1) {
                System.out.println(ex1.getMessage());
            }
            return false;
        }
    }

    @Override
    public List<Persona> buscarTodos() 
    {

        DBConexion conexion = DBConexion.getConexion();

        List<Persona> listaPersona = new ArrayList<> ();
        try
        {
            
            String sql = "Select * from persona";
            
            PreparedStatement ps = conexion.conectar().prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            
            
            while(rs.next())
            {
                
                Persona persona = new Persona();
                
                persona.setId(rs.getInt("id"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setIdentificacion(rs.getString("identificacion"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setCorreo(rs.getString("correo"));
                persona.setPass(rs.getString("pass"));
                
                listaPersona.add(persona);    
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
       return listaPersona;
    }
    
    @Override
    public boolean modificarPersona(Persona persona)
    {
    	DBConexion conexion = DBConexion.getConexion();

        try 
        {

            conexion.conectar().setAutoCommit(false);

            String sql = "update persona set nombre=?, apellido=?, identificacion=?, telefono=?, correo=?, pass=?) "
            		+ "where id =?";

            PreparedStatement st = conexion.conectar().prepareStatement(sql);
            st.setString(0, persona.getNombre());
            st.setString(1, persona.getApellido());
            st.setString(2, persona.getIdentificacion());
            st.setString(3, persona.getTelefono());
            st.setString(4, persona.getCorreo());
            st.setString(5, persona.getPass());
            st.setInt(6, persona.getId());

            st.execute();
            conexion.conectar().commit();
            return true;

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
            try {
                conexion.conectar().setAutoCommit(false);
                conexion.conectar().rollback();
            } catch (SQLException ex1) {
                System.out.println(ex1.getMessage());
            }
            return false;
        }
    }

}
