package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GdsGiftCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsGiftCriteria() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
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

        public Criteria andGiftPicIsNull() {
            addCriterion("GIFT_PIC is null");
            return (Criteria) this;
        }

        public Criteria andGiftPicIsNotNull() {
            addCriterion("GIFT_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andGiftPicEqualTo(String value) {
            addCriterion("GIFT_PIC =", value, "giftPic");
            return (Criteria) this;
        }

        public Criteria andGiftPicNotEqualTo(String value) {
            addCriterion("GIFT_PIC <>", value, "giftPic");
            return (Criteria) this;
        }

        public Criteria andGiftPicGreaterThan(String value) {
            addCriterion("GIFT_PIC >", value, "giftPic");
            return (Criteria) this;
        }

        public Criteria andGiftPicGreaterThanOrEqualTo(String value) {
            addCriterion("GIFT_PIC >=", value, "giftPic");
            return (Criteria) this;
        }

        public Criteria andGiftPicLessThan(String value) {
            addCriterion("GIFT_PIC <", value, "giftPic");
            return (Criteria) this;
        }

        public Criteria andGiftPicLessThanOrEqualTo(String value) {
            addCriterion("GIFT_PIC <=", value, "giftPic");
            return (Criteria) this;
        }

        public Criteria andGiftPicLike(String value) {
            addCriterion("GIFT_PIC like", value, "giftPic");
            return (Criteria) this;
        }

        public Criteria andGiftPicNotLike(String value) {
            addCriterion("GIFT_PIC not like", value, "giftPic");
            return (Criteria) this;
        }

        public Criteria andGiftPicIn(List<String> values) {
            addCriterion("GIFT_PIC in", values, "giftPic");
            return (Criteria) this;
        }

        public Criteria andGiftPicNotIn(List<String> values) {
            addCriterion("GIFT_PIC not in", values, "giftPic");
            return (Criteria) this;
        }

        public Criteria andGiftPicBetween(String value1, String value2) {
            addCriterion("GIFT_PIC between", value1, value2, "giftPic");
            return (Criteria) this;
        }

        public Criteria andGiftPicNotBetween(String value1, String value2) {
            addCriterion("GIFT_PIC not between", value1, value2, "giftPic");
            return (Criteria) this;
        }

        public Criteria andGiftNameIsNull() {
            addCriterion("GIFT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGiftNameIsNotNull() {
            addCriterion("GIFT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGiftNameEqualTo(String value) {
            addCriterion("GIFT_NAME =", value, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameNotEqualTo(String value) {
            addCriterion("GIFT_NAME <>", value, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameGreaterThan(String value) {
            addCriterion("GIFT_NAME >", value, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameGreaterThanOrEqualTo(String value) {
            addCriterion("GIFT_NAME >=", value, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameLessThan(String value) {
            addCriterion("GIFT_NAME <", value, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameLessThanOrEqualTo(String value) {
            addCriterion("GIFT_NAME <=", value, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameLike(String value) {
            addCriterion("GIFT_NAME like", value, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameNotLike(String value) {
            addCriterion("GIFT_NAME not like", value, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameIn(List<String> values) {
            addCriterion("GIFT_NAME in", values, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameNotIn(List<String> values) {
            addCriterion("GIFT_NAME not in", values, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameBetween(String value1, String value2) {
            addCriterion("GIFT_NAME between", value1, value2, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftNameNotBetween(String value1, String value2) {
            addCriterion("GIFT_NAME not between", value1, value2, "giftName");
            return (Criteria) this;
        }

        public Criteria andGiftDescIsNull() {
            addCriterion("GIFT_DESC is null");
            return (Criteria) this;
        }

        public Criteria andGiftDescIsNotNull() {
            addCriterion("GIFT_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andGiftDescEqualTo(String value) {
            addCriterion("GIFT_DESC =", value, "giftDesc");
            return (Criteria) this;
        }

        public Criteria andGiftDescNotEqualTo(String value) {
            addCriterion("GIFT_DESC <>", value, "giftDesc");
            return (Criteria) this;
        }

        public Criteria andGiftDescGreaterThan(String value) {
            addCriterion("GIFT_DESC >", value, "giftDesc");
            return (Criteria) this;
        }

        public Criteria andGiftDescGreaterThanOrEqualTo(String value) {
            addCriterion("GIFT_DESC >=", value, "giftDesc");
            return (Criteria) this;
        }

        public Criteria andGiftDescLessThan(String value) {
            addCriterion("GIFT_DESC <", value, "giftDesc");
            return (Criteria) this;
        }

        public Criteria andGiftDescLessThanOrEqualTo(String value) {
            addCriterion("GIFT_DESC <=", value, "giftDesc");
            return (Criteria) this;
        }

        public Criteria andGiftDescLike(String value) {
            addCriterion("GIFT_DESC like", value, "giftDesc");
            return (Criteria) this;
        }

        public Criteria andGiftDescNotLike(String value) {
            addCriterion("GIFT_DESC not like", value, "giftDesc");
            return (Criteria) this;
        }

        public Criteria andGiftDescIn(List<String> values) {
            addCriterion("GIFT_DESC in", values, "giftDesc");
            return (Criteria) this;
        }

        public Criteria andGiftDescNotIn(List<String> values) {
            addCriterion("GIFT_DESC not in", values, "giftDesc");
            return (Criteria) this;
        }

        public Criteria andGiftDescBetween(String value1, String value2) {
            addCriterion("GIFT_DESC between", value1, value2, "giftDesc");
            return (Criteria) this;
        }

        public Criteria andGiftDescNotBetween(String value1, String value2) {
            addCriterion("GIFT_DESC not between", value1, value2, "giftDesc");
            return (Criteria) this;
        }

        public Criteria andGiftValueIsNull() {
            addCriterion("GIFT_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andGiftValueIsNotNull() {
            addCriterion("GIFT_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andGiftValueEqualTo(Long value) {
            addCriterion("GIFT_VALUE =", value, "giftValue");
            return (Criteria) this;
        }

        public Criteria andGiftValueNotEqualTo(Long value) {
            addCriterion("GIFT_VALUE <>", value, "giftValue");
            return (Criteria) this;
        }

        public Criteria andGiftValueGreaterThan(Long value) {
            addCriterion("GIFT_VALUE >", value, "giftValue");
            return (Criteria) this;
        }

        public Criteria andGiftValueGreaterThanOrEqualTo(Long value) {
            addCriterion("GIFT_VALUE >=", value, "giftValue");
            return (Criteria) this;
        }

        public Criteria andGiftValueLessThan(Long value) {
            addCriterion("GIFT_VALUE <", value, "giftValue");
            return (Criteria) this;
        }

        public Criteria andGiftValueLessThanOrEqualTo(Long value) {
            addCriterion("GIFT_VALUE <=", value, "giftValue");
            return (Criteria) this;
        }

        public Criteria andGiftValueIn(List<Long> values) {
            addCriterion("GIFT_VALUE in", values, "giftValue");
            return (Criteria) this;
        }

        public Criteria andGiftValueNotIn(List<Long> values) {
            addCriterion("GIFT_VALUE not in", values, "giftValue");
            return (Criteria) this;
        }

        public Criteria andGiftValueBetween(Long value1, Long value2) {
            addCriterion("GIFT_VALUE between", value1, value2, "giftValue");
            return (Criteria) this;
        }

        public Criteria andGiftValueNotBetween(Long value1, Long value2) {
            addCriterion("GIFT_VALUE not between", value1, value2, "giftValue");
            return (Criteria) this;
        }

        public Criteria andGiftStatusIsNull() {
            addCriterion("GIFT_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andGiftStatusIsNotNull() {
            addCriterion("GIFT_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andGiftStatusEqualTo(String value) {
            addCriterion("GIFT_STATUS =", value, "giftStatus");
            return (Criteria) this;
        }

        public Criteria andGiftStatusNotEqualTo(String value) {
            addCriterion("GIFT_STATUS <>", value, "giftStatus");
            return (Criteria) this;
        }

        public Criteria andGiftStatusGreaterThan(String value) {
            addCriterion("GIFT_STATUS >", value, "giftStatus");
            return (Criteria) this;
        }

        public Criteria andGiftStatusGreaterThanOrEqualTo(String value) {
            addCriterion("GIFT_STATUS >=", value, "giftStatus");
            return (Criteria) this;
        }

        public Criteria andGiftStatusLessThan(String value) {
            addCriterion("GIFT_STATUS <", value, "giftStatus");
            return (Criteria) this;
        }

        public Criteria andGiftStatusLessThanOrEqualTo(String value) {
            addCriterion("GIFT_STATUS <=", value, "giftStatus");
            return (Criteria) this;
        }

        public Criteria andGiftStatusLike(String value) {
            addCriterion("GIFT_STATUS like", value, "giftStatus");
            return (Criteria) this;
        }

        public Criteria andGiftStatusNotLike(String value) {
            addCriterion("GIFT_STATUS not like", value, "giftStatus");
            return (Criteria) this;
        }

        public Criteria andGiftStatusIn(List<String> values) {
            addCriterion("GIFT_STATUS in", values, "giftStatus");
            return (Criteria) this;
        }

        public Criteria andGiftStatusNotIn(List<String> values) {
            addCriterion("GIFT_STATUS not in", values, "giftStatus");
            return (Criteria) this;
        }

        public Criteria andGiftStatusBetween(String value1, String value2) {
            addCriterion("GIFT_STATUS between", value1, value2, "giftStatus");
            return (Criteria) this;
        }

        public Criteria andGiftStatusNotBetween(String value1, String value2) {
            addCriterion("GIFT_STATUS not between", value1, value2, "giftStatus");
            return (Criteria) this;
        }

        public Criteria andGiftAmountIsNull() {
            addCriterion("GIFT_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andGiftAmountIsNotNull() {
            addCriterion("GIFT_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andGiftAmountEqualTo(Long value) {
            addCriterion("GIFT_AMOUNT =", value, "giftAmount");
            return (Criteria) this;
        }

        public Criteria andGiftAmountNotEqualTo(Long value) {
            addCriterion("GIFT_AMOUNT <>", value, "giftAmount");
            return (Criteria) this;
        }

        public Criteria andGiftAmountGreaterThan(Long value) {
            addCriterion("GIFT_AMOUNT >", value, "giftAmount");
            return (Criteria) this;
        }

        public Criteria andGiftAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("GIFT_AMOUNT >=", value, "giftAmount");
            return (Criteria) this;
        }

        public Criteria andGiftAmountLessThan(Long value) {
            addCriterion("GIFT_AMOUNT <", value, "giftAmount");
            return (Criteria) this;
        }

        public Criteria andGiftAmountLessThanOrEqualTo(Long value) {
            addCriterion("GIFT_AMOUNT <=", value, "giftAmount");
            return (Criteria) this;
        }

        public Criteria andGiftAmountIn(List<Long> values) {
            addCriterion("GIFT_AMOUNT in", values, "giftAmount");
            return (Criteria) this;
        }

        public Criteria andGiftAmountNotIn(List<Long> values) {
            addCriterion("GIFT_AMOUNT not in", values, "giftAmount");
            return (Criteria) this;
        }

        public Criteria andGiftAmountBetween(Long value1, Long value2) {
            addCriterion("GIFT_AMOUNT between", value1, value2, "giftAmount");
            return (Criteria) this;
        }

        public Criteria andGiftAmountNotBetween(Long value1, Long value2) {
            addCriterion("GIFT_AMOUNT not between", value1, value2, "giftAmount");
            return (Criteria) this;
        }

        public Criteria andGiftSendIsNull() {
            addCriterion("GIFT_SEND is null");
            return (Criteria) this;
        }

        public Criteria andGiftSendIsNotNull() {
            addCriterion("GIFT_SEND is not null");
            return (Criteria) this;
        }

        public Criteria andGiftSendEqualTo(Long value) {
            addCriterion("GIFT_SEND =", value, "giftSend");
            return (Criteria) this;
        }

        public Criteria andGiftSendNotEqualTo(Long value) {
            addCriterion("GIFT_SEND <>", value, "giftSend");
            return (Criteria) this;
        }

        public Criteria andGiftSendGreaterThan(Long value) {
            addCriterion("GIFT_SEND >", value, "giftSend");
            return (Criteria) this;
        }

        public Criteria andGiftSendGreaterThanOrEqualTo(Long value) {
            addCriterion("GIFT_SEND >=", value, "giftSend");
            return (Criteria) this;
        }

        public Criteria andGiftSendLessThan(Long value) {
            addCriterion("GIFT_SEND <", value, "giftSend");
            return (Criteria) this;
        }

        public Criteria andGiftSendLessThanOrEqualTo(Long value) {
            addCriterion("GIFT_SEND <=", value, "giftSend");
            return (Criteria) this;
        }

        public Criteria andGiftSendIn(List<Long> values) {
            addCriterion("GIFT_SEND in", values, "giftSend");
            return (Criteria) this;
        }

        public Criteria andGiftSendNotIn(List<Long> values) {
            addCriterion("GIFT_SEND not in", values, "giftSend");
            return (Criteria) this;
        }

        public Criteria andGiftSendBetween(Long value1, Long value2) {
            addCriterion("GIFT_SEND between", value1, value2, "giftSend");
            return (Criteria) this;
        }

        public Criteria andGiftSendNotBetween(Long value1, Long value2) {
            addCriterion("GIFT_SEND not between", value1, value2, "giftSend");
            return (Criteria) this;
        }

        public Criteria andGiftValidIsNull() {
            addCriterion("GIFT_VALID is null");
            return (Criteria) this;
        }

        public Criteria andGiftValidIsNotNull() {
            addCriterion("GIFT_VALID is not null");
            return (Criteria) this;
        }

        public Criteria andGiftValidEqualTo(Long value) {
            addCriterion("GIFT_VALID =", value, "giftValid");
            return (Criteria) this;
        }

        public Criteria andGiftValidNotEqualTo(Long value) {
            addCriterion("GIFT_VALID <>", value, "giftValid");
            return (Criteria) this;
        }

        public Criteria andGiftValidGreaterThan(Long value) {
            addCriterion("GIFT_VALID >", value, "giftValid");
            return (Criteria) this;
        }

        public Criteria andGiftValidGreaterThanOrEqualTo(Long value) {
            addCriterion("GIFT_VALID >=", value, "giftValid");
            return (Criteria) this;
        }

        public Criteria andGiftValidLessThan(Long value) {
            addCriterion("GIFT_VALID <", value, "giftValid");
            return (Criteria) this;
        }

        public Criteria andGiftValidLessThanOrEqualTo(Long value) {
            addCriterion("GIFT_VALID <=", value, "giftValid");
            return (Criteria) this;
        }

        public Criteria andGiftValidIn(List<Long> values) {
            addCriterion("GIFT_VALID in", values, "giftValid");
            return (Criteria) this;
        }

        public Criteria andGiftValidNotIn(List<Long> values) {
            addCriterion("GIFT_VALID not in", values, "giftValid");
            return (Criteria) this;
        }

        public Criteria andGiftValidBetween(Long value1, Long value2) {
            addCriterion("GIFT_VALID between", value1, value2, "giftValid");
            return (Criteria) this;
        }

        public Criteria andGiftValidNotBetween(Long value1, Long value2) {
            addCriterion("GIFT_VALID not between", value1, value2, "giftValid");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Timestamp value) {
            addCriterion("START_TIME =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Timestamp value) {
            addCriterion("START_TIME <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Timestamp value) {
            addCriterion("START_TIME >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("START_TIME >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Timestamp value) {
            addCriterion("START_TIME <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("START_TIME <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Timestamp> values) {
            addCriterion("START_TIME in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Timestamp> values) {
            addCriterion("START_TIME not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("START_TIME between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("START_TIME not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Timestamp value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Timestamp value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Timestamp value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Timestamp value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Timestamp> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Timestamp> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
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

        public Criteria andGiftTypeIsNull() {
            addCriterion("GIFT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andGiftTypeIsNotNull() {
            addCriterion("GIFT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andGiftTypeEqualTo(String value) {
            addCriterion("GIFT_TYPE =", value, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeNotEqualTo(String value) {
            addCriterion("GIFT_TYPE <>", value, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeGreaterThan(String value) {
            addCriterion("GIFT_TYPE >", value, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeGreaterThanOrEqualTo(String value) {
            addCriterion("GIFT_TYPE >=", value, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeLessThan(String value) {
            addCriterion("GIFT_TYPE <", value, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeLessThanOrEqualTo(String value) {
            addCriterion("GIFT_TYPE <=", value, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeLike(String value) {
            addCriterion("GIFT_TYPE like", value, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeNotLike(String value) {
            addCriterion("GIFT_TYPE not like", value, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeIn(List<String> values) {
            addCriterion("GIFT_TYPE in", values, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeNotIn(List<String> values) {
            addCriterion("GIFT_TYPE not in", values, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeBetween(String value1, String value2) {
            addCriterion("GIFT_TYPE between", value1, value2, "giftType");
            return (Criteria) this;
        }

        public Criteria andGiftTypeNotBetween(String value1, String value2) {
            addCriterion("GIFT_TYPE not between", value1, value2, "giftType");
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
