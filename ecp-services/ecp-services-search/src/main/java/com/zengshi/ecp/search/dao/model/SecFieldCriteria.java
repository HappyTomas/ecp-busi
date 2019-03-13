package com.zengshi.ecp.search.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SecFieldCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public SecFieldCriteria() {
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

        public Criteria andFieldBeanFieldNameIsNull() {
            addCriterion("FIELD_BEAN_FIELD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFieldBeanFieldNameIsNotNull() {
            addCriterion("FIELD_BEAN_FIELD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFieldBeanFieldNameEqualTo(String value) {
            addCriterion("FIELD_BEAN_FIELD_NAME =", value, "fieldBeanFieldName");
            return (Criteria) this;
        }

        public Criteria andFieldBeanFieldNameNotEqualTo(String value) {
            addCriterion("FIELD_BEAN_FIELD_NAME <>", value, "fieldBeanFieldName");
            return (Criteria) this;
        }

        public Criteria andFieldBeanFieldNameGreaterThan(String value) {
            addCriterion("FIELD_BEAN_FIELD_NAME >", value, "fieldBeanFieldName");
            return (Criteria) this;
        }

        public Criteria andFieldBeanFieldNameGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_BEAN_FIELD_NAME >=", value, "fieldBeanFieldName");
            return (Criteria) this;
        }

        public Criteria andFieldBeanFieldNameLessThan(String value) {
            addCriterion("FIELD_BEAN_FIELD_NAME <", value, "fieldBeanFieldName");
            return (Criteria) this;
        }

        public Criteria andFieldBeanFieldNameLessThanOrEqualTo(String value) {
            addCriterion("FIELD_BEAN_FIELD_NAME <=", value, "fieldBeanFieldName");
            return (Criteria) this;
        }

        public Criteria andFieldBeanFieldNameLike(String value) {
            addCriterion("FIELD_BEAN_FIELD_NAME like", value, "fieldBeanFieldName");
            return (Criteria) this;
        }

        public Criteria andFieldBeanFieldNameNotLike(String value) {
            addCriterion("FIELD_BEAN_FIELD_NAME not like", value, "fieldBeanFieldName");
            return (Criteria) this;
        }

        public Criteria andFieldBeanFieldNameIn(List<String> values) {
            addCriterion("FIELD_BEAN_FIELD_NAME in", values, "fieldBeanFieldName");
            return (Criteria) this;
        }

        public Criteria andFieldBeanFieldNameNotIn(List<String> values) {
            addCriterion("FIELD_BEAN_FIELD_NAME not in", values, "fieldBeanFieldName");
            return (Criteria) this;
        }

        public Criteria andFieldBeanFieldNameBetween(String value1, String value2) {
            addCriterion("FIELD_BEAN_FIELD_NAME between", value1, value2, "fieldBeanFieldName");
            return (Criteria) this;
        }

        public Criteria andFieldBeanFieldNameNotBetween(String value1, String value2) {
            addCriterion("FIELD_BEAN_FIELD_NAME not between", value1, value2, "fieldBeanFieldName");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNameIsNull() {
            addCriterion("FIELD_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNameIsNotNull() {
            addCriterion("FIELD_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNameEqualTo(String value) {
            addCriterion("FIELD_TYPE_NAME =", value, "fieldTypeName");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNameNotEqualTo(String value) {
            addCriterion("FIELD_TYPE_NAME <>", value, "fieldTypeName");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNameGreaterThan(String value) {
            addCriterion("FIELD_TYPE_NAME >", value, "fieldTypeName");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_TYPE_NAME >=", value, "fieldTypeName");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNameLessThan(String value) {
            addCriterion("FIELD_TYPE_NAME <", value, "fieldTypeName");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNameLessThanOrEqualTo(String value) {
            addCriterion("FIELD_TYPE_NAME <=", value, "fieldTypeName");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNameLike(String value) {
            addCriterion("FIELD_TYPE_NAME like", value, "fieldTypeName");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNameNotLike(String value) {
            addCriterion("FIELD_TYPE_NAME not like", value, "fieldTypeName");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNameIn(List<String> values) {
            addCriterion("FIELD_TYPE_NAME in", values, "fieldTypeName");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNameNotIn(List<String> values) {
            addCriterion("FIELD_TYPE_NAME not in", values, "fieldTypeName");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNameBetween(String value1, String value2) {
            addCriterion("FIELD_TYPE_NAME between", value1, value2, "fieldTypeName");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNameNotBetween(String value1, String value2) {
            addCriterion("FIELD_TYPE_NAME not between", value1, value2, "fieldTypeName");
            return (Criteria) this;
        }

        public Criteria andFieldIndexNameIsNull() {
            addCriterion("FIELD_INDEX_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFieldIndexNameIsNotNull() {
            addCriterion("FIELD_INDEX_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFieldIndexNameEqualTo(String value) {
            addCriterion("FIELD_INDEX_NAME =", value, "fieldIndexName");
            return (Criteria) this;
        }

        public Criteria andFieldIndexNameNotEqualTo(String value) {
            addCriterion("FIELD_INDEX_NAME <>", value, "fieldIndexName");
            return (Criteria) this;
        }

        public Criteria andFieldIndexNameGreaterThan(String value) {
            addCriterion("FIELD_INDEX_NAME >", value, "fieldIndexName");
            return (Criteria) this;
        }

        public Criteria andFieldIndexNameGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_INDEX_NAME >=", value, "fieldIndexName");
            return (Criteria) this;
        }

        public Criteria andFieldIndexNameLessThan(String value) {
            addCriterion("FIELD_INDEX_NAME <", value, "fieldIndexName");
            return (Criteria) this;
        }

        public Criteria andFieldIndexNameLessThanOrEqualTo(String value) {
            addCriterion("FIELD_INDEX_NAME <=", value, "fieldIndexName");
            return (Criteria) this;
        }

        public Criteria andFieldIndexNameLike(String value) {
            addCriterion("FIELD_INDEX_NAME like", value, "fieldIndexName");
            return (Criteria) this;
        }

        public Criteria andFieldIndexNameNotLike(String value) {
            addCriterion("FIELD_INDEX_NAME not like", value, "fieldIndexName");
            return (Criteria) this;
        }

        public Criteria andFieldIndexNameIn(List<String> values) {
            addCriterion("FIELD_INDEX_NAME in", values, "fieldIndexName");
            return (Criteria) this;
        }

        public Criteria andFieldIndexNameNotIn(List<String> values) {
            addCriterion("FIELD_INDEX_NAME not in", values, "fieldIndexName");
            return (Criteria) this;
        }

        public Criteria andFieldIndexNameBetween(String value1, String value2) {
            addCriterion("FIELD_INDEX_NAME between", value1, value2, "fieldIndexName");
            return (Criteria) this;
        }

        public Criteria andFieldIndexNameNotBetween(String value1, String value2) {
            addCriterion("FIELD_INDEX_NAME not between", value1, value2, "fieldIndexName");
            return (Criteria) this;
        }

        public Criteria andFieldNamecnIsNull() {
            addCriterion("FIELD_NAMECN is null");
            return (Criteria) this;
        }

        public Criteria andFieldNamecnIsNotNull() {
            addCriterion("FIELD_NAMECN is not null");
            return (Criteria) this;
        }

        public Criteria andFieldNamecnEqualTo(String value) {
            addCriterion("FIELD_NAMECN =", value, "fieldNamecn");
            return (Criteria) this;
        }

        public Criteria andFieldNamecnNotEqualTo(String value) {
            addCriterion("FIELD_NAMECN <>", value, "fieldNamecn");
            return (Criteria) this;
        }

        public Criteria andFieldNamecnGreaterThan(String value) {
            addCriterion("FIELD_NAMECN >", value, "fieldNamecn");
            return (Criteria) this;
        }

        public Criteria andFieldNamecnGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_NAMECN >=", value, "fieldNamecn");
            return (Criteria) this;
        }

        public Criteria andFieldNamecnLessThan(String value) {
            addCriterion("FIELD_NAMECN <", value, "fieldNamecn");
            return (Criteria) this;
        }

        public Criteria andFieldNamecnLessThanOrEqualTo(String value) {
            addCriterion("FIELD_NAMECN <=", value, "fieldNamecn");
            return (Criteria) this;
        }

        public Criteria andFieldNamecnLike(String value) {
            addCriterion("FIELD_NAMECN like", value, "fieldNamecn");
            return (Criteria) this;
        }

        public Criteria andFieldNamecnNotLike(String value) {
            addCriterion("FIELD_NAMECN not like", value, "fieldNamecn");
            return (Criteria) this;
        }

        public Criteria andFieldNamecnIn(List<String> values) {
            addCriterion("FIELD_NAMECN in", values, "fieldNamecn");
            return (Criteria) this;
        }

        public Criteria andFieldNamecnNotIn(List<String> values) {
            addCriterion("FIELD_NAMECN not in", values, "fieldNamecn");
            return (Criteria) this;
        }

        public Criteria andFieldNamecnBetween(String value1, String value2) {
            addCriterion("FIELD_NAMECN between", value1, value2, "fieldNamecn");
            return (Criteria) this;
        }

        public Criteria andFieldNamecnNotBetween(String value1, String value2) {
            addCriterion("FIELD_NAMECN not between", value1, value2, "fieldNamecn");
            return (Criteria) this;
        }

        public Criteria andFieldCommentIsNull() {
            addCriterion("FIELD_COMMENT is null");
            return (Criteria) this;
        }

        public Criteria andFieldCommentIsNotNull() {
            addCriterion("FIELD_COMMENT is not null");
            return (Criteria) this;
        }

        public Criteria andFieldCommentEqualTo(String value) {
            addCriterion("FIELD_COMMENT =", value, "fieldComment");
            return (Criteria) this;
        }

        public Criteria andFieldCommentNotEqualTo(String value) {
            addCriterion("FIELD_COMMENT <>", value, "fieldComment");
            return (Criteria) this;
        }

        public Criteria andFieldCommentGreaterThan(String value) {
            addCriterion("FIELD_COMMENT >", value, "fieldComment");
            return (Criteria) this;
        }

        public Criteria andFieldCommentGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_COMMENT >=", value, "fieldComment");
            return (Criteria) this;
        }

        public Criteria andFieldCommentLessThan(String value) {
            addCriterion("FIELD_COMMENT <", value, "fieldComment");
            return (Criteria) this;
        }

        public Criteria andFieldCommentLessThanOrEqualTo(String value) {
            addCriterion("FIELD_COMMENT <=", value, "fieldComment");
            return (Criteria) this;
        }

        public Criteria andFieldCommentLike(String value) {
            addCriterion("FIELD_COMMENT like", value, "fieldComment");
            return (Criteria) this;
        }

        public Criteria andFieldCommentNotLike(String value) {
            addCriterion("FIELD_COMMENT not like", value, "fieldComment");
            return (Criteria) this;
        }

        public Criteria andFieldCommentIn(List<String> values) {
            addCriterion("FIELD_COMMENT in", values, "fieldComment");
            return (Criteria) this;
        }

        public Criteria andFieldCommentNotIn(List<String> values) {
            addCriterion("FIELD_COMMENT not in", values, "fieldComment");
            return (Criteria) this;
        }

        public Criteria andFieldCommentBetween(String value1, String value2) {
            addCriterion("FIELD_COMMENT between", value1, value2, "fieldComment");
            return (Criteria) this;
        }

        public Criteria andFieldCommentNotBetween(String value1, String value2) {
            addCriterion("FIELD_COMMENT not between", value1, value2, "fieldComment");
            return (Criteria) this;
        }

        public Criteria andFieldIfBelongtoDfIsNull() {
            addCriterion("FIELD_IF_BELONGTO_DF is null");
            return (Criteria) this;
        }

        public Criteria andFieldIfBelongtoDfIsNotNull() {
            addCriterion("FIELD_IF_BELONGTO_DF is not null");
            return (Criteria) this;
        }

        public Criteria andFieldIfBelongtoDfEqualTo(String value) {
            addCriterion("FIELD_IF_BELONGTO_DF =", value, "fieldIfBelongtoDf");
            return (Criteria) this;
        }

        public Criteria andFieldIfBelongtoDfNotEqualTo(String value) {
            addCriterion("FIELD_IF_BELONGTO_DF <>", value, "fieldIfBelongtoDf");
            return (Criteria) this;
        }

        public Criteria andFieldIfBelongtoDfGreaterThan(String value) {
            addCriterion("FIELD_IF_BELONGTO_DF >", value, "fieldIfBelongtoDf");
            return (Criteria) this;
        }

        public Criteria andFieldIfBelongtoDfGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_IF_BELONGTO_DF >=", value, "fieldIfBelongtoDf");
            return (Criteria) this;
        }

        public Criteria andFieldIfBelongtoDfLessThan(String value) {
            addCriterion("FIELD_IF_BELONGTO_DF <", value, "fieldIfBelongtoDf");
            return (Criteria) this;
        }

        public Criteria andFieldIfBelongtoDfLessThanOrEqualTo(String value) {
            addCriterion("FIELD_IF_BELONGTO_DF <=", value, "fieldIfBelongtoDf");
            return (Criteria) this;
        }

        public Criteria andFieldIfBelongtoDfLike(String value) {
            addCriterion("FIELD_IF_BELONGTO_DF like", value, "fieldIfBelongtoDf");
            return (Criteria) this;
        }

        public Criteria andFieldIfBelongtoDfNotLike(String value) {
            addCriterion("FIELD_IF_BELONGTO_DF not like", value, "fieldIfBelongtoDf");
            return (Criteria) this;
        }

        public Criteria andFieldIfBelongtoDfIn(List<String> values) {
            addCriterion("FIELD_IF_BELONGTO_DF in", values, "fieldIfBelongtoDf");
            return (Criteria) this;
        }

        public Criteria andFieldIfBelongtoDfNotIn(List<String> values) {
            addCriterion("FIELD_IF_BELONGTO_DF not in", values, "fieldIfBelongtoDf");
            return (Criteria) this;
        }

        public Criteria andFieldIfBelongtoDfBetween(String value1, String value2) {
            addCriterion("FIELD_IF_BELONGTO_DF between", value1, value2, "fieldIfBelongtoDf");
            return (Criteria) this;
        }

        public Criteria andFieldIfBelongtoDfNotBetween(String value1, String value2) {
            addCriterion("FIELD_IF_BELONGTO_DF not between", value1, value2, "fieldIfBelongtoDf");
            return (Criteria) this;
        }

        public Criteria andFieldProcessorNameIsNull() {
            addCriterion("FIELD_PROCESSOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFieldProcessorNameIsNotNull() {
            addCriterion("FIELD_PROCESSOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFieldProcessorNameEqualTo(String value) {
            addCriterion("FIELD_PROCESSOR_NAME =", value, "fieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andFieldProcessorNameNotEqualTo(String value) {
            addCriterion("FIELD_PROCESSOR_NAME <>", value, "fieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andFieldProcessorNameGreaterThan(String value) {
            addCriterion("FIELD_PROCESSOR_NAME >", value, "fieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andFieldProcessorNameGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_PROCESSOR_NAME >=", value, "fieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andFieldProcessorNameLessThan(String value) {
            addCriterion("FIELD_PROCESSOR_NAME <", value, "fieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andFieldProcessorNameLessThanOrEqualTo(String value) {
            addCriterion("FIELD_PROCESSOR_NAME <=", value, "fieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andFieldProcessorNameLike(String value) {
            addCriterion("FIELD_PROCESSOR_NAME like", value, "fieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andFieldProcessorNameNotLike(String value) {
            addCriterion("FIELD_PROCESSOR_NAME not like", value, "fieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andFieldProcessorNameIn(List<String> values) {
            addCriterion("FIELD_PROCESSOR_NAME in", values, "fieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andFieldProcessorNameNotIn(List<String> values) {
            addCriterion("FIELD_PROCESSOR_NAME not in", values, "fieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andFieldProcessorNameBetween(String value1, String value2) {
            addCriterion("FIELD_PROCESSOR_NAME between", value1, value2, "fieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andFieldProcessorNameNotBetween(String value1, String value2) {
            addCriterion("FIELD_PROCESSOR_NAME not between", value1, value2, "fieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andFieldIfMutlivalueIsNull() {
            addCriterion("FIELD_IF_MUTLIVALUE is null");
            return (Criteria) this;
        }

        public Criteria andFieldIfMutlivalueIsNotNull() {
            addCriterion("FIELD_IF_MUTLIVALUE is not null");
            return (Criteria) this;
        }

        public Criteria andFieldIfMutlivalueEqualTo(String value) {
            addCriterion("FIELD_IF_MUTLIVALUE =", value, "fieldIfMutlivalue");
            return (Criteria) this;
        }

        public Criteria andFieldIfMutlivalueNotEqualTo(String value) {
            addCriterion("FIELD_IF_MUTLIVALUE <>", value, "fieldIfMutlivalue");
            return (Criteria) this;
        }

        public Criteria andFieldIfMutlivalueGreaterThan(String value) {
            addCriterion("FIELD_IF_MUTLIVALUE >", value, "fieldIfMutlivalue");
            return (Criteria) this;
        }

        public Criteria andFieldIfMutlivalueGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_IF_MUTLIVALUE >=", value, "fieldIfMutlivalue");
            return (Criteria) this;
        }

        public Criteria andFieldIfMutlivalueLessThan(String value) {
            addCriterion("FIELD_IF_MUTLIVALUE <", value, "fieldIfMutlivalue");
            return (Criteria) this;
        }

        public Criteria andFieldIfMutlivalueLessThanOrEqualTo(String value) {
            addCriterion("FIELD_IF_MUTLIVALUE <=", value, "fieldIfMutlivalue");
            return (Criteria) this;
        }

        public Criteria andFieldIfMutlivalueLike(String value) {
            addCriterion("FIELD_IF_MUTLIVALUE like", value, "fieldIfMutlivalue");
            return (Criteria) this;
        }

        public Criteria andFieldIfMutlivalueNotLike(String value) {
            addCriterion("FIELD_IF_MUTLIVALUE not like", value, "fieldIfMutlivalue");
            return (Criteria) this;
        }

        public Criteria andFieldIfMutlivalueIn(List<String> values) {
            addCriterion("FIELD_IF_MUTLIVALUE in", values, "fieldIfMutlivalue");
            return (Criteria) this;
        }

        public Criteria andFieldIfMutlivalueNotIn(List<String> values) {
            addCriterion("FIELD_IF_MUTLIVALUE not in", values, "fieldIfMutlivalue");
            return (Criteria) this;
        }

        public Criteria andFieldIfMutlivalueBetween(String value1, String value2) {
            addCriterion("FIELD_IF_MUTLIVALUE between", value1, value2, "fieldIfMutlivalue");
            return (Criteria) this;
        }

        public Criteria andFieldIfMutlivalueNotBetween(String value1, String value2) {
            addCriterion("FIELD_IF_MUTLIVALUE not between", value1, value2, "fieldIfMutlivalue");
            return (Criteria) this;
        }

        public Criteria andFieldIfFacetIsNull() {
            addCriterion("FIELD_IF_FACET is null");
            return (Criteria) this;
        }

        public Criteria andFieldIfFacetIsNotNull() {
            addCriterion("FIELD_IF_FACET is not null");
            return (Criteria) this;
        }

        public Criteria andFieldIfFacetEqualTo(String value) {
            addCriterion("FIELD_IF_FACET =", value, "fieldIfFacet");
            return (Criteria) this;
        }

        public Criteria andFieldIfFacetNotEqualTo(String value) {
            addCriterion("FIELD_IF_FACET <>", value, "fieldIfFacet");
            return (Criteria) this;
        }

        public Criteria andFieldIfFacetGreaterThan(String value) {
            addCriterion("FIELD_IF_FACET >", value, "fieldIfFacet");
            return (Criteria) this;
        }

        public Criteria andFieldIfFacetGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_IF_FACET >=", value, "fieldIfFacet");
            return (Criteria) this;
        }

        public Criteria andFieldIfFacetLessThan(String value) {
            addCriterion("FIELD_IF_FACET <", value, "fieldIfFacet");
            return (Criteria) this;
        }

        public Criteria andFieldIfFacetLessThanOrEqualTo(String value) {
            addCriterion("FIELD_IF_FACET <=", value, "fieldIfFacet");
            return (Criteria) this;
        }

        public Criteria andFieldIfFacetLike(String value) {
            addCriterion("FIELD_IF_FACET like", value, "fieldIfFacet");
            return (Criteria) this;
        }

        public Criteria andFieldIfFacetNotLike(String value) {
            addCriterion("FIELD_IF_FACET not like", value, "fieldIfFacet");
            return (Criteria) this;
        }

        public Criteria andFieldIfFacetIn(List<String> values) {
            addCriterion("FIELD_IF_FACET in", values, "fieldIfFacet");
            return (Criteria) this;
        }

        public Criteria andFieldIfFacetNotIn(List<String> values) {
            addCriterion("FIELD_IF_FACET not in", values, "fieldIfFacet");
            return (Criteria) this;
        }

        public Criteria andFieldIfFacetBetween(String value1, String value2) {
            addCriterion("FIELD_IF_FACET between", value1, value2, "fieldIfFacet");
            return (Criteria) this;
        }

        public Criteria andFieldIfFacetNotBetween(String value1, String value2) {
            addCriterion("FIELD_IF_FACET not between", value1, value2, "fieldIfFacet");
            return (Criteria) this;
        }

        public Criteria andFieldIfSpellcheckIsNull() {
            addCriterion("FIELD_IF_SPELLCHECK is null");
            return (Criteria) this;
        }

        public Criteria andFieldIfSpellcheckIsNotNull() {
            addCriterion("FIELD_IF_SPELLCHECK is not null");
            return (Criteria) this;
        }

        public Criteria andFieldIfSpellcheckEqualTo(String value) {
            addCriterion("FIELD_IF_SPELLCHECK =", value, "fieldIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andFieldIfSpellcheckNotEqualTo(String value) {
            addCriterion("FIELD_IF_SPELLCHECK <>", value, "fieldIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andFieldIfSpellcheckGreaterThan(String value) {
            addCriterion("FIELD_IF_SPELLCHECK >", value, "fieldIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andFieldIfSpellcheckGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_IF_SPELLCHECK >=", value, "fieldIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andFieldIfSpellcheckLessThan(String value) {
            addCriterion("FIELD_IF_SPELLCHECK <", value, "fieldIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andFieldIfSpellcheckLessThanOrEqualTo(String value) {
            addCriterion("FIELD_IF_SPELLCHECK <=", value, "fieldIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andFieldIfSpellcheckLike(String value) {
            addCriterion("FIELD_IF_SPELLCHECK like", value, "fieldIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andFieldIfSpellcheckNotLike(String value) {
            addCriterion("FIELD_IF_SPELLCHECK not like", value, "fieldIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andFieldIfSpellcheckIn(List<String> values) {
            addCriterion("FIELD_IF_SPELLCHECK in", values, "fieldIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andFieldIfSpellcheckNotIn(List<String> values) {
            addCriterion("FIELD_IF_SPELLCHECK not in", values, "fieldIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andFieldIfSpellcheckBetween(String value1, String value2) {
            addCriterion("FIELD_IF_SPELLCHECK between", value1, value2, "fieldIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andFieldIfSpellcheckNotBetween(String value1, String value2) {
            addCriterion("FIELD_IF_SPELLCHECK not between", value1, value2, "fieldIfSpellcheck");
            return (Criteria) this;
        }

        public Criteria andFieldIfHlfieldIsNull() {
            addCriterion("FIELD_IF_HLFIELD is null");
            return (Criteria) this;
        }

        public Criteria andFieldIfHlfieldIsNotNull() {
            addCriterion("FIELD_IF_HLFIELD is not null");
            return (Criteria) this;
        }

        public Criteria andFieldIfHlfieldEqualTo(String value) {
            addCriterion("FIELD_IF_HLFIELD =", value, "fieldIfHlfield");
            return (Criteria) this;
        }

        public Criteria andFieldIfHlfieldNotEqualTo(String value) {
            addCriterion("FIELD_IF_HLFIELD <>", value, "fieldIfHlfield");
            return (Criteria) this;
        }

        public Criteria andFieldIfHlfieldGreaterThan(String value) {
            addCriterion("FIELD_IF_HLFIELD >", value, "fieldIfHlfield");
            return (Criteria) this;
        }

        public Criteria andFieldIfHlfieldGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_IF_HLFIELD >=", value, "fieldIfHlfield");
            return (Criteria) this;
        }

        public Criteria andFieldIfHlfieldLessThan(String value) {
            addCriterion("FIELD_IF_HLFIELD <", value, "fieldIfHlfield");
            return (Criteria) this;
        }

        public Criteria andFieldIfHlfieldLessThanOrEqualTo(String value) {
            addCriterion("FIELD_IF_HLFIELD <=", value, "fieldIfHlfield");
            return (Criteria) this;
        }

        public Criteria andFieldIfHlfieldLike(String value) {
            addCriterion("FIELD_IF_HLFIELD like", value, "fieldIfHlfield");
            return (Criteria) this;
        }

        public Criteria andFieldIfHlfieldNotLike(String value) {
            addCriterion("FIELD_IF_HLFIELD not like", value, "fieldIfHlfield");
            return (Criteria) this;
        }

        public Criteria andFieldIfHlfieldIn(List<String> values) {
            addCriterion("FIELD_IF_HLFIELD in", values, "fieldIfHlfield");
            return (Criteria) this;
        }

        public Criteria andFieldIfHlfieldNotIn(List<String> values) {
            addCriterion("FIELD_IF_HLFIELD not in", values, "fieldIfHlfield");
            return (Criteria) this;
        }

        public Criteria andFieldIfHlfieldBetween(String value1, String value2) {
            addCriterion("FIELD_IF_HLFIELD between", value1, value2, "fieldIfHlfield");
            return (Criteria) this;
        }

        public Criteria andFieldIfHlfieldNotBetween(String value1, String value2) {
            addCriterion("FIELD_IF_HLFIELD not between", value1, value2, "fieldIfHlfield");
            return (Criteria) this;
        }

        public Criteria andFieldSortIsNull() {
            addCriterion("FIELD_SORT is null");
            return (Criteria) this;
        }

        public Criteria andFieldSortIsNotNull() {
            addCriterion("FIELD_SORT is not null");
            return (Criteria) this;
        }

        public Criteria andFieldSortEqualTo(String value) {
            addCriterion("FIELD_SORT =", value, "fieldSort");
            return (Criteria) this;
        }

        public Criteria andFieldSortNotEqualTo(String value) {
            addCriterion("FIELD_SORT <>", value, "fieldSort");
            return (Criteria) this;
        }

        public Criteria andFieldSortGreaterThan(String value) {
            addCriterion("FIELD_SORT >", value, "fieldSort");
            return (Criteria) this;
        }

        public Criteria andFieldSortGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_SORT >=", value, "fieldSort");
            return (Criteria) this;
        }

        public Criteria andFieldSortLessThan(String value) {
            addCriterion("FIELD_SORT <", value, "fieldSort");
            return (Criteria) this;
        }

        public Criteria andFieldSortLessThanOrEqualTo(String value) {
            addCriterion("FIELD_SORT <=", value, "fieldSort");
            return (Criteria) this;
        }

        public Criteria andFieldSortLike(String value) {
            addCriterion("FIELD_SORT like", value, "fieldSort");
            return (Criteria) this;
        }

        public Criteria andFieldSortNotLike(String value) {
            addCriterion("FIELD_SORT not like", value, "fieldSort");
            return (Criteria) this;
        }

        public Criteria andFieldSortIn(List<String> values) {
            addCriterion("FIELD_SORT in", values, "fieldSort");
            return (Criteria) this;
        }

        public Criteria andFieldSortNotIn(List<String> values) {
            addCriterion("FIELD_SORT not in", values, "fieldSort");
            return (Criteria) this;
        }

        public Criteria andFieldSortBetween(String value1, String value2) {
            addCriterion("FIELD_SORT between", value1, value2, "fieldSort");
            return (Criteria) this;
        }

        public Criteria andFieldSortNotBetween(String value1, String value2) {
            addCriterion("FIELD_SORT not between", value1, value2, "fieldSort");
            return (Criteria) this;
        }

        public Criteria andFieldIfSortcustIsNull() {
            addCriterion("FIELD_IF_SORTCUST is null");
            return (Criteria) this;
        }

        public Criteria andFieldIfSortcustIsNotNull() {
            addCriterion("FIELD_IF_SORTCUST is not null");
            return (Criteria) this;
        }

        public Criteria andFieldIfSortcustEqualTo(String value) {
            addCriterion("FIELD_IF_SORTCUST =", value, "fieldIfSortcust");
            return (Criteria) this;
        }

        public Criteria andFieldIfSortcustNotEqualTo(String value) {
            addCriterion("FIELD_IF_SORTCUST <>", value, "fieldIfSortcust");
            return (Criteria) this;
        }

        public Criteria andFieldIfSortcustGreaterThan(String value) {
            addCriterion("FIELD_IF_SORTCUST >", value, "fieldIfSortcust");
            return (Criteria) this;
        }

        public Criteria andFieldIfSortcustGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_IF_SORTCUST >=", value, "fieldIfSortcust");
            return (Criteria) this;
        }

        public Criteria andFieldIfSortcustLessThan(String value) {
            addCriterion("FIELD_IF_SORTCUST <", value, "fieldIfSortcust");
            return (Criteria) this;
        }

        public Criteria andFieldIfSortcustLessThanOrEqualTo(String value) {
            addCriterion("FIELD_IF_SORTCUST <=", value, "fieldIfSortcust");
            return (Criteria) this;
        }

        public Criteria andFieldIfSortcustLike(String value) {
            addCriterion("FIELD_IF_SORTCUST like", value, "fieldIfSortcust");
            return (Criteria) this;
        }

        public Criteria andFieldIfSortcustNotLike(String value) {
            addCriterion("FIELD_IF_SORTCUST not like", value, "fieldIfSortcust");
            return (Criteria) this;
        }

        public Criteria andFieldIfSortcustIn(List<String> values) {
            addCriterion("FIELD_IF_SORTCUST in", values, "fieldIfSortcust");
            return (Criteria) this;
        }

        public Criteria andFieldIfSortcustNotIn(List<String> values) {
            addCriterion("FIELD_IF_SORTCUST not in", values, "fieldIfSortcust");
            return (Criteria) this;
        }

        public Criteria andFieldIfSortcustBetween(String value1, String value2) {
            addCriterion("FIELD_IF_SORTCUST between", value1, value2, "fieldIfSortcust");
            return (Criteria) this;
        }

        public Criteria andFieldIfSortcustNotBetween(String value1, String value2) {
            addCriterion("FIELD_IF_SORTCUST not between", value1, value2, "fieldIfSortcust");
            return (Criteria) this;
        }

        public Criteria andFieldBoostIsNull() {
            addCriterion("FIELD_BOOST is null");
            return (Criteria) this;
        }

        public Criteria andFieldBoostIsNotNull() {
            addCriterion("FIELD_BOOST is not null");
            return (Criteria) this;
        }

        public Criteria andFieldBoostEqualTo(BigDecimal value) {
            addCriterion("FIELD_BOOST =", value, "fieldBoost");
            return (Criteria) this;
        }

        public Criteria andFieldBoostNotEqualTo(BigDecimal value) {
            addCriterion("FIELD_BOOST <>", value, "fieldBoost");
            return (Criteria) this;
        }

        public Criteria andFieldBoostGreaterThan(BigDecimal value) {
            addCriterion("FIELD_BOOST >", value, "fieldBoost");
            return (Criteria) this;
        }

        public Criteria andFieldBoostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FIELD_BOOST >=", value, "fieldBoost");
            return (Criteria) this;
        }

        public Criteria andFieldBoostLessThan(BigDecimal value) {
            addCriterion("FIELD_BOOST <", value, "fieldBoost");
            return (Criteria) this;
        }

        public Criteria andFieldBoostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FIELD_BOOST <=", value, "fieldBoost");
            return (Criteria) this;
        }

        public Criteria andFieldBoostIn(List<BigDecimal> values) {
            addCriterion("FIELD_BOOST in", values, "fieldBoost");
            return (Criteria) this;
        }

        public Criteria andFieldBoostNotIn(List<BigDecimal> values) {
            addCriterion("FIELD_BOOST not in", values, "fieldBoost");
            return (Criteria) this;
        }

        public Criteria andFieldBoostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FIELD_BOOST between", value1, value2, "fieldBoost");
            return (Criteria) this;
        }

        public Criteria andFieldBoostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FIELD_BOOST not between", value1, value2, "fieldBoost");
            return (Criteria) this;
        }

        public Criteria andFieldIfMultilanIsNull() {
            addCriterion("FIELD_IF_MULTILAN is null");
            return (Criteria) this;
        }

        public Criteria andFieldIfMultilanIsNotNull() {
            addCriterion("FIELD_IF_MULTILAN is not null");
            return (Criteria) this;
        }

        public Criteria andFieldIfMultilanEqualTo(String value) {
            addCriterion("FIELD_IF_MULTILAN =", value, "fieldIfMultilan");
            return (Criteria) this;
        }

        public Criteria andFieldIfMultilanNotEqualTo(String value) {
            addCriterion("FIELD_IF_MULTILAN <>", value, "fieldIfMultilan");
            return (Criteria) this;
        }

        public Criteria andFieldIfMultilanGreaterThan(String value) {
            addCriterion("FIELD_IF_MULTILAN >", value, "fieldIfMultilan");
            return (Criteria) this;
        }

        public Criteria andFieldIfMultilanGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_IF_MULTILAN >=", value, "fieldIfMultilan");
            return (Criteria) this;
        }

        public Criteria andFieldIfMultilanLessThan(String value) {
            addCriterion("FIELD_IF_MULTILAN <", value, "fieldIfMultilan");
            return (Criteria) this;
        }

        public Criteria andFieldIfMultilanLessThanOrEqualTo(String value) {
            addCriterion("FIELD_IF_MULTILAN <=", value, "fieldIfMultilan");
            return (Criteria) this;
        }

        public Criteria andFieldIfMultilanLike(String value) {
            addCriterion("FIELD_IF_MULTILAN like", value, "fieldIfMultilan");
            return (Criteria) this;
        }

        public Criteria andFieldIfMultilanNotLike(String value) {
            addCriterion("FIELD_IF_MULTILAN not like", value, "fieldIfMultilan");
            return (Criteria) this;
        }

        public Criteria andFieldIfMultilanIn(List<String> values) {
            addCriterion("FIELD_IF_MULTILAN in", values, "fieldIfMultilan");
            return (Criteria) this;
        }

        public Criteria andFieldIfMultilanNotIn(List<String> values) {
            addCriterion("FIELD_IF_MULTILAN not in", values, "fieldIfMultilan");
            return (Criteria) this;
        }

        public Criteria andFieldIfMultilanBetween(String value1, String value2) {
            addCriterion("FIELD_IF_MULTILAN between", value1, value2, "fieldIfMultilan");
            return (Criteria) this;
        }

        public Criteria andFieldIfMultilanNotBetween(String value1, String value2) {
            addCriterion("FIELD_IF_MULTILAN not between", value1, value2, "fieldIfMultilan");
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

        public Criteria andFieldSortNumIsNull() {
            addCriterion("FIELD_SORT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andFieldSortNumIsNotNull() {
            addCriterion("FIELD_SORT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andFieldSortNumEqualTo(Short value) {
            addCriterion("FIELD_SORT_NUM =", value, "fieldSortNum");
            return (Criteria) this;
        }

        public Criteria andFieldSortNumNotEqualTo(Short value) {
            addCriterion("FIELD_SORT_NUM <>", value, "fieldSortNum");
            return (Criteria) this;
        }

        public Criteria andFieldSortNumGreaterThan(Short value) {
            addCriterion("FIELD_SORT_NUM >", value, "fieldSortNum");
            return (Criteria) this;
        }

        public Criteria andFieldSortNumGreaterThanOrEqualTo(Short value) {
            addCriterion("FIELD_SORT_NUM >=", value, "fieldSortNum");
            return (Criteria) this;
        }

        public Criteria andFieldSortNumLessThan(Short value) {
            addCriterion("FIELD_SORT_NUM <", value, "fieldSortNum");
            return (Criteria) this;
        }

        public Criteria andFieldSortNumLessThanOrEqualTo(Short value) {
            addCriterion("FIELD_SORT_NUM <=", value, "fieldSortNum");
            return (Criteria) this;
        }

        public Criteria andFieldSortNumIn(List<Short> values) {
            addCriterion("FIELD_SORT_NUM in", values, "fieldSortNum");
            return (Criteria) this;
        }

        public Criteria andFieldSortNumNotIn(List<Short> values) {
            addCriterion("FIELD_SORT_NUM not in", values, "fieldSortNum");
            return (Criteria) this;
        }

        public Criteria andFieldSortNumBetween(Short value1, Short value2) {
            addCriterion("FIELD_SORT_NUM between", value1, value2, "fieldSortNum");
            return (Criteria) this;
        }

        public Criteria andFieldSortNumNotBetween(Short value1, Short value2) {
            addCriterion("FIELD_SORT_NUM not between", value1, value2, "fieldSortNum");
            return (Criteria) this;
        }

        public Criteria andFieldParamsIsNull() {
            addCriterion("FIELD_PARAMS is null");
            return (Criteria) this;
        }

        public Criteria andFieldParamsIsNotNull() {
            addCriterion("FIELD_PARAMS is not null");
            return (Criteria) this;
        }

        public Criteria andFieldParamsEqualTo(String value) {
            addCriterion("FIELD_PARAMS =", value, "fieldParams");
            return (Criteria) this;
        }

        public Criteria andFieldParamsNotEqualTo(String value) {
            addCriterion("FIELD_PARAMS <>", value, "fieldParams");
            return (Criteria) this;
        }

        public Criteria andFieldParamsGreaterThan(String value) {
            addCriterion("FIELD_PARAMS >", value, "fieldParams");
            return (Criteria) this;
        }

        public Criteria andFieldParamsGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_PARAMS >=", value, "fieldParams");
            return (Criteria) this;
        }

        public Criteria andFieldParamsLessThan(String value) {
            addCriterion("FIELD_PARAMS <", value, "fieldParams");
            return (Criteria) this;
        }

        public Criteria andFieldParamsLessThanOrEqualTo(String value) {
            addCriterion("FIELD_PARAMS <=", value, "fieldParams");
            return (Criteria) this;
        }

        public Criteria andFieldParamsLike(String value) {
            addCriterion("FIELD_PARAMS like", value, "fieldParams");
            return (Criteria) this;
        }

        public Criteria andFieldParamsNotLike(String value) {
            addCriterion("FIELD_PARAMS not like", value, "fieldParams");
            return (Criteria) this;
        }

        public Criteria andFieldParamsIn(List<String> values) {
            addCriterion("FIELD_PARAMS in", values, "fieldParams");
            return (Criteria) this;
        }

        public Criteria andFieldParamsNotIn(List<String> values) {
            addCriterion("FIELD_PARAMS not in", values, "fieldParams");
            return (Criteria) this;
        }

        public Criteria andFieldParamsBetween(String value1, String value2) {
            addCriterion("FIELD_PARAMS between", value1, value2, "fieldParams");
            return (Criteria) this;
        }

        public Criteria andFieldParamsNotBetween(String value1, String value2) {
            addCriterion("FIELD_PARAMS not between", value1, value2, "fieldParams");
            return (Criteria) this;
        }

        public Criteria andFieldInitSortIsNull() {
            addCriterion("FIELD_INIT_SORT is null");
            return (Criteria) this;
        }

        public Criteria andFieldInitSortIsNotNull() {
            addCriterion("FIELD_INIT_SORT is not null");
            return (Criteria) this;
        }

        public Criteria andFieldInitSortEqualTo(Short value) {
            addCriterion("FIELD_INIT_SORT =", value, "fieldInitSort");
            return (Criteria) this;
        }

        public Criteria andFieldInitSortNotEqualTo(Short value) {
            addCriterion("FIELD_INIT_SORT <>", value, "fieldInitSort");
            return (Criteria) this;
        }

        public Criteria andFieldInitSortGreaterThan(Short value) {
            addCriterion("FIELD_INIT_SORT >", value, "fieldInitSort");
            return (Criteria) this;
        }

        public Criteria andFieldInitSortGreaterThanOrEqualTo(Short value) {
            addCriterion("FIELD_INIT_SORT >=", value, "fieldInitSort");
            return (Criteria) this;
        }

        public Criteria andFieldInitSortLessThan(Short value) {
            addCriterion("FIELD_INIT_SORT <", value, "fieldInitSort");
            return (Criteria) this;
        }

        public Criteria andFieldInitSortLessThanOrEqualTo(Short value) {
            addCriterion("FIELD_INIT_SORT <=", value, "fieldInitSort");
            return (Criteria) this;
        }

        public Criteria andFieldInitSortIn(List<Short> values) {
            addCriterion("FIELD_INIT_SORT in", values, "fieldInitSort");
            return (Criteria) this;
        }

        public Criteria andFieldInitSortNotIn(List<Short> values) {
            addCriterion("FIELD_INIT_SORT not in", values, "fieldInitSort");
            return (Criteria) this;
        }

        public Criteria andFieldInitSortBetween(Short value1, Short value2) {
            addCriterion("FIELD_INIT_SORT between", value1, value2, "fieldInitSort");
            return (Criteria) this;
        }

        public Criteria andFieldInitSortNotBetween(Short value1, Short value2) {
            addCriterion("FIELD_INIT_SORT not between", value1, value2, "fieldInitSort");
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