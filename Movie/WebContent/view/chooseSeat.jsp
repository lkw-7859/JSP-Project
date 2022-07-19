<%@page import="vo.TicketVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="vo.ScheduleVO"%>
<%@page import="vo.MovieVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ include file="../header.jsp" %>

<%
	if(user == null) {
		out.print("<script>alert('로그인 후 이용 가능합니다.'); location.href = '/view/login.jsp';</script>");
		return;
	}

	ArrayList<TicketVO> ticketList = new ArrayList<>();

	int schNo = (int)request.getAttribute("schNo");
	
	if(request.getAttribute("ticketList") == null) {
		response.sendRedirect("/");
	}else {
		ticketList = (ArrayList) request.getAttribute("ticketList");
	}
%>

<div class="ui container">
    <div class="visual">
        <img src="/images/logo.jpg" alt="visualImg">
    </div>

    <div class="main" style="display: flex; justify-content: center; align-items: center; flex-direction: column;">
        <h2>좌석 선택하기</h2>
        
        <div class="seatBox">
        <%
        for(int i = 1; i <= 25; i++) {
        	boolean status = false;
        	
        	for (TicketVO item : ticketList) {
        		if(item.getSeatNo() == i) {
        			status = true;
        		}
        	}
        	
        	if(status == true) {
        %>
        	<button class="ui pink basic button" disabled><%= i %></button>
        <%
        	}else {
       	%>
       		<button class="ui blue basic button" data-seatno="<%=i %>"><%= i %></button>
       	<%
        	}
        	
        	if(i % 5 == 0) {
        %>
        	<br>
        <%
        	}
        }        
        %>
        </div>
        
        <div style="text-align: right;">
        	<button class="ui purple basic button submitBtn">예매하기</button>
        </div>
	</div>
</div>

<script>
	let arr = [];
	
	$(".seatBox > button").on("click", function() {		
		$(this).toggleClass("basic");
		
		if(arr.indexOf($(this).data("seatno")) == -1) {
			arr.push($(this).data("seatno"));	
		}else {
			arr.splice(arr.indexOf($(this).data("seatno")), 1);  
		}
	});
	
	$(".submitBtn").on("click", function() {
		if(arr.length <= 0) {
			swal("오류", "좌석이 선택되지 않았습니다.", "error");
			return;
		}else {
			location.href = "ticketBuy.do?schNo="+ <%=schNo%> +"&seatNo=" + arr + "&user=<%=user.getId()%>";
		}
	});
</script>

<%@ include file="../footer.jsp" %>