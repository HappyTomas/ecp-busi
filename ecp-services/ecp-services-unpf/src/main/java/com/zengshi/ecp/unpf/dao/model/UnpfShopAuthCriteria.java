package com.zengshi.ecp.unpf.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UnpfShopAuthCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public UnpfShopAuthCriteria() {
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

        public Criteria andPlatTypeIsNull() {
            addCriterion("PLAT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPlatTypeIsNotNull() {
            addCriterion("PLAT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPlatTypeEqualTo(String value) {
            addCriterion("PLAT_TYPE =", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeNotEqualTo(String value) {
            addCriterion("PLAT_TYPE <>", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeGreaterThan(String value) {
            addCriterion("PLAT_TYPE >", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PLAT_TYPE >=", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeLessThan(String value) {
            addCriterion("PLAT_TYPE <", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeLessThanOrEqualTo(String value) {
            addCriterion("PLAT_TYPE <=", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeLike(String value) {
            addCriterion("PLAT_TYPE like", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeNotLike(String value) {
            addCriterion("PLAT_TYPE not like", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeIn(List<String> values) {
            addCriterion("PLAT_TYPE in", values, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeNotIn(List<String> values) {
            addCriterion("PLAT_TYPE not in", values, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeBetween(String value1, String value2) {
            addCriterion("PLAT_TYPE between", value1, value2, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeNotBetween(String value1, String value2) {
            addCriterion("PLAT_TYPE not between", value1, value2, "platType");
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

        public Criteria andAppkeyIsNull() {
            addCriterion("APPKEY is null");
            return (Criteria) this;
        }

        public Criteria andAppkeyIsNotNull() {
            addCriterion("APPKEY is not null");
            return (Criteria) this;
        }

        public Criteria andAppkeyEqualTo(String value) {
            addCriterion("APPKEY =", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotEqualTo(String value) {
            addCriterion("APPKEY <>", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyGreaterThan(String value) {
            addCriterion("APPKEY >", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyGreaterThanOrEqualTo(String value) {
            addCriterion("APPKEY >=", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyLessThan(String value) {
            addCriterion("APPKEY <", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyLessThanOrEqualTo(String value) {
            addCriterion("APPKEY <=", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyLike(String value) {
            addCriterion("APPKEY like", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotLike(String value) {
            addCriterion("APPKEY not like", value, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyIn(List<String> values) {
            addCriterion("APPKEY in", values, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotIn(List<String> values) {
            addCriterion("APPKEY not in", values, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyBetween(String value1, String value2) {
            addCriterion("APPKEY between", value1, value2, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppkeyNotBetween(String value1, String value2) {
            addCriterion("APPKEY not between", value1, value2, "appkey");
            return (Criteria) this;
        }

        public Criteria andAppscretIsNull() {
            addCriterion("APPSCRET is null");
            return (Criteria) this;
        }

        public Criteria andAppscretIsNotNull() {
            addCriterion("APPSCRET is not null");
            return (Criteria) this;
        }

        public Criteria andAppscretEqualTo(String value) {
            addCriterion("APPSCRET =", value, "appscret");
            return (Criteria) this;
        }

        public Criteria andAppscretNotEqualTo(String value) {
            addCriterion("APPSCRET <>", value, "appscret");
            return (Criteria) this;
        }

        public Criteria andAppscretGreaterThan(String value) {
            addCriterion("APPSCRET >", value, "appscret");
            return (Criteria) this;
        }

        public Criteria andAppscretGreaterThanOrEqualTo(String value) {
            addCriterion("APPSCRET >=", value, "appscret");
            return (Criteria) this;
        }

        public Criteria andAppscretLessThan(String value) {
            addCriterion("APPSCRET <", value, "appscret");
            return (Criteria) this;
        }

        public Criteria andAppscretLessThanOrEqualTo(String value) {
            addCriterion("APPSCRET <=", value, "appscret");
            return (Criteria) this;
        }

        public Criteria andAppscretLike(String value) {
            addCriterion("APPSCRET like", value, "appscret");
            return (Criteria) this;
        }

        public Criteria andAppscretNotLike(String value) {
            addCriterion("APPSCRET not like", value, "appscret");
            return (Criteria) this;
        }

        public Criteria andAppscretIn(List<String> values) {
            addCriterion("APPSCRET in", values, "appscret");
            return (Criteria) this;
        }

        public Criteria andAppscretNotIn(List<String> values) {
            addCriterion("APPSCRET not in", values, "appscret");
            return (Criteria) this;
        }

        public Criteria andAppscretBetween(String value1, String value2) {
            addCriterion("APPSCRET between", value1, value2, "appscret");
            return (Criteria) this;
        }

        public Criteria andAppscretNotBetween(String value1, String value2) {
            addCriterion("APPSCRET not between", value1, value2, "appscret");
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

        public Criteria andExpiredTimeIsNull() {
            addCriterion("EXPIRED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeIsNotNull() {
            addCriterion("EXPIRED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeEqualTo(Timestamp value) {
            addCriterion("EXPIRED_TIME =", value, "expiredTime");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeNotEqualTo(Timestamp value) {
            addCriterion("EXPIRED_TIME <>", value, "expiredTime");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeGreaterThan(Timestamp value) {
            addCriterion("EXPIRED_TIME >", value, "expiredTime");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("EXPIRED_TIME >=", value, "expiredTime");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeLessThan(Timestamp value) {
            addCriterion("EXPIRED_TIME <", value, "expiredTime");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("EXPIRED_TIME <=", value, "expiredTime");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeIn(List<Timestamp> values) {
            addCriterion("EXPIRED_TIME in", values, "expiredTime");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeNotIn(List<Timestamp> values) {
            addCriterion("EXPIRED_TIME not in", values, "expiredTime");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("EXPIRED_TIME between", value1, value2, "expiredTime");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("EXPIRED_TIME not between", value1, value2, "expiredTime");
            return (Criteria) this;
        }

        public Criteria andServerUrlIsNull() {
            addCriterion("SERVER_URL is null");
            return (Criteria) this;
        }

        public Criteria andServerUrlIsNotNull() {
            addCriterion("SERVER_URL is not null");
            return (Criteria) this;
        }

        public Criteria andServerUrlEqualTo(String value) {
            addCriterion("SERVER_URL =", value, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlNotEqualTo(String value) {
            addCriterion("SERVER_URL <>", value, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlGreaterThan(String value) {
            addCriterion("SERVER_URL >", value, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlGreaterThanOrEqualTo(String value) {
            addCriterion("SERVER_URL >=", value, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlLessThan(String value) {
            addCriterion("SERVER_URL <", value, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlLessThanOrEqualTo(String value) {
            addCriterion("SERVER_URL <=", value, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlLike(String value) {
            addCriterion("SERVER_URL like", value, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlNotLike(String value) {
            addCriterion("SERVER_URL not like", value, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlIn(List<String> values) {
            addCriterion("SERVER_URL in", values, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlNotIn(List<String> values) {
            addCriterion("SERVER_URL not in", values, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlBetween(String value1, String value2) {
            addCriterion("SERVER_URL between", value1, value2, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlNotBetween(String value1, String value2) {
            addCriterion("SERVER_URL not between", value1, value2, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andFormatIsNull() {
            addCriterion("FORMAT is null");
            return (Criteria) this;
        }

        public Criteria andFormatIsNotNull() {
            addCriterion("FORMAT is not null");
            return (Criteria) this;
        }

        public Criteria andFormatEqualTo(String value) {
            addCriterion("FORMAT =", value, "format");
            return (Criteria) this;
        }

        public Criteria andFormatNotEqualTo(String value) {
            addCriterion("FORMAT <>", value, "format");
            return (Criteria) this;
        }

        public Criteria andFormatGreaterThan(String value) {
            addCriterion("FORMAT >", value, "format");
            return (Criteria) this;
        }

        public Criteria andFormatGreaterThanOrEqualTo(String value) {
            addCriterion("FORMAT >=", value, "format");
            return (Criteria) this;
        }

        public Criteria andFormatLessThan(String value) {
            addCriterion("FORMAT <", value, "format");
            return (Criteria) this;
        }

        public Criteria andFormatLessThanOrEqualTo(String value) {
            addCriterion("FORMAT <=", value, "format");
            return (Criteria) this;
        }

        public Criteria andFormatLike(String value) {
            addCriterion("FORMAT like", value, "format");
            return (Criteria) this;
        }

        public Criteria andFormatNotLike(String value) {
            addCriterion("FORMAT not like", value, "format");
            return (Criteria) this;
        }

        public Criteria andFormatIn(List<String> values) {
            addCriterion("FORMAT in", values, "format");
            return (Criteria) this;
        }

        public Criteria andFormatNotIn(List<String> values) {
            addCriterion("FORMAT not in", values, "format");
            return (Criteria) this;
        }

        public Criteria andFormatBetween(String value1, String value2) {
            addCriterion("FORMAT between", value1, value2, "format");
            return (Criteria) this;
        }

        public Criteria andFormatNotBetween(String value1, String value2) {
            addCriterion("FORMAT not between", value1, value2, "format");
            return (Criteria) this;
        }

        public Criteria andSignmethodIsNull() {
            addCriterion("SIGNMETHOD is null");
            return (Criteria) this;
        }

        public Criteria andSignmethodIsNotNull() {
            addCriterion("SIGNMETHOD is not null");
            return (Criteria) this;
        }

        public Criteria andSignmethodEqualTo(String value) {
            addCriterion("SIGNMETHOD =", value, "signmethod");
            return (Criteria) this;
        }

        public Criteria andSignmethodNotEqualTo(String value) {
            addCriterion("SIGNMETHOD <>", value, "signmethod");
            return (Criteria) this;
        }

        public Criteria andSignmethodGreaterThan(String value) {
            addCriterion("SIGNMETHOD >", value, "signmethod");
            return (Criteria) this;
        }

        public Criteria andSignmethodGreaterThanOrEqualTo(String value) {
            addCriterion("SIGNMETHOD >=", value, "signmethod");
            return (Criteria) this;
        }

        public Criteria andSignmethodLessThan(String value) {
            addCriterion("SIGNMETHOD <", value, "signmethod");
            return (Criteria) this;
        }

        public Criteria andSignmethodLessThanOrEqualTo(String value) {
            addCriterion("SIGNMETHOD <=", value, "signmethod");
            return (Criteria) this;
        }

        public Criteria andSignmethodLike(String value) {
            addCriterion("SIGNMETHOD like", value, "signmethod");
            return (Criteria) this;
        }

        public Criteria andSignmethodNotLike(String value) {
            addCriterion("SIGNMETHOD not like", value, "signmethod");
            return (Criteria) this;
        }

        public Criteria andSignmethodIn(List<String> values) {
            addCriterion("SIGNMETHOD in", values, "signmethod");
            return (Criteria) this;
        }

        public Criteria andSignmethodNotIn(List<String> values) {
            addCriterion("SIGNMETHOD not in", values, "signmethod");
            return (Criteria) this;
        }

        public Criteria andSignmethodBetween(String value1, String value2) {
            addCriterion("SIGNMETHOD between", value1, value2, "signmethod");
            return (Criteria) this;
        }

        public Criteria andSignmethodNotBetween(String value1, String value2) {
            addCriterion("SIGNMETHOD not between", value1, value2, "signmethod");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("VERSION like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("VERSION not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("VERSION not between", value1, value2, "version");
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

        public Criteria andAuthUrlIsNull() {
            addCriterion("AUTH_URL is null");
            return (Criteria) this;
        }

        public Criteria andAuthUrlIsNotNull() {
            addCriterion("AUTH_URL is not null");
            return (Criteria) this;
        }

        public Criteria andAuthUrlEqualTo(String value) {
            addCriterion("AUTH_URL =", value, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlNotEqualTo(String value) {
            addCriterion("AUTH_URL <>", value, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlGreaterThan(String value) {
            addCriterion("AUTH_URL >", value, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlGreaterThanOrEqualTo(String value) {
            addCriterion("AUTH_URL >=", value, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlLessThan(String value) {
            addCriterion("AUTH_URL <", value, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlLessThanOrEqualTo(String value) {
            addCriterion("AUTH_URL <=", value, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlLike(String value) {
            addCriterion("AUTH_URL like", value, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlNotLike(String value) {
            addCriterion("AUTH_URL not like", value, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlIn(List<String> values) {
            addCriterion("AUTH_URL in", values, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlNotIn(List<String> values) {
            addCriterion("AUTH_URL not in", values, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlBetween(String value1, String value2) {
            addCriterion("AUTH_URL between", value1, value2, "authUrl");
            return (Criteria) this;
        }

        public Criteria andAuthUrlNotBetween(String value1, String value2) {
            addCriterion("AUTH_URL not between", value1, value2, "authUrl");
            return (Criteria) this;
        }

        public Criteria andRedirectUriIsNull() {
            addCriterion("REDIRECT_URI is null");
            return (Criteria) this;
        }

        public Criteria andRedirectUriIsNotNull() {
            addCriterion("REDIRECT_URI is not null");
            return (Criteria) this;
        }

        public Criteria andRedirectUriEqualTo(String value) {
            addCriterion("REDIRECT_URI =", value, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriNotEqualTo(String value) {
            addCriterion("REDIRECT_URI <>", value, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriGreaterThan(String value) {
            addCriterion("REDIRECT_URI >", value, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriGreaterThanOrEqualTo(String value) {
            addCriterion("REDIRECT_URI >=", value, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriLessThan(String value) {
            addCriterion("REDIRECT_URI <", value, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriLessThanOrEqualTo(String value) {
            addCriterion("REDIRECT_URI <=", value, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriLike(String value) {
            addCriterion("REDIRECT_URI like", value, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriNotLike(String value) {
            addCriterion("REDIRECT_URI not like", value, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriIn(List<String> values) {
            addCriterion("REDIRECT_URI in", values, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriNotIn(List<String> values) {
            addCriterion("REDIRECT_URI not in", values, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriBetween(String value1, String value2) {
            addCriterion("REDIRECT_URI between", value1, value2, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andRedirectUriNotBetween(String value1, String value2) {
            addCriterion("REDIRECT_URI not between", value1, value2, "redirectUri");
            return (Criteria) this;
        }

        public Criteria andAuthCodeIsNull() {
            addCriterion("AUTH_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAuthCodeIsNotNull() {
            addCriterion("AUTH_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAuthCodeEqualTo(String value) {
            addCriterion("AUTH_CODE =", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeNotEqualTo(String value) {
            addCriterion("AUTH_CODE <>", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeGreaterThan(String value) {
            addCriterion("AUTH_CODE >", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeGreaterThanOrEqualTo(String value) {
            addCriterion("AUTH_CODE >=", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeLessThan(String value) {
            addCriterion("AUTH_CODE <", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeLessThanOrEqualTo(String value) {
            addCriterion("AUTH_CODE <=", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeLike(String value) {
            addCriterion("AUTH_CODE like", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeNotLike(String value) {
            addCriterion("AUTH_CODE not like", value, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeIn(List<String> values) {
            addCriterion("AUTH_CODE in", values, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeNotIn(List<String> values) {
            addCriterion("AUTH_CODE not in", values, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeBetween(String value1, String value2) {
            addCriterion("AUTH_CODE between", value1, value2, "authCode");
            return (Criteria) this;
        }

        public Criteria andAuthCodeNotBetween(String value1, String value2) {
            addCriterion("AUTH_CODE not between", value1, value2, "authCode");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNull() {
            addCriterion("ACCESS_TOKEN is null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNotNull() {
            addCriterion("ACCESS_TOKEN is not null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenEqualTo(String value) {
            addCriterion("ACCESS_TOKEN =", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotEqualTo(String value) {
            addCriterion("ACCESS_TOKEN <>", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThan(String value) {
            addCriterion("ACCESS_TOKEN >", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThanOrEqualTo(String value) {
            addCriterion("ACCESS_TOKEN >=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThan(String value) {
            addCriterion("ACCESS_TOKEN <", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThanOrEqualTo(String value) {
            addCriterion("ACCESS_TOKEN <=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLike(String value) {
            addCriterion("ACCESS_TOKEN like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotLike(String value) {
            addCriterion("ACCESS_TOKEN not like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIn(List<String> values) {
            addCriterion("ACCESS_TOKEN in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotIn(List<String> values) {
            addCriterion("ACCESS_TOKEN not in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenBetween(String value1, String value2) {
            addCriterion("ACCESS_TOKEN between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotBetween(String value1, String value2) {
            addCriterion("ACCESS_TOKEN not between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenIsNull() {
            addCriterion("REFRESH_TOKEN is null");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenIsNotNull() {
            addCriterion("REFRESH_TOKEN is not null");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenEqualTo(String value) {
            addCriterion("REFRESH_TOKEN =", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenNotEqualTo(String value) {
            addCriterion("REFRESH_TOKEN <>", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenGreaterThan(String value) {
            addCriterion("REFRESH_TOKEN >", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenGreaterThanOrEqualTo(String value) {
            addCriterion("REFRESH_TOKEN >=", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenLessThan(String value) {
            addCriterion("REFRESH_TOKEN <", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenLessThanOrEqualTo(String value) {
            addCriterion("REFRESH_TOKEN <=", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenLike(String value) {
            addCriterion("REFRESH_TOKEN like", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenNotLike(String value) {
            addCriterion("REFRESH_TOKEN not like", value, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenIn(List<String> values) {
            addCriterion("REFRESH_TOKEN in", values, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenNotIn(List<String> values) {
            addCriterion("REFRESH_TOKEN not in", values, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenBetween(String value1, String value2) {
            addCriterion("REFRESH_TOKEN between", value1, value2, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andRefreshTokenNotBetween(String value1, String value2) {
            addCriterion("REFRESH_TOKEN not between", value1, value2, "refreshToken");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeAIsNull() {
            addCriterion("EXPIRED_TIME_A is null");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeAIsNotNull() {
            addCriterion("EXPIRED_TIME_A is not null");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeAEqualTo(Timestamp value) {
            addCriterion("EXPIRED_TIME_A =", value, "expiredTimeA");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeANotEqualTo(Timestamp value) {
            addCriterion("EXPIRED_TIME_A <>", value, "expiredTimeA");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeAGreaterThan(Timestamp value) {
            addCriterion("EXPIRED_TIME_A >", value, "expiredTimeA");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeAGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("EXPIRED_TIME_A >=", value, "expiredTimeA");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeALessThan(Timestamp value) {
            addCriterion("EXPIRED_TIME_A <", value, "expiredTimeA");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeALessThanOrEqualTo(Timestamp value) {
            addCriterion("EXPIRED_TIME_A <=", value, "expiredTimeA");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeAIn(List<Timestamp> values) {
            addCriterion("EXPIRED_TIME_A in", values, "expiredTimeA");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeANotIn(List<Timestamp> values) {
            addCriterion("EXPIRED_TIME_A not in", values, "expiredTimeA");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeABetween(Timestamp value1, Timestamp value2) {
            addCriterion("EXPIRED_TIME_A between", value1, value2, "expiredTimeA");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeANotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("EXPIRED_TIME_A not between", value1, value2, "expiredTimeA");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeRIsNull() {
            addCriterion("EXPIRED_TIME_R is null");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeRIsNotNull() {
            addCriterion("EXPIRED_TIME_R is not null");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeREqualTo(Timestamp value) {
            addCriterion("EXPIRED_TIME_R =", value, "expiredTimeR");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeRNotEqualTo(Timestamp value) {
            addCriterion("EXPIRED_TIME_R <>", value, "expiredTimeR");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeRGreaterThan(Timestamp value) {
            addCriterion("EXPIRED_TIME_R >", value, "expiredTimeR");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeRGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("EXPIRED_TIME_R >=", value, "expiredTimeR");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeRLessThan(Timestamp value) {
            addCriterion("EXPIRED_TIME_R <", value, "expiredTimeR");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeRLessThanOrEqualTo(Timestamp value) {
            addCriterion("EXPIRED_TIME_R <=", value, "expiredTimeR");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeRIn(List<Timestamp> values) {
            addCriterion("EXPIRED_TIME_R in", values, "expiredTimeR");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeRNotIn(List<Timestamp> values) {
            addCriterion("EXPIRED_TIME_R not in", values, "expiredTimeR");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeRBetween(Timestamp value1, Timestamp value2) {
            addCriterion("EXPIRED_TIME_R between", value1, value2, "expiredTimeR");
            return (Criteria) this;
        }

        public Criteria andExpiredTimeRNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("EXPIRED_TIME_R not between", value1, value2, "expiredTimeR");
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