package com.org.usuarios.app.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.org.usuarios.app.model.entity.Usuario;
import com.org.usuarios.app.service.IUsuarioService;

@Controller
public class LoginController {

	
	@GetMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout", required = false)String logout,
			@RequestParam(value="invalid", required = false) String invalid,
			Model model, Principal principal, RedirectAttributes flash) {
		
		
		if(invalid != null) {
			System.out.println("Nueva contraseña");
			return "/nuevoPassword";
		}
		
		if(principal != null) {
			
				flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
				return "redirect:/";				
			}

		


		
		if(error != null) {
			
			model.addAttribute("error", "Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
		    
		}
		
		if(logout != null) {
			model.addAttribute("success", "Ha cerrado sesión con éxito!");
		}
		
		return "login";
	}
}
