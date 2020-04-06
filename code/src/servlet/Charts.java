package servlet;
import dao.CountryCrudImp;
import model.Country;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


/**
 * Servlet implementation class Charts
 */
@WebServlet("/Charts")
public class Charts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CountryCrudImp countryImp;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Charts() {
        super();
        // TODO Auto-generated constructor stub
    }
public void init() {
	countryImp = new CountryCrudImp();
}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getServletPath();
		if(url == "/displayCountryChart") {
			showCountryChart(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void showCountryChart(HttpServletRequest request, HttpServletResponse response) {
		List<Map<Object, Object>> countryList = new ArrayList<Map<Object, Object>>();
		countryList = countryImp.getCountryData();
		String data = null;
		Gson g = new Gson();
		data = g.toJson(countryList);
		
	}

}
