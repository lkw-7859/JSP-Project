package controller;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReviewDAO;
import vo.ReviewVO;

public class ReviewInsertController  implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) 
			throws ServerException, IOException, ServletException {
		
		// 해당 영화에 대한 후기 작성
		// 파라미터값 추출
		String id = req.getParameter("userId");
		String txt = req.getParameter("txt");
		int movieNo = Integer.parseInt(req.getParameter("movieNo"));
		
		// DB 연동(후기 등록)
		ReviewDAO instance = ReviewDAO.getInstance();
		instance.insertReview(txt, id, movieNo);
		
		// 해당 영화의 movieNo를 쿼리스트링
		String dis = "/reviewList.do?movieNo=" + movieNo;
		req.getRequestDispatcher(dis).forward(req, resp);
	}

}
