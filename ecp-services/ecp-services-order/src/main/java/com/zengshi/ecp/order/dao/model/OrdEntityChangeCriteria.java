package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrdEntityChangeCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OrdEntityChangeCriteria() {
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

        public Criteria andFromTypeIsNull() {
            addCriterion("FROM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andFromTypeIsNotNull() {
            addCriterion("FROM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFromTypeEqualTo(String value) {
            addCriterion("FROM_TYPE =", value, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeNotEqualTo(String value) {
            addCriterion("FROM_TYPE <>", value, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeGreaterThan(String value) {
            addCriterion("FROM_TYPE >", value, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_TYPE >=", value, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeLessThan(String value) {
            addCriterion("FROM_TYPE <", value, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeLessThanOrEqualTo(String value) {
            addCriterion("FROM_TYPE <=", value, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeLike(String value) {
            addCriterion("FROM_TYPE like", value, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeNotLike(String value) {
            addCriterion("FROM_TYPE not like", value, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeIn(List<String> values) {
            addCriterion("FROM_TYPE in", values, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeNotIn(List<String> values) {
            addCriterion("FROM_TYPE not in", values, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeBetween(String value1, String value2) {
            addCriterion("FROM_TYPE between", value1, value2, "fromType");
            return (Criteria) this;
        }

        public Criteria andFromTypeNotBetween(String value1, String value2) {
            addCriterion("FROM_TYPE not between", value1, value2, "fromType");
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

        public Criteria andEntityCodeBfIsNull() {
            addCriterion("ENTITY_CODE_BF is null");
            return (Criteria) this;
        }

        public Criteria andEntityCodeBfIsNotNull() {
            addCriterion("ENTITY_CODE_BF is not null");
            return (Criteria) this;
        }

        public Criteria andEntityCodeBfEqualTo(String value) {
            addCriterion("ENTITY_CODE_BF =", value, "entityCodeBf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeBfNotEqualTo(String value) {
            addCriterion("ENTITY_CODE_BF <>", value, "entityCodeBf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeBfGreaterThan(String value) {
            addCriterion("ENTITY_CODE_BF >", value, "entityCodeBf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeBfGreaterThanOrEqualTo(String value) {
            addCriterion("ENTITY_CODE_BF >=", value, "entityCodeBf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeBfLessThan(String value) {
            addCriterion("ENTITY_CODE_BF <", value, "entityCodeBf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeBfLessThanOrEqualTo(String value) {
            addCriterion("ENTITY_CODE_BF <=", value, "entityCodeBf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeBfLike(String value) {
            addCriterion("ENTITY_CODE_BF like", value, "entityCodeBf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeBfNotLike(String value) {
            addCriterion("ENTITY_CODE_BF not like", value, "entityCodeBf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeBfIn(List<String> values) {
            addCriterion("ENTITY_CODE_BF in", values, "entityCodeBf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeBfNotIn(List<String> values) {
            addCriterion("ENTITY_CODE_BF not in", values, "entityCodeBf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeBfBetween(String value1, String value2) {
            addCriterion("ENTITY_CODE_BF between", value1, value2, "entityCodeBf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeBfNotBetween(String value1, String value2) {
            addCriterion("ENTITY_CODE_BF not between", value1, value2, "entityCodeBf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeAfIsNull() {
            addCriterion("ENTITY_CODE_AF is null");
            return (Criteria) this;
        }

        public Criteria andEntityCodeAfIsNotNull() {
            addCriterion("ENTITY_CODE_AF is not null");
            return (Criteria) this;
        }

        public Criteria andEntityCodeAfEqualTo(String value) {
            addCriterion("ENTITY_CODE_AF =", value, "entityCodeAf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeAfNotEqualTo(String value) {
            addCriterion("ENTITY_CODE_AF <>", value, "entityCodeAf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeAfGreaterThan(String value) {
            addCriterion("ENTITY_CODE_AF >", value, "entityCodeAf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeAfGreaterThanOrEqualTo(String value) {
            addCriterion("ENTITY_CODE_AF >=", value, "entityCodeAf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeAfLessThan(String value) {
            addCriterion("ENTITY_CODE_AF <", value, "entityCodeAf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeAfLessThanOrEqualTo(String value) {
            addCriterion("ENTITY_CODE_AF <=", value, "entityCodeAf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeAfLike(String value) {
            addCriterion("ENTITY_CODE_AF like", value, "entityCodeAf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeAfNotLike(String value) {
            addCriterion("ENTITY_CODE_AF not like", value, "entityCodeAf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeAfIn(List<String> values) {
            addCriterion("ENTITY_CODE_AF in", values, "entityCodeAf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeAfNotIn(List<String> values) {
            addCriterion("ENTITY_CODE_AF not in", values, "entityCodeAf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeAfBetween(String value1, String value2) {
            addCriterion("ENTITY_CODE_AF between", value1, value2, "entityCodeAf");
            return (Criteria) this;
        }

        public Criteria andEntityCodeAfNotBetween(String value1, String value2) {
            addCriterion("ENTITY_CODE_AF not between", value1, value2, "entityCodeAf");
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