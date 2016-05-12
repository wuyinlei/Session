package com.yinlei.car;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yinlei.car.bean.Book;
import com.yinlei.car.bean.BookUtils;

/**
 * Servlet implementation class ShowAllBookServlet
 * 
 */
@WebServlet("/ShowAllBookServlet")
public class ShowAllBookServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.write("��վ�����º��飺<br>");
		// 1����ʾ���е��鼮
		// ��ȡ���е���ļ���
		Map<String, Book> map = BookUtils.getAllBook();
		// ��������
		for (Map.Entry<String, Book> entry : map.entrySet()) {
			// �õ�ÿһ�����id
			String id = entry.getKey();
			// �õ�ÿһ����
			Book book = entry.getValue();

			// ����������
			out.write(book.getBookName() + "&nbsp;&nbsp;<a href = '" + request.getContextPath()
					+ "/ShowBookDetailServlet?id=" + id +"'>��ʾ��ϸ��Ϣ</a><br>");

		}
		out.write("<br><br><br><br>");

		// �ṩ�鿴���ӹ��ﳵ������
		out.write("<a href = '" + request.getContextPath() + "/CartServlet'>�鿴���ﳵ</a>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
