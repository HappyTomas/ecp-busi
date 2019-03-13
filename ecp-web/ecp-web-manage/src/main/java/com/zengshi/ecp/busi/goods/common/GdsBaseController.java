/** 
 * Project Name:ecp-web-manage 
 * File Name:GdsBaseController.java 
 * Package Name:com.zengshi.ecp.busi.goods.common 
 * Date:2015年8月29日下午4:11:54 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.goods.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.mvc.JsonResultThreadLocal;
import com.zengshi.ecp.base.mvc.annotation.NativeJson;
import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 商品域基础抽象Controller <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月29日下午4:11:54  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public abstract class GdsBaseController extends EcpBaseController {
    
    protected String MODULE = getClass().getName();
    /**
     * 图片扩展名类型正则。
     */
    public static final Pattern IMG_TYPE_PATTERN = Pattern.compile("\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$");
    /**
     * 默认上传文件大小.(单位byte)
     */
    public static final Long DEFAULT_FILE_MAXSIZE = 102400L; 
    
  //定义允许上传的文件扩展名
	public static final HashMap<String, String> extMap = new HashMap<String, String>();
	
	static{
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
	}
    
    
	/**
	 * 
	 * 图片上传.(上传代码参考自框架.) 
	 * 
	 * @author liyong7
	 * @param file
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @since JDK 1.6
	 */
    @RequestMapping("/imgUpload")
    @ResponseBody
    @NativeJson(true)
    public String imgUpload(@RequestParam(value = "imgObj", required = false) MultipartFile file,Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
    	JsonResultThreadLocal.set(false);

    	//定义允许上传的文件扩展名
    	Map<String, String> extMap = getExtMap();
    	

    	//最大文件大小
    	long maxSize = getMaxSize();
    	response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

    	if(!ServletFileUpload.isMultipartContent(request)){
    		return getError("请选择文件。");
    	}

    	String dirName = request.getParameter("dir");
    	if (dirName == null) {
    		dirName = "image";
    	}
    	if(!extMap.containsKey(dirName)){
    		return getError("目录名不正确。");
    	}
    	
    	if(file.getSize() > maxSize){
			return getError("上传文件大小超过限制。");
    	}
		//检查扩展名
		String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
		
		if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
			return getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
		}

		String fileId = FileUtil.saveFile(file.getBytes(), file.getOriginalFilename(), fileExt);
		String url = ImageUtil.getImageUrl(fileId);
		
		
		JSONObject obj = new JSONObject();
		obj.put("fileId", fileId);
		obj.put("url", url);
    	return obj.toJSONString();
    }
	
	
    
    /**
     * 
     * 功能描述：处理错误信息的JSON结果
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2015-8-31 下午5:34:29</p>
     *
     * @param message
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    private String getError(String message) {
    	JSONObject obj = new JSONObject();
    	obj.put("error", 1);
    	obj.put("message", message);
    	return obj.toJSONString();
    }
    
    
    
    /**
     * 
     * uploadImage:(上传图片). <br/> 
     * @author gxq 
     * @param model
     * @param req
     * @param rep
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/uploadimage")
    @ResponseBody
    protected String uploadImage(@RequestParam(value="detailPic",required=false)MultipartFile file, HttpServletResponse rep) {
        JSONObject obj = new JSONObject();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        try {
            if (file == null) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "请选择上传文件！");
                LogUtil.info(MODULE, "请选择上传文件！");
                return obj.toString();
            }
            String fileName = file.getOriginalFilename();
            String extensionName = "." + getExtensionName(fileName);

            /** 支持文件拓展名：.jpg,.png,.jpeg,.gif,.bmp */
            boolean flag = IMG_TYPE_PATTERN.matcher(extensionName.toLowerCase()).find();
            if (!flag) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp)!");
                LogUtil.error(MODULE, "上传图片失败,原因---请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp)!");
                return obj.toString();
            }
            //判断图片的长宽（像素）暂时不用，请勿删除
            /*BufferedImage bi = ImageIO.read(file.getInputStream());
            int width = bi.getWidth(); 
            int height = bi.getHeight();
            if(width<700 || height <700){
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "请上传图片规格为：</br>宽度：≥700像素；</br>高度：≥700像素的图片！");
                LogUtil.error(MODULE, "上传的图片必须按1:1上传！");
                return obj.toJSONString();
            }
            if(width != height){
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "上传的图片的宽度和高度像素不一致！");
                LogUtil.error(MODULE, "上传的图片的宽度和高度像素不一致！");
                return obj.toJSONString();
            }*/
            byte[] datas = inputStream2Bytes(file.getInputStream());
            if(datas.length>5*1024*1024){
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "上传的图片大于5M!");
                LogUtil.error(MODULE, "图片上传失败，上传的图片必须小于5M!");
                return obj.toString();
            }
            fileName = Math.random()+"";
            String vfsId = ImageUtil.upLoadImage(datas, fileName);
            resultMap.put("vfsId", vfsId);
            resultMap.put("imageName", fileName);
//            resultMap.put("id", id);
            resultMap.put("imagePath", new AiToolUtil().genImageUrl(vfsId,"150x150!"));
            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            obj.put("message", "保存成功!");
            obj.put("map", resultMap);
        } catch (Exception e) {
            LogUtil.info(MODULE,"上传图片出错,原因---"+e.getMessage(), e);
            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            obj.put("message", "保存失败，图片服务器异常，请联系管理员!");
        }
        return obj.toString();
    }
    
    /**
     * 
     * inputStream2Bytes:(将InputStream转换成byte数组). <br/> 
     * @author gxq 
     * @param in
     * @return
     * @throws IOException 
     * @since JDK 1.6
     */
    protected byte[] inputStream2Bytes(InputStream in) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        while ((count = in.read(data, 0, 4096)) != -1)
            outStream.write(data, 0, count);
        data = null;
        return outStream.toByteArray();
    }

    /**
     * 
     * getExtensionName:(获取文件拓展名). <br/> 
     * @author gxq 
     * @param fileName
     * @return 
     * @since JDK 1.6
     */
    protected String getExtensionName(String fileName) {
        if ((fileName != null) && (fileName.length() > 0)) {
            int dot = fileName.lastIndexOf('.');
            if ((dot > -1) && (dot < (fileName.length() - 1))) {
                return fileName.substring(dot + 1);
            }
        }
        return fileName;
    }
    
    
    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     */
  //  @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
//        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) {
//                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
//            }
//
//            @Override
//            public String getAsText() {
//                Object value = getValue();
//                return value != null ? value.toString() : "";
//            }
//        });
//        // Date 类型转换
//      binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) {
//                setValue(DateUtils.parseDate(text));
//            }
//			@Override
//			public String getAsText() {
//				Object value = getValue();
//				return value != null ? DateUtils.formatDateTime((Date)value) : "";
//			}
//        });
//    }
    

    
    /**
     * 
     * 必传参数检测，仅对普通参数进行判空处理，抛出异常信息为:必传参数{0}缺失!<br/>
     * 会对嵌套对象，如果外围对外为空，一并传入判断那么该方法会报空。<br/>
     * 举例：当外围对象为reqDTO为空时如果按以下格式调用则会报空:
     * 其中两个参数的params与msgs的数组长度要一致。
     * 
     * @author liyong7
     * @param params
     * @param msgs
     * @since JDK 1.6
     */
    protected void paramCheck(Object[] params, String[] msgs) {
        if (null != params && null != msgs && params.length == msgs.length) {
               StringBuffer errorMsg = new StringBuffer();
               for(int i = 0; i < params.length; ++ i){
                   Object obj = params[i];
                   if(obj instanceof String){
                       if(StringUtil.isBlank((String)obj)){
                           errorMsg.append(msgs[i]);
                           errorMsg.append(",");
                       }
                   }else if(obj instanceof Object[]){
                	   if(obj == null || ((Object[])obj).length == 0){
                		   errorMsg.append(msgs[i]);
                           errorMsg.append(",");
                	   }
                   }else if(obj instanceof Collection<?>){
                	   if(obj == null || CollectionUtils.isEmpty((Collection<?>)obj)){
                		   errorMsg.append(msgs[i]);
                           errorMsg.append(",");
                	   }
                   }else{
                       if(null == obj){
                           errorMsg.append(msgs[i]);
                           errorMsg.append(",");
                       }
                   }
               }
               if(0 < errorMsg.length()){
                   errorMsg.deleteCharAt(errorMsg.length() - 1);
                   throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,new String[]{errorMsg.toString()});
               }
        } else {
            LogUtil.error(MODULE, "参数检测方法执行异常！请保证params与msgs不为空，并且两个参数数组长度一致");
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200099,
                    new String[] { Thread.currentThread().getStackTrace()[1].getMethodName() });
        }
    }
    
    
    
    protected Map<String, String> getExtMap(){
    	return extMap;
    }
    
    
    protected Long getMaxSize(){
        return DEFAULT_FILE_MAXSIZE;	
    }
    /**
     * 
     * uploadFile:(上传文件). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * 
     * @author gxq 
     * @param model
     * @param uploadFileObj
     * @param req
     * @param rep
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/uploadfile")
    @ResponseBody
    @NativeJson(true)
    public String uploadFile(Model model, 
            @RequestParam(value = "uploadFileObj", required = false) MultipartFile uploadFileObj,
            HttpServletRequest req,HttpServletResponse rep) throws Exception {
        JSONObject obj = new JSONObject();// 返回结果
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            if (uploadFileObj == null) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "请选择上传文件！");
                LogUtil.info(MODULE, "请选择上传文件！");
                return obj.toJSONString();
            }
            String vfsName = uploadFileObj.getOriginalFilename();// 文件名
            String extensionName = "." + this.getExtensionName(vfsName);// 文件后缀
            /** 支持文件拓展名：. */
//            boolean flag = Pattern
//                    .compile("\\.(pdf)$")
//                    .matcher(extensionName.toLowerCase()).find();
//            if (!flag) {
//                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
//                obj.put("message", "请选择文件(.doc,.pdf,.zip,.rar)！");
//                LogUtil.error(MODULE,"上传文件失败,原因---请选择文件(.doc,.pdf,.zip,.rar)!");
//                return obj.toJSONString();
//            }
            byte[] datas = uploadFileObj.getBytes();
            if (datas.length > 10 * 1024 * 1024) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "附件上传失败，上传的文件必须小于10M!");
                LogUtil.error(MODULE, "附件上传失败，上传的文件必须小于10M!");
                return obj.toJSONString();
            }

            String vfsId = FileUtil.saveFile(datas, vfsName,uploadFileObj.getContentType());
            resultMap.put("vfsId", vfsId);
//            resultMap.put("vfsUrl", ImageUtil.getStaticDocUrl(vfsId, "doc"));
            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            obj.put("message", "附件上传成功!");
            obj.put("resultMap", resultMap);
        } catch (Exception e) {
            LogUtil.info(MODULE, "附件上传失败,原因---" + e.getMessage(), e);
            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            obj.put("message", "附件上传失败，图片服务器异常，请联系管理员!");
        }
        return obj.toJSONString();
    }
}

