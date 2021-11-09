package com.studywithus.servlet.chargestudy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/chargestudy/deleterequestform")
public class ChargeStudyDeleteRequestFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/swu/user/loginform");
      return;
    }

    request.setAttribute("no", request.getParameter("no"));
    request.getRequestDispatcher("/chargestudy/DeleteRequestForm.jsp").forward(request, response);
  }
}