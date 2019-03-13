package com.zengshi.ecp.sys.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AppVersionCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public AppVersionCriteria() {
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

        public Criteria andVerProgramIsNull() {
            addCriterion("VER_PROGRAM is null");
            return (Criteria) this;
        }

        public Criteria andVerProgramIsNotNull() {
            addCriterion("VER_PROGRAM is not null");
            return (Criteria) this;
        }

        public Criteria andVerProgramEqualTo(String value) {
            addCriterion("VER_PROGRAM =", value, "verProgram");
            return (Criteria) this;
        }

        public Criteria andVerProgramNotEqualTo(String value) {
            addCriterion("VER_PROGRAM <>", value, "verProgram");
            return (Criteria) this;
        }

        public Criteria andVerProgramGreaterThan(String value) {
            addCriterion("VER_PROGRAM >", value, "verProgram");
            return (Criteria) this;
        }

        public Criteria andVerProgramGreaterThanOrEqualTo(String value) {
            addCriterion("VER_PROGRAM >=", value, "verProgram");
            return (Criteria) this;
        }

        public Criteria andVerProgramLessThan(String value) {
            addCriterion("VER_PROGRAM <", value, "verProgram");
            return (Criteria) this;
        }

        public Criteria andVerProgramLessThanOrEqualTo(String value) {
            addCriterion("VER_PROGRAM <=", value, "verProgram");
            return (Criteria) this;
        }

        public Criteria andVerProgramLike(String value) {
            addCriterion("VER_PROGRAM like", value, "verProgram");
            return (Criteria) this;
        }

        public Criteria andVerProgramNotLike(String value) {
            addCriterion("VER_PROGRAM not like", value, "verProgram");
            return (Criteria) this;
        }

        public Criteria andVerProgramIn(List<String> values) {
            addCriterion("VER_PROGRAM in", values, "verProgram");
            return (Criteria) this;
        }

        public Criteria andVerProgramNotIn(List<String> values) {
            addCriterion("VER_PROGRAM not in", values, "verProgram");
            return (Criteria) this;
        }

        public Criteria andVerProgramBetween(String value1, String value2) {
            addCriterion("VER_PROGRAM between", value1, value2, "verProgram");
            return (Criteria) this;
        }

        public Criteria andVerProgramNotBetween(String value1, String value2) {
            addCriterion("VER_PROGRAM not between", value1, value2, "verProgram");
            return (Criteria) this;
        }

        public Criteria andVerPublishNoIsNull() {
            addCriterion("VER_PUBLISH_NO is null");
            return (Criteria) this;
        }

        public Criteria andVerPublishNoIsNotNull() {
            addCriterion("VER_PUBLISH_NO is not null");
            return (Criteria) this;
        }

        public Criteria andVerPublishNoEqualTo(String value) {
            addCriterion("VER_PUBLISH_NO =", value, "verPublishNo");
            return (Criteria) this;
        }

        public Criteria andVerPublishNoNotEqualTo(String value) {
            addCriterion("VER_PUBLISH_NO <>", value, "verPublishNo");
            return (Criteria) this;
        }

        public Criteria andVerPublishNoGreaterThan(String value) {
            addCriterion("VER_PUBLISH_NO >", value, "verPublishNo");
            return (Criteria) this;
        }

        public Criteria andVerPublishNoGreaterThanOrEqualTo(String value) {
            addCriterion("VER_PUBLISH_NO >=", value, "verPublishNo");
            return (Criteria) this;
        }

        public Criteria andVerPublishNoLessThan(String value) {
            addCriterion("VER_PUBLISH_NO <", value, "verPublishNo");
            return (Criteria) this;
        }

        public Criteria andVerPublishNoLessThanOrEqualTo(String value) {
            addCriterion("VER_PUBLISH_NO <=", value, "verPublishNo");
            return (Criteria) this;
        }

        public Criteria andVerPublishNoLike(String value) {
            addCriterion("VER_PUBLISH_NO like", value, "verPublishNo");
            return (Criteria) this;
        }

        public Criteria andVerPublishNoNotLike(String value) {
            addCriterion("VER_PUBLISH_NO not like", value, "verPublishNo");
            return (Criteria) this;
        }

        public Criteria andVerPublishNoIn(List<String> values) {
            addCriterion("VER_PUBLISH_NO in", values, "verPublishNo");
            return (Criteria) this;
        }

        public Criteria andVerPublishNoNotIn(List<String> values) {
            addCriterion("VER_PUBLISH_NO not in", values, "verPublishNo");
            return (Criteria) this;
        }

        public Criteria andVerPublishNoBetween(String value1, String value2) {
            addCriterion("VER_PUBLISH_NO between", value1, value2, "verPublishNo");
            return (Criteria) this;
        }

        public Criteria andVerPublishNoNotBetween(String value1, String value2) {
            addCriterion("VER_PUBLISH_NO not between", value1, value2, "verPublishNo");
            return (Criteria) this;
        }

        public Criteria andVerNoIsNull() {
            addCriterion("VER_NO is null");
            return (Criteria) this;
        }

        public Criteria andVerNoIsNotNull() {
            addCriterion("VER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andVerNoEqualTo(Long value) {
            addCriterion("VER_NO =", value, "verNo");
            return (Criteria) this;
        }

        public Criteria andVerNoNotEqualTo(Long value) {
            addCriterion("VER_NO <>", value, "verNo");
            return (Criteria) this;
        }

        public Criteria andVerNoGreaterThan(Long value) {
            addCriterion("VER_NO >", value, "verNo");
            return (Criteria) this;
        }

        public Criteria andVerNoGreaterThanOrEqualTo(Long value) {
            addCriterion("VER_NO >=", value, "verNo");
            return (Criteria) this;
        }

        public Criteria andVerNoLessThan(Long value) {
            addCriterion("VER_NO <", value, "verNo");
            return (Criteria) this;
        }

        public Criteria andVerNoLessThanOrEqualTo(Long value) {
            addCriterion("VER_NO <=", value, "verNo");
            return (Criteria) this;
        }

        public Criteria andVerNoIn(List<Long> values) {
            addCriterion("VER_NO in", values, "verNo");
            return (Criteria) this;
        }

        public Criteria andVerNoNotIn(List<Long> values) {
            addCriterion("VER_NO not in", values, "verNo");
            return (Criteria) this;
        }

        public Criteria andVerNoBetween(Long value1, Long value2) {
            addCriterion("VER_NO between", value1, value2, "verNo");
            return (Criteria) this;
        }

        public Criteria andVerNoNotBetween(Long value1, Long value2) {
            addCriterion("VER_NO not between", value1, value2, "verNo");
            return (Criteria) this;
        }

        public Criteria andIfForceIsNull() {
            addCriterion("IF_FORCE is null");
            return (Criteria) this;
        }

        public Criteria andIfForceIsNotNull() {
            addCriterion("IF_FORCE is not null");
            return (Criteria) this;
        }

        public Criteria andIfForceEqualTo(String value) {
            addCriterion("IF_FORCE =", value, "ifForce");
            return (Criteria) this;
        }

        public Criteria andIfForceNotEqualTo(String value) {
            addCriterion("IF_FORCE <>", value, "ifForce");
            return (Criteria) this;
        }

        public Criteria andIfForceGreaterThan(String value) {
            addCriterion("IF_FORCE >", value, "ifForce");
            return (Criteria) this;
        }

        public Criteria andIfForceGreaterThanOrEqualTo(String value) {
            addCriterion("IF_FORCE >=", value, "ifForce");
            return (Criteria) this;
        }

        public Criteria andIfForceLessThan(String value) {
            addCriterion("IF_FORCE <", value, "ifForce");
            return (Criteria) this;
        }

        public Criteria andIfForceLessThanOrEqualTo(String value) {
            addCriterion("IF_FORCE <=", value, "ifForce");
            return (Criteria) this;
        }

        public Criteria andIfForceLike(String value) {
            addCriterion("IF_FORCE like", value, "ifForce");
            return (Criteria) this;
        }

        public Criteria andIfForceNotLike(String value) {
            addCriterion("IF_FORCE not like", value, "ifForce");
            return (Criteria) this;
        }

        public Criteria andIfForceIn(List<String> values) {
            addCriterion("IF_FORCE in", values, "ifForce");
            return (Criteria) this;
        }

        public Criteria andIfForceNotIn(List<String> values) {
            addCriterion("IF_FORCE not in", values, "ifForce");
            return (Criteria) this;
        }

        public Criteria andIfForceBetween(String value1, String value2) {
            addCriterion("IF_FORCE between", value1, value2, "ifForce");
            return (Criteria) this;
        }

        public Criteria andIfForceNotBetween(String value1, String value2) {
            addCriterion("IF_FORCE not between", value1, value2, "ifForce");
            return (Criteria) this;
        }

        public Criteria andVerDetailIsNull() {
            addCriterion("VER_DETAIL is null");
            return (Criteria) this;
        }

        public Criteria andVerDetailIsNotNull() {
            addCriterion("VER_DETAIL is not null");
            return (Criteria) this;
        }

        public Criteria andVerDetailEqualTo(String value) {
            addCriterion("VER_DETAIL =", value, "verDetail");
            return (Criteria) this;
        }

        public Criteria andVerDetailNotEqualTo(String value) {
            addCriterion("VER_DETAIL <>", value, "verDetail");
            return (Criteria) this;
        }

        public Criteria andVerDetailGreaterThan(String value) {
            addCriterion("VER_DETAIL >", value, "verDetail");
            return (Criteria) this;
        }

        public Criteria andVerDetailGreaterThanOrEqualTo(String value) {
            addCriterion("VER_DETAIL >=", value, "verDetail");
            return (Criteria) this;
        }

        public Criteria andVerDetailLessThan(String value) {
            addCriterion("VER_DETAIL <", value, "verDetail");
            return (Criteria) this;
        }

        public Criteria andVerDetailLessThanOrEqualTo(String value) {
            addCriterion("VER_DETAIL <=", value, "verDetail");
            return (Criteria) this;
        }

        public Criteria andVerDetailLike(String value) {
            addCriterion("VER_DETAIL like", value, "verDetail");
            return (Criteria) this;
        }

        public Criteria andVerDetailNotLike(String value) {
            addCriterion("VER_DETAIL not like", value, "verDetail");
            return (Criteria) this;
        }

        public Criteria andVerDetailIn(List<String> values) {
            addCriterion("VER_DETAIL in", values, "verDetail");
            return (Criteria) this;
        }

        public Criteria andVerDetailNotIn(List<String> values) {
            addCriterion("VER_DETAIL not in", values, "verDetail");
            return (Criteria) this;
        }

        public Criteria andVerDetailBetween(String value1, String value2) {
            addCriterion("VER_DETAIL between", value1, value2, "verDetail");
            return (Criteria) this;
        }

        public Criteria andVerDetailNotBetween(String value1, String value2) {
            addCriterion("VER_DETAIL not between", value1, value2, "verDetail");
            return (Criteria) this;
        }

        public Criteria andVerOsIsNull() {
            addCriterion("VER_OS is null");
            return (Criteria) this;
        }

        public Criteria andVerOsIsNotNull() {
            addCriterion("VER_OS is not null");
            return (Criteria) this;
        }

        public Criteria andVerOsEqualTo(String value) {
            addCriterion("VER_OS =", value, "verOs");
            return (Criteria) this;
        }

        public Criteria andVerOsNotEqualTo(String value) {
            addCriterion("VER_OS <>", value, "verOs");
            return (Criteria) this;
        }

        public Criteria andVerOsGreaterThan(String value) {
            addCriterion("VER_OS >", value, "verOs");
            return (Criteria) this;
        }

        public Criteria andVerOsGreaterThanOrEqualTo(String value) {
            addCriterion("VER_OS >=", value, "verOs");
            return (Criteria) this;
        }

        public Criteria andVerOsLessThan(String value) {
            addCriterion("VER_OS <", value, "verOs");
            return (Criteria) this;
        }

        public Criteria andVerOsLessThanOrEqualTo(String value) {
            addCriterion("VER_OS <=", value, "verOs");
            return (Criteria) this;
        }

        public Criteria andVerOsLike(String value) {
            addCriterion("VER_OS like", value, "verOs");
            return (Criteria) this;
        }

        public Criteria andVerOsNotLike(String value) {
            addCriterion("VER_OS not like", value, "verOs");
            return (Criteria) this;
        }

        public Criteria andVerOsIn(List<String> values) {
            addCriterion("VER_OS in", values, "verOs");
            return (Criteria) this;
        }

        public Criteria andVerOsNotIn(List<String> values) {
            addCriterion("VER_OS not in", values, "verOs");
            return (Criteria) this;
        }

        public Criteria andVerOsBetween(String value1, String value2) {
            addCriterion("VER_OS between", value1, value2, "verOs");
            return (Criteria) this;
        }

        public Criteria andVerOsNotBetween(String value1, String value2) {
            addCriterion("VER_OS not between", value1, value2, "verOs");
            return (Criteria) this;
        }

        public Criteria andVerAdaptOsIsNull() {
            addCriterion("VER_ADAPT_OS is null");
            return (Criteria) this;
        }

        public Criteria andVerAdaptOsIsNotNull() {
            addCriterion("VER_ADAPT_OS is not null");
            return (Criteria) this;
        }

        public Criteria andVerAdaptOsEqualTo(String value) {
            addCriterion("VER_ADAPT_OS =", value, "verAdaptOs");
            return (Criteria) this;
        }

        public Criteria andVerAdaptOsNotEqualTo(String value) {
            addCriterion("VER_ADAPT_OS <>", value, "verAdaptOs");
            return (Criteria) this;
        }

        public Criteria andVerAdaptOsGreaterThan(String value) {
            addCriterion("VER_ADAPT_OS >", value, "verAdaptOs");
            return (Criteria) this;
        }

        public Criteria andVerAdaptOsGreaterThanOrEqualTo(String value) {
            addCriterion("VER_ADAPT_OS >=", value, "verAdaptOs");
            return (Criteria) this;
        }

        public Criteria andVerAdaptOsLessThan(String value) {
            addCriterion("VER_ADAPT_OS <", value, "verAdaptOs");
            return (Criteria) this;
        }

        public Criteria andVerAdaptOsLessThanOrEqualTo(String value) {
            addCriterion("VER_ADAPT_OS <=", value, "verAdaptOs");
            return (Criteria) this;
        }

        public Criteria andVerAdaptOsLike(String value) {
            addCriterion("VER_ADAPT_OS like", value, "verAdaptOs");
            return (Criteria) this;
        }

        public Criteria andVerAdaptOsNotLike(String value) {
            addCriterion("VER_ADAPT_OS not like", value, "verAdaptOs");
            return (Criteria) this;
        }

        public Criteria andVerAdaptOsIn(List<String> values) {
            addCriterion("VER_ADAPT_OS in", values, "verAdaptOs");
            return (Criteria) this;
        }

        public Criteria andVerAdaptOsNotIn(List<String> values) {
            addCriterion("VER_ADAPT_OS not in", values, "verAdaptOs");
            return (Criteria) this;
        }

        public Criteria andVerAdaptOsBetween(String value1, String value2) {
            addCriterion("VER_ADAPT_OS between", value1, value2, "verAdaptOs");
            return (Criteria) this;
        }

        public Criteria andVerAdaptOsNotBetween(String value1, String value2) {
            addCriterion("VER_ADAPT_OS not between", value1, value2, "verAdaptOs");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNull() {
            addCriterion("PUBLISH_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNotNull() {
            addCriterion("PUBLISH_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeEqualTo(Timestamp value) {
            addCriterion("PUBLISH_TIME =", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotEqualTo(Timestamp value) {
            addCriterion("PUBLISH_TIME <>", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThan(Timestamp value) {
            addCriterion("PUBLISH_TIME >", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("PUBLISH_TIME >=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThan(Timestamp value) {
            addCriterion("PUBLISH_TIME <", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("PUBLISH_TIME <=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIn(List<Timestamp> values) {
            addCriterion("PUBLISH_TIME in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotIn(List<Timestamp> values) {
            addCriterion("PUBLISH_TIME not in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PUBLISH_TIME between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PUBLISH_TIME not between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andVerUrlIsNull() {
            addCriterion("VER_URL is null");
            return (Criteria) this;
        }

        public Criteria andVerUrlIsNotNull() {
            addCriterion("VER_URL is not null");
            return (Criteria) this;
        }

        public Criteria andVerUrlEqualTo(String value) {
            addCriterion("VER_URL =", value, "verUrl");
            return (Criteria) this;
        }

        public Criteria andVerUrlNotEqualTo(String value) {
            addCriterion("VER_URL <>", value, "verUrl");
            return (Criteria) this;
        }

        public Criteria andVerUrlGreaterThan(String value) {
            addCriterion("VER_URL >", value, "verUrl");
            return (Criteria) this;
        }

        public Criteria andVerUrlGreaterThanOrEqualTo(String value) {
            addCriterion("VER_URL >=", value, "verUrl");
            return (Criteria) this;
        }

        public Criteria andVerUrlLessThan(String value) {
            addCriterion("VER_URL <", value, "verUrl");
            return (Criteria) this;
        }

        public Criteria andVerUrlLessThanOrEqualTo(String value) {
            addCriterion("VER_URL <=", value, "verUrl");
            return (Criteria) this;
        }

        public Criteria andVerUrlLike(String value) {
            addCriterion("VER_URL like", value, "verUrl");
            return (Criteria) this;
        }

        public Criteria andVerUrlNotLike(String value) {
            addCriterion("VER_URL not like", value, "verUrl");
            return (Criteria) this;
        }

        public Criteria andVerUrlIn(List<String> values) {
            addCriterion("VER_URL in", values, "verUrl");
            return (Criteria) this;
        }

        public Criteria andVerUrlNotIn(List<String> values) {
            addCriterion("VER_URL not in", values, "verUrl");
            return (Criteria) this;
        }

        public Criteria andVerUrlBetween(String value1, String value2) {
            addCriterion("VER_URL between", value1, value2, "verUrl");
            return (Criteria) this;
        }

        public Criteria andVerUrlNotBetween(String value1, String value2) {
            addCriterion("VER_URL not between", value1, value2, "verUrl");
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

        public Criteria andPublishStaffIsNull() {
            addCriterion("PUBLISH_STAFF is null");
            return (Criteria) this;
        }

        public Criteria andPublishStaffIsNotNull() {
            addCriterion("PUBLISH_STAFF is not null");
            return (Criteria) this;
        }

        public Criteria andPublishStaffEqualTo(Long value) {
            addCriterion("PUBLISH_STAFF =", value, "publishStaff");
            return (Criteria) this;
        }

        public Criteria andPublishStaffNotEqualTo(Long value) {
            addCriterion("PUBLISH_STAFF <>", value, "publishStaff");
            return (Criteria) this;
        }

        public Criteria andPublishStaffGreaterThan(Long value) {
            addCriterion("PUBLISH_STAFF >", value, "publishStaff");
            return (Criteria) this;
        }

        public Criteria andPublishStaffGreaterThanOrEqualTo(Long value) {
            addCriterion("PUBLISH_STAFF >=", value, "publishStaff");
            return (Criteria) this;
        }

        public Criteria andPublishStaffLessThan(Long value) {
            addCriterion("PUBLISH_STAFF <", value, "publishStaff");
            return (Criteria) this;
        }

        public Criteria andPublishStaffLessThanOrEqualTo(Long value) {
            addCriterion("PUBLISH_STAFF <=", value, "publishStaff");
            return (Criteria) this;
        }

        public Criteria andPublishStaffIn(List<Long> values) {
            addCriterion("PUBLISH_STAFF in", values, "publishStaff");
            return (Criteria) this;
        }

        public Criteria andPublishStaffNotIn(List<Long> values) {
            addCriterion("PUBLISH_STAFF not in", values, "publishStaff");
            return (Criteria) this;
        }

        public Criteria andPublishStaffBetween(Long value1, Long value2) {
            addCriterion("PUBLISH_STAFF between", value1, value2, "publishStaff");
            return (Criteria) this;
        }

        public Criteria andPublishStaffNotBetween(Long value1, Long value2) {
            addCriterion("PUBLISH_STAFF not between", value1, value2, "publishStaff");
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
