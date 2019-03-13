package com.zengshi.ecp.staff.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShopShipperCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public ShopShipperCriteria() {
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

        public Criteria andShipperNameIsNull() {
            addCriterion("SHIPPER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andShipperNameIsNotNull() {
            addCriterion("SHIPPER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andShipperNameEqualTo(String value) {
            addCriterion("SHIPPER_NAME =", value, "shipperName");
            return (Criteria) this;
        }

        public Criteria andShipperNameNotEqualTo(String value) {
            addCriterion("SHIPPER_NAME <>", value, "shipperName");
            return (Criteria) this;
        }

        public Criteria andShipperNameGreaterThan(String value) {
            addCriterion("SHIPPER_NAME >", value, "shipperName");
            return (Criteria) this;
        }

        public Criteria andShipperNameGreaterThanOrEqualTo(String value) {
            addCriterion("SHIPPER_NAME >=", value, "shipperName");
            return (Criteria) this;
        }

        public Criteria andShipperNameLessThan(String value) {
            addCriterion("SHIPPER_NAME <", value, "shipperName");
            return (Criteria) this;
        }

        public Criteria andShipperNameLessThanOrEqualTo(String value) {
            addCriterion("SHIPPER_NAME <=", value, "shipperName");
            return (Criteria) this;
        }

        public Criteria andShipperNameLike(String value) {
            addCriterion("SHIPPER_NAME like", value, "shipperName");
            return (Criteria) this;
        }

        public Criteria andShipperNameNotLike(String value) {
            addCriterion("SHIPPER_NAME not like", value, "shipperName");
            return (Criteria) this;
        }

        public Criteria andShipperNameIn(List<String> values) {
            addCriterion("SHIPPER_NAME in", values, "shipperName");
            return (Criteria) this;
        }

        public Criteria andShipperNameNotIn(List<String> values) {
            addCriterion("SHIPPER_NAME not in", values, "shipperName");
            return (Criteria) this;
        }

        public Criteria andShipperNameBetween(String value1, String value2) {
            addCriterion("SHIPPER_NAME between", value1, value2, "shipperName");
            return (Criteria) this;
        }

        public Criteria andShipperNameNotBetween(String value1, String value2) {
            addCriterion("SHIPPER_NAME not between", value1, value2, "shipperName");
            return (Criteria) this;
        }

        public Criteria andShipperPhoneIsNull() {
            addCriterion("SHIPPER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andShipperPhoneIsNotNull() {
            addCriterion("SHIPPER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andShipperPhoneEqualTo(String value) {
            addCriterion("SHIPPER_PHONE =", value, "shipperPhone");
            return (Criteria) this;
        }

        public Criteria andShipperPhoneNotEqualTo(String value) {
            addCriterion("SHIPPER_PHONE <>", value, "shipperPhone");
            return (Criteria) this;
        }

        public Criteria andShipperPhoneGreaterThan(String value) {
            addCriterion("SHIPPER_PHONE >", value, "shipperPhone");
            return (Criteria) this;
        }

        public Criteria andShipperPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("SHIPPER_PHONE >=", value, "shipperPhone");
            return (Criteria) this;
        }

        public Criteria andShipperPhoneLessThan(String value) {
            addCriterion("SHIPPER_PHONE <", value, "shipperPhone");
            return (Criteria) this;
        }

        public Criteria andShipperPhoneLessThanOrEqualTo(String value) {
            addCriterion("SHIPPER_PHONE <=", value, "shipperPhone");
            return (Criteria) this;
        }

        public Criteria andShipperPhoneLike(String value) {
            addCriterion("SHIPPER_PHONE like", value, "shipperPhone");
            return (Criteria) this;
        }

        public Criteria andShipperPhoneNotLike(String value) {
            addCriterion("SHIPPER_PHONE not like", value, "shipperPhone");
            return (Criteria) this;
        }

        public Criteria andShipperPhoneIn(List<String> values) {
            addCriterion("SHIPPER_PHONE in", values, "shipperPhone");
            return (Criteria) this;
        }

        public Criteria andShipperPhoneNotIn(List<String> values) {
            addCriterion("SHIPPER_PHONE not in", values, "shipperPhone");
            return (Criteria) this;
        }

        public Criteria andShipperPhoneBetween(String value1, String value2) {
            addCriterion("SHIPPER_PHONE between", value1, value2, "shipperPhone");
            return (Criteria) this;
        }

        public Criteria andShipperPhoneNotBetween(String value1, String value2) {
            addCriterion("SHIPPER_PHONE not between", value1, value2, "shipperPhone");
            return (Criteria) this;
        }

        public Criteria andShipperMobileIsNull() {
            addCriterion("SHIPPER_MOBILE is null");
            return (Criteria) this;
        }

        public Criteria andShipperMobileIsNotNull() {
            addCriterion("SHIPPER_MOBILE is not null");
            return (Criteria) this;
        }

        public Criteria andShipperMobileEqualTo(String value) {
            addCriterion("SHIPPER_MOBILE =", value, "shipperMobile");
            return (Criteria) this;
        }

        public Criteria andShipperMobileNotEqualTo(String value) {
            addCriterion("SHIPPER_MOBILE <>", value, "shipperMobile");
            return (Criteria) this;
        }

        public Criteria andShipperMobileGreaterThan(String value) {
            addCriterion("SHIPPER_MOBILE >", value, "shipperMobile");
            return (Criteria) this;
        }

        public Criteria andShipperMobileGreaterThanOrEqualTo(String value) {
            addCriterion("SHIPPER_MOBILE >=", value, "shipperMobile");
            return (Criteria) this;
        }

        public Criteria andShipperMobileLessThan(String value) {
            addCriterion("SHIPPER_MOBILE <", value, "shipperMobile");
            return (Criteria) this;
        }

        public Criteria andShipperMobileLessThanOrEqualTo(String value) {
            addCriterion("SHIPPER_MOBILE <=", value, "shipperMobile");
            return (Criteria) this;
        }

        public Criteria andShipperMobileLike(String value) {
            addCriterion("SHIPPER_MOBILE like", value, "shipperMobile");
            return (Criteria) this;
        }

        public Criteria andShipperMobileNotLike(String value) {
            addCriterion("SHIPPER_MOBILE not like", value, "shipperMobile");
            return (Criteria) this;
        }

        public Criteria andShipperMobileIn(List<String> values) {
            addCriterion("SHIPPER_MOBILE in", values, "shipperMobile");
            return (Criteria) this;
        }

        public Criteria andShipperMobileNotIn(List<String> values) {
            addCriterion("SHIPPER_MOBILE not in", values, "shipperMobile");
            return (Criteria) this;
        }

        public Criteria andShipperMobileBetween(String value1, String value2) {
            addCriterion("SHIPPER_MOBILE between", value1, value2, "shipperMobile");
            return (Criteria) this;
        }

        public Criteria andShipperMobileNotBetween(String value1, String value2) {
            addCriterion("SHIPPER_MOBILE not between", value1, value2, "shipperMobile");
            return (Criteria) this;
        }

        public Criteria andShipperAddressIsNull() {
            addCriterion("SHIPPER_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andShipperAddressIsNotNull() {
            addCriterion("SHIPPER_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andShipperAddressEqualTo(String value) {
            addCriterion("SHIPPER_ADDRESS =", value, "shipperAddress");
            return (Criteria) this;
        }

        public Criteria andShipperAddressNotEqualTo(String value) {
            addCriterion("SHIPPER_ADDRESS <>", value, "shipperAddress");
            return (Criteria) this;
        }

        public Criteria andShipperAddressGreaterThan(String value) {
            addCriterion("SHIPPER_ADDRESS >", value, "shipperAddress");
            return (Criteria) this;
        }

        public Criteria andShipperAddressGreaterThanOrEqualTo(String value) {
            addCriterion("SHIPPER_ADDRESS >=", value, "shipperAddress");
            return (Criteria) this;
        }

        public Criteria andShipperAddressLessThan(String value) {
            addCriterion("SHIPPER_ADDRESS <", value, "shipperAddress");
            return (Criteria) this;
        }

        public Criteria andShipperAddressLessThanOrEqualTo(String value) {
            addCriterion("SHIPPER_ADDRESS <=", value, "shipperAddress");
            return (Criteria) this;
        }

        public Criteria andShipperAddressLike(String value) {
            addCriterion("SHIPPER_ADDRESS like", value, "shipperAddress");
            return (Criteria) this;
        }

        public Criteria andShipperAddressNotLike(String value) {
            addCriterion("SHIPPER_ADDRESS not like", value, "shipperAddress");
            return (Criteria) this;
        }

        public Criteria andShipperAddressIn(List<String> values) {
            addCriterion("SHIPPER_ADDRESS in", values, "shipperAddress");
            return (Criteria) this;
        }

        public Criteria andShipperAddressNotIn(List<String> values) {
            addCriterion("SHIPPER_ADDRESS not in", values, "shipperAddress");
            return (Criteria) this;
        }

        public Criteria andShipperAddressBetween(String value1, String value2) {
            addCriterion("SHIPPER_ADDRESS between", value1, value2, "shipperAddress");
            return (Criteria) this;
        }

        public Criteria andShipperAddressNotBetween(String value1, String value2) {
            addCriterion("SHIPPER_ADDRESS not between", value1, value2, "shipperAddress");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNull() {
            addCriterion("POST_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNotNull() {
            addCriterion("POST_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPostCodeEqualTo(String value) {
            addCriterion("POST_CODE =", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotEqualTo(String value) {
            addCriterion("POST_CODE <>", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThan(String value) {
            addCriterion("POST_CODE >", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThanOrEqualTo(String value) {
            addCriterion("POST_CODE >=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThan(String value) {
            addCriterion("POST_CODE <", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThanOrEqualTo(String value) {
            addCriterion("POST_CODE <=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLike(String value) {
            addCriterion("POST_CODE like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotLike(String value) {
            addCriterion("POST_CODE not like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeIn(List<String> values) {
            addCriterion("POST_CODE in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotIn(List<String> values) {
            addCriterion("POST_CODE not in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeBetween(String value1, String value2) {
            addCriterion("POST_CODE between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotBetween(String value1, String value2) {
            addCriterion("POST_CODE not between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andShipperUsingFlagIsNull() {
            addCriterion("SHIPPER_USING_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andShipperUsingFlagIsNotNull() {
            addCriterion("SHIPPER_USING_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andShipperUsingFlagEqualTo(String value) {
            addCriterion("SHIPPER_USING_FLAG =", value, "shipperUsingFlag");
            return (Criteria) this;
        }

        public Criteria andShipperUsingFlagNotEqualTo(String value) {
            addCriterion("SHIPPER_USING_FLAG <>", value, "shipperUsingFlag");
            return (Criteria) this;
        }

        public Criteria andShipperUsingFlagGreaterThan(String value) {
            addCriterion("SHIPPER_USING_FLAG >", value, "shipperUsingFlag");
            return (Criteria) this;
        }

        public Criteria andShipperUsingFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SHIPPER_USING_FLAG >=", value, "shipperUsingFlag");
            return (Criteria) this;
        }

        public Criteria andShipperUsingFlagLessThan(String value) {
            addCriterion("SHIPPER_USING_FLAG <", value, "shipperUsingFlag");
            return (Criteria) this;
        }

        public Criteria andShipperUsingFlagLessThanOrEqualTo(String value) {
            addCriterion("SHIPPER_USING_FLAG <=", value, "shipperUsingFlag");
            return (Criteria) this;
        }

        public Criteria andShipperUsingFlagLike(String value) {
            addCriterion("SHIPPER_USING_FLAG like", value, "shipperUsingFlag");
            return (Criteria) this;
        }

        public Criteria andShipperUsingFlagNotLike(String value) {
            addCriterion("SHIPPER_USING_FLAG not like", value, "shipperUsingFlag");
            return (Criteria) this;
        }

        public Criteria andShipperUsingFlagIn(List<String> values) {
            addCriterion("SHIPPER_USING_FLAG in", values, "shipperUsingFlag");
            return (Criteria) this;
        }

        public Criteria andShipperUsingFlagNotIn(List<String> values) {
            addCriterion("SHIPPER_USING_FLAG not in", values, "shipperUsingFlag");
            return (Criteria) this;
        }

        public Criteria andShipperUsingFlagBetween(String value1, String value2) {
            addCriterion("SHIPPER_USING_FLAG between", value1, value2, "shipperUsingFlag");
            return (Criteria) this;
        }

        public Criteria andShipperUsingFlagNotBetween(String value1, String value2) {
            addCriterion("SHIPPER_USING_FLAG not between", value1, value2, "shipperUsingFlag");
            return (Criteria) this;
        }

        public Criteria andBackUsingFlagIsNull() {
            addCriterion("BACK_USING_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andBackUsingFlagIsNotNull() {
            addCriterion("BACK_USING_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andBackUsingFlagEqualTo(String value) {
            addCriterion("BACK_USING_FLAG =", value, "backUsingFlag");
            return (Criteria) this;
        }

        public Criteria andBackUsingFlagNotEqualTo(String value) {
            addCriterion("BACK_USING_FLAG <>", value, "backUsingFlag");
            return (Criteria) this;
        }

        public Criteria andBackUsingFlagGreaterThan(String value) {
            addCriterion("BACK_USING_FLAG >", value, "backUsingFlag");
            return (Criteria) this;
        }

        public Criteria andBackUsingFlagGreaterThanOrEqualTo(String value) {
            addCriterion("BACK_USING_FLAG >=", value, "backUsingFlag");
            return (Criteria) this;
        }

        public Criteria andBackUsingFlagLessThan(String value) {
            addCriterion("BACK_USING_FLAG <", value, "backUsingFlag");
            return (Criteria) this;
        }

        public Criteria andBackUsingFlagLessThanOrEqualTo(String value) {
            addCriterion("BACK_USING_FLAG <=", value, "backUsingFlag");
            return (Criteria) this;
        }

        public Criteria andBackUsingFlagLike(String value) {
            addCriterion("BACK_USING_FLAG like", value, "backUsingFlag");
            return (Criteria) this;
        }

        public Criteria andBackUsingFlagNotLike(String value) {
            addCriterion("BACK_USING_FLAG not like", value, "backUsingFlag");
            return (Criteria) this;
        }

        public Criteria andBackUsingFlagIn(List<String> values) {
            addCriterion("BACK_USING_FLAG in", values, "backUsingFlag");
            return (Criteria) this;
        }

        public Criteria andBackUsingFlagNotIn(List<String> values) {
            addCriterion("BACK_USING_FLAG not in", values, "backUsingFlag");
            return (Criteria) this;
        }

        public Criteria andBackUsingFlagBetween(String value1, String value2) {
            addCriterion("BACK_USING_FLAG between", value1, value2, "backUsingFlag");
            return (Criteria) this;
        }

        public Criteria andBackUsingFlagNotBetween(String value1, String value2) {
            addCriterion("BACK_USING_FLAG not between", value1, value2, "backUsingFlag");
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