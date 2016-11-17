package es.salesianos.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.Lenguaje;
import es.salesianos.model.User;
import es.salesianos.model.assembler.LenguajeAssembler;
import es.salesianos.model.assembler.UserAssembler;
import es.salesianos.utils.DateConverter;

public class Service {
	
	String sqlCrearPais = "create table IF NOT EXISTS PAIS(name varchar(25) PRIMARY KEY,languaje varchar(25));";
	String sqlCrearLanguaje = "create table IF NOT EXISTS LANGUAJE(nombreLenguaje varchar(25) PRIMARY KEY)";
	Repository repositorio = new Repository();
	
	private DateConverter converter = new DateConverter();
	private ConnectionManager manager = new ConnectionH2();
	
	
	public User assembleUserFromRequest(HttpServletRequest req) {
		return UserAssembler.assembleUserFrom(req);
	}
	
	public Lenguaje assembleLenguaje(HttpServletRequest req) {
		return LenguajeAssembler.assembleUserFrom(req);
	}

	public void insertOrUpdate(User user, Lenguaje lenguaje) {
		 
		
		repositorio.createTable(sqlCrearPais);
		repositorio.createTable(sqlCrearLanguaje);//Así creamos las tablas si todavía no existen
		
		User user1 = repositorio.searchPais(user);//Aquí buscamos si el pais que se nos pasa ya existe y lo "guardamos" en user1 (si existe)
		
		if(user1 == null){
			repositorio.insertPais(user);//Si el usuario que se busca es nulo, se inserta el usuario como nuevo
		}else {
			repositorio.updatePais(user);//Si se encuentra un usuario en 'search()', hacemos el 'update()' 
		}
		
		Lenguaje leng = repositorio.searchLanguaje(lenguaje);
		
		if(leng == null){
			repositorio.insertLenguaje(lenguaje);
		}else{
			repositorio.updateLenguaje(lenguaje);
		}
	}
	
	public List<User> listAllUsers() {
		return repositorio.searchAll();
	}
	
	public void delete(String user){
		repositorio.deleteLenguaje(user);
		repositorio.deletePaisDelLenguaje(user);
	}
	
	public ConnectionManager getManager() {
		return manager;
	}
	public void setManager(ConnectionManager manager) {
		this.manager = manager;
	}

}
