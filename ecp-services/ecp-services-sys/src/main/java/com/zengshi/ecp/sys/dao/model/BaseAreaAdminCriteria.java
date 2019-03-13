package com.zengshi.ecp.sys.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BaseAreaAdminCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public BaseAreaAdminCriteria() {
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

        public Criteria andAreaCodeIsNull() {
            addCriterion("AREA_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNotNull() {
            addCriterion("AREA_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeEqualTo(String value) {
            addCriterion("AREA_CODE =", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotEqualTo(String value) {
            addCriterion("AREA_CODE <>", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThan(String value) {
            addCriterion("AREA_CODE >", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("AREA_CODE >=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThan(String value) {
            addCriterion("AREA_CODE <", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("AREA_CODE <=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLike(String value) {
            addCriterion("AREA_CODE like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotLike(String value) {
            addCriterion("AREA_CODE not like", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIn(List<String> values) {
            addCriterion("AREA_CODE in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotIn(List<String> values) {
            addCriterion("AREA_CODE not in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeBetween(String value1, String value2) {
            addCriterion("AREA_CODE between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotBetween(String value1, String value2) {
            addCriterion("AREA_CODE not between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNull() {
            addCriterion("AREA_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNotNull() {
            addCriterion("AREA_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAreaNameEqualTo(String value) {
            addCriterion("AREA_NAME =", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotEqualTo(String value) {
            addCriterion("AREA_NAME <>", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThan(String value) {
            addCriterion("AREA_NAME >", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("AREA_NAME >=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThan(String value) {
            addCriterion("AREA_NAME <", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThanOrEqualTo(String value) {
            addCriterion("AREA_NAME <=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLike(String value) {
            addCriterion("AREA_NAME like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotLike(String value) {
            addCriterion("AREA_NAME not like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameIn(List<String> values) {
            addCriterion("AREA_NAME in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotIn(List<String> values) {
            addCriterion("AREA_NAME not in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameBetween(String value1, String value2) {
            addCriterion("AREA_NAME between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotBetween(String value1, String value2) {
            addCriterion("AREA_NAME not between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andParentAreaCodeIsNull() {
            addCriterion("PARENT_AREA_CODE is null");
            return (Criteria) this;
        }

        public Criteria andParentAreaCodeIsNotNull() {
            addCriterion("PARENT_AREA_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andParentAreaCodeEqualTo(String value) {
            addCriterion("PARENT_AREA_CODE =", value, "parentAreaCode");
            return (Criteria) this;
        }

        public Criteria andParentAreaCodeNotEqualTo(String value) {
            addCriterion("PARENT_AREA_CODE <>", value, "parentAreaCode");
            return (Criteria) this;
        }

        public Criteria andParentAreaCodeGreaterThan(String value) {
            addCriterion("PARENT_AREA_CODE >", value, "parentAreaCode");
            return (Criteria) this;
        }

        public Criteria andParentAreaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_AREA_CODE >=", value, "parentAreaCode");
            return (Criteria) this;
        }

        public Criteria andParentAreaCodeLessThan(String value) {
            addCriterion("PARENT_AREA_CODE <", value, "parentAreaCode");
            return (Criteria) this;
        }

        public Criteria andParentAreaCodeLessThanOrEqualTo(String value) {
            addCriterion("PARENT_AREA_CODE <=", value, "parentAreaCode");
            return (Criteria) this;
        }

        public Criteria andParentAreaCodeLike(String value) {
            addCriterion("PARENT_AREA_CODE like", value, "parentAreaCode");
            return (Criteria) this;
        }

        public Criteria andParentAreaCodeNotLike(String value) {
            addCriterion("PARENT_AREA_CODE not like", value, "parentAreaCode");
            return (Criteria) this;
        }

        public Criteria andParentAreaCodeIn(List<String> values) {
            addCriterion("PARENT_AREA_CODE in", values, "parentAreaCode");
            return (Criteria) this;
        }

        public Criteria andParentAreaCodeNotIn(List<String> values) {
            addCriterion("PARENT_AREA_CODE not in", values, "parentAreaCode");
            return (Criteria) this;
        }

        public Criteria andParentAreaCodeBetween(String value1, String value2) {
            addCriterion("PARENT_AREA_CODE between", value1, value2, "parentAreaCode");
            return (Criteria) this;
        }

        public Criteria andParentAreaCodeNotBetween(String value1, String value2) {
            addCriterion("PARENT_AREA_CODE not between", value1, value2, "parentAreaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeShortIsNull() {
            addCriterion("AREA_CODE_SHORT is null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeShortIsNotNull() {
            addCriterion("AREA_CODE_SHORT is not null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeShortEqualTo(String value) {
            addCriterion("AREA_CODE_SHORT =", value, "areaCodeShort");
            return (Criteria) this;
        }

        public Criteria andAreaCodeShortNotEqualTo(String value) {
            addCriterion("AREA_CODE_SHORT <>", value, "areaCodeShort");
            return (Criteria) this;
        }

        public Criteria andAreaCodeShortGreaterThan(String value) {
            addCriterion("AREA_CODE_SHORT >", value, "areaCodeShort");
            return (Criteria) this;
        }

        public Criteria andAreaCodeShortGreaterThanOrEqualTo(String value) {
            addCriterion("AREA_CODE_SHORT >=", value, "areaCodeShort");
            return (Criteria) this;
        }

        public Criteria andAreaCodeShortLessThan(String value) {
            addCriterion("AREA_CODE_SHORT <", value, "areaCodeShort");
            return (Criteria) this;
        }

        public Criteria andAreaCodeShortLessThanOrEqualTo(String value) {
            addCriterion("AREA_CODE_SHORT <=", value, "areaCodeShort");
            return (Criteria) this;
        }

        public Criteria andAreaCodeShortLike(String value) {
            addCriterion("AREA_CODE_SHORT like", value, "areaCodeShort");
            return (Criteria) this;
        }

        public Criteria andAreaCodeShortNotLike(String value) {
            addCriterion("AREA_CODE_SHORT not like", value, "areaCodeShort");
            return (Criteria) this;
        }

        public Criteria andAreaCodeShortIn(List<String> values) {
            addCriterion("AREA_CODE_SHORT in", values, "areaCodeShort");
            return (Criteria) this;
        }

        public Criteria andAreaCodeShortNotIn(List<String> values) {
            addCriterion("AREA_CODE_SHORT not in", values, "areaCodeShort");
            return (Criteria) this;
        }

        public Criteria andAreaCodeShortBetween(String value1, String value2) {
            addCriterion("AREA_CODE_SHORT between", value1, value2, "areaCodeShort");
            return (Criteria) this;
        }

        public Criteria andAreaCodeShortNotBetween(String value1, String value2) {
            addCriterion("AREA_CODE_SHORT not between", value1, value2, "areaCodeShort");
            return (Criteria) this;
        }

        public Criteria andAreaLevelIsNull() {
            addCriterion("AREA_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andAreaLevelIsNotNull() {
            addCriterion("AREA_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andAreaLevelEqualTo(String value) {
            addCriterion("AREA_LEVEL =", value, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelNotEqualTo(String value) {
            addCriterion("AREA_LEVEL <>", value, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelGreaterThan(String value) {
            addCriterion("AREA_LEVEL >", value, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelGreaterThanOrEqualTo(String value) {
            addCriterion("AREA_LEVEL >=", value, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelLessThan(String value) {
            addCriterion("AREA_LEVEL <", value, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelLessThanOrEqualTo(String value) {
            addCriterion("AREA_LEVEL <=", value, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelLike(String value) {
            addCriterion("AREA_LEVEL like", value, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelNotLike(String value) {
            addCriterion("AREA_LEVEL not like", value, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelIn(List<String> values) {
            addCriterion("AREA_LEVEL in", values, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelNotIn(List<String> values) {
            addCriterion("AREA_LEVEL not in", values, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelBetween(String value1, String value2) {
            addCriterion("AREA_LEVEL between", value1, value2, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelNotBetween(String value1, String value2) {
            addCriterion("AREA_LEVEL not between", value1, value2, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaOrderIsNull() {
            addCriterion("AREA_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andAreaOrderIsNotNull() {
            addCriterion("AREA_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andAreaOrderEqualTo(BigDecimal value) {
            addCriterion("AREA_ORDER =", value, "areaOrder");
            return (Criteria) this;
        }

        public Criteria andAreaOrderNotEqualTo(BigDecimal value) {
            addCriterion("AREA_ORDER <>", value, "areaOrder");
            return (Criteria) this;
        }

        public Criteria andAreaOrderGreaterThan(BigDecimal value) {
            addCriterion("AREA_ORDER >", value, "areaOrder");
            return (Criteria) this;
        }

        public Criteria andAreaOrderGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("AREA_ORDER >=", value, "areaOrder");
            return (Criteria) this;
        }

        public Criteria andAreaOrderLessThan(BigDecimal value) {
            addCriterion("AREA_ORDER <", value, "areaOrder");
            return (Criteria) this;
        }

        public Criteria andAreaOrderLessThanOrEqualTo(BigDecimal value) {
            addCriterion("AREA_ORDER <=", value, "areaOrder");
            return (Criteria) this;
        }

        public Criteria andAreaOrderIn(List<BigDecimal> values) {
            addCriterion("AREA_ORDER in", values, "areaOrder");
            return (Criteria) this;
        }

        public Criteria andAreaOrderNotIn(List<BigDecimal> values) {
            addCriterion("AREA_ORDER not in", values, "areaOrder");
            return (Criteria) this;
        }

        public Criteria andAreaOrderBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AREA_ORDER between", value1, value2, "areaOrder");
            return (Criteria) this;
        }

        public Criteria andAreaOrderNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("AREA_ORDER not between", value1, value2, "areaOrder");
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

        public Criteria andCenterFlagIsNull() {
            addCriterion("CENTER_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andCenterFlagIsNotNull() {
            addCriterion("CENTER_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andCenterFlagEqualTo(String value) {
            addCriterion("CENTER_FLAG =", value, "centerFlag");
            return (Criteria) this;
        }

        public Criteria andCenterFlagNotEqualTo(String value) {
            addCriterion("CENTER_FLAG <>", value, "centerFlag");
            return (Criteria) this;
        }

        public Criteria andCenterFlagGreaterThan(String value) {
            addCriterion("CENTER_FLAG >", value, "centerFlag");
            return (Criteria) this;
        }

        public Criteria andCenterFlagGreaterThanOrEqualTo(String value) {
            addCriterion("CENTER_FLAG >=", value, "centerFlag");
            return (Criteria) this;
        }

        public Criteria andCenterFlagLessThan(String value) {
            addCriterion("CENTER_FLAG <", value, "centerFlag");
            return (Criteria) this;
        }

        public Criteria andCenterFlagLessThanOrEqualTo(String value) {
            addCriterion("CENTER_FLAG <=", value, "centerFlag");
            return (Criteria) this;
        }

        public Criteria andCenterFlagLike(String value) {
            addCriterion("CENTER_FLAG like", value, "centerFlag");
            return (Criteria) this;
        }

        public Criteria andCenterFlagNotLike(String value) {
            addCriterion("CENTER_FLAG not like", value, "centerFlag");
            return (Criteria) this;
        }

        public Criteria andCenterFlagIn(List<String> values) {
            addCriterion("CENTER_FLAG in", values, "centerFlag");
            return (Criteria) this;
        }

        public Criteria andCenterFlagNotIn(List<String> values) {
            addCriterion("CENTER_FLAG not in", values, "centerFlag");
            return (Criteria) this;
        }

        public Criteria andCenterFlagBetween(String value1, String value2) {
            addCriterion("CENTER_FLAG between", value1, value2, "centerFlag");
            return (Criteria) this;
        }

        public Criteria andCenterFlagNotBetween(String value1, String value2) {
            addCriterion("CENTER_FLAG not between", value1, value2, "centerFlag");
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
