package com.zengshi.ecp.app.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.client.JsonClientHandler;
import com.zengshi.butterfly.app.model.AppDatapackage;

@Controller
@RequestMapping("/app")
public class AppDownloadController extends EcpBaseController{
    
    //private static Logger logger = LoggerFactory.getLogger(AppDownloadController.class);
	private static final String MODULE = AppDownloadController.class.getName();

    @RequestMapping("/download")
    public void download(Model model, HttpServletResponse response,HttpServletRequest request) throws IOException 
    { 
    	String url = request.getServletContext().getRealPath("/app/android/");
    	File apk = new File(url);
    	String[] tempName = apk.list();
    	if(tempName.length<0){
    		return ;
    	}
    	File oldfile= null;
    	File newfile= null;
    	String fileName = "";
    	String oldname = tempName[0];
    	String newname = "pmph.apk";
    	if(!oldname.equals(newname)){
    		oldfile=new File(url+"/"+oldname); 
    		newfile=new File(url+"/"+newname);
    		oldfile.renameTo(newfile);
    		fileName = newfile.getName();
    	}else{
    		fileName = oldname;
    		newfile = new File(url+"/"+oldname);
    	}
    	InputStream is = null;
        ServletOutputStream sos = null;
    	try {
    		is = new FileInputStream(newfile);
			//response.reset(); // 必要地清除response中的缓存信息
			response.setContentLength(is.available());
			response.setHeader("Content-type","application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + new String(fileName.getBytes()));
			sos = response.getOutputStream();
			FileCopyUtils.copy(is, sos); //该工具已经关闭流 
			LogUtil.info(MODULE, "===========下载APP升级包完成==========");
		} catch (Exception e) {
			LogUtil.error(MODULE, "===========下载APP升级包出错==========",e);
		} finally {
			LogUtil.info(MODULE, "===========finally==========");
	    }  
    }

}

