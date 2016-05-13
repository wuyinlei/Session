package com.yinlei.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;
//��servlet��Ŀ���ǲ���ҳ�棬ͬʱ�ṩһ��Ψһ�Ŀ��token
@WebServlet("/RegisterServletUI")
public class RegisterServletUI extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8") ;
		response.setContentType("text/html;charset=UTF-8") ;
		PrintWriter out = response.getWriter() ;
		
		//����һ��Ψһ��ֵ
		String token = "" ;
		
		//token = System.nanoTime() + "" ;
		//���õ����ַ�ʽ:����ָ��
		token = UUID.randomUUID().toString() ;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5") ;
			byte[] bs = md.digest(token.getBytes()) ;
			BASE64Encoder base = new BASE64Encoder() ;
			token = base.encode(bs) ;
			System.out.println("session:" + token);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//��������ָ���п��ܴ��������ַ�������ٴν��б���
		//���������session
 		request.getSession().setAttribute("stoken", token) ;
		
		//�õ�������Ϣ��ʾ
		String error = (String) request.getAttribute("error") ;
		if(error !=null){
			out.write(error) ;
		}
		
		//����ҳ��
		out.write("<form action = '" + request.getContextPath()+"/ResiterServlet' method = 'post'>") ;
		out.write("������<input type = 'text'  name = 'username'> <br>") ;
		out.write("<input type = 'hidden'  name = 'ftoken' value = '" + token + "'> <br>") ;
		out.write("<input type = 'submit'  value = 'ע��'> <br>") ;
		out.write("</form>") ;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
