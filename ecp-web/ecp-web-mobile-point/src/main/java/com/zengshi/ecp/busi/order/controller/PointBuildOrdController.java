package com.zengshi.ecp.busi.order.controller;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.velocity.ParamToolUtil;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.OrdConstant;
import com.zengshi.ecp.busi.order.vo.*;
import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.general.order.dto.CoupDetailRespDTO;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.prom.dubbo.dto.CartPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromItemDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/point/order/build")
public class PointBuildOrdController extends EcpBaseController {

    private static final String MODULE = PointBuildOrdController.class.getName();
    
    private static final String URL = "/order/build";

    @Resource
    private IOrdCartRSV ordCartRSV;

    /**
     * 收货地址
     */
    @Resource
    private ICustAddrRSV custaddrRSV;

    /**
     * 店铺服务
     */
    @Resource
    private IShopInfoRSV shopInfoRSV;

    @Resource
    private IOrdMainRSV ordMain;


    @RequestMapping
    public String init(Model model){
        return URL+"/init";
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
            String itemCheck = "on";
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
                            //判断明细是否有效
                            if(rOrdCartItemReqVO.isGdsStatus() == true){
                              //判断是否明细项目被选中
                                if(StringUtils.isNotEmpty(rOrdCartItemReqVO.getItemCheck()) && rOrdCartItemReqVO.getItemCheck().equals(itemCheck)){
                                    RCrePreOrdItemRequest rOrdCartItemRequest = new RCrePreOrdItemRequest();
                                    ObjectCopyUtil.copyObjValue(rOrdCartItemReqVO, rOrdCartItemRequest, "", false);
                                    ordCartItemList.add(rOrdCartItemRequest);
                                } 
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


            //数据来源
            rOrdCartsRequest.setSource(CommonConstants.SOURCE.SOURCE_APP);
            
            if(CollectionUtils.isNotEmpty(ordCartList)){
                rOrdCartsRequest.setCarList(ordCartList);
                RPreOrdMainsResponse resp = ordCartRSV.createPreOrd(rOrdCartsRequest);
                LogUtil.info(MODULE, "===========================调用ordCartRSV.createPreOrd================================");
                //把购物车列表信息缓存起来 时间为半小时
                String redisKey = ""+staffId+ DateUtil.getCurrentTimeMillis();
                CacheUtil.addItem(OrdConstant.ORDER_SESSION_KEY_PREFIX+redisKey, resp,OrdConstant.ORDER_SESSION_TIME);      
                respVO.setMainHashKey(redisKey);
                
                LogUtil.info(MODULE, "==========================保存sessio信息完毕===============================================");

                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            }else{
                //当选择明细只有一条为失效商品
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
                respVO.setResultMsg("您选择的是失效商品不能购买！");
            }
            

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

            RPreOrdMainsResponse resp = new RPreOrdMainsResponse();

            Long staffId = new BaseInfo().getStaff().getId();

            //购物车列表获取
            LogUtil.info(MODULE, "==========================获取session信息===============================================");
            resp = (RPreOrdMainsResponse) CacheUtil.getItem(OrdConstant.ORDER_SESSION_KEY_PREFIX+mainHashKey);           
            if(resp == null){
                return "redirect:/order/cart/list";
            }
            model.addAttribute("staffId", staffId);

            List<RPreOrdMainResponse> list = new ArrayList<RPreOrdMainResponse>();
            list = resp.getPreOrdMainList();
            LogUtil.info(MODULE, list.toString());
            //提交订单时的合计价格
            Long agentMoneys = 0l;
            model.addAttribute("agentMoneys",agentMoneys);
            model.addAttribute("cartProm", resp.getCartPromRespDTO());
            Map<Long,Integer> coupSizeMap = new HashMap<>();

            for(RPreOrdMainResponse ordMain :list){
                int size = 0;
                if(CollectionUtils.isNotEmpty(ordMain.getCoupCheckBeanRespDTOs())){
                    for(CoupCheckBeanRespDTO coup :ordMain.getCoupCheckBeanRespDTOs()){
                        size +=coup.getCoupSize();
                    }
                }
                coupSizeMap.put(ordMain.getShopId(),size);
            }
            //跟页面对应，判断是否应该展示优惠券字段
            if(StringUtil.isNotEmpty(resp.getCoupOrdCheckRespDTO())){
                model.addAttribute("coupOrdSkuMap",resp.getCoupOrdCheckRespDTO().getCoupOrdSkuMap());
            }
            model.addAttribute("preOrdMainList", list);
            model.addAttribute("coupSizeMap",coupSizeMap);
            //配送方式
            Map<Long,String> deliverTypes = new HashMap<Long,String>();
            LogUtil.info(MODULE, "==============================获取配送方式===============================================");
            for(RPreOrdMainResponse ordMain : list){
                ShopInfoResDTO shopInfo = shopInfoRSV.findShopInfoByShopID(ordMain.getShopId());
                if(StringUtil.isBlank(shopInfo.getDistribution())){
                    deliverTypes.put(ordMain.getShopId(),OrdConstant.DeliverType.EXPERSS);
                }else{
                    deliverTypes.put(ordMain.getShopId(),shopInfo.getDistribution());
                }

            }
            model.addAttribute("deliverTypes", deliverTypes);
            LogUtil.info(MODULE, deliverTypes.toString());

            Long orderMoneys = 0l;//总金额
            Long discountMoneys = 0l;//总优惠金额
            Long realExpressFees = 0l;//运费
            Long scores = 0l;//总积分
            Long allMoney = 0l;//应付总额
            Long orderAmounts = 0l;//总数量

            LogUtil.info(MODULE, "==============================计算金额===============================================");
            Map<Long,CartPromItemDTO> cartPromItemDTOMap = new HashMap<>();
            Map<Long,CartPromDTO> cartPromDTOMap = new HashMap<>();
            Map<Long,Long> discountPriceMoneyMap = new HashMap<>();
            if(resp.getCartPromRespDTO()!=null){
                cartPromItemDTOMap = resp.getCartPromRespDTO().getCartPromItemDTOMap();
                cartPromDTOMap = resp.getCartPromRespDTO().getCartPromDTOMap();
            }

            for(int i=0;i<list.size();i++){
                RPreOrdMainResponse ordMain = list.get(i);
                orderMoneys += ordMain.getOrderMoney();
                Long discountMoney = 0l;



                if(deliverTypes.get(ordMain.getShopId()).contains(OrdConstant.DeliverType.EXPERSS)){
                    realExpressFees += ordMain.getRealExpressFee();//有包含快递就使用快递
                }

                orderAmounts += ordMain.getOrderAmount();
                if(cartPromDTOMap.get(ordMain.getCartId())!=null){//店铺促销优惠减免金额
                    discountMoney += cartPromDTOMap.get(ordMain.getCartId()).getDiscountMoney().longValue();
                }

                if(CollectionUtils.isNotEmpty(ordMain.getGroupLists())){
                    for(List<RPreOrdSubResponse> groups : ordMain.getGroupLists()){
                        if(CollectionUtils.isNotEmpty(groups)){
                            for(RPreOrdSubResponse groupItem : groups){
                                if(cartPromItemDTOMap.get(groupItem.getCartItemId())!=null){
                                    CartPromItemDTO cartPromItemDTO = cartPromItemDTOMap.get(groupItem.getCartItemId());

                                    if(!cartPromItemDTO.isIfFulfillProm()){
                                        //没参与促销时，自行计算价格差值
                                        Long discountPrice = groupItem.getBasePrice() - groupItem.getBuyPrice();
                                        discountPrice = (discountPrice<0?(-discountPrice):discountPrice);
                                        discountMoney += discountPrice * groupItem.getOrderAmount();

                                    }else{
                                        discountMoney += cartPromItemDTO.getDiscountMoney().longValue();//商品促销优惠减免金额
                                    }
                                }
                            }
                        }
                    }
                }

                if(CollectionUtils.isNotEmpty(ordMain.getPreOrdSubList())){
                    for(RPreOrdSubResponse ordsub : ordMain.getPreOrdSubList()){
                        if(cartPromItemDTOMap.get(ordsub.getCartItemId())!=null){
                            CartPromItemDTO cartPromItemDTO = cartPromItemDTOMap.get(ordsub.getCartItemId());

                            if(!cartPromItemDTO.isIfFulfillProm()){
                                //没参与促销时，自行计算价格差值
                                Long discountPrice = ordsub.getBasePrice() - ordsub.getBuyPrice();
                                discountPrice = (discountPrice<0?(-discountPrice):discountPrice);
                                discountMoney += discountPrice * ordsub.getOrderAmount();

                            }else{
                                discountMoney += cartPromItemDTO.getDiscountMoney().longValue();//商品促销优惠减免金额
                            }
                        }
                        scores += ordsub.getScore() * ordsub.getOrderAmount();
                    }
                }

                discountMoneys += discountMoney;
                discountPriceMoneyMap.put(ordMain.getCartId(),discountMoney);

            }
            LogUtil.info(MODULE, "==============================计算金额结束===============================================");
            allMoney = orderMoneys + realExpressFees - discountMoneys;
            model.addAttribute("orderMoneys", orderMoneys);
            model.addAttribute("realExpressFees", realExpressFees);
            model.addAttribute("scores",scores);
            model.addAttribute("allMoney",allMoney);
            model.addAttribute("orderAmounts",orderAmounts);
            model.addAttribute("discountMoneys",discountMoneys);
            model.addAttribute("discountPriceMoneyMap",discountPriceMoneyMap);
            model.addAttribute("addrs",getCustAddr());
            model.addAttribute("mainHashKey", mainHashKey);
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
        return URL+"/build-create";
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

        //行选中属性
        String itemCheck = "checked";

        try{
          //获取缓存中保存购物车信息
            RPreOrdMainsResponse ordMains = (RPreOrdMainsResponse) CacheUtil.getItem(OrdConstant.ORDER_SESSION_KEY_PREFIX+vo.getMainHashKey());
                
            if(ordMains==null){
                if(sumbitMains.getStaff()==null || sumbitMains.getStaff().getId()==0){
                    throw new Exception("用户未登录");
                }
                throw new Exception("订单不能重复提交");
            }

            //设置促销信息
            sumbitMains.setCartPromRespDTO(ordMains.getCartPromRespDTO());

            //优惠券全局检测信息(RPreOrdMainsResponse待定是否只存oupIdskuIdMap)
            if(ordMains.getCoupOrdCheckRespDTO()!=null){
                sumbitMains.setCoupIdskuIdMap(ordMains.getCoupOrdCheckRespDTO().getCoupIdskuIdMap());
            }

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
                sumbitMain.setBasicMoney(ordMain.getBasicMoney());
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

                /**
                 *=======================================
                 * 优惠券多个(在一个循环里检测另一个循环的判断会重复)
                 * 在session当中存放的位置顺序是否和页面上传的相同
                 *=======================================
                 */
                Long coupMoney = 0l;
                if(CollectionUtils.isNotEmpty(vo.getSumbitMainList())&&vo.getSumbitMainList().size()>0){
                    List<CoupCheckBeanRespVO> coupCheckBean = vo.getSumbitMainList().get(i).getCoupCheckBean();
                    List<CoupCheckBeanRespDTO> coupCheckBeanResp = new ArrayList<>();
                    if(coupCheckBean!=null && coupCheckBean.size()>0){
                        for(CoupCheckBeanRespVO coupCheckBeanRespVO : coupCheckBean){
                            CoupCheckBeanRespDTO coupCheckBeanDTO = new CoupCheckBeanRespDTO();

                            if(itemCheck.equals(coupCheckBeanRespVO.getChecked())){
                                ObjectCopyUtil.copyObjValue(coupCheckBeanRespVO,coupCheckBeanDTO,"coupDetails",false);
                                if(CollectionUtils.isNotEmpty(coupCheckBeanRespVO.getCoupDetails())){

                                    List<CoupDetailRespDTO> coupDetailRespDTOs = new ArrayList<>();
                                    for(CoupDetailRespVO coupDetailRespVO : coupCheckBeanRespVO.getCoupDetails()){
                                        CoupDetailRespDTO coupDetailDTO = new CoupDetailRespDTO();
                                        if(itemCheck.equals(coupDetailRespVO.getChecked())){
                                            ObjectCopyUtil.copyObjValue(coupDetailRespVO,coupDetailDTO,"",false);
                                            coupDetailRespDTOs.add(coupDetailDTO);
                                            coupMoney += coupDetailRespVO.getCoupValue();
                                        }
                                    }
                                    coupCheckBeanDTO.setCoupDetails(coupDetailRespDTOs);
                                }
                                coupCheckBeanResp.add(coupCheckBeanDTO);
                            }

                        }

                        sumbitMain.setCoupCheckBean(coupCheckBeanResp);
                    }

                }

                //复制组合商品信息
                if(CollectionUtils.isNotEmpty(ordMain.getGroupLists())){
                    for(List<RPreOrdSubResponse> groups : ordMain.getGroupLists()){
                        for(RPreOrdSubResponse group : groups){
                            RSumbitSubRequest sumbitSub = new RSumbitSubRequest();
                            ObjectCopyUtil.copyObjValue(group, sumbitSub, "", false);
                            sumbitSub.setStaffId(staffId);
                            preOrdSubList.add(sumbitSub);
                        }
                    }
                }

                sumbitMain.setPreOrdSubList(preOrdSubList);
                //复制资金账户信息
                List<AcctInfoResDTO> acctInfoList = ordMain.getOrdAcctInfoList();
                //首先清零使用资金
                for(int a=0;a<acctInfoList.size();a++){
                    acctInfoList.get(a).setDeductOrderMoney(0l);
                }
                //页面信息(能否保证放入session的顺序和页面排列的顺序一致？？？？？)
                RSumbitMainReqVO sumbitMainVO = vo.getSumbitMainList().get(i);
                //将页面资金使用情况设置到资金账户当中
                if((sumbitMainVO.getOrdAcctInfoList() !=null) && (sumbitMainVO.getOrdAcctInfoList().size() == acctInfoList.size())){
                    for(int m=0;m<sumbitMainVO.getOrdAcctInfoList().size();m++){
                        acctInfoList.get(m).setDeductOrderMoney(sumbitMainVO.getOrdAcctInfoList().get(m).getDeductOrderMoney());
                    }
                }
                //主订单发票信息
                sumbitMain.setInvoiceType(sumbitMainVO.getInvoiceType());

                ROrdInvoiceCommRequest rOrdInvoiceCommRequest = sumbitMainVO.getrOrdInvoiceCommRequest();
                sumbitMain.setrOrdInvoiceCommRequest(rOrdInvoiceCommRequest);

                ROrdInvoiceTaxRequest rOrdInvoiceTaxRequest = sumbitMainVO.getrOrdInvoiceTaxRequest();
                sumbitMain.setrOrdInvoiceTaxRequest(rOrdInvoiceTaxRequest);

                //session金额校验从前台页面获取到得orderMoney+运费是否和 realMoney是否一致
                /**========================================================
                 * 校验钱  校验钱 前台传递的根据一组运算规则要计算成orderMoney这个不变的值才行
                 * ========================================================
                 */

                //页面资金账户的钱
                boolean moneyFlag = true;
                Long pageAcctMoney = 0l;
                for(AcctInfoResDTO acct : acctInfoList){
                    pageAcctMoney += acct.getDeductOrderMoney();
                }
                //页面金额重计
                Long pageOrderMoney = Long.valueOf(sumbitMainVO.getRealMoney())
                        -Long.valueOf(sumbitMainVO.getRealExpressFee())
                        +sumbitMainVO.getDiscountMoney()
                        + pageAcctMoney
                        + coupMoney;
                Long sessionOrderMoney = ordMain.getOrderMoney();
                Long pageRealExpressFees = Long.valueOf(sumbitMainVO.getRealExpressFee());
                Long sessionRealExpressFees = ordMain.getRealExpressFee();

                if(!pageOrderMoney.equals(sessionOrderMoney)) moneyFlag = false;
                LogUtil.info(MODULE, "订单金额"+pageOrderMoney+"||"+sessionOrderMoney);
                LogUtil.info(MODULE, "运费金额"+pageRealExpressFees+"||"+sessionRealExpressFees);

                //控制js修改金额和0元以下单
                if(!moneyFlag){
                    LogUtil.error(MODULE, "订单号："+ordMain.getOrderId()+"订单金额异常");
                    throw new BusinessException("订单号："+ordMain.getOrderId()+"订单金额异常,请重新检查");
                }
                //积分商城不做管控
//                if(sumbitMainVO.getRealMoney() <= 0){
//                    LogUtil.error(MODULE, "系统不支持0元以及以下订单");
//                    throw new BusinessException("系统不支持0元以及以下订单");
//                }
                /**===================================
                 * 校验钱
                 * ===================================
                 */

                sumbitMain.setOrdAcctInfoList(acctInfoList);
                sumbitMain.setDeliverType(sumbitMainVO.getDeliverType());
                sumbitMain.setRealExpressFee(Long.valueOf(sumbitMainVO.getRealExpressFee()));
                sumbitMain.setRealMoney(Long.valueOf(sumbitMainVO.getRealMoney()));

                //买家留言  buyerMsg
                sumbitMain.setBuyerMsg(sumbitMainVO.getBuyerMsg());

                sumbitMainList.add(sumbitMain);
            }
            LogUtil.info(MODULE, "=========================结束复制信息==========================");

            //收货地址管理
            Long addrId = vo.getAddrId();
            String gdsType = vo.getGdsType();

            CustAddrReqDTO cust = new CustAddrReqDTO();
            cust.setStaffId(cust.getStaff().getId());
            cust.setId(addrId);
            CustAddrResDTO custresp = custaddrRSV.findAddr(cust);
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
            LogUtil.info(MODULE, "======================收货地址管理=========================");

            //设置地址信息
            sumbitMains.setrOrdDeliveAddrRequest(addrreq);
            //购物车信息
            sumbitMains.setSumbitMainList(sumbitMainList);
            //设置用户Id
            sumbitMains.setStaffId(staffId);
            //设置支付方式
            sumbitMains.setPayType(vo.getPayType());

            sumbitMains.setSource(CommonConstants.SOURCE.SOURCE_APP);

            ROrdMainsResponse rOrdMainsResponse = ordMain.sumbitOrd(sumbitMains);

            //提交订单清理session
            CacheUtil.delItem(OrdConstant.ORDER_SESSION_KEY_PREFIX+vo.getMainHashKey());

            List<ROrdMainResponse> orderList =  rOrdMainsResponse.getOrdMainList();
            LogUtil.info(MODULE, "===========================线上支付参数保存session=============================");
            if(StringUtil.isNotEmpty(orderList)){
                String onlineKey = "" +staffId+ DateUtil.getCurrentTimeMillis();//临时存储优惠码信息
                CacheUtil.addItem(OrdConstant.OnlineOrd.ORDER_ONLINE_KEY+onlineKey, orderList,OrdConstant.ORDER_SESSION_TIME);  
                resp.setOnlineKey(onlineKey);     
            }
            
            String payTypeKey =""+staffId+ DateUtil.getCurrentTimeMillis();//临时存储优惠码信息
            CacheUtil.addItem(OrdConstant.ORDER_PAY_TYPE+payTypeKey, vo.getPayType(),OrdConstant.ORDER_SESSION_TIME);  
            resp.setPayTypeKey(payTypeKey);
            LogUtil.info(MODULE, "===========================线上支付参数保存session完毕=============================");

            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            LogUtil.error(MODULE, "提交订单失败");
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            resp.setResultMsg(e.getMessage() != null ? e.getMessage() : "系统异常");
        }

        LogUtil.info(MODULE, "========================提交订单结束=============================");

        return resp;
    }


    //用户收货地址数据
    private List<CustAddrResDTO> getCustAddr(){
        List<CustAddrResDTO> addrs = new ArrayList<CustAddrResDTO>();
        //获取用户收货地址信息
        CustAddrReqDTO dto = new CustAddrReqDTO();
        dto.setStaffId(dto.getStaff().getId());

        addrs = custaddrRSV.listCustAddr(dto);
        return addrs;
    }

    //收货地址保存
    @RequestMapping(value="/saveaddr")
    @ResponseBody
    public CustAddrRespVO saveAddr(@ModelAttribute CustAddrVO custaddr){
        CustAddrRespVO res = new CustAddrRespVO();
        LogUtil.info(MODULE, "============== 保存店铺收货地址    开始  =============");

        CustAddrReqDTO cusraddrDTO = new CustAddrReqDTO();
        ObjectCopyUtil.copyObjValue(custaddr, cusraddrDTO, null, false);
        cusraddrDTO.setStaffId(cusraddrDTO.getStaff().getId());

        try{
            if(cusraddrDTO.getId() == null){
                CustAddrResDTO custaddrresp = custaddrRSV.saveCustAddr(cusraddrDTO);
                ObjectCopyUtil.copyObjValue(custaddrresp, res, null, false);
            }else {
                custaddrRSV.updateCustAddr(cusraddrDTO);
                ObjectCopyUtil.copyObjValue(cusraddrDTO, res, null, false);
            }
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }catch(Exception e){
            LogUtil.error(MODULE, "保存地址出错");
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            res.setResultMsg(e.getMessage());
        }
        return res;
    }

}
