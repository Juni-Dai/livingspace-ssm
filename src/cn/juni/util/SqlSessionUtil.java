package cn.juni.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtil {
	
	private static SqlSessionFactory factory = null;
	private static InputStream inputStream=null;
	private static final String URL = "sqlMapperConfig.xml";
	
	static {
		try {
			inputStream = Resources.getResourceAsStream(URL);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ªÒ»°SqlSession
	 * @return
	 */
	public static SqlSession getSqlSession() {
		return factory.openSession();
	}
	
	/**
	 * πÿ±’SqlSession
	 * @param session
	 */
	public static void closeSqlSession(SqlSession session) {
		session.commit();
		session.close();
	}
}
