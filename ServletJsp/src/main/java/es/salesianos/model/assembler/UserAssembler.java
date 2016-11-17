package es.salesianos.model.assembler;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.model.User;

public class UserAssembler {

	public static User assembleUserFrom(HttpServletRequest req) {
		User user = new User();
		String nombre = req.getParameter("name");
		String course = req.getParameter("course");
		String idioma = req.getParameter("idioma");
		String prueba = "";
		
		user.setName(nombre);
		if(idioma == "0"){
			user.setLanguaje(course);
		}else{
			user.setLanguaje(idioma);
		}		
		prueba = user.getName();
		return user;
	}
}