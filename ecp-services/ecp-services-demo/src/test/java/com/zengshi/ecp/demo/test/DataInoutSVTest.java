package com.zengshi.ecp.demo.test;

import com.zengshi.ecp.server.service.impl.datainout.DataImportHandler;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.server.util.DataInoutUtil;
import com.zengshi.paas.utils.FileUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jiangysh on 2015/12/14.
 */
public class DataInoutSVTest extends EcpServicesTest {

    @Test
    public void importTest(){
        FileInputStream fis=null;
        try {
            fis = new FileInputStream("G:\\121.xlsx");
            DataInoutUtil.importExcel(new DataImportHandler(fis, "121", "xlsx", "demo", "jys") {

                @Override
                public boolean doCallback(List<List<Object>> datas, String fileId) {
                    for (List<Object> row : datas) {
                        for (Object cell : row) {
                            System.out.println(cell);
                        }
                    }
                    System.out.println("****************************" + fileId);

                    return true;
                }
            }, 2, 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            try {
                if(null!=fis){
                    fis.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void exportTest(){
        List<List<Object>> datas=new ArrayList<List<Object>>();

        List<String> titles=new ArrayList<String>();

        titles.add("序号");
        titles.add("名称");
        titles.add("日期");

        for(int i=0;i<20;i++){
            List<Object> data=new ArrayList<Object>();
            data.add(i);
            data.add("字符串"+i);
            data.add(new Date());
            datas.add(data);
        }

        String fileId=DataInoutUtil.exportExcel(datas, titles, "测试", "xlsx", "demo", "jys");
        System.out.println("++++++++++++++++++++++++++:"+fileId);

        Assert.assertNotNull(fileId);
    }

    @Test
    public void getFile(){
        FileUtil.readFile("5673fff22c350645e3ae466e","G://a.xlsx");
    }
}
