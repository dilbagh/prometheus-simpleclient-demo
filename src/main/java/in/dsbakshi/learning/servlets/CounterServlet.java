package in.dsbakshi.learning.servlets;

import io.prometheus.client.Counter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/hello", loadOnStartup = 1)
public class CounterServlet extends HttpServlet {

    public static Counter helloCounter;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        helloCounter = Counter.build()
                .name("request_count")
                .help("Number of hello requests")
                .register();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        helloCounter.inc();
        out.print("<h1>Hello World!</h1>");
        out.flush();
    }
}
