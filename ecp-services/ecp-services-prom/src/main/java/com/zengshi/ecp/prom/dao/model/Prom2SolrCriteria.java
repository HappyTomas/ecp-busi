package com.zengshi.ecp.prom.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Prom2SolrCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public Prom2SolrCriteria() {
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

        public Criteria andPromIdIsNull() {
            addCriterion("PROM_ID is null");
            return (Criteria) this;
        }

        public Criteria andPromIdIsNotNull() {
            addCriterion("PROM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPromIdEqualTo(Long value) {
            addCriterion("PROM_ID =", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotEqualTo(Long value) {
            addCriterion("PROM_ID <>", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdGreaterThan(Long value) {
            addCriterion("PROM_ID >", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROM_ID >=", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdLessThan(Long value) {
            addCriterion("PROM_ID <", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdLessThanOrEqualTo(Long value) {
            addCriterion("PROM_ID <=", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdIn(List<Long> values) {
            addCriterion("PROM_ID in", values, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotIn(List<Long> values) {
            addCriterion("PROM_ID not in", values, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdBetween(Long value1, Long value2) {
            addCriterion("PROM_ID between", value1, value2, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotBetween(Long value1, Long value2) {
            addCriterion("PROM_ID not between", value1, value2, "promId");
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

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Timestamp value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Timestamp value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Timestamp value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Timestamp value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Timestamp> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Timestamp> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeIsNull() {
            addCriterion("SHOW_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeIsNotNull() {
            addCriterion("SHOW_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeEqualTo(Timestamp value) {
            addCriterion("SHOW_START_TIME =", value, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeNotEqualTo(Timestamp value) {
            addCriterion("SHOW_START_TIME <>", value, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeGreaterThan(Timestamp value) {
            addCriterion("SHOW_START_TIME >", value, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("SHOW_START_TIME >=", value, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeLessThan(Timestamp value) {
            addCriterion("SHOW_START_TIME <", value, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("SHOW_START_TIME <=", value, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeIn(List<Timestamp> values) {
            addCriterion("SHOW_START_TIME in", values, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeNotIn(List<Timestamp> values) {
            addCriterion("SHOW_START_TIME not in", values, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SHOW_START_TIME between", value1, value2, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowStartTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SHOW_START_TIME not between", value1, value2, "showStartTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeIsNull() {
            addCriterion("SHOW_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeIsNotNull() {
            addCriterion("SHOW_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeEqualTo(Timestamp value) {
            addCriterion("SHOW_END_TIME =", value, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeNotEqualTo(Timestamp value) {
            addCriterion("SHOW_END_TIME <>", value, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeGreaterThan(Timestamp value) {
            addCriterion("SHOW_END_TIME >", value, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("SHOW_END_TIME >=", value, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeLessThan(Timestamp value) {
            addCriterion("SHOW_END_TIME <", value, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("SHOW_END_TIME <=", value, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeIn(List<Timestamp> values) {
            addCriterion("SHOW_END_TIME in", values, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeNotIn(List<Timestamp> values) {
            addCriterion("SHOW_END_TIME not in", values, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SHOW_END_TIME between", value1, value2, "showEndTime");
            return (Criteria) this;
        }

        public Criteria andShowEndTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SHOW_END_TIME not between", value1, value2, "showEndTime");
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

        public Criteria andPromTypeCodeIsNull() {
            addCriterion("PROM_TYPE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeIsNotNull() {
            addCriterion("PROM_TYPE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeEqualTo(String value) {
            addCriterion("PROM_TYPE_CODE =", value, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeNotEqualTo(String value) {
            addCriterion("PROM_TYPE_CODE <>", value, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeGreaterThan(String value) {
            addCriterion("PROM_TYPE_CODE >", value, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PROM_TYPE_CODE >=", value, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeLessThan(String value) {
            addCriterion("PROM_TYPE_CODE <", value, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("PROM_TYPE_CODE <=", value, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeLike(String value) {
            addCriterion("PROM_TYPE_CODE like", value, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeNotLike(String value) {
            addCriterion("PROM_TYPE_CODE not like", value, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeIn(List<String> values) {
            addCriterion("PROM_TYPE_CODE in", values, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeNotIn(List<String> values) {
            addCriterion("PROM_TYPE_CODE not in", values, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeBetween(String value1, String value2) {
            addCriterion("PROM_TYPE_CODE between", value1, value2, "promTypeCode");
            return (Criteria) this;
        }

        public Criteria andPromTypeCodeNotBetween(String value1, String value2) {
            addCriterion("PROM_TYPE_CODE not between", value1, value2, "promTypeCode");
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

        public Criteria andJoinRangeIsNull() {
            addCriterion("JOIN_RANGE is null");
            return (Criteria) this;
        }

        public Criteria andJoinRangeIsNotNull() {
            addCriterion("JOIN_RANGE is not null");
            return (Criteria) this;
        }

        public Criteria andJoinRangeEqualTo(String value) {
            addCriterion("JOIN_RANGE =", value, "joinRange");
            return (Criteria) this;
        }

        public Criteria andJoinRangeNotEqualTo(String value) {
            addCriterion("JOIN_RANGE <>", value, "joinRange");
            return (Criteria) this;
        }

        public Criteria andJoinRangeGreaterThan(String value) {
            addCriterion("JOIN_RANGE >", value, "joinRange");
            return (Criteria) this;
        }

        public Criteria andJoinRangeGreaterThanOrEqualTo(String value) {
            addCriterion("JOIN_RANGE >=", value, "joinRange");
            return (Criteria) this;
        }

        public Criteria andJoinRangeLessThan(String value) {
            addCriterion("JOIN_RANGE <", value, "joinRange");
            return (Criteria) this;
        }

        public Criteria andJoinRangeLessThanOrEqualTo(String value) {
            addCriterion("JOIN_RANGE <=", value, "joinRange");
            return (Criteria) this;
        }

        public Criteria andJoinRangeLike(String value) {
            addCriterion("JOIN_RANGE like", value, "joinRange");
            return (Criteria) this;
        }

        public Criteria andJoinRangeNotLike(String value) {
            addCriterion("JOIN_RANGE not like", value, "joinRange");
            return (Criteria) this;
        }

        public Criteria andJoinRangeIn(List<String> values) {
            addCriterion("JOIN_RANGE in", values, "joinRange");
            return (Criteria) this;
        }

        public Criteria andJoinRangeNotIn(List<String> values) {
            addCriterion("JOIN_RANGE not in", values, "joinRange");
            return (Criteria) this;
        }

        public Criteria andJoinRangeBetween(String value1, String value2) {
            addCriterion("JOIN_RANGE between", value1, value2, "joinRange");
            return (Criteria) this;
        }

        public Criteria andJoinRangeNotBetween(String value1, String value2) {
            addCriterion("JOIN_RANGE not between", value1, value2, "joinRange");
            return (Criteria) this;
        }

        public Criteria andIfMatchIsNull() {
            addCriterion("IF_MATCH is null");
            return (Criteria) this;
        }

        public Criteria andIfMatchIsNotNull() {
            addCriterion("IF_MATCH is not null");
            return (Criteria) this;
        }

        public Criteria andIfMatchEqualTo(String value) {
            addCriterion("IF_MATCH =", value, "ifMatch");
            return (Criteria) this;
        }

        public Criteria andIfMatchNotEqualTo(String value) {
            addCriterion("IF_MATCH <>", value, "ifMatch");
            return (Criteria) this;
        }

        public Criteria andIfMatchGreaterThan(String value) {
            addCriterion("IF_MATCH >", value, "ifMatch");
            return (Criteria) this;
        }

        public Criteria andIfMatchGreaterThanOrEqualTo(String value) {
            addCriterion("IF_MATCH >=", value, "ifMatch");
            return (Criteria) this;
        }

        public Criteria andIfMatchLessThan(String value) {
            addCriterion("IF_MATCH <", value, "ifMatch");
            return (Criteria) this;
        }

        public Criteria andIfMatchLessThanOrEqualTo(String value) {
            addCriterion("IF_MATCH <=", value, "ifMatch");
            return (Criteria) this;
        }

        public Criteria andIfMatchLike(String value) {
            addCriterion("IF_MATCH like", value, "ifMatch");
            return (Criteria) this;
        }

        public Criteria andIfMatchNotLike(String value) {
            addCriterion("IF_MATCH not like", value, "ifMatch");
            return (Criteria) this;
        }

        public Criteria andIfMatchIn(List<String> values) {
            addCriterion("IF_MATCH in", values, "ifMatch");
            return (Criteria) this;
        }

        public Criteria andIfMatchNotIn(List<String> values) {
            addCriterion("IF_MATCH not in", values, "ifMatch");
            return (Criteria) this;
        }

        public Criteria andIfMatchBetween(String value1, String value2) {
            addCriterion("IF_MATCH between", value1, value2, "ifMatch");
            return (Criteria) this;
        }

        public Criteria andIfMatchNotBetween(String value1, String value2) {
            addCriterion("IF_MATCH not between", value1, value2, "ifMatch");
            return (Criteria) this;
        }

        public Criteria andIfBlacklistIsNull() {
            addCriterion("IF_BLACKLIST is null");
            return (Criteria) this;
        }

        public Criteria andIfBlacklistIsNotNull() {
            addCriterion("IF_BLACKLIST is not null");
            return (Criteria) this;
        }

        public Criteria andIfBlacklistEqualTo(String value) {
            addCriterion("IF_BLACKLIST =", value, "ifBlacklist");
            return (Criteria) this;
        }

        public Criteria andIfBlacklistNotEqualTo(String value) {
            addCriterion("IF_BLACKLIST <>", value, "ifBlacklist");
            return (Criteria) this;
        }

        public Criteria andIfBlacklistGreaterThan(String value) {
            addCriterion("IF_BLACKLIST >", value, "ifBlacklist");
            return (Criteria) this;
        }

        public Criteria andIfBlacklistGreaterThanOrEqualTo(String value) {
            addCriterion("IF_BLACKLIST >=", value, "ifBlacklist");
            return (Criteria) this;
        }

        public Criteria andIfBlacklistLessThan(String value) {
            addCriterion("IF_BLACKLIST <", value, "ifBlacklist");
            return (Criteria) this;
        }

        public Criteria andIfBlacklistLessThanOrEqualTo(String value) {
            addCriterion("IF_BLACKLIST <=", value, "ifBlacklist");
            return (Criteria) this;
        }

        public Criteria andIfBlacklistLike(String value) {
            addCriterion("IF_BLACKLIST like", value, "ifBlacklist");
            return (Criteria) this;
        }

        public Criteria andIfBlacklistNotLike(String value) {
            addCriterion("IF_BLACKLIST not like", value, "ifBlacklist");
            return (Criteria) this;
        }

        public Criteria andIfBlacklistIn(List<String> values) {
            addCriterion("IF_BLACKLIST in", values, "ifBlacklist");
            return (Criteria) this;
        }

        public Criteria andIfBlacklistNotIn(List<String> values) {
            addCriterion("IF_BLACKLIST not in", values, "ifBlacklist");
            return (Criteria) this;
        }

        public Criteria andIfBlacklistBetween(String value1, String value2) {
            addCriterion("IF_BLACKLIST between", value1, value2, "ifBlacklist");
            return (Criteria) this;
        }

        public Criteria andIfBlacklistNotBetween(String value1, String value2) {
            addCriterion("IF_BLACKLIST not between", value1, value2, "ifBlacklist");
            return (Criteria) this;
        }

        public Criteria andSendStatusIsNull() {
            addCriterion("SEND_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andSendStatusIsNotNull() {
            addCriterion("SEND_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andSendStatusEqualTo(String value) {
            addCriterion("SEND_STATUS =", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotEqualTo(String value) {
            addCriterion("SEND_STATUS <>", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusGreaterThan(String value) {
            addCriterion("SEND_STATUS >", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_STATUS >=", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLessThan(String value) {
            addCriterion("SEND_STATUS <", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLessThanOrEqualTo(String value) {
            addCriterion("SEND_STATUS <=", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusLike(String value) {
            addCriterion("SEND_STATUS like", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotLike(String value) {
            addCriterion("SEND_STATUS not like", value, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusIn(List<String> values) {
            addCriterion("SEND_STATUS in", values, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotIn(List<String> values) {
            addCriterion("SEND_STATUS not in", values, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusBetween(String value1, String value2) {
            addCriterion("SEND_STATUS between", value1, value2, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSendStatusNotBetween(String value1, String value2) {
            addCriterion("SEND_STATUS not between", value1, value2, "sendStatus");
            return (Criteria) this;
        }

        public Criteria andSolrTypeIsNull() {
            addCriterion("SOLR_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSolrTypeIsNotNull() {
            addCriterion("SOLR_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSolrTypeEqualTo(String value) {
            addCriterion("SOLR_TYPE =", value, "solrType");
            return (Criteria) this;
        }

        public Criteria andSolrTypeNotEqualTo(String value) {
            addCriterion("SOLR_TYPE <>", value, "solrType");
            return (Criteria) this;
        }

        public Criteria andSolrTypeGreaterThan(String value) {
            addCriterion("SOLR_TYPE >", value, "solrType");
            return (Criteria) this;
        }

        public Criteria andSolrTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SOLR_TYPE >=", value, "solrType");
            return (Criteria) this;
        }

        public Criteria andSolrTypeLessThan(String value) {
            addCriterion("SOLR_TYPE <", value, "solrType");
            return (Criteria) this;
        }

        public Criteria andSolrTypeLessThanOrEqualTo(String value) {
            addCriterion("SOLR_TYPE <=", value, "solrType");
            return (Criteria) this;
        }

        public Criteria andSolrTypeLike(String value) {
            addCriterion("SOLR_TYPE like", value, "solrType");
            return (Criteria) this;
        }

        public Criteria andSolrTypeNotLike(String value) {
            addCriterion("SOLR_TYPE not like", value, "solrType");
            return (Criteria) this;
        }

        public Criteria andSolrTypeIn(List<String> values) {
            addCriterion("SOLR_TYPE in", values, "solrType");
            return (Criteria) this;
        }

        public Criteria andSolrTypeNotIn(List<String> values) {
            addCriterion("SOLR_TYPE not in", values, "solrType");
            return (Criteria) this;
        }

        public Criteria andSolrTypeBetween(String value1, String value2) {
            addCriterion("SOLR_TYPE between", value1, value2, "solrType");
            return (Criteria) this;
        }

        public Criteria andSolrTypeNotBetween(String value1, String value2) {
            addCriterion("SOLR_TYPE not between", value1, value2, "solrType");
            return (Criteria) this;
        }

        public Criteria andSendTypeIsNull() {
            addCriterion("SEND_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSendTypeIsNotNull() {
            addCriterion("SEND_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSendTypeEqualTo(String value) {
            addCriterion("SEND_TYPE =", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotEqualTo(String value) {
            addCriterion("SEND_TYPE <>", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeGreaterThan(String value) {
            addCriterion("SEND_TYPE >", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_TYPE >=", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLessThan(String value) {
            addCriterion("SEND_TYPE <", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLessThanOrEqualTo(String value) {
            addCriterion("SEND_TYPE <=", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLike(String value) {
            addCriterion("SEND_TYPE like", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotLike(String value) {
            addCriterion("SEND_TYPE not like", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeIn(List<String> values) {
            addCriterion("SEND_TYPE in", values, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotIn(List<String> values) {
            addCriterion("SEND_TYPE not in", values, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeBetween(String value1, String value2) {
            addCriterion("SEND_TYPE between", value1, value2, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotBetween(String value1, String value2) {
            addCriterion("SEND_TYPE not between", value1, value2, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("SEND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("SEND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Timestamp value) {
            addCriterion("SEND_TIME =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Timestamp value) {
            addCriterion("SEND_TIME <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Timestamp value) {
            addCriterion("SEND_TIME >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("SEND_TIME >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Timestamp value) {
            addCriterion("SEND_TIME <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("SEND_TIME <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Timestamp> values) {
            addCriterion("SEND_TIME in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Timestamp> values) {
            addCriterion("SEND_TIME not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SEND_TIME between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SEND_TIME not between", value1, value2, "sendTime");
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