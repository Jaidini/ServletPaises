package es.salesianos.model.assembler;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.model.Lenguaje;

public class LenguajeAssembler {

	public static Lenguaje assembleUserFrom(HttpServletRequest req) {
		Lenguaje lenguaje = new Lenguaje();
		String nombre1 = req.getParameter("idioma");
		String nombre2 = req.getParameter("course");
		
		if(nombre1 == "0"){
			lenguaje.setName(nombre2);
		}else{
			lenguaje.setName(nombre1);
		}
		
		return lenguaje;
	}
}