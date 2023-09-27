package util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.tomcat.util.file.ConfigurationSource.Resource;

public class MybatisSqlSessionFactory {
	//마이바티스를 사용할 java 클래스 
	
	private static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "resource/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static SqlSessionFactory getsqlSessionFactory() {
		return sqlSessionFactory;
	}
	

}
