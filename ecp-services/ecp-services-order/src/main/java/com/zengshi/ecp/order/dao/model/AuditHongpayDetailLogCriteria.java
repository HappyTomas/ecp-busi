package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AuditHongpayDetailLogCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public AuditHongpayDetailLogCriteria() {
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

        public Criteria andPayTypeIsNull() {
            addCriterion("PAY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("PAY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(String value) {
            addCriterion("PAY_TYPE =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(String value) {
            addCriterion("PAY_TYPE <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(String value) {
            addCriterion("PAY_TYPE >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_TYPE >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(String value) {
            addCriterion("PAY_TYPE <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(String value) {
            addCriterion("PAY_TYPE <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLike(String value) {
            addCriterion("PAY_TYPE like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotLike(String value) {
            addCriterion("PAY_TYPE not like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<String> values) {
            addCriterion("PAY_TYPE in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<String> values) {
            addCriterion("PAY_TYPE not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(String value1, String value2) {
            addCriterion("PAY_TYPE between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(String value1, String value2) {
            addCriterion("PAY_TYPE not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andMercCodeIsNull() {
            addCriterion("MERC_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMercCodeIsNotNull() {
            addCriterion("MERC_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMercCodeEqualTo(String value) {
            addCriterion("MERC_CODE =", value, "mercCode");
            return (Criteria) this;
        }

        public Criteria andMercCodeNotEqualTo(String value) {
            addCriterion("MERC_CODE <>", value, "mercCode");
            return (Criteria) this;
        }

        public Criteria andMercCodeGreaterThan(String value) {
            addCriterion("MERC_CODE >", value, "mercCode");
            return (Criteria) this;
        }

        public Criteria andMercCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MERC_CODE >=", value, "mercCode");
            return (Criteria) this;
        }

        public Criteria andMercCodeLessThan(String value) {
            addCriterion("MERC_CODE <", value, "mercCode");
            return (Criteria) this;
        }

        public Criteria andMercCodeLessThanOrEqualTo(String value) {
            addCriterion("MERC_CODE <=", value, "mercCode");
            return (Criteria) this;
        }

        public Criteria andMercCodeLike(String value) {
            addCriterion("MERC_CODE like", value, "mercCode");
            return (Criteria) this;
        }

        public Criteria andMercCodeNotLike(String value) {
            addCriterion("MERC_CODE not like", value, "mercCode");
            return (Criteria) this;
        }

        public Criteria andMercCodeIn(List<String> values) {
            addCriterion("MERC_CODE in", values, "mercCode");
            return (Criteria) this;
        }

        public Criteria andMercCodeNotIn(List<String> values) {
            addCriterion("MERC_CODE not in", values, "mercCode");
            return (Criteria) this;
        }

        public Criteria andMercCodeBetween(String value1, String value2) {
            addCriterion("MERC_CODE between", value1, value2, "mercCode");
            return (Criteria) this;
        }

        public Criteria andMercCodeNotBetween(String value1, String value2) {
            addCriterion("MERC_CODE not between", value1, value2, "mercCode");
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

        public Criteria andTransNoIsNull() {
            addCriterion("TRANS_NO is null");
            return (Criteria) this;
        }

        public Criteria andTransNoIsNotNull() {
            addCriterion("TRANS_NO is not null");
            return (Criteria) this;
        }

        public Criteria andTransNoEqualTo(String value) {
            addCriterion("TRANS_NO =", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoNotEqualTo(String value) {
            addCriterion("TRANS_NO <>", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoGreaterThan(String value) {
            addCriterion("TRANS_NO >", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_NO >=", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoLessThan(String value) {
            addCriterion("TRANS_NO <", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoLessThanOrEqualTo(String value) {
            addCriterion("TRANS_NO <=", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoLike(String value) {
            addCriterion("TRANS_NO like", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoNotLike(String value) {
            addCriterion("TRANS_NO not like", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoIn(List<String> values) {
            addCriterion("TRANS_NO in", values, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoNotIn(List<String> values) {
            addCriterion("TRANS_NO not in", values, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoBetween(String value1, String value2) {
            addCriterion("TRANS_NO between", value1, value2, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoNotBetween(String value1, String value2) {
            addCriterion("TRANS_NO not between", value1, value2, "transNo");
            return (Criteria) this;
        }

        public Criteria andBankCardIdIsNull() {
            addCriterion("BANK_CARD_ID is null");
            return (Criteria) this;
        }

        public Criteria andBankCardIdIsNotNull() {
            addCriterion("BANK_CARD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBankCardIdEqualTo(String value) {
            addCriterion("BANK_CARD_ID =", value, "bankCardId");
            return (Criteria) this;
        }

        public Criteria andBankCardIdNotEqualTo(String value) {
            addCriterion("BANK_CARD_ID <>", value, "bankCardId");
            return (Criteria) this;
        }

        public Criteria andBankCardIdGreaterThan(String value) {
            addCriterion("BANK_CARD_ID >", value, "bankCardId");
            return (Criteria) this;
        }

        public Criteria andBankCardIdGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_CARD_ID >=", value, "bankCardId");
            return (Criteria) this;
        }

        public Criteria andBankCardIdLessThan(String value) {
            addCriterion("BANK_CARD_ID <", value, "bankCardId");
            return (Criteria) this;
        }

        public Criteria andBankCardIdLessThanOrEqualTo(String value) {
            addCriterion("BANK_CARD_ID <=", value, "bankCardId");
            return (Criteria) this;
        }

        public Criteria andBankCardIdLike(String value) {
            addCriterion("BANK_CARD_ID like", value, "bankCardId");
            return (Criteria) this;
        }

        public Criteria andBankCardIdNotLike(String value) {
            addCriterion("BANK_CARD_ID not like", value, "bankCardId");
            return (Criteria) this;
        }

        public Criteria andBankCardIdIn(List<String> values) {
            addCriterion("BANK_CARD_ID in", values, "bankCardId");
            return (Criteria) this;
        }

        public Criteria andBankCardIdNotIn(List<String> values) {
            addCriterion("BANK_CARD_ID not in", values, "bankCardId");
            return (Criteria) this;
        }

        public Criteria andBankCardIdBetween(String value1, String value2) {
            addCriterion("BANK_CARD_ID between", value1, value2, "bankCardId");
            return (Criteria) this;
        }

        public Criteria andBankCardIdNotBetween(String value1, String value2) {
            addCriterion("BANK_CARD_ID not between", value1, value2, "bankCardId");
            return (Criteria) this;
        }

        public Criteria andBankCardNameIsNull() {
            addCriterion("BANK_CARD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBankCardNameIsNotNull() {
            addCriterion("BANK_CARD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBankCardNameEqualTo(String value) {
            addCriterion("BANK_CARD_NAME =", value, "bankCardName");
            return (Criteria) this;
        }

        public Criteria andBankCardNameNotEqualTo(String value) {
            addCriterion("BANK_CARD_NAME <>", value, "bankCardName");
            return (Criteria) this;
        }

        public Criteria andBankCardNameGreaterThan(String value) {
            addCriterion("BANK_CARD_NAME >", value, "bankCardName");
            return (Criteria) this;
        }

        public Criteria andBankCardNameGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_CARD_NAME >=", value, "bankCardName");
            return (Criteria) this;
        }

        public Criteria andBankCardNameLessThan(String value) {
            addCriterion("BANK_CARD_NAME <", value, "bankCardName");
            return (Criteria) this;
        }

        public Criteria andBankCardNameLessThanOrEqualTo(String value) {
            addCriterion("BANK_CARD_NAME <=", value, "bankCardName");
            return (Criteria) this;
        }

        public Criteria andBankCardNameLike(String value) {
            addCriterion("BANK_CARD_NAME like", value, "bankCardName");
            return (Criteria) this;
        }

        public Criteria andBankCardNameNotLike(String value) {
            addCriterion("BANK_CARD_NAME not like", value, "bankCardName");
            return (Criteria) this;
        }

        public Criteria andBankCardNameIn(List<String> values) {
            addCriterion("BANK_CARD_NAME in", values, "bankCardName");
            return (Criteria) this;
        }

        public Criteria andBankCardNameNotIn(List<String> values) {
            addCriterion("BANK_CARD_NAME not in", values, "bankCardName");
            return (Criteria) this;
        }

        public Criteria andBankCardNameBetween(String value1, String value2) {
            addCriterion("BANK_CARD_NAME between", value1, value2, "bankCardName");
            return (Criteria) this;
        }

        public Criteria andBankCardNameNotBetween(String value1, String value2) {
            addCriterion("BANK_CARD_NAME not between", value1, value2, "bankCardName");
            return (Criteria) this;
        }

        public Criteria andPayeerAccountIdIsNull() {
            addCriterion("PAYEER_ACCOUNT_ID is null");
            return (Criteria) this;
        }

        public Criteria andPayeerAccountIdIsNotNull() {
            addCriterion("PAYEER_ACCOUNT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPayeerAccountIdEqualTo(String value) {
            addCriterion("PAYEER_ACCOUNT_ID =", value, "payeerAccountId");
            return (Criteria) this;
        }

        public Criteria andPayeerAccountIdNotEqualTo(String value) {
            addCriterion("PAYEER_ACCOUNT_ID <>", value, "payeerAccountId");
            return (Criteria) this;
        }

        public Criteria andPayeerAccountIdGreaterThan(String value) {
            addCriterion("PAYEER_ACCOUNT_ID >", value, "payeerAccountId");
            return (Criteria) this;
        }

        public Criteria andPayeerAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("PAYEER_ACCOUNT_ID >=", value, "payeerAccountId");
            return (Criteria) this;
        }

        public Criteria andPayeerAccountIdLessThan(String value) {
            addCriterion("PAYEER_ACCOUNT_ID <", value, "payeerAccountId");
            return (Criteria) this;
        }

        public Criteria andPayeerAccountIdLessThanOrEqualTo(String value) {
            addCriterion("PAYEER_ACCOUNT_ID <=", value, "payeerAccountId");
            return (Criteria) this;
        }

        public Criteria andPayeerAccountIdLike(String value) {
            addCriterion("PAYEER_ACCOUNT_ID like", value, "payeerAccountId");
            return (Criteria) this;
        }

        public Criteria andPayeerAccountIdNotLike(String value) {
            addCriterion("PAYEER_ACCOUNT_ID not like", value, "payeerAccountId");
            return (Criteria) this;
        }

        public Criteria andPayeerAccountIdIn(List<String> values) {
            addCriterion("PAYEER_ACCOUNT_ID in", values, "payeerAccountId");
            return (Criteria) this;
        }

        public Criteria andPayeerAccountIdNotIn(List<String> values) {
            addCriterion("PAYEER_ACCOUNT_ID not in", values, "payeerAccountId");
            return (Criteria) this;
        }

        public Criteria andPayeerAccountIdBetween(String value1, String value2) {
            addCriterion("PAYEER_ACCOUNT_ID between", value1, value2, "payeerAccountId");
            return (Criteria) this;
        }

        public Criteria andPayeerAccountIdNotBetween(String value1, String value2) {
            addCriterion("PAYEER_ACCOUNT_ID not between", value1, value2, "payeerAccountId");
            return (Criteria) this;
        }

        public Criteria andTransTypeIsNull() {
            addCriterion("TRANS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTransTypeIsNotNull() {
            addCriterion("TRANS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTransTypeEqualTo(String value) {
            addCriterion("TRANS_TYPE =", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotEqualTo(String value) {
            addCriterion("TRANS_TYPE <>", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeGreaterThan(String value) {
            addCriterion("TRANS_TYPE >", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_TYPE >=", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeLessThan(String value) {
            addCriterion("TRANS_TYPE <", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeLessThanOrEqualTo(String value) {
            addCriterion("TRANS_TYPE <=", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeLike(String value) {
            addCriterion("TRANS_TYPE like", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotLike(String value) {
            addCriterion("TRANS_TYPE not like", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeIn(List<String> values) {
            addCriterion("TRANS_TYPE in", values, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotIn(List<String> values) {
            addCriterion("TRANS_TYPE not in", values, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeBetween(String value1, String value2) {
            addCriterion("TRANS_TYPE between", value1, value2, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotBetween(String value1, String value2) {
            addCriterion("TRANS_TYPE not between", value1, value2, "transType");
            return (Criteria) this;
        }

        public Criteria andTransAmountIsNull() {
            addCriterion("TRANS_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andTransAmountIsNotNull() {
            addCriterion("TRANS_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTransAmountEqualTo(String value) {
            addCriterion("TRANS_AMOUNT =", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountNotEqualTo(String value) {
            addCriterion("TRANS_AMOUNT <>", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountGreaterThan(String value) {
            addCriterion("TRANS_AMOUNT >", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountGreaterThanOrEqualTo(String value) {
            addCriterion("TRANS_AMOUNT >=", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountLessThan(String value) {
            addCriterion("TRANS_AMOUNT <", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountLessThanOrEqualTo(String value) {
            addCriterion("TRANS_AMOUNT <=", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountLike(String value) {
            addCriterion("TRANS_AMOUNT like", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountNotLike(String value) {
            addCriterion("TRANS_AMOUNT not like", value, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountIn(List<String> values) {
            addCriterion("TRANS_AMOUNT in", values, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountNotIn(List<String> values) {
            addCriterion("TRANS_AMOUNT not in", values, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountBetween(String value1, String value2) {
            addCriterion("TRANS_AMOUNT between", value1, value2, "transAmount");
            return (Criteria) this;
        }

        public Criteria andTransAmountNotBetween(String value1, String value2) {
            addCriterion("TRANS_AMOUNT not between", value1, value2, "transAmount");
            return (Criteria) this;
        }

        public Criteria andPayResultIsNull() {
            addCriterion("PAY_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andPayResultIsNotNull() {
            addCriterion("PAY_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andPayResultEqualTo(String value) {
            addCriterion("PAY_RESULT =", value, "payResult");
            return (Criteria) this;
        }

        public Criteria andPayResultNotEqualTo(String value) {
            addCriterion("PAY_RESULT <>", value, "payResult");
            return (Criteria) this;
        }

        public Criteria andPayResultGreaterThan(String value) {
            addCriterion("PAY_RESULT >", value, "payResult");
            return (Criteria) this;
        }

        public Criteria andPayResultGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_RESULT >=", value, "payResult");
            return (Criteria) this;
        }

        public Criteria andPayResultLessThan(String value) {
            addCriterion("PAY_RESULT <", value, "payResult");
            return (Criteria) this;
        }

        public Criteria andPayResultLessThanOrEqualTo(String value) {
            addCriterion("PAY_RESULT <=", value, "payResult");
            return (Criteria) this;
        }

        public Criteria andPayResultLike(String value) {
            addCriterion("PAY_RESULT like", value, "payResult");
            return (Criteria) this;
        }

        public Criteria andPayResultNotLike(String value) {
            addCriterion("PAY_RESULT not like", value, "payResult");
            return (Criteria) this;
        }

        public Criteria andPayResultIn(List<String> values) {
            addCriterion("PAY_RESULT in", values, "payResult");
            return (Criteria) this;
        }

        public Criteria andPayResultNotIn(List<String> values) {
            addCriterion("PAY_RESULT not in", values, "payResult");
            return (Criteria) this;
        }

        public Criteria andPayResultBetween(String value1, String value2) {
            addCriterion("PAY_RESULT between", value1, value2, "payResult");
            return (Criteria) this;
        }

        public Criteria andPayResultNotBetween(String value1, String value2) {
            addCriterion("PAY_RESULT not between", value1, value2, "payResult");
            return (Criteria) this;
        }

        public Criteria andTransTimeIsNull() {
            addCriterion("TRANS_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTransTimeIsNotNull() {
            addCriterion("TRANS_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTransTimeEqualTo(Timestamp value) {
            addCriterion("TRANS_TIME =", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeNotEqualTo(Timestamp value) {
            addCriterion("TRANS_TIME <>", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeGreaterThan(Timestamp value) {
            addCriterion("TRANS_TIME >", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("TRANS_TIME >=", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeLessThan(Timestamp value) {
            addCriterion("TRANS_TIME <", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("TRANS_TIME <=", value, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeIn(List<Timestamp> values) {
            addCriterion("TRANS_TIME in", values, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeNotIn(List<Timestamp> values) {
            addCriterion("TRANS_TIME not in", values, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("TRANS_TIME between", value1, value2, "transTime");
            return (Criteria) this;
        }

        public Criteria andTransTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("TRANS_TIME not between", value1, value2, "transTime");
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