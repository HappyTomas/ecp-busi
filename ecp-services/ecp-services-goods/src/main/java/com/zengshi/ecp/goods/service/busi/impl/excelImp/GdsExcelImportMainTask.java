package com.zengshi.ecp.goods.service.busi.impl.excelImp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RecursiveTask;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.ExcelImportGdsModelDTO;
import com.zengshi.ecp.goods.dubbo.dto.excelImp.GdsExcelImportResultDTO;

public class GdsExcelImportMainTask extends RecursiveTask<GdsExcelImportResultDTO> {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = -5529673826528986354L;
	// 一个任务单执行的数据量,根据测试调整
	private final int ONE_TASK_DEAL_NUMBER = 20;
	// 计数器，计算器执行完，表示最后一个任务也跑完
	private CountDownLatch countDownLatch = null;
	private List<GdsExcelImpInfoSubTask> excelImpInfoSubTasks = new ArrayList<GdsExcelImpInfoSubTask>();

	private List<ExcelImportGdsModelDTO> models;
	private String optType;

	public GdsExcelImportMainTask(List<ExcelImportGdsModelDTO> models,String optType) {
		super();
		this.models = models;
		this.optType = optType;
	}

	@Override
	protected GdsExcelImportResultDTO compute() {
		
		
        //需要拆分成多少个任务单数
        int needSplitCount = 1;
        //剩余多少任务单
        int leftCount = models.size()%ONE_TASK_DEAL_NUMBER;
        needSplitCount = (leftCount == 0?models.size()/ONE_TASK_DEAL_NUMBER:models.size()/ONE_TASK_DEAL_NUMBER+1);
        //初始化计数器
        countDownLatch = new CountDownLatch(needSplitCount);
        
        //拆分任务单
        for(int i=0; i<needSplitCount; i++)
        {
        	GdsExcelImpInfoSubTask workTask = null;
            if(i == needSplitCount-1 && leftCount!=0)
            {
                
                workTask  = new GdsExcelImpInfoSubTask(i*ONE_TASK_DEAL_NUMBER, i*ONE_TASK_DEAL_NUMBER+leftCount, countDownLatch, models,optType);
            }else {
                workTask = new GdsExcelImpInfoSubTask(i*ONE_TASK_DEAL_NUMBER, (i+1)*ONE_TASK_DEAL_NUMBER, countDownLatch, models,optType);
            
            }
            excelImpInfoSubTasks.add(workTask);
            //提交到线程池队列中
            workTask.fork();
        }
        
        GdsExcelImportResultDTO excelImportResultDTO = new GdsExcelImportResultDTO();
          for(GdsExcelImpInfoSubTask excelImpInfoSubTask: excelImpInfoSubTasks){
        	  GdsExcelImportResultDTO importSubResultDTO	   = excelImpInfoSubTask.join();
        	  excelImportResultDTO.setErrorCount(excelImportResultDTO.getErrorCount() + importSubResultDTO.getErrorCount());
        	  excelImportResultDTO.setSuccessCount(excelImportResultDTO.getSuccessCount() + importSubResultDTO.getSuccessCount());
        	  excelImportResultDTO.setTotalCount(excelImportResultDTO.getTotalCount() + importSubResultDTO.getTotalCount());
          }
		return excelImportResultDTO;
	}

	public List<ExcelImportGdsModelDTO> getModels() {
		return models;
	}

	public void setModels(List<ExcelImportGdsModelDTO> models) {
		this.models = models;
	}

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}
}
