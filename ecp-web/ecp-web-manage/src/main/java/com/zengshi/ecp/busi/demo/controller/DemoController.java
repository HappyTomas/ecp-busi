///** 
// * Project Name:ecp-web-demo 
// * File Name:DemoController.java 
// * Package Name:com.zengshi.ecp.busi.demo.controller 
// * Date:2015-8-5下午2:51:38 
// * Copyright (c) 2015, ZengShi All Rights Reserved. 
// * 
// */
//package com.zengshi.ecp.busi.demo.controller;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.zengshi.ecp.base.controller.EcpBaseController;
//import com.zengshi.ecp.base.mvc.JsonResultThreadLocal;
//import com.zengshi.ecp.base.mvc.annotation.NativeJson;
//import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
//import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
//import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
//import com.zengshi.ecp.busi.demo.vo.DemoCfgVO;
//import com.zengshi.ecp.demo.dubbo.dto.DemoCfgReqDTO;
//import com.zengshi.ecp.demo.dubbo.dto.DemoCfgRespDTO;
//import com.zengshi.ecp.demo.dubbo.interfaces.IDemoCfgRSV;
//import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
//import com.zengshi.ecp.server.front.dto.PageResponseDTO;
//import com.zengshi.ecp.server.front.param.IBaseSysCfgRSV;
//import com.zengshi.ecp.server.front.util.SysCfgUtil;
//import com.zengshi.paas.utils.LogUtil;
//import com.alibaba.fastjson.JSONObject;
//
///**
// * Title: ECP <br>
// * Project Name:ecp-web-demo <br>
// * Description: <br>
// * Date:2015-8-5下午2:51:38 <br>
// * Copyright (c) 2015 ZengShi All Rights Reserved <br>
// * 
// * @author yugn
// * @version
// * @since JDK 1.6
// */
//@Controller
//@RequestMapping(value = "/demo")
//public class DemoController extends EcpBaseController {
//
//	private static String MODULE = DemoController.class.getName();
//
//	/**
//	 * 
//	 * init:页面初始化，requestMapping如果不写的话，访问地址同：Controller类的 requestmapping的URL
//	 * 
//	 * @author yugn
//	 * @return
//	 * @since JDK 1.6
//	 */
//	@RequestMapping()
//	public String init() {
//		return "/demo/demo-init";
//	}
//
//	/**
//	 * 
//	 * 功能描述：Gird列表
//	 *
//	 * @author 曾海沥(Terry)
//	 *         <p>
//	 *         创建日期 ：2015-8-5 下午8:29:55
//	 *         </p>
//	 *
//	 * @return
//	 *
//	 *         <p>
//	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
//	 *         </p>
//	 */
//	@RequestMapping(value = "/grid")
//	public String grid() {
//		return "/demo/demo-grid";
//	}
//
//	/**
//	 * 
//	 * 功能描述：基础表单
//	 *
//	 * @author 曾海沥(Terry)
//	 *         <p>
//	 *         创建日期 ：2015-8-5 下午8:29:55
//	 *         </p>
//	 *
//	 * @return
//	 *
//	 *         <p>
//	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
//	 *         </p>
//	 */
//	@RequestMapping(value = "/form")
//	public String form() {
//		return "/demo/demo-form";
//	}
//
//	/**
//	 * 
//	 * 功能描述：大表单
//	 *
//	 * @author 曾海沥(Terry)
//	 *         <p>
//	 *         创建日期 ：2015-8-5 下午8:29:55
//	 *         </p>
//	 *
//	 * @return
//	 *
//	 *         <p>
//	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
//	 *         </p>
//	 */
//	@RequestMapping(value = "/form-more")
//	public String formMore(Model model) {
//
//		BaseSysCfgRespDTO dto = SysCfgUtil.fetchSysCfg("SYS_TEST4");
//
//		model.addAttribute("sysCfg", dto);
//		return "/demo/demo-form-more";
//	}
//
//	/**
//	 * 
//	 * 功能描述：其他内容
//	 *
//	 * @author 曾海沥(Terry)
//	 *         <p>
//	 *         创建日期 ：2015-8-5 下午8:29:55
//	 *         </p>
//	 *
//	 * @return
//	 *
//	 *         <p>
//	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
//	 *         </p>
//	 */
//	@RequestMapping(value = "/plugin")
//	public String plugin() {
//		return "/demo/demo-plugin";
//	}
//
//	/**
//	 * 
//	 * 功能描述：其他内容
//	 *
//	 * @author 曾海沥(Terry)
//	 *         <p>
//	 *         创建日期 ：2015-8-5 下午8:29:55
//	 *         </p>
//	 *
//	 * @return
//	 *
//	 *         <p>
//	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
//	 *         </p>
//	 */
//	@RequestMapping(value = "/plugin1")
//	public String plugin1() {
//		return "/demo/demo-plugin1";
//	}
//
//	/**
//	 * 
//	 * 功能描述：图标库
//	 *
//	 * @author 曾海沥(Terry)
//	 *         <p>
//	 *         创建日期 ：2015-8-5 下午8:29:55
//	 *         </p>
//	 *
//	 * @return
//	 *
//	 *         <p>
//	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
//	 *         </p>
//	 */
//	@RequestMapping(value = "/icons")
//	public String icons() {
//		return "/demo/demo-icons";
//	}
//
//	/**
//	 * 
//	 * 功能描述：弹出窗口
//	 *
//	 * @author 曾海沥(Terry)
//	 *         <p>
//	 *         创建日期 ：2015-8-18 下午2:19:35
//	 *         </p>
//	 *
//	 * @return
//	 *
//	 *         <p>
//	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
//	 *         </p>
//	 */
//	@RequestMapping(value = "/open")
//	public String open() {
//		return "/demo/open/demo-open";
//	}
//
//	/**
//	 * 
//	 * 功能描述：测试分页跳转
//	 *
//	 * @author 曾海沥(Terry)
//	 *         <p>
//	 *         创建日期 ：2015-9-16 上午11:34:15
//	 *         </p>
//	 *
//	 * @return
//	 *
//	 *         <p>
//	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
//	 *         </p>
//	 */
//	@RequestMapping(value = "/openPage")
//	public String openPage() {
//		return "/demo/openpage/demo-open-page";
//	}
//
//	/**
//	 * 
//	 * 功能描述：公用上传文件页面
//	 *
//	 * @author 曾海沥(Terry)
//	 *         <p>
//	 *         创建日期 ：2015-9-3 下午5:27:32
//	 *         </p>
//	 *
//	 * @return
//	 *
//	 *         <p>
//	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
//	 *         </p>
//	 */
//	@RequestMapping(value = "/uploadSelector")
//	public String uploadSelector() {
//		return "/common/upload/common-upload";
//	}
//
//	/**
//	 * 
//	 * 功能描述：选择器
//	 *
//	 * @author 曾海沥(Terry)
//	 *         <p>
//	 *         创建日期 ：2015-8-18 下午2:19:43
//	 *         </p>
//	 *
//	 * @return
//	 *
//	 *         <p>
//	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
//	 *         </p>
//	 */
//	@RequestMapping(value = "/selector")
//	public String selector() {
//		return "/demo/open/demo-selector";
//	}
//
//	/**
//	 * 
//	 * edit:编辑页面；
//	 * 
//	 * @author yugn
//	 * @return
//	 * @since JDK 1.6
//	 */
//	@RequestMapping(value = "/edit")
//	public String edit(Model model) {
//		DemoCfgVO demoCfg = new DemoCfgVO();
//		demoCfg.setCode("A001");
//		demoCfg.setInfo("测试");
//		demoCfg.setCreateTime(Calendar.getInstance().getTime());
//		model.addAttribute("demoCfg", demoCfg);
//		return "/demo/demo-form";
//	}
//
//	@RequestMapping(value = "/macro")
//	public String macro(Model model) {
//		DemoCfgVO demoCfg = new DemoCfgVO();
//		demoCfg.setCode("A001");
//		demoCfg.setInfo("测试");
//		demoCfg.setCreateTime(Calendar.getInstance().getTime());
//		model.addAttribute("demoCfg", demoCfg);
//		return "/demo/demo-macro";
//	}
//
//	@Resource
//	private IBaseSysCfgRSV baseSysCfgRSV;
//
//	/**
//	 * 
//	 * more:转入demo-more<br/>
//	 * 
//	 * @author yugn
//	 * @return
//	 * @since JDK 1.6
//	 */
//	@RequestMapping(value = "/more")
//	public String more(Model model) {
//		BaseSysCfgRespDTO dto = baseSysCfgRSV.queryCfgByCode("SYS_TEST");
//
//		model.addAttribute("sysCfg", dto);
//		return "/demo/demo-form-more";
//	}
//
//	/**
//	 * 
//	 * 功能描述：文件上传处理
//	 *
//	 * @author 曾海沥(Terry)
//	 *         <p>
//	 *         创建日期 ：2015-8-31 下午4:26:59
//	 *         </p>
//	 *
//	 * @return
//	 *
//	 *         <p>
//	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
//	 *         </p>
//	 */
//	@RequestMapping("/fileUpload")
//	@ResponseBody
//	@NativeJson(true)
//	public String fileUpload(
//			@RequestParam(value = "imgFile", required = false) MultipartFile file,
//			Model model, HttpServletRequest request,
//			HttpServletResponse response,
//			@RequestParam(value = "dir", required = false) String dirName)
//			throws Exception {
//		String savePath = request.getServletContext().getRealPath("/")
//				+ "attached/";
//		JsonResultThreadLocal.set(false);
//
//		// 文件保存目录URL
//		String saveUrl = request.getContextPath() + "/attached/";
//
//		// 定义允许上传的文件扩展名
//		HashMap<String, String> extMap = new HashMap<String, String>();
//		extMap.put("image", "gif,jpg,jpeg,png,bmp");
//		extMap.put("flash", "swf,flv");
//		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
//		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
//
//		// 最大文件大小
//		long maxSize = 10000000;
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//
//		if (!ServletFileUpload.isMultipartContent(request)) {
//			return getError("请选择文件。");
//		}
//		// 检查目录
//		File uploadDir = new File(savePath);
//		if (!uploadDir.isDirectory()) {
//			return getError("上传目录不存在。");
//		}
//		// 检查目录写权限
//		if (!uploadDir.canWrite()) {
//			return getError("上传目录没有写权限。");
//		}
//
//		if (dirName == null) {
//			dirName = "image";
//		}
//		if (!extMap.containsKey(dirName)) {
//			return getError("目录名不正确。");
//		}
//		// 创建文件夹
//		savePath += dirName + "/";
//		saveUrl += dirName + "/";
//		File saveDirFile = new File(savePath);
//		if (!saveDirFile.exists()) {
//			saveDirFile.mkdirs();
//		}
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		String ymd = sdf.format(new Date());
//		savePath += ymd + "/";
//		saveUrl += ymd + "/";
//		File dirFile = new File(savePath);
//		if (!dirFile.exists()) {
//			dirFile.mkdirs();
//		}
//
//		if (file.getSize() > maxSize) {
//			return getError("上传文件大小超过限制。");
//		}
//		// 检查扩展名
//		String fileExt = file.getOriginalFilename()
//				.substring(file.getOriginalFilename().lastIndexOf(".") + 1)
//				.toLowerCase();
//
//		if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(
//				fileExt)) {
//			return getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName)
//					+ "格式。");
//		}
//
//		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//		String newFileName = df.format(new Date()) + "_"
//				+ new Random().nextInt(1000) + "." + fileExt;
//
//		try {
//			File uploadedFile = new File(savePath, newFileName);
//			file.transferTo(uploadedFile);
//		} catch (Exception e) {
//			return getError("上传文件失败。");
//		}
//
//		/*
//		 * 
//		 * FileItemFactory factory = new DiskFileItemFactory();
//		 * ServletFileUpload upload = new ServletFileUpload(factory);
//		 * upload.setHeaderEncoding("UTF-8"); List items =
//		 * upload.parseRequest(request); Iterator itr = items.iterator(); while
//		 * (itr.hasNext()) { FileItem item = (FileItem) itr.next(); String
//		 * fileName = item.getName(); long fileSize = item.getSize(); if
//		 * (!item.isFormField()) { //检查文件大小 if(item.getSize() > maxSize){
//		 * model.addAttribute(getError("上传文件大小超过限制。")); return model; } //检查扩展名
//		 * String fileExt = fileName.substring(fileName.lastIndexOf(".") +
//		 * 1).toLowerCase();
//		 * if(!Arrays.<String>asList(extMap.get(dirName).split(
//		 * ",")).contains(fileExt)){
//		 * model.addAttribute(getError("上传文件扩展名是不允许的扩展名。\n只允许" +
//		 * extMap.get(dirName) + "格式。")); return model; }
//		 * 
//		 * SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); String
//		 * newFileName = df.format(new Date()) + "_" + new
//		 * Random().nextInt(1000) + "." + fileExt; try{ File uploadedFile = new
//		 * File(savePath, newFileName); item.write(uploadedFile);
//		 * }catch(Exception e){ model.addAttribute(getError("上传文件失败。")); return
//		 * model; }
//		 * 
//		 * JSONObject obj = new JSONObject(); obj.put("error", 0);
//		 * obj.put("url", saveUrl + newFileName); model.addAttribute(obj); } }
//		 */
//		JSONObject obj = new JSONObject();
//		// obj.put("error", 0);
//		// obj.put("url", saveUrl + newFileName);
//		obj.put("fileId", "23849234");
//		obj.put("url",
//				"http://img1.mydrivers.com/img/20150904/189105bb132a40a9aebc2a3bf7096222.jpg");
//		return obj.toJSONString();
//	}
//
//	/**
//	 * 
//	 * 功能描述：处理错误信息的JSON结果
//	 *
//	 * @author 曾海沥(Terry)
//	 *         <p>
//	 *         创建日期 ：2015-8-31 下午5:34:29
//	 *         </p>
//	 *
//	 * @param message
//	 * @return
//	 *
//	 *         <p>
//	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
//	 *         </p>
//	 */
//	private String getError(String message) {
//		JSONObject obj = new JSONObject();
//		obj.put("error", 1);
//		obj.put("message", message);
//		return obj.toJSONString();
//	}
//
//	/**
//	 * 
//	 * save:保存
//	 * 
//	 * @author yugn
//	 * @return
//	 * @since JDK 1.6
//	 */
//	@RequestMapping(value = "/save")
//	@ResponseBody
//	public EcpBaseResponseVO save(@Valid DemoCfgVO demoCfg) {
//		DemoCfgReqDTO dto = new DemoCfgReqDTO();
//		dto.setCode(demoCfg.getCode());
//		dto.setCreateTime(demoCfg.getCreateTime());
//		dto.setInfo(demoCfg.getInfo());
//		LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(demoCfg));
//		EcpBaseResponseVO vo = new EcpBaseResponseVO();
//
//		try {
//			demoCfgRSV.saveDemoCfg(dto);
//			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
//		} catch (Exception err) {
//			err.printStackTrace();
//			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
//		}
//		return vo;
//	}
//
//	@Resource(name = "demoCfgRSV")
//	private IDemoCfgRSV demoCfgRSV;
//
//	@RequestMapping(value = "/save2")
//	@ResponseBody
//	public EcpBaseResponseVO save2(
//			@RequestParam("code") String code,
//			@RequestParam("info") String info,
//			@RequestParam("createTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date creatTime) {
//
//		LogUtil.info(MODULE, "==========createTime:" + creatTime.getTime()
//				+ ";info:" + info + ";code:" + code);
//		EcpBaseResponseVO vo = new EcpBaseResponseVO();
//		vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
//		return vo;
//	}
//
//	@RequestMapping(value = "/saveform")
//	public String saveForm(@Valid @ModelAttribute("demoCfg") DemoCfgVO demoCfg,
//			BindingResult result) {
//		LogUtil.info(this.getClass().getName(), "========saveform");
//		if (result.hasErrors()) {
//			LogUtil.info(this.getClass().getName(), "========hasError");
//			return "/demo/demo-form";
//		} else {
//			return "redirect:/demo/grid";
//		}
//	}
//
//	@RequestMapping("/gridlist")
//	@ResponseBody
//	public Model gridList(Model model, EcpBasePageReqVO vo) throws Exception {
//		// /后场服务所需要的DTO；
//		DemoCfgReqDTO s = vo.toBaseInfo(DemoCfgReqDTO.class);
//		s.setInfo("xx"); // /其它的查询条件；
//
//		// 模拟一个后场返回的列表信息；
//		// PageResponseDTO<DemoCfgRespDTO> t = this.initParam();
//		PageResponseDTO<DemoCfgRespDTO> t = demoCfgRSV.listDemoCfgPage(s);
//
//		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
//		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO
//				.buildByPageResponseDTO(t);
//
//		return super.addPageToModel(model, respVO);
//	}
//
//	// /模拟后场返回的数据；
//	private PageResponseDTO<DemoCfgRespDTO> initParam(EcpBasePageReqVO vo) {
//	    int cnt = 102;
//		PageResponseDTO<DemoCfgRespDTO> t = new PageResponseDTO<DemoCfgRespDTO>();
//		t.setPageNo(vo.getPageNumber());
//        t.setPageSize(vo.getPageSize());
//		t.setCount(cnt);
//		t.setPageCount(t.getPageCount());
//		
//		List<DemoCfgRespDTO> lst = new ArrayList<DemoCfgRespDTO>();
//		int begin = vo.getPageNumber()*(vo.getPageSize()-1);
//		for(int i = 0;i<vo.getPageSize() && i<cnt-begin ;i++){
//		    DemoCfgRespDTO dto = new DemoCfgRespDTO();
//		    dto.setCode((i+begin)+"");
//		    dto.setId(i+begin);
//		    dto.setInfo("测试0"+(i+begin));
//		    dto.setCreateTime(Calendar.getInstance().getTime());
//		    lst.add(dto);
//		}
//		t.setResult(lst);
//
//		return t;
//	}
//	
//	/**
//	 * 
//	 * demoPageInit: <br/> 
//	 * 
//	 * @author yugn 
//	 * @param model
//	 * @param vo
//	 * @return
//	 * @throws Exception 
//	 * @since JDK 1.6
//	 */
//	@RequestMapping("/page/init")
//	public String demoPageInit(Model model,EcpBasePageReqVO vo) throws Exception{
//	    super.addPageToModel(model, this.data(vo));
//        return "demo/demo-page";
//    }
//	
//	/**
//	 * demoPageData: 独立分页栏，采用异步刷新方式（非整个页面跳转；是局部页面刷新 ）的时候需要这个方法，用于展示表格的数据（即：分页区的内容，以及分页栏信息） ；如果<br/> 
//	 * 
//	 * @author yugn 
//	 * @param model
//	 * @return
//	 * @throws Exception 
//	 * @since JDK 1.6
//	 */
//	@RequestMapping("/page/data")
//	public String demoPageData(Model model ,EcpBasePageReqVO vo) throws Exception{
//	    super.addPageToModel(model, this.data(vo));
//	    return "demo/page/page";
//	}
//	
//	/**
//	 * data: 分页区数据；<br/> 
//	 * 
//	 * @author yugn 
//	 * @return
//	 * @throws Exception 
//	 * @since JDK 1.6
//	 */
//	private EcpBasePageRespVO<Map> data(EcpBasePageReqVO vo)  throws Exception {
//	    PageResponseDTO<DemoCfgRespDTO> t = this.initParam(vo);
//        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
//        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
//        return respVO;
//	}
//	
//	@RequestMapping("/page/init2")
//    public String demoPageInit2(Model model,EcpBasePageReqVO vo) throws Exception{
//        super.addPageToModel(model, this.data(vo));
//        return "demo/demo-page2";
//    }
//
//}
