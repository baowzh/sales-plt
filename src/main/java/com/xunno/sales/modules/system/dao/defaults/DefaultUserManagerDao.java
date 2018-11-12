package com.xunno.sales.modules.system.dao.defaults;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asiainfo.eframe.sqlsession.DBSqlSession;
import com.asiainfo.eframe.sqlsession.model.DBPageValue;
import com.asiainfo.eframe.sqlsession.model.DBPagingPrams;
import com.xunno.sales.modules.system.dao.UserManagerDao;
import com.xunno.sales.modules.system.model.User;

@Repository
public class DefaultUserManagerDao implements UserManagerDao {

	@Autowired
	private DBSqlSession sqlSession;

	@Override
	public User getByLoginName(User user) {
		return sqlSession.getDataSingle(User.class, user);
	}

	@Override
	public List<User> findUserByOfficeId(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long findAllCount(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePasswordById(User user) {
		
		return this.sqlSession.update("updatePasswordById", user);
	}

	@Override
	public int updateLoginInfo(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUserRole(User user) {
		return sqlSession.del("delUserRole", user);
	}

	@Override
	public int insertUserRole(User user) {
		return sqlSession.insert(User.class, user);
	}

	@Override
	public int updateUserInfo(User user) {
		// TODO Auto-generated method stub
				return 0;
	}

	@Override
	public DBPageValue<User> findUser(DBPagingPrams page) {
		return sqlSession.pagingqueryData(User.class, page);
	}

	@Override
	public List<User> getUsers(User userInfo) {
		return sqlSession.getData(User.class, userInfo);
	}

	@Override
	public void deleteUser(User userInfo) {
		sqlSession.del(User.class, userInfo);
	}

	@Override
	public User findUserByStaffid(String staffid) {
		return sqlSession.getDataSingle("getUserByStaffid", staffid);
	}

}
