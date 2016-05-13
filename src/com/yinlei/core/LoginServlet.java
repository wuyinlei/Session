package com.yinlei.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 拿取页面传递过来的信息
		String username = request.getParameter("name");
		String pass = request.getParameter("password");
		String code = request.getParameter("code");

		// System.out.println("username----》" + username);

		// System.out.println("code:" + code);
		// 先验证验证码
		if (code == "") {

			out.write("验证码不能为空，2秒后转向登录页面");
			response.setHeader("Refresh", "2;url=" + request.getContextPath() + "/login.html");

			// response.sendRedirect("login.html");
			return;
		} else {
			HttpSession session = request.getSession();
			// 从session中获取session
			String scode = (String) session.getAttribute("scode");

			// System.out.println("scode : " +scode);

			if (!code.equals(scode)) {
				// 请求重定向
				// request.getRequestDispatcher("/login.html").forward(request,
				// response);
				out.write("验证码错误，2秒后转向登录页面");
				response.setHeader("Refresh", "2;url=" + request.getContextPath() + "/login.html");

				return;
			}
			// 验证码正确
			if ("小白".equals(username) && "123".equals(pass)) {
				// 将用户名和密码存入session
				session.setAttribute("username", username);
				request.getRequestDispatcher("MainServlet").forward(request, response);

			} else {
				// request.setAttribute("error", "用户名或者密码错误");
				out.write("用户名或者密码错误，2秒后转向登录页面");
				response.setHeader("Refresh", "2;url=" + request.getContextPath() + "/login.html");
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
