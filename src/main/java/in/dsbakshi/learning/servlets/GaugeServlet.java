package in.dsbakshi.learning.servlets;

import io.prometheus.client.Gauge;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/queue", loadOnStartup = 1)
public class GaugeServlet extends HttpServlet {

    private static Gauge queueSize;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        queueSize = Gauge.build()
                .name("queue_size")
                .help("Size of queue.")
                .register();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("Push".equals(action)) {
            queueSize.inc();
        }

        if ("Pop".equals(action)) {
            queueSize.dec();
        }
        resp.sendRedirect("queue.html");
    }
}
