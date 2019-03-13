package com.zengshi.ecp.demo.test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.ImageUtil;
import org.junit.Test;

import java.io.*;

/**
 * Created by jiangysh on 2015/12/5.
 */
public class ImageUtilTest extends EcpServicesTest {

    @Test
    public void uploadTest(){
        File file=new File("G:\\upload\\123.jpg");
        InputStream is=null;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        try {

            is=new FileInputStream(file);
            byte[] bytes=new byte[1024];
            int len;
            while((len=is.read(bytes))!=-1){
                baos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(null!=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String id=ImageUtil.upLoadImage(baos.toByteArray(),"123.jpg");
        System.out.println("++++++++++++++++++++++++:  "+id);

        String url=ImageUtil.getImageUrl(id);
        System.out.println("********************************  "+url);
    }
}
