package com.cxy.web.service;


import com.cxy.frame.plugin.Page;
import com.cxy.web.annotation.SystemLogService;
import com.cxy.web.dao.BsRoleDao;
import com.cxy.web.po.BsRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BsRoleService {

	@Autowired
	private BsRoleDao bsRoleDao;
	
	@SystemLogService(description = "查询所有角色")
	public List<BsRole> getAll(){
		return bsRoleDao.getAll();
	}
	
	@SystemLogService(description = "分页查询角色")
	public Page selectListPage(BsRole bsRole, Page page){
		return bsRoleDao.selectListPage(bsRole,page);
	}
	
	@SystemLogService(description = "根据角色Id查询角色信息")
	public BsRole selectBsRoleByPk(Integer id){
		return bsRoleDao.selectBsRoleByPk(id);
	}
	
	@SystemLogService(description = "根据角色Id删除角色")
	public int deleteRoleByPk(Map map){
		return bsRoleDao.deleteRoleByPk(map);
	}
	@SystemLogService(description = "保存角色信息")
	public void saveRole(BsRole bsRole){
		bsRoleDao.saveRole(bsRole);
	}
	
	@SystemLogService(description = "更新角色信息")
	public void updateRole(Map map){
		bsRoleDao.updateRole(map);
	}
}
