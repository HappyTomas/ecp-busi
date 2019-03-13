package com.zengshi.ecp.busi.goods.prop.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.prop.vo.GdsPropReqInfo;
import com.zengshi.ecp.busi.goods.prop.vo.GdsPropReqListInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

@Controller
@RequestMapping("/goods/gdsprop")
public class GdsPropController extends EcpBaseController {

    private static final String Module = GdsPropController.class.getName();

    @Resource(name = "gdsPropRSV")
    private IGdsPropRSV gdsPropRSV;

    @RequestMapping()
    public String pageInit() throws Exception {
        return "/goods/gdsProp/prop-grid";
    }

    @RequestMapping("/addProp")
    public String addProp(Model model, GdsPropReqInfo gdsPropReqInfo) throws Exception {
        LogUtil.debug(Module,"参数为：" + gdsPropReqInfo.toString());
        return "/goods/gdsProp/prop-add";

    }

    @RequestMapping("/editProp")
    public String editProp(Model model, GdsPropReqInfo gdsPropReqInfo) throws Exception {
        LogUtil.debug(Module,"参数为：" + gdsPropReqInfo.toString());

        GdsPropReqDTO gdsPropReqDTO = new GdsPropReqDTO();
        gdsPropReqDTO.setPageNo(0);
        gdsPropReqDTO.setPageSize(Integer.MAX_VALUE);
        gdsPropReqDTO.setId(gdsPropReqInfo.getId());
        PageResponseDTO<GdsPropRespDTO> pageResponseDTO = gdsPropRSV
                .queryGdsPropPaging(gdsPropReqDTO);
        List<GdsPropRespDTO> gdsPropRespDTOs = pageResponseDTO.getResult();
        if (gdsPropRespDTOs.size() == 0) {

            throw new BusinessException("web.gds.200004");
        }
        GdsPropRespDTO gdsPropRespDTO = gdsPropRespDTOs.get(0);
        GdsPropValueReqDTO propValueReqDTO = new GdsPropValueReqDTO();
        propValueReqDTO.setPageNo(0);
        propValueReqDTO.setPageSize(Integer.MAX_VALUE);
        propValueReqDTO.setPropId(gdsPropRespDTO.getId());
        propValueReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        PageResponseDTO<GdsPropValueRespDTO> responseDTO = gdsPropRSV
                .queryGdsPropValuePaging(propValueReqDTO);
        List<GdsPropValueRespDTO> valueRespDTOs = responseDTO.getResult();
        if (valueRespDTOs == null) {
            valueRespDTOs = new ArrayList<GdsPropValueRespDTO>();

        } else {
            for (GdsPropValueRespDTO propValueRespDTO : valueRespDTOs) {
                if (propValueRespDTO.getMediaId() != null
                        && !"".equals(propValueRespDTO.getMediaId())) {
                    String imageUrl = ImageUtil.getImageUrl(propValueRespDTO.getMediaId()
                            + "_150x150");
                    propValueRespDTO.setMediaURL(imageUrl);
                }
            }

        }

        model.addAttribute("propInfo", gdsPropRespDTO);
        model.addAttribute("propValues", valueRespDTOs);
        return "/goods/gdsProp/prop-edit";

    }

    @RequestMapping("/saveAddProp")
    public String saveAddProp(Model model, @Valid
    GdsPropReqInfo gdsPropReqInfo) throws Exception {

        JSONArray arrayTemp = JSONArray.fromObject(gdsPropReqInfo.getPropValuesStr());
        @SuppressWarnings({ "unchecked" })
        List<GdsPropValueReqDTO> propValueReqDTOs = JSONArray.toList(arrayTemp,
                GdsPropValueReqDTO.class);
        List<String> vals = new ArrayList<String>();
        if (gdsPropReqInfo.getPropValueType()!=null && !gdsPropReqInfo.getPropValueType().equals(GdsConstants.GdsProp.PROP_VALUE_TYPE_1) &&propValueReqDTOs.size() == 0) {

            throw new BusinessException("web.gds.200003");
        }
        for (GdsPropValueReqDTO gdsPropValueReqDTO : propValueReqDTOs) {
            if (gdsPropValueReqDTO.getPropValue() == null
                    || gdsPropValueReqDTO.getPropValue().equals("")) {

                throw new BusinessException("web.gds.200001");
            }
            if (vals.contains(gdsPropValueReqDTO.getPropValue())) {

                throw new BusinessException("web.gds.200002");
            }
            vals.add(gdsPropValueReqDTO.getPropValue());
        }
        GdsPropReqDTO gdsPropReqDTO = new GdsPropReqDTO();
        ObjectCopyUtil.copyObjValue(gdsPropReqInfo, gdsPropReqDTO, null, false);
        gdsPropReqDTO.setIfAbleEdit(GdsConstants.Commons.STATUS_INVALID);
        gdsPropReqDTO.setPropValues(propValueReqDTOs);
        gdsPropRSV.createGdsProp(gdsPropReqDTO);
        return "/goods/gdsProp";

    }

    @RequestMapping(value = "/listProp")
    @ResponseBody
    public Model listProp(Model model, GdsPropReqListInfo gdsPropReqListInfo) throws Exception {
        LogUtil.debug(Module,"参数为：" + gdsPropReqListInfo.toString());

        // /后场服务所需要的DTO；
        GdsPropReqDTO gdsPropReqDTO = gdsPropReqListInfo.toBaseInfo(GdsPropReqDTO.class);
        ObjectCopyUtil.copyObjValue(gdsPropReqListInfo, gdsPropReqDTO, null, false);

        // 模拟一个后场返回的列表信息；
        PageResponseDTO<GdsPropRespDTO> t = gdsPropRSV.queryGdsPropPaging(gdsPropReqDTO);

        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        if (t.getResult() == null) {
            t.setResult(new ArrayList<GdsPropRespDTO>());
        }
        for (GdsPropRespDTO gdsPropRespDTO : t.getResult()) {
            gdsPropRespDTO.setStatus(BaseParamUtil.fetchParamValue("PUBLIC_PARAM_STATUS",
                    gdsPropRespDTO.getStatus()));

            gdsPropRespDTO.setPropType(BaseParamUtil.fetchParamValue("GDS_PROP_TYPE",
                    gdsPropRespDTO.getPropType()));

            gdsPropRespDTO.setPropValueType(BaseParamUtil.fetchParamValue("GDS_PROP_VALUE_TYPE",
                    gdsPropRespDTO.getPropValueType()));

        }

        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);

    }

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public void uploadImage(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        // 获取图片
        Iterator<MultipartFile> files = multipartRequest.getFileMap().values().iterator();
        MultipartFile file = null;
        if (files.hasNext()) {
            file = files.next();
        }
        Iterator<String> ids = multipartRequest.getFileMap().keySet().iterator();
        String id = null;
        if (ids.hasNext()) {
            id = ids.next();
        }
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (file == null) {
                resultMap.put("flag", false);
                resultMap.put("error", "请选择上传文件！");
                out.print(JSONObject.fromObject(resultMap).toString());
                return;
            }
            String fileName = file.getOriginalFilename();
            String extensionName = "." + getExtensionName(fileName);

            /** 支持文件拓展名：.jpg,.png,.jpeg,.gif,.bmp */
            boolean flag = Pattern.compile("\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$")
                    .matcher(extensionName.toLowerCase()).find();
            if (!flag) {
                resultMap.put("flag", false);
                resultMap.put("error", "请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp)!");
                out.print(JSONObject.fromObject(resultMap).toString());
                return;
            }
            byte[] datas = inputStream2Bytes(file.getInputStream());
            String imageId = ImageUtil.upLoadImage(datas,"1");
            resultMap.put("flag", true);
            resultMap.put("imageId", imageId);
            resultMap.put("imageName", fileName);
            resultMap.put("id", id);
            resultMap.put("imagePath", ImageUtil.getImageUrl(imageId + "_150x150"));
            out.print(JSONObject.fromObject(resultMap).toString());
            LogUtil.debug(Module,"imageId = " + imageId);
        } catch (Exception e) {
             LogUtil.error(Module,"【图片保存失败】异常信息：" + e);
             resultMap.put("flag", false);
             resultMap.put("msg", "图片保存失败!");
             out.print(JSONObject.fromObject(resultMap).toString());
        } finally {
            out.close();
        }
    }

    private byte[] inputStream2Bytes(InputStream in) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        while ((count = in.read(data, 0, 4096)) != -1)
            outStream.write(data, 0, count);
        data = null;
        return outStream.toByteArray();
    }

    private String getExtensionName(String fileName) {
        if ((fileName != null) && (fileName.length() > 0)) {
            int dot = fileName.lastIndexOf('.');
            if ((dot > -1) && (dot < (fileName.length() - 1))) {
                return fileName.substring(dot + 1);
            }
        }
        return fileName;
    }

    @RequestMapping("/disableProp")
    public void disableProp(GdsPropReqInfo gdsPropReqInfo) throws Exception {
        
        LogUtil.debug(Module,"参数为：" + gdsPropReqInfo.toString());
        LongReqDTO longReqDTO = new LongReqDTO();
        ObjectCopyUtil.copyObjValue(gdsPropReqInfo, longReqDTO, null, false);
        
        GdsCatg2PropReqDTO chkCondition = new GdsCatg2PropReqDTO();
        chkCondition.setPropId(longReqDTO.getId());
        chkCondition.setStatus(GdsConstants.Commons.STATUS_VALID);
        if(gdsPropRSV.queryIsPropInUse(chkCondition)){
        	throw new BusinessException("web.gds.2000015");
        }
        gdsPropRSV.executeDisableGdsPropByPK(longReqDTO);
    }

    @RequestMapping("/enableProp")
    public void enableProp(GdsPropReqInfo gdsPropReqInfo) throws Exception {
        LogUtil.debug(Module,"参数为：" + gdsPropReqInfo.toString());
        LongReqDTO longReqDTO = new LongReqDTO();
        ObjectCopyUtil.copyObjValue(gdsPropReqInfo, longReqDTO, null, false);
        gdsPropRSV.executeEnableGdsPropByPK(longReqDTO);
    }

    @RequestMapping("/saveEditProp")
    public void saveEditProp(Model model, @Valid
    GdsPropReqInfo gdsPropReqInfo) throws Exception {
        LogUtil.debug(Module,"参数为：" + gdsPropReqInfo.toString());
        
        // 属性可编辑性检测开始。
        GdsPropReqDTO condition = new GdsPropReqDTO();
        condition.setPageNo(0);
        condition.setPageSize(Integer.MAX_VALUE);
        condition.setId(gdsPropReqInfo.getId());
        PageResponseDTO<GdsPropRespDTO> pageResponseDTO = gdsPropRSV
                .queryGdsPropPaging(condition);
        List<GdsPropRespDTO> gdsPropRespDTOs = pageResponseDTO.getResult();
        if (gdsPropRespDTOs.size() == 0) {
            throw new BusinessException("web.gds.200004");
        }
        
        GdsPropRespDTO originProp = gdsPropRespDTOs.get(0);
        
        if(GdsConstants.Commons.STATUS_VALID.equals(originProp.getIfAbleEdit())){
        	// 当前属性不允许进行编辑!
        	throw new BusinessException("web.gds.2000017");
        }
        // 属性可编辑性检测结束。
        

        JSONArray arrayTemp = JSONArray.fromObject(gdsPropReqInfo.getPropValuesStr());
        @SuppressWarnings({ "unchecked" })
        List<GdsPropValueReqDTO> propValueReqDTOs = JSONArray.toList(arrayTemp,
                GdsPropValueReqDTO.class);
        List<String> vals = new ArrayList<String>();
        if (gdsPropReqInfo.getPropValueType()!=null && !gdsPropReqInfo.getPropValueType().equals(GdsConstants.GdsProp.PROP_VALUE_TYPE_1) &&propValueReqDTOs.size() == 0) {
            throw new BusinessException("web.gds.200003");
        }
        for (GdsPropValueReqDTO gdsPropValueReqDTO : propValueReqDTOs) {
            if (gdsPropValueReqDTO.getPropValue() == null
                    || gdsPropValueReqDTO.getPropValue().equals("")) {

                throw new BusinessException("web.gds.200001");
            }
            if (vals.contains(gdsPropValueReqDTO.getPropValue())) {

                throw new BusinessException("web.gds.200002");
            }
            vals.add(gdsPropValueReqDTO.getPropValue());
        }
        // 封装删除的属性值到入参
        String delIdsStr = gdsPropReqInfo.getDelPropValIds();
        String delIds[] = delIdsStr.split(",");
        for (String id : delIds) {
            if (!id.equals("")) {
                GdsPropValueReqDTO gdsPropValueReqDTO = new GdsPropValueReqDTO();
                gdsPropValueReqDTO.setId(Long.valueOf(id));
                gdsPropValueReqDTO.setStatus(GdsConstants.Commons.STATUS_INVALID);
                propValueReqDTOs.add(gdsPropValueReqDTO);
            }
        }
        LogUtil.debug(Module, gdsPropReqInfo.toString());
        GdsPropReqDTO gdsPropReqDTO = new GdsPropReqDTO();
        ObjectCopyUtil.copyObjValue(gdsPropReqInfo, gdsPropReqDTO, null, false);
        gdsPropReqDTO.setPropValues(propValueReqDTOs);
        gdsPropRSV.editGdsProp(gdsPropReqDTO);
        // return "/goods/gdsProp";

    }
    
    
    
    /**
     * 
     * 属性禁用检测(已经与分类相关联的属性不允许禁用.)
     * 
     * @author liyong7
     * @param gdsPropReqInfo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/disableCheck")
    public EcpBaseResponseVO disablePropCheck(GdsPropReqInfo gdsPropReqInfo) throws Exception{
    	EcpBaseResponseVO respVO = new EcpBaseResponseVO();
    	GdsCatg2PropReqDTO condition = new GdsCatg2PropReqDTO();
    	condition.setStatus(GdsConstants.Commons.STATUS_VALID);
    	condition.setPropId(gdsPropReqInfo.getId());
    	
    	try{
    	    boolean isInUse = gdsPropRSV.queryIsPropInUse(condition);
    	    respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
    	    if(isInUse){
    	    	respVO.setResultMsg("true");
    	    }else{
    	    	respVO.setResultMsg("false");
    	    }
    	}catch (BusinessException e) {
			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			respVO.setResultMsg(e.getErrorMessage());
		}
    	return respVO;
    }
}
