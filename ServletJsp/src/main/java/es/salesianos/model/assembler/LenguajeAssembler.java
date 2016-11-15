package es.salesianos.model.assembler;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.model.Lenguaje;

public class LenguajeAssembler {

	public static Lenguaje assembleUserFrom(HttpServletRequest req) {
		Lenguaje lenguaje = new Lenguaje();
		String nombre = req.getParameter("course");
		lenguaje.setName(nombre);
		
		return lenguaje;
	}
}