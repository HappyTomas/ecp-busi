package com.zengshi.ecp.prom.service.busi.importdata.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RecursiveTask;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.general.prom.util.PromMsgUtil;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSku2PropPropIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dao.model.PromStockLimit;
import com.zengshi.ecp.prom.dubbo.dto.ImprotPromResultDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.importdata.interfaces.ISkuInfoSwitchSV;
import com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MoneyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-prom-server <br>
 * Description: <br>
 * Date:2015-12-31下午3:45:02  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * forkjoin并行任务分配后，数据导入工作类
 * 
 */
public class ImportDistributeDataSubFJTask extends RecursiveTask<ImprotPromResultDTO>{

    private static final long serialVersionUID = -5838207561858858969L;
    private static final String MODULE = ImportDistributeDataSubFJTask.class.getName();

    //需要处理的数据
    private List<List<Object>> datas;
    //处理的文件
    private String datasFileId = null;
    private int startIndex;
    private int endIndex;
    
    
    private CountDownLatch countDownLatch = null;
    
    private PromInfoDTO promInfoDTO = null;
    private PromImportReqDTO promImportReqDTO = null;

    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV = EcpFrameContextHolder.getBean("gdsSkuInfoQueryRSV", IGdsSkuInfoQueryRSV.class);
    
    private IGdsInfoExternalRSV gdsInfoExternalRSV = EcpFrameContextHolder.getBean("gdsInfoExternalRSV", IGdsInfoExternalRSV.class);
    
    private IPromSkuSV promSkuSV=  EcpFrameContextHolder.getBean("promSkuSV", IPromSkuSV.class);
    
    private ISkuInfoSwitchSV skuInfoSwitchSV =  EcpFrameContextHolder.getBean("skuInfoSwitchSV", ISkuInfoSwitchSV.class);
    private PaasSequence seq_prom_import_id = EcpFrameContextHolder.getBean("seq_prom_import_id", PaasSequence.class);
    
    
    public ImportDistributeDataSubFJTask()
    {
        
    }
    
    public ImportDistributeDataSubFJTask(int startIndex, int endIndex, CountDownLatch countDownLatch, List<List<Object>> datas)
    {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.countDownLatch = countDownLatch;
        this.datas = datas;
    }

    @Override
    protected ImprotPromResultDTO compute() {       
       return doCompute();
    }
    
    private ImprotPromResultDTO  doCompute(){
    	 LogUtil.info(MODULE, "子任务[id:"+Thread.currentThread().getId()+"]开始执行,工作区间[startIndex="+startIndex+",endIndex="+endIndex+"]");
         
         ImprotPromResultDTO IMPROT_RESULT = new ImprotPromResultDTO();
         //处理子任务应该处理的数据偏移
         for(int i=startIndex; i<endIndex; i++)
         {
             List<Object> row= datas.get(i);
             PromImportReqDTO excelData = null;
             try {
                 //读取一行的数据并解析
                 excelData = readExcel(row);
                 
                 PromSku promSku = new PromSku();
                 PromStockLimit promStockLimit = new PromStockLimit();
                 //将数据解析成可入库格式的对象
                 paraExcelData(excelData, promSku, promStockLimit);
                 
                 //入库,需要判断是否入库成功
                 saveRowData(promSku, promStockLimit);
             }catch(BusinessException be){
                 //处理失败，错误数+1
                 LogUtil.error(MODULE, "批量导入单品/处理失败"+be.toString());
                 IMPROT_RESULT.setErrorCount(IMPROT_RESULT.getErrorCount()+1);
//                 IMPROT_RESULT.getErrorList().add(new ImportPromErrorInfoDTO( excelData.getPromCnt().toString(),excelData.getPromId().toString(),be.getErrorCode(), be.getErrorMessage()));
                 continue;
             }
             catch (Exception e) {
                 // TODO: handle exception
                 LogUtil.error(MODULE, "批量导入单品错误"+e.toString());
                 IMPROT_RESULT.setErrorCount(IMPROT_RESULT.getErrorCount()+1);
//                 IMPROT_RESULT.getErrorList().add(new ImportPromErrorInfoDTO( excelData.getPromCnt().toString(),excelData.getPromId().toString()));
                 continue;
             }

             //处理成功，成功数+1
             IMPROT_RESULT.setSuccessCount(IMPROT_RESULT.getSuccessCount()+1);
         }
         LogUtil.info(MODULE, "子任务[id:"+Thread.currentThread().getId()+"]执行结束，处理结果："+IMPROT_RESULT.toString());

         //计算器做减法
         countDownLatch.countDown();
         
         return IMPROT_RESULT;
    }
    

    private PromImportReqDTO readExcel(List<Object> row)throws BusinessException
    {
        Long shopId = promInfoDTO.getShopId();
        String fileId = this.getDatasFileId();
        Long siteId = promInfoDTO.getSiteId();

        String skuId = "";
        String promCnt = "";
        String priceType = null;
        Long priceValue = null;
        // 基础校验：站点编码 店铺编码 促销编码 商品编码 单品编码 活动量 促销价格
        String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
        // 商品编码
        /*
         * boolean item3 = row.get(3).toString().matches("[0-9]+"); if(!(item3)){ throw new
         * BusinessException("prom.400187"); }
         */
        try{
        	skuInfoSwitchSV.checkedRow(row);
        } catch (NullPointerException e) {
            throw new BusinessException("prom.400212");
        }
        try {
        	//方便其他工程做扩展
        	skuId = skuInfoSwitchSV.switchSkuId(row);          
        } catch (NullPointerException e) {
            throw new BusinessException("prom.400195");
        }
        try {
            // 活动量
            boolean item1 = row.get(1).toString().matches(regex);
            if (!(item1)) {
                throw new BusinessException("prom.400189", new String[] { row.get(1).toString() });
            }
            promCnt = row.get(1).toString();
            if (promCnt.indexOf(".") > 0) {
                promCnt = promCnt.replaceAll("0+?$", "");// 去掉多余的0
                promCnt = promCnt.replaceAll("[.]$", "");// 如最后一位是.则去掉
            }
        } catch (NullPointerException e) {
            throw new BusinessException("prom.400196");
        }
        //秒杀促销特殊处理
        if ("1000000019".equals(promInfoDTO.getPromTypeCode())) {
            try { 
                //秒杀价格类型
                String item2 = row.get(2).toString(); 
                if(StringUtil.isBlank(item2))
                { 
                    throw new BusinessException(); 
                }
                if (item2.indexOf(".")>0) {
                    item2 = item2.replaceAll("0+?$", "");
                    item2 = item2.replaceAll("[.]$", "");
                }
                if (!PromConstants.PromDiscountRule.SECKILL_PRICE_TYPE_0.equals(item2)&&!PromConstants.PromDiscountRule.SECKILL_PRICE_TYPE_1.equals(item2)) {
                    throw new BusinessException();
                }

                priceType = item2;
            } catch(NullPointerException e) { 
                throw new BusinessException(); 
            }
            catch (Exception e) {
                // TODO: handle exception
                throw e;
            }
             try { 
                 //秒杀价 
                 boolean item3 = row.get(3).toString().matches(regex); 
                 if(!(item3))
                 { 
                     throw new BusinessException(); 
                 } 
                 priceValue = Long.valueOf(MoneyUtil.convertYuanToCent(row.get(3).toString()));
                 if (!(priceValue>0)) {
                    throw new BusinessException();
                }
                 if (PromConstants.PromDiscountRule.SECKILL_PRICE_TYPE_0.equals(priceType)) {
                    if (priceValue > 10000) {
                        throw new BusinessException();
                    }
                }
             } catch(NullPointerException e) { 
                 throw new BusinessException(); 
             }  
             catch (Exception e) {
                // TODO: handle exception
                 throw e;
            }
        }

         
        // 验证 单品 店铺 站点是否一致
        GdsSkuInfoReqDTO dto = new GdsSkuInfoReqDTO();
        dto.setId(Long.valueOf(skuId));
        // 库存查询 设置条件
        SkuQueryOption[] skuQuery = { SkuQueryOption.BASIC };
        dto.setSkuQuery(skuQuery);
        GdsSkuInfoRespDTO gdsSkuInforespDTO = new GdsSkuInfoRespDTO();
        gdsSkuInforespDTO = gdsSkuInfoQueryRSV.querySkuInfoByOptions(dto);
        if (gdsSkuInforespDTO == null) {
            // 单品不存在
            throw new BusinessException("prom.400199", new String[] { skuId });
        }
        if (!shopId.equals(gdsSkuInforespDTO.getShopId())) {
            // 该店铺 不存在此单品
            throw new BusinessException("prom.400200", new String[] { shopId.toString(), skuId });
        }

        // 通过后 放入set 过滤重复
        String skuIdStr = "";
        // if(!setSku.contains(skuId)){
        PromImportReqDTO reqDTO = new PromImportReqDTO();
        reqDTO.setStaff(promImportReqDTO.getStaff());
        reqDTO.setAppName(promImportReqDTO.getAppName());
        // reqDTO.setCatgCode(catgCode)
        reqDTO.setCreateStaff(promImportReqDTO.getStaff().getId());
        reqDTO.setCurrentSiteId(promImportReqDTO.getCurrentSiteId());
        // reqDTO.setEveryTimeCnt(everyTimeCnt)
        reqDTO.setFileName(promImportReqDTO.getFileName());
        reqDTO.setFileId(fileId);
        reqDTO.setGdsId(gdsSkuInforespDTO.getGdsId());
        reqDTO.setGdsName(gdsSkuInforespDTO.getGdsName());
        // 序列号
        // reqDTO.setImportDesc(importDesc);
        // reqDTO.setImportType(importType);
        reqDTO.setPromCnt(Long.valueOf(promCnt));
        reqDTO.setPriceType(priceType);
        reqDTO.setPriceValue(priceValue);
        
        //不需要库存 可以无限大
        //if(Long.valueOf(2).equals(gdsSkuInforespDTO.getGdsTypeId())){
        LongReqDTO longReqDTO=new LongReqDTO();
        longReqDTO.setId(gdsSkuInforespDTO.getGdsTypeId());
        if(!gdsInfoExternalRSV.isNeedStockAmount(longReqDTO)){
            //虚拟商品 默认无限大
            reqDTO.setPromCnt(Long.valueOf(Integer.MAX_VALUE));
        }
        reqDTO.setPromId(promImportReqDTO.getPromId());
        reqDTO.setShopId(shopId);
        reqDTO.setSiteId(siteId);
        reqDTO.setSkuId(gdsSkuInforespDTO.getId());
        reqDTO.setImportType(promImportReqDTO.getImportType());
        reqDTO.setCreateTime(DateUtil.getSysDate());
        reqDTO.setId(seq_prom_import_id.nextValue());
        return reqDTO;
    
    }
    
    private void paraExcelData(PromImportReqDTO excelData,PromSku promSku,PromStockLimit promStockLimit)
    {
        if(promSku == null)
        {
            promSku=new PromSku();
        }
        promSku.setCreateStaff(excelData.getCreateStaff());
        promSku.setCreateTime(new Timestamp(DateUtil.getSysDate().getTime()));
        promSku.setEndTime(promInfoDTO.getEndTime());
        promSku.setGdsId(excelData.getGdsId());
        promSku.setIfComposit(promInfoDTO.getIfComposit());
        promSku.setIfShow(promInfoDTO.getIfShow());
        promSku.setJoinType(PromConstants.PromSku.JOIN_TYPE_2);
        promSku.setMatchType(PromConstants.PromSku.MATCH_TYPE_1);
        promSku.setPriority(promInfoDTO.getPriority());
        promSku.setPromClass(promInfoDTO.getPromClass());
        if(StringUtil.isEmpty(excelData.getPromCnt())){
            promSku.setPromCnt(Long.valueOf(Integer.MAX_VALUE));
        }else{
            promSku.setPromCnt(excelData.getPromCnt());
        }
        promSku.setPriceType(excelData.getPriceType());
        promSku.setPriceValue(excelData.getPriceValue());
        promSku.setPromId(excelData.getPromId());
        promSku.setShopId(excelData.getShopId());
        promSku.setShowEndTime(promInfoDTO.getShowEndTime());
        promSku.setShowStartTime(promInfoDTO.getShowStartTime());
        promSku.setSiteId(promInfoDTO.getSiteId());
        promSku.setSkuId(excelData.getSkuId());
        promSku.setStatus(promInfoDTO.getStatus());
        promSku.setStartTime(promInfoDTO.getStartTime());
        promSku.setIfValid(PromConstants.PromSku.IF_VALID_1);
        
        if(promStockLimit == null)
        {
            promStockLimit=new PromStockLimit();
        }
        
        promStockLimit.setBuyCnt(new Long(0));
        promStockLimit.setGdsId(promSku.getGdsId());
        promStockLimit.setPromCnt(promSku.getPromCnt().longValue());
        promStockLimit.setPromId(promSku.getPromId());
        promStockLimit.setRemaindCnt(promStockLimit.getPromCnt());
        promStockLimit.setSkuId(promSku.getSkuId());
        promStockLimit.setCreateStaff(promSku.getCreateStaff());
        promStockLimit.setCreateTime(new Timestamp(DateUtil.getSysDate().getTime()));
        
    }
    private void saveRowData(PromSku promSku,PromStockLimit promStockLimit) throws BusinessException
    {
        try {

            //验证是否 单品id+促销编码+有效的已经存在  不存在insert（这里仅仅过滤和既往是否存在相同，当前的重复数据已在上面程序过滤了）
            PromSkuDTO promSkuDTO=new PromSkuDTO();
            promSkuDTO.setPageSize(1);
            promSkuDTO.setPromId(promSku.getPromId());
            promSkuDTO.setSkuId(promSku.getSkuId());
            promSkuDTO.setIfValid(PromConstants.PromSku.IF_VALID_1);
            
            
            Long count = promSkuSV.countNum(promSkuDTO);
            //Long count = 0L;
            
            if(count!=null && count > 0){
                LogUtil.info(MODULE, "存在相同的单品编码 更新促销数量"+JSON.toJSONString(promSku));
                promSkuDTO.setUpdateStaff(promSku.getCreateStaff());
                promSkuDTO.setPromCnt(promSku.getPromCnt());
                promSkuSV.updatePromSkuCnt(promSkuDTO);
            }else{
            
            promSkuSV.savePromSku(promSku);
            //库存量设置
            promSkuSV.savePromStockLimit(promStockLimit);
            
            //发送消息
            PromMsgUtil.sendPromIndexMsg(promSku.getSiteId(), promSku.getPromId(), promSku.getSkuId(), promSku.getStatus(), ImportDistributeDataSubFJTask.class.toString());
            
            }
        
        } catch (Exception ex) {
            // TODO: 记录错误信息
            LogUtil.error(MODULE, "ImportSkuTaskSVImpl传入参数="+JSON.toJSONString(promSku));
            LogUtil.error(MODULE, ex.toString());
            throw new BusinessException("");
        }
    }
    public List<List<Object>> getDatas() {
        return datas;
    }

    public void setDatas(List<List<Object>> datas) {
        this.datas = datas;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public PromInfoDTO getPromInfoDTO() {
        return promInfoDTO;
    }

    public void setPromInfoDTO(PromInfoDTO promInfoDTO) {
        this.promInfoDTO = promInfoDTO;
    }

    public PromImportReqDTO getPromImportReqDTO() {
        return promImportReqDTO;
    }

    public void setPromImportReqDTO(PromImportReqDTO promImportReqDTO) {
        this.promImportReqDTO = promImportReqDTO;
    }

    public String getDatasFileId() {
        return datasFileId;
    }

    public void setDatasFileId(String datasFileId) {
        this.datasFileId = datasFileId;
    }

}

