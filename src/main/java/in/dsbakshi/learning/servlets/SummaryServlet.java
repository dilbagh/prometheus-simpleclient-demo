package in.dsbakshi.learning.servlets;

import io.prometheus.client.Summary;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "wait-summary", loadOnStartup = 1)
public class SummaryServlet extends HttpServlet {

    private static Summary requestDuration;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        requestDuration = Summary.build()
                .name("request_duration_summary")
                .help("Time for HTTP request.")
                .quantile(0.95, 0.01)
                .register();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Summary.Timer timer = requestDuration.startTimer();

        long sleepDuration = Double.valueOf(Math.floor(Math.random() * 10 * 1000)).longValue();
        sleep(sleepDuration);

        timer.observeDuration();

        ServletOutputStream out = resp.getOutputStream();
        out.print(String.format("<h1>You waited for %s ms!</h1>", sleepDuration));
        out.flush();
    }

    private void sleep(long sleepDuration) {
        try {
            Thread.sleep(sleepDuration);
        } catch (InterruptedException e) {
        }
    }

}
