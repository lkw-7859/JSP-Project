package controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;
import vo.MovieVO;
import vo.ScheduleVO;

public class MovieInfoController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException, ServletException {		
		
			// 영화 info 
			// movieNo 파라미터 추출
			if (req.getParameter("movieNo") != null) {
				int movieNo = Integer.parseInt(req.getParameter("movieNo"));
			
			//DB 연동(movieNo에 따른 영화 리스트 / 영화 스케줄)
			MovieDAO instance = MovieDAO.getInstance();
			MovieVO movieGetList = instance.movieInfo(movieNo);
			ArrayList<ScheduleVO> scheduleAList = instance.scheduleAList(movieNo);
			
			if(scheduleAList != null) {
				req.setAttribute("movieGetList", movieGetList);
				req.setAttribute("scheduleAList", scheduleAList);
				req.getRequestDispatcher("/view/movieInfo.jsp").forward(req, resp);	
			}
		}		
	}

}
