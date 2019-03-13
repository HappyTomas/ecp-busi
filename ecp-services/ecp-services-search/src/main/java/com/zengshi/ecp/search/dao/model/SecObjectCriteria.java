package com.zengshi.ecp.search.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SecObjectCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public SecObjectCriteria() {
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

        public Criteria andObjectNamecnIsNull() {
            addCriterion("OBJECT_NAMECN is null");
            return (Criteria) this;
        }

        public Criteria andObjectNamecnIsNotNull() {
            addCriterion("OBJECT_NAMECN is not null");
            return (Criteria) this;
        }

        public Criteria andObjectNamecnEqualTo(String value) {
            addCriterion("OBJECT_NAMECN =", value, "objectNamecn");
            return (Criteria) this;
        }

        public Criteria andObjectNamecnNotEqualTo(String value) {
            addCriterion("OBJECT_NAMECN <>", value, "objectNamecn");
            return (Criteria) this;
        }

        public Criteria andObjectNamecnGreaterThan(String value) {
            addCriterion("OBJECT_NAMECN >", value, "objectNamecn");
            return (Criteria) this;
        }

        public Criteria andObjectNamecnGreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT_NAMECN >=", value, "objectNamecn");
            return (Criteria) this;
        }

        public Criteria andObjectNamecnLessThan(String value) {
            addCriterion("OBJECT_NAMECN <", value, "objectNamecn");
            return (Criteria) this;
        }

        public Criteria andObjectNamecnLessThanOrEqualTo(String value) {
            addCriterion("OBJECT_NAMECN <=", value, "objectNamecn");
            return (Criteria) this;
        }

        public Criteria andObjectNamecnLike(String value) {
            addCriterion("OBJECT_NAMECN like", value, "objectNamecn");
            return (Criteria) this;
        }

        public Criteria andObjectNamecnNotLike(String value) {
            addCriterion("OBJECT_NAMECN not like", value, "objectNamecn");
            return (Criteria) this;
        }

        public Criteria andObjectNamecnIn(List<String> values) {
            addCriterion("OBJECT_NAMECN in", values, "objectNamecn");
            return (Criteria) this;
        }

        public Criteria andObjectNamecnNotIn(List<String> values) {
            addCriterion("OBJECT_NAMECN not in", values, "objectNamecn");
            return (Criteria) this;
        }

        public Criteria andObjectNamecnBetween(String value1, String value2) {
            addCriterion("OBJECT_NAMECN between", value1, value2, "objectNamecn");
            return (Criteria) this;
        }

        public Criteria andObjectNamecnNotBetween(String value1, String value2) {
            addCriterion("OBJECT_NAMECN not between", value1, value2, "objectNamecn");
            return (Criteria) this;
        }

        public Criteria andObjectNameenIsNull() {
            addCriterion("OBJECT_NAMEEN is null");
            return (Criteria) this;
        }

        public Criteria andObjectNameenIsNotNull() {
            addCriterion("OBJECT_NAMEEN is not null");
            return (Criteria) this;
        }

        public Criteria andObjectNameenEqualTo(String value) {
            addCriterion("OBJECT_NAMEEN =", value, "objectNameen");
            return (Criteria) this;
        }

        public Criteria andObjectNameenNotEqualTo(String value) {
            addCriterion("OBJECT_NAMEEN <>", value, "objectNameen");
            return (Criteria) this;
        }

        public Criteria andObjectNameenGreaterThan(String value) {
            addCriterion("OBJECT_NAMEEN >", value, "objectNameen");
            return (Criteria) this;
        }

        public Criteria andObjectNameenGreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT_NAMEEN >=", value, "objectNameen");
            return (Criteria) this;
        }

        public Criteria andObjectNameenLessThan(String value) {
            addCriterion("OBJECT_NAMEEN <", value, "objectNameen");
            return (Criteria) this;
        }

        public Criteria andObjectNameenLessThanOrEqualTo(String value) {
            addCriterion("OBJECT_NAMEEN <=", value, "objectNameen");
            return (Criteria) this;
        }

        public Criteria andObjectNameenLike(String value) {
            addCriterion("OBJECT_NAMEEN like", value, "objectNameen");
            return (Criteria) this;
        }

        public Criteria andObjectNameenNotLike(String value) {
            addCriterion("OBJECT_NAMEEN not like", value, "objectNameen");
            return (Criteria) this;
        }

        public Criteria andObjectNameenIn(List<String> values) {
            addCriterion("OBJECT_NAMEEN in", values, "objectNameen");
            return (Criteria) this;
        }

        public Criteria andObjectNameenNotIn(List<String> values) {
            addCriterion("OBJECT_NAMEEN not in", values, "objectNameen");
            return (Criteria) this;
        }

        public Criteria andObjectNameenBetween(String value1, String value2) {
            addCriterion("OBJECT_NAMEEN between", value1, value2, "objectNameen");
            return (Criteria) this;
        }

        public Criteria andObjectNameenNotBetween(String value1, String value2) {
            addCriterion("OBJECT_NAMEEN not between", value1, value2, "objectNameen");
            return (Criteria) this;
        }

        public Criteria andObjectDescIsNull() {
            addCriterion("OBJECT_DESC is null");
            return (Criteria) this;
        }

        public Criteria andObjectDescIsNotNull() {
            addCriterion("OBJECT_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andObjectDescEqualTo(String value) {
            addCriterion("OBJECT_DESC =", value, "objectDesc");
            return (Criteria) this;
        }

        public Criteria andObjectDescNotEqualTo(String value) {
            addCriterion("OBJECT_DESC <>", value, "objectDesc");
            return (Criteria) this;
        }

        public Criteria andObjectDescGreaterThan(String value) {
            addCriterion("OBJECT_DESC >", value, "objectDesc");
            return (Criteria) this;
        }

        public Criteria andObjectDescGreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT_DESC >=", value, "objectDesc");
            return (Criteria) this;
        }

        public Criteria andObjectDescLessThan(String value) {
            addCriterion("OBJECT_DESC <", value, "objectDesc");
            return (Criteria) this;
        }

        public Criteria andObjectDescLessThanOrEqualTo(String value) {
            addCriterion("OBJECT_DESC <=", value, "objectDesc");
            return (Criteria) this;
        }

        public Criteria andObjectDescLike(String value) {
            addCriterion("OBJECT_DESC like", value, "objectDesc");
            return (Criteria) this;
        }

        public Criteria andObjectDescNotLike(String value) {
            addCriterion("OBJECT_DESC not like", value, "objectDesc");
            return (Criteria) this;
        }

        public Criteria andObjectDescIn(List<String> values) {
            addCriterion("OBJECT_DESC in", values, "objectDesc");
            return (Criteria) this;
        }

        public Criteria andObjectDescNotIn(List<String> values) {
            addCriterion("OBJECT_DESC not in", values, "objectDesc");
            return (Criteria) this;
        }

        public Criteria andObjectDescBetween(String value1, String value2) {
            addCriterion("OBJECT_DESC between", value1, value2, "objectDesc");
            return (Criteria) this;
        }

        public Criteria andObjectDescNotBetween(String value1, String value2) {
            addCriterion("OBJECT_DESC not between", value1, value2, "objectDesc");
            return (Criteria) this;
        }

        public Criteria andObjectUniquefieldNameIsNull() {
            addCriterion("OBJECT_UNIQUEFIELD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andObjectUniquefieldNameIsNotNull() {
            addCriterion("OBJECT_UNIQUEFIELD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andObjectUniquefieldNameEqualTo(String value) {
            addCriterion("OBJECT_UNIQUEFIELD_NAME =", value, "objectUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andObjectUniquefieldNameNotEqualTo(String value) {
            addCriterion("OBJECT_UNIQUEFIELD_NAME <>", value, "objectUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andObjectUniquefieldNameGreaterThan(String value) {
            addCriterion("OBJECT_UNIQUEFIELD_NAME >", value, "objectUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andObjectUniquefieldNameGreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT_UNIQUEFIELD_NAME >=", value, "objectUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andObjectUniquefieldNameLessThan(String value) {
            addCriterion("OBJECT_UNIQUEFIELD_NAME <", value, "objectUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andObjectUniquefieldNameLessThanOrEqualTo(String value) {
            addCriterion("OBJECT_UNIQUEFIELD_NAME <=", value, "objectUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andObjectUniquefieldNameLike(String value) {
            addCriterion("OBJECT_UNIQUEFIELD_NAME like", value, "objectUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andObjectUniquefieldNameNotLike(String value) {
            addCriterion("OBJECT_UNIQUEFIELD_NAME not like", value, "objectUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andObjectUniquefieldNameIn(List<String> values) {
            addCriterion("OBJECT_UNIQUEFIELD_NAME in", values, "objectUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andObjectUniquefieldNameNotIn(List<String> values) {
            addCriterion("OBJECT_UNIQUEFIELD_NAME not in", values, "objectUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andObjectUniquefieldNameBetween(String value1, String value2) {
            addCriterion("OBJECT_UNIQUEFIELD_NAME between", value1, value2, "objectUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andObjectUniquefieldNameNotBetween(String value1, String value2) {
            addCriterion("OBJECT_UNIQUEFIELD_NAME not between", value1, value2, "objectUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andObjectProcessorNameIsNull() {
            addCriterion("OBJECT_PROCESSOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andObjectProcessorNameIsNotNull() {
            addCriterion("OBJECT_PROCESSOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andObjectProcessorNameEqualTo(String value) {
            addCriterion("OBJECT_PROCESSOR_NAME =", value, "objectProcessorName");
            return (Criteria) this;
        }

        public Criteria andObjectProcessorNameNotEqualTo(String value) {
            addCriterion("OBJECT_PROCESSOR_NAME <>", value, "objectProcessorName");
            return (Criteria) this;
        }

        public Criteria andObjectProcessorNameGreaterThan(String value) {
            addCriterion("OBJECT_PROCESSOR_NAME >", value, "objectProcessorName");
            return (Criteria) this;
        }

        public Criteria andObjectProcessorNameGreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT_PROCESSOR_NAME >=", value, "objectProcessorName");
            return (Criteria) this;
        }

        public Criteria andObjectProcessorNameLessThan(String value) {
            addCriterion("OBJECT_PROCESSOR_NAME <", value, "objectProcessorName");
            return (Criteria) this;
        }

        public Criteria andObjectProcessorNameLessThanOrEqualTo(String value) {
            addCriterion("OBJECT_PROCESSOR_NAME <=", value, "objectProcessorName");
            return (Criteria) this;
        }

        public Criteria andObjectProcessorNameLike(String value) {
            addCriterion("OBJECT_PROCESSOR_NAME like", value, "objectProcessorName");
            return (Criteria) this;
        }

        public Criteria andObjectProcessorNameNotLike(String value) {
            addCriterion("OBJECT_PROCESSOR_NAME not like", value, "objectProcessorName");
            return (Criteria) this;
        }

        public Criteria andObjectProcessorNameIn(List<String> values) {
            addCriterion("OBJECT_PROCESSOR_NAME in", values, "objectProcessorName");
            return (Criteria) this;
        }

        public Criteria andObjectProcessorNameNotIn(List<String> values) {
            addCriterion("OBJECT_PROCESSOR_NAME not in", values, "objectProcessorName");
            return (Criteria) this;
        }

        public Criteria andObjectProcessorNameBetween(String value1, String value2) {
            addCriterion("OBJECT_PROCESSOR_NAME between", value1, value2, "objectProcessorName");
            return (Criteria) this;
        }

        public Criteria andObjectProcessorNameNotBetween(String value1, String value2) {
            addCriterion("OBJECT_PROCESSOR_NAME not between", value1, value2, "objectProcessorName");
            return (Criteria) this;
        }

        public Criteria andDubboServicenameIsNull() {
            addCriterion("DUBBO_SERVICENAME is null");
            return (Criteria) this;
        }

        public Criteria andDubboServicenameIsNotNull() {
            addCriterion("DUBBO_SERVICENAME is not null");
            return (Criteria) this;
        }

        public Criteria andDubboServicenameEqualTo(String value) {
            addCriterion("DUBBO_SERVICENAME =", value, "dubboServicename");
            return (Criteria) this;
        }

        public Criteria andDubboServicenameNotEqualTo(String value) {
            addCriterion("DUBBO_SERVICENAME <>", value, "dubboServicename");
            return (Criteria) this;
        }

        public Criteria andDubboServicenameGreaterThan(String value) {
            addCriterion("DUBBO_SERVICENAME >", value, "dubboServicename");
            return (Criteria) this;
        }

        public Criteria andDubboServicenameGreaterThanOrEqualTo(String value) {
            addCriterion("DUBBO_SERVICENAME >=", value, "dubboServicename");
            return (Criteria) this;
        }

        public Criteria andDubboServicenameLessThan(String value) {
            addCriterion("DUBBO_SERVICENAME <", value, "dubboServicename");
            return (Criteria) this;
        }

        public Criteria andDubboServicenameLessThanOrEqualTo(String value) {
            addCriterion("DUBBO_SERVICENAME <=", value, "dubboServicename");
            return (Criteria) this;
        }

        public Criteria andDubboServicenameLike(String value) {
            addCriterion("DUBBO_SERVICENAME like", value, "dubboServicename");
            return (Criteria) this;
        }

        public Criteria andDubboServicenameNotLike(String value) {
            addCriterion("DUBBO_SERVICENAME not like", value, "dubboServicename");
            return (Criteria) this;
        }

        public Criteria andDubboServicenameIn(List<String> values) {
            addCriterion("DUBBO_SERVICENAME in", values, "dubboServicename");
            return (Criteria) this;
        }

        public Criteria andDubboServicenameNotIn(List<String> values) {
            addCriterion("DUBBO_SERVICENAME not in", values, "dubboServicename");
            return (Criteria) this;
        }

        public Criteria andDubboServicenameBetween(String value1, String value2) {
            addCriterion("DUBBO_SERVICENAME between", value1, value2, "dubboServicename");
            return (Criteria) this;
        }

        public Criteria andDubboServicenameNotBetween(String value1, String value2) {
            addCriterion("DUBBO_SERVICENAME not between", value1, value2, "dubboServicename");
            return (Criteria) this;
        }

        public Criteria andObjectParamsIsNull() {
            addCriterion("OBJECT_PARAMS is null");
            return (Criteria) this;
        }

        public Criteria andObjectParamsIsNotNull() {
            addCriterion("OBJECT_PARAMS is not null");
            return (Criteria) this;
        }

        public Criteria andObjectParamsEqualTo(String value) {
            addCriterion("OBJECT_PARAMS =", value, "objectParams");
            return (Criteria) this;
        }

        public Criteria andObjectParamsNotEqualTo(String value) {
            addCriterion("OBJECT_PARAMS <>", value, "objectParams");
            return (Criteria) this;
        }

        public Criteria andObjectParamsGreaterThan(String value) {
            addCriterion("OBJECT_PARAMS >", value, "objectParams");
            return (Criteria) this;
        }

        public Criteria andObjectParamsGreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT_PARAMS >=", value, "objectParams");
            return (Criteria) this;
        }

        public Criteria andObjectParamsLessThan(String value) {
            addCriterion("OBJECT_PARAMS <", value, "objectParams");
            return (Criteria) this;
        }

        public Criteria andObjectParamsLessThanOrEqualTo(String value) {
            addCriterion("OBJECT_PARAMS <=", value, "objectParams");
            return (Criteria) this;
        }

        public Criteria andObjectParamsLike(String value) {
            addCriterion("OBJECT_PARAMS like", value, "objectParams");
            return (Criteria) this;
        }

        public Criteria andObjectParamsNotLike(String value) {
            addCriterion("OBJECT_PARAMS not like", value, "objectParams");
            return (Criteria) this;
        }

        public Criteria andObjectParamsIn(List<String> values) {
            addCriterion("OBJECT_PARAMS in", values, "objectParams");
            return (Criteria) this;
        }

        public Criteria andObjectParamsNotIn(List<String> values) {
            addCriterion("OBJECT_PARAMS not in", values, "objectParams");
            return (Criteria) this;
        }

        public Criteria andObjectParamsBetween(String value1, String value2) {
            addCriterion("OBJECT_PARAMS between", value1, value2, "objectParams");
            return (Criteria) this;
        }

        public Criteria andObjectParamsNotBetween(String value1, String value2) {
            addCriterion("OBJECT_PARAMS not between", value1, value2, "objectParams");
            return (Criteria) this;
        }

        public Criteria andMethodQuerybyidParamTypeIsNull() {
            addCriterion("METHOD_QUERYBYID_PARAM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andMethodQuerybyidParamTypeIsNotNull() {
            addCriterion("METHOD_QUERYBYID_PARAM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMethodQuerybyidParamTypeEqualTo(String value) {
            addCriterion("METHOD_QUERYBYID_PARAM_TYPE =", value, "methodQuerybyidParamType");
            return (Criteria) this;
        }

        public Criteria andMethodQuerybyidParamTypeNotEqualTo(String value) {
            addCriterion("METHOD_QUERYBYID_PARAM_TYPE <>", value, "methodQuerybyidParamType");
            return (Criteria) this;
        }

        public Criteria andMethodQuerybyidParamTypeGreaterThan(String value) {
            addCriterion("METHOD_QUERYBYID_PARAM_TYPE >", value, "methodQuerybyidParamType");
            return (Criteria) this;
        }

        public Criteria andMethodQuerybyidParamTypeGreaterThanOrEqualTo(String value) {
            addCriterion("METHOD_QUERYBYID_PARAM_TYPE >=", value, "methodQuerybyidParamType");
            return (Criteria) this;
        }

        public Criteria andMethodQuerybyidParamTypeLessThan(String value) {
            addCriterion("METHOD_QUERYBYID_PARAM_TYPE <", value, "methodQuerybyidParamType");
            return (Criteria) this;
        }

        public Criteria andMethodQuerybyidParamTypeLessThanOrEqualTo(String value) {
            addCriterion("METHOD_QUERYBYID_PARAM_TYPE <=", value, "methodQuerybyidParamType");
            return (Criteria) this;
        }

        public Criteria andMethodQuerybyidParamTypeLike(String value) {
            addCriterion("METHOD_QUERYBYID_PARAM_TYPE like", value, "methodQuerybyidParamType");
            return (Criteria) this;
        }

        public Criteria andMethodQuerybyidParamTypeNotLike(String value) {
            addCriterion("METHOD_QUERYBYID_PARAM_TYPE not like", value, "methodQuerybyidParamType");
            return (Criteria) this;
        }

        public Criteria andMethodQuerybyidParamTypeIn(List<String> values) {
            addCriterion("METHOD_QUERYBYID_PARAM_TYPE in", values, "methodQuerybyidParamType");
            return (Criteria) this;
        }

        public Criteria andMethodQuerybyidParamTypeNotIn(List<String> values) {
            addCriterion("METHOD_QUERYBYID_PARAM_TYPE not in", values, "methodQuerybyidParamType");
            return (Criteria) this;
        }

        public Criteria andMethodQuerybyidParamTypeBetween(String value1, String value2) {
            addCriterion("METHOD_QUERYBYID_PARAM_TYPE between", value1, value2, "methodQuerybyidParamType");
            return (Criteria) this;
        }

        public Criteria andMethodQuerybyidParamTypeNotBetween(String value1, String value2) {
            addCriterion("METHOD_QUERYBYID_PARAM_TYPE not between", value1, value2, "methodQuerybyidParamType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeIsNull() {
            addCriterion("OBJECT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andObjectTypeIsNotNull() {
            addCriterion("OBJECT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andObjectTypeEqualTo(String value) {
            addCriterion("OBJECT_TYPE =", value, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeNotEqualTo(String value) {
            addCriterion("OBJECT_TYPE <>", value, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeGreaterThan(String value) {
            addCriterion("OBJECT_TYPE >", value, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeGreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT_TYPE >=", value, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeLessThan(String value) {
            addCriterion("OBJECT_TYPE <", value, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeLessThanOrEqualTo(String value) {
            addCriterion("OBJECT_TYPE <=", value, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeLike(String value) {
            addCriterion("OBJECT_TYPE like", value, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeNotLike(String value) {
            addCriterion("OBJECT_TYPE not like", value, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeIn(List<String> values) {
            addCriterion("OBJECT_TYPE in", values, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeNotIn(List<String> values) {
            addCriterion("OBJECT_TYPE not in", values, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeBetween(String value1, String value2) {
            addCriterion("OBJECT_TYPE between", value1, value2, "objectType");
            return (Criteria) this;
        }

        public Criteria andObjectTypeNotBetween(String value1, String value2) {
            addCriterion("OBJECT_TYPE not between", value1, value2, "objectType");
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

        public Criteria andObjectHandlerIsNull() {
            addCriterion("OBJECT_HANDLER is null");
            return (Criteria) this;
        }

        public Criteria andObjectHandlerIsNotNull() {
            addCriterion("OBJECT_HANDLER is not null");
            return (Criteria) this;
        }

        public Criteria andObjectHandlerEqualTo(String value) {
            addCriterion("OBJECT_HANDLER =", value, "objectHandler");
            return (Criteria) this;
        }

        public Criteria andObjectHandlerNotEqualTo(String value) {
            addCriterion("OBJECT_HANDLER <>", value, "objectHandler");
            return (Criteria) this;
        }

        public Criteria andObjectHandlerGreaterThan(String value) {
            addCriterion("OBJECT_HANDLER >", value, "objectHandler");
            return (Criteria) this;
        }

        public Criteria andObjectHandlerGreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT_HANDLER >=", value, "objectHandler");
            return (Criteria) this;
        }

        public Criteria andObjectHandlerLessThan(String value) {
            addCriterion("OBJECT_HANDLER <", value, "objectHandler");
            return (Criteria) this;
        }

        public Criteria andObjectHandlerLessThanOrEqualTo(String value) {
            addCriterion("OBJECT_HANDLER <=", value, "objectHandler");
            return (Criteria) this;
        }

        public Criteria andObjectHandlerLike(String value) {
            addCriterion("OBJECT_HANDLER like", value, "objectHandler");
            return (Criteria) this;
        }

        public Criteria andObjectHandlerNotLike(String value) {
            addCriterion("OBJECT_HANDLER not like", value, "objectHandler");
            return (Criteria) this;
        }

        public Criteria andObjectHandlerIn(List<String> values) {
            addCriterion("OBJECT_HANDLER in", values, "objectHandler");
            return (Criteria) this;
        }

        public Criteria andObjectHandlerNotIn(List<String> values) {
            addCriterion("OBJECT_HANDLER not in", values, "objectHandler");
            return (Criteria) this;
        }

        public Criteria andObjectHandlerBetween(String value1, String value2) {
            addCriterion("OBJECT_HANDLER between", value1, value2, "objectHandler");
            return (Criteria) this;
        }

        public Criteria andObjectHandlerNotBetween(String value1, String value2) {
            addCriterion("OBJECT_HANDLER not between", value1, value2, "objectHandler");
            return (Criteria) this;
        }

        public Criteria andObjectInspectorIsNull() {
            addCriterion("OBJECT_INSPECTOR is null");
            return (Criteria) this;
        }

        public Criteria andObjectInspectorIsNotNull() {
            addCriterion("OBJECT_INSPECTOR is not null");
            return (Criteria) this;
        }

        public Criteria andObjectInspectorEqualTo(String value) {
            addCriterion("OBJECT_INSPECTOR =", value, "objectInspector");
            return (Criteria) this;
        }

        public Criteria andObjectInspectorNotEqualTo(String value) {
            addCriterion("OBJECT_INSPECTOR <>", value, "objectInspector");
            return (Criteria) this;
        }

        public Criteria andObjectInspectorGreaterThan(String value) {
            addCriterion("OBJECT_INSPECTOR >", value, "objectInspector");
            return (Criteria) this;
        }

        public Criteria andObjectInspectorGreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT_INSPECTOR >=", value, "objectInspector");
            return (Criteria) this;
        }

        public Criteria andObjectInspectorLessThan(String value) {
            addCriterion("OBJECT_INSPECTOR <", value, "objectInspector");
            return (Criteria) this;
        }

        public Criteria andObjectInspectorLessThanOrEqualTo(String value) {
            addCriterion("OBJECT_INSPECTOR <=", value, "objectInspector");
            return (Criteria) this;
        }

        public Criteria andObjectInspectorLike(String value) {
            addCriterion("OBJECT_INSPECTOR like", value, "objectInspector");
            return (Criteria) this;
        }

        public Criteria andObjectInspectorNotLike(String value) {
            addCriterion("OBJECT_INSPECTOR not like", value, "objectInspector");
            return (Criteria) this;
        }

        public Criteria andObjectInspectorIn(List<String> values) {
            addCriterion("OBJECT_INSPECTOR in", values, "objectInspector");
            return (Criteria) this;
        }

        public Criteria andObjectInspectorNotIn(List<String> values) {
            addCriterion("OBJECT_INSPECTOR not in", values, "objectInspector");
            return (Criteria) this;
        }

        public Criteria andObjectInspectorBetween(String value1, String value2) {
            addCriterion("OBJECT_INSPECTOR between", value1, value2, "objectInspector");
            return (Criteria) this;
        }

        public Criteria andObjectInspectorNotBetween(String value1, String value2) {
            addCriterion("OBJECT_INSPECTOR not between", value1, value2, "objectInspector");
            return (Criteria) this;
        }

        public Criteria andPagesizeIsNull() {
            addCriterion("PAGESIZE is null");
            return (Criteria) this;
        }

        public Criteria andPagesizeIsNotNull() {
            addCriterion("PAGESIZE is not null");
            return (Criteria) this;
        }

        public Criteria andPagesizeEqualTo(String value) {
            addCriterion("PAGESIZE =", value, "pagesize");
            return (Criteria) this;
        }

        public Criteria andPagesizeNotEqualTo(String value) {
            addCriterion("PAGESIZE <>", value, "pagesize");
            return (Criteria) this;
        }

        public Criteria andPagesizeGreaterThan(String value) {
            addCriterion("PAGESIZE >", value, "pagesize");
            return (Criteria) this;
        }

        public Criteria andPagesizeGreaterThanOrEqualTo(String value) {
            addCriterion("PAGESIZE >=", value, "pagesize");
            return (Criteria) this;
        }

        public Criteria andPagesizeLessThan(String value) {
            addCriterion("PAGESIZE <", value, "pagesize");
            return (Criteria) this;
        }

        public Criteria andPagesizeLessThanOrEqualTo(String value) {
            addCriterion("PAGESIZE <=", value, "pagesize");
            return (Criteria) this;
        }

        public Criteria andPagesizeLike(String value) {
            addCriterion("PAGESIZE like", value, "pagesize");
            return (Criteria) this;
        }

        public Criteria andPagesizeNotLike(String value) {
            addCriterion("PAGESIZE not like", value, "pagesize");
            return (Criteria) this;
        }

        public Criteria andPagesizeIn(List<String> values) {
            addCriterion("PAGESIZE in", values, "pagesize");
            return (Criteria) this;
        }

        public Criteria andPagesizeNotIn(List<String> values) {
            addCriterion("PAGESIZE not in", values, "pagesize");
            return (Criteria) this;
        }

        public Criteria andPagesizeBetween(String value1, String value2) {
            addCriterion("PAGESIZE between", value1, value2, "pagesize");
            return (Criteria) this;
        }

        public Criteria andPagesizeNotBetween(String value1, String value2) {
            addCriterion("PAGESIZE not between", value1, value2, "pagesize");
            return (Criteria) this;
        }

        public Criteria andInsidepagerIsNull() {
            addCriterion("INSIDEPAGER is null");
            return (Criteria) this;
        }

        public Criteria andInsidepagerIsNotNull() {
            addCriterion("INSIDEPAGER is not null");
            return (Criteria) this;
        }

        public Criteria andInsidepagerEqualTo(String value) {
            addCriterion("INSIDEPAGER =", value, "insidepager");
            return (Criteria) this;
        }

        public Criteria andInsidepagerNotEqualTo(String value) {
            addCriterion("INSIDEPAGER <>", value, "insidepager");
            return (Criteria) this;
        }

        public Criteria andInsidepagerGreaterThan(String value) {
            addCriterion("INSIDEPAGER >", value, "insidepager");
            return (Criteria) this;
        }

        public Criteria andInsidepagerGreaterThanOrEqualTo(String value) {
            addCriterion("INSIDEPAGER >=", value, "insidepager");
            return (Criteria) this;
        }

        public Criteria andInsidepagerLessThan(String value) {
            addCriterion("INSIDEPAGER <", value, "insidepager");
            return (Criteria) this;
        }

        public Criteria andInsidepagerLessThanOrEqualTo(String value) {
            addCriterion("INSIDEPAGER <=", value, "insidepager");
            return (Criteria) this;
        }

        public Criteria andInsidepagerLike(String value) {
            addCriterion("INSIDEPAGER like", value, "insidepager");
            return (Criteria) this;
        }

        public Criteria andInsidepagerNotLike(String value) {
            addCriterion("INSIDEPAGER not like", value, "insidepager");
            return (Criteria) this;
        }

        public Criteria andInsidepagerIn(List<String> values) {
            addCriterion("INSIDEPAGER in", values, "insidepager");
            return (Criteria) this;
        }

        public Criteria andInsidepagerNotIn(List<String> values) {
            addCriterion("INSIDEPAGER not in", values, "insidepager");
            return (Criteria) this;
        }

        public Criteria andInsidepagerBetween(String value1, String value2) {
            addCriterion("INSIDEPAGER between", value1, value2, "insidepager");
            return (Criteria) this;
        }

        public Criteria andInsidepagerNotBetween(String value1, String value2) {
            addCriterion("INSIDEPAGER not between", value1, value2, "insidepager");
            return (Criteria) this;
        }

        public Criteria andInsidepagerUniquefieldNameIsNull() {
            addCriterion("INSIDEPAGER_UNIQUEFIELD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInsidepagerUniquefieldNameIsNotNull() {
            addCriterion("INSIDEPAGER_UNIQUEFIELD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInsidepagerUniquefieldNameEqualTo(String value) {
            addCriterion("INSIDEPAGER_UNIQUEFIELD_NAME =", value, "insidepagerUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andInsidepagerUniquefieldNameNotEqualTo(String value) {
            addCriterion("INSIDEPAGER_UNIQUEFIELD_NAME <>", value, "insidepagerUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andInsidepagerUniquefieldNameGreaterThan(String value) {
            addCriterion("INSIDEPAGER_UNIQUEFIELD_NAME >", value, "insidepagerUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andInsidepagerUniquefieldNameGreaterThanOrEqualTo(String value) {
            addCriterion("INSIDEPAGER_UNIQUEFIELD_NAME >=", value, "insidepagerUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andInsidepagerUniquefieldNameLessThan(String value) {
            addCriterion("INSIDEPAGER_UNIQUEFIELD_NAME <", value, "insidepagerUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andInsidepagerUniquefieldNameLessThanOrEqualTo(String value) {
            addCriterion("INSIDEPAGER_UNIQUEFIELD_NAME <=", value, "insidepagerUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andInsidepagerUniquefieldNameLike(String value) {
            addCriterion("INSIDEPAGER_UNIQUEFIELD_NAME like", value, "insidepagerUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andInsidepagerUniquefieldNameNotLike(String value) {
            addCriterion("INSIDEPAGER_UNIQUEFIELD_NAME not like", value, "insidepagerUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andInsidepagerUniquefieldNameIn(List<String> values) {
            addCriterion("INSIDEPAGER_UNIQUEFIELD_NAME in", values, "insidepagerUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andInsidepagerUniquefieldNameNotIn(List<String> values) {
            addCriterion("INSIDEPAGER_UNIQUEFIELD_NAME not in", values, "insidepagerUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andInsidepagerUniquefieldNameBetween(String value1, String value2) {
            addCriterion("INSIDEPAGER_UNIQUEFIELD_NAME between", value1, value2, "insidepagerUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andInsidepagerUniquefieldNameNotBetween(String value1, String value2) {
            addCriterion("INSIDEPAGER_UNIQUEFIELD_NAME not between", value1, value2, "insidepagerUniquefieldName");
            return (Criteria) this;
        }

        public Criteria andObjectIfMultilanIsNull() {
            addCriterion("OBJECT_IF_MULTILAN is null");
            return (Criteria) this;
        }

        public Criteria andObjectIfMultilanIsNotNull() {
            addCriterion("OBJECT_IF_MULTILAN is not null");
            return (Criteria) this;
        }

        public Criteria andObjectIfMultilanEqualTo(String value) {
            addCriterion("OBJECT_IF_MULTILAN =", value, "objectIfMultilan");
            return (Criteria) this;
        }

        public Criteria andObjectIfMultilanNotEqualTo(String value) {
            addCriterion("OBJECT_IF_MULTILAN <>", value, "objectIfMultilan");
            return (Criteria) this;
        }

        public Criteria andObjectIfMultilanGreaterThan(String value) {
            addCriterion("OBJECT_IF_MULTILAN >", value, "objectIfMultilan");
            return (Criteria) this;
        }

        public Criteria andObjectIfMultilanGreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT_IF_MULTILAN >=", value, "objectIfMultilan");
            return (Criteria) this;
        }

        public Criteria andObjectIfMultilanLessThan(String value) {
            addCriterion("OBJECT_IF_MULTILAN <", value, "objectIfMultilan");
            return (Criteria) this;
        }

        public Criteria andObjectIfMultilanLessThanOrEqualTo(String value) {
            addCriterion("OBJECT_IF_MULTILAN <=", value, "objectIfMultilan");
            return (Criteria) this;
        }

        public Criteria andObjectIfMultilanLike(String value) {
            addCriterion("OBJECT_IF_MULTILAN like", value, "objectIfMultilan");
            return (Criteria) this;
        }

        public Criteria andObjectIfMultilanNotLike(String value) {
            addCriterion("OBJECT_IF_MULTILAN not like", value, "objectIfMultilan");
            return (Criteria) this;
        }

        public Criteria andObjectIfMultilanIn(List<String> values) {
            addCriterion("OBJECT_IF_MULTILAN in", values, "objectIfMultilan");
            return (Criteria) this;
        }

        public Criteria andObjectIfMultilanNotIn(List<String> values) {
            addCriterion("OBJECT_IF_MULTILAN not in", values, "objectIfMultilan");
            return (Criteria) this;
        }

        public Criteria andObjectIfMultilanBetween(String value1, String value2) {
            addCriterion("OBJECT_IF_MULTILAN between", value1, value2, "objectIfMultilan");
            return (Criteria) this;
        }

        public Criteria andObjectIfMultilanNotBetween(String value1, String value2) {
            addCriterion("OBJECT_IF_MULTILAN not between", value1, value2, "objectIfMultilan");
            return (Criteria) this;
        }

        public Criteria andLansIsNull() {
            addCriterion("LANS is null");
            return (Criteria) this;
        }

        public Criteria andLansIsNotNull() {
            addCriterion("LANS is not null");
            return (Criteria) this;
        }

        public Criteria andLansEqualTo(String value) {
            addCriterion("LANS =", value, "lans");
            return (Criteria) this;
        }

        public Criteria andLansNotEqualTo(String value) {
            addCriterion("LANS <>", value, "lans");
            return (Criteria) this;
        }

        public Criteria andLansGreaterThan(String value) {
            addCriterion("LANS >", value, "lans");
            return (Criteria) this;
        }

        public Criteria andLansGreaterThanOrEqualTo(String value) {
            addCriterion("LANS >=", value, "lans");
            return (Criteria) this;
        }

        public Criteria andLansLessThan(String value) {
            addCriterion("LANS <", value, "lans");
            return (Criteria) this;
        }

        public Criteria andLansLessThanOrEqualTo(String value) {
            addCriterion("LANS <=", value, "lans");
            return (Criteria) this;
        }

        public Criteria andLansLike(String value) {
            addCriterion("LANS like", value, "lans");
            return (Criteria) this;
        }

        public Criteria andLansNotLike(String value) {
            addCriterion("LANS not like", value, "lans");
            return (Criteria) this;
        }

        public Criteria andLansIn(List<String> values) {
            addCriterion("LANS in", values, "lans");
            return (Criteria) this;
        }

        public Criteria andLansNotIn(List<String> values) {
            addCriterion("LANS not in", values, "lans");
            return (Criteria) this;
        }

        public Criteria andLansBetween(String value1, String value2) {
            addCriterion("LANS between", value1, value2, "lans");
            return (Criteria) this;
        }

        public Criteria andLansNotBetween(String value1, String value2) {
            addCriterion("LANS not between", value1, value2, "lans");
            return (Criteria) this;
        }

        public Criteria andLanFieldIsNull() {
            addCriterion("LAN_FIELD is null");
            return (Criteria) this;
        }

        public Criteria andLanFieldIsNotNull() {
            addCriterion("LAN_FIELD is not null");
            return (Criteria) this;
        }

        public Criteria andLanFieldEqualTo(String value) {
            addCriterion("LAN_FIELD =", value, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldNotEqualTo(String value) {
            addCriterion("LAN_FIELD <>", value, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldGreaterThan(String value) {
            addCriterion("LAN_FIELD >", value, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldGreaterThanOrEqualTo(String value) {
            addCriterion("LAN_FIELD >=", value, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldLessThan(String value) {
            addCriterion("LAN_FIELD <", value, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldLessThanOrEqualTo(String value) {
            addCriterion("LAN_FIELD <=", value, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldLike(String value) {
            addCriterion("LAN_FIELD like", value, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldNotLike(String value) {
            addCriterion("LAN_FIELD not like", value, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldIn(List<String> values) {
            addCriterion("LAN_FIELD in", values, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldNotIn(List<String> values) {
            addCriterion("LAN_FIELD not in", values, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldBetween(String value1, String value2) {
            addCriterion("LAN_FIELD between", value1, value2, "lanField");
            return (Criteria) this;
        }

        public Criteria andLanFieldNotBetween(String value1, String value2) {
            addCriterion("LAN_FIELD not between", value1, value2, "lanField");
            return (Criteria) this;
        }

        public Criteria andMulvalfieldProcessorNameIsNull() {
            addCriterion("MULVALFIELD_PROCESSOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMulvalfieldProcessorNameIsNotNull() {
            addCriterion("MULVALFIELD_PROCESSOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMulvalfieldProcessorNameEqualTo(String value) {
            addCriterion("MULVALFIELD_PROCESSOR_NAME =", value, "mulvalfieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andMulvalfieldProcessorNameNotEqualTo(String value) {
            addCriterion("MULVALFIELD_PROCESSOR_NAME <>", value, "mulvalfieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andMulvalfieldProcessorNameGreaterThan(String value) {
            addCriterion("MULVALFIELD_PROCESSOR_NAME >", value, "mulvalfieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andMulvalfieldProcessorNameGreaterThanOrEqualTo(String value) {
            addCriterion("MULVALFIELD_PROCESSOR_NAME >=", value, "mulvalfieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andMulvalfieldProcessorNameLessThan(String value) {
            addCriterion("MULVALFIELD_PROCESSOR_NAME <", value, "mulvalfieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andMulvalfieldProcessorNameLessThanOrEqualTo(String value) {
            addCriterion("MULVALFIELD_PROCESSOR_NAME <=", value, "mulvalfieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andMulvalfieldProcessorNameLike(String value) {
            addCriterion("MULVALFIELD_PROCESSOR_NAME like", value, "mulvalfieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andMulvalfieldProcessorNameNotLike(String value) {
            addCriterion("MULVALFIELD_PROCESSOR_NAME not like", value, "mulvalfieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andMulvalfieldProcessorNameIn(List<String> values) {
            addCriterion("MULVALFIELD_PROCESSOR_NAME in", values, "mulvalfieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andMulvalfieldProcessorNameNotIn(List<String> values) {
            addCriterion("MULVALFIELD_PROCESSOR_NAME not in", values, "mulvalfieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andMulvalfieldProcessorNameBetween(String value1, String value2) {
            addCriterion("MULVALFIELD_PROCESSOR_NAME between", value1, value2, "mulvalfieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andMulvalfieldProcessorNameNotBetween(String value1, String value2) {
            addCriterion("MULVALFIELD_PROCESSOR_NAME not between", value1, value2, "mulvalfieldProcessorName");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeIsNull() {
            addCriterion("LAN_FIELD_FIELD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeIsNotNull() {
            addCriterion("LAN_FIELD_FIELD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeEqualTo(String value) {
            addCriterion("LAN_FIELD_FIELD_TYPE =", value, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeNotEqualTo(String value) {
            addCriterion("LAN_FIELD_FIELD_TYPE <>", value, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeGreaterThan(String value) {
            addCriterion("LAN_FIELD_FIELD_TYPE >", value, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeGreaterThanOrEqualTo(String value) {
            addCriterion("LAN_FIELD_FIELD_TYPE >=", value, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeLessThan(String value) {
            addCriterion("LAN_FIELD_FIELD_TYPE <", value, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeLessThanOrEqualTo(String value) {
            addCriterion("LAN_FIELD_FIELD_TYPE <=", value, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeLike(String value) {
            addCriterion("LAN_FIELD_FIELD_TYPE like", value, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeNotLike(String value) {
            addCriterion("LAN_FIELD_FIELD_TYPE not like", value, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeIn(List<String> values) {
            addCriterion("LAN_FIELD_FIELD_TYPE in", values, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeNotIn(List<String> values) {
            addCriterion("LAN_FIELD_FIELD_TYPE not in", values, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeBetween(String value1, String value2) {
            addCriterion("LAN_FIELD_FIELD_TYPE between", value1, value2, "lanFieldFieldType");
            return (Criteria) this;
        }

        public Criteria andLanFieldFieldTypeNotBetween(String value1, String value2) {
            addCriterion("LAN_FIELD_FIELD_TYPE not between", value1, value2, "lanFieldFieldType");
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