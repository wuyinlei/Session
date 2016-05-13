package com.yinlei.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//���ע��Ĺ���
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8") ;
		response.setContentType("text/html;charset=UTF-8") ;
		PrintWriter out = response.getWriter() ;
		
		//�õ�ҳ�洫�ݵ�����
		String name = request.getParameter("username") ;
		String ftoken = request.getParameter("ftoken") ;
		System.out.println("ҳ�棺 " + ftoken);
		
		//�ж���Ϣ����ע��
		if("".equals(name)){
			//����ע��ҳ��\
			request.setAttribute("error", "�û���������д") ;
			request.getRequestDispatcher("RegisterServletUI").forward(request, response) ;
		}else{
			//��ע���û���ʱ�����жϿ������һ���ٽ���ע�ᣬ��������ע��
			//ע�⣬����ɹ���Ҫ�������session��ɾ��
			//�õ�session�д洢�Ŀ���
			String stoken = (String) request.getSession().getAttribute("stoken") ;
			if(ftoken.equals(stoken)){
				out.write("�û��ѱ��浽���ݿ���") ;
				//ɾ������
				request.getSession().removeAttribute("stoken") ;
			}else{
				out.write("��Ҫ�ظ��ύ��2���ת��ע��ҳ��") ;
				response.setHeader("Refresh", "2;url=" + request.getContextPath() + "/RegisterServletUI") ;
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
