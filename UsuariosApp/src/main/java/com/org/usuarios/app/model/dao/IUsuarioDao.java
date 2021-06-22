package com.org.usuarios.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.org.usuarios.app.model.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, String>{
	
	public Usuario findByUsername(String login);

}
