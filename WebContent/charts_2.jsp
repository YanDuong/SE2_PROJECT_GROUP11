<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
 
<%
Gson gsonObj = new Gson();
String dataPoints1 = null;
String dataPoints2 = null;
String dataPoints3 = null;
String dataPoints4 = null;
String dataPoints5 = null;
String dataPoints6 = null;
List<Map<Object,Object>> list1 = new ArrayList<Map<Object, Object>>();
List<Map<Object,Object>> list2 = new ArrayList<Map<Object, Object>>();
List<Map<Object,Object>> list3 = new ArrayList<Map<Object, Object>>();
List<Map<Object,Object>> list4 = new ArrayList<Map<Object, Object>>();

List<Map<Object,Object>> list5 = new ArrayList<Map<Object, Object>>();
List<Map<Object,Object>> list6 = new ArrayList<Map<Object, Object>>();
Map<Object,Object> map1 = null;
Map<Object,Object> map2 = null;
Map<Object,Object> map3 = null;
Map<Object,Object> map4 = null;
Map<Object,Object> map5 = null;
Map<Object,Object> map6 = null;
Class.forName("com.mysql.cj.jdbc.Driver"); 
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/corona_database", "root", "12345678");
PreparedStatement ps = null;
try{
	
	ps = conn.prepareStatement("select * from countries");
	ResultSet rs = ps.executeQuery();
	String name;
	while(rs.next()){
		name = rs.getString("name");
		int totalOfInfected = rs.getInt("totalOfInfected");
		int totalOfRecovered =  rs.getInt("totalOfRecovered");
		int totalOfDeaths = rs.getInt("totalOfDeaths");
		int updatedInfected = rs.getInt("updatedInfected");
		int updatedRecovered = rs.getInt("updatedRecovered");
		int updatedDeaths = rs.getInt("updatedDeaths");
		 map1 = new HashMap<Object, Object>();
		 map2 = new HashMap<Object, Object>(); 
		map3 = new HashMap<Object, Object>();
		 map4 = new HashMap<Object, Object>();
		 map5 = new HashMap<Object, Object>();
		map6 = new HashMap<Object, Object>();		
		map1.put("label", name);
		map1.put("y", totalOfInfected);
		map2.put("label", name);
		map2.put("y",totalOfRecovered); 
		map3.put("label", name);
		map3.put("y", totalOfDeaths); 
		map4.put("label", name);
		map4.put("y", updatedInfected); 
		map5.put("label", name);
		map5.put("y", updatedRecovered); 
		map6.put("label", name);
		map6.put("y", updatedDeaths); 	
		list1.add(map1);
		list2.add(map2); 
		list3.add(map3);
		list4.add(map4);
		list5.add(map5);
		list6.add(map6);
		dataPoints1 = gsonObj.toJson(list1);
		 dataPoints2 = gsonObj.toJson(list2);
		 dataPoints3 = gsonObj.toJson(list3);
		 dataPoints4 = gsonObj.toJson(list4);
		 dataPoints5 = gsonObj.toJson(list5);
		 dataPoints6 = gsonObj.toJson(list6);			
	} 		
}catch(SQLException e) {
		e.printStackTrace();
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
 
<% if(dataPoints1 != null || dataPoints2 != null || dataPoints3 != null ||dataPoints4 != null||dataPoints5 != null || dataPoints6 != null ) { %>
var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,
	exportEnabled: true,
	title: {
		text: "Countries Charts"
	},
	
	data: [{
		type: "column",
		name: "TotalOfInfected",
		showInLengend:true,
		dataPoints: <%out.print(dataPoints1);%>
		
	} , {
		type: "column",
		name: "TotalOfRecovered",
		showInLengend:true,
		dataPoints: <%out.print(dataPoints2);%>
	}, {
		type: "column",
		name: "TotalOfDeaths",
		showInLengend:true,
		dataPoints: <%out.print(dataPoints3);%>
	}, {
		type: "column",
		name: "updatedInfected",
		showInLengend:true,
		dataPoints: <%out.print(dataPoints4);%>
	}, {
		type: "column",
		name: "updatedRecovered",
		showInLengend:true,
		dataPoints: <%out.print(dataPoints5);%>
	}, {
		type: "column",
		name: "updatedDeaths",
		showInLengend:true,
		dataPoints: <%out.print(dataPoints6);%>
	}] 
});
chart.render();
<% } %> 
 
};
</script>
</head>
<body>
<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>          