///** 
// * Project Name:ecp-web-demo 
// * File Name:DemoController.java 
// * Package Name:com.zengshi.ecp.busi.demo.controller 
// * Date:2015-8-5下午2:51:38 
// * Copyright (c) 2015, ZengShi All Rights Reserved. 
// * 
// */ 
//package com.zengshi.ecp.busi.demo.controller;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.validation.Valid;
//
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.zengshi.ecp.base.controller.EcpBaseController;
//import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
//import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
//import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
//import com.zengshi.ecp.busi.demo.vo.DemoCfgVO;
//import com.zengshi.ecp.demo.dubbo.dto.DemoCfgReqDTO;
//import com.zengshi.ecp.demo.dubbo.dto.DemoCfgRespDTO;
//import com.zengshi.ecp.demo.dubbo.interfaces.IDemoCfgRSV;
//import com.zengshi.ecp.server.front.dto.BaseInfo;
//import com.zengshi.ecp.server.front.dto.BaseStaff;
//import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
//import com.zengshi.ecp.server.front.dto.PageResponseDTO;
//import com.zengshi.ecp.server.front.param.IBaseSysCfgRSV;
//import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
//import com.zengshi.ecp.server.front.util.SysCfgUtil;
//import com.zengshi.paas.utils.LogUtil;
//import com.alibaba.fastjson.JSONObject;
//
//
///**
// * Title: ECP <br>
// * Project Name:ecp-web-demo <br>
// * Description: <br>
// * Date:2015-8-5下午2:51:38  <br>
// * Copyright (c) 2015 ZengShi All Rights Reserved <br>
// * 
// * @author yugn
// * @version  
// * @since JDK 1.6 
// */
//@Controller
//@RequestMapping(value="/demo")
//public class DemoController extends EcpBaseController {
//    
//    private static String MODULE = DemoController.class.getName();
//    
//    /**
//     * 
//     * init:页面初始化，requestMapping如果不写的话，访问地址同：Controller类的 requestmapping的URL
//     * 
//     * @author yugn 
//     * @return 
//     * @since JDK 1.6
//     */
//    @RequestMapping()
//    public String init(Model model){
//        BaseStaff staff = StaffLocaleUtil.getStaff();
//        LogUtil.info(MODULE,"staff: ======" + staff.getStaffCode());
//        LogUtil.info(MODULE,"staff: ======" + staff.getStaffClass());
//        BaseInfo info = new BaseInfo();
//        LogUtil.info(MODULE,"info.staff: ======" + info.getStaff().getStaffCode());
//        LogUtil.info(MODULE,"info.staff: ======" + info.getStaff().getStaffClass());
//        model.addAttribute("staffCode", staff.getStaffCode());
//        model.addAttribute("infoStaffCode", info.getStaff().getStaffCode());
//        return "/demo/demo-init";
//    }
//    
//    /**
//     * 
//     * 功能描述：Gird列表
//     *
//     * @author  曾海沥(Terry)
//     * <p>创建日期 ：2015-8-5 下午8:29:55</p>
//     *
//     * @return
//     *
//     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
//     */
//    @RequestMapping(value="/grid")
//    public String grid(){
//    	return "/demo/demo-grid";
//    }
//    
//    /**
//     * 
//     * 功能描述：基础表单
//     *
//     * @author  曾海沥(Terry)
//     * <p>创建日期 ：2015-8-5 下午8:29:55</p>
//     *
//     * @return
//     *
//     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
//     */
//    @RequestMapping(value="/form")
//    public String form(){
//    	return "/demo/demo-form";
//    }
//    
//    /**
//     * 
//     * 功能描述：大表单
//     *
//     * @author  曾海沥(Terry)
//     * <p>创建日期 ：2015-8-5 下午8:29:55</p>
//     *
//     * @return
//     *
//     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
//     */
//    @RequestMapping(value="/form-more")
//    public String formMore(Model model){
//        
//        BaseSysCfgRespDTO dto = SysCfgUtil.fetchSysCfg("SYS_TEST4");
//        
//        model.addAttribute("sysCfg", dto);
//    	return "/demo/demo-form-more";
//    }
//    
//    /**
//     * 
//     * 功能描述：其他内容
//     *
//     * @author  曾海沥(Terry)
//     * <p>创建日期 ：2015-8-5 下午8:29:55</p>
//     *
//     * @return
//     *
//     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
//     */
//    @RequestMapping(value="/plugin")
//    public String plugin(){
//    	return "/demo/demo-plugin";
//    }
//    
//    /**
//     * 
//     * 功能描述：图标库
//     *
//     * @author  曾海沥(Terry)
//     * <p>创建日期 ：2015-8-5 下午8:29:55</p>
//     *
//     * @return
//     *
//     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
//     */
//    @RequestMapping(value="/icons")
//    public String icons(){
//    	return "/demo/demo-icons";
//    }
//    
//    /**
//     * 
//     * 功能描述：弹出窗口
//     *
//     * @author  曾海沥(Terry)
//     * <p>创建日期 ：2015-8-18 下午2:19:35</p>
//     *
//     * @return
//     *
//     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
//     */
//    @RequestMapping(value="/open")
//    public String open(){
//    	return "/demo/open/demo-open";
//    }
//    
//    /**
//     * 
//     * 功能描述：选择器
//     *
//     * @author  曾海沥(Terry)
//     * <p>创建日期 ：2015-8-18 下午2:19:43</p>
//     *
//     * @return
//     *
//     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
//     */
//    @RequestMapping(value="/selector")
//    public String selector(){
//    	return "/demo/open/demo-selector";
//    }
//    
//    /**
//     * 
//     * edit:编辑页面；
//     *  
//     * @author yugn 
//     * @return 
//     * @since JDK 1.6
//     */
//    @RequestMapping(value="/edit")
//    public String edit(Model model){
//        DemoCfgVO demoCfg = new DemoCfgVO();
//        demoCfg.setCode("A001");
//        demoCfg.setInfo("测试");
//        demoCfg.setCreateTime(Calendar.getInstance().getTime());
//        model.addAttribute("demoCfg",demoCfg);
//        return "/demo/demo-form";
//    }
//    
//    @Resource
//    private IBaseSysCfgRSV baseSysCfgRSV;
//    /**
//     * 
//     * more:转入demo-more<br/> 
//     * 
//     * @author yugn 
//     * @return 
//     * @since JDK 1.6
//     */
//    @RequestMapping(value="/more")
//    public String more(Model model){
//        BaseSysCfgRespDTO dto = baseSysCfgRSV.queryCfgByCode("SYS_TEST");
//        
//        model.addAttribute("sysCfg", dto);
//        return "/demo/demo-form-more";
//    }
//    
//    /**
//     * 
//     * save:保存 
//     * 
//     * @author yugn 
//     * @return 
//     * @since JDK 1.6
//     */
//    @RequestMapping(value="/save")
//    @ResponseBody
//    public EcpBaseResponseVO save(@Valid DemoCfgVO demoCfg){
//        DemoCfgReqDTO dto = new DemoCfgReqDTO();
//        dto.setCode(demoCfg.getCode());
//        dto.setCreateTime(demoCfg.getCreateTime());
//        dto.setInfo(demoCfg.getInfo());
//        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(demoCfg));
//        EcpBaseResponseVO vo = new EcpBaseResponseVO();
//       
//        try{
//            demoCfgRSV.saveDemoCfg(dto);
//            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
//        } catch(Exception err){
//            err.printStackTrace();
//            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
//        }
//        return vo;
//    }
//    
//    @Resource(name="demoCfgRSV")
//    private IDemoCfgRSV demoCfgRSV;
//
//    
//    @RequestMapping(value="/save2")
//    @ResponseBody
//    public EcpBaseResponseVO save2(@RequestParam("code")String code ,@RequestParam("info")String info, 
//            @RequestParam("createTime") @DateTimeFormat(pattern="yyyy-MM-dd")Date creatTime){
//        
//        LogUtil.info(MODULE,"==========createTime:"+creatTime.getTime()+";info:"+info+";code:"+code);
//        EcpBaseResponseVO vo = new EcpBaseResponseVO();
//        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
//        return vo;
//    }
//    
//    @RequestMapping(value="/saveform")
//    public String saveForm(@Valid @ModelAttribute("demoCfg") DemoCfgVO demoCfg, BindingResult result){
//        LogUtil.info(this.getClass().getName(), "========saveform");
//        if(result.hasErrors()){
//            LogUtil.info(this.getClass().getName(), "========hasError");
//            return "/demo/demo-form";
//        } else {
//            return "redirect:/demo/grid";
//        }
//    }
//    
//    
//    @RequestMapping("/gridlist")
//    @ResponseBody
//    public Model gridList(Model model, EcpBasePageReqVO vo) throws Exception{
//        ///后场服务所需要的DTO；
//        DemoCfgReqDTO s = vo.toBaseInfo(DemoCfgReqDTO.class);
//        s.setInfo("xx"); ///其它的查询条件；
//        
//        //模拟一个后场返回的列表信息；
//        //PageResponseDTO<DemoCfgRespDTO> t = this.initParam();
//        PageResponseDTO<DemoCfgRespDTO> t = demoCfgRSV.listDemoCfgPage(s);
//        
//        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
//        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
//
//        return super.addPageToModel(model, respVO);
//    }
//    
//    ///模拟后场返回的数据；
//    private PageResponseDTO<DemoCfgRespDTO> initParam(){
//        PageResponseDTO<DemoCfgRespDTO> t = new PageResponseDTO<DemoCfgRespDTO>();
//        t.setCount(100);
//        t.setPageCount(10);
//        t.setPageNo(1);
//        t.setPageSize(10);
//        
//        DemoCfgRespDTO dto1 = new DemoCfgRespDTO();
//        dto1.setCode("1");
//        dto1.setId(1);
//        dto1.setInfo("测试");
//        dto1.setCreateTime(Calendar.getInstance().getTime());
//        
//        DemoCfgRespDTO dto2 = new DemoCfgRespDTO();
//        dto2.setCode("2");
//        dto2.setId(2);
//        dto2.setInfo("测试02");
//        dto2.setCreateTime(Calendar.getInstance().getTime());
//        
//        
//        List<DemoCfgRespDTO> lst = new ArrayList<DemoCfgRespDTO>();
//        lst.add(dto2);
//        lst.add(dto1);
//        t.setResult(lst);
//        
//        return t;
//    }
//
//}
//
//
//
