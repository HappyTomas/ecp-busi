package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GdsCategoryViewCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsCategoryViewCriteria() {
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

        public Criteria andCatgCodeIsNull() {
            addCriterion("CATG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCatgCodeIsNotNull() {
            addCriterion("CATG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCatgCodeEqualTo(String value) {
            addCriterion("CATG_CODE =", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeNotEqualTo(String value) {
            addCriterion("CATG_CODE <>", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeGreaterThan(String value) {
            addCriterion("CATG_CODE >", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CATG_CODE >=", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeLessThan(String value) {
            addCriterion("CATG_CODE <", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeLessThanOrEqualTo(String value) {
            addCriterion("CATG_CODE <=", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeLike(String value) {
            addCriterion("CATG_CODE like", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeNotLike(String value) {
            addCriterion("CATG_CODE not like", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeIn(List<String> values) {
            addCriterion("CATG_CODE in", values, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeNotIn(List<String> values) {
            addCriterion("CATG_CODE not in", values, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeBetween(String value1, String value2) {
            addCriterion("CATG_CODE between", value1, value2, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeNotBetween(String value1, String value2) {
            addCriterion("CATG_CODE not between", value1, value2, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgNameIsNull() {
            addCriterion("CATG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCatgNameIsNotNull() {
            addCriterion("CATG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCatgNameEqualTo(String value) {
            addCriterion("CATG_NAME =", value, "catgName");
            return (Criteria) this;
        }

        public Criteria andCatgNameNotEqualTo(String value) {
            addCriterion("CATG_NAME <>", value, "catgName");
            return (Criteria) this;
        }

        public Criteria andCatgNameGreaterThan(String value) {
            addCriterion("CATG_NAME >", value, "catgName");
            return (Criteria) this;
        }

        public Criteria andCatgNameGreaterThanOrEqualTo(String value) {
            addCriterion("CATG_NAME >=", value, "catgName");
            return (Criteria) this;
        }

        public Criteria andCatgNameLessThan(String value) {
            addCriterion("CATG_NAME <", value, "catgName");
            return (Criteria) this;
        }

        public Criteria andCatgNameLessThanOrEqualTo(String value) {
            addCriterion("CATG_NAME <=", value, "catgName");
            return (Criteria) this;
        }

        public Criteria andCatgNameLike(String value) {
            addCriterion("CATG_NAME like", value, "catgName");
            return (Criteria) this;
        }

        public Criteria andCatgNameNotLike(String value) {
            addCriterion("CATG_NAME not like", value, "catgName");
            return (Criteria) this;
        }

        public Criteria andCatgNameIn(List<String> values) {
            addCriterion("CATG_NAME in", values, "catgName");
            return (Criteria) this;
        }

        public Criteria andCatgNameNotIn(List<String> values) {
            addCriterion("CATG_NAME not in", values, "catgName");
            return (Criteria) this;
        }

        public Criteria andCatgNameBetween(String value1, String value2) {
            addCriterion("CATG_NAME between", value1, value2, "catgName");
            return (Criteria) this;
        }

        public Criteria andCatgNameNotBetween(String value1, String value2) {
            addCriterion("CATG_NAME not between", value1, value2, "catgName");
            return (Criteria) this;
        }

        public Criteria andCatgLevelIsNull() {
            addCriterion("CATG_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andCatgLevelIsNotNull() {
            addCriterion("CATG_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andCatgLevelEqualTo(Short value) {
            addCriterion("CATG_LEVEL =", value, "catgLevel");
            return (Criteria) this;
        }

        public Criteria andCatgLevelNotEqualTo(Short value) {
            addCriterion("CATG_LEVEL <>", value, "catgLevel");
            return (Criteria) this;
        }

        public Criteria andCatgLevelGreaterThan(Short value) {
            addCriterion("CATG_LEVEL >", value, "catgLevel");
            return (Criteria) this;
        }

        public Criteria andCatgLevelGreaterThanOrEqualTo(Short value) {
            addCriterion("CATG_LEVEL >=", value, "catgLevel");
            return (Criteria) this;
        }

        public Criteria andCatgLevelLessThan(Short value) {
            addCriterion("CATG_LEVEL <", value, "catgLevel");
            return (Criteria) this;
        }

        public Criteria andCatgLevelLessThanOrEqualTo(Short value) {
            addCriterion("CATG_LEVEL <=", value, "catgLevel");
            return (Criteria) this;
        }

        public Criteria andCatgLevelIn(List<Short> values) {
            addCriterion("CATG_LEVEL in", values, "catgLevel");
            return (Criteria) this;
        }

        public Criteria andCatgLevelNotIn(List<Short> values) {
            addCriterion("CATG_LEVEL not in", values, "catgLevel");
            return (Criteria) this;
        }

        public Criteria andCatgLevelBetween(Short value1, Short value2) {
            addCriterion("CATG_LEVEL between", value1, value2, "catgLevel");
            return (Criteria) this;
        }

        public Criteria andCatgLevelNotBetween(Short value1, Short value2) {
            addCriterion("CATG_LEVEL not between", value1, value2, "catgLevel");
            return (Criteria) this;
        }

        public Criteria andCatgTypeIsNull() {
            addCriterion("CATG_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCatgTypeIsNotNull() {
            addCriterion("CATG_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCatgTypeEqualTo(String value) {
            addCriterion("CATG_TYPE =", value, "catgType");
            return (Criteria) this;
        }

        public Criteria andCatgTypeNotEqualTo(String value) {
            addCriterion("CATG_TYPE <>", value, "catgType");
            return (Criteria) this;
        }

        public Criteria andCatgTypeGreaterThan(String value) {
            addCriterion("CATG_TYPE >", value, "catgType");
            return (Criteria) this;
        }

        public Criteria andCatgTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CATG_TYPE >=", value, "catgType");
            return (Criteria) this;
        }

        public Criteria andCatgTypeLessThan(String value) {
            addCriterion("CATG_TYPE <", value, "catgType");
            return (Criteria) this;
        }

        public Criteria andCatgTypeLessThanOrEqualTo(String value) {
            addCriterion("CATG_TYPE <=", value, "catgType");
            return (Criteria) this;
        }

        public Criteria andCatgTypeLike(String value) {
            addCriterion("CATG_TYPE like", value, "catgType");
            return (Criteria) this;
        }

        public Criteria andCatgTypeNotLike(String value) {
            addCriterion("CATG_TYPE not like", value, "catgType");
            return (Criteria) this;
        }

        public Criteria andCatgTypeIn(List<String> values) {
            addCriterion("CATG_TYPE in", values, "catgType");
            return (Criteria) this;
        }

        public Criteria andCatgTypeNotIn(List<String> values) {
            addCriterion("CATG_TYPE not in", values, "catgType");
            return (Criteria) this;
        }

        public Criteria andCatgTypeBetween(String value1, String value2) {
            addCriterion("CATG_TYPE between", value1, value2, "catgType");
            return (Criteria) this;
        }

        public Criteria andCatgTypeNotBetween(String value1, String value2) {
            addCriterion("CATG_TYPE not between", value1, value2, "catgType");
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

        public Criteria andSortNoEqualTo(Integer value) {
            addCriterion("SORT_NO =", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotEqualTo(Integer value) {
            addCriterion("SORT_NO <>", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThan(Integer value) {
            addCriterion("SORT_NO >", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("SORT_NO >=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThan(Integer value) {
            addCriterion("SORT_NO <", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThanOrEqualTo(Integer value) {
            addCriterion("SORT_NO <=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoIn(List<Integer> values) {
            addCriterion("SORT_NO in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotIn(List<Integer> values) {
            addCriterion("SORT_NO not in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoBetween(Integer value1, Integer value2) {
            addCriterion("SORT_NO between", value1, value2, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotBetween(Integer value1, Integer value2) {
            addCriterion("SORT_NO not between", value1, value2, "sortNo");
            return (Criteria) this;
        }

        public Criteria andCatgParentIsNull() {
            addCriterion("CATG_PARENT is null");
            return (Criteria) this;
        }

        public Criteria andCatgParentIsNotNull() {
            addCriterion("CATG_PARENT is not null");
            return (Criteria) this;
        }

        public Criteria andCatgParentEqualTo(String value) {
            addCriterion("CATG_PARENT =", value, "catgParent");
            return (Criteria) this;
        }

        public Criteria andCatgParentNotEqualTo(String value) {
            addCriterion("CATG_PARENT <>", value, "catgParent");
            return (Criteria) this;
        }

        public Criteria andCatgParentGreaterThan(String value) {
            addCriterion("CATG_PARENT >", value, "catgParent");
            return (Criteria) this;
        }

        public Criteria andCatgParentGreaterThanOrEqualTo(String value) {
            addCriterion("CATG_PARENT >=", value, "catgParent");
            return (Criteria) this;
        }

        public Criteria andCatgParentLessThan(String value) {
            addCriterion("CATG_PARENT <", value, "catgParent");
            return (Criteria) this;
        }

        public Criteria andCatgParentLessThanOrEqualTo(String value) {
            addCriterion("CATG_PARENT <=", value, "catgParent");
            return (Criteria) this;
        }

        public Criteria andCatgParentLike(String value) {
            addCriterion("CATG_PARENT like", value, "catgParent");
            return (Criteria) this;
        }

        public Criteria andCatgParentNotLike(String value) {
            addCriterion("CATG_PARENT not like", value, "catgParent");
            return (Criteria) this;
        }

        public Criteria andCatgParentIn(List<String> values) {
            addCriterion("CATG_PARENT in", values, "catgParent");
            return (Criteria) this;
        }

        public Criteria andCatgParentNotIn(List<String> values) {
            addCriterion("CATG_PARENT not in", values, "catgParent");
            return (Criteria) this;
        }

        public Criteria andCatgParentBetween(String value1, String value2) {
            addCriterion("CATG_PARENT between", value1, value2, "catgParent");
            return (Criteria) this;
        }

        public Criteria andCatgParentNotBetween(String value1, String value2) {
            addCriterion("CATG_PARENT not between", value1, value2, "catgParent");
            return (Criteria) this;
        }

        public Criteria andCatlogIdIsNull() {
            addCriterion("CATLOG_ID is null");
            return (Criteria) this;
        }

        public Criteria andCatlogIdIsNotNull() {
            addCriterion("CATLOG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCatlogIdEqualTo(Long value) {
            addCriterion("CATLOG_ID =", value, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdNotEqualTo(Long value) {
            addCriterion("CATLOG_ID <>", value, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdGreaterThan(Long value) {
            addCriterion("CATLOG_ID >", value, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CATLOG_ID >=", value, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdLessThan(Long value) {
            addCriterion("CATLOG_ID <", value, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdLessThanOrEqualTo(Long value) {
            addCriterion("CATLOG_ID <=", value, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdIn(List<Long> values) {
            addCriterion("CATLOG_ID in", values, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdNotIn(List<Long> values) {
            addCriterion("CATLOG_ID not in", values, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdBetween(Long value1, Long value2) {
            addCriterion("CATLOG_ID between", value1, value2, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdNotBetween(Long value1, Long value2) {
            addCriterion("CATLOG_ID not between", value1, value2, "catlogId");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeIsNull() {
            addCriterion("IF_ENTITY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeIsNotNull() {
            addCriterion("IF_ENTITY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeEqualTo(String value) {
            addCriterion("IF_ENTITY_CODE =", value, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeNotEqualTo(String value) {
            addCriterion("IF_ENTITY_CODE <>", value, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeGreaterThan(String value) {
            addCriterion("IF_ENTITY_CODE >", value, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("IF_ENTITY_CODE >=", value, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeLessThan(String value) {
            addCriterion("IF_ENTITY_CODE <", value, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeLessThanOrEqualTo(String value) {
            addCriterion("IF_ENTITY_CODE <=", value, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeLike(String value) {
            addCriterion("IF_ENTITY_CODE like", value, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeNotLike(String value) {
            addCriterion("IF_ENTITY_CODE not like", value, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeIn(List<String> values) {
            addCriterion("IF_ENTITY_CODE in", values, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeNotIn(List<String> values) {
            addCriterion("IF_ENTITY_CODE not in", values, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeBetween(String value1, String value2) {
            addCriterion("IF_ENTITY_CODE between", value1, value2, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeNotBetween(String value1, String value2) {
            addCriterion("IF_ENTITY_CODE not between", value1, value2, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfLeafIsNull() {
            addCriterion("IF_LEAF is null");
            return (Criteria) this;
        }

        public Criteria andIfLeafIsNotNull() {
            addCriterion("IF_LEAF is not null");
            return (Criteria) this;
        }

        public Criteria andIfLeafEqualTo(String value) {
            addCriterion("IF_LEAF =", value, "ifLeaf");
            return (Criteria) this;
        }

        public Criteria andIfLeafNotEqualTo(String value) {
            addCriterion("IF_LEAF <>", value, "ifLeaf");
            return (Criteria) this;
        }

        public Criteria andIfLeafGreaterThan(String value) {
            addCriterion("IF_LEAF >", value, "ifLeaf");
            return (Criteria) this;
        }

        public Criteria andIfLeafGreaterThanOrEqualTo(String value) {
            addCriterion("IF_LEAF >=", value, "ifLeaf");
            return (Criteria) this;
        }

        public Criteria andIfLeafLessThan(String value) {
            addCriterion("IF_LEAF <", value, "ifLeaf");
            return (Criteria) this;
        }

        public Criteria andIfLeafLessThanOrEqualTo(String value) {
            addCriterion("IF_LEAF <=", value, "ifLeaf");
            return (Criteria) this;
        }

        public Criteria andIfLeafLike(String value) {
            addCriterion("IF_LEAF like", value, "ifLeaf");
            return (Criteria) this;
        }

        public Criteria andIfLeafNotLike(String value) {
            addCriterion("IF_LEAF not like", value, "ifLeaf");
            return (Criteria) this;
        }

        public Criteria andIfLeafIn(List<String> values) {
            addCriterion("IF_LEAF in", values, "ifLeaf");
            return (Criteria) this;
        }

        public Criteria andIfLeafNotIn(List<String> values) {
            addCriterion("IF_LEAF not in", values, "ifLeaf");
            return (Criteria) this;
        }

        public Criteria andIfLeafBetween(String value1, String value2) {
            addCriterion("IF_LEAF between", value1, value2, "ifLeaf");
            return (Criteria) this;
        }

        public Criteria andIfLeafNotBetween(String value1, String value2) {
            addCriterion("IF_LEAF not between", value1, value2, "ifLeaf");
            return (Criteria) this;
        }

        public Criteria andCatgUrlIsNull() {
            addCriterion("CATG_URL is null");
            return (Criteria) this;
        }

        public Criteria andCatgUrlIsNotNull() {
            addCriterion("CATG_URL is not null");
            return (Criteria) this;
        }

        public Criteria andCatgUrlEqualTo(String value) {
            addCriterion("CATG_URL =", value, "catgUrl");
            return (Criteria) this;
        }

        public Criteria andCatgUrlNotEqualTo(String value) {
            addCriterion("CATG_URL <>", value, "catgUrl");
            return (Criteria) this;
        }

        public Criteria andCatgUrlGreaterThan(String value) {
            addCriterion("CATG_URL >", value, "catgUrl");
            return (Criteria) this;
        }

        public Criteria andCatgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("CATG_URL >=", value, "catgUrl");
            return (Criteria) this;
        }

        public Criteria andCatgUrlLessThan(String value) {
            addCriterion("CATG_URL <", value, "catgUrl");
            return (Criteria) this;
        }

        public Criteria andCatgUrlLessThanOrEqualTo(String value) {
            addCriterion("CATG_URL <=", value, "catgUrl");
            return (Criteria) this;
        }

        public Criteria andCatgUrlLike(String value) {
            addCriterion("CATG_URL like", value, "catgUrl");
            return (Criteria) this;
        }

        public Criteria andCatgUrlNotLike(String value) {
            addCriterion("CATG_URL not like", value, "catgUrl");
            return (Criteria) this;
        }

        public Criteria andCatgUrlIn(List<String> values) {
            addCriterion("CATG_URL in", values, "catgUrl");
            return (Criteria) this;
        }

        public Criteria andCatgUrlNotIn(List<String> values) {
            addCriterion("CATG_URL not in", values, "catgUrl");
            return (Criteria) this;
        }

        public Criteria andCatgUrlBetween(String value1, String value2) {
            addCriterion("CATG_URL between", value1, value2, "catgUrl");
            return (Criteria) this;
        }

        public Criteria andCatgUrlNotBetween(String value1, String value2) {
            addCriterion("CATG_URL not between", value1, value2, "catgUrl");
            return (Criteria) this;
        }

        public Criteria andCatgTypeUnitIsNull() {
            addCriterion("CATG_TYPE_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andCatgTypeUnitIsNotNull() {
            addCriterion("CATG_TYPE_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andCatgTypeUnitEqualTo(Long value) {
            addCriterion("CATG_TYPE_UNIT =", value, "catgTypeUnit");
            return (Criteria) this;
        }

        public Criteria andCatgTypeUnitNotEqualTo(Long value) {
            addCriterion("CATG_TYPE_UNIT <>", value, "catgTypeUnit");
            return (Criteria) this;
        }

        public Criteria andCatgTypeUnitGreaterThan(Long value) {
            addCriterion("CATG_TYPE_UNIT >", value, "catgTypeUnit");
            return (Criteria) this;
        }

        public Criteria andCatgTypeUnitGreaterThanOrEqualTo(Long value) {
            addCriterion("CATG_TYPE_UNIT >=", value, "catgTypeUnit");
            return (Criteria) this;
        }

        public Criteria andCatgTypeUnitLessThan(Long value) {
            addCriterion("CATG_TYPE_UNIT <", value, "catgTypeUnit");
            return (Criteria) this;
        }

        public Criteria andCatgTypeUnitLessThanOrEqualTo(Long value) {
            addCriterion("CATG_TYPE_UNIT <=", value, "catgTypeUnit");
            return (Criteria) this;
        }

        public Criteria andCatgTypeUnitIn(List<Long> values) {
            addCriterion("CATG_TYPE_UNIT in", values, "catgTypeUnit");
            return (Criteria) this;
        }

        public Criteria andCatgTypeUnitNotIn(List<Long> values) {
            addCriterion("CATG_TYPE_UNIT not in", values, "catgTypeUnit");
            return (Criteria) this;
        }

        public Criteria andCatgTypeUnitBetween(Long value1, Long value2) {
            addCriterion("CATG_TYPE_UNIT between", value1, value2, "catgTypeUnit");
            return (Criteria) this;
        }

        public Criteria andCatgTypeUnitNotBetween(Long value1, Long value2) {
            addCriterion("CATG_TYPE_UNIT not between", value1, value2, "catgTypeUnit");
            return (Criteria) this;
        }

        public Criteria andMinPriceIsNull() {
            addCriterion("MIN_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andMinPriceIsNotNull() {
            addCriterion("MIN_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andMinPriceEqualTo(Long value) {
            addCriterion("MIN_PRICE =", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceNotEqualTo(Long value) {
            addCriterion("MIN_PRICE <>", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceGreaterThan(Long value) {
            addCriterion("MIN_PRICE >", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("MIN_PRICE >=", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceLessThan(Long value) {
            addCriterion("MIN_PRICE <", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceLessThanOrEqualTo(Long value) {
            addCriterion("MIN_PRICE <=", value, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceIn(List<Long> values) {
            addCriterion("MIN_PRICE in", values, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceNotIn(List<Long> values) {
            addCriterion("MIN_PRICE not in", values, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceBetween(Long value1, Long value2) {
            addCriterion("MIN_PRICE between", value1, value2, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMinPriceNotBetween(Long value1, Long value2) {
            addCriterion("MIN_PRICE not between", value1, value2, "minPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceIsNull() {
            addCriterion("MAX_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andMaxPriceIsNotNull() {
            addCriterion("MAX_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andMaxPriceEqualTo(Long value) {
            addCriterion("MAX_PRICE =", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceNotEqualTo(Long value) {
            addCriterion("MAX_PRICE <>", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceGreaterThan(Long value) {
            addCriterion("MAX_PRICE >", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("MAX_PRICE >=", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceLessThan(Long value) {
            addCriterion("MAX_PRICE <", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceLessThanOrEqualTo(Long value) {
            addCriterion("MAX_PRICE <=", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceIn(List<Long> values) {
            addCriterion("MAX_PRICE in", values, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceNotIn(List<Long> values) {
            addCriterion("MAX_PRICE not in", values, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceBetween(Long value1, Long value2) {
            addCriterion("MAX_PRICE between", value1, value2, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceNotBetween(Long value1, Long value2) {
            addCriterion("MAX_PRICE not between", value1, value2, "maxPrice");
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

        public Criteria andMediaUuidIsNull() {
            addCriterion("MEDIA_UUID is null");
            return (Criteria) this;
        }

        public Criteria andMediaUuidIsNotNull() {
            addCriterion("MEDIA_UUID is not null");
            return (Criteria) this;
        }

        public Criteria andMediaUuidEqualTo(String value) {
            addCriterion("MEDIA_UUID =", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidNotEqualTo(String value) {
            addCriterion("MEDIA_UUID <>", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidGreaterThan(String value) {
            addCriterion("MEDIA_UUID >", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidGreaterThanOrEqualTo(String value) {
            addCriterion("MEDIA_UUID >=", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidLessThan(String value) {
            addCriterion("MEDIA_UUID <", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidLessThanOrEqualTo(String value) {
            addCriterion("MEDIA_UUID <=", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidLike(String value) {
            addCriterion("MEDIA_UUID like", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidNotLike(String value) {
            addCriterion("MEDIA_UUID not like", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidIn(List<String> values) {
            addCriterion("MEDIA_UUID in", values, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidNotIn(List<String> values) {
            addCriterion("MEDIA_UUID not in", values, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidBetween(String value1, String value2) {
            addCriterion("MEDIA_UUID between", value1, value2, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidNotBetween(String value1, String value2) {
            addCriterion("MEDIA_UUID not between", value1, value2, "mediaUuid");
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

        public Criteria andIfShowIsNull() {
            addCriterion("IF_SHOW is null");
            return (Criteria) this;
        }

        public Criteria andIfShowIsNotNull() {
            addCriterion("IF_SHOW is not null");
            return (Criteria) this;
        }

        public Criteria andIfShowEqualTo(String value) {
            addCriterion("IF_SHOW =", value, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowNotEqualTo(String value) {
            addCriterion("IF_SHOW <>", value, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowGreaterThan(String value) {
            addCriterion("IF_SHOW >", value, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowGreaterThanOrEqualTo(String value) {
            addCriterion("IF_SHOW >=", value, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowLessThan(String value) {
            addCriterion("IF_SHOW <", value, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowLessThanOrEqualTo(String value) {
            addCriterion("IF_SHOW <=", value, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowLike(String value) {
            addCriterion("IF_SHOW like", value, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowNotLike(String value) {
            addCriterion("IF_SHOW not like", value, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowIn(List<String> values) {
            addCriterion("IF_SHOW in", values, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowNotIn(List<String> values) {
            addCriterion("IF_SHOW not in", values, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowBetween(String value1, String value2) {
            addCriterion("IF_SHOW between", value1, value2, "ifShow");
            return (Criteria) this;
        }

        public Criteria andIfShowNotBetween(String value1, String value2) {
            addCriterion("IF_SHOW not between", value1, value2, "ifShow");
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

        public Criteria andIfAbleEditIsNull() {
            addCriterion("IF_ABLE_EDIT is null");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditIsNotNull() {
            addCriterion("IF_ABLE_EDIT is not null");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditEqualTo(String value) {
            addCriterion("IF_ABLE_EDIT =", value, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditNotEqualTo(String value) {
            addCriterion("IF_ABLE_EDIT <>", value, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditGreaterThan(String value) {
            addCriterion("IF_ABLE_EDIT >", value, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditGreaterThanOrEqualTo(String value) {
            addCriterion("IF_ABLE_EDIT >=", value, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditLessThan(String value) {
            addCriterion("IF_ABLE_EDIT <", value, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditLessThanOrEqualTo(String value) {
            addCriterion("IF_ABLE_EDIT <=", value, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditLike(String value) {
            addCriterion("IF_ABLE_EDIT like", value, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditNotLike(String value) {
            addCriterion("IF_ABLE_EDIT not like", value, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditIn(List<String> values) {
            addCriterion("IF_ABLE_EDIT in", values, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditNotIn(List<String> values) {
            addCriterion("IF_ABLE_EDIT not in", values, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditBetween(String value1, String value2) {
            addCriterion("IF_ABLE_EDIT between", value1, value2, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andIfAbleEditNotBetween(String value1, String value2) {
            addCriterion("IF_ABLE_EDIT not between", value1, value2, "ifAbleEdit");
            return (Criteria) this;
        }

        public Criteria andCommissionIsNull() {
            addCriterion("COMMISSION is null");
            return (Criteria) this;
        }

        public Criteria andCommissionIsNotNull() {
            addCriterion("COMMISSION is not null");
            return (Criteria) this;
        }

        public Criteria andCommissionEqualTo(BigDecimal value) {
            addCriterion("COMMISSION =", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionNotEqualTo(BigDecimal value) {
            addCriterion("COMMISSION <>", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionGreaterThan(BigDecimal value) {
            addCriterion("COMMISSION >", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("COMMISSION >=", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionLessThan(BigDecimal value) {
            addCriterion("COMMISSION <", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("COMMISSION <=", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionIn(List<BigDecimal> values) {
            addCriterion("COMMISSION in", values, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionNotIn(List<BigDecimal> values) {
            addCriterion("COMMISSION not in", values, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COMMISSION between", value1, value2, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COMMISSION not between", value1, value2, "commission");
            return (Criteria) this;
        }

        public Criteria andCatgPathIsNull() {
            addCriterion("CATG_PATH is null");
            return (Criteria) this;
        }

        public Criteria andCatgPathIsNotNull() {
            addCriterion("CATG_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andCatgPathEqualTo(String value) {
            addCriterion("CATG_PATH =", value, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathNotEqualTo(String value) {
            addCriterion("CATG_PATH <>", value, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathGreaterThan(String value) {
            addCriterion("CATG_PATH >", value, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathGreaterThanOrEqualTo(String value) {
            addCriterion("CATG_PATH >=", value, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathLessThan(String value) {
            addCriterion("CATG_PATH <", value, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathLessThanOrEqualTo(String value) {
            addCriterion("CATG_PATH <=", value, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathLike(String value) {
            addCriterion("CATG_PATH like", value, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathNotLike(String value) {
            addCriterion("CATG_PATH not like", value, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathIn(List<String> values) {
            addCriterion("CATG_PATH in", values, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathNotIn(List<String> values) {
            addCriterion("CATG_PATH not in", values, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathBetween(String value1, String value2) {
            addCriterion("CATG_PATH between", value1, value2, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathNotBetween(String value1, String value2) {
            addCriterion("CATG_PATH not between", value1, value2, "catgPath");
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
