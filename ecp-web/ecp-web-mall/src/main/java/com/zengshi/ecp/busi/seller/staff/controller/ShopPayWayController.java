package com.zengshi.ecp.busi.seller.staff.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.mvc.annotation.NativeJson;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.seller.staff.vo.PayWayVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.PayShopCfgReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayShopCfgResDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayWayReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayWayResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopPayWayRSV;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年4月26日上午11:36:47  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 店铺支付通道
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value="/seller/payway")
public class ShopPayWayController extends EcpBaseController{

   @Resource
   private IShopPayWayRSV shopPayWayRSV;
   private static String MODULE = ShopPayWayController.class.getName();

    @RequestMapping("")
    public String index(Model model)throws BusinessException{
        
        PayWayReqDTO dto = new PayWayReqDTO();
        List<PayWayResDTO> list = shopPayWayRSV.getShopPayWay(dto);
        model.addAttribute("paywaylist", list);
        return "/seller/staff/payway/seller-payway";
    }
    
    @RequestMapping("list")
    public String list(Model model,PayWayVO payWayVO)throws BusinessException{
     
        PayShopCfgReqDTO payShopCfgReqDTO = new PayShopCfgReqDTO();
        ObjectCopyUtil.copyObjValue(payWayVO, payShopCfgReqDTO, null, false);
        payShopCfgReqDTO.setPayWay(payWayVO.getPayWay());
        payShopCfgReqDTO.setPageNo(payWayVO.getPageNumber());
        payShopCfgReqDTO.setPageSize(payWayVO.getPageSize());
        PageResponseDTO<PayShopCfgResDTO>  list = shopPayWayRSV.queryPayShopCfgList(payShopCfgReqDTO);
        
        model.addAttribute("page", list);
        model.addAttribute("list", list.getResult());
        
        //判断新增按钮
        PayWayReqDTO dto = new PayWayReqDTO();
        dto.setId(payWayVO.getPayWay());
        List<PayWayResDTO> listway = shopPayWayRSV.getShopPayWay(dto);
        if(listway.size()==list.getCount()){
            model.addAttribute("ifadd", false);
        }else{
            model.addAttribute("ifadd", true);
        }
      
        return "/seller/staff/payway/list/seller-payway-list";
    }
    
    @RequestMapping("edit")
    @ResponseBody
    public EcpBaseResponseVO edit(Model model,PayWayVO payWayVO)throws BusinessException{
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        PayShopCfgReqDTO payShopCfgReqDTO = new PayShopCfgReqDTO();
        ObjectCopyUtil.copyObjValue(payWayVO, payShopCfgReqDTO, null, false);
        shopPayWayRSV.updatePayShopCfg(payShopCfgReqDTO);
        vo.setResultFlag("ok");
        vo.setResultMsg("操作成功");
        return vo;
        
    }
    
    @RequestMapping("editpage/{shopId}-{payway}")
    public String editPage(Model model,@PathVariable("shopId") Long shopId,@PathVariable("payway") String payway)throws BusinessException{
        PayShopCfgReqDTO payShopCfgReqDTO = new PayShopCfgReqDTO();
        payShopCfgReqDTO.setPageNo(0);
        payShopCfgReqDTO.setPageSize(1);
        payShopCfgReqDTO.setShopId(shopId);
        payShopCfgReqDTO.setPayWay(payway);
        PageResponseDTO<PayShopCfgResDTO>  list = shopPayWayRSV.queryPayShopCfgList(payShopCfgReqDTO);
        String fileName="";
		if (list.getCount() >= 1) {
			List<PayShopCfgResDTO> paylist = list.getResult();
			if (StringUtil.isNotBlank(paylist.get(0).getCerName())) {
				try {
					model.addAttribute("CerNameFile", FileUtil.getFileName(paylist.get(0).getCerName()));
				} catch (Exception e) {
					model.addAttribute("changeInputCer", "1");
					LogUtil.error(MODULE, "", e);
				}

			}
			if (StringUtil.isNotBlank(paylist.get(0).getKeyName())) {
				try {
					model.addAttribute("KeyNameFile", FileUtil.getFileName(paylist.get(0).getKeyName()));
				} catch (Exception e) {
					model.addAttribute("changeInput", "1");
					LogUtil.error(MODULE, "", e);
				}

			}
			model.addAttribute("dto", paylist.get(0));

		}
        
        return "/seller/staff/payway/seller-payway-edit";
    }
    
    @RequestMapping("add")
    @ResponseBody
    public EcpBaseResponseVO add(Model model,PayWayVO payWayVO)throws BusinessException{
        //判断支付通道是否已经配置
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        PayShopCfgReqDTO payShopCfgReqDTO = new PayShopCfgReqDTO();
        payShopCfgReqDTO.setPayWay(payWayVO.getPayWay());
        payShopCfgReqDTO.setShopId(payWayVO.getShopId());
        payShopCfgReqDTO.setPageNo(0);
        payShopCfgReqDTO.setPageSize(1);
        PageResponseDTO<PayShopCfgResDTO>  list = shopPayWayRSV.queryPayShopCfgList(payShopCfgReqDTO);
        if(list.getCount()>0){
        List<PayShopCfgResDTO> listr = list.getResult();
        for (PayShopCfgResDTO payShopCfgResDTO : listr) {
            if(payWayVO.getPayWay().equals(payShopCfgResDTO.getPayWay())){
                throw new BusinessException("当前支付通道已经配置");
            }
        }
          }
        
        ObjectCopyUtil.copyObjValue(payWayVO, payShopCfgReqDTO, null, false);
        payShopCfgReqDTO.setUseFlag("1");
        shopPayWayRSV.addPayShopCfg(payShopCfgReqDTO);
        vo.setResultFlag("ok");
        vo.setResultMsg("保存成功");
        return vo;
    }
    
    @RequestMapping("addpage/{shopId}")
    public String addPage(Model model,@PathVariable("shopId") Long shopId)throws BusinessException{
        
        PayWayReqDTO dto = new PayWayReqDTO();
        List<PayWayResDTO> list = shopPayWayRSV.getShopPayWay(dto);
        model.addAttribute("paywaylist", list);
        model.addAttribute("shopId", shopId);
        return "/seller/staff/payway/seller-payway-add";
    }
    
    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    @NativeJson(true)
    public String uploadFile(Model model,@RequestParam(value = "uploadFileObj", required = false) MultipartFile uploadFileObj,
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
           
            /** 支持文件拓展名：.doc,.pdf,.zip,.rar */
    /*        boolean flag = Pattern
                    .compile("\\.(doc)$|\\.(docx)$|\\.(pdf)$|\\.(zip)$|\\.(rar)$")
                    .matcher(extensionName.toLowerCase()).find();
            if (!flag) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "请选择图片文件(.doc,.docx,.pdf,.zip,.rar)！");
                LogUtil.error(MODULE,"上传图片失败,原因---请选择图片文件(.doc,.docx,.pdf,.zip,.rar)!");
                return obj.toJSONString();
            }*/
            byte[] datas = uploadFileObj.getBytes();
    

            String vfsId = FileUtil.saveFile(datas, vfsName,uploadFileObj.getContentType());
            resultMap.put("vfsId", vfsId);
            resultMap.put("vfsName", vfsName);
            resultMap.put("vfsUrl", ImageUtil.getStaticDocUrl(vfsId, "doc"));
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