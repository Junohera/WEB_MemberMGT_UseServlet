package com.juno.mgt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.juno.dao.MemberDAO;
import com.juno.dto.MemberDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 페이지 화면 로딩(포워딩)
		
		String url = "member/loginForm.jsp"; // 포워딩될 페이지의 경로 설정
		HttpSession session = request.getSession(); // 현재 웹사이트의 세션객체 얻어옵니다.
		
		// loginUser 세션값이 널이 아니라면, (누군가 로그인되어 있는 상태라면)
		if (session.getAttribute("loginUser") != null) {
			url = "main.jsp"; // 세션을 통해 기로그인유저로 판단될 경우, 포워딩될 url을 메인으로 변경
		}

		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 실제 입력된 아이디 비번을 이용한 로그인 처리
		String url = "member/loginForm.jsp"; // 로그인 실패(아이디 비번오류)했을 때 포워딩할 경로
		HttpSession session = request.getSession();

		/** 1. parameter를 전달받습니다. request.getParameter()이용 */
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");

		/** 2. 데이터베이스에서 전달받은 아이디로 검색한 회원정보를 얻어옵니다. */
		MemberDTO member = null; // 조회한 회원의 각 필드를 담을 dto
		MemberDAO memberDAO = MemberDAO.getIst(); // 데이터베이스 조회를 위한 객체
		member = memberDAO.selectMember(userid);// 없는 아이디면 member는 null
		
		// 3. 회원정보 중 패스워드를 전달받은 파라미터와 비교합니다.
		// 4. 맞으면 로그인, 틀리면 다시 로그인폼으로 돌아갑니다.
		if (member == null) {
			request.setAttribute("message", "id not found");
		} else {
			if (member.getPwd() != null) {
				if (!member.getPwd().equals(pwd)) {
					request.setAttribute("message", "password diff");
				} else {
					session.setAttribute("loginUser", member); // 멤버정보를 세션에 저장
					url = "main.jsp";
				}
			} else {
				request.setAttribute("message", "invalid user info. call to admin");
			}
		}

		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}

}
