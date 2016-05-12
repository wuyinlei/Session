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
 * <li>将要买的书籍放到购物车
 */
@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 获取页面传递的id
		String id = request.getParameter("id");
		// 然后根据id找到book对象
		Book book = BookUtils.getBookById(id);

		// 拿到session
		HttpSession session = request.getSession();

		// 将这个书存入到session中的一个集合中
		@SuppressWarnings({ "unchecked", "unused" })
		List<Book> list = (List<Book>) session.getAttribute("carlist");

		if (list == null) {
			// 说明第一次购买，此时session中是不存在list集合的，需要手动的创建
			list = new ArrayList<>();
			// 同时将购买的书籍放入
			book.setCount(1);
			list.add(book);

			// 将list存储到session中
			session.setAttribute("carlist", list);
		} else {
			// 已经购买过书籍了
			// 判断购物车中是否已经购买过此书
			int index = list.indexOf(book);
			if (index == -1) {
				// 没有买过这本书
				book.setCount(1);
				list.add(book);
			} else {
				// 说明买过这一本书
				list.get(index).setCount(list.get(index).getCount() + 1);
			}

			// 提醒用户，此书已经放入购物车中
			out.write(book.getBookName() + "已经放入到购物车中，2秒后转向主页"
					+ "<a href = '"+request.getContextPath()+"/ShowAllBookServlet'>点击跳转</a>");

			response.setHeader("Refresh", "4;url=" + request.getContextPath() +"/ShowAllBookServlet");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
