package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrdInvoiceCommCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OrdInvoiceCommCriteria() {
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

        public Criteria andInvoiceTitleIsNull() {
            addCriterion("INVOICE_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleIsNotNull() {
            addCriterion("INVOICE_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleEqualTo(String value) {
            addCriterion("INVOICE_TITLE =", value, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleNotEqualTo(String value) {
            addCriterion("INVOICE_TITLE <>", value, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleGreaterThan(String value) {
            addCriterion("INVOICE_TITLE >", value, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_TITLE >=", value, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleLessThan(String value) {
            addCriterion("INVOICE_TITLE <", value, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_TITLE <=", value, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleLike(String value) {
            addCriterion("INVOICE_TITLE like", value, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleNotLike(String value) {
            addCriterion("INVOICE_TITLE not like", value, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleIn(List<String> values) {
            addCriterion("INVOICE_TITLE in", values, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleNotIn(List<String> values) {
            addCriterion("INVOICE_TITLE not in", values, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleBetween(String value1, String value2) {
            addCriterion("INVOICE_TITLE between", value1, value2, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceTitleNotBetween(String value1, String value2) {
            addCriterion("INVOICE_TITLE not between", value1, value2, "invoiceTitle");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentIsNull() {
            addCriterion("INVOICE_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentIsNotNull() {
            addCriterion("INVOICE_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentEqualTo(String value) {
            addCriterion("INVOICE_CONTENT =", value, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentNotEqualTo(String value) {
            addCriterion("INVOICE_CONTENT <>", value, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentGreaterThan(String value) {
            addCriterion("INVOICE_CONTENT >", value, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_CONTENT >=", value, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentLessThan(String value) {
            addCriterion("INVOICE_CONTENT <", value, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_CONTENT <=", value, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentLike(String value) {
            addCriterion("INVOICE_CONTENT like", value, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentNotLike(String value) {
            addCriterion("INVOICE_CONTENT not like", value, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentIn(List<String> values) {
            addCriterion("INVOICE_CONTENT in", values, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentNotIn(List<String> values) {
            addCriterion("INVOICE_CONTENT not in", values, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentBetween(String value1, String value2) {
            addCriterion("INVOICE_CONTENT between", value1, value2, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceContentNotBetween(String value1, String value2) {
            addCriterion("INVOICE_CONTENT not between", value1, value2, "invoiceContent");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNull() {
            addCriterion("INVOICE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNotNull() {
            addCriterion("INVOICE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeEqualTo(String value) {
            addCriterion("INVOICE_TYPE =", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotEqualTo(String value) {
            addCriterion("INVOICE_TYPE <>", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThan(String value) {
            addCriterion("INVOICE_TYPE >", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_TYPE >=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThan(String value) {
            addCriterion("INVOICE_TYPE <", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_TYPE <=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLike(String value) {
            addCriterion("INVOICE_TYPE like", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotLike(String value) {
            addCriterion("INVOICE_TYPE not like", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIn(List<String> values) {
            addCriterion("INVOICE_TYPE in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotIn(List<String> values) {
            addCriterion("INVOICE_TYPE not in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeBetween(String value1, String value2) {
            addCriterion("INVOICE_TYPE between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotBetween(String value1, String value2) {
            addCriterion("INVOICE_TYPE not between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andTakerPhoneIsNull() {
            addCriterion("TAKER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andTakerPhoneIsNotNull() {
            addCriterion("TAKER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andTakerPhoneEqualTo(String value) {
            addCriterion("TAKER_PHONE =", value, "takerPhone");
            return (Criteria) this;
        }

        public Criteria andTakerPhoneNotEqualTo(String value) {
            addCriterion("TAKER_PHONE <>", value, "takerPhone");
            return (Criteria) this;
        }

        public Criteria andTakerPhoneGreaterThan(String value) {
            addCriterion("TAKER_PHONE >", value, "takerPhone");
            return (Criteria) this;
        }

        public Criteria andTakerPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("TAKER_PHONE >=", value, "takerPhone");
            return (Criteria) this;
        }

        public Criteria andTakerPhoneLessThan(String value) {
            addCriterion("TAKER_PHONE <", value, "takerPhone");
            return (Criteria) this;
        }

        public Criteria andTakerPhoneLessThanOrEqualTo(String value) {
            addCriterion("TAKER_PHONE <=", value, "takerPhone");
            return (Criteria) this;
        }

        public Criteria andTakerPhoneLike(String value) {
            addCriterion("TAKER_PHONE like", value, "takerPhone");
            return (Criteria) this;
        }

        public Criteria andTakerPhoneNotLike(String value) {
            addCriterion("TAKER_PHONE not like", value, "takerPhone");
            return (Criteria) this;
        }

        public Criteria andTakerPhoneIn(List<String> values) {
            addCriterion("TAKER_PHONE in", values, "takerPhone");
            return (Criteria) this;
        }

        public Criteria andTakerPhoneNotIn(List<String> values) {
            addCriterion("TAKER_PHONE not in", values, "takerPhone");
            return (Criteria) this;
        }

        public Criteria andTakerPhoneBetween(String value1, String value2) {
            addCriterion("TAKER_PHONE between", value1, value2, "takerPhone");
            return (Criteria) this;
        }

        public Criteria andTakerPhoneNotBetween(String value1, String value2) {
            addCriterion("TAKER_PHONE not between", value1, value2, "takerPhone");
            return (Criteria) this;
        }

        public Criteria andTakerEmailIsNull() {
            addCriterion("TAKER_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andTakerEmailIsNotNull() {
            addCriterion("TAKER_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andTakerEmailEqualTo(String value) {
            addCriterion("TAKER_EMAIL =", value, "takerEmail");
            return (Criteria) this;
        }

        public Criteria andTakerEmailNotEqualTo(String value) {
            addCriterion("TAKER_EMAIL <>", value, "takerEmail");
            return (Criteria) this;
        }

        public Criteria andTakerEmailGreaterThan(String value) {
            addCriterion("TAKER_EMAIL >", value, "takerEmail");
            return (Criteria) this;
        }

        public Criteria andTakerEmailGreaterThanOrEqualTo(String value) {
            addCriterion("TAKER_EMAIL >=", value, "takerEmail");
            return (Criteria) this;
        }

        public Criteria andTakerEmailLessThan(String value) {
            addCriterion("TAKER_EMAIL <", value, "takerEmail");
            return (Criteria) this;
        }

        public Criteria andTakerEmailLessThanOrEqualTo(String value) {
            addCriterion("TAKER_EMAIL <=", value, "takerEmail");
            return (Criteria) this;
        }

        public Criteria andTakerEmailLike(String value) {
            addCriterion("TAKER_EMAIL like", value, "takerEmail");
            return (Criteria) this;
        }

        public Criteria andTakerEmailNotLike(String value) {
            addCriterion("TAKER_EMAIL not like", value, "takerEmail");
            return (Criteria) this;
        }

        public Criteria andTakerEmailIn(List<String> values) {
            addCriterion("TAKER_EMAIL in", values, "takerEmail");
            return (Criteria) this;
        }

        public Criteria andTakerEmailNotIn(List<String> values) {
            addCriterion("TAKER_EMAIL not in", values, "takerEmail");
            return (Criteria) this;
        }

        public Criteria andTakerEmailBetween(String value1, String value2) {
            addCriterion("TAKER_EMAIL between", value1, value2, "takerEmail");
            return (Criteria) this;
        }

        public Criteria andTakerEmailNotBetween(String value1, String value2) {
            addCriterion("TAKER_EMAIL not between", value1, value2, "takerEmail");
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

        public Criteria andDetailFlagIsNull() {
            addCriterion("DETAIL_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andDetailFlagIsNotNull() {
            addCriterion("DETAIL_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDetailFlagEqualTo(String value) {
            addCriterion("DETAIL_FLAG =", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagNotEqualTo(String value) {
            addCriterion("DETAIL_FLAG <>", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagGreaterThan(String value) {
            addCriterion("DETAIL_FLAG >", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_FLAG >=", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagLessThan(String value) {
            addCriterion("DETAIL_FLAG <", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_FLAG <=", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagLike(String value) {
            addCriterion("DETAIL_FLAG like", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagNotLike(String value) {
            addCriterion("DETAIL_FLAG not like", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagIn(List<String> values) {
            addCriterion("DETAIL_FLAG in", values, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagNotIn(List<String> values) {
            addCriterion("DETAIL_FLAG not in", values, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagBetween(String value1, String value2) {
            addCriterion("DETAIL_FLAG between", value1, value2, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagNotBetween(String value1, String value2) {
            addCriterion("DETAIL_FLAG not between", value1, value2, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNoIsNull() {
            addCriterion("TAXPAYER_NO is null");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNoIsNotNull() {
            addCriterion("TAXPAYER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNoEqualTo(String value) {
            addCriterion("TAXPAYER_NO =", value, "taxpayerNo");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNoNotEqualTo(String value) {
            addCriterion("TAXPAYER_NO <>", value, "taxpayerNo");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNoGreaterThan(String value) {
            addCriterion("TAXPAYER_NO >", value, "taxpayerNo");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNoGreaterThanOrEqualTo(String value) {
            addCriterion("TAXPAYER_NO >=", value, "taxpayerNo");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNoLessThan(String value) {
            addCriterion("TAXPAYER_NO <", value, "taxpayerNo");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNoLessThanOrEqualTo(String value) {
            addCriterion("TAXPAYER_NO <=", value, "taxpayerNo");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNoLike(String value) {
            addCriterion("TAXPAYER_NO like", value, "taxpayerNo");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNoNotLike(String value) {
            addCriterion("TAXPAYER_NO not like", value, "taxpayerNo");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNoIn(List<String> values) {
            addCriterion("TAXPAYER_NO in", values, "taxpayerNo");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNoNotIn(List<String> values) {
            addCriterion("TAXPAYER_NO not in", values, "taxpayerNo");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNoBetween(String value1, String value2) {
            addCriterion("TAXPAYER_NO between", value1, value2, "taxpayerNo");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNoNotBetween(String value1, String value2) {
            addCriterion("TAXPAYER_NO not between", value1, value2, "taxpayerNo");
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