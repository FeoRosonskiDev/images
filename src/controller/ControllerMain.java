package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.PersonDao;
import model.Person;

@MultipartConfig
@WebServlet(description = "main controller", urlPatterns = { "/Operations" })
public class ControllerMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Person p;
	private PersonDao pdao = new PersonDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action.equals("Listar"))
		{
			List<Person> people = pdao.listAllPerson();
			request.setAttribute("peopleList", people);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else if(action.equals("Nuevo"))
		{
			request.getRequestDispatcher("addPerson.jsp").forward(request, response);
		}
		else if(action.equals("Agregar"))
		{
			String name = request.getParameter("name");
			Part part = request.getPart("photo");
			InputStream photo = part.getInputStream();
			
			p = new Person();
			p.setNames(name);
			p.setPhoto(photo);
			
			if(pdao.addPerson(p))
			{
				request.getRequestDispatcher("Operations?action=Listar").forward(request, response);
			}
			
		}
	}

}
