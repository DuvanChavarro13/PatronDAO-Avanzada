package com.stiven.controller.dao;
import com.stiven.controller.impl.ProductoLista;
import com.stiven.model.Producto;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface IProductoDAO {
    
	public Response adicionar(Producto producto, @PathParam(value = "correo") String correo);
    public Response modificar(Producto producto, @PathParam(value = "id") int codigo);
    public Response eliminar(@PathParam(value = "id") int id);
    
    
    public ProductoLista getProductos(@PathParam(value = "correo") String correo);
}

