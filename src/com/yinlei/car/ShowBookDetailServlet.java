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
 * 此类完成两件事情： 1. 显示书的详细信息 2. 提供购买的链接 3. 返回继续游览的链接
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

		// 1.显示书的详细信息
		// 拿到页面传递的id
		String id = request.getParameter("id");
		// 根据id获取书
		Book book = BookUtils.getBookById(id);
		// 显示信息
		out.write(book + "&nbsp;&nbsp;<br><a href = '" + request.getContextPath()
				+ "/ShowAllBookServlet'>返回主页继续浏览</a><br>" + "<a href = '" + request.getContextPath()
				+ "/BuyServlet?id=" + id + "'>放入购物车</a><br>");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
