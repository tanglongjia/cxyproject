package com.cxy.web.dao;

import com.cxy.frame.orm.MyBatisDao;
import com.cxy.frame.plugin.Page;
import com.cxy.web.constant.TreeBean;
import com.cxy.web.po.BsResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;



@Repository
public class BsResourceDao extends MyBatisDao<BsResource> {

	public List<BsResource> getBsResourceByParentId(Map<String,Integer> paramMap){
		return this.getSqlSession().selectList("getBsResourceByParentId",paramMap);
	}
	
	public List<TreeBean> getTree(Map paramMap){
		return this.getSqlSession().selectList("getTree",paramMap);
	}
	
	public Page selectListPage(BsResource bsRes, Page page){
		return super.selectPage(bsRes, page, "selectPage");
	}
	
	public BsResource getResByPk(Integer id){
		return this.getSqlSession().selectOne("getResByPk",id);
	}
	
	public void updateResByPk(Map map){
		 this.getSqlSession().update("updateResByPk", map);
	}
	
	public void savaRes(BsResource bsRes){
		this.getSqlSession().insert("com.tonyj.myweb.po.BsResource.insert", bsRes);
	}
}
