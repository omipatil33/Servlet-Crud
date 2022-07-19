import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Update extends HttpServlet 
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

       int sno=Integer.parseInt(request.getParameter("sno"));
       String sname="";
       int age=0;

       Connection con=null;

       try
         {
           Class.forName("oracle.jdbc.driver.OracleDriver");
           con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "System", "javapatil"); 

           String query="Select * from student where sno=" + sno;

           Statement stmt=con.createStatement();
           ResultSet rs=stmt.executeQuery(query);

           if(rs.next()) 
             {
               sname=rs.getString("sname");             
               age=rs.getInt("age");             
             }

           rs.close();
           stmt.close();
           con.close();
         }
       catch(Exception e)
         {
           System.out.println(e);
         }


        out.println("<html>");
        out.println("<body>");

        out.println("<form action='Edit'>");        

        out.println("<table bgcolor='yellow' align='center'>");        

        out.println("<tr>");        
        out.println("<td>S. No: </td>");        
        out.println("<td>" + sno + "<input type='hidden' name='sno' value='" + sno + "'></td>");        
        out.println("</tr>");        

        out.println("<tr>");        
        out.println("<td>S. Name: </td>");        
        out.println("<td><input type='text' name='sname'  value='" + sname + "'></td>");        
        out.println("</tr>");       

        out.println("<tr>");        
        out.println("<td>Age: </td>");        
        out.println("<td><input type='text' name='age'  value='" + age + "'></td>");        
        out.println("</tr>");       

        out.println("<tr>");        
        out.println("<th colspan='1'><input type='Submit' value='Submit'></th>");        
        out.println("</tr>");        

        out.println("</table>");        

        out.println("</form>");        

        out.println("</body>");
        out.println("</html>");
    }

}



