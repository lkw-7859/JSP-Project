package controller;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;

public class DeleteMyTicketController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServerException, IOException, ServletException {
		String id = req.getParameter("user");
		int ticketNo = Integer.parseInt(req.getParameter("ticketNo"));
		int schNo = Integer.parseInt(req.getParameter("schNo"));
		if(ticketNo != 0) {
			MovieDAO instance = MovieDAO.getInstance();
			instance.cancelTicket(id,ticketNo,schNo);
		}
		
		req.getRequestDispatcher("/view/myTickets.jsp").forward(req, resp);
	}

}
