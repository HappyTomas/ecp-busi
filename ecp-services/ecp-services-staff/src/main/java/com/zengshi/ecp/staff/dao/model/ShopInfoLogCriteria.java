package com.zengshi.ecp.staff.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ShopInfoLogCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public ShopInfoLogCriteria() {
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

        public Criteria andCompanyIdIsNull() {
            addCriterion("COMPANY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("COMPANY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Long value) {
            addCriterion("COMPANY_ID =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Long value) {
            addCriterion("COMPANY_ID <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Long value) {
            addCriterion("COMPANY_ID >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("COMPANY_ID >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Long value) {
            addCriterion("COMPANY_ID <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Long value) {
            addCriterion("COMPANY_ID <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Long> values) {
            addCriterion("COMPANY_ID in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Long> values) {
            addCriterion("COMPANY_ID not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Long value1, Long value2) {
            addCriterion("COMPANY_ID between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Long value1, Long value2) {
            addCriterion("COMPANY_ID not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("SHOP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("SHOP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("SHOP_NAME =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("SHOP_NAME <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("SHOP_NAME >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_NAME >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("SHOP_NAME <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("SHOP_NAME <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("SHOP_NAME like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("SHOP_NAME not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("SHOP_NAME in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("SHOP_NAME not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("SHOP_NAME between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("SHOP_NAME not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameIsNull() {
            addCriterion("SHOP_FULL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andShopFullNameIsNotNull() {
            addCriterion("SHOP_FULL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andShopFullNameEqualTo(String value) {
            addCriterion("SHOP_FULL_NAME =", value, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameNotEqualTo(String value) {
            addCriterion("SHOP_FULL_NAME <>", value, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameGreaterThan(String value) {
            addCriterion("SHOP_FULL_NAME >", value, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_FULL_NAME >=", value, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameLessThan(String value) {
            addCriterion("SHOP_FULL_NAME <", value, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameLessThanOrEqualTo(String value) {
            addCriterion("SHOP_FULL_NAME <=", value, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameLike(String value) {
            addCriterion("SHOP_FULL_NAME like", value, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameNotLike(String value) {
            addCriterion("SHOP_FULL_NAME not like", value, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameIn(List<String> values) {
            addCriterion("SHOP_FULL_NAME in", values, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameNotIn(List<String> values) {
            addCriterion("SHOP_FULL_NAME not in", values, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameBetween(String value1, String value2) {
            addCriterion("SHOP_FULL_NAME between", value1, value2, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameNotBetween(String value1, String value2) {
            addCriterion("SHOP_FULL_NAME not between", value1, value2, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopGradeIsNull() {
            addCriterion("SHOP_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andShopGradeIsNotNull() {
            addCriterion("SHOP_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andShopGradeEqualTo(String value) {
            addCriterion("SHOP_GRADE =", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeNotEqualTo(String value) {
            addCriterion("SHOP_GRADE <>", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeGreaterThan(String value) {
            addCriterion("SHOP_GRADE >", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_GRADE >=", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeLessThan(String value) {
            addCriterion("SHOP_GRADE <", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeLessThanOrEqualTo(String value) {
            addCriterion("SHOP_GRADE <=", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeLike(String value) {
            addCriterion("SHOP_GRADE like", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeNotLike(String value) {
            addCriterion("SHOP_GRADE not like", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeIn(List<String> values) {
            addCriterion("SHOP_GRADE in", values, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeNotIn(List<String> values) {
            addCriterion("SHOP_GRADE not in", values, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeBetween(String value1, String value2) {
            addCriterion("SHOP_GRADE between", value1, value2, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeNotBetween(String value1, String value2) {
            addCriterion("SHOP_GRADE not between", value1, value2, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopDescIsNull() {
            addCriterion("SHOP_DESC is null");
            return (Criteria) this;
        }

        public Criteria andShopDescIsNotNull() {
            addCriterion("SHOP_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andShopDescEqualTo(String value) {
            addCriterion("SHOP_DESC =", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescNotEqualTo(String value) {
            addCriterion("SHOP_DESC <>", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescGreaterThan(String value) {
            addCriterion("SHOP_DESC >", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_DESC >=", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescLessThan(String value) {
            addCriterion("SHOP_DESC <", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescLessThanOrEqualTo(String value) {
            addCriterion("SHOP_DESC <=", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescLike(String value) {
            addCriterion("SHOP_DESC like", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescNotLike(String value) {
            addCriterion("SHOP_DESC not like", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescIn(List<String> values) {
            addCriterion("SHOP_DESC in", values, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescNotIn(List<String> values) {
            addCriterion("SHOP_DESC not in", values, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescBetween(String value1, String value2) {
            addCriterion("SHOP_DESC between", value1, value2, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescNotBetween(String value1, String value2) {
            addCriterion("SHOP_DESC not between", value1, value2, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopStatusIsNull() {
            addCriterion("SHOP_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andShopStatusIsNotNull() {
            addCriterion("SHOP_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andShopStatusEqualTo(String value) {
            addCriterion("SHOP_STATUS =", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusNotEqualTo(String value) {
            addCriterion("SHOP_STATUS <>", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusGreaterThan(String value) {
            addCriterion("SHOP_STATUS >", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_STATUS >=", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusLessThan(String value) {
            addCriterion("SHOP_STATUS <", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusLessThanOrEqualTo(String value) {
            addCriterion("SHOP_STATUS <=", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusLike(String value) {
            addCriterion("SHOP_STATUS like", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusNotLike(String value) {
            addCriterion("SHOP_STATUS not like", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusIn(List<String> values) {
            addCriterion("SHOP_STATUS in", values, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusNotIn(List<String> values) {
            addCriterion("SHOP_STATUS not in", values, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusBetween(String value1, String value2) {
            addCriterion("SHOP_STATUS between", value1, value2, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusNotBetween(String value1, String value2) {
            addCriterion("SHOP_STATUS not between", value1, value2, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeIsNull() {
            addCriterion("SHOP_DEAL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeIsNotNull() {
            addCriterion("SHOP_DEAL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeEqualTo(String value) {
            addCriterion("SHOP_DEAL_TYPE =", value, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeNotEqualTo(String value) {
            addCriterion("SHOP_DEAL_TYPE <>", value, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeGreaterThan(String value) {
            addCriterion("SHOP_DEAL_TYPE >", value, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_DEAL_TYPE >=", value, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeLessThan(String value) {
            addCriterion("SHOP_DEAL_TYPE <", value, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeLessThanOrEqualTo(String value) {
            addCriterion("SHOP_DEAL_TYPE <=", value, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeLike(String value) {
            addCriterion("SHOP_DEAL_TYPE like", value, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeNotLike(String value) {
            addCriterion("SHOP_DEAL_TYPE not like", value, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeIn(List<String> values) {
            addCriterion("SHOP_DEAL_TYPE in", values, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeNotIn(List<String> values) {
            addCriterion("SHOP_DEAL_TYPE not in", values, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeBetween(String value1, String value2) {
            addCriterion("SHOP_DEAL_TYPE between", value1, value2, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeNotBetween(String value1, String value2) {
            addCriterion("SHOP_DEAL_TYPE not between", value1, value2, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyIsNull() {
            addCriterion("CAUTION_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyIsNotNull() {
            addCriterion("CAUTION_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyEqualTo(Long value) {
            addCriterion("CAUTION_MONEY =", value, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyNotEqualTo(Long value) {
            addCriterion("CAUTION_MONEY <>", value, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyGreaterThan(Long value) {
            addCriterion("CAUTION_MONEY >", value, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("CAUTION_MONEY >=", value, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyLessThan(Long value) {
            addCriterion("CAUTION_MONEY <", value, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyLessThanOrEqualTo(Long value) {
            addCriterion("CAUTION_MONEY <=", value, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyIn(List<Long> values) {
            addCriterion("CAUTION_MONEY in", values, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyNotIn(List<Long> values) {
            addCriterion("CAUTION_MONEY not in", values, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyBetween(Long value1, Long value2) {
            addCriterion("CAUTION_MONEY between", value1, value2, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyNotBetween(Long value1, Long value2) {
            addCriterion("CAUTION_MONEY not between", value1, value2, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtIsNull() {
            addCriterion("MICRO_MESSAGE_EXT is null");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtIsNotNull() {
            addCriterion("MICRO_MESSAGE_EXT is not null");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtEqualTo(String value) {
            addCriterion("MICRO_MESSAGE_EXT =", value, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtNotEqualTo(String value) {
            addCriterion("MICRO_MESSAGE_EXT <>", value, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtGreaterThan(String value) {
            addCriterion("MICRO_MESSAGE_EXT >", value, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtGreaterThanOrEqualTo(String value) {
            addCriterion("MICRO_MESSAGE_EXT >=", value, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtLessThan(String value) {
            addCriterion("MICRO_MESSAGE_EXT <", value, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtLessThanOrEqualTo(String value) {
            addCriterion("MICRO_MESSAGE_EXT <=", value, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtLike(String value) {
            addCriterion("MICRO_MESSAGE_EXT like", value, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtNotLike(String value) {
            addCriterion("MICRO_MESSAGE_EXT not like", value, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtIn(List<String> values) {
            addCriterion("MICRO_MESSAGE_EXT in", values, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtNotIn(List<String> values) {
            addCriterion("MICRO_MESSAGE_EXT not in", values, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtBetween(String value1, String value2) {
            addCriterion("MICRO_MESSAGE_EXT between", value1, value2, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtNotBetween(String value1, String value2) {
            addCriterion("MICRO_MESSAGE_EXT not between", value1, value2, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedIsNull() {
            addCriterion("HOT_SHOW_SUPPORTED is null");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedIsNotNull() {
            addCriterion("HOT_SHOW_SUPPORTED is not null");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedEqualTo(String value) {
            addCriterion("HOT_SHOW_SUPPORTED =", value, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedNotEqualTo(String value) {
            addCriterion("HOT_SHOW_SUPPORTED <>", value, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedGreaterThan(String value) {
            addCriterion("HOT_SHOW_SUPPORTED >", value, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedGreaterThanOrEqualTo(String value) {
            addCriterion("HOT_SHOW_SUPPORTED >=", value, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedLessThan(String value) {
            addCriterion("HOT_SHOW_SUPPORTED <", value, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedLessThanOrEqualTo(String value) {
            addCriterion("HOT_SHOW_SUPPORTED <=", value, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedLike(String value) {
            addCriterion("HOT_SHOW_SUPPORTED like", value, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedNotLike(String value) {
            addCriterion("HOT_SHOW_SUPPORTED not like", value, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedIn(List<String> values) {
            addCriterion("HOT_SHOW_SUPPORTED in", values, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedNotIn(List<String> values) {
            addCriterion("HOT_SHOW_SUPPORTED not in", values, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedBetween(String value1, String value2) {
            addCriterion("HOT_SHOW_SUPPORTED between", value1, value2, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedNotBetween(String value1, String value2) {
            addCriterion("HOT_SHOW_SUPPORTED not between", value1, value2, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSortIsNull() {
            addCriterion("HOT_SHOW_SORT is null");
            return (Criteria) this;
        }

        public Criteria andHotShowSortIsNotNull() {
            addCriterion("HOT_SHOW_SORT is not null");
            return (Criteria) this;
        }

        public Criteria andHotShowSortEqualTo(Short value) {
            addCriterion("HOT_SHOW_SORT =", value, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortNotEqualTo(Short value) {
            addCriterion("HOT_SHOW_SORT <>", value, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortGreaterThan(Short value) {
            addCriterion("HOT_SHOW_SORT >", value, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortGreaterThanOrEqualTo(Short value) {
            addCriterion("HOT_SHOW_SORT >=", value, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortLessThan(Short value) {
            addCriterion("HOT_SHOW_SORT <", value, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortLessThanOrEqualTo(Short value) {
            addCriterion("HOT_SHOW_SORT <=", value, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortIn(List<Short> values) {
            addCriterion("HOT_SHOW_SORT in", values, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortNotIn(List<Short> values) {
            addCriterion("HOT_SHOW_SORT not in", values, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortBetween(Short value1, Short value2) {
            addCriterion("HOT_SHOW_SORT between", value1, value2, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortNotBetween(Short value1, Short value2) {
            addCriterion("HOT_SHOW_SORT not between", value1, value2, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andLogoPathIsNull() {
            addCriterion("LOGO_PATH is null");
            return (Criteria) this;
        }

        public Criteria andLogoPathIsNotNull() {
            addCriterion("LOGO_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andLogoPathEqualTo(String value) {
            addCriterion("LOGO_PATH =", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathNotEqualTo(String value) {
            addCriterion("LOGO_PATH <>", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathGreaterThan(String value) {
            addCriterion("LOGO_PATH >", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathGreaterThanOrEqualTo(String value) {
            addCriterion("LOGO_PATH >=", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathLessThan(String value) {
            addCriterion("LOGO_PATH <", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathLessThanOrEqualTo(String value) {
            addCriterion("LOGO_PATH <=", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathLike(String value) {
            addCriterion("LOGO_PATH like", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathNotLike(String value) {
            addCriterion("LOGO_PATH not like", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathIn(List<String> values) {
            addCriterion("LOGO_PATH in", values, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathNotIn(List<String> values) {
            addCriterion("LOGO_PATH not in", values, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathBetween(String value1, String value2) {
            addCriterion("LOGO_PATH between", value1, value2, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathNotBetween(String value1, String value2) {
            addCriterion("LOGO_PATH not between", value1, value2, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLinkPersonIsNull() {
            addCriterion("LINK_PERSON is null");
            return (Criteria) this;
        }

        public Criteria andLinkPersonIsNotNull() {
            addCriterion("LINK_PERSON is not null");
            return (Criteria) this;
        }

        public Criteria andLinkPersonEqualTo(String value) {
            addCriterion("LINK_PERSON =", value, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonNotEqualTo(String value) {
            addCriterion("LINK_PERSON <>", value, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonGreaterThan(String value) {
            addCriterion("LINK_PERSON >", value, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonGreaterThanOrEqualTo(String value) {
            addCriterion("LINK_PERSON >=", value, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonLessThan(String value) {
            addCriterion("LINK_PERSON <", value, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonLessThanOrEqualTo(String value) {
            addCriterion("LINK_PERSON <=", value, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonLike(String value) {
            addCriterion("LINK_PERSON like", value, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonNotLike(String value) {
            addCriterion("LINK_PERSON not like", value, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonIn(List<String> values) {
            addCriterion("LINK_PERSON in", values, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonNotIn(List<String> values) {
            addCriterion("LINK_PERSON not in", values, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonBetween(String value1, String value2) {
            addCriterion("LINK_PERSON between", value1, value2, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonNotBetween(String value1, String value2) {
            addCriterion("LINK_PERSON not between", value1, value2, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneIsNull() {
            addCriterion("LINK_MOBILEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneIsNotNull() {
            addCriterion("LINK_MOBILEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneEqualTo(String value) {
            addCriterion("LINK_MOBILEPHONE =", value, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneNotEqualTo(String value) {
            addCriterion("LINK_MOBILEPHONE <>", value, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneGreaterThan(String value) {
            addCriterion("LINK_MOBILEPHONE >", value, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneGreaterThanOrEqualTo(String value) {
            addCriterion("LINK_MOBILEPHONE >=", value, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneLessThan(String value) {
            addCriterion("LINK_MOBILEPHONE <", value, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneLessThanOrEqualTo(String value) {
            addCriterion("LINK_MOBILEPHONE <=", value, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneLike(String value) {
            addCriterion("LINK_MOBILEPHONE like", value, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneNotLike(String value) {
            addCriterion("LINK_MOBILEPHONE not like", value, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneIn(List<String> values) {
            addCriterion("LINK_MOBILEPHONE in", values, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneNotIn(List<String> values) {
            addCriterion("LINK_MOBILEPHONE not in", values, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneBetween(String value1, String value2) {
            addCriterion("LINK_MOBILEPHONE between", value1, value2, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneNotBetween(String value1, String value2) {
            addCriterion("LINK_MOBILEPHONE not between", value1, value2, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkEmailIsNull() {
            addCriterion("LINK_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andLinkEmailIsNotNull() {
            addCriterion("LINK_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andLinkEmailEqualTo(String value) {
            addCriterion("LINK_EMAIL =", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailNotEqualTo(String value) {
            addCriterion("LINK_EMAIL <>", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailGreaterThan(String value) {
            addCriterion("LINK_EMAIL >", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailGreaterThanOrEqualTo(String value) {
            addCriterion("LINK_EMAIL >=", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailLessThan(String value) {
            addCriterion("LINK_EMAIL <", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailLessThanOrEqualTo(String value) {
            addCriterion("LINK_EMAIL <=", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailLike(String value) {
            addCriterion("LINK_EMAIL like", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailNotLike(String value) {
            addCriterion("LINK_EMAIL not like", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailIn(List<String> values) {
            addCriterion("LINK_EMAIL in", values, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailNotIn(List<String> values) {
            addCriterion("LINK_EMAIL not in", values, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailBetween(String value1, String value2) {
            addCriterion("LINK_EMAIL between", value1, value2, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailNotBetween(String value1, String value2) {
            addCriterion("LINK_EMAIL not between", value1, value2, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andDistributionIsNull() {
            addCriterion("DISTRIBUTION is null");
            return (Criteria) this;
        }

        public Criteria andDistributionIsNotNull() {
            addCriterion("DISTRIBUTION is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionEqualTo(String value) {
            addCriterion("DISTRIBUTION =", value, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionNotEqualTo(String value) {
            addCriterion("DISTRIBUTION <>", value, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionGreaterThan(String value) {
            addCriterion("DISTRIBUTION >", value, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionGreaterThanOrEqualTo(String value) {
            addCriterion("DISTRIBUTION >=", value, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionLessThan(String value) {
            addCriterion("DISTRIBUTION <", value, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionLessThanOrEqualTo(String value) {
            addCriterion("DISTRIBUTION <=", value, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionLike(String value) {
            addCriterion("DISTRIBUTION like", value, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionNotLike(String value) {
            addCriterion("DISTRIBUTION not like", value, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionIn(List<String> values) {
            addCriterion("DISTRIBUTION in", values, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionNotIn(List<String> values) {
            addCriterion("DISTRIBUTION not in", values, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionBetween(String value1, String value2) {
            addCriterion("DISTRIBUTION between", value1, value2, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionNotBetween(String value1, String value2) {
            addCriterion("DISTRIBUTION not between", value1, value2, "distribution");
            return (Criteria) this;
        }

        public Criteria andIsUseVipIsNull() {
            addCriterion("IS_USE_VIP is null");
            return (Criteria) this;
        }

        public Criteria andIsUseVipIsNotNull() {
            addCriterion("IS_USE_VIP is not null");
            return (Criteria) this;
        }

        public Criteria andIsUseVipEqualTo(String value) {
            addCriterion("IS_USE_VIP =", value, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipNotEqualTo(String value) {
            addCriterion("IS_USE_VIP <>", value, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipGreaterThan(String value) {
            addCriterion("IS_USE_VIP >", value, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipGreaterThanOrEqualTo(String value) {
            addCriterion("IS_USE_VIP >=", value, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipLessThan(String value) {
            addCriterion("IS_USE_VIP <", value, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipLessThanOrEqualTo(String value) {
            addCriterion("IS_USE_VIP <=", value, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipLike(String value) {
            addCriterion("IS_USE_VIP like", value, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipNotLike(String value) {
            addCriterion("IS_USE_VIP not like", value, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipIn(List<String> values) {
            addCriterion("IS_USE_VIP in", values, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipNotIn(List<String> values) {
            addCriterion("IS_USE_VIP not in", values, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipBetween(String value1, String value2) {
            addCriterion("IS_USE_VIP between", value1, value2, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipNotBetween(String value1, String value2) {
            addCriterion("IS_USE_VIP not between", value1, value2, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedIsNull() {
            addCriterion("OFFLINE_SUPPORTED is null");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedIsNotNull() {
            addCriterion("OFFLINE_SUPPORTED is not null");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedEqualTo(String value) {
            addCriterion("OFFLINE_SUPPORTED =", value, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedNotEqualTo(String value) {
            addCriterion("OFFLINE_SUPPORTED <>", value, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedGreaterThan(String value) {
            addCriterion("OFFLINE_SUPPORTED >", value, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedGreaterThanOrEqualTo(String value) {
            addCriterion("OFFLINE_SUPPORTED >=", value, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedLessThan(String value) {
            addCriterion("OFFLINE_SUPPORTED <", value, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedLessThanOrEqualTo(String value) {
            addCriterion("OFFLINE_SUPPORTED <=", value, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedLike(String value) {
            addCriterion("OFFLINE_SUPPORTED like", value, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedNotLike(String value) {
            addCriterion("OFFLINE_SUPPORTED not like", value, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedIn(List<String> values) {
            addCriterion("OFFLINE_SUPPORTED in", values, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedNotIn(List<String> values) {
            addCriterion("OFFLINE_SUPPORTED not in", values, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedBetween(String value1, String value2) {
            addCriterion("OFFLINE_SUPPORTED between", value1, value2, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedNotBetween(String value1, String value2) {
            addCriterion("OFFLINE_SUPPORTED not between", value1, value2, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedIsNull() {
            addCriterion("ONLINE_SUPPORTED is null");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedIsNotNull() {
            addCriterion("ONLINE_SUPPORTED is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedEqualTo(String value) {
            addCriterion("ONLINE_SUPPORTED =", value, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedNotEqualTo(String value) {
            addCriterion("ONLINE_SUPPORTED <>", value, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedGreaterThan(String value) {
            addCriterion("ONLINE_SUPPORTED >", value, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedGreaterThanOrEqualTo(String value) {
            addCriterion("ONLINE_SUPPORTED >=", value, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedLessThan(String value) {
            addCriterion("ONLINE_SUPPORTED <", value, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedLessThanOrEqualTo(String value) {
            addCriterion("ONLINE_SUPPORTED <=", value, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedLike(String value) {
            addCriterion("ONLINE_SUPPORTED like", value, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedNotLike(String value) {
            addCriterion("ONLINE_SUPPORTED not like", value, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedIn(List<String> values) {
            addCriterion("ONLINE_SUPPORTED in", values, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedNotIn(List<String> values) {
            addCriterion("ONLINE_SUPPORTED not in", values, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedBetween(String value1, String value2) {
            addCriterion("ONLINE_SUPPORTED between", value1, value2, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedNotBetween(String value1, String value2) {
            addCriterion("ONLINE_SUPPORTED not between", value1, value2, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListIsNull() {
            addCriterion("IS_WHITE_LIST is null");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListIsNotNull() {
            addCriterion("IS_WHITE_LIST is not null");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListEqualTo(String value) {
            addCriterion("IS_WHITE_LIST =", value, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListNotEqualTo(String value) {
            addCriterion("IS_WHITE_LIST <>", value, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListGreaterThan(String value) {
            addCriterion("IS_WHITE_LIST >", value, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListGreaterThanOrEqualTo(String value) {
            addCriterion("IS_WHITE_LIST >=", value, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListLessThan(String value) {
            addCriterion("IS_WHITE_LIST <", value, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListLessThanOrEqualTo(String value) {
            addCriterion("IS_WHITE_LIST <=", value, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListLike(String value) {
            addCriterion("IS_WHITE_LIST like", value, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListNotLike(String value) {
            addCriterion("IS_WHITE_LIST not like", value, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListIn(List<String> values) {
            addCriterion("IS_WHITE_LIST in", values, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListNotIn(List<String> values) {
            addCriterion("IS_WHITE_LIST not in", values, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListBetween(String value1, String value2) {
            addCriterion("IS_WHITE_LIST between", value1, value2, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListNotBetween(String value1, String value2) {
            addCriterion("IS_WHITE_LIST not between", value1, value2, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListIsNull() {
            addCriterion("IS_BLACK_LIST is null");
            return (Criteria) this;
        }

        public Criteria andIsBlackListIsNotNull() {
            addCriterion("IS_BLACK_LIST is not null");
            return (Criteria) this;
        }

        public Criteria andIsBlackListEqualTo(String value) {
            addCriterion("IS_BLACK_LIST =", value, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListNotEqualTo(String value) {
            addCriterion("IS_BLACK_LIST <>", value, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListGreaterThan(String value) {
            addCriterion("IS_BLACK_LIST >", value, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListGreaterThanOrEqualTo(String value) {
            addCriterion("IS_BLACK_LIST >=", value, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListLessThan(String value) {
            addCriterion("IS_BLACK_LIST <", value, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListLessThanOrEqualTo(String value) {
            addCriterion("IS_BLACK_LIST <=", value, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListLike(String value) {
            addCriterion("IS_BLACK_LIST like", value, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListNotLike(String value) {
            addCriterion("IS_BLACK_LIST not like", value, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListIn(List<String> values) {
            addCriterion("IS_BLACK_LIST in", values, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListNotIn(List<String> values) {
            addCriterion("IS_BLACK_LIST not in", values, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListBetween(String value1, String value2) {
            addCriterion("IS_BLACK_LIST between", value1, value2, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListNotBetween(String value1, String value2) {
            addCriterion("IS_BLACK_LIST not between", value1, value2, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIsNull() {
            addCriterion("INVALID_DATE is null");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIsNotNull() {
            addCriterion("INVALID_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidDateEqualTo(Timestamp value) {
            addCriterion("INVALID_DATE =", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotEqualTo(Timestamp value) {
            addCriterion("INVALID_DATE <>", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateGreaterThan(Timestamp value) {
            addCriterion("INVALID_DATE >", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("INVALID_DATE >=", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateLessThan(Timestamp value) {
            addCriterion("INVALID_DATE <", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("INVALID_DATE <=", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIn(List<Timestamp> values) {
            addCriterion("INVALID_DATE in", values, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotIn(List<Timestamp> values) {
            addCriterion("INVALID_DATE not in", values, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INVALID_DATE between", value1, value2, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INVALID_DATE not between", value1, value2, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffIsNull() {
            addCriterion("INVALID_STAFF is null");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffIsNotNull() {
            addCriterion("INVALID_STAFF is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffEqualTo(String value) {
            addCriterion("INVALID_STAFF =", value, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffNotEqualTo(String value) {
            addCriterion("INVALID_STAFF <>", value, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffGreaterThan(String value) {
            addCriterion("INVALID_STAFF >", value, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffGreaterThanOrEqualTo(String value) {
            addCriterion("INVALID_STAFF >=", value, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffLessThan(String value) {
            addCriterion("INVALID_STAFF <", value, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffLessThanOrEqualTo(String value) {
            addCriterion("INVALID_STAFF <=", value, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffLike(String value) {
            addCriterion("INVALID_STAFF like", value, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffNotLike(String value) {
            addCriterion("INVALID_STAFF not like", value, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffIn(List<String> values) {
            addCriterion("INVALID_STAFF in", values, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffNotIn(List<String> values) {
            addCriterion("INVALID_STAFF not in", values, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffBetween(String value1, String value2) {
            addCriterion("INVALID_STAFF between", value1, value2, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffNotBetween(String value1, String value2) {
            addCriterion("INVALID_STAFF not between", value1, value2, "invalidStaff");
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