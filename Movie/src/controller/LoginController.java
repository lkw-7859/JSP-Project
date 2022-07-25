package controller;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import vo.MemberVO;

public class LoginController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServerException, IOException, ServletException {
		// �Ķ���� ����
		String userId = req.getParameter("userId");
		String userPW = req.getParameter("userPW");

		// ��ȿ�� üũ
		if (userId.isEmpty() || userPW.isEmpty()) {
			req.setAttribute("error", "��� �׸��� �������� �Է����ֽñ� �ٶ��ϴ�.");
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
			return;
		}
		
		//DB ����
		MemberDAO instance = MemberDAO.getInstance();
		MemberVO vo = instance.memberLogin(userId, userPW);
		
		if(vo.getId() != null) {
			req.setAttribute("Auser", vo);
			req.getRequestDispatcher("/").forward(req, resp);
		}else {
			req.setAttribute("error", "���̵� �Ǵ� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
		}
	}

}
