package com.zengshi.ecp.order.service.busi.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.model.OrdEntityChange;
import com.zengshi.ecp.order.dao.model.OrdEntityImport;
import com.zengshi.ecp.order.dubbo.dto.REntityChgImportRequest;
import com.zengshi.ecp.order.dubbo.dto.RFileImportRequest;
import com.zengshi.ecp.order.dubbo.dto.SEntityAddImportInfo;
import com.zengshi.ecp.order.dubbo.util.DelyConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDealFileSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdEntityChangeSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdEntityImportSV;
import com.zengshi.ecp.server.service.impl.datainout.DataImportHandler;
import com.zengshi.ecp.server.util.DataInoutUtil;

public class OrdDealFileSVImpl implements IOrdDealFileSV {
    
    @Resource
    private IOrdEntityImportSV ordEntityImportSV;
    
    @Resource
    private IOrdEntityChangeSV ordEntityChangeSV;
    
//    private static final String MODULE = OrdDealFileSVImpl.class.getName();

    @Override
    public void importEntityAddExcel(final RFileImportRequest rEntityAddImport) {
        
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(rEntityAddImport.getFileName());
             DataInoutUtil.importExcel(new DataImportHandler(fis,rEntityAddImport.getFileName(),"xlsx",rEntityAddImport.getModuleName(),rEntityAddImport.getShopId().toString()){

                @Override
                public boolean doCallback(List<List<Object>> list, String s) {
                    List<OrdEntityImport> oeis = new ArrayList<OrdEntityImport>();
                    SEntityAddImportInfo seai = new SEntityAddImportInfo();
                    
                    for(List<Object> row:list){
                        seai.setOrderSubId((String)row.get(0));
                        seai.setEntityCode((String)row.get(1));
//                        for(Object cell:row){
//                            System.out.println(cell);
//                        }
                        OrdEntityImport oei = new OrdEntityImport();
                        oei.setImportNo(rEntityAddImport.getImportNo());
                        oei.setShopId(rEntityAddImport.getShopId());
                        oei.setStatus(DelyConstants.ImportStatus.IMPORT_STATUS_INPUT);
                        oei.setOrderId(rEntityAddImport.getOrderId());
                        oei.setOrderSubId(seai.getOrderSubId());
                        oei.setRemark("");
                        oei.setEntityCode(seai.getEntityCode());
                        oeis.add(oei);
                    }
                    ordEntityImportSV.saveOrdEntityImportList(oeis);
                    return true;
                }}, 2, 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void importEntityChgExcel(final REntityChgImportRequest rEntityChgImportRequest) {
        
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(rEntityChgImportRequest.getFileName());
             DataInoutUtil.importExcel(new DataImportHandler(fis,rEntityChgImportRequest.getFileName(),"xlsx",rEntityChgImportRequest.getModuleName(),rEntityChgImportRequest.getShopId().toString()){

                @Override
                public boolean doCallback(List<List<Object>> list, String s) {
                    OrdEntityChange oec = new OrdEntityChange();
                    for(List<Object> row:list){
                        oec.setOrderId((String)row.get(0));
                        oec.setEntityCodeBf((String)row.get(1));
                        oec.setEntityCodeAf((String)row.get(2));
                        oec.setShopId(rEntityChgImportRequest.getShopId());
                        oec.setImportNo(rEntityChgImportRequest.getImportNo());
                        oec.setFromType(DelyConstants.FromType.FROM_TYPE_IMPORT);
                        oec.setStatus(DelyConstants.ImportStatus.IMPORT_STATUS_INPUT);
                        oec.setRemark("");
                        ordEntityChangeSV.saveOrdEntityChange(oec);
                    }
                    return true;
                }}, 2, 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}

