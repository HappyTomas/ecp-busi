package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class StockRepAdaptCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public StockRepAdaptCriteria() {
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

        public Criteria andRepCodeIsNull() {
            addCriterion("REP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRepCodeIsNotNull() {
            addCriterion("REP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRepCodeEqualTo(Long value) {
            addCriterion("REP_CODE =", value, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeNotEqualTo(Long value) {
            addCriterion("REP_CODE <>", value, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeGreaterThan(Long value) {
            addCriterion("REP_CODE >", value, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("REP_CODE >=", value, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeLessThan(Long value) {
            addCriterion("REP_CODE <", value, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeLessThanOrEqualTo(Long value) {
            addCriterion("REP_CODE <=", value, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeIn(List<Long> values) {
            addCriterion("REP_CODE in", values, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeNotIn(List<Long> values) {
            addCriterion("REP_CODE not in", values, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeBetween(Long value1, Long value2) {
            addCriterion("REP_CODE between", value1, value2, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeNotBetween(Long value1, Long value2) {
            addCriterion("REP_CODE not between", value1, value2, "repCode");
            return (Criteria) this;
        }

        public Criteria andAdaptCountryIsNull() {
            addCriterion("ADAPT_COUNTRY is null");
            return (Criteria) this;
        }

        public Criteria andAdaptCountryIsNotNull() {
            addCriterion("ADAPT_COUNTRY is not null");
            return (Criteria) this;
        }

        public Criteria andAdaptCountryEqualTo(String value) {
            addCriterion("ADAPT_COUNTRY =", value, "adaptCountry");
            return (Criteria) this;
        }

        public Criteria andAdaptCountryNotEqualTo(String value) {
            addCriterion("ADAPT_COUNTRY <>", value, "adaptCountry");
            return (Criteria) this;
        }

        public Criteria andAdaptCountryGreaterThan(String value) {
            addCriterion("ADAPT_COUNTRY >", value, "adaptCountry");
            return (Criteria) this;
        }

        public Criteria andAdaptCountryGreaterThanOrEqualTo(String value) {
            addCriterion("ADAPT_COUNTRY >=", value, "adaptCountry");
            return (Criteria) this;
        }

        public Criteria andAdaptCountryLessThan(String value) {
            addCriterion("ADAPT_COUNTRY <", value, "adaptCountry");
            return (Criteria) this;
        }

        public Criteria andAdaptCountryLessThanOrEqualTo(String value) {
            addCriterion("ADAPT_COUNTRY <=", value, "adaptCountry");
            return (Criteria) this;
        }

        public Criteria andAdaptCountryLike(String value) {
            addCriterion("ADAPT_COUNTRY like", value, "adaptCountry");
            return (Criteria) this;
        }

        public Criteria andAdaptCountryNotLike(String value) {
            addCriterion("ADAPT_COUNTRY not like", value, "adaptCountry");
            return (Criteria) this;
        }

        public Criteria andAdaptCountryIn(List<String> values) {
            addCriterion("ADAPT_COUNTRY in", values, "adaptCountry");
            return (Criteria) this;
        }

        public Criteria andAdaptCountryNotIn(List<String> values) {
            addCriterion("ADAPT_COUNTRY not in", values, "adaptCountry");
            return (Criteria) this;
        }

        public Criteria andAdaptCountryBetween(String value1, String value2) {
            addCriterion("ADAPT_COUNTRY between", value1, value2, "adaptCountry");
            return (Criteria) this;
        }

        public Criteria andAdaptCountryNotBetween(String value1, String value2) {
            addCriterion("ADAPT_COUNTRY not between", value1, value2, "adaptCountry");
            return (Criteria) this;
        }

        public Criteria andAdaptProvinceIsNull() {
            addCriterion("ADAPT_PROVINCE is null");
            return (Criteria) this;
        }

        public Criteria andAdaptProvinceIsNotNull() {
            addCriterion("ADAPT_PROVINCE is not null");
            return (Criteria) this;
        }

        public Criteria andAdaptProvinceEqualTo(String value) {
            addCriterion("ADAPT_PROVINCE =", value, "adaptProvince");
            return (Criteria) this;
        }

        public Criteria andAdaptProvinceNotEqualTo(String value) {
            addCriterion("ADAPT_PROVINCE <>", value, "adaptProvince");
            return (Criteria) this;
        }

        public Criteria andAdaptProvinceGreaterThan(String value) {
            addCriterion("ADAPT_PROVINCE >", value, "adaptProvince");
            return (Criteria) this;
        }

        public Criteria andAdaptProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("ADAPT_PROVINCE >=", value, "adaptProvince");
            return (Criteria) this;
        }

        public Criteria andAdaptProvinceLessThan(String value) {
            addCriterion("ADAPT_PROVINCE <", value, "adaptProvince");
            return (Criteria) this;
        }

        public Criteria andAdaptProvinceLessThanOrEqualTo(String value) {
            addCriterion("ADAPT_PROVINCE <=", value, "adaptProvince");
            return (Criteria) this;
        }

        public Criteria andAdaptProvinceLike(String value) {
            addCriterion("ADAPT_PROVINCE like", value, "adaptProvince");
            return (Criteria) this;
        }

        public Criteria andAdaptProvinceNotLike(String value) {
            addCriterion("ADAPT_PROVINCE not like", value, "adaptProvince");
            return (Criteria) this;
        }

        public Criteria andAdaptProvinceIn(List<String> values) {
            addCriterion("ADAPT_PROVINCE in", values, "adaptProvince");
            return (Criteria) this;
        }

        public Criteria andAdaptProvinceNotIn(List<String> values) {
            addCriterion("ADAPT_PROVINCE not in", values, "adaptProvince");
            return (Criteria) this;
        }

        public Criteria andAdaptProvinceBetween(String value1, String value2) {
            addCriterion("ADAPT_PROVINCE between", value1, value2, "adaptProvince");
            return (Criteria) this;
        }

        public Criteria andAdaptProvinceNotBetween(String value1, String value2) {
            addCriterion("ADAPT_PROVINCE not between", value1, value2, "adaptProvince");
            return (Criteria) this;
        }

        public Criteria andAdaptCityIsNull() {
            addCriterion("ADAPT_CITY is null");
            return (Criteria) this;
        }

        public Criteria andAdaptCityIsNotNull() {
            addCriterion("ADAPT_CITY is not null");
            return (Criteria) this;
        }

        public Criteria andAdaptCityEqualTo(String value) {
            addCriterion("ADAPT_CITY =", value, "adaptCity");
            return (Criteria) this;
        }

        public Criteria andAdaptCityNotEqualTo(String value) {
            addCriterion("ADAPT_CITY <>", value, "adaptCity");
            return (Criteria) this;
        }

        public Criteria andAdaptCityGreaterThan(String value) {
            addCriterion("ADAPT_CITY >", value, "adaptCity");
            return (Criteria) this;
        }

        public Criteria andAdaptCityGreaterThanOrEqualTo(String value) {
            addCriterion("ADAPT_CITY >=", value, "adaptCity");
            return (Criteria) this;
        }

        public Criteria andAdaptCityLessThan(String value) {
            addCriterion("ADAPT_CITY <", value, "adaptCity");
            return (Criteria) this;
        }

        public Criteria andAdaptCityLessThanOrEqualTo(String value) {
            addCriterion("ADAPT_CITY <=", value, "adaptCity");
            return (Criteria) this;
        }

        public Criteria andAdaptCityLike(String value) {
            addCriterion("ADAPT_CITY like", value, "adaptCity");
            return (Criteria) this;
        }

        public Criteria andAdaptCityNotLike(String value) {
            addCriterion("ADAPT_CITY not like", value, "adaptCity");
            return (Criteria) this;
        }

        public Criteria andAdaptCityIn(List<String> values) {
            addCriterion("ADAPT_CITY in", values, "adaptCity");
            return (Criteria) this;
        }

        public Criteria andAdaptCityNotIn(List<String> values) {
            addCriterion("ADAPT_CITY not in", values, "adaptCity");
            return (Criteria) this;
        }

        public Criteria andAdaptCityBetween(String value1, String value2) {
            addCriterion("ADAPT_CITY between", value1, value2, "adaptCity");
            return (Criteria) this;
        }

        public Criteria andAdaptCityNotBetween(String value1, String value2) {
            addCriterion("ADAPT_CITY not between", value1, value2, "adaptCity");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
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
