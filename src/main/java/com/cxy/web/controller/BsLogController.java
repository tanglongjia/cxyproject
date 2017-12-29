package com.cxy.web.controller;

import com.cxy.frame.plugin.Page;
import com.cxy.frame.web.BaseController;
import com.cxy.web.annotation.SystemLogBeforeController;
import com.cxy.web.constant.Constant;
import com.cxy.web.po.BsLog;
import com.cxy.web.service.BsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * @author TonyJ
 *
 */
@Controller
@RequestMapping("/bsLog")
public class BsLogController extends BaseController {

	@Autowired
	private BsLogService bsLogService;
	
	@RequestMapping(value="/index",method = RequestMethod.GET)
	@SystemLogBeforeController(description = "初始化日志页面")
	public ModelAndView logIndex(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		model.put("defaultDate", date);
		return new ModelAndView(Constant.PAGE_LOG_PATH+"logIndex",model);
	}
	
	@RequestMapping(value="/logData")
    @SystemLogBeforeController(description = "分页查询日志信息")  
    public ModelAndView logData(HttpServletRequest request, HttpServletResponse response, BsLog bsLog, Page page, ModelMap model){
		page = bsLogService.selectPage(bsLog,page);
    	model.put("page", page);
    	if(bsLog.getLogType() == 0){
    		return new ModelAndView(Constant.PAGE_LOG_PATH+"operLogData", model);
    	}else{
    		return new ModelAndView(Constant.PAGE_LOG_PATH+"execpLogData", model);
    	}
    }
}
