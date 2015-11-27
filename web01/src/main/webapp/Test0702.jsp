<%-- JSP 전용 태그(JSP Action 태그)
=> JSP 엔진이 서블릿 콘텐츠를 만들 때 특정 자바 코드를 변환하여 생성한다.
=> 클라이언트에게 보내는 콘텐츠가 아니다.

    <jsp:include>
    . RequestDispatcher의 include() 호출 코드로 바뀜
    
    <jsp:forward>
    . RequestDispatcher의 forward() 호출 코드로 바뀜
--%>
<%@ page language="java" 
		contentType="text/plain; charset=UTF-8"
    	pageEncoding="UTF-8"
    	trimDirectiveWhitespaces="true"%>
   
 템플릿 데이터1
 <% if (request.getParameter("param1") != null) {
 Thread.currentThread().sleep(2000);%>
 <jsp:forward page="/Test07forward.jsp"/>
 <%}%>
 템플릿 데이터2   	
