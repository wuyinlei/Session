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

		// ��ȡҳ�洫�ݹ�������Ϣ
		String username = request.getParameter("name");
		String pass = request.getParameter("password");
		String code = request.getParameter("code");

		// System.out.println("username----��" + username);

		// System.out.println("code:" + code);
		// ����֤��֤��
		if (code == "") {

			out.write("��֤�벻��Ϊ�գ�2���ת���¼ҳ��");
			response.setHeader("Refresh", "2;url=" + request.getContextPath() + "/login.html");

			// response.sendRedirect("login.html");
			return;
		} else {
			HttpSession session = request.getSession();
			// ��session�л�ȡsession
			String scode = (String) session.getAttribute("scode");

			// System.out.println("scode : " +scode);

			if (!code.equals(scode)) {
				// �����ض���
				// request.getRequestDispatcher("/login.html").forward(request,
				// response);
				out.write("��֤�����2���ת���¼ҳ��");
				response.setHeader("Refresh", "2;url=" + request.getContextPath() + "/login.html");

				return;
			}
			// ��֤����ȷ
			if ("С��".equals(username) && "123".equals(pass)) {
				// ���û������������session
				session.setAttribute("username", username);
				request.getRequestDispatcher("MainServlet").forward(request, response);

			} else {
				// request.setAttribute("error", "�û��������������");
				out.write("�û��������������2���ת���¼ҳ��");
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
