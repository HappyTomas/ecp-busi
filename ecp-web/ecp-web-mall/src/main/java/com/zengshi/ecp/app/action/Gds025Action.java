package com.zengshi.ecp.app.action;

import com.zengshi.ecp.app.req.Gds025Req;
import com.zengshi.ecp.app.resp.Gds025Resp;
import com.zengshi.ecp.app.resp.gds.GdsCategoryVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.butterfly.app.annotation.Action;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 店铺分类服务
 * Created by HDF on 2016/6/1.
 */
@Service("gds025")
@Action(bizcode = "gds025", userCheck = false)
@Scope("prototype")
public class Gds025Action extends AppBaseAction<Gds025Req, Gds025Resp>{

    @Resource
    private IGdsCategoryRSV iGdsCategoryRSV;

    @Override
    protected void getResponse() {

        Gds025Req gds025Req = this.request;
        Gds025Resp gds025Resp = this.response;

        gds025Resp.setCatgList(this.queryShopCatgInfo(gds025Req));

    }


    private List<GdsCategoryVO> queryShopCatgInfo(Gds025Req gds025Req){
        GdsCategoryReqDTO gdsCategoryReqDTO=new GdsCategoryReqDTO();
        gdsCategoryReqDTO.setShopId(Long.valueOf(gds025Req.getId()));
        gdsCategoryReqDTO.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_2);

        List<GdsCategoryVO> vos=new ArrayList<GdsCategoryVO>();
        List<GdsCategoryRespDTO> catgs=iGdsCategoryRSV.queryRootCategory(gdsCategoryReqDTO);
        if(CollectionUtils.isNotEmpty(catgs)){
            for(GdsCategoryRespDTO catg :catgs){
                GdsCategoryReqDTO req=new GdsCategoryReqDTO();
                req.setCatgParent(catg.getCatgCode());
                List<GdsCategoryRespDTO> childCatgs=iGdsCategoryRSV.querySubCategory(req);

                GdsCategoryVO vo=new GdsCategoryVO();
                vo.setCatgCode(catg.getCatgCode());
                vo.setCatgName(catg.getCatgName());
                vo.setSortNo(catg.getSortNo());

                // 生成图片路径
                vo.setMediaURL(ImageUtil.getImageUrl(catg.getMediaUuid() + GdsCategoryVO.SUFFIX_IMAGE_SIZE));

                if(CollectionUtils.isNotEmpty(childCatgs)){
                    List<GdsCategoryVO> childVos=new ArrayList<GdsCategoryVO>();
                    for(GdsCategoryRespDTO childCatg:childCatgs){
                        GdsCategoryVO childVo=new GdsCategoryVO();
                        childVo.setCatgCode(childCatg.getCatgCode());
                        childVo.setCatgName(childCatg.getCatgName());
                        childVo.setSortNo(childCatg.getSortNo());
                        // 生成图片路径
                        childVo.setMediaURL(ImageUtil.getImageUrl(childCatg.getMediaUuid() + GdsCategoryVO.SUFFIX_IMAGE_SIZE));
                        childVos.add(childVo);
                    }
                    vo.setChildren(childVos);
                }
                vos.add(vo);
            }
        }

        return vos;
    }

}
