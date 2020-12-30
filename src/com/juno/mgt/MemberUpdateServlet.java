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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/update.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// updateForm.jsp로 포워딩
		String url = "member/updateForm.jsp";
		String userid = request.getParameter("userid");
		MemberDAO dao = MemberDAO.getIst();
		MemberDTO member = dao.selectMember(userid);
		request.setAttribute("member", member);
		
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") == null) {
			request.setAttribute("message", "session expired. please login again");
			url = "member/loginForm.jsp";
		}
		
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MemberDTO member = new MemberDTO();
		member.setName(request.getParameter("name"));
		member.setUserid(request.getParameter("userid"));
		member.setPwd(request.getParameter("pwd"));
		member.setEmail(request.getParameter("email"));
		member.setPhone(request.getParameter("phone"));
		member.setAdmin(Integer.parseInt(request.getParameter("admin")));

		MemberDAO dao = MemberDAO.getIst();
		int result = dao.update(member);

		if (result == 1) {
			request.setAttribute("message", "update complete");
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", member); // 세션 최신정보로 갱신
		} else {
			request.setAttribute("message", "update fail");
		}

		RequestDispatcher dp = request.getRequestDispatcher("main.jsp");
		dp.forward(request, response);
	}

}
