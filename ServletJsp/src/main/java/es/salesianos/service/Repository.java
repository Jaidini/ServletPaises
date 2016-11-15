package es.salesianos.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.Lenguaje;
import es.salesianos.model.User;
import es.salesianos.model.Lenguaje;
import es.salesianos.utils.DateConverter;

public class Repository {
	
	private static String jdbc = "jdbc:h2:file:./src/main/resources/test";
	ConnectionManager manager = new ConnectionH2();
	
	Connection conn = null;
	
	public void createTable(String sqlCrear){
		Statement statement = null;
		try{
			Connection conn = manager.open(jdbc);
			statement = conn.createStatement();
			statement.execute(sqlCrear);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(statement != null){
				try{
					statement.close();
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
	
	public User searchPais(User userDelFormulario){
		User userEnLaBase = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection conn = manager.open(jdbc);
		try{
			preparedStatement = conn.prepareStatement("select * from PAIS where name = ?");
			preparedStatement.setString(1, userDelFormulario.getName());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				userEnLaBase = new User();
				userEnLaBase.setName(resultSet.getString(1));
				userEnLaBase.setLanguaje(resultSet.getString(2));
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			close(preparedStatement);
			close(resultSet);
		}
		manager.close(conn);
		return userEnLaBase;
	}
	
	public void insertPais(User user){
		PreparedStatement preparedstatement = null;
		Connection conn = manager.open(jdbc);
		try{
			preparedstatement = conn.prepareStatement("insert into PAIS(name, languaje) values (?, ?)");
			preparedstatement.setString(1, user.getName());
			preparedstatement.setString(2, user.getLanguaje());
			preparedstatement.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			close(preparedstatement);
		}
		manager.close(conn);
	}
	
	public void updatePais(User user){
		PreparedStatement preparedStatement = null;
		Connection conn = manager.open(jdbc);
		try{
			preparedStatement = conn.prepareStatement("update PAIS set languaje=? where name=?");
			preparedStatement.setString(1, user.getLanguaje());
			preparedStatement.setString(2, user.getName());
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException();
		}
		manager.close(conn);
	}
	
	public Lenguaje searchLanguaje(Lenguaje lenguajeEnLaBase){
		Lenguaje leng = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection conn = manager.open(jdbc);
		try{
			preparedStatement = conn.prepareStatement("select * from LANGUAJE where nombreLenguaje = ?");
			preparedStatement.setString(1, lenguajeEnLaBase.getName());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				leng = new Lenguaje();
				leng.setName(resultSet.getString(1));
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			close(preparedStatement);
			close(resultSet);
		}
		manager.close(conn);
		return leng;
	}
	
	public void insertLenguaje(Lenguaje lenguaje){
		PreparedStatement preparedstatement = null;
		Connection conn = manager.open(jdbc);
		try{
			preparedstatement = conn.prepareStatement("insert into LANGUAJE(nombreLenguaje) values (?)");
			preparedstatement.setString(1, lenguaje.getName());
			preparedstatement.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			close(preparedstatement);
		}
		manager.close(conn);
	}
	
	public void updateLenguaje(Lenguaje lenguaje){
		PreparedStatement preparedStatement = null;
		Connection conn = manager.open(jdbc);
		try{
			preparedStatement = conn.prepareStatement("update LANGUAJE set nombreLenguaje=? where nombreLenguaje=?");
			preparedStatement.setString(1, lenguaje.getName());
			preparedStatement.setString(2, lenguaje.getName());
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException();
		}
		manager.close(conn);
	}
	
	public void close(PreparedStatement preparedStatement){
		if(preparedStatement != null){
			try{
				preparedStatement.close();
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	
	public void close(ResultSet resultSet){
		if(resultSet != null){
			try{
				resultSet.close();
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	
	public List<User> searchAll(){
		List<User> listaADevolver = new ArrayList<User>();
		Connection conn = manager.open(jdbc);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = conn.prepareStatement("select * from PAIS");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				User user = new User();
				user.setName(resultSet.getString(1));
				user.setLanguaje(resultSet.getString(2));
				listaADevolver.add(user);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			close(preparedStatement);
			close(resultSet);
		}
		manager.close(conn);
		return listaADevolver;
	}
	
	public List<Lenguaje> listaLenguaje(){
		List<Lenguaje> listaADevolver = new ArrayList<Lenguaje>();
		Connection conn = manager.open(jdbc);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = conn.prepareStatement("select * from LANGUAJE");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Lenguaje user = new Lenguaje();
				user.setName(resultSet.getString(1));
				listaADevolver.add(user);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			close(preparedStatement);
			close(resultSet);
		}
		manager.close(conn);
		return listaADevolver;
	}
	
	public void deleteLenguaje(String nombre){
		Connection conn = manager.open(jdbc);
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = conn.prepareStatement("delete from LANGUAJE where nombreLenguaje='" + nombre + "'");
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			if(preparedStatement != null){
				try{
					preparedStatement.close();
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
	
	public void deletePaisDelLenguaje(String nombre){
		Connection conn = manager.open(jdbc);
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = conn.prepareStatement("delete from PAIS where languaje='" + nombre + "'");
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			if(preparedStatement != null){
				try{
					preparedStatement.close();
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
	
}
