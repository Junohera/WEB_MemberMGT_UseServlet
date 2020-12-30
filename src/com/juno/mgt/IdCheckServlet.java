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
 * Servlet implementation class IdCheckServlet
 */
@WebServlet("/idcheck.do")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid"); // 검사할 아이디 전송받음
		MemberDAO memberDAO = MemberDAO.getIst();
		int result = memberDAO.confirmId(userid); // userid를 보내고 아이디 사용가능을 정수형으로 반환
		request.setAttribute("userid", userid);
		request.setAttribute("result", result);
		RequestDispatcher dp = request.getRequestDispatcher("member/idcheck.jsp");
		dp.forward(request, response); // 팝업창에 표시할 페이지 포워딩
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
