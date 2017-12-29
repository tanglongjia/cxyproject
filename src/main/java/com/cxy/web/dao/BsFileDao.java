package com.cxy.web.dao;

import com.cxy.frame.orm.MyBatisDao;
import com.cxy.frame.plugin.Page;
import com.cxy.web.po.BsFile;
import org.springframework.stereotype.Repository;



@Repository
public class BsFileDao extends MyBatisDao<BsFile> {

	public Page selectPage(BsFile bsFile, Page page){
		return this.selectPage(bsFile,page,"selectPage");
	}
	
	public void saveFile(BsFile bsFile)throws Exception{
		this.insert(bsFile);
	}
	
	public void deleteFile(Integer id){
		this.getSqlSession().delete("com.tonyj.myweb.po.BsFile.deleteFile", id);
	}
}
