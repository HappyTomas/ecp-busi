package com.zengshi.ecp.cms.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CmsChannelCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CmsChannelCriteria() {
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

        public Criteria andChannelNameIsNull() {
            addCriterion("CHANNEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChannelNameIsNotNull() {
            addCriterion("CHANNEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChannelNameEqualTo(String value) {
            addCriterion("CHANNEL_NAME =", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotEqualTo(String value) {
            addCriterion("CHANNEL_NAME <>", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameGreaterThan(String value) {
            addCriterion("CHANNEL_NAME >", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHANNEL_NAME >=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLessThan(String value) {
            addCriterion("CHANNEL_NAME <", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLessThanOrEqualTo(String value) {
            addCriterion("CHANNEL_NAME <=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLike(String value) {
            addCriterion("CHANNEL_NAME like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotLike(String value) {
            addCriterion("CHANNEL_NAME not like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameIn(List<String> values) {
            addCriterion("CHANNEL_NAME in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotIn(List<String> values) {
            addCriterion("CHANNEL_NAME not in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameBetween(String value1, String value2) {
            addCriterion("CHANNEL_NAME between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotBetween(String value1, String value2) {
            addCriterion("CHANNEL_NAME not between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelTypeIsNull() {
            addCriterion("CHANNEL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andChannelTypeIsNotNull() {
            addCriterion("CHANNEL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andChannelTypeEqualTo(String value) {
            addCriterion("CHANNEL_TYPE =", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeNotEqualTo(String value) {
            addCriterion("CHANNEL_TYPE <>", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeGreaterThan(String value) {
            addCriterion("CHANNEL_TYPE >", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CHANNEL_TYPE >=", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeLessThan(String value) {
            addCriterion("CHANNEL_TYPE <", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeLessThanOrEqualTo(String value) {
            addCriterion("CHANNEL_TYPE <=", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeLike(String value) {
            addCriterion("CHANNEL_TYPE like", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeNotLike(String value) {
            addCriterion("CHANNEL_TYPE not like", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeIn(List<String> values) {
            addCriterion("CHANNEL_TYPE in", values, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeNotIn(List<String> values) {
            addCriterion("CHANNEL_TYPE not in", values, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeBetween(String value1, String value2) {
            addCriterion("CHANNEL_TYPE between", value1, value2, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeNotBetween(String value1, String value2) {
            addCriterion("CHANNEL_TYPE not between", value1, value2, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelParentIsNull() {
            addCriterion("CHANNEL_PARENT is null");
            return (Criteria) this;
        }

        public Criteria andChannelParentIsNotNull() {
            addCriterion("CHANNEL_PARENT is not null");
            return (Criteria) this;
        }

        public Criteria andChannelParentEqualTo(Long value) {
            addCriterion("CHANNEL_PARENT =", value, "channelParent");
            return (Criteria) this;
        }

        public Criteria andChannelParentNotEqualTo(Long value) {
            addCriterion("CHANNEL_PARENT <>", value, "channelParent");
            return (Criteria) this;
        }

        public Criteria andChannelParentGreaterThan(Long value) {
            addCriterion("CHANNEL_PARENT >", value, "channelParent");
            return (Criteria) this;
        }

        public Criteria andChannelParentGreaterThanOrEqualTo(Long value) {
            addCriterion("CHANNEL_PARENT >=", value, "channelParent");
            return (Criteria) this;
        }

        public Criteria andChannelParentLessThan(Long value) {
            addCriterion("CHANNEL_PARENT <", value, "channelParent");
            return (Criteria) this;
        }

        public Criteria andChannelParentLessThanOrEqualTo(Long value) {
            addCriterion("CHANNEL_PARENT <=", value, "channelParent");
            return (Criteria) this;
        }

        public Criteria andChannelParentIn(List<Long> values) {
            addCriterion("CHANNEL_PARENT in", values, "channelParent");
            return (Criteria) this;
        }

        public Criteria andChannelParentNotIn(List<Long> values) {
            addCriterion("CHANNEL_PARENT not in", values, "channelParent");
            return (Criteria) this;
        }

        public Criteria andChannelParentBetween(Long value1, Long value2) {
            addCriterion("CHANNEL_PARENT between", value1, value2, "channelParent");
            return (Criteria) this;
        }

        public Criteria andChannelParentNotBetween(Long value1, Long value2) {
            addCriterion("CHANNEL_PARENT not between", value1, value2, "channelParent");
            return (Criteria) this;
        }

        public Criteria andChannelLabelIsNull() {
            addCriterion("CHANNEL_LABEL is null");
            return (Criteria) this;
        }

        public Criteria andChannelLabelIsNotNull() {
            addCriterion("CHANNEL_LABEL is not null");
            return (Criteria) this;
        }

        public Criteria andChannelLabelEqualTo(String value) {
            addCriterion("CHANNEL_LABEL =", value, "channelLabel");
            return (Criteria) this;
        }

        public Criteria andChannelLabelNotEqualTo(String value) {
            addCriterion("CHANNEL_LABEL <>", value, "channelLabel");
            return (Criteria) this;
        }

        public Criteria andChannelLabelGreaterThan(String value) {
            addCriterion("CHANNEL_LABEL >", value, "channelLabel");
            return (Criteria) this;
        }

        public Criteria andChannelLabelGreaterThanOrEqualTo(String value) {
            addCriterion("CHANNEL_LABEL >=", value, "channelLabel");
            return (Criteria) this;
        }

        public Criteria andChannelLabelLessThan(String value) {
            addCriterion("CHANNEL_LABEL <", value, "channelLabel");
            return (Criteria) this;
        }

        public Criteria andChannelLabelLessThanOrEqualTo(String value) {
            addCriterion("CHANNEL_LABEL <=", value, "channelLabel");
            return (Criteria) this;
        }

        public Criteria andChannelLabelLike(String value) {
            addCriterion("CHANNEL_LABEL like", value, "channelLabel");
            return (Criteria) this;
        }

        public Criteria andChannelLabelNotLike(String value) {
            addCriterion("CHANNEL_LABEL not like", value, "channelLabel");
            return (Criteria) this;
        }

        public Criteria andChannelLabelIn(List<String> values) {
            addCriterion("CHANNEL_LABEL in", values, "channelLabel");
            return (Criteria) this;
        }

        public Criteria andChannelLabelNotIn(List<String> values) {
            addCriterion("CHANNEL_LABEL not in", values, "channelLabel");
            return (Criteria) this;
        }

        public Criteria andChannelLabelBetween(String value1, String value2) {
            addCriterion("CHANNEL_LABEL between", value1, value2, "channelLabel");
            return (Criteria) this;
        }

        public Criteria andChannelLabelNotBetween(String value1, String value2) {
            addCriterion("CHANNEL_LABEL not between", value1, value2, "channelLabel");
            return (Criteria) this;
        }

        public Criteria andChannelUrlIsNull() {
            addCriterion("CHANNEL_URL is null");
            return (Criteria) this;
        }

        public Criteria andChannelUrlIsNotNull() {
            addCriterion("CHANNEL_URL is not null");
            return (Criteria) this;
        }

        public Criteria andChannelUrlEqualTo(String value) {
            addCriterion("CHANNEL_URL =", value, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlNotEqualTo(String value) {
            addCriterion("CHANNEL_URL <>", value, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlGreaterThan(String value) {
            addCriterion("CHANNEL_URL >", value, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlGreaterThanOrEqualTo(String value) {
            addCriterion("CHANNEL_URL >=", value, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlLessThan(String value) {
            addCriterion("CHANNEL_URL <", value, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlLessThanOrEqualTo(String value) {
            addCriterion("CHANNEL_URL <=", value, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlLike(String value) {
            addCriterion("CHANNEL_URL like", value, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlNotLike(String value) {
            addCriterion("CHANNEL_URL not like", value, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlIn(List<String> values) {
            addCriterion("CHANNEL_URL in", values, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlNotIn(List<String> values) {
            addCriterion("CHANNEL_URL not in", values, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlBetween(String value1, String value2) {
            addCriterion("CHANNEL_URL between", value1, value2, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlNotBetween(String value1, String value2) {
            addCriterion("CHANNEL_URL not between", value1, value2, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateIsNull() {
            addCriterion("CHANNEL_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateIsNotNull() {
            addCriterion("CHANNEL_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateEqualTo(Long value) {
            addCriterion("CHANNEL_TEMPLATE =", value, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateNotEqualTo(Long value) {
            addCriterion("CHANNEL_TEMPLATE <>", value, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateGreaterThan(Long value) {
            addCriterion("CHANNEL_TEMPLATE >", value, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateGreaterThanOrEqualTo(Long value) {
            addCriterion("CHANNEL_TEMPLATE >=", value, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateLessThan(Long value) {
            addCriterion("CHANNEL_TEMPLATE <", value, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateLessThanOrEqualTo(Long value) {
            addCriterion("CHANNEL_TEMPLATE <=", value, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateIn(List<Long> values) {
            addCriterion("CHANNEL_TEMPLATE in", values, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateNotIn(List<Long> values) {
            addCriterion("CHANNEL_TEMPLATE not in", values, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateBetween(Long value1, Long value2) {
            addCriterion("CHANNEL_TEMPLATE between", value1, value2, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateNotBetween(Long value1, Long value2) {
            addCriterion("CHANNEL_TEMPLATE not between", value1, value2, "channelTemplate");
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

        public Criteria andCreateStaffEqualTo(String value) {
            addCriterion("CREATE_STAFF =", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotEqualTo(String value) {
            addCriterion("CREATE_STAFF <>", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffGreaterThan(String value) {
            addCriterion("CREATE_STAFF >", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_STAFF >=", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLessThan(String value) {
            addCriterion("CREATE_STAFF <", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLessThanOrEqualTo(String value) {
            addCriterion("CREATE_STAFF <=", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLike(String value) {
            addCriterion("CREATE_STAFF like", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotLike(String value) {
            addCriterion("CREATE_STAFF not like", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIn(List<String> values) {
            addCriterion("CREATE_STAFF in", values, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotIn(List<String> values) {
            addCriterion("CREATE_STAFF not in", values, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffBetween(String value1, String value2) {
            addCriterion("CREATE_STAFF between", value1, value2, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotBetween(String value1, String value2) {
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

        public Criteria andUpdateStaffEqualTo(String value) {
            addCriterion("UPDATE_STAFF =", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotEqualTo(String value) {
            addCriterion("UPDATE_STAFF <>", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffGreaterThan(String value) {
            addCriterion("UPDATE_STAFF >", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_STAFF >=", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLessThan(String value) {
            addCriterion("UPDATE_STAFF <", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_STAFF <=", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLike(String value) {
            addCriterion("UPDATE_STAFF like", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotLike(String value) {
            addCriterion("UPDATE_STAFF not like", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIn(List<String> values) {
            addCriterion("UPDATE_STAFF in", values, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotIn(List<String> values) {
            addCriterion("UPDATE_STAFF not in", values, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffBetween(String value1, String value2) {
            addCriterion("UPDATE_STAFF between", value1, value2, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotBetween(String value1, String value2) {
            addCriterion("UPDATE_STAFF not between", value1, value2, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andIsresiteinfoIsNull() {
            addCriterion("ISRESITEINFO is null");
            return (Criteria) this;
        }

        public Criteria andIsresiteinfoIsNotNull() {
            addCriterion("ISRESITEINFO is not null");
            return (Criteria) this;
        }

        public Criteria andIsresiteinfoEqualTo(String value) {
            addCriterion("ISRESITEINFO =", value, "isresiteinfo");
            return (Criteria) this;
        }

        public Criteria andIsresiteinfoNotEqualTo(String value) {
            addCriterion("ISRESITEINFO <>", value, "isresiteinfo");
            return (Criteria) this;
        }

        public Criteria andIsresiteinfoGreaterThan(String value) {
            addCriterion("ISRESITEINFO >", value, "isresiteinfo");
            return (Criteria) this;
        }

        public Criteria andIsresiteinfoGreaterThanOrEqualTo(String value) {
            addCriterion("ISRESITEINFO >=", value, "isresiteinfo");
            return (Criteria) this;
        }

        public Criteria andIsresiteinfoLessThan(String value) {
            addCriterion("ISRESITEINFO <", value, "isresiteinfo");
            return (Criteria) this;
        }

        public Criteria andIsresiteinfoLessThanOrEqualTo(String value) {
            addCriterion("ISRESITEINFO <=", value, "isresiteinfo");
            return (Criteria) this;
        }

        public Criteria andIsresiteinfoLike(String value) {
            addCriterion("ISRESITEINFO like", value, "isresiteinfo");
            return (Criteria) this;
        }

        public Criteria andIsresiteinfoNotLike(String value) {
            addCriterion("ISRESITEINFO not like", value, "isresiteinfo");
            return (Criteria) this;
        }

        public Criteria andIsresiteinfoIn(List<String> values) {
            addCriterion("ISRESITEINFO in", values, "isresiteinfo");
            return (Criteria) this;
        }

        public Criteria andIsresiteinfoNotIn(List<String> values) {
            addCriterion("ISRESITEINFO not in", values, "isresiteinfo");
            return (Criteria) this;
        }

        public Criteria andIsresiteinfoBetween(String value1, String value2) {
            addCriterion("ISRESITEINFO between", value1, value2, "isresiteinfo");
            return (Criteria) this;
        }

        public Criteria andIsresiteinfoNotBetween(String value1, String value2) {
            addCriterion("ISRESITEINFO not between", value1, value2, "isresiteinfo");
            return (Criteria) this;
        }

        public Criteria andSiteinfoIdIsNull() {
            addCriterion("SITEINFO_ID is null");
            return (Criteria) this;
        }

        public Criteria andSiteinfoIdIsNotNull() {
            addCriterion("SITEINFO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSiteinfoIdEqualTo(Long value) {
            addCriterion("SITEINFO_ID =", value, "siteinfoId");
            return (Criteria) this;
        }

        public Criteria andSiteinfoIdNotEqualTo(Long value) {
            addCriterion("SITEINFO_ID <>", value, "siteinfoId");
            return (Criteria) this;
        }

        public Criteria andSiteinfoIdGreaterThan(Long value) {
            addCriterion("SITEINFO_ID >", value, "siteinfoId");
            return (Criteria) this;
        }

        public Criteria andSiteinfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SITEINFO_ID >=", value, "siteinfoId");
            return (Criteria) this;
        }

        public Criteria andSiteinfoIdLessThan(Long value) {
            addCriterion("SITEINFO_ID <", value, "siteinfoId");
            return (Criteria) this;
        }

        public Criteria andSiteinfoIdLessThanOrEqualTo(Long value) {
            addCriterion("SITEINFO_ID <=", value, "siteinfoId");
            return (Criteria) this;
        }

        public Criteria andSiteinfoIdIn(List<Long> values) {
            addCriterion("SITEINFO_ID in", values, "siteinfoId");
            return (Criteria) this;
        }

        public Criteria andSiteinfoIdNotIn(List<Long> values) {
            addCriterion("SITEINFO_ID not in", values, "siteinfoId");
            return (Criteria) this;
        }

        public Criteria andSiteinfoIdBetween(Long value1, Long value2) {
            addCriterion("SITEINFO_ID between", value1, value2, "siteinfoId");
            return (Criteria) this;
        }

        public Criteria andSiteinfoIdNotBetween(Long value1, Long value2) {
            addCriterion("SITEINFO_ID not between", value1, value2, "siteinfoId");
            return (Criteria) this;
        }

        public Criteria andIsOutLinkIsNull() {
            addCriterion("IS_OUT_LINK is null");
            return (Criteria) this;
        }

        public Criteria andIsOutLinkIsNotNull() {
            addCriterion("IS_OUT_LINK is not null");
            return (Criteria) this;
        }

        public Criteria andIsOutLinkEqualTo(String value) {
            addCriterion("IS_OUT_LINK =", value, "isOutLink");
            return (Criteria) this;
        }

        public Criteria andIsOutLinkNotEqualTo(String value) {
            addCriterion("IS_OUT_LINK <>", value, "isOutLink");
            return (Criteria) this;
        }

        public Criteria andIsOutLinkGreaterThan(String value) {
            addCriterion("IS_OUT_LINK >", value, "isOutLink");
            return (Criteria) this;
        }

        public Criteria andIsOutLinkGreaterThanOrEqualTo(String value) {
            addCriterion("IS_OUT_LINK >=", value, "isOutLink");
            return (Criteria) this;
        }

        public Criteria andIsOutLinkLessThan(String value) {
            addCriterion("IS_OUT_LINK <", value, "isOutLink");
            return (Criteria) this;
        }

        public Criteria andIsOutLinkLessThanOrEqualTo(String value) {
            addCriterion("IS_OUT_LINK <=", value, "isOutLink");
            return (Criteria) this;
        }

        public Criteria andIsOutLinkLike(String value) {
            addCriterion("IS_OUT_LINK like", value, "isOutLink");
            return (Criteria) this;
        }

        public Criteria andIsOutLinkNotLike(String value) {
            addCriterion("IS_OUT_LINK not like", value, "isOutLink");
            return (Criteria) this;
        }

        public Criteria andIsOutLinkIn(List<String> values) {
            addCriterion("IS_OUT_LINK in", values, "isOutLink");
            return (Criteria) this;
        }

        public Criteria andIsOutLinkNotIn(List<String> values) {
            addCriterion("IS_OUT_LINK not in", values, "isOutLink");
            return (Criteria) this;
        }

        public Criteria andIsOutLinkBetween(String value1, String value2) {
            addCriterion("IS_OUT_LINK between", value1, value2, "isOutLink");
            return (Criteria) this;
        }

        public Criteria andIsOutLinkNotBetween(String value1, String value2) {
            addCriterion("IS_OUT_LINK not between", value1, value2, "isOutLink");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeIsNull() {
            addCriterion("PLATFORM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeIsNotNull() {
            addCriterion("PLATFORM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeEqualTo(String value) {
            addCriterion("PLATFORM_TYPE =", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeNotEqualTo(String value) {
            addCriterion("PLATFORM_TYPE <>", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeGreaterThan(String value) {
            addCriterion("PLATFORM_TYPE >", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PLATFORM_TYPE >=", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeLessThan(String value) {
            addCriterion("PLATFORM_TYPE <", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeLessThanOrEqualTo(String value) {
            addCriterion("PLATFORM_TYPE <=", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeLike(String value) {
            addCriterion("PLATFORM_TYPE like", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeNotLike(String value) {
            addCriterion("PLATFORM_TYPE not like", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeIn(List<String> values) {
            addCriterion("PLATFORM_TYPE in", values, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeNotIn(List<String> values) {
            addCriterion("PLATFORM_TYPE not in", values, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeBetween(String value1, String value2) {
            addCriterion("PLATFORM_TYPE between", value1, value2, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeNotBetween(String value1, String value2) {
            addCriterion("PLATFORM_TYPE not between", value1, value2, "platformType");
            return (Criteria) this;
        }

        public Criteria andChannelIconIsNull() {
            addCriterion("CHANNEL_ICON is null");
            return (Criteria) this;
        }

        public Criteria andChannelIconIsNotNull() {
            addCriterion("CHANNEL_ICON is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIconEqualTo(String value) {
            addCriterion("CHANNEL_ICON =", value, "channelIcon");
            return (Criteria) this;
        }

        public Criteria andChannelIconNotEqualTo(String value) {
            addCriterion("CHANNEL_ICON <>", value, "channelIcon");
            return (Criteria) this;
        }

        public Criteria andChannelIconGreaterThan(String value) {
            addCriterion("CHANNEL_ICON >", value, "channelIcon");
            return (Criteria) this;
        }

        public Criteria andChannelIconGreaterThanOrEqualTo(String value) {
            addCriterion("CHANNEL_ICON >=", value, "channelIcon");
            return (Criteria) this;
        }

        public Criteria andChannelIconLessThan(String value) {
            addCriterion("CHANNEL_ICON <", value, "channelIcon");
            return (Criteria) this;
        }

        public Criteria andChannelIconLessThanOrEqualTo(String value) {
            addCriterion("CHANNEL_ICON <=", value, "channelIcon");
            return (Criteria) this;
        }

        public Criteria andChannelIconLike(String value) {
            addCriterion("CHANNEL_ICON like", value, "channelIcon");
            return (Criteria) this;
        }

        public Criteria andChannelIconNotLike(String value) {
            addCriterion("CHANNEL_ICON not like", value, "channelIcon");
            return (Criteria) this;
        }

        public Criteria andChannelIconIn(List<String> values) {
            addCriterion("CHANNEL_ICON in", values, "channelIcon");
            return (Criteria) this;
        }

        public Criteria andChannelIconNotIn(List<String> values) {
            addCriterion("CHANNEL_ICON not in", values, "channelIcon");
            return (Criteria) this;
        }

        public Criteria andChannelIconBetween(String value1, String value2) {
            addCriterion("CHANNEL_ICON between", value1, value2, "channelIcon");
            return (Criteria) this;
        }

        public Criteria andChannelIconNotBetween(String value1, String value2) {
            addCriterion("CHANNEL_ICON not between", value1, value2, "channelIcon");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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
