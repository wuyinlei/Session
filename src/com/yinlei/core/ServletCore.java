package com.yinlei.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.regexp.internal.REDebugCompiler;
import com.sun.org.apache.regexp.internal.recompile;

/**
 * Servlet implementation class ServletCorw 输出验证码
 */
@WebServlet("/ServletCore")
public class ServletCore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		drawImage(request, response);

	}

	private void drawImage(HttpServletRequest request, HttpServletResponse response) {
		// 创建变量---->图片的宽和高
		int width = 200;
		int height = 30;
		// 创建一个内存图像
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		// 创建画笔
		Graphics graphics = image.getGraphics();
		// 指定边框颜色
		graphics.setColor(Color.red);
		// 画图像的边框
		graphics.drawRect(0, 0, width, height);
		// 填充矩形的背景色
		// 设定画笔的颜色
		graphics.setColor(Color.yellow);
		// 填充矩形的背景
		graphics.fillRect(1, 1, width - 2, height - 2);

		// 设置字体大小
		graphics.setFont(new Font("幼圆", Font.BOLD + Font.ITALIC, 24));

		Random r = new Random();
		/*
		 * // 干扰线 设置画笔颜色 graphics.setColor(Color.gray); // 画30条干扰线 for (int i =
		 * 0; i < 30; i++) { graphics.drawLine(r.nextInt(width),
		 * r.nextInt(height), r.nextInt(width), r.nextInt(height)); }
		 */

		// 设定画笔的颜色
		graphics.setColor(Color.blue);
		//
		String s = "北京欢迎你你可知道hellobeijing";
		// 先将汉字转换为unicode编码

		StringBuffer sb = new StringBuffer();

		s = "\u5317\u4EAC\u6B22\u8FCE\u4F60\u4F60\u53EF\u77E5\u9053hellobeijing";
		for (int i = 0; i < 4; i++) {
			char c = s.charAt(r.nextInt(s.length()));
			sb.append(c);
			// 产生 1 -1
			int flag = r.nextBoolean() ? 1 : -1;
			// 可以上下左右随机
			graphics.drawString(c + "", 20 + 40 * i + flag * r.nextInt(10), 20 + flag * r.nextInt(5));
		}

		// 随机产生4个汉字

		/*
		 * // 随机产生4个数字输出到页面 for (int i = 0; i < 4; i++) { int n = r.nextInt(10);
		 * // 10以内的数字 // 画到图像上 graphics.drawString(n + "", 20 + 40 * i, 20); }
		 */
		
		//System.out.println(sb.toString());
		// 将验证码存入sesstion
		request.getSession().setAttribute("scode", sb.toString());

		// 告诉客户端，不要缓存,这样每次刷新的时候就会去服务器请求新的数据
		response.setHeader("Expires", -1 + "");
		response.setHeader("Cache-control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// 将图片输出到客户端
		try {
			ImageIO.write(image, "jpg", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
