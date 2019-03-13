package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AuditAlipayDetailLogCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public AuditAlipayDetailLogCriteria() {
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

        public Criteria andOriginalIdIsNull() {
            addCriterion("ORIGINAL_ID is null");
            return (Criteria) this;
        }

        public Criteria andOriginalIdIsNotNull() {
            addCriterion("ORIGINAL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalIdEqualTo(Long value) {
            addCriterion("ORIGINAL_ID =", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdNotEqualTo(Long value) {
            addCriterion("ORIGINAL_ID <>", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdGreaterThan(Long value) {
            addCriterion("ORIGINAL_ID >", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ORIGINAL_ID >=", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdLessThan(Long value) {
            addCriterion("ORIGINAL_ID <", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdLessThanOrEqualTo(Long value) {
            addCriterion("ORIGINAL_ID <=", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdIn(List<Long> values) {
            addCriterion("ORIGINAL_ID in", values, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdNotIn(List<Long> values) {
            addCriterion("ORIGINAL_ID not in", values, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdBetween(Long value1, Long value2) {
            addCriterion("ORIGINAL_ID between", value1, value2, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdNotBetween(Long value1, Long value2) {
            addCriterion("ORIGINAL_ID not between", value1, value2, "originalId");
            return (Criteria) this;
        }

        public Criteria andCheckDateIsNull() {
            addCriterion("CHECK_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCheckDateIsNotNull() {
            addCriterion("CHECK_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCheckDateEqualTo(Timestamp value) {
            addCriterion("CHECK_DATE =", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotEqualTo(Timestamp value) {
            addCriterion("CHECK_DATE <>", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThan(Timestamp value) {
            addCriterion("CHECK_DATE >", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CHECK_DATE >=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThan(Timestamp value) {
            addCriterion("CHECK_DATE <", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("CHECK_DATE <=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateIn(List<Timestamp> values) {
            addCriterion("CHECK_DATE in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotIn(List<Timestamp> values) {
            addCriterion("CHECK_DATE not in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CHECK_DATE between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CHECK_DATE not between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andTransOutOrderNoIsNull() {
            addCriterion("TRANS_OUT_ORDER_NO is null");
            return (Criteria) this;
        }

        public Criteria andTransOutOrderNoIsNotNull() {
            addCriterion("TRANS_OUT_ORDER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andTransOutOrderNoEqualTo(String value) {
            addCriterion("TRANS_OUT_ORDER_NO =", value, "transOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andTransOutOrderNoNotEqualTo(String value) {
            addCriterion("TRANS_OUT_ORDER_NO <>", value, "transOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andTransOutOrderNoGreaterThan(String value) {
            addCriterion("TRANS_OUT_ORDER_NO >", value, "transOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andTransOutOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_OUT_ORDER_NO >=", value, "transOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andTransOutOrderNoLessThan(String value) {
            addCriterion("TRANS_OUT_ORDER_NO <", value, "transOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andTransOutOrderNoLessThanOrEqualTo(String value) {
            addCriterion("TRANS_OUT_ORDER_NO <=", value, "transOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andTransOutOrderNoLike(String value) {
            addCriterion("TRANS_OUT_ORDER_NO like", value, "transOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andTransOutOrderNoNotLike(String value) {
            addCriterion("TRANS_OUT_ORDER_NO not like", value, "transOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andTransOutOrderNoIn(List<String> values) {
            addCriterion("TRANS_OUT_ORDER_NO in", values, "transOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andTransOutOrderNoNotIn(List<String> values) {
            addCriterion("TRANS_OUT_ORDER_NO not in", values, "transOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andTransOutOrderNoBetween(String value1, String value2) {
            addCriterion("TRANS_OUT_ORDER_NO between", value1, value2, "transOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andTransOutOrderNoNotBetween(String value1, String value2) {
            addCriterion("TRANS_OUT_ORDER_NO not between", value1, value2, "transOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoIsNull() {
            addCriterion("TRADE_NO is null");
            return (Criteria) this;
        }

        public Criteria andTradeNoIsNotNull() {
            addCriterion("TRADE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andTradeNoEqualTo(String value) {
            addCriterion("TRADE_NO =", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotEqualTo(String value) {
            addCriterion("TRADE_NO <>", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoGreaterThan(String value) {
            addCriterion("TRADE_NO >", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoGreaterThanOrEqualTo(String value) {
            addCriterion("TRADE_NO >=", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLessThan(String value) {
            addCriterion("TRADE_NO <", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLessThanOrEqualTo(String value) {
            addCriterion("TRADE_NO <=", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoLike(String value) {
            addCriterion("TRADE_NO like", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotLike(String value) {
            addCriterion("TRADE_NO not like", value, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoIn(List<String> values) {
            addCriterion("TRADE_NO in", values, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotIn(List<String> values) {
            addCriterion("TRADE_NO not in", values, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoBetween(String value1, String value2) {
            addCriterion("TRADE_NO between", value1, value2, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeNoNotBetween(String value1, String value2) {
            addCriterion("TRADE_NO not between", value1, value2, "tradeNo");
            return (Criteria) this;
        }

        public Criteria andPartnerIdIsNull() {
            addCriterion("PARTNER_ID is null");
            return (Criteria) this;
        }

        public Criteria andPartnerIdIsNotNull() {
            addCriterion("PARTNER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerIdEqualTo(String value) {
            addCriterion("PARTNER_ID =", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotEqualTo(String value) {
            addCriterion("PARTNER_ID <>", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdGreaterThan(String value) {
            addCriterion("PARTNER_ID >", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARTNER_ID >=", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdLessThan(String value) {
            addCriterion("PARTNER_ID <", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdLessThanOrEqualTo(String value) {
            addCriterion("PARTNER_ID <=", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdLike(String value) {
            addCriterion("PARTNER_ID like", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotLike(String value) {
            addCriterion("PARTNER_ID not like", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdIn(List<String> values) {
            addCriterion("PARTNER_ID in", values, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotIn(List<String> values) {
            addCriterion("PARTNER_ID not in", values, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdBetween(String value1, String value2) {
            addCriterion("PARTNER_ID between", value1, value2, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotBetween(String value1, String value2) {
            addCriterion("PARTNER_ID not between", value1, value2, "partnerId");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("BALANCE is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("BALANCE is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(String value) {
            addCriterion("BALANCE =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(String value) {
            addCriterion("BALANCE <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(String value) {
            addCriterion("BALANCE >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(String value) {
            addCriterion("BALANCE >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(String value) {
            addCriterion("BALANCE <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(String value) {
            addCriterion("BALANCE <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLike(String value) {
            addCriterion("BALANCE like", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotLike(String value) {
            addCriterion("BALANCE not like", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<String> values) {
            addCriterion("BALANCE in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<String> values) {
            addCriterion("BALANCE not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(String value1, String value2) {
            addCriterion("BALANCE between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(String value1, String value2) {
            addCriterion("BALANCE not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIsNull() {
            addCriterion("BANK_ACCOUNT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIsNotNull() {
            addCriterion("BANK_ACCOUNT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NAME =", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NAME <>", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameGreaterThan(String value) {
            addCriterion("BANK_ACCOUNT_NAME >", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NAME >=", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLessThan(String value) {
            addCriterion("BANK_ACCOUNT_NAME <", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLessThanOrEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NAME <=", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLike(String value) {
            addCriterion("BANK_ACCOUNT_NAME like", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotLike(String value) {
            addCriterion("BANK_ACCOUNT_NAME not like", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIn(List<String> values) {
            addCriterion("BANK_ACCOUNT_NAME in", values, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotIn(List<String> values) {
            addCriterion("BANK_ACCOUNT_NAME not in", values, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameBetween(String value1, String value2) {
            addCriterion("BANK_ACCOUNT_NAME between", value1, value2, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotBetween(String value1, String value2) {
            addCriterion("BANK_ACCOUNT_NAME not between", value1, value2, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoIsNull() {
            addCriterion("BANK_ACCOUNT_NO is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoIsNotNull() {
            addCriterion("BANK_ACCOUNT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NO =", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoNotEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NO <>", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoGreaterThan(String value) {
            addCriterion("BANK_ACCOUNT_NO >", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NO >=", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoLessThan(String value) {
            addCriterion("BANK_ACCOUNT_NO <", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoLessThanOrEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NO <=", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoLike(String value) {
            addCriterion("BANK_ACCOUNT_NO like", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoNotLike(String value) {
            addCriterion("BANK_ACCOUNT_NO not like", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoIn(List<String> values) {
            addCriterion("BANK_ACCOUNT_NO in", values, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoNotIn(List<String> values) {
            addCriterion("BANK_ACCOUNT_NO not in", values, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoBetween(String value1, String value2) {
            addCriterion("BANK_ACCOUNT_NO between", value1, value2, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoNotBetween(String value1, String value2) {
            addCriterion("BANK_ACCOUNT_NO not between", value1, value2, "bankAccountNo");
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

        public Criteria andBuyerAccountIsNull() {
            addCriterion("BUYER_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andBuyerAccountIsNotNull() {
            addCriterion("BUYER_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerAccountEqualTo(String value) {
            addCriterion("BUYER_ACCOUNT =", value, "buyerAccount");
            return (Criteria) this;
        }

        public Criteria andBuyerAccountNotEqualTo(String value) {
            addCriterion("BUYER_ACCOUNT <>", value, "buyerAccount");
            return (Criteria) this;
        }

        public Criteria andBuyerAccountGreaterThan(String value) {
            addCriterion("BUYER_ACCOUNT >", value, "buyerAccount");
            return (Criteria) this;
        }

        public Criteria andBuyerAccountGreaterThanOrEqualTo(String value) {
            addCriterion("BUYER_ACCOUNT >=", value, "buyerAccount");
            return (Criteria) this;
        }

        public Criteria andBuyerAccountLessThan(String value) {
            addCriterion("BUYER_ACCOUNT <", value, "buyerAccount");
            return (Criteria) this;
        }

        public Criteria andBuyerAccountLessThanOrEqualTo(String value) {
            addCriterion("BUYER_ACCOUNT <=", value, "buyerAccount");
            return (Criteria) this;
        }

        public Criteria andBuyerAccountLike(String value) {
            addCriterion("BUYER_ACCOUNT like", value, "buyerAccount");
            return (Criteria) this;
        }

        public Criteria andBuyerAccountNotLike(String value) {
            addCriterion("BUYER_ACCOUNT not like", value, "buyerAccount");
            return (Criteria) this;
        }

        public Criteria andBuyerAccountIn(List<String> values) {
            addCriterion("BUYER_ACCOUNT in", values, "buyerAccount");
            return (Criteria) this;
        }

        public Criteria andBuyerAccountNotIn(List<String> values) {
            addCriterion("BUYER_ACCOUNT not in", values, "buyerAccount");
            return (Criteria) this;
        }

        public Criteria andBuyerAccountBetween(String value1, String value2) {
            addCriterion("BUYER_ACCOUNT between", value1, value2, "buyerAccount");
            return (Criteria) this;
        }

        public Criteria andBuyerAccountNotBetween(String value1, String value2) {
            addCriterion("BUYER_ACCOUNT not between", value1, value2, "buyerAccount");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("CURRENCY is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("CURRENCY is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("CURRENCY =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("CURRENCY <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("CURRENCY >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("CURRENCY >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("CURRENCY <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("CURRENCY <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("CURRENCY like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("CURRENCY not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("CURRENCY in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("CURRENCY not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("CURRENCY between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("CURRENCY not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andDepositBankNoIsNull() {
            addCriterion("DEPOSIT_BANK_NO is null");
            return (Criteria) this;
        }

        public Criteria andDepositBankNoIsNotNull() {
            addCriterion("DEPOSIT_BANK_NO is not null");
            return (Criteria) this;
        }

        public Criteria andDepositBankNoEqualTo(String value) {
            addCriterion("DEPOSIT_BANK_NO =", value, "depositBankNo");
            return (Criteria) this;
        }

        public Criteria andDepositBankNoNotEqualTo(String value) {
            addCriterion("DEPOSIT_BANK_NO <>", value, "depositBankNo");
            return (Criteria) this;
        }

        public Criteria andDepositBankNoGreaterThan(String value) {
            addCriterion("DEPOSIT_BANK_NO >", value, "depositBankNo");
            return (Criteria) this;
        }

        public Criteria andDepositBankNoGreaterThanOrEqualTo(String value) {
            addCriterion("DEPOSIT_BANK_NO >=", value, "depositBankNo");
            return (Criteria) this;
        }

        public Criteria andDepositBankNoLessThan(String value) {
            addCriterion("DEPOSIT_BANK_NO <", value, "depositBankNo");
            return (Criteria) this;
        }

        public Criteria andDepositBankNoLessThanOrEqualTo(String value) {
            addCriterion("DEPOSIT_BANK_NO <=", value, "depositBankNo");
            return (Criteria) this;
        }

        public Criteria andDepositBankNoLike(String value) {
            addCriterion("DEPOSIT_BANK_NO like", value, "depositBankNo");
            return (Criteria) this;
        }

        public Criteria andDepositBankNoNotLike(String value) {
            addCriterion("DEPOSIT_BANK_NO not like", value, "depositBankNo");
            return (Criteria) this;
        }

        public Criteria andDepositBankNoIn(List<String> values) {
            addCriterion("DEPOSIT_BANK_NO in", values, "depositBankNo");
            return (Criteria) this;
        }

        public Criteria andDepositBankNoNotIn(List<String> values) {
            addCriterion("DEPOSIT_BANK_NO not in", values, "depositBankNo");
            return (Criteria) this;
        }

        public Criteria andDepositBankNoBetween(String value1, String value2) {
            addCriterion("DEPOSIT_BANK_NO between", value1, value2, "depositBankNo");
            return (Criteria) this;
        }

        public Criteria andDepositBankNoNotBetween(String value1, String value2) {
            addCriterion("DEPOSIT_BANK_NO not between", value1, value2, "depositBankNo");
            return (Criteria) this;
        }

        public Criteria andGoodsTitleIsNull() {
            addCriterion("GOODS_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andGoodsTitleIsNotNull() {
            addCriterion("GOODS_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsTitleEqualTo(String value) {
            addCriterion("GOODS_TITLE =", value, "goodsTitle");
            return (Criteria) this;
        }

        public Criteria andGoodsTitleNotEqualTo(String value) {
            addCriterion("GOODS_TITLE <>", value, "goodsTitle");
            return (Criteria) this;
        }

        public Criteria andGoodsTitleGreaterThan(String value) {
            addCriterion("GOODS_TITLE >", value, "goodsTitle");
            return (Criteria) this;
        }

        public Criteria andGoodsTitleGreaterThanOrEqualTo(String value) {
            addCriterion("GOODS_TITLE >=", value, "goodsTitle");
            return (Criteria) this;
        }

        public Criteria andGoodsTitleLessThan(String value) {
            addCriterion("GOODS_TITLE <", value, "goodsTitle");
            return (Criteria) this;
        }

        public Criteria andGoodsTitleLessThanOrEqualTo(String value) {
            addCriterion("GOODS_TITLE <=", value, "goodsTitle");
            return (Criteria) this;
        }

        public Criteria andGoodsTitleLike(String value) {
            addCriterion("GOODS_TITLE like", value, "goodsTitle");
            return (Criteria) this;
        }

        public Criteria andGoodsTitleNotLike(String value) {
            addCriterion("GOODS_TITLE not like", value, "goodsTitle");
            return (Criteria) this;
        }

        public Criteria andGoodsTitleIn(List<String> values) {
            addCriterion("GOODS_TITLE in", values, "goodsTitle");
            return (Criteria) this;
        }

        public Criteria andGoodsTitleNotIn(List<String> values) {
            addCriterion("GOODS_TITLE not in", values, "goodsTitle");
            return (Criteria) this;
        }

        public Criteria andGoodsTitleBetween(String value1, String value2) {
            addCriterion("GOODS_TITLE between", value1, value2, "goodsTitle");
            return (Criteria) this;
        }

        public Criteria andGoodsTitleNotBetween(String value1, String value2) {
            addCriterion("GOODS_TITLE not between", value1, value2, "goodsTitle");
            return (Criteria) this;
        }

        public Criteria andIncomeIsNull() {
            addCriterion("INCOME is null");
            return (Criteria) this;
        }

        public Criteria andIncomeIsNotNull() {
            addCriterion("INCOME is not null");
            return (Criteria) this;
        }

        public Criteria andIncomeEqualTo(String value) {
            addCriterion("INCOME =", value, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeNotEqualTo(String value) {
            addCriterion("INCOME <>", value, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeGreaterThan(String value) {
            addCriterion("INCOME >", value, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeGreaterThanOrEqualTo(String value) {
            addCriterion("INCOME >=", value, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeLessThan(String value) {
            addCriterion("INCOME <", value, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeLessThanOrEqualTo(String value) {
            addCriterion("INCOME <=", value, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeLike(String value) {
            addCriterion("INCOME like", value, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeNotLike(String value) {
            addCriterion("INCOME not like", value, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeIn(List<String> values) {
            addCriterion("INCOME in", values, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeNotIn(List<String> values) {
            addCriterion("INCOME not in", values, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeBetween(String value1, String value2) {
            addCriterion("INCOME between", value1, value2, "income");
            return (Criteria) this;
        }

        public Criteria andIncomeNotBetween(String value1, String value2) {
            addCriterion("INCOME not between", value1, value2, "income");
            return (Criteria) this;
        }

        public Criteria andIwAccountLogIdIsNull() {
            addCriterion("IW_ACCOUNT_LOG_ID is null");
            return (Criteria) this;
        }

        public Criteria andIwAccountLogIdIsNotNull() {
            addCriterion("IW_ACCOUNT_LOG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andIwAccountLogIdEqualTo(String value) {
            addCriterion("IW_ACCOUNT_LOG_ID =", value, "iwAccountLogId");
            return (Criteria) this;
        }

        public Criteria andIwAccountLogIdNotEqualTo(String value) {
            addCriterion("IW_ACCOUNT_LOG_ID <>", value, "iwAccountLogId");
            return (Criteria) this;
        }

        public Criteria andIwAccountLogIdGreaterThan(String value) {
            addCriterion("IW_ACCOUNT_LOG_ID >", value, "iwAccountLogId");
            return (Criteria) this;
        }

        public Criteria andIwAccountLogIdGreaterThanOrEqualTo(String value) {
            addCriterion("IW_ACCOUNT_LOG_ID >=", value, "iwAccountLogId");
            return (Criteria) this;
        }

        public Criteria andIwAccountLogIdLessThan(String value) {
            addCriterion("IW_ACCOUNT_LOG_ID <", value, "iwAccountLogId");
            return (Criteria) this;
        }

        public Criteria andIwAccountLogIdLessThanOrEqualTo(String value) {
            addCriterion("IW_ACCOUNT_LOG_ID <=", value, "iwAccountLogId");
            return (Criteria) this;
        }

        public Criteria andIwAccountLogIdLike(String value) {
            addCriterion("IW_ACCOUNT_LOG_ID like", value, "iwAccountLogId");
            return (Criteria) this;
        }

        public Criteria andIwAccountLogIdNotLike(String value) {
            addCriterion("IW_ACCOUNT_LOG_ID not like", value, "iwAccountLogId");
            return (Criteria) this;
        }

        public Criteria andIwAccountLogIdIn(List<String> values) {
            addCriterion("IW_ACCOUNT_LOG_ID in", values, "iwAccountLogId");
            return (Criteria) this;
        }

        public Criteria andIwAccountLogIdNotIn(List<String> values) {
            addCriterion("IW_ACCOUNT_LOG_ID not in", values, "iwAccountLogId");
            return (Criteria) this;
        }

        public Criteria andIwAccountLogIdBetween(String value1, String value2) {
            addCriterion("IW_ACCOUNT_LOG_ID between", value1, value2, "iwAccountLogId");
            return (Criteria) this;
        }

        public Criteria andIwAccountLogIdNotBetween(String value1, String value2) {
            addCriterion("IW_ACCOUNT_LOG_ID not between", value1, value2, "iwAccountLogId");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("MEMO in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("MEMO not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMerchantOutOrderNoIsNull() {
            addCriterion("MERCHANT_OUT_ORDER_NO is null");
            return (Criteria) this;
        }

        public Criteria andMerchantOutOrderNoIsNotNull() {
            addCriterion("MERCHANT_OUT_ORDER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantOutOrderNoEqualTo(String value) {
            addCriterion("MERCHANT_OUT_ORDER_NO =", value, "merchantOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andMerchantOutOrderNoNotEqualTo(String value) {
            addCriterion("MERCHANT_OUT_ORDER_NO <>", value, "merchantOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andMerchantOutOrderNoGreaterThan(String value) {
            addCriterion("MERCHANT_OUT_ORDER_NO >", value, "merchantOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andMerchantOutOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("MERCHANT_OUT_ORDER_NO >=", value, "merchantOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andMerchantOutOrderNoLessThan(String value) {
            addCriterion("MERCHANT_OUT_ORDER_NO <", value, "merchantOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andMerchantOutOrderNoLessThanOrEqualTo(String value) {
            addCriterion("MERCHANT_OUT_ORDER_NO <=", value, "merchantOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andMerchantOutOrderNoLike(String value) {
            addCriterion("MERCHANT_OUT_ORDER_NO like", value, "merchantOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andMerchantOutOrderNoNotLike(String value) {
            addCriterion("MERCHANT_OUT_ORDER_NO not like", value, "merchantOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andMerchantOutOrderNoIn(List<String> values) {
            addCriterion("MERCHANT_OUT_ORDER_NO in", values, "merchantOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andMerchantOutOrderNoNotIn(List<String> values) {
            addCriterion("MERCHANT_OUT_ORDER_NO not in", values, "merchantOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andMerchantOutOrderNoBetween(String value1, String value2) {
            addCriterion("MERCHANT_OUT_ORDER_NO between", value1, value2, "merchantOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andMerchantOutOrderNoNotBetween(String value1, String value2) {
            addCriterion("MERCHANT_OUT_ORDER_NO not between", value1, value2, "merchantOutOrderNo");
            return (Criteria) this;
        }

        public Criteria andOtherAccountEmailIsNull() {
            addCriterion("OTHER_ACCOUNT_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andOtherAccountEmailIsNotNull() {
            addCriterion("OTHER_ACCOUNT_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andOtherAccountEmailEqualTo(String value) {
            addCriterion("OTHER_ACCOUNT_EMAIL =", value, "otherAccountEmail");
            return (Criteria) this;
        }

        public Criteria andOtherAccountEmailNotEqualTo(String value) {
            addCriterion("OTHER_ACCOUNT_EMAIL <>", value, "otherAccountEmail");
            return (Criteria) this;
        }

        public Criteria andOtherAccountEmailGreaterThan(String value) {
            addCriterion("OTHER_ACCOUNT_EMAIL >", value, "otherAccountEmail");
            return (Criteria) this;
        }

        public Criteria andOtherAccountEmailGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_ACCOUNT_EMAIL >=", value, "otherAccountEmail");
            return (Criteria) this;
        }

        public Criteria andOtherAccountEmailLessThan(String value) {
            addCriterion("OTHER_ACCOUNT_EMAIL <", value, "otherAccountEmail");
            return (Criteria) this;
        }

        public Criteria andOtherAccountEmailLessThanOrEqualTo(String value) {
            addCriterion("OTHER_ACCOUNT_EMAIL <=", value, "otherAccountEmail");
            return (Criteria) this;
        }

        public Criteria andOtherAccountEmailLike(String value) {
            addCriterion("OTHER_ACCOUNT_EMAIL like", value, "otherAccountEmail");
            return (Criteria) this;
        }

        public Criteria andOtherAccountEmailNotLike(String value) {
            addCriterion("OTHER_ACCOUNT_EMAIL not like", value, "otherAccountEmail");
            return (Criteria) this;
        }

        public Criteria andOtherAccountEmailIn(List<String> values) {
            addCriterion("OTHER_ACCOUNT_EMAIL in", values, "otherAccountEmail");
            return (Criteria) this;
        }

        public Criteria andOtherAccountEmailNotIn(List<String> values) {
            addCriterion("OTHER_ACCOUNT_EMAIL not in", values, "otherAccountEmail");
            return (Criteria) this;
        }

        public Criteria andOtherAccountEmailBetween(String value1, String value2) {
            addCriterion("OTHER_ACCOUNT_EMAIL between", value1, value2, "otherAccountEmail");
            return (Criteria) this;
        }

        public Criteria andOtherAccountEmailNotBetween(String value1, String value2) {
            addCriterion("OTHER_ACCOUNT_EMAIL not between", value1, value2, "otherAccountEmail");
            return (Criteria) this;
        }

        public Criteria andOtherAccountFullnameIsNull() {
            addCriterion("OTHER_ACCOUNT_FULLNAME is null");
            return (Criteria) this;
        }

        public Criteria andOtherAccountFullnameIsNotNull() {
            addCriterion("OTHER_ACCOUNT_FULLNAME is not null");
            return (Criteria) this;
        }

        public Criteria andOtherAccountFullnameEqualTo(String value) {
            addCriterion("OTHER_ACCOUNT_FULLNAME =", value, "otherAccountFullname");
            return (Criteria) this;
        }

        public Criteria andOtherAccountFullnameNotEqualTo(String value) {
            addCriterion("OTHER_ACCOUNT_FULLNAME <>", value, "otherAccountFullname");
            return (Criteria) this;
        }

        public Criteria andOtherAccountFullnameGreaterThan(String value) {
            addCriterion("OTHER_ACCOUNT_FULLNAME >", value, "otherAccountFullname");
            return (Criteria) this;
        }

        public Criteria andOtherAccountFullnameGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_ACCOUNT_FULLNAME >=", value, "otherAccountFullname");
            return (Criteria) this;
        }

        public Criteria andOtherAccountFullnameLessThan(String value) {
            addCriterion("OTHER_ACCOUNT_FULLNAME <", value, "otherAccountFullname");
            return (Criteria) this;
        }

        public Criteria andOtherAccountFullnameLessThanOrEqualTo(String value) {
            addCriterion("OTHER_ACCOUNT_FULLNAME <=", value, "otherAccountFullname");
            return (Criteria) this;
        }

        public Criteria andOtherAccountFullnameLike(String value) {
            addCriterion("OTHER_ACCOUNT_FULLNAME like", value, "otherAccountFullname");
            return (Criteria) this;
        }

        public Criteria andOtherAccountFullnameNotLike(String value) {
            addCriterion("OTHER_ACCOUNT_FULLNAME not like", value, "otherAccountFullname");
            return (Criteria) this;
        }

        public Criteria andOtherAccountFullnameIn(List<String> values) {
            addCriterion("OTHER_ACCOUNT_FULLNAME in", values, "otherAccountFullname");
            return (Criteria) this;
        }

        public Criteria andOtherAccountFullnameNotIn(List<String> values) {
            addCriterion("OTHER_ACCOUNT_FULLNAME not in", values, "otherAccountFullname");
            return (Criteria) this;
        }

        public Criteria andOtherAccountFullnameBetween(String value1, String value2) {
            addCriterion("OTHER_ACCOUNT_FULLNAME between", value1, value2, "otherAccountFullname");
            return (Criteria) this;
        }

        public Criteria andOtherAccountFullnameNotBetween(String value1, String value2) {
            addCriterion("OTHER_ACCOUNT_FULLNAME not between", value1, value2, "otherAccountFullname");
            return (Criteria) this;
        }

        public Criteria andOtherUserIdIsNull() {
            addCriterion("OTHER_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOtherUserIdIsNotNull() {
            addCriterion("OTHER_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOtherUserIdEqualTo(String value) {
            addCriterion("OTHER_USER_ID =", value, "otherUserId");
            return (Criteria) this;
        }

        public Criteria andOtherUserIdNotEqualTo(String value) {
            addCriterion("OTHER_USER_ID <>", value, "otherUserId");
            return (Criteria) this;
        }

        public Criteria andOtherUserIdGreaterThan(String value) {
            addCriterion("OTHER_USER_ID >", value, "otherUserId");
            return (Criteria) this;
        }

        public Criteria andOtherUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_USER_ID >=", value, "otherUserId");
            return (Criteria) this;
        }

        public Criteria andOtherUserIdLessThan(String value) {
            addCriterion("OTHER_USER_ID <", value, "otherUserId");
            return (Criteria) this;
        }

        public Criteria andOtherUserIdLessThanOrEqualTo(String value) {
            addCriterion("OTHER_USER_ID <=", value, "otherUserId");
            return (Criteria) this;
        }

        public Criteria andOtherUserIdLike(String value) {
            addCriterion("OTHER_USER_ID like", value, "otherUserId");
            return (Criteria) this;
        }

        public Criteria andOtherUserIdNotLike(String value) {
            addCriterion("OTHER_USER_ID not like", value, "otherUserId");
            return (Criteria) this;
        }

        public Criteria andOtherUserIdIn(List<String> values) {
            addCriterion("OTHER_USER_ID in", values, "otherUserId");
            return (Criteria) this;
        }

        public Criteria andOtherUserIdNotIn(List<String> values) {
            addCriterion("OTHER_USER_ID not in", values, "otherUserId");
            return (Criteria) this;
        }

        public Criteria andOtherUserIdBetween(String value1, String value2) {
            addCriterion("OTHER_USER_ID between", value1, value2, "otherUserId");
            return (Criteria) this;
        }

        public Criteria andOtherUserIdNotBetween(String value1, String value2) {
            addCriterion("OTHER_USER_ID not between", value1, value2, "otherUserId");
            return (Criteria) this;
        }

        public Criteria andOutcomeIsNull() {
            addCriterion("OUTCOME is null");
            return (Criteria) this;
        }

        public Criteria andOutcomeIsNotNull() {
            addCriterion("OUTCOME is not null");
            return (Criteria) this;
        }

        public Criteria andOutcomeEqualTo(String value) {
            addCriterion("OUTCOME =", value, "outcome");
            return (Criteria) this;
        }

        public Criteria andOutcomeNotEqualTo(String value) {
            addCriterion("OUTCOME <>", value, "outcome");
            return (Criteria) this;
        }

        public Criteria andOutcomeGreaterThan(String value) {
            addCriterion("OUTCOME >", value, "outcome");
            return (Criteria) this;
        }

        public Criteria andOutcomeGreaterThanOrEqualTo(String value) {
            addCriterion("OUTCOME >=", value, "outcome");
            return (Criteria) this;
        }

        public Criteria andOutcomeLessThan(String value) {
            addCriterion("OUTCOME <", value, "outcome");
            return (Criteria) this;
        }

        public Criteria andOutcomeLessThanOrEqualTo(String value) {
            addCriterion("OUTCOME <=", value, "outcome");
            return (Criteria) this;
        }

        public Criteria andOutcomeLike(String value) {
            addCriterion("OUTCOME like", value, "outcome");
            return (Criteria) this;
        }

        public Criteria andOutcomeNotLike(String value) {
            addCriterion("OUTCOME not like", value, "outcome");
            return (Criteria) this;
        }

        public Criteria andOutcomeIn(List<String> values) {
            addCriterion("OUTCOME in", values, "outcome");
            return (Criteria) this;
        }

        public Criteria andOutcomeNotIn(List<String> values) {
            addCriterion("OUTCOME not in", values, "outcome");
            return (Criteria) this;
        }

        public Criteria andOutcomeBetween(String value1, String value2) {
            addCriterion("OUTCOME between", value1, value2, "outcome");
            return (Criteria) this;
        }

        public Criteria andOutcomeNotBetween(String value1, String value2) {
            addCriterion("OUTCOME not between", value1, value2, "outcome");
            return (Criteria) this;
        }

        public Criteria andSellerAccountIsNull() {
            addCriterion("SELLER_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andSellerAccountIsNotNull() {
            addCriterion("SELLER_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andSellerAccountEqualTo(String value) {
            addCriterion("SELLER_ACCOUNT =", value, "sellerAccount");
            return (Criteria) this;
        }

        public Criteria andSellerAccountNotEqualTo(String value) {
            addCriterion("SELLER_ACCOUNT <>", value, "sellerAccount");
            return (Criteria) this;
        }

        public Criteria andSellerAccountGreaterThan(String value) {
            addCriterion("SELLER_ACCOUNT >", value, "sellerAccount");
            return (Criteria) this;
        }

        public Criteria andSellerAccountGreaterThanOrEqualTo(String value) {
            addCriterion("SELLER_ACCOUNT >=", value, "sellerAccount");
            return (Criteria) this;
        }

        public Criteria andSellerAccountLessThan(String value) {
            addCriterion("SELLER_ACCOUNT <", value, "sellerAccount");
            return (Criteria) this;
        }

        public Criteria andSellerAccountLessThanOrEqualTo(String value) {
            addCriterion("SELLER_ACCOUNT <=", value, "sellerAccount");
            return (Criteria) this;
        }

        public Criteria andSellerAccountLike(String value) {
            addCriterion("SELLER_ACCOUNT like", value, "sellerAccount");
            return (Criteria) this;
        }

        public Criteria andSellerAccountNotLike(String value) {
            addCriterion("SELLER_ACCOUNT not like", value, "sellerAccount");
            return (Criteria) this;
        }

        public Criteria andSellerAccountIn(List<String> values) {
            addCriterion("SELLER_ACCOUNT in", values, "sellerAccount");
            return (Criteria) this;
        }

        public Criteria andSellerAccountNotIn(List<String> values) {
            addCriterion("SELLER_ACCOUNT not in", values, "sellerAccount");
            return (Criteria) this;
        }

        public Criteria andSellerAccountBetween(String value1, String value2) {
            addCriterion("SELLER_ACCOUNT between", value1, value2, "sellerAccount");
            return (Criteria) this;
        }

        public Criteria andSellerAccountNotBetween(String value1, String value2) {
            addCriterion("SELLER_ACCOUNT not between", value1, value2, "sellerAccount");
            return (Criteria) this;
        }

        public Criteria andSellerFullnameIsNull() {
            addCriterion("SELLER_FULLNAME is null");
            return (Criteria) this;
        }

        public Criteria andSellerFullnameIsNotNull() {
            addCriterion("SELLER_FULLNAME is not null");
            return (Criteria) this;
        }

        public Criteria andSellerFullnameEqualTo(String value) {
            addCriterion("SELLER_FULLNAME =", value, "sellerFullname");
            return (Criteria) this;
        }

        public Criteria andSellerFullnameNotEqualTo(String value) {
            addCriterion("SELLER_FULLNAME <>", value, "sellerFullname");
            return (Criteria) this;
        }

        public Criteria andSellerFullnameGreaterThan(String value) {
            addCriterion("SELLER_FULLNAME >", value, "sellerFullname");
            return (Criteria) this;
        }

        public Criteria andSellerFullnameGreaterThanOrEqualTo(String value) {
            addCriterion("SELLER_FULLNAME >=", value, "sellerFullname");
            return (Criteria) this;
        }

        public Criteria andSellerFullnameLessThan(String value) {
            addCriterion("SELLER_FULLNAME <", value, "sellerFullname");
            return (Criteria) this;
        }

        public Criteria andSellerFullnameLessThanOrEqualTo(String value) {
            addCriterion("SELLER_FULLNAME <=", value, "sellerFullname");
            return (Criteria) this;
        }

        public Criteria andSellerFullnameLike(String value) {
            addCriterion("SELLER_FULLNAME like", value, "sellerFullname");
            return (Criteria) this;
        }

        public Criteria andSellerFullnameNotLike(String value) {
            addCriterion("SELLER_FULLNAME not like", value, "sellerFullname");
            return (Criteria) this;
        }

        public Criteria andSellerFullnameIn(List<String> values) {
            addCriterion("SELLER_FULLNAME in", values, "sellerFullname");
            return (Criteria) this;
        }

        public Criteria andSellerFullnameNotIn(List<String> values) {
            addCriterion("SELLER_FULLNAME not in", values, "sellerFullname");
            return (Criteria) this;
        }

        public Criteria andSellerFullnameBetween(String value1, String value2) {
            addCriterion("SELLER_FULLNAME between", value1, value2, "sellerFullname");
            return (Criteria) this;
        }

        public Criteria andSellerFullnameNotBetween(String value1, String value2) {
            addCriterion("SELLER_FULLNAME not between", value1, value2, "sellerFullname");
            return (Criteria) this;
        }

        public Criteria andServiceFeeIsNull() {
            addCriterion("SERVICE_FEE is null");
            return (Criteria) this;
        }

        public Criteria andServiceFeeIsNotNull() {
            addCriterion("SERVICE_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andServiceFeeEqualTo(String value) {
            addCriterion("SERVICE_FEE =", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeNotEqualTo(String value) {
            addCriterion("SERVICE_FEE <>", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeGreaterThan(String value) {
            addCriterion("SERVICE_FEE >", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICE_FEE >=", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeLessThan(String value) {
            addCriterion("SERVICE_FEE <", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeLessThanOrEqualTo(String value) {
            addCriterion("SERVICE_FEE <=", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeLike(String value) {
            addCriterion("SERVICE_FEE like", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeNotLike(String value) {
            addCriterion("SERVICE_FEE not like", value, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeIn(List<String> values) {
            addCriterion("SERVICE_FEE in", values, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeNotIn(List<String> values) {
            addCriterion("SERVICE_FEE not in", values, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeBetween(String value1, String value2) {
            addCriterion("SERVICE_FEE between", value1, value2, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeNotBetween(String value1, String value2) {
            addCriterion("SERVICE_FEE not between", value1, value2, "serviceFee");
            return (Criteria) this;
        }

        public Criteria andServiceFeeRatioIsNull() {
            addCriterion("SERVICE_FEE_RATIO is null");
            return (Criteria) this;
        }

        public Criteria andServiceFeeRatioIsNotNull() {
            addCriterion("SERVICE_FEE_RATIO is not null");
            return (Criteria) this;
        }

        public Criteria andServiceFeeRatioEqualTo(String value) {
            addCriterion("SERVICE_FEE_RATIO =", value, "serviceFeeRatio");
            return (Criteria) this;
        }

        public Criteria andServiceFeeRatioNotEqualTo(String value) {
            addCriterion("SERVICE_FEE_RATIO <>", value, "serviceFeeRatio");
            return (Criteria) this;
        }

        public Criteria andServiceFeeRatioGreaterThan(String value) {
            addCriterion("SERVICE_FEE_RATIO >", value, "serviceFeeRatio");
            return (Criteria) this;
        }

        public Criteria andServiceFeeRatioGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICE_FEE_RATIO >=", value, "serviceFeeRatio");
            return (Criteria) this;
        }

        public Criteria andServiceFeeRatioLessThan(String value) {
            addCriterion("SERVICE_FEE_RATIO <", value, "serviceFeeRatio");
            return (Criteria) this;
        }

        public Criteria andServiceFeeRatioLessThanOrEqualTo(String value) {
            addCriterion("SERVICE_FEE_RATIO <=", value, "serviceFeeRatio");
            return (Criteria) this;
        }

        public Criteria andServiceFeeRatioLike(String value) {
            addCriterion("SERVICE_FEE_RATIO like", value, "serviceFeeRatio");
            return (Criteria) this;
        }

        public Criteria andServiceFeeRatioNotLike(String value) {
            addCriterion("SERVICE_FEE_RATIO not like", value, "serviceFeeRatio");
            return (Criteria) this;
        }

        public Criteria andServiceFeeRatioIn(List<String> values) {
            addCriterion("SERVICE_FEE_RATIO in", values, "serviceFeeRatio");
            return (Criteria) this;
        }

        public Criteria andServiceFeeRatioNotIn(List<String> values) {
            addCriterion("SERVICE_FEE_RATIO not in", values, "serviceFeeRatio");
            return (Criteria) this;
        }

        public Criteria andServiceFeeRatioBetween(String value1, String value2) {
            addCriterion("SERVICE_FEE_RATIO between", value1, value2, "serviceFeeRatio");
            return (Criteria) this;
        }

        public Criteria andServiceFeeRatioNotBetween(String value1, String value2) {
            addCriterion("SERVICE_FEE_RATIO not between", value1, value2, "serviceFeeRatio");
            return (Criteria) this;
        }

        public Criteria andTotalFeeIsNull() {
            addCriterion("TOTAL_FEE is null");
            return (Criteria) this;
        }

        public Criteria andTotalFeeIsNotNull() {
            addCriterion("TOTAL_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andTotalFeeEqualTo(String value) {
            addCriterion("TOTAL_FEE =", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeNotEqualTo(String value) {
            addCriterion("TOTAL_FEE <>", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeGreaterThan(String value) {
            addCriterion("TOTAL_FEE >", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeGreaterThanOrEqualTo(String value) {
            addCriterion("TOTAL_FEE >=", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeLessThan(String value) {
            addCriterion("TOTAL_FEE <", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeLessThanOrEqualTo(String value) {
            addCriterion("TOTAL_FEE <=", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeLike(String value) {
            addCriterion("TOTAL_FEE like", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeNotLike(String value) {
            addCriterion("TOTAL_FEE not like", value, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeIn(List<String> values) {
            addCriterion("TOTAL_FEE in", values, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeNotIn(List<String> values) {
            addCriterion("TOTAL_FEE not in", values, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeBetween(String value1, String value2) {
            addCriterion("TOTAL_FEE between", value1, value2, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTotalFeeNotBetween(String value1, String value2) {
            addCriterion("TOTAL_FEE not between", value1, value2, "totalFee");
            return (Criteria) this;
        }

        public Criteria andTradeRefundAmountIsNull() {
            addCriterion("TRADE_REFUND_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andTradeRefundAmountIsNotNull() {
            addCriterion("TRADE_REFUND_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTradeRefundAmountEqualTo(String value) {
            addCriterion("TRADE_REFUND_AMOUNT =", value, "tradeRefundAmount");
            return (Criteria) this;
        }

        public Criteria andTradeRefundAmountNotEqualTo(String value) {
            addCriterion("TRADE_REFUND_AMOUNT <>", value, "tradeRefundAmount");
            return (Criteria) this;
        }

        public Criteria andTradeRefundAmountGreaterThan(String value) {
            addCriterion("TRADE_REFUND_AMOUNT >", value, "tradeRefundAmount");
            return (Criteria) this;
        }

        public Criteria andTradeRefundAmountGreaterThanOrEqualTo(String value) {
            addCriterion("TRADE_REFUND_AMOUNT >=", value, "tradeRefundAmount");
            return (Criteria) this;
        }

        public Criteria andTradeRefundAmountLessThan(String value) {
            addCriterion("TRADE_REFUND_AMOUNT <", value, "tradeRefundAmount");
            return (Criteria) this;
        }

        public Criteria andTradeRefundAmountLessThanOrEqualTo(String value) {
            addCriterion("TRADE_REFUND_AMOUNT <=", value, "tradeRefundAmount");
            return (Criteria) this;
        }

        public Criteria andTradeRefundAmountLike(String value) {
            addCriterion("TRADE_REFUND_AMOUNT like", value, "tradeRefundAmount");
            return (Criteria) this;
        }

        public Criteria andTradeRefundAmountNotLike(String value) {
            addCriterion("TRADE_REFUND_AMOUNT not like", value, "tradeRefundAmount");
            return (Criteria) this;
        }

        public Criteria andTradeRefundAmountIn(List<String> values) {
            addCriterion("TRADE_REFUND_AMOUNT in", values, "tradeRefundAmount");
            return (Criteria) this;
        }

        public Criteria andTradeRefundAmountNotIn(List<String> values) {
            addCriterion("TRADE_REFUND_AMOUNT not in", values, "tradeRefundAmount");
            return (Criteria) this;
        }

        public Criteria andTradeRefundAmountBetween(String value1, String value2) {
            addCriterion("TRADE_REFUND_AMOUNT between", value1, value2, "tradeRefundAmount");
            return (Criteria) this;
        }

        public Criteria andTradeRefundAmountNotBetween(String value1, String value2) {
            addCriterion("TRADE_REFUND_AMOUNT not between", value1, value2, "tradeRefundAmount");
            return (Criteria) this;
        }

        public Criteria andTransAccountIsNull() {
            addCriterion("TRANS_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andTransAccountIsNotNull() {
            addCriterion("TRANS_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTransAccountEqualTo(String value) {
            addCriterion("TRANS_ACCOUNT =", value, "transAccount");
            return (Criteria) this;
        }

        public Criteria andTransAccountNotEqualTo(String value) {
            addCriterion("TRANS_ACCOUNT <>", value, "transAccount");
            return (Criteria) this;
        }

        public Criteria andTransAccountGreaterThan(String value) {
            addCriterion("TRANS_ACCOUNT >", value, "transAccount");
            return (Criteria) this;
        }

        public Criteria andTransAccountGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_ACCOUNT >=", value, "transAccount");
            return (Criteria) this;
        }

        public Criteria andTransAccountLessThan(String value) {
            addCriterion("TRANS_ACCOUNT <", value, "transAccount");
            return (Criteria) this;
        }

        public Criteria andTransAccountLessThanOrEqualTo(String value) {
            addCriterion("TRANS_ACCOUNT <=", value, "transAccount");
            return (Criteria) this;
        }

        public Criteria andTransAccountLike(String value) {
            addCriterion("TRANS_ACCOUNT like", value, "transAccount");
            return (Criteria) this;
        }

        public Criteria andTransAccountNotLike(String value) {
            addCriterion("TRANS_ACCOUNT not like", value, "transAccount");
            return (Criteria) this;
        }

        public Criteria andTransAccountIn(List<String> values) {
            addCriterion("TRANS_ACCOUNT in", values, "transAccount");
            return (Criteria) this;
        }

        public Criteria andTransAccountNotIn(List<String> values) {
            addCriterion("TRANS_ACCOUNT not in", values, "transAccount");
            return (Criteria) this;
        }

        public Criteria andTransAccountBetween(String value1, String value2) {
            addCriterion("TRANS_ACCOUNT between", value1, value2, "transAccount");
            return (Criteria) this;
        }

        public Criteria andTransAccountNotBetween(String value1, String value2) {
            addCriterion("TRANS_ACCOUNT not between", value1, value2, "transAccount");
            return (Criteria) this;
        }

        public Criteria andTransCodeMsgIsNull() {
            addCriterion("TRANS_CODE_MSG is null");
            return (Criteria) this;
        }

        public Criteria andTransCodeMsgIsNotNull() {
            addCriterion("TRANS_CODE_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andTransCodeMsgEqualTo(String value) {
            addCriterion("TRANS_CODE_MSG =", value, "transCodeMsg");
            return (Criteria) this;
        }

        public Criteria andTransCodeMsgNotEqualTo(String value) {
            addCriterion("TRANS_CODE_MSG <>", value, "transCodeMsg");
            return (Criteria) this;
        }

        public Criteria andTransCodeMsgGreaterThan(String value) {
            addCriterion("TRANS_CODE_MSG >", value, "transCodeMsg");
            return (Criteria) this;
        }

        public Criteria andTransCodeMsgGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_CODE_MSG >=", value, "transCodeMsg");
            return (Criteria) this;
        }

        public Criteria andTransCodeMsgLessThan(String value) {
            addCriterion("TRANS_CODE_MSG <", value, "transCodeMsg");
            return (Criteria) this;
        }

        public Criteria andTransCodeMsgLessThanOrEqualTo(String value) {
            addCriterion("TRANS_CODE_MSG <=", value, "transCodeMsg");
            return (Criteria) this;
        }

        public Criteria andTransCodeMsgLike(String value) {
            addCriterion("TRANS_CODE_MSG like", value, "transCodeMsg");
            return (Criteria) this;
        }

        public Criteria andTransCodeMsgNotLike(String value) {
            addCriterion("TRANS_CODE_MSG not like", value, "transCodeMsg");
            return (Criteria) this;
        }

        public Criteria andTransCodeMsgIn(List<String> values) {
            addCriterion("TRANS_CODE_MSG in", values, "transCodeMsg");
            return (Criteria) this;
        }

        public Criteria andTransCodeMsgNotIn(List<String> values) {
            addCriterion("TRANS_CODE_MSG not in", values, "transCodeMsg");
            return (Criteria) this;
        }

        public Criteria andTransCodeMsgBetween(String value1, String value2) {
            addCriterion("TRANS_CODE_MSG between", value1, value2, "transCodeMsg");
            return (Criteria) this;
        }

        public Criteria andTransCodeMsgNotBetween(String value1, String value2) {
            addCriterion("TRANS_CODE_MSG not between", value1, value2, "transCodeMsg");
            return (Criteria) this;
        }

        public Criteria andTransDateIsNull() {
            addCriterion("TRANS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTransDateIsNotNull() {
            addCriterion("TRANS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTransDateEqualTo(Timestamp value) {
            addCriterion("TRANS_DATE =", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateNotEqualTo(Timestamp value) {
            addCriterion("TRANS_DATE <>", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateGreaterThan(Timestamp value) {
            addCriterion("TRANS_DATE >", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("TRANS_DATE >=", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateLessThan(Timestamp value) {
            addCriterion("TRANS_DATE <", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("TRANS_DATE <=", value, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateIn(List<Timestamp> values) {
            addCriterion("TRANS_DATE in", values, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateNotIn(List<Timestamp> values) {
            addCriterion("TRANS_DATE not in", values, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("TRANS_DATE between", value1, value2, "transDate");
            return (Criteria) this;
        }

        public Criteria andTransDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("TRANS_DATE not between", value1, value2, "transDate");
            return (Criteria) this;
        }

        public Criteria andSubTransCodeMsgIsNull() {
            addCriterion("SUB_TRANS_CODE_MSG is null");
            return (Criteria) this;
        }

        public Criteria andSubTransCodeMsgIsNotNull() {
            addCriterion("SUB_TRANS_CODE_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andSubTransCodeMsgEqualTo(String value) {
            addCriterion("SUB_TRANS_CODE_MSG =", value, "subTransCodeMsg");
            return (Criteria) this;
        }

        public Criteria andSubTransCodeMsgNotEqualTo(String value) {
            addCriterion("SUB_TRANS_CODE_MSG <>", value, "subTransCodeMsg");
            return (Criteria) this;
        }

        public Criteria andSubTransCodeMsgGreaterThan(String value) {
            addCriterion("SUB_TRANS_CODE_MSG >", value, "subTransCodeMsg");
            return (Criteria) this;
        }

        public Criteria andSubTransCodeMsgGreaterThanOrEqualTo(String value) {
            addCriterion("SUB_TRANS_CODE_MSG >=", value, "subTransCodeMsg");
            return (Criteria) this;
        }

        public Criteria andSubTransCodeMsgLessThan(String value) {
            addCriterion("SUB_TRANS_CODE_MSG <", value, "subTransCodeMsg");
            return (Criteria) this;
        }

        public Criteria andSubTransCodeMsgLessThanOrEqualTo(String value) {
            addCriterion("SUB_TRANS_CODE_MSG <=", value, "subTransCodeMsg");
            return (Criteria) this;
        }

        public Criteria andSubTransCodeMsgLike(String value) {
            addCriterion("SUB_TRANS_CODE_MSG like", value, "subTransCodeMsg");
            return (Criteria) this;
        }

        public Criteria andSubTransCodeMsgNotLike(String value) {
            addCriterion("SUB_TRANS_CODE_MSG not like", value, "subTransCodeMsg");
            return (Criteria) this;
        }

        public Criteria andSubTransCodeMsgIn(List<String> values) {
            addCriterion("SUB_TRANS_CODE_MSG in", values, "subTransCodeMsg");
            return (Criteria) this;
        }

        public Criteria andSubTransCodeMsgNotIn(List<String> values) {
            addCriterion("SUB_TRANS_CODE_MSG not in", values, "subTransCodeMsg");
            return (Criteria) this;
        }

        public Criteria andSubTransCodeMsgBetween(String value1, String value2) {
            addCriterion("SUB_TRANS_CODE_MSG between", value1, value2, "subTransCodeMsg");
            return (Criteria) this;
        }

        public Criteria andSubTransCodeMsgNotBetween(String value1, String value2) {
            addCriterion("SUB_TRANS_CODE_MSG not between", value1, value2, "subTransCodeMsg");
            return (Criteria) this;
        }

        public Criteria andSignProductNameIsNull() {
            addCriterion("SIGN_PRODUCT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSignProductNameIsNotNull() {
            addCriterion("SIGN_PRODUCT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSignProductNameEqualTo(String value) {
            addCriterion("SIGN_PRODUCT_NAME =", value, "signProductName");
            return (Criteria) this;
        }

        public Criteria andSignProductNameNotEqualTo(String value) {
            addCriterion("SIGN_PRODUCT_NAME <>", value, "signProductName");
            return (Criteria) this;
        }

        public Criteria andSignProductNameGreaterThan(String value) {
            addCriterion("SIGN_PRODUCT_NAME >", value, "signProductName");
            return (Criteria) this;
        }

        public Criteria andSignProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_PRODUCT_NAME >=", value, "signProductName");
            return (Criteria) this;
        }

        public Criteria andSignProductNameLessThan(String value) {
            addCriterion("SIGN_PRODUCT_NAME <", value, "signProductName");
            return (Criteria) this;
        }

        public Criteria andSignProductNameLessThanOrEqualTo(String value) {
            addCriterion("SIGN_PRODUCT_NAME <=", value, "signProductName");
            return (Criteria) this;
        }

        public Criteria andSignProductNameLike(String value) {
            addCriterion("SIGN_PRODUCT_NAME like", value, "signProductName");
            return (Criteria) this;
        }

        public Criteria andSignProductNameNotLike(String value) {
            addCriterion("SIGN_PRODUCT_NAME not like", value, "signProductName");
            return (Criteria) this;
        }

        public Criteria andSignProductNameIn(List<String> values) {
            addCriterion("SIGN_PRODUCT_NAME in", values, "signProductName");
            return (Criteria) this;
        }

        public Criteria andSignProductNameNotIn(List<String> values) {
            addCriterion("SIGN_PRODUCT_NAME not in", values, "signProductName");
            return (Criteria) this;
        }

        public Criteria andSignProductNameBetween(String value1, String value2) {
            addCriterion("SIGN_PRODUCT_NAME between", value1, value2, "signProductName");
            return (Criteria) this;
        }

        public Criteria andSignProductNameNotBetween(String value1, String value2) {
            addCriterion("SIGN_PRODUCT_NAME not between", value1, value2, "signProductName");
            return (Criteria) this;
        }

        public Criteria andRateIsNull() {
            addCriterion("RATE is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("RATE is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(String value) {
            addCriterion("RATE =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(String value) {
            addCriterion("RATE <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(String value) {
            addCriterion("RATE >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(String value) {
            addCriterion("RATE >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(String value) {
            addCriterion("RATE <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(String value) {
            addCriterion("RATE <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLike(String value) {
            addCriterion("RATE like", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotLike(String value) {
            addCriterion("RATE not like", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<String> values) {
            addCriterion("RATE in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<String> values) {
            addCriterion("RATE not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(String value1, String value2) {
            addCriterion("RATE between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(String value1, String value2) {
            addCriterion("RATE not between", value1, value2, "rate");
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