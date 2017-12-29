package com.cxy.web.controller;

import com.cxy.frame.plugin.Page;
import com.cxy.frame.util.FileUtil;
import com.cxy.frame.util.JsonUtils;
import com.cxy.frame.util.MessageStreamResult;
import com.cxy.frame.web.BaseController;
import com.cxy.web.annotation.SystemLogBeforeController;
import com.cxy.web.constant.Constant;
import com.cxy.web.po.BsFile;
import com.cxy.web.po.BsUser;
import com.cxy.web.service.BsFileService;
import com.cxy.web.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("bsFile")
public class BsFileController extends BaseController {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private BsFileService bsFileService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@SystemLogBeforeController(description = "初始化文件模块")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		List<Map> filedList = commonService.getFiled("FILE_TYPE");
		model.put("filedList", filedList);
		return new ModelAndView(Constant.PAGE_FILE_PATH+"fileIndex", model);
	}
	
	@RequestMapping(value="/fileData")
    @SystemLogBeforeController(description = "查询文件列表信息")  
    public ModelAndView getFileData(HttpServletRequest request, HttpServletResponse response, BsFile file, Page page, ModelMap model){
		page = bsFileService.selectPage(file, page);
    	model.put("page", page);
    	List<Map> filedList = commonService.getFiled("FILE_TYPE");
		model.put("filedList", filedList);
    	return new ModelAndView(Constant.PAGE_FILE_PATH+"fileData", model);
    }
	
	
	@RequestMapping(value="/delFile")
    @SystemLogBeforeController(description = "删除文件信息")  
    public void delFile(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		try {
			BsFile bsFile = bsFileService.selectSingle(new Integer(id));
			//删除具体文件
			File delFile = new File(bsFile.getFileName()+File.separator+bsFile.getFileName());
			delFile.delete();
			//删数据库
			bsFileService.deleteFile(new Integer(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	 /**
     * 上传文件
     * @param request
     * @param response
     * @param file 上传的文件，支持多文件
     * @throws Exception
     */
    @RequestMapping("/mupload")
    @SystemLogBeforeController(description = "多文件上传操作") 
    public void mupload(HttpServletRequest request,HttpServletResponse response
            ,@RequestParam("mfile") MultipartFile[] file) throws Exception{
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	BsUser bsUser =(BsUser) request.getSession().getAttribute("bsUser");
        if(file!=null&&file.length>0){
            //组合image名称，“;隔开”
            List<String> fileName =new ArrayList<String>();
            try {
                for (int i = 0; i < file.length; i++) {
                    if (!file[i].isEmpty()) {
                        //上传文件，随机名称，";"分号隔开
                        fileName.add(FileUtil.uploadImage(request, "/upload/"+sdf.format(new Date())+"/", file[i], false));
                    }
                }
                //上传成功
                if(fileName!=null&&fileName.size()>0){
                    System.out.println("上传成功！");
                    //插入数据库
                    for (int i = 0; i < file.length; i++) {
                    	BsFile  bsFile = new BsFile();
                        bsFile.setFileName(file[i].getOriginalFilename());
                        bsFile.setViewFilePath("/upload/"+sdf.format(new Date())+"/"+file[i].getOriginalFilename());
                        bsFile.setFilePath(request.getSession().getServletContext().getRealPath( "/upload/"+sdf.format(new Date())+"/"));
                        bsFile.setFileType(request.getParameter("fileType"));
                        bsFile.setCreated(new Date());
                        bsFile.setCreatedby(bsUser.getId());
                        bsFile.setStatus(1);
                        try {
            				bsFileService.saveFile(bsFile);
            			} catch (Exception e1) {
            			}
                    }
                    MessageStreamResult.msgStreamResult(response, JsonUtils.objectToJson(fileName));;
                }else {
                	MessageStreamResult.msgStreamResult(response, "上传失败！文件格式错误！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                MessageStreamResult.msgStreamResult(response, "上传出现异常！异常出现在：BsFileController.mupload()");
            }
        }else {
        	MessageStreamResult.msgStreamResult(response, "没有检测到文件！");
        }
    }
	
	
	@RequestMapping(value = "/upload")
	@SystemLogBeforeController(description = "文件上传操作")  
    public String updateThumb(HttpServletRequest request, HttpServletResponse response,@RequestParam("addFileType") String fileType,
            @RequestParam("file") MultipartFile file){
		//String filePath = "E:/MyEclipse10Workspaces/BaseSystem/src/main/webapp/assets/upload/";
		String filePath =request.getSession().getServletContext().getRealPath("upload");
		BsUser bsUser =(BsUser) request.getSession().getAttribute("bsUser");
        if (!file.isEmpty()) {
            try {
				file.transferTo(new File(filePath+File.separator+file.getOriginalFilename()));
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BsFile  bsFile = new BsFile();
            bsFile.setFileName(file.getOriginalFilename());
            bsFile.setViewFilePath("upload/"+file.getOriginalFilename());
            bsFile.setFilePath(filePath);
            bsFile.setFileType(fileType);
            bsFile.setCreated(new Date());
            bsFile.setCreatedby(bsUser.getId());
            bsFile.setStatus(1);
            try {
				bsFileService.saveFile(bsFile);
			} catch (Exception e1) {
				//发生异常的时候 删除已经上传的文件
				File delFile = new File(filePath+file.getOriginalFilename());
				delFile.delete();
			}
            try {
				MessageStreamResult.msgStreamResult(response, "0");
			} catch (Exception e) {
				e.printStackTrace();
			}
        } else {
        	 try {
 				MessageStreamResult.msgStreamResult(response, "1");
 			} catch (Exception e) {
 				e.printStackTrace();
 			}
        }
        return null;
    }
}
