package com.org.usuarios.app.service;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.usuarios.app.model.dao.IUsuarioDao;
import com.org.usuarios.app.model.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	IUsuarioDao usuarioDao;

	private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Override
	@Transactional(readOnly=true)
	public boolean fechaCaducidad(Date fechaActual, Usuario usuario) {
		// TODO Auto-generated method stub
		System.out.println("fecha Actual "+fechaActual);
		  System.out.println("FECHA MODIFICACION "+usuario.getFechaModificacion());
		  
		  logger.info("fecha Actual "+fechaActual);
		  logger.info("FECHA MODIFICACION "+usuario.getFechaModificacion());
		  
		if(usuario.getFechaModificacion().before(fechaActual))
			return true;
		else 
			return false;
	}




	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		 Calendar c = Calendar.getInstance();
	        c.setTime(usuario.getFechaModificacion());
	        c.add(Calendar.DATE, 30);

		usuario.setFechaModificacion(c.getTime());
		return usuarioDao.save(usuario);
	}




	@Override
	@Transactional(readOnly=true)
	public Usuario findByUsername(String login) {
		// TODO Auto-generated method stub
		return usuarioDao.findByUsername(login);
	}

}
