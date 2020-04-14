package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import model.Country;
import dao.CountryCrudImp;
import java.util.List;
/**
 * Servlet implementation class CountryServlet
 */
@WebServlet("/CountrySerlvet")
public class CountryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountryServlet() {
        super();
        
    }
    private CountryCrudImp countryImp;
    public void init() {
    	countryImp = new CountryCrudImp();
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String request_action = request.getServletPath();
		try {
			switch(request_action) {
			
			case "/newCountryForm":
				showCountryForm(request, response);
				break;
			case "/addNewCountry":
				addNewCountry(request, response);
				break;
			case "/deleteCountry":
				deleteCountry(request, response);
				break;
			case "/updateCountryStatistics":
				updateCountryStatistics(request, response);
				break;
			case "/showUpdateForm":
				showUpdateForm(request, response);
				break;
			default:
				showCountryList(request, response);
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
	
	private void showCountryList(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		List<Country> countries = countryImp.getAllCountries();
		request.setAttribute("countries", countries);
		request.getRequestDispatcher("tables.jsp").forward(request, response);
	}
	
	private void showCountryForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		request.getRequestDispatcher("country_form.jsp").forward(request, response);
	}
	
	private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Country country = countryImp.getCountry(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("country_form.jsp");
		request.setAttribute("country", country);
		dispatcher.forward(request, response);
		
		
		
	}
	
	private void addNewCountry(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		/*
		 * String name = request.getParameter("name"); int totalOfInfected =
		 * Integer.parseInt(request.getParameter("totalOfInfected")); int
		 * totalOfRecovered =
		 * Integer.parseInt(request.getParameter("totalOfRecovered")); int totalOfDeaths
		 * = Integer.parseInt(request.getParameter("totalOfDeaths")); int
		 * updatedInfected = Integer.parseInt(request.getParameter("updatedInfected"));
		 * int updatedRecovered =
		 * Integer.parseInt(request.getParameter("updatedRecovered")); int updatedDeaths
		 * = Integer.parseInt(request.getParameter("updatedDeaths"));
		 * countryImp.addCountry(new Country(name, totalOfInfected, totalOfRecovered,
		 * totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths));
		 * response.sendRedirect("list");
		 */
		String name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths;
		String message = "";
		name = request.getParameter("name");
		totalOfInfected = request.getParameter("totalOfInfected");
		totalOfRecovered = request.getParameter("totalOfRecovered");
		totalOfDeaths = request.getParameter("totalOfDeaths");
		updatedInfected = request.getParameter("updatedInfected");
		updatedRecovered = request.getParameter("updatedRecovered");
		updatedDeaths = request.getParameter("updatedDeaths");
		
		 if(countryImp.checkInteger(totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths) == false) {
			message += "You need to enter number!";
		} else {
			int numberOfInfected = Integer.parseInt(totalOfInfected);
			int numberOfRecovered = Integer.parseInt(totalOfRecovered);
			int numberOfDeaths = Integer.parseInt(totalOfDeaths);
			int newInfected = Integer.parseInt(updatedInfected);
			int newRecovered = Integer.parseInt(updatedRecovered);
			int newDeaths = Integer.parseInt(updatedDeaths);
			if(newInfected > numberOfInfected || newRecovered > numberOfRecovered || newDeaths > numberOfDeaths ) {
				if(newInfected > numberOfDeaths) {
					message += "The new infected can not be larger than number of infected! + \n";
				}
				if(newRecovered > numberOfRecovered) {
					message += "The new recovered can not be larger than the number of recovered!";
				}
				if(newDeaths > numberOfDeaths) {
					message += "The new deaths can not be larger than the number of deaths";
				}
			} else {
				countryImp.addCountry(new Country(name, numberOfInfected, numberOfRecovered, numberOfDeaths, newInfected, newRecovered, newDeaths));
				response.sendRedirect("list");
			}
			
			
		}
		if(message.length() > 0) {
			request.setAttribute("message", message);
			request.getRequestDispatcher("country_form.jsp").forward(request, response);
		}
		
		
		
		
	}
	
	private void updateCountryStatistics(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String totalOfInfected = request.getParameter("totalOfInfected");
		String totalOfRecovered = request.getParameter("totalOfRecovered");
		String totalOfDeaths = request.getParameter("totalOfDeaths");
		String updatedInfected = request.getParameter("updatedInfected");
		String updatedRecovered = request.getParameter("updatedRecovered");
		String updatedDeaths = request.getParameter("updatedDeaths");
		String message = "";
		 if(countryImp.checkInteger(totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths) == false) {
			message += "You need to enter number!";
		} else if(countryImp.checkExistingField(name) == true) {
			message += "The country is already existed!";
		} else {
		
			int numberOfInfected = Integer.parseInt(totalOfInfected);
			int numberOfRecovered = Integer.parseInt(totalOfRecovered);
			int numberOfDeaths = Integer.parseInt(totalOfDeaths);
			int newInfected = Integer.parseInt(updatedInfected);
			int newRecovered = Integer.parseInt(updatedRecovered);
			int newDeaths = Integer.parseInt(updatedDeaths);
			if(newInfected > numberOfDeaths) {
				message += "The new infected can not be larger than number of infected! + \n";
			}
			if(newRecovered > numberOfRecovered) {
				message += "The new recovered can not be larger than the number of recovered! + \n";
			}
			if(newDeaths > numberOfDeaths) {
				message += "The new deaths can not be larger than the number of deaths";
			}
			Country c = new Country(id, name, numberOfInfected, numberOfRecovered, numberOfDeaths, newInfected, newRecovered, newDeaths);
			countryImp.updateCountry(c);
			response.sendRedirect("list");
		
		
	}
		 if(message.length() > 0) {
				request.setAttribute("message", message);
				request.getRequestDispatcher("country_form.jsp").forward(request, response);
			}
	}
	private void deleteCountry(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		countryImp.deleteCountry(id);
		/* countryImp.resetId(); */
		response.sendRedirect("list");
		
	}

	

}
