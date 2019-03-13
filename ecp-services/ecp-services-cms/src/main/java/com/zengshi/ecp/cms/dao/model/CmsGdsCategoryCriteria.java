package com.zengshi.ecp.cms.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CmsGdsCategoryCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CmsGdsCategoryCriteria() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
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

        public Criteria andShowCatgIdIsNull() {
            addCriterion("SHOW_CATG_ID is null");
            return (Criteria) this;
        }

        public Criteria andShowCatgIdIsNotNull() {
            addCriterion("SHOW_CATG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andShowCatgIdEqualTo(String value) {
            addCriterion("SHOW_CATG_ID =", value, "showCatgId");
            return (Criteria) this;
        }

        public Criteria andShowCatgIdNotEqualTo(String value) {
            addCriterion("SHOW_CATG_ID <>", value, "showCatgId");
            return (Criteria) this;
        }

        public Criteria andShowCatgIdGreaterThan(String value) {
            addCriterion("SHOW_CATG_ID >", value, "showCatgId");
            return (Criteria) this;
        }

        public Criteria andShowCatgIdGreaterThanOrEqualTo(String value) {
            addCriterion("SHOW_CATG_ID >=", value, "showCatgId");
            return (Criteria) this;
        }

        public Criteria andShowCatgIdLessThan(String value) {
            addCriterion("SHOW_CATG_ID <", value, "showCatgId");
            return (Criteria) this;
        }

        public Criteria andShowCatgIdLessThanOrEqualTo(String value) {
            addCriterion("SHOW_CATG_ID <=", value, "showCatgId");
            return (Criteria) this;
        }

        public Criteria andShowCatgIdLike(String value) {
            addCriterion("SHOW_CATG_ID like", value, "showCatgId");
            return (Criteria) this;
        }

        public Criteria andShowCatgIdNotLike(String value) {
            addCriterion("SHOW_CATG_ID not like", value, "showCatgId");
            return (Criteria) this;
        }

        public Criteria andShowCatgIdIn(List<String> values) {
            addCriterion("SHOW_CATG_ID in", values, "showCatgId");
            return (Criteria) this;
        }

        public Criteria andShowCatgIdNotIn(List<String> values) {
            addCriterion("SHOW_CATG_ID not in", values, "showCatgId");
            return (Criteria) this;
        }

        public Criteria andShowCatgIdBetween(String value1, String value2) {
            addCriterion("SHOW_CATG_ID between", value1, value2, "showCatgId");
            return (Criteria) this;
        }

        public Criteria andShowCatgIdNotBetween(String value1, String value2) {
            addCriterion("SHOW_CATG_ID not between", value1, value2, "showCatgId");
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

        public Criteria andSiteIdIsNull() {
            addCriterion("SITE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSiteIdIsNotNull() {
            addCriterion("SITE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSiteIdEqualTo(Long value) {
            addCriterion("SITE_ID =", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotEqualTo(Long value) {
            addCriterion("SITE_ID <>", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThan(Long value) {
            addCriterion("SITE_ID >", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SITE_ID >=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThan(Long value) {
            addCriterion("SITE_ID <", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThanOrEqualTo(Long value) {
            addCriterion("SITE_ID <=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdIn(List<Long> values) {
            addCriterion("SITE_ID in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotIn(List<Long> values) {
            addCriterion("SITE_ID not in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdBetween(Long value1, Long value2) {
            addCriterion("SITE_ID between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotBetween(Long value1, Long value2) {
            addCriterion("SITE_ID not between", value1, value2, "siteId");
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
