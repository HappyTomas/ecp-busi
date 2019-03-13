package com.zengshi.ecp.busi.cms.imageswitcher.controller;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zengshi.ecp.base.mvc.annotation.NativeJson;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.cms.imageswitcher.vo.CmsImageSwitcherVO;
import com.zengshi.ecp.busi.goods.common.GdsBaseController;
import com.zengshi.ecp.cms.dubbo.dto.CmsImageSwitcherReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsImageSwitcherRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsImageSwitcherRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.system.util.ConstantTool;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping({ "/cms/weixh" })
public class CmsImageSwitcherController extends GdsBaseController {

    @Resource(name = "cmsImageSwitcherRSV")
    private ICmsImageSwitcherRSV cmsImageSwitcherRSV;

    @RequestMapping(value = "/grid")
    public String grid() {
        return "/cms/imageswitcher/imageswitcher-grid";
    }

    /**
     * 
     * gridList:(会员列表). <br/>
     * 
     * @author wangbh
     * @param model
     * @param vo
     * @param custInfoList
     * @return
     * @throws Exception
     * @since JDK 1.7
     */

    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, EcpBasePageReqVO vo, @ModelAttribute
    CmsImageSwitcherVO cmsImageSwitcherVO) throws Exception {
        CmsImageSwitcherReqDTO info = vo.toBaseInfo(CmsImageSwitcherReqDTO.class);

        ObjectCopyUtil.copyObjValue(cmsImageSwitcherVO, info, null, false);

        PageResponseDTO<CmsImageSwitcherRespDTO> t = cmsImageSwitcherRSV
                .queryCmsImageSwitcherPage(info);

        if (CollectionUtils.isNotEmpty(t.getResult())) {
            for (CmsImageSwitcherRespDTO dto : t.getResult()) {
                // 3.1调文件服务器，返回图片
                if (StringUtils.isNotBlank(dto.getOnePic())) {
                    dto.setOnePicUrl(ImageUtil.getImageUrl(dto.getOnePic() + "_" + "120x50!"));
                }
            }

        }

        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
        return super.addPageToModel(model, respVO);

    }

    @RequestMapping("/editpage")
    public String editpage(Model model, @ModelAttribute
    CmsImageSwitcherVO cmsImageSwitcherVO) throws Exception {

        CmsImageSwitcherReqDTO cmsImageSwitcherReqDTO = new CmsImageSwitcherReqDTO();
        if(null!=cmsImageSwitcherVO.getId()){
        	cmsImageSwitcherReqDTO.setId(cmsImageSwitcherVO.getId());
        	CmsImageSwitcherRespDTO resp = cmsImageSwitcherRSV
        			.selectCmsImageSwitcher(cmsImageSwitcherReqDTO);
        	model.addAttribute("respVO", resp);
        }
        return "/cms/imageswitcher/imageswitcher-edit";
    }
    
    @RequestMapping("/view")
    public String view(Model model, @ModelAttribute
    CmsImageSwitcherVO cmsImageSwitcherVO) throws Exception{
        
        CmsImageSwitcherReqDTO cmsImageSwitcherReqDTO = new CmsImageSwitcherReqDTO();
        cmsImageSwitcherReqDTO.setId(cmsImageSwitcherVO.getId());
        CmsImageSwitcherRespDTO resp = cmsImageSwitcherRSV
                .selectCmsImageSwitcher(cmsImageSwitcherReqDTO);
        model.addAttribute("respVO", resp);
        return "/cms/imageswitcher/imageswitcher-view";
    }
    
    /**
     * 
     * edit:(实际上是发布). <br/> 
     * 
     * @author wangbh
     * @param model
     * @param cmsImageSwitcherVO
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */

    @RequestMapping("/edit")
    @ResponseBody
    public EcpBaseResponseVO edit(Model model, @ModelAttribute
    CmsImageSwitcherVO cmsImageSwitcherVO) throws Exception {

        EcpBaseResponseVO res = new EcpBaseResponseVO();
        CmsImageSwitcherReqDTO cmsImageSwitcherReqDTO = new CmsImageSwitcherReqDTO();
        ObjectCopyUtil.copyObjValue(cmsImageSwitcherVO, cmsImageSwitcherReqDTO, null, false);
        cmsImageSwitcherReqDTO.setStatus(CmsConstants.CheckStatus.CMS_CHECKSTATUS_1);
        cmsImageSwitcherRSV.saveCmsImageSwitcher(cmsImageSwitcherReqDTO);
        res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return res;
    }

    
    /**
     * 
     * save:(保存). <br/> 
     * 
     * @author wangbh
     * @param model
     * @param cmsImageSwitcherVO
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping("/save")
    @ResponseBody
    public EcpBaseResponseVO save(Model model, @ModelAttribute
    CmsImageSwitcherVO cmsImageSwitcherVO) throws Exception {

        EcpBaseResponseVO res = new EcpBaseResponseVO();
        CmsImageSwitcherReqDTO cmsImageSwitcherReqDTO = new CmsImageSwitcherReqDTO();
        ObjectCopyUtil.copyObjValue(cmsImageSwitcherVO, cmsImageSwitcherReqDTO, null, false);
        cmsImageSwitcherReqDTO.setStatus(CmsConstants.CheckStatus.CMS_CHECKSTATUS_0);
        cmsImageSwitcherRSV.saveCmsImageSwitcher(cmsImageSwitcherReqDTO);
        res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return res;
    }
    
    /**
     * 
     * update:(修改). <br/> 
     * 
     * @author wangbh
     * @param cmsImageSwitcherVO
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    
    @RequestMapping("/update")
    @ResponseBody
    public EcpBaseResponseVO update(@ModelAttribute
    CmsImageSwitcherVO cmsImageSwitcherVO) throws Exception{
        
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        CmsImageSwitcherReqDTO dto = new CmsImageSwitcherReqDTO();
        ObjectCopyUtil.copyObjValue(cmsImageSwitcherVO, dto, null, false);
        cmsImageSwitcherRSV.updateCmsImageSwitcher(dto);
        res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return res;
    }
    
    
    /**
     * uploadImage:(上传图片). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @param model
     * @param file
     * @param req
     * @param rep
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/uploadImage")
    @ResponseBody
    @NativeJson(true)
    public String uploadImage(Model model,
            @RequestParam(value = "uploadFileObj2", required = false)
            MultipartFile uploadFileObj, @RequestParam(value = "place_width", required = false)
            int place_width, @RequestParam(value = "place_height", required = false)
            int place_height, @RequestParam(value = "place_size", required = false)
            int place_size, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JSONObject obj = new JSONObject();// 返回结果
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            if (uploadFileObj == null) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "请选择上传文件！");
                LogUtil.info(MODULE, "请选择上传文件！");
                return obj.toJSONString();
            }
            String vfsName = uploadFileObj.getOriginalFilename();
            vfsName = vfsName.replace(" ", "");
            String extensionName = "." + ConstantTool.getExtensionName(vfsName);

            /** 支持文件拓展名：.jpg,.png,.jpeg,.gif,.bmp */
            boolean flag = Pattern.compile("\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$")
                    .matcher(extensionName.toLowerCase()).find();
            if (!flag) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp)！");
                LogUtil.error(MODULE, "上传图片失败,原因---请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp)!");
                return obj.toJSONString();
            }

            byte[] datas = ConstantTool.inputStream2Bytes(uploadFileObj.getInputStream());
            if (datas.length > place_size * 1024) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "图片上传失败，上传的图片必须小于" + place_size + "  KB!");
                LogUtil.error(MODULE, "图片上传失败，上传的图片必须小于!");
                return obj.toJSONString();
            }
            // 判断图片的长宽（像素）
            BufferedImage bi = ImageIO.read(uploadFileObj.getInputStream());
            ;
            int width = bi.getWidth();
            int height = bi.getHeight();
            if (place_width < width || place_height < height) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "图片上传失败，上传的图片尺寸必须小于" + "  " + place_width + "*" + place_height
                        + "px！");
                LogUtil.error(MODULE, "图片上传失败，上传的图片必须小于!");
                return obj.toJSONString();
            }
            // String vfsId = ImageUtil.upLoadImage(datas, vfsName);
            String vfsId;
            if (extensionName.equalsIgnoreCase(".png")) {
                vfsId = ImageUtil.saveToRomte(datas, "image", "png");
            } else {
                vfsId = ImageUtil.upLoadImage(datas, "image");
            }
            // resultMap.put("id", id);
            resultMap.put("vfsId", vfsId);
            resultMap.put("vfsName", vfsName);
            resultMap.put("vfsUrl", ConstantTool.getImageUrl(vfsId, "150x150!"));
            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            obj.put("message", "图片上传成功!");
            obj.put("resultMap", resultMap);
        } catch (Exception e) {
            LogUtil.info(MODULE, "图片上传失败,原因---" + e.getMessage(), e);
            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            obj.put("message", "图片上传失败，图片服务器异常，请联系管理员!");
        }
        return obj.toJSONString();
    }
}