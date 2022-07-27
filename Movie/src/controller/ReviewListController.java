package controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;
import dao.ReviewDAO;
import vo.MovieVO;
import vo.ReviewVO;
import vo.ScheduleVO;

public class ReviewListController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException, ServletException {		
		
		// movieNo 파라미터 추출
		if (req.getParameter("movieNo") != null) {
			int movieNo = Integer.parseInt(req.getParameter("movieNo"));
			/* int pageNo = Integer.parseInt(req.getParameter("pageNo")); */
		
		// DB 연동(movieNo에 대한 영화 정보 / 영화 리스트) 
		MovieDAO instance = MovieDAO.getInstance();
		ReviewDAO instance1 = ReviewDAO.getInstance();
		MovieVO movieGetList = instance.movieInfo(movieNo);
		ArrayList<ReviewVO> reviewList = instance1.reviewList(movieNo);
		int num = instance1.maxReviewNum(movieNo);
		
		
		if(reviewList != null) {
			req.setAttribute("mn", movieNo);
			req.setAttribute("num", num);
			req.setAttribute("movieGetList", movieGetList);
			req.setAttribute("list", reviewList);
			req.getRequestDispatcher("/view/reviewList.jsp").forward(req, resp);
		}
	}
}
}
