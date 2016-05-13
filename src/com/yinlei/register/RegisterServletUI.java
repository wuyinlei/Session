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
//此servlet的目的是产生页面，同时提供一个唯一的口令：token
@WebServlet("/RegisterServletUI")
public class RegisterServletUI extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8") ;
		response.setContentType("text/html;charset=UTF-8") ;
		PrintWriter out = response.getWriter() ;
		
		//产生一个唯一的值
		String token = "" ;
		
		//token = System.nanoTime() + "" ;
		//采用第三种方式:数据指纹
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
		
		//由于数据指纹中可能存在特殊字符，因此再次进行编码
		//将口令存入session
 		request.getSession().setAttribute("stoken", token) ;
		
		//拿到错误信息显示
		String error = (String) request.getAttribute("error") ;
		if(error !=null){
			out.write(error) ;
		}
		
		//创建页面
		out.write("<form action = '" + request.getContextPath()+"/ResiterServlet' method = 'post'>") ;
		out.write("姓名：<input type = 'text'  name = 'username'> <br>") ;
		out.write("<input type = 'hidden'  name = 'ftoken' value = '" + token + "'> <br>") ;
		out.write("<input type = 'submit'  value = '注册'> <br>") ;
		out.write("</form>") ;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
