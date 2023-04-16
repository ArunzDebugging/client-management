package com.ClientManagement.dao;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;

import com.ClientManagement.bean.Client;

public class ClientDao {
	
	public ClientDao()
	{
		
	}
   
	private String jdbcURL="jdbc:mysql://localhost:3306/clientdb?useSSL=false";
    private String jdbcUsername="root";
    private String jdbcPassword="arun@7358890024";
    private String jdbcDriver="com.mysql.cj.jdbc.Driver";
    
    private static final String INSERT_CLIENTS_SQL="INSERT INTO clients"+"(name,email,country) VALUES"+"(?,?,?);";
    private static final String SELECT_ALL_CLIENTS="SELECT * from clients";
    private static final String SELECT_CLIENTS_BY_ID="SELECT id,name,email,country from clients WHERE id=?";
    private static final String DELETE_CLIENTS_SQL="DELETE FROM clients WHERE id=?;";
    private static final String UPDATE_CLIENTS_SQL="UPDATE clients SET name=?,email=?,country=? WHERE id=?;";
    
    // method for connection
    
    protected Connection getConnection()
    {
    	Connection connection=null;
    	try {
    		Class.forName(jdbcDriver);
    		connection=DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
     	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	catch(ClassNotFoundException e)
    	{
    		e.printStackTrace();
    	}
    	return connection;
    } 
    
    // CRUD operations for clients
    
    //insert clients
     
    public void insertClient(Client client) throws SQLException
    {
    	System.out.println(INSERT_CLIENTS_SQL);
    	try (Connection connection=getConnection();
    			PreparedStatement preparedStatement=connection.prepareStatement(INSERT_CLIENTS_SQL)){
    	preparedStatement.setString(1,client.getName());	
    	preparedStatement.setString(2,client.getEmail());
    	preparedStatement.setString(3,client.getCountry());
    	System.out.println(preparedStatement);
    	preparedStatement.executeUpdate();
    		
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    }
    
    //update clients
    
    public boolean updateClient(Client client)throws SQLException
    {
    	
    	boolean rowUpdated;
    	try (Connection connection=getConnection();
    			PreparedStatement statement=connection.prepareStatement(UPDATE_CLIENTS_SQL)){
    	statement.setString(1,client.getName());	
    	statement.setString(2,client.getEmail());
    	statement.setString(3,client.getCountry());
    	statement.setInt(4,client.getId());
    	
    	rowUpdated=statement.executeUpdate()>0;
    		
    	}
    	return rowUpdated;
    	
    }
    
    // select clients by id
    
    public Client selectClient(int id)
    {
    	Client client=null;
    	try (Connection connection=getConnection();
    			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_CLIENTS_BY_ID)){
    	preparedStatement.setInt(1,id);
    	System.out.println(preparedStatement);
    	ResultSet rs=preparedStatement.executeQuery();
    	while(rs.next())
    	{
    		String name=rs.getString("name");
    		String email=rs.getString("email");
    		String country=rs.getString("country");
    		client=new Client(id,name,email,country);
    		
    	}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	return client;
    	
    }
    
    // select all the clients
    
    public List<Client> selectAllClients()
    {
    	List<Client> clients=new ArrayList<>();
    	try (Connection connection=getConnection();
    			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_CLIENTS)){
    		System.out.println(preparedStatement);
    		ResultSet rs=preparedStatement.executeQuery();
    		while(rs.next())
    		{
    			int id=rs.getInt("id");
    			String name=rs.getString("name");
    			String email=rs.getString("email");
    			String country=rs.getString("country");
    			clients.add(new Client(id,name,email,country));
    			
    		}
    		
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	return clients;
    	
    	
    }
    
    // delete client records
    
    public boolean deleteClient(int id)throws SQLException
    {
    	boolean rowDeleted;
    	try (Connection connection=getConnection();
    			PreparedStatement statement=connection.prepareStatement(DELETE_CLIENTS_SQL)){
    	statement.setInt(1,id);
    	rowDeleted=statement.executeUpdate()>0;
    }
    	return rowDeleted;
    
    }
    
}
