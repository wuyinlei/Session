package com.yinlei.car;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yinlei.car.bean.Book;

/**
 * Servlet implementation class CartSevlet
 * <li>�鿴���ﳵ
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		// ����û�ֱ���ڵ�ַ���������ַ�鿴���ﳵ,��ô��ʱ���session�������´�����
		if (session.isNew()) {
			// ������ҳ��
			response.sendRedirect(request.getContextPath() + "/ShowAllBookServlet");
			return;
		}
		// �ȴ�session���õ����ﳵ
		List<Book> list = (List<Book>) session.getAttribute("carlist");

		// ����û�ֱ�Ӵ���ҳ��鿴���ﳵ��������ʱ��û�й����κ��鼮����ʾ�û�����ҳ
		if (list == null) {
			// �״η��ʹ��ﳵ
			out.write("���û�й����κ��鼮���뷵�ص��������й���");
			request.getRequestDispatcher("ShowAllBookServlet").forward(request, response);

		}

		// ˵���������鼮
		out.write("�㹺����鼮����");
		out.write("����\t����\t�ܼ۸�");
		for (int i = 0; i < list.size(); i++) {
			Book book = list.get(i);
			out.write("<br>" + book.getBookName() + "\t" + book.getCount() + "\t" + book.getPrice() * book.getCount());
		}

		out.write("<br><br><a href = '" + request.getContextPath() + "/ShowAllBookServlet'>������ҳ��</a>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
