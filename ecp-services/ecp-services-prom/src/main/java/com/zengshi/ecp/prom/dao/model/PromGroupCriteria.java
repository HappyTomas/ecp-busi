package com.zengshi.ecp.prom.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PromGroupCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PromGroupCriteria() {
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

        public Criteria andPromThemeIsNull() {
            addCriterion("PROM_THEME is null");
            return (Criteria) this;
        }

        public Criteria andPromThemeIsNotNull() {
            addCriterion("PROM_THEME is not null");
            return (Criteria) this;
        }

        public Criteria andPromThemeEqualTo(String value) {
            addCriterion("PROM_THEME =", value, "promTheme");
            return (Criteria) this;
        }

        public Criteria andPromThemeNotEqualTo(String value) {
            addCriterion("PROM_THEME <>", value, "promTheme");
            return (Criteria) this;
        }

        public Criteria andPromThemeGreaterThan(String value) {
            addCriterion("PROM_THEME >", value, "promTheme");
            return (Criteria) this;
        }

        public Criteria andPromThemeGreaterThanOrEqualTo(String value) {
            addCriterion("PROM_THEME >=", value, "promTheme");
            return (Criteria) this;
        }

        public Criteria andPromThemeLessThan(String value) {
            addCriterion("PROM_THEME <", value, "promTheme");
            return (Criteria) this;
        }

        public Criteria andPromThemeLessThanOrEqualTo(String value) {
            addCriterion("PROM_THEME <=", value, "promTheme");
            return (Criteria) this;
        }

        public Criteria andPromThemeLike(String value) {
            addCriterion("PROM_THEME like", value, "promTheme");
            return (Criteria) this;
        }

        public Criteria andPromThemeNotLike(String value) {
            addCriterion("PROM_THEME not like", value, "promTheme");
            return (Criteria) this;
        }

        public Criteria andPromThemeIn(List<String> values) {
            addCriterion("PROM_THEME in", values, "promTheme");
            return (Criteria) this;
        }

        public Criteria andPromThemeNotIn(List<String> values) {
            addCriterion("PROM_THEME not in", values, "promTheme");
            return (Criteria) this;
        }

        public Criteria andPromThemeBetween(String value1, String value2) {
            addCriterion("PROM_THEME between", value1, value2, "promTheme");
            return (Criteria) this;
        }

        public Criteria andPromThemeNotBetween(String value1, String value2) {
            addCriterion("PROM_THEME not between", value1, value2, "promTheme");
            return (Criteria) this;
        }

        public Criteria andPromContentIsNull() {
            addCriterion("PROM_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andPromContentIsNotNull() {
            addCriterion("PROM_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andPromContentEqualTo(String value) {
            addCriterion("PROM_CONTENT =", value, "promContent");
            return (Criteria) this;
        }

        public Criteria andPromContentNotEqualTo(String value) {
            addCriterion("PROM_CONTENT <>", value, "promContent");
            return (Criteria) this;
        }

        public Criteria andPromContentGreaterThan(String value) {
            addCriterion("PROM_CONTENT >", value, "promContent");
            return (Criteria) this;
        }

        public Criteria andPromContentGreaterThanOrEqualTo(String value) {
            addCriterion("PROM_CONTENT >=", value, "promContent");
            return (Criteria) this;
        }

        public Criteria andPromContentLessThan(String value) {
            addCriterion("PROM_CONTENT <", value, "promContent");
            return (Criteria) this;
        }

        public Criteria andPromContentLessThanOrEqualTo(String value) {
            addCriterion("PROM_CONTENT <=", value, "promContent");
            return (Criteria) this;
        }

        public Criteria andPromContentLike(String value) {
            addCriterion("PROM_CONTENT like", value, "promContent");
            return (Criteria) this;
        }

        public Criteria andPromContentNotLike(String value) {
            addCriterion("PROM_CONTENT not like", value, "promContent");
            return (Criteria) this;
        }

        public Criteria andPromContentIn(List<String> values) {
            addCriterion("PROM_CONTENT in", values, "promContent");
            return (Criteria) this;
        }

        public Criteria andPromContentNotIn(List<String> values) {
            addCriterion("PROM_CONTENT not in", values, "promContent");
            return (Criteria) this;
        }

        public Criteria andPromContentBetween(String value1, String value2) {
            addCriterion("PROM_CONTENT between", value1, value2, "promContent");
            return (Criteria) this;
        }

        public Criteria andPromContentNotBetween(String value1, String value2) {
            addCriterion("PROM_CONTENT not between", value1, value2, "promContent");
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

        public Criteria andPromUrlIsNull() {
            addCriterion("PROM_URL is null");
            return (Criteria) this;
        }

        public Criteria andPromUrlIsNotNull() {
            addCriterion("PROM_URL is not null");
            return (Criteria) this;
        }

        public Criteria andPromUrlEqualTo(String value) {
            addCriterion("PROM_URL =", value, "promUrl");
            return (Criteria) this;
        }

        public Criteria andPromUrlNotEqualTo(String value) {
            addCriterion("PROM_URL <>", value, "promUrl");
            return (Criteria) this;
        }

        public Criteria andPromUrlGreaterThan(String value) {
            addCriterion("PROM_URL >", value, "promUrl");
            return (Criteria) this;
        }

        public Criteria andPromUrlGreaterThanOrEqualTo(String value) {
            addCriterion("PROM_URL >=", value, "promUrl");
            return (Criteria) this;
        }

        public Criteria andPromUrlLessThan(String value) {
            addCriterion("PROM_URL <", value, "promUrl");
            return (Criteria) this;
        }

        public Criteria andPromUrlLessThanOrEqualTo(String value) {
            addCriterion("PROM_URL <=", value, "promUrl");
            return (Criteria) this;
        }

        public Criteria andPromUrlLike(String value) {
            addCriterion("PROM_URL like", value, "promUrl");
            return (Criteria) this;
        }

        public Criteria andPromUrlNotLike(String value) {
            addCriterion("PROM_URL not like", value, "promUrl");
            return (Criteria) this;
        }

        public Criteria andPromUrlIn(List<String> values) {
            addCriterion("PROM_URL in", values, "promUrl");
            return (Criteria) this;
        }

        public Criteria andPromUrlNotIn(List<String> values) {
            addCriterion("PROM_URL not in", values, "promUrl");
            return (Criteria) this;
        }

        public Criteria andPromUrlBetween(String value1, String value2) {
            addCriterion("PROM_URL between", value1, value2, "promUrl");
            return (Criteria) this;
        }

        public Criteria andPromUrlNotBetween(String value1, String value2) {
            addCriterion("PROM_URL not between", value1, value2, "promUrl");
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