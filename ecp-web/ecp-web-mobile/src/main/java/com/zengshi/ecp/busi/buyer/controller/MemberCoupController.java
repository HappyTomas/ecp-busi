package com.zengshi.ecp.busi.buyer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.buyer.vo.CoupDetailVO;
import com.zengshi.ecp.busi.buyer.vo.CoupScrollResultVO;
import com.zengshi.ecp.busi.buyer.vo.CustAddrVO;
import com.zengshi.ecp.busi.buyer.vo.MemberCoupVO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupMeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupMeRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

@Controller
@RequestMapping(value = "/membercoup")
public class MemberCoupController {
    
    @Resource
    private ICoupDetailRSV coupDetailRSV;
    
    private static final String Module = MemberCoupController.class.getName();
    
    @RequestMapping(value = "/index")
    public String index(Model model, MemberCoupVO coupVO) throws BusinessException {
        
        
        CoupMeReqDTO queryDTO = coupVO.toBaseInfo(CoupMeReqDTO.class);
        // 0:已过期 1:可使用 2:已使用  3:已冻结
        
        queryDTO.setStaffId(StaffLocaleUtil.getStaff().getId());
        queryDTO.setPageNo(0);//all 
        
        queryDTO.setOpeType(CouponConstants.CoupDetail.opeType_0);
        long count_past= coupDetailRSV.queryCoupDetailCount(queryDTO);
        
        queryDTO.setOpeType(CouponConstants.CoupDetail.opeType_1);
        long count_active = coupDetailRSV.queryCoupDetailCount(queryDTO);
        
        queryDTO.setOpeType(CouponConstants.CoupDetail.opeType_2);
        long count_used= coupDetailRSV.queryCoupDetailCount(queryDTO);
        
        model.addAttribute("count_past", count_past);
        model.addAttribute("count_active", count_active);
        model.addAttribute("count_used", count_used);
        
        return "/buyer/member-coupinfo";
    }
    @RequestMapping(value="/data")
    @ResponseBody
    public CoupScrollResultVO data(Model model, MemberCoupVO coupVO)
    {
        CoupScrollResultVO resultVo = new CoupScrollResultVO();

        CoupMeReqDTO queryDTO = coupVO.toBaseInfo(CoupMeReqDTO.class);
        // 0:已过期 1:可使用 2:已使用  3:已冻结

        queryDTO.setStaffId(StaffLocaleUtil.getStaff().getId());
        Map<String, String> mapSort = new HashMap<String, String>();

        //1:可使用 2:已使用  0:已过期 3:已冻结
        switch (coupVO.getDatatype()) {
        
        case CouponConstants.CoupDetail.opeType_0:
            mapSort.put("UPDATE_TIME", "0");
            queryDTO.setOpeType(CouponConstants.CoupDetail.opeType_0);
            break;
        case CouponConstants.CoupDetail.opeType_1:
            mapSort.put("CREATE_TIME", "0");
            queryDTO.setOpeType(CouponConstants.CoupDetail.opeType_1);
            break;
        case CouponConstants.CoupDetail.opeType_2:
            mapSort.put("USE_TIME", "0");
            queryDTO.setOpeType(CouponConstants.CoupDetail.opeType_2);
            break;
        default:
            return resultVo;
        }
        queryDTO.setMapSort(mapSort);
        
        PageResponseDTO<CoupMeRespDTO> couppage= coupDetailRSV.queryCoupDetailPage(queryDTO);
        if (couppage != null && CollectionUtils.isNotEmpty(couppage.getResult())) {
            resultVo.setDatas(new ArrayList<CoupDetailVO>(couppage.getResult().size()));
            for(CoupMeRespDTO  record : couppage.getResult())
            {
                CoupDetailVO detailVO = new CoupDetailVO();
                detailVO.setShopId(record.getShopId());
                detailVO.setShopName(record.getShopName());
                detailVO.setCoupValue(record.getCoupValue());
                detailVO.setConditions(record.getConditions());
                detailVO.setConditionsShow(record.getConditionsShow());
                detailVO.setActiveTime(DateUtil.getDateString(record.getActiveTime(), "yyyy-MM-dd"));
                detailVO.setInactiveTime(DateUtil.getDateString(record.getInactiveTime(), "yyyy-MM-dd"));
                detailVO.setCoupStatus(record.getCoupStatus());
                detailVO.setSiteId(record.getSiteId());
                if(StringUtil.isNotBlank(record.getUseRuleCode())&&record.getUseRuleCode().contains("240")){
                	detailVO.setDiscountCoup("1");
                }else{
                	detailVO.setDiscountCoup("0");
                }
                resultVo.getDatas().add(detailVO);
            }
        }
        resultVo.setTotal(couppage.getPageCount());
        resultVo.setDataType(coupVO.getDatatype());
        return resultVo;
    }
}

