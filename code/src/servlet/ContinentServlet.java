package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.Continent;
import dao.ContinentImp;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class ContinentServlet
 */
@WebServlet("/ContinentServlet")
public class ContinentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContinentImp continentImp = null;

	public void init() {
		continentImp = new ContinentImp();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContinentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actions = request.getServletPath();
		
		  try { 
			  switch(actions) { 
			  
			  case "/showContinentUpdateForm": 
				  showContinentUpdateForm(request,response); 
				  break; 
			
			  case "/updateContinent": 
				  updateContinent(request, response);
				  break;
			 
			 
			  default: 
				  showContinents(request, response); 
				 
				  break;
			  }
		  } catch(SQLException e) {
			  e.printStackTrace();
		  }
			  
		 
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	  private void updateContinent(HttpServletRequest request, HttpServletResponse
	  response) throws ServletException, IOException, SQLException { 
		  int id =
	  Integer.parseInt(request.getParameter("id")); 
		  String name =
	  request.getParameter("name"); 
		  int totalOfInfected =
	  Integer.parseInt(request.getParameter("totalOfInfected")); 
		  int
	  totalOfRecovered =
	  Integer.parseInt(request.getParameter("totalOfRecovered")); 
		  int totalOfDeaths
	  = Integer.parseInt(request.getParameter("totalOfDeaths")); 
		  int newInfected =
	  Integer.parseInt(request.getParameter("updatedInfected")); 
		  int newRecovered =
	  Integer.parseInt(request.getParameter("updatedRecovered")); 
		  int newDeaths =
	  Integer.parseInt(request.getParameter("updatedDeaths")); 
		  Continent c = new
	  Continent(id, name, totalOfInfected, totalOfRecovered, totalOfDeaths,
	  newInfected, newRecovered, newDeaths);
		  continentImp.updateContinent(c);
	  response.sendRedirect("showContinentList"); }
	 

	private void showContinentUpdateForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Continent continent = continentImp.getContinent(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("continent_form.jsp");
		request.setAttribute("continent", continent);
		dispatcher.forward(request, response);
	}

	private void showContinents(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int worldInfected = continentImp.getTotalOfInfected();
		int worldRecovered = continentImp.getTotalOfRecovered();
		int worldDeaths = continentImp.getTotalOfDeaths();
		int newInfected = continentImp.getUpdatedInfected();
		int newRecovered = continentImp.getUpdatedRecovered();
		int newDeaths = continentImp.getUpdatedDeaths();
		request.setAttribute("worldInfected", worldInfected);
		request.setAttribute("worldRecovered", worldRecovered);
		request.setAttribute("worldDeaths", worldDeaths);
		request.setAttribute("newInfected", newInfected);
		request.setAttribute("newRecovered", newRecovered);
		request.setAttribute("newDeaths", newDeaths);
		List<Continent> continents = continentImp.getAllContinents();
		request.setAttribute("continents", continents);
		request.getRequestDispatcher("continent-table.jsp").forward(request, response);
	}
	
	
	
	

}

