package com.studywithus.web.chargestudy;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.StudyDao;
import com.studywithus.dao.StudyMemberDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;

@WebServlet("/chargestudy/add")
public class ChargeStudyAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao chargeStudyDao;
  StudyMemberDao studyMemberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
    chargeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
    studyMemberDao = (StudyMemberDao) servletContext.getAttribute("studyMemberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Study chargeStudy = new Study();
    Member writer = (Member) request.getSession(false).getAttribute("loginUser");

    chargeStudy.setTitle(request.getParameter("title"));
    chargeStudy.setArea(request.getParameter("area"));
    chargeStudy.setCategory(request.getParameter("category"));
    chargeStudy.setContent(request.getParameter("content"));
    chargeStudy.setMaxMembers(Integer.parseInt(request.getParameter("maxMembers")));
    chargeStudy.setPrice(Integer.parseInt(request.getParameter("price")));
    chargeStudy.setStartDate(Date.valueOf(request.getParameter("startDate")));
    chargeStudy.setEndDate(Date.valueOf(request.getParameter("endDate")));

    try {
      chargeStudyDao.insert(chargeStudy);
      studyMemberDao.insert(writer.getNo(), chargeStudy.getNo(), Study.OWNER_STATUS);
      sqlSession.commit();

      response.sendRedirect("list");

    } catch (Exception e) {
      sqlSession.rollback();
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}