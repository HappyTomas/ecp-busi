package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GdsMediaLibCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsMediaLibCriteria() {
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

        public Criteria andLibNameIsNull() {
            addCriterion("LIB_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLibNameIsNotNull() {
            addCriterion("LIB_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLibNameEqualTo(String value) {
            addCriterion("LIB_NAME =", value, "libName");
            return (Criteria) this;
        }

        public Criteria andLibNameNotEqualTo(String value) {
            addCriterion("LIB_NAME <>", value, "libName");
            return (Criteria) this;
        }

        public Criteria andLibNameGreaterThan(String value) {
            addCriterion("LIB_NAME >", value, "libName");
            return (Criteria) this;
        }

        public Criteria andLibNameGreaterThanOrEqualTo(String value) {
            addCriterion("LIB_NAME >=", value, "libName");
            return (Criteria) this;
        }

        public Criteria andLibNameLessThan(String value) {
            addCriterion("LIB_NAME <", value, "libName");
            return (Criteria) this;
        }

        public Criteria andLibNameLessThanOrEqualTo(String value) {
            addCriterion("LIB_NAME <=", value, "libName");
            return (Criteria) this;
        }

        public Criteria andLibNameLike(String value) {
            addCriterion("LIB_NAME like", value, "libName");
            return (Criteria) this;
        }

        public Criteria andLibNameNotLike(String value) {
            addCriterion("LIB_NAME not like", value, "libName");
            return (Criteria) this;
        }

        public Criteria andLibNameIn(List<String> values) {
            addCriterion("LIB_NAME in", values, "libName");
            return (Criteria) this;
        }

        public Criteria andLibNameNotIn(List<String> values) {
            addCriterion("LIB_NAME not in", values, "libName");
            return (Criteria) this;
        }

        public Criteria andLibNameBetween(String value1, String value2) {
            addCriterion("LIB_NAME between", value1, value2, "libName");
            return (Criteria) this;
        }

        public Criteria andLibNameNotBetween(String value1, String value2) {
            addCriterion("LIB_NAME not between", value1, value2, "libName");
            return (Criteria) this;
        }

        public Criteria andLibDescIsNull() {
            addCriterion("LIB_DESC is null");
            return (Criteria) this;
        }

        public Criteria andLibDescIsNotNull() {
            addCriterion("LIB_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andLibDescEqualTo(String value) {
            addCriterion("LIB_DESC =", value, "libDesc");
            return (Criteria) this;
        }

        public Criteria andLibDescNotEqualTo(String value) {
            addCriterion("LIB_DESC <>", value, "libDesc");
            return (Criteria) this;
        }

        public Criteria andLibDescGreaterThan(String value) {
            addCriterion("LIB_DESC >", value, "libDesc");
            return (Criteria) this;
        }

        public Criteria andLibDescGreaterThanOrEqualTo(String value) {
            addCriterion("LIB_DESC >=", value, "libDesc");
            return (Criteria) this;
        }

        public Criteria andLibDescLessThan(String value) {
            addCriterion("LIB_DESC <", value, "libDesc");
            return (Criteria) this;
        }

        public Criteria andLibDescLessThanOrEqualTo(String value) {
            addCriterion("LIB_DESC <=", value, "libDesc");
            return (Criteria) this;
        }

        public Criteria andLibDescLike(String value) {
            addCriterion("LIB_DESC like", value, "libDesc");
            return (Criteria) this;
        }

        public Criteria andLibDescNotLike(String value) {
            addCriterion("LIB_DESC not like", value, "libDesc");
            return (Criteria) this;
        }

        public Criteria andLibDescIn(List<String> values) {
            addCriterion("LIB_DESC in", values, "libDesc");
            return (Criteria) this;
        }

        public Criteria andLibDescNotIn(List<String> values) {
            addCriterion("LIB_DESC not in", values, "libDesc");
            return (Criteria) this;
        }

        public Criteria andLibDescBetween(String value1, String value2) {
            addCriterion("LIB_DESC between", value1, value2, "libDesc");
            return (Criteria) this;
        }

        public Criteria andLibDescNotBetween(String value1, String value2) {
            addCriterion("LIB_DESC not between", value1, value2, "libDesc");
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

        public Criteria andVNowIsNull() {
            addCriterion("V_NOW is null");
            return (Criteria) this;
        }

        public Criteria andVNowIsNotNull() {
            addCriterion("V_NOW is not null");
            return (Criteria) this;
        }

        public Criteria andVNowEqualTo(Long value) {
            addCriterion("V_NOW =", value, "vNow");
            return (Criteria) this;
        }

        public Criteria andVNowNotEqualTo(Long value) {
            addCriterion("V_NOW <>", value, "vNow");
            return (Criteria) this;
        }

        public Criteria andVNowGreaterThan(Long value) {
            addCriterion("V_NOW >", value, "vNow");
            return (Criteria) this;
        }

        public Criteria andVNowGreaterThanOrEqualTo(Long value) {
            addCriterion("V_NOW >=", value, "vNow");
            return (Criteria) this;
        }

        public Criteria andVNowLessThan(Long value) {
            addCriterion("V_NOW <", value, "vNow");
            return (Criteria) this;
        }

        public Criteria andVNowLessThanOrEqualTo(Long value) {
            addCriterion("V_NOW <=", value, "vNow");
            return (Criteria) this;
        }

        public Criteria andVNowIn(List<Long> values) {
            addCriterion("V_NOW in", values, "vNow");
            return (Criteria) this;
        }

        public Criteria andVNowNotIn(List<Long> values) {
            addCriterion("V_NOW not in", values, "vNow");
            return (Criteria) this;
        }

        public Criteria andVNowBetween(Long value1, Long value2) {
            addCriterion("V_NOW between", value1, value2, "vNow");
            return (Criteria) this;
        }

        public Criteria andVNowNotBetween(Long value1, Long value2) {
            addCriterion("V_NOW not between", value1, value2, "vNow");
            return (Criteria) this;
        }

        public Criteria andVLimitIsNull() {
            addCriterion("V_LIMIT is null");
            return (Criteria) this;
        }

        public Criteria andVLimitIsNotNull() {
            addCriterion("V_LIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andVLimitEqualTo(Long value) {
            addCriterion("V_LIMIT =", value, "vLimit");
            return (Criteria) this;
        }

        public Criteria andVLimitNotEqualTo(Long value) {
            addCriterion("V_LIMIT <>", value, "vLimit");
            return (Criteria) this;
        }

        public Criteria andVLimitGreaterThan(Long value) {
            addCriterion("V_LIMIT >", value, "vLimit");
            return (Criteria) this;
        }

        public Criteria andVLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("V_LIMIT >=", value, "vLimit");
            return (Criteria) this;
        }

        public Criteria andVLimitLessThan(Long value) {
            addCriterion("V_LIMIT <", value, "vLimit");
            return (Criteria) this;
        }

        public Criteria andVLimitLessThanOrEqualTo(Long value) {
            addCriterion("V_LIMIT <=", value, "vLimit");
            return (Criteria) this;
        }

        public Criteria andVLimitIn(List<Long> values) {
            addCriterion("V_LIMIT in", values, "vLimit");
            return (Criteria) this;
        }

        public Criteria andVLimitNotIn(List<Long> values) {
            addCriterion("V_LIMIT not in", values, "vLimit");
            return (Criteria) this;
        }

        public Criteria andVLimitBetween(Long value1, Long value2) {
            addCriterion("V_LIMIT between", value1, value2, "vLimit");
            return (Criteria) this;
        }

        public Criteria andVLimitNotBetween(Long value1, Long value2) {
            addCriterion("V_LIMIT not between", value1, value2, "vLimit");
            return (Criteria) this;
        }

        public Criteria andANowIsNull() {
            addCriterion("A_NOW is null");
            return (Criteria) this;
        }

        public Criteria andANowIsNotNull() {
            addCriterion("A_NOW is not null");
            return (Criteria) this;
        }

        public Criteria andANowEqualTo(Long value) {
            addCriterion("A_NOW =", value, "aNow");
            return (Criteria) this;
        }

        public Criteria andANowNotEqualTo(Long value) {
            addCriterion("A_NOW <>", value, "aNow");
            return (Criteria) this;
        }

        public Criteria andANowGreaterThan(Long value) {
            addCriterion("A_NOW >", value, "aNow");
            return (Criteria) this;
        }

        public Criteria andANowGreaterThanOrEqualTo(Long value) {
            addCriterion("A_NOW >=", value, "aNow");
            return (Criteria) this;
        }

        public Criteria andANowLessThan(Long value) {
            addCriterion("A_NOW <", value, "aNow");
            return (Criteria) this;
        }

        public Criteria andANowLessThanOrEqualTo(Long value) {
            addCriterion("A_NOW <=", value, "aNow");
            return (Criteria) this;
        }

        public Criteria andANowIn(List<Long> values) {
            addCriterion("A_NOW in", values, "aNow");
            return (Criteria) this;
        }

        public Criteria andANowNotIn(List<Long> values) {
            addCriterion("A_NOW not in", values, "aNow");
            return (Criteria) this;
        }

        public Criteria andANowBetween(Long value1, Long value2) {
            addCriterion("A_NOW between", value1, value2, "aNow");
            return (Criteria) this;
        }

        public Criteria andANowNotBetween(Long value1, Long value2) {
            addCriterion("A_NOW not between", value1, value2, "aNow");
            return (Criteria) this;
        }

        public Criteria andALimitIsNull() {
            addCriterion("A_LIMIT is null");
            return (Criteria) this;
        }

        public Criteria andALimitIsNotNull() {
            addCriterion("A_LIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andALimitEqualTo(Long value) {
            addCriterion("A_LIMIT =", value, "aLimit");
            return (Criteria) this;
        }

        public Criteria andALimitNotEqualTo(Long value) {
            addCriterion("A_LIMIT <>", value, "aLimit");
            return (Criteria) this;
        }

        public Criteria andALimitGreaterThan(Long value) {
            addCriterion("A_LIMIT >", value, "aLimit");
            return (Criteria) this;
        }

        public Criteria andALimitGreaterThanOrEqualTo(Long value) {
            addCriterion("A_LIMIT >=", value, "aLimit");
            return (Criteria) this;
        }

        public Criteria andALimitLessThan(Long value) {
            addCriterion("A_LIMIT <", value, "aLimit");
            return (Criteria) this;
        }

        public Criteria andALimitLessThanOrEqualTo(Long value) {
            addCriterion("A_LIMIT <=", value, "aLimit");
            return (Criteria) this;
        }

        public Criteria andALimitIn(List<Long> values) {
            addCriterion("A_LIMIT in", values, "aLimit");
            return (Criteria) this;
        }

        public Criteria andALimitNotIn(List<Long> values) {
            addCriterion("A_LIMIT not in", values, "aLimit");
            return (Criteria) this;
        }

        public Criteria andALimitBetween(Long value1, Long value2) {
            addCriterion("A_LIMIT between", value1, value2, "aLimit");
            return (Criteria) this;
        }

        public Criteria andALimitNotBetween(Long value1, Long value2) {
            addCriterion("A_LIMIT not between", value1, value2, "aLimit");
            return (Criteria) this;
        }

        public Criteria andPNowIsNull() {
            addCriterion("P_NOW is null");
            return (Criteria) this;
        }

        public Criteria andPNowIsNotNull() {
            addCriterion("P_NOW is not null");
            return (Criteria) this;
        }

        public Criteria andPNowEqualTo(Long value) {
            addCriterion("P_NOW =", value, "pNow");
            return (Criteria) this;
        }

        public Criteria andPNowNotEqualTo(Long value) {
            addCriterion("P_NOW <>", value, "pNow");
            return (Criteria) this;
        }

        public Criteria andPNowGreaterThan(Long value) {
            addCriterion("P_NOW >", value, "pNow");
            return (Criteria) this;
        }

        public Criteria andPNowGreaterThanOrEqualTo(Long value) {
            addCriterion("P_NOW >=", value, "pNow");
            return (Criteria) this;
        }

        public Criteria andPNowLessThan(Long value) {
            addCriterion("P_NOW <", value, "pNow");
            return (Criteria) this;
        }

        public Criteria andPNowLessThanOrEqualTo(Long value) {
            addCriterion("P_NOW <=", value, "pNow");
            return (Criteria) this;
        }

        public Criteria andPNowIn(List<Long> values) {
            addCriterion("P_NOW in", values, "pNow");
            return (Criteria) this;
        }

        public Criteria andPNowNotIn(List<Long> values) {
            addCriterion("P_NOW not in", values, "pNow");
            return (Criteria) this;
        }

        public Criteria andPNowBetween(Long value1, Long value2) {
            addCriterion("P_NOW between", value1, value2, "pNow");
            return (Criteria) this;
        }

        public Criteria andPNowNotBetween(Long value1, Long value2) {
            addCriterion("P_NOW not between", value1, value2, "pNow");
            return (Criteria) this;
        }

        public Criteria andPLimitIsNull() {
            addCriterion("P_LIMIT is null");
            return (Criteria) this;
        }

        public Criteria andPLimitIsNotNull() {
            addCriterion("P_LIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andPLimitEqualTo(Long value) {
            addCriterion("P_LIMIT =", value, "pLimit");
            return (Criteria) this;
        }

        public Criteria andPLimitNotEqualTo(Long value) {
            addCriterion("P_LIMIT <>", value, "pLimit");
            return (Criteria) this;
        }

        public Criteria andPLimitGreaterThan(Long value) {
            addCriterion("P_LIMIT >", value, "pLimit");
            return (Criteria) this;
        }

        public Criteria andPLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("P_LIMIT >=", value, "pLimit");
            return (Criteria) this;
        }

        public Criteria andPLimitLessThan(Long value) {
            addCriterion("P_LIMIT <", value, "pLimit");
            return (Criteria) this;
        }

        public Criteria andPLimitLessThanOrEqualTo(Long value) {
            addCriterion("P_LIMIT <=", value, "pLimit");
            return (Criteria) this;
        }

        public Criteria andPLimitIn(List<Long> values) {
            addCriterion("P_LIMIT in", values, "pLimit");
            return (Criteria) this;
        }

        public Criteria andPLimitNotIn(List<Long> values) {
            addCriterion("P_LIMIT not in", values, "pLimit");
            return (Criteria) this;
        }

        public Criteria andPLimitBetween(Long value1, Long value2) {
            addCriterion("P_LIMIT between", value1, value2, "pLimit");
            return (Criteria) this;
        }

        public Criteria andPLimitNotBetween(Long value1, Long value2) {
            addCriterion("P_LIMIT not between", value1, value2, "pLimit");
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
