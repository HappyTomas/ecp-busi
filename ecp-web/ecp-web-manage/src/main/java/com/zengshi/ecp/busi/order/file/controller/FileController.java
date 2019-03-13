package com.zengshi.ecp.busi.order.file.controller;
 
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV; 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.mvc.annotation.NativeJson;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.bill.vo.ROrdInvoiceReqVO;
import com.zengshi.ecp.busi.order.bill.vo.RQueryOrderReqVO; 
import com.zengshi.ecp.busi.order.util.ParamsTool; 
import com.zengshi.ecp.order.dubbo.dto.ROfflineReviewRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdInvoiceResponse; 
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest; 
import com.zengshi.ecp.server.front.dto.PageResponseDTO;  
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil; 
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value="/order/file")
public class FileController extends EcpBaseController {

    private static final String MODULE = FileController.class.getName();
    
    /**
     * 
     * init:(文件上专页面). <br/> 
     * 
     * @author panjs 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init(Model model) {
        model.addAllAttributes(ParamsTool.params());
        return "/order/file/file-init";
    } 
    
    /**
     * 
     * uploadFile:(上传文件). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * 
     * @author panjs
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
            byte[] datas = uploadFileObj.getBytes();
            if (datas.length > 10 * 1024 * 1024) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "附件上传失败，上传的文件必须小于10M!");
                LogUtil.error(MODULE, "附件上传失败，上传的文件必须小于10M!");
                return obj.toJSONString();
            }

            String vfsId = FileUtil.saveFile(datas, vfsName,uploadFileObj.getContentType());
            resultMap.put("vfsId", vfsId);
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
    
    /**
     * 
     * getExtensionName:(获取文件拓展名). <br/> 
     * @author panjs
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
}

