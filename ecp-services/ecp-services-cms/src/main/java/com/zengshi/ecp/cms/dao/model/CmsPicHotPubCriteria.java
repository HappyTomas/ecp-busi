package com.zengshi.ecp.cms.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CmsPicHotPubCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CmsPicHotPubCriteria() {
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

        public Criteria andHotIdIsNull() {
            addCriterion("HOT_ID is null");
            return (Criteria) this;
        }

        public Criteria andHotIdIsNotNull() {
            addCriterion("HOT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHotIdEqualTo(Long value) {
            addCriterion("HOT_ID =", value, "hotId");
            return (Criteria) this;
        }

        public Criteria andHotIdNotEqualTo(Long value) {
            addCriterion("HOT_ID <>", value, "hotId");
            return (Criteria) this;
        }

        public Criteria andHotIdGreaterThan(Long value) {
            addCriterion("HOT_ID >", value, "hotId");
            return (Criteria) this;
        }

        public Criteria andHotIdGreaterThanOrEqualTo(Long value) {
            addCriterion("HOT_ID >=", value, "hotId");
            return (Criteria) this;
        }

        public Criteria andHotIdLessThan(Long value) {
            addCriterion("HOT_ID <", value, "hotId");
            return (Criteria) this;
        }

        public Criteria andHotIdLessThanOrEqualTo(Long value) {
            addCriterion("HOT_ID <=", value, "hotId");
            return (Criteria) this;
        }

        public Criteria andHotIdIn(List<Long> values) {
            addCriterion("HOT_ID in", values, "hotId");
            return (Criteria) this;
        }

        public Criteria andHotIdNotIn(List<Long> values) {
            addCriterion("HOT_ID not in", values, "hotId");
            return (Criteria) this;
        }

        public Criteria andHotIdBetween(Long value1, Long value2) {
            addCriterion("HOT_ID between", value1, value2, "hotId");
            return (Criteria) this;
        }

        public Criteria andHotIdNotBetween(Long value1, Long value2) {
            addCriterion("HOT_ID not between", value1, value2, "hotId");
            return (Criteria) this;
        }

        public Criteria andPicIdIsNull() {
            addCriterion("PIC_ID is null");
            return (Criteria) this;
        }

        public Criteria andPicIdIsNotNull() {
            addCriterion("PIC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPicIdEqualTo(String value) {
            addCriterion("PIC_ID =", value, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdNotEqualTo(String value) {
            addCriterion("PIC_ID <>", value, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdGreaterThan(String value) {
            addCriterion("PIC_ID >", value, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdGreaterThanOrEqualTo(String value) {
            addCriterion("PIC_ID >=", value, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdLessThan(String value) {
            addCriterion("PIC_ID <", value, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdLessThanOrEqualTo(String value) {
            addCriterion("PIC_ID <=", value, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdLike(String value) {
            addCriterion("PIC_ID like", value, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdNotLike(String value) {
            addCriterion("PIC_ID not like", value, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdIn(List<String> values) {
            addCriterion("PIC_ID in", values, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdNotIn(List<String> values) {
            addCriterion("PIC_ID not in", values, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdBetween(String value1, String value2) {
            addCriterion("PIC_ID between", value1, value2, "picId");
            return (Criteria) this;
        }

        public Criteria andPicIdNotBetween(String value1, String value2) {
            addCriterion("PIC_ID not between", value1, value2, "picId");
            return (Criteria) this;
        }

        public Criteria andPageIdIsNull() {
            addCriterion("PAGE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPageIdIsNotNull() {
            addCriterion("PAGE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPageIdEqualTo(Long value) {
            addCriterion("PAGE_ID =", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdNotEqualTo(Long value) {
            addCriterion("PAGE_ID <>", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdGreaterThan(Long value) {
            addCriterion("PAGE_ID >", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PAGE_ID >=", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdLessThan(Long value) {
            addCriterion("PAGE_ID <", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdLessThanOrEqualTo(Long value) {
            addCriterion("PAGE_ID <=", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdIn(List<Long> values) {
            addCriterion("PAGE_ID in", values, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdNotIn(List<Long> values) {
            addCriterion("PAGE_ID not in", values, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdBetween(Long value1, Long value2) {
            addCriterion("PAGE_ID between", value1, value2, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdNotBetween(Long value1, Long value2) {
            addCriterion("PAGE_ID not between", value1, value2, "pageId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdIsNull() {
            addCriterion("ITEM_PROP_ID is null");
            return (Criteria) this;
        }

        public Criteria andItemPropIdIsNotNull() {
            addCriterion("ITEM_PROP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andItemPropIdEqualTo(Long value) {
            addCriterion("ITEM_PROP_ID =", value, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdNotEqualTo(Long value) {
            addCriterion("ITEM_PROP_ID <>", value, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdGreaterThan(Long value) {
            addCriterion("ITEM_PROP_ID >", value, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ITEM_PROP_ID >=", value, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdLessThan(Long value) {
            addCriterion("ITEM_PROP_ID <", value, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdLessThanOrEqualTo(Long value) {
            addCriterion("ITEM_PROP_ID <=", value, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdIn(List<Long> values) {
            addCriterion("ITEM_PROP_ID in", values, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdNotIn(List<Long> values) {
            addCriterion("ITEM_PROP_ID not in", values, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdBetween(Long value1, Long value2) {
            addCriterion("ITEM_PROP_ID between", value1, value2, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andItemPropIdNotBetween(Long value1, Long value2) {
            addCriterion("ITEM_PROP_ID not between", value1, value2, "itemPropId");
            return (Criteria) this;
        }

        public Criteria andRelativeCoordIsNull() {
            addCriterion("RELATIVE_COORD is null");
            return (Criteria) this;
        }

        public Criteria andRelativeCoordIsNotNull() {
            addCriterion("RELATIVE_COORD is not null");
            return (Criteria) this;
        }

        public Criteria andRelativeCoordEqualTo(String value) {
            addCriterion("RELATIVE_COORD =", value, "relativeCoord");
            return (Criteria) this;
        }

        public Criteria andRelativeCoordNotEqualTo(String value) {
            addCriterion("RELATIVE_COORD <>", value, "relativeCoord");
            return (Criteria) this;
        }

        public Criteria andRelativeCoordGreaterThan(String value) {
            addCriterion("RELATIVE_COORD >", value, "relativeCoord");
            return (Criteria) this;
        }

        public Criteria andRelativeCoordGreaterThanOrEqualTo(String value) {
            addCriterion("RELATIVE_COORD >=", value, "relativeCoord");
            return (Criteria) this;
        }

        public Criteria andRelativeCoordLessThan(String value) {
            addCriterion("RELATIVE_COORD <", value, "relativeCoord");
            return (Criteria) this;
        }

        public Criteria andRelativeCoordLessThanOrEqualTo(String value) {
            addCriterion("RELATIVE_COORD <=", value, "relativeCoord");
            return (Criteria) this;
        }

        public Criteria andRelativeCoordLike(String value) {
            addCriterion("RELATIVE_COORD like", value, "relativeCoord");
            return (Criteria) this;
        }

        public Criteria andRelativeCoordNotLike(String value) {
            addCriterion("RELATIVE_COORD not like", value, "relativeCoord");
            return (Criteria) this;
        }

        public Criteria andRelativeCoordIn(List<String> values) {
            addCriterion("RELATIVE_COORD in", values, "relativeCoord");
            return (Criteria) this;
        }

        public Criteria andRelativeCoordNotIn(List<String> values) {
            addCriterion("RELATIVE_COORD not in", values, "relativeCoord");
            return (Criteria) this;
        }

        public Criteria andRelativeCoordBetween(String value1, String value2) {
            addCriterion("RELATIVE_COORD between", value1, value2, "relativeCoord");
            return (Criteria) this;
        }

        public Criteria andRelativeCoordNotBetween(String value1, String value2) {
            addCriterion("RELATIVE_COORD not between", value1, value2, "relativeCoord");
            return (Criteria) this;
        }

        public Criteria andUrlInfoIsNull() {
            addCriterion("URL_INFO is null");
            return (Criteria) this;
        }

        public Criteria andUrlInfoIsNotNull() {
            addCriterion("URL_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andUrlInfoEqualTo(String value) {
            addCriterion("URL_INFO =", value, "urlInfo");
            return (Criteria) this;
        }

        public Criteria andUrlInfoNotEqualTo(String value) {
            addCriterion("URL_INFO <>", value, "urlInfo");
            return (Criteria) this;
        }

        public Criteria andUrlInfoGreaterThan(String value) {
            addCriterion("URL_INFO >", value, "urlInfo");
            return (Criteria) this;
        }

        public Criteria andUrlInfoGreaterThanOrEqualTo(String value) {
            addCriterion("URL_INFO >=", value, "urlInfo");
            return (Criteria) this;
        }

        public Criteria andUrlInfoLessThan(String value) {
            addCriterion("URL_INFO <", value, "urlInfo");
            return (Criteria) this;
        }

        public Criteria andUrlInfoLessThanOrEqualTo(String value) {
            addCriterion("URL_INFO <=", value, "urlInfo");
            return (Criteria) this;
        }

        public Criteria andUrlInfoLike(String value) {
            addCriterion("URL_INFO like", value, "urlInfo");
            return (Criteria) this;
        }

        public Criteria andUrlInfoNotLike(String value) {
            addCriterion("URL_INFO not like", value, "urlInfo");
            return (Criteria) this;
        }

        public Criteria andUrlInfoIn(List<String> values) {
            addCriterion("URL_INFO in", values, "urlInfo");
            return (Criteria) this;
        }

        public Criteria andUrlInfoNotIn(List<String> values) {
            addCriterion("URL_INFO not in", values, "urlInfo");
            return (Criteria) this;
        }

        public Criteria andUrlInfoBetween(String value1, String value2) {
            addCriterion("URL_INFO between", value1, value2, "urlInfo");
            return (Criteria) this;
        }

        public Criteria andUrlInfoNotBetween(String value1, String value2) {
            addCriterion("URL_INFO not between", value1, value2, "urlInfo");
            return (Criteria) this;
        }

        public Criteria andShowTitleIsNull() {
            addCriterion("SHOW_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andShowTitleIsNotNull() {
            addCriterion("SHOW_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andShowTitleEqualTo(String value) {
            addCriterion("SHOW_TITLE =", value, "showTitle");
            return (Criteria) this;
        }

        public Criteria andShowTitleNotEqualTo(String value) {
            addCriterion("SHOW_TITLE <>", value, "showTitle");
            return (Criteria) this;
        }

        public Criteria andShowTitleGreaterThan(String value) {
            addCriterion("SHOW_TITLE >", value, "showTitle");
            return (Criteria) this;
        }

        public Criteria andShowTitleGreaterThanOrEqualTo(String value) {
            addCriterion("SHOW_TITLE >=", value, "showTitle");
            return (Criteria) this;
        }

        public Criteria andShowTitleLessThan(String value) {
            addCriterion("SHOW_TITLE <", value, "showTitle");
            return (Criteria) this;
        }

        public Criteria andShowTitleLessThanOrEqualTo(String value) {
            addCriterion("SHOW_TITLE <=", value, "showTitle");
            return (Criteria) this;
        }

        public Criteria andShowTitleLike(String value) {
            addCriterion("SHOW_TITLE like", value, "showTitle");
            return (Criteria) this;
        }

        public Criteria andShowTitleNotLike(String value) {
            addCriterion("SHOW_TITLE not like", value, "showTitle");
            return (Criteria) this;
        }

        public Criteria andShowTitleIn(List<String> values) {
            addCriterion("SHOW_TITLE in", values, "showTitle");
            return (Criteria) this;
        }

        public Criteria andShowTitleNotIn(List<String> values) {
            addCriterion("SHOW_TITLE not in", values, "showTitle");
            return (Criteria) this;
        }

        public Criteria andShowTitleBetween(String value1, String value2) {
            addCriterion("SHOW_TITLE between", value1, value2, "showTitle");
            return (Criteria) this;
        }

        public Criteria andShowTitleNotBetween(String value1, String value2) {
            addCriterion("SHOW_TITLE not between", value1, value2, "showTitle");
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
