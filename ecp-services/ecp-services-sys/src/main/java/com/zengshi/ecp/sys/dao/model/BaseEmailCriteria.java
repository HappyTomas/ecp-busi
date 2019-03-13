package com.zengshi.ecp.sys.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseEmailCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public BaseEmailCriteria() {
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

        public Criteria andSmtpServerIsNull() {
            addCriterion("SMTP_SERVER is null");
            return (Criteria) this;
        }

        public Criteria andSmtpServerIsNotNull() {
            addCriterion("SMTP_SERVER is not null");
            return (Criteria) this;
        }

        public Criteria andSmtpServerEqualTo(String value) {
            addCriterion("SMTP_SERVER =", value, "smtpServer");
            return (Criteria) this;
        }

        public Criteria andSmtpServerNotEqualTo(String value) {
            addCriterion("SMTP_SERVER <>", value, "smtpServer");
            return (Criteria) this;
        }

        public Criteria andSmtpServerGreaterThan(String value) {
            addCriterion("SMTP_SERVER >", value, "smtpServer");
            return (Criteria) this;
        }

        public Criteria andSmtpServerGreaterThanOrEqualTo(String value) {
            addCriterion("SMTP_SERVER >=", value, "smtpServer");
            return (Criteria) this;
        }

        public Criteria andSmtpServerLessThan(String value) {
            addCriterion("SMTP_SERVER <", value, "smtpServer");
            return (Criteria) this;
        }

        public Criteria andSmtpServerLessThanOrEqualTo(String value) {
            addCriterion("SMTP_SERVER <=", value, "smtpServer");
            return (Criteria) this;
        }

        public Criteria andSmtpServerLike(String value) {
            addCriterion("SMTP_SERVER like", value, "smtpServer");
            return (Criteria) this;
        }

        public Criteria andSmtpServerNotLike(String value) {
            addCriterion("SMTP_SERVER not like", value, "smtpServer");
            return (Criteria) this;
        }

        public Criteria andSmtpServerIn(List<String> values) {
            addCriterion("SMTP_SERVER in", values, "smtpServer");
            return (Criteria) this;
        }

        public Criteria andSmtpServerNotIn(List<String> values) {
            addCriterion("SMTP_SERVER not in", values, "smtpServer");
            return (Criteria) this;
        }

        public Criteria andSmtpServerBetween(String value1, String value2) {
            addCriterion("SMTP_SERVER between", value1, value2, "smtpServer");
            return (Criteria) this;
        }

        public Criteria andSmtpServerNotBetween(String value1, String value2) {
            addCriterion("SMTP_SERVER not between", value1, value2, "smtpServer");
            return (Criteria) this;
        }

        public Criteria andSmtpPortIsNull() {
            addCriterion("SMTP_PORT is null");
            return (Criteria) this;
        }

        public Criteria andSmtpPortIsNotNull() {
            addCriterion("SMTP_PORT is not null");
            return (Criteria) this;
        }

        public Criteria andSmtpPortEqualTo(Integer value) {
            addCriterion("SMTP_PORT =", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortNotEqualTo(Integer value) {
            addCriterion("SMTP_PORT <>", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortGreaterThan(Integer value) {
            addCriterion("SMTP_PORT >", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("SMTP_PORT >=", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortLessThan(Integer value) {
            addCriterion("SMTP_PORT <", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortLessThanOrEqualTo(Integer value) {
            addCriterion("SMTP_PORT <=", value, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortIn(List<Integer> values) {
            addCriterion("SMTP_PORT in", values, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortNotIn(List<Integer> values) {
            addCriterion("SMTP_PORT not in", values, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortBetween(Integer value1, Integer value2) {
            addCriterion("SMTP_PORT between", value1, value2, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSmtpPortNotBetween(Integer value1, Integer value2) {
            addCriterion("SMTP_PORT not between", value1, value2, "smtpPort");
            return (Criteria) this;
        }

        public Criteria andSendEmailIsNull() {
            addCriterion("SEND_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andSendEmailIsNotNull() {
            addCriterion("SEND_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andSendEmailEqualTo(String value) {
            addCriterion("SEND_EMAIL =", value, "sendEmail");
            return (Criteria) this;
        }

        public Criteria andSendEmailNotEqualTo(String value) {
            addCriterion("SEND_EMAIL <>", value, "sendEmail");
            return (Criteria) this;
        }

        public Criteria andSendEmailGreaterThan(String value) {
            addCriterion("SEND_EMAIL >", value, "sendEmail");
            return (Criteria) this;
        }

        public Criteria andSendEmailGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_EMAIL >=", value, "sendEmail");
            return (Criteria) this;
        }

        public Criteria andSendEmailLessThan(String value) {
            addCriterion("SEND_EMAIL <", value, "sendEmail");
            return (Criteria) this;
        }

        public Criteria andSendEmailLessThanOrEqualTo(String value) {
            addCriterion("SEND_EMAIL <=", value, "sendEmail");
            return (Criteria) this;
        }

        public Criteria andSendEmailLike(String value) {
            addCriterion("SEND_EMAIL like", value, "sendEmail");
            return (Criteria) this;
        }

        public Criteria andSendEmailNotLike(String value) {
            addCriterion("SEND_EMAIL not like", value, "sendEmail");
            return (Criteria) this;
        }

        public Criteria andSendEmailIn(List<String> values) {
            addCriterion("SEND_EMAIL in", values, "sendEmail");
            return (Criteria) this;
        }

        public Criteria andSendEmailNotIn(List<String> values) {
            addCriterion("SEND_EMAIL not in", values, "sendEmail");
            return (Criteria) this;
        }

        public Criteria andSendEmailBetween(String value1, String value2) {
            addCriterion("SEND_EMAIL between", value1, value2, "sendEmail");
            return (Criteria) this;
        }

        public Criteria andSendEmailNotBetween(String value1, String value2) {
            addCriterion("SEND_EMAIL not between", value1, value2, "sendEmail");
            return (Criteria) this;
        }

        public Criteria andSmtpUserIsNull() {
            addCriterion("SMTP_USER is null");
            return (Criteria) this;
        }

        public Criteria andSmtpUserIsNotNull() {
            addCriterion("SMTP_USER is not null");
            return (Criteria) this;
        }

        public Criteria andSmtpUserEqualTo(String value) {
            addCriterion("SMTP_USER =", value, "smtpUser");
            return (Criteria) this;
        }

        public Criteria andSmtpUserNotEqualTo(String value) {
            addCriterion("SMTP_USER <>", value, "smtpUser");
            return (Criteria) this;
        }

        public Criteria andSmtpUserGreaterThan(String value) {
            addCriterion("SMTP_USER >", value, "smtpUser");
            return (Criteria) this;
        }

        public Criteria andSmtpUserGreaterThanOrEqualTo(String value) {
            addCriterion("SMTP_USER >=", value, "smtpUser");
            return (Criteria) this;
        }

        public Criteria andSmtpUserLessThan(String value) {
            addCriterion("SMTP_USER <", value, "smtpUser");
            return (Criteria) this;
        }

        public Criteria andSmtpUserLessThanOrEqualTo(String value) {
            addCriterion("SMTP_USER <=", value, "smtpUser");
            return (Criteria) this;
        }

        public Criteria andSmtpUserLike(String value) {
            addCriterion("SMTP_USER like", value, "smtpUser");
            return (Criteria) this;
        }

        public Criteria andSmtpUserNotLike(String value) {
            addCriterion("SMTP_USER not like", value, "smtpUser");
            return (Criteria) this;
        }

        public Criteria andSmtpUserIn(List<String> values) {
            addCriterion("SMTP_USER in", values, "smtpUser");
            return (Criteria) this;
        }

        public Criteria andSmtpUserNotIn(List<String> values) {
            addCriterion("SMTP_USER not in", values, "smtpUser");
            return (Criteria) this;
        }

        public Criteria andSmtpUserBetween(String value1, String value2) {
            addCriterion("SMTP_USER between", value1, value2, "smtpUser");
            return (Criteria) this;
        }

        public Criteria andSmtpUserNotBetween(String value1, String value2) {
            addCriterion("SMTP_USER not between", value1, value2, "smtpUser");
            return (Criteria) this;
        }

        public Criteria andSmtpPasswordIsNull() {
            addCriterion("SMTP_PASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andSmtpPasswordIsNotNull() {
            addCriterion("SMTP_PASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andSmtpPasswordEqualTo(String value) {
            addCriterion("SMTP_PASSWORD =", value, "smtpPassword");
            return (Criteria) this;
        }

        public Criteria andSmtpPasswordNotEqualTo(String value) {
            addCriterion("SMTP_PASSWORD <>", value, "smtpPassword");
            return (Criteria) this;
        }

        public Criteria andSmtpPasswordGreaterThan(String value) {
            addCriterion("SMTP_PASSWORD >", value, "smtpPassword");
            return (Criteria) this;
        }

        public Criteria andSmtpPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("SMTP_PASSWORD >=", value, "smtpPassword");
            return (Criteria) this;
        }

        public Criteria andSmtpPasswordLessThan(String value) {
            addCriterion("SMTP_PASSWORD <", value, "smtpPassword");
            return (Criteria) this;
        }

        public Criteria andSmtpPasswordLessThanOrEqualTo(String value) {
            addCriterion("SMTP_PASSWORD <=", value, "smtpPassword");
            return (Criteria) this;
        }

        public Criteria andSmtpPasswordLike(String value) {
            addCriterion("SMTP_PASSWORD like", value, "smtpPassword");
            return (Criteria) this;
        }

        public Criteria andSmtpPasswordNotLike(String value) {
            addCriterion("SMTP_PASSWORD not like", value, "smtpPassword");
            return (Criteria) this;
        }

        public Criteria andSmtpPasswordIn(List<String> values) {
            addCriterion("SMTP_PASSWORD in", values, "smtpPassword");
            return (Criteria) this;
        }

        public Criteria andSmtpPasswordNotIn(List<String> values) {
            addCriterion("SMTP_PASSWORD not in", values, "smtpPassword");
            return (Criteria) this;
        }

        public Criteria andSmtpPasswordBetween(String value1, String value2) {
            addCriterion("SMTP_PASSWORD between", value1, value2, "smtpPassword");
            return (Criteria) this;
        }

        public Criteria andSmtpPasswordNotBetween(String value1, String value2) {
            addCriterion("SMTP_PASSWORD not between", value1, value2, "smtpPassword");
            return (Criteria) this;
        }

        public Criteria andRecEmailIsNull() {
            addCriterion("REC_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andRecEmailIsNotNull() {
            addCriterion("REC_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andRecEmailEqualTo(String value) {
            addCriterion("REC_EMAIL =", value, "recEmail");
            return (Criteria) this;
        }

        public Criteria andRecEmailNotEqualTo(String value) {
            addCriterion("REC_EMAIL <>", value, "recEmail");
            return (Criteria) this;
        }

        public Criteria andRecEmailGreaterThan(String value) {
            addCriterion("REC_EMAIL >", value, "recEmail");
            return (Criteria) this;
        }

        public Criteria andRecEmailGreaterThanOrEqualTo(String value) {
            addCriterion("REC_EMAIL >=", value, "recEmail");
            return (Criteria) this;
        }

        public Criteria andRecEmailLessThan(String value) {
            addCriterion("REC_EMAIL <", value, "recEmail");
            return (Criteria) this;
        }

        public Criteria andRecEmailLessThanOrEqualTo(String value) {
            addCriterion("REC_EMAIL <=", value, "recEmail");
            return (Criteria) this;
        }

        public Criteria andRecEmailLike(String value) {
            addCriterion("REC_EMAIL like", value, "recEmail");
            return (Criteria) this;
        }

        public Criteria andRecEmailNotLike(String value) {
            addCriterion("REC_EMAIL not like", value, "recEmail");
            return (Criteria) this;
        }

        public Criteria andRecEmailIn(List<String> values) {
            addCriterion("REC_EMAIL in", values, "recEmail");
            return (Criteria) this;
        }

        public Criteria andRecEmailNotIn(List<String> values) {
            addCriterion("REC_EMAIL not in", values, "recEmail");
            return (Criteria) this;
        }

        public Criteria andRecEmailBetween(String value1, String value2) {
            addCriterion("REC_EMAIL between", value1, value2, "recEmail");
            return (Criteria) this;
        }

        public Criteria andRecEmailNotBetween(String value1, String value2) {
            addCriterion("REC_EMAIL not between", value1, value2, "recEmail");
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
