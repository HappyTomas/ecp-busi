package com.zengshi.ecp.busi.order.entity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;

/**
 * 
 * @author wangxq
 *
 * 功能：
 *   买家收货确认
实体编号新增录入
实体编号新增录入查询
实体编号新增录入删除
实体编号新增文件导入
实体编号新增文件导入批次查看
实体编号新增文件导入失败信息查看
实体编号新增文件导入删除
实体编号变更查询
实体编号单个修改
实体编号变更文件导入
实体编号变更文件导入批次查看
实体编号变更文件导入失败信息查看
实体编号变更文件导入删除

 */
@Controller
@RequestMapping(value="/entitycode")
public class EntityCodeController extends EcpBaseController {

//	private static final String MODULE = EntityCodeController.class.getName();
//	
//    @Resource
//    private IOrdEntityChangeRSV ordEntityChangeRSV;
//    
//    @Resource
//    private IOrdEntityAddRSV ordEntityAddRSV;
//	
//    @RequestMapping
//    public String init(){
//    	return "/order/entity/entity-init";
//    }
//    
//	@RequestMapping(value="/grid")
//	public String grid(Model model){
//		//页面查询日期初始化
//		Map<String,Date> demoCfg = new HashMap<String,Date>();
//		demoCfg.put("startTime", DateUtils.addDay(new Date(), -7));
//		demoCfg.put("endTime", new Date());
//		model.addAttribute("demoCfg", demoCfg);
//		return "/order/entityCode/entityCode-grid";
//	}
//	
//	/**
//	 * 实体编号变更查询
//	 * @param model
//	 * @param vo
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/gridlist")
//	@ResponseBody
//	public Model gridList(Model model, RShowEntityChgReqVO vo) throws Exception{
//		//拿到REntityInputVO对应的后场DTO，其实REntityInputVO就是根据后场DTO定义出来的不是吗
//		LogUtil.debug(MODULE, vo.toString());
//		
//		RShowEntityChgRequest rdto = vo.toBaseInfo(RShowEntityChgRequest.class);
//		ObjectCopyUtil.copyObjValue(vo, rdto, "", true);
//		
//		PageResponseDTO<RShowEntityChgResponse> t = ordEntityChangeRSV.queryEntityChg(rdto); 
//		
//        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
//
//		return super.addPageToModel(model, respVO);
//	}
//	
//	/**
//	 * 实体编号新增录入
//	 * @param vo
//	 * @return
//	 */
//	@RequestMapping(value="/save")
//	@ResponseBody
//	public EcpBaseResponseVO save(REntityInputReqVO vo){
//		EcpBaseResponseVO resp = new EcpBaseResponseVO();
//		REntityInputRequest rdto = new REntityInputRequest();
//		try{
//			ObjectCopyUtil.copyObjValue(vo, rdto, null, true);
//			
//			ordEntityAddRSV.entityInput(rdto);
//			
//			resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
//			
//		}catch(BusinessException e){
//			LogUtil.error(MODULE, "录入出错", e);
//			resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
//			resp.setResultMsg(e.getMessage());
//		}
//
//		return resp;
//		
//	}
//	
//	//实体编号录入查询
//	/**
//	 * 实体编号录入查询
//	 * @param model
//	 * @param vo
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/inputlist")
//	@ResponseBody
//	public Model inputList(Model model,REntityInputReqVO vo) throws Exception{
//		
//		REntityInputRequest rdto = vo.toBaseInfo(REntityInputRequest.class);
//		
//		PageResponseDTO<RShowEntityResponse> t = ordEntityAddRSV.queryOrderSubEntity(rdto);
//		
//		//调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
//		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
//		
//    	return super.addPageToModel(model, respVO);
//	}
//	
//	//实体编号录入删除
//	@RequestMapping(value="/delete")
//	@ResponseBody
//	public EcpBaseResponseVO delete(REntityInputReqVO vo){
//		REntityInputRequest rdto = vo.toBaseInfo(REntityInputRequest.class);
//		
//		EcpBaseResponseVO resp = new EcpBaseResponseVO();
//		return resp;
//	}
//	
//	//实体编号新增文件导入
//	@RequestMapping(value="/importFile")
//	public EcpBaseResponseVO importFile(@RequestParam(value = "entitycodefile", required = false) MultipartFile file,Model model){
//        
//	    
//        EcpBaseResponseVO resp = new EcpBaseResponseVO();
//        return resp;
//	}
}
