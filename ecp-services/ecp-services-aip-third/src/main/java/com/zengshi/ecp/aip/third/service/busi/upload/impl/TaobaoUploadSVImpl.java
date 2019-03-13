package com.zengshi.ecp.aip.third.service.busi.upload.impl;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.UploadThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.UploadThirdRespDTO;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.aip.third.service.busi.upload.interfaces.IUploadSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.FileItem;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.PictureUploadRequest;
import com.taobao.api.response.PictureUploadResponse;

public class TaobaoUploadSVImpl implements IUploadSV{
    
    public static final String MODULE = TaobaoUploadSVImpl.class.getName();
    
    @Resource
   	private ITokenSV defaultTokenSV;
    
    @Override
    public UploadThirdRespDTO upload(UploadThirdReqDTO uploadThirdReqDTO)throws BusinessException{
    	UploadThirdRespDTO resp=null;
    	List<File> files=new ArrayList<File>();
        try{ 
        	
        	TaobaoClient client = new DefaultTaobaoClient(
        			uploadThirdReqDTO.getServerUrl(), uploadThirdReqDTO.getAppkey(),
        			uploadThirdReqDTO.getAppscret());
        	
        	PictureUploadRequest req = new PictureUploadRequest();
        	
        	req.setImageInputTitle(uploadThirdReqDTO.getImageInputTitle());
        	
        	if(StringUtils.isNotBlank(uploadThirdReqDTO.getImgUrl())){
        		req.setImg(new FileItem(uploadThirdReqDTO.getImgUrl()));
        	}else{
        		//通过fileId存在本地  在设置url到img字段中
        		byte[] images=FileUtil.readFile(uploadThirdReqDTO.getFileId());
        		File file=createTempFile(images, uploadThirdReqDTO.getFileId(), ".jpg");
        		files.add(file);
        		FileItem fileItem=new FileItem(file.getCanonicalPath());
        		req.setImg(fileItem);
        	}
        	
        	req.setPictureCategoryId(Long.valueOf(uploadThirdReqDTO.getPictureCategoryId()));
        	req.setTitle(uploadThirdReqDTO.getTitle());
        	
           // 验证参数是否正确
           req.check();
	       String token=defaultTokenSV.fetchShopToken(uploadThirdReqDTO.findBaseShopAuth());
	       PictureUploadResponse rsp = client.execute(req,token); 
	       
	      	// 返回结果解析
			if (rsp.isSuccess()) {
			       resp=new UploadThirdRespDTO();
			       resp.setPictureCategoryId(rsp.getPicture().getPictureCategoryId().toString());
			       resp.setPictureId(rsp.getPicture().getPictureId().toString());
			       resp.setPicturePath(rsp.getPicture().getPicturePath());
			       resp.setTitle(rsp.getPicture().getTitle());
			} else {
				LogUtil.error(MODULE, rsp.getBody().toString());
				throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{rsp.getBody().toString()});
			}
			
        }catch(Exception e){
        	LogUtil.error(MODULE, e.toString());
        	 throw new BusinessException("AIPTHIRD.ERRORS.100002",new String[]{e.toString()});
        }finally{
        	if(CollectionUtils.isNotEmpty(files)){
        		for(File f:files){
        			try{
        			    f.deleteOnExit();
        			}catch(Exception ex){
        				LogUtil.error(MODULE, ex.getMessage());
        			}
        		}
        	}
        }
    	
        return resp;
    }
    /** 
     * 创建临时文件 
     * @param prefix    临时文件名的前缀 
     * @param suffix    临时文件名的后缀 
     * @return 临时文件路径 
     */  
    public File createTempFile(byte[] images,String prefix, String suffix) {  
        File tempFile = null;  
        FileImageOutputStream imageOutput=null;
            try {  
                // 在默认文件夹下创建临时文件  
                tempFile = File.createTempFile(prefix, suffix);  
                if(images!=null){
                	imageOutput = new FileImageOutputStream(tempFile);//打开输入流
                    imageOutput.write(images, 0, images.length);//将byte写入硬盘
                    imageOutput.close();
                }
                // 返回临时文件的路径  
                return tempFile; 
            } catch (IOException e) {  
            	LogUtil.error(MODULE, "创建临时文件失败!"+e.getMessage());
                return null;  
            }finally{
            	if(imageOutput!=null){
            		try {
            			imageOutput.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						LogUtil.error(MODULE, "关闭临时文件失败!"+e.getMessage());
					}
            	}
            }
    }  
}
