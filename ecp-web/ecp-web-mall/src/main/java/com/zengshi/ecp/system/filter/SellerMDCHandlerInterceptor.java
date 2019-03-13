package com.zengshi.ecp.system.filter;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zengshi.ecp.base.mvc.MessageModel;
import com.zengshi.ecp.base.mvc.ResponseModel;
import com.zengshi.ecp.busi.seller.staff.controller.ShopDashboardController;
import com.zengshi.ecp.staff.dubbo.dto.AuthManageReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.SellerResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IMenuManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.system.util.ConstantTool;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.core.config.Application;
import com.zengshi.butterfly.core.web.WebConstants;
import com.zengshi.butterfly.core.web.interceptor.AbstractCustomerHandlerInterceptor;

import net.sf.json.JSONObject;

public class SellerMDCHandlerInterceptor extends AbstractCustomerHandlerInterceptor {

    @Resource
    private IStaffUnionRSV iStaffUnionRSV;
    
    @Resource
    private IMenuManageRSV iMenuManageRSV;
    
    private static final String MODULE = SellerMDCHandlerInterceptor.class.getName();
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
    	LogUtil.info(MODULE, "=================卖家拦截开始==================");
        CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
        Long staffid = custInfoReqDTO.getStaff().getId();
        String uri = request.getRequestURI().toLowerCase();
        // 正则表达式规则
        String regEx = "seller.*";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(uri);
        // 查找字符串中是否有匹配正则表达式的字符/字符串
        boolean rs = matcher.find();
        if (StringUtil.isNotBlank(uri) && rs) {
            if(staffid==0){
                String header = request.getHeader("x-requested-with");
                if(StringUtil.isNotBlank(header)&&"XMLHttpRequest".equals(header)){
                    response.setHeader("Content-Type", "application/json;charset=UTF-8");
                    ResponseModel respModel=new ResponseModel();
                    respModel.setAjaxResult(ResponseModel.ResultTypeEnum.TIMEOUT);
                    respModel.setErrorMessage(new ArrayList<MessageModel>());
                    Map<String,String> map=new HashMap<String,String>();
                    String url=Application.getValue(WebConstants.URL_LOGIN_PAGE);
                    String referer=request.getHeader("Referer");
                    String protocol=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

                    if(StringUtil.isNotBlank(url) && StringUtil.isNotBlank(referer)){
//            	            boolean flag=referer.startsWith(protocol);
//            	            if(flag){
//            	                referer=referer.replace(protocol, "");
//            	            }
                        referer=response.encodeURL(referer);
                        if(url.indexOf("?")>0){
                            url=url+"&Referer="+referer;
                        }else{
                            url=url+"?Referer="+referer;
                        }
                        if(!url.startsWith(request.getScheme()+"://")){

                            if(url.startsWith("/")){
                                url=protocol+url;
                            }else{
                                url=protocol+"/"+url;
                            }
                        }
                    }
                    map.put("url", url);
                    respModel.setValues(map);
                    respModel.getErrorMessage().add(new MessageModel("webcore.000003", ResourceMsgUtil.getMessage("webcore.000002", new Object[]{})));
                    JSONObject jsonObject=JSONObject.fromObject(respModel);
                    String jsonString=jsonObject.toString();
                    response.getWriter().print(jsonString);
//            	        response.getWriter().print("This session has been expired (possibly due to multiple concurrent " +
//            	                "logins being attempted as the same user).");
                    response.flushBuffer();
                    response.getWriter().close();
                    return false;
                }else{
                    String url = request.getContextPath()+Application.getValue(WebConstants.URL_LOGIN_PAGE)+"?Referer="+request.getServletPath();
                    response.sendRedirect(url);
                    return true;
                }
            }
            //都不匹配时跳转
      /*      if(!flag){
                String referer = request.getHeader("Referer");
                if(referer != null){   
                    response.sendRedirect(referer);   
                    return true;
                }   
                response.sendRedirect(request.getContextPath()+Application.getValue(WebConstants.URL_HOME_PAGE));
                return true;
            }*/
            
            SellerResDTO sellerinfo = new SellerResDTO();
            // 判断卖家信息是否存在session中
            sellerinfo = (SellerResDTO) request.getSession().getAttribute("sellerInfo");
            if (StringUtil.isEmpty(sellerinfo)) {
                custInfoReqDTO.setId(staffid);
                sellerinfo = iStaffUnionRSV.getSellerInfo(custInfoReqDTO);
                if(StaffConstants.custInfo.SHOP_FLAG_NO.equals(sellerinfo.getShopFlag())){
                    String referer = request.getHeader("Referer");
                    if(referer != null){   
                        response.sendRedirect(referer);   
                        return true;
                    }   
                    response.sendRedirect(request.getContextPath()+Application.getValue(WebConstants.URL_HOME_PAGE));
                    return true;
                }
                
                request.getSession().setAttribute("sellerInfo", sellerinfo);
                SellerLocaleUtil.setSeller(request);
            } else {
                SellerLocaleUtil.setSeller(request);
            }
            
            //查询条件匹配
            String shopId = request.getParameter("shopId");
            boolean prflag=false;
            if(StringUtil.isNotBlank(shopId)){
            	SellerResDTO dto = SellerLocaleUtil.getSeller();
            	for (ShopInfoResDTO shopInfoResDTO : dto.getShoplist()) {
            		if(Long.parseLong(shopId)==shopInfoResDTO.getId()){
            			prflag = true;
            		}
				}
            	if(!prflag){
            		outHtml(response);
            		return true;
            	}
            }
            
            //权限匹配
            if(!authority(request,response)){
            	 response.sendRedirect(request.getContextPath()+Application.getValue(ParamsTool.Page.SELLER_ERROR));   
            }
        }
        return true;
    }
    
    
    public void outHtml(HttpServletResponse response) throws Exception{
    	response.setHeader("Content-type", "text/html;charset=UTF-8");//指定消息头以UTF-8码表读数据  
    	PrintWriter p  =response.getWriter();
    	p.println("  <script type='text/javascript'> eDialog.alert('对不起，您没有权限操作此店铺!')</script>");
		p.flush();
		p.close();
    }
    
    
    public boolean authority(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	  String path =  request.getServletPath();
    	  LogUtil.info(MODULE, "=================卖家当前url="+path+"==================");
          request.setAttribute("sellerurl", path);
          String ifSellerMenu="";
          boolean flag = true;
     	  AuthManageReqDTO authManageReqDTO = new AuthManageReqDTO();
      	  List<String> sysCodes = new ArrayList<String>();
    	  sysCodes.add(StaffConstants.PublicParam.SYS_CODE_SELLER);
    	  authManageReqDTO.setSysCodes(sysCodes);
    	  List<AuthMenuResDTO> sellerlist = iMenuManageRSV.listAuthMenu(authManageReqDTO);
    	  for (AuthMenuResDTO authMenuResDTO : sellerlist) {
    		  if(StringUtil.isNotBlank(authMenuResDTO.getMenuUrl())){
    		     if(authMenuResDTO.getMenuUrl().equals(path)){
    		    	 ifSellerMenu = "ok";
    		     }
    		   }
		     }
    	  
    	  if(ifSellerMenu.equals("ok")){
    		  List<AuthMenuResDTO> list = ConstantTool.getMenuByStaffCache();
              for (AuthMenuResDTO authMenuResDTO : list) {
                  List<com.zengshi.ecp.server.front.security.AuthMenuResDTO> list2 = authMenuResDTO.getSonList();
                  for (com.zengshi.ecp.server.front.security.AuthMenuResDTO authMenuResDTO2 : list2) {
                      if(StringUtil.isNotBlank(authMenuResDTO2.getMenuUrl())){
                          if(authMenuResDTO2.getMenuUrl().contains(path)){
                              flag =true;
                              break;
                          }else{
                              flag=false;
                          }
                      }
                  }
                  if(flag){
                      break;
                  }
              }
              return flag;
    	}else{
    		return flag;
    	}
    }

}
