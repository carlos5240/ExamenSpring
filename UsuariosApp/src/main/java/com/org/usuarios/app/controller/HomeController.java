package com.org.usuarios.app.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.org.usuarios.app.model.entity.Usuario;
import com.org.usuarios.app.service.IUsuarioService;

@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	IUsuarioService usuarioService;

	
	
	@GetMapping({"/","/index"})
	public String index(Authentication authentication, Model model) {
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		
		if(new Date().after(usuario.getFechaModificacion())) {
			model.addAttribute("usuario",usuario);
			return "/nuevoPassword";
		}
		
		
		return "/index";
	}
	
	@PostMapping("/actualizarPassword")
	public String actualizarPassword(HttpServletRequest request,Model model, RedirectAttributes flash) {
		Usuario usuario = usuarioService.findByUsername(request.getParameter("username"));
		System.out.println("username"+request.getParameter("username"));
		System.out.println("usuario "+usuario.getUsername());
		//String bcryptPassword = passwordEncoder.encode(request.getParameter("password"));
		usuario.setPassword(passwordEncoder.encode(request.getParameter("password")));
        usuario=usuarioService.save(usuario);	
        
        model.addAttribute("usuario", usuario);
		return "/index";
	}

}
