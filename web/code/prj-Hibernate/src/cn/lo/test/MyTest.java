package cn.lo.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.lo.beans.Student;

public class MyTest {
	@Test
	public void test(){
		//1.�����������ļ�
		Configuration configure = new Configuration().configure();
		//2.����session����
		SessionFactory sessionFactory = configure.buildSessionFactory();
		//3.��ȡsession
		Session session = sessionFactory.getCurrentSession();
		try {
			//4.��������
			session.beginTransaction();
			//5.����
			Student student = new Student("Lucy",16,90.5);
			session.save(student);
			//6�ύ����
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
}
