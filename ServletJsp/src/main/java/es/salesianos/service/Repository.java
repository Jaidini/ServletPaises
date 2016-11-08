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
import es.salesianos.model.User;
import es.salesianos.utils.DateConverter;

public class Repository {
	
	private static String jdbc = "jdbc:h2:file:./src/main/resources/test";
	ConnectionManager manager = new ConnectionH2();
	String sqlCrear = "create table IF NOT EXISTS USER(name varchar(25) PRIMARY KEY,course varchar(25),dateOfBirth integer);";
	Connection conn = null;
	
	public void createTable(){
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
	
	public User search(User userDelFormulario){
		User userEnLaBase = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection conn = manager.open(jdbc);
		try{
			
			preparedStatement = conn.prepareStatement("select * from USER where name = ?");
			preparedStatement.setString(1, userDelFormulario.getName());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				userEnLaBase = new User();
				userEnLaBase.setName(resultSet.getString(1));
				userEnLaBase.setCourse(resultSet.getString(2));
				userEnLaBase.setDateOfBirth(resultSet.getString(3));
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
	
	public void insert(User user){
		PreparedStatement preparedstatement = null;
		Connection conn = manager.open(jdbc);
		try{
			preparedstatement = conn.prepareStatement("insert into user(name, course, dateOfBirth) values (?, ?, ?)");
			preparedstatement.setString(1, user.getName());
			preparedstatement.setString(2, user.getCourse());
			preparedstatement.setDate(3, new java.sql.Date(user.getDateOfBirth().getTime()));
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
	
	public void update(User user){
		PreparedStatement preparedStatement = null;
		Connection conn = manager.open(jdbc);
		try{
			preparedStatement = conn.prepareStatement("update user set course=?, dateOfBirth=? where name=?");
			preparedStatement.setString(1, user.getCourse());
			preparedStatement.setDate(2, new java.sql.Date(user.getDateOfBirth().getTime()));
			preparedStatement.setString(3, user.getName());
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException();
		}
		manager.close(conn);
	}
	
	public void close(PreparedStatement preparedStatement){
		try{
			preparedStatement.close();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void close(ResultSet resultSet){
		try{
			resultSet.close();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<User> searchAll(){
		List<User> listaADevolver = new ArrayList<User>();
		Connection conn = manager.open(jdbc);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			preparedStatement = conn.prepareStatement("select * from user");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				User user = new User();
				user.setName(resultSet.getString(1));
				user.setCourse(resultSet.getString(2));
				user.setDateOfBirth(resultSet.getString(3));
				
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
	
	public void deleteUser(String nombre){
		Connection conn = manager.open(jdbc);
		PreparedStatement preparedStatement = null;
		try{
			preparedStatement = conn.prepareStatement("delete from user where name='" + nombre + "'");
			//preparedStatement.setString(1, nombre);
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
