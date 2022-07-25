package controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import dao.MovieDAO;
import vo.MemberVO;
import vo.TicketVO;

public class TicketInsertController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServerException, IOException, ServletException {
		// 파파미터 추출
		int schNo = Integer.parseInt(req.getParameter("schNo"));
		int roomNo = Integer.parseInt(req.getParameter("roomNo"));
		String seatNo = req.getParameter("seatNo");
		String user = req.getParameter("user");
		
		String arr[] = seatNo.split(",");

		for(int i = 0; i < arr.length; i++) {
			// VO객체에 데이터 바인딩
			MovieDAO instance = MovieDAO.getInstance();
			
			TicketVO vo = instance.setTicketInfo(schNo,roomNo);
			vo.setSchNo(schNo);
			vo.setSeatNo(Integer.parseInt(arr[i]));
			vo.setRoomNo(roomNo);
			vo.setId(user);
			
			int n = instance.ticketBuy(vo);
			
			if(n > 0) {
				if(i == arr.length -1) {
					req.setAttribute("buyTicket", 1);
					req.getRequestDispatcher("/view/buyTicket.jsp").forward(req, resp);	
				}
			}else {
				req.setAttribute("error", "예매 실패입니다.");
				req.getRequestDispatcher("/").forward(req, resp);
			}
		}
	}

}
