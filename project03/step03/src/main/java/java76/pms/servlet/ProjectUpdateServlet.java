package java76.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import java76.pms.dao.ProjectDao;
import java76.pms.domain.Project;

public class ProjectUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    
    int no = Integer.parseInt(request.getParameter("no"));
    ApplicationContext iocContainer = 
        (ApplicationContext)this.getServletContext()
        .getAttribute("iocContainer");
    ProjectDao projectDao = iocContainer.getBean(ProjectDao.class);
    Project project = projectDao.selectOne(no);
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
   
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>프로젝트-상세정보</title>");
    out.println(" </head>");
    out.println("<body>");
    out.println("<h1>프로젝트 정보</h1>");
    
    if(project != null) {
      out.println("<form id='form2' action='update' method='post'>");
      out.println(" <table border='1'>");
      out.println("  <tr>");
      out.println("    <th>번호</th>");          
      out.printf("    <td><input type='text' name='no' value='%d' readOnly></td>\n", project.getNo());
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("    <th>제목</th>");          
      out.printf("    <td><input type='text' name='title' value='%s'></td>\n", project.getTitle());
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("    <th>시작일</th>");      
      out.printf("    <td><input type='text' name='startDate' value='%s' readOnly></td>\n", project.getStartDate());
      out.println("  </tr>");
      out.println("  <tr>");
      out.println("    <th>종료일</th>");          
      out.printf("    <td><input type='text' name='endDate' value='%s' readOnly></td>\n", project.getEndDate());
      out.println("  </tr>");
      out.println("<tr>");
      out.println("  <th>멤버</th>");
      out.printf("  <td><input type='text' name='member' value='%s'></td>\n", project.getMember());
      out.println("</tr>");
      out.println(" </table>"); 
      
      out.println("<p>");
      out.println("<button name='update' type='submit'>변경</button>");
      out.println("<button name='delete' type='submit' onclick='deleteProject()'>삭제</button>");
      out.println("</p>");
      
      out.println(" </form>");
    } else {
      out.println("<p>해당 번호의 프로젝트를 찾을 수 없습니다.</p>");
    }
    RequestDispatcher rd = request.getRequestDispatcher("/copyright");
    rd.include(request, response); 
   
    out.println(" <script>");
    out.println(" function deleteProject() {");
    out.println(" alert('삭제');");
    out.println("   document.getElementById('form2').action = 'delete';");
    out.println(" }");
    out.println(" </script>");
    
    out.println(" </body>");
    out.println("</html>");
  }
  //POST 요청이 호출되면 - 해당 게시물 변경
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    try {      
      Project project = new Project();
      project.setNo(Integer.parseInt(request.getParameter("no")));
      project.setTitle(request.getParameter("title"));
      project.setStartDate(Date.valueOf(request.getParameter("startDate")));
      project.setEndDate(Date.valueOf(request.getParameter("endDate")));
      project.setMember(request.getParameter("member"));

      
      ApplicationContext iocContainer = 
          (ApplicationContext)this.getServletContext()
                                  .getAttribute("iocContainer");
      ProjectDao projectDao = iocContainer.getBean(ProjectDao.class);

      if (projectDao.update(project) > 0) {
        response.sendRedirect("list");
        return;
      } 
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("해당 프로젝트가 존재하지 않거나 암호가 맞지 않습니다.");     

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>프로젝트-변경</title>");
        out.println(" </head>");
        out.println("<body>");
        out.println("<h1>프로젝트 변경 오류</h1>");
        out.println("<p>해당 게시물이 존재하지 않거나 암호가 맞지 않습니다</p>");
        
      RequestDispatcher rd = request.getRequestDispatcher("/copyright"); 
      //서블릿 URL - web.xml
      rd.include(request, response);
      
      out.println(" </body>");
      out.println("</html>");
      
      response.setHeader("Refresh", "2; url=list");
      
    } catch (Exception e) {
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      request.setAttribute("error", e); //error 정보를 ErrorServlet에게 전달한다
      rd.forward(request, response);
    }
  }
  
}



