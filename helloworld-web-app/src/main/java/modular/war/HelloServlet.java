package modular.war;

import java.io.IOException;

import helloworld.provider.HelloWorldProvider;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // Very simple - just return some plain text
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                "Transitional//EN\">\n" +
                "<HTML>\n" +
                "<HEAD><TITLE>Hello World</TITLE></HEAD>\n" +
                "<BODY>\n" +
                "<H1>" + HelloWorldProvider.greeting() + "</H1>\n" +
                // print module names of our implementation classes: expected to be "helloworld.web.app" and
                // "helloworld.provider". However it is not the case when run on a regular Tomcat.
                "<H3>HelloServlet module: " + HelloServlet.class.getModule().getName() + "</H3>\n" +
                "<H3>HelloWorldProvider module: " + HelloWorldProvider.class.getModule().getName() + "</H3>\n" +
                "</BODY></HTML>");
    }
}
