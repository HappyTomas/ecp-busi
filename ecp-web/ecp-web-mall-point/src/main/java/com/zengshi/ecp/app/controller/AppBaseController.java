package com.zengshi.ecp.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.paas.utils.HttpUtil;
import com.zengshi.butterfly.app.MappContext;
import com.zengshi.butterfly.app.client.JsonClientHandler;
import com.zengshi.butterfly.app.model.AppDatapackage;
import com.zengshi.butterfly.core.util.DESUtils;
import com.zengshi.butterfly.core.util.GZipUtils;

@Controller
@RequestMapping("/app")
public class AppBaseController extends EcpBaseController{
    
    private static Logger logger = LoggerFactory.getLogger(AppBaseController.class);
    
    @Autowired
    private JsonClientHandler<AppDatapackage> jsonHandler;
//    
    @RequestMapping(value = "/json",produces="text/html;charset=UTF-8",method={RequestMethod.POST})
    public @ResponseBody String json(String msg , HttpServletRequest request, HttpServletResponse respnose) throws Exception {
        logger.debug("接收到app端请求："+request.getRemoteAddr());
        
        //将http请求头中的参数，放入多attrMap中
        Map<String, Object> attrMap = new HashMap<String, Object>();
        
        //获取app端传入pp的ip，存入到context中
        ///客户的IP信息，和 cookie信息；
        MappContext.setAttribute(MappContext.MAPPCONTEXT_REQUEST_IP, HttpUtil.getRemoteAddr(request));
        MappContext.setAttribute(MappContext.MAPPCONTEXT_SESSIONID, request.getHeader("ECP-COOKIE"));
       
        String responseJson = "";
        
        //调用action服务，获取返回json串
        responseJson = jsonHandler.doHandle(msg, attrMap);
        
        logger.debug("返回给app客户端："+responseJson);

        return responseJson;
    }
    
    @RequestMapping(value = {"/json"},produces="text/html;charset=UTF-8",method={RequestMethod.GET})
    public @ResponseBody String testMappJson(HttpServletRequest request, HttpServletResponse respnose) throws Exception {
       // 不响应get方法，返回服务器时间
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    
    /**
     * 
     * test: 转入测试页面（/app/test）<br/> 
     * @author yugn 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping()
    public String test(){
        return "/app/test";
    }
    
    @RequestMapping(value="domsg/{type}")
    @ResponseBody
    public Map<String,String> doMsg(@PathVariable String type, String msg){
        
        Map<String, String> map = new HashMap<>();
        
        if(StringUtils.isEmpty(msg) || StringUtils.isEmpty(type)){
            map.put("msg", "操作类型或者待处理消息为空！");
            map.put("code", "err");
        }
        
        try{
            if("encrypt".equalsIgnoreCase(type)){
                
                map.put("msg", encryptMsg(msg));
                map.put("code", "ok");
                
            } else if("decrypt".equalsIgnoreCase(type)){
                
                map.put("msg", decryptMsg(msg));
                map.put("code", "ok");
                
            } else {
                map.put("msg", "操作方式错误，允许的值是：encrypt | decrypt");
                map.put("code", "err");
            }
            
        } catch(Exception err){
            logger.error("APP加/解密验证页面请求错误！",err);
            
            map.put("msg", "APP加/解密验证页面请求错误: "+err.getMessage());
            map.put("code", "err");
        }
        
        return map;
        
    }
    
    /**
     * 
     * decryptMsg: 对字符串进行解密操作；
     *   顺序： base64解密--》gzip解压 --> des解密 
     * 
     * @author yugn 
     * @param msg
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    private String decryptMsg(String msg) throws Exception{
        
        logger.debug("对参数进行解密操作。");
        
        ///先将参数 从Base64 转换为字符编码；
        byte[] arg0_byte = DESUtils.decryptBASE64(msg);
        
        // gzip 解压
        arg0_byte = GZipUtils.decompress(arg0_byte);
        
        /// 解密， 加密密钥是：APP_DESKEY
        return DESUtils.decrypt(new String(arg0_byte), "APP_DESKEY");
    }
    
    /**
     * 
     * encryptMsg:加密字符串；
     * 顺序：des加密 --> gzip压缩 --》 字节数组转base64
     * 
     * @author yugn 
     * @param msg
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    private String encryptMsg(String msg) throws Exception{
        
        logger.debug("对参数进行加密操作。");
        ///加密
        String result = DESUtils.encrypt(msg, "APP_DESKEY");
        
        byte[] ret_byte =  result.getBytes("utf-8");
        
        //gzip压缩；
        ret_byte = GZipUtils.compress(ret_byte);
        
        //转为base64
        return DESUtils.encryptBASE64(ret_byte);
    }
    
}

