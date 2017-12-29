package com.cxy.web.dao;

import com.cxy.frame.orm.MyBatisDao;
import com.cxy.frame.plugin.Page;
import com.cxy.web.po.BsDictionary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class BsDictionaryDao extends MyBatisDao<BsDictionary> {

	public List<BsDictionary> getAll(Map map){
		return this.getSqlSession().selectList("com.tonyj.myweb.dao.BsDictionary.getAll",map);
	}
	
	public Page selectPage(BsDictionary dict, Page page){
		return this.selectPage(dict,page,"selectPage");
	}
	
	public List<Map> getFieldList(Map map){
		return this.getSqlSession().selectList("com.tonyj.myweb.dao.BsDictionary.getField",map);
	}
	
	public void batchSaveDict(List<BsDictionary> list){
		this.getSqlSession().insert("com.tonyj.myweb.dao.BsDictionary.batchSaveDict", list);
	}
	
	public int delDict(Map map){
		return this.getSqlSession().delete("com.tonyj.myweb.dao.BsDictionary.delDict", map);
	}
	
	public void updateDict(Map map){
		 this.getSqlSession().update("com.tonyj.myweb.dao.BsDictionary.updateDict", map);
	}
}
