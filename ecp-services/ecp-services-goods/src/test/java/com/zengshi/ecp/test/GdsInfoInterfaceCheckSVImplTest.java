package com.zengshi.ecp.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.goods.dao.mapper.busi.GdsGds2CatgCatgIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsGds2CatgMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsInfoMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsInfoShopIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsInterfaceGdsGidxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsInterfaceGdsMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSku2PriceMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSkuInfoGdsIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSkuInfoMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSkuInfoShopIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockCompanyInfoIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockCompanyRepIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockInfoMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockRepInfoIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.StockShopInfoIdxMapper;
import com.zengshi.ecp.goods.dao.model.GdsGds2CatgCatgIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsGds2CatgCriteria;
import com.zengshi.ecp.goods.dao.model.GdsInfoCriteria;
import com.zengshi.ecp.goods.dao.model.GdsInfoShopIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsInterfaceGdsCriteria;
import com.zengshi.ecp.goods.dao.model.GdsInterfaceGdsGidxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsSku2PriceCriteria;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoCriteria;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoGdsIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoShopIdxCriteria;
import com.zengshi.ecp.goods.dao.model.StockCompanyInfoIdxCriteria;
import com.zengshi.ecp.goods.dao.model.StockInfoCriteria;
import com.zengshi.ecp.goods.dao.model.StockRepInfoIdxCriteria;
import com.zengshi.ecp.goods.dao.model.StockShopInfoIdxCriteria;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.LogUtil;

public class GdsInfoInterfaceCheckSVImplTest extends EcpServicesTest {

    /*-------------------商品相关----------------------*/
    @Resource
    GdsInfoMapper gdsInfoMapper;

    @Resource
    GdsInfoShopIdxMapper gdsInfoShopIdxMapper;

    @Resource
    GdsGds2CatgMapper gdsGds2CatgMapper;

    @Resource
    GdsGds2CatgCatgIdxMapper gdsGds2CatgCatgIdxMapper;

    /*-------------------单品相关----------------------*/
    @Resource
    GdsSkuInfoMapper gdsSkuInfoMapper;

    @Resource
    GdsSkuInfoShopIdxMapper gdsSkuInfoShopIdxMapper;

    @Resource
    GdsSkuInfoGdsIdxMapper gdsSkuInfoGdsIdxMapper;

    @Resource
    GdsSku2PriceMapper gdsSku2PriceMapper;

    /*-------------------库存相关----------------------*/
    @Resource
    StockInfoMapper stockInfoMapper;

    @Resource
    StockShopInfoIdxMapper stockShopInfoIdx;

    @Resource
    StockCompanyInfoIdxMapper stockCompanyInfoIdx;

    @Resource
    StockCompanyRepIdxMapper stockCompanyRepIdx;

    @Resource
    StockRepInfoIdxMapper stockRepInfoIdxMapper;

    /*-------------------数据导入关系相关----------------------*/
    @Resource
    GdsInterfaceGdsMapper gdsInterfaceGdsMapper;
    
    @Resource
    GdsInterfaceGdsGidxMapper gdsInterfaceGdsGidxMapper;

    private static String MODULE = "数据完整性检查";

    @Test
    public void checkGdsInfoData() {

        /*-------------------商品相关----------------------*/
        Long gdsInfo = gdsInfoMapper.countByExample(new GdsInfoCriteria());
        Long gdsInfoShopIdx = gdsInfoShopIdxMapper.countByExample(new GdsInfoShopIdxCriteria());
        GdsGds2CatgCriteria catgCatgIdxCriteria = new GdsGds2CatgCriteria();
        catgCatgIdxCriteria.createCriteria().andStatusEqualTo("1");
        Long gdsGds2Catg = gdsGds2CatgMapper.countByExample(catgCatgIdxCriteria);
        Long gdsGds2CatgCatg = gdsGds2CatgCatgIdxMapper
                .countByExample(new GdsGds2CatgCatgIdxCriteria());

        /*-------------------单品相关----------------------*/
        Long skuInfo = gdsSkuInfoMapper.countByExample(new GdsSkuInfoCriteria());
        Long skuInfoShopIdx = gdsSkuInfoShopIdxMapper
                .countByExample(new GdsSkuInfoShopIdxCriteria());
        Long skuInfoGdsIdx = gdsSkuInfoGdsIdxMapper.countByExample(new GdsSkuInfoGdsIdxCriteria());
        GdsSku2PriceCriteria sku2PriceCriteria = new GdsSku2PriceCriteria();
        sku2PriceCriteria.createCriteria().andStatusEqualTo("1");
        Long gdsSku2Price = gdsSku2PriceMapper.countByExample(sku2PriceCriteria);

        /*-------------------库存相关----------------------*/
        Long stockInfo = stockInfoMapper.countByExample(new StockInfoCriteria());
        Long stockShopInfoIdxC = stockShopInfoIdx.countByExample(new StockShopInfoIdxCriteria());
        Long stockCompanyInfoIdxC = stockCompanyInfoIdx
                .countByExample(new StockCompanyInfoIdxCriteria());
        Long stockRepInfoIdxC = stockRepInfoIdxMapper.countByExample(new StockRepInfoIdxCriteria());

        /*-------------------数据导入关系相关----------------------*/
        GdsInterfaceGdsCriteria gdsInterfaceGdsCriteria = new GdsInterfaceGdsCriteria();
//        gdsInterfaceGdsCriteria.createCriteria().andStatusEqualTo("1");
        Long gdsInterfaceGds = gdsInterfaceGdsMapper.countByExample(gdsInterfaceGdsCriteria);
        
        Long gdsInterfaceGdsGidx = gdsInterfaceGdsGidxMapper.countByExample(new GdsInterfaceGdsGidxCriteria());

        LogUtil.info(
                MODULE,
                "======================================================================================================");

        LogUtil.info(MODULE, ">>>>>>>>>>>>>>>>>>>商品信息gdsInfo:" + gdsInfo);
        LogUtil.info(MODULE, ">>>>>>>>>>>>>>>>>>>商品店铺索引gdsInfoShopIdx:" + gdsInfoShopIdx);
        LogUtil.info(MODULE, ">>>>>>>>>>>>>>>>>>>商品分类关系gdsGds2Catg:" + gdsGds2Catg);
        LogUtil.info(MODULE, ">>>>>>>>>>>>>>>>>>>商品分类关系分类索引gdsGds2CatgCatg:" + gdsGds2CatgCatg);

        LogUtil.info(MODULE, ">>>>>>>>>>>>>>>>>>>单品信息skuInfo:" + skuInfo);
        LogUtil.info(MODULE, ">>>>>>>>>>>>>>>>>>>单品店铺索引skuInfoShopIdx:" + skuInfoShopIdx);
        LogUtil.info(MODULE, ">>>>>>>>>>>>>>>>>>>单品商品索引skuInfoGdsIdx:" + skuInfoGdsIdx);
        LogUtil.info(MODULE, ">>>>>>>>>>>>>>>>>>>商品价格关系gdsSku2Price:" + gdsSku2Price);

        LogUtil.info(MODULE, ">>>>>>>>>>>>>>>>>>>库存信息stockInfo:" + stockInfo);
        LogUtil.info(MODULE, ">>>>>>>>>>>>>>>>>>>库存信息店铺索引stockShopInfoIdx:" + stockShopInfoIdxC);
        LogUtil.info(MODULE, ">>>>>>>>>>>>>>>>>>>库存信息公司索引stockCompanyInfoIdx:"
                + stockCompanyInfoIdxC);
        LogUtil.info(MODULE, ">>>>>>>>>>>>>>>>>>>库存信息仓库索引stockRepInfoIdx:" + stockRepInfoIdxC);

        LogUtil.info(MODULE, ">>>>>>>>>>>>>>>>>>>第三方系统商品和ECP商品映射数(第三方系统商品编码维度)gdsInterfaceGds:"
                + gdsInterfaceGds);
        LogUtil.info(MODULE, ">>>>>>>>>>>>>>>>>>>第三方系统商品和ECP商品映射数(亚信侧商品编码维度)gdsInterfaceGdsGidx:"
                + gdsInterfaceGdsGidx);

        LogUtil.info(
                MODULE,
                "======================================================================================================");

    }

}
