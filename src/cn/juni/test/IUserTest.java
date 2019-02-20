package cn.juni.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.juni.dao.IUser;
import cn.juni.pojo.User;

public class IUserTest {
	private SqlSessionFactory factory;
	private SqlSession session;
	
	@Test
	public void testLogin() {
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(inputStream);
			session = factory.openSession();
			IUser iUser = session.getMapper(IUser.class);
//			User user = iUser.login("Juni","7777777");
//			System.out.println(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
