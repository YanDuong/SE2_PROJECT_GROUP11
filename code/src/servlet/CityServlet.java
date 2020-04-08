package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CityCrudImp;
import model.City;

/**
 * Servlet implementation class CityServlet
 */
 @WebServlet("/CityServlet") 
public class CityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CityCrudImp cityImp;
	 public void init() {
	    	cityImp = new CityCrudImp();
	    }
	    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String request_action = request.getServletPath();
		try {
			switch(request_action) {
			case "/newCityForm":
				showCityForm(request, response);
				break;
			case "/addNewCity":
				addNewCity(request, response);
				break;
			case "/deleteCity":
				deleteCity(request, response);
				break;
			case "/updateCityStatistics":
				updateCityStatistics(request, response);
				break;
			case "/showCitiesUpdateForm":
				showUpdateForm(request, response);
				break;
			default:
				showCityList(request, response);
				break;
			}
			
			
		} catch(SQLException e) {
			throw new ServletException(e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	private void showCityList(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		List<City> cities = cityImp.getAllCities();
		int numberOfInfected = cityImp.getTotalOfInfected();
		request.setAttribute("numberOfInfected", numberOfInfected);
		int numberOfRecovered = cityImp.getTotalOfRecovered();
		request.setAttribute("numberOfRecovered", numberOfRecovered);
		int numberOfDeaths = cityImp.getTotalOfDeaths();
		request.setAttribute("numberOfDeaths", numberOfDeaths);
		int updatedInfected = cityImp.getUpdatedInfected();
		request.setAttribute("numberOfNewInfected", updatedInfected);
		int updatedRecovered = cityImp.getTotalOfRecovered();
		request.setAttribute("numberOfNewRecovered", updatedRecovered);
		int updatedDeaths = cityImp.getUpdatedDeaths();
		request.setAttribute("numberOfNewDeaths", updatedDeaths);
		
		
		request.setAttribute("cities", cities);
		request.getRequestDispatcher("cities-table.jsp").forward(request, response);
	}
	
	private void showCityForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		request.getRequestDispatcher("city_form.jsp").forward(request, response);
	}
	
	private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		City city = cityImp.getCity(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("city_form.jsp");
		request.setAttribute("city", city);
		dispatcher.forward(request, response);
		
		
		
	}
	
	private void addNewCity(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String name = request.getParameter("name");
		int totalOfInfected = Integer.parseInt(request.getParameter("totalOfInfected"));
		int totalOfRecovered = Integer.parseInt(request.getParameter("totalOfRecovered"));
		int totalOfDeaths = Integer.parseInt(request.getParameter("totalOfDeaths"));
		int updatedInfected = Integer.parseInt(request.getParameter("updatedInfected"));
		int updatedRecovered = Integer.parseInt(request.getParameter("updatedRecovered"));
		int updatedDeaths = Integer.parseInt(request.getParameter("updatedDeaths"));
		cityImp.addCity(new City(name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths));
		response.sendRedirect("cities-list");
		
		
	}
	
	private void updateCityStatistics(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int totalOfInfected = Integer.parseInt(request.getParameter("totalOfInfected"));
		int totalOfRecovered = Integer.parseInt(request.getParameter("totalOfRecovered"));
		int totalOfDeaths = Integer.parseInt(request.getParameter("totalOfDeaths"));
		int updatedInfected = Integer.parseInt(request.getParameter("updatedInfected"));
		int updatedRecovered = Integer.parseInt(request.getParameter("updatedRecovered"));
		int updatedDeaths = Integer.parseInt(request.getParameter("updatedDeaths"));
		City c = new City(id, name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths);
		cityImp.updateCity(c);
		response.sendRedirect("cities-list");
	}
	private void deleteCity(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		cityImp.deleteCity(id);
		response.sendRedirect("cities-list");
		
	}
	
	
	

}
