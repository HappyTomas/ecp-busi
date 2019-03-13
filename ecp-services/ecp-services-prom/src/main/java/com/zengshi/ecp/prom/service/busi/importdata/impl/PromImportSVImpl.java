package com.zengshi.ecp.prom.service.busi.importdata.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.prom.dao.mapper.busi.PromImportLogMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromImportMapper;
import com.zengshi.ecp.prom.dao.model.PromImport;
import com.zengshi.ecp.prom.dao.model.PromImportCriteria;
import com.zengshi.ecp.prom.dao.model.PromImportCriteria.Criteria;
import com.zengshi.ecp.prom.dao.model.PromImportFile;
import com.zengshi.ecp.prom.dao.model.PromImportLog;
import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dao.model.PromStockLimit;
import com.zengshi.ecp.prom.dubbo.dto.ImprotPromResultDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromImportFileReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromImportRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.Converter;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportFileSV;
import com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.impl.datainout.DataImportHandler;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.server.util.DataInoutUtil;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ExcelFileUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-12-03 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromImportSVImpl extends GeneralSQLSVImpl implements IPromImportSV {

    private static final String MODULE = PromImportSVImpl.class.getName();
    //线程池大小
    private static final int FORK_JOIN_THREADS = 4*8;
    @Resource
    private PromImportMapper promImportMapper;
    
    @Resource
    private PromImportLogMapper promImportLogMapper;
    
    @Resource
    private  Converter<PromImportReqDTO, PromImport> promImportConverter;
    
    @Resource
    private Converter<PromImport, PromImportRespDTO> promImportRespDTOConverter;
    
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    
    @Resource
    private IGdsInfoExternalRSV gdsInfoExternalRSV;
    
    @Resource
    private IPromInfoSV promInfoSV;
    
    @Resource
    private IPromImportFileSV promImportFileSV;
    
    @Resource(name = "seq_prom_import_id")
    private PaasSequence seq_prom_import_id;
    
    //默认线程处理个数
    private static int nThreadsCount=30;
  
    public static final int cacheSeconds = 86400;//秒  86400秒=24小时
    /**
     * 多线程支持删除
     * @param promId
     * @param staffId
     * @throws BusinessException
     * @author huangjx
     */
    private void deleteMulitThread(Long promId,Long staffId) throws BusinessException{
        
        if(StringUtil.isEmpty(promId) || StringUtil.isEmpty(staffId)){
            LogUtil.error(MODULE, "输入参数为空哦");
            return ;
        }
        PromImportReqDTO promImportReqDTO=new PromImportReqDTO();
        promImportReqDTO.setPromId(promId);
        promImportReqDTO.setCreateStaff(staffId);
        List<PromImportRespDTO>  list= this.queryPromImportList(promImportReqDTO);
        
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        ExecutorService threadExecutor = Executors.newFixedThreadPool(nThreadsCount);
        
        try{
             for(PromImportRespDTO promImportRespDTO:list){
                 PromImportReqDTO reqDTO=new PromImportReqDTO();
                 reqDTO.setId(promImportRespDTO.getId());
                 ImportDataDeleteTaskSVImpl task1 = new ImportDataDeleteTaskSVImpl(reqDTO);  
                 threadExecutor.execute( task1 );  
             }
          
              threadExecutor.shutdown();   
             
        }catch(Exception ex){
            LogUtil.error(MODULE, "异常信息"+ex.toString());
        }finally{
            if(threadExecutor!=null && !threadExecutor.isShutdown()){
                threadExecutor.shutdown();  
            }
        }
    }
    /**
     * TODO 多线程支持 保存 正式导入
     * @see com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportSV#saveMulitThread(com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO)
     * @param promImportReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveMulitThreadSku(List<PromImportReqDTO> list,PromInfoDTO promInfoDTO) throws BusinessException{

        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "输入参数为空哦");
            return ;
        }
        ExecutorService threadExecutor = Executors.newFixedThreadPool(nThreadsCount);
        
        CountDownLatch countDownLatch  =  new  CountDownLatch (list.size()) ;
        
        try{
             for(PromImportReqDTO promImportReqDTO:list){
                 PromSku promSku=new PromSku();
                 promSku.setCreateStaff(promImportReqDTO.getCreateStaff());
                 promSku.setCreateTime(new Timestamp(DateUtil.getSysDate().getTime()));
                 promSku.setEndTime(promInfoDTO.getEndTime());
                 promSku.setGdsId(promImportReqDTO.getGdsId());
                 promSku.setIfComposit(promInfoDTO.getIfComposit());
                 promSku.setIfShow(promInfoDTO.getIfShow());
                 promSku.setJoinType(PromConstants.PromSku.JOIN_TYPE_2);
                 promSku.setMatchType(PromConstants.PromSku.MATCH_TYPE_1);
                 promSku.setPriority(promInfoDTO.getPriority());
                 promSku.setPromClass(promInfoDTO.getPromClass());
                 if(StringUtil.isEmpty(promImportReqDTO.getPromCnt())){
                     promSku.setPromCnt(Long.valueOf(Integer.MAX_VALUE));
                 }else{
                     promSku.setPromCnt(promImportReqDTO.getPromCnt());
                 }
                 promSku.setPromId(promImportReqDTO.getPromId());
                 promSku.setShopId(promImportReqDTO.getShopId());
                 promSku.setShowEndTime(promInfoDTO.getShowEndTime());
                 promSku.setShowStartTime(promInfoDTO.getShowStartTime());
                 promSku.setSiteId(promInfoDTO.getSiteId());
                 promSku.setSkuId(promImportReqDTO.getSkuId());
                 promSku.setStatus(promInfoDTO.getStatus());
                 promSku.setStartTime(promInfoDTO.getStartTime());
                 promSku.setIfValid(PromConstants.PromSku.IF_VALID_1);
                 
                 PromStockLimit promStockLimit=new PromStockLimit();
                 
                 promStockLimit.setBuyCnt(new Long(0));
                 promStockLimit.setGdsId(promSku.getGdsId());
                 promStockLimit.setPromCnt(promSku.getPromCnt().longValue());
                 promStockLimit.setPromId(promSku.getPromId());
                 promStockLimit.setRemaindCnt(promStockLimit.getPromCnt());
                 promStockLimit.setSkuId(promSku.getSkuId());
                 promStockLimit.setCreateStaff(promSku.getCreateStaff());
                 promStockLimit.setCreateTime(new Timestamp(DateUtil.getSysDate().getTime()));
                 
                 ImportSkuTaskSVImpl task1 = new ImportSkuTaskSVImpl(promImportReqDTO,promSku,promStockLimit,countDownLatch);  
                 threadExecutor.execute( task1 );  
             }
              //countDownLatch.await();
              
              threadExecutor.shutdown();   
             
        }catch(Exception ex){
            LogUtil.error(MODULE, "异常信息"+ex.toString());
            CacheUtil.addItem(list.get(0).getFileId(), "2", cacheSeconds);
        }finally{
            if(threadExecutor!=null && !threadExecutor.isShutdown()){
                threadExecutor.shutdown();  
            }
        }
    
    }
    /**
     * TODO 多线程支持 保存 
     * @see com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportSV#saveMulitThread(com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO)
     * @param promImportReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveMulitThread(List<PromImportReqDTO> list) throws BusinessException{
        
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "输入参数为空哦");
            return ;
        }
        ExecutorService threadExecutor = Executors.newFixedThreadPool(nThreadsCount);
        
        CountDownLatch countDownLatch  =  new  CountDownLatch (list.size()) ;
        
        try{
             for(PromImportReqDTO promImportReqDTO:list){
                 ImportDataTaskSVImpl task1 = new ImportDataTaskSVImpl(promImportReqDTO,countDownLatch);  
                 threadExecutor.execute( task1 );  
             }
              //countDownLatch.await();
              
              threadExecutor.shutdown();   
             
        }catch(Exception ex){
            LogUtil.error(MODULE, "异常信息"+ex.toString());
        }finally{
            if(threadExecutor!=null && !threadExecutor.isShutdown()){
                threadExecutor.shutdown();  
            }
        }
    }
    /**
     * TODO 多线程支持 读取excel
     * @see com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportSV#saveMulitThread(com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO)
     * @param promImportReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void readMulitThread(List<List<Object>> list,String fileId, Long shopId, Long siteId, PromImportReqDTO promImportReqDTO,List<PromImportReqDTO> importList) throws BusinessException{
        
        if(CollectionUtils.isEmpty(list)){
            LogUtil.error(MODULE, "输入参数为空哦");
            return ;
        }
        if(list.size()<=1){
            LogUtil.error(MODULE, "输入参数为空哦");
            return ;
        }
        Set importSet=new HashSet();
        
        boolean ifSuccess=true;
        ExecutorService threadExecutor = Executors.newFixedThreadPool(nThreadsCount);
        
        try{
            
            ArrayList<Future<PromImportReqDTO>> al=new ArrayList<Future<PromImportReqDTO>>();  
            for(int i=1;i<list.size();i++){  
                
                al.add(threadExecutor.submit(new ReadExcelDataTaskSVImpl(list.get(i),null,fileId,shopId,siteId,promImportReqDTO)));  
            }  
              
            for(Future<PromImportReqDTO> fs:al){  
                try {
                    PromImportReqDTO r=fs.get();
                    if(r==null){
                        importList=null;
                        ifSuccess=false;
                        break;
                    }
                    if(!importSet.contains(r.getSkuId())){
                        importList.add(r);
                    }
                    importSet.add(r.getSkuId());
                } catch (InterruptedException e) {  
                    LogUtil.error(MODULE, "异常信息InterruptedException"+e.toString());
                } catch (ExecutionException e) {  
                    LogUtil.error(MODULE, "异常信息ExecutionException"+e.toString());
                }  
            }
            //清空处理
            importSet=null;
            //失败
            if(!ifSuccess){
                throw new BusinessException("prom.400206");
            }
              threadExecutor.shutdown();   
              
              
        }catch(Exception ex){
            LogUtil.error(MODULE, "异常信息"+ex.toString());
            CacheUtil.addItem(fileId, "2", cacheSeconds);
            if((ex instanceof BusinessException)){
                throw new BusinessException(((BusinessException) ex).getErrorMessage());
            }else{
                throw new BusinessException("prom.400203");
            }
        }finally{
            if(threadExecutor!=null && !threadExecutor.isShutdown()){
                threadExecutor.shutdown();  
            }
        }
    }
    /**
     * 导入中间表保存  通过monogo id解析文件 
     * @param fileId
     * @throws BusinessException
     * @author huangjx
     */
    public void saveByFile(final PromImportReqDTO promImportReqDTO) throws BusinessException{
        //通过fileId获得excel文件
        //excel错误提醒  文件无数据  文件数据格式不对 文件数据重复 文件填写不对等
        //解析excel到list
        //提交线程处理
        byte[] byteFile = FileUtil.readFile(promImportReqDTO.getFileId());//根据文件id得到文件
        InputStream inputs = new ByteArrayInputStream(byteFile);
        //importExcel 调用局部final 变量
        final List<PromImportReqDTO> importList=new ArrayList<PromImportReqDTO>();
        //获得促销基础信息
        final PromInfoDTO promInfoDTO=promInfoSV.queryPromInfoDTOById(promImportReqDTO.getPromId());
        try{
        //文件解析
        //modify by huangjx importExcel会重复保存 file
        //DataInoutUtil.importExcel(new DataImportHandler(inputs, promImportReqDTO.getFileName(), "xls", "prom", promImportReqDTO.getStaff().getStaffCode()) {
        DataInoutUtil.importExcelWithoutSave(new DataImportHandler(inputs, promImportReqDTO.getFileName(), "xls", "prom", promImportReqDTO.getStaff().getStaffCode()) {
            @Override
            public boolean doCallback(List<List<Object>> datas, String fileId) {
                
                if(CollectionUtils.isEmpty(datas)){
                  
                }else{
                    readMulitThread(datas,promImportReqDTO.getFileId(), promInfoDTO.getShopId(), promInfoDTO.getSiteId(), promImportReqDTO,importList);
                }
                return true;
                }
        }, 1, 1);
        //删除中间表数据
       // deleteMulitThread(promImportReqDTO.getPromId(),promImportReqDTO.getStaff().getId());
        //insert中间表数据
        saveMulitThreadSku(importList,promInfoDTO);
        
    }catch(Exception ex){
        CacheUtil.addItem(promImportReqDTO.getFileId(), "2", cacheSeconds);
        LogUtil.error(MODULE, ex.toString());
        if((ex instanceof BusinessException)){
            throw new BusinessException(((BusinessException) ex).getErrorMessage());
        }else{
            throw new BusinessException("prom.400203");
        }
    }finally{
        try {
            if (inputs != null) {
                inputs.close();
            }
        } catch (IOException e) {
            LogUtil.error(MODULE, e.toString());
            throw new BusinessException("prom.400203");
        }
     }
    }
    @Override
    public ImprotPromResultDTO saveByFileFJ(final PromImportReqDTO promImportReqDTO)
            throws BusinessException {
        //通过fileId获得excel文件
        //excel错误提醒  文件无数据  文件数据格式不对 文件数据重复 文件填写不对等
        byte[] byteFile = FileUtil.readFile(promImportReqDTO.getFileId());//根据文件id得到文件
        InputStream inputs = new ByteArrayInputStream(byteFile);
        
        ImprotPromResultDTO promResult = null;

        //直接用excel文件解析
        Object[] object=ExcelFileUtil.importExcel(inputs, 2, 1, promImportReqDTO.getFileName(), "xls");
        //判断文件大小
        List<List<Object>> datasList = (List<List<Object>>)object[0];
        BaseSysCfgRespDTO syscfg = SysCfgUtil.fetchSysCfg("PROM_IMPORT_NUMBER_LIMIT");

        if(datasList.size() > Long.valueOf(syscfg.getParaValue()))
        {
            throw new BusinessException("prom.400207", new String[]{syscfg.getParaValue()});
        }
        
        ImportDistributeDataFJTask mainTask = new ImportDistributeDataFJTask(promImportReqDTO.getFileId(), promImportReqDTO.getPromId(), promImportReqDTO, datasList);
        
        
        ForkJoinPool forkjoinpool = new ForkJoinPool(FORK_JOIN_THREADS);
        Future<ImprotPromResultDTO> future = forkjoinpool.submit(mainTask);
        
        try {
            promResult = future.get();
            
            LogUtil.warn(MODULE, "多线程执行完毕，最终结果为："+promResult.toString());

            return promResult;
        
    }catch(Exception ex){
        CacheUtil.addItem(promImportReqDTO.getFileId(), "2", cacheSeconds);
        LogUtil.error(MODULE, ex.toString());
        if((ex instanceof BusinessException)){
            throw (BusinessException)ex;
        }else{
            throw new BusinessException("prom.400203");
        }
    }finally{
        try {
            if (inputs != null) {
                inputs.close();
            }
        } catch (IOException e) {
            LogUtil.error(MODULE, e.toString());
            throw new BusinessException("prom.400203");
        }
     }
    }
    /**
     * TODO 导入保存 中间表
     * @see com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportSV#save(com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO)
     * @param promImportReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void save( PromImportReqDTO promImportReqDTO) throws BusinessException{
        /*    synchronized(this) {
               promImportReqDTO.setId(seq_prom_import_id.nextValue());
            }*/
                promImportReqDTO.setCreateTime(DateUtil.getSysDate());
                promImportMapper.insert(promImportConverter.convert(promImportReqDTO));
            
    }
    /**
     * TODO 导入保存 中间表
     * @see com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportSV#save(com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO)
     * @param promImportReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveList( List<PromImportReqDTO> list) throws BusinessException{
            if(!CollectionUtils.isEmpty(list)){
                for(PromImportReqDTO d:list){
                    d.setId(seq_prom_import_id.nextValue());
                    this.save(d);
                }
            }
    }
    /**
     * TODO 更新中间表
     * @see com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportSV#update(com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO)
     * @param promImportReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public long update(PromImportReqDTO promImportReqDTO) throws BusinessException{
        PromImport s=promImportConverter.convert(promImportReqDTO);
        return promImportMapper.updateByPrimaryKeySelective(s);
    }
    /**
     * TODO删除中间表数据
     * @see com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportSV#delete(com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO)
     * @param promImportReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void deleteById(PromImportReqDTO promImportReqDTO) throws BusinessException{
        //保留log
        if(StringUtil.isNotEmpty(promImportReqDTO.getId()) && StringUtil.isNotEmpty(promImportReqDTO.getPromId())){
            PromImportLog log=new PromImportLog();
            ObjectCopyUtil.copyObjValue(promImportReqDTO, log, "", false);
            log.setCreateTimeLog(log.getCreateTime());
            log.setCreateTime(DateUtil.getSysDate());
            log.setCreateStaffLog(log.getCreateStaff());
            log.setCreateStaff(promImportReqDTO.getStaff().getId());
            promImportLogMapper.insert(log);
        }else{
            //通过id获得信息
           PromImport i= promImportMapper.selectByPrimaryKey(promImportReqDTO.getId());
           PromImportLog log=new PromImportLog();
           ObjectCopyUtil.copyObjValue(i, log, "", false);
           log.setCreateTimeLog(log.getCreateTime());
           log.setCreateTime(DateUtil.getSysDate());
           log.setCreateStaffLog(log.getCreateStaff());
           log.setCreateStaff(promImportReqDTO.getStaff().getId());
           promImportLogMapper.insert(log);
        }
       //删除记录
        promImportMapper.deleteByPrimaryKey(promImportReqDTO.getId());
        
    }
 
    /**
     * 促销中间表 数据  不分页
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromImportRespDTO> queryPromImportList(PromImportReqDTO promImportReqDTO) throws BusinessException{

        PromImportCriteria example = new PromImportCriteria();
        // 初始化查询条件
        this.initParm(promImportReqDTO,example);
        
        example.setOrderByClause(" ID ASC ");
        // 查询
        List<PromImport> list = promImportMapper.selectByExample(example);
        
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        // 解析结果集并转化
        List<PromImportRespDTO> reList = new ArrayList<PromImportRespDTO>();
        
        for (PromImport promImport : list) {
            reList.add(promImportRespDTOConverter.convert(promImport));
        }
        
        return reList;
    
    }
 
    /**
     * 列表 分页功能
     * @param promImportReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromImportRespDTO> queryPromImportForPage(PromImportReqDTO promImportReqDTO)
            throws BusinessException{

        PromImportCriteria example = new PromImportCriteria();
        // 初始化查询页数 数量
        example.setLimitClauseCount(promImportReqDTO.getPageSize());
        example.setLimitClauseStart(promImportReqDTO.getStartRowIndex());
        // 排序
        example.setOrderByClause("id desc");
        // 初始化查询条件
        this.initParm(promImportReqDTO,example);

        // 返回查询分页结果集
        return super.queryByPagination(promImportReqDTO, example, true,
                new PaginationCallback<PromImport, PromImportRespDTO>() {
                    // 查询记录集
                    @Override
                    public List<PromImport> queryDB(BaseCriteria example) {

                        return promImportMapper.selectByExample((PromImportCriteria) example);
                    }

                    // 查询总记录数
                    @Override
                    public long queryTotal(BaseCriteria example) {

                        return promImportMapper.countByExample((PromImportCriteria) example);
                    }
                    // 查询结果转换
                    @Override
                    public PromImportRespDTO warpReturnObject(PromImport t) {
                        return promImportRespDTOConverter.convert(t);
                    }
                });
    
    }
    
    /**
     * 初始化查询条件
     * @param reqDTO
     * @param example
     * @author huangjx
     */
    private void initParm(PromImportReqDTO reqDTO,PromImportCriteria example){
        
        if (reqDTO == null) {
            return;
        }
        Criteria cr = example.createCriteria();
        
        //id 
        if(StringUtil.isNotEmpty(reqDTO.getId())){
            cr.andIdEqualTo(reqDTO.getId());
        }
        //promid
        if(StringUtil.isNotEmpty(reqDTO.getPromId())){
            cr.andPromIdEqualTo(reqDTO.getPromId());
        }   
        //importType
        if(StringUtil.isNotEmpty(reqDTO.getImportType())){
            cr.andImportTypeEqualTo(reqDTO.getImportType());
        }  
        //分类 
        if(StringUtil.isNotEmpty(reqDTO.getCatgCode())){
            cr.andCatgCodeEqualTo(reqDTO.getCatgCode());
        }  
        //商品id
        if(StringUtil.isNotEmpty(reqDTO.getGdsId())){
            cr.andGdsIdEqualTo(reqDTO.getGdsId());
        }  
        //单品id
        if(StringUtil.isNotEmpty(reqDTO.getSkuId())){
            cr.andSkuIdEqualTo(reqDTO.getSkuId());
        } 
        //操作人
        if(StringUtil.isNotEmpty(reqDTO.getCreateStaff())){
            cr.andCreateStaffEqualTo(reqDTO.getCreateStaff());
        }  
    }
    
    /**
     * TODO excel数据转为bean 列表
     * @see com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportSV#readExcel(java.util.List, java.lang.String, java.lang.Long, java.lang.Long, com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO)
     * @param datas
     * @param fileId
     * @param shopId
     * @param siteId
     * @param promImportReqDTO
     * @return
     * @author huangjx
     */
    public PromImportReqDTO readExcel(List<Object> datas, String fileId, Long shopId, Long siteId,
            PromImportReqDTO promImportReqDTO) {
        String skuId = "";
        String promCnt = "";
        String promPrice = "";
        // 基础校验：站点编码 店铺编码 促销编码 商品编码 单品编码 活动量 促销价格
        String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
        // 商品编码
        /*
         * boolean item3 = row.get(3).toString().matches("[0-9]+"); if(!(item3)){ throw new
         * BusinessException("prom.400187"); }
         */
        try {
            // 单品编码
            boolean item0 = datas.get(0).toString().matches(regex);
            if (!(item0)) {
                throw new BusinessException("prom.400188", new String[] { datas.get(0).toString() });
            }
            skuId = datas.get(0).toString();
            if (skuId.indexOf(".") > 0) {
                skuId = skuId.replaceAll("0+?$", "");// 去掉多余的0
                skuId = skuId.replaceAll("[.]$", "");// 如最后一位是.则去掉
            }
        } catch (NullPointerException e) {
            throw new BusinessException("prom.400195");
        }
        try {
            // 活动量
            boolean item1 = datas.get(1).toString().matches(regex);
            if (!(item1)) {
                throw new BusinessException("prom.400189", new String[] { datas.get(1).toString() });
            }
            promCnt = datas.get(1).toString();
            if (promCnt.indexOf(".") > 0) {
                promCnt = promCnt.replaceAll("0+?$", "");// 去掉多余的0
                promCnt = promCnt.replaceAll("[.]$", "");// 如最后一位是.则去掉
            }
        } catch (NullPointerException e) {
            throw new BusinessException("prom.400196");
        }
        /*
         * try { //价格 boolean item2 = row.get(2).toString().matches(regex); if(!(item2)){ throw new
         * BusinessException("prom.400190",new String[]{row.get(2).toString()}); } } catch
         * (NullPointerException e) { throw new BusinessException("prom.400197"); }
         */
        // 验证 单品 店铺 站点是否一致
        GdsSkuInfoReqDTO dto = new GdsSkuInfoReqDTO();
        dto.setId(Long.valueOf(skuId));
        // 库存查询 设置条件
        SkuQueryOption[] skuQuery = { SkuQueryOption.BASIC };
        dto.setSkuQuery(skuQuery);
        GdsSkuInfoRespDTO respDTO = new GdsSkuInfoRespDTO();
        respDTO = gdsSkuInfoQueryRSV.querySkuInfoByOptions(dto);
        if (respDTO == null) {
            // 单品不存在
            throw new BusinessException("prom.400199", new String[] { skuId });
        }
        if (!shopId.equals(respDTO.getShopId())) {
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
        reqDTO.setGdsId(respDTO.getGdsId());
        reqDTO.setGdsName(respDTO.getGdsName());
        // 序列号
        // reqDTO.setImportDesc(importDesc);
        // reqDTO.setImportType(importType);
        // reqDTO.setPrice(Long.valueOf(promPrice));
        reqDTO.setPromCnt(Long.valueOf(promCnt));
        
        //if(Long.valueOf(2).equals(respDTO.getGdsTypeId())){
            LongReqDTO longReqDTO=new LongReqDTO();
            longReqDTO.setId(respDTO.getGdsTypeId());
        if(!gdsInfoExternalRSV.isNeedStockAmount(longReqDTO)){
            //虚拟商品 默认无限大
            reqDTO.setPromCnt(Long.valueOf(Integer.MAX_VALUE));
        }
        reqDTO.setPromId(promImportReqDTO.getPromId());
        reqDTO.setShopId(shopId);
        reqDTO.setSiteId(siteId);
        reqDTO.setSkuId(respDTO.getId());
        reqDTO.setImportType(promImportReqDTO.getImportType());
        reqDTO.setCreateTime(DateUtil.getSysDate());
        reqDTO.setId(seq_prom_import_id.nextValue());
        return reqDTO;
    }
    /**
     * TODO 验证是否上传成功
     * @see com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportSV#checkOver(java.lang.String)
     * @param fileId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public String checkOver(String fileId) throws BusinessException{
        
        Object o=CacheUtil.getItem(fileId);
        if(o==null){
            //查询数据库 是否有值
            PromImportFileReqDTO promImportFileReqDTO=new PromImportFileReqDTO();
            promImportFileReqDTO.setFileId(fileId);
            PromImportFile promImportFile =promImportFileSV.query(promImportFileReqDTO);
            if(promImportFile==null){
                return "0";
            }else{
                if(StringUtil.isEmpty(promImportFile.getFileId())){
                    return "0";
                }else{
                    return "1";
                }
            }
        }
        return o.toString();
        
    }

}
