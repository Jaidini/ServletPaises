package es.salesianos.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.User;
import es.salesianos.model.assembler.UserAssembler;
import es.salesianos.utils.DateConverter;

public class Service {
	
	String sqlCreaTabla = "create table IF NOT EXISTS USER(name varchar(25) PRIMARY KEY,course varchar(25),dateOfBirth date);";
	Repository repositorio = new Repository();
	
	private DateConverter converter = new DateConverter();
	private ConnectionManager manager = new ConnectionH2();
	
	
	public User assembleUserFromRequest(HttpServletRequest req) {
		return UserAssembler.assembleUserFrom(req);
	}

	public void insertOrUpdate(User user) {
		 
		
		repositorio.createTable();//Así creamos la base de datos si todavía no existe
		
		User user1 = repositorio.search(user);//Aquí buscamos si el usuario que se nos pasa ya existe y lo "guardamos" en user1 (si existe)
		
		if(user1 == null){
			repositorio.insert(user);//Si el usuario que se busca es nulo, se inserta el usuario como nuevo
		}else {
			repositorio.update(user);//Si se encuentra un usuario en 'search()', hacemos el update()  
		}
	}

	public void calculateAgeAndAddIntoRequest(HttpServletRequest req, Date date) {
		Integer yearNac = converter.getAge(date);
		req.setAttribute("age", yearNac.toString());
	}
	
	public List<User> listAllUsers() {
		return repositorio.searchAll();
	}
	
	public DateConverter getConverter() {
		return converter;
	}
	public void setConverter(DateConverter converter) {
		this.converter = converter;
	}
	public ConnectionManager getManager() {
		return manager;
	}
	public void setManager(ConnectionManager manager) {
		this.manager = manager;
	}
	
	public void delete(String user){
		repositorio.deleteUser(user);
	}
	

}
