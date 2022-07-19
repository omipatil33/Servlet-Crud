import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class List extends HttpServlet 
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<body>");

        out.println("<center><a href='Add'>Add New Student</a><br></center>");

       Connection con=null;
       int sno=0;

       try
         {
           Class.forName("oracle.jdbc.driver.OracleDriver");
           con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "System", "javapatil"); 

           String query="Select * from student order by sno";

           Statement stmt=con.createStatement();
           ResultSet rs=stmt.executeQuery(query);

           out.println("<table border='1' align='center'>");
           out.println("<tr>");
           out.println("<th>Student No</th>");
           out.println("<th>Student Name</th>");
           out.println("<th>Student Age</th>");
           out.println("<th>Update</th>");
           out.println("<th>Delete</th>");
           out.println("</tr>");

           while(rs.next()) 
             {
               sno=rs.getInt("sno");

               out.println("<tr>");
               out.println("<td>" + sno + "</td>");
               out.println("<td>" + rs.getString("sname") + "</td>");
               out.println("<td>" + rs.getInt("age") + "</td>");
               out.println("<td><a href='Update?sno=" + sno + "'>Update</a></td>");
               out.println("<td><a href='Delete?sno=" + sno + "'>Delete</a></td>");
               out.println("</tr>");
             }

           out.println("</table>");

           rs.close();
           stmt.close();
           con.close();
         }
       catch(Exception e)
         {
           System.out.println(e);
         }

        out.println("</body>");
        out.println("</html>");
    }

}



