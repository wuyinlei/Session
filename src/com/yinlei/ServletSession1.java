package com.yinlei;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletSession1 演示创建session
 */
@WebServlet("/ServletSession1")
public class ServletSession1 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * getSession()方法的工作:
		 * <li>1、查看客户端是否携带了JSESSIONIDCookie
		 * <li>2、如果没有携带，创建一个新的session对象，并分配一个唯一的ID发送到
		 * <li>客户端，存储到客户端的缓存中，如果携带了，那么将根据这个Cookie的值(id)
		 * <li>那么就会去服务端的内存中寻找这个session，如果找到了则返回这个session为
		 * <li>客户端服务，如果找不到，就创建新的session对象，并分配一个唯一的ID发送到
		 * <li>客户端，存储到客户端的缓存中
		 */
		HttpSession session = request.getSession();
		String id = session.getId();

		System.out.println("id" + id);
		session.setAttribute("name", "张无忌爱赵敏？");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
