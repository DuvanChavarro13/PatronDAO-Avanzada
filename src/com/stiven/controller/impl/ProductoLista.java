package com.stiven.controller.impl;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.stiven.model.Producto;

@XmlRootElement(name = "listing")
public class ProductoLista {
	private List<Producto> items;

	public ProductoLista() {
	}

	public ProductoLista(List<Producto> items) {
		this.items = items;
	}

	@XmlElement(name = "data")
	public List<Producto> getItems() {
		return items;
	}
}
