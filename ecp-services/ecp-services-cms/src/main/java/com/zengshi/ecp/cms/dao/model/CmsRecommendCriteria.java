package com.zengshi.ecp.cms.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CmsRecommendCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CmsRecommendCriteria() {
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

        public Criteria andRecommendTypeIsNull() {
            addCriterion("RECOMMEND_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeIsNotNull() {
            addCriterion("RECOMMEND_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeEqualTo(String value) {
            addCriterion("RECOMMEND_TYPE =", value, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeNotEqualTo(String value) {
            addCriterion("RECOMMEND_TYPE <>", value, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeGreaterThan(String value) {
            addCriterion("RECOMMEND_TYPE >", value, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RECOMMEND_TYPE >=", value, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeLessThan(String value) {
            addCriterion("RECOMMEND_TYPE <", value, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeLessThanOrEqualTo(String value) {
            addCriterion("RECOMMEND_TYPE <=", value, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeLike(String value) {
            addCriterion("RECOMMEND_TYPE like", value, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeNotLike(String value) {
            addCriterion("RECOMMEND_TYPE not like", value, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeIn(List<String> values) {
            addCriterion("RECOMMEND_TYPE in", values, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeNotIn(List<String> values) {
            addCriterion("RECOMMEND_TYPE not in", values, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeBetween(String value1, String value2) {
            addCriterion("RECOMMEND_TYPE between", value1, value2, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendTypeNotBetween(String value1, String value2) {
            addCriterion("RECOMMEND_TYPE not between", value1, value2, "recommendType");
            return (Criteria) this;
        }

        public Criteria andRecommendGdsIdIsNull() {
            addCriterion("RECOMMEND_GDS_ID is null");
            return (Criteria) this;
        }

        public Criteria andRecommendGdsIdIsNotNull() {
            addCriterion("RECOMMEND_GDS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendGdsIdEqualTo(Long value) {
            addCriterion("RECOMMEND_GDS_ID =", value, "recommendGdsId");
            return (Criteria) this;
        }

        public Criteria andRecommendGdsIdNotEqualTo(Long value) {
            addCriterion("RECOMMEND_GDS_ID <>", value, "recommendGdsId");
            return (Criteria) this;
        }

        public Criteria andRecommendGdsIdGreaterThan(Long value) {
            addCriterion("RECOMMEND_GDS_ID >", value, "recommendGdsId");
            return (Criteria) this;
        }

        public Criteria andRecommendGdsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("RECOMMEND_GDS_ID >=", value, "recommendGdsId");
            return (Criteria) this;
        }

        public Criteria andRecommendGdsIdLessThan(Long value) {
            addCriterion("RECOMMEND_GDS_ID <", value, "recommendGdsId");
            return (Criteria) this;
        }

        public Criteria andRecommendGdsIdLessThanOrEqualTo(Long value) {
            addCriterion("RECOMMEND_GDS_ID <=", value, "recommendGdsId");
            return (Criteria) this;
        }

        public Criteria andRecommendGdsIdIn(List<Long> values) {
            addCriterion("RECOMMEND_GDS_ID in", values, "recommendGdsId");
            return (Criteria) this;
        }

        public Criteria andRecommendGdsIdNotIn(List<Long> values) {
            addCriterion("RECOMMEND_GDS_ID not in", values, "recommendGdsId");
            return (Criteria) this;
        }

        public Criteria andRecommendGdsIdBetween(Long value1, Long value2) {
            addCriterion("RECOMMEND_GDS_ID between", value1, value2, "recommendGdsId");
            return (Criteria) this;
        }

        public Criteria andRecommendGdsIdNotBetween(Long value1, Long value2) {
            addCriterion("RECOMMEND_GDS_ID not between", value1, value2, "recommendGdsId");
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

        public Criteria andAuthorIntroductionIsNull() {
            addCriterion("AUTHOR_INTRODUCTION is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroductionIsNotNull() {
            addCriterion("AUTHOR_INTRODUCTION is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroductionEqualTo(String value) {
            addCriterion("AUTHOR_INTRODUCTION =", value, "authorIntroduction");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroductionNotEqualTo(String value) {
            addCriterion("AUTHOR_INTRODUCTION <>", value, "authorIntroduction");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroductionGreaterThan(String value) {
            addCriterion("AUTHOR_INTRODUCTION >", value, "authorIntroduction");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("AUTHOR_INTRODUCTION >=", value, "authorIntroduction");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroductionLessThan(String value) {
            addCriterion("AUTHOR_INTRODUCTION <", value, "authorIntroduction");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroductionLessThanOrEqualTo(String value) {
            addCriterion("AUTHOR_INTRODUCTION <=", value, "authorIntroduction");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroductionLike(String value) {
            addCriterion("AUTHOR_INTRODUCTION like", value, "authorIntroduction");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroductionNotLike(String value) {
            addCriterion("AUTHOR_INTRODUCTION not like", value, "authorIntroduction");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroductionIn(List<String> values) {
            addCriterion("AUTHOR_INTRODUCTION in", values, "authorIntroduction");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroductionNotIn(List<String> values) {
            addCriterion("AUTHOR_INTRODUCTION not in", values, "authorIntroduction");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroductionBetween(String value1, String value2) {
            addCriterion("AUTHOR_INTRODUCTION between", value1, value2, "authorIntroduction");
            return (Criteria) this;
        }

        public Criteria andAuthorIntroductionNotBetween(String value1, String value2) {
            addCriterion("AUTHOR_INTRODUCTION not between", value1, value2, "authorIntroduction");
            return (Criteria) this;
        }

        public Criteria andAuthorImageIsNull() {
            addCriterion("AUTHOR_IMAGE is null");
            return (Criteria) this;
        }

        public Criteria andAuthorImageIsNotNull() {
            addCriterion("AUTHOR_IMAGE is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorImageEqualTo(String value) {
            addCriterion("AUTHOR_IMAGE =", value, "authorImage");
            return (Criteria) this;
        }

        public Criteria andAuthorImageNotEqualTo(String value) {
            addCriterion("AUTHOR_IMAGE <>", value, "authorImage");
            return (Criteria) this;
        }

        public Criteria andAuthorImageGreaterThan(String value) {
            addCriterion("AUTHOR_IMAGE >", value, "authorImage");
            return (Criteria) this;
        }

        public Criteria andAuthorImageGreaterThanOrEqualTo(String value) {
            addCriterion("AUTHOR_IMAGE >=", value, "authorImage");
            return (Criteria) this;
        }

        public Criteria andAuthorImageLessThan(String value) {
            addCriterion("AUTHOR_IMAGE <", value, "authorImage");
            return (Criteria) this;
        }

        public Criteria andAuthorImageLessThanOrEqualTo(String value) {
            addCriterion("AUTHOR_IMAGE <=", value, "authorImage");
            return (Criteria) this;
        }

        public Criteria andAuthorImageLike(String value) {
            addCriterion("AUTHOR_IMAGE like", value, "authorImage");
            return (Criteria) this;
        }

        public Criteria andAuthorImageNotLike(String value) {
            addCriterion("AUTHOR_IMAGE not like", value, "authorImage");
            return (Criteria) this;
        }

        public Criteria andAuthorImageIn(List<String> values) {
            addCriterion("AUTHOR_IMAGE in", values, "authorImage");
            return (Criteria) this;
        }

        public Criteria andAuthorImageNotIn(List<String> values) {
            addCriterion("AUTHOR_IMAGE not in", values, "authorImage");
            return (Criteria) this;
        }

        public Criteria andAuthorImageBetween(String value1, String value2) {
            addCriterion("AUTHOR_IMAGE between", value1, value2, "authorImage");
            return (Criteria) this;
        }

        public Criteria andAuthorImageNotBetween(String value1, String value2) {
            addCriterion("AUTHOR_IMAGE not between", value1, value2, "authorImage");
            return (Criteria) this;
        }

        public Criteria andOtherProductionIsNull() {
            addCriterion("OTHER_PRODUCTION is null");
            return (Criteria) this;
        }

        public Criteria andOtherProductionIsNotNull() {
            addCriterion("OTHER_PRODUCTION is not null");
            return (Criteria) this;
        }

        public Criteria andOtherProductionEqualTo(String value) {
            addCriterion("OTHER_PRODUCTION =", value, "otherProduction");
            return (Criteria) this;
        }

        public Criteria andOtherProductionNotEqualTo(String value) {
            addCriterion("OTHER_PRODUCTION <>", value, "otherProduction");
            return (Criteria) this;
        }

        public Criteria andOtherProductionGreaterThan(String value) {
            addCriterion("OTHER_PRODUCTION >", value, "otherProduction");
            return (Criteria) this;
        }

        public Criteria andOtherProductionGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_PRODUCTION >=", value, "otherProduction");
            return (Criteria) this;
        }

        public Criteria andOtherProductionLessThan(String value) {
            addCriterion("OTHER_PRODUCTION <", value, "otherProduction");
            return (Criteria) this;
        }

        public Criteria andOtherProductionLessThanOrEqualTo(String value) {
            addCriterion("OTHER_PRODUCTION <=", value, "otherProduction");
            return (Criteria) this;
        }

        public Criteria andOtherProductionLike(String value) {
            addCriterion("OTHER_PRODUCTION like", value, "otherProduction");
            return (Criteria) this;
        }

        public Criteria andOtherProductionNotLike(String value) {
            addCriterion("OTHER_PRODUCTION not like", value, "otherProduction");
            return (Criteria) this;
        }

        public Criteria andOtherProductionIn(List<String> values) {
            addCriterion("OTHER_PRODUCTION in", values, "otherProduction");
            return (Criteria) this;
        }

        public Criteria andOtherProductionNotIn(List<String> values) {
            addCriterion("OTHER_PRODUCTION not in", values, "otherProduction");
            return (Criteria) this;
        }

        public Criteria andOtherProductionBetween(String value1, String value2) {
            addCriterion("OTHER_PRODUCTION between", value1, value2, "otherProduction");
            return (Criteria) this;
        }

        public Criteria andOtherProductionNotBetween(String value1, String value2) {
            addCriterion("OTHER_PRODUCTION not between", value1, value2, "otherProduction");
            return (Criteria) this;
        }

        public Criteria andRecommendProductionIsNull() {
            addCriterion("RECOMMEND_PRODUCTION is null");
            return (Criteria) this;
        }

        public Criteria andRecommendProductionIsNotNull() {
            addCriterion("RECOMMEND_PRODUCTION is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendProductionEqualTo(String value) {
            addCriterion("RECOMMEND_PRODUCTION =", value, "recommendProduction");
            return (Criteria) this;
        }

        public Criteria andRecommendProductionNotEqualTo(String value) {
            addCriterion("RECOMMEND_PRODUCTION <>", value, "recommendProduction");
            return (Criteria) this;
        }

        public Criteria andRecommendProductionGreaterThan(String value) {
            addCriterion("RECOMMEND_PRODUCTION >", value, "recommendProduction");
            return (Criteria) this;
        }

        public Criteria andRecommendProductionGreaterThanOrEqualTo(String value) {
            addCriterion("RECOMMEND_PRODUCTION >=", value, "recommendProduction");
            return (Criteria) this;
        }

        public Criteria andRecommendProductionLessThan(String value) {
            addCriterion("RECOMMEND_PRODUCTION <", value, "recommendProduction");
            return (Criteria) this;
        }

        public Criteria andRecommendProductionLessThanOrEqualTo(String value) {
            addCriterion("RECOMMEND_PRODUCTION <=", value, "recommendProduction");
            return (Criteria) this;
        }

        public Criteria andRecommendProductionLike(String value) {
            addCriterion("RECOMMEND_PRODUCTION like", value, "recommendProduction");
            return (Criteria) this;
        }

        public Criteria andRecommendProductionNotLike(String value) {
            addCriterion("RECOMMEND_PRODUCTION not like", value, "recommendProduction");
            return (Criteria) this;
        }

        public Criteria andRecommendProductionIn(List<String> values) {
            addCriterion("RECOMMEND_PRODUCTION in", values, "recommendProduction");
            return (Criteria) this;
        }

        public Criteria andRecommendProductionNotIn(List<String> values) {
            addCriterion("RECOMMEND_PRODUCTION not in", values, "recommendProduction");
            return (Criteria) this;
        }

        public Criteria andRecommendProductionBetween(String value1, String value2) {
            addCriterion("RECOMMEND_PRODUCTION between", value1, value2, "recommendProduction");
            return (Criteria) this;
        }

        public Criteria andRecommendProductionNotBetween(String value1, String value2) {
            addCriterion("RECOMMEND_PRODUCTION not between", value1, value2, "recommendProduction");
            return (Criteria) this;
        }

        public Criteria andOtherLikeIsNull() {
            addCriterion("OTHER_LIKE is null");
            return (Criteria) this;
        }

        public Criteria andOtherLikeIsNotNull() {
            addCriterion("OTHER_LIKE is not null");
            return (Criteria) this;
        }

        public Criteria andOtherLikeEqualTo(String value) {
            addCriterion("OTHER_LIKE =", value, "otherLike");
            return (Criteria) this;
        }

        public Criteria andOtherLikeNotEqualTo(String value) {
            addCriterion("OTHER_LIKE <>", value, "otherLike");
            return (Criteria) this;
        }

        public Criteria andOtherLikeGreaterThan(String value) {
            addCriterion("OTHER_LIKE >", value, "otherLike");
            return (Criteria) this;
        }

        public Criteria andOtherLikeGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_LIKE >=", value, "otherLike");
            return (Criteria) this;
        }

        public Criteria andOtherLikeLessThan(String value) {
            addCriterion("OTHER_LIKE <", value, "otherLike");
            return (Criteria) this;
        }

        public Criteria andOtherLikeLessThanOrEqualTo(String value) {
            addCriterion("OTHER_LIKE <=", value, "otherLike");
            return (Criteria) this;
        }

        public Criteria andOtherLikeLike(String value) {
            addCriterion("OTHER_LIKE like", value, "otherLike");
            return (Criteria) this;
        }

        public Criteria andOtherLikeNotLike(String value) {
            addCriterion("OTHER_LIKE not like", value, "otherLike");
            return (Criteria) this;
        }

        public Criteria andOtherLikeIn(List<String> values) {
            addCriterion("OTHER_LIKE in", values, "otherLike");
            return (Criteria) this;
        }

        public Criteria andOtherLikeNotIn(List<String> values) {
            addCriterion("OTHER_LIKE not in", values, "otherLike");
            return (Criteria) this;
        }

        public Criteria andOtherLikeBetween(String value1, String value2) {
            addCriterion("OTHER_LIKE between", value1, value2, "otherLike");
            return (Criteria) this;
        }

        public Criteria andOtherLikeNotBetween(String value1, String value2) {
            addCriterion("OTHER_LIKE not between", value1, value2, "otherLike");
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
