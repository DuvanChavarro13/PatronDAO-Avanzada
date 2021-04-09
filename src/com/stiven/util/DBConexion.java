package com.stiven.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConexion {

    private DBConexion() {
        
        System.out.println();
    }
    
    public Connection cnx;
    
    private static DBConexion instancia;
    
    public static DBConexion getConexion(){
        
        if(instancia ==null){
            
            instancia= new DBConexion();
            System.out.println("se instancia");
        }else
            System.out.println("ya ha sido instanciada");
        
        
        return instancia;
 
    }
    
    
    
    public Connection conectar()
    {
      try {
            String host= "localhost";
            String user= "root";
            String password= "5119";
            String database = "producto";
            
            String driver= "com.mysql.jdbc.Driver";
            
            Class.forName(driver);
            
            cnx =  DriverManager.
                    getConnection("jdbc:mysql://"+host+":3306/"+database+"?useSSL=false",user,password);
            
            System.out.println("Conexion exitosa");
            
            
        } catch (Exception ex) {
            System.out.println("Conexion no exitosa");
            System.out.println(ex);
        }
        
        return cnx;
        
    
    }
}
