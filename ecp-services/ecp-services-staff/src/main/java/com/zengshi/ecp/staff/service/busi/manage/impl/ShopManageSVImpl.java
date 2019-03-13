package com.zengshi.ecp.staff.service.busi.manage.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopHotlineInfoMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopVipLevelMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopVipRealMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopVipRealSIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopVipRuleMapper;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.ShopHotlineInfo;
import com.zengshi.ecp.staff.dao.model.ShopHotlineInfoCriteria;
import com.zengshi.ecp.staff.dao.model.ShopHotlineInfoCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dao.model.ShopVipLevel;
import com.zengshi.ecp.staff.dao.model.ShopVipLevelCriteria;
import com.zengshi.ecp.staff.dao.model.ShopVipLevelKey;
import com.zengshi.ecp.staff.dao.model.ShopVipReal;
import com.zengshi.ecp.staff.dao.model.ShopVipRealCriteria;
import com.zengshi.ecp.staff.dao.model.ShopVipRealSIDX;
import com.zengshi.ecp.staff.dao.model.ShopVipRealSIDXCriteria;
import com.zengshi.ecp.staff.dao.model.ShopVipRule;
import com.zengshi.ecp.staff.dao.model.ShopVipRuleCriteria;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopCollectReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopHotlineReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopHotlineResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipLevelReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipLevelResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRealReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRealResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRealSIDXReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustInfoSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 店铺客服信息接口实现类<br>
 * Date:2015-8-19下午7:52:02  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public class ShopManageSVImpl extends GeneralSQLSVImpl implements IShopManageSV{

    @Resource(name = "seq_shop_hotline")
    private Sequence seq_hotline;
    
    @Resource(name = "seq_shop_vip_level")
    private Sequence seq_hot_vip_level;
    
    @Resource(name = "seq_shop_vip_rule_idx")
    private Sequence seq_shop_vip_rule_idx;
    
    @Resource(name = "seq_shop_vip_real")
    private Sequence seq_shop_vip_real;
    
    @Resource
    private ICustInfoSV custInfoSV;
    
    @Resource
    private IShopMgrSV shopMgrSV; //店铺服务
    
    @Resource
    private ShopHotlineInfoMapper mapper;
    
    @Resource
    private ShopVipLevelMapper levelMapper;
    
    @Resource
    private ShopVipRealMapper realMapper;
    
    @Resource
    private ShopVipRuleMapper shopVipRuleMapper;
    
    @Resource
    private ShopVipRealSIDXMapper realSIDXMapper; //关系的用户索引表
    
    @Override
    public PageResponseDTO<ShopHotlineResDTO> listShopHotline(ShopHotlineReqDTO req)
            throws BusinessException {
        PageResponseDTO<ShopHotlineResDTO> pageInfo = new PageResponseDTO<ShopHotlineResDTO>();
        pageInfo.setPageNo(req.getPageNo());
        pageInfo.setPageSize(req.getPageSize());
        pageInfo.setCount(0);//初始化记录为0
        pageInfo.setResult(null);//初始传空值
        ShopHotlineInfoCriteria criteria = new ShopHotlineInfoCriteria();
        Criteria sql = criteria.createCriteria();
        sql.andShopIdEqualTo(req.getShopId());
        if (!StringUtil.isEmpty(req.getModuleType())) {
           sql.andModuleTypeEqualTo(req.getModuleType()); 
        }
        //分页查询处理
        pageInfo = super.queryByPagination(req, criteria, true, new PaginationCallback<ShopHotlineInfo, ShopHotlineResDTO>() {

            @Override
            public List<ShopHotlineInfo> queryDB(BaseCriteria bc) {
                return mapper.selectByExample((ShopHotlineInfoCriteria)bc);
            }

            @Override
            public long queryTotal(BaseCriteria bc) {
                return mapper.countByExample((ShopHotlineInfoCriteria)bc);
            }

            @Override
            public List<Comparator<ShopHotlineInfo>> defineComparators() {
                List<Comparator<ShopHotlineInfo>> ls=new ArrayList<Comparator<ShopHotlineInfo>>();
                ls.add(new Comparator<ShopHotlineInfo>(){

                    @Override
                    public int compare(ShopHotlineInfo o1, ShopHotlineInfo o2) {
                        return o1.getCreateTime().getTime()<o2.getCreateTime().getTime()?1:-1;
                    }
                    
                });
                return ls;
            }

            @Override
            public ShopHotlineResDTO warpReturnObject(ShopHotlineInfo info) {
                ShopHotlineResDTO dto = new ShopHotlineResDTO();
                ObjectCopyUtil.copyObjValue(info,dto,null,false);
                return dto;
            }
        });
        return pageInfo;
    }

    @Override
    public int saveShopHotline(ShopHotlineReqDTO req) throws BusinessException {
        ShopHotlineInfo hotline = new ShopHotlineInfo();
        ObjectCopyUtil.copyObjValue(req, hotline, null, false);
        hotline.setId(seq_hotline.nextValue());
        return mapper.insertSelective(hotline);
    }

    @Override
    public int updateShopHotline(ShopHotlineReqDTO req) throws BusinessException {
        ShopHotlineInfo hotline = new ShopHotlineInfo();
        ObjectCopyUtil.copyObjValue(req, hotline, null, false);
        return mapper.updateByPrimaryKey(hotline);
    }

    @Override
    public int deleteShopHotline(ShopHotlineReqDTO req) throws BusinessException {
        return mapper.deleteByPrimaryKey(req.getId());
    }

    @Override
    public int saveShopVipLevel(ShopVipLevelReqDTO req) throws BusinessException {
        ShopVipLevel shopVipLevel = new ShopVipLevel();
        ObjectCopyUtil.copyObjValue(req, shopVipLevel, null, false);
        shopVipLevel.setId(seq_hot_vip_level.nextValue());
        shopVipLevel.setCreateTime(DateUtil.getSysDate());
        shopVipLevel.setUpdateTime(DateUtil.getSysDate());
        shopVipLevel.setCreateStaff(req.getStaff().getId());
        shopVipLevel.setUpdateStaff(req.getStaff().getId());
        shopVipLevel.setStatus("1");
        return levelMapper.insert(shopVipLevel);
    }

    @Override
    public int updateShopVipLevel(ShopVipLevelReqDTO req) throws BusinessException {
        ShopVipLevel shopVipLevel = new ShopVipLevel();
        ObjectCopyUtil.copyObjValue(req, shopVipLevel, null, true);
        return levelMapper.updateByPrimaryKey(shopVipLevel);
    }

    @Override
    public PageResponseDTO<ShopVipLevelResDTO> listShopVipLevel(ShopVipLevelReqDTO req)
            throws BusinessException {
        PageResponseDTO<ShopVipLevelResDTO> pageInfo = new PageResponseDTO<ShopVipLevelResDTO>();
        pageInfo.setPageNo(req.getPageNo());
        pageInfo.setPageSize(req.getPageSize());
        pageInfo.setCount(0);//初始化记录为0
        pageInfo.setResult(null);//初始传空值
        ShopVipLevelCriteria criteria = new ShopVipLevelCriteria();
        com.zengshi.ecp.staff.dao.model.ShopVipLevelCriteria.Criteria sql = criteria.createCriteria();
        
        sql.andShopIdEqualTo(req.getShopId());
        //查询条件：会员等级
        if (StringUtil.isNotBlank(req.getVipLevelCode())) {
            sql.andVipLevelCodeEqualTo(req.getVipLevelCode());
        }else {
        	//查询条件：消费金额
        	if(req.getOrderPay()!=null){
            	sql.andOrderPayLessThanOrEqualTo(req.getOrderPay());//比条件值小或相等的集合
            }
        }
        //过滤有效记录
        if(StringUtil.isBlank(req.getStatus())){
        	sql.andStatusEqualTo(StaffConstants.PublicParam.STATUS_ACTIVE);
        }else{
        	sql.andStatusEqualTo(req.getStatus());
        }
        
        criteria.setLimitClauseCount(req.getPageSize());
        criteria.setLimitClauseStart(req.getStartRowIndex());
        criteria.setOrderByClause(" order_pay desc ");//按消费金额升序排/等级由“高”到“低”
        
        pageInfo = super.queryByPagination(req, criteria, true, new PaginationCallback<ShopVipLevel, ShopVipLevelResDTO>() {

            @Override
            public List<ShopVipLevel> queryDB(BaseCriteria bc) {
                return levelMapper.selectByExample((ShopVipLevelCriteria)bc);
            }

            @Override
            public long queryTotal(BaseCriteria bc) {
                return levelMapper.countByExample((ShopVipLevelCriteria)bc);
            }

            @Override
            public ShopVipLevelResDTO warpReturnObject(ShopVipLevel vipLevel) {
                ShopVipLevelResDTO dto = new ShopVipLevelResDTO();
                ObjectCopyUtil.copyObjValue(vipLevel, dto, null, true);
                //查询出店铺vip会员的规则
                ShopVipRuleCriteria criteria = new ShopVipRuleCriteria();
                criteria.createCriteria().andShopIdEqualTo(vipLevel.getShopId() + "").andVipLevelCodeEqualTo(vipLevel.getVipLevelCode());
                List<ShopVipRule> rule = ShopManageSVImpl.this.shopVipRuleMapper.selectByExample(criteria);
                if (CollectionUtils.isNotEmpty(rule)) {
                    dto.setDiscount(rule.get(0).getDiscount());
                }
                return dto;
            }
        });
        return pageInfo;
    }

    @Override
    public PageResponseDTO<ShopVipRealResDTO> listShopVipReal(ShopVipRealReqDTO req)
            throws BusinessException {
        PageResponseDTO<ShopVipRealResDTO> pageInfo = new PageResponseDTO<ShopVipRealResDTO>();
        pageInfo.setPageNo(req.getPageNo());
        pageInfo.setPageSize(req.getPageSize());
        pageInfo.setCount(0);//初始化记录为0
        pageInfo.setResult(null);//初始传空值
        ShopVipRealCriteria criteria = new ShopVipRealCriteria();
        com.zengshi.ecp.staff.dao.model.ShopVipRealCriteria.Criteria sql = criteria.createCriteria();
        
        sql.andShopIdEqualTo(req.getShopId());
        //查询条件：会员名
        if (StringUtil.isNotBlank(req.getStaffCode())) {
            sql.andStaffCodeLike("%" + req.getStaffCode() + "%");
        }
        //查询条件：会员id
        if (req.getStaffId() != null && req.getStaffId() != 0L) {
        	sql.andStaffIdEqualTo(req.getStaffId());
        }
        //查询条件：会员等级
        if (StringUtil.isNotBlank(req.getCustLevelCode())) {
            sql.andCustLevelCodeEqualTo(req.getCustLevelCode());
        }
        //查询条件：交易额from
        if (req.getPayMoneyFrom() != null) {
            sql.andPayMoneyGreaterThanOrEqualTo(req.getPayMoneyFrom());
        }
        //查询条件：交易额end
        if (req.getPayMoneyEnd() != null) {
            sql.andPayMoneyLessThanOrEqualTo(req.getPayMoneyEnd());
        }
        //查询条件：次数from
        if (req.getTradesNumFrom() != null) {
            sql.andTradesNumGreaterThanOrEqualTo(req.getTradesNumFrom());
        }
        //查询条件：次数end
        if (req.getTradesNumEnd() != null) {
            sql.andTradesNumLessThanOrEqualTo(req.getTradesNumEnd());
        }
        //查询条件：状态
        if (StringUtil.isNotBlank(req.getStatus())) {
            sql.andStatusEqualTo(req.getStatus());
        }
        
        criteria.setLimitClauseStart(pageInfo.getStartRowIndex());
        criteria.setLimitClauseCount(pageInfo.getPageSize());
        criteria.setOrderByClause("pay_money desc");//根据交易总金额倒序
        pageInfo = super.queryByPagination(req, criteria, true, new PaginationCallback<ShopVipReal, ShopVipRealResDTO>() {

            @Override
            public List<ShopVipReal> queryDB(BaseCriteria bc) {
                return realMapper.selectByExample((ShopVipRealCriteria)bc);
            }

            @Override
            public long queryTotal(BaseCriteria bc) {
                return realMapper.countByExample((ShopVipRealCriteria)bc);
            }
            @Override
            public List<Comparator<ShopVipReal>> defineComparators() {
                List<Comparator<ShopVipReal>> ls=new ArrayList<Comparator<ShopVipReal>>();
                ls.add(new Comparator<ShopVipReal>(){

                    @Override
                    public int compare(ShopVipReal o1, ShopVipReal o2) {
                        return o1.getCreateTime().getTime()<o2.getCreateTime().getTime()?1:-1;
                    }
                    
                });
                return ls;
            }
            
            @Override
            public ShopVipRealResDTO warpReturnObject(ShopVipReal realLevel) {
                ShopVipRealResDTO dto = new ShopVipRealResDTO();
                ObjectCopyUtil.copyObjValue(realLevel, dto, null, true);
                //通过staffId带出会员的相关信息
                if (dto.getStaffId() != null && dto.getStaffId() != 0L) {
                   CustInfo custInfo = custInfoSV.findCustInfoById(dto.getStaffId());
                   CustInfoResDTO custInfoRes = new CustInfoResDTO();
                   ObjectCopyUtil.copyObjValue(custInfo, custInfoRes, null, true);
                   dto.setCustInfo(custInfoRes);
                }
                if (StringUtil.isNotBlank(realLevel.getCustLevelCode())) {
                	ShopVipLevelReqDTO levelReq = new ShopVipLevelReqDTO();
                	levelReq.setVipLevelCode(realLevel.getCustLevelCode());
                	levelReq.setShopId(realLevel.getShopId());
                	PageResponseDTO<ShopVipLevelResDTO> levelPage = ShopManageSVImpl.this.listShopVipLevel(levelReq);
                	if (levelPage != null && CollectionUtils.isNotEmpty(levelPage.getResult())) {
                		dto.setCustLevelName(levelPage.getResult().get(0).getVipLevelName());
                	}
                }
                if (realLevel.getShopId() != null && realLevel.getShopId() != 0L) {
                	ShopInfo shopInfo = shopMgrSV.findShopByShopID(realLevel.getShopId());
                	if (StringUtil.isNotBlank(shopInfo.getLogoPath())) {
    					dto.setLogoPath(shopInfo.getLogoPath());
                    } else {
                    	dto.setLogoPath(ImageUtil.getDefaultImageId());
                    }
                }
                return dto;
            }
        });
        return pageInfo;
    }
   /**
    * 
    *  @author chenyz
    *  店铺会员生效/失效
    */
    @Override
    public int updateOffOrOnShopVipReal(ShopVipRealReqDTO req) throws BusinessException {
       ShopVipReal shopVipReal = new ShopVipReal();//获取modle对象
       ObjectCopyUtil.copyObjValue(req, shopVipReal, null, true);//入参数据复制到modle
       shopVipReal.setCreateTime(DateUtil.getSysDate());
       shopVipReal.setCreateStaff(req.getStaff().getId());
       shopVipReal.setUpdateStaff(req.getStaff().getId());
       shopVipReal.setUpdateTime(DateUtil.getSysDate());
       return realMapper.updateByPrimaryKeySelective(shopVipReal);//执行更新操作
    }
    /**
     * 
     *  @author chenyz
     *  删除店铺会员关系
     */
    @Override
    public int deleteShopVipReal(ShopVipRealReqDTO req) throws BusinessException {
        return realMapper.deleteByPrimaryKey(req.getId());
    }

    @Override
    public int deleteShopVipLevel(ShopVipLevelReqDTO req) throws BusinessException {
        ShopVipLevel level = new ShopVipLevel();
        level.setShopId(req.getShopId());
        return levelMapper.deleteByShopId(level);
    }

    @Override
    public int saveShopVipRule(ShopVipRuleReqDTO req) throws BusinessException {
        ShopVipRule rule = new ShopVipRule();
        ObjectCopyUtil.copyObjValue(req, rule, null, false);
        rule.setId(seq_shop_vip_rule_idx.nextValue());
        rule.setCreateStaff(req.getStaff().getId());
        rule.setCreateTime(DateUtil.getSysDate());
        rule.setUpdateTime(DateUtil.getSysDate());
        rule.setUpdateStaff(req.getStaff().getId());
        return shopVipRuleMapper.insertSelective(rule);
    }

    @Override
    public int deleteShopVipRule(ShopVipRuleReqDTO req) throws BusinessException {
        ShopVipRuleCriteria cirteria = new ShopVipRuleCriteria();
        com.zengshi.ecp.staff.dao.model.ShopVipRuleCriteria.Criteria sql = cirteria.createCriteria();
        //条件：店铺会员等级
        if (StringUtil.isNotBlank(req.getVipLevelCode())) {
            sql.andVipLevelCodeEqualTo(req.getVipLevelCode());
        }
        //条件：店铺id
        if (StringUtil.isNotBlank(req.getShopId())) {
            sql.andShopIdEqualTo(req.getShopId());
        }
        return shopVipRuleMapper.deleteByExample(cirteria);
    }

    @Override
    public int deleteShopLevelByLevelCode(ShopVipLevelReqDTO req) throws BusinessException {
        /*删除表t_shop_vip_level表中数据*/
        ShopVipLevelKey key = new ShopVipLevelKey();
        key.setVipLevelCode(req.getVipLevelCode());
        levelMapper.deleteByLevelCode(key);
        
        /*删除t_shop_vip_rule表中数据*/
        ShopVipRuleReqDTO rule = new ShopVipRuleReqDTO();
        rule.setVipLevelCode(req.getVipLevelCode());
        this.deleteShopVipRule(rule);
        return 0;
    }

	@Override
	public int updateShopVipReal(ShopVipRealReqDTO req) throws BusinessException {
		ShopVipReal real = new ShopVipReal();
		ObjectCopyUtil.copyObjValue(req, real, null, false);
		real.setUpdateStaff(req.getStaffId());
		real.setUpdateTime(DateUtil.getSysDate());
		ShopVipRealCriteria critera = new ShopVipRealCriteria();
		critera.createCriteria().andStaffIdEqualTo(req.getStaffId()).andShopIdEqualTo(req.getShopId());
		return realMapper.updateByExampleSelective(real, critera);
	}

	@Override
	public int saveShopVipReal(ShopVipRealReqDTO req) throws BusinessException {
		ShopVipReal real = new ShopVipReal();
		ObjectCopyUtil.copyObjValue(req, real, null, false);
		real.setUpdateStaff(req.getStaffId());
		real.setUpdateTime(DateUtil.getSysDate());
		real.setCreateStaff(req.getStaffId());
		real.setCreateTime(DateUtil.getSysDate());
		real.setId(seq_shop_vip_real.nextValue());
		real.setStatus("1");
		
		//增加索引记录
		ShopVipRealSIDX idx = new ShopVipRealSIDX();
		ObjectCopyUtil.copyObjValue(real, idx, null, false);
		realSIDXMapper.insertSelective(idx);
		
		return realMapper.insertSelective(real);
	}

	@Override
	public int updateShopVipRealForPay(PayQuartzInfoRequest req) throws BusinessException {
		/*订单参数不为0，且订单金额不为0，则进行后续业务处理*/
		if (req == null || req.getPayment() == 0L) {
			return 1;
		}
		ROrdCartItemCommRequest item = req.getOrdCartItemCommList().get(0);
		Long shopId = item.getShopId();
		/*2、查询用户是否已经有了店铺会员记录*/
		ShopVipRealReqDTO realReq = new ShopVipRealReqDTO();
		realReq.setStaffId(req.getStaffId());//会员id
		realReq.setShopId(shopId);//店铺id
		PageResponseDTO<ShopVipRealResDTO> resList = this.listShopVipReal(realReq);
		
		//查询会员相关信息
		CustInfo custInfo = custInfoSV.findCustInfoById(req.getStaffId());
		realReq.setCustLevelCode(custInfo.getCustLevelCode());
		realReq.setStaffCode(custInfo.getStaffCode());
		realReq.setPayDate(DateUtil.getSysDate());
		/*不存在记录*/
		if (resList == null || CollectionUtils.isEmpty(resList.getResult())) {
			realReq.setPayMoney(req.getPayment());
			realReq.setTradesNum(1L);
			String level = this.calShopVipLevel(req.getPayment()/100, 1L, shopId);//计算店铺会员等级
			realReq.setCustLevelCode(level);
			this.saveShopVipReal(realReq);
			/*存在，则更新数据 ，金额叠加、次数加1*/
		} else {
			ShopVipRealResDTO realRes = resList.getResult().get(0);
			long payMoney =  realRes.getPayMoney();
			long tradesNum =  realRes.getTradesNum();
			payMoney = payMoney + req.getPayment();
			tradesNum = tradesNum + 1;
			realReq.setPayMoney(payMoney);
			realReq.setTradesNum(tradesNum);
			String level = this.calShopVipLevel(payMoney/100, tradesNum, shopId);//计算店铺会员等级
			realReq.setCustLevelCode(level);
			this.updateShopVipReal(realReq);
		}
		
		return 1;
	}

	/**
	 * 通过交易金额或交易次数，计算店铺会员等级
	 * @param money
	 * @param cnt
	 * @param shopId
	 * @return
	 */
	private String calShopVipLevel(long money,long cnt,Long shopId) {
		ShopVipLevelReqDTO levelReq = new ShopVipLevelReqDTO();
		levelReq.setShopId(shopId);
		levelReq.setPageNo(0);
		PageResponseDTO<ShopVipLevelResDTO> page = this.listShopVipLevel(levelReq);
		
		/*店铺有设置会员 等级则计算*/
		ShopVipLevel custLevel = new ShopVipLevel();//此次计算，用户已获得的等级
		custLevel.setOrderPay(new BigDecimal(0));
		custLevel.setTradesNum(new BigDecimal(0));
		if (page != null && CollectionUtils.isNotEmpty(page.getResult())) {
			for (ShopVipLevelResDTO res : page.getResult()) {
				//根据次数计算等级
				//用户的交易次数，大于或等于该等级需要的交易次数，则获得该等级
				if (res.getTradesNum() != null && new BigDecimal(cnt).compareTo(res.getTradesNum()) != -1) {
					//如果循环对象的金额，大于已获得的等级的金额，则等级设为金额更大的（等级始终按金额更大的为准）
					if (res.getOrderPay() != null && res.getOrderPay().compareTo(custLevel.getOrderPay()) != -1) {
						custLevel.setVipLevelCode(res.getVipLevelCode());
						custLevel.setOrderPay(res.getOrderPay());
						custLevel.setTradesNum(res.getTradesNum());
						//该等级没有配置金额
					} else if (res.getOrderPay() == null && res.getTradesNum().compareTo(custLevel.getTradesNum()) == 1) {
						custLevel.setVipLevelCode(res.getVipLevelCode());
						custLevel.setOrderPay(res.getOrderPay());
						custLevel.setTradesNum(res.getTradesNum());
					}
				}
				//根据金额计算等级
				//用户的金额，大于或等级该等级需要的金额，则获得该等级
				if (res.getOrderPay() != null && new BigDecimal(money).compareTo(res.getOrderPay()) != -1) {
					//如果循环对象的金额，大于已获得的等级的金额，则等级设为金额更大的（等级始终按金额更大的为准）
					if (res.getOrderPay() != null && res.getOrderPay().compareTo(custLevel.getOrderPay()) != -1) {
						custLevel.setVipLevelCode(res.getVipLevelCode());
						custLevel.setOrderPay(res.getOrderPay());
						custLevel.setTradesNum(res.getTradesNum());
					} 
				}
			}
			/*如果店铺没有设置会员等级，则直接返回null*/
		} else {
			return "";
		}
		
		return custLevel.getVipLevelCode();
	}
	
	@Override
	public PageResponseDTO<ShopVipRealResDTO> listShopVipReal(ShopVipRealSIDXReqDTO req) throws BusinessException {
		
		//入参校验
		if(req == null){
			throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
		}
		
		PageResponseDTO<ShopVipRealResDTO> res = new PageResponseDTO<ShopVipRealResDTO>();
		ShopVipRealSIDXCriteria sidxCriteria = new ShopVipRealSIDXCriteria();
		ShopVipRealSIDXCriteria.Criteria sql = sidxCriteria.createCriteria();
		
		//会员id
		if(req.getStaffId()!=null){
			sql.andStaffIdEqualTo(req.getStaffId());
		}
		//模糊查shop列表
		if(StringUtil.isNotBlank(req.getShopName())){
			List<Long> shopIds = new ArrayList<Long>();//店铺集
			ShopCollectReqDTO shopCollectReqDTO = new ShopCollectReqDTO();
			shopCollectReqDTO.setShopName(req.getShopName());
			shopCollectReqDTO.setPageNo(0);//不分页查询
			PageResponseDTO<ShopInfoResDTO> shopList = shopMgrSV.listShop(shopCollectReqDTO, null);//查到店铺
			if(shopList.getCount()>0){
				for (ShopInfoResDTO shopInfoResDTO : shopList.getResult()) {
					shopIds.add(shopInfoResDTO.getId());
				}
				sql.andShopIdIn(shopIds);
			}
		}
		//店铺id
		if(req.getShopId()!=null){
			sql.andShopIdEqualTo(req.getShopId());
		}
		
		sidxCriteria.setLimitClauseCount(req.getPageSize());
		sidxCriteria.setLimitClauseStart(req.getStartRowIndex());
		
		res = super.queryByPagination(req, sidxCriteria, true, new PaginationCallback<ShopVipRealSIDX, ShopVipRealResDTO>() {

			@Override
			public List<ShopVipRealSIDX> queryDB(BaseCriteria criteria) {
				return realSIDXMapper.selectByExample((ShopVipRealSIDXCriteria) criteria);
			}

			@Override
			public long queryTotal(BaseCriteria criteria) {
				return realSIDXMapper.countByExample((ShopVipRealSIDXCriteria) criteria);
			}

			@Override
			public ShopVipRealResDTO warpReturnObject(ShopVipRealSIDX t) {
				ShopVipRealResDTO dto = null;
				ShopVipRealReqDTO findDTO = new ShopVipRealReqDTO();
				findDTO.setId(t.getId());
				findDTO.setShopId(t.getShopId());
				//查ShopVipReal详细
				PageResponseDTO<ShopVipRealResDTO> list = listShopVipReal(findDTO);
				dto = list.getResult().get(0);
				//补充信息：店铺会员等级
				ShopVipLevelReqDTO levelReq = new ShopVipLevelReqDTO();
				levelReq.setShopId(dto.getShopId());
				levelReq.setVipLevelCode(dto.getCustLevelCode());//仅该对象中custLevelCode与vipLevelCode一致
				levelReq.setPageNo(0);//查全集
				PageResponseDTO<ShopVipLevelResDTO> listLevel = listShopVipLevel(levelReq);
				if(listLevel.getCount()>0){
					ShopVipLevelResDTO level = listLevel.getResult().get(0);//取第一条记录。符合消费所对应等级
					dto.setVipLevelCode(level.getVipLevelCode());//店铺会员等级编码
					dto.setVipLevelName(level.getVipLevelName());//店铺会员等级名称
					dto.setDiscount(level.getDiscount());//店铺会员等级规则
				}
				//补充信息：店铺名称
				ShopInfo si = shopMgrSV.findShopByShopID(dto.getShopId());
				dto.setShopName(si.getShopName());//店铺名
				if (StringUtil.isNotBlank(si.getLogoPath())) {
					dto.setLogoPath(si.getLogoPath());
                } else {
                	dto.setLogoPath(ImageUtil.getDefaultImageId());
                }
				return dto;
			}
			
		});
		
		return res;
	}

   
}

