package com.yinlei.car;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yinlei.car.bean.Book;
import com.yinlei.car.bean.BookUtils;

/**
 * ��������������飺 1. ��ʾ�����ϸ��Ϣ 2. �ṩ��������� 3. ���ؼ�������������
 * 
 * @author Administrator
 *
 */
@WebServlet("/ShowBookDetailServlet")
public class ShowBookDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 1.��ʾ�����ϸ��Ϣ
		// �õ�ҳ�洫�ݵ�id
		String id = request.getParameter("id");
		// ����id��ȡ��
		Book book = BookUtils.getBookById(id);
		// ��ʾ��Ϣ
		out.write(book + "&nbsp;&nbsp;<br><a href = '" + request.getContextPath()
				+ "/ShowAllBookServlet'>������ҳ�������</a><br>" + "<a href = '" + request.getContextPath()
				+ "/BuyServlet?id=" + id + "'>���빺�ﳵ</a><br>");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
