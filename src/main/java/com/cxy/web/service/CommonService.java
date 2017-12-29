package com.cxy.web.service;

import com.cxy.web.dao.BsDictionaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CommonService {

	@Autowired
	private BsDictionaryDao bsDictionaryDao;
	
	/**
	 * 根据字典编码 获取该字典编码对应的字段值
	 * @param code
	 * @return
	 */
	public List<Map> getFiled(String code){
		Map pMap = new HashMap();
		pMap.put("dictCode", code);
		return bsDictionaryDao.getFieldList(pMap);
	}
}
