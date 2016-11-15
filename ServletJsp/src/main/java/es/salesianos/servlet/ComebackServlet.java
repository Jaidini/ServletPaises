package es.salesianos.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.User;
import es.salesianos.model.assembler.UserAssembler;
import es.salesianos.service.Repository;
import es.salesianos.service.Service;
import es.salesianos.utils.DateConverter;

public class ComebackServlet extends HttpServlet{
	
	private Service servicio = new Service();
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*String user = req.getParameter("user");
		req.setAttribute("nom", user);
		
		servicio.delete(user);
		redirect(req,resp);*/
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("user");
		req.setAttribute("nom", user);
		
		redirect(req,resp);
	} 
	
	private void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confirmacion.jsp");
		dispatcher.forward(req,resp);
	}
	
}
