package com.zengshi.ecp.coupon.dubbo.util;


public class CouponConstants {
	
	public class CoupPara {
        public static final String coupUseLimitSV = "coupUseLimitSV";
        //240:折扣券
        //180:免邮
        //160:单个订单同类型优惠券使用张数 (如果无值系统默认为1张)
        //170:渠道来源使用限制  (1:WEB端,2:手机端,3:微信端等等)
        //150:是否与其他类型优惠券共同使用(0:与所有优惠券共同使用,1:与特定的优惠券共同使用,2:与其他优惠券互斥) 
        //140:满减
        //110:品类限制
        //120:店铺限制
        //130:黑名单限制
        public static final String RULE_CODE_240 = "240";
        public static final String RULE_CODE_180 = "180";
        public static final String RULE_CODE_170 = "170";
        public static final String RULE_CODE_160 = "160";
        public static final String RULE_CODE_150 = "150";
        public static final String RULE_CODE_140 = "140";
        public static final String RULE_CODE_110 = "110";
        public static final String RULE_CODE_120 = "120";
        public static final String RULE_CODE_130 = "130";
        //优惠券类型无限制
        public static final String RULE_CODE_VALUE_0="0";
        
    }
     
    public class CoupType {
        // 1有效 0无效 2草稿
        public static final String STATUS_2 = "2";
        public static final String STATUS_1 = "1";
        public static final String STATUS_0 = "0";
    }
  
    //配置表参数  t_base_param_config
    public class CoupKey {

        //类型状态
        public static final String COUP_TYPE_STATUS = "COUP_TYPE_STATUS";
        //类型类型
        public static final String COUP_TYPE_TYPE_LIMIT = "COUP_TYPE_TYPE_LIMIT";
        //类型类型
        public static final String COUP_PARAM_RULE_TYPE = "COUP_PARAM_RULE_TYPE";
        //优惠券类型
        public static final String COUP_TYPE = "COUP_TYPE";
        //是否支持优惠码
        public static final String COUP_IF_CODE = "COUP_IF_CODE";
        //是否支持退货
        public static final String COUP_IF_BACK = "COUP_IF_BACK";
        
        //有效期类型 0:浮动时间 1:固定时间类型
        public static final String COUP_EFF_TYPE = "COUP_EFF_TYPE";
        
        //是否支持退货
        public static final String COUP_INFO_STATUS = "COUP_INFO_STATUS";
        
        //1 使用key  2 领取key
        public static final String COUP_PARAM_1 = "COUP_PARAM_1";
        public static final String COUP_PARAM_2 = "COUP_PARAM_2";
        
        //1 使用key key=code  2 领取key
        public static final String COUP_PARAM_3 = "COUP_PARAM_3";
        public static final String COUP_PARAM_4 = "COUP_PARAM_4";

    }
    
    public class CoupInfo{
		// 状态（0:失效,1:有效 2:草稿,3.删除,4.系统删除）删除为用户自己删除,系统删除比如是生效改失效先系统删除,再添加
		public static final String STATUS_0 = "0";
		public static final String STATUS_1 = "1";
		public static final String STATUS_2 = "2";
		public static final String STATUS_3 = "3";
		public static final String STATUS_4 = "4";
		
		//是否优惠码 0否 1是
		public static final String IF_CODE_0 = "0";
        public static final String IF_CODE_1 = "1";
        
		// 是否支持退货 0否 1是
		public static final String IF_BACK_0 = "0";
		public static final String IF_BACK_1 = "1";
        
        //有效期类型 0:浮动时间 1:固定时间类型
        public static final String EFF_TYPE_0="0";
        public static final String EFF_TYPE_1="1";
        
    }
    
    public class CoupSys{
    	
    	//模糊查询使用
        public static final String LIKE_PERCENT="%";
        
        //0:平台级别,1:店铺级别
        public static final String TYPE_LIMIT_0="0";
        public static final String TYPE_LIMIT_1="1";
        
        //状态 0:失效,1:有效,2:冻结
        public static final String status_0="0";
        public static final String status_1="1";
        public static final String status_2="2";
        //日志表 0:新增 1:修改
        public static final String status_log_0="0";
        public static final String status_log_1="1";
        
        //0:新增 1:编辑
        public static final String edit_ADD="0";
        public static final String edit_EDIT="1";
        public static final String edit_DELETE="2";
        //排序 0:降序 1:升序
        public static final String sort_0="0";
        public static final String sort_1="1";
        //无限制||平台级
        public static final int infinite=-1;
        
        public static final int shopInfinite=0;
        //优惠券默认单个订单使用1张
        public static final int defaultUseNo=1;
        
        public static final String coupGetLimitSV="coupGetLimitSV";
        //优惠券规则校验 0:不通过 1:通过   2:多个单品组合
        public static final String judge_0="0";
        public static final String judge_1="1";
        public static final String judge_2="2";
        
        //0:优惠券结算校验
        public static final String checkLimit_0="0";
        
        //1:免邮
        public static final String noExpress_1="1";
        //1:折扣优惠券
        public static final String discountCoup_1="1";
    }
    
    public class CoupDetail{
    	//1:可使用 2:已使用  0:已过期 3:已冻结
    	public static final String opeType_0="0";
    	public static final String opeType_1="1";
    	public static final String opeType_2="2";
    	public static final String opeType_3="3";
    	//优惠券是否使用 0:未使用 , 1:使用
    	public static final String IF_USE_0="0";
    	public static final String IF_USE_1="1";
    	
    	//0:降序
    	public static final String sort_0="0";
    	// 1:升序
    	public static final String sort_1="1";
    	
    	public static final String DESC="DESC";
    	public static final String ASC="ASC";
    	//优惠券来源,领取的方式  10:主动领取, 20被动领取
    	public static final String COUP_SOURCE_10="10";
    	public static final String COUP_SOURCE_20="20";
    	
    	public static final String update_ident="用户信息修改";
    	
    	public static final String RLUE_110="110";
    	public static final String RLUE_130="130";
    	public static final String RLUE_110130_EXPLAIN="品类限制";
    	public static final String RLUE_140="140";
    	public static final String RLUE_140_EXPLAIN="满";
    	public static final String RLUE_EXPLAIN="全品类";
    	
    	//0:不展示  1:新(2天内领取) 2:快过期(离到期时间3天以内)  用来前店表示已过期或已使用
    	public static final String coupStatus_0="0";
    	public static final String coupStatus_1="1";
    	public static final String coupStatus_2="2";
    	public static final String coupStatus_3="3";
    }
    
    public class CoupBlackLimit{
        //黑名单品类类型信息 0:单品级,1:商品级,2:分类级别 
        public static final String CATEGORY_TYPE_0="0";
        public static final String CATEGORY_TYPE_1="1";
        public static final String CATEGORY_TYPE_2="2";
        //状态 0:失效,1:有效
        public static final String STATUS_0="0";
        public static final String STATUS_1="1";
    }
    public class CoupFullLimit{
        //满减类型 1:满金额2满数量
        public static final String TYPE_1="1";
        public static final String TYPE_2="2";
    }
    public class CoupUseLimit{
        //10:单个订单下同类型优惠券使用张数 (如果无值系统默认为1张)
        //20:渠道来源使用限制 (0:WEB端,1:手机端,3:微信端等等): (如果无设置限制信息系统默认为无限制)
        public static final String USE_RULE_KEY_10="10";
        public static final String USE_RULE_KEY_20="20";
    }
    //分类 商品 单品 限制
    public class CoupCatgLimit{
        //0单品 1商品 2分类
        public static final String CATEGORY_TYPE_0="0";
        public static final String CATEGORY_TYPE_1="1";
        public static final String CATEGORY_TYPE_2="2";
    }
    
    public class CoupConsume{
    	//操作类型（10：订单消费，11：订单退货）
    	public static final String operType_10="10";
    	public static final String operType_11="11";
    }
    
    public class CoupError{
		
		public static final String coupon_error_450001="coupon.450001";
		public static final String coupon_error_450002="coupon.450002";
		public static final String coupon_error_450003="coupon.450003";
		public static final String coupon_error_450004="coupon.450004";
		public static final String coupon_error_450005="coupon.450005";
		public static final String coupon_error_450006="coupon.450006";
		public static final String coupon_error_450007="coupon.error.450007";
		public static final String coupon_error_450008="coupon.error.450008";
		public static final String coupon_error_450009="coupon.error.450009";
		public static final String coupon_error_450010="coupon_error_450010";//有效
		public static final String coupon_error_450011="coupon_error_450011";//失效
		public static final String coupon_error_450012="coupon.error.450012";
		public static final String coupon_error_450013="coupon.error.450013";
		public static final String coupon_error_450039="coupon.error.450039";
    }
    
    public class CoupElse{
    	public static final String  e1="仅可购买";
    	public static final String  e2="等商品";
    	public static final String  e3="全品类支持";
    	public static final String  e4="支持部分商品";
    	public static final String  e5="购买促销商品达到特定数量使用";
    }
    
    public class CoupBatch{
    	
    	//状态 0:失效,1:有效,2:已经执行
        public static final String status_0="0";
        public static final String status_1="1";
        public static final String status_2="2";
        
        //0:用户类型,1:金额类型(交易金额范围,交易次数)
        public static final String paraType_0="0";
        public static final String paraType_1="1";
    }
    
}
