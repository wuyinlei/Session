package com.yinlei.car.bean;


import java.util.HashMap;
import java.util.Map;

import com.yinlei.car.bean.Book;



public class BookUtils {

	private static Map<String,Book> map = new HashMap<String,Book>() ;
	
	static{
		map.put("1", new Book("1","��������","��������",100,"�����񹦣����������û�����")) ;
		map.put("2", new Book("2","��а����","�¹�ϣ",80,"��������")) ;
		map.put("3", new Book("3","���μ�","��ж�",50,"һȺС���ӵĹ���")) ;
		map.put("4", new Book("4","ˮ䰴�","ʩ����",90,"����Ů�˺�105�����˵Ĺ���")) ;
		map.put("5", new Book("5","�����","����",70,"�úÿ���������������")) ;
		map.put("6", new Book("6","�������","��ӹ",100,"���춯�صĿ�������")) ;
		map.put("7", new Book("7","��¥��","��«��",60,"���˵����롣������������")) ;
	}
	//��ȡ���е���
	public  static Map<String,Book> getAllBook(){
		return map ;
	}
	
	//�������id��ȡıһ����
	public static Book getBookById(String id){
		return map.get(id) ;
	}
}
