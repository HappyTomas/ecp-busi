package com.zengshi.ecp.cms.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CmsArticleCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CmsArticleCriteria() {
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

        public Criteria andArticleTitleIsNull() {
            addCriterion("ARTICLE_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIsNotNull() {
            addCriterion("ARTICLE_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleEqualTo(String value) {
            addCriterion("ARTICLE_TITLE =", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotEqualTo(String value) {
            addCriterion("ARTICLE_TITLE <>", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleGreaterThan(String value) {
            addCriterion("ARTICLE_TITLE >", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleGreaterThanOrEqualTo(String value) {
            addCriterion("ARTICLE_TITLE >=", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLessThan(String value) {
            addCriterion("ARTICLE_TITLE <", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLessThanOrEqualTo(String value) {
            addCriterion("ARTICLE_TITLE <=", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLike(String value) {
            addCriterion("ARTICLE_TITLE like", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotLike(String value) {
            addCriterion("ARTICLE_TITLE not like", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIn(List<String> values) {
            addCriterion("ARTICLE_TITLE in", values, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotIn(List<String> values) {
            addCriterion("ARTICLE_TITLE not in", values, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleBetween(String value1, String value2) {
            addCriterion("ARTICLE_TITLE between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotBetween(String value1, String value2) {
            addCriterion("ARTICLE_TITLE not between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleRemarkIsNull() {
            addCriterion("ARTICLE_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andArticleRemarkIsNotNull() {
            addCriterion("ARTICLE_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andArticleRemarkEqualTo(String value) {
            addCriterion("ARTICLE_REMARK =", value, "articleRemark");
            return (Criteria) this;
        }

        public Criteria andArticleRemarkNotEqualTo(String value) {
            addCriterion("ARTICLE_REMARK <>", value, "articleRemark");
            return (Criteria) this;
        }

        public Criteria andArticleRemarkGreaterThan(String value) {
            addCriterion("ARTICLE_REMARK >", value, "articleRemark");
            return (Criteria) this;
        }

        public Criteria andArticleRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("ARTICLE_REMARK >=", value, "articleRemark");
            return (Criteria) this;
        }

        public Criteria andArticleRemarkLessThan(String value) {
            addCriterion("ARTICLE_REMARK <", value, "articleRemark");
            return (Criteria) this;
        }

        public Criteria andArticleRemarkLessThanOrEqualTo(String value) {
            addCriterion("ARTICLE_REMARK <=", value, "articleRemark");
            return (Criteria) this;
        }

        public Criteria andArticleRemarkLike(String value) {
            addCriterion("ARTICLE_REMARK like", value, "articleRemark");
            return (Criteria) this;
        }

        public Criteria andArticleRemarkNotLike(String value) {
            addCriterion("ARTICLE_REMARK not like", value, "articleRemark");
            return (Criteria) this;
        }

        public Criteria andArticleRemarkIn(List<String> values) {
            addCriterion("ARTICLE_REMARK in", values, "articleRemark");
            return (Criteria) this;
        }

        public Criteria andArticleRemarkNotIn(List<String> values) {
            addCriterion("ARTICLE_REMARK not in", values, "articleRemark");
            return (Criteria) this;
        }

        public Criteria andArticleRemarkBetween(String value1, String value2) {
            addCriterion("ARTICLE_REMARK between", value1, value2, "articleRemark");
            return (Criteria) this;
        }

        public Criteria andArticleRemarkNotBetween(String value1, String value2) {
            addCriterion("ARTICLE_REMARK not between", value1, value2, "articleRemark");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("SOURCE =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("SOURCE <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("SOURCE >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCE >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("SOURCE <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("SOURCE <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("SOURCE like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("SOURCE not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("SOURCE in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("SOURCE not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("SOURCE between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("SOURCE not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andIstopIsNull() {
            addCriterion("ISTOP is null");
            return (Criteria) this;
        }

        public Criteria andIstopIsNotNull() {
            addCriterion("ISTOP is not null");
            return (Criteria) this;
        }

        public Criteria andIstopEqualTo(String value) {
            addCriterion("ISTOP =", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopNotEqualTo(String value) {
            addCriterion("ISTOP <>", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopGreaterThan(String value) {
            addCriterion("ISTOP >", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopGreaterThanOrEqualTo(String value) {
            addCriterion("ISTOP >=", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopLessThan(String value) {
            addCriterion("ISTOP <", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopLessThanOrEqualTo(String value) {
            addCriterion("ISTOP <=", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopLike(String value) {
            addCriterion("ISTOP like", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopNotLike(String value) {
            addCriterion("ISTOP not like", value, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopIn(List<String> values) {
            addCriterion("ISTOP in", values, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopNotIn(List<String> values) {
            addCriterion("ISTOP not in", values, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopBetween(String value1, String value2) {
            addCriterion("ISTOP between", value1, value2, "istop");
            return (Criteria) this;
        }

        public Criteria andIstopNotBetween(String value1, String value2) {
            addCriterion("ISTOP not between", value1, value2, "istop");
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

        public Criteria andPubTimeIsNull() {
            addCriterion("PUB_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPubTimeIsNotNull() {
            addCriterion("PUB_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPubTimeEqualTo(Timestamp value) {
            addCriterion("PUB_TIME =", value, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeNotEqualTo(Timestamp value) {
            addCriterion("PUB_TIME <>", value, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeGreaterThan(Timestamp value) {
            addCriterion("PUB_TIME >", value, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("PUB_TIME >=", value, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeLessThan(Timestamp value) {
            addCriterion("PUB_TIME <", value, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("PUB_TIME <=", value, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeIn(List<Timestamp> values) {
            addCriterion("PUB_TIME in", values, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeNotIn(List<Timestamp> values) {
            addCriterion("PUB_TIME not in", values, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PUB_TIME between", value1, value2, "pubTime");
            return (Criteria) this;
        }

        public Criteria andPubTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PUB_TIME not between", value1, value2, "pubTime");
            return (Criteria) this;
        }

        public Criteria andLostTimeIsNull() {
            addCriterion("LOST_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLostTimeIsNotNull() {
            addCriterion("LOST_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLostTimeEqualTo(Timestamp value) {
            addCriterion("LOST_TIME =", value, "lostTime");
            return (Criteria) this;
        }

        public Criteria andLostTimeNotEqualTo(Timestamp value) {
            addCriterion("LOST_TIME <>", value, "lostTime");
            return (Criteria) this;
        }

        public Criteria andLostTimeGreaterThan(Timestamp value) {
            addCriterion("LOST_TIME >", value, "lostTime");
            return (Criteria) this;
        }

        public Criteria andLostTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("LOST_TIME >=", value, "lostTime");
            return (Criteria) this;
        }

        public Criteria andLostTimeLessThan(Timestamp value) {
            addCriterion("LOST_TIME <", value, "lostTime");
            return (Criteria) this;
        }

        public Criteria andLostTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("LOST_TIME <=", value, "lostTime");
            return (Criteria) this;
        }

        public Criteria andLostTimeIn(List<Timestamp> values) {
            addCriterion("LOST_TIME in", values, "lostTime");
            return (Criteria) this;
        }

        public Criteria andLostTimeNotIn(List<Timestamp> values) {
            addCriterion("LOST_TIME not in", values, "lostTime");
            return (Criteria) this;
        }

        public Criteria andLostTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("LOST_TIME between", value1, value2, "lostTime");
            return (Criteria) this;
        }

        public Criteria andLostTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("LOST_TIME not between", value1, value2, "lostTime");
            return (Criteria) this;
        }

        public Criteria andThumbnailNameIsNull() {
            addCriterion("THUMBNAIL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andThumbnailNameIsNotNull() {
            addCriterion("THUMBNAIL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andThumbnailNameEqualTo(String value) {
            addCriterion("THUMBNAIL_NAME =", value, "thumbnailName");
            return (Criteria) this;
        }

        public Criteria andThumbnailNameNotEqualTo(String value) {
            addCriterion("THUMBNAIL_NAME <>", value, "thumbnailName");
            return (Criteria) this;
        }

        public Criteria andThumbnailNameGreaterThan(String value) {
            addCriterion("THUMBNAIL_NAME >", value, "thumbnailName");
            return (Criteria) this;
        }

        public Criteria andThumbnailNameGreaterThanOrEqualTo(String value) {
            addCriterion("THUMBNAIL_NAME >=", value, "thumbnailName");
            return (Criteria) this;
        }

        public Criteria andThumbnailNameLessThan(String value) {
            addCriterion("THUMBNAIL_NAME <", value, "thumbnailName");
            return (Criteria) this;
        }

        public Criteria andThumbnailNameLessThanOrEqualTo(String value) {
            addCriterion("THUMBNAIL_NAME <=", value, "thumbnailName");
            return (Criteria) this;
        }

        public Criteria andThumbnailNameLike(String value) {
            addCriterion("THUMBNAIL_NAME like", value, "thumbnailName");
            return (Criteria) this;
        }

        public Criteria andThumbnailNameNotLike(String value) {
            addCriterion("THUMBNAIL_NAME not like", value, "thumbnailName");
            return (Criteria) this;
        }

        public Criteria andThumbnailNameIn(List<String> values) {
            addCriterion("THUMBNAIL_NAME in", values, "thumbnailName");
            return (Criteria) this;
        }

        public Criteria andThumbnailNameNotIn(List<String> values) {
            addCriterion("THUMBNAIL_NAME not in", values, "thumbnailName");
            return (Criteria) this;
        }

        public Criteria andThumbnailNameBetween(String value1, String value2) {
            addCriterion("THUMBNAIL_NAME between", value1, value2, "thumbnailName");
            return (Criteria) this;
        }

        public Criteria andThumbnailNameNotBetween(String value1, String value2) {
            addCriterion("THUMBNAIL_NAME not between", value1, value2, "thumbnailName");
            return (Criteria) this;
        }

        public Criteria andThumbnailIsNull() {
            addCriterion("THUMBNAIL is null");
            return (Criteria) this;
        }

        public Criteria andThumbnailIsNotNull() {
            addCriterion("THUMBNAIL is not null");
            return (Criteria) this;
        }

        public Criteria andThumbnailEqualTo(String value) {
            addCriterion("THUMBNAIL =", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailNotEqualTo(String value) {
            addCriterion("THUMBNAIL <>", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailGreaterThan(String value) {
            addCriterion("THUMBNAIL >", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailGreaterThanOrEqualTo(String value) {
            addCriterion("THUMBNAIL >=", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailLessThan(String value) {
            addCriterion("THUMBNAIL <", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailLessThanOrEqualTo(String value) {
            addCriterion("THUMBNAIL <=", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailLike(String value) {
            addCriterion("THUMBNAIL like", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailNotLike(String value) {
            addCriterion("THUMBNAIL not like", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailIn(List<String> values) {
            addCriterion("THUMBNAIL in", values, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailNotIn(List<String> values) {
            addCriterion("THUMBNAIL not in", values, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailBetween(String value1, String value2) {
            addCriterion("THUMBNAIL between", value1, value2, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailNotBetween(String value1, String value2) {
            addCriterion("THUMBNAIL not between", value1, value2, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andStaticIdIsNull() {
            addCriterion("STATIC_ID is null");
            return (Criteria) this;
        }

        public Criteria andStaticIdIsNotNull() {
            addCriterion("STATIC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStaticIdEqualTo(String value) {
            addCriterion("STATIC_ID =", value, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdNotEqualTo(String value) {
            addCriterion("STATIC_ID <>", value, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdGreaterThan(String value) {
            addCriterion("STATIC_ID >", value, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdGreaterThanOrEqualTo(String value) {
            addCriterion("STATIC_ID >=", value, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdLessThan(String value) {
            addCriterion("STATIC_ID <", value, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdLessThanOrEqualTo(String value) {
            addCriterion("STATIC_ID <=", value, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdLike(String value) {
            addCriterion("STATIC_ID like", value, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdNotLike(String value) {
            addCriterion("STATIC_ID not like", value, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdIn(List<String> values) {
            addCriterion("STATIC_ID in", values, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdNotIn(List<String> values) {
            addCriterion("STATIC_ID not in", values, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdBetween(String value1, String value2) {
            addCriterion("STATIC_ID between", value1, value2, "staticId");
            return (Criteria) this;
        }

        public Criteria andStaticIdNotBetween(String value1, String value2) {
            addCriterion("STATIC_ID not between", value1, value2, "staticId");
            return (Criteria) this;
        }

        public Criteria andVfsNameIsNull() {
            addCriterion("VFS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andVfsNameIsNotNull() {
            addCriterion("VFS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andVfsNameEqualTo(String value) {
            addCriterion("VFS_NAME =", value, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameNotEqualTo(String value) {
            addCriterion("VFS_NAME <>", value, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameGreaterThan(String value) {
            addCriterion("VFS_NAME >", value, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameGreaterThanOrEqualTo(String value) {
            addCriterion("VFS_NAME >=", value, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameLessThan(String value) {
            addCriterion("VFS_NAME <", value, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameLessThanOrEqualTo(String value) {
            addCriterion("VFS_NAME <=", value, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameLike(String value) {
            addCriterion("VFS_NAME like", value, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameNotLike(String value) {
            addCriterion("VFS_NAME not like", value, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameIn(List<String> values) {
            addCriterion("VFS_NAME in", values, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameNotIn(List<String> values) {
            addCriterion("VFS_NAME not in", values, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameBetween(String value1, String value2) {
            addCriterion("VFS_NAME between", value1, value2, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsNameNotBetween(String value1, String value2) {
            addCriterion("VFS_NAME not between", value1, value2, "vfsName");
            return (Criteria) this;
        }

        public Criteria andVfsIdIsNull() {
            addCriterion("VFS_ID is null");
            return (Criteria) this;
        }

        public Criteria andVfsIdIsNotNull() {
            addCriterion("VFS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andVfsIdEqualTo(String value) {
            addCriterion("VFS_ID =", value, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdNotEqualTo(String value) {
            addCriterion("VFS_ID <>", value, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdGreaterThan(String value) {
            addCriterion("VFS_ID >", value, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdGreaterThanOrEqualTo(String value) {
            addCriterion("VFS_ID >=", value, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdLessThan(String value) {
            addCriterion("VFS_ID <", value, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdLessThanOrEqualTo(String value) {
            addCriterion("VFS_ID <=", value, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdLike(String value) {
            addCriterion("VFS_ID like", value, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdNotLike(String value) {
            addCriterion("VFS_ID not like", value, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdIn(List<String> values) {
            addCriterion("VFS_ID in", values, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdNotIn(List<String> values) {
            addCriterion("VFS_ID not in", values, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdBetween(String value1, String value2) {
            addCriterion("VFS_ID between", value1, value2, "vfsId");
            return (Criteria) this;
        }

        public Criteria andVfsIdNotBetween(String value1, String value2) {
            addCriterion("VFS_ID not between", value1, value2, "vfsId");
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

        public Criteria andChannelIdIsNull() {
            addCriterion("CHANNEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNotNull() {
            addCriterion("CHANNEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIdEqualTo(Long value) {
            addCriterion("CHANNEL_ID =", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotEqualTo(Long value) {
            addCriterion("CHANNEL_ID <>", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThan(Long value) {
            addCriterion("CHANNEL_ID >", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CHANNEL_ID >=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThan(Long value) {
            addCriterion("CHANNEL_ID <", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThanOrEqualTo(Long value) {
            addCriterion("CHANNEL_ID <=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIn(List<Long> values) {
            addCriterion("CHANNEL_ID in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotIn(List<Long> values) {
            addCriterion("CHANNEL_ID not in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdBetween(Long value1, Long value2) {
            addCriterion("CHANNEL_ID between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotBetween(Long value1, Long value2) {
            addCriterion("CHANNEL_ID not between", value1, value2, "channelId");
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

        public Criteria andHomepageIsShowIsNull() {
            addCriterion("HOMEPAGE_IS_SHOW is null");
            return (Criteria) this;
        }

        public Criteria andHomepageIsShowIsNotNull() {
            addCriterion("HOMEPAGE_IS_SHOW is not null");
            return (Criteria) this;
        }

        public Criteria andHomepageIsShowEqualTo(String value) {
            addCriterion("HOMEPAGE_IS_SHOW =", value, "homepageIsShow");
            return (Criteria) this;
        }

        public Criteria andHomepageIsShowNotEqualTo(String value) {
            addCriterion("HOMEPAGE_IS_SHOW <>", value, "homepageIsShow");
            return (Criteria) this;
        }

        public Criteria andHomepageIsShowGreaterThan(String value) {
            addCriterion("HOMEPAGE_IS_SHOW >", value, "homepageIsShow");
            return (Criteria) this;
        }

        public Criteria andHomepageIsShowGreaterThanOrEqualTo(String value) {
            addCriterion("HOMEPAGE_IS_SHOW >=", value, "homepageIsShow");
            return (Criteria) this;
        }

        public Criteria andHomepageIsShowLessThan(String value) {
            addCriterion("HOMEPAGE_IS_SHOW <", value, "homepageIsShow");
            return (Criteria) this;
        }

        public Criteria andHomepageIsShowLessThanOrEqualTo(String value) {
            addCriterion("HOMEPAGE_IS_SHOW <=", value, "homepageIsShow");
            return (Criteria) this;
        }

        public Criteria andHomepageIsShowLike(String value) {
            addCriterion("HOMEPAGE_IS_SHOW like", value, "homepageIsShow");
            return (Criteria) this;
        }

        public Criteria andHomepageIsShowNotLike(String value) {
            addCriterion("HOMEPAGE_IS_SHOW not like", value, "homepageIsShow");
            return (Criteria) this;
        }

        public Criteria andHomepageIsShowIn(List<String> values) {
            addCriterion("HOMEPAGE_IS_SHOW in", values, "homepageIsShow");
            return (Criteria) this;
        }

        public Criteria andHomepageIsShowNotIn(List<String> values) {
            addCriterion("HOMEPAGE_IS_SHOW not in", values, "homepageIsShow");
            return (Criteria) this;
        }

        public Criteria andHomepageIsShowBetween(String value1, String value2) {
            addCriterion("HOMEPAGE_IS_SHOW between", value1, value2, "homepageIsShow");
            return (Criteria) this;
        }

        public Criteria andHomepageIsShowNotBetween(String value1, String value2) {
            addCriterion("HOMEPAGE_IS_SHOW not between", value1, value2, "homepageIsShow");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIsNull() {
            addCriterion("AUTHOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIsNotNull() {
            addCriterion("AUTHOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorNameEqualTo(String value) {
            addCriterion("AUTHOR_NAME =", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotEqualTo(String value) {
            addCriterion("AUTHOR_NAME <>", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameGreaterThan(String value) {
            addCriterion("AUTHOR_NAME >", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUTHOR_NAME >=", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLessThan(String value) {
            addCriterion("AUTHOR_NAME <", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLessThanOrEqualTo(String value) {
            addCriterion("AUTHOR_NAME <=", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLike(String value) {
            addCriterion("AUTHOR_NAME like", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotLike(String value) {
            addCriterion("AUTHOR_NAME not like", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIn(List<String> values) {
            addCriterion("AUTHOR_NAME in", values, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotIn(List<String> values) {
            addCriterion("AUTHOR_NAME not in", values, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameBetween(String value1, String value2) {
            addCriterion("AUTHOR_NAME between", value1, value2, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotBetween(String value1, String value2) {
            addCriterion("AUTHOR_NAME not between", value1, value2, "authorName");
            return (Criteria) this;
        }

        public Criteria andQrCodeIsNull() {
            addCriterion("QR_CODE is null");
            return (Criteria) this;
        }

        public Criteria andQrCodeIsNotNull() {
            addCriterion("QR_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andQrCodeEqualTo(String value) {
            addCriterion("QR_CODE =", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotEqualTo(String value) {
            addCriterion("QR_CODE <>", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeGreaterThan(String value) {
            addCriterion("QR_CODE >", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeGreaterThanOrEqualTo(String value) {
            addCriterion("QR_CODE >=", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLessThan(String value) {
            addCriterion("QR_CODE <", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLessThanOrEqualTo(String value) {
            addCriterion("QR_CODE <=", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLike(String value) {
            addCriterion("QR_CODE like", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotLike(String value) {
            addCriterion("QR_CODE not like", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeIn(List<String> values) {
            addCriterion("QR_CODE in", values, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotIn(List<String> values) {
            addCriterion("QR_CODE not in", values, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeBetween(String value1, String value2) {
            addCriterion("QR_CODE between", value1, value2, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotBetween(String value1, String value2) {
            addCriterion("QR_CODE not between", value1, value2, "qrCode");
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
