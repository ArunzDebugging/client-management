package com.ClientManagement.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ClientManagement.bean.Client;
import com.ClientManagement.dao.ClientDao;
import java.util.*;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet("/")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientDao clientDao;
       
	public void init() throws ServletException {
		this.clientDao=new ClientDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request,response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getServletPath();
		switch(action)
		{
		case "/new":
			showNewForm(request,response);
			break;
		case "/insert":
			try {
				insertClient(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/delete":
			try {
				deleteClient(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/edit": 
			try {
				showEditForm(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/update":
			try {
				updateClient(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			try {
				listClient(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
		
	}
	
	//to list all the clients
	
	private void listClient(HttpServletRequest request,HttpServletResponse response)throws SQLException,ServletException,IOException
	{
		List<Client>listClient=clientDao.selectAllClients();
	    request.setAttribute("listClient", listClient);
	    RequestDispatcher dispatcher=request.getRequestDispatcher("client-list.jsp");
	    dispatcher.forward(request, response);
	    
	}
	
	//to get the values from the form
	private void showNewForm(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		RequestDispatcher dispatcher=request.getRequestDispatcher("client-form.jsp");
		dispatcher.forward(request,response);
	}
	//insert client
	
	private void insertClient(HttpServletRequest request,HttpServletResponse response)throws SQLException,IOException
	{
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String country=request.getParameter("country");
		Client newClient=new Client(name,email,country);
		clientDao.insertClient(newClient);
		response.sendRedirect("list");
	}
	
	//delete client
	private void deleteClient(HttpServletRequest request,HttpServletResponse response)throws SQLException,IOException
	{
		int id=Integer.parseInt(request.getParameter("id"));
		clientDao.deleteClient(id);
		response.sendRedirect("list");
	}
	
	//edit client
	private void showEditForm(HttpServletRequest request,HttpServletResponse response)throws SQLException,ServletException,IOException
    {
		int id=Integer.parseInt(request.getParameter("id"));
		Client existingClient=clientDao.selectClient(id);
		RequestDispatcher dispatcher=request.getRequestDispatcher("client-form.jsp");
		request.setAttribute("client",existingClient);
		dispatcher.forward(request, response);
    }
	
	//update client
	private void updateClient(HttpServletRequest request,HttpServletResponse response)throws SQLException,IOException
	{
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String country=request.getParameter("country");
		Client book=new Client(id,name,email,country);
		clientDao.updateClient(book);
		response.sendRedirect("list");
		
	}

	

}
