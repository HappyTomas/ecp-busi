package com.zengshi.ecp.cms.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CmsSiteCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CmsSiteCriteria() {
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

        public Criteria andSiteNameIsNull() {
            addCriterion("SITE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSiteNameIsNotNull() {
            addCriterion("SITE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSiteNameEqualTo(String value) {
            addCriterion("SITE_NAME =", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotEqualTo(String value) {
            addCriterion("SITE_NAME <>", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameGreaterThan(String value) {
            addCriterion("SITE_NAME >", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_NAME >=", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameLessThan(String value) {
            addCriterion("SITE_NAME <", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameLessThanOrEqualTo(String value) {
            addCriterion("SITE_NAME <=", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameLike(String value) {
            addCriterion("SITE_NAME like", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotLike(String value) {
            addCriterion("SITE_NAME not like", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameIn(List<String> values) {
            addCriterion("SITE_NAME in", values, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotIn(List<String> values) {
            addCriterion("SITE_NAME not in", values, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameBetween(String value1, String value2) {
            addCriterion("SITE_NAME between", value1, value2, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotBetween(String value1, String value2) {
            addCriterion("SITE_NAME not between", value1, value2, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteUrlIsNull() {
            addCriterion("SITE_URL is null");
            return (Criteria) this;
        }

        public Criteria andSiteUrlIsNotNull() {
            addCriterion("SITE_URL is not null");
            return (Criteria) this;
        }

        public Criteria andSiteUrlEqualTo(String value) {
            addCriterion("SITE_URL =", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlNotEqualTo(String value) {
            addCriterion("SITE_URL <>", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlGreaterThan(String value) {
            addCriterion("SITE_URL >", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_URL >=", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlLessThan(String value) {
            addCriterion("SITE_URL <", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlLessThanOrEqualTo(String value) {
            addCriterion("SITE_URL <=", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlLike(String value) {
            addCriterion("SITE_URL like", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlNotLike(String value) {
            addCriterion("SITE_URL not like", value, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlIn(List<String> values) {
            addCriterion("SITE_URL in", values, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlNotIn(List<String> values) {
            addCriterion("SITE_URL not in", values, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlBetween(String value1, String value2) {
            addCriterion("SITE_URL between", value1, value2, "siteUrl");
            return (Criteria) this;
        }

        public Criteria andSiteUrlNotBetween(String value1, String value2) {
            addCriterion("SITE_URL not between", value1, value2, "siteUrl");
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

        public Criteria andIsdefaultIsNull() {
            addCriterion("ISDEFAULT is null");
            return (Criteria) this;
        }

        public Criteria andIsdefaultIsNotNull() {
            addCriterion("ISDEFAULT is not null");
            return (Criteria) this;
        }

        public Criteria andIsdefaultEqualTo(String value) {
            addCriterion("ISDEFAULT =", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultNotEqualTo(String value) {
            addCriterion("ISDEFAULT <>", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultGreaterThan(String value) {
            addCriterion("ISDEFAULT >", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultGreaterThanOrEqualTo(String value) {
            addCriterion("ISDEFAULT >=", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultLessThan(String value) {
            addCriterion("ISDEFAULT <", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultLessThanOrEqualTo(String value) {
            addCriterion("ISDEFAULT <=", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultLike(String value) {
            addCriterion("ISDEFAULT like", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultNotLike(String value) {
            addCriterion("ISDEFAULT not like", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultIn(List<String> values) {
            addCriterion("ISDEFAULT in", values, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultNotIn(List<String> values) {
            addCriterion("ISDEFAULT not in", values, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultBetween(String value1, String value2) {
            addCriterion("ISDEFAULT between", value1, value2, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultNotBetween(String value1, String value2) {
            addCriterion("ISDEFAULT not between", value1, value2, "isdefault");
            return (Criteria) this;
        }

        public Criteria andSiteLogoIsNull() {
            addCriterion("SITE_LOGO is null");
            return (Criteria) this;
        }

        public Criteria andSiteLogoIsNotNull() {
            addCriterion("SITE_LOGO is not null");
            return (Criteria) this;
        }

        public Criteria andSiteLogoEqualTo(String value) {
            addCriterion("SITE_LOGO =", value, "siteLogo");
            return (Criteria) this;
        }

        public Criteria andSiteLogoNotEqualTo(String value) {
            addCriterion("SITE_LOGO <>", value, "siteLogo");
            return (Criteria) this;
        }

        public Criteria andSiteLogoGreaterThan(String value) {
            addCriterion("SITE_LOGO >", value, "siteLogo");
            return (Criteria) this;
        }

        public Criteria andSiteLogoGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_LOGO >=", value, "siteLogo");
            return (Criteria) this;
        }

        public Criteria andSiteLogoLessThan(String value) {
            addCriterion("SITE_LOGO <", value, "siteLogo");
            return (Criteria) this;
        }

        public Criteria andSiteLogoLessThanOrEqualTo(String value) {
            addCriterion("SITE_LOGO <=", value, "siteLogo");
            return (Criteria) this;
        }

        public Criteria andSiteLogoLike(String value) {
            addCriterion("SITE_LOGO like", value, "siteLogo");
            return (Criteria) this;
        }

        public Criteria andSiteLogoNotLike(String value) {
            addCriterion("SITE_LOGO not like", value, "siteLogo");
            return (Criteria) this;
        }

        public Criteria andSiteLogoIn(List<String> values) {
            addCriterion("SITE_LOGO in", values, "siteLogo");
            return (Criteria) this;
        }

        public Criteria andSiteLogoNotIn(List<String> values) {
            addCriterion("SITE_LOGO not in", values, "siteLogo");
            return (Criteria) this;
        }

        public Criteria andSiteLogoBetween(String value1, String value2) {
            addCriterion("SITE_LOGO between", value1, value2, "siteLogo");
            return (Criteria) this;
        }

        public Criteria andSiteLogoNotBetween(String value1, String value2) {
            addCriterion("SITE_LOGO not between", value1, value2, "siteLogo");
            return (Criteria) this;
        }

        public Criteria andCaseNoIsNull() {
            addCriterion("CASE_NO is null");
            return (Criteria) this;
        }

        public Criteria andCaseNoIsNotNull() {
            addCriterion("CASE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCaseNoEqualTo(String value) {
            addCriterion("CASE_NO =", value, "caseNo");
            return (Criteria) this;
        }

        public Criteria andCaseNoNotEqualTo(String value) {
            addCriterion("CASE_NO <>", value, "caseNo");
            return (Criteria) this;
        }

        public Criteria andCaseNoGreaterThan(String value) {
            addCriterion("CASE_NO >", value, "caseNo");
            return (Criteria) this;
        }

        public Criteria andCaseNoGreaterThanOrEqualTo(String value) {
            addCriterion("CASE_NO >=", value, "caseNo");
            return (Criteria) this;
        }

        public Criteria andCaseNoLessThan(String value) {
            addCriterion("CASE_NO <", value, "caseNo");
            return (Criteria) this;
        }

        public Criteria andCaseNoLessThanOrEqualTo(String value) {
            addCriterion("CASE_NO <=", value, "caseNo");
            return (Criteria) this;
        }

        public Criteria andCaseNoLike(String value) {
            addCriterion("CASE_NO like", value, "caseNo");
            return (Criteria) this;
        }

        public Criteria andCaseNoNotLike(String value) {
            addCriterion("CASE_NO not like", value, "caseNo");
            return (Criteria) this;
        }

        public Criteria andCaseNoIn(List<String> values) {
            addCriterion("CASE_NO in", values, "caseNo");
            return (Criteria) this;
        }

        public Criteria andCaseNoNotIn(List<String> values) {
            addCriterion("CASE_NO not in", values, "caseNo");
            return (Criteria) this;
        }

        public Criteria andCaseNoBetween(String value1, String value2) {
            addCriterion("CASE_NO between", value1, value2, "caseNo");
            return (Criteria) this;
        }

        public Criteria andCaseNoNotBetween(String value1, String value2) {
            addCriterion("CASE_NO not between", value1, value2, "caseNo");
            return (Criteria) this;
        }

        public Criteria andSiteAddressIsNull() {
            addCriterion("SITE_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andSiteAddressIsNotNull() {
            addCriterion("SITE_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andSiteAddressEqualTo(String value) {
            addCriterion("SITE_ADDRESS =", value, "siteAddress");
            return (Criteria) this;
        }

        public Criteria andSiteAddressNotEqualTo(String value) {
            addCriterion("SITE_ADDRESS <>", value, "siteAddress");
            return (Criteria) this;
        }

        public Criteria andSiteAddressGreaterThan(String value) {
            addCriterion("SITE_ADDRESS >", value, "siteAddress");
            return (Criteria) this;
        }

        public Criteria andSiteAddressGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_ADDRESS >=", value, "siteAddress");
            return (Criteria) this;
        }

        public Criteria andSiteAddressLessThan(String value) {
            addCriterion("SITE_ADDRESS <", value, "siteAddress");
            return (Criteria) this;
        }

        public Criteria andSiteAddressLessThanOrEqualTo(String value) {
            addCriterion("SITE_ADDRESS <=", value, "siteAddress");
            return (Criteria) this;
        }

        public Criteria andSiteAddressLike(String value) {
            addCriterion("SITE_ADDRESS like", value, "siteAddress");
            return (Criteria) this;
        }

        public Criteria andSiteAddressNotLike(String value) {
            addCriterion("SITE_ADDRESS not like", value, "siteAddress");
            return (Criteria) this;
        }

        public Criteria andSiteAddressIn(List<String> values) {
            addCriterion("SITE_ADDRESS in", values, "siteAddress");
            return (Criteria) this;
        }

        public Criteria andSiteAddressNotIn(List<String> values) {
            addCriterion("SITE_ADDRESS not in", values, "siteAddress");
            return (Criteria) this;
        }

        public Criteria andSiteAddressBetween(String value1, String value2) {
            addCriterion("SITE_ADDRESS between", value1, value2, "siteAddress");
            return (Criteria) this;
        }

        public Criteria andSiteAddressNotBetween(String value1, String value2) {
            addCriterion("SITE_ADDRESS not between", value1, value2, "siteAddress");
            return (Criteria) this;
        }

        public Criteria andCopyrightCompanyIsNull() {
            addCriterion("COPYRIGHT_COMPANY is null");
            return (Criteria) this;
        }

        public Criteria andCopyrightCompanyIsNotNull() {
            addCriterion("COPYRIGHT_COMPANY is not null");
            return (Criteria) this;
        }

        public Criteria andCopyrightCompanyEqualTo(String value) {
            addCriterion("COPYRIGHT_COMPANY =", value, "copyrightCompany");
            return (Criteria) this;
        }

        public Criteria andCopyrightCompanyNotEqualTo(String value) {
            addCriterion("COPYRIGHT_COMPANY <>", value, "copyrightCompany");
            return (Criteria) this;
        }

        public Criteria andCopyrightCompanyGreaterThan(String value) {
            addCriterion("COPYRIGHT_COMPANY >", value, "copyrightCompany");
            return (Criteria) this;
        }

        public Criteria andCopyrightCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("COPYRIGHT_COMPANY >=", value, "copyrightCompany");
            return (Criteria) this;
        }

        public Criteria andCopyrightCompanyLessThan(String value) {
            addCriterion("COPYRIGHT_COMPANY <", value, "copyrightCompany");
            return (Criteria) this;
        }

        public Criteria andCopyrightCompanyLessThanOrEqualTo(String value) {
            addCriterion("COPYRIGHT_COMPANY <=", value, "copyrightCompany");
            return (Criteria) this;
        }

        public Criteria andCopyrightCompanyLike(String value) {
            addCriterion("COPYRIGHT_COMPANY like", value, "copyrightCompany");
            return (Criteria) this;
        }

        public Criteria andCopyrightCompanyNotLike(String value) {
            addCriterion("COPYRIGHT_COMPANY not like", value, "copyrightCompany");
            return (Criteria) this;
        }

        public Criteria andCopyrightCompanyIn(List<String> values) {
            addCriterion("COPYRIGHT_COMPANY in", values, "copyrightCompany");
            return (Criteria) this;
        }

        public Criteria andCopyrightCompanyNotIn(List<String> values) {
            addCriterion("COPYRIGHT_COMPANY not in", values, "copyrightCompany");
            return (Criteria) this;
        }

        public Criteria andCopyrightCompanyBetween(String value1, String value2) {
            addCriterion("COPYRIGHT_COMPANY between", value1, value2, "copyrightCompany");
            return (Criteria) this;
        }

        public Criteria andCopyrightCompanyNotBetween(String value1, String value2) {
            addCriterion("COPYRIGHT_COMPANY not between", value1, value2, "copyrightCompany");
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
