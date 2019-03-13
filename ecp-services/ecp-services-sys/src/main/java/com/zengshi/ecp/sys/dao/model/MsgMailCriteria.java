package com.zengshi.ecp.sys.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MsgMailCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public MsgMailCriteria() {
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

        public Criteria andMsgCodeIsNull() {
            addCriterion("MSG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMsgCodeIsNotNull() {
            addCriterion("MSG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMsgCodeEqualTo(String value) {
            addCriterion("MSG_CODE =", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeNotEqualTo(String value) {
            addCriterion("MSG_CODE <>", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeGreaterThan(String value) {
            addCriterion("MSG_CODE >", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_CODE >=", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeLessThan(String value) {
            addCriterion("MSG_CODE <", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeLessThanOrEqualTo(String value) {
            addCriterion("MSG_CODE <=", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeLike(String value) {
            addCriterion("MSG_CODE like", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeNotLike(String value) {
            addCriterion("MSG_CODE not like", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeIn(List<String> values) {
            addCriterion("MSG_CODE in", values, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeNotIn(List<String> values) {
            addCriterion("MSG_CODE not in", values, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeBetween(String value1, String value2) {
            addCriterion("MSG_CODE between", value1, value2, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeNotBetween(String value1, String value2) {
            addCriterion("MSG_CODE not between", value1, value2, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMailTitleTemplateIsNull() {
            addCriterion("MAIL_TITLE_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andMailTitleTemplateIsNotNull() {
            addCriterion("MAIL_TITLE_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andMailTitleTemplateEqualTo(String value) {
            addCriterion("MAIL_TITLE_TEMPLATE =", value, "mailTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMailTitleTemplateNotEqualTo(String value) {
            addCriterion("MAIL_TITLE_TEMPLATE <>", value, "mailTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMailTitleTemplateGreaterThan(String value) {
            addCriterion("MAIL_TITLE_TEMPLATE >", value, "mailTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMailTitleTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("MAIL_TITLE_TEMPLATE >=", value, "mailTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMailTitleTemplateLessThan(String value) {
            addCriterion("MAIL_TITLE_TEMPLATE <", value, "mailTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMailTitleTemplateLessThanOrEqualTo(String value) {
            addCriterion("MAIL_TITLE_TEMPLATE <=", value, "mailTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMailTitleTemplateLike(String value) {
            addCriterion("MAIL_TITLE_TEMPLATE like", value, "mailTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMailTitleTemplateNotLike(String value) {
            addCriterion("MAIL_TITLE_TEMPLATE not like", value, "mailTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMailTitleTemplateIn(List<String> values) {
            addCriterion("MAIL_TITLE_TEMPLATE in", values, "mailTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMailTitleTemplateNotIn(List<String> values) {
            addCriterion("MAIL_TITLE_TEMPLATE not in", values, "mailTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMailTitleTemplateBetween(String value1, String value2) {
            addCriterion("MAIL_TITLE_TEMPLATE between", value1, value2, "mailTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMailTitleTemplateNotBetween(String value1, String value2) {
            addCriterion("MAIL_TITLE_TEMPLATE not between", value1, value2, "mailTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMailBodyTemplateIsNull() {
            addCriterion("MAIL_BODY_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andMailBodyTemplateIsNotNull() {
            addCriterion("MAIL_BODY_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andMailBodyTemplateEqualTo(String value) {
            addCriterion("MAIL_BODY_TEMPLATE =", value, "mailBodyTemplate");
            return (Criteria) this;
        }

        public Criteria andMailBodyTemplateNotEqualTo(String value) {
            addCriterion("MAIL_BODY_TEMPLATE <>", value, "mailBodyTemplate");
            return (Criteria) this;
        }

        public Criteria andMailBodyTemplateGreaterThan(String value) {
            addCriterion("MAIL_BODY_TEMPLATE >", value, "mailBodyTemplate");
            return (Criteria) this;
        }

        public Criteria andMailBodyTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("MAIL_BODY_TEMPLATE >=", value, "mailBodyTemplate");
            return (Criteria) this;
        }

        public Criteria andMailBodyTemplateLessThan(String value) {
            addCriterion("MAIL_BODY_TEMPLATE <", value, "mailBodyTemplate");
            return (Criteria) this;
        }

        public Criteria andMailBodyTemplateLessThanOrEqualTo(String value) {
            addCriterion("MAIL_BODY_TEMPLATE <=", value, "mailBodyTemplate");
            return (Criteria) this;
        }

        public Criteria andMailBodyTemplateLike(String value) {
            addCriterion("MAIL_BODY_TEMPLATE like", value, "mailBodyTemplate");
            return (Criteria) this;
        }

        public Criteria andMailBodyTemplateNotLike(String value) {
            addCriterion("MAIL_BODY_TEMPLATE not like", value, "mailBodyTemplate");
            return (Criteria) this;
        }

        public Criteria andMailBodyTemplateIn(List<String> values) {
            addCriterion("MAIL_BODY_TEMPLATE in", values, "mailBodyTemplate");
            return (Criteria) this;
        }

        public Criteria andMailBodyTemplateNotIn(List<String> values) {
            addCriterion("MAIL_BODY_TEMPLATE not in", values, "mailBodyTemplate");
            return (Criteria) this;
        }

        public Criteria andMailBodyTemplateBetween(String value1, String value2) {
            addCriterion("MAIL_BODY_TEMPLATE between", value1, value2, "mailBodyTemplate");
            return (Criteria) this;
        }

        public Criteria andMailBodyTemplateNotBetween(String value1, String value2) {
            addCriterion("MAIL_BODY_TEMPLATE not between", value1, value2, "mailBodyTemplate");
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
