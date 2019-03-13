package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrdInvoiceTaxCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OrdInvoiceTaxCriteria() {
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

        public Criteria andContactInfoIsNull() {
            addCriterion("CONTACT_INFO is null");
            return (Criteria) this;
        }

        public Criteria andContactInfoIsNotNull() {
            addCriterion("CONTACT_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andContactInfoEqualTo(String value) {
            addCriterion("CONTACT_INFO =", value, "contactInfo");
            return (Criteria) this;
        }

        public Criteria andContactInfoNotEqualTo(String value) {
            addCriterion("CONTACT_INFO <>", value, "contactInfo");
            return (Criteria) this;
        }

        public Criteria andContactInfoGreaterThan(String value) {
            addCriterion("CONTACT_INFO >", value, "contactInfo");
            return (Criteria) this;
        }

        public Criteria andContactInfoGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_INFO >=", value, "contactInfo");
            return (Criteria) this;
        }

        public Criteria andContactInfoLessThan(String value) {
            addCriterion("CONTACT_INFO <", value, "contactInfo");
            return (Criteria) this;
        }

        public Criteria andContactInfoLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_INFO <=", value, "contactInfo");
            return (Criteria) this;
        }

        public Criteria andContactInfoLike(String value) {
            addCriterion("CONTACT_INFO like", value, "contactInfo");
            return (Criteria) this;
        }

        public Criteria andContactInfoNotLike(String value) {
            addCriterion("CONTACT_INFO not like", value, "contactInfo");
            return (Criteria) this;
        }

        public Criteria andContactInfoIn(List<String> values) {
            addCriterion("CONTACT_INFO in", values, "contactInfo");
            return (Criteria) this;
        }

        public Criteria andContactInfoNotIn(List<String> values) {
            addCriterion("CONTACT_INFO not in", values, "contactInfo");
            return (Criteria) this;
        }

        public Criteria andContactInfoBetween(String value1, String value2) {
            addCriterion("CONTACT_INFO between", value1, value2, "contactInfo");
            return (Criteria) this;
        }

        public Criteria andContactInfoNotBetween(String value1, String value2) {
            addCriterion("CONTACT_INFO not between", value1, value2, "contactInfo");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("PHONE is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("PHONE =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("PHONE <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("PHONE >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("PHONE <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("PHONE <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("PHONE like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("PHONE not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("PHONE in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("PHONE not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("PHONE between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("PHONE not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("BANK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("BANK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("BANK_NAME =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("BANK_NAME <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("BANK_NAME >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_NAME >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("BANK_NAME <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("BANK_NAME <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("BANK_NAME like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("BANK_NAME not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("BANK_NAME in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("BANK_NAME not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("BANK_NAME between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("BANK_NAME not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andAcctInfoIsNull() {
            addCriterion("ACCT_INFO is null");
            return (Criteria) this;
        }

        public Criteria andAcctInfoIsNotNull() {
            addCriterion("ACCT_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andAcctInfoEqualTo(String value) {
            addCriterion("ACCT_INFO =", value, "acctInfo");
            return (Criteria) this;
        }

        public Criteria andAcctInfoNotEqualTo(String value) {
            addCriterion("ACCT_INFO <>", value, "acctInfo");
            return (Criteria) this;
        }

        public Criteria andAcctInfoGreaterThan(String value) {
            addCriterion("ACCT_INFO >", value, "acctInfo");
            return (Criteria) this;
        }

        public Criteria andAcctInfoGreaterThanOrEqualTo(String value) {
            addCriterion("ACCT_INFO >=", value, "acctInfo");
            return (Criteria) this;
        }

        public Criteria andAcctInfoLessThan(String value) {
            addCriterion("ACCT_INFO <", value, "acctInfo");
            return (Criteria) this;
        }

        public Criteria andAcctInfoLessThanOrEqualTo(String value) {
            addCriterion("ACCT_INFO <=", value, "acctInfo");
            return (Criteria) this;
        }

        public Criteria andAcctInfoLike(String value) {
            addCriterion("ACCT_INFO like", value, "acctInfo");
            return (Criteria) this;
        }

        public Criteria andAcctInfoNotLike(String value) {
            addCriterion("ACCT_INFO not like", value, "acctInfo");
            return (Criteria) this;
        }

        public Criteria andAcctInfoIn(List<String> values) {
            addCriterion("ACCT_INFO in", values, "acctInfo");
            return (Criteria) this;
        }

        public Criteria andAcctInfoNotIn(List<String> values) {
            addCriterion("ACCT_INFO not in", values, "acctInfo");
            return (Criteria) this;
        }

        public Criteria andAcctInfoBetween(String value1, String value2) {
            addCriterion("ACCT_INFO between", value1, value2, "acctInfo");
            return (Criteria) this;
        }

        public Criteria andAcctInfoNotBetween(String value1, String value2) {
            addCriterion("ACCT_INFO not between", value1, value2, "acctInfo");
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

        public Criteria andTakerProvinceIsNull() {
            addCriterion("TAKER_PROVINCE is null");
            return (Criteria) this;
        }

        public Criteria andTakerProvinceIsNotNull() {
            addCriterion("TAKER_PROVINCE is not null");
            return (Criteria) this;
        }

        public Criteria andTakerProvinceEqualTo(String value) {
            addCriterion("TAKER_PROVINCE =", value, "takerProvince");
            return (Criteria) this;
        }

        public Criteria andTakerProvinceNotEqualTo(String value) {
            addCriterion("TAKER_PROVINCE <>", value, "takerProvince");
            return (Criteria) this;
        }

        public Criteria andTakerProvinceGreaterThan(String value) {
            addCriterion("TAKER_PROVINCE >", value, "takerProvince");
            return (Criteria) this;
        }

        public Criteria andTakerProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("TAKER_PROVINCE >=", value, "takerProvince");
            return (Criteria) this;
        }

        public Criteria andTakerProvinceLessThan(String value) {
            addCriterion("TAKER_PROVINCE <", value, "takerProvince");
            return (Criteria) this;
        }

        public Criteria andTakerProvinceLessThanOrEqualTo(String value) {
            addCriterion("TAKER_PROVINCE <=", value, "takerProvince");
            return (Criteria) this;
        }

        public Criteria andTakerProvinceLike(String value) {
            addCriterion("TAKER_PROVINCE like", value, "takerProvince");
            return (Criteria) this;
        }

        public Criteria andTakerProvinceNotLike(String value) {
            addCriterion("TAKER_PROVINCE not like", value, "takerProvince");
            return (Criteria) this;
        }

        public Criteria andTakerProvinceIn(List<String> values) {
            addCriterion("TAKER_PROVINCE in", values, "takerProvince");
            return (Criteria) this;
        }

        public Criteria andTakerProvinceNotIn(List<String> values) {
            addCriterion("TAKER_PROVINCE not in", values, "takerProvince");
            return (Criteria) this;
        }

        public Criteria andTakerProvinceBetween(String value1, String value2) {
            addCriterion("TAKER_PROVINCE between", value1, value2, "takerProvince");
            return (Criteria) this;
        }

        public Criteria andTakerProvinceNotBetween(String value1, String value2) {
            addCriterion("TAKER_PROVINCE not between", value1, value2, "takerProvince");
            return (Criteria) this;
        }

        public Criteria andTakerCityIsNull() {
            addCriterion("TAKER_CITY is null");
            return (Criteria) this;
        }

        public Criteria andTakerCityIsNotNull() {
            addCriterion("TAKER_CITY is not null");
            return (Criteria) this;
        }

        public Criteria andTakerCityEqualTo(String value) {
            addCriterion("TAKER_CITY =", value, "takerCity");
            return (Criteria) this;
        }

        public Criteria andTakerCityNotEqualTo(String value) {
            addCriterion("TAKER_CITY <>", value, "takerCity");
            return (Criteria) this;
        }

        public Criteria andTakerCityGreaterThan(String value) {
            addCriterion("TAKER_CITY >", value, "takerCity");
            return (Criteria) this;
        }

        public Criteria andTakerCityGreaterThanOrEqualTo(String value) {
            addCriterion("TAKER_CITY >=", value, "takerCity");
            return (Criteria) this;
        }

        public Criteria andTakerCityLessThan(String value) {
            addCriterion("TAKER_CITY <", value, "takerCity");
            return (Criteria) this;
        }

        public Criteria andTakerCityLessThanOrEqualTo(String value) {
            addCriterion("TAKER_CITY <=", value, "takerCity");
            return (Criteria) this;
        }

        public Criteria andTakerCityLike(String value) {
            addCriterion("TAKER_CITY like", value, "takerCity");
            return (Criteria) this;
        }

        public Criteria andTakerCityNotLike(String value) {
            addCriterion("TAKER_CITY not like", value, "takerCity");
            return (Criteria) this;
        }

        public Criteria andTakerCityIn(List<String> values) {
            addCriterion("TAKER_CITY in", values, "takerCity");
            return (Criteria) this;
        }

        public Criteria andTakerCityNotIn(List<String> values) {
            addCriterion("TAKER_CITY not in", values, "takerCity");
            return (Criteria) this;
        }

        public Criteria andTakerCityBetween(String value1, String value2) {
            addCriterion("TAKER_CITY between", value1, value2, "takerCity");
            return (Criteria) this;
        }

        public Criteria andTakerCityNotBetween(String value1, String value2) {
            addCriterion("TAKER_CITY not between", value1, value2, "takerCity");
            return (Criteria) this;
        }

        public Criteria andTakerCountyIsNull() {
            addCriterion("TAKER_COUNTY is null");
            return (Criteria) this;
        }

        public Criteria andTakerCountyIsNotNull() {
            addCriterion("TAKER_COUNTY is not null");
            return (Criteria) this;
        }

        public Criteria andTakerCountyEqualTo(String value) {
            addCriterion("TAKER_COUNTY =", value, "takerCounty");
            return (Criteria) this;
        }

        public Criteria andTakerCountyNotEqualTo(String value) {
            addCriterion("TAKER_COUNTY <>", value, "takerCounty");
            return (Criteria) this;
        }

        public Criteria andTakerCountyGreaterThan(String value) {
            addCriterion("TAKER_COUNTY >", value, "takerCounty");
            return (Criteria) this;
        }

        public Criteria andTakerCountyGreaterThanOrEqualTo(String value) {
            addCriterion("TAKER_COUNTY >=", value, "takerCounty");
            return (Criteria) this;
        }

        public Criteria andTakerCountyLessThan(String value) {
            addCriterion("TAKER_COUNTY <", value, "takerCounty");
            return (Criteria) this;
        }

        public Criteria andTakerCountyLessThanOrEqualTo(String value) {
            addCriterion("TAKER_COUNTY <=", value, "takerCounty");
            return (Criteria) this;
        }

        public Criteria andTakerCountyLike(String value) {
            addCriterion("TAKER_COUNTY like", value, "takerCounty");
            return (Criteria) this;
        }

        public Criteria andTakerCountyNotLike(String value) {
            addCriterion("TAKER_COUNTY not like", value, "takerCounty");
            return (Criteria) this;
        }

        public Criteria andTakerCountyIn(List<String> values) {
            addCriterion("TAKER_COUNTY in", values, "takerCounty");
            return (Criteria) this;
        }

        public Criteria andTakerCountyNotIn(List<String> values) {
            addCriterion("TAKER_COUNTY not in", values, "takerCounty");
            return (Criteria) this;
        }

        public Criteria andTakerCountyBetween(String value1, String value2) {
            addCriterion("TAKER_COUNTY between", value1, value2, "takerCounty");
            return (Criteria) this;
        }

        public Criteria andTakerCountyNotBetween(String value1, String value2) {
            addCriterion("TAKER_COUNTY not between", value1, value2, "takerCounty");
            return (Criteria) this;
        }

        public Criteria andTakerAddressIsNull() {
            addCriterion("TAKER_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andTakerAddressIsNotNull() {
            addCriterion("TAKER_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andTakerAddressEqualTo(String value) {
            addCriterion("TAKER_ADDRESS =", value, "takerAddress");
            return (Criteria) this;
        }

        public Criteria andTakerAddressNotEqualTo(String value) {
            addCriterion("TAKER_ADDRESS <>", value, "takerAddress");
            return (Criteria) this;
        }

        public Criteria andTakerAddressGreaterThan(String value) {
            addCriterion("TAKER_ADDRESS >", value, "takerAddress");
            return (Criteria) this;
        }

        public Criteria andTakerAddressGreaterThanOrEqualTo(String value) {
            addCriterion("TAKER_ADDRESS >=", value, "takerAddress");
            return (Criteria) this;
        }

        public Criteria andTakerAddressLessThan(String value) {
            addCriterion("TAKER_ADDRESS <", value, "takerAddress");
            return (Criteria) this;
        }

        public Criteria andTakerAddressLessThanOrEqualTo(String value) {
            addCriterion("TAKER_ADDRESS <=", value, "takerAddress");
            return (Criteria) this;
        }

        public Criteria andTakerAddressLike(String value) {
            addCriterion("TAKER_ADDRESS like", value, "takerAddress");
            return (Criteria) this;
        }

        public Criteria andTakerAddressNotLike(String value) {
            addCriterion("TAKER_ADDRESS not like", value, "takerAddress");
            return (Criteria) this;
        }

        public Criteria andTakerAddressIn(List<String> values) {
            addCriterion("TAKER_ADDRESS in", values, "takerAddress");
            return (Criteria) this;
        }

        public Criteria andTakerAddressNotIn(List<String> values) {
            addCriterion("TAKER_ADDRESS not in", values, "takerAddress");
            return (Criteria) this;
        }

        public Criteria andTakerAddressBetween(String value1, String value2) {
            addCriterion("TAKER_ADDRESS between", value1, value2, "takerAddress");
            return (Criteria) this;
        }

        public Criteria andTakerAddressNotBetween(String value1, String value2) {
            addCriterion("TAKER_ADDRESS not between", value1, value2, "takerAddress");
            return (Criteria) this;
        }

        public Criteria andVfsId1IsNull() {
            addCriterion("VFS_ID1 is null");
            return (Criteria) this;
        }

        public Criteria andVfsId1IsNotNull() {
            addCriterion("VFS_ID1 is not null");
            return (Criteria) this;
        }

        public Criteria andVfsId1EqualTo(String value) {
            addCriterion("VFS_ID1 =", value, "vfsId1");
            return (Criteria) this;
        }

        public Criteria andVfsId1NotEqualTo(String value) {
            addCriterion("VFS_ID1 <>", value, "vfsId1");
            return (Criteria) this;
        }

        public Criteria andVfsId1GreaterThan(String value) {
            addCriterion("VFS_ID1 >", value, "vfsId1");
            return (Criteria) this;
        }

        public Criteria andVfsId1GreaterThanOrEqualTo(String value) {
            addCriterion("VFS_ID1 >=", value, "vfsId1");
            return (Criteria) this;
        }

        public Criteria andVfsId1LessThan(String value) {
            addCriterion("VFS_ID1 <", value, "vfsId1");
            return (Criteria) this;
        }

        public Criteria andVfsId1LessThanOrEqualTo(String value) {
            addCriterion("VFS_ID1 <=", value, "vfsId1");
            return (Criteria) this;
        }

        public Criteria andVfsId1Like(String value) {
            addCriterion("VFS_ID1 like", value, "vfsId1");
            return (Criteria) this;
        }

        public Criteria andVfsId1NotLike(String value) {
            addCriterion("VFS_ID1 not like", value, "vfsId1");
            return (Criteria) this;
        }

        public Criteria andVfsId1In(List<String> values) {
            addCriterion("VFS_ID1 in", values, "vfsId1");
            return (Criteria) this;
        }

        public Criteria andVfsId1NotIn(List<String> values) {
            addCriterion("VFS_ID1 not in", values, "vfsId1");
            return (Criteria) this;
        }

        public Criteria andVfsId1Between(String value1, String value2) {
            addCriterion("VFS_ID1 between", value1, value2, "vfsId1");
            return (Criteria) this;
        }

        public Criteria andVfsId1NotBetween(String value1, String value2) {
            addCriterion("VFS_ID1 not between", value1, value2, "vfsId1");
            return (Criteria) this;
        }

        public Criteria andVfsId2IsNull() {
            addCriterion("VFS_ID2 is null");
            return (Criteria) this;
        }

        public Criteria andVfsId2IsNotNull() {
            addCriterion("VFS_ID2 is not null");
            return (Criteria) this;
        }

        public Criteria andVfsId2EqualTo(String value) {
            addCriterion("VFS_ID2 =", value, "vfsId2");
            return (Criteria) this;
        }

        public Criteria andVfsId2NotEqualTo(String value) {
            addCriterion("VFS_ID2 <>", value, "vfsId2");
            return (Criteria) this;
        }

        public Criteria andVfsId2GreaterThan(String value) {
            addCriterion("VFS_ID2 >", value, "vfsId2");
            return (Criteria) this;
        }

        public Criteria andVfsId2GreaterThanOrEqualTo(String value) {
            addCriterion("VFS_ID2 >=", value, "vfsId2");
            return (Criteria) this;
        }

        public Criteria andVfsId2LessThan(String value) {
            addCriterion("VFS_ID2 <", value, "vfsId2");
            return (Criteria) this;
        }

        public Criteria andVfsId2LessThanOrEqualTo(String value) {
            addCriterion("VFS_ID2 <=", value, "vfsId2");
            return (Criteria) this;
        }

        public Criteria andVfsId2Like(String value) {
            addCriterion("VFS_ID2 like", value, "vfsId2");
            return (Criteria) this;
        }

        public Criteria andVfsId2NotLike(String value) {
            addCriterion("VFS_ID2 not like", value, "vfsId2");
            return (Criteria) this;
        }

        public Criteria andVfsId2In(List<String> values) {
            addCriterion("VFS_ID2 in", values, "vfsId2");
            return (Criteria) this;
        }

        public Criteria andVfsId2NotIn(List<String> values) {
            addCriterion("VFS_ID2 not in", values, "vfsId2");
            return (Criteria) this;
        }

        public Criteria andVfsId2Between(String value1, String value2) {
            addCriterion("VFS_ID2 between", value1, value2, "vfsId2");
            return (Criteria) this;
        }

        public Criteria andVfsId2NotBetween(String value1, String value2) {
            addCriterion("VFS_ID2 not between", value1, value2, "vfsId2");
            return (Criteria) this;
        }

        public Criteria andVfsId3IsNull() {
            addCriterion("VFS_ID3 is null");
            return (Criteria) this;
        }

        public Criteria andVfsId3IsNotNull() {
            addCriterion("VFS_ID3 is not null");
            return (Criteria) this;
        }

        public Criteria andVfsId3EqualTo(String value) {
            addCriterion("VFS_ID3 =", value, "vfsId3");
            return (Criteria) this;
        }

        public Criteria andVfsId3NotEqualTo(String value) {
            addCriterion("VFS_ID3 <>", value, "vfsId3");
            return (Criteria) this;
        }

        public Criteria andVfsId3GreaterThan(String value) {
            addCriterion("VFS_ID3 >", value, "vfsId3");
            return (Criteria) this;
        }

        public Criteria andVfsId3GreaterThanOrEqualTo(String value) {
            addCriterion("VFS_ID3 >=", value, "vfsId3");
            return (Criteria) this;
        }

        public Criteria andVfsId3LessThan(String value) {
            addCriterion("VFS_ID3 <", value, "vfsId3");
            return (Criteria) this;
        }

        public Criteria andVfsId3LessThanOrEqualTo(String value) {
            addCriterion("VFS_ID3 <=", value, "vfsId3");
            return (Criteria) this;
        }

        public Criteria andVfsId3Like(String value) {
            addCriterion("VFS_ID3 like", value, "vfsId3");
            return (Criteria) this;
        }

        public Criteria andVfsId3NotLike(String value) {
            addCriterion("VFS_ID3 not like", value, "vfsId3");
            return (Criteria) this;
        }

        public Criteria andVfsId3In(List<String> values) {
            addCriterion("VFS_ID3 in", values, "vfsId3");
            return (Criteria) this;
        }

        public Criteria andVfsId3NotIn(List<String> values) {
            addCriterion("VFS_ID3 not in", values, "vfsId3");
            return (Criteria) this;
        }

        public Criteria andVfsId3Between(String value1, String value2) {
            addCriterion("VFS_ID3 between", value1, value2, "vfsId3");
            return (Criteria) this;
        }

        public Criteria andVfsId3NotBetween(String value1, String value2) {
            addCriterion("VFS_ID3 not between", value1, value2, "vfsId3");
            return (Criteria) this;
        }

        public Criteria andVfsId4IsNull() {
            addCriterion("VFS_ID4 is null");
            return (Criteria) this;
        }

        public Criteria andVfsId4IsNotNull() {
            addCriterion("VFS_ID4 is not null");
            return (Criteria) this;
        }

        public Criteria andVfsId4EqualTo(String value) {
            addCriterion("VFS_ID4 =", value, "vfsId4");
            return (Criteria) this;
        }

        public Criteria andVfsId4NotEqualTo(String value) {
            addCriterion("VFS_ID4 <>", value, "vfsId4");
            return (Criteria) this;
        }

        public Criteria andVfsId4GreaterThan(String value) {
            addCriterion("VFS_ID4 >", value, "vfsId4");
            return (Criteria) this;
        }

        public Criteria andVfsId4GreaterThanOrEqualTo(String value) {
            addCriterion("VFS_ID4 >=", value, "vfsId4");
            return (Criteria) this;
        }

        public Criteria andVfsId4LessThan(String value) {
            addCriterion("VFS_ID4 <", value, "vfsId4");
            return (Criteria) this;
        }

        public Criteria andVfsId4LessThanOrEqualTo(String value) {
            addCriterion("VFS_ID4 <=", value, "vfsId4");
            return (Criteria) this;
        }

        public Criteria andVfsId4Like(String value) {
            addCriterion("VFS_ID4 like", value, "vfsId4");
            return (Criteria) this;
        }

        public Criteria andVfsId4NotLike(String value) {
            addCriterion("VFS_ID4 not like", value, "vfsId4");
            return (Criteria) this;
        }

        public Criteria andVfsId4In(List<String> values) {
            addCriterion("VFS_ID4 in", values, "vfsId4");
            return (Criteria) this;
        }

        public Criteria andVfsId4NotIn(List<String> values) {
            addCriterion("VFS_ID4 not in", values, "vfsId4");
            return (Criteria) this;
        }

        public Criteria andVfsId4Between(String value1, String value2) {
            addCriterion("VFS_ID4 between", value1, value2, "vfsId4");
            return (Criteria) this;
        }

        public Criteria andVfsId4NotBetween(String value1, String value2) {
            addCriterion("VFS_ID4 not between", value1, value2, "vfsId4");
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