import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class StartSpring extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StartSpring() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        com.ldh.start.SiStart.start("com.ldh.servletTest");
    }
}
