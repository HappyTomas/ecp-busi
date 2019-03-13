package com.zengshi.ecp.staff.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AcctInfoTempCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public AcctInfoTempCriteria() {
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

        public Criteria andStaffIdIsNull() {
            addCriterion("STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNotNull() {
            addCriterion("STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStaffIdEqualTo(Long value) {
            addCriterion("STAFF_ID =", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotEqualTo(Long value) {
            addCriterion("STAFF_ID <>", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThan(Long value) {
            addCriterion("STAFF_ID >", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThanOrEqualTo(Long value) {
            addCriterion("STAFF_ID >=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThan(Long value) {
            addCriterion("STAFF_ID <", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThanOrEqualTo(Long value) {
            addCriterion("STAFF_ID <=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdIn(List<Long> values) {
            addCriterion("STAFF_ID in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotIn(List<Long> values) {
            addCriterion("STAFF_ID not in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdBetween(Long value1, Long value2) {
            addCriterion("STAFF_ID between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotBetween(Long value1, Long value2) {
            addCriterion("STAFF_ID not between", value1, value2, "staffId");
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

        public Criteria andAdaptTypeIsNull() {
            addCriterion("ADAPT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAdaptTypeIsNotNull() {
            addCriterion("ADAPT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAdaptTypeEqualTo(String value) {
            addCriterion("ADAPT_TYPE =", value, "adaptType");
            return (Criteria) this;
        }

        public Criteria andAdaptTypeNotEqualTo(String value) {
            addCriterion("ADAPT_TYPE <>", value, "adaptType");
            return (Criteria) this;
        }

        public Criteria andAdaptTypeGreaterThan(String value) {
            addCriterion("ADAPT_TYPE >", value, "adaptType");
            return (Criteria) this;
        }

        public Criteria andAdaptTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ADAPT_TYPE >=", value, "adaptType");
            return (Criteria) this;
        }

        public Criteria andAdaptTypeLessThan(String value) {
            addCriterion("ADAPT_TYPE <", value, "adaptType");
            return (Criteria) this;
        }

        public Criteria andAdaptTypeLessThanOrEqualTo(String value) {
            addCriterion("ADAPT_TYPE <=", value, "adaptType");
            return (Criteria) this;
        }

        public Criteria andAdaptTypeLike(String value) {
            addCriterion("ADAPT_TYPE like", value, "adaptType");
            return (Criteria) this;
        }

        public Criteria andAdaptTypeNotLike(String value) {
            addCriterion("ADAPT_TYPE not like", value, "adaptType");
            return (Criteria) this;
        }

        public Criteria andAdaptTypeIn(List<String> values) {
            addCriterion("ADAPT_TYPE in", values, "adaptType");
            return (Criteria) this;
        }

        public Criteria andAdaptTypeNotIn(List<String> values) {
            addCriterion("ADAPT_TYPE not in", values, "adaptType");
            return (Criteria) this;
        }

        public Criteria andAdaptTypeBetween(String value1, String value2) {
            addCriterion("ADAPT_TYPE between", value1, value2, "adaptType");
            return (Criteria) this;
        }

        public Criteria andAdaptTypeNotBetween(String value1, String value2) {
            addCriterion("ADAPT_TYPE not between", value1, value2, "adaptType");
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

        public Criteria andShopIdEqualTo(String value) {
            addCriterion("SHOP_ID =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(String value) {
            addCriterion("SHOP_ID <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(String value) {
            addCriterion("SHOP_ID >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_ID >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(String value) {
            addCriterion("SHOP_ID <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(String value) {
            addCriterion("SHOP_ID <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLike(String value) {
            addCriterion("SHOP_ID like", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotLike(String value) {
            addCriterion("SHOP_ID not like", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<String> values) {
            addCriterion("SHOP_ID in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<String> values) {
            addCriterion("SHOP_ID not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(String value1, String value2) {
            addCriterion("SHOP_ID between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(String value1, String value2) {
            addCriterion("SHOP_ID not between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andStaffCodeIsNull() {
            addCriterion("STAFF_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStaffCodeIsNotNull() {
            addCriterion("STAFF_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStaffCodeEqualTo(String value) {
            addCriterion("STAFF_CODE =", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotEqualTo(String value) {
            addCriterion("STAFF_CODE <>", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeGreaterThan(String value) {
            addCriterion("STAFF_CODE >", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_CODE >=", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeLessThan(String value) {
            addCriterion("STAFF_CODE <", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeLessThanOrEqualTo(String value) {
            addCriterion("STAFF_CODE <=", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeLike(String value) {
            addCriterion("STAFF_CODE like", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotLike(String value) {
            addCriterion("STAFF_CODE not like", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeIn(List<String> values) {
            addCriterion("STAFF_CODE in", values, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotIn(List<String> values) {
            addCriterion("STAFF_CODE not in", values, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeBetween(String value1, String value2) {
            addCriterion("STAFF_CODE between", value1, value2, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotBetween(String value1, String value2) {
            addCriterion("STAFF_CODE not between", value1, value2, "staffCode");
            return (Criteria) this;
        }

        public Criteria andTradeMoneyIsNull() {
            addCriterion("TRADE_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andTradeMoneyIsNotNull() {
            addCriterion("TRADE_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andTradeMoneyEqualTo(String value) {
            addCriterion("TRADE_MONEY =", value, "tradeMoney");
            return (Criteria) this;
        }

        public Criteria andTradeMoneyNotEqualTo(String value) {
            addCriterion("TRADE_MONEY <>", value, "tradeMoney");
            return (Criteria) this;
        }

        public Criteria andTradeMoneyGreaterThan(String value) {
            addCriterion("TRADE_MONEY >", value, "tradeMoney");
            return (Criteria) this;
        }

        public Criteria andTradeMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("TRADE_MONEY >=", value, "tradeMoney");
            return (Criteria) this;
        }

        public Criteria andTradeMoneyLessThan(String value) {
            addCriterion("TRADE_MONEY <", value, "tradeMoney");
            return (Criteria) this;
        }

        public Criteria andTradeMoneyLessThanOrEqualTo(String value) {
            addCriterion("TRADE_MONEY <=", value, "tradeMoney");
            return (Criteria) this;
        }

        public Criteria andTradeMoneyLike(String value) {
            addCriterion("TRADE_MONEY like", value, "tradeMoney");
            return (Criteria) this;
        }

        public Criteria andTradeMoneyNotLike(String value) {
            addCriterion("TRADE_MONEY not like", value, "tradeMoney");
            return (Criteria) this;
        }

        public Criteria andTradeMoneyIn(List<String> values) {
            addCriterion("TRADE_MONEY in", values, "tradeMoney");
            return (Criteria) this;
        }

        public Criteria andTradeMoneyNotIn(List<String> values) {
            addCriterion("TRADE_MONEY not in", values, "tradeMoney");
            return (Criteria) this;
        }

        public Criteria andTradeMoneyBetween(String value1, String value2) {
            addCriterion("TRADE_MONEY between", value1, value2, "tradeMoney");
            return (Criteria) this;
        }

        public Criteria andTradeMoneyNotBetween(String value1, String value2) {
            addCriterion("TRADE_MONEY not between", value1, value2, "tradeMoney");
            return (Criteria) this;
        }

        public Criteria andIsGoodIsNull() {
            addCriterion("IS_GOOD is null");
            return (Criteria) this;
        }

        public Criteria andIsGoodIsNotNull() {
            addCriterion("IS_GOOD is not null");
            return (Criteria) this;
        }

        public Criteria andIsGoodEqualTo(String value) {
            addCriterion("IS_GOOD =", value, "isGood");
            return (Criteria) this;
        }

        public Criteria andIsGoodNotEqualTo(String value) {
            addCriterion("IS_GOOD <>", value, "isGood");
            return (Criteria) this;
        }

        public Criteria andIsGoodGreaterThan(String value) {
            addCriterion("IS_GOOD >", value, "isGood");
            return (Criteria) this;
        }

        public Criteria andIsGoodGreaterThanOrEqualTo(String value) {
            addCriterion("IS_GOOD >=", value, "isGood");
            return (Criteria) this;
        }

        public Criteria andIsGoodLessThan(String value) {
            addCriterion("IS_GOOD <", value, "isGood");
            return (Criteria) this;
        }

        public Criteria andIsGoodLessThanOrEqualTo(String value) {
            addCriterion("IS_GOOD <=", value, "isGood");
            return (Criteria) this;
        }

        public Criteria andIsGoodLike(String value) {
            addCriterion("IS_GOOD like", value, "isGood");
            return (Criteria) this;
        }

        public Criteria andIsGoodNotLike(String value) {
            addCriterion("IS_GOOD not like", value, "isGood");
            return (Criteria) this;
        }

        public Criteria andIsGoodIn(List<String> values) {
            addCriterion("IS_GOOD in", values, "isGood");
            return (Criteria) this;
        }

        public Criteria andIsGoodNotIn(List<String> values) {
            addCriterion("IS_GOOD not in", values, "isGood");
            return (Criteria) this;
        }

        public Criteria andIsGoodBetween(String value1, String value2) {
            addCriterion("IS_GOOD between", value1, value2, "isGood");
            return (Criteria) this;
        }

        public Criteria andIsGoodNotBetween(String value1, String value2) {
            addCriterion("IS_GOOD not between", value1, value2, "isGood");
            return (Criteria) this;
        }

        public Criteria andIsCommitIsNull() {
            addCriterion("IS_COMMIT is null");
            return (Criteria) this;
        }

        public Criteria andIsCommitIsNotNull() {
            addCriterion("IS_COMMIT is not null");
            return (Criteria) this;
        }

        public Criteria andIsCommitEqualTo(String value) {
            addCriterion("IS_COMMIT =", value, "isCommit");
            return (Criteria) this;
        }

        public Criteria andIsCommitNotEqualTo(String value) {
            addCriterion("IS_COMMIT <>", value, "isCommit");
            return (Criteria) this;
        }

        public Criteria andIsCommitGreaterThan(String value) {
            addCriterion("IS_COMMIT >", value, "isCommit");
            return (Criteria) this;
        }

        public Criteria andIsCommitGreaterThanOrEqualTo(String value) {
            addCriterion("IS_COMMIT >=", value, "isCommit");
            return (Criteria) this;
        }

        public Criteria andIsCommitLessThan(String value) {
            addCriterion("IS_COMMIT <", value, "isCommit");
            return (Criteria) this;
        }

        public Criteria andIsCommitLessThanOrEqualTo(String value) {
            addCriterion("IS_COMMIT <=", value, "isCommit");
            return (Criteria) this;
        }

        public Criteria andIsCommitLike(String value) {
            addCriterion("IS_COMMIT like", value, "isCommit");
            return (Criteria) this;
        }

        public Criteria andIsCommitNotLike(String value) {
            addCriterion("IS_COMMIT not like", value, "isCommit");
            return (Criteria) this;
        }

        public Criteria andIsCommitIn(List<String> values) {
            addCriterion("IS_COMMIT in", values, "isCommit");
            return (Criteria) this;
        }

        public Criteria andIsCommitNotIn(List<String> values) {
            addCriterion("IS_COMMIT not in", values, "isCommit");
            return (Criteria) this;
        }

        public Criteria andIsCommitBetween(String value1, String value2) {
            addCriterion("IS_COMMIT between", value1, value2, "isCommit");
            return (Criteria) this;
        }

        public Criteria andIsCommitNotBetween(String value1, String value2) {
            addCriterion("IS_COMMIT not between", value1, value2, "isCommit");
            return (Criteria) this;
        }

        public Criteria andBadDataLocIsNull() {
            addCriterion("BAD_DATA_LOC is null");
            return (Criteria) this;
        }

        public Criteria andBadDataLocIsNotNull() {
            addCriterion("BAD_DATA_LOC is not null");
            return (Criteria) this;
        }

        public Criteria andBadDataLocEqualTo(String value) {
            addCriterion("BAD_DATA_LOC =", value, "badDataLoc");
            return (Criteria) this;
        }

        public Criteria andBadDataLocNotEqualTo(String value) {
            addCriterion("BAD_DATA_LOC <>", value, "badDataLoc");
            return (Criteria) this;
        }

        public Criteria andBadDataLocGreaterThan(String value) {
            addCriterion("BAD_DATA_LOC >", value, "badDataLoc");
            return (Criteria) this;
        }

        public Criteria andBadDataLocGreaterThanOrEqualTo(String value) {
            addCriterion("BAD_DATA_LOC >=", value, "badDataLoc");
            return (Criteria) this;
        }

        public Criteria andBadDataLocLessThan(String value) {
            addCriterion("BAD_DATA_LOC <", value, "badDataLoc");
            return (Criteria) this;
        }

        public Criteria andBadDataLocLessThanOrEqualTo(String value) {
            addCriterion("BAD_DATA_LOC <=", value, "badDataLoc");
            return (Criteria) this;
        }

        public Criteria andBadDataLocLike(String value) {
            addCriterion("BAD_DATA_LOC like", value, "badDataLoc");
            return (Criteria) this;
        }

        public Criteria andBadDataLocNotLike(String value) {
            addCriterion("BAD_DATA_LOC not like", value, "badDataLoc");
            return (Criteria) this;
        }

        public Criteria andBadDataLocIn(List<String> values) {
            addCriterion("BAD_DATA_LOC in", values, "badDataLoc");
            return (Criteria) this;
        }

        public Criteria andBadDataLocNotIn(List<String> values) {
            addCriterion("BAD_DATA_LOC not in", values, "badDataLoc");
            return (Criteria) this;
        }

        public Criteria andBadDataLocBetween(String value1, String value2) {
            addCriterion("BAD_DATA_LOC between", value1, value2, "badDataLoc");
            return (Criteria) this;
        }

        public Criteria andBadDataLocNotBetween(String value1, String value2) {
            addCriterion("BAD_DATA_LOC not between", value1, value2, "badDataLoc");
            return (Criteria) this;
        }

        public Criteria andRecordDescIsNull() {
            addCriterion("RECORD_DESC is null");
            return (Criteria) this;
        }

        public Criteria andRecordDescIsNotNull() {
            addCriterion("RECORD_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andRecordDescEqualTo(String value) {
            addCriterion("RECORD_DESC =", value, "recordDesc");
            return (Criteria) this;
        }

        public Criteria andRecordDescNotEqualTo(String value) {
            addCriterion("RECORD_DESC <>", value, "recordDesc");
            return (Criteria) this;
        }

        public Criteria andRecordDescGreaterThan(String value) {
            addCriterion("RECORD_DESC >", value, "recordDesc");
            return (Criteria) this;
        }

        public Criteria andRecordDescGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_DESC >=", value, "recordDesc");
            return (Criteria) this;
        }

        public Criteria andRecordDescLessThan(String value) {
            addCriterion("RECORD_DESC <", value, "recordDesc");
            return (Criteria) this;
        }

        public Criteria andRecordDescLessThanOrEqualTo(String value) {
            addCriterion("RECORD_DESC <=", value, "recordDesc");
            return (Criteria) this;
        }

        public Criteria andRecordDescLike(String value) {
            addCriterion("RECORD_DESC like", value, "recordDesc");
            return (Criteria) this;
        }

        public Criteria andRecordDescNotLike(String value) {
            addCriterion("RECORD_DESC not like", value, "recordDesc");
            return (Criteria) this;
        }

        public Criteria andRecordDescIn(List<String> values) {
            addCriterion("RECORD_DESC in", values, "recordDesc");
            return (Criteria) this;
        }

        public Criteria andRecordDescNotIn(List<String> values) {
            addCriterion("RECORD_DESC not in", values, "recordDesc");
            return (Criteria) this;
        }

        public Criteria andRecordDescBetween(String value1, String value2) {
            addCriterion("RECORD_DESC between", value1, value2, "recordDesc");
            return (Criteria) this;
        }

        public Criteria andRecordDescNotBetween(String value1, String value2) {
            addCriterion("RECORD_DESC not between", value1, value2, "recordDesc");
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