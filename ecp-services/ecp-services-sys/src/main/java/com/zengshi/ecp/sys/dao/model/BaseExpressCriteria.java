package com.zengshi.ecp.sys.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BaseExpressCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public BaseExpressCriteria() {
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

        public Criteria andExpressFullNameIsNull() {
            addCriterion("EXPRESS_FULL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andExpressFullNameIsNotNull() {
            addCriterion("EXPRESS_FULL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andExpressFullNameEqualTo(String value) {
            addCriterion("EXPRESS_FULL_NAME =", value, "expressFullName");
            return (Criteria) this;
        }

        public Criteria andExpressFullNameNotEqualTo(String value) {
            addCriterion("EXPRESS_FULL_NAME <>", value, "expressFullName");
            return (Criteria) this;
        }

        public Criteria andExpressFullNameGreaterThan(String value) {
            addCriterion("EXPRESS_FULL_NAME >", value, "expressFullName");
            return (Criteria) this;
        }

        public Criteria andExpressFullNameGreaterThanOrEqualTo(String value) {
            addCriterion("EXPRESS_FULL_NAME >=", value, "expressFullName");
            return (Criteria) this;
        }

        public Criteria andExpressFullNameLessThan(String value) {
            addCriterion("EXPRESS_FULL_NAME <", value, "expressFullName");
            return (Criteria) this;
        }

        public Criteria andExpressFullNameLessThanOrEqualTo(String value) {
            addCriterion("EXPRESS_FULL_NAME <=", value, "expressFullName");
            return (Criteria) this;
        }

        public Criteria andExpressFullNameLike(String value) {
            addCriterion("EXPRESS_FULL_NAME like", value, "expressFullName");
            return (Criteria) this;
        }

        public Criteria andExpressFullNameNotLike(String value) {
            addCriterion("EXPRESS_FULL_NAME not like", value, "expressFullName");
            return (Criteria) this;
        }

        public Criteria andExpressFullNameIn(List<String> values) {
            addCriterion("EXPRESS_FULL_NAME in", values, "expressFullName");
            return (Criteria) this;
        }

        public Criteria andExpressFullNameNotIn(List<String> values) {
            addCriterion("EXPRESS_FULL_NAME not in", values, "expressFullName");
            return (Criteria) this;
        }

        public Criteria andExpressFullNameBetween(String value1, String value2) {
            addCriterion("EXPRESS_FULL_NAME between", value1, value2, "expressFullName");
            return (Criteria) this;
        }

        public Criteria andExpressFullNameNotBetween(String value1, String value2) {
            addCriterion("EXPRESS_FULL_NAME not between", value1, value2, "expressFullName");
            return (Criteria) this;
        }

        public Criteria andExpressNameIsNull() {
            addCriterion("EXPRESS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andExpressNameIsNotNull() {
            addCriterion("EXPRESS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andExpressNameEqualTo(String value) {
            addCriterion("EXPRESS_NAME =", value, "expressName");
            return (Criteria) this;
        }

        public Criteria andExpressNameNotEqualTo(String value) {
            addCriterion("EXPRESS_NAME <>", value, "expressName");
            return (Criteria) this;
        }

        public Criteria andExpressNameGreaterThan(String value) {
            addCriterion("EXPRESS_NAME >", value, "expressName");
            return (Criteria) this;
        }

        public Criteria andExpressNameGreaterThanOrEqualTo(String value) {
            addCriterion("EXPRESS_NAME >=", value, "expressName");
            return (Criteria) this;
        }

        public Criteria andExpressNameLessThan(String value) {
            addCriterion("EXPRESS_NAME <", value, "expressName");
            return (Criteria) this;
        }

        public Criteria andExpressNameLessThanOrEqualTo(String value) {
            addCriterion("EXPRESS_NAME <=", value, "expressName");
            return (Criteria) this;
        }

        public Criteria andExpressNameLike(String value) {
            addCriterion("EXPRESS_NAME like", value, "expressName");
            return (Criteria) this;
        }

        public Criteria andExpressNameNotLike(String value) {
            addCriterion("EXPRESS_NAME not like", value, "expressName");
            return (Criteria) this;
        }

        public Criteria andExpressNameIn(List<String> values) {
            addCriterion("EXPRESS_NAME in", values, "expressName");
            return (Criteria) this;
        }

        public Criteria andExpressNameNotIn(List<String> values) {
            addCriterion("EXPRESS_NAME not in", values, "expressName");
            return (Criteria) this;
        }

        public Criteria andExpressNameBetween(String value1, String value2) {
            addCriterion("EXPRESS_NAME between", value1, value2, "expressName");
            return (Criteria) this;
        }

        public Criteria andExpressNameNotBetween(String value1, String value2) {
            addCriterion("EXPRESS_NAME not between", value1, value2, "expressName");
            return (Criteria) this;
        }

        public Criteria andExpressWebsiteIsNull() {
            addCriterion("EXPRESS_WEBSITE is null");
            return (Criteria) this;
        }

        public Criteria andExpressWebsiteIsNotNull() {
            addCriterion("EXPRESS_WEBSITE is not null");
            return (Criteria) this;
        }

        public Criteria andExpressWebsiteEqualTo(String value) {
            addCriterion("EXPRESS_WEBSITE =", value, "expressWebsite");
            return (Criteria) this;
        }

        public Criteria andExpressWebsiteNotEqualTo(String value) {
            addCriterion("EXPRESS_WEBSITE <>", value, "expressWebsite");
            return (Criteria) this;
        }

        public Criteria andExpressWebsiteGreaterThan(String value) {
            addCriterion("EXPRESS_WEBSITE >", value, "expressWebsite");
            return (Criteria) this;
        }

        public Criteria andExpressWebsiteGreaterThanOrEqualTo(String value) {
            addCriterion("EXPRESS_WEBSITE >=", value, "expressWebsite");
            return (Criteria) this;
        }

        public Criteria andExpressWebsiteLessThan(String value) {
            addCriterion("EXPRESS_WEBSITE <", value, "expressWebsite");
            return (Criteria) this;
        }

        public Criteria andExpressWebsiteLessThanOrEqualTo(String value) {
            addCriterion("EXPRESS_WEBSITE <=", value, "expressWebsite");
            return (Criteria) this;
        }

        public Criteria andExpressWebsiteLike(String value) {
            addCriterion("EXPRESS_WEBSITE like", value, "expressWebsite");
            return (Criteria) this;
        }

        public Criteria andExpressWebsiteNotLike(String value) {
            addCriterion("EXPRESS_WEBSITE not like", value, "expressWebsite");
            return (Criteria) this;
        }

        public Criteria andExpressWebsiteIn(List<String> values) {
            addCriterion("EXPRESS_WEBSITE in", values, "expressWebsite");
            return (Criteria) this;
        }

        public Criteria andExpressWebsiteNotIn(List<String> values) {
            addCriterion("EXPRESS_WEBSITE not in", values, "expressWebsite");
            return (Criteria) this;
        }

        public Criteria andExpressWebsiteBetween(String value1, String value2) {
            addCriterion("EXPRESS_WEBSITE between", value1, value2, "expressWebsite");
            return (Criteria) this;
        }

        public Criteria andExpressWebsiteNotBetween(String value1, String value2) {
            addCriterion("EXPRESS_WEBSITE not between", value1, value2, "expressWebsite");
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

        public Criteria andSortNoIsNull() {
            addCriterion("SORT_NO is null");
            return (Criteria) this;
        }

        public Criteria andSortNoIsNotNull() {
            addCriterion("SORT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andSortNoEqualTo(BigDecimal value) {
            addCriterion("SORT_NO =", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotEqualTo(BigDecimal value) {
            addCriterion("SORT_NO <>", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThan(BigDecimal value) {
            addCriterion("SORT_NO >", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SORT_NO >=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThan(BigDecimal value) {
            addCriterion("SORT_NO <", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SORT_NO <=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoIn(List<BigDecimal> values) {
            addCriterion("SORT_NO in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotIn(List<BigDecimal> values) {
            addCriterion("SORT_NO not in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SORT_NO between", value1, value2, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SORT_NO not between", value1, value2, "sortNo");
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
