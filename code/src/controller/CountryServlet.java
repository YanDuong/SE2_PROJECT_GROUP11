package controller;

import java.io.IOException;
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
@WebServlet("/CountryServlet")
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
			switch(action) {
			case "/showCountryForm":
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
	
	private void showCountryForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOExeption, ServletException {
		request.getRequestDispatcher("country_form").forward(request, response);
	}
	
	private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("country_id"));
		Country country = countryImp.selectCountry(id);
		
	}

}
