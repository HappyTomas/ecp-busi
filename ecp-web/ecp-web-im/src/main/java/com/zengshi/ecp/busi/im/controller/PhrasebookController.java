package com.zengshi.ecp.busi.im.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.im.vo.PhrasebookGroupRespVO;
import com.zengshi.ecp.busi.im.vo.PhrasebookItemRespVO;
import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookGroupReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookGroupResDTO;
import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookItemReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookItemResDTO;
import com.zengshi.ecp.im.dubbo.interfaces.IPhrasebookGroupRSV;
import com.zengshi.ecp.im.dubbo.interfaces.IPhrasebookItemRSV;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2017年4月7日下午4:32:15  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/phrasebook")
public class PhrasebookController extends EcpBaseController {
	
	private static String MODULE = ChatController.class.getName();
	/**
	 * 常用语分组
	 */
	@Resource
	private IPhrasebookGroupRSV phrasebookGroupRSV;
	/**
	 * 常用语短语
	 */
	@Resource
	private IPhrasebookItemRSV phrasebookItemRSV;
	
	/**
	 * 
	 * list:(查询常用语). <br/> 
	 * 
	 * @author linby
	 * @param model
	 * @return
	 * @throws Exception 
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public List<ImPhrasebookGroupResDTO> list(Model model, ImPhrasebookGroupReqDTO reqDTO) throws Exception{
		ImPhrasebookGroupReqDTO listDTO = new ImPhrasebookGroupReqDTO();
		//只接收group_class、shop_id
		listDTO.setGroupClass(reqDTO.getGroupClass());
		listDTO.setShopId(reqDTO.getShopId());
		//处理个人分组条件
		if(reqDTO.getGroupClass()!=null && reqDTO.getGroupClass().equals(ImConstants.PhraseBook.GROUP_CLASS_PRIVATE)){
			listDTO.setCreateStaff(reqDTO.getStaff().getId());
		}
		List<ImPhrasebookGroupResDTO> list = phrasebookGroupRSV.findPhrasebookGroupByShopId(listDTO);
		return list;
	}
	
	/**
	 * 
	 * saveGroup:(分组保存或修改). <br/> 
	 * 
	 * @author linby
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "group/save")
	@ResponseBody
	public PhrasebookGroupRespVO saveGroup(ImPhrasebookGroupReqDTO reqDTO) throws BusinessException{
		PhrasebookGroupRespVO respVO = new PhrasebookGroupRespVO();
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		if(reqDTO.getId()==null){
			Long id = phrasebookGroupRSV.savePhrasebookGroup(reqDTO);
			respVO.setId(id);
		}else{
			phrasebookGroupRSV.updatePhrasebookGroupById(reqDTO);
		}
		
		return respVO;
	}
	
	/**
	 * 
	 * switchGroup:(分组交换位置). <br/>
	 * 上移下移 
	 * 
	 * @author linby
	 * @param source
	 * @param dest
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@RequestMapping("group/switch/{source}/{dest}")
	@ResponseBody
	public EcpBaseResponseVO switchGroup(@PathVariable Long source, @PathVariable Long dest) throws BusinessException{
		
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		//查询
		ImPhrasebookGroupReqDTO sourceReq = new ImPhrasebookGroupReqDTO();
		sourceReq.setId(source);
		ImPhrasebookGroupResDTO sourceRes = phrasebookGroupRSV.queryPhrasebookGroupById(sourceReq);
		ImPhrasebookGroupReqDTO destReq = new ImPhrasebookGroupReqDTO();
		destReq.setId(dest);
		ImPhrasebookGroupResDTO destRes = phrasebookGroupRSV.queryPhrasebookGroupById(destReq);
		if(!sourceRes.getShopId().equals(destRes.getShopId())){
			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			respVO.setResultMsg("不同店铺下的分组不可以交换位置！");
			return respVO;
		}
		//交换
		Long sourceSortNo = sourceRes.getSortNo();
		sourceRes.setSortNo(destRes.getSortNo());
		destRes.setSortNo(sourceSortNo);
		ObjectCopyUtil.copyObjValue(sourceRes, sourceReq, null, false);
		ObjectCopyUtil.copyObjValue(destRes, destReq, null, false);
		phrasebookGroupRSV.updatePhrasebookGroupById(sourceReq);
		phrasebookGroupRSV.updatePhrasebookGroupById(destReq);
		
		return respVO;
	}
	
	/**
	 * 
	 * switchGroup:(常用语交换位置). <br/>
	 * 上移下移 
	 * 同个组里
	 * 
	 * @author linby
	 * @param source
	 * @param dest
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@RequestMapping("item/switch/{source}/{dest}")
	@ResponseBody
	public EcpBaseResponseVO switchItem(@PathVariable Long source, @PathVariable Long dest) throws BusinessException{
		
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		//查询
		ImPhrasebookItemReqDTO sourceReq = new ImPhrasebookItemReqDTO();
		sourceReq.setId(source);
		ImPhrasebookItemResDTO sourceRes = phrasebookItemRSV.queryPhrasebookItemById(sourceReq);
		ImPhrasebookItemReqDTO destReq = new ImPhrasebookItemReqDTO();
		destReq.setId(dest);
		ImPhrasebookItemResDTO destRes = phrasebookItemRSV.queryPhrasebookItemById(destReq);
		if(!sourceRes.getGroupId().equals(destRes.getGroupId())){
			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			respVO.setResultMsg("不同组下的元素不可以交换位置！");
			return respVO;
		}
		//交换
		Long sourceSortNo = sourceRes.getSortNo();
		sourceRes.setSortNo(destRes.getSortNo());
		destRes.setSortNo(sourceSortNo);
		ObjectCopyUtil.copyObjValue(sourceRes, sourceReq, null, false);
		ObjectCopyUtil.copyObjValue(destRes, destReq, null, false);
		phrasebookItemRSV.updatePhrasebookItemById(sourceReq);
		phrasebookItemRSV.updatePhrasebookItemById(destReq);
		
		return respVO;
	}
	
	/**
	 * 
	 * deleteGroup:(删除分组). <br/> 
	 * 
	 * @author linby
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "group/delete")
	@ResponseBody
	public EcpBaseResponseVO deleteGroup(ImPhrasebookGroupReqDTO reqDTO) throws BusinessException{
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		phrasebookGroupRSV.deletePhrasebookGroupById(reqDTO);
		
		return respVO;
	}
	
	@RequestMapping("item/save")
	@ResponseBody
	public PhrasebookItemRespVO saveItem(ImPhrasebookItemReqDTO reqDTO) throws BusinessException{
		PhrasebookItemRespVO respVO = new PhrasebookItemRespVO();
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		if(reqDTO.getId()==null){
			Long id = phrasebookItemRSV.savePhrasebookItem(reqDTO);
			respVO.setId(id);
		}else{
			phrasebookItemRSV.updatePhrasebookItemById(reqDTO);
		}
		
		return respVO;
	}
	
	/**
	 * 
	 * deleteItem:(删除常用语). <br/> 
	 * 
	 * @author linby
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "item/delete")
	@ResponseBody
	public EcpBaseResponseVO deleteItem(ImPhrasebookItemReqDTO reqDTO) throws BusinessException{
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		phrasebookItemRSV.deletePhrasebookItemById(reqDTO);
		
		return respVO;
	}
	
	
}
