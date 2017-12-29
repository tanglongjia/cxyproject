package com.cxy.web.service;

import com.cxy.frame.plugin.Page;
import com.cxy.web.annotation.SystemLogService;
import com.cxy.web.dao.BsFileDao;
import com.cxy.web.po.BsFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class BsFileService {
	
	@Autowired
	private BsFileDao bsFileDao;

	@SystemLogService(description = "查询文件列表")
	public Page selectPage(BsFile file, Page page){
		return bsFileDao.selectPage(file,page);
	}
	
	@SystemLogService(description = "保存文件")
	public void saveFile(BsFile file)throws Exception{
		bsFileDao.saveFile(file);
	}
	
	@SystemLogService(description = "查询单个文件")
	public BsFile selectSingle(Integer id)throws Exception{
		return bsFileDao.selectSingle(id);
	}
	
	@SystemLogService(description = "删除文件")
	public void deleteFile(Integer id)throws Exception{
		bsFileDao.deleteFile(id);
	}
}
