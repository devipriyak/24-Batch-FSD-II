package com.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProcessRegisterServlet
 */
@WebServlet("/ProcessRegisterServlet")
public class ProcessRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String username,branch;
       private static final String URL =
               "jdbc:oracle:thin:@localhost:1521:xe";

       private static final String USER = "system";

       private static final String PASSWORD = "manager";
       PrintWriter out=null;

		/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		out=response.getWriter();
		//read input from the name textfield
		    username= request.getParameter("Name");
		    branch=request.getParameter("branch");
		    out.println("my name is"+username);
		    out.println("branch is"+branch);
		    String sql = "INSERT INTO STUDENT11 " +
                    "(username, branch) " +
                    "VALUES (?, ?)";

		//load driver
		    Class.forName("oracle.jdbc.driver.OracleDriver");
		    out.println("Driver loading sucess");

     
		Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
	 
		PreparedStatement ps = con.prepareStatement(sql);


        ps.setString(1, username);
        ps.setString(2, branch);
        
        int result = ps.executeUpdate();


        if (result > 0) {

            out.println("<h2>Registration Successful!</h2>");

            out.println("<p>Student details inserted successfully.</p>");
            out.println("<h3>Student Details:</h3>");

            out.println("<p><b>USERNAME</b> " +username+ "</p>");
            out.println("<p><b>BRANCH:</b> " + branch+ "</p>");
           


		}}
		catch (Exception e) {
		// TODO Auto-generated catch block
			out.println(e);
		e.printStackTrace();
	}

		
	}

}
