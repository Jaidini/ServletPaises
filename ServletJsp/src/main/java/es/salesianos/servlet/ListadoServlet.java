package es.salesianos.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.salesianos.model.Lenguaje;
import es.salesianos.model.User;
import es.salesianos.service.Service;

public class ListadoServlet extends HttpServlet {
	
	private Service servicio = new Service();
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> listAllUsers = servicio.listAllUsers();
		req.setAttribute("listAllUsers", listAllUsers);
		redirect(req,resp);
	}
	
	public void doGet(){
		
	}
	
	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listado.jsp");
		dispatcher.forward(req,resp);
	}
	
}
