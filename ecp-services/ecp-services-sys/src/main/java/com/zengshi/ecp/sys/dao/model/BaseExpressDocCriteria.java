package com.zengshi.ecp.sys.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BaseExpressDocCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public BaseExpressDocCriteria() {
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

        public Criteria andTemplateNameIsNull() {
            addCriterion("TEMPLATE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIsNotNull() {
            addCriterion("TEMPLATE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateNameEqualTo(String value) {
            addCriterion("TEMPLATE_NAME =", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotEqualTo(String value) {
            addCriterion("TEMPLATE_NAME <>", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameGreaterThan(String value) {
            addCriterion("TEMPLATE_NAME >", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_NAME >=", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLessThan(String value) {
            addCriterion("TEMPLATE_NAME <", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLessThanOrEqualTo(String value) {
            addCriterion("TEMPLATE_NAME <=", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLike(String value) {
            addCriterion("TEMPLATE_NAME like", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotLike(String value) {
            addCriterion("TEMPLATE_NAME not like", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIn(List<String> values) {
            addCriterion("TEMPLATE_NAME in", values, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotIn(List<String> values) {
            addCriterion("TEMPLATE_NAME not in", values, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameBetween(String value1, String value2) {
            addCriterion("TEMPLATE_NAME between", value1, value2, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotBetween(String value1, String value2) {
            addCriterion("TEMPLATE_NAME not between", value1, value2, "templateName");
            return (Criteria) this;
        }

        public Criteria andDocPicIsNull() {
            addCriterion("DOC_PIC is null");
            return (Criteria) this;
        }

        public Criteria andDocPicIsNotNull() {
            addCriterion("DOC_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andDocPicEqualTo(String value) {
            addCriterion("DOC_PIC =", value, "docPic");
            return (Criteria) this;
        }

        public Criteria andDocPicNotEqualTo(String value) {
            addCriterion("DOC_PIC <>", value, "docPic");
            return (Criteria) this;
        }

        public Criteria andDocPicGreaterThan(String value) {
            addCriterion("DOC_PIC >", value, "docPic");
            return (Criteria) this;
        }

        public Criteria andDocPicGreaterThanOrEqualTo(String value) {
            addCriterion("DOC_PIC >=", value, "docPic");
            return (Criteria) this;
        }

        public Criteria andDocPicLessThan(String value) {
            addCriterion("DOC_PIC <", value, "docPic");
            return (Criteria) this;
        }

        public Criteria andDocPicLessThanOrEqualTo(String value) {
            addCriterion("DOC_PIC <=", value, "docPic");
            return (Criteria) this;
        }

        public Criteria andDocPicLike(String value) {
            addCriterion("DOC_PIC like", value, "docPic");
            return (Criteria) this;
        }

        public Criteria andDocPicNotLike(String value) {
            addCriterion("DOC_PIC not like", value, "docPic");
            return (Criteria) this;
        }

        public Criteria andDocPicIn(List<String> values) {
            addCriterion("DOC_PIC in", values, "docPic");
            return (Criteria) this;
        }

        public Criteria andDocPicNotIn(List<String> values) {
            addCriterion("DOC_PIC not in", values, "docPic");
            return (Criteria) this;
        }

        public Criteria andDocPicBetween(String value1, String value2) {
            addCriterion("DOC_PIC between", value1, value2, "docPic");
            return (Criteria) this;
        }

        public Criteria andDocPicNotBetween(String value1, String value2) {
            addCriterion("DOC_PIC not between", value1, value2, "docPic");
            return (Criteria) this;
        }

        public Criteria andPrintCfgIsNull() {
            addCriterion("PRINT_CFG is null");
            return (Criteria) this;
        }

        public Criteria andPrintCfgIsNotNull() {
            addCriterion("PRINT_CFG is not null");
            return (Criteria) this;
        }

        public Criteria andPrintCfgEqualTo(String value) {
            addCriterion("PRINT_CFG =", value, "printCfg");
            return (Criteria) this;
        }

        public Criteria andPrintCfgNotEqualTo(String value) {
            addCriterion("PRINT_CFG <>", value, "printCfg");
            return (Criteria) this;
        }

        public Criteria andPrintCfgGreaterThan(String value) {
            addCriterion("PRINT_CFG >", value, "printCfg");
            return (Criteria) this;
        }

        public Criteria andPrintCfgGreaterThanOrEqualTo(String value) {
            addCriterion("PRINT_CFG >=", value, "printCfg");
            return (Criteria) this;
        }

        public Criteria andPrintCfgLessThan(String value) {
            addCriterion("PRINT_CFG <", value, "printCfg");
            return (Criteria) this;
        }

        public Criteria andPrintCfgLessThanOrEqualTo(String value) {
            addCriterion("PRINT_CFG <=", value, "printCfg");
            return (Criteria) this;
        }

        public Criteria andPrintCfgLike(String value) {
            addCriterion("PRINT_CFG like", value, "printCfg");
            return (Criteria) this;
        }

        public Criteria andPrintCfgNotLike(String value) {
            addCriterion("PRINT_CFG not like", value, "printCfg");
            return (Criteria) this;
        }

        public Criteria andPrintCfgIn(List<String> values) {
            addCriterion("PRINT_CFG in", values, "printCfg");
            return (Criteria) this;
        }

        public Criteria andPrintCfgNotIn(List<String> values) {
            addCriterion("PRINT_CFG not in", values, "printCfg");
            return (Criteria) this;
        }

        public Criteria andPrintCfgBetween(String value1, String value2) {
            addCriterion("PRINT_CFG between", value1, value2, "printCfg");
            return (Criteria) this;
        }

        public Criteria andPrintCfgNotBetween(String value1, String value2) {
            addCriterion("PRINT_CFG not between", value1, value2, "printCfg");
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
