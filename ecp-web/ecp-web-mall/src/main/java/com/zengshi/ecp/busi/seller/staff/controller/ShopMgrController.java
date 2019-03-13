package com.zengshi.ecp.busi.seller.staff.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.seller.staff.vo.SaveShopRuleVO;
import com.zengshi.ecp.busi.seller.staff.vo.ShopInfoVO;
import com.zengshi.ecp.busi.seller.staff.vo.ShopVipLevelVO;
import com.zengshi.ecp.busi.seller.staff.vo.ShopVipRealVO;
import com.zengshi.ecp.busi.seller.staff.vo.ShopVipRuleVO;
import com.zengshi.ecp.busi.seller.staff.vo.SubAcctRoleVO;
import com.zengshi.ecp.busi.seller.staff.vo.SubAcctVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaff2RoleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaff2RoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustSubInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.SellerResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipLevelReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipLevelResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRealReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRealResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthRelManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICompanyManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IRoleManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopSubAuthStaffRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.system.filter.SellerLocaleUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 店铺管理：包括基本信息设置、会员管理、子帐号管理<br>
 * Date:2016-4-11下午5:49:17  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/seller/shopmgr")
public class ShopMgrController extends EcpBaseController{

    @Resource 
    private  IShopManageRSV shopManageRSV;
    
    @Resource
    private IShopSubAuthStaffRSV shopSubAuthStaffRSV;
    
    @Resource
    private IRoleManageRSV roleManageRSV;
    

    @Resource
    private IShopInfoRSV shopInfoRSV;

    @Resource
    private ICustManageRSV custManageRSV;
    
    @Resource
    private IAuthRelManageRSV authRelManageRSV;
    
    @Resource
    private ICompanyManageRSV companyManageRSV;
    /**
     * 
     * shopvip:(会员信息列表初始化). <br/> 
     
     * @author chenyz3 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/index")
    public String shopVip(Model model){
        ShopVipRealReqDTO realReqDTO = new ShopVipRealReqDTO();//后场服务所需要的DTO
        realReqDTO.setPageNo(1);
        realReqDTO.setPageSize(10);
        SellerResDTO sellerResDTO = SellerLocaleUtil.getSeller(); //通过工具类获取登录会员信息
        realReqDTO.setShopId(sellerResDTO.getShoplist().get(0).getId());//店铺id
        PageResponseDTO<ShopVipRealResDTO> page = shopManageRSV.listShopVipReal(realReqDTO); //获取店铺会员信息
        model.addAttribute("shopPage", page); //返回查询结果
        ShopVipLevelReqDTO reqLevDTO = new ShopVipLevelReqDTO();//以下代码目的是获取会员等级设置好的会员名称
        reqLevDTO.setShopId(sellerResDTO.getShoplist().get(0).getId());
        reqLevDTO.setPageNo(0);//不分页查询
        PageResponseDTO<ShopVipLevelResDTO> pageLevel = shopManageRSV.listShopVipLevel(reqLevDTO);
        model.addAttribute("levPage", pageLevel); //返回查询结果
        return "/seller/staff/shopmgr/member-index";
    }
    /**
     * 
     * isNumber:(使用正则表达式判断是否是数字). <br/> 
     
     * @author chenyz3 
     * @param str
     * @return 
     * @since JDK 1.6
     */
    public static boolean isNumber(String str){
        Pattern pattern = Pattern.compile("[0-9]{0,12}");//验证12位以内的数字
        Matcher isNum = pattern.matcher(str);
        if(!isNum.matches()){
            return false;
        }
        return true;
    }
    /**
     * 
     * gradesave:(会员等级设置). <br/> 
     * @author chenyz 
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/gradesave")
    @ResponseBody
    public EcpBaseResponseVO gradeSave(@Valid SaveShopRuleVO vo){
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        //SellerResDTO sellerResDTO = SellerLocaleUtil.getSeller(); //通过工具类获取登录会员信息
        int i = 1; //VipLevelCode的初始值
        if(vo != null && CollectionUtils.isNotEmpty(vo.getRuleArr())){//循环取出表格数据放到会员等级关系表中
            boolean flag = false;
            for(ShopVipRuleVO shopvr : vo.getRuleArr()){
                if (StringUtil.isBlank(shopvr.getVipLevelName())) {
                    res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                    res.setResultMsg("会员等级名称不能为空");
                    flag = true;
                    break;
                }
                if(shopvr.getVipLevelName().length() > 16){
                    res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                    res.setResultMsg("会员等级名称不能超过16位");
                    flag = true;
                    break;
                    
                }
                if (StringUtil.isBlank(shopvr.getDiscount() + "")) {
                    res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                    res.setResultMsg("折扣率不能为空");
                    flag = true;
                    break;
                }
                if(! isNumber(shopvr.getDiscount() + "")){
                    res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                    res.setResultMsg("折扣率为不超过12位数的数字");
                    flag = true;
                    break;
                }
                if(!isNumber(shopvr.getOrderPay() + "")){
                    res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                    res.setResultMsg("交易金额为不超过12位数的数字");
                    flag = true;
                    break;
                }
                if(!isNumber(shopvr.getTradesNum() + "")){
                    res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                    res.setResultMsg("交易次数为不超过12位数的数字");
                    flag = true;
                    break;
                }
                if (StringUtil.isBlank(shopvr.getOrderPay() + "") && StringUtil.isBlank(shopvr.getTradesNum() + "")) {
                    res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                    res.setResultMsg("交易次数与交易金额不能都为空");
                    flag = true;
                    break;
                }
            }
            if (flag) {
                return res;
            }
            //保存数据前先删除表中数据
            ShopVipLevelReqDTO reqVipDel = new ShopVipLevelReqDTO();
            reqVipDel.setShopId(vo.getShopId());
            shopManageRSV.deleteShopVipLevel(reqVipDel);//通过shopId删除ShopVipLevel数据
            ShopVipRuleReqDTO ruleReqDel = new ShopVipRuleReqDTO();
            ruleReqDel.setShopId(vo.getShopId()+"");
            shopManageRSV.deleteShopVipRule(ruleReqDel);//通过shopId删除ShopVipRule数据
            //开始保存数据
            List<ShopVipRuleVO> saveList = new ArrayList<>();
            for(ShopVipRuleVO shopvr : vo.getRuleArr()){
            	int count = 1;
            	if(!CollectionUtils.isEmpty(saveList)){
            		for (ShopVipRuleVO shopVipRuleVO : saveList) {
            			if(shopvr.getVipLevelName().equals(shopVipRuleVO.getVipLevelName())
            				&&shopvr.getOrderPay().equals(shopVipRuleVO.getOrderPay())){
            				count++;
            			}
					}
            	}
            	if(count==1){
            		saveList.add(shopvr);
            		ShopVipLevelReqDTO reqdto = new ShopVipLevelReqDTO();
            		ObjectCopyUtil.copyObjValue(shopvr, reqdto, null, false);
            		reqdto.setShopId(vo.getShopId());//登录会员的ShopId
            		reqdto.setOrderPay(reqdto.getOrderPay());
            		reqdto.setVipLevelCode("vip_" + reqdto.getShopId() + "_" + i); //用户每设置一个等级，就追加一个VipLevelcode
            		i++;
            		ShopVipRuleReqDTO ruleReq = new ShopVipRuleReqDTO();
            		ruleReq.setVipLevelCode(reqdto.getVipLevelCode());
            		ruleReq.setShopId(reqdto.getShopId() + "");
            		ruleReq.setDiscount(Long.parseLong(shopvr.getDiscount()));
            		ruleReq.setStatus("1");
            		shopManageRSV.saveShopVipLevel(reqdto);
            		shopManageRSV.saveShopVipRule(ruleReq);
            	}
            }
            res.setResultFlag("ok");
            res.setResultMsg("操作成功");
        }
        return res;
    } 
    /**
     * 
     * shopVipRuleList:(初始化VIP等级设置列表). <br/> 
     * @author chenyz3 
     * @param model
     * @param VO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/gradelist")
    @ResponseBody
    public List<ShopVipLevelResDTO> shopVipRuleList(Model model,ShopVipLevelVO VO ){
        List<ShopVipLevelResDTO> list = new ArrayList<ShopVipLevelResDTO>();
        ShopVipLevelReqDTO reqDTO = new ShopVipLevelReqDTO();
        reqDTO.setShopId(VO.getShopId());
        reqDTO.setPageNo(0);//不分页查询
        PageResponseDTO<ShopVipLevelResDTO> page = shopManageRSV.listShopVipLevel(reqDTO);
        if(page.getResult()==null){
            return list;
        }
        return page.getResult();
    }
    /**
     * 
     * dataItemDelete:(等级设置表删除一条设置操作。根据shopId和vipLevelCode确定要删除记录). <br/> 
     * @author chenyz3 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/gradelist/delete")
    @ResponseBody
    public EcpBaseResponseVO dataItemDelete(@Valid ShopVipLevelVO vo) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        ShopVipLevelReqDTO reqdtodelete = new ShopVipLevelReqDTO();
        reqdtodelete.setVipLevelCode(vo.getVipLevelCode());//vipLevelCode是唯一的
        try {
            shopManageRSV.deleteShopLevelByLevelCode(reqdtodelete);
            vo.setResultFlag("ok");
            vo.setResultMsg("操作成功");
        } catch (BusinessException e) {
            res.setResultFlag("fail");
            res.setResultMsg("操作失败" + e.getMessage());
        }
        return res;
    }
    /**
     * 
     * shopVipRealList:(查询店铺会员列表). <br/> 
     * 
     * @author chenyz3 
     * @param model
     * @param shopvip
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/shopviplist")
    public String shopVipRealList(Model model,ShopVipRealVO shopvip){
        ShopVipRealReqDTO realReqDTO = shopvip.toBaseInfo(ShopVipRealReqDTO.class);//后场服务所需要的DTO
        realReqDTO.setShopId(shopvip.getShopId());//店铺id
        realReqDTO.setStaffCode(shopvip.getStaffCode());//会员名称
        realReqDTO.setCustLevelCode(shopvip.getCustLevelCode()); //会员级别
        if(shopvip.getPayMoneyFrom() != null){
            realReqDTO.setPayMoneyFrom(shopvip.getPayMoneyFrom() * 100);//交易额范围
        }
        if(shopvip.getPayMoneyEnd() != null){
            realReqDTO.setPayMoneyEnd(shopvip.getPayMoneyEnd() * 100);
        }
        realReqDTO.setTradesNumFrom(shopvip.getTradesNumFrom());//交易次数范围
        realReqDTO.setTradesNumEnd(shopvip.getTradesNumEnd());
        PageResponseDTO<ShopVipRealResDTO> page = shopManageRSV.listShopVipReal(realReqDTO); //获取店铺会员信息
        model.addAttribute("shopPage", page); //返回会员信息查询结果
        return "/seller/staff/shopmgr/tab/page/member2-list";
    }
    /**
     * 
     * subAcctList:(查询子帐号列表数据). <br/> 
     * 
     * @author huangxl5
     * @param model
     * @param subAcctVO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @RequestMapping(value="/subacctlist")
    public String subAcctList(Model model,SubAcctVO subAcctVO) throws BusinessException{
        
        //获取卖家店铺信息
        SellerResDTO sellerResDTO = SellerLocaleUtil.getSeller();
        //设置查询条件
        CustInfoReqDTO cust = new CustInfoReqDTO();
        long count = 0L;
        if (subAcctVO.getShopId() == null || subAcctVO.getShopId() == 0L) {
        	cust.setShopId(sellerResDTO.getShoplist().get(0).getId());//设置店铺id
        	model.addAttribute("shopId", sellerResDTO.getShoplist().get(0).getId());
        	count = shopSubAuthStaffRSV.countShopSubAuthStaff(sellerResDTO.getShoplist().get(0).getId());
        } else {
        	cust.setShopId(subAcctVO.getShopId());
        	model.addAttribute("shopId", subAcctVO.getShopId());
        	count = shopSubAuthStaffRSV.countShopSubAuthStaff(subAcctVO.getShopId());
        }
        CustSubInfoReqDTO custSub = new CustSubInfoReqDTO();
        custSub.setStaffCode(subAcctVO.getStaffCode());//用户名
        custSub.setStatus(subAcctVO.getStatus());//状态
        custSub.setPageNo(0);//查所有数据
        //调用业务方法，查询数据
        PageResponseDTO<CustInfoResDTO> page = shopSubAuthStaffRSV.listShopSubAuthStaff(cust,custSub);
        model.addAttribute("subAcctPage", page);//结果集
        model.addAttribute("staffCode", subAcctVO.getStaffCode());
        model.addAttribute("status", subAcctVO.getStatus());
        model.addAttribute("defaultShopId", sellerResDTO.getShoplist().get(0).getId());
        String maxCount = BaseParamUtil.fetchParamValue("SHOP_SUB_ACCT_COUNT", "MAX_COUNT");
        model.addAttribute("maxCount", maxCount);//最大可创建数量
        model.addAttribute("controlSub", Long.parseLong(maxCount) - count);//剩余可创建的数量
        return "/seller/staff/shopmgr/sub-acct";
    }
    /**
     * 
     * subAcctDel:(删除子帐号). <br/> 
     * 
     * @author huangxl5
     * @param subAcctVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/subacct/del")
    @ResponseBody
    public EcpBaseResponseVO subAcctDel(SubAcctVO subAcctVO){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try {
            /*调用业务方法，删除子帐号*/
            shopSubAuthStaffRSV.deleteShopSubAuthStaff(subAcctVO.getStaffId());
            vo.setResultFlag("ok");
            vo.setResultMsg("操作成功");
        } catch (Exception e) {
            vo.setResultFlag("fail");
            vo.setResultMsg("操作失败" + e.getMessage());
        }
        return vo;
    }
    
    /**
     * 
     * subAcctDel:(使子帐号失效). <br/> 
     * 
     * @author huangxl5
     * @param subAcctVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/subacct/disable")
    @ResponseBody
    public EcpBaseResponseVO subAcctDisable(SubAcctVO subAcctVO){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try {
            /*调用业务方法，使子帐号失效*/
            shopSubAuthStaffRSV.disableShopSubAuthStaff(subAcctVO.getStaffId());
            vo.setResultFlag("ok");
            vo.setResultMsg("操作成功");
        } catch (Exception e) {
            vo.setResultFlag("fail");
            vo.setResultMsg("操作失败" + e.getMessage());
        }
        return vo;
    }
    
    /**
     * 
     * subAcctDel:(使子帐号生效). <br/> 
     * 
     * @author huangxl5
     * @param subAcctVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/subacct/enable")
    @ResponseBody
    public EcpBaseResponseVO subAcctEnable(SubAcctVO subAcctVO){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try {
            /*调用业务方法，使子帐号生效*/
            shopSubAuthStaffRSV.enableShopSubAuthStaff(subAcctVO.getStaffId());
            vo.setResultFlag("ok");
            vo.setResultMsg("操作成功");
        } catch (Exception e) {
            vo.setResultFlag("fail");
            vo.setResultMsg("操作失败" + e.getMessage());
        }
        return vo;
    }
    
    /**
     * 
     * addSubAcct:(跳转到新增子帐号页面). <br/> 
     * 
     * @author huangxl5
     * @param model
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @RequestMapping(value="/subacct/add")
    public String addSubAcct(Model model,SubAcctVO subAcctVO) throws BusinessException{
        /*查询角色列表*/
        AuthRoleReqDTO authRoleReq =  new AuthRoleReqDTO();
        authRoleReq.setPageNo(0);//设为0，查询全部
        authRoleReq.setSysCode(StaffConstants.PublicParam.SYS_CODE_SELLER);//只查询管理平台的角色
        authRoleReq.setStatus(StaffConstants.PublicParam.STATUS_ACTIVE);//查询有效的角色
        PageResponseDTO<AuthRoleResDTO> authRoleRes = roleManageRSV.listAuthRole(authRoleReq);
        model.addAttribute("rolePage", authRoleRes);
        /*查询剩余可创建子帐号数量*/
        long count = shopSubAuthStaffRSV.countShopSubAuthStaff(subAcctVO.getShopId());
        String maxCount = BaseParamUtil.fetchParamValue("SHOP_SUB_ACCT_COUNT", "MAX_COUNT");
        model.addAttribute("subCount", Long.parseLong(maxCount) - count);//剩余可创建的数量
        model.addAttribute("maxCount", maxCount);//最大可创建数量
        model.addAttribute("shopId", subAcctVO.getShopId());
        return "/seller/staff/shopmgr/sub-acct-add";
    }
    
    /**
     * 
     * saveSubAcct:(保存子帐号信息). <br/> 
     * 
     * @author huangxl5
     * @param subAcctVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/subacct/save")
    @ResponseBody
    public EcpBaseResponseVO saveSubAcct(SubAcctVO subAcctVO){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        if (subAcctVO.getShopId() == null || subAcctVO.getShopId() == 0L) {
        	vo.setResultFlag("fail");
            vo.setResultMsg("操作失败：没有shopId");
            return vo;
        }
        //获取卖家店铺信息
        ShopInfoResDTO shopRes = shopInfoRSV.findShopInfoByShopID(subAcctVO.getShopId());
        try {
            /*设置参数信息*/
            CustInfoReqDTO cust = new CustInfoReqDTO();
            cust.setShopId(subAcctVO.getShopId());
            cust.setCompanyId(shopRes.getCompanyId());
            cust.setStaffCode(cust.getStaff().getStaffCode());
            CustSubInfoReqDTO custSub = new CustSubInfoReqDTO();
            custSub.setCustName(subAcctVO.getCustName());//真实姓名
            custSub.setStaffPasswd(subAcctVO.getStaffPwd());//密码
            custSub.setGender(subAcctVO.getGender());//性别
            custSub.setStaffCode(subAcctVO.getStaffCode());//用户名
            if (StringUtil.isNotBlank(subAcctVO.getRoleIds())) {
                custSub.setRoleIds(subAcctVO.getRoleIds().substring(0, subAcctVO.getRoleIds().length() - 1));//角色ids
            }
            /*调用业务方法，保存子帐号信息*/
            shopSubAuthStaffRSV.saveShopSubAuthStaff(cust, custSub);
            vo.setResultFlag("ok");
            vo.setResultMsg("操作成功");
        } catch (Exception e) {
            vo.setResultFlag("fail");
            vo.setResultMsg("操作失败" + e.getMessage());
        }
        return vo;
    }
    /**
     * 
     * remove:(根据ID删除会员关系). <br/> 
     * 
     * @author chenyz3 
     * @param model
     * @param id
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/remove")
    @ResponseBody
    public EcpBaseResponseVO remove(Model model, @RequestParam("id") Long id) {
        ShopVipRealReqDTO realReqDTO = new ShopVipRealReqDTO(); //后场服务所需要的DTO
        realReqDTO.setId(id);                                   //传入id（通过id删除）
        shopManageRSV.deleteShopVipReal(realReqDTO);            //调用后场方法删除记录
        EcpBaseResponseVO vo = new EcpBaseResponseVO();         //处理结果返回对象
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);//返回删除结果
        return vo;
    }
    /**
     * 
     * statusEdit:(修改会员状态（失效/生效）). <br/> 
     * 
     * @author chenyz3 
     * @param model
     * @param status
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/statusEdit")
    @ResponseBody
    public EcpBaseResponseVO statusEdit(Model model, @RequestParam("status") String status, @RequestParam("id") Long id){
        ShopVipRealReqDTO realReqDTO = new ShopVipRealReqDTO();     //后场服务所需要的DTO
        realReqDTO.setStatus(status);                               //传入要更改的状态
        realReqDTO.setId(id); 
        shopManageRSV.updateOffOrOnShopVipReal(realReqDTO);         //调用后场方法更新状态
        EcpBaseResponseVO vo = new EcpBaseResponseVO();             //处理结果返回对象
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);    //返回删除结果
        return vo;
    }

    /**
     * 
     * init:(初始化店铺设置信息). <br/> 
     * @author chenyz3 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    
    @RequestMapping(value="/shopset") 
    public String shopShop(Model model, @RequestParam(value="shopId",required=false) Long shopId){
        SellerResDTO sellerResDTO = SellerLocaleUtil.getSeller(); //通过工具类获取登录会员信息
        //通过sellerResDTO.getShoplist().get(0)返回第一个List<ShopInfoResDTO>对象，getId()获取shopid
        if(shopId==null||shopId==0){
        	shopId = sellerResDTO.getShoplist().get(0).getId();
        }
        ShopInfoResDTO shopRes = shopInfoRSV.findShopInfoByShopID(shopId);//通过店铺id查询店铺信息
        model.addAttribute("shopdto", shopRes);//把查询结果放入model
        model.addAttribute("useVip", shopRes.getIsUseVip());//卖家不能操作是否开启店铺VIP，所以页面中单独取出。
        CompanyInfoResDTO company = getComPanyInfo(shopRes.getCompanyId());//通过企业ID查询企业信息
        //CompanyInfoResDTO parent = getComPanyParent(company.getParentId());//通过父企业ID查询父企业名字
        model.addAttribute("companySignInfo", company);//返回企业信息
        //model.addAttribute("parentName", parent);//返回父企业名字
        model.addAttribute("shopId", shopId);
        return "/seller/staff/shopmgr/shopset-index";
    }
    /**
     * 
     * getComPanyInfo:(获取companyName). <br/> 
     * 
     * 
     * @author chenyz3 
     * @param parentId
     * @return 
     * @since JDK 1.6
     */
    public CompanyInfoResDTO getComPanyParent(Long parentId){
        
        if(parentId != null && parentId != 0L){
            CompanyInfoResDTO parent = companyManageRSV.findCompanyInfoById(parentId);
            return parent;
        }
        return null;
    }
    /**
     * 
     * getComPanyInfo:(获取company信息). <br/> 
     
     * @author chenyz3 
     * @param companyId
     * @return 
     * @since JDK 1.6
     */
    public CompanyInfoResDTO getComPanyInfo(Long companyId){
        CompanyInfoResDTO res = companyManageRSV.findCompanyInfoById(companyId);
        return res;
    }
    /**
     * 
     * saveShopInfo:(保存店铺设置信息). <br/> 
     * 
     * @author chenyz3 
     * @param model
     * @param shopInfoVO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @RequestMapping(value="/shopset/save") 
    @ResponseBody
    public EcpBaseResponseVO saveShopInfo( ShopInfoVO shopInfoVO)throws BusinessException{
        EcpBaseResponseVO resultVo = new EcpBaseResponseVO();
        if (shopInfoVO.getShopId() == null && shopInfoVO.getShopId() == 0L) {
        	throw new BusinessException("店铺ID缺失");
        }
        try {
            ShopInfoReqDTO req = new ShopInfoReqDTO();//创建入参对象
            ObjectCopyUtil.copyObjValue(shopInfoVO, req, null, false);//把shopInfoVO的数据复制到入参对象中
            //向入参对象增加上传图片返回的key值
            if(!StringUtil.isBlank(shopInfoVO.getLogoMongodbKey()))
            {
                req.setLogoPath(shopInfoVO.getLogoMongodbKey());
            }
            req.setId(shopInfoVO.getShopId());
            shopInfoRSV.updateShopInfo(req);//更新店铺信息表
            resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (Exception e) {
            resultVo.setResultFlag("fail");
            resultVo.setResultMsg("操作失败" + e.getMessage());
        }
        return resultVo;
    }

    
    /**
     * 
     * editSubAcct:(编辑子帐号). <br/> 
     * 
     * @author huangxl5
     * @param model
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @RequestMapping(value="/subacct/edit")
    public String editSubAcct(Model model,@RequestParam("staffId") Long staffId) throws BusinessException{
        /*查询用户信息*/
        CustInfoResDTO cust = custManageRSV.findCustInfoById(staffId);
        model.addAttribute("custInfo", cust);
        
        /*查询角色列表*/
        AuthRoleReqDTO authRoleReq =  new AuthRoleReqDTO();
        authRoleReq.setPageNo(0);//设为0，查询全部
        authRoleReq.setSysCode(StaffConstants.PublicParam.SYS_CODE_SELLER);//只查询管理平台的角色
        authRoleReq.setStatus(StaffConstants.PublicParam.STATUS_ACTIVE);//查询有效的角色
        PageResponseDTO<AuthRoleResDTO> authRoleRes = roleManageRSV.listAuthRole(authRoleReq);
        
        List<SubAcctRoleVO> resultList = new ArrayList<>();
        if (authRoleRes != null && CollectionUtils.isNotEmpty(authRoleRes.getResult())) {
            /*查询用户已分配的角色*/
            AuthStaff2RoleReqDTO staff2role = new AuthStaff2RoleReqDTO();
            staff2role.setStaffId(staffId);
            List<AuthStaff2RoleResDTO> list = authRelManageRSV.listStaffRoleRel(staff2role);
            for (AuthRoleResDTO role : authRoleRes.getResult()) {
                SubAcctRoleVO roleVO = new SubAcctRoleVO();
                roleVO.setRoleId(role.getId());
                roleVO.setRoleName(role.getRoleName());
                roleVO.setIsExist("0");
                for (AuthStaff2RoleResDTO staffRole : list) {
                    if (staffRole.getRoleId().longValue() == role.getId().longValue()) {
                        roleVO.setIsExist("1");//已分配了该角色
                    }
                }
                resultList.add(roleVO);
            }
        }
        model.addAttribute("roleList", resultList);
        /*查询剩余可创建子帐号数量*/
        long count = shopSubAuthStaffRSV.countShopSubAuthStaff(cust.getShopId());
        String maxCount = BaseParamUtil.fetchParamValue("SHOP_SUB_ACCT_COUNT", "MAX_COUNT");
        model.addAttribute("subCount", Long.parseLong(maxCount) - count);//剩余可创建的数量
        model.addAttribute("maxCount", maxCount);//最大可创建数量
        
        return "/seller/staff/shopmgr/sub-acct-edit";
    }
    
    /**
     * 
     * saveEditSubAcct:(保存编辑). <br/> 
     * 
     * @author huangxl5
     * @param subAcctVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/subacct/edit/save")
    @ResponseBody
    public EcpBaseResponseVO saveEditSubAcct(SubAcctVO subAcctVO){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try {
            /*设置参数信息*/
            CustInfoReqDTO cust = new CustInfoReqDTO();
            //cust.setShopId(sellerResDTO.getShoplist().get(0).getId());
            cust.setStaffCode(cust.getStaff().getStaffCode());
            CustSubInfoReqDTO custSub = new CustSubInfoReqDTO();
            custSub.setCustName(subAcctVO.getCustName());//真实姓名
            custSub.setGender(subAcctVO.getGender());//性别
            custSub.setId(subAcctVO.getStaffId());
            if (StringUtil.isNotBlank(subAcctVO.getRoleIds())) {
                custSub.setRoleIds(subAcctVO.getRoleIds().substring(0, subAcctVO.getRoleIds().length() - 1));//角色ids
            }
            /*调用业务方法，保存子帐号信息*/
            shopSubAuthStaffRSV.updateSubAcctRole(custSub);
            vo.setResultFlag("ok");
            vo.setResultMsg("操作成功");
        } catch (Exception e) {
            vo.setResultFlag("fail");
            vo.setResultMsg("操作失败" + e.getMessage());
        }
        return vo;
    }
    
    /**
     * 
     * updateSubAcct:(更新用户角色). <br/> 
     * 
     * @author huangxl5
     * @param subAcctVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/subacct/upate")
    @ResponseBody
    public EcpBaseResponseVO updateSubAcct(SubAcctVO subAcctVO){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try {
            /*设置参数信息*/
            CustSubInfoReqDTO custSub = new CustSubInfoReqDTO();
            custSub.setId(subAcctVO.getStaffId());
            if (StringUtil.isNotBlank(subAcctVO.getRoleIds())) {
                custSub.setRoleIds(subAcctVO.getRoleIds().substring(0, subAcctVO.getRoleIds().length() - 1));//角色ids
            } else {
                vo.setResultFlag("fail");
                vo.setResultMsg("操作失败：角色不能为空！");
            }
            /*调用业务方法，更新用户角色信息*/
            shopSubAuthStaffRSV.updateSubAcctRole(custSub);
            vo.setResultFlag("ok");
            vo.setResultMsg("操作成功");
        } catch (Exception e) {
            vo.setResultFlag("fail");
            vo.setResultMsg("操作失败" + e.getMessage());
        }
        return vo;
    }
    @RequestMapping(value="/subacct/rolemgr")
    public String roleMgrSubAcct(Model model,@RequestParam("staffId") Long staffId) throws BusinessException{
        /*查询角色列表*/
        AuthRoleReqDTO authRoleReq =  new AuthRoleReqDTO();
        authRoleReq.setPageNo(0);//设为0，查询全部
        authRoleReq.setSysCode(StaffConstants.PublicParam.SYS_CODE_SELLER);//只查询管理平台的角色
        authRoleReq.setStatus(StaffConstants.PublicParam.STATUS_ACTIVE);//查询有效的角色
        PageResponseDTO<AuthRoleResDTO> authRoleRes = roleManageRSV.listAuthRole(authRoleReq);
        model.addAttribute("rolePage", authRoleRes);
        
        return "/seller/staff/shopmgr/role/sub-acct-role";
    }
    /**
     * 
     * updateSubAcct:(获取店铺会员等级列表). <br/> 
     * 
     * @author asus1 
     * @param subAcctVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/shoplevellist")
    @ResponseBody
    public Map<String,Object> findShopLevelList(SubAcctVO subAcctVO){
        Map<String,Object> result = new HashMap<String,Object>();
        ShopVipLevelReqDTO req = new ShopVipLevelReqDTO();
        req.setPageNo(0);
        req.setShopId(subAcctVO.getShopId());
        try {
            PageResponseDTO<ShopVipLevelResDTO> page = shopManageRSV.listShopVipLevel(req);
            if (page != null && CollectionUtils.isNotEmpty(page.getResult())) {
                String levelList = JSON.toJSONString(page.getResult());
                result.put("levelList", levelList);
            }
            result.put("flag", "ok");
        } catch (Exception e) {
            result.put("flag", "fail");
        }
        return result;
    }
}