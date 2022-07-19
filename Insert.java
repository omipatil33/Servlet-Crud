import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Insert extends HttpServlet 
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

       int sno=Integer.parseInt(request.getParameter("sno"));
       String sname=request.getParameter("sname");
       int age=Integer.parseInt(request.getParameter("age"));

       Connection con=null;


       try
         {
           Class.forName("oracle.jdbc.driver.OracleDriver");
           con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "System", "javapatil"); 

           String query="Insert into student values(" + sno +",'" + sname + "'," + age + ")";

           Statement stmt=con.createStatement();
           stmt.executeUpdate(query);

           stmt.close();
           con.close();
         }
       catch(Exception e)
         {
           System.out.println(e);
         }

       response.sendRedirect("List");
    }

}



