package com.yinlei.register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//完成注册的功能
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8") ;
		response.setContentType("text/html;charset=UTF-8") ;
		PrintWriter out = response.getWriter() ;
		
		//拿到页面传递的数据
		String name = request.getParameter("username") ;
		String ftoken = request.getParameter("ftoken") ;
		System.out.println("页面： " + ftoken);
		
		//判断信息进行注册
		if("".equals(name)){
			//返回注册页面\
			request.setAttribute("error", "用户名必须填写") ;
			request.getRequestDispatcher("RegisterServletUI").forward(request, response) ;
		}else{
			//在注册用户的时候先判断口令，口令一致再进行注册，否则不允许注册
			//注意，保存成功后要将口令从session中删除
			//拿到session中存储的口令
			String stoken = (String) request.getSession().getAttribute("stoken") ;
			if(ftoken.equals(stoken)){
				out.write("用户已保存到数据库中") ;
				//删除口令
				request.getSession().removeAttribute("stoken") ;
			}else{
				out.write("不要重复提交，2秒后转向注册页面") ;
				response.setHeader("Refresh", "2;url=" + request.getContextPath() + "/RegisterServletUI") ;
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
