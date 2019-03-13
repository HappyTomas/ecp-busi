package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrdEntityImportCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OrdEntityImportCriteria() {
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

        public Criteria andImportNoIsNull() {
            addCriterion("IMPORT_NO is null");
            return (Criteria) this;
        }

        public Criteria andImportNoIsNotNull() {
            addCriterion("IMPORT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andImportNoEqualTo(Long value) {
            addCriterion("IMPORT_NO =", value, "importNo");
            return (Criteria) this;
        }

        public Criteria andImportNoNotEqualTo(Long value) {
            addCriterion("IMPORT_NO <>", value, "importNo");
            return (Criteria) this;
        }

        public Criteria andImportNoGreaterThan(Long value) {
            addCriterion("IMPORT_NO >", value, "importNo");
            return (Criteria) this;
        }

        public Criteria andImportNoGreaterThanOrEqualTo(Long value) {
            addCriterion("IMPORT_NO >=", value, "importNo");
            return (Criteria) this;
        }

        public Criteria andImportNoLessThan(Long value) {
            addCriterion("IMPORT_NO <", value, "importNo");
            return (Criteria) this;
        }

        public Criteria andImportNoLessThanOrEqualTo(Long value) {
            addCriterion("IMPORT_NO <=", value, "importNo");
            return (Criteria) this;
        }

        public Criteria andImportNoIn(List<Long> values) {
            addCriterion("IMPORT_NO in", values, "importNo");
            return (Criteria) this;
        }

        public Criteria andImportNoNotIn(List<Long> values) {
            addCriterion("IMPORT_NO not in", values, "importNo");
            return (Criteria) this;
        }

        public Criteria andImportNoBetween(Long value1, Long value2) {
            addCriterion("IMPORT_NO between", value1, value2, "importNo");
            return (Criteria) this;
        }

        public Criteria andImportNoNotBetween(Long value1, Long value2) {
            addCriterion("IMPORT_NO not between", value1, value2, "importNo");
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

        public Criteria andOrderSubIdIsNull() {
            addCriterion("ORDER_SUB_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdIsNotNull() {
            addCriterion("ORDER_SUB_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdEqualTo(String value) {
            addCriterion("ORDER_SUB_ID =", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdNotEqualTo(String value) {
            addCriterion("ORDER_SUB_ID <>", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdGreaterThan(String value) {
            addCriterion("ORDER_SUB_ID >", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_SUB_ID >=", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdLessThan(String value) {
            addCriterion("ORDER_SUB_ID <", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdLessThanOrEqualTo(String value) {
            addCriterion("ORDER_SUB_ID <=", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdLike(String value) {
            addCriterion("ORDER_SUB_ID like", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdNotLike(String value) {
            addCriterion("ORDER_SUB_ID not like", value, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdIn(List<String> values) {
            addCriterion("ORDER_SUB_ID in", values, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdNotIn(List<String> values) {
            addCriterion("ORDER_SUB_ID not in", values, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdBetween(String value1, String value2) {
            addCriterion("ORDER_SUB_ID between", value1, value2, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andOrderSubIdNotBetween(String value1, String value2) {
            addCriterion("ORDER_SUB_ID not between", value1, value2, "orderSubId");
            return (Criteria) this;
        }

        public Criteria andEntityCodeIsNull() {
            addCriterion("ENTITY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andEntityCodeIsNotNull() {
            addCriterion("ENTITY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andEntityCodeEqualTo(String value) {
            addCriterion("ENTITY_CODE =", value, "entityCode");
            return (Criteria) this;
        }

        public Criteria andEntityCodeNotEqualTo(String value) {
            addCriterion("ENTITY_CODE <>", value, "entityCode");
            return (Criteria) this;
        }

        public Criteria andEntityCodeGreaterThan(String value) {
            addCriterion("ENTITY_CODE >", value, "entityCode");
            return (Criteria) this;
        }

        public Criteria andEntityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ENTITY_CODE >=", value, "entityCode");
            return (Criteria) this;
        }

        public Criteria andEntityCodeLessThan(String value) {
            addCriterion("ENTITY_CODE <", value, "entityCode");
            return (Criteria) this;
        }

        public Criteria andEntityCodeLessThanOrEqualTo(String value) {
            addCriterion("ENTITY_CODE <=", value, "entityCode");
            return (Criteria) this;
        }

        public Criteria andEntityCodeLike(String value) {
            addCriterion("ENTITY_CODE like", value, "entityCode");
            return (Criteria) this;
        }

        public Criteria andEntityCodeNotLike(String value) {
            addCriterion("ENTITY_CODE not like", value, "entityCode");
            return (Criteria) this;
        }

        public Criteria andEntityCodeIn(List<String> values) {
            addCriterion("ENTITY_CODE in", values, "entityCode");
            return (Criteria) this;
        }

        public Criteria andEntityCodeNotIn(List<String> values) {
            addCriterion("ENTITY_CODE not in", values, "entityCode");
            return (Criteria) this;
        }

        public Criteria andEntityCodeBetween(String value1, String value2) {
            addCriterion("ENTITY_CODE between", value1, value2, "entityCode");
            return (Criteria) this;
        }

        public Criteria andEntityCodeNotBetween(String value1, String value2) {
            addCriterion("ENTITY_CODE not between", value1, value2, "entityCode");
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