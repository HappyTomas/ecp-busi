package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrdBackApplyCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OrdBackApplyCriteria() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("ORDER_ID like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("ORDER_ID not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIsNull() {
            addCriterion("APPLY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIsNotNull() {
            addCriterion("APPLY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeEqualTo(String value) {
            addCriterion("APPLY_TYPE =", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotEqualTo(String value) {
            addCriterion("APPLY_TYPE <>", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeGreaterThan(String value) {
            addCriterion("APPLY_TYPE >", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_TYPE >=", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeLessThan(String value) {
            addCriterion("APPLY_TYPE <", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeLessThanOrEqualTo(String value) {
            addCriterion("APPLY_TYPE <=", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeLike(String value) {
            addCriterion("APPLY_TYPE like", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotLike(String value) {
            addCriterion("APPLY_TYPE not like", value, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIn(List<String> values) {
            addCriterion("APPLY_TYPE in", values, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotIn(List<String> values) {
            addCriterion("APPLY_TYPE not in", values, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeBetween(String value1, String value2) {
            addCriterion("APPLY_TYPE between", value1, value2, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNotBetween(String value1, String value2) {
            addCriterion("APPLY_TYPE not between", value1, value2, "applyType");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIsNull() {
            addCriterion("APPLY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIsNotNull() {
            addCriterion("APPLY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTimeEqualTo(Timestamp value) {
            addCriterion("APPLY_TIME =", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotEqualTo(Timestamp value) {
            addCriterion("APPLY_TIME <>", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeGreaterThan(Timestamp value) {
            addCriterion("APPLY_TIME >", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("APPLY_TIME >=", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLessThan(Timestamp value) {
            addCriterion("APPLY_TIME <", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("APPLY_TIME <=", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIn(List<Timestamp> values) {
            addCriterion("APPLY_TIME in", values, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotIn(List<Timestamp> values) {
            addCriterion("APPLY_TIME not in", values, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("APPLY_TIME between", value1, value2, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("APPLY_TIME not between", value1, value2, "applyTime");
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

        public Criteria andBackTypeIsNull() {
            addCriterion("BACK_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andBackTypeIsNotNull() {
            addCriterion("BACK_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andBackTypeEqualTo(String value) {
            addCriterion("BACK_TYPE =", value, "backType");
            return (Criteria) this;
        }

        public Criteria andBackTypeNotEqualTo(String value) {
            addCriterion("BACK_TYPE <>", value, "backType");
            return (Criteria) this;
        }

        public Criteria andBackTypeGreaterThan(String value) {
            addCriterion("BACK_TYPE >", value, "backType");
            return (Criteria) this;
        }

        public Criteria andBackTypeGreaterThanOrEqualTo(String value) {
            addCriterion("BACK_TYPE >=", value, "backType");
            return (Criteria) this;
        }

        public Criteria andBackTypeLessThan(String value) {
            addCriterion("BACK_TYPE <", value, "backType");
            return (Criteria) this;
        }

        public Criteria andBackTypeLessThanOrEqualTo(String value) {
            addCriterion("BACK_TYPE <=", value, "backType");
            return (Criteria) this;
        }

        public Criteria andBackTypeLike(String value) {
            addCriterion("BACK_TYPE like", value, "backType");
            return (Criteria) this;
        }

        public Criteria andBackTypeNotLike(String value) {
            addCriterion("BACK_TYPE not like", value, "backType");
            return (Criteria) this;
        }

        public Criteria andBackTypeIn(List<String> values) {
            addCriterion("BACK_TYPE in", values, "backType");
            return (Criteria) this;
        }

        public Criteria andBackTypeNotIn(List<String> values) {
            addCriterion("BACK_TYPE not in", values, "backType");
            return (Criteria) this;
        }

        public Criteria andBackTypeBetween(String value1, String value2) {
            addCriterion("BACK_TYPE between", value1, value2, "backType");
            return (Criteria) this;
        }

        public Criteria andBackTypeNotBetween(String value1, String value2) {
            addCriterion("BACK_TYPE not between", value1, value2, "backType");
            return (Criteria) this;
        }

        public Criteria andBackTypeNameIsNull() {
            addCriterion("BACK_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBackTypeNameIsNotNull() {
            addCriterion("BACK_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBackTypeNameEqualTo(String value) {
            addCriterion("BACK_TYPE_NAME =", value, "backTypeName");
            return (Criteria) this;
        }

        public Criteria andBackTypeNameNotEqualTo(String value) {
            addCriterion("BACK_TYPE_NAME <>", value, "backTypeName");
            return (Criteria) this;
        }

        public Criteria andBackTypeNameGreaterThan(String value) {
            addCriterion("BACK_TYPE_NAME >", value, "backTypeName");
            return (Criteria) this;
        }

        public Criteria andBackTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("BACK_TYPE_NAME >=", value, "backTypeName");
            return (Criteria) this;
        }

        public Criteria andBackTypeNameLessThan(String value) {
            addCriterion("BACK_TYPE_NAME <", value, "backTypeName");
            return (Criteria) this;
        }

        public Criteria andBackTypeNameLessThanOrEqualTo(String value) {
            addCriterion("BACK_TYPE_NAME <=", value, "backTypeName");
            return (Criteria) this;
        }

        public Criteria andBackTypeNameLike(String value) {
            addCriterion("BACK_TYPE_NAME like", value, "backTypeName");
            return (Criteria) this;
        }

        public Criteria andBackTypeNameNotLike(String value) {
            addCriterion("BACK_TYPE_NAME not like", value, "backTypeName");
            return (Criteria) this;
        }

        public Criteria andBackTypeNameIn(List<String> values) {
            addCriterion("BACK_TYPE_NAME in", values, "backTypeName");
            return (Criteria) this;
        }

        public Criteria andBackTypeNameNotIn(List<String> values) {
            addCriterion("BACK_TYPE_NAME not in", values, "backTypeName");
            return (Criteria) this;
        }

        public Criteria andBackTypeNameBetween(String value1, String value2) {
            addCriterion("BACK_TYPE_NAME between", value1, value2, "backTypeName");
            return (Criteria) this;
        }

        public Criteria andBackTypeNameNotBetween(String value1, String value2) {
            addCriterion("BACK_TYPE_NAME not between", value1, value2, "backTypeName");
            return (Criteria) this;
        }

        public Criteria andBackDescIsNull() {
            addCriterion("BACK_DESC is null");
            return (Criteria) this;
        }

        public Criteria andBackDescIsNotNull() {
            addCriterion("BACK_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andBackDescEqualTo(String value) {
            addCriterion("BACK_DESC =", value, "backDesc");
            return (Criteria) this;
        }

        public Criteria andBackDescNotEqualTo(String value) {
            addCriterion("BACK_DESC <>", value, "backDesc");
            return (Criteria) this;
        }

        public Criteria andBackDescGreaterThan(String value) {
            addCriterion("BACK_DESC >", value, "backDesc");
            return (Criteria) this;
        }

        public Criteria andBackDescGreaterThanOrEqualTo(String value) {
            addCriterion("BACK_DESC >=", value, "backDesc");
            return (Criteria) this;
        }

        public Criteria andBackDescLessThan(String value) {
            addCriterion("BACK_DESC <", value, "backDesc");
            return (Criteria) this;
        }

        public Criteria andBackDescLessThanOrEqualTo(String value) {
            addCriterion("BACK_DESC <=", value, "backDesc");
            return (Criteria) this;
        }

        public Criteria andBackDescLike(String value) {
            addCriterion("BACK_DESC like", value, "backDesc");
            return (Criteria) this;
        }

        public Criteria andBackDescNotLike(String value) {
            addCriterion("BACK_DESC not like", value, "backDesc");
            return (Criteria) this;
        }

        public Criteria andBackDescIn(List<String> values) {
            addCriterion("BACK_DESC in", values, "backDesc");
            return (Criteria) this;
        }

        public Criteria andBackDescNotIn(List<String> values) {
            addCriterion("BACK_DESC not in", values, "backDesc");
            return (Criteria) this;
        }

        public Criteria andBackDescBetween(String value1, String value2) {
            addCriterion("BACK_DESC between", value1, value2, "backDesc");
            return (Criteria) this;
        }

        public Criteria andBackDescNotBetween(String value1, String value2) {
            addCriterion("BACK_DESC not between", value1, value2, "backDesc");
            return (Criteria) this;
        }

        public Criteria andIsEntireIsNull() {
            addCriterion("IS_ENTIRE is null");
            return (Criteria) this;
        }

        public Criteria andIsEntireIsNotNull() {
            addCriterion("IS_ENTIRE is not null");
            return (Criteria) this;
        }

        public Criteria andIsEntireEqualTo(String value) {
            addCriterion("IS_ENTIRE =", value, "isEntire");
            return (Criteria) this;
        }

        public Criteria andIsEntireNotEqualTo(String value) {
            addCriterion("IS_ENTIRE <>", value, "isEntire");
            return (Criteria) this;
        }

        public Criteria andIsEntireGreaterThan(String value) {
            addCriterion("IS_ENTIRE >", value, "isEntire");
            return (Criteria) this;
        }

        public Criteria andIsEntireGreaterThanOrEqualTo(String value) {
            addCriterion("IS_ENTIRE >=", value, "isEntire");
            return (Criteria) this;
        }

        public Criteria andIsEntireLessThan(String value) {
            addCriterion("IS_ENTIRE <", value, "isEntire");
            return (Criteria) this;
        }

        public Criteria andIsEntireLessThanOrEqualTo(String value) {
            addCriterion("IS_ENTIRE <=", value, "isEntire");
            return (Criteria) this;
        }

        public Criteria andIsEntireLike(String value) {
            addCriterion("IS_ENTIRE like", value, "isEntire");
            return (Criteria) this;
        }

        public Criteria andIsEntireNotLike(String value) {
            addCriterion("IS_ENTIRE not like", value, "isEntire");
            return (Criteria) this;
        }

        public Criteria andIsEntireIn(List<String> values) {
            addCriterion("IS_ENTIRE in", values, "isEntire");
            return (Criteria) this;
        }

        public Criteria andIsEntireNotIn(List<String> values) {
            addCriterion("IS_ENTIRE not in", values, "isEntire");
            return (Criteria) this;
        }

        public Criteria andIsEntireBetween(String value1, String value2) {
            addCriterion("IS_ENTIRE between", value1, value2, "isEntire");
            return (Criteria) this;
        }

        public Criteria andIsEntireNotBetween(String value1, String value2) {
            addCriterion("IS_ENTIRE not between", value1, value2, "isEntire");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("NUM is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("NUM is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Long value) {
            addCriterion("NUM =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Long value) {
            addCriterion("NUM <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Long value) {
            addCriterion("NUM >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Long value) {
            addCriterion("NUM >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Long value) {
            addCriterion("NUM <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Long value) {
            addCriterion("NUM <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Long> values) {
            addCriterion("NUM in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Long> values) {
            addCriterion("NUM not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Long value1, Long value2) {
            addCriterion("NUM between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Long value1, Long value2) {
            addCriterion("NUM not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNull() {
            addCriterion("STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNotNull() {
            addCriterion("STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStaffIdEqualTo(Long value) {
            addCriterion("STAFF_ID =", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotEqualTo(Long value) {
            addCriterion("STAFF_ID <>", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThan(Long value) {
            addCriterion("STAFF_ID >", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThanOrEqualTo(Long value) {
            addCriterion("STAFF_ID >=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThan(Long value) {
            addCriterion("STAFF_ID <", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThanOrEqualTo(Long value) {
            addCriterion("STAFF_ID <=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdIn(List<Long> values) {
            addCriterion("STAFF_ID in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotIn(List<Long> values) {
            addCriterion("STAFF_ID not in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdBetween(Long value1, Long value2) {
            addCriterion("STAFF_ID between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotBetween(Long value1, Long value2) {
            addCriterion("STAFF_ID not between", value1, value2, "staffId");
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

        public Criteria andCheckDescIsNull() {
            addCriterion("CHECK_DESC is null");
            return (Criteria) this;
        }

        public Criteria andCheckDescIsNotNull() {
            addCriterion("CHECK_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andCheckDescEqualTo(String value) {
            addCriterion("CHECK_DESC =", value, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescNotEqualTo(String value) {
            addCriterion("CHECK_DESC <>", value, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescGreaterThan(String value) {
            addCriterion("CHECK_DESC >", value, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_DESC >=", value, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescLessThan(String value) {
            addCriterion("CHECK_DESC <", value, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescLessThanOrEqualTo(String value) {
            addCriterion("CHECK_DESC <=", value, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescLike(String value) {
            addCriterion("CHECK_DESC like", value, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescNotLike(String value) {
            addCriterion("CHECK_DESC not like", value, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescIn(List<String> values) {
            addCriterion("CHECK_DESC in", values, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescNotIn(List<String> values) {
            addCriterion("CHECK_DESC not in", values, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescBetween(String value1, String value2) {
            addCriterion("CHECK_DESC between", value1, value2, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andCheckDescNotBetween(String value1, String value2) {
            addCriterion("CHECK_DESC not between", value1, value2, "checkDesc");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("PAY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("PAY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(String value) {
            addCriterion("PAY_TYPE =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(String value) {
            addCriterion("PAY_TYPE <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(String value) {
            addCriterion("PAY_TYPE >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_TYPE >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(String value) {
            addCriterion("PAY_TYPE <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(String value) {
            addCriterion("PAY_TYPE <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLike(String value) {
            addCriterion("PAY_TYPE like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotLike(String value) {
            addCriterion("PAY_TYPE not like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<String> values) {
            addCriterion("PAY_TYPE in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<String> values) {
            addCriterion("PAY_TYPE not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(String value1, String value2) {
            addCriterion("PAY_TYPE between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(String value1, String value2) {
            addCriterion("PAY_TYPE not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andBackMoneyIsNull() {
            addCriterion("BACK_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andBackMoneyIsNotNull() {
            addCriterion("BACK_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andBackMoneyEqualTo(Long value) {
            addCriterion("BACK_MONEY =", value, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyNotEqualTo(Long value) {
            addCriterion("BACK_MONEY <>", value, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyGreaterThan(Long value) {
            addCriterion("BACK_MONEY >", value, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("BACK_MONEY >=", value, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyLessThan(Long value) {
            addCriterion("BACK_MONEY <", value, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyLessThanOrEqualTo(Long value) {
            addCriterion("BACK_MONEY <=", value, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyIn(List<Long> values) {
            addCriterion("BACK_MONEY in", values, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyNotIn(List<Long> values) {
            addCriterion("BACK_MONEY not in", values, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyBetween(Long value1, Long value2) {
            addCriterion("BACK_MONEY between", value1, value2, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackMoneyNotBetween(Long value1, Long value2) {
            addCriterion("BACK_MONEY not between", value1, value2, "backMoney");
            return (Criteria) this;
        }

        public Criteria andBackScoreIsNull() {
            addCriterion("BACK_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andBackScoreIsNotNull() {
            addCriterion("BACK_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andBackScoreEqualTo(Long value) {
            addCriterion("BACK_SCORE =", value, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreNotEqualTo(Long value) {
            addCriterion("BACK_SCORE <>", value, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreGreaterThan(Long value) {
            addCriterion("BACK_SCORE >", value, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("BACK_SCORE >=", value, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreLessThan(Long value) {
            addCriterion("BACK_SCORE <", value, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreLessThanOrEqualTo(Long value) {
            addCriterion("BACK_SCORE <=", value, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreIn(List<Long> values) {
            addCriterion("BACK_SCORE in", values, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreNotIn(List<Long> values) {
            addCriterion("BACK_SCORE not in", values, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreBetween(Long value1, Long value2) {
            addCriterion("BACK_SCORE between", value1, value2, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackScoreNotBetween(Long value1, Long value2) {
            addCriterion("BACK_SCORE not between", value1, value2, "backScore");
            return (Criteria) this;
        }

        public Criteria andBackAccountIsNull() {
            addCriterion("BACK_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andBackAccountIsNotNull() {
            addCriterion("BACK_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andBackAccountEqualTo(Long value) {
            addCriterion("BACK_ACCOUNT =", value, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountNotEqualTo(Long value) {
            addCriterion("BACK_ACCOUNT <>", value, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountGreaterThan(Long value) {
            addCriterion("BACK_ACCOUNT >", value, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountGreaterThanOrEqualTo(Long value) {
            addCriterion("BACK_ACCOUNT >=", value, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountLessThan(Long value) {
            addCriterion("BACK_ACCOUNT <", value, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountLessThanOrEqualTo(Long value) {
            addCriterion("BACK_ACCOUNT <=", value, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountIn(List<Long> values) {
            addCriterion("BACK_ACCOUNT in", values, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountNotIn(List<Long> values) {
            addCriterion("BACK_ACCOUNT not in", values, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountBetween(Long value1, Long value2) {
            addCriterion("BACK_ACCOUNT between", value1, value2, "backAccount");
            return (Criteria) this;
        }

        public Criteria andBackAccountNotBetween(Long value1, Long value2) {
            addCriterion("BACK_ACCOUNT not between", value1, value2, "backAccount");
            return (Criteria) this;
        }

        public Criteria andExpressIdIsNull() {
            addCriterion("EXPRESS_ID is null");
            return (Criteria) this;
        }

        public Criteria andExpressIdIsNotNull() {
            addCriterion("EXPRESS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExpressIdEqualTo(Long value) {
            addCriterion("EXPRESS_ID =", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotEqualTo(Long value) {
            addCriterion("EXPRESS_ID <>", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThan(Long value) {
            addCriterion("EXPRESS_ID >", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThanOrEqualTo(Long value) {
            addCriterion("EXPRESS_ID >=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThan(Long value) {
            addCriterion("EXPRESS_ID <", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThanOrEqualTo(Long value) {
            addCriterion("EXPRESS_ID <=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdIn(List<Long> values) {
            addCriterion("EXPRESS_ID in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotIn(List<Long> values) {
            addCriterion("EXPRESS_ID not in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdBetween(Long value1, Long value2) {
            addCriterion("EXPRESS_ID between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotBetween(Long value1, Long value2) {
            addCriterion("EXPRESS_ID not between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIsNull() {
            addCriterion("EXPRESS is null");
            return (Criteria) this;
        }

        public Criteria andExpressIsNotNull() {
            addCriterion("EXPRESS is not null");
            return (Criteria) this;
        }

        public Criteria andExpressEqualTo(String value) {
            addCriterion("EXPRESS =", value, "express");
            return (Criteria) this;
        }

        public Criteria andExpressNotEqualTo(String value) {
            addCriterion("EXPRESS <>", value, "express");
            return (Criteria) this;
        }

        public Criteria andExpressGreaterThan(String value) {
            addCriterion("EXPRESS >", value, "express");
            return (Criteria) this;
        }

        public Criteria andExpressGreaterThanOrEqualTo(String value) {
            addCriterion("EXPRESS >=", value, "express");
            return (Criteria) this;
        }

        public Criteria andExpressLessThan(String value) {
            addCriterion("EXPRESS <", value, "express");
            return (Criteria) this;
        }

        public Criteria andExpressLessThanOrEqualTo(String value) {
            addCriterion("EXPRESS <=", value, "express");
            return (Criteria) this;
        }

        public Criteria andExpressLike(String value) {
            addCriterion("EXPRESS like", value, "express");
            return (Criteria) this;
        }

        public Criteria andExpressNotLike(String value) {
            addCriterion("EXPRESS not like", value, "express");
            return (Criteria) this;
        }

        public Criteria andExpressIn(List<String> values) {
            addCriterion("EXPRESS in", values, "express");
            return (Criteria) this;
        }

        public Criteria andExpressNotIn(List<String> values) {
            addCriterion("EXPRESS not in", values, "express");
            return (Criteria) this;
        }

        public Criteria andExpressBetween(String value1, String value2) {
            addCriterion("EXPRESS between", value1, value2, "express");
            return (Criteria) this;
        }

        public Criteria andExpressNotBetween(String value1, String value2) {
            addCriterion("EXPRESS not between", value1, value2, "express");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNull() {
            addCriterion("EXPRESS_NO is null");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNotNull() {
            addCriterion("EXPRESS_NO is not null");
            return (Criteria) this;
        }

        public Criteria andExpressNoEqualTo(String value) {
            addCriterion("EXPRESS_NO =", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotEqualTo(String value) {
            addCriterion("EXPRESS_NO <>", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThan(String value) {
            addCriterion("EXPRESS_NO >", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("EXPRESS_NO >=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThan(String value) {
            addCriterion("EXPRESS_NO <", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThanOrEqualTo(String value) {
            addCriterion("EXPRESS_NO <=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLike(String value) {
            addCriterion("EXPRESS_NO like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotLike(String value) {
            addCriterion("EXPRESS_NO not like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoIn(List<String> values) {
            addCriterion("EXPRESS_NO in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotIn(List<String> values) {
            addCriterion("EXPRESS_NO not in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoBetween(String value1, String value2) {
            addCriterion("EXPRESS_NO between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotBetween(String value1, String value2) {
            addCriterion("EXPRESS_NO not between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andSiteIdIsNull() {
            addCriterion("SITE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSiteIdIsNotNull() {
            addCriterion("SITE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSiteIdEqualTo(Long value) {
            addCriterion("SITE_ID =", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotEqualTo(Long value) {
            addCriterion("SITE_ID <>", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThan(Long value) {
            addCriterion("SITE_ID >", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SITE_ID >=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThan(Long value) {
            addCriterion("SITE_ID <", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThanOrEqualTo(Long value) {
            addCriterion("SITE_ID <=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdIn(List<Long> values) {
            addCriterion("SITE_ID in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotIn(List<Long> values) {
            addCriterion("SITE_ID not in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdBetween(Long value1, Long value2) {
            addCriterion("SITE_ID between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotBetween(Long value1, Long value2) {
            addCriterion("SITE_ID not between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileIsNull() {
            addCriterion("IS_IN_AUDIT_FILE is null");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileIsNotNull() {
            addCriterion("IS_IN_AUDIT_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileEqualTo(String value) {
            addCriterion("IS_IN_AUDIT_FILE =", value, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileNotEqualTo(String value) {
            addCriterion("IS_IN_AUDIT_FILE <>", value, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileGreaterThan(String value) {
            addCriterion("IS_IN_AUDIT_FILE >", value, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileGreaterThanOrEqualTo(String value) {
            addCriterion("IS_IN_AUDIT_FILE >=", value, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileLessThan(String value) {
            addCriterion("IS_IN_AUDIT_FILE <", value, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileLessThanOrEqualTo(String value) {
            addCriterion("IS_IN_AUDIT_FILE <=", value, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileLike(String value) {
            addCriterion("IS_IN_AUDIT_FILE like", value, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileNotLike(String value) {
            addCriterion("IS_IN_AUDIT_FILE not like", value, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileIn(List<String> values) {
            addCriterion("IS_IN_AUDIT_FILE in", values, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileNotIn(List<String> values) {
            addCriterion("IS_IN_AUDIT_FILE not in", values, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileBetween(String value1, String value2) {
            addCriterion("IS_IN_AUDIT_FILE between", value1, value2, "isInAuditFile");
            return (Criteria) this;
        }

        public Criteria andIsInAuditFileNotBetween(String value1, String value2) {
            addCriterion("IS_IN_AUDIT_FILE not between", value1, value2, "isInAuditFile");
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

        public Criteria andIsCompenstateIsNull() {
            addCriterion("IS_COMPENSTATE is null");
            return (Criteria) this;
        }

        public Criteria andIsCompenstateIsNotNull() {
            addCriterion("IS_COMPENSTATE is not null");
            return (Criteria) this;
        }

        public Criteria andIsCompenstateEqualTo(String value) {
            addCriterion("IS_COMPENSTATE =", value, "isCompenstate");
            return (Criteria) this;
        }

        public Criteria andIsCompenstateNotEqualTo(String value) {
            addCriterion("IS_COMPENSTATE <>", value, "isCompenstate");
            return (Criteria) this;
        }

        public Criteria andIsCompenstateGreaterThan(String value) {
            addCriterion("IS_COMPENSTATE >", value, "isCompenstate");
            return (Criteria) this;
        }

        public Criteria andIsCompenstateGreaterThanOrEqualTo(String value) {
            addCriterion("IS_COMPENSTATE >=", value, "isCompenstate");
            return (Criteria) this;
        }

        public Criteria andIsCompenstateLessThan(String value) {
            addCriterion("IS_COMPENSTATE <", value, "isCompenstate");
            return (Criteria) this;
        }

        public Criteria andIsCompenstateLessThanOrEqualTo(String value) {
            addCriterion("IS_COMPENSTATE <=", value, "isCompenstate");
            return (Criteria) this;
        }

        public Criteria andIsCompenstateLike(String value) {
            addCriterion("IS_COMPENSTATE like", value, "isCompenstate");
            return (Criteria) this;
        }

        public Criteria andIsCompenstateNotLike(String value) {
            addCriterion("IS_COMPENSTATE not like", value, "isCompenstate");
            return (Criteria) this;
        }

        public Criteria andIsCompenstateIn(List<String> values) {
            addCriterion("IS_COMPENSTATE in", values, "isCompenstate");
            return (Criteria) this;
        }

        public Criteria andIsCompenstateNotIn(List<String> values) {
            addCriterion("IS_COMPENSTATE not in", values, "isCompenstate");
            return (Criteria) this;
        }

        public Criteria andIsCompenstateBetween(String value1, String value2) {
            addCriterion("IS_COMPENSTATE between", value1, value2, "isCompenstate");
            return (Criteria) this;
        }

        public Criteria andIsCompenstateNotBetween(String value1, String value2) {
            addCriterion("IS_COMPENSTATE not between", value1, value2, "isCompenstate");
            return (Criteria) this;
        }

        public Criteria andPayTranNoIsNull() {
            addCriterion("PAY_TRAN_NO is null");
            return (Criteria) this;
        }

        public Criteria andPayTranNoIsNotNull() {
            addCriterion("PAY_TRAN_NO is not null");
            return (Criteria) this;
        }

        public Criteria andPayTranNoEqualTo(String value) {
            addCriterion("PAY_TRAN_NO =", value, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoNotEqualTo(String value) {
            addCriterion("PAY_TRAN_NO <>", value, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoGreaterThan(String value) {
            addCriterion("PAY_TRAN_NO >", value, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_TRAN_NO >=", value, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoLessThan(String value) {
            addCriterion("PAY_TRAN_NO <", value, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoLessThanOrEqualTo(String value) {
            addCriterion("PAY_TRAN_NO <=", value, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoLike(String value) {
            addCriterion("PAY_TRAN_NO like", value, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoNotLike(String value) {
            addCriterion("PAY_TRAN_NO not like", value, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoIn(List<String> values) {
            addCriterion("PAY_TRAN_NO in", values, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoNotIn(List<String> values) {
            addCriterion("PAY_TRAN_NO not in", values, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoBetween(String value1, String value2) {
            addCriterion("PAY_TRAN_NO between", value1, value2, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andPayTranNoNotBetween(String value1, String value2) {
            addCriterion("PAY_TRAN_NO not between", value1, value2, "payTranNo");
            return (Criteria) this;
        }

        public Criteria andRefundTimeIsNull() {
            addCriterion("REFUND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRefundTimeIsNotNull() {
            addCriterion("REFUND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRefundTimeEqualTo(Timestamp value) {
            addCriterion("REFUND_TIME =", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeNotEqualTo(Timestamp value) {
            addCriterion("REFUND_TIME <>", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeGreaterThan(Timestamp value) {
            addCriterion("REFUND_TIME >", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("REFUND_TIME >=", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeLessThan(Timestamp value) {
            addCriterion("REFUND_TIME <", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("REFUND_TIME <=", value, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeIn(List<Timestamp> values) {
            addCriterion("REFUND_TIME in", values, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeNotIn(List<Timestamp> values) {
            addCriterion("REFUND_TIME not in", values, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("REFUND_TIME between", value1, value2, "refundTime");
            return (Criteria) this;
        }

        public Criteria andRefundTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("REFUND_TIME not between", value1, value2, "refundTime");
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