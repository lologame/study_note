package cn.lo.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.lo.beans.Student;

public class MyTest {
	@Test
	public void test(){
		//1.加载主配置文件
		Configuration configure = new Configuration().configure();
		//2.创建session工厂
		SessionFactory sessionFactory = configure.buildSessionFactory();
		//3.获取session
		Session session = sessionFactory.getCurrentSession();
		try {
			//4.开启事务
			session.beginTransaction();
			//5.操作
			Student student = new Student("Lucy",16,90.5);
			session.save(student);
			//6提交事务
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
}
