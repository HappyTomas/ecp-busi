package com.zengshi.ecp.app.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/app")
public class AppResourceController extends EcpBaseController{
    
	private static final String MODULE = AppResourceController.class.getName();

	/**
	 * 
	 * uploadImage:(这里用一句话描述这个方法的作用). <br/> 
	 * 
	 * @author linby
	 * @param uploadFileObj
	 * @param req
	 * @param rep
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@RequestMapping("/upload/image")
	@ResponseBody
	public String uploadImage(@RequestParam(value="image", required=false) MultipartFile uploadFileObj,
			HttpServletRequest req, HttpServletResponse rep) throws BusinessException{
		
		JSONObject obj = new JSONObject();//返回结果
		
		if (uploadFileObj == null) {
            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            obj.put("message", "请选择上传文件！");
            LogUtil.info(MODULE, "请选择上传文件！");
            return obj.toJSONString();
        }
		//文件名
		String vfsName = uploadFileObj.getOriginalFilename();
		vfsName = vfsName.replace(" ", "");
		//扩展名
		String extensionName = "." + getExtensionName(vfsName);
		//mongodbId
		String vfsId = "";
		
		/** 支持文件拓展名：.jpg,.png,.jpeg,.gif,.bmp */
		boolean flag = Pattern.
		        compile("\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$")
		        .matcher(extensionName.toLowerCase()).find();
		if (!flag) {
		    obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		    obj.put("message", "请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp)！");
		    LogUtil.error(MODULE, "上传图片失败,原因---请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp)!");
		    return obj.toJSONString();
		}
		//类型、扩展名
		obj.put("type", getExtensionName(vfsName));
		try {
			byte[] datas = inputStream2Bytes(uploadFileObj.getInputStream());
			// 图片大小判断
			if (datas.length > 8 * 1024 * 1024) {
				obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			    obj.put("message", "图片上传失败，上传的图片必须小于8M!");
			    LogUtil.error(MODULE, "上传图片失败,原因---图片上传失败，上传的图片必须小于8M!");
			    return obj.toJSONString();
			}
			if(extensionName.equalsIgnoreCase(".png")){
				vfsId = ImageUtil.saveToRomte(datas, "image", "png");
			}
			else{
				vfsId = ImageUtil.upLoadImage(datas, "image");
			}
			//文件id
			obj.put("fileId", vfsId);
		} catch (IOException e) {
			obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		    obj.put("message", "图片上传失败");
		    LogUtil.error(MODULE, "上传图片失败"+e.getMessage());
		    return obj.toJSONString();
		} catch (Exception e) {
			obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		    obj.put("message", "图片上传失败");
		    LogUtil.error(MODULE, "上传图片失败"+e.getMessage());
		    return obj.toJSONString();
		}
		
		obj.put("success", EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
	    obj.put("message", "上传成功！");
	    obj.put("url", getImageUrl(vfsId, ""));
		obj.put("mimeType", "");
	    
		return obj.toJSONString();
		
	}
	
    /*
     * 获取文件扩展名
     */
    private String getExtensionName(String fileName) {
        if ((fileName != null) && (fileName.length() > 0)) {
            int dot = fileName.lastIndexOf('.');
            if ((dot > -1) && (dot < (fileName.length() - 1))) {
                return fileName.substring(dot + 1);
            }
        }
        return fileName;
    }

    /*
     * 获取文件大小
     */
    private byte[] inputStream2Bytes(InputStream in){
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        try {
			while ((count = in.read(data, 0, 4096)) != -1)
			    outStream.write(data, 0, count);
		} catch (IOException e) {
			LogUtil.debug(MODULE, e.getMessage());
		}
        data = null;
        return outStream.toByteArray();
    }
    
    /*
     * 通过工具获取图片url
     */
    private String getImageUrl(String vfsId,String param){
        StringBuilder sb=new StringBuilder();
        sb.append(vfsId);
        if(!StringUtil.isBlank(param)){
            sb.append("_");
            sb.append(param);
        }
        return ImageUtil.getImageUrl(sb.toString());
    }
}

