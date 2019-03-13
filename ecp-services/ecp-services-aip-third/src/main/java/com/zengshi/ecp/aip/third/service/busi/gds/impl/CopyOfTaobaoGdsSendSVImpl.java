package com.zengshi.ecp.aip.third.service.busi.gds.impl;


import java.math.BigDecimal;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsInfoThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsSendThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.SkuInfoThirdReqDTO;
import com.zengshi.ecp.aip.third.service.busi.gds.interfaces.IGdsSendSV;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.FileItem;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ItemAddRequest;
import com.taobao.api.response.ItemAddResponse;

public class CopyOfTaobaoGdsSendSVImpl implements IGdsSendSV{
    
    public static final String MODULE = CopyOfTaobaoGdsSendSVImpl.class.getName();

    @Resource
	private ITokenSV defaultTokenSV;
    
    
    @Override
    public HashMap send(GdsSendThirdReqDTO gdsSendReqDTO) throws BusinessException { 
    	
    	/*GdsInfoThirdReqDTO gdsInfo=gdsSendReqDTO.getGdsInfo();*/
    	
    	GdsInfoThirdReqDTO gdsInfo=null;
    	
    	TaobaoClient client = new DefaultTaobaoClient(gdsSendReqDTO.getServerUrl(), gdsSendReqDTO.getAppkey(), gdsSendReqDTO.getAppscret());
    	
    	//参考接口http://open.taobao.com/docs/api.htm?spm=a219a.7629065.0.0.Peqdoc&apiId=22
    	
    	ItemAddRequest req = new ItemAddRequest();
    	//req.setSkuSpecIds("123,123,1243");//此参数暂时不起作用
    	//req.setSkuDeliveryTimes("123,123,1243");//此参数暂时不起作用
    	//req.setSkuHdLength("20,30,30");//家装建材类目，商品SKU的长度，正整数，单位为cm，部分类目必选。天猫商家专用。 数据和SKU一一对应，用,分隔，如：20,30,30
    	//req.setSkuHdHeight("15-25,25-50,25-50");//家装建材类目，商品SKU的高度，单位为cm，部分类目必选。天猫商家专用。 可选值为："0-15", "15-25", "25-50", "50-60", "60-80", "80-120", "120-160", "160-200"。 数据和SKU一一对应，用,分隔，如：15-25,25-50,25-50
    	//req.setSkuHdLampQuantity("3,5,7");//家装建材类目，商品SKU的灯头数量，正整数，大于等于3，部分类目必选。天猫商家专用。 数据和SKU一一对应，用,分隔，如：3,5,7
    	//用户自行输入的子属性名和属性值，结构:"父属性值;一级子属性名;一级子属性值;二级子属性名;自定义输入值,....",如：“耐克;耐克系列;科比系列;科比系列;2K5,Nike乔丹鞋;乔丹系列;乔丹鞋系列;乔丹鞋系列;json5”，多个自定义属性用','分割，input_str需要与input_pids一一对应，注：通常一个类目下用户可输入的关键属性不超过1个。所有属性别名加起来不能超过3999字节。此处不可以使用“其他”、“其它”和“其她”这三个词
    	req.setInputStr(null);//待定  需要文彬确认
    	//??????????????????????????????????????????????
    	
    	
    	req.setInputPids(null);//待定  需要文彬确认
    	//??????????????????????????????????????????????
    	
    	
    	req.setNum(Long.valueOf(gdsInfo.getSkuInfos().size()));// 必填字段
    	//商品数量。取值范围:0-900000000的整数。且需要等于Sku所有数量的和。拍卖商品中增加拍只能为1，荷兰拍要在[2,500)范围内。
    	req.setPrice(gdsInfo.getGuidePrice().toString());//商品价格。取值范围:0-100000000;精确到2位小数;单位:元。如:200.07，表示:200元7分。需要在正确的价格区间内。拍卖商品对应的起拍价。
    	//价格取什么价格 待定
    	req.setType("fixed");//必填字段
    	//发布类型。可选值:fixed(一口价),auction(拍卖)。B商家不能发布拍卖商品，而且拍卖商品是没有SKU的。拍卖商品发布时需要附加拍卖商品信息：拍卖类型(paimai_info.mode，拍卖类型包括三种：增价拍[1]，荷兰拍[2]以及降价拍[3])，商品数量(num)，起拍价(price)，价格幅度(increament)，保证金(paimai_info.deposit)。另外拍卖商品支持自定义销售周期，通过paimai_info.valid_hour和paimai_info.valid_minute来指定。对于降价拍来说需要设置降价周期(paimai_info.interval)和拍卖保留价(paimai_info.reserve)。注意：通过taobao.item.get接口获取拍卖信息时，会返回除了valid_hour和valid_minute之外的所有拍卖信息。
    	req.setStuffStatus("new");//必填字段
    	//新旧程度。可选值：new(新)，second(二手)。B商家不能发布二手商品。如果是二手商品，特定类目下属性里面必填新旧成色属性
    	req.setTitle(gdsInfo.getGdsName());//必填字段
    	//宝贝标题。不能超过30字符，受违禁词控制。天猫图书管控类目最大允许120字符；
    	req.setDesc(gdsInfo.getGdsDesc());//必填
    	//宝贝描述。字数要大于5个字符，小于25000个字符，受违禁词控制
    	
    	//req.setLocationState("浙江");//必填
    	req.setLocationState(gdsInfo.getProvinceName());
    	//所在地省份。如浙江
    	//req.setLocationCity("杭州");//必填
    	req.setLocationCity(gdsInfo.getCityName());
    	//所在地城市。如杭州 。
    	req.setApproveStatus("onsale");//商品上传后的状态。可选值:onsale(出售中),instock(仓库中);默认值:onsale
    	req.setCid(Long.valueOf(gdsInfo.getCid()));//必填
    	//叶子类目id
    	req.setProps(gdsInfo.getPropStr());
    	//商品属性列表。格式:pid:vid;pid:vid。属性的pid调用taobao.itemprops.get取得，属性值的vid用taobao.itempropvalues.get取得vid。 如果该类目下面没有属性，可以不用填写。如果有属性，必选属性必填，其他非必选属性可以选择不填写.属性不能超过35对。所有属性加起来包括分割符不能超过549字节，单个属性没有限制。 如果有属性是可输入的话，则用字段input_str填入属性的值
    	
    	req.setFreightPayer(gdsInfo.getShipFeeType());//需要设置 运费模板等 待需求确认 如何开发
        //运费承担方式。可选值:seller（卖家承担）,buyer(买家承担);默认值:seller。卖家承担不用设置邮费和postage_id.买家承担的时候，必填邮费和postage_id 如果用户设置了运费模板会优先使用运费模板，否则要同步设置邮费（post_fee,express_fee,ems_fee）
    	
    	
    	//req.setValidThru(7L);//有效期。可选值:7,14;单位:天;默认值:14
    	req.setHasInvoice(true);//是否有发票。可选值:true,false (商城卖家此字段必须为true);默认值:false(无发票)
    	req.setHasWarranty(true);//是否有保修。可选值:true,false;默认值:false(不保修)
    	req.setAutoRepost(true);
    	req.setHasShowcase(true);//橱窗推荐。可选值:true,false;默认值:false(不推荐)
    	req.setSellerCids(null);//商品所属的店铺类目列表。按逗号分隔。结构:",cid1,cid2,...,"，如果店铺类目存在二级类目，必须传入子类目cids
    	//待定是否需要 如果需要 那就需要到分类表中 取到对应的层级
    	
    	
    	req.setHasDiscount(true);//支持会员打折。可选值:true,false;默认值:false(不打折)
    	
    	if("buyer".equals(gdsInfo.getShipFeeType())){
    		if(StringUtils.isNotBlank(gdsInfo.getPostFee())){
	    	    req.setPostFee(this.format2Decimal(gdsInfo.getPostFee()));//平邮费用。取值范围:0.01-999.00;精确到2位小数;单位:元。如:5.07，表示:5元7分. 注:post_fee,express_fee,ems_fee需要一起填写
    		}
    		if(StringUtils.isNotBlank(gdsInfo.getExpressFee())){
	    	      req.setExpressFee(this.format2Decimal(gdsInfo.getExpressFee()));//快递费用。取值范围:0.01-999.00;精确到2位小数;单位:元。如:15.07，表示:15元7分
    		}
    		if(StringUtils.isNotBlank(gdsInfo.getEmsFee())){
	    	    req.setEmsFee(this.format2Decimal(gdsInfo.getEmsFee()));//ems费用。取值范围:0.01-999.00;精确到2位小数;单位:元。如:25.07，表示:25元7分
    		}
    	}
    	//req.setListTime(StringUtils.parseDateTime("2000-01-01 00:00:00"));
    	req.setListTime(null);//定时上架时间。(时间格式：yyyy-MM-dd HH:mm:ss)
    	req.setIncrement("0");//加价(降价)幅度。如果为0，代表系统代理幅度。对于增价拍和荷兰拍来说是加价幅度，对于降价拍来说是降价幅度。
    	//req.setImage(new FileItem("/tmp/file.txt"));//商品主图片。类型:JPG,GIF;最大长度:3M支持的文件类型：gif,jpg,jpeg,png
    	
    	
    	//FileItem mainImgage=new FileItem("/tmp/file.txt");
    	
    	
    	req.setImage(new FileItem(gdsInfo.getMainImageUrl()));//url如何获得 待定
    	
    	if("buyer".equals(gdsInfo.getShipFeeType())){
    		if(StringUtils.isNotBlank(gdsInfo.getPostageId())){
    	        req.setPostageId(Long.valueOf(gdsInfo.getPostageId()));//宝贝所属的运费模板ID。取值范围：整数且必须是该卖家的运费模板的ID（可通过taobao.delivery.template.get获得当前会话用户的所有邮费模板）
    		  }
    		}
    	//req.setAuctionPoint(null);//	商品的积分返点比例。如:5,表示:返点比例0.5%. 注意：返点比例必须是>0的整数，而且最大是90,即为9%.B商家在发布非虚拟商品时，返点必须是 5的倍数，即0.5%的倍数。其它是1的倍数，即0.1%的倍数。无名良品商家发布商品时，复用该字段记录积分宝返点比例，返点必须是对应类目的返点步长的整数倍，默认是5，即0.5%。注意此时该字段值依旧必须是>0的整数，最高值不超过500，即50%
    	req.setPropertyAlias(null);//属性值别名。如pid:vid:别名;pid1:vid1:别名1 ，其中：pid是属性id vid是属性值id。总长度不超过512字节
    	
    	StringBuffer prices=new StringBuffer();
    	StringBuffer skuIds=new StringBuffer();
    	StringBuffer quantities=new StringBuffer();
    	StringBuffer skuProps=new StringBuffer();
    	for(SkuInfoThirdReqDTO s:gdsInfo.getSkuInfos()){
    	   	//req.setSkuProperties("pid:vid,pid:vid");//更新的sku的属性串，调用taobao.itemprops.get获取。格式:pid1:vid;pid2:vid,多个sku属性之间用逗号分隔。该字段内的属性需要在props字段同时包含。如果新增商品包含了sku，则此字段一定要传入,字段长度要控制在512个字节以内。
        	skuIds.append(s.getId()+",");
        	prices.append(this.format2Yuan(s.getCommonPrice())+",");
        	quantities.append(s.getQuantities()+",");
        	skuProps.append(s.getProps()+",");
    	}
     	if(prices!=null && prices.length()>0){
    		req.setSkuPrices(prices.substring(0, prices.toString().lastIndexOf(",")).toString());
    	}
      	if(skuIds!=null && skuIds.length()>0){
    		req.setSkuOuterIds(skuIds.substring(0, skuIds.toString().lastIndexOf(",")).toString());
    	}
     	if(quantities!=null && quantities.length()>0){
    		req.setSkuQuantities(quantities.substring(0, quantities.toString().lastIndexOf(",")).toString());
    	}
    	if(skuProps!=null && skuProps.length()>0){
    		req.setSkuProperties(skuProps.substring(0, skuProps.toString().lastIndexOf(",")).toString());
    	}
    	
    	req.setLang("zh_CN");
    	
    	req.setOuterId(gdsInfo.getId().toString());//商品外部编码，该字段的最大长度是64个字节
    	
    	//req.setProductId(123456789L);//	商品所属的产品ID(B商家发布商品需要用)
    	
    	//req.setPicPath("i7/T1rfxpXcVhXXXH9QcZ_033150.jpg");//	商品主图需要关联的图片空间的相对url。这个url所对应的图片必须要属于当前用户。pic_path和image只需要传入一个,如果两个都传，默认选择pic_path
    	
    	
    	req.setAutoFill("no_mark");//代充商品类型。在代充商品的类目下，不传表示不标记商品类型（交易搜索中就不能通过标记搜到相关的交易了）。可选类型： no_mark(不做类型标记) time_card(点卡软件代充) fee_card(话费软件代充)
    	
    	req.setIsTaobao(true);//是否在淘宝上显示（如果传FALSE，则在淘宝主站无法显示该商品）
    	req.setIsEx(true);//是否在外店显示
    	req.setIs3D(false);//是否是3D
    	req.setSellPromise(true);//	是否承诺退换货服务!虚拟商品无须设置此项!
    	//req.setCodPostageId(53899L);//此为货到付款运费模板的ID，对应的JAVA类型是long,如果COD卖家应用了货到付款运费模板，此值要进行设置。该字段已经废弃
    	req.setIsLightningConsignment(true);//实物闪电发货
    	//req.setWeight(100L);//商品的重量(商超卖家专用字段)
    	
    	//未知参数
    	req.setShape("0");//?????????
    	req.setIsXinpin(false);//商品是否为新品。只有在当前类目开通新品,并且当前用户拥有该类目下发布新品权限时才能设置is_xinpin为true，否则设置true后会返回错误码:isv.invalid-permission:add-xinpin。同时只有一口价全新的宝贝才能设置为新品，否则会返回错误码：isv.invalid-parameter:xinpin。不设置该参数值或设置为false效果一致。
    	req.setSubStock(2L);//商品是否支持拍下减库存:1支持;2取消支持(付款减库存);0(默认)不更改集市卖家默认拍下减库存;商城卖家默认付款减库存
    	
    	//req.setFoodSecurityPrdLicenseNo("QS410006010388");//生产许可证号
    	//req.setFoodSecurityDesignCode(gdsInfo.getIsbn());//产品标准号
    	
    	
    	//req.setFoodSecurityFactory("远东恒天然乳品有限公司");
    	//req.setFoodSecurityFactorySite("台北市仁爱路4段85号");
    	//req.setFoodSecurityContact("00800-021216");
    	//req.setFoodSecurityMix("有机乳糖、有机植物油");
    	//req.setFoodSecurityPlanStorage("常温");
    	//req.setFoodSecurityPeriod("2年");
    	//req.setFoodSecurityFoodAdditive("磷脂 、膨松剂");
    	//req.setFoodSecuritySupplier("深圳岸通商贸有限公司");
    	//req.setFoodSecurityProductDateStart("2012-06-01");
    	//req.setFoodSecurityProductDateEnd("2012-06-10");
    	//req.setFoodSecurityStockDateStart("2012-06-20");
    	//req.setFoodSecurityStockDateEnd("2012-06-30");
    	//req.setScenicTicketPayWay(1L);
    	//req.setScenicTicketBookCost("5.99");
    	//req.setItemSize("bulk:8");
    	//req.setItemWeight("10");
    	//req.setSellPoint("2013新款 时尚 前卫");//商品卖点信息，最长150个字符。天猫商家和集市卖家都可用。
    	//req.setFoodSecurityHealthProductNo("卫食健字(1997)第167号");
    	req.setBarcode(gdsInfo.getIsbn());//商品条形码
    	
    	//req.setSkuBarcode("6903244981002");//	sku层面的条形码，多个SKU情况，与SKU价格库存格式类似，用逗号分隔
    	req.setNewprepay("1");//该宝贝是否支持【7天无理由退货】，卖家选择的值只是一个因素，最终以类目和选择的属性条件来确定是否支持7天。填入字符0，表示不支持；未填写或填人字符1，表示支持7天无理由退货；
    	//req.setQualification("string");//商品资质信息
    	//req.setO2oBindService(true);//汽车O2O绑定线下服务标记，如不为空，表示关联服务，否则，不关联服务。
    	//req.setFeatures(";mysize_tp:12586;keyx:valuex;");//宝贝特征值，格式为：【key1:value1;key2:value2;key3:value3;】，key和value用【:】分隔，key&value之间用【;】分隔，只有在Top支持的特征值才能保存到宝贝上，目前支持的Key列表为：mysize_tp
    	//req.setLocalityLifeObs("1");//预约门店是否支持门店自提,1:是
    	//req.setLocalityLifeVersion("1");//新版电子凭证字段
    	//req.setLocalityLifePackageid("12344");//新版电子凭证包 id
    	//req.setIgnorewarning(",ifd_warning,FakeCredit_Warning,");//忽略警告提示.
    	//req.setMsPaymentReferencePrice("199");//http://bangpai.taobao.com/group/thread/15031186-303287205.htm
    	//req.setMsPaymentVoucherPrice("50");//尾款可抵扣金额。详见说明：http://bangpai.taobao.com/group/thread/15031186-303287205.htm
    	//req.setMsPaymentPrice("100");//订金。在“线上付订金线下付尾款”模式中，有订金、尾款可抵扣金额和参考价，三者需要同时填写。该商品订单首次支付价格为 订金 价格，用户可根据 参考价 估算全款。该模式有别于“一口价”付款方式，针对一个商品，只能选择两种付款方式中的一种，其适用于家装、二手车等场景。详见说明：http://bangpai.taobao.com/group/thread/15031186-303287205.htm
    	//req.setAfterSaleId(12323L);//售后说明模板id
    	//可固定？？？
    	
    	req.setDeliveryTimeNeedDeliveryTime("none");//设置是否使用发货时间，商品级别，sku级别
    	req.setDeliveryTimeDeliveryTimeType("relative");//发货时间类型：绝对发货时间或者相对发货时间
    	req.setDeliveryTimeDeliveryTime("5");//商品级别设置的发货时间。设置了商品级别的发货时间，相对发货时间，则填写相对发货时间的天数（大于3）；绝对发货时间，则填写yyyy-mm-dd格式，如2013-11-11
    	//req.setChangeProp("pid:vid:vid1,vid2,vid3;pid:vid:vid1,vid2");//基础色数据
    	
    	
    	
    	//待定字段？？？？？？？？？？？？？？？？
    	//req.setDescModules("[{\"module_id\":123,\"module_name\":\"模特图\",\"type\":\"cat_mod\",\"content\":\"模特要漂亮一点拜托\"},{\"module_id\":null,\"module_name\":\"店主最漂亮\",\"type\":\"usr_mod\",\"content\":\"老娘全网最美\"}]");
    	req.setIsOffline("1");//是否是线下商品。1：线上商品（默认值）；2：线上或线下商品；3：线下商品。
    	
    	//req.setWirelessDesc("无线宝贝描述，亲");//无线的宝贝描述
    	//req.setSpuConfirm(false); //手机类目spu 优化，信息确认字段
    	//req.setLocalityLifeChooseLogis("0");//发布电子凭证宝贝时候表示是否使用邮寄 0: 代表不使用邮寄； 1：代表使用邮寄；如果不设置这个值，代表不使用邮寄
    	//req.setLocalityLifeExpirydate("2012-08-06,2012-08-16");//本地生活电子交易凭证业务，目前此字段只涉及到的信息为有效期;如果有效期为起止日期类型，此值为2012-08-06,2012-08-16如果有效期为【购买成功日 至】类型则格式为2012-08-16如果有效期为天数类型则格式为15
    	//req.setLocalityLifeNetworkId("5645746");//网点ID
    	//req.setLocalityLifeMerchant("56879:码商X");//码商信息，格式为 码商id:nick
    	
    	//??????????????????????????????????
    	req.setLocalityLifeVerification("1");//核销打款 1代表核销打款 0代表非核销打款
    	
    	//req.setLocalityLifeRefundRatio(50L);//退款比例，百分比%前的数字,1-100的正整数值
    	
    	//?????????????
    	//req.setLocalityLifeOnsaleAutoRefundRatio(80L);//电子凭证售中自动退款比例，百分比%前的数字，介于1-100之间的整数
    	
    	req.setLocalityLifeRefundmafee("b");//	退款码费承担方。发布电子凭证宝贝的时候会增加“退款码费承担方”配置项，可选填：(1)s（卖家承担） (2)b(买家承担)
    	
    	//req.setLocalityLifeEticket(";market:eticket;consume_way:4;");
    	//电子凭证业务属性，数据字典是: 1、is_card:1 (暂时不用) 2、consume_way:4 （1 串码 ，4 身份证）3、consume_midmnick ：(核销放行账号:用户id-用户名，支持多个，用逗号分隔,例如 1234-测试账号35,1345-测试账号56）4、market:eticket (电子凭证商品标记) 5、has_pos:1 (1 表示商品配置线下门店，在detail上进行展示 ，没有或者其他值只不展示)格式是: k1:v2;k2:v2;........ 如：has_pos:1;market:eticket;consume_midmnick:901409638-OPPO;consume_way:4
    	
    	
    	//req.setPaimaiInfoMode(1L);//拍卖商品选择的拍卖类型，拍卖类型包括三种：增价拍(1)，荷兰拍(2)和降价拍(3)。
    	//req.setPaimaiInfoDeposit(20L);//	拍卖宝贝的保证金。对于增价拍和荷兰拍来说保证金有两种模式：淘宝默认模式（首次出价金额的10%），自定义固定保证金（固定冻结金额只能输入不超过30万的正整数），并且保证金只冻结1次。对于降价拍来说保证金只有淘宝默认的（竞拍价格的10% * 竞拍数量），并且每次出价都需要冻结保证金。对于拍卖宝贝来说，保证金是必须的，但是默认使用淘宝默认保证金模式，只有用户需要使用自定义固定保证金的时候才需要使用到这个参数，如果该参数不传或传入0则代表使用默认。
    	//req.setPaimaiInfoInterval(5L);//降价拍宝贝的降价周期(分钟)。降价拍宝贝的价格每隔paimai_info.interval时间会下降一次increment。
    	//req.setPaimaiInfoReserve("11");//	降价拍宝贝的保留价。对于降价拍来说，paimai_info.reserve必须大于0，且小于price-increment，而且（price-paimai_info.reserve）/increment的计算结果必须为整数
    	//req.setPaimaiInfoValidHour(2L);//自定义销售周期的小时数。拍卖宝贝可以自定义销售周期，这里指定销售周期的小时数。注意，该参数只作为输入参数，不能通过taobao.item.get接口获取。
    	//req.setPaimaiInfoValidMinute(22L);//自定义销售周期的分钟数。拍卖宝贝可以自定义销售周期，这里是指定销售周期的分钟数。自定义销售周期的小时数。拍卖宝贝可以自定义销售周期，这里指定销售周期的小时数。注意，该参数只作为输入参数，不能通过taobao.item.get接口获取。
    	//req.setGlobalStockType("1");//全球购商品采购地（库存类型），有两种库存类型：现货和代购参数值为1时代表现货，值为2时代表代购。注意：使用时请与 全球购商品采购地（地区/国家）配合使用
    	//req.setGlobalStockCountry("美国");//全球购商品采购地（地区/国家）,默认值只在全球购商品采购地（库存类型选择情况生效），地区国家值为（美国, 香港, 日本, 英国, 新西兰, 德国, 韩国, 荷兰, 澳洲, 法国, 意大利, 台湾, 澳门, 加拿大, 瑞士, 西班牙, 泰国, 新加坡, 马来西亚, 菲律宾, 其他）
    	//req.setSupportCustomMade(true);//是否支持定制市场 true代表支持，false代表支持,如果为空代表与之前保持不变不会修改
    	//req.setCustomMadeTypeId("12");//定制工具Id如果支持定制市场，这个值不填写，就用之前的定制工具Id，之前的定制工具Id没有值就默认为-1
    	//req.setGlobalStockDeliveryPlace("1");//	全球购商品发货地，发货地现在有两种类型：“国内”和“海外及港澳台”，参数值为1时代表“国内”，值为2时代表“海外及港澳台”，默认为国内。注意：卖家必须已经签署并启用“海外直邮”合约，才能选择发货地为“海外及港澳台”
    	//req.setGlobalStockTaxFreePromise(false);//全球购商品卖家包税承诺，当值为true时，代表卖家承诺包税。注意：请与“全球购商品发货地”配合使用，包税承诺必须满足：1、发货地为海外及港澳台 2、卖家已经签署并启用“包税合约”合约
    	
    	//?????????  文档没有此字段
    	//req.setChaoshiExtendsInfo("{\"mchaoshi_group\":\"个清洗护\"}");
    	
    	//针对当前商品的自定义属性值，目前是针对销售属性值自定义的，所以调用方需要把自定义属性值对应的虚拟属性值ID（负整数，例如例子中的 -1和-2）像标准属性值值的id一样设置到SKU上，如果自定义属性值有属性值图片，也要设置到属性图片上
    	//req.setInputCustomCpv("1627207:-1:渐变红;1627207:-2:宝石蓝");
    	//req.setCpvMemo("1627207:28341:颜色偏黑;1627207:3232483:颜色偏淡");//针对当前商品的标准属性值的补充说明，让买家更加了解商品信息减少交易纠纷
    	
    	
    	HashMap map=new HashMap();
    	ItemAddResponse rsp;
		try {
			//验证参数是否正确
			req.check();
			//获得token
			String token=defaultTokenSV.fetchShopToken(gdsSendReqDTO.findBaseShopAuth());
			
			//有些方法不需要传入token 具体查看每个api的头部说明  一般和当前店铺有关系的都需要token
			rsp = client.execute(req, token);
			// 返回结果解析
			if (rsp.isSuccess()) { 
				//map.put("num_iid", rsp.get)
			} else {
				LogUtil.error(MODULE, rsp.getBody().toString());
				throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{rsp.getBody().toString()});
			}
		} catch (ApiException e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{e.toString()});
		} catch (Exception e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{e.toString()});
		}

    	return null;
    }
    //分转为元
    private String format2Yuan(Long money){
    	String re="0.00";
    	if(money==null){
    		return re;
    	}
    	try{
	    	BigDecimal   b   =   new   BigDecimal(money);  
	    	b=b.divide(new BigDecimal(100));
	    	re   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).toString();  
    	}catch(Exception ex){
    		LogUtil.error(MODULE, "分转为元 并且保留两位有效小数报错啦，"+ex.toString());
    	}
    	return re;
    }
    
    //四舍五入 保留2位小数
    private String format2Decimal(String str){
    	String re="0.00";
    	if(StringUtils.isBlank(str)){
    		return re;
    	}
    	try{
	    	BigDecimal   b   =   new   BigDecimal(str);  
	    	re   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).toString();  
    	}catch(Exception ex){
    		LogUtil.error(MODULE, "转化2为小数出错啦，"+ex.toString());
    	}
    	return re;
    }
  /*  
    @Override
    public String send(GdsSendThirdReqDTO gdsSendReqDTO) throws BusinessException { 
    	
    	TaobaoClient client = new DefaultTaobaoClient(gdsSendReqDTO.getServerUrl(), gdsSendReqDTO.getAppkey(), gdsSendReqDTO.getAppscret());
    	ItemAddRequest req = new ItemAddRequest();
    	req.setSkuSpecIds("123,123,1243");
    	req.setSkuDeliveryTimes("123,123,1243");
    	req.setSkuHdLength("20,30,30");
    	req.setSkuHdHeight("15-25,25-50,25-50");
    	req.setSkuHdLampQuantity("3,5,7");
    	req.setInputStr("123123");
    	req.setInputPids("12312312");
    	req.setNum(30L);
    	req.setPrice("200.07");
    	req.setType("fixed");
    	req.setStuffStatus("new");
    	req.setTitle("Nokia N97全新行货");
    	req.setDesc("这是一个好商品");
    	req.setLocationState("浙江");
    	req.setLocationCity("杭州");
    	req.setApproveStatus("onsale");
    	req.setCid(1512L);
    	req.setProps("20000:33564;21514:38489");
    	req.setFreightPayer("buyer");
    	req.setValidThru(7L);
    	req.setHasInvoice(true);
    	req.setHasWarranty(true);
    	req.setAutoRepost(true);
    	req.setHasShowcase(true);
    	req.setSellerCids("1101,1102,1103");
    	req.setHasDiscount(true);
    	req.setPostFee("5.07");
    	req.setExpressFee("15.07");
    	req.setEmsFee("25.07");
    	//req.setListTime(StringUtils.parseDateTime("2000-01-01 00:00:00"));
    	req.setIncrement("2.50");
    	//req.setImage(new FileItem("/tmp/file.txt"));
    	req.setPostageId(775752L);
    	req.setAuctionPoint(5L);
    	//req.setPropertyAlias("pid:vid:别名,pid1:vid1:别名1");
    	//req.setSkuProperties("pid:vid,pid:vid");
    	req.setSkuProperties("1:2,11:12");
    	req.setSkuQuantities("2,3");
    	req.setSkuPrices("200.07,111.00");
    	req.setSkuOuterIds("1234,1342");
    	req.setLang("zh_CN");
    	req.setOuterId("12345,123456");
    	req.setProductId(123456789L);
    	req.setPicPath("i7/T1rfxpXcVhXXXH9QcZ_033150.jpg");
    	req.setAutoFill("time_card");
    	req.setIsTaobao(true);
    	req.setIsEx(true);
    	req.setIs3D(true);
    	req.setSellPromise(true);
    	req.setCodPostageId(53899L);
    	req.setIsLightningConsignment(true);
    	req.setWeight(100L);
    	req.setShape("0");
    	req.setIsXinpin(false);
    	req.setSubStock(1L);
    	req.setFoodSecurityPrdLicenseNo("QS410006010388");
    	req.setFoodSecurityDesignCode("Q/DHL.001-2008");
    	req.setFoodSecurityFactory("远东恒天然乳品有限公司");
    	req.setFoodSecurityFactorySite("台北市仁爱路4段85号");
    	req.setFoodSecurityContact("00800-021216");
    	req.setFoodSecurityMix("有机乳糖、有机植物油");
    	req.setFoodSecurityPlanStorage("常温");
    	req.setFoodSecurityPeriod("2年");
    	req.setFoodSecurityFoodAdditive("磷脂 、膨松剂");
    	req.setFoodSecuritySupplier("深圳岸通商贸有限公司");
    	req.setFoodSecurityProductDateStart("2012-06-01");
    	req.setFoodSecurityProductDateEnd("2012-06-10");
    	req.setFoodSecurityStockDateStart("2012-06-20");
    	req.setFoodSecurityStockDateEnd("2012-06-30");
    	req.setScenicTicketPayWay(1L);
    	req.setScenicTicketBookCost("5.99");
    	req.setItemSize("bulk:8");
    	req.setItemWeight("10");
    	req.setSellPoint("2013新款 时尚 前卫");
    	req.setFoodSecurityHealthProductNo("卫食健字(1997)第167号");
    	req.setBarcode("6903244981002");
    	req.setSkuBarcode("6903244981002");
    	req.setNewprepay("1");
    	req.setQualification("string");
    	req.setO2oBindService(true);
    	req.setFeatures(";mysize_tp:12586;keyx:valuex;");
    	req.setLocalityLifeObs("1");
    	req.setLocalityLifeVersion("1");
    	req.setLocalityLifePackageid("12344");
    	req.setIgnorewarning(",ifd_warning,FakeCredit_Warning,");
    	req.setMsPaymentReferencePrice("199");
    	req.setMsPaymentVoucherPrice("50");
    	req.setMsPaymentPrice("100");
    	req.setAfterSaleId(12323L);
    	req.setDeliveryTimeNeedDeliveryTime("none/item/sku");
    	req.setDeliveryTimeDeliveryTimeType("absolute，relative");
    	req.setDeliveryTimeDeliveryTime("2013-11-11");
    	req.setChangeProp("pid:vid:vid1,vid2,vid3;pid:vid:vid1,vid2");
    	req.setDescModules("[{\"module_id\":123,\"module_name\":\"模特图\",\"type\":\"cat_mod\",\"content\":\"模特要漂亮一点拜托\"},{\"module_id\":null,\"module_name\":\"店主最漂亮\",\"type\":\"usr_mod\",\"content\":\"老娘全网最美\"}]");
    	req.setIsOffline("1");
    	req.setWirelessDesc("无线宝贝描述，亲");
    	req.setSpuConfirm(false);
    	req.setLocalityLifeChooseLogis("0");
    	req.setLocalityLifeExpirydate("2012-08-06,2012-08-16");
    	req.setLocalityLifeNetworkId("5645746");
    	req.setLocalityLifeMerchant("56879:码商X");
    	req.setLocalityLifeVerification("101");
    	req.setLocalityLifeRefundRatio(50L);
    	req.setLocalityLifeOnsaleAutoRefundRatio(80L);
    	req.setLocalityLifeRefundmafee("b");
    	req.setLocalityLifeEticket(";market:eticket;consume_way:4;");
    	req.setPaimaiInfoMode(1L);
    	req.setPaimaiInfoDeposit(20L);
    	req.setPaimaiInfoInterval(5L);
    	req.setPaimaiInfoReserve("11");
    	req.setPaimaiInfoValidHour(2L);
    	req.setPaimaiInfoValidMinute(22L);
    	req.setGlobalStockType("1");
    	req.setGlobalStockCountry("美国");
    	req.setSupportCustomMade(true);
    	req.setCustomMadeTypeId("12");
    	req.setGlobalStockDeliveryPlace("1");
    	req.setGlobalStockTaxFreePromise(false);
    	req.setChaoshiExtendsInfo("{\"mchaoshi_group\":\"个清洗护\"}");
    	req.setInputCustomCpv("1627207:-1:渐变红;1627207:-2:宝石蓝");
    	req.setCpvMemo("1627207:28341:颜色偏黑;1627207:3232483:颜色偏淡");
    	ItemAddResponse rsp;
		try {
			//验证参数是否正确
			req.check();
			//获得token
			String token=defaultTokenSV.fetchShopToken(gdsSendReqDTO.findBaseShopAuth());
			
			//有些方法不需要传入token 具体查看每个api的头部说明
			rsp = client.execute(req, token);
			
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			throw new BusinessException(e.toString());
		}
    	
    	return rsp.getMsg();
    }*/
}

