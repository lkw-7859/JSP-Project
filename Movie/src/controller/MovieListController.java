package controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;
import vo.MovieVO;

public class MovieListController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) 
			throws ServerException, IOException, ServletException {
		
		// 상영중인 영화 목록 리스트 
		// category 파라미터 추출(category=0 일때 전체 영화 목록)
		int category = 0;
		if (req.getParameter("cat_no") != null) {
			category = Integer.parseInt(req.getParameter("cat_no"));
		}
		
		//DB 연동(카테고리에 따른 영화 리스트)
		MovieDAO instance = MovieDAO.getInstance();
		ArrayList<MovieVO> movieList = instance.selectCategory(category);

		req.setAttribute("movieList", movieList);
		req.getRequestDispatcher("/view/movieList.jsp").forward(req, resp);
		
	}

}
