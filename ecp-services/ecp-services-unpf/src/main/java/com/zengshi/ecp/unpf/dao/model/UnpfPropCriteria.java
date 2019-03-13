package com.zengshi.ecp.unpf.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UnpfPropCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public UnpfPropCriteria() {
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

        public Criteria andPropCodeIsNull() {
            addCriterion("PROP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPropCodeIsNotNull() {
            addCriterion("PROP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPropCodeEqualTo(String value) {
            addCriterion("PROP_CODE =", value, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeNotEqualTo(String value) {
            addCriterion("PROP_CODE <>", value, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeGreaterThan(String value) {
            addCriterion("PROP_CODE >", value, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_CODE >=", value, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeLessThan(String value) {
            addCriterion("PROP_CODE <", value, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeLessThanOrEqualTo(String value) {
            addCriterion("PROP_CODE <=", value, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeLike(String value) {
            addCriterion("PROP_CODE like", value, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeNotLike(String value) {
            addCriterion("PROP_CODE not like", value, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeIn(List<String> values) {
            addCriterion("PROP_CODE in", values, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeNotIn(List<String> values) {
            addCriterion("PROP_CODE not in", values, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeBetween(String value1, String value2) {
            addCriterion("PROP_CODE between", value1, value2, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropCodeNotBetween(String value1, String value2) {
            addCriterion("PROP_CODE not between", value1, value2, "propCode");
            return (Criteria) this;
        }

        public Criteria andPropNameIsNull() {
            addCriterion("PROP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPropNameIsNotNull() {
            addCriterion("PROP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPropNameEqualTo(String value) {
            addCriterion("PROP_NAME =", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameNotEqualTo(String value) {
            addCriterion("PROP_NAME <>", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameGreaterThan(String value) {
            addCriterion("PROP_NAME >", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_NAME >=", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameLessThan(String value) {
            addCriterion("PROP_NAME <", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameLessThanOrEqualTo(String value) {
            addCriterion("PROP_NAME <=", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameLike(String value) {
            addCriterion("PROP_NAME like", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameNotLike(String value) {
            addCriterion("PROP_NAME not like", value, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameIn(List<String> values) {
            addCriterion("PROP_NAME in", values, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameNotIn(List<String> values) {
            addCriterion("PROP_NAME not in", values, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameBetween(String value1, String value2) {
            addCriterion("PROP_NAME between", value1, value2, "propName");
            return (Criteria) this;
        }

        public Criteria andPropNameNotBetween(String value1, String value2) {
            addCriterion("PROP_NAME not between", value1, value2, "propName");
            return (Criteria) this;
        }

        public Criteria andPropSnameIsNull() {
            addCriterion("PROP_SNAME is null");
            return (Criteria) this;
        }

        public Criteria andPropSnameIsNotNull() {
            addCriterion("PROP_SNAME is not null");
            return (Criteria) this;
        }

        public Criteria andPropSnameEqualTo(String value) {
            addCriterion("PROP_SNAME =", value, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameNotEqualTo(String value) {
            addCriterion("PROP_SNAME <>", value, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameGreaterThan(String value) {
            addCriterion("PROP_SNAME >", value, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_SNAME >=", value, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameLessThan(String value) {
            addCriterion("PROP_SNAME <", value, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameLessThanOrEqualTo(String value) {
            addCriterion("PROP_SNAME <=", value, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameLike(String value) {
            addCriterion("PROP_SNAME like", value, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameNotLike(String value) {
            addCriterion("PROP_SNAME not like", value, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameIn(List<String> values) {
            addCriterion("PROP_SNAME in", values, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameNotIn(List<String> values) {
            addCriterion("PROP_SNAME not in", values, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameBetween(String value1, String value2) {
            addCriterion("PROP_SNAME between", value1, value2, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropSnameNotBetween(String value1, String value2) {
            addCriterion("PROP_SNAME not between", value1, value2, "propSname");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeIsNull() {
            addCriterion("PROP_VALUE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeIsNotNull() {
            addCriterion("PROP_VALUE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeEqualTo(String value) {
            addCriterion("PROP_VALUE_TYPE =", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeNotEqualTo(String value) {
            addCriterion("PROP_VALUE_TYPE <>", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeGreaterThan(String value) {
            addCriterion("PROP_VALUE_TYPE >", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_VALUE_TYPE >=", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeLessThan(String value) {
            addCriterion("PROP_VALUE_TYPE <", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeLessThanOrEqualTo(String value) {
            addCriterion("PROP_VALUE_TYPE <=", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeLike(String value) {
            addCriterion("PROP_VALUE_TYPE like", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeNotLike(String value) {
            addCriterion("PROP_VALUE_TYPE not like", value, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeIn(List<String> values) {
            addCriterion("PROP_VALUE_TYPE in", values, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeNotIn(List<String> values) {
            addCriterion("PROP_VALUE_TYPE not in", values, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeBetween(String value1, String value2) {
            addCriterion("PROP_VALUE_TYPE between", value1, value2, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropValueTypeNotBetween(String value1, String value2) {
            addCriterion("PROP_VALUE_TYPE not between", value1, value2, "propValueType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeIsNull() {
            addCriterion("PROP_INPUT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeIsNotNull() {
            addCriterion("PROP_INPUT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeEqualTo(String value) {
            addCriterion("PROP_INPUT_TYPE =", value, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeNotEqualTo(String value) {
            addCriterion("PROP_INPUT_TYPE <>", value, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeGreaterThan(String value) {
            addCriterion("PROP_INPUT_TYPE >", value, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_INPUT_TYPE >=", value, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeLessThan(String value) {
            addCriterion("PROP_INPUT_TYPE <", value, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeLessThanOrEqualTo(String value) {
            addCriterion("PROP_INPUT_TYPE <=", value, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeLike(String value) {
            addCriterion("PROP_INPUT_TYPE like", value, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeNotLike(String value) {
            addCriterion("PROP_INPUT_TYPE not like", value, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeIn(List<String> values) {
            addCriterion("PROP_INPUT_TYPE in", values, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeNotIn(List<String> values) {
            addCriterion("PROP_INPUT_TYPE not in", values, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeBetween(String value1, String value2) {
            addCriterion("PROP_INPUT_TYPE between", value1, value2, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputTypeNotBetween(String value1, String value2) {
            addCriterion("PROP_INPUT_TYPE not between", value1, value2, "propInputType");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleIsNull() {
            addCriterion("PROP_INPUT_RULE is null");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleIsNotNull() {
            addCriterion("PROP_INPUT_RULE is not null");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleEqualTo(String value) {
            addCriterion("PROP_INPUT_RULE =", value, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleNotEqualTo(String value) {
            addCriterion("PROP_INPUT_RULE <>", value, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleGreaterThan(String value) {
            addCriterion("PROP_INPUT_RULE >", value, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_INPUT_RULE >=", value, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleLessThan(String value) {
            addCriterion("PROP_INPUT_RULE <", value, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleLessThanOrEqualTo(String value) {
            addCriterion("PROP_INPUT_RULE <=", value, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleLike(String value) {
            addCriterion("PROP_INPUT_RULE like", value, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleNotLike(String value) {
            addCriterion("PROP_INPUT_RULE not like", value, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleIn(List<String> values) {
            addCriterion("PROP_INPUT_RULE in", values, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleNotIn(List<String> values) {
            addCriterion("PROP_INPUT_RULE not in", values, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleBetween(String value1, String value2) {
            addCriterion("PROP_INPUT_RULE between", value1, value2, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropInputRuleNotBetween(String value1, String value2) {
            addCriterion("PROP_INPUT_RULE not between", value1, value2, "propInputRule");
            return (Criteria) this;
        }

        public Criteria andPropTypeIsNull() {
            addCriterion("PROP_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPropTypeIsNotNull() {
            addCriterion("PROP_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPropTypeEqualTo(String value) {
            addCriterion("PROP_TYPE =", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeNotEqualTo(String value) {
            addCriterion("PROP_TYPE <>", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeGreaterThan(String value) {
            addCriterion("PROP_TYPE >", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_TYPE >=", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeLessThan(String value) {
            addCriterion("PROP_TYPE <", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeLessThanOrEqualTo(String value) {
            addCriterion("PROP_TYPE <=", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeLike(String value) {
            addCriterion("PROP_TYPE like", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeNotLike(String value) {
            addCriterion("PROP_TYPE not like", value, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeIn(List<String> values) {
            addCriterion("PROP_TYPE in", values, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeNotIn(List<String> values) {
            addCriterion("PROP_TYPE not in", values, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeBetween(String value1, String value2) {
            addCriterion("PROP_TYPE between", value1, value2, "propType");
            return (Criteria) this;
        }

        public Criteria andPropTypeNotBetween(String value1, String value2) {
            addCriterion("PROP_TYPE not between", value1, value2, "propType");
            return (Criteria) this;
        }

        public Criteria andPropDescIsNull() {
            addCriterion("PROP_DESC is null");
            return (Criteria) this;
        }

        public Criteria andPropDescIsNotNull() {
            addCriterion("PROP_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andPropDescEqualTo(String value) {
            addCriterion("PROP_DESC =", value, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescNotEqualTo(String value) {
            addCriterion("PROP_DESC <>", value, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescGreaterThan(String value) {
            addCriterion("PROP_DESC >", value, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_DESC >=", value, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescLessThan(String value) {
            addCriterion("PROP_DESC <", value, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescLessThanOrEqualTo(String value) {
            addCriterion("PROP_DESC <=", value, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescLike(String value) {
            addCriterion("PROP_DESC like", value, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescNotLike(String value) {
            addCriterion("PROP_DESC not like", value, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescIn(List<String> values) {
            addCriterion("PROP_DESC in", values, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescNotIn(List<String> values) {
            addCriterion("PROP_DESC not in", values, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescBetween(String value1, String value2) {
            addCriterion("PROP_DESC between", value1, value2, "propDesc");
            return (Criteria) this;
        }

        public Criteria andPropDescNotBetween(String value1, String value2) {
            addCriterion("PROP_DESC not between", value1, value2, "propDesc");
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

        public Criteria andIfAbleEidtIsNull() {
            addCriterion("IF_ABLE_EIDT is null");
            return (Criteria) this;
        }

        public Criteria andIfAbleEidtIsNotNull() {
            addCriterion("IF_ABLE_EIDT is not null");
            return (Criteria) this;
        }

        public Criteria andIfAbleEidtEqualTo(String value) {
            addCriterion("IF_ABLE_EIDT =", value, "ifAbleEidt");
            return (Criteria) this;
        }

        public Criteria andIfAbleEidtNotEqualTo(String value) {
            addCriterion("IF_ABLE_EIDT <>", value, "ifAbleEidt");
            return (Criteria) this;
        }

        public Criteria andIfAbleEidtGreaterThan(String value) {
            addCriterion("IF_ABLE_EIDT >", value, "ifAbleEidt");
            return (Criteria) this;
        }

        public Criteria andIfAbleEidtGreaterThanOrEqualTo(String value) {
            addCriterion("IF_ABLE_EIDT >=", value, "ifAbleEidt");
            return (Criteria) this;
        }

        public Criteria andIfAbleEidtLessThan(String value) {
            addCriterion("IF_ABLE_EIDT <", value, "ifAbleEidt");
            return (Criteria) this;
        }

        public Criteria andIfAbleEidtLessThanOrEqualTo(String value) {
            addCriterion("IF_ABLE_EIDT <=", value, "ifAbleEidt");
            return (Criteria) this;
        }

        public Criteria andIfAbleEidtLike(String value) {
            addCriterion("IF_ABLE_EIDT like", value, "ifAbleEidt");
            return (Criteria) this;
        }

        public Criteria andIfAbleEidtNotLike(String value) {
            addCriterion("IF_ABLE_EIDT not like", value, "ifAbleEidt");
            return (Criteria) this;
        }

        public Criteria andIfAbleEidtIn(List<String> values) {
            addCriterion("IF_ABLE_EIDT in", values, "ifAbleEidt");
            return (Criteria) this;
        }

        public Criteria andIfAbleEidtNotIn(List<String> values) {
            addCriterion("IF_ABLE_EIDT not in", values, "ifAbleEidt");
            return (Criteria) this;
        }

        public Criteria andIfAbleEidtBetween(String value1, String value2) {
            addCriterion("IF_ABLE_EIDT between", value1, value2, "ifAbleEidt");
            return (Criteria) this;
        }

        public Criteria andIfAbleEidtNotBetween(String value1, String value2) {
            addCriterion("IF_ABLE_EIDT not between", value1, value2, "ifAbleEidt");
            return (Criteria) this;
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

        public Criteria andPlatTypeIsNull() {
            addCriterion("PLAT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPlatTypeIsNotNull() {
            addCriterion("PLAT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPlatTypeEqualTo(String value) {
            addCriterion("PLAT_TYPE =", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeNotEqualTo(String value) {
            addCriterion("PLAT_TYPE <>", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeGreaterThan(String value) {
            addCriterion("PLAT_TYPE >", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PLAT_TYPE >=", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeLessThan(String value) {
            addCriterion("PLAT_TYPE <", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeLessThanOrEqualTo(String value) {
            addCriterion("PLAT_TYPE <=", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeLike(String value) {
            addCriterion("PLAT_TYPE like", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeNotLike(String value) {
            addCriterion("PLAT_TYPE not like", value, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeIn(List<String> values) {
            addCriterion("PLAT_TYPE in", values, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeNotIn(List<String> values) {
            addCriterion("PLAT_TYPE not in", values, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeBetween(String value1, String value2) {
            addCriterion("PLAT_TYPE between", value1, value2, "platType");
            return (Criteria) this;
        }

        public Criteria andPlatTypeNotBetween(String value1, String value2) {
            addCriterion("PLAT_TYPE not between", value1, value2, "platType");
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

        public Criteria andShopAuthIdIsNull() {
            addCriterion("SHOP_AUTH_ID is null");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdIsNotNull() {
            addCriterion("SHOP_AUTH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdEqualTo(Long value) {
            addCriterion("SHOP_AUTH_ID =", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdNotEqualTo(Long value) {
            addCriterion("SHOP_AUTH_ID <>", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdGreaterThan(Long value) {
            addCriterion("SHOP_AUTH_ID >", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SHOP_AUTH_ID >=", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdLessThan(Long value) {
            addCriterion("SHOP_AUTH_ID <", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdLessThanOrEqualTo(Long value) {
            addCriterion("SHOP_AUTH_ID <=", value, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdIn(List<Long> values) {
            addCriterion("SHOP_AUTH_ID in", values, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdNotIn(List<Long> values) {
            addCriterion("SHOP_AUTH_ID not in", values, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdBetween(Long value1, Long value2) {
            addCriterion("SHOP_AUTH_ID between", value1, value2, "shopAuthId");
            return (Criteria) this;
        }

        public Criteria andShopAuthIdNotBetween(Long value1, Long value2) {
            addCriterion("SHOP_AUTH_ID not between", value1, value2, "shopAuthId");
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