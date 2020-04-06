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
		String name = request.getParameter("name");
		int totalOfInfected = Integer.parseInt(request.getParameter("totalOfInfected"));
		int totalOfRecovered = Integer.parseInt(request.getParameter("totalOfRecovered"));
		int totalOfDeaths = Integer.parseInt(request.getParameter("totalOfDeaths"));
		int updatedInfected = Integer.parseInt(request.getParameter("updatedInfected"));
		int updatedRecovered = Integer.parseInt(request.getParameter("updatedRecovered"));
		int updatedDeaths = Integer.parseInt(request.getParameter("updatedDeaths"));
		countryImp.addCountry(new Country(name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths));
		response.sendRedirect("list");
		
		
	}
	
	private void updateCountryStatistics(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int totalOfInfected = Integer.parseInt(request.getParameter("totalOfInfected"));
		int totalOfRecovered = Integer.parseInt(request.getParameter("totalOfRecovered"));
		int totalOfDeaths = Integer.parseInt(request.getParameter("totalOfDeaths"));
		int updatedInfected = Integer.parseInt(request.getParameter("updatedInfected"));
		int updatedRecovered = Integer.parseInt(request.getParameter("updatedRecovered"));
		int updatedDeaths = Integer.parseInt(request.getParameter("updatedDeaths"));
		Country c = new Country(id, name, totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths);
		countryImp.updateCountry(c);
		response.sendRedirect("list");
	}
	private void deleteCountry(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		countryImp.deleteCountry(id);
		response.sendRedirect("list");
		
	}
	

}
