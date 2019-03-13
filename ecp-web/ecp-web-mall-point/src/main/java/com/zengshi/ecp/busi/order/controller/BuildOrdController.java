package com.zengshi.ecp.busi.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.velocity.ParamToolUtil;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.OrdConstant;
import com.zengshi.ecp.busi.order.vo.RCrePreOrdItemReqVO;
import com.zengshi.ecp.busi.order.vo.RCrePreOrdReqVO;
import com.zengshi.ecp.busi.order.vo.RCrePreOrdsReqVO;
import com.zengshi.ecp.busi.order.vo.ROrderMainCheckCarRespVO;
import com.zengshi.ecp.busi.order.vo.RSubmitOrderRespVO;
import com.zengshi.ecp.busi.order.vo.RSumbitMainReqVO;
import com.zengshi.ecp.busi.order.vo.RSumbitMainsReqVO;
import com.zengshi.ecp.busi.staff.buyer.vo.CustAddrVO;
import com.zengshi.ecp.order.dubbo.dto.RCrePreOrdItemRequest;
import com.zengshi.ecp.order.dubbo.dto.RCrePreOrdRequest;
import com.zengshi.ecp.order.dubbo.dto.RCrePreOrdsRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdDeliveAddrRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainsResponse;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdMainsResponse;
import com.zengshi.ecp.order.dubbo.dto.RPreOrdSubResponse;
import com.zengshi.ecp.order.dubbo.dto.RSumbitMainRequest;
import com.zengshi.ecp.order.dubbo.dto.RSumbitMainsRequest;
import com.zengshi.ecp.order.dubbo.dto.RSumbitSubRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

@Controller
@RequestMapping(value="/order/build")
public class BuildOrdController extends EcpBaseController {

	private static final String MODULE = BuildOrdController.class.getName();

	
	@Resource
	private IOrdCartRSV ordCartRSV;
	
    @Resource
    private ICustAddrRSV custaddrRSV;
    
    @Resource
    private IShopInfoRSV shopInfoRSV;
    
    @Resource
    private IOrdMainRSV ordMain;
	
	/*
	 * 客户管理域服务
	 */
	@Resource
	private ICustAddrRSV custAddrRSV;
	
	@RequestMapping
	public String init(Model model){
	    return "/order/build/init";
	}
	
	//获取订单信息
	@RequestMapping(value="/info")
	public Model orderInfo(Model model){
		return model;
	}
	
	//校验购物车列表
    @RequestMapping(value="/checkcar")
    @ResponseBody
    public ROrderMainCheckCarRespVO checkCar(RCrePreOrdsReqVO rvo,HttpServletRequest request){
        LogUtil.info(MODULE, "===========================开始展示订单================================");
        ROrderMainCheckCarRespVO respVO = new ROrderMainCheckCarRespVO();
        try{
            
            //行选中属性
            String itemCheck = "checked";
            //获取staffId测试获取
    
            RCrePreOrdsRequest rOrdCartsRequest = new RCrePreOrdsRequest();
            Long staffId = rOrdCartsRequest.getStaff().getId();
            
            rOrdCartsRequest.setStaffId(staffId);
            //放入预订单
            List<RCrePreOrdRequest> ordCartList = new ArrayList<RCrePreOrdRequest>() ;
        
            LogUtil.info(MODULE, "===========================封装参数================================");

            if(!CollectionUtils.isEmpty(rvo.getCarList())){
                for(RCrePreOrdReqVO rOrdCartReqVO : rvo.getCarList()){
                    RCrePreOrdRequest rOrdCartRequest = new RCrePreOrdRequest();
                    ObjectCopyUtil.copyObjValue(rOrdCartReqVO, rOrdCartRequest, "ordCartItemList", false);

                    List<RCrePreOrdItemRequest> ordCartItemList = new ArrayList<RCrePreOrdItemRequest>();
                    if(!CollectionUtils.isEmpty(rOrdCartReqVO.getCartItemIdList())){
                        for(RCrePreOrdItemReqVO rOrdCartItemReqVO : rOrdCartReqVO.getCartItemIdList()){
                            //判断是否明细项目被选中
                            if(StringUtils.isNotEmpty(rOrdCartItemReqVO.getItemCheck()) && rOrdCartItemReqVO.getItemCheck().equals(itemCheck)){
                                RCrePreOrdItemRequest rOrdCartItemRequest = new RCrePreOrdItemRequest();
                                ObjectCopyUtil.copyObjValue(rOrdCartItemReqVO, rOrdCartItemRequest, "", false);
                                ordCartItemList.add(rOrdCartItemRequest);
                            }
                        }
                        //店铺中有明细被选中才添加
                        if(ordCartItemList.size()>0){
                            rOrdCartRequest.setCartItemIdList(ordCartItemList);
                            ordCartList.add(rOrdCartRequest);
                        }
                    }

                }
            }

			rOrdCartsRequest.setSource(CommonConstants.SOURCE.SOURCE_WEB);
            rOrdCartsRequest.setCarList(ordCartList);
            
            RPreOrdMainsResponse resp = ordCartRSV.createPreOrd(rOrdCartsRequest);
            
            LogUtil.info(MODULE, "===========================调用ordCartRSV.createPreOrd================================");
            //把购物车列表信息缓存起来 时间为半小时
           
            String redisKey = ""+staffId+ DateUtil.getCurrentTimeMillis();
            CacheUtil.addItem(OrdConstant.ORDER_SESSION_KEY_PREFIX+redisKey, resp,OrdConstant.ORDER_SESSION_TIME);      
            respVO.setMainHashKey(redisKey);
            
            LogUtil.info(MODULE, "==========================保存session信息完毕===============================================");

            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        
        }catch(Exception e){
            LogUtil.error(MODULE, e.getMessage());
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            respVO.setResultMsg(e.getMessage());
        }
        
        return respVO;
    }
    
    //展示订单
    @RequestMapping(value="/preord/{mainHashKey}")
    public String createPreOrd(Model model,@PathVariable String mainHashKey,HttpServletRequest request){
        LogUtil.info(MODULE, "===========================开始展示订单================================");
        try{
            HttpSession session = request.getSession();
            
            RPreOrdMainsResponse resp = new RPreOrdMainsResponse();
            
            Long staffId = new BaseInfo().getStaff().getId();
            model.addAttribute("staffId", staffId);
            LogUtil.info(MODULE, "===========================调用ordCartRSV.createPreOrd================================");
            //把购物车列表信息缓存起来 时间为半小时
            resp = (RPreOrdMainsResponse) CacheUtil.getItem(OrdConstant.ORDER_SESSION_KEY_PREFIX+mainHashKey);                   
            if(resp == null){
                //session超时处理
                return "redirect:/homepage";
            }
            LogUtil.info(MODULE, "==========================保存session信息===============================================");

            List<RPreOrdMainResponse> list = new ArrayList<RPreOrdMainResponse>();
            list = resp.getPreOrdMainList();
            LogUtil.info(MODULE, list.toString());
            //提交订单时的合计价格
            Long agentMoneys = 0l;
            model.addAttribute("agentMoneys",agentMoneys);
            model.addAttribute("cartProm", resp.getCartPromRespDTO());
            model.addAttribute("preOrdMainList", list);
            //配送方式
            List<String> deliverTypes = new ArrayList<String>();
            LogUtil.info(MODULE, "==============================获取配送方式===============================================");
            for(RPreOrdMainResponse ordMain : list){
                ShopInfoResDTO shopInfo = shopInfoRSV.findShopInfoByShopID(ordMain.getShopId());
                if(StringUtil.isBlank(shopInfo.getDistribution())){
                    deliverTypes.add(OrdConstant.DeliverType.EXPERSS);
                }else{
                    deliverTypes.add(shopInfo.getDistribution());
                }
                
            }
            model.addAttribute("deliverTypes", deliverTypes);
            LogUtil.info(MODULE, deliverTypes.toString());
            //总金额
            Long orderMoneys = 0l;
            //运费
            Long realExpressFees = 0l;
            //应付总额
            Long allMoney = 0l;
            //总数量
            Long orderAmounts = 0l;
            //总积分
            Long allScores = 0l;
            LogUtil.info(MODULE, "==============================计算金额===============================================");
            for(int i=0;i<list.size();i++){
                RPreOrdMainResponse ordMain = list.get(i);
                orderMoneys += ordMain.getOrderMoney();
                //有包含快递就使用快递
                //if(deliverTypes.get(i).contains(OrdConstant.DeliverType.EXPERSS))realExpressFees += ordMain.getRealExpressFee();
                orderAmounts += ordMain.getOrderAmount();
                for(RPreOrdSubResponse ordSub : ordMain.getPreOrdSubList()){
                    allScores += ordSub.getOrderScore();
                }
            }
            LogUtil.info(MODULE, "==============================计算金额结束===============================================");
            allMoney = orderMoneys + realExpressFees;
            model.addAttribute("orderMoneys", orderMoneys);
            model.addAttribute("realExpressFees", realExpressFees);
            model.addAttribute("allMoney",allMoney);
            model.addAttribute("allScores", allScores);
            model.addAttribute("orderAmounts",orderAmounts);
            model.addAttribute("mainHashKey", mainHashKey);
            model.addAttribute("addrs",getCustAddr());
        
        }catch(BusinessException bus){
            LogUtil.error(MODULE, "==================================展示订单出错===========================");
            LogUtil.error(MODULE, bus.getMessage());
            bus.printStackTrace();

        }catch(Exception e){
            LogUtil.error(MODULE, "==================================展示订单出错============================");
            LogUtil.error(MODULE, e.getMessage());
            e.printStackTrace();
        }
        
        LogUtil.info(MODULE, "===========================展示订单结束================================");
        return "/order/build/build-create";
    }
    
	
	//收货地址新增弹出窗口 repCode
	@RequestMapping(value="/buyeraddrnew")
	public String buyerAddrNew(){
		return "/order/build/open/buyer-addressnew";
	}
	
	/**
	 * 
	 * 功能描述：普通发票信息设置
	 *
	 * @author  曾海沥(zenghl)
	 * <p>创建日期 ：2015-10-9 上午11:28:58</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value="/billInfoInit")
	public String billInfoInit(){
		return "/order/build/bill/bill-info";
	}
	


    
	/**
     * 
     * 功能描述：提交订单
     *
     * @author  曾海沥(zenghl)
     * <p>创建日期 ：2015-10-9 下午8:30:52</p>
     *
     * @param model
     * @param vo
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping(value="/submitOrd")
    @ResponseBody
    public RSubmitOrderRespVO submitOrd(Model model,RSumbitMainsReqVO vo,HttpServletRequest request){
        LogUtil.info(MODULE, "===========================提交订单开始===============================");
        RSubmitOrderRespVO resp = new RSubmitOrderRespVO();
        //dto初始化
        RSumbitMainsRequest sumbitMains = new RSumbitMainsRequest();
        List<RSumbitMainRequest> sumbitMainList = new ArrayList<RSumbitMainRequest>();
        
        //从session里面拿对应的购物车信息
        Long staffId = sumbitMains.getStaff().getId();
        
        try{
            
          //获取缓存中保存购物车信息
            RPreOrdMainsResponse ordMains = (RPreOrdMainsResponse) CacheUtil.getItem(OrdConstant.ORDER_SESSION_KEY_PREFIX+vo.getMainHashKey());
          
            if(ordMains==null){
                throw new BusinessException("session超时");
            }
          
            //设置促销信息
            sumbitMains.setCartPromRespDTO(ordMains.getCartPromRespDTO());

            //开始复制信息
            LogUtil.info(MODULE, "=========================开始复制信息==========================");
            for(int i=0;i<ordMains.getPreOrdMainList().size();i++){
                //session信息
                RPreOrdMainResponse ordMain = (RPreOrdMainResponse)ordMains.getPreOrdMainList().get(i);
                RSumbitMainRequest sumbitMain = new RSumbitMainRequest();
                //ObjectCopyUtil.copyObjValue(ordMain, sumbitMain, "ordAcctInfoList", false);
                sumbitMain.setStaffId(staffId);
                sumbitMain.setShopId(ordMain.getShopId());
                sumbitMain.setShopName(ordMain.getShopName());
                sumbitMain.setOrderMoney(ordMain.getOrderMoney());
                sumbitMain.setOrderAmount(ordMain.getOrderAmount());
                sumbitMain.setCartId(ordMain.getCartId());
                
                
                
                //复制子订单信息
                List<RSumbitSubRequest> preOrdSubList = new ArrayList<RSumbitSubRequest>();
                for(RPreOrdSubResponse ordSub : ordMain.getPreOrdSubList()){
                    RSumbitSubRequest sumbitSub = new RSumbitSubRequest();
                    ObjectCopyUtil.copyObjValue(ordSub, sumbitSub, "", false);
                    sumbitSub.setStaffId(staffId);
                    preOrdSubList.add(sumbitSub);
                }
                
                sumbitMain.setPreOrdSubList(preOrdSubList);
                //复制资金账户信息
                List<AcctInfoResDTO> acctInfoList = ordMain.getOrdAcctInfoList();
                //页面信息(能否保证放入session的顺序和页面排列的顺序一致？？？？？)
               
                RSumbitMainReqVO sumbitMainVO = vo.getSumbitMainList().get(i);
                 
                
                //session金额校验从前台页面获取到得orderMoney+运费是否和 realMoney是否一致
                /**========================================================
                 * 校验钱  校验钱 前台传递的根据一组运算规则要计算成orderMoney这个不变的值才行
                 * ========================================================
                 */
                boolean moneyFlag = true;
                Long pageOrderMoney = Long.valueOf(sumbitMainVO.getRealMoney())-Long.valueOf(sumbitMainVO.getRealExpressFee());
                Long sessionOrderMoney = ordMain.getOrderMoney();
                Long pageRealExpressFees = Long.valueOf(sumbitMainVO.getRealExpressFee());
                Long sessionRealExpressFees = ordMain.getRealExpressFee();
                
                if(pageOrderMoney!=sessionOrderMoney) moneyFlag = false;
                if(pageRealExpressFees!=sessionRealExpressFees) moneyFlag = false;
                LogUtil.info(MODULE, "订单金额"+pageOrderMoney+"||"+sessionOrderMoney);
                LogUtil.info(MODULE, "运费金额"+pageRealExpressFees+"||"+sessionRealExpressFees);
                
                sumbitMain.setOrdAcctInfoList(acctInfoList);
                sumbitMain.setDeliverType(sumbitMainVO.getDeliverType());
                sumbitMain.setRealExpressFee(Long.valueOf(sumbitMainVO.getRealExpressFee()));
                sumbitMain.setRealMoney(Long.valueOf(sumbitMainVO.getRealMoney()));
                
                
                sumbitMainList.add(sumbitMain);
            }
            LogUtil.info(MODULE, "=========================结束复制信息==========================");
            
             
          //收货地址管理
            Long addrId = vo.getAddrId();
            String gdsType = vo.getGdsType();
            
            CustAddrReqDTO cust = new CustAddrReqDTO();
            cust.setStaffId(cust.getStaff().getId());
            cust.setId(addrId);
            CustAddrResDTO custresp = custAddrRSV.findAddr(cust);
            //校验收货地址
            if(gdsType.contains(OrdConstant.ORDER_ENTITY_TYPE)){
                if(custresp == null){
                    throw new BusinessException("收货地址不允许为空");
                }
            }

            ROrdDeliveAddrRequest addrreq = new ROrdDeliveAddrRequest();
            if(!StringUtil.isEmpty(custresp)){
                ObjectCopyUtil.copyObjValue(custresp, addrreq, "", false);
                //获取省市县具体位置
                ParamToolUtil area = new ParamToolUtil();
                String newchnlAddress = custresp.getChnlAddress();
                String newProvince = area.getAreaName(custresp.getProvince());
                String newCity = area.getAreaName(custresp.getCityCode());
                String newCounty = area.getAreaName(custresp.getCountyCode());
                if(StringUtil.isBlank(newProvince)){
                    newProvince = "";
                }
                if(StringUtil.isBlank(newProvince)){
                    newCity = "";
                }
                if(StringUtil.isBlank(newProvince)){
                    newCounty = "";
                }
                newchnlAddress = newProvince + newCity + newCounty + newchnlAddress;
                addrreq.setChnlAddress(newchnlAddress);
            }
            LogUtil.info(MODULE,"=======================收货地址管理==========================");
            
            //设置地址信息
            sumbitMains.setrOrdDeliveAddrRequest(addrreq);
            //购物车信息
            sumbitMains.setSumbitMainList(sumbitMainList);
            //设置用户Id
            sumbitMains.setStaffId(staffId);
            //设置支付方式
            sumbitMains.setPayType(vo.getPayType());
	        sumbitMains.setSource(CommonConstants.SOURCE.SOURCE_WEB);
            
            ROrdMainsResponse rOrdMainsResponse = ordMain.sumbitOrd(sumbitMains);
           // String onlineKey = OrdConstant.OnlineOrd.ORDER_ONLINE_KEY;
            if(OrdConstant.PaytStatus.PAY_ONLINE.equals(vo.getPayType())){
                List<ROrdMainResponse> orderList =  rOrdMainsResponse.getOrdMainList();
                LogUtil.info(MODULE, "===========================线上支付参数保存session=============================");             
                String onlineKey ="" +staffId+ DateUtil.getCurrentTimeMillis();//临时存储优惠码信息
                CacheUtil.addItem(OrdConstant.OnlineOrd.ORDER_ONLINE_KEY+onlineKey, orderList,OrdConstant.ORDER_SESSION_TIME);  
                resp.setOnlineKey(onlineKey);      
                LogUtil.info(MODULE, "===========================线上支付参数保存session完毕=============================");
            }
            
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            LogUtil.error(MODULE, "提交订单失败");
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            resp.setResultMsg(e.getMessage());
        }
        
        LogUtil.info(MODULE, "========================提交订单结束=============================");
        
        return resp;
    }
	
	
  //收货地址保存
    @RequestMapping(value="/saveaddr")
    @ResponseBody
    public Map<String,Object> saveAddr(@ModelAttribute CustAddrVO custaddr){
        Map<String, Object> cust = new HashMap<String, Object>();
        LogUtil.info(MODULE, "============== 保存店铺收货地址    开始  =============");
        
        CustAddrReqDTO cusraddrDTO = new CustAddrReqDTO();
        ObjectCopyUtil.copyObjValue(custaddr, cusraddrDTO, null, false);
        CustAddrResDTO custaddrresp = new CustAddrResDTO();
//        cusraddrDTO.setStaffId(ParamsTool.getStaffId());
        cusraddrDTO.setStaffId(cusraddrDTO.getStaff().getId());

        try{
            if(cusraddrDTO.getId() == null)
            {
                custaddrresp = custaddrRSV.saveCustAddr(cusraddrDTO);
            }
            else {
                custaddrRSV.updateCustAddr(cusraddrDTO);
                ObjectCopyUtil.copyObjValue(custaddr, custaddrresp, null, false);
            }
            cust.put("resultFlag","ok");
            
        }catch(Exception e){
            custaddrresp = null;
            LogUtil.error(MODULE, "保存地址出错");
            cust.put("resultFlag","expt");
            
        }
        cust.put("resultInfo", custaddrresp);
        return cust;
    }
	
	//收货地址编辑弹出窗口 repCode
    @RequestMapping(value="/buyeraddrupdate")
    public String buyerAddrUpdate(Model model,@RequestParam(value="id")String id){
        //获取staffId
//        Long staffId = ParamsTool.getStaffId();
    	//根据店铺id，查找地址信息
        CustAddrReqDTO dto = new CustAddrReqDTO();
        Long staffId = dto.getStaff().getId();
        if(StringUtil.isBlank(id)){
            throw new BusinessException("----");
        }
        
        dto.setStaffId(Long.valueOf(staffId));
        dto.setId(Long.valueOf(id));
        
        CustAddrResDTO custaddr = custaddrRSV.findAddr(dto);
        model.addAttribute("buyerAddr", custaddr);
        return "/order/build/open/buyer-addressupdate";
    }
    
    //用户收货地址数据
    private List<CustAddrResDTO> getCustAddr(){
    	LogUtil.debug(MODULE, "=================地址获取开始==================");
        List<CustAddrResDTO> addrs = new ArrayList<CustAddrResDTO>();
        //获取用户收货地址信息
        CustAddrReqDTO dto = new CustAddrReqDTO();
        dto.setStaffId(dto.getStaff().getId());
        
        addrs = custaddrRSV.listCustAddr(dto);
        LogUtil.debug(MODULE, "=================地址获取结束==================");
        return addrs;
    }
}
