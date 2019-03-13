package com.zengshi.ecp.prom.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PromTypeCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PromTypeCriteria() {
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

        public Criteria andPromTypeNameIsNull() {
            addCriterion("PROM_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameIsNotNull() {
            addCriterion("PROM_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameEqualTo(String value) {
            addCriterion("PROM_TYPE_NAME =", value, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameNotEqualTo(String value) {
            addCriterion("PROM_TYPE_NAME <>", value, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameGreaterThan(String value) {
            addCriterion("PROM_TYPE_NAME >", value, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROM_TYPE_NAME >=", value, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameLessThan(String value) {
            addCriterion("PROM_TYPE_NAME <", value, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameLessThanOrEqualTo(String value) {
            addCriterion("PROM_TYPE_NAME <=", value, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameLike(String value) {
            addCriterion("PROM_TYPE_NAME like", value, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameNotLike(String value) {
            addCriterion("PROM_TYPE_NAME not like", value, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameIn(List<String> values) {
            addCriterion("PROM_TYPE_NAME in", values, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameNotIn(List<String> values) {
            addCriterion("PROM_TYPE_NAME not in", values, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameBetween(String value1, String value2) {
            addCriterion("PROM_TYPE_NAME between", value1, value2, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andPromTypeNameNotBetween(String value1, String value2) {
            addCriterion("PROM_TYPE_NAME not between", value1, value2, "promTypeName");
            return (Criteria) this;
        }

        public Criteria andNameShortIsNull() {
            addCriterion("NAME_SHORT is null");
            return (Criteria) this;
        }

        public Criteria andNameShortIsNotNull() {
            addCriterion("NAME_SHORT is not null");
            return (Criteria) this;
        }

        public Criteria andNameShortEqualTo(String value) {
            addCriterion("NAME_SHORT =", value, "nameShort");
            return (Criteria) this;
        }

        public Criteria andNameShortNotEqualTo(String value) {
            addCriterion("NAME_SHORT <>", value, "nameShort");
            return (Criteria) this;
        }

        public Criteria andNameShortGreaterThan(String value) {
            addCriterion("NAME_SHORT >", value, "nameShort");
            return (Criteria) this;
        }

        public Criteria andNameShortGreaterThanOrEqualTo(String value) {
            addCriterion("NAME_SHORT >=", value, "nameShort");
            return (Criteria) this;
        }

        public Criteria andNameShortLessThan(String value) {
            addCriterion("NAME_SHORT <", value, "nameShort");
            return (Criteria) this;
        }

        public Criteria andNameShortLessThanOrEqualTo(String value) {
            addCriterion("NAME_SHORT <=", value, "nameShort");
            return (Criteria) this;
        }

        public Criteria andNameShortLike(String value) {
            addCriterion("NAME_SHORT like", value, "nameShort");
            return (Criteria) this;
        }

        public Criteria andNameShortNotLike(String value) {
            addCriterion("NAME_SHORT not like", value, "nameShort");
            return (Criteria) this;
        }

        public Criteria andNameShortIn(List<String> values) {
            addCriterion("NAME_SHORT in", values, "nameShort");
            return (Criteria) this;
        }

        public Criteria andNameShortNotIn(List<String> values) {
            addCriterion("NAME_SHORT not in", values, "nameShort");
            return (Criteria) this;
        }

        public Criteria andNameShortBetween(String value1, String value2) {
            addCriterion("NAME_SHORT between", value1, value2, "nameShort");
            return (Criteria) this;
        }

        public Criteria andNameShortNotBetween(String value1, String value2) {
            addCriterion("NAME_SHORT not between", value1, value2, "nameShort");
            return (Criteria) this;
        }

        public Criteria andPromTypeDescIsNull() {
            addCriterion("PROM_TYPE_DESC is null");
            return (Criteria) this;
        }

        public Criteria andPromTypeDescIsNotNull() {
            addCriterion("PROM_TYPE_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andPromTypeDescEqualTo(String value) {
            addCriterion("PROM_TYPE_DESC =", value, "promTypeDesc");
            return (Criteria) this;
        }

        public Criteria andPromTypeDescNotEqualTo(String value) {
            addCriterion("PROM_TYPE_DESC <>", value, "promTypeDesc");
            return (Criteria) this;
        }

        public Criteria andPromTypeDescGreaterThan(String value) {
            addCriterion("PROM_TYPE_DESC >", value, "promTypeDesc");
            return (Criteria) this;
        }

        public Criteria andPromTypeDescGreaterThanOrEqualTo(String value) {
            addCriterion("PROM_TYPE_DESC >=", value, "promTypeDesc");
            return (Criteria) this;
        }

        public Criteria andPromTypeDescLessThan(String value) {
            addCriterion("PROM_TYPE_DESC <", value, "promTypeDesc");
            return (Criteria) this;
        }

        public Criteria andPromTypeDescLessThanOrEqualTo(String value) {
            addCriterion("PROM_TYPE_DESC <=", value, "promTypeDesc");
            return (Criteria) this;
        }

        public Criteria andPromTypeDescLike(String value) {
            addCriterion("PROM_TYPE_DESC like", value, "promTypeDesc");
            return (Criteria) this;
        }

        public Criteria andPromTypeDescNotLike(String value) {
            addCriterion("PROM_TYPE_DESC not like", value, "promTypeDesc");
            return (Criteria) this;
        }

        public Criteria andPromTypeDescIn(List<String> values) {
            addCriterion("PROM_TYPE_DESC in", values, "promTypeDesc");
            return (Criteria) this;
        }

        public Criteria andPromTypeDescNotIn(List<String> values) {
            addCriterion("PROM_TYPE_DESC not in", values, "promTypeDesc");
            return (Criteria) this;
        }

        public Criteria andPromTypeDescBetween(String value1, String value2) {
            addCriterion("PROM_TYPE_DESC between", value1, value2, "promTypeDesc");
            return (Criteria) this;
        }

        public Criteria andPromTypeDescNotBetween(String value1, String value2) {
            addCriterion("PROM_TYPE_DESC not between", value1, value2, "promTypeDesc");
            return (Criteria) this;
        }

        public Criteria andPromImgIsNull() {
            addCriterion("PROM_IMG is null");
            return (Criteria) this;
        }

        public Criteria andPromImgIsNotNull() {
            addCriterion("PROM_IMG is not null");
            return (Criteria) this;
        }

        public Criteria andPromImgEqualTo(String value) {
            addCriterion("PROM_IMG =", value, "promImg");
            return (Criteria) this;
        }

        public Criteria andPromImgNotEqualTo(String value) {
            addCriterion("PROM_IMG <>", value, "promImg");
            return (Criteria) this;
        }

        public Criteria andPromImgGreaterThan(String value) {
            addCriterion("PROM_IMG >", value, "promImg");
            return (Criteria) this;
        }

        public Criteria andPromImgGreaterThanOrEqualTo(String value) {
            addCriterion("PROM_IMG >=", value, "promImg");
            return (Criteria) this;
        }

        public Criteria andPromImgLessThan(String value) {
            addCriterion("PROM_IMG <", value, "promImg");
            return (Criteria) this;
        }

        public Criteria andPromImgLessThanOrEqualTo(String value) {
            addCriterion("PROM_IMG <=", value, "promImg");
            return (Criteria) this;
        }

        public Criteria andPromImgLike(String value) {
            addCriterion("PROM_IMG like", value, "promImg");
            return (Criteria) this;
        }

        public Criteria andPromImgNotLike(String value) {
            addCriterion("PROM_IMG not like", value, "promImg");
            return (Criteria) this;
        }

        public Criteria andPromImgIn(List<String> values) {
            addCriterion("PROM_IMG in", values, "promImg");
            return (Criteria) this;
        }

        public Criteria andPromImgNotIn(List<String> values) {
            addCriterion("PROM_IMG not in", values, "promImg");
            return (Criteria) this;
        }

        public Criteria andPromImgBetween(String value1, String value2) {
            addCriterion("PROM_IMG between", value1, value2, "promImg");
            return (Criteria) this;
        }

        public Criteria andPromImgNotBetween(String value1, String value2) {
            addCriterion("PROM_IMG not between", value1, value2, "promImg");
            return (Criteria) this;
        }

        public Criteria andPromClassIsNull() {
            addCriterion("PROM_CLASS is null");
            return (Criteria) this;
        }

        public Criteria andPromClassIsNotNull() {
            addCriterion("PROM_CLASS is not null");
            return (Criteria) this;
        }

        public Criteria andPromClassEqualTo(String value) {
            addCriterion("PROM_CLASS =", value, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassNotEqualTo(String value) {
            addCriterion("PROM_CLASS <>", value, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassGreaterThan(String value) {
            addCriterion("PROM_CLASS >", value, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassGreaterThanOrEqualTo(String value) {
            addCriterion("PROM_CLASS >=", value, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassLessThan(String value) {
            addCriterion("PROM_CLASS <", value, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassLessThanOrEqualTo(String value) {
            addCriterion("PROM_CLASS <=", value, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassLike(String value) {
            addCriterion("PROM_CLASS like", value, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassNotLike(String value) {
            addCriterion("PROM_CLASS not like", value, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassIn(List<String> values) {
            addCriterion("PROM_CLASS in", values, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassNotIn(List<String> values) {
            addCriterion("PROM_CLASS not in", values, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassBetween(String value1, String value2) {
            addCriterion("PROM_CLASS between", value1, value2, "promClass");
            return (Criteria) this;
        }

        public Criteria andPromClassNotBetween(String value1, String value2) {
            addCriterion("PROM_CLASS not between", value1, value2, "promClass");
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

        public Criteria andIfCompositIsNull() {
            addCriterion("IF_COMPOSIT is null");
            return (Criteria) this;
        }

        public Criteria andIfCompositIsNotNull() {
            addCriterion("IF_COMPOSIT is not null");
            return (Criteria) this;
        }

        public Criteria andIfCompositEqualTo(String value) {
            addCriterion("IF_COMPOSIT =", value, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositNotEqualTo(String value) {
            addCriterion("IF_COMPOSIT <>", value, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositGreaterThan(String value) {
            addCriterion("IF_COMPOSIT >", value, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositGreaterThanOrEqualTo(String value) {
            addCriterion("IF_COMPOSIT >=", value, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositLessThan(String value) {
            addCriterion("IF_COMPOSIT <", value, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositLessThanOrEqualTo(String value) {
            addCriterion("IF_COMPOSIT <=", value, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositLike(String value) {
            addCriterion("IF_COMPOSIT like", value, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositNotLike(String value) {
            addCriterion("IF_COMPOSIT not like", value, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositIn(List<String> values) {
            addCriterion("IF_COMPOSIT in", values, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositNotIn(List<String> values) {
            addCriterion("IF_COMPOSIT not in", values, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositBetween(String value1, String value2) {
            addCriterion("IF_COMPOSIT between", value1, value2, "ifComposit");
            return (Criteria) this;
        }

        public Criteria andIfCompositNotBetween(String value1, String value2) {
            addCriterion("IF_COMPOSIT not between", value1, value2, "ifComposit");
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

        public Criteria andJsonBeanIdIsNull() {
            addCriterion("JSON_BEAN_ID is null");
            return (Criteria) this;
        }

        public Criteria andJsonBeanIdIsNotNull() {
            addCriterion("JSON_BEAN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andJsonBeanIdEqualTo(String value) {
            addCriterion("JSON_BEAN_ID =", value, "jsonBeanId");
            return (Criteria) this;
        }

        public Criteria andJsonBeanIdNotEqualTo(String value) {
            addCriterion("JSON_BEAN_ID <>", value, "jsonBeanId");
            return (Criteria) this;
        }

        public Criteria andJsonBeanIdGreaterThan(String value) {
            addCriterion("JSON_BEAN_ID >", value, "jsonBeanId");
            return (Criteria) this;
        }

        public Criteria andJsonBeanIdGreaterThanOrEqualTo(String value) {
            addCriterion("JSON_BEAN_ID >=", value, "jsonBeanId");
            return (Criteria) this;
        }

        public Criteria andJsonBeanIdLessThan(String value) {
            addCriterion("JSON_BEAN_ID <", value, "jsonBeanId");
            return (Criteria) this;
        }

        public Criteria andJsonBeanIdLessThanOrEqualTo(String value) {
            addCriterion("JSON_BEAN_ID <=", value, "jsonBeanId");
            return (Criteria) this;
        }

        public Criteria andJsonBeanIdLike(String value) {
            addCriterion("JSON_BEAN_ID like", value, "jsonBeanId");
            return (Criteria) this;
        }

        public Criteria andJsonBeanIdNotLike(String value) {
            addCriterion("JSON_BEAN_ID not like", value, "jsonBeanId");
            return (Criteria) this;
        }

        public Criteria andJsonBeanIdIn(List<String> values) {
            addCriterion("JSON_BEAN_ID in", values, "jsonBeanId");
            return (Criteria) this;
        }

        public Criteria andJsonBeanIdNotIn(List<String> values) {
            addCriterion("JSON_BEAN_ID not in", values, "jsonBeanId");
            return (Criteria) this;
        }

        public Criteria andJsonBeanIdBetween(String value1, String value2) {
            addCriterion("JSON_BEAN_ID between", value1, value2, "jsonBeanId");
            return (Criteria) this;
        }

        public Criteria andJsonBeanIdNotBetween(String value1, String value2) {
            addCriterion("JSON_BEAN_ID not between", value1, value2, "jsonBeanId");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNull() {
            addCriterion("SERVICE_ID is null");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNotNull() {
            addCriterion("SERVICE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andServiceIdEqualTo(String value) {
            addCriterion("SERVICE_ID =", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotEqualTo(String value) {
            addCriterion("SERVICE_ID <>", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThan(String value) {
            addCriterion("SERVICE_ID >", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICE_ID >=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThan(String value) {
            addCriterion("SERVICE_ID <", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThanOrEqualTo(String value) {
            addCriterion("SERVICE_ID <=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLike(String value) {
            addCriterion("SERVICE_ID like", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotLike(String value) {
            addCriterion("SERVICE_ID not like", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdIn(List<String> values) {
            addCriterion("SERVICE_ID in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotIn(List<String> values) {
            addCriterion("SERVICE_ID not in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdBetween(String value1, String value2) {
            addCriterion("SERVICE_ID between", value1, value2, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotBetween(String value1, String value2) {
            addCriterion("SERVICE_ID not between", value1, value2, "serviceId");
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

        public Criteria andSortNoEqualTo(BigDecimal value) {
            addCriterion("SORT_NO =", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotEqualTo(BigDecimal value) {
            addCriterion("SORT_NO <>", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThan(BigDecimal value) {
            addCriterion("SORT_NO >", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SORT_NO >=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThan(BigDecimal value) {
            addCriterion("SORT_NO <", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SORT_NO <=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoIn(List<BigDecimal> values) {
            addCriterion("SORT_NO in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotIn(List<BigDecimal> values) {
            addCriterion("SORT_NO not in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SORT_NO between", value1, value2, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SORT_NO not between", value1, value2, "sortNo");
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