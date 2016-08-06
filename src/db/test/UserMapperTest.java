package db.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import db.mapper.UserMapper;
import db.po.User;

public class UserMapperTest {

	private SqlSessionFactory sqlSessionFactory;
	
	//创建工厂
	@Before
	public void init() throws IOException {
		
		//配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		
		//创建会话工厂
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	
	@Test
	public void testFindUserById() throws Exception{
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User user = userMapper.findUserById(30);
		sqlSession.close();
		System.out.println(user);
	}
	
	@Test
	public void testFindUserByName() throws Exception{
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		List<User> users = userMapper.findUserByName("小明");
		sqlSession.close();
		for (User user : users) {
			System.out.println(user);
		}
		
	}
	
	@Test
	public void testInsertUser() throws Exception{
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		//插入对象
		User user = new User();
		user.setUsername("老li");
		
		int count = userMapper.insertUser(user);
		System.out.println(count);
		sqlSession.commit();
		sqlSession.close();
	}
	
	
}
















