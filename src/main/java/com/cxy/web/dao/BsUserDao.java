package com.cxy.web.dao;

import com.cxy.frame.orm.MyBatisDao;
import com.cxy.frame.plugin.Page;
import com.cxy.web.po.BsUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;



@Repository
public class BsUserDao extends MyBatisDao<BsUser> {

	public BsUser getUserByLoginName(String loginName){
		return this.getSqlSession().selectOne("getUserByLoginName", loginName);
	}
	
	public List<BsUser> getAll(){
		return this.getSqlSession().selectList("com.tonyj.myweb.po.BsUser.getAll");
	}
	
	public List<BsUser> findByUser(BsUser userInfo){
		return this.getSqlSession().selectList("findByUser",userInfo);
	}
	
	public Page selectPage(BsUser userInfo,Page page){
		return this.selectPage(userInfo,page,"selectPage");
	}
	
	public List<BsUser> getUserByLogin(Map map){
		return this.getSqlSession().selectList("getUserByLogin",map);
	}
	
	public void insertUser(BsUser user){
		this.insert(user);
	}
	
	public BsUser getUserById(Integer id){
		return this.selectSingle(id);
	}
	
	public void updatePwd(Map map){
		this.getSqlSession().update("updatePassWord", map);
	}
}
