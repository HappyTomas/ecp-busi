package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GdsInfoCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsInfoCriteria() {
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

        public Criteria andSnapIdIsNull() {
            addCriterion("SNAP_ID is null");
            return (Criteria) this;
        }

        public Criteria andSnapIdIsNotNull() {
            addCriterion("SNAP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSnapIdEqualTo(Long value) {
            addCriterion("SNAP_ID =", value, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdNotEqualTo(Long value) {
            addCriterion("SNAP_ID <>", value, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdGreaterThan(Long value) {
            addCriterion("SNAP_ID >", value, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SNAP_ID >=", value, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdLessThan(Long value) {
            addCriterion("SNAP_ID <", value, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdLessThanOrEqualTo(Long value) {
            addCriterion("SNAP_ID <=", value, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdIn(List<Long> values) {
            addCriterion("SNAP_ID in", values, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdNotIn(List<Long> values) {
            addCriterion("SNAP_ID not in", values, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdBetween(Long value1, Long value2) {
            addCriterion("SNAP_ID between", value1, value2, "snapId");
            return (Criteria) this;
        }

        public Criteria andSnapIdNotBetween(Long value1, Long value2) {
            addCriterion("SNAP_ID not between", value1, value2, "snapId");
            return (Criteria) this;
        }

        public Criteria andGdsNameIsNull() {
            addCriterion("GDS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGdsNameIsNotNull() {
            addCriterion("GDS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGdsNameEqualTo(String value) {
            addCriterion("GDS_NAME =", value, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameNotEqualTo(String value) {
            addCriterion("GDS_NAME <>", value, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameGreaterThan(String value) {
            addCriterion("GDS_NAME >", value, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameGreaterThanOrEqualTo(String value) {
            addCriterion("GDS_NAME >=", value, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameLessThan(String value) {
            addCriterion("GDS_NAME <", value, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameLessThanOrEqualTo(String value) {
            addCriterion("GDS_NAME <=", value, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameLike(String value) {
            addCriterion("GDS_NAME like", value, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameNotLike(String value) {
            addCriterion("GDS_NAME not like", value, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameIn(List<String> values) {
            addCriterion("GDS_NAME in", values, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameNotIn(List<String> values) {
            addCriterion("GDS_NAME not in", values, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameBetween(String value1, String value2) {
            addCriterion("GDS_NAME between", value1, value2, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsNameNotBetween(String value1, String value2) {
            addCriterion("GDS_NAME not between", value1, value2, "gdsName");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadIsNull() {
            addCriterion("GDS_SUB_HEAD is null");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadIsNotNull() {
            addCriterion("GDS_SUB_HEAD is not null");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadEqualTo(String value) {
            addCriterion("GDS_SUB_HEAD =", value, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadNotEqualTo(String value) {
            addCriterion("GDS_SUB_HEAD <>", value, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadGreaterThan(String value) {
            addCriterion("GDS_SUB_HEAD >", value, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadGreaterThanOrEqualTo(String value) {
            addCriterion("GDS_SUB_HEAD >=", value, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadLessThan(String value) {
            addCriterion("GDS_SUB_HEAD <", value, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadLessThanOrEqualTo(String value) {
            addCriterion("GDS_SUB_HEAD <=", value, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadLike(String value) {
            addCriterion("GDS_SUB_HEAD like", value, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadNotLike(String value) {
            addCriterion("GDS_SUB_HEAD not like", value, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadIn(List<String> values) {
            addCriterion("GDS_SUB_HEAD in", values, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadNotIn(List<String> values) {
            addCriterion("GDS_SUB_HEAD not in", values, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadBetween(String value1, String value2) {
            addCriterion("GDS_SUB_HEAD between", value1, value2, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGdsSubHeadNotBetween(String value1, String value2) {
            addCriterion("GDS_SUB_HEAD not between", value1, value2, "gdsSubHead");
            return (Criteria) this;
        }

        public Criteria andGuidePriceIsNull() {
            addCriterion("GUIDE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andGuidePriceIsNotNull() {
            addCriterion("GUIDE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andGuidePriceEqualTo(Long value) {
            addCriterion("GUIDE_PRICE =", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceNotEqualTo(Long value) {
            addCriterion("GUIDE_PRICE <>", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceGreaterThan(Long value) {
            addCriterion("GUIDE_PRICE >", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("GUIDE_PRICE >=", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceLessThan(Long value) {
            addCriterion("GUIDE_PRICE <", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceLessThanOrEqualTo(Long value) {
            addCriterion("GUIDE_PRICE <=", value, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceIn(List<Long> values) {
            addCriterion("GUIDE_PRICE in", values, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceNotIn(List<Long> values) {
            addCriterion("GUIDE_PRICE not in", values, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceBetween(Long value1, Long value2) {
            addCriterion("GUIDE_PRICE between", value1, value2, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGuidePriceNotBetween(Long value1, Long value2) {
            addCriterion("GUIDE_PRICE not between", value1, value2, "guidePrice");
            return (Criteria) this;
        }

        public Criteria andGdsDescIsNull() {
            addCriterion("GDS_DESC is null");
            return (Criteria) this;
        }

        public Criteria andGdsDescIsNotNull() {
            addCriterion("GDS_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andGdsDescEqualTo(String value) {
            addCriterion("GDS_DESC =", value, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescNotEqualTo(String value) {
            addCriterion("GDS_DESC <>", value, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescGreaterThan(String value) {
            addCriterion("GDS_DESC >", value, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescGreaterThanOrEqualTo(String value) {
            addCriterion("GDS_DESC >=", value, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescLessThan(String value) {
            addCriterion("GDS_DESC <", value, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescLessThanOrEqualTo(String value) {
            addCriterion("GDS_DESC <=", value, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescLike(String value) {
            addCriterion("GDS_DESC like", value, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescNotLike(String value) {
            addCriterion("GDS_DESC not like", value, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescIn(List<String> values) {
            addCriterion("GDS_DESC in", values, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescNotIn(List<String> values) {
            addCriterion("GDS_DESC not in", values, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescBetween(String value1, String value2) {
            addCriterion("GDS_DESC between", value1, value2, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsDescNotBetween(String value1, String value2) {
            addCriterion("GDS_DESC not between", value1, value2, "gdsDesc");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistIsNull() {
            addCriterion("GDS_PARTLIST is null");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistIsNotNull() {
            addCriterion("GDS_PARTLIST is not null");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistEqualTo(String value) {
            addCriterion("GDS_PARTLIST =", value, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistNotEqualTo(String value) {
            addCriterion("GDS_PARTLIST <>", value, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistGreaterThan(String value) {
            addCriterion("GDS_PARTLIST >", value, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistGreaterThanOrEqualTo(String value) {
            addCriterion("GDS_PARTLIST >=", value, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistLessThan(String value) {
            addCriterion("GDS_PARTLIST <", value, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistLessThanOrEqualTo(String value) {
            addCriterion("GDS_PARTLIST <=", value, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistLike(String value) {
            addCriterion("GDS_PARTLIST like", value, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistNotLike(String value) {
            addCriterion("GDS_PARTLIST not like", value, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistIn(List<String> values) {
            addCriterion("GDS_PARTLIST in", values, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistNotIn(List<String> values) {
            addCriterion("GDS_PARTLIST not in", values, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistBetween(String value1, String value2) {
            addCriterion("GDS_PARTLIST between", value1, value2, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsPartlistNotBetween(String value1, String value2) {
            addCriterion("GDS_PARTLIST not between", value1, value2, "gdsPartlist");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdIsNull() {
            addCriterion("GDS_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdIsNotNull() {
            addCriterion("GDS_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdEqualTo(Long value) {
            addCriterion("GDS_TYPE_ID =", value, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdNotEqualTo(Long value) {
            addCriterion("GDS_TYPE_ID <>", value, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdGreaterThan(Long value) {
            addCriterion("GDS_TYPE_ID >", value, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("GDS_TYPE_ID >=", value, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdLessThan(Long value) {
            addCriterion("GDS_TYPE_ID <", value, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("GDS_TYPE_ID <=", value, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdIn(List<Long> values) {
            addCriterion("GDS_TYPE_ID in", values, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdNotIn(List<Long> values) {
            addCriterion("GDS_TYPE_ID not in", values, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdBetween(Long value1, Long value2) {
            addCriterion("GDS_TYPE_ID between", value1, value2, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("GDS_TYPE_ID not between", value1, value2, "gdsTypeId");
            return (Criteria) this;
        }

        public Criteria andGdsStatusIsNull() {
            addCriterion("GDS_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andGdsStatusIsNotNull() {
            addCriterion("GDS_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andGdsStatusEqualTo(String value) {
            addCriterion("GDS_STATUS =", value, "gdsStatus");
            return (Criteria) this;
        }

        public Criteria andGdsStatusNotEqualTo(String value) {
            addCriterion("GDS_STATUS <>", value, "gdsStatus");
            return (Criteria) this;
        }

        public Criteria andGdsStatusGreaterThan(String value) {
            addCriterion("GDS_STATUS >", value, "gdsStatus");
            return (Criteria) this;
        }

        public Criteria andGdsStatusGreaterThanOrEqualTo(String value) {
            addCriterion("GDS_STATUS >=", value, "gdsStatus");
            return (Criteria) this;
        }

        public Criteria andGdsStatusLessThan(String value) {
            addCriterion("GDS_STATUS <", value, "gdsStatus");
            return (Criteria) this;
        }

        public Criteria andGdsStatusLessThanOrEqualTo(String value) {
            addCriterion("GDS_STATUS <=", value, "gdsStatus");
            return (Criteria) this;
        }

        public Criteria andGdsStatusLike(String value) {
            addCriterion("GDS_STATUS like", value, "gdsStatus");
            return (Criteria) this;
        }

        public Criteria andGdsStatusNotLike(String value) {
            addCriterion("GDS_STATUS not like", value, "gdsStatus");
            return (Criteria) this;
        }

        public Criteria andGdsStatusIn(List<String> values) {
            addCriterion("GDS_STATUS in", values, "gdsStatus");
            return (Criteria) this;
        }

        public Criteria andGdsStatusNotIn(List<String> values) {
            addCriterion("GDS_STATUS not in", values, "gdsStatus");
            return (Criteria) this;
        }

        public Criteria andGdsStatusBetween(String value1, String value2) {
            addCriterion("GDS_STATUS between", value1, value2, "gdsStatus");
            return (Criteria) this;
        }

        public Criteria andGdsStatusNotBetween(String value1, String value2) {
            addCriterion("GDS_STATUS not between", value1, value2, "gdsStatus");
            return (Criteria) this;
        }

        public Criteria andGdsApproveIsNull() {
            addCriterion("GDS_APPROVE is null");
            return (Criteria) this;
        }

        public Criteria andGdsApproveIsNotNull() {
            addCriterion("GDS_APPROVE is not null");
            return (Criteria) this;
        }

        public Criteria andGdsApproveEqualTo(String value) {
            addCriterion("GDS_APPROVE =", value, "gdsApprove");
            return (Criteria) this;
        }

        public Criteria andGdsApproveNotEqualTo(String value) {
            addCriterion("GDS_APPROVE <>", value, "gdsApprove");
            return (Criteria) this;
        }

        public Criteria andGdsApproveGreaterThan(String value) {
            addCriterion("GDS_APPROVE >", value, "gdsApprove");
            return (Criteria) this;
        }

        public Criteria andGdsApproveGreaterThanOrEqualTo(String value) {
            addCriterion("GDS_APPROVE >=", value, "gdsApprove");
            return (Criteria) this;
        }

        public Criteria andGdsApproveLessThan(String value) {
            addCriterion("GDS_APPROVE <", value, "gdsApprove");
            return (Criteria) this;
        }

        public Criteria andGdsApproveLessThanOrEqualTo(String value) {
            addCriterion("GDS_APPROVE <=", value, "gdsApprove");
            return (Criteria) this;
        }

        public Criteria andGdsApproveLike(String value) {
            addCriterion("GDS_APPROVE like", value, "gdsApprove");
            return (Criteria) this;
        }

        public Criteria andGdsApproveNotLike(String value) {
            addCriterion("GDS_APPROVE not like", value, "gdsApprove");
            return (Criteria) this;
        }

        public Criteria andGdsApproveIn(List<String> values) {
            addCriterion("GDS_APPROVE in", values, "gdsApprove");
            return (Criteria) this;
        }

        public Criteria andGdsApproveNotIn(List<String> values) {
            addCriterion("GDS_APPROVE not in", values, "gdsApprove");
            return (Criteria) this;
        }

        public Criteria andGdsApproveBetween(String value1, String value2) {
            addCriterion("GDS_APPROVE between", value1, value2, "gdsApprove");
            return (Criteria) this;
        }

        public Criteria andGdsApproveNotBetween(String value1, String value2) {
            addCriterion("GDS_APPROVE not between", value1, value2, "gdsApprove");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("URL is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("URL is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("URL =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("URL <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("URL >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("URL >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("URL <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("URL <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("URL like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("URL not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("URL in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("URL not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("URL between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("URL not between", value1, value2, "url");
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

        public Criteria andTaxIdIsNull() {
            addCriterion("TAX_ID is null");
            return (Criteria) this;
        }

        public Criteria andTaxIdIsNotNull() {
            addCriterion("TAX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTaxIdEqualTo(Long value) {
            addCriterion("TAX_ID =", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdNotEqualTo(Long value) {
            addCriterion("TAX_ID <>", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdGreaterThan(Long value) {
            addCriterion("TAX_ID >", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TAX_ID >=", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdLessThan(Long value) {
            addCriterion("TAX_ID <", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdLessThanOrEqualTo(Long value) {
            addCriterion("TAX_ID <=", value, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdIn(List<Long> values) {
            addCriterion("TAX_ID in", values, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdNotIn(List<Long> values) {
            addCriterion("TAX_ID not in", values, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdBetween(Long value1, Long value2) {
            addCriterion("TAX_ID between", value1, value2, "taxId");
            return (Criteria) this;
        }

        public Criteria andTaxIdNotBetween(Long value1, Long value2) {
            addCriterion("TAX_ID not between", value1, value2, "taxId");
            return (Criteria) this;
        }

        public Criteria andGdsLabelIsNull() {
            addCriterion("GDS_LABEL is null");
            return (Criteria) this;
        }

        public Criteria andGdsLabelIsNotNull() {
            addCriterion("GDS_LABEL is not null");
            return (Criteria) this;
        }

        public Criteria andGdsLabelEqualTo(String value) {
            addCriterion("GDS_LABEL =", value, "gdsLabel");
            return (Criteria) this;
        }

        public Criteria andGdsLabelNotEqualTo(String value) {
            addCriterion("GDS_LABEL <>", value, "gdsLabel");
            return (Criteria) this;
        }

        public Criteria andGdsLabelGreaterThan(String value) {
            addCriterion("GDS_LABEL >", value, "gdsLabel");
            return (Criteria) this;
        }

        public Criteria andGdsLabelGreaterThanOrEqualTo(String value) {
            addCriterion("GDS_LABEL >=", value, "gdsLabel");
            return (Criteria) this;
        }

        public Criteria andGdsLabelLessThan(String value) {
            addCriterion("GDS_LABEL <", value, "gdsLabel");
            return (Criteria) this;
        }

        public Criteria andGdsLabelLessThanOrEqualTo(String value) {
            addCriterion("GDS_LABEL <=", value, "gdsLabel");
            return (Criteria) this;
        }

        public Criteria andGdsLabelLike(String value) {
            addCriterion("GDS_LABEL like", value, "gdsLabel");
            return (Criteria) this;
        }

        public Criteria andGdsLabelNotLike(String value) {
            addCriterion("GDS_LABEL not like", value, "gdsLabel");
            return (Criteria) this;
        }

        public Criteria andGdsLabelIn(List<String> values) {
            addCriterion("GDS_LABEL in", values, "gdsLabel");
            return (Criteria) this;
        }

        public Criteria andGdsLabelNotIn(List<String> values) {
            addCriterion("GDS_LABEL not in", values, "gdsLabel");
            return (Criteria) this;
        }

        public Criteria andGdsLabelBetween(String value1, String value2) {
            addCriterion("GDS_LABEL between", value1, value2, "gdsLabel");
            return (Criteria) this;
        }

        public Criteria andGdsLabelNotBetween(String value1, String value2) {
            addCriterion("GDS_LABEL not between", value1, value2, "gdsLabel");
            return (Criteria) this;
        }

        public Criteria andPutonTimeIsNull() {
            addCriterion("PUTON_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPutonTimeIsNotNull() {
            addCriterion("PUTON_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPutonTimeEqualTo(Timestamp value) {
            addCriterion("PUTON_TIME =", value, "putonTime");
            return (Criteria) this;
        }

        public Criteria andPutonTimeNotEqualTo(Timestamp value) {
            addCriterion("PUTON_TIME <>", value, "putonTime");
            return (Criteria) this;
        }

        public Criteria andPutonTimeGreaterThan(Timestamp value) {
            addCriterion("PUTON_TIME >", value, "putonTime");
            return (Criteria) this;
        }

        public Criteria andPutonTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("PUTON_TIME >=", value, "putonTime");
            return (Criteria) this;
        }

        public Criteria andPutonTimeLessThan(Timestamp value) {
            addCriterion("PUTON_TIME <", value, "putonTime");
            return (Criteria) this;
        }

        public Criteria andPutonTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("PUTON_TIME <=", value, "putonTime");
            return (Criteria) this;
        }

        public Criteria andPutonTimeIn(List<Timestamp> values) {
            addCriterion("PUTON_TIME in", values, "putonTime");
            return (Criteria) this;
        }

        public Criteria andPutonTimeNotIn(List<Timestamp> values) {
            addCriterion("PUTON_TIME not in", values, "putonTime");
            return (Criteria) this;
        }

        public Criteria andPutonTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PUTON_TIME between", value1, value2, "putonTime");
            return (Criteria) this;
        }

        public Criteria andPutonTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PUTON_TIME not between", value1, value2, "putonTime");
            return (Criteria) this;
        }

        public Criteria andPutoffTimeIsNull() {
            addCriterion("PUTOFF_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPutoffTimeIsNotNull() {
            addCriterion("PUTOFF_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPutoffTimeEqualTo(Timestamp value) {
            addCriterion("PUTOFF_TIME =", value, "putoffTime");
            return (Criteria) this;
        }

        public Criteria andPutoffTimeNotEqualTo(Timestamp value) {
            addCriterion("PUTOFF_TIME <>", value, "putoffTime");
            return (Criteria) this;
        }

        public Criteria andPutoffTimeGreaterThan(Timestamp value) {
            addCriterion("PUTOFF_TIME >", value, "putoffTime");
            return (Criteria) this;
        }

        public Criteria andPutoffTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("PUTOFF_TIME >=", value, "putoffTime");
            return (Criteria) this;
        }

        public Criteria andPutoffTimeLessThan(Timestamp value) {
            addCriterion("PUTOFF_TIME <", value, "putoffTime");
            return (Criteria) this;
        }

        public Criteria andPutoffTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("PUTOFF_TIME <=", value, "putoffTime");
            return (Criteria) this;
        }

        public Criteria andPutoffTimeIn(List<Timestamp> values) {
            addCriterion("PUTOFF_TIME in", values, "putoffTime");
            return (Criteria) this;
        }

        public Criteria andPutoffTimeNotIn(List<Timestamp> values) {
            addCriterion("PUTOFF_TIME not in", values, "putoffTime");
            return (Criteria) this;
        }

        public Criteria andPutoffTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PUTOFF_TIME between", value1, value2, "putoffTime");
            return (Criteria) this;
        }

        public Criteria andPutoffTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PUTOFF_TIME not between", value1, value2, "putoffTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeIsNull() {
            addCriterion("POST_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPostTimeIsNotNull() {
            addCriterion("POST_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPostTimeEqualTo(Long value) {
            addCriterion("POST_TIME =", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotEqualTo(Long value) {
            addCriterion("POST_TIME <>", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeGreaterThan(Long value) {
            addCriterion("POST_TIME >", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("POST_TIME >=", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeLessThan(Long value) {
            addCriterion("POST_TIME <", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeLessThanOrEqualTo(Long value) {
            addCriterion("POST_TIME <=", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeIn(List<Long> values) {
            addCriterion("POST_TIME in", values, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotIn(List<Long> values) {
            addCriterion("POST_TIME not in", values, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeBetween(Long value1, Long value2) {
            addCriterion("POST_TIME between", value1, value2, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotBetween(Long value1, Long value2) {
            addCriterion("POST_TIME not between", value1, value2, "postTime");
            return (Criteria) this;
        }

        public Criteria andIfSendscoreIsNull() {
            addCriterion("IF_SENDSCORE is null");
            return (Criteria) this;
        }

        public Criteria andIfSendscoreIsNotNull() {
            addCriterion("IF_SENDSCORE is not null");
            return (Criteria) this;
        }

        public Criteria andIfSendscoreEqualTo(String value) {
            addCriterion("IF_SENDSCORE =", value, "ifSendscore");
            return (Criteria) this;
        }

        public Criteria andIfSendscoreNotEqualTo(String value) {
            addCriterion("IF_SENDSCORE <>", value, "ifSendscore");
            return (Criteria) this;
        }

        public Criteria andIfSendscoreGreaterThan(String value) {
            addCriterion("IF_SENDSCORE >", value, "ifSendscore");
            return (Criteria) this;
        }

        public Criteria andIfSendscoreGreaterThanOrEqualTo(String value) {
            addCriterion("IF_SENDSCORE >=", value, "ifSendscore");
            return (Criteria) this;
        }

        public Criteria andIfSendscoreLessThan(String value) {
            addCriterion("IF_SENDSCORE <", value, "ifSendscore");
            return (Criteria) this;
        }

        public Criteria andIfSendscoreLessThanOrEqualTo(String value) {
            addCriterion("IF_SENDSCORE <=", value, "ifSendscore");
            return (Criteria) this;
        }

        public Criteria andIfSendscoreLike(String value) {
            addCriterion("IF_SENDSCORE like", value, "ifSendscore");
            return (Criteria) this;
        }

        public Criteria andIfSendscoreNotLike(String value) {
            addCriterion("IF_SENDSCORE not like", value, "ifSendscore");
            return (Criteria) this;
        }

        public Criteria andIfSendscoreIn(List<String> values) {
            addCriterion("IF_SENDSCORE in", values, "ifSendscore");
            return (Criteria) this;
        }

        public Criteria andIfSendscoreNotIn(List<String> values) {
            addCriterion("IF_SENDSCORE not in", values, "ifSendscore");
            return (Criteria) this;
        }

        public Criteria andIfSendscoreBetween(String value1, String value2) {
            addCriterion("IF_SENDSCORE between", value1, value2, "ifSendscore");
            return (Criteria) this;
        }

        public Criteria andIfSendscoreNotBetween(String value1, String value2) {
            addCriterion("IF_SENDSCORE not between", value1, value2, "ifSendscore");
            return (Criteria) this;
        }

        public Criteria andIfSalealoneIsNull() {
            addCriterion("IF_SALEALONE is null");
            return (Criteria) this;
        }

        public Criteria andIfSalealoneIsNotNull() {
            addCriterion("IF_SALEALONE is not null");
            return (Criteria) this;
        }

        public Criteria andIfSalealoneEqualTo(String value) {
            addCriterion("IF_SALEALONE =", value, "ifSalealone");
            return (Criteria) this;
        }

        public Criteria andIfSalealoneNotEqualTo(String value) {
            addCriterion("IF_SALEALONE <>", value, "ifSalealone");
            return (Criteria) this;
        }

        public Criteria andIfSalealoneGreaterThan(String value) {
            addCriterion("IF_SALEALONE >", value, "ifSalealone");
            return (Criteria) this;
        }

        public Criteria andIfSalealoneGreaterThanOrEqualTo(String value) {
            addCriterion("IF_SALEALONE >=", value, "ifSalealone");
            return (Criteria) this;
        }

        public Criteria andIfSalealoneLessThan(String value) {
            addCriterion("IF_SALEALONE <", value, "ifSalealone");
            return (Criteria) this;
        }

        public Criteria andIfSalealoneLessThanOrEqualTo(String value) {
            addCriterion("IF_SALEALONE <=", value, "ifSalealone");
            return (Criteria) this;
        }

        public Criteria andIfSalealoneLike(String value) {
            addCriterion("IF_SALEALONE like", value, "ifSalealone");
            return (Criteria) this;
        }

        public Criteria andIfSalealoneNotLike(String value) {
            addCriterion("IF_SALEALONE not like", value, "ifSalealone");
            return (Criteria) this;
        }

        public Criteria andIfSalealoneIn(List<String> values) {
            addCriterion("IF_SALEALONE in", values, "ifSalealone");
            return (Criteria) this;
        }

        public Criteria andIfSalealoneNotIn(List<String> values) {
            addCriterion("IF_SALEALONE not in", values, "ifSalealone");
            return (Criteria) this;
        }

        public Criteria andIfSalealoneBetween(String value1, String value2) {
            addCriterion("IF_SALEALONE between", value1, value2, "ifSalealone");
            return (Criteria) this;
        }

        public Criteria andIfSalealoneNotBetween(String value1, String value2) {
            addCriterion("IF_SALEALONE not between", value1, value2, "ifSalealone");
            return (Criteria) this;
        }

        public Criteria andIfRecommIsNull() {
            addCriterion("IF_RECOMM is null");
            return (Criteria) this;
        }

        public Criteria andIfRecommIsNotNull() {
            addCriterion("IF_RECOMM is not null");
            return (Criteria) this;
        }

        public Criteria andIfRecommEqualTo(String value) {
            addCriterion("IF_RECOMM =", value, "ifRecomm");
            return (Criteria) this;
        }

        public Criteria andIfRecommNotEqualTo(String value) {
            addCriterion("IF_RECOMM <>", value, "ifRecomm");
            return (Criteria) this;
        }

        public Criteria andIfRecommGreaterThan(String value) {
            addCriterion("IF_RECOMM >", value, "ifRecomm");
            return (Criteria) this;
        }

        public Criteria andIfRecommGreaterThanOrEqualTo(String value) {
            addCriterion("IF_RECOMM >=", value, "ifRecomm");
            return (Criteria) this;
        }

        public Criteria andIfRecommLessThan(String value) {
            addCriterion("IF_RECOMM <", value, "ifRecomm");
            return (Criteria) this;
        }

        public Criteria andIfRecommLessThanOrEqualTo(String value) {
            addCriterion("IF_RECOMM <=", value, "ifRecomm");
            return (Criteria) this;
        }

        public Criteria andIfRecommLike(String value) {
            addCriterion("IF_RECOMM like", value, "ifRecomm");
            return (Criteria) this;
        }

        public Criteria andIfRecommNotLike(String value) {
            addCriterion("IF_RECOMM not like", value, "ifRecomm");
            return (Criteria) this;
        }

        public Criteria andIfRecommIn(List<String> values) {
            addCriterion("IF_RECOMM in", values, "ifRecomm");
            return (Criteria) this;
        }

        public Criteria andIfRecommNotIn(List<String> values) {
            addCriterion("IF_RECOMM not in", values, "ifRecomm");
            return (Criteria) this;
        }

        public Criteria andIfRecommBetween(String value1, String value2) {
            addCriterion("IF_RECOMM between", value1, value2, "ifRecomm");
            return (Criteria) this;
        }

        public Criteria andIfRecommNotBetween(String value1, String value2) {
            addCriterion("IF_RECOMM not between", value1, value2, "ifRecomm");
            return (Criteria) this;
        }

        public Criteria andIfNewIsNull() {
            addCriterion("IF_NEW is null");
            return (Criteria) this;
        }

        public Criteria andIfNewIsNotNull() {
            addCriterion("IF_NEW is not null");
            return (Criteria) this;
        }

        public Criteria andIfNewEqualTo(String value) {
            addCriterion("IF_NEW =", value, "ifNew");
            return (Criteria) this;
        }

        public Criteria andIfNewNotEqualTo(String value) {
            addCriterion("IF_NEW <>", value, "ifNew");
            return (Criteria) this;
        }

        public Criteria andIfNewGreaterThan(String value) {
            addCriterion("IF_NEW >", value, "ifNew");
            return (Criteria) this;
        }

        public Criteria andIfNewGreaterThanOrEqualTo(String value) {
            addCriterion("IF_NEW >=", value, "ifNew");
            return (Criteria) this;
        }

        public Criteria andIfNewLessThan(String value) {
            addCriterion("IF_NEW <", value, "ifNew");
            return (Criteria) this;
        }

        public Criteria andIfNewLessThanOrEqualTo(String value) {
            addCriterion("IF_NEW <=", value, "ifNew");
            return (Criteria) this;
        }

        public Criteria andIfNewLike(String value) {
            addCriterion("IF_NEW like", value, "ifNew");
            return (Criteria) this;
        }

        public Criteria andIfNewNotLike(String value) {
            addCriterion("IF_NEW not like", value, "ifNew");
            return (Criteria) this;
        }

        public Criteria andIfNewIn(List<String> values) {
            addCriterion("IF_NEW in", values, "ifNew");
            return (Criteria) this;
        }

        public Criteria andIfNewNotIn(List<String> values) {
            addCriterion("IF_NEW not in", values, "ifNew");
            return (Criteria) this;
        }

        public Criteria andIfNewBetween(String value1, String value2) {
            addCriterion("IF_NEW between", value1, value2, "ifNew");
            return (Criteria) this;
        }

        public Criteria andIfNewNotBetween(String value1, String value2) {
            addCriterion("IF_NEW not between", value1, value2, "ifNew");
            return (Criteria) this;
        }

        public Criteria andIfStocknoticeIsNull() {
            addCriterion("IF_STOCKNOTICE is null");
            return (Criteria) this;
        }

        public Criteria andIfStocknoticeIsNotNull() {
            addCriterion("IF_STOCKNOTICE is not null");
            return (Criteria) this;
        }

        public Criteria andIfStocknoticeEqualTo(String value) {
            addCriterion("IF_STOCKNOTICE =", value, "ifStocknotice");
            return (Criteria) this;
        }

        public Criteria andIfStocknoticeNotEqualTo(String value) {
            addCriterion("IF_STOCKNOTICE <>", value, "ifStocknotice");
            return (Criteria) this;
        }

        public Criteria andIfStocknoticeGreaterThan(String value) {
            addCriterion("IF_STOCKNOTICE >", value, "ifStocknotice");
            return (Criteria) this;
        }

        public Criteria andIfStocknoticeGreaterThanOrEqualTo(String value) {
            addCriterion("IF_STOCKNOTICE >=", value, "ifStocknotice");
            return (Criteria) this;
        }

        public Criteria andIfStocknoticeLessThan(String value) {
            addCriterion("IF_STOCKNOTICE <", value, "ifStocknotice");
            return (Criteria) this;
        }

        public Criteria andIfStocknoticeLessThanOrEqualTo(String value) {
            addCriterion("IF_STOCKNOTICE <=", value, "ifStocknotice");
            return (Criteria) this;
        }

        public Criteria andIfStocknoticeLike(String value) {
            addCriterion("IF_STOCKNOTICE like", value, "ifStocknotice");
            return (Criteria) this;
        }

        public Criteria andIfStocknoticeNotLike(String value) {
            addCriterion("IF_STOCKNOTICE not like", value, "ifStocknotice");
            return (Criteria) this;
        }

        public Criteria andIfStocknoticeIn(List<String> values) {
            addCriterion("IF_STOCKNOTICE in", values, "ifStocknotice");
            return (Criteria) this;
        }

        public Criteria andIfStocknoticeNotIn(List<String> values) {
            addCriterion("IF_STOCKNOTICE not in", values, "ifStocknotice");
            return (Criteria) this;
        }

        public Criteria andIfStocknoticeBetween(String value1, String value2) {
            addCriterion("IF_STOCKNOTICE between", value1, value2, "ifStocknotice");
            return (Criteria) this;
        }

        public Criteria andIfStocknoticeNotBetween(String value1, String value2) {
            addCriterion("IF_STOCKNOTICE not between", value1, value2, "ifStocknotice");
            return (Criteria) this;
        }

        public Criteria andIfFreeIsNull() {
            addCriterion("IF_FREE is null");
            return (Criteria) this;
        }

        public Criteria andIfFreeIsNotNull() {
            addCriterion("IF_FREE is not null");
            return (Criteria) this;
        }

        public Criteria andIfFreeEqualTo(String value) {
            addCriterion("IF_FREE =", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeNotEqualTo(String value) {
            addCriterion("IF_FREE <>", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeGreaterThan(String value) {
            addCriterion("IF_FREE >", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeGreaterThanOrEqualTo(String value) {
            addCriterion("IF_FREE >=", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeLessThan(String value) {
            addCriterion("IF_FREE <", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeLessThanOrEqualTo(String value) {
            addCriterion("IF_FREE <=", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeLike(String value) {
            addCriterion("IF_FREE like", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeNotLike(String value) {
            addCriterion("IF_FREE not like", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeIn(List<String> values) {
            addCriterion("IF_FREE in", values, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeNotIn(List<String> values) {
            addCriterion("IF_FREE not in", values, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeBetween(String value1, String value2) {
            addCriterion("IF_FREE between", value1, value2, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeNotBetween(String value1, String value2) {
            addCriterion("IF_FREE not between", value1, value2, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfDisperseStockIsNull() {
            addCriterion("IF_DISPERSE_STOCK is null");
            return (Criteria) this;
        }

        public Criteria andIfDisperseStockIsNotNull() {
            addCriterion("IF_DISPERSE_STOCK is not null");
            return (Criteria) this;
        }

        public Criteria andIfDisperseStockEqualTo(String value) {
            addCriterion("IF_DISPERSE_STOCK =", value, "ifDisperseStock");
            return (Criteria) this;
        }

        public Criteria andIfDisperseStockNotEqualTo(String value) {
            addCriterion("IF_DISPERSE_STOCK <>", value, "ifDisperseStock");
            return (Criteria) this;
        }

        public Criteria andIfDisperseStockGreaterThan(String value) {
            addCriterion("IF_DISPERSE_STOCK >", value, "ifDisperseStock");
            return (Criteria) this;
        }

        public Criteria andIfDisperseStockGreaterThanOrEqualTo(String value) {
            addCriterion("IF_DISPERSE_STOCK >=", value, "ifDisperseStock");
            return (Criteria) this;
        }

        public Criteria andIfDisperseStockLessThan(String value) {
            addCriterion("IF_DISPERSE_STOCK <", value, "ifDisperseStock");
            return (Criteria) this;
        }

        public Criteria andIfDisperseStockLessThanOrEqualTo(String value) {
            addCriterion("IF_DISPERSE_STOCK <=", value, "ifDisperseStock");
            return (Criteria) this;
        }

        public Criteria andIfDisperseStockLike(String value) {
            addCriterion("IF_DISPERSE_STOCK like", value, "ifDisperseStock");
            return (Criteria) this;
        }

        public Criteria andIfDisperseStockNotLike(String value) {
            addCriterion("IF_DISPERSE_STOCK not like", value, "ifDisperseStock");
            return (Criteria) this;
        }

        public Criteria andIfDisperseStockIn(List<String> values) {
            addCriterion("IF_DISPERSE_STOCK in", values, "ifDisperseStock");
            return (Criteria) this;
        }

        public Criteria andIfDisperseStockNotIn(List<String> values) {
            addCriterion("IF_DISPERSE_STOCK not in", values, "ifDisperseStock");
            return (Criteria) this;
        }

        public Criteria andIfDisperseStockBetween(String value1, String value2) {
            addCriterion("IF_DISPERSE_STOCK between", value1, value2, "ifDisperseStock");
            return (Criteria) this;
        }

        public Criteria andIfDisperseStockNotBetween(String value1, String value2) {
            addCriterion("IF_DISPERSE_STOCK not between", value1, value2, "ifDisperseStock");
            return (Criteria) this;
        }

        public Criteria andIfLadderPriceIsNull() {
            addCriterion("IF_LADDER_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andIfLadderPriceIsNotNull() {
            addCriterion("IF_LADDER_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andIfLadderPriceEqualTo(String value) {
            addCriterion("IF_LADDER_PRICE =", value, "ifLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIfLadderPriceNotEqualTo(String value) {
            addCriterion("IF_LADDER_PRICE <>", value, "ifLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIfLadderPriceGreaterThan(String value) {
            addCriterion("IF_LADDER_PRICE >", value, "ifLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIfLadderPriceGreaterThanOrEqualTo(String value) {
            addCriterion("IF_LADDER_PRICE >=", value, "ifLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIfLadderPriceLessThan(String value) {
            addCriterion("IF_LADDER_PRICE <", value, "ifLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIfLadderPriceLessThanOrEqualTo(String value) {
            addCriterion("IF_LADDER_PRICE <=", value, "ifLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIfLadderPriceLike(String value) {
            addCriterion("IF_LADDER_PRICE like", value, "ifLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIfLadderPriceNotLike(String value) {
            addCriterion("IF_LADDER_PRICE not like", value, "ifLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIfLadderPriceIn(List<String> values) {
            addCriterion("IF_LADDER_PRICE in", values, "ifLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIfLadderPriceNotIn(List<String> values) {
            addCriterion("IF_LADDER_PRICE not in", values, "ifLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIfLadderPriceBetween(String value1, String value2) {
            addCriterion("IF_LADDER_PRICE between", value1, value2, "ifLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIfLadderPriceNotBetween(String value1, String value2) {
            addCriterion("IF_LADDER_PRICE not between", value1, value2, "ifLadderPrice");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeIsNull() {
            addCriterion("IF_ENTITY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeIsNotNull() {
            addCriterion("IF_ENTITY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeEqualTo(String value) {
            addCriterion("IF_ENTITY_CODE =", value, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeNotEqualTo(String value) {
            addCriterion("IF_ENTITY_CODE <>", value, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeGreaterThan(String value) {
            addCriterion("IF_ENTITY_CODE >", value, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("IF_ENTITY_CODE >=", value, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeLessThan(String value) {
            addCriterion("IF_ENTITY_CODE <", value, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeLessThanOrEqualTo(String value) {
            addCriterion("IF_ENTITY_CODE <=", value, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeLike(String value) {
            addCriterion("IF_ENTITY_CODE like", value, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeNotLike(String value) {
            addCriterion("IF_ENTITY_CODE not like", value, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeIn(List<String> values) {
            addCriterion("IF_ENTITY_CODE in", values, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeNotIn(List<String> values) {
            addCriterion("IF_ENTITY_CODE not in", values, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeBetween(String value1, String value2) {
            addCriterion("IF_ENTITY_CODE between", value1, value2, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfEntityCodeNotBetween(String value1, String value2) {
            addCriterion("IF_ENTITY_CODE not between", value1, value2, "ifEntityCode");
            return (Criteria) this;
        }

        public Criteria andIfSeniorPriceIsNull() {
            addCriterion("IF_SENIOR_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andIfSeniorPriceIsNotNull() {
            addCriterion("IF_SENIOR_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andIfSeniorPriceEqualTo(String value) {
            addCriterion("IF_SENIOR_PRICE =", value, "ifSeniorPrice");
            return (Criteria) this;
        }

        public Criteria andIfSeniorPriceNotEqualTo(String value) {
            addCriterion("IF_SENIOR_PRICE <>", value, "ifSeniorPrice");
            return (Criteria) this;
        }

        public Criteria andIfSeniorPriceGreaterThan(String value) {
            addCriterion("IF_SENIOR_PRICE >", value, "ifSeniorPrice");
            return (Criteria) this;
        }

        public Criteria andIfSeniorPriceGreaterThanOrEqualTo(String value) {
            addCriterion("IF_SENIOR_PRICE >=", value, "ifSeniorPrice");
            return (Criteria) this;
        }

        public Criteria andIfSeniorPriceLessThan(String value) {
            addCriterion("IF_SENIOR_PRICE <", value, "ifSeniorPrice");
            return (Criteria) this;
        }

        public Criteria andIfSeniorPriceLessThanOrEqualTo(String value) {
            addCriterion("IF_SENIOR_PRICE <=", value, "ifSeniorPrice");
            return (Criteria) this;
        }

        public Criteria andIfSeniorPriceLike(String value) {
            addCriterion("IF_SENIOR_PRICE like", value, "ifSeniorPrice");
            return (Criteria) this;
        }

        public Criteria andIfSeniorPriceNotLike(String value) {
            addCriterion("IF_SENIOR_PRICE not like", value, "ifSeniorPrice");
            return (Criteria) this;
        }

        public Criteria andIfSeniorPriceIn(List<String> values) {
            addCriterion("IF_SENIOR_PRICE in", values, "ifSeniorPrice");
            return (Criteria) this;
        }

        public Criteria andIfSeniorPriceNotIn(List<String> values) {
            addCriterion("IF_SENIOR_PRICE not in", values, "ifSeniorPrice");
            return (Criteria) this;
        }

        public Criteria andIfSeniorPriceBetween(String value1, String value2) {
            addCriterion("IF_SENIOR_PRICE between", value1, value2, "ifSeniorPrice");
            return (Criteria) this;
        }

        public Criteria andIfSeniorPriceNotBetween(String value1, String value2) {
            addCriterion("IF_SENIOR_PRICE not between", value1, value2, "ifSeniorPrice");
            return (Criteria) this;
        }

        public Criteria andIfScoreGdsIsNull() {
            addCriterion("IF_SCORE_GDS is null");
            return (Criteria) this;
        }

        public Criteria andIfScoreGdsIsNotNull() {
            addCriterion("IF_SCORE_GDS is not null");
            return (Criteria) this;
        }

        public Criteria andIfScoreGdsEqualTo(String value) {
            addCriterion("IF_SCORE_GDS =", value, "ifScoreGds");
            return (Criteria) this;
        }

        public Criteria andIfScoreGdsNotEqualTo(String value) {
            addCriterion("IF_SCORE_GDS <>", value, "ifScoreGds");
            return (Criteria) this;
        }

        public Criteria andIfScoreGdsGreaterThan(String value) {
            addCriterion("IF_SCORE_GDS >", value, "ifScoreGds");
            return (Criteria) this;
        }

        public Criteria andIfScoreGdsGreaterThanOrEqualTo(String value) {
            addCriterion("IF_SCORE_GDS >=", value, "ifScoreGds");
            return (Criteria) this;
        }

        public Criteria andIfScoreGdsLessThan(String value) {
            addCriterion("IF_SCORE_GDS <", value, "ifScoreGds");
            return (Criteria) this;
        }

        public Criteria andIfScoreGdsLessThanOrEqualTo(String value) {
            addCriterion("IF_SCORE_GDS <=", value, "ifScoreGds");
            return (Criteria) this;
        }

        public Criteria andIfScoreGdsLike(String value) {
            addCriterion("IF_SCORE_GDS like", value, "ifScoreGds");
            return (Criteria) this;
        }

        public Criteria andIfScoreGdsNotLike(String value) {
            addCriterion("IF_SCORE_GDS not like", value, "ifScoreGds");
            return (Criteria) this;
        }

        public Criteria andIfScoreGdsIn(List<String> values) {
            addCriterion("IF_SCORE_GDS in", values, "ifScoreGds");
            return (Criteria) this;
        }

        public Criteria andIfScoreGdsNotIn(List<String> values) {
            addCriterion("IF_SCORE_GDS not in", values, "ifScoreGds");
            return (Criteria) this;
        }

        public Criteria andIfScoreGdsBetween(String value1, String value2) {
            addCriterion("IF_SCORE_GDS between", value1, value2, "ifScoreGds");
            return (Criteria) this;
        }

        public Criteria andIfScoreGdsNotBetween(String value1, String value2) {
            addCriterion("IF_SCORE_GDS not between", value1, value2, "ifScoreGds");
            return (Criteria) this;
        }

        public Criteria andShipTemplateIdIsNull() {
            addCriterion("SHIP_TEMPLATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andShipTemplateIdIsNotNull() {
            addCriterion("SHIP_TEMPLATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andShipTemplateIdEqualTo(Long value) {
            addCriterion("SHIP_TEMPLATE_ID =", value, "shipTemplateId");
            return (Criteria) this;
        }

        public Criteria andShipTemplateIdNotEqualTo(Long value) {
            addCriterion("SHIP_TEMPLATE_ID <>", value, "shipTemplateId");
            return (Criteria) this;
        }

        public Criteria andShipTemplateIdGreaterThan(Long value) {
            addCriterion("SHIP_TEMPLATE_ID >", value, "shipTemplateId");
            return (Criteria) this;
        }

        public Criteria andShipTemplateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SHIP_TEMPLATE_ID >=", value, "shipTemplateId");
            return (Criteria) this;
        }

        public Criteria andShipTemplateIdLessThan(Long value) {
            addCriterion("SHIP_TEMPLATE_ID <", value, "shipTemplateId");
            return (Criteria) this;
        }

        public Criteria andShipTemplateIdLessThanOrEqualTo(Long value) {
            addCriterion("SHIP_TEMPLATE_ID <=", value, "shipTemplateId");
            return (Criteria) this;
        }

        public Criteria andShipTemplateIdIn(List<Long> values) {
            addCriterion("SHIP_TEMPLATE_ID in", values, "shipTemplateId");
            return (Criteria) this;
        }

        public Criteria andShipTemplateIdNotIn(List<Long> values) {
            addCriterion("SHIP_TEMPLATE_ID not in", values, "shipTemplateId");
            return (Criteria) this;
        }

        public Criteria andShipTemplateIdBetween(Long value1, Long value2) {
            addCriterion("SHIP_TEMPLATE_ID between", value1, value2, "shipTemplateId");
            return (Criteria) this;
        }

        public Criteria andShipTemplateIdNotBetween(Long value1, Long value2) {
            addCriterion("SHIP_TEMPLATE_ID not between", value1, value2, "shipTemplateId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNull() {
            addCriterion("SUPPLIER_ID is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNotNull() {
            addCriterion("SUPPLIER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdEqualTo(Long value) {
            addCriterion("SUPPLIER_ID =", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotEqualTo(Long value) {
            addCriterion("SUPPLIER_ID <>", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThan(Long value) {
            addCriterion("SUPPLIER_ID >", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SUPPLIER_ID >=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThan(Long value) {
            addCriterion("SUPPLIER_ID <", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThanOrEqualTo(Long value) {
            addCriterion("SUPPLIER_ID <=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIn(List<Long> values) {
            addCriterion("SUPPLIER_ID in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotIn(List<Long> values) {
            addCriterion("SUPPLIER_ID not in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdBetween(Long value1, Long value2) {
            addCriterion("SUPPLIER_ID between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotBetween(Long value1, Long value2) {
            addCriterion("SUPPLIER_ID not between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andPlatCatgsIsNull() {
            addCriterion("PLAT_CATGS is null");
            return (Criteria) this;
        }

        public Criteria andPlatCatgsIsNotNull() {
            addCriterion("PLAT_CATGS is not null");
            return (Criteria) this;
        }

        public Criteria andPlatCatgsEqualTo(String value) {
            addCriterion("PLAT_CATGS =", value, "platCatgs");
            return (Criteria) this;
        }

        public Criteria andPlatCatgsNotEqualTo(String value) {
            addCriterion("PLAT_CATGS <>", value, "platCatgs");
            return (Criteria) this;
        }

        public Criteria andPlatCatgsGreaterThan(String value) {
            addCriterion("PLAT_CATGS >", value, "platCatgs");
            return (Criteria) this;
        }

        public Criteria andPlatCatgsGreaterThanOrEqualTo(String value) {
            addCriterion("PLAT_CATGS >=", value, "platCatgs");
            return (Criteria) this;
        }

        public Criteria andPlatCatgsLessThan(String value) {
            addCriterion("PLAT_CATGS <", value, "platCatgs");
            return (Criteria) this;
        }

        public Criteria andPlatCatgsLessThanOrEqualTo(String value) {
            addCriterion("PLAT_CATGS <=", value, "platCatgs");
            return (Criteria) this;
        }

        public Criteria andPlatCatgsLike(String value) {
            addCriterion("PLAT_CATGS like", value, "platCatgs");
            return (Criteria) this;
        }

        public Criteria andPlatCatgsNotLike(String value) {
            addCriterion("PLAT_CATGS not like", value, "platCatgs");
            return (Criteria) this;
        }

        public Criteria andPlatCatgsIn(List<String> values) {
            addCriterion("PLAT_CATGS in", values, "platCatgs");
            return (Criteria) this;
        }

        public Criteria andPlatCatgsNotIn(List<String> values) {
            addCriterion("PLAT_CATGS not in", values, "platCatgs");
            return (Criteria) this;
        }

        public Criteria andPlatCatgsBetween(String value1, String value2) {
            addCriterion("PLAT_CATGS between", value1, value2, "platCatgs");
            return (Criteria) this;
        }

        public Criteria andPlatCatgsNotBetween(String value1, String value2) {
            addCriterion("PLAT_CATGS not between", value1, value2, "platCatgs");
            return (Criteria) this;
        }

        public Criteria andShopCatgsIsNull() {
            addCriterion("SHOP_CATGS is null");
            return (Criteria) this;
        }

        public Criteria andShopCatgsIsNotNull() {
            addCriterion("SHOP_CATGS is not null");
            return (Criteria) this;
        }

        public Criteria andShopCatgsEqualTo(String value) {
            addCriterion("SHOP_CATGS =", value, "shopCatgs");
            return (Criteria) this;
        }

        public Criteria andShopCatgsNotEqualTo(String value) {
            addCriterion("SHOP_CATGS <>", value, "shopCatgs");
            return (Criteria) this;
        }

        public Criteria andShopCatgsGreaterThan(String value) {
            addCriterion("SHOP_CATGS >", value, "shopCatgs");
            return (Criteria) this;
        }

        public Criteria andShopCatgsGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_CATGS >=", value, "shopCatgs");
            return (Criteria) this;
        }

        public Criteria andShopCatgsLessThan(String value) {
            addCriterion("SHOP_CATGS <", value, "shopCatgs");
            return (Criteria) this;
        }

        public Criteria andShopCatgsLessThanOrEqualTo(String value) {
            addCriterion("SHOP_CATGS <=", value, "shopCatgs");
            return (Criteria) this;
        }

        public Criteria andShopCatgsLike(String value) {
            addCriterion("SHOP_CATGS like", value, "shopCatgs");
            return (Criteria) this;
        }

        public Criteria andShopCatgsNotLike(String value) {
            addCriterion("SHOP_CATGS not like", value, "shopCatgs");
            return (Criteria) this;
        }

        public Criteria andShopCatgsIn(List<String> values) {
            addCriterion("SHOP_CATGS in", values, "shopCatgs");
            return (Criteria) this;
        }

        public Criteria andShopCatgsNotIn(List<String> values) {
            addCriterion("SHOP_CATGS not in", values, "shopCatgs");
            return (Criteria) this;
        }

        public Criteria andShopCatgsBetween(String value1, String value2) {
            addCriterion("SHOP_CATGS between", value1, value2, "shopCatgs");
            return (Criteria) this;
        }

        public Criteria andShopCatgsNotBetween(String value1, String value2) {
            addCriterion("SHOP_CATGS not between", value1, value2, "shopCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsIsNull() {
            addCriterion("MAIN_CATGS is null");
            return (Criteria) this;
        }

        public Criteria andMainCatgsIsNotNull() {
            addCriterion("MAIN_CATGS is not null");
            return (Criteria) this;
        }

        public Criteria andMainCatgsEqualTo(String value) {
            addCriterion("MAIN_CATGS =", value, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsNotEqualTo(String value) {
            addCriterion("MAIN_CATGS <>", value, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsGreaterThan(String value) {
            addCriterion("MAIN_CATGS >", value, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsGreaterThanOrEqualTo(String value) {
            addCriterion("MAIN_CATGS >=", value, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsLessThan(String value) {
            addCriterion("MAIN_CATGS <", value, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsLessThanOrEqualTo(String value) {
            addCriterion("MAIN_CATGS <=", value, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsLike(String value) {
            addCriterion("MAIN_CATGS like", value, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsNotLike(String value) {
            addCriterion("MAIN_CATGS not like", value, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsIn(List<String> values) {
            addCriterion("MAIN_CATGS in", values, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsNotIn(List<String> values) {
            addCriterion("MAIN_CATGS not in", values, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsBetween(String value1, String value2) {
            addCriterion("MAIN_CATGS between", value1, value2, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andMainCatgsNotBetween(String value1, String value2) {
            addCriterion("MAIN_CATGS not between", value1, value2, "mainCatgs");
            return (Criteria) this;
        }

        public Criteria andCatlogIdIsNull() {
            addCriterion("CATLOG_ID is null");
            return (Criteria) this;
        }

        public Criteria andCatlogIdIsNotNull() {
            addCriterion("CATLOG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCatlogIdEqualTo(Long value) {
            addCriterion("CATLOG_ID =", value, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdNotEqualTo(Long value) {
            addCriterion("CATLOG_ID <>", value, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdGreaterThan(Long value) {
            addCriterion("CATLOG_ID >", value, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CATLOG_ID >=", value, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdLessThan(Long value) {
            addCriterion("CATLOG_ID <", value, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdLessThanOrEqualTo(Long value) {
            addCriterion("CATLOG_ID <=", value, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdIn(List<Long> values) {
            addCriterion("CATLOG_ID in", values, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdNotIn(List<Long> values) {
            addCriterion("CATLOG_ID not in", values, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdBetween(Long value1, Long value2) {
            addCriterion("CATLOG_ID between", value1, value2, "catlogId");
            return (Criteria) this;
        }

        public Criteria andCatlogIdNotBetween(Long value1, Long value2) {
            addCriterion("CATLOG_ID not between", value1, value2, "catlogId");
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

        public Criteria andChangePropStrIsNull() {
            addCriterion("CHANGE_PROP_STR is null");
            return (Criteria) this;
        }

        public Criteria andChangePropStrIsNotNull() {
            addCriterion("CHANGE_PROP_STR is not null");
            return (Criteria) this;
        }

        public Criteria andChangePropStrEqualTo(String value) {
            addCriterion("CHANGE_PROP_STR =", value, "changePropStr");
            return (Criteria) this;
        }

        public Criteria andChangePropStrNotEqualTo(String value) {
            addCriterion("CHANGE_PROP_STR <>", value, "changePropStr");
            return (Criteria) this;
        }

        public Criteria andChangePropStrGreaterThan(String value) {
            addCriterion("CHANGE_PROP_STR >", value, "changePropStr");
            return (Criteria) this;
        }

        public Criteria andChangePropStrGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_PROP_STR >=", value, "changePropStr");
            return (Criteria) this;
        }

        public Criteria andChangePropStrLessThan(String value) {
            addCriterion("CHANGE_PROP_STR <", value, "changePropStr");
            return (Criteria) this;
        }

        public Criteria andChangePropStrLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_PROP_STR <=", value, "changePropStr");
            return (Criteria) this;
        }

        public Criteria andChangePropStrLike(String value) {
            addCriterion("CHANGE_PROP_STR like", value, "changePropStr");
            return (Criteria) this;
        }

        public Criteria andChangePropStrNotLike(String value) {
            addCriterion("CHANGE_PROP_STR not like", value, "changePropStr");
            return (Criteria) this;
        }

        public Criteria andChangePropStrIn(List<String> values) {
            addCriterion("CHANGE_PROP_STR in", values, "changePropStr");
            return (Criteria) this;
        }

        public Criteria andChangePropStrNotIn(List<String> values) {
            addCriterion("CHANGE_PROP_STR not in", values, "changePropStr");
            return (Criteria) this;
        }

        public Criteria andChangePropStrBetween(String value1, String value2) {
            addCriterion("CHANGE_PROP_STR between", value1, value2, "changePropStr");
            return (Criteria) this;
        }

        public Criteria andChangePropStrNotBetween(String value1, String value2) {
            addCriterion("CHANGE_PROP_STR not between", value1, value2, "changePropStr");
            return (Criteria) this;
        }

        public Criteria andIsbnIsNull() {
            addCriterion("ISBN is null");
            return (Criteria) this;
        }

        public Criteria andIsbnIsNotNull() {
            addCriterion("ISBN is not null");
            return (Criteria) this;
        }

        public Criteria andIsbnEqualTo(String value) {
            addCriterion("ISBN =", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotEqualTo(String value) {
            addCriterion("ISBN <>", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnGreaterThan(String value) {
            addCriterion("ISBN >", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnGreaterThanOrEqualTo(String value) {
            addCriterion("ISBN >=", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnLessThan(String value) {
            addCriterion("ISBN <", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnLessThanOrEqualTo(String value) {
            addCriterion("ISBN <=", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnLike(String value) {
            addCriterion("ISBN like", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotLike(String value) {
            addCriterion("ISBN not like", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnIn(List<String> values) {
            addCriterion("ISBN in", values, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotIn(List<String> values) {
            addCriterion("ISBN not in", values, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnBetween(String value1, String value2) {
            addCriterion("ISBN between", value1, value2, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotBetween(String value1, String value2) {
            addCriterion("ISBN not between", value1, value2, "isbn");
            return (Criteria) this;
        }

        public Criteria andExt1IsNull() {
            addCriterion("EXT1 is null");
            return (Criteria) this;
        }

        public Criteria andExt1IsNotNull() {
            addCriterion("EXT1 is not null");
            return (Criteria) this;
        }

        public Criteria andExt1EqualTo(String value) {
            addCriterion("EXT1 =", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotEqualTo(String value) {
            addCriterion("EXT1 <>", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThan(String value) {
            addCriterion("EXT1 >", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThanOrEqualTo(String value) {
            addCriterion("EXT1 >=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThan(String value) {
            addCriterion("EXT1 <", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThanOrEqualTo(String value) {
            addCriterion("EXT1 <=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Like(String value) {
            addCriterion("EXT1 like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotLike(String value) {
            addCriterion("EXT1 not like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1In(List<String> values) {
            addCriterion("EXT1 in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotIn(List<String> values) {
            addCriterion("EXT1 not in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Between(String value1, String value2) {
            addCriterion("EXT1 between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotBetween(String value1, String value2) {
            addCriterion("EXT1 not between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt2IsNull() {
            addCriterion("EXT2 is null");
            return (Criteria) this;
        }

        public Criteria andExt2IsNotNull() {
            addCriterion("EXT2 is not null");
            return (Criteria) this;
        }

        public Criteria andExt2EqualTo(String value) {
            addCriterion("EXT2 =", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotEqualTo(String value) {
            addCriterion("EXT2 <>", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThan(String value) {
            addCriterion("EXT2 >", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThanOrEqualTo(String value) {
            addCriterion("EXT2 >=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThan(String value) {
            addCriterion("EXT2 <", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThanOrEqualTo(String value) {
            addCriterion("EXT2 <=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Like(String value) {
            addCriterion("EXT2 like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotLike(String value) {
            addCriterion("EXT2 not like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2In(List<String> values) {
            addCriterion("EXT2 in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotIn(List<String> values) {
            addCriterion("EXT2 not in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Between(String value1, String value2) {
            addCriterion("EXT2 between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotBetween(String value1, String value2) {
            addCriterion("EXT2 not between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt3IsNull() {
            addCriterion("EXT3 is null");
            return (Criteria) this;
        }

        public Criteria andExt3IsNotNull() {
            addCriterion("EXT3 is not null");
            return (Criteria) this;
        }

        public Criteria andExt3EqualTo(String value) {
            addCriterion("EXT3 =", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotEqualTo(String value) {
            addCriterion("EXT3 <>", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3GreaterThan(String value) {
            addCriterion("EXT3 >", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3GreaterThanOrEqualTo(String value) {
            addCriterion("EXT3 >=", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3LessThan(String value) {
            addCriterion("EXT3 <", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3LessThanOrEqualTo(String value) {
            addCriterion("EXT3 <=", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3Like(String value) {
            addCriterion("EXT3 like", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotLike(String value) {
            addCriterion("EXT3 not like", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3In(List<String> values) {
            addCriterion("EXT3 in", values, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotIn(List<String> values) {
            addCriterion("EXT3 not in", values, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3Between(String value1, String value2) {
            addCriterion("EXT3 between", value1, value2, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotBetween(String value1, String value2) {
            addCriterion("EXT3 not between", value1, value2, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt4IsNull() {
            addCriterion("EXT4 is null");
            return (Criteria) this;
        }

        public Criteria andExt4IsNotNull() {
            addCriterion("EXT4 is not null");
            return (Criteria) this;
        }

        public Criteria andExt4EqualTo(String value) {
            addCriterion("EXT4 =", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotEqualTo(String value) {
            addCriterion("EXT4 <>", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4GreaterThan(String value) {
            addCriterion("EXT4 >", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4GreaterThanOrEqualTo(String value) {
            addCriterion("EXT4 >=", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4LessThan(String value) {
            addCriterion("EXT4 <", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4LessThanOrEqualTo(String value) {
            addCriterion("EXT4 <=", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4Like(String value) {
            addCriterion("EXT4 like", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotLike(String value) {
            addCriterion("EXT4 not like", value, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4In(List<String> values) {
            addCriterion("EXT4 in", values, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotIn(List<String> values) {
            addCriterion("EXT4 not in", values, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4Between(String value1, String value2) {
            addCriterion("EXT4 between", value1, value2, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt4NotBetween(String value1, String value2) {
            addCriterion("EXT4 not between", value1, value2, "ext4");
            return (Criteria) this;
        }

        public Criteria andExt5IsNull() {
            addCriterion("EXT5 is null");
            return (Criteria) this;
        }

        public Criteria andExt5IsNotNull() {
            addCriterion("EXT5 is not null");
            return (Criteria) this;
        }

        public Criteria andExt5EqualTo(String value) {
            addCriterion("EXT5 =", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5NotEqualTo(String value) {
            addCriterion("EXT5 <>", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5GreaterThan(String value) {
            addCriterion("EXT5 >", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5GreaterThanOrEqualTo(String value) {
            addCriterion("EXT5 >=", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5LessThan(String value) {
            addCriterion("EXT5 <", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5LessThanOrEqualTo(String value) {
            addCriterion("EXT5 <=", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5Like(String value) {
            addCriterion("EXT5 like", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5NotLike(String value) {
            addCriterion("EXT5 not like", value, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5In(List<String> values) {
            addCriterion("EXT5 in", values, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5NotIn(List<String> values) {
            addCriterion("EXT5 not in", values, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5Between(String value1, String value2) {
            addCriterion("EXT5 between", value1, value2, "ext5");
            return (Criteria) this;
        }

        public Criteria andExt5NotBetween(String value1, String value2) {
            addCriterion("EXT5 not between", value1, value2, "ext5");
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
