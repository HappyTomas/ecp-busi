package com.zengshi.ecp.search.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SecConfigCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public SecConfigCriteria() {
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

        public Criteria andConfigNameIsNull() {
            addCriterion("CONFIG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andConfigNameIsNotNull() {
            addCriterion("CONFIG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andConfigNameEqualTo(String value) {
            addCriterion("CONFIG_NAME =", value, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameNotEqualTo(String value) {
            addCriterion("CONFIG_NAME <>", value, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameGreaterThan(String value) {
            addCriterion("CONFIG_NAME >", value, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONFIG_NAME >=", value, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameLessThan(String value) {
            addCriterion("CONFIG_NAME <", value, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameLessThanOrEqualTo(String value) {
            addCriterion("CONFIG_NAME <=", value, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameLike(String value) {
            addCriterion("CONFIG_NAME like", value, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameNotLike(String value) {
            addCriterion("CONFIG_NAME not like", value, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameIn(List<String> values) {
            addCriterion("CONFIG_NAME in", values, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameNotIn(List<String> values) {
            addCriterion("CONFIG_NAME not in", values, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameBetween(String value1, String value2) {
            addCriterion("CONFIG_NAME between", value1, value2, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigNameNotBetween(String value1, String value2) {
            addCriterion("CONFIG_NAME not between", value1, value2, "configName");
            return (Criteria) this;
        }

        public Criteria andConfigDescIsNull() {
            addCriterion("CONFIG_DESC is null");
            return (Criteria) this;
        }

        public Criteria andConfigDescIsNotNull() {
            addCriterion("CONFIG_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andConfigDescEqualTo(String value) {
            addCriterion("CONFIG_DESC =", value, "configDesc");
            return (Criteria) this;
        }

        public Criteria andConfigDescNotEqualTo(String value) {
            addCriterion("CONFIG_DESC <>", value, "configDesc");
            return (Criteria) this;
        }

        public Criteria andConfigDescGreaterThan(String value) {
            addCriterion("CONFIG_DESC >", value, "configDesc");
            return (Criteria) this;
        }

        public Criteria andConfigDescGreaterThanOrEqualTo(String value) {
            addCriterion("CONFIG_DESC >=", value, "configDesc");
            return (Criteria) this;
        }

        public Criteria andConfigDescLessThan(String value) {
            addCriterion("CONFIG_DESC <", value, "configDesc");
            return (Criteria) this;
        }

        public Criteria andConfigDescLessThanOrEqualTo(String value) {
            addCriterion("CONFIG_DESC <=", value, "configDesc");
            return (Criteria) this;
        }

        public Criteria andConfigDescLike(String value) {
            addCriterion("CONFIG_DESC like", value, "configDesc");
            return (Criteria) this;
        }

        public Criteria andConfigDescNotLike(String value) {
            addCriterion("CONFIG_DESC not like", value, "configDesc");
            return (Criteria) this;
        }

        public Criteria andConfigDescIn(List<String> values) {
            addCriterion("CONFIG_DESC in", values, "configDesc");
            return (Criteria) this;
        }

        public Criteria andConfigDescNotIn(List<String> values) {
            addCriterion("CONFIG_DESC not in", values, "configDesc");
            return (Criteria) this;
        }

        public Criteria andConfigDescBetween(String value1, String value2) {
            addCriterion("CONFIG_DESC between", value1, value2, "configDesc");
            return (Criteria) this;
        }

        public Criteria andConfigDescNotBetween(String value1, String value2) {
            addCriterion("CONFIG_DESC not between", value1, value2, "configDesc");
            return (Criteria) this;
        }

        public Criteria andConfigCollectionNameIsNull() {
            addCriterion("CONFIG_COLLECTION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andConfigCollectionNameIsNotNull() {
            addCriterion("CONFIG_COLLECTION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andConfigCollectionNameEqualTo(String value) {
            addCriterion("CONFIG_COLLECTION_NAME =", value, "configCollectionName");
            return (Criteria) this;
        }

        public Criteria andConfigCollectionNameNotEqualTo(String value) {
            addCriterion("CONFIG_COLLECTION_NAME <>", value, "configCollectionName");
            return (Criteria) this;
        }

        public Criteria andConfigCollectionNameGreaterThan(String value) {
            addCriterion("CONFIG_COLLECTION_NAME >", value, "configCollectionName");
            return (Criteria) this;
        }

        public Criteria andConfigCollectionNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONFIG_COLLECTION_NAME >=", value, "configCollectionName");
            return (Criteria) this;
        }

        public Criteria andConfigCollectionNameLessThan(String value) {
            addCriterion("CONFIG_COLLECTION_NAME <", value, "configCollectionName");
            return (Criteria) this;
        }

        public Criteria andConfigCollectionNameLessThanOrEqualTo(String value) {
            addCriterion("CONFIG_COLLECTION_NAME <=", value, "configCollectionName");
            return (Criteria) this;
        }

        public Criteria andConfigCollectionNameLike(String value) {
            addCriterion("CONFIG_COLLECTION_NAME like", value, "configCollectionName");
            return (Criteria) this;
        }

        public Criteria andConfigCollectionNameNotLike(String value) {
            addCriterion("CONFIG_COLLECTION_NAME not like", value, "configCollectionName");
            return (Criteria) this;
        }

        public Criteria andConfigCollectionNameIn(List<String> values) {
            addCriterion("CONFIG_COLLECTION_NAME in", values, "configCollectionName");
            return (Criteria) this;
        }

        public Criteria andConfigCollectionNameNotIn(List<String> values) {
            addCriterion("CONFIG_COLLECTION_NAME not in", values, "configCollectionName");
            return (Criteria) this;
        }

        public Criteria andConfigCollectionNameBetween(String value1, String value2) {
            addCriterion("CONFIG_COLLECTION_NAME between", value1, value2, "configCollectionName");
            return (Criteria) this;
        }

        public Criteria andConfigCollectionNameNotBetween(String value1, String value2) {
            addCriterion("CONFIG_COLLECTION_NAME not between", value1, value2, "configCollectionName");
            return (Criteria) this;
        }

        public Criteria andConfigQueryOpIsNull() {
            addCriterion("CONFIG_QUERY_OP is null");
            return (Criteria) this;
        }

        public Criteria andConfigQueryOpIsNotNull() {
            addCriterion("CONFIG_QUERY_OP is not null");
            return (Criteria) this;
        }

        public Criteria andConfigQueryOpEqualTo(String value) {
            addCriterion("CONFIG_QUERY_OP =", value, "configQueryOp");
            return (Criteria) this;
        }

        public Criteria andConfigQueryOpNotEqualTo(String value) {
            addCriterion("CONFIG_QUERY_OP <>", value, "configQueryOp");
            return (Criteria) this;
        }

        public Criteria andConfigQueryOpGreaterThan(String value) {
            addCriterion("CONFIG_QUERY_OP >", value, "configQueryOp");
            return (Criteria) this;
        }

        public Criteria andConfigQueryOpGreaterThanOrEqualTo(String value) {
            addCriterion("CONFIG_QUERY_OP >=", value, "configQueryOp");
            return (Criteria) this;
        }

        public Criteria andConfigQueryOpLessThan(String value) {
            addCriterion("CONFIG_QUERY_OP <", value, "configQueryOp");
            return (Criteria) this;
        }

        public Criteria andConfigQueryOpLessThanOrEqualTo(String value) {
            addCriterion("CONFIG_QUERY_OP <=", value, "configQueryOp");
            return (Criteria) this;
        }

        public Criteria andConfigQueryOpLike(String value) {
            addCriterion("CONFIG_QUERY_OP like", value, "configQueryOp");
            return (Criteria) this;
        }

        public Criteria andConfigQueryOpNotLike(String value) {
            addCriterion("CONFIG_QUERY_OP not like", value, "configQueryOp");
            return (Criteria) this;
        }

        public Criteria andConfigQueryOpIn(List<String> values) {
            addCriterion("CONFIG_QUERY_OP in", values, "configQueryOp");
            return (Criteria) this;
        }

        public Criteria andConfigQueryOpNotIn(List<String> values) {
            addCriterion("CONFIG_QUERY_OP not in", values, "configQueryOp");
            return (Criteria) this;
        }

        public Criteria andConfigQueryOpBetween(String value1, String value2) {
            addCriterion("CONFIG_QUERY_OP between", value1, value2, "configQueryOp");
            return (Criteria) this;
        }

        public Criteria andConfigQueryOpNotBetween(String value1, String value2) {
            addCriterion("CONFIG_QUERY_OP not between", value1, value2, "configQueryOp");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfHlIsNull() {
            addCriterion("CONFIG_QUERY_IF_HL is null");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfHlIsNotNull() {
            addCriterion("CONFIG_QUERY_IF_HL is not null");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfHlEqualTo(String value) {
            addCriterion("CONFIG_QUERY_IF_HL =", value, "configQueryIfHl");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfHlNotEqualTo(String value) {
            addCriterion("CONFIG_QUERY_IF_HL <>", value, "configQueryIfHl");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfHlGreaterThan(String value) {
            addCriterion("CONFIG_QUERY_IF_HL >", value, "configQueryIfHl");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfHlGreaterThanOrEqualTo(String value) {
            addCriterion("CONFIG_QUERY_IF_HL >=", value, "configQueryIfHl");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfHlLessThan(String value) {
            addCriterion("CONFIG_QUERY_IF_HL <", value, "configQueryIfHl");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfHlLessThanOrEqualTo(String value) {
            addCriterion("CONFIG_QUERY_IF_HL <=", value, "configQueryIfHl");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfHlLike(String value) {
            addCriterion("CONFIG_QUERY_IF_HL like", value, "configQueryIfHl");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfHlNotLike(String value) {
            addCriterion("CONFIG_QUERY_IF_HL not like", value, "configQueryIfHl");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfHlIn(List<String> values) {
            addCriterion("CONFIG_QUERY_IF_HL in", values, "configQueryIfHl");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfHlNotIn(List<String> values) {
            addCriterion("CONFIG_QUERY_IF_HL not in", values, "configQueryIfHl");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfHlBetween(String value1, String value2) {
            addCriterion("CONFIG_QUERY_IF_HL between", value1, value2, "configQueryIfHl");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfHlNotBetween(String value1, String value2) {
            addCriterion("CONFIG_QUERY_IF_HL not between", value1, value2, "configQueryIfHl");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPreIsNull() {
            addCriterion("CONFIG_QUERY_HL_PRE is null");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPreIsNotNull() {
            addCriterion("CONFIG_QUERY_HL_PRE is not null");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPreEqualTo(String value) {
            addCriterion("CONFIG_QUERY_HL_PRE =", value, "configQueryHlPre");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPreNotEqualTo(String value) {
            addCriterion("CONFIG_QUERY_HL_PRE <>", value, "configQueryHlPre");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPreGreaterThan(String value) {
            addCriterion("CONFIG_QUERY_HL_PRE >", value, "configQueryHlPre");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPreGreaterThanOrEqualTo(String value) {
            addCriterion("CONFIG_QUERY_HL_PRE >=", value, "configQueryHlPre");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPreLessThan(String value) {
            addCriterion("CONFIG_QUERY_HL_PRE <", value, "configQueryHlPre");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPreLessThanOrEqualTo(String value) {
            addCriterion("CONFIG_QUERY_HL_PRE <=", value, "configQueryHlPre");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPreLike(String value) {
            addCriterion("CONFIG_QUERY_HL_PRE like", value, "configQueryHlPre");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPreNotLike(String value) {
            addCriterion("CONFIG_QUERY_HL_PRE not like", value, "configQueryHlPre");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPreIn(List<String> values) {
            addCriterion("CONFIG_QUERY_HL_PRE in", values, "configQueryHlPre");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPreNotIn(List<String> values) {
            addCriterion("CONFIG_QUERY_HL_PRE not in", values, "configQueryHlPre");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPreBetween(String value1, String value2) {
            addCriterion("CONFIG_QUERY_HL_PRE between", value1, value2, "configQueryHlPre");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPreNotBetween(String value1, String value2) {
            addCriterion("CONFIG_QUERY_HL_PRE not between", value1, value2, "configQueryHlPre");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPostIsNull() {
            addCriterion("CONFIG_QUERY_HL_POST is null");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPostIsNotNull() {
            addCriterion("CONFIG_QUERY_HL_POST is not null");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPostEqualTo(String value) {
            addCriterion("CONFIG_QUERY_HL_POST =", value, "configQueryHlPost");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPostNotEqualTo(String value) {
            addCriterion("CONFIG_QUERY_HL_POST <>", value, "configQueryHlPost");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPostGreaterThan(String value) {
            addCriterion("CONFIG_QUERY_HL_POST >", value, "configQueryHlPost");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPostGreaterThanOrEqualTo(String value) {
            addCriterion("CONFIG_QUERY_HL_POST >=", value, "configQueryHlPost");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPostLessThan(String value) {
            addCriterion("CONFIG_QUERY_HL_POST <", value, "configQueryHlPost");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPostLessThanOrEqualTo(String value) {
            addCriterion("CONFIG_QUERY_HL_POST <=", value, "configQueryHlPost");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPostLike(String value) {
            addCriterion("CONFIG_QUERY_HL_POST like", value, "configQueryHlPost");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPostNotLike(String value) {
            addCriterion("CONFIG_QUERY_HL_POST not like", value, "configQueryHlPost");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPostIn(List<String> values) {
            addCriterion("CONFIG_QUERY_HL_POST in", values, "configQueryHlPost");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPostNotIn(List<String> values) {
            addCriterion("CONFIG_QUERY_HL_POST not in", values, "configQueryHlPost");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPostBetween(String value1, String value2) {
            addCriterion("CONFIG_QUERY_HL_POST between", value1, value2, "configQueryHlPost");
            return (Criteria) this;
        }

        public Criteria andConfigQueryHlPostNotBetween(String value1, String value2) {
            addCriterion("CONFIG_QUERY_HL_POST not between", value1, value2, "configQueryHlPost");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfSpellcheckIsNull() {
            addCriterion("CONFIG_QUERY_IF_SPELLCHECK is null");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfSpellcheckIsNotNull() {
            addCriterion("CONFIG_QUERY_IF_SPELLCHECK is not null");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfSpellcheckEqualTo(String value) {
            addCriterion("CONFIG_QUERY_IF_SPELLCHECK =", value, "configQueryIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfSpellcheckNotEqualTo(String value) {
            addCriterion("CONFIG_QUERY_IF_SPELLCHECK <>", value, "configQueryIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfSpellcheckGreaterThan(String value) {
            addCriterion("CONFIG_QUERY_IF_SPELLCHECK >", value, "configQueryIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfSpellcheckGreaterThanOrEqualTo(String value) {
            addCriterion("CONFIG_QUERY_IF_SPELLCHECK >=", value, "configQueryIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfSpellcheckLessThan(String value) {
            addCriterion("CONFIG_QUERY_IF_SPELLCHECK <", value, "configQueryIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfSpellcheckLessThanOrEqualTo(String value) {
            addCriterion("CONFIG_QUERY_IF_SPELLCHECK <=", value, "configQueryIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfSpellcheckLike(String value) {
            addCriterion("CONFIG_QUERY_IF_SPELLCHECK like", value, "configQueryIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfSpellcheckNotLike(String value) {
            addCriterion("CONFIG_QUERY_IF_SPELLCHECK not like", value, "configQueryIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfSpellcheckIn(List<String> values) {
            addCriterion("CONFIG_QUERY_IF_SPELLCHECK in", values, "configQueryIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfSpellcheckNotIn(List<String> values) {
            addCriterion("CONFIG_QUERY_IF_SPELLCHECK not in", values, "configQueryIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfSpellcheckBetween(String value1, String value2) {
            addCriterion("CONFIG_QUERY_IF_SPELLCHECK between", value1, value2, "configQueryIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andConfigQueryIfSpellcheckNotBetween(String value1, String value2) {
            addCriterion("CONFIG_QUERY_IF_SPELLCHECK not between", value1, value2, "configQueryIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andConfigQuerySpellcheckCountIsNull() {
            addCriterion("CONFIG_QUERY_SPELLCHECK_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andConfigQuerySpellcheckCountIsNotNull() {
            addCriterion("CONFIG_QUERY_SPELLCHECK_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andConfigQuerySpellcheckCountEqualTo(Short value) {
            addCriterion("CONFIG_QUERY_SPELLCHECK_COUNT =", value, "configQuerySpellcheckCount");
            return (Criteria) this;
        }

        public Criteria andConfigQuerySpellcheckCountNotEqualTo(Short value) {
            addCriterion("CONFIG_QUERY_SPELLCHECK_COUNT <>", value, "configQuerySpellcheckCount");
            return (Criteria) this;
        }

        public Criteria andConfigQuerySpellcheckCountGreaterThan(Short value) {
            addCriterion("CONFIG_QUERY_SPELLCHECK_COUNT >", value, "configQuerySpellcheckCount");
            return (Criteria) this;
        }

        public Criteria andConfigQuerySpellcheckCountGreaterThanOrEqualTo(Short value) {
            addCriterion("CONFIG_QUERY_SPELLCHECK_COUNT >=", value, "configQuerySpellcheckCount");
            return (Criteria) this;
        }

        public Criteria andConfigQuerySpellcheckCountLessThan(Short value) {
            addCriterion("CONFIG_QUERY_SPELLCHECK_COUNT <", value, "configQuerySpellcheckCount");
            return (Criteria) this;
        }

        public Criteria andConfigQuerySpellcheckCountLessThanOrEqualTo(Short value) {
            addCriterion("CONFIG_QUERY_SPELLCHECK_COUNT <=", value, "configQuerySpellcheckCount");
            return (Criteria) this;
        }

        public Criteria andConfigQuerySpellcheckCountIn(List<Short> values) {
            addCriterion("CONFIG_QUERY_SPELLCHECK_COUNT in", values, "configQuerySpellcheckCount");
            return (Criteria) this;
        }

        public Criteria andConfigQuerySpellcheckCountNotIn(List<Short> values) {
            addCriterion("CONFIG_QUERY_SPELLCHECK_COUNT not in", values, "configQuerySpellcheckCount");
            return (Criteria) this;
        }

        public Criteria andConfigQuerySpellcheckCountBetween(Short value1, Short value2) {
            addCriterion("CONFIG_QUERY_SPELLCHECK_COUNT between", value1, value2, "configQuerySpellcheckCount");
            return (Criteria) this;
        }

        public Criteria andConfigQuerySpellcheckCountNotBetween(Short value1, Short value2) {
            addCriterion("CONFIG_QUERY_SPELLCHECK_COUNT not between", value1, value2, "configQuerySpellcheckCount");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusIsNull() {
            addCriterion("COLLECTION_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusIsNotNull() {
            addCriterion("COLLECTION_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusEqualTo(String value) {
            addCriterion("COLLECTION_STATUS =", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusNotEqualTo(String value) {
            addCriterion("COLLECTION_STATUS <>", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusGreaterThan(String value) {
            addCriterion("COLLECTION_STATUS >", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusGreaterThanOrEqualTo(String value) {
            addCriterion("COLLECTION_STATUS >=", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusLessThan(String value) {
            addCriterion("COLLECTION_STATUS <", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusLessThanOrEqualTo(String value) {
            addCriterion("COLLECTION_STATUS <=", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusLike(String value) {
            addCriterion("COLLECTION_STATUS like", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusNotLike(String value) {
            addCriterion("COLLECTION_STATUS not like", value, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusIn(List<String> values) {
            addCriterion("COLLECTION_STATUS in", values, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusNotIn(List<String> values) {
            addCriterion("COLLECTION_STATUS not in", values, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusBetween(String value1, String value2) {
            addCriterion("COLLECTION_STATUS between", value1, value2, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andCollectionStatusNotBetween(String value1, String value2) {
            addCriterion("COLLECTION_STATUS not between", value1, value2, "collectionStatus");
            return (Criteria) this;
        }

        public Criteria andIndexStatusIsNull() {
            addCriterion("INDEX_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andIndexStatusIsNotNull() {
            addCriterion("INDEX_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andIndexStatusEqualTo(String value) {
            addCriterion("INDEX_STATUS =", value, "indexStatus");
            return (Criteria) this;
        }

        public Criteria andIndexStatusNotEqualTo(String value) {
            addCriterion("INDEX_STATUS <>", value, "indexStatus");
            return (Criteria) this;
        }

        public Criteria andIndexStatusGreaterThan(String value) {
            addCriterion("INDEX_STATUS >", value, "indexStatus");
            return (Criteria) this;
        }

        public Criteria andIndexStatusGreaterThanOrEqualTo(String value) {
            addCriterion("INDEX_STATUS >=", value, "indexStatus");
            return (Criteria) this;
        }

        public Criteria andIndexStatusLessThan(String value) {
            addCriterion("INDEX_STATUS <", value, "indexStatus");
            return (Criteria) this;
        }

        public Criteria andIndexStatusLessThanOrEqualTo(String value) {
            addCriterion("INDEX_STATUS <=", value, "indexStatus");
            return (Criteria) this;
        }

        public Criteria andIndexStatusLike(String value) {
            addCriterion("INDEX_STATUS like", value, "indexStatus");
            return (Criteria) this;
        }

        public Criteria andIndexStatusNotLike(String value) {
            addCriterion("INDEX_STATUS not like", value, "indexStatus");
            return (Criteria) this;
        }

        public Criteria andIndexStatusIn(List<String> values) {
            addCriterion("INDEX_STATUS in", values, "indexStatus");
            return (Criteria) this;
        }

        public Criteria andIndexStatusNotIn(List<String> values) {
            addCriterion("INDEX_STATUS not in", values, "indexStatus");
            return (Criteria) this;
        }

        public Criteria andIndexStatusBetween(String value1, String value2) {
            addCriterion("INDEX_STATUS between", value1, value2, "indexStatus");
            return (Criteria) this;
        }

        public Criteria andIndexStatusNotBetween(String value1, String value2) {
            addCriterion("INDEX_STATUS not between", value1, value2, "indexStatus");
            return (Criteria) this;
        }

        public Criteria andConfigIfActiveIsNull() {
            addCriterion("CONFIG_IF_ACTIVE is null");
            return (Criteria) this;
        }

        public Criteria andConfigIfActiveIsNotNull() {
            addCriterion("CONFIG_IF_ACTIVE is not null");
            return (Criteria) this;
        }

        public Criteria andConfigIfActiveEqualTo(String value) {
            addCriterion("CONFIG_IF_ACTIVE =", value, "configIfActive");
            return (Criteria) this;
        }

        public Criteria andConfigIfActiveNotEqualTo(String value) {
            addCriterion("CONFIG_IF_ACTIVE <>", value, "configIfActive");
            return (Criteria) this;
        }

        public Criteria andConfigIfActiveGreaterThan(String value) {
            addCriterion("CONFIG_IF_ACTIVE >", value, "configIfActive");
            return (Criteria) this;
        }

        public Criteria andConfigIfActiveGreaterThanOrEqualTo(String value) {
            addCriterion("CONFIG_IF_ACTIVE >=", value, "configIfActive");
            return (Criteria) this;
        }

        public Criteria andConfigIfActiveLessThan(String value) {
            addCriterion("CONFIG_IF_ACTIVE <", value, "configIfActive");
            return (Criteria) this;
        }

        public Criteria andConfigIfActiveLessThanOrEqualTo(String value) {
            addCriterion("CONFIG_IF_ACTIVE <=", value, "configIfActive");
            return (Criteria) this;
        }

        public Criteria andConfigIfActiveLike(String value) {
            addCriterion("CONFIG_IF_ACTIVE like", value, "configIfActive");
            return (Criteria) this;
        }

        public Criteria andConfigIfActiveNotLike(String value) {
            addCriterion("CONFIG_IF_ACTIVE not like", value, "configIfActive");
            return (Criteria) this;
        }

        public Criteria andConfigIfActiveIn(List<String> values) {
            addCriterion("CONFIG_IF_ACTIVE in", values, "configIfActive");
            return (Criteria) this;
        }

        public Criteria andConfigIfActiveNotIn(List<String> values) {
            addCriterion("CONFIG_IF_ACTIVE not in", values, "configIfActive");
            return (Criteria) this;
        }

        public Criteria andConfigIfActiveBetween(String value1, String value2) {
            addCriterion("CONFIG_IF_ACTIVE between", value1, value2, "configIfActive");
            return (Criteria) this;
        }

        public Criteria andConfigIfActiveNotBetween(String value1, String value2) {
            addCriterion("CONFIG_IF_ACTIVE not between", value1, value2, "configIfActive");
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

        public Criteria andConfigIfMultilanIsNull() {
            addCriterion("CONFIG_IF_MULTILAN is null");
            return (Criteria) this;
        }

        public Criteria andConfigIfMultilanIsNotNull() {
            addCriterion("CONFIG_IF_MULTILAN is not null");
            return (Criteria) this;
        }

        public Criteria andConfigIfMultilanEqualTo(String value) {
            addCriterion("CONFIG_IF_MULTILAN =", value, "configIfMultilan");
            return (Criteria) this;
        }

        public Criteria andConfigIfMultilanNotEqualTo(String value) {
            addCriterion("CONFIG_IF_MULTILAN <>", value, "configIfMultilan");
            return (Criteria) this;
        }

        public Criteria andConfigIfMultilanGreaterThan(String value) {
            addCriterion("CONFIG_IF_MULTILAN >", value, "configIfMultilan");
            return (Criteria) this;
        }

        public Criteria andConfigIfMultilanGreaterThanOrEqualTo(String value) {
            addCriterion("CONFIG_IF_MULTILAN >=", value, "configIfMultilan");
            return (Criteria) this;
        }

        public Criteria andConfigIfMultilanLessThan(String value) {
            addCriterion("CONFIG_IF_MULTILAN <", value, "configIfMultilan");
            return (Criteria) this;
        }

        public Criteria andConfigIfMultilanLessThanOrEqualTo(String value) {
            addCriterion("CONFIG_IF_MULTILAN <=", value, "configIfMultilan");
            return (Criteria) this;
        }

        public Criteria andConfigIfMultilanLike(String value) {
            addCriterion("CONFIG_IF_MULTILAN like", value, "configIfMultilan");
            return (Criteria) this;
        }

        public Criteria andConfigIfMultilanNotLike(String value) {
            addCriterion("CONFIG_IF_MULTILAN not like", value, "configIfMultilan");
            return (Criteria) this;
        }

        public Criteria andConfigIfMultilanIn(List<String> values) {
            addCriterion("CONFIG_IF_MULTILAN in", values, "configIfMultilan");
            return (Criteria) this;
        }

        public Criteria andConfigIfMultilanNotIn(List<String> values) {
            addCriterion("CONFIG_IF_MULTILAN not in", values, "configIfMultilan");
            return (Criteria) this;
        }

        public Criteria andConfigIfMultilanBetween(String value1, String value2) {
            addCriterion("CONFIG_IF_MULTILAN between", value1, value2, "configIfMultilan");
            return (Criteria) this;
        }

        public Criteria andConfigIfMultilanNotBetween(String value1, String value2) {
            addCriterion("CONFIG_IF_MULTILAN not between", value1, value2, "configIfMultilan");
            return (Criteria) this;
        }

        public Criteria andLansIsNull() {
            addCriterion("LANS is null");
            return (Criteria) this;
        }

        public Criteria andLansIsNotNull() {
            addCriterion("LANS is not null");
            return (Criteria) this;
        }

        public Criteria andLansEqualTo(String value) {
            addCriterion("LANS =", value, "lans");
            return (Criteria) this;
        }

        public Criteria andLansNotEqualTo(String value) {
            addCriterion("LANS <>", value, "lans");
            return (Criteria) this;
        }

        public Criteria andLansGreaterThan(String value) {
            addCriterion("LANS >", value, "lans");
            return (Criteria) this;
        }

        public Criteria andLansGreaterThanOrEqualTo(String value) {
            addCriterion("LANS >=", value, "lans");
            return (Criteria) this;
        }

        public Criteria andLansLessThan(String value) {
            addCriterion("LANS <", value, "lans");
            return (Criteria) this;
        }

        public Criteria andLansLessThanOrEqualTo(String value) {
            addCriterion("LANS <=", value, "lans");
            return (Criteria) this;
        }

        public Criteria andLansLike(String value) {
            addCriterion("LANS like", value, "lans");
            return (Criteria) this;
        }

        public Criteria andLansNotLike(String value) {
            addCriterion("LANS not like", value, "lans");
            return (Criteria) this;
        }

        public Criteria andLansIn(List<String> values) {
            addCriterion("LANS in", values, "lans");
            return (Criteria) this;
        }

        public Criteria andLansNotIn(List<String> values) {
            addCriterion("LANS not in", values, "lans");
            return (Criteria) this;
        }

        public Criteria andLansBetween(String value1, String value2) {
            addCriterion("LANS between", value1, value2, "lans");
            return (Criteria) this;
        }

        public Criteria andLansNotBetween(String value1, String value2) {
            addCriterion("LANS not between", value1, value2, "lans");
            return (Criteria) this;
        }

        public Criteria andConfigParamsIsNull() {
            addCriterion("CONFIG_PARAMS is null");
            return (Criteria) this;
        }

        public Criteria andConfigParamsIsNotNull() {
            addCriterion("CONFIG_PARAMS is not null");
            return (Criteria) this;
        }

        public Criteria andConfigParamsEqualTo(String value) {
            addCriterion("CONFIG_PARAMS =", value, "configParams");
            return (Criteria) this;
        }

        public Criteria andConfigParamsNotEqualTo(String value) {
            addCriterion("CONFIG_PARAMS <>", value, "configParams");
            return (Criteria) this;
        }

        public Criteria andConfigParamsGreaterThan(String value) {
            addCriterion("CONFIG_PARAMS >", value, "configParams");
            return (Criteria) this;
        }

        public Criteria andConfigParamsGreaterThanOrEqualTo(String value) {
            addCriterion("CONFIG_PARAMS >=", value, "configParams");
            return (Criteria) this;
        }

        public Criteria andConfigParamsLessThan(String value) {
            addCriterion("CONFIG_PARAMS <", value, "configParams");
            return (Criteria) this;
        }

        public Criteria andConfigParamsLessThanOrEqualTo(String value) {
            addCriterion("CONFIG_PARAMS <=", value, "configParams");
            return (Criteria) this;
        }

        public Criteria andConfigParamsLike(String value) {
            addCriterion("CONFIG_PARAMS like", value, "configParams");
            return (Criteria) this;
        }

        public Criteria andConfigParamsNotLike(String value) {
            addCriterion("CONFIG_PARAMS not like", value, "configParams");
            return (Criteria) this;
        }

        public Criteria andConfigParamsIn(List<String> values) {
            addCriterion("CONFIG_PARAMS in", values, "configParams");
            return (Criteria) this;
        }

        public Criteria andConfigParamsNotIn(List<String> values) {
            addCriterion("CONFIG_PARAMS not in", values, "configParams");
            return (Criteria) this;
        }

        public Criteria andConfigParamsBetween(String value1, String value2) {
            addCriterion("CONFIG_PARAMS between", value1, value2, "configParams");
            return (Criteria) this;
        }

        public Criteria andConfigParamsNotBetween(String value1, String value2) {
            addCriterion("CONFIG_PARAMS not between", value1, value2, "configParams");
            return (Criteria) this;
        }

        public Criteria andLanFieldIsNull() {
            addCriterion("LAN_FIELD is null");
            return (Criteria) this;
        }

        public Criteria andLanFieldIsNotNull() {
            addCriterion("LAN_FIELD is not null");
            return (Criteria) this;
        }

        public Criteria andLanFieldEqualTo(String value) {
            addCriterion("LAN_FIELD =", value, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldNotEqualTo(String value) {
            addCriterion("LAN_FIELD <>", value, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldGreaterThan(String value) {
            addCriterion("LAN_FIELD >", value, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldGreaterThanOrEqualTo(String value) {
            addCriterion("LAN_FIELD >=", value, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldLessThan(String value) {
            addCriterion("LAN_FIELD <", value, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldLessThanOrEqualTo(String value) {
            addCriterion("LAN_FIELD <=", value, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldLike(String value) {
            addCriterion("LAN_FIELD like", value, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldNotLike(String value) {
            addCriterion("LAN_FIELD not like", value, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldIn(List<String> values) {
            addCriterion("LAN_FIELD in", values, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldNotIn(List<String> values) {
            addCriterion("LAN_FIELD not in", values, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldBetween(String value1, String value2) {
            addCriterion("LAN_FIELD between", value1, value2, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldNotBetween(String value1, String value2) {
            addCriterion("LAN_FIELD not between", value1, value2, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeIsNull() {
            addCriterion("LAN_FIELD_FIELD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeIsNotNull() {
            addCriterion("LAN_FIELD_FIELD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeEqualTo(String value) {
            addCriterion("LAN_FIELD_FIELD_TYPE =", value, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeNotEqualTo(String value) {
            addCriterion("LAN_FIELD_FIELD_TYPE <>", value, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeGreaterThan(String value) {
            addCriterion("LAN_FIELD_FIELD_TYPE >", value, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeGreaterThanOrEqualTo(String value) {
            addCriterion("LAN_FIELD_FIELD_TYPE >=", value, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeLessThan(String value) {
            addCriterion("LAN_FIELD_FIELD_TYPE <", value, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeLessThanOrEqualTo(String value) {
            addCriterion("LAN_FIELD_FIELD_TYPE <=", value, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeLike(String value) {
            addCriterion("LAN_FIELD_FIELD_TYPE like", value, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeNotLike(String value) {
            addCriterion("LAN_FIELD_FIELD_TYPE not like", value, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeIn(List<String> values) {
            addCriterion("LAN_FIELD_FIELD_TYPE in", values, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeNotIn(List<String> values) {
            addCriterion("LAN_FIELD_FIELD_TYPE not in", values, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeBetween(String value1, String value2) {
            addCriterion("LAN_FIELD_FIELD_TYPE between", value1, value2, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeNotBetween(String value1, String value2) {
            addCriterion("LAN_FIELD_FIELD_TYPE not between", value1, value2, "lanFieldFieldType");
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