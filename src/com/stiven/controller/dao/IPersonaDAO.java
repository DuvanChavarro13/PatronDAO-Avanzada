package com.stiven.controller.dao;

import com.stiven.model.Persona;
import java.util.List;

public interface IPersonaDAO {
    
    public boolean guardarPersona(Persona persona);
    public boolean modificarPersona(Persona persona);
    
    public List<Persona> buscarTodos();
    
}
