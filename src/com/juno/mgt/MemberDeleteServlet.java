package com.juno.mgt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juno.dao.MemberDAO;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/delete.do")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터로 아이디 얻는다.
		String userid = request.getParameter("userid");
		
		// dao객체를 반환(싱글톤이 아니라면 생성, 싱글톤이라면 반환)
		MemberDAO dao = MemberDAO.getIst();
		
		// dao의 delete 메서드를 실행하고 결과를 1 또는 0으로 리턴
		int result = dao.delete(userid);

		// 최종 로그인페이지로 포워딩, delete메서드의 결과에 따라 메세지를 request에 담습니다.
		if (result == 1) {
			request.setAttribute("message", "delete complete");
		} else {
			request.setAttribute("message", "delete fail, Please try again later.");
		}
		
		RequestDispatcher dp = request.getRequestDispatcher("member/loginForm.jsp");
		dp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
