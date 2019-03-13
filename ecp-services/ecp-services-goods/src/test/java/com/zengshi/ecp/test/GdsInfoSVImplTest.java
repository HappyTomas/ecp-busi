package com.zengshi.ecp.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.general.solr.dto.SearchDataPageReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataPageRespDTO;
import com.zengshi.ecp.general.solr.interfaces.ISearchDataSupport;
import com.zengshi.ecp.goods.dao.model.GdsGds2Catg;
import com.zengshi.ecp.goods.dao.model.GdsMedia;
import com.zengshi.ecp.goods.dao.model.GdsSku2Prop;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoAddReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2MediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2MediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.CartItemPriceInfo;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.price.GdsPriceTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsMediaSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoManageSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoManageSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoIDXSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2CatgSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2PropSV;
import com.zengshi.ecp.goods.service.busi.interfaces.price.IGdsPriceSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropSV;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;

public class GdsInfoSVImplTest extends EcpServicesTest {

	@Autowired(required = false)
    private IGdsInfoManageSV gdsInfoManageSV;

    @Resource
    private IGdsInfoQuerySV gdsInfoQuerySV;

    @Resource
    private IGdsInfo2CatgSV gdsInfo2CatgSV;

    @Resource
    private IGdsSkuInfoManageSV gdsSkuInfoManageSV;

    @Resource
    private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;

    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;

    @Resource
    private IGdsPropSV gdsPropSV;

    @Autowired(required = false)
    private IGdsPriceSV gdsPriceSV;

    @Resource
    private IGdsCategorySV gdsCategorySV;

    @Resource
    private IGdsMediaSV gdsMediaSV;

    @Resource
    private IGdsInfoIDXSV gdsInfoIDXSV;

    @Test
    public void repairGds2Catg() {
        boolean flag = true;
        int i = 1;
        while (flag) {
            // LogUtil.info("","----------------第" + i + "次:");
            // List<Long> ids=new ArrayList<Long>();
            // ids.add(1l);
            // ids.add(2l);

            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
            gdsInfoReqDTO.setPageNo(i);
            gdsInfoReqDTO.setPageSize(200);
            // gdsInfoReqDTO.setCatalogIds(ids);
            PageResponseDTO<GdsInfoRespDTO> gdsInfos = gdsInfoQuerySV.queryGdsInfoListPageALLDB(gdsInfoReqDTO);
            List<GdsInfoRespDTO> dataList = gdsInfos.getResult();

            File file = new File("C:\\dev\\repair.logs");
            try {
                file.createNewFile();
            } catch (IOException e1) {
                LogUtil.info("", "", e1);
            }
            if (CollectionUtils.isNotEmpty(dataList)) {
                for (GdsInfoRespDTO gdsInfoRespDTO : dataList) {
                    // 更新商品分类关系表
                    GdsInfoReqDTO req = new GdsInfoReqDTO();
                    req.setId(gdsInfoRespDTO.getId());
                    GdsInfoRespDTO resp = gdsInfoQuerySV.queryGdsInfoByOption(req);
                    List<GdsGds2Catg> catgResp = gdsInfo2CatgSV.queryGds2CatgsModelByGdsId(gdsInfoRespDTO.getId());
                    List<GdsGds2CatgReqDTO> catgReq = GdsUtils.doConvert(catgResp, GdsGds2CatgReqDTO.class);
                    if (CollectionUtils.isNotEmpty(catgReq)) {
                        for (GdsGds2CatgReqDTO gds2CatgReqDTO : catgReq) {
                            gds2CatgReqDTO.setGdsStatus(resp.getGdsStatus());
                            gds2CatgReqDTO.setGdsId(gdsInfoRespDTO.getId());
                            gds2CatgReqDTO.setStaff(GdsUtils.getStaff(178L));

                            GdsGds2CatgReqDTO query = new GdsGds2CatgReqDTO();
                            query.setGdsStatus(resp.getGdsStatus());
                            query.setGdsId(gdsInfoRespDTO.getId());

                            gdsInfo2CatgSV.updateGds2Catg(gds2CatgReqDTO, query);
                        }
                    }
                    // 更新属性索引表
                    gdsInfoIDXSV.editGdsInfoIDX(null, catgReq, null);

                    try {
                        FileUtils.writeStringToFile(file, "正在修复id:" + gdsInfoRespDTO.getId() + "\n", true);
                    } catch (IOException e) {
                        LogUtil.info("", "", e);
                    }

                }
            } else {
                flag = false;
            }
            i++;
        }

    }

    @Test
    public void testQueryGdsInfoPage() {
        Long total = 0l;
        // for (int j = 0; j < 30; j++) {
        // for (int i = 1; i < 21; i++) {
        Long now = System.currentTimeMillis();
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setShopId(100l);
        gdsInfoReqDTO.setPageSize(30);
        gdsInfoReqDTO.setPageNo(1);
        gdsInfoReqDTO.setGdsQueryOptions(new GdsOption.GdsQueryOption[] { GdsQueryOption.MAINPIC,
                // GdsQueryOption.BASIC,
                GdsQueryOption.MAINPIC });
        PageResponseDTO<GdsInfoRespDTO> result = gdsInfoQuerySV.queryGdsInfoListPage(gdsInfoReqDTO);
        long last = System.currentTimeMillis() - now;
        total = total + last;
        LogUtil.info("", "查询耗时" + last + "ms");
        // }
        // }
        LogUtil.info("", "总共耗时" + total + "ms");
        LogUtil.info("", "平均耗时" + total / 20 + "ms");

    }

    @Resource
    ISearchDataSupport gdsSearchDataListSupportRSV;

    @Test
    public void testQueryGdsInfoSearch() {

        boolean flag = true;
        int i = 0;
        Map<String, Integer> maps = new HashMap<String, Integer>();
        // while (flag) {

        LogUtil.info("", "----------------第" + i + "次:");
        SearchDataPageReqDTO searchDataPageReqDTO = new SearchDataPageReqDTO();
        searchDataPageReqDTO.setPageNo(i);
        searchDataPageReqDTO.setPageSize(400);
        SearchDataPageRespDTO search = gdsSearchDataListSupportRSV.getDataPage(searchDataPageReqDTO);
        List<Map<String, Object>> dataList = search.getDataList();
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (Map<String, Object> map : dataList) {
                String id = ((Long) map.get("id")).toString();
                if (!maps.containsKey(id)) {
                    maps.put(id, 1);
                } else {
                    Integer count = maps.get(id);
                    maps.put(id, count + 1);
                }
            }
        } else {
            flag = false;
        }
        // }

        Set<String> keys = maps.keySet();
        File file = new File("C:\\abc.txt");
        try {
            file.createNewFile();
        } catch (IOException e1) {
            LogUtil.info("", "", e1);
        }
        for (String key : keys) {
            int count = maps.get(key);
            if (count == 1) {
                String content = "1次" + key;
                try {
                    FileUtils.write(file, "UTF-8", content, true);
                } catch (IOException e) {
                    LogUtil.info("", "", e);
                }
            } else {
                String content = count + "次-----------" + key;
                try {
                    FileUtils.write(file, "UTF-8", content, true);
                } catch (IOException e) {
                    LogUtil.info("", "", e);
                }
            }

        }

    }

    @Test
    public void testCaculate() {
        ROrdCartsCommRequest reqDto = new ROrdCartsCommRequest();
        reqDto.setStaff(getBaseStaff());
        reqDto.setStaffId(getBaseStaff().getId());

        List<ROrdCartItemCommRequest> arr = new ArrayList<ROrdCartItemCommRequest>();
        for (long i = 0; i < 2; i++) {
            ROrdCartItemCommRequest rOrdCartItemChkRequest = new ROrdCartItemCommRequest();
            rOrdCartItemChkRequest.setId(i);
            rOrdCartItemChkRequest.setGdsId(1008609528l);
            rOrdCartItemChkRequest.setSkuId(1008617993l);
            rOrdCartItemChkRequest.setShopId(35l);
            rOrdCartItemChkRequest.setOrderAmount(1l);
            arr.add(rOrdCartItemChkRequest);
        }

        for (long i = 0; i < 4; i++) {
            ROrdCartItemCommRequest rOrdCartItemChkRequest = new ROrdCartItemCommRequest();
            rOrdCartItemChkRequest.setId(i + 6);
            rOrdCartItemChkRequest.setGdsId(1008609516l);
            if (i % 2 == 0) {
                rOrdCartItemChkRequest.setSkuId(1008617968l);
            } else {
                rOrdCartItemChkRequest.setSkuId(1008617972l);

            }
            rOrdCartItemChkRequest.setShopId(35l);
            rOrdCartItemChkRequest.setOrderAmount(3l);
            arr.add(rOrdCartItemChkRequest);
        }

        List<ROrdCartCommRequest> ordCartsCommList = new ArrayList<ROrdCartCommRequest>();
        ROrdCartCommRequest request = new ROrdCartCommRequest();
        request.setOrdCartItemCommList(arr);
        ordCartsCommList.add(request);

        reqDto.setOrdCartsCommList(ordCartsCommList);
        Map<Long, CartItemPriceInfo> maps = gdsPriceSV.caculatePrice(reqDto);
        // Set<Long> keys=maps.keySet();
        // for (Long key : keys) {
        // System.out.print(maps.get(key).getItemId());
        // System.out.print("---------");
        // System.out.print(maps.get(key).getGdsId());
        // System.out.print("---------");
        // System.out.print(maps.get(key).getSkuId());
        // System.out.print("---------");
        // System.out.print(maps.get(key).getBuyPrice());
        // System.out.print("---------");
        // System.out.print(maps.get(key).getBasePrice());
        // LogUtil.info("",);
        // }
    }

    @Test
    public void testQueryGdsInfoDetail() {

        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        // gdsInfoReqDTO.setShopId(1234l);
        // gdsInfoReqDTO.setCompanyId(8L);
        gdsInfoReqDTO.setId(37345l);
        // gdsInfoReqDTO.setSkuId(7455222l);
        gdsInfoReqDTO.setGdsQueryOptions(new GdsOption.GdsQueryOption[] { GdsQueryOption.BASIC, GdsQueryOption.MAINPIC, GdsQueryOption.PRICE, GdsQueryOption.PROP });
        gdsInfoReqDTO.setSkuQuerys(new GdsOption.SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.MAINPIC, SkuQueryOption.PROP, SkuQueryOption.PRICE });
        GdsInfoDetailRespDTO resp = gdsInfoQuerySV.queryGdsInfoDetail(gdsInfoReqDTO);
        LogUtil.info("", (String) JSON.toJSON(resp));
    }

    @Test
    public void testQuerySkuInfoPage() {
        Long total = 0l;
        Long now = System.currentTimeMillis();
        GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
        gdsSkuInfoReqDTO.setShopId(1234l);
        gdsSkuInfoReqDTO.setPageSize(30);
        gdsSkuInfoReqDTO.setPageNo(1);
        PageResponseDTO<GdsSkuInfoRespDTO> result = gdsSkuInfoQueryRSV.queryGdsSkuInfoListPage(gdsSkuInfoReqDTO);
        long last = System.currentTimeMillis() - now;
        total = total + last;
        LogUtil.info("", "总共" + result.getCount() + "条数据");
        LogUtil.info("", "当前有" + result.getResult().size() + "条数据");
        LogUtil.info("", "总共耗时" + total + "ms");
    }

    @Test
    public void testSaveGdsMedia() {
        for (int i = 0; i < 500; i++) {
            GdsMedia gdsMedia = new GdsMedia();
            gdsMedia.setCreateStaff(1l);
            gdsMedia.setCreateTime(DateUtil.getSysDate());
            gdsMedia.setUpdateStaff(1l);
            gdsMedia.setUpdateTime(DateUtil.getSysDate());
            gdsMedia.setStatus("1");
            gdsMedia.setShopId(1234l);
            gdsMedia.setMediaLibId(1234L);
            gdsMedia.setMediaType("1");
            gdsMedia.setMediaName("测试视频" + i + ".avi");
            gdsMedia.setMediaUuid(StringUtil.getRandomString(16));
            gdsMedia.setPicCatgCode("1");
            gdsMedia.setSortNo(i);
            gdsMediaSV.saveGdsMedia(gdsMedia);
        }
    }

    @Test
    public void testQueryGdsMedia() {
        GdsMediaReqDTO gdsMediaReqDTO = new GdsMediaReqDTO();
        gdsMediaReqDTO.setShopId(1234l);
        gdsMediaReqDTO.setMediaType("2");
        gdsMediaReqDTO.setPageSize(30);
        PageResponseDTO<GdsMediaRespDTO> medias = gdsMediaSV.queryGdsMediaListPage(gdsMediaReqDTO);
        LogUtil.info("", medias.getResult().size() + "");
    }

    @Test
    public void testSearchData() {

        long dbCount = 0l;
        Map<String, String> idCountMap = new HashMap<String, String>();
        boolean flag = true;
        int pageNo = 0;
        while (flag) {
            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
            gdsInfoReqDTO.setPageNo(++pageNo);
            gdsInfoReqDTO.setPageSize(50);
            List<Long> ids = new ArrayList<Long>();
            ids.add(1l);
            gdsInfoReqDTO.setCatalogIds(ids);

            PageResponseDTO<GdsInfoRespDTO> gdsInfos = gdsInfoQuerySV.queryGdsInfoListPageALLDB(gdsInfoReqDTO);
            if (CollectionUtils.isNotEmpty(gdsInfos.getResult())) {
                dbCount += gdsInfos.getResult().size();

                for (GdsInfoRespDTO gdsInfoRespDTO : gdsInfos.getResult()) {
                    idCountMap.put(gdsInfoRespDTO.getId() + "", gdsInfoRespDTO.getId() + "");
                }

            } else {
                flag = false;
            }

        }

        LogUtil.info("", "=========================框架分页全量查询数据总量:" + dbCount);
        LogUtil.info("", "=========================商品编码不重复的数据总量:" + idCountMap.size());

    }

    @Test
    public void testQuerySkuInfo() {
        GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
        skuInfoReqDTO.setId(128058L);

        SkuQueryOption[] query = { SkuQueryOption.BASIC, SkuQueryOption.STOCK, SkuQueryOption.SHOWPRICE };
        skuInfoReqDTO.setSkuQuery(query);
        GdsSkuInfoRespDTO skuInfoRespDTO = gdsSkuInfoQuerySV.querySkuInfoByOptions(skuInfoReqDTO, query);
//        ObjectOutputStream oo;
//        try {
//            oo = new ObjectOutputStream(new FileOutputStream(new File("E:/Person.txt")));
//            oo.writeObject(skuInfoRespDTO);
//        } catch (FileNotFoundException e) {
//            LogUtil.info("", "", e);
//        } catch (IOException e) {
//            LogUtil.info("", "", e);
//        }
        LogUtil.info("", "success");
        LogUtil.info("", JSON.toJSONString(skuInfoRespDTO));

    }

    @Test
    public void testQueryGdsInfo() {
        GdsInfoReqDTO gdsInfo = new GdsInfoReqDTO();
        gdsInfo.setId(37235l);

        GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQuerySV.queryGdsInfoByOption(gdsInfo, new GdsOption.GdsQueryOption[] { GdsOption.GdsQueryOption.ALL },
                new GdsOption.SkuQueryOption[] { GdsOption.SkuQueryOption.ALL });

        LogUtil.info("", "success");
        LogUtil.info("", JSON.toJSONString(gdsInfoRespDTO));

    }

    @Test
    public void testQuerySkuInfoByProp() {

        List<GdsSku2PropReqDTO> props = new ArrayList<GdsSku2PropReqDTO>();

        GdsSku2PropReqDTO propReqDTO = new GdsSku2PropReqDTO();
        propReqDTO.setPropId(3l);
        propReqDTO.setPropValue("电信4G");
        props.add(propReqDTO);

        GdsSku2PropReqDTO propReqDTO1 = new GdsSku2PropReqDTO();
        propReqDTO1.setPropId(2l);
        propReqDTO1.setPropValue("64G");
        props.add(propReqDTO1);

        GdsSku2PropReqDTO propReqDTO2 = new GdsSku2PropReqDTO();
        propReqDTO2.setPropId(22l);
        propReqDTO2.setPropValue("是啥");
        props.add(propReqDTO2);

        List<GdsSku2Prop> result = gdsSkuInfoQuerySV.querySkuInfoByProp(props, 37249l, true);

        LogUtil.info("", "success");

    }

    @Test
    public void testDelGdlInfo() {
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setId(1008609224l);
        gdsInfoReqDTO.setShopId(1234l);
        gdsInfoReqDTO.setStaff(getBaseStaff());
        gdsInfoManageSV.deleteGdsInfo(gdsInfoReqDTO);
    }

    @Test
    public void testDoGdsShelves() {
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setId(290l);
        gdsInfoReqDTO.setShopId(1234l);
        gdsInfoReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
        gdsInfoManageSV.executeGdsShelves(gdsInfoReqDTO);
    }

    @Test
    public void testDoSkuShelves() {
        GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
        gdsSkuInfoReqDTO.setId(555l);
        gdsSkuInfoReqDTO.setShopId(1234l);
        gdsSkuInfoReqDTO.setGdsId(291l);
        gdsSkuInfoReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES);
        gdsSkuInfoManageSV.executeSkuShelves(gdsSkuInfoReqDTO);
    }

    @Test
    public void testQueryAllPriceType() {

        List<GdsPriceTypeRespDTO> values = gdsPriceSV.queryAllPriceType();
        for (GdsPriceTypeRespDTO gdsPriceTypeRespDTO : values) {
            LogUtil.info("", gdsPriceTypeRespDTO.getPriceTypeName());
        }
    }

    @Test
    public void testEditGdsInfo() {

        // 构造商品信息
        GdsInfoAddReqDTO gdsInfoReqDTO = new GdsInfoAddReqDTO();
        GdsInfoReqDTO gdsInfo = new GdsInfoReqDTO();
        gdsInfoReqDTO.setGdsInfoReqDTO(gdsInfo);
        gdsInfo.setStaff(getBaseStaff());
        gdsInfoReqDTO.getGdsInfoReqDTO().setId(303l);
        gdsInfoReqDTO.getGdsInfoReqDTO().setGdsName("测试商品编辑后");
        gdsInfoReqDTO.getGdsInfoReqDTO().setShopId(1234L);
        gdsInfoReqDTO.getGdsInfoReqDTO().setGdsDesc("abcdefg编辑后");
        gdsInfoReqDTO.getGdsInfoReqDTO().setGdsLabel("速度快，相应快编辑后");
        gdsInfoReqDTO.getGdsInfoReqDTO().setGdsSubHead("副标题编辑后");
        gdsInfoReqDTO.getGdsInfoReqDTO().setGuidePrice(12888l);

        // 构造单品列表
        List<GdsSkuInfoReqDTO> skuInfoReqDTOs = new ArrayList<GdsSkuInfoReqDTO>();
        createSkuInfoList(skuInfoReqDTOs, gdsInfoReqDTO);
        skuInfoReqDTOs.get(0).setId(579l);
        skuInfoReqDTOs.get(1).setId(580l);
        gdsInfoReqDTO.setSkuInfoReqDTOs(skuInfoReqDTOs);

        // 构造商品分类信息
        List<GdsGds2CatgReqDTO> gds2CatgReqDTOs = new ArrayList<GdsGds2CatgReqDTO>();

        GdsGds2CatgReqDTO gds2CatgReqDTO = new GdsGds2CatgReqDTO();
        gds2CatgReqDTO.setGds2catgType("1");
        gds2CatgReqDTO.setCatgCode("111");
        gds2CatgReqDTOs.add(gds2CatgReqDTO);

        GdsGds2CatgReqDTO gds2CatgReqDTO2 = new GdsGds2CatgReqDTO();
        gds2CatgReqDTO2.setGds2catgType("2");
        gds2CatgReqDTO2.setCatgCode("112");
        gds2CatgReqDTOs.add(gds2CatgReqDTO2);

        GdsGds2CatgReqDTO gds2CatgReqDTO1 = new GdsGds2CatgReqDTO();
        gds2CatgReqDTO1.setGds2catgType("2");
        gds2CatgReqDTO1.setCatgCode("411");
        gds2CatgReqDTOs.add(gds2CatgReqDTO1);

        GdsGds2CatgReqDTO gds2CatgReqDTO4 = new GdsGds2CatgReqDTO();
        gds2CatgReqDTO4.setGds2catgType("2");
        gds2CatgReqDTO4.setCatgCode("412");
        gds2CatgReqDTOs.add(gds2CatgReqDTO4);

        gdsInfoReqDTO.setGds2CatgReqDTOs(gds2CatgReqDTOs);

        // 构造商品媒体信息
        List<GdsGds2MediaReqDTO> gds2MediaReqDTOs = new ArrayList<GdsGds2MediaReqDTO>();

        GdsGds2MediaReqDTO sku2MediaReqDTO = new GdsGds2MediaReqDTO();
        sku2MediaReqDTO.setMediaUuid("abcsds1");
        sku2MediaReqDTO.setMediaRtype("2");
        sku2MediaReqDTO.setMediaType("1");
        sku2MediaReqDTO.setSortNo(1);
        gds2MediaReqDTOs.add(sku2MediaReqDTO);

        GdsGds2MediaReqDTO sku2MediaReqDTO1 = new GdsGds2MediaReqDTO();
        sku2MediaReqDTO1.setMediaUuid("abcsds2");
        sku2MediaReqDTO1.setMediaRtype("2");
        sku2MediaReqDTO1.setMediaType("1");
        sku2MediaReqDTO1.setSortNo(2);
        gds2MediaReqDTOs.add(sku2MediaReqDTO1);

        gdsInfoReqDTO.setGds2MediaReqDTOs(gds2MediaReqDTOs);

        // 构造商品属性信息
        List<GdsGds2PropReqDTO> gds2PropReqDTOs = new ArrayList<GdsGds2PropReqDTO>();
        createGdsProps(gds2PropReqDTOs);
        gdsInfoReqDTO.setGds2PropReqDTOs(gds2PropReqDTOs);
        gdsInfoManageSV.editGdsInfoAndReference(gdsInfoReqDTO);

    }

    @Test
    public void testSaveGdsInfo() throws InterruptedException {

        Long total = 0L;
        for (int i = 1; i < 5000; i++) {
            Long begin = System.currentTimeMillis();
            // 构造商品信息
            GdsInfoAddReqDTO gdsInfoReqDTO = new GdsInfoAddReqDTO();
            GdsInfoReqDTO gdsInfo = new GdsInfoReqDTO();
            gdsInfoReqDTO.setGdsInfoReqDTO(gdsInfo);
            gdsInfoReqDTO.setStaff(getBaseStaff());
            gdsInfoReqDTO.getGdsInfoReqDTO().setGdsName(" 新版本商品" + StringUtil.getRandomString(32) + "");
            gdsInfoReqDTO.getGdsInfoReqDTO().setShopId(35l);
            gdsInfoReqDTO.setCompanyId(16l);
            gdsInfoReqDTO.getGdsInfoReqDTO().setCompanyId(16l);
            gdsInfoReqDTO.getGdsInfoReqDTO().setGdsTypeCode("PRODUCT_ORDINARY");
            gdsInfoReqDTO.getGdsInfoReqDTO().setGdsDesc(StringUtil.getRandomString(24));
            gdsInfoReqDTO.getGdsInfoReqDTO().setGdsLabel("速度快，相应快");
            gdsInfoReqDTO.getGdsInfoReqDTO().setGdsSubHead("新版本-副标题" + StringUtil.getRandomString(32) + "");
            gdsInfoReqDTO.getGdsInfoReqDTO().setGuidePrice(12888l);

            // 构造单品列表
            List<GdsSkuInfoReqDTO> skuInfoReqDTOs = new ArrayList<GdsSkuInfoReqDTO>();
            createSkuInfoList(skuInfoReqDTOs, gdsInfoReqDTO);
            gdsInfoReqDTO.setSkuInfoReqDTOs(skuInfoReqDTOs);

            // 构造商品分类信息
            List<GdsGds2CatgReqDTO> gds2CatgReqDTOs = new ArrayList<GdsGds2CatgReqDTO>();

            GdsGds2CatgReqDTO gds2CatgReqDTO = new GdsGds2CatgReqDTO();
            gds2CatgReqDTO.setGds2catgType("1");
            gds2CatgReqDTO.setCatgCode("1115");
            gds2CatgReqDTO.setCatgType("1");
            gds2CatgReqDTOs.add(gds2CatgReqDTO);

            GdsGds2CatgReqDTO gds2CatgReqDTO2 = new GdsGds2CatgReqDTO();
            gds2CatgReqDTO2.setGds2catgType("2");
            gds2CatgReqDTO2.setCatgCode("2700");
            gds2CatgReqDTO2.setCatgType("1");
            gds2CatgReqDTOs.add(gds2CatgReqDTO2);

            GdsGds2CatgReqDTO gds2CatgReqDTO1 = new GdsGds2CatgReqDTO();
            gds2CatgReqDTO1.setGds2catgType("2");
            gds2CatgReqDTO1.setCatgCode("2701");
            gds2CatgReqDTO1.setCatgType("2");
            gds2CatgReqDTOs.add(gds2CatgReqDTO1);

            GdsGds2CatgReqDTO gds2CatgReqDTO4 = new GdsGds2CatgReqDTO();
            gds2CatgReqDTO4.setGds2catgType("2");
            gds2CatgReqDTO4.setCatgCode("2702");
            gds2CatgReqDTO4.setCatgType("2");
            gds2CatgReqDTOs.add(gds2CatgReqDTO4);

            gdsInfoReqDTO.setGds2CatgReqDTOs(gds2CatgReqDTOs);

            // 构造商品媒体信息
            List<GdsGds2MediaReqDTO> gds2MediaReqDTOs = new ArrayList<GdsGds2MediaReqDTO>();

            GdsGds2MediaReqDTO sku2MediaReqDTO = new GdsGds2MediaReqDTO();
            sku2MediaReqDTO.setMediaUuid("5646ef6ce4b0a5eabc828f86");
            sku2MediaReqDTO.setMediaRtype("2");
            sku2MediaReqDTO.setMediaType("1");
            sku2MediaReqDTO.setSortNo(1);
            gds2MediaReqDTOs.add(sku2MediaReqDTO);

            GdsGds2MediaReqDTO sku2MediaReqDTO1 = new GdsGds2MediaReqDTO();
            sku2MediaReqDTO1.setMediaUuid("564ace99e4b0a5eabc8290c5");
            sku2MediaReqDTO1.setMediaRtype("2");
            sku2MediaReqDTO1.setMediaType("1");
            sku2MediaReqDTO1.setSortNo(2);
            gds2MediaReqDTOs.add(sku2MediaReqDTO1);

            gdsInfoReqDTO.setGds2MediaReqDTOs(gds2MediaReqDTOs);

            // 构造商品属性信息
            List<GdsGds2PropReqDTO> gds2PropReqDTOs = new ArrayList<GdsGds2PropReqDTO>();
            createGdsProps(gds2PropReqDTOs);
            gdsInfoReqDTO.setGds2PropReqDTOs(gds2PropReqDTOs);

            GdsInfoRespDTO gdsinfo = gdsInfoManageSV.addGdsInfo(gdsInfoReqDTO);

            LogUtil.info("", "写入的商品编码为：" + gdsinfo.getId());
            Long last = System.currentTimeMillis() - begin;
            total = total + last;
            LogUtil.info("", "当前插入耗时：     " + last + "ms");
        }
        LogUtil.info("", "插入总共耗时：     " + total + "ms");
        LogUtil.info("", "插入平均耗时：     " + (total / 9000) + "ms");
    }

    @Test
    public void testSavePropAndCategory() {

        GdsPropReqDTO gdsPropReqDTO = new GdsPropReqDTO();
        gdsPropReqDTO.setPropType(GdsConstants.GdsProp.PROP_TYPE_1);
        gdsPropReqDTO.setPropValueType(GdsConstants.GdsProp.PROP_VALUE_TYPE_1);
        gdsPropReqDTO.setId(1L);
        gdsPropReqDTO.setPropName("颜色");
        List<GdsPropValueReqDTO> values = new ArrayList<GdsPropValueReqDTO>();

        GdsPropValueReqDTO value1 = new GdsPropValueReqDTO();
        value1.setId(1L);
        value1.setPropId(1L);
        value1.setPropValue("测试值1");
        values.add(value1);

        GdsPropValueReqDTO value2 = new GdsPropValueReqDTO();
        value2.setId(2L);
        value2.setPropId(1L);
        value2.setPropValue("测试值2");
        values.add(value2);

        gdsPropReqDTO.setPropValues(values);
        gdsPropSV.saveGdsProp(gdsPropReqDTO);

        GdsPropReqDTO gdsPropReqDTO1 = new GdsPropReqDTO();
        gdsPropReqDTO1.setPropType(GdsConstants.GdsProp.PROP_TYPE_1);
        gdsPropReqDTO1.setPropValueType(GdsConstants.GdsProp.PROP_VALUE_TYPE_1);
        gdsPropReqDTO1.setId(2L);
        gdsPropReqDTO1.setPropName("内存");
        List<GdsPropValueReqDTO> values1 = new ArrayList<GdsPropValueReqDTO>();

        GdsPropValueReqDTO value3 = new GdsPropValueReqDTO();
        value3.setId(11L);
        value3.setPropId(2L);
        value3.setPropValue("测试值11");
        values1.add(value3);

        GdsPropValueReqDTO value4 = new GdsPropValueReqDTO();
        value4.setId(22L);
        value4.setPropId(2L);
        value4.setPropValue("测试值22");
        values1.add(value4);

        gdsPropReqDTO1.setPropValues(values1);
        gdsPropSV.saveGdsProp(gdsPropReqDTO1);

        // GdsCategoryReqDTO categoryReqDTO=new GdsCategoryReqDTO();
        // categoryReqDTO.setCatgCode("1333");
        // categoryReqDTO.setCatgName("分类1333");
        // categoryReqDTO.setCatgParent("133");
        // categoryReqDTO.setCatgLevel((short)3);
        // categoryReqDTO.setCatgType("1");
        //
        // gdsCategorySV.saveGdsCategory(categoryReqDTO);
        //
        //
        // GdsCategoryReqDTO categoryReqDTO1=new GdsCategoryReqDTO();
        // categoryReqDTO1.setCatgCode("1222");
        // categoryReqDTO1.setCatgName("分类1222");
        // categoryReqDTO1.setCatgParent("133");
        // categoryReqDTO1.setCatgLevel((short)3);
        // categoryReqDTO1.setCatgType("1");
        // gdsCategorySV.saveGdsCategory(categoryReqDTO1);
        //
        //
        // GdsCategoryReqDTO categoryReqDTO2=new GdsCategoryReqDTO();
        // categoryReqDTO2.setCatgCode("133");
        // categoryReqDTO2.setCatgName("分类133");
        // categoryReqDTO2.setCatgParent("1");
        // categoryReqDTO2.setCatgLevel((short)2);
        // categoryReqDTO2.setCatgType("1");
        // gdsCategorySV.saveGdsCategory(categoryReqDTO2);
        //
        //
        // GdsCategoryReqDTO categoryReqDTO3=new GdsCategoryReqDTO();
        // categoryReqDTO3.setCatgCode("1");
        // categoryReqDTO3.setCatgName("分类1");
        // categoryReqDTO3.setCatgLevel((short)1);
        // categoryReqDTO3.setCatgType("1");
        // gdsCategorySV.saveGdsCategory(categoryReqDTO3);

    }

    /**
     * 
     * createGdsProps:(构造商品属性). <br/>
     * 
     * @author linwb3
     * @param gds2PropReqDTOs
     * @since JDK 1.6
     */
    private void createGdsProps(List<GdsGds2PropReqDTO> gds2PropReqDTOs) {
        GdsGds2PropReqDTO gdsSku2PropReqDTO = new GdsGds2PropReqDTO();
        gdsSku2PropReqDTO.setIfMust("1");
        gdsSku2PropReqDTO.setPropId(1L);
        gdsSku2PropReqDTO.setPropName("颜色");
        gdsSku2PropReqDTO.setPropType(GdsConstants.GdsProp.PROP_TYPE_1);
        gdsSku2PropReqDTO.setPropValue("白色");
        gdsSku2PropReqDTO.setPropValueId(1L);
        gdsSku2PropReqDTO.setPropValueType(GdsConstants.GdsProp.PROP_VALUE_TYPE_1);
        gds2PropReqDTOs.add(gdsSku2PropReqDTO);

        GdsGds2PropReqDTO gdsSku2PropReqDTO1 = new GdsGds2PropReqDTO();
        gdsSku2PropReqDTO1.setIfMust("1");
        gdsSku2PropReqDTO1.setPropId(1L);
        gdsSku2PropReqDTO1.setPropName("颜色");
        gdsSku2PropReqDTO1.setPropType(GdsConstants.GdsProp.PROP_TYPE_1);
        gdsSku2PropReqDTO1.setPropValue("黑色");
        gdsSku2PropReqDTO1.setPropValueId(2L);
        gdsSku2PropReqDTO1.setPropValueType(GdsConstants.GdsProp.PROP_VALUE_TYPE_1);
        gds2PropReqDTOs.add(gdsSku2PropReqDTO1);

        GdsGds2PropReqDTO gdsSku2PropReqDTO2 = new GdsGds2PropReqDTO();
        gdsSku2PropReqDTO2.setIfMust("1");
        gdsSku2PropReqDTO2.setPropId(2L);
        gdsSku2PropReqDTO2.setPropName("内存");
        gdsSku2PropReqDTO2.setPropType(GdsConstants.GdsProp.PROP_TYPE_1);
        gdsSku2PropReqDTO2.setPropValue("16G");
        gdsSku2PropReqDTO2.setPropValueId(7L);
        gdsSku2PropReqDTO2.setPropValueType(GdsConstants.GdsProp.PROP_VALUE_TYPE_1);
        gds2PropReqDTOs.add(gdsSku2PropReqDTO2);

        GdsGds2PropReqDTO gdsSku2PropReqDTO3 = new GdsGds2PropReqDTO();
        gdsSku2PropReqDTO3.setIfMust("1");
        gdsSku2PropReqDTO3.setPropId(2L);
        gdsSku2PropReqDTO3.setPropName("内存");
        gdsSku2PropReqDTO3.setPropType(GdsConstants.GdsProp.PROP_TYPE_1);
        gdsSku2PropReqDTO3.setPropValue("32G");
        gdsSku2PropReqDTO3.setPropValueId(8L);
        gdsSku2PropReqDTO3.setPropValueType(GdsConstants.GdsProp.PROP_VALUE_TYPE_1);
        gds2PropReqDTOs.add(gdsSku2PropReqDTO3);
    }

    /**
     * 
     * createSkuInfoList:(构造单品数据). <br/>
     * 
     * @author linwb3
     * @param gds2PropReqDTOs
     * @since JDK 1.6
     */
    private void createSkuInfoList(List<GdsSkuInfoReqDTO> skuInfoReqDTOs, GdsInfoAddReqDTO gdsInfoAddReqDTO) {

        GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
        skuInfoReqDTO.setRealCount(1234l);
        skuInfoReqDTO.setCompanyId(16l);
        // 构造单品图片
        List<GdsSku2MediaReqDTO> sku2MediaReqDTOs = new ArrayList<GdsSku2MediaReqDTO>();
        GdsSku2MediaReqDTO sku2MediaReqDTO = new GdsSku2MediaReqDTO();
        sku2MediaReqDTO.setMediaUuid("5646ef6ce4b0a5eabc828f86");
        sku2MediaReqDTO.setMediaRtype("2");
        sku2MediaReqDTO.setMediaType("1");
        sku2MediaReqDTO.setSortNo(1);
        sku2MediaReqDTOs.add(sku2MediaReqDTO);

        GdsSku2MediaReqDTO sku2MediaReqDTO1 = new GdsSku2MediaReqDTO();
        sku2MediaReqDTO1.setMediaUuid("564ace99e4b0a5eabc8290c5");
        sku2MediaReqDTO1.setMediaRtype("2");
        sku2MediaReqDTO1.setMediaType("1");
        sku2MediaReqDTO1.setSortNo(2);
        sku2MediaReqDTOs.add(sku2MediaReqDTO1);
        skuInfoReqDTO.setSku2MediaReqDTOs(sku2MediaReqDTOs);

        // 构造单品价格
        List<GdsSku2PriceReqDTO> sku2PriceReqDTOs = new ArrayList<GdsSku2PriceReqDTO>();

        GdsSku2PriceReqDTO gdsSku2PriceReqDTO = new GdsSku2PriceReqDTO();
        gdsSku2PriceReqDTO.setPriceType(GdsConstants.GdsInfo.SKU_PRICE_TYPE_ORDINARY);
        gdsSku2PriceReqDTO.setPriceTypeCode(GdsConstants.GdsInfo.SKU_PRICE_TYPE_ORDINARY);

        GdsPriceReqDTO price = new GdsPriceReqDTO();
        price.setPrice(133300L);
        gdsSku2PriceReqDTO.setPrice(price);

        sku2PriceReqDTOs.add(gdsSku2PriceReqDTO);
        skuInfoReqDTO.setSku2PriceReqDTOs(sku2PriceReqDTOs);

        // 构造单品属性
        List<GdsSku2PropReqDTO> sku2PropReqDTOs = new ArrayList<GdsSku2PropReqDTO>();
        GdsSku2PropReqDTO gdsSku2PropReqDTO = new GdsSku2PropReqDTO();
        gdsSku2PropReqDTO.setIfBasic("1");
        gdsSku2PropReqDTO.setIfMust("1");
        gdsSku2PropReqDTO.setPropId(1017L);
        gdsSku2PropReqDTO.setPropName("语言");
        gdsSku2PropReqDTO.setPropType(GdsConstants.GdsProp.PROP_TYPE_1);
        gdsSku2PropReqDTO.setPropValue("中文");
        gdsSku2PropReqDTO.setPropValueId(1L);
        gdsSku2PropReqDTO.setPropValueType(GdsConstants.GdsProp.PROP_VALUE_TYPE_1);
        sku2PropReqDTOs.add(gdsSku2PropReqDTO);

        GdsSku2PropReqDTO gdsSku2PropReqDTO2 = new GdsSku2PropReqDTO();
        gdsSku2PropReqDTO2.setIfBasic("1");
        gdsSku2PropReqDTO2.setIfMust("1");
        gdsSku2PropReqDTO2.setPropId(1016L);
        gdsSku2PropReqDTO2.setPropName("装帧");
        gdsSku2PropReqDTO2.setPropType(GdsConstants.GdsProp.PROP_TYPE_1);
        gdsSku2PropReqDTO2.setPropValue("16G");
        gdsSku2PropReqDTO2.setPropValueId(7L);
        gdsSku2PropReqDTO2.setPropValueType(GdsConstants.GdsProp.PROP_VALUE_TYPE_1);
        sku2PropReqDTOs.add(gdsSku2PropReqDTO2);
        skuInfoReqDTO.setSku2PropReqDTOs(sku2PropReqDTOs);
        gdsInfoAddReqDTO.setSkuProps(GdsUtils.doConvert(sku2PropReqDTOs, GdsGds2PropReqDTO.class));

        skuInfoReqDTOs.add(skuInfoReqDTO);

        GdsSkuInfoReqDTO skuInfoReqDTO1 = new GdsSkuInfoReqDTO();
        skuInfoReqDTO1.setRealCount(1234l);
        skuInfoReqDTO1.setCompanyId(16l);
        // 构造单品图片
        List<GdsSku2MediaReqDTO> sku2MediaReqDTOs1 = new ArrayList<GdsSku2MediaReqDTO>();
        GdsSku2MediaReqDTO sku2MediaReqDTO2 = new GdsSku2MediaReqDTO();
        sku2MediaReqDTO2.setMediaUuid("5646ef6ce4b0a5eabc828f86");
        sku2MediaReqDTO2.setMediaRtype("2");
        sku2MediaReqDTO2.setMediaType("1");
        sku2MediaReqDTO2.setSortNo(1);
        sku2MediaReqDTOs1.add(sku2MediaReqDTO2);

        GdsSku2MediaReqDTO sku2MediaReqDTO3 = new GdsSku2MediaReqDTO();
        sku2MediaReqDTO3.setMediaUuid("564ace99e4b0a5eabc8290c5");
        sku2MediaReqDTO3.setMediaRtype("2");
        sku2MediaReqDTO3.setMediaType("1");
        sku2MediaReqDTO3.setSortNo(2);
        sku2MediaReqDTOs1.add(sku2MediaReqDTO3);

        skuInfoReqDTO1.setSku2MediaReqDTOs(sku2MediaReqDTOs1);

        // 构造单品价格
        List<GdsSku2PriceReqDTO> sku2PriceReqDTOs1 = new ArrayList<GdsSku2PriceReqDTO>();

        GdsSku2PriceReqDTO gdsSku2PriceReqDTO1 = new GdsSku2PriceReqDTO();
        gdsSku2PriceReqDTO1.setPriceType(GdsConstants.GdsInfo.SKU_PRICE_TYPE_ORDINARY);
        gdsSku2PriceReqDTO1.setPriceTypeCode(GdsConstants.GdsInfo.SKU_PRICE_TYPE_ORDINARY);

        GdsPriceReqDTO price1 = new GdsPriceReqDTO();
        price1.setPrice(133300L);
        gdsSku2PriceReqDTO1.setPrice(price1);

        sku2PriceReqDTOs1.add(gdsSku2PriceReqDTO1);

        skuInfoReqDTO1.setSku2PriceReqDTOs(sku2PriceReqDTOs1);

        // 构造单品属性
        List<GdsSku2PropReqDTO> sku2PropReqDTOs1 = new ArrayList<GdsSku2PropReqDTO>();
        GdsSku2PropReqDTO gdsSku2PropReqDTO4 = new GdsSku2PropReqDTO();
        gdsSku2PropReqDTO4.setIfBasic("1");
        gdsSku2PropReqDTO4.setIfMust("1");
        gdsSku2PropReqDTO4.setPropId(1017L);
        gdsSku2PropReqDTO4.setPropName("语言");
        gdsSku2PropReqDTO4.setPropType(GdsConstants.GdsProp.PROP_TYPE_1);
        gdsSku2PropReqDTO4.setPropValue("中文");
        gdsSku2PropReqDTO4.setPropValueId(2L);
        gdsSku2PropReqDTO4.setPropValueType(GdsConstants.GdsProp.PROP_VALUE_TYPE_1);
        sku2PropReqDTOs1.add(gdsSku2PropReqDTO4);

        GdsSku2PropReqDTO gdsSku2PropReqDTO3 = new GdsSku2PropReqDTO();
        gdsSku2PropReqDTO3.setIfBasic("1");
        gdsSku2PropReqDTO3.setIfMust("1");
        gdsSku2PropReqDTO3.setPropId(1016L);
        gdsSku2PropReqDTO3.setPropName("装帧");
        gdsSku2PropReqDTO3.setPropType(GdsConstants.GdsProp.PROP_TYPE_1);
        gdsSku2PropReqDTO3.setPropValue("32G");
        gdsSku2PropReqDTO3.setPropValueId(8L);
        gdsSku2PropReqDTO3.setPropValueType(GdsConstants.GdsProp.PROP_VALUE_TYPE_1);
        sku2PropReqDTOs1.add(gdsSku2PropReqDTO3);

        skuInfoReqDTO1.setSku2PropReqDTOs(sku2PropReqDTOs1);
        gdsInfoAddReqDTO.getSkuProps().addAll(GdsUtils.doConvert(sku2PropReqDTOs1, GdsGds2PropReqDTO.class));
        skuInfoReqDTOs.add(skuInfoReqDTO1);
    }

    private BaseStaff getBaseStaff() {
        BaseStaff staff = new BaseStaff();
        staff.setId(123456789L);
        staff.setStaffCode("admin");
        return staff;
    }

}
