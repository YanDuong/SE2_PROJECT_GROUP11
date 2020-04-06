<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
 
<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Object> list = new ArrayList<>();
List<Map<Object,Object>> listData = new ArrayList<Map<Object,Object>>();
String dataPoints = null;
Class.forName("com.mysql.cj.jdbc.Driver"); 
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/corona_database", "root", "12345678");
PreparedStatement ps = null;
try{
	ps = conn.prepareStatement("select * from country");

	
	ResultSet rs = ps.executeQuery();
	
	while(rs.next()){
		int x =Integer.parseInt( rs.getString("countryId"));
		int totalOfInfected = Integer.parseInt(rs.getString("totalOfInfected"));
		
		int totalOfRecovered = Integer.parseInt(rs.getString("totalOfRecovered"));
		
		int totalOfDeaths = Integer.parseInt(rs.getString("totalOfDeaths"));
		
		int updatedInfected = Integer.parseInt(rs.getString("updatedInfected"));
		
		int updatedRecovered = Integer.parseInt(rs.getString("updatedRecovered"));
		
		int updatedDeaths = Integer.parseInt(rs.getString("updatedDeaths"));
		
		list = Arrays.asList(totalOfInfected, totalOfRecovered, totalOfDeaths, updatedInfected, updatedRecovered, updatedDeaths);
		map = new HashMap<Object,Object>(); 
		map.put("x", x); 
		map.put("y", list); 
		listData.add(map);
		dataPoints = gsonObj.toJson(listData);
		
	} 
	
}catch(SQLException e) {
		out.println("<div style = 'width: 50%; margin-left: auto, margin-right: auto; margin-top: 200px;'>Failed to connect to database.</div>");
		dataPoints = null;
	} finally {
		if(ps != null) {
			try {
				ps.close();
			} catch(SQLException e) {}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {}
		}
}

%>
 
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
window.onload = function() { 
 
<% if(dataPoints != null) { %>
var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,
	exportEnabled: true,
	title: {
		text: "JSP Column Chart from Database"
	},
	data: [{
		type: "column",
		dataPoints: <%out.print(dataPoints);%>
	}]
});
chart.render();
<% } %> 
 
}
</script>
</head>
<body>
<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>          