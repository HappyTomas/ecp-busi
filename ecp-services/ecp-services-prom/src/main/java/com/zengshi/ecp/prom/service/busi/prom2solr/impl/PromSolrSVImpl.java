package com.zengshi.ecp.prom.service.busi.prom2solr.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.general.prom.util.PromMsgUtil;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSolrReqDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromMatchSV;
import com.zengshi.ecp.prom.service.busi.prom2solr.interfaces.IProm2SolrSV;
import com.zengshi.ecp.prom.service.busi.prom2solr.interfaces.IPromSolrSV;
import com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-11-19 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromSolrSVImpl extends GeneralSQLSVImpl implements IPromSolrSV {

	private static final String MODULE = PromSolrSVImpl.class.getName();

	@Resource
	private IProm2SolrSV prom2SolrSV;

	@Resource
	private IPromSkuSV promSkuSV;

	@Resource
	private IPromMatchSV promMatchSV;

	@Resource
	private IGdsCategoryRSV gdsCategoryRSV;

	@Resource
	private IGdsInfoQueryRSV gdsInfoQueryRSV;

	/**
	 * 
	 * TODO发送促销id的信息到solr
	 * 
	 * @see com.zengshi.ecp.prom.service.busi.thread.solr.interfaces.IPromSolrSV#sendSolrByPromId(com.zengshi.ecp.prom.dubbo.dto.PromSolrReqDTO)
	 * @param promSolrReqDTO
	 * @throws BusinessException
	 * @author huangjx
	 */
	public void sendSolr(PromSolrReqDTO promSolrReqDTO) throws BusinessException {
		try {
			if (promSolrReqDTO != null) {

				if (PromConstants.Prom2Solr.SEND_RANGE_1.equals(promSolrReqDTO.getSendRange())) {
					// 全量发送
					GdsUtils.sendGdsIndexMsg(promSolrReqDTO.getSiteId().toString(), null, MODULE);
				}
				if (PromConstants.Prom2Solr.SEND_RANGE_2.equals(promSolrReqDTO.getSendRange())) {
					// 分类发送
					GdsUtils.sendGdsIndexMsg(null, promSolrReqDTO.getCatgCode().toString(), MODULE);
				}
				if (PromConstants.Prom2Solr.SEND_RANGE_3.equals(promSolrReqDTO.getSendRange())) {
					// 商品发送
					GdsInfoReqDTO gdsInfo = new GdsInfoReqDTO();
					gdsInfo.setId(promSolrReqDTO.getGdsId());
					GdsInfoRespDTO resp = gdsInfoQueryRSV.queryGdsInfoByOption(gdsInfo);
					// 上架并且商品存在
					if (resp != null && resp.getId() != null && GdsUtils.isOnShelves(resp.getGdsStatus())) {
					      LogUtil.info(MODULE, "商品编码开始" + promSolrReqDTO.getGdsId());
						GdsUtils.sendGdsIndexMsg(null, "T_GDS_INFO", MODULE, promSolrReqDTO.getGdsId(), resp.getCatlogId());
						  LogUtil.info(MODULE, "商品编码结束" + promSolrReqDTO.getGdsId());
					}
				}
			}
		} catch (BusinessException bx) {
			LogUtil.error(MODULE, "BusinessException批量发送执行solr报错啦" + bx.toString());
		} catch (Exception ex) {
			LogUtil.error(MODULE, "Exception批量发送执行solr报错啦" + ex.toString());
		}
	}

	/**
	 * TODO通过促销id获得列表数据
	 * 
	 * @see com.zengshi.ecp.prom.service.busi.thread.solr.interfaces.IPromSolrSV#queryDataByPromId(com.zengshi.ecp.prom.dubbo.dto.PromSolrReqDTO)
	 * @param promSolrReqDTO
	 * @throws BusinessException
	 * @author huangjx
	 */
	public void sendData(Prom2SolrReqDTO prom2SolrReqDTO) throws BusinessException {

		// 取发送消息数据 展示开始时间 展示截止时间 状态为 10生效 和20 失效
		PageResponseDTO<Prom2SolrRespDTO> page = prom2SolrSV.queryProm2SolrForPage(prom2SolrReqDTO);

		if (page == null) {
			LogUtil.error(MODULE, "居然没有促销发送数据呀");
			return;
		}
		if (page.getCount() <= 0) {
			LogUtil.error(MODULE, "居然没有促销发送数据呀");
			return;
		}
		if(CollectionUtils.isEmpty(page.getResult())){
	            LogUtil.error(MODULE, "居然没有促销发送数据呀");
	            return;
	     }
		// 全场 标记 默认false
		boolean ifAll = false;
		// 如果存在全场 其他的促销可不用跑

		// 分类map
		Map<String, String> catgMap = new HashMap<String, String>();

		// 商品map
		Map<String, String> gdsMap = new HashMap<String, String>();

		// 组织待发送数据
		for (Prom2SolrRespDTO dto : page.getResult()) {
			if (PromConstants.PromInfo.JOIN_RANGE_1.equals(dto.getJoinRange())) {
				// 存在全场促销
				ifAll = true;
				break;
			}
		}
		if (!ifAll) {
		    Map<String,Prom2SolrRespDTO> map=new HashMap<String,Prom2SolrRespDTO>();
		    //过滤重复数据
		    for (Prom2SolrRespDTO dto : page.getResult()) {
		       if(!map.containsKey(dto.getPromId().toString())){
		           map.put(dto.getPromId().toString(), dto);
		       }
		    }
		    if(!CollectionUtils.isEmpty(map)){
		        Iterator it = map.keySet().iterator();  
	               while (it.hasNext()) {
	                   String key = it.next().toString();  
	                   Prom2SolrRespDTO dto=map.get(key);
					if (PromConstants.PromInfo.JOIN_RANGE_1.equals(dto.getJoinRange())) {
						// 存在全场促销
						ifAll = true;
						break;
					} else {
					    //很多商品 如何处理？？
						List<PromSku> l = promSkuSV.querySkuPromByPromId(dto.getPromId());
						if (!CollectionUtils.isEmpty(l)) {
							for (PromSku promSku : l) {
								if (!StringUtil.isEmpty(promSku.getCatgCode())) {
									catgMap.put(promSku.getCatgCode(), "1");
								}
								if (!StringUtil.isEmpty(promSku.getGdsId())) {
									gdsMap.put(promSku.getGdsId().toString(), "1");
								}
							}
						}
					}
				// 计算搭配
				if (!ifAll) {
					if (PromConstants.PromInfo.IF_SHOW_0.equals(dto.getIfShow())) {
					    //很多搭配如何处理？？？
						List<PromMatchSkuDTO> l = promMatchSV.queryMatchSkuListByPromId(dto.getPromId());
						if (!CollectionUtils.isEmpty(l)) {
							for (PromMatchSkuDTO promMatchSkuDTO1 : l) {
								if (!StringUtil.isEmpty(promMatchSkuDTO1.getGdsId())) {
									gdsMap.put(promMatchSkuDTO1.getGdsId().toString(), "1");
								}
							}
						}
					}
				}

			}
		 }
		}
		// 发送数据调用
		if (ifAll) {
			PromSolrReqDTO promSolrReqDTO = new PromSolrReqDTO();
			promSolrReqDTO.setSiteId(prom2SolrReqDTO.getSiteId());
			promSolrReqDTO.setSendRange(PromConstants.Prom2Solr.SEND_RANGE_1);
			this.sendSolr(promSolrReqDTO);
		} else {
			// 分类发送
			if (!CollectionUtils.isEmpty(catgMap)) {
				Iterator i = catgMap.entrySet().iterator();
				PromSolrReqDTO promSolrReqDTO = new PromSolrReqDTO();
				while (i.hasNext()) {
					Map.Entry e = (Map.Entry) i.next();
					if (e.getKey() != null) {
						promSolrReqDTO.setCatgCode(Long.valueOf(e.getKey().toString()));
						promSolrReqDTO.setSendRange(PromConstants.Prom2Solr.SEND_RANGE_2);
						this.sendSolr(promSolrReqDTO);
					}
				}
			}

			// 商品发送
			if (!CollectionUtils.isEmpty(gdsMap)) {
				Iterator i = gdsMap.entrySet().iterator();
				PromSolrReqDTO promSolrReqDTO = new PromSolrReqDTO();
				while (i.hasNext()) {
					Map.Entry e = (Map.Entry) i.next();
					if (e.getKey() != null) {
						promSolrReqDTO.setGdsId(Long.valueOf(e.getKey().toString()));
						promSolrReqDTO.setCatgCode(Long.valueOf(e.getKey().toString()));
						promSolrReqDTO.setSendRange(PromConstants.Prom2Solr.SEND_RANGE_3);
						this.sendSolr(promSolrReqDTO);
					}
				}
			}
		}
		// 更新服务
		Prom2SolrReqDTO updateDTO = new Prom2SolrReqDTO();
		Date d = DateUtil.getSysDate();
		for (Prom2SolrRespDTO dto : page.getResult()) {
			updateDTO.setSendStatus(PromConstants.Prom2Solr.SEND_STATUS_1);
			updateDTO.setId(dto.getId());
			updateDTO.setSendTime(d);
			updateDTO.setUpdateTime(DateUtil.getSysDate());
			updateDTO.setUpdateStaff(0l);
			try {
				prom2SolrSV.update(updateDTO);
			} catch (BusinessException bx) {
				LogUtil.error(MODULE, "prom2SolrSV.update报错啦BusinessException" + bx.toString());
			} catch (Exception ex) {
				LogUtil.error(MODULE, "prom2SolrSV.update报错啦Exception" + ex.toString());
			}
		}
	}
	/**
     * TODO通过促销id获得列表数据
     * 
     * @see com.zengshi.ecp.prom.service.busi.thread.solr.interfaces.IPromSolrSV#queryDataByPromId(com.zengshi.ecp.prom.dubbo.dto.PromSolrReqDTO)
     * @param promSolrReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void sendDataThread(Prom2SolrReqDTO prom2SolrReqDTO) throws BusinessException { 
        String strJson="";
        try{
            strJson=JSON.toJSONString(prom2SolrReqDTO);
            this.sendData(strJson);
      }catch(Exception ex){
          LogUtil.info(MODULE, "参数转为bean"+strJson);
          LogUtil.info(MODULE, "异常信息"+ex.toString());
      }
      
    }
	 /**
	 * TODO通过促销id获得列表数据
	 * @see com.zengshi.ecp.prom.service.busi.prom2solr.interfaces.IPromSolrSV#sendData(java.lang.String)
	 * @param strJson
	 * @throws BusinessException
	 * @author huangjx
	 */
	public void sendData(String strJson) throws BusinessException{
	    
	    if(StringUtil.isEmpty(strJson)){
	        LogUtil.error(MODULE, "输入参数为空哦");
	        return ;
	    }
	    ExecutorService threadExecutor = Executors.newCachedThreadPool();  
	    try{
    	    
    	      SendDataTaskSVImpl task1 = new SendDataTaskSVImpl("",strJson);  
    	      
    	      //主要异步处理 线程池用不上
    	      threadExecutor.execute( task1 );  
    	  
    	      threadExecutor.shutdown();   
    	      
	    }catch(Exception ex){
	        LogUtil.error(MODULE, "参数转为bean"+strJson);
	        LogUtil.error(MODULE, "异常信息"+ex.toString());
	    }finally{
	        if(threadExecutor!=null && !threadExecutor.isShutdown()){
	            threadExecutor.shutdown();
	        }
	    }
    }
    /**
     * TODO 促销商品发送solr
     * @see com.zengshi.ecp.prom.service.busi.prom2solr.interfaces.IPromSolrSV#sendPromGdsData(com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO)
     * @param prom2SolrReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void sendPromGdsData(Prom2SolrReqDTO prom2SolrReqDTO) throws BusinessException {

        // 取发送消息数据 展示开始时间 展示截止时间 状态为 10生效 和20 失效
        prom2SolrReqDTO.setSolrType(PromConstants.Prom2Solr.SOLR_TYPE_2);
        PageResponseDTO<Prom2SolrRespDTO> page = prom2SolrSV.queryProm2SolrForPage(prom2SolrReqDTO);

        if (page == null) {
            LogUtil.error(MODULE, "居然没有促销发送数据呀");
            return;
        }
        if (page.getCount() <= 0) {
            LogUtil.error(MODULE, "居然没有促销发送数据呀");
            return;
        }
        if(CollectionUtils.isEmpty(page.getResult())){
            LogUtil.error(MODULE, "居然没有促销发送数据呀");
            return;
        }
        // 更新服务
        Prom2SolrReqDTO updateDTO = new Prom2SolrReqDTO();
        Date d = DateUtil.getSysDate();
        for (Prom2SolrRespDTO dto : page.getResult()) {
            
            //发生消息
            PromMsgUtil.sendPromIndexMsg(dto.getSiteId(), dto.getPromId(), null, dto.getStatus(), PromSolrSVImpl.class.toString());
            
            updateDTO.setSendStatus(PromConstants.Prom2Solr.SEND_STATUS_1);
            updateDTO.setId(dto.getId());
            updateDTO.setSendTime(d);
            updateDTO.setUpdateTime(DateUtil.getSysDate());
            updateDTO.setUpdateStaff(0l);
            try {
                prom2SolrSV.update(updateDTO);
            } catch (BusinessException bx) {
                LogUtil.error(MODULE, JSON.toJSONString(updateDTO));
                LogUtil.error(MODULE, "prom2SolrSV.update报错啦BusinessException" + bx.toString());
            } catch (Exception ex) {
                LogUtil.error(MODULE, JSON.toJSONString(updateDTO));
                LogUtil.error(MODULE, "prom2SolrSV.update报错啦Exception" + ex.toString());
            }
        }
    }
}
