package com.cxy.web.dao;

import com.cxy.frame.orm.MyBatisDao;
import com.cxy.web.po.BsUserRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class BsAuthorityDao extends MyBatisDao<BsUserRole> {

	
	public void deleteRoleMenu(Map map){
		this.getSqlSession().delete("deleteRoleMenu",map);
	}
	
	public void deleteUserRole(Map map){
		this.getSqlSession().delete("deleteUserRole",map);
	}
	
	/**
	 * 保存用户角色
	 * @param map
	 * @
	 */
	public void saveUserRole(List list){
		this.getSqlSession().insert("saveUserRole",list);
	}
	
	/**
	 * 角色菜单
	 * @param map
	 * @
	 */
	public void saveRoleMenu(List list){
		this.getSqlSession().insert("saveRoleMenu",list);
	}
	
	/**
	 * 选择角色
	 * @param map
	 * @return
	 * @
	 */
	public List selectRole(Map map){
		return this.getSqlSession().selectList("selectRole",map);
	}
	
	/**
	 * 根据人员 角色加载菜单
	 * @param map
	 * @return
	 * @
	 */
	public List selectMenu(Map map){
		return this.getSqlSession().selectList("selectMenu",map);
	}
	
	public List selectRoleByUserId(Integer id){
		return this.getSqlSession().selectList("selectRoleByUserId",id);
	}
	
	public List selectResByRoleId(Integer id){
		return this.getSqlSession().selectList("selectResByRoleId",id);
	}
}
