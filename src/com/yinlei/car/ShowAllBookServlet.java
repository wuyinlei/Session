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

		out.write("本站有以下好书：<br>");
		// 1、显示所有的书籍
		// 获取所有的书的集合
		Map<String, Book> map = BookUtils.getAllBook();
		// 遍历集合
		for (Map.Entry<String, Book> entry : map.entrySet()) {
			// 拿到每一本书的id
			String id = entry.getKey();
			// 拿到每一本书
			Book book = entry.getValue();

			// 输出书的名字
			out.write(book.getBookName() + "&nbsp;&nbsp;<a href = '" + request.getContextPath()
					+ "/ShowBookDetailServlet?id=" + id +"'>显示详细信息</a><br>");

		}
		out.write("<br><br><br><br>");

		// 提供查看链接购物车的链接
		out.write("<a href = '" + request.getContextPath() + "/CartServlet'>查看购物车</a>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
