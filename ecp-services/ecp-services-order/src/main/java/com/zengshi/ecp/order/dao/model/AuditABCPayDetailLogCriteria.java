package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AuditABCPayDetailLogCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public AuditABCPayDetailLogCriteria() {
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

        public Criteria andMerchAcctIdIsNull() {
            addCriterion("MERCH_ACCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andMerchAcctIdIsNotNull() {
            addCriterion("MERCH_ACCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMerchAcctIdEqualTo(String value) {
            addCriterion("MERCH_ACCT_ID =", value, "merchAcctId");
            return (Criteria) this;
        }

        public Criteria andMerchAcctIdNotEqualTo(String value) {
            addCriterion("MERCH_ACCT_ID <>", value, "merchAcctId");
            return (Criteria) this;
        }

        public Criteria andMerchAcctIdGreaterThan(String value) {
            addCriterion("MERCH_ACCT_ID >", value, "merchAcctId");
            return (Criteria) this;
        }

        public Criteria andMerchAcctIdGreaterThanOrEqualTo(String value) {
            addCriterion("MERCH_ACCT_ID >=", value, "merchAcctId");
            return (Criteria) this;
        }

        public Criteria andMerchAcctIdLessThan(String value) {
            addCriterion("MERCH_ACCT_ID <", value, "merchAcctId");
            return (Criteria) this;
        }

        public Criteria andMerchAcctIdLessThanOrEqualTo(String value) {
            addCriterion("MERCH_ACCT_ID <=", value, "merchAcctId");
            return (Criteria) this;
        }

        public Criteria andMerchAcctIdLike(String value) {
            addCriterion("MERCH_ACCT_ID like", value, "merchAcctId");
            return (Criteria) this;
        }

        public Criteria andMerchAcctIdNotLike(String value) {
            addCriterion("MERCH_ACCT_ID not like", value, "merchAcctId");
            return (Criteria) this;
        }

        public Criteria andMerchAcctIdIn(List<String> values) {
            addCriterion("MERCH_ACCT_ID in", values, "merchAcctId");
            return (Criteria) this;
        }

        public Criteria andMerchAcctIdNotIn(List<String> values) {
            addCriterion("MERCH_ACCT_ID not in", values, "merchAcctId");
            return (Criteria) this;
        }

        public Criteria andMerchAcctIdBetween(String value1, String value2) {
            addCriterion("MERCH_ACCT_ID between", value1, value2, "merchAcctId");
            return (Criteria) this;
        }

        public Criteria andMerchAcctIdNotBetween(String value1, String value2) {
            addCriterion("MERCH_ACCT_ID not between", value1, value2, "merchAcctId");
            return (Criteria) this;
        }

        public Criteria andMerchAcctMoneyIsNull() {
            addCriterion("MERCH_ACCT_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andMerchAcctMoneyIsNotNull() {
            addCriterion("MERCH_ACCT_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andMerchAcctMoneyEqualTo(String value) {
            addCriterion("MERCH_ACCT_MONEY =", value, "merchAcctMoney");
            return (Criteria) this;
        }

        public Criteria andMerchAcctMoneyNotEqualTo(String value) {
            addCriterion("MERCH_ACCT_MONEY <>", value, "merchAcctMoney");
            return (Criteria) this;
        }

        public Criteria andMerchAcctMoneyGreaterThan(String value) {
            addCriterion("MERCH_ACCT_MONEY >", value, "merchAcctMoney");
            return (Criteria) this;
        }

        public Criteria andMerchAcctMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("MERCH_ACCT_MONEY >=", value, "merchAcctMoney");
            return (Criteria) this;
        }

        public Criteria andMerchAcctMoneyLessThan(String value) {
            addCriterion("MERCH_ACCT_MONEY <", value, "merchAcctMoney");
            return (Criteria) this;
        }

        public Criteria andMerchAcctMoneyLessThanOrEqualTo(String value) {
            addCriterion("MERCH_ACCT_MONEY <=", value, "merchAcctMoney");
            return (Criteria) this;
        }

        public Criteria andMerchAcctMoneyLike(String value) {
            addCriterion("MERCH_ACCT_MONEY like", value, "merchAcctMoney");
            return (Criteria) this;
        }

        public Criteria andMerchAcctMoneyNotLike(String value) {
            addCriterion("MERCH_ACCT_MONEY not like", value, "merchAcctMoney");
            return (Criteria) this;
        }

        public Criteria andMerchAcctMoneyIn(List<String> values) {
            addCriterion("MERCH_ACCT_MONEY in", values, "merchAcctMoney");
            return (Criteria) this;
        }

        public Criteria andMerchAcctMoneyNotIn(List<String> values) {
            addCriterion("MERCH_ACCT_MONEY not in", values, "merchAcctMoney");
            return (Criteria) this;
        }

        public Criteria andMerchAcctMoneyBetween(String value1, String value2) {
            addCriterion("MERCH_ACCT_MONEY between", value1, value2, "merchAcctMoney");
            return (Criteria) this;
        }

        public Criteria andMerchAcctMoneyNotBetween(String value1, String value2) {
            addCriterion("MERCH_ACCT_MONEY not between", value1, value2, "merchAcctMoney");
            return (Criteria) this;
        }

        public Criteria andPayeeAcctIsNull() {
            addCriterion("PAYEE_ACCT is null");
            return (Criteria) this;
        }

        public Criteria andPayeeAcctIsNotNull() {
            addCriterion("PAYEE_ACCT is not null");
            return (Criteria) this;
        }

        public Criteria andPayeeAcctEqualTo(String value) {
            addCriterion("PAYEE_ACCT =", value, "payeeAcct");
            return (Criteria) this;
        }

        public Criteria andPayeeAcctNotEqualTo(String value) {
            addCriterion("PAYEE_ACCT <>", value, "payeeAcct");
            return (Criteria) this;
        }

        public Criteria andPayeeAcctGreaterThan(String value) {
            addCriterion("PAYEE_ACCT >", value, "payeeAcct");
            return (Criteria) this;
        }

        public Criteria andPayeeAcctGreaterThanOrEqualTo(String value) {
            addCriterion("PAYEE_ACCT >=", value, "payeeAcct");
            return (Criteria) this;
        }

        public Criteria andPayeeAcctLessThan(String value) {
            addCriterion("PAYEE_ACCT <", value, "payeeAcct");
            return (Criteria) this;
        }

        public Criteria andPayeeAcctLessThanOrEqualTo(String value) {
            addCriterion("PAYEE_ACCT <=", value, "payeeAcct");
            return (Criteria) this;
        }

        public Criteria andPayeeAcctLike(String value) {
            addCriterion("PAYEE_ACCT like", value, "payeeAcct");
            return (Criteria) this;
        }

        public Criteria andPayeeAcctNotLike(String value) {
            addCriterion("PAYEE_ACCT not like", value, "payeeAcct");
            return (Criteria) this;
        }

        public Criteria andPayeeAcctIn(List<String> values) {
            addCriterion("PAYEE_ACCT in", values, "payeeAcct");
            return (Criteria) this;
        }

        public Criteria andPayeeAcctNotIn(List<String> values) {
            addCriterion("PAYEE_ACCT not in", values, "payeeAcct");
            return (Criteria) this;
        }

        public Criteria andPayeeAcctBetween(String value1, String value2) {
            addCriterion("PAYEE_ACCT between", value1, value2, "payeeAcct");
            return (Criteria) this;
        }

        public Criteria andPayeeAcctNotBetween(String value1, String value2) {
            addCriterion("PAYEE_ACCT not between", value1, value2, "payeeAcct");
            return (Criteria) this;
        }

        public Criteria andAcctTypeIsNull() {
            addCriterion("ACCT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAcctTypeIsNotNull() {
            addCriterion("ACCT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAcctTypeEqualTo(String value) {
            addCriterion("ACCT_TYPE =", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotEqualTo(String value) {
            addCriterion("ACCT_TYPE <>", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeGreaterThan(String value) {
            addCriterion("ACCT_TYPE >", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ACCT_TYPE >=", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeLessThan(String value) {
            addCriterion("ACCT_TYPE <", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeLessThanOrEqualTo(String value) {
            addCriterion("ACCT_TYPE <=", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeLike(String value) {
            addCriterion("ACCT_TYPE like", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotLike(String value) {
            addCriterion("ACCT_TYPE not like", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeIn(List<String> values) {
            addCriterion("ACCT_TYPE in", values, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotIn(List<String> values) {
            addCriterion("ACCT_TYPE not in", values, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeBetween(String value1, String value2) {
            addCriterion("ACCT_TYPE between", value1, value2, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotBetween(String value1, String value2) {
            addCriterion("ACCT_TYPE not between", value1, value2, "acctType");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("FEE is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("FEE is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(String value) {
            addCriterion("FEE =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(String value) {
            addCriterion("FEE <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(String value) {
            addCriterion("FEE >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(String value) {
            addCriterion("FEE >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(String value) {
            addCriterion("FEE <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(String value) {
            addCriterion("FEE <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLike(String value) {
            addCriterion("FEE like", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotLike(String value) {
            addCriterion("FEE not like", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<String> values) {
            addCriterion("FEE in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<String> values) {
            addCriterion("FEE not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(String value1, String value2) {
            addCriterion("FEE between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(String value1, String value2) {
            addCriterion("FEE not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andStagingFeeIsNull() {
            addCriterion("STAGING_FEE is null");
            return (Criteria) this;
        }

        public Criteria andStagingFeeIsNotNull() {
            addCriterion("STAGING_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andStagingFeeEqualTo(String value) {
            addCriterion("STAGING_FEE =", value, "stagingFee");
            return (Criteria) this;
        }

        public Criteria andStagingFeeNotEqualTo(String value) {
            addCriterion("STAGING_FEE <>", value, "stagingFee");
            return (Criteria) this;
        }

        public Criteria andStagingFeeGreaterThan(String value) {
            addCriterion("STAGING_FEE >", value, "stagingFee");
            return (Criteria) this;
        }

        public Criteria andStagingFeeGreaterThanOrEqualTo(String value) {
            addCriterion("STAGING_FEE >=", value, "stagingFee");
            return (Criteria) this;
        }

        public Criteria andStagingFeeLessThan(String value) {
            addCriterion("STAGING_FEE <", value, "stagingFee");
            return (Criteria) this;
        }

        public Criteria andStagingFeeLessThanOrEqualTo(String value) {
            addCriterion("STAGING_FEE <=", value, "stagingFee");
            return (Criteria) this;
        }

        public Criteria andStagingFeeLike(String value) {
            addCriterion("STAGING_FEE like", value, "stagingFee");
            return (Criteria) this;
        }

        public Criteria andStagingFeeNotLike(String value) {
            addCriterion("STAGING_FEE not like", value, "stagingFee");
            return (Criteria) this;
        }

        public Criteria andStagingFeeIn(List<String> values) {
            addCriterion("STAGING_FEE in", values, "stagingFee");
            return (Criteria) this;
        }

        public Criteria andStagingFeeNotIn(List<String> values) {
            addCriterion("STAGING_FEE not in", values, "stagingFee");
            return (Criteria) this;
        }

        public Criteria andStagingFeeBetween(String value1, String value2) {
            addCriterion("STAGING_FEE between", value1, value2, "stagingFee");
            return (Criteria) this;
        }

        public Criteria andStagingFeeNotBetween(String value1, String value2) {
            addCriterion("STAGING_FEE not between", value1, value2, "stagingFee");
            return (Criteria) this;
        }

        public Criteria andAccountingDateIsNull() {
            addCriterion("ACCOUNTING_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAccountingDateIsNotNull() {
            addCriterion("ACCOUNTING_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAccountingDateEqualTo(Timestamp value) {
            addCriterion("ACCOUNTING_DATE =", value, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateNotEqualTo(Timestamp value) {
            addCriterion("ACCOUNTING_DATE <>", value, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateGreaterThan(Timestamp value) {
            addCriterion("ACCOUNTING_DATE >", value, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("ACCOUNTING_DATE >=", value, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateLessThan(Timestamp value) {
            addCriterion("ACCOUNTING_DATE <", value, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("ACCOUNTING_DATE <=", value, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateIn(List<Timestamp> values) {
            addCriterion("ACCOUNTING_DATE in", values, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateNotIn(List<Timestamp> values) {
            addCriterion("ACCOUNTING_DATE not in", values, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ACCOUNTING_DATE between", value1, value2, "accountingDate");
            return (Criteria) this;
        }

        public Criteria andAccountingDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ACCOUNTING_DATE not between", value1, value2, "accountingDate");
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

        public Criteria andTransno9014IsNull() {
            addCriterion("TRANSNO9014 is null");
            return (Criteria) this;
        }

        public Criteria andTransno9014IsNotNull() {
            addCriterion("TRANSNO9014 is not null");
            return (Criteria) this;
        }

        public Criteria andTransno9014EqualTo(String value) {
            addCriterion("TRANSNO9014 =", value, "transno9014");
            return (Criteria) this;
        }

        public Criteria andTransno9014NotEqualTo(String value) {
            addCriterion("TRANSNO9014 <>", value, "transno9014");
            return (Criteria) this;
        }

        public Criteria andTransno9014GreaterThan(String value) {
            addCriterion("TRANSNO9014 >", value, "transno9014");
            return (Criteria) this;
        }

        public Criteria andTransno9014GreaterThanOrEqualTo(String value) {
            addCriterion("TRANSNO9014 >=", value, "transno9014");
            return (Criteria) this;
        }

        public Criteria andTransno9014LessThan(String value) {
            addCriterion("TRANSNO9014 <", value, "transno9014");
            return (Criteria) this;
        }

        public Criteria andTransno9014LessThanOrEqualTo(String value) {
            addCriterion("TRANSNO9014 <=", value, "transno9014");
            return (Criteria) this;
        }

        public Criteria andTransno9014Like(String value) {
            addCriterion("TRANSNO9014 like", value, "transno9014");
            return (Criteria) this;
        }

        public Criteria andTransno9014NotLike(String value) {
            addCriterion("TRANSNO9014 not like", value, "transno9014");
            return (Criteria) this;
        }

        public Criteria andTransno9014In(List<String> values) {
            addCriterion("TRANSNO9014 in", values, "transno9014");
            return (Criteria) this;
        }

        public Criteria andTransno9014NotIn(List<String> values) {
            addCriterion("TRANSNO9014 not in", values, "transno9014");
            return (Criteria) this;
        }

        public Criteria andTransno9014Between(String value1, String value2) {
            addCriterion("TRANSNO9014 between", value1, value2, "transno9014");
            return (Criteria) this;
        }

        public Criteria andTransno9014NotBetween(String value1, String value2) {
            addCriterion("TRANSNO9014 not between", value1, value2, "transno9014");
            return (Criteria) this;
        }

        public Criteria andOldOrderIdIsNull() {
            addCriterion("OLD_ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOldOrderIdIsNotNull() {
            addCriterion("OLD_ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOldOrderIdEqualTo(String value) {
            addCriterion("OLD_ORDER_ID =", value, "oldOrderId");
            return (Criteria) this;
        }

        public Criteria andOldOrderIdNotEqualTo(String value) {
            addCriterion("OLD_ORDER_ID <>", value, "oldOrderId");
            return (Criteria) this;
        }

        public Criteria andOldOrderIdGreaterThan(String value) {
            addCriterion("OLD_ORDER_ID >", value, "oldOrderId");
            return (Criteria) this;
        }

        public Criteria andOldOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("OLD_ORDER_ID >=", value, "oldOrderId");
            return (Criteria) this;
        }

        public Criteria andOldOrderIdLessThan(String value) {
            addCriterion("OLD_ORDER_ID <", value, "oldOrderId");
            return (Criteria) this;
        }

        public Criteria andOldOrderIdLessThanOrEqualTo(String value) {
            addCriterion("OLD_ORDER_ID <=", value, "oldOrderId");
            return (Criteria) this;
        }

        public Criteria andOldOrderIdLike(String value) {
            addCriterion("OLD_ORDER_ID like", value, "oldOrderId");
            return (Criteria) this;
        }

        public Criteria andOldOrderIdNotLike(String value) {
            addCriterion("OLD_ORDER_ID not like", value, "oldOrderId");
            return (Criteria) this;
        }

        public Criteria andOldOrderIdIn(List<String> values) {
            addCriterion("OLD_ORDER_ID in", values, "oldOrderId");
            return (Criteria) this;
        }

        public Criteria andOldOrderIdNotIn(List<String> values) {
            addCriterion("OLD_ORDER_ID not in", values, "oldOrderId");
            return (Criteria) this;
        }

        public Criteria andOldOrderIdBetween(String value1, String value2) {
            addCriterion("OLD_ORDER_ID between", value1, value2, "oldOrderId");
            return (Criteria) this;
        }

        public Criteria andOldOrderIdNotBetween(String value1, String value2) {
            addCriterion("OLD_ORDER_ID not between", value1, value2, "oldOrderId");
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