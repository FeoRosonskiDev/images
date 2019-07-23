package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonDao;

@WebServlet(description = "controller for showing each photo", urlPatterns = { "/ControllerPhoto" })
public class ControllerPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PersonDao pdao = new PersonDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = Integer.valueOf(request.getParameter("idPerson"));
		
		pdao.listPhoto(id, response);
	}

}
