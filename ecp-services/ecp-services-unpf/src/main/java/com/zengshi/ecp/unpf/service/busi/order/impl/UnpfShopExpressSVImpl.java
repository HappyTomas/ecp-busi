package com.zengshi.ecp.unpf.service.busi.order.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderLogisticsReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.Company;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderLogisticsRespDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IOrderLogisticsRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfShopExpressMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfShopExpress;
import com.zengshi.ecp.unpf.dao.model.UnpfShopExpressCriteria;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfShopExpressReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfShopExpressResp;
import com.zengshi.ecp.unpf.service.busi.auth.interfaces.IUnpfShopAuthSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfShopExpressSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UnpfShopExpressSVImpl implements IUnpfShopExpressSV {

    private static final String MODULE = UnpfShopExpressSVImpl.class.getName();

    @Resource
    private IOrderLogisticsRSV orderLogisticsRSV;

    @Resource
    private IUnpfShopAuthSV unpfShopAuthSV;

    @Resource
    private UnpfShopExpressMapper unpfShopExpressMapper;

    @Resource(name = "seq_unpf_shop_express")
    private Sequence seqUnpfShopExpress;

    @Override
    public void dealShopExpress(OrderLogisticsReqDTO orderLogisticsReqDTO) throws BusinessException {
        // TODO Auto-generated method stub
        if (orderLogisticsReqDTO == null || orderLogisticsReqDTO.getAuthId() == null) {
            throw new BusinessException("unpf.100003");
        }
        UnpfShopAuthReqDTO shopReq = new UnpfShopAuthReqDTO();
        shopReq.setId(orderLogisticsReqDTO.getAuthId());
        //查询店铺授权基本信息
        UnpfShopAuthRespDTO shopResp = unpfShopAuthSV.queryPlatType4ShopById(shopReq);
        if (shopResp == null || shopResp.getId() == null) {
            throw new BusinessException("unpf.100005");
        }
        BaseShopAuthReqDTO baseShopAuthReqDTO = new BaseShopAuthReqDTO();
        ObjectCopyUtil.copyObjValue(shopResp, baseShopAuthReqDTO, null, false);
        ObjectCopyUtil.copyObjValue(baseShopAuthReqDTO, orderLogisticsReqDTO, null, false);
        orderLogisticsReqDTO.setAuthId(shopResp.getId());

        OrderLogisticsRespDTO resp = orderLogisticsRSV.queryLogisticsCompany(orderLogisticsReqDTO);
        int sortNo = 0;

        if (StringUtil.isNotEmpty(resp) && StringUtil.isNotEmpty(resp.getCompanies())) {
            //删除该店铺该平台下的所有物流公司
            UnpfShopExpressCriteria criteria = new UnpfShopExpressCriteria();
            criteria.createCriteria().andShopIdEqualTo(orderLogisticsReqDTO.getShopId())
                    .andPlatTypeEqualTo(orderLogisticsReqDTO.getPlatType());
            unpfShopExpressMapper.deleteByExample(criteria);
            //插入该店铺该平台下的最新的物流公司信息
            for (Company company : resp.getCompanies()) {
                try {
                    UnpfShopExpress unpfShopExpress = new UnpfShopExpress();
                    unpfShopExpress.setcId("" + company.getId());
                    unpfShopExpress.setCode(company.getCode());
                    unpfShopExpress.setName(company.getName());
                    unpfShopExpress.setRegMailNo(company.getRegMailNo());
                    unpfShopExpress.setPlatType(orderLogisticsReqDTO.getPlatType());
                    unpfShopExpress.setStatus("1");//默认1：启用
                    unpfShopExpress.setCreateStaff(1000L);
                    unpfShopExpress.setShopId(orderLogisticsReqDTO.getShopId());//所属店铺
                    unpfShopExpress.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    sortNo++;
                    unpfShopExpress.setSortNo(new Long(sortNo));//按自然数顺序从小到大排序
                    unpfShopExpress.setId(seqUnpfShopExpress.nextValue());
                    unpfShopExpressMapper.insert(unpfShopExpress);
                } catch (Exception e) {
                    LogUtil.error(MODULE, "=============插入表t_unpf_shop_express失败，失败物流公司code为" + company.getCode() + " error =============", e);
                }
            }
        }
    }

    @Override
    public List<RUnpfShopExpressResp> queryShopExpressResp(RUnpfShopExpressReq req) throws BusinessException {
        // TODO Auto-generated method stub
        List<RUnpfShopExpressResp> shopExpressResps = new ArrayList<RUnpfShopExpressResp>();
        UnpfShopExpressCriteria criteria = new UnpfShopExpressCriteria();
        criteria.createCriteria().andShopIdEqualTo(req.getShopId()).andPlatTypeEqualTo(req.getPlatType());
        List<UnpfShopExpress> list = unpfShopExpressMapper.selectByExample(criteria);
        if (list != null) {
            for (UnpfShopExpress unpfShopExpress : list) {
                RUnpfShopExpressResp shopExpressResp = new RUnpfShopExpressResp();
                ObjectCopyUtil.copyObjValue(unpfShopExpress, shopExpressResp, null, false);
                shopExpressResps.add(shopExpressResp);
            }
        }
        return shopExpressResps;
    }

    @Override
    public RUnpfShopExpressResp getShopExpressResp(RUnpfShopExpressReq req) throws BusinessException {
        // TODO Auto-generated method stub
        RUnpfShopExpressResp shopExpressResp = null;
        UnpfShopExpressCriteria criteria = new UnpfShopExpressCriteria();
        criteria.createCriteria().andShopIdEqualTo(req.getShopId()).andPlatTypeEqualTo(req.getPlatType()).andCodeEqualTo(req.getCode());
        List<UnpfShopExpress> list = unpfShopExpressMapper.selectByExample(criteria);
        if (list != null && list.size() > 0) {
            shopExpressResp = new RUnpfShopExpressResp();
            ObjectCopyUtil.copyObjValue(list.get(0), shopExpressResp, null, false);
        }
        return shopExpressResp;
    }

    @Override
    public void insert(UnpfShopExpress express) {
        //避免重复数据
        UnpfShopExpressCriteria criteria = new UnpfShopExpressCriteria();
        criteria.createCriteria().andPlatTypeEqualTo(express.getPlatType())
                .andStatusEqualTo("1").andShopIdEqualTo(express.getShopId())
                .andCodeEqualTo(express.getCode());
        List<UnpfShopExpress> list = unpfShopExpressMapper.selectByExample(criteria);
        if (CollectionUtils.isEmpty(list)) {
            express.setId(seqUnpfShopExpress.nextValue());
            unpfShopExpressMapper.insert(express);
        }
    }
}
