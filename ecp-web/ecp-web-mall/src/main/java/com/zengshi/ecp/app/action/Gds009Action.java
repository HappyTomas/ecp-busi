package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds009Req;
import com.zengshi.ecp.app.resp.Gds009Resp;
import com.zengshi.ecp.app.resp.gds.GdsCollectBaseInfo;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 获取收藏商品列表 Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月10日下午2:40:37 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
@Service("gds009")
@Action(bizcode = "gds009", userCheck = true)
@Scope("prototype")
public class Gds009Action extends AppBaseAction<Gds009Req, Gds009Resp> {

    @Resource
    private IOrdCartRSV ordCartRSV;

    @Resource
    private IGdsCollectRSV iGdsCollectRSV;
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    @Resource
    private IGdsInfoExternalRSV gdsInfoExternalRSV;

    private static final String MODULE = Gds009Action.class.getName();

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        Gds009Req gds009Req = this.request;
        Gds009Resp gds009Resp = this.response;
        // 初始化分页

        GdsCollectReqDTO reqDto = new GdsCollectReqDTO();
        reqDto.setPageNo(gds009Req.getPageNumber());
        reqDto.setPageSize(gds009Req.getPageSize());
        reqDto.setStaffId(reqDto.getStaff().getId());
        EcpBasePageRespVO<Map> pageRespVo = null;
        PageResponseDTO<GdsCollectRespDTO> pageRespDto = null;
        // 查询数据
        pageRespDto = getCollData(reqDto);

        // 如果返回空集 创建一个
        if (pageRespDto.getResult() == null) {
            pageRespDto.setResult(new ArrayList<GdsCollectRespDTO>());
        } else {
            // 转化参数 增加reduce字段值
            for (GdsCollectRespDTO respDto : pageRespDto.getResult()) {
                if (respDto.getGdsPrice() == null) {
                    respDto.setGdsPrice(0l);
                }
                if (respDto.getNowPrice() == null) {
                    respDto.setNowPrice(0l);
                }
                respDto.setReducePrice(respDto.getGdsPrice() - respDto.getNowPrice());
            }

        }
        List<GdsCollectBaseInfo> collectBaseInfos = new ArrayList<GdsCollectBaseInfo>();
        for (GdsCollectRespDTO collectRespDTO : pageRespDto.getResult()) {
            GdsCollectBaseInfo collectBaseInfo = new GdsCollectBaseInfo();
            ObjectCopyUtil.copyObjValue(collectRespDTO, collectBaseInfo, null, false);
            GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
            gdsSkuInfoReqDTO.setId(collectRespDTO.getSkuId());
            GdsSkuInfoRespDTO gdsSkuInfoRespDTO =     gdsSkuInfoQueryRSV.queryGdsSkuInfoResp(gdsSkuInfoReqDTO);
            collectBaseInfo.setCatgCode(gdsSkuInfoRespDTO.getMainCatgs());
            collectBaseInfo.setSkuMainPicUrl(collectRespDTO.getSkuMainPic());
            collectBaseInfo.setGdsStatus(gdsSkuInfoRespDTO.getGdsStatus());
            boolean isVirtual = gdsInfoExternalRSV.isVirtualProduct(new LongReqDTO(gdsSkuInfoRespDTO.getGdsTypeId()));
            collectBaseInfo.setVirtual(isVirtual);
            collectBaseInfos.add(collectBaseInfo);
        }

        gds009Resp.setGdsCollectBaseInfos(collectBaseInfos);
        gds009Resp.setCount(pageRespDto.getCount());
        gds009Resp.setPageCount(pageRespDto.getPageCount());
    }

    private PageResponseDTO<GdsCollectRespDTO> getCollData(GdsCollectReqDTO reqDto) {

        PageResponseDTO<GdsCollectRespDTO> pageRespDto = null;
        try {
            // 查询数据
            pageRespDto = iGdsCollectRSV.queryGdsCollectRespDTOPagingByStaff(reqDto);

            if (CollectionUtils.isNotEmpty(pageRespDto.getResult())) {
                List<GdsCollectRespDTO> result = pageRespDto.getResult();
                for (GdsCollectRespDTO gdsCollectRespDTO : result) {
                    String imageUrl = getImageUrl(gdsCollectRespDTO.getSkuMainPic(), "200x200!");
                    gdsCollectRespDTO.setSkuMainPic(imageUrl);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.info(MODULE, "获取买家我的收藏列表失败！", e);
            pageRespDto = null;
        }

        return pageRespDto;
    }// end of method getCollData

    private String getImageUrl(String vfsId, String param) {
        StringBuilder sb = new StringBuilder();
        sb.append(vfsId);
        if (!StringUtil.isBlank(param)) {
            sb.append("_");
            sb.append(param);
        }
        return ImageUtil.getImageUrl(sb.toString());
    }
}
