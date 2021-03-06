package com.cxy.web.service;

import com.cxy.frame.plugin.Page;
import com.cxy.web.annotation.SystemLogService;
import com.cxy.web.dao.BsDictionaryDao;
import com.cxy.web.po.BsDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class BsDictionaryService {

	@Autowired
	private BsDictionaryDao bsDictionaryDao;
	
	@SystemLogService(description = "查询数据字典信息")
	public List<BsDictionary> getDictList(Map map){
		return bsDictionaryDao.getAll(map);
	}
	@SystemLogService(description = "分页查询数据字典信息")
	public Page selectPage(BsDictionary dict, Page page){
		return bsDictionaryDao.selectPage(dict,page);
	}
	@SystemLogService(description = "查询字典中对应的字段列表")
	public List<Map> getFieldList(Map map){
		return bsDictionaryDao.getFieldList(map);
	}
	@SystemLogService(description = "保存字典信息")
	public void batchSaveDict(List<BsDictionary> list){
			bsDictionaryDao.batchSaveDict(list);
	}
	@SystemLogService(description = "删除字典信息")
	public int delDict(Map map){
		return bsDictionaryDao.delDict(map);
	}
	@SystemLogService(description = "更新字典信息")
	public void updateDict(Map map){
		bsDictionaryDao.updateDict(map);
	}
	
}
