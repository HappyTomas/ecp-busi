package com.zengshi.express.controller;

import com.zengshi.ecp.unpf.dubbo.dto.UnpfShopExpressReqDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IUnpfShopExpressRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;
import com.google.common.primitives.Longs;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by guojingman on 2017/3/24.
 */
@Controller
@RequestMapping(value = "/youzan/express")
public class YouzanExpressGeneratorController {
    @Resource
    private IUnpfShopExpressRSV shopExpressRSV;

    private static final String PLAT_TYPE = "youzan";

    @RequestMapping(value = "/e664008618154c4bb3d303e566/d3b299dcceb5", method = RequestMethod.GET)
    @ResponseBody
    /**
     * 系统上线后需调用此接口，初始化有赞物流公司数据（多次调用不会覆盖数据）
     */
    public Object generateExpress(String shopId) {
        Map<String, String> result = new HashMap<>();
        if (StringUtil.isBlank(shopId) || Longs.tryParse(shopId) == null) {
            result.put("msg", "请传入正确的shopId值(shopId必须为数字型)");
            return result;
        }

        Map<String, String> expressDataMap = getExpressData();
        Iterator iter = expressDataMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            UnpfShopExpressReqDTO express = new UnpfShopExpressReqDTO();
            express.setCode(entry.getKey().toString());
            express.setName(entry.getValue().toString());
            express.setFullName(entry.getValue().toString());
            express.setStatus("1");
            express.setPlatType(PLAT_TYPE);
            express.setCreateTime(DateUtil.getSysDate());
            express.setShopId(Long.parseLong(shopId));
            shopExpressRSV.insert(express);
        }
        result.put("msg", "物流公司数据创建成功,共计：" + expressDataMap.size());
        return result;
    }

    /**
     * 有赞快递公司类型清单（有赞没有提供接口，只提供数据清单）
     * http://open.youzan.com/docs/server#627
     *
     * @return
     */
    private Map<String, String> getExpressData() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "申通E物流");
        map.put("2", "圆通速递");
        map.put("3", "中通速递");
        map.put("4", "韵达快运");
        map.put("5", "天天快递");
        map.put("6", "百世汇通");
        map.put("7", "顺丰速运");
        map.put("8", "邮政国内小包");
        map.put("10", "EMS经济快递");
        map.put("11", "EMS");
        map.put("12", "邮政平邮");
        map.put("13", "德邦快递");
        map.put("16", "联昊通");
        map.put("17", "全峰快递");
        map.put("18", "全一快递");
        map.put("19", "城市100");
        map.put("20", "汇强快递");
        map.put("21", "广东EMS");
        map.put("22", "速尔");
        map.put("23", "飞康达速运");
        map.put("25", "宅急送");
        map.put("27", "联邦快递");
        map.put("28", "德邦物流");
        map.put("30", "中铁快运");
        map.put("31", "信丰物流");
        map.put("32", "龙邦速递");
        map.put("33", "天地华宇");
        map.put("34", "快捷速递");
        map.put("36", "新邦物流");
        map.put("37", "能达速递");
        map.put("38", "优速快递");
        map.put("40", "国通快递");
        map.put("42", "顺丰快递");
        map.put("43", "AAE");
        map.put("44", "安信达");
        map.put("45", "百福东方");
        map.put("46", "BHT");
        map.put("47", "邦送物流");
        map.put("48", "传喜物流");
        map.put("49", "大田物流");
        map.put("50", "D速快递");
        map.put("51", "递四方");
        map.put("52", "飞康达物流");
        map.put("53", "飞快达");
        map.put("54", "凡客如风达");
        map.put("55", "风行天下");
        map.put("56", "飞豹快递");
        map.put("57", "港中能达");
        map.put("58", "广东邮政");
        map.put("59", "共速达");
        map.put("60", "汇通快运");
        map.put("61", "华宇物流");
        map.put("62", "恒路物流");
        map.put("63", "华夏龙");
        map.put("64", "海航天天");
        map.put("65", "海盟速递");
        map.put("66", "华企快运");
        map.put("67", "山东海红");
        map.put("68", "佳吉物流");
        map.put("69", "佳怡物流");
        map.put("70", "加运美");
        map.put("71", "京广速递");
        map.put("72", "急先达");
        map.put("73", "晋越快递");
        map.put("74", "捷特快递");
        map.put("75", "金大物流");
        map.put("76", "嘉里大通");
        map.put("77", "康力物流");
        map.put("78", "跨越物流");
        map.put("79", "龙邦物流");
        map.put("80", "蓝镖快递");
        map.put("81", "隆浪快递");
        map.put("82", "门对门");
        map.put("83", "明亮物流");
        map.put("84", "全晨快递");
        map.put("85", "全际通");
        map.put("86", "全日通");
        map.put("87", "如风达快递");
        map.put("88", "三态速递");
        map.put("89", "盛辉物流");
        map.put("90", "速尔物流");
        map.put("91", "盛丰物流");
        map.put("92", "上大物流");
        map.put("94", "赛澳递");
        map.put("95", "圣安物流");
        map.put("96", "穗佳物流");
        map.put("97", "优速物流");
        map.put("98", "万家物流");
        map.put("99", "万象物流");
        map.put("100", "新蛋奥硕物流");
        map.put("101", "香港邮政");
        map.put("102", "运通快递");
        map.put("103", "远成物流");
        map.put("104", "亚风速递");
        map.put("105", "一邦速递");
        map.put("106", "源伟丰快递");
        map.put("107", "元智捷诚");
        map.put("108", "越丰物流");
        map.put("109", "源安达");
        map.put("110", "原飞航");
        map.put("111", "忠信达快递");
        map.put("112", "芝麻开门");
        map.put("113", "银捷速递");
        map.put("114", "中邮物流");
        map.put("115", "中速快件");
        map.put("116", "中天万运");
        map.put("117", "河北建华");
        map.put("118", "乐捷递");
        map.put("119", "立即送");
        map.put("120", "通和天下");
        map.put("121", "微特派");
        map.put("122", "一统飞鸿");
        map.put("123", "郑州建华");
        map.put("125", "山西红马甲");
        map.put("126", "陕西黄马甲");
        map.put("127", "快速递");
        map.put("128", "安能物流");
        map.put("129", "新顺丰");
        map.put("130", "钱报速运");
        map.put("131", "日日顺");
        map.put("132", "神盾快运");
        map.put("133", "京华亿家");
        map.put("134", "南方传媒物流");
        map.put("135", "成都商报物流");
        map.put("136", "冻到家物流");
        map.put("137", "亚马逊物流");
        map.put("138", "京东快递");
        map.put("139", "e邮宝");
        map.put("140", "思迈");
        map.put("141", "UPS");
        map.put("142", "南京100");
        map.put("143", "民航快递");
        map.put("144", "贝海国际速递");
        map.put("145", "CJ物流");
        map.put("146", "央广购物");
        map.put("147", "易时联国际速递");
        map.put("148", "风先生");
        map.put("149", "耀启物流");
        map.put("150", "内蒙EMS");
        map.put("151", "小红帽");
        map.put("152", "PCA Express");
        map.put("153", "诚义物流");
        map.put("154", "秦远国际物流");
        map.put("155", "万家康快递");
        map.put("156", "澳邮中国快运");
        map.put("157", "一号线国际速递");
        map.put("158", "EWE国际物流");
        map.put("159", "爱送配送");
        map.put("160", "POSTNZ");
        map.put("161", "FASTGO");
        map.put("162", "天越物流");
        map.put("163", "德中物流");
        map.put("164", "行必达");
        map.put("165", "EFS快递");
        map.put("166", "中邮速递");
        map.put("167", "一号仓");
        map.put("168", "速通达跨境物流");
        map.put("170", "五亨国际");
        map.put("171", "迅物流");
        map.put("172", "中环国际_澳洲");
        map.put("173", "美仓快递");
        map.put("174", "澳通速递");
        map.put("175", "济南猎豹速递");
        map.put("176", "澳运速递");
        map.put("177", "优达生鲜");
        map.put("178", "P2UEXPRESS");
        map.put("179", "黑猫宅急便");
        map.put("180", "快客快运");
        map.put("181", "当当物流");
        map.put("182", "百世快运");
        map.put("183", "艾瑞斯远");
        map.put("186", "斑马物联网");
        map.put("187", "泛捷国际速运");
        map.put("188", "黄马甲快递");
        map.put("189", "蓝天快递");
        map.put("190", "银河物流");
        map.put("191", "海龟国际速运");
        map.put("192", "申通国际");
        map.put("193", "安鲜达");
        map.put("194", "闪送");
        map.put("195", "我的物流");
        map.put("196", "黑狗快递");
        map.put("197", "富腾达快递");
        map.put("198", "程光快递");
        map.put("199", "吉祥邮");
        map.put("200", "天时海淘转运");
        map.put("201", "澳大利亚AOL快递");
        map.put("202", "亿翔快递");
        map.put("203", "中澳国际物流");
        map.put("204", "全速快递");
        map.put("205", "极客快递");
        map.put("206", "金岸物流");
        return map;
    }

}
