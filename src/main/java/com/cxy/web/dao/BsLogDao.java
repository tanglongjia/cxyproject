package com.cxy.web.dao;

import com.cxy.frame.orm.MyBatisDao;
import com.cxy.frame.plugin.Page;
import com.cxy.web.po.BsLog;
import org.springframework.stereotype.Repository;



@Repository
public class BsLogDao extends MyBatisDao<BsLog> {

	public Page selectPage(BsLog bsLog, Page page){
		return this.selectPage(bsLog,page,"selectPage");
	}
	
	public void saveLog(BsLog bsLog){
		this.insert(bsLog);
	}
}
