package com.zengshi.ecp.prom.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PromGiftCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PromGiftCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPromIdIsNull() {
            addCriterion("PROM_ID is null");
            return (Criteria) this;
        }

        public Criteria andPromIdIsNotNull() {
            addCriterion("PROM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPromIdEqualTo(Long value) {
            addCriterion("PROM_ID =", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotEqualTo(Long value) {
            addCriterion("PROM_ID <>", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdGreaterThan(Long value) {
            addCriterion("PROM_ID >", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROM_ID >=", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdLessThan(Long value) {
            addCriterion("PROM_ID <", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdLessThanOrEqualTo(Long value) {
            addCriterion("PROM_ID <=", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdIn(List<Long> values) {
            addCriterion("PROM_ID in", values, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotIn(List<Long> values) {
            addCriterion("PROM_ID not in", values, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdBetween(Long value1, Long value2) {
            addCriterion("PROM_ID between", value1, value2, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotBetween(Long value1, Long value2) {
            addCriterion("PROM_ID not between", value1, value2, "promId");
            return (Criteria) this;
        }

        public Criteria andGiftIdIsNull() {
            addCriterion("GIFT_ID is null");
            return (Criteria) this;
        }

        public Criteria andGiftIdIsNotNull() {
            addCriterion("GIFT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGiftIdEqualTo(Long value) {
            addCriterion("GIFT_ID =", value, "giftId");
            return (Criteria) this;
        }

        public Criteria andGiftIdNotEqualTo(Long value) {
            addCriterion("GIFT_ID <>", value, "giftId");
            return (Criteria) this;
        }

        public Criteria andGiftIdGreaterThan(Long value) {
            addCriterion("GIFT_ID >", value, "giftId");
            return (Criteria) this;
        }

        public Criteria andGiftIdGreaterThanOrEqualTo(Long value) {
            addCriterion("GIFT_ID >=", value, "giftId");
            return (Criteria) this;
        }

        public Criteria andGiftIdLessThan(Long value) {
            addCriterion("GIFT_ID <", value, "giftId");
            return (Criteria) this;
        }

        public Criteria andGiftIdLessThanOrEqualTo(Long value) {
            addCriterion("GIFT_ID <=", value, "giftId");
            return (Criteria) this;
        }

        public Criteria andGiftIdIn(List<Long> values) {
            addCriterion("GIFT_ID in", values, "giftId");
            return (Criteria) this;
        }

        public Criteria andGiftIdNotIn(List<Long> values) {
            addCriterion("GIFT_ID not in", values, "giftId");
            return (Criteria) this;
        }

        public Criteria andGiftIdBetween(Long value1, Long value2) {
            addCriterion("GIFT_ID between", value1, value2, "giftId");
            return (Criteria) this;
        }

        public Criteria andGiftIdNotBetween(Long value1, Long value2) {
            addCriterion("GIFT_ID not between", value1, value2, "giftId");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNull() {
            addCriterion("SHOP_ID is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("SHOP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(Long value) {
            addCriterion("SHOP_ID =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(Long value) {
            addCriterion("SHOP_ID <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(Long value) {
            addCriterion("SHOP_ID >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SHOP_ID >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(Long value) {
            addCriterion("SHOP_ID <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Long value) {
            addCriterion("SHOP_ID <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<Long> values) {
            addCriterion("SHOP_ID in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<Long> values) {
            addCriterion("SHOP_ID not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(Long value1, Long value2) {
            addCriterion("SHOP_ID between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(Long value1, Long value2) {
            addCriterion("SHOP_ID not between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andGdsIdIsNull() {
            addCriterion("GDS_ID is null");
            return (Criteria) this;
        }

        public Criteria andGdsIdIsNotNull() {
            addCriterion("GDS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGdsIdEqualTo(Long value) {
            addCriterion("GDS_ID =", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdNotEqualTo(Long value) {
            addCriterion("GDS_ID <>", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdGreaterThan(Long value) {
            addCriterion("GDS_ID >", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("GDS_ID >=", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdLessThan(Long value) {
            addCriterion("GDS_ID <", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdLessThanOrEqualTo(Long value) {
            addCriterion("GDS_ID <=", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdIn(List<Long> values) {
            addCriterion("GDS_ID in", values, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdNotIn(List<Long> values) {
            addCriterion("GDS_ID not in", values, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdBetween(Long value1, Long value2) {
            addCriterion("GDS_ID between", value1, value2, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdNotBetween(Long value1, Long value2) {
            addCriterion("GDS_ID not between", value1, value2, "gdsId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNull() {
            addCriterion("SKU_ID is null");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNotNull() {
            addCriterion("SKU_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSkuIdEqualTo(Long value) {
            addCriterion("SKU_ID =", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotEqualTo(Long value) {
            addCriterion("SKU_ID <>", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThan(Long value) {
            addCriterion("SKU_ID >", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SKU_ID >=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThan(Long value) {
            addCriterion("SKU_ID <", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThanOrEqualTo(Long value) {
            addCriterion("SKU_ID <=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIn(List<Long> values) {
            addCriterion("SKU_ID in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotIn(List<Long> values) {
            addCriterion("SKU_ID not in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdBetween(Long value1, Long value2) {
            addCriterion("SKU_ID between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotBetween(Long value1, Long value2) {
            addCriterion("SKU_ID not between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andPresentAllCntIsNull() {
            addCriterion("PRESENT_ALL_CNT is null");
            return (Criteria) this;
        }

        public Criteria andPresentAllCntIsNotNull() {
            addCriterion("PRESENT_ALL_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andPresentAllCntEqualTo(Long value) {
            addCriterion("PRESENT_ALL_CNT =", value, "presentAllCnt");
            return (Criteria) this;
        }

        public Criteria andPresentAllCntNotEqualTo(Long value) {
            addCriterion("PRESENT_ALL_CNT <>", value, "presentAllCnt");
            return (Criteria) this;
        }

        public Criteria andPresentAllCntGreaterThan(Long value) {
            addCriterion("PRESENT_ALL_CNT >", value, "presentAllCnt");
            return (Criteria) this;
        }

        public Criteria andPresentAllCntGreaterThanOrEqualTo(Long value) {
            addCriterion("PRESENT_ALL_CNT >=", value, "presentAllCnt");
            return (Criteria) this;
        }

        public Criteria andPresentAllCntLessThan(Long value) {
            addCriterion("PRESENT_ALL_CNT <", value, "presentAllCnt");
            return (Criteria) this;
        }

        public Criteria andPresentAllCntLessThanOrEqualTo(Long value) {
            addCriterion("PRESENT_ALL_CNT <=", value, "presentAllCnt");
            return (Criteria) this;
        }

        public Criteria andPresentAllCntIn(List<Long> values) {
            addCriterion("PRESENT_ALL_CNT in", values, "presentAllCnt");
            return (Criteria) this;
        }

        public Criteria andPresentAllCntNotIn(List<Long> values) {
            addCriterion("PRESENT_ALL_CNT not in", values, "presentAllCnt");
            return (Criteria) this;
        }

        public Criteria andPresentAllCntBetween(Long value1, Long value2) {
            addCriterion("PRESENT_ALL_CNT between", value1, value2, "presentAllCnt");
            return (Criteria) this;
        }

        public Criteria andPresentAllCntNotBetween(Long value1, Long value2) {
            addCriterion("PRESENT_ALL_CNT not between", value1, value2, "presentAllCnt");
            return (Criteria) this;
        }

        public Criteria andEveryTimeCntIsNull() {
            addCriterion("EVERY_TIME_CNT is null");
            return (Criteria) this;
        }

        public Criteria andEveryTimeCntIsNotNull() {
            addCriterion("EVERY_TIME_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andEveryTimeCntEqualTo(Long value) {
            addCriterion("EVERY_TIME_CNT =", value, "everyTimeCnt");
            return (Criteria) this;
        }

        public Criteria andEveryTimeCntNotEqualTo(Long value) {
            addCriterion("EVERY_TIME_CNT <>", value, "everyTimeCnt");
            return (Criteria) this;
        }

        public Criteria andEveryTimeCntGreaterThan(Long value) {
            addCriterion("EVERY_TIME_CNT >", value, "everyTimeCnt");
            return (Criteria) this;
        }

        public Criteria andEveryTimeCntGreaterThanOrEqualTo(Long value) {
            addCriterion("EVERY_TIME_CNT >=", value, "everyTimeCnt");
            return (Criteria) this;
        }

        public Criteria andEveryTimeCntLessThan(Long value) {
            addCriterion("EVERY_TIME_CNT <", value, "everyTimeCnt");
            return (Criteria) this;
        }

        public Criteria andEveryTimeCntLessThanOrEqualTo(Long value) {
            addCriterion("EVERY_TIME_CNT <=", value, "everyTimeCnt");
            return (Criteria) this;
        }

        public Criteria andEveryTimeCntIn(List<Long> values) {
            addCriterion("EVERY_TIME_CNT in", values, "everyTimeCnt");
            return (Criteria) this;
        }

        public Criteria andEveryTimeCntNotIn(List<Long> values) {
            addCriterion("EVERY_TIME_CNT not in", values, "everyTimeCnt");
            return (Criteria) this;
        }

        public Criteria andEveryTimeCntBetween(Long value1, Long value2) {
            addCriterion("EVERY_TIME_CNT between", value1, value2, "everyTimeCnt");
            return (Criteria) this;
        }

        public Criteria andEveryTimeCntNotBetween(Long value1, Long value2) {
            addCriterion("EVERY_TIME_CNT not between", value1, value2, "everyTimeCnt");
            return (Criteria) this;
        }

        public Criteria andPresentCntIsNull() {
            addCriterion("PRESENT_CNT is null");
            return (Criteria) this;
        }

        public Criteria andPresentCntIsNotNull() {
            addCriterion("PRESENT_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andPresentCntEqualTo(Long value) {
            addCriterion("PRESENT_CNT =", value, "presentCnt");
            return (Criteria) this;
        }

        public Criteria andPresentCntNotEqualTo(Long value) {
            addCriterion("PRESENT_CNT <>", value, "presentCnt");
            return (Criteria) this;
        }

        public Criteria andPresentCntGreaterThan(Long value) {
            addCriterion("PRESENT_CNT >", value, "presentCnt");
            return (Criteria) this;
        }

        public Criteria andPresentCntGreaterThanOrEqualTo(Long value) {
            addCriterion("PRESENT_CNT >=", value, "presentCnt");
            return (Criteria) this;
        }

        public Criteria andPresentCntLessThan(Long value) {
            addCriterion("PRESENT_CNT <", value, "presentCnt");
            return (Criteria) this;
        }

        public Criteria andPresentCntLessThanOrEqualTo(Long value) {
            addCriterion("PRESENT_CNT <=", value, "presentCnt");
            return (Criteria) this;
        }

        public Criteria andPresentCntIn(List<Long> values) {
            addCriterion("PRESENT_CNT in", values, "presentCnt");
            return (Criteria) this;
        }

        public Criteria andPresentCntNotIn(List<Long> values) {
            addCriterion("PRESENT_CNT not in", values, "presentCnt");
            return (Criteria) this;
        }

        public Criteria andPresentCntBetween(Long value1, Long value2) {
            addCriterion("PRESENT_CNT between", value1, value2, "presentCnt");
            return (Criteria) this;
        }

        public Criteria andPresentCntNotBetween(Long value1, Long value2) {
            addCriterion("PRESENT_CNT not between", value1, value2, "presentCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntIsNull() {
            addCriterion("REMAIND_CNT is null");
            return (Criteria) this;
        }

        public Criteria andRemaindCntIsNotNull() {
            addCriterion("REMAIND_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andRemaindCntEqualTo(Long value) {
            addCriterion("REMAIND_CNT =", value, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntNotEqualTo(Long value) {
            addCriterion("REMAIND_CNT <>", value, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntGreaterThan(Long value) {
            addCriterion("REMAIND_CNT >", value, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntGreaterThanOrEqualTo(Long value) {
            addCriterion("REMAIND_CNT >=", value, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntLessThan(Long value) {
            addCriterion("REMAIND_CNT <", value, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntLessThanOrEqualTo(Long value) {
            addCriterion("REMAIND_CNT <=", value, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntIn(List<Long> values) {
            addCriterion("REMAIND_CNT in", values, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntNotIn(List<Long> values) {
            addCriterion("REMAIND_CNT not in", values, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntBetween(Long value1, Long value2) {
            addCriterion("REMAIND_CNT between", value1, value2, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andRemaindCntNotBetween(Long value1, Long value2) {
            addCriterion("REMAIND_CNT not between", value1, value2, "remaindCnt");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Timestamp value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Timestamp value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Timestamp> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Timestamp> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIsNull() {
            addCriterion("CREATE_STAFF is null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIsNotNull() {
            addCriterion("CREATE_STAFF is not null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffEqualTo(Long value) {
            addCriterion("CREATE_STAFF =", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotEqualTo(Long value) {
            addCriterion("CREATE_STAFF <>", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffGreaterThan(Long value) {
            addCriterion("CREATE_STAFF >", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffGreaterThanOrEqualTo(Long value) {
            addCriterion("CREATE_STAFF >=", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLessThan(Long value) {
            addCriterion("CREATE_STAFF <", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLessThanOrEqualTo(Long value) {
            addCriterion("CREATE_STAFF <=", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIn(List<Long> values) {
            addCriterion("CREATE_STAFF in", values, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotIn(List<Long> values) {
            addCriterion("CREATE_STAFF not in", values, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffBetween(Long value1, Long value2) {
            addCriterion("CREATE_STAFF between", value1, value2, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotBetween(Long value1, Long value2) {
            addCriterion("CREATE_STAFF not between", value1, value2, "createStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Timestamp value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Timestamp value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Timestamp value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Timestamp value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Timestamp> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Timestamp> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIsNull() {
            addCriterion("UPDATE_STAFF is null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIsNotNull() {
            addCriterion("UPDATE_STAFF is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffEqualTo(Long value) {
            addCriterion("UPDATE_STAFF =", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotEqualTo(Long value) {
            addCriterion("UPDATE_STAFF <>", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffGreaterThan(Long value) {
            addCriterion("UPDATE_STAFF >", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffGreaterThanOrEqualTo(Long value) {
            addCriterion("UPDATE_STAFF >=", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLessThan(Long value) {
            addCriterion("UPDATE_STAFF <", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLessThanOrEqualTo(Long value) {
            addCriterion("UPDATE_STAFF <=", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIn(List<Long> values) {
            addCriterion("UPDATE_STAFF in", values, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotIn(List<Long> values) {
            addCriterion("UPDATE_STAFF not in", values, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffBetween(Long value1, Long value2) {
            addCriterion("UPDATE_STAFF between", value1, value2, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotBetween(Long value1, Long value2) {
            addCriterion("UPDATE_STAFF not between", value1, value2, "updateStaff");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}