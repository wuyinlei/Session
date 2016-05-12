package com.yinlei;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletSession1 ��ʾ����session
 */
@WebServlet("/ServletSession1")
public class ServletSession1 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * getSession()�����Ĺ���:
		 * <li>1���鿴�ͻ����Ƿ�Я����JSESSIONIDCookie
		 * <li>2�����û��Я��������һ���µ�session���󣬲�����һ��Ψһ��ID���͵�
		 * <li>�ͻ��ˣ��洢���ͻ��˵Ļ����У����Я���ˣ���ô���������Cookie��ֵ(id)
		 * <li>��ô�ͻ�ȥ����˵��ڴ���Ѱ�����session������ҵ����򷵻����sessionΪ
		 * <li>�ͻ��˷�������Ҳ������ʹ����µ�session���󣬲�����һ��Ψһ��ID���͵�
		 * <li>�ͻ��ˣ��洢���ͻ��˵Ļ�����
		 */
		HttpSession session = request.getSession();
		String id = session.getId();

		System.out.println("id" + id);
		session.setAttribute("name", "���޼ɰ�������");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
