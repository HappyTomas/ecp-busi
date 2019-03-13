package com.zengshi.ecp.busi.order.point.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.mvc.annotation.NativeJson;
import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.OrdConstant;
import com.zengshi.ecp.busi.order.vo.RBackApplyOrdSubReqVO;
import com.zengshi.ecp.busi.order.vo.RBackApplyReqVO;
import com.zengshi.ecp.busi.order.vo.RBackConfirmReqVO;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyOrdResp;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyOrdSubResp;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyReq;
import com.zengshi.ecp.order.dubbo.dto.RBackConfirmReq;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackDetailResp;
import com.zengshi.ecp.order.dubbo.dto.ROrderBackReq;
import com.zengshi.ecp.order.dubbo.dto.ROrderDetailsRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdBackGdsRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdBackMoneyRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDetailsRSV;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value="/order/point/return")
public class PointReturnOrdController extends EcpBaseController {

    private static final String MODULE = PointReturnOrdController.class.getName();
    @Resource
    private IOrdDetailsRSV ordDetailsRSV;
    
    @Resource
    private IOrdBackMoneyRSV ordBackMoneyRSV;
    
    @Resource
    private IOrdBackGdsRSV ordBackGdsRSV;
    
    @Resource
    private IShopInfoRSV shopInfoRSV;
     
    /**
     * returnMoney:(退款详情页面). <br/> 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/returnMoney/{backId}/{orderId}")
    public String returnMoney(Model model, @PathVariable("backId") String backId, @PathVariable("orderId") String orderId) {
    	//后场服务所需要的DTO；
    	ROrderBackReq dto = new ROrderBackReq();
    	dto.setBackId(Long.valueOf(backId));
    	dto.setOrderId(orderId);
    	ROrderBackDetailResp resp  = ordBackMoneyRSV.queryBackMoneyDetails(dto);
    	//订单信息
    	model.addAttribute("rBackGdsResps", resp.getrBackGdsResps());
    	//订单优惠信息 （金额、资金账户、积分相关信息）
    	model.addAttribute("rBackDiscountResps", resp.getrBackDiscountResps());
    	//订单优惠信息 （优惠券相关信息）
    	model.addAttribute("rBackCouponResps", resp.getrBackCouponResps());
    	//订单优惠信息 （赠品相关信息）
    	model.addAttribute("rBackGiftResps", resp.getrBackGiftResps());
    	//订单优惠信息 （图片作证相关信息）
    	model.addAttribute("rBackPicResps", resp.getrBackPicResps());
    	//退款日志信息
    	model.addAttribute("rBackTrackResps", resp.getrBackTrackResps());
    	//退货申请信息
    	model.addAttribute("rBackApplyResp", resp.getrBackApplyResp()); 
    	
        return "/order/point/return/return-money";
    } 
    
    /**
     * returnDetail:(退货详情页面). <br/> 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/returnDetail/{backId}/{orderId}")
    public String returnDetail(Model model, @PathVariable("backId") String backId, @PathVariable("orderId") String orderId) {
    	//后场服务所需要的DTO；
    	ROrderBackReq dto = new ROrderBackReq();
    	dto.setBackId(Long.valueOf(backId));
    	dto.setOrderId(orderId);
    	ROrderBackDetailResp resp  = ordBackGdsRSV.queryBackGdsDetails(dto);
    	//订单信息
    	model.addAttribute("rBackGdsResps", resp.getrBackGdsResps());
    	//订单优惠信息 （金额、资金账户、积分相关信息）
    	model.addAttribute("rBackDiscountResps", resp.getrBackDiscountResps());
    	//订单优惠信息 （优惠券相关信息）
    	model.addAttribute("rBackCouponResps", resp.getrBackCouponResps());
    	//订单优惠信息 （赠品相关信息）
    	model.addAttribute("rBackGiftResps", resp.getrBackGiftResps());
    	//订单优惠信息 （图片作证相关信息）
    	model.addAttribute("rBackPicResps", resp.getrBackPicResps());
    	//退款日志信息
    	model.addAttribute("rBackTrackResps", resp.getrBackTrackResps());
    	//退货申请信息
    	model.addAttribute("rBackApplyResp", resp.getrBackApplyResp()); 
    	
        return "/order/point/return/return-detail";
    } 
    
    /**
     * checkReturn:(验证退货、退款申请及确认发货). <br/> 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkReturn")
    public Map<String,Object> checkReturn(Model model, @RequestParam("backId") String backId, @RequestParam("orderId") String orderId,@RequestParam("oper") String oper) {
    	Map<String, Object> map = new HashMap<String, Object>(); 
        try {
            ROrderDetailsRequest dto = new ROrderDetailsRequest(); 
            dto.setOrderId(orderId); 
            dto.setOper(oper);
            if(oper.equals("02")){
            	dto.setBackId(Long.valueOf(backId));
            }
            ROrdCartsChkResponse  resp = new ROrdCartsChkResponse();
            resp = ordBackGdsRSV.queryOrdOperChk(dto);
            map.put("flag",resp.isStatus());
            map.put("msg",resp.getMsg());
        } catch (Exception e) {
        	LogUtil.error(MODULE, e.getMessage(),e);
            map.put("flag", false);
            map.put("msg",e.getMessage());
        }
        return map; 
    } 
    
    /**
     * returnApply:(退款申请提交页面). <br/> 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/returnApply/{orderId}")
    public String returnApply(Model model, @PathVariable("orderId") String orderId) {
    	ROrderBackReq dto = new ROrderBackReq();
        if (StringUtil.isBlank(orderId)) {
            throw new BusinessException("order.orderid.null.error");
        }
        dto.setOrderId(orderId);
        RBackApplyOrdResp resp = new RBackApplyOrdResp();
        resp = ordBackGdsRSV.queryBackOrderSub(dto);
        
        // 订单id
        model.addAttribute("orderId", orderId);
        // 子订单相关字段
        model.addAttribute("backApplyOrdSubResps", resp.getrBackApplyOrdSubResps());
        return "/order/point/return/return-apply";
    } 
    
    /**
     * returnApply:(退款申请保存). <br/> 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/saveBackMoneyApply")
    public EcpBaseResponseVO saveBackMoneyApply(Model model, RBackApplyReqVO vo) {
    	LogUtil.info(MODULE, "===========================退款申请开始===============================");
    	EcpBaseResponseVO resp = new EcpBaseResponseVO(); 
        try {
        	//后场服务所需要的DTO；
        	RBackApplyReq dto = vo.toBaseInfo(RBackApplyReq.class);
            ObjectCopyUtil.copyObjValue(vo, dto, "", false); 
            dto.setrBackOrdSubReqs(vo.getrBackOrdSubReqs());
        	ordBackMoneyRSV.saveBackMoneyApply(dto);
        	resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch(Exception e){
		    e.printStackTrace();
		    LogUtil.error(MODULE, "退款申请失败");
		    resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
		    resp.setResultMsg(e.getMessage()!=null?e.getMessage():"系统异常");
		}
		LogUtil.info(MODULE, "========================退款申请结束=============================");
		return resp; 
    } 
    
    /**
     * returnChild:(子订单申请退货页面页面). <br/> 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/returnChild/{orderId}")
    public String returnChild(Model model, @PathVariable("orderId") String orderId) { 
    	ROrderBackReq dto = new ROrderBackReq();
        if (StringUtil.isBlank(orderId)) {
            throw new BusinessException("order.orderid.null.error");
        }
        dto.setOrderId(orderId);
        RBackApplyOrdResp resp = new RBackApplyOrdResp();
        resp = ordBackGdsRSV.queryBackOrderSub(dto);
        
        // 订单id
        model.addAttribute("orderId", orderId);
        //主订单相关字段
        model.addAttribute("sOrderDetailsMain", resp.getSOrderDetailsMain());
        // 子订单相关字段
        model.addAttribute("backApplyOrdSubResps", resp.getrBackApplyOrdSubResps());
        return "/order/point/return/return-child";
    }
    
    /**
     * saveSessionSub:(保存session子订单信息). <br/> 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveSessionSub")
    @ResponseBody
    public EcpBaseResponseVO saveSessionSub(Model model,RBackApplyReqVO vo,HttpServletRequest request) { 
    	EcpBaseResponseVO respVO = new EcpBaseResponseVO();
    	//行选中属性
		String itemCheck = "checked";
    	try {
    		LogUtil.info(MODULE, "===========================封装参数================================");
    		List<RBackApplyOrdSubResp> resp = new ArrayList<RBackApplyOrdSubResp>();
			if(!CollectionUtils.isEmpty(vo.getrBackApplyOrdSubResps())){
				for(RBackApplyOrdSubReqVO rBackApplyOrdSubReqVO : vo.getrBackApplyOrdSubResps()){
					//判断是否明细项目被选中
					if(StringUtils.isNotEmpty(rBackApplyOrdSubReqVO.getItemCheck()) && rBackApplyOrdSubReqVO.getItemCheck().equals(itemCheck)){
						RBackApplyOrdSubResp rBackApplyOrdSubResp = new RBackApplyOrdSubResp();
						ObjectCopyUtil.copyObjValue(rBackApplyOrdSubReqVO, rBackApplyOrdSubResp, "", false);
						resp.add(rBackApplyOrdSubResp);
					}
				}
			} 
			LogUtil.info(MODULE, "==========================保存session信息开始===============================================");
			//把子订单列表信息缓存起来 时间为半小时
			HttpSession session = request.getSession(); 
			Long staffId = new BaseInfo().getStaff().getId();
			session.setAttribute(OrdConstant.ORDER_SESSION_KEY_SUB+staffId, resp);
			session.setMaxInactiveInterval(OrdConstant.ORDER_SESSION_TIME);
			
			LogUtil.info(MODULE, "==========================保存session信息完毕===============================================");

			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		}catch(Exception e){
			LogUtil.error(MODULE, e.getMessage(),e);
			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			respVO.setResultMsg(e.getMessage());
		}
		
		return respVO;
    }
    
    /**
     * returnApplySub:(退货申请提交页面). <br/> 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/returnApplySub/{orderId}")
    public String returnApplySub(Model model, @PathVariable("orderId") String orderId,HttpServletRequest request) { 
    	try {
    		HttpSession session = request.getSession();
    		RBackApplyOrdResp resp = new RBackApplyOrdResp();
    		Long staffId = new BaseInfo().getStaff().getId();
    		LogUtil.info(MODULE, "==========================获取session信息===============================================");
    		List<RBackApplyOrdSubResp> rBackApplyOrdSubResps = (List<RBackApplyOrdSubResp>)session.getAttribute(OrdConstant.ORDER_SESSION_KEY_SUB+staffId); 
    		resp.setrBackApplyOrdSubResps(rBackApplyOrdSubResps);
			if(resp == null){
                return "redirect:/homepage";
            }
			// 订单id
	        model.addAttribute("orderId", orderId);
	        // 子订单相关字段
	        model.addAttribute("backApplyOrdSubResps", resp.getrBackApplyOrdSubResps());
		} catch (Exception e) {
			LogUtil.error(MODULE, "==================================展示退货申请提交页面出错============================");
			LogUtil.error(MODULE, e.getMessage(),e);
		}  
        return "/order/point/return/return-apply-sub";
    }
    
    /**
     * returnApply:(退货申请保存). <br/> 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveBackGdsApply")
    @ResponseBody
    public EcpBaseResponseVO saveBackGdsApply(Model model, RBackApplyReqVO vo) {
    	LogUtil.info(MODULE, "===========================退货申请开始===============================");
    	EcpBaseResponseVO resp = new EcpBaseResponseVO(); 
        try {
        	//后场服务所需要的DTO；
        	RBackApplyReq dto = vo.toBaseInfo(RBackApplyReq.class);
            ObjectCopyUtil.copyObjValue(vo, dto, "", false); 
            dto.setrBackOrdSubReqs(vo.getrBackOrdSubReqs());
            ordBackGdsRSV.saveBackGdsApply(dto);
        	resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch(Exception e){
		    e.printStackTrace();
		    LogUtil.error(MODULE, "退货申请失败");
		    resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
		    resp.setResultMsg(e.getMessage()!=null?e.getMessage():"系统异常");
		}
		LogUtil.info(MODULE, "========================退货申请结束=============================");
		return resp; 
    }
    
    /**
     * returnBack:(退货发货页面). <br/> 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/returnBack/{backId}/{orderId}")
    public String returnBack(Model model, @PathVariable("backId") String backId, @PathVariable("orderId") String orderId) {
    	//后场服务所需要的DTO；
    	ROrderBackReq dto = new ROrderBackReq();
    	dto.setBackId(Long.valueOf(backId));
    	dto.setOrderId(orderId);
    	ROrderBackDetailResp resp  = ordBackGdsRSV.queryBackGdsDetails(dto);
        model.addAttribute("rBackApplyResp", resp.getrBackApplyResp());
        model.addAttribute("rBackGdsResps", resp.getrBackGdsResps());
        return "/order/point/return/return-back";
    }
    
    /**
     * returnBack:(退货发货保存). <br/> 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveBackGdsSend")
    @ResponseBody
    public EcpBaseResponseVO saveBackGdsSend(Model model, RBackConfirmReqVO vo) {
    	LogUtil.info(MODULE, "===========================退货发货开始===============================");
    	EcpBaseResponseVO resp = new EcpBaseResponseVO(); 
        try {
        	//后场服务所需要的DTO；
        	RBackConfirmReq dto = vo.toBaseInfo(RBackConfirmReq.class);
            ObjectCopyUtil.copyObjValue(vo, dto, "", false); 
            ordBackGdsRSV.saveBackGdsSend(dto);
        	resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch(Exception e){
		    e.printStackTrace();
		    LogUtil.error(MODULE, "退货发货失败");
		    resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
		    resp.setResultMsg(e.getMessage()!=null?e.getMessage():"系统异常");
		}
		LogUtil.info(MODULE, "========================退货发货结束=============================");
		return resp; 
    } 
    
    
    /**
     * uploadImage:(上传图片). <br/> 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadimage")
    @ResponseBody
    @NativeJson(true)
    private String uploadImage(Model model,HttpServletRequest req, HttpServletResponse rep) {
        JSONObject obj = new JSONObject();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
        // 获取图片
        Iterator<MultipartFile> files = multipartRequest.getFileMap().values().iterator();
        MultipartFile file = null;
        if (files.hasNext()) {
            file = files.next();
        }
        Iterator<String> ids = multipartRequest.getFileMap().keySet().iterator();
        String id = null;
        if (ids.hasNext()) {
            id = ids.next();
        }
        try {
            if (file == null) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "请选择上传文件！");
                LogUtil.error(MODULE, "请选择上传文件！");
                return obj.toJSONString();
            }
            String fileName = file.getOriginalFilename();
            String extensionName = "." + getExtensionName(fileName);

            /** 支持文件拓展名：.jpg,.png,.jpeg,.gif,.bmp */
            boolean flag = Pattern.compile("\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$")
                    .matcher(extensionName.toLowerCase()).find();
            if (!flag) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "请选择图片文件(.jpg,.png,.jpeg,.gif)!");
                LogUtil.error(MODULE, "上传图片失败,原因---请选择图片文件(.jpg,.png,.jpeg,.gif)!");
                return obj.toJSONString();
            }
            
            byte[] datas = inputStream2Bytes(file.getInputStream());
            if(datas.length>5*1024*1024){
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "上传的图片不能大于5M！");
                LogUtil.error(MODULE, "图片上传失败，上传的图片必须小于5M！");
                return obj.toJSONString();
            }
            fileName = Math.random()+"";
            String vfsId = ImageUtil.upLoadImage(datas, fileName);
            resultMap.put("vfsId", vfsId);
            resultMap.put("imageName", fileName);
            resultMap.put("id", id);
            resultMap.put("imagePath", new AiToolUtil().genImageUrl(vfsId,"150x150!"));
            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            obj.put("message", "保存成功!");
            obj.put("map", resultMap);
        } catch (BusinessException e) {
            LogUtil.error(MODULE,"上传图片出错,原因---"+e.getMessage(), e);
            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            obj.put("message", "保存失败，图片服务器异常，请联系管理员!");
        } catch (IOException e) {
            LogUtil.error(MODULE,"上传图片出错,原因---"+e.getMessage(), e);
            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            obj.put("message", "保存失败，图片服务器异常，请联系管理员!");
        }
        return obj.toJSONString();
    } 
   
    /**
     * getExtensionName:(获取文件拓展名). <br/> 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
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
    
    /**
     * inputStream2Bytes:(将InputStream转换成byte数组). <br/> 
     * @param model
     * @param orderid
     * @return
     * @throws Exception
     */
    private byte[] inputStream2Bytes(InputStream in) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        while ((count = in.read(data, 0, 4096)) != -1)
            outStream.write(data, 0, count);
        data = null;
        return outStream.toByteArray();
    }
    
}

