package com.zengshi.ecp.busi.buyer.controller;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.buyer.vo.CustInfoVO;
import com.zengshi.ecp.busi.buyer.vo.ModifyPwdVO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthStaffRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.system.util.ConstantTool;
import com.zengshi.ecp.wxbase.util.WeixinUtil;
import com.zengshi.ecp.wxbase.util.WxConstants;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value = "/infomation")
public class InfomationController extends EcpBaseController {

	private static final String Module = InfomationController.class.getName();

	@Resource
	private ICustInfoRSV custInfoRSV;
	
	@Resource
	private ICustManageRSV custManageRSV;
	
	@Resource
    private IAuthStaffRSV authStaffRSV;
	
	@RequestMapping(value = "/index")
	public String index(Model model,HttpServletRequest request) throws BusinessException, NoSuchAlgorithmException {
		
		CustInfoReqDTO dto = new CustInfoReqDTO();
		dto.setId(dto.getStaff().getId());
		CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(dto);
		model.addAttribute("infomation", custInfoResDTO);
		
		
		
	/*	String signature = WeixinUtil.getJsSignature("http://wangbaihuai.vicp.net"+request.getRequestURI());
		String timestamp = ConstantTool.getWeixParam(WxConstants.ECP_WEIX+"timestamp");
		String nonce = ConstantTool.getWeixParam(WxConstants.ECP_WEIX+"nonce");
		String echostr = ConstantTool.getWeixParam(WxConstants.ECP_WEIX+"echostr");*/
	/*	model.addAttribute("signature", signature);
		model.addAttribute("timestamp", timestamp);
		model.addAttribute("nonce", nonce);
		model.addAttribute("echostr", echostr);*/
		/*model.addAttribute("appid", WxConstants.APPID);*/
		return "/buyer/infomation/infomation";
	}
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public EcpBaseResponseVO updateCustInfo(CustInfoVO custInfoVO)throws BusinessException{
		EcpBaseResponseVO responseVO = new EcpBaseResponseVO();
		CustInfoReqDTO req = new CustInfoReqDTO();
		ObjectCopyUtil.copyObjValue(custInfoVO, req, null, false);
		req.setId(req.getStaff().getId());
		custManageRSV.updateCustInfo(req);
		responseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		return responseVO;
	}
	
	@RequestMapping(value = "/uploadimg",method = RequestMethod.POST)
    public String headImg(@RequestParam(value = "image", required = false) MultipartFile file) throws Exception {
		   JSONObject obj = new JSONObject();//返回结果
	        Map<String, Object> resultMap = new HashMap<String, Object>();
	        try {
	            if (file == null) {
	                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
	                obj.put("message", "请选择上传文件！");
	                return obj.toJSONString();
	            }
	            String vfsName = file.getOriginalFilename();
	            vfsName = vfsName.replace(" ", ""); 
	            String extensionName = "." + ConstantTool.getExtensionName(vfsName);
	    
	            /** 支持文件拓展名：.jpg,.png,.jpeg,.gif,.bmp */
	            boolean flag = Pattern.
	                    compile("\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$")
	                    .matcher(extensionName.toLowerCase()).find();
	            if (!flag) {
	                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
	                obj.put("message", "请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp)！");
	                return obj.toJSONString();
	            }
	            
	            byte[] datas = ConstantTool.inputStream2Bytes(file.getInputStream());
	            String vfsId ;
	            if(extensionName.equalsIgnoreCase(".png")){
	            	vfsId = ImageUtil.saveToRomte(datas, "image", "png");
	            }
	            else{
	            	vfsId = ImageUtil.upLoadImage(datas, "image");
	            }
	        	CustInfoReqDTO req = new CustInfoReqDTO();
	    		req.setId(req.getStaff().getId());
	    		req.setCustPic(vfsId);
	    		custManageRSV.updateCustInfo(req);
	            //resultMap.put("id", id);
	            resultMap.put("vfsId", vfsId);
	            resultMap.put("vfsName", vfsName);
	            resultMap.put("vfsUrlPri", ConstantTool.getImageUrl(vfsId,""));
	            resultMap.put("vfsUrl", ConstantTool.getImageUrl(vfsId,"150x150!"));
	            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
	            obj.put("message", "图片上传成功!");
	            obj.put("resultMap", resultMap);
	        } catch (Exception e) {
	            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
	            obj.put("message", "图片上传失败，图片服务器异常，请联系管理员!");
	        }
	        return "/infomation/index";
            
    }
	
	/**
	 * 
	 * pwd:(跳转到密码修改页面). <br/> 
	 * 
	 * @author huangxl5 
	 * @param model
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/pwd")
	public String pwd(Model model) throws BusinessException {
		
		return "/buyer/infomation/pwd";
	}

	/**
	 * 
	 * savePwd:(保存密码修改). <br/> 
	 * 
	 * @author huangxl5 
	 * @param custInfoVO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/savepwd")
	@ResponseBody
	public EcpBaseResponseVO savePwd(ModifyPwdVO vo)throws BusinessException{
		EcpBaseResponseVO res = new EcpBaseResponseVO();
		AuthStaffReqDTO req = new AuthStaffReqDTO();
        req.setId(req.getStaff().getId());//id
        req.setStaffPasswd(vo.getNewPassword());//新密码
        req.setStaffPwdOld(vo.getOldPassword());//旧密码
        try {
        	authStaffRSV.updatePwdById(req);
        	res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			res.setResultMsg(e.getMessage());
			res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		}
        
		return res;
	}
}
