package com.yinlei.car;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.ws.util.NoCloseOutputStream;
import com.yinlei.car.bean.Book;
import com.yinlei.car.bean.BookUtils;

/**
 * Servlet implementation class BuyServlet
 * <li>��Ҫ����鼮�ŵ����ﳵ
 */
@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// ��ȡҳ�洫�ݵ�id
		String id = request.getParameter("id");
		// Ȼ�����id�ҵ�book����
		Book book = BookUtils.getBookById(id);

		// �õ�session
		HttpSession session = request.getSession();

		// ���������뵽session�е�һ��������
		@SuppressWarnings({ "unchecked", "unused" })
		List<Book> list = (List<Book>) session.getAttribute("carlist");

		if (list == null) {
			// ˵����һ�ι��򣬴�ʱsession���ǲ�����list���ϵģ���Ҫ�ֶ��Ĵ���
			list = new ArrayList<>();
			// ͬʱ��������鼮����
			book.setCount(1);
			list.add(book);

			// ��list�洢��session��
			session.setAttribute("carlist", list);
		} else {
			// �Ѿ�������鼮��
			// �жϹ��ﳵ���Ƿ��Ѿ����������
			int index = list.indexOf(book);
			if (index == -1) {
				// û������Ȿ��
				book.setCount(1);
				list.add(book);
			} else {
				// ˵�������һ����
				list.get(index).setCount(list.get(index).getCount() + 1);
			}

			// �����û��������Ѿ����빺�ﳵ��
			out.write(book.getBookName() + "�Ѿ����뵽���ﳵ�У�2���ת����ҳ"
					+ "<a href = '"+request.getContextPath()+"/ShowAllBookServlet'>�����ת</a>");

			response.setHeader("Refresh", "4;url=" + request.getContextPath() +"/ShowAllBookServlet");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
