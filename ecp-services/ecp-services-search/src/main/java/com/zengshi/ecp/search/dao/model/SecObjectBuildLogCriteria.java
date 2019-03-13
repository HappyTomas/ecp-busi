package com.zengshi.ecp.search.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SecObjectBuildLogCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public SecObjectBuildLogCriteria() {
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

        public Criteria andConfigIdIsNull() {
            addCriterion("CONFIG_ID is null");
            return (Criteria) this;
        }

        public Criteria andConfigIdIsNotNull() {
            addCriterion("CONFIG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andConfigIdEqualTo(Long value) {
            addCriterion("CONFIG_ID =", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdNotEqualTo(Long value) {
            addCriterion("CONFIG_ID <>", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdGreaterThan(Long value) {
            addCriterion("CONFIG_ID >", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CONFIG_ID >=", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdLessThan(Long value) {
            addCriterion("CONFIG_ID <", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdLessThanOrEqualTo(Long value) {
            addCriterion("CONFIG_ID <=", value, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdIn(List<Long> values) {
            addCriterion("CONFIG_ID in", values, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdNotIn(List<Long> values) {
            addCriterion("CONFIG_ID not in", values, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdBetween(Long value1, Long value2) {
            addCriterion("CONFIG_ID between", value1, value2, "configId");
            return (Criteria) this;
        }

        public Criteria andConfigIdNotBetween(Long value1, Long value2) {
            addCriterion("CONFIG_ID not between", value1, value2, "configId");
            return (Criteria) this;
        }

        public Criteria andObjectIdIsNull() {
            addCriterion("OBJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andObjectIdIsNotNull() {
            addCriterion("OBJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andObjectIdEqualTo(Long value) {
            addCriterion("OBJECT_ID =", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotEqualTo(Long value) {
            addCriterion("OBJECT_ID <>", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdGreaterThan(Long value) {
            addCriterion("OBJECT_ID >", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("OBJECT_ID >=", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdLessThan(Long value) {
            addCriterion("OBJECT_ID <", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdLessThanOrEqualTo(Long value) {
            addCriterion("OBJECT_ID <=", value, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdIn(List<Long> values) {
            addCriterion("OBJECT_ID in", values, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotIn(List<Long> values) {
            addCriterion("OBJECT_ID not in", values, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdBetween(Long value1, Long value2) {
            addCriterion("OBJECT_ID between", value1, value2, "objectId");
            return (Criteria) this;
        }

        public Criteria andObjectIdNotBetween(Long value1, Long value2) {
            addCriterion("OBJECT_ID not between", value1, value2, "objectId");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Timestamp value) {
            addCriterion("START_TIME =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Timestamp value) {
            addCriterion("START_TIME <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Timestamp value) {
            addCriterion("START_TIME >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("START_TIME >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Timestamp value) {
            addCriterion("START_TIME <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("START_TIME <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Timestamp> values) {
            addCriterion("START_TIME in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Timestamp> values) {
            addCriterion("START_TIME not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("START_TIME between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("START_TIME not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andNowTimeIsNull() {
            addCriterion("NOW_TIME is null");
            return (Criteria) this;
        }

        public Criteria andNowTimeIsNotNull() {
            addCriterion("NOW_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andNowTimeEqualTo(Timestamp value) {
            addCriterion("NOW_TIME =", value, "nowTime");
            return (Criteria) this;
        }

        public Criteria andNowTimeNotEqualTo(Timestamp value) {
            addCriterion("NOW_TIME <>", value, "nowTime");
            return (Criteria) this;
        }

        public Criteria andNowTimeGreaterThan(Timestamp value) {
            addCriterion("NOW_TIME >", value, "nowTime");
            return (Criteria) this;
        }

        public Criteria andNowTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("NOW_TIME >=", value, "nowTime");
            return (Criteria) this;
        }

        public Criteria andNowTimeLessThan(Timestamp value) {
            addCriterion("NOW_TIME <", value, "nowTime");
            return (Criteria) this;
        }

        public Criteria andNowTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("NOW_TIME <=", value, "nowTime");
            return (Criteria) this;
        }

        public Criteria andNowTimeIn(List<Timestamp> values) {
            addCriterion("NOW_TIME in", values, "nowTime");
            return (Criteria) this;
        }

        public Criteria andNowTimeNotIn(List<Timestamp> values) {
            addCriterion("NOW_TIME not in", values, "nowTime");
            return (Criteria) this;
        }

        public Criteria andNowTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("NOW_TIME between", value1, value2, "nowTime");
            return (Criteria) this;
        }

        public Criteria andNowTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("NOW_TIME not between", value1, value2, "nowTime");
            return (Criteria) this;
        }

        public Criteria andDatacountIsNull() {
            addCriterion("DATACOUNT is null");
            return (Criteria) this;
        }

        public Criteria andDatacountIsNotNull() {
            addCriterion("DATACOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDatacountEqualTo(Long value) {
            addCriterion("DATACOUNT =", value, "datacount");
            return (Criteria) this;
        }

        public Criteria andDatacountNotEqualTo(Long value) {
            addCriterion("DATACOUNT <>", value, "datacount");
            return (Criteria) this;
        }

        public Criteria andDatacountGreaterThan(Long value) {
            addCriterion("DATACOUNT >", value, "datacount");
            return (Criteria) this;
        }

        public Criteria andDatacountGreaterThanOrEqualTo(Long value) {
            addCriterion("DATACOUNT >=", value, "datacount");
            return (Criteria) this;
        }

        public Criteria andDatacountLessThan(Long value) {
            addCriterion("DATACOUNT <", value, "datacount");
            return (Criteria) this;
        }

        public Criteria andDatacountLessThanOrEqualTo(Long value) {
            addCriterion("DATACOUNT <=", value, "datacount");
            return (Criteria) this;
        }

        public Criteria andDatacountIn(List<Long> values) {
            addCriterion("DATACOUNT in", values, "datacount");
            return (Criteria) this;
        }

        public Criteria andDatacountNotIn(List<Long> values) {
            addCriterion("DATACOUNT not in", values, "datacount");
            return (Criteria) this;
        }

        public Criteria andDatacountBetween(Long value1, Long value2) {
            addCriterion("DATACOUNT between", value1, value2, "datacount");
            return (Criteria) this;
        }

        public Criteria andDatacountNotBetween(Long value1, Long value2) {
            addCriterion("DATACOUNT not between", value1, value2, "datacount");
            return (Criteria) this;
        }

        public Criteria andFailurecountIsNull() {
            addCriterion("FAILURECOUNT is null");
            return (Criteria) this;
        }

        public Criteria andFailurecountIsNotNull() {
            addCriterion("FAILURECOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andFailurecountEqualTo(Long value) {
            addCriterion("FAILURECOUNT =", value, "failurecount");
            return (Criteria) this;
        }

        public Criteria andFailurecountNotEqualTo(Long value) {
            addCriterion("FAILURECOUNT <>", value, "failurecount");
            return (Criteria) this;
        }

        public Criteria andFailurecountGreaterThan(Long value) {
            addCriterion("FAILURECOUNT >", value, "failurecount");
            return (Criteria) this;
        }

        public Criteria andFailurecountGreaterThanOrEqualTo(Long value) {
            addCriterion("FAILURECOUNT >=", value, "failurecount");
            return (Criteria) this;
        }

        public Criteria andFailurecountLessThan(Long value) {
            addCriterion("FAILURECOUNT <", value, "failurecount");
            return (Criteria) this;
        }

        public Criteria andFailurecountLessThanOrEqualTo(Long value) {
            addCriterion("FAILURECOUNT <=", value, "failurecount");
            return (Criteria) this;
        }

        public Criteria andFailurecountIn(List<Long> values) {
            addCriterion("FAILURECOUNT in", values, "failurecount");
            return (Criteria) this;
        }

        public Criteria andFailurecountNotIn(List<Long> values) {
            addCriterion("FAILURECOUNT not in", values, "failurecount");
            return (Criteria) this;
        }

        public Criteria andFailurecountBetween(Long value1, Long value2) {
            addCriterion("FAILURECOUNT between", value1, value2, "failurecount");
            return (Criteria) this;
        }

        public Criteria andFailurecountNotBetween(Long value1, Long value2) {
            addCriterion("FAILURECOUNT not between", value1, value2, "failurecount");
            return (Criteria) this;
        }

        public Criteria andFailureinfoIsNull() {
            addCriterion("FAILUREINFO is null");
            return (Criteria) this;
        }

        public Criteria andFailureinfoIsNotNull() {
            addCriterion("FAILUREINFO is not null");
            return (Criteria) this;
        }

        public Criteria andFailureinfoEqualTo(String value) {
            addCriterion("FAILUREINFO =", value, "failureinfo");
            return (Criteria) this;
        }

        public Criteria andFailureinfoNotEqualTo(String value) {
            addCriterion("FAILUREINFO <>", value, "failureinfo");
            return (Criteria) this;
        }

        public Criteria andFailureinfoGreaterThan(String value) {
            addCriterion("FAILUREINFO >", value, "failureinfo");
            return (Criteria) this;
        }

        public Criteria andFailureinfoGreaterThanOrEqualTo(String value) {
            addCriterion("FAILUREINFO >=", value, "failureinfo");
            return (Criteria) this;
        }

        public Criteria andFailureinfoLessThan(String value) {
            addCriterion("FAILUREINFO <", value, "failureinfo");
            return (Criteria) this;
        }

        public Criteria andFailureinfoLessThanOrEqualTo(String value) {
            addCriterion("FAILUREINFO <=", value, "failureinfo");
            return (Criteria) this;
        }

        public Criteria andFailureinfoLike(String value) {
            addCriterion("FAILUREINFO like", value, "failureinfo");
            return (Criteria) this;
        }

        public Criteria andFailureinfoNotLike(String value) {
            addCriterion("FAILUREINFO not like", value, "failureinfo");
            return (Criteria) this;
        }

        public Criteria andFailureinfoIn(List<String> values) {
            addCriterion("FAILUREINFO in", values, "failureinfo");
            return (Criteria) this;
        }

        public Criteria andFailureinfoNotIn(List<String> values) {
            addCriterion("FAILUREINFO not in", values, "failureinfo");
            return (Criteria) this;
        }

        public Criteria andFailureinfoBetween(String value1, String value2) {
            addCriterion("FAILUREINFO between", value1, value2, "failureinfo");
            return (Criteria) this;
        }

        public Criteria andFailureinfoNotBetween(String value1, String value2) {
            addCriterion("FAILUREINFO not between", value1, value2, "failureinfo");
            return (Criteria) this;
        }

        public Criteria andSecondsIsNull() {
            addCriterion("SECONDS is null");
            return (Criteria) this;
        }

        public Criteria andSecondsIsNotNull() {
            addCriterion("SECONDS is not null");
            return (Criteria) this;
        }

        public Criteria andSecondsEqualTo(Integer value) {
            addCriterion("SECONDS =", value, "seconds");
            return (Criteria) this;
        }

        public Criteria andSecondsNotEqualTo(Integer value) {
            addCriterion("SECONDS <>", value, "seconds");
            return (Criteria) this;
        }

        public Criteria andSecondsGreaterThan(Integer value) {
            addCriterion("SECONDS >", value, "seconds");
            return (Criteria) this;
        }

        public Criteria andSecondsGreaterThanOrEqualTo(Integer value) {
            addCriterion("SECONDS >=", value, "seconds");
            return (Criteria) this;
        }

        public Criteria andSecondsLessThan(Integer value) {
            addCriterion("SECONDS <", value, "seconds");
            return (Criteria) this;
        }

        public Criteria andSecondsLessThanOrEqualTo(Integer value) {
            addCriterion("SECONDS <=", value, "seconds");
            return (Criteria) this;
        }

        public Criteria andSecondsIn(List<Integer> values) {
            addCriterion("SECONDS in", values, "seconds");
            return (Criteria) this;
        }

        public Criteria andSecondsNotIn(List<Integer> values) {
            addCriterion("SECONDS not in", values, "seconds");
            return (Criteria) this;
        }

        public Criteria andSecondsBetween(Integer value1, Integer value2) {
            addCriterion("SECONDS between", value1, value2, "seconds");
            return (Criteria) this;
        }

        public Criteria andSecondsNotBetween(Integer value1, Integer value2) {
            addCriterion("SECONDS not between", value1, value2, "seconds");
            return (Criteria) this;
        }

        public Criteria andTpsIsNull() {
            addCriterion("TPS is null");
            return (Criteria) this;
        }

        public Criteria andTpsIsNotNull() {
            addCriterion("TPS is not null");
            return (Criteria) this;
        }

        public Criteria andTpsEqualTo(Short value) {
            addCriterion("TPS =", value, "tps");
            return (Criteria) this;
        }

        public Criteria andTpsNotEqualTo(Short value) {
            addCriterion("TPS <>", value, "tps");
            return (Criteria) this;
        }

        public Criteria andTpsGreaterThan(Short value) {
            addCriterion("TPS >", value, "tps");
            return (Criteria) this;
        }

        public Criteria andTpsGreaterThanOrEqualTo(Short value) {
            addCriterion("TPS >=", value, "tps");
            return (Criteria) this;
        }

        public Criteria andTpsLessThan(Short value) {
            addCriterion("TPS <", value, "tps");
            return (Criteria) this;
        }

        public Criteria andTpsLessThanOrEqualTo(Short value) {
            addCriterion("TPS <=", value, "tps");
            return (Criteria) this;
        }

        public Criteria andTpsIn(List<Short> values) {
            addCriterion("TPS in", values, "tps");
            return (Criteria) this;
        }

        public Criteria andTpsNotIn(List<Short> values) {
            addCriterion("TPS not in", values, "tps");
            return (Criteria) this;
        }

        public Criteria andTpsBetween(Short value1, Short value2) {
            addCriterion("TPS between", value1, value2, "tps");
            return (Criteria) this;
        }

        public Criteria andTpsNotBetween(Short value1, Short value2) {
            addCriterion("TPS not between", value1, value2, "tps");
            return (Criteria) this;
        }

        public Criteria andErrorIsNull() {
            addCriterion("ERROR is null");
            return (Criteria) this;
        }

        public Criteria andErrorIsNotNull() {
            addCriterion("ERROR is not null");
            return (Criteria) this;
        }

        public Criteria andErrorEqualTo(String value) {
            addCriterion("ERROR =", value, "error");
            return (Criteria) this;
        }

        public Criteria andErrorNotEqualTo(String value) {
            addCriterion("ERROR <>", value, "error");
            return (Criteria) this;
        }

        public Criteria andErrorGreaterThan(String value) {
            addCriterion("ERROR >", value, "error");
            return (Criteria) this;
        }

        public Criteria andErrorGreaterThanOrEqualTo(String value) {
            addCriterion("ERROR >=", value, "error");
            return (Criteria) this;
        }

        public Criteria andErrorLessThan(String value) {
            addCriterion("ERROR <", value, "error");
            return (Criteria) this;
        }

        public Criteria andErrorLessThanOrEqualTo(String value) {
            addCriterion("ERROR <=", value, "error");
            return (Criteria) this;
        }

        public Criteria andErrorLike(String value) {
            addCriterion("ERROR like", value, "error");
            return (Criteria) this;
        }

        public Criteria andErrorNotLike(String value) {
            addCriterion("ERROR not like", value, "error");
            return (Criteria) this;
        }

        public Criteria andErrorIn(List<String> values) {
            addCriterion("ERROR in", values, "error");
            return (Criteria) this;
        }

        public Criteria andErrorNotIn(List<String> values) {
            addCriterion("ERROR not in", values, "error");
            return (Criteria) this;
        }

        public Criteria andErrorBetween(String value1, String value2) {
            addCriterion("ERROR between", value1, value2, "error");
            return (Criteria) this;
        }

        public Criteria andErrorNotBetween(String value1, String value2) {
            addCriterion("ERROR not between", value1, value2, "error");
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

        public Criteria andArgsIsNull() {
            addCriterion("ARGS is null");
            return (Criteria) this;
        }

        public Criteria andArgsIsNotNull() {
            addCriterion("ARGS is not null");
            return (Criteria) this;
        }

        public Criteria andArgsEqualTo(String value) {
            addCriterion("ARGS =", value, "args");
            return (Criteria) this;
        }

        public Criteria andArgsNotEqualTo(String value) {
            addCriterion("ARGS <>", value, "args");
            return (Criteria) this;
        }

        public Criteria andArgsGreaterThan(String value) {
            addCriterion("ARGS >", value, "args");
            return (Criteria) this;
        }

        public Criteria andArgsGreaterThanOrEqualTo(String value) {
            addCriterion("ARGS >=", value, "args");
            return (Criteria) this;
        }

        public Criteria andArgsLessThan(String value) {
            addCriterion("ARGS <", value, "args");
            return (Criteria) this;
        }

        public Criteria andArgsLessThanOrEqualTo(String value) {
            addCriterion("ARGS <=", value, "args");
            return (Criteria) this;
        }

        public Criteria andArgsLike(String value) {
            addCriterion("ARGS like", value, "args");
            return (Criteria) this;
        }

        public Criteria andArgsNotLike(String value) {
            addCriterion("ARGS not like", value, "args");
            return (Criteria) this;
        }

        public Criteria andArgsIn(List<String> values) {
            addCriterion("ARGS in", values, "args");
            return (Criteria) this;
        }

        public Criteria andArgsNotIn(List<String> values) {
            addCriterion("ARGS not in", values, "args");
            return (Criteria) this;
        }

        public Criteria andArgsBetween(String value1, String value2) {
            addCriterion("ARGS between", value1, value2, "args");
            return (Criteria) this;
        }

        public Criteria andArgsNotBetween(String value1, String value2) {
            addCriterion("ARGS not between", value1, value2, "args");
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