package db.mapper;

import java.util.List;

import db.po.User;


public interface UserMapper {
	// 根据用户id查询用户信息
	public User findUserById(int id) throws Exception;

	// 根据用户名称查询用户信息
	public List<User> findUserByName(String username) throws Exception;

	// 插入对象
	public int insertUser(User user) throws Exception;

	// 删除
	public void deleteUser(int id) throws Exception;

	// 更新
	public void updateUser(User user) throws Exception;
	
}
