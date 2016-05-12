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
 * <li>查看购物车
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		// 如果用户直接在地址栏中输入地址查看购物车,那么此时这个session对象是新创建的
		if (session.isNew()) {
			// 返回主页面
			response.sendRedirect(request.getContextPath() + "/ShowAllBookServlet");
			return;
		}
		// 先从session中拿到购物车
		List<Book> list = (List<Book>) session.getAttribute("carlist");

		// 如果用户直接从主页面查看购物车过来，此时还没有购买任何书籍，提示用户回主页
		if (list == null) {
			// 首次访问购物车
			out.write("你好没有购买任何书籍，请返回到主界面中购买");
			request.getRequestDispatcher("ShowAllBookServlet").forward(request, response);

		}

		// 说明购买了书籍
		out.write("你购买的书籍如下");
		out.write("书名\t数量\t总价格");
		for (int i = 0; i < list.size(); i++) {
			Book book = list.get(i);
			out.write("<br>" + book.getBookName() + "\t" + book.getCount() + "\t" + book.getPrice() * book.getCount());
		}

		out.write("<br><br><a href = '" + request.getContextPath() + "/ShowAllBookServlet'>返回主页面</a>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
