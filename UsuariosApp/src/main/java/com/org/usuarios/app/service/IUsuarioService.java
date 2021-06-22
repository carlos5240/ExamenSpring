package com.org.usuarios.app.service;

import java.util.Date;

import com.org.usuarios.app.model.entity.Usuario;

public interface IUsuarioService {

	public boolean fechaCaducidad(Date fechaActual, Usuario usuario);
	
	public Usuario save(Usuario usuario);
	
	public Usuario findByUsername(String login);
}
