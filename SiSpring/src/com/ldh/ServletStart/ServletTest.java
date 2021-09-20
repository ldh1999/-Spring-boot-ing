package com.ldh.ServletStart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ServletTest  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletTest() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        com.ldh.start.SiStart.start("com.ldh.servletTest");
    }
}
