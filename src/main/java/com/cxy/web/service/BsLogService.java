package com.cxy.web.service;

import com.cxy.frame.plugin.Page;
import com.cxy.web.annotation.SystemLogService;
import com.cxy.web.dao.BsLogDao;
import com.cxy.web.po.BsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BsLogService {

	@Autowired
	private BsLogDao bsLogDao;
	
	@SystemLogService(description = "分页查询日志信息")
	public Page selectPage(BsLog bsLog, Page page){
		return bsLogDao.selectPage(bsLog, page);
	}
	@SystemLogService(description = "保存日志信息")
	public void saveLog(BsLog bsLog){
		bsLogDao.saveLog(bsLog);
	}
}
