package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrdSubCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OrdSubCriteria() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("ORDER_ID like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("ORDER_ID not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
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

        public Criteria andOrderCodeIsNull() {
            addCriterion("ORDER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNotNull() {
            addCriterion("ORDER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeEqualTo(String value) {
            addCriterion("ORDER_CODE =", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotEqualTo(String value) {
            addCriterion("ORDER_CODE <>", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThan(String value) {
            addCriterion("ORDER_CODE >", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_CODE >=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThan(String value) {
            addCriterion("ORDER_CODE <", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("ORDER_CODE <=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLike(String value) {
            addCriterion("ORDER_CODE like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotLike(String value) {
            addCriterion("ORDER_CODE not like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIn(List<String> values) {
            addCriterion("ORDER_CODE in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotIn(List<String> values) {
            addCriterion("ORDER_CODE not in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeBetween(String value1, String value2) {
            addCriterion("ORDER_CODE between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotBetween(String value1, String value2) {
            addCriterion("ORDER_CODE not between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andCategoryCodeIsNull() {
            addCriterion("CATEGORY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCategoryCodeIsNotNull() {
            addCriterion("CATEGORY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryCodeEqualTo(String value) {
            addCriterion("CATEGORY_CODE =", value, "categoryCode");
            return (Criteria) this;
        }

        public Criteria andCategoryCodeNotEqualTo(String value) {
            addCriterion("CATEGORY_CODE <>", value, "categoryCode");
            return (Criteria) this;
        }

        public Criteria andCategoryCodeGreaterThan(String value) {
            addCriterion("CATEGORY_CODE >", value, "categoryCode");
            return (Criteria) this;
        }

        public Criteria andCategoryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CATEGORY_CODE >=", value, "categoryCode");
            return (Criteria) this;
        }

        public Criteria andCategoryCodeLessThan(String value) {
            addCriterion("CATEGORY_CODE <", value, "categoryCode");
            return (Criteria) this;
        }

        public Criteria andCategoryCodeLessThanOrEqualTo(String value) {
            addCriterion("CATEGORY_CODE <=", value, "categoryCode");
            return (Criteria) this;
        }

        public Criteria andCategoryCodeLike(String value) {
            addCriterion("CATEGORY_CODE like", value, "categoryCode");
            return (Criteria) this;
        }

        public Criteria andCategoryCodeNotLike(String value) {
            addCriterion("CATEGORY_CODE not like", value, "categoryCode");
            return (Criteria) this;
        }

        public Criteria andCategoryCodeIn(List<String> values) {
            addCriterion("CATEGORY_CODE in", values, "categoryCode");
            return (Criteria) this;
        }

        public Criteria andCategoryCodeNotIn(List<String> values) {
            addCriterion("CATEGORY_CODE not in", values, "categoryCode");
            return (Criteria) this;
        }

        public Criteria andCategoryCodeBetween(String value1, String value2) {
            addCriterion("CATEGORY_CODE between", value1, value2, "categoryCode");
            return (Criteria) this;
        }

        public Criteria andCategoryCodeNotBetween(String value1, String value2) {
            addCriterion("CATEGORY_CODE not between", value1, value2, "categoryCode");
            return (Criteria) this;
        }

        public Criteria andGdsIdIsNull() {
            addCriterion("GDS_ID is null");
            return (Criteria) this;
        }

        public Criteria andGdsIdIsNotNull() {
            addCriterion("GDS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGdsIdEqualTo(Long value) {
            addCriterion("GDS_ID =", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdNotEqualTo(Long value) {
            addCriterion("GDS_ID <>", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdGreaterThan(Long value) {
            addCriterion("GDS_ID >", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("GDS_ID >=", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdLessThan(Long value) {
            addCriterion("GDS_ID <", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdLessThanOrEqualTo(Long value) {
            addCriterion("GDS_ID <=", value, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdIn(List<Long> values) {
            addCriterion("GDS_ID in", values, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdNotIn(List<Long> values) {
            addCriterion("GDS_ID not in", values, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdBetween(Long value1, Long value2) {
            addCriterion("GDS_ID between", value1, value2, "gdsId");
            return (Criteria) this;
        }

        public Criteria andGdsIdNotBetween(Long value1, Long value2) {
            addCriterion("GDS_ID not between", value1, value2, "gdsId");
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

        public Criteria andSkuIdIsNull() {
            addCriterion("SKU_ID is null");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNotNull() {
            addCriterion("SKU_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSkuIdEqualTo(Long value) {
            addCriterion("SKU_ID =", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotEqualTo(Long value) {
            addCriterion("SKU_ID <>", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThan(Long value) {
            addCriterion("SKU_ID >", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SKU_ID >=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThan(Long value) {
            addCriterion("SKU_ID <", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThanOrEqualTo(Long value) {
            addCriterion("SKU_ID <=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIn(List<Long> values) {
            addCriterion("SKU_ID in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotIn(List<Long> values) {
            addCriterion("SKU_ID not in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdBetween(Long value1, Long value2) {
            addCriterion("SKU_ID between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotBetween(Long value1, Long value2) {
            addCriterion("SKU_ID not between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuHisIdIsNull() {
            addCriterion("SKU_HIS_ID is null");
            return (Criteria) this;
        }

        public Criteria andSkuHisIdIsNotNull() {
            addCriterion("SKU_HIS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSkuHisIdEqualTo(Long value) {
            addCriterion("SKU_HIS_ID =", value, "skuHisId");
            return (Criteria) this;
        }

        public Criteria andSkuHisIdNotEqualTo(Long value) {
            addCriterion("SKU_HIS_ID <>", value, "skuHisId");
            return (Criteria) this;
        }

        public Criteria andSkuHisIdGreaterThan(Long value) {
            addCriterion("SKU_HIS_ID >", value, "skuHisId");
            return (Criteria) this;
        }

        public Criteria andSkuHisIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SKU_HIS_ID >=", value, "skuHisId");
            return (Criteria) this;
        }

        public Criteria andSkuHisIdLessThan(Long value) {
            addCriterion("SKU_HIS_ID <", value, "skuHisId");
            return (Criteria) this;
        }

        public Criteria andSkuHisIdLessThanOrEqualTo(Long value) {
            addCriterion("SKU_HIS_ID <=", value, "skuHisId");
            return (Criteria) this;
        }

        public Criteria andSkuHisIdIn(List<Long> values) {
            addCriterion("SKU_HIS_ID in", values, "skuHisId");
            return (Criteria) this;
        }

        public Criteria andSkuHisIdNotIn(List<Long> values) {
            addCriterion("SKU_HIS_ID not in", values, "skuHisId");
            return (Criteria) this;
        }

        public Criteria andSkuHisIdBetween(Long value1, Long value2) {
            addCriterion("SKU_HIS_ID between", value1, value2, "skuHisId");
            return (Criteria) this;
        }

        public Criteria andSkuHisIdNotBetween(Long value1, Long value2) {
            addCriterion("SKU_HIS_ID not between", value1, value2, "skuHisId");
            return (Criteria) this;
        }

        public Criteria andSkuInfoIsNull() {
            addCriterion("SKU_INFO is null");
            return (Criteria) this;
        }

        public Criteria andSkuInfoIsNotNull() {
            addCriterion("SKU_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andSkuInfoEqualTo(String value) {
            addCriterion("SKU_INFO =", value, "skuInfo");
            return (Criteria) this;
        }

        public Criteria andSkuInfoNotEqualTo(String value) {
            addCriterion("SKU_INFO <>", value, "skuInfo");
            return (Criteria) this;
        }

        public Criteria andSkuInfoGreaterThan(String value) {
            addCriterion("SKU_INFO >", value, "skuInfo");
            return (Criteria) this;
        }

        public Criteria andSkuInfoGreaterThanOrEqualTo(String value) {
            addCriterion("SKU_INFO >=", value, "skuInfo");
            return (Criteria) this;
        }

        public Criteria andSkuInfoLessThan(String value) {
            addCriterion("SKU_INFO <", value, "skuInfo");
            return (Criteria) this;
        }

        public Criteria andSkuInfoLessThanOrEqualTo(String value) {
            addCriterion("SKU_INFO <=", value, "skuInfo");
            return (Criteria) this;
        }

        public Criteria andSkuInfoLike(String value) {
            addCriterion("SKU_INFO like", value, "skuInfo");
            return (Criteria) this;
        }

        public Criteria andSkuInfoNotLike(String value) {
            addCriterion("SKU_INFO not like", value, "skuInfo");
            return (Criteria) this;
        }

        public Criteria andSkuInfoIn(List<String> values) {
            addCriterion("SKU_INFO in", values, "skuInfo");
            return (Criteria) this;
        }

        public Criteria andSkuInfoNotIn(List<String> values) {
            addCriterion("SKU_INFO not in", values, "skuInfo");
            return (Criteria) this;
        }

        public Criteria andSkuInfoBetween(String value1, String value2) {
            addCriterion("SKU_INFO between", value1, value2, "skuInfo");
            return (Criteria) this;
        }

        public Criteria andSkuInfoNotBetween(String value1, String value2) {
            addCriterion("SKU_INFO not between", value1, value2, "skuInfo");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIsNull() {
            addCriterion("GROUP_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIsNotNull() {
            addCriterion("GROUP_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeEqualTo(String value) {
            addCriterion("GROUP_TYPE =", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotEqualTo(String value) {
            addCriterion("GROUP_TYPE <>", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeGreaterThan(String value) {
            addCriterion("GROUP_TYPE >", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_TYPE >=", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLessThan(String value) {
            addCriterion("GROUP_TYPE <", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLessThanOrEqualTo(String value) {
            addCriterion("GROUP_TYPE <=", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLike(String value) {
            addCriterion("GROUP_TYPE like", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotLike(String value) {
            addCriterion("GROUP_TYPE not like", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIn(List<String> values) {
            addCriterion("GROUP_TYPE in", values, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotIn(List<String> values) {
            addCriterion("GROUP_TYPE not in", values, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeBetween(String value1, String value2) {
            addCriterion("GROUP_TYPE between", value1, value2, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotBetween(String value1, String value2) {
            addCriterion("GROUP_TYPE not between", value1, value2, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupDetailIsNull() {
            addCriterion("GROUP_DETAIL is null");
            return (Criteria) this;
        }

        public Criteria andGroupDetailIsNotNull() {
            addCriterion("GROUP_DETAIL is not null");
            return (Criteria) this;
        }

        public Criteria andGroupDetailEqualTo(String value) {
            addCriterion("GROUP_DETAIL =", value, "groupDetail");
            return (Criteria) this;
        }

        public Criteria andGroupDetailNotEqualTo(String value) {
            addCriterion("GROUP_DETAIL <>", value, "groupDetail");
            return (Criteria) this;
        }

        public Criteria andGroupDetailGreaterThan(String value) {
            addCriterion("GROUP_DETAIL >", value, "groupDetail");
            return (Criteria) this;
        }

        public Criteria andGroupDetailGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_DETAIL >=", value, "groupDetail");
            return (Criteria) this;
        }

        public Criteria andGroupDetailLessThan(String value) {
            addCriterion("GROUP_DETAIL <", value, "groupDetail");
            return (Criteria) this;
        }

        public Criteria andGroupDetailLessThanOrEqualTo(String value) {
            addCriterion("GROUP_DETAIL <=", value, "groupDetail");
            return (Criteria) this;
        }

        public Criteria andGroupDetailLike(String value) {
            addCriterion("GROUP_DETAIL like", value, "groupDetail");
            return (Criteria) this;
        }

        public Criteria andGroupDetailNotLike(String value) {
            addCriterion("GROUP_DETAIL not like", value, "groupDetail");
            return (Criteria) this;
        }

        public Criteria andGroupDetailIn(List<String> values) {
            addCriterion("GROUP_DETAIL in", values, "groupDetail");
            return (Criteria) this;
        }

        public Criteria andGroupDetailNotIn(List<String> values) {
            addCriterion("GROUP_DETAIL not in", values, "groupDetail");
            return (Criteria) this;
        }

        public Criteria andGroupDetailBetween(String value1, String value2) {
            addCriterion("GROUP_DETAIL between", value1, value2, "groupDetail");
            return (Criteria) this;
        }

        public Criteria andGroupDetailNotBetween(String value1, String value2) {
            addCriterion("GROUP_DETAIL not between", value1, value2, "groupDetail");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIsNull() {
            addCriterion("ORDER_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIsNotNull() {
            addCriterion("ORDER_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountEqualTo(Long value) {
            addCriterion("ORDER_AMOUNT =", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotEqualTo(Long value) {
            addCriterion("ORDER_AMOUNT <>", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThan(Long value) {
            addCriterion("ORDER_AMOUNT >", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDER_AMOUNT >=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThan(Long value) {
            addCriterion("ORDER_AMOUNT <", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThanOrEqualTo(Long value) {
            addCriterion("ORDER_AMOUNT <=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIn(List<Long> values) {
            addCriterion("ORDER_AMOUNT in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotIn(List<Long> values) {
            addCriterion("ORDER_AMOUNT not in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountBetween(Long value1, Long value2) {
            addCriterion("ORDER_AMOUNT between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotBetween(Long value1, Long value2) {
            addCriterion("ORDER_AMOUNT not between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andPriceTypeIsNull() {
            addCriterion("PRICE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPriceTypeIsNotNull() {
            addCriterion("PRICE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPriceTypeEqualTo(String value) {
            addCriterion("PRICE_TYPE =", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeNotEqualTo(String value) {
            addCriterion("PRICE_TYPE <>", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeGreaterThan(String value) {
            addCriterion("PRICE_TYPE >", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PRICE_TYPE >=", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeLessThan(String value) {
            addCriterion("PRICE_TYPE <", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeLessThanOrEqualTo(String value) {
            addCriterion("PRICE_TYPE <=", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeLike(String value) {
            addCriterion("PRICE_TYPE like", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeNotLike(String value) {
            addCriterion("PRICE_TYPE not like", value, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeIn(List<String> values) {
            addCriterion("PRICE_TYPE in", values, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeNotIn(List<String> values) {
            addCriterion("PRICE_TYPE not in", values, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeBetween(String value1, String value2) {
            addCriterion("PRICE_TYPE between", value1, value2, "priceType");
            return (Criteria) this;
        }

        public Criteria andPriceTypeNotBetween(String value1, String value2) {
            addCriterion("PRICE_TYPE not between", value1, value2, "priceType");
            return (Criteria) this;
        }

        public Criteria andBasePriceIsNull() {
            addCriterion("BASE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andBasePriceIsNotNull() {
            addCriterion("BASE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andBasePriceEqualTo(Long value) {
            addCriterion("BASE_PRICE =", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceNotEqualTo(Long value) {
            addCriterion("BASE_PRICE <>", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceGreaterThan(Long value) {
            addCriterion("BASE_PRICE >", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("BASE_PRICE >=", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceLessThan(Long value) {
            addCriterion("BASE_PRICE <", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceLessThanOrEqualTo(Long value) {
            addCriterion("BASE_PRICE <=", value, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceIn(List<Long> values) {
            addCriterion("BASE_PRICE in", values, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceNotIn(List<Long> values) {
            addCriterion("BASE_PRICE not in", values, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceBetween(Long value1, Long value2) {
            addCriterion("BASE_PRICE between", value1, value2, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBasePriceNotBetween(Long value1, Long value2) {
            addCriterion("BASE_PRICE not between", value1, value2, "basePrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceIsNull() {
            addCriterion("BUY_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andBuyPriceIsNotNull() {
            addCriterion("BUY_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andBuyPriceEqualTo(Long value) {
            addCriterion("BUY_PRICE =", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceNotEqualTo(Long value) {
            addCriterion("BUY_PRICE <>", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceGreaterThan(Long value) {
            addCriterion("BUY_PRICE >", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("BUY_PRICE >=", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceLessThan(Long value) {
            addCriterion("BUY_PRICE <", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceLessThanOrEqualTo(Long value) {
            addCriterion("BUY_PRICE <=", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceIn(List<Long> values) {
            addCriterion("BUY_PRICE in", values, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceNotIn(List<Long> values) {
            addCriterion("BUY_PRICE not in", values, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceBetween(Long value1, Long value2) {
            addCriterion("BUY_PRICE between", value1, value2, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceNotBetween(Long value1, Long value2) {
            addCriterion("BUY_PRICE not between", value1, value2, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyIsNull() {
            addCriterion("ORDER_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyIsNotNull() {
            addCriterion("ORDER_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyEqualTo(Long value) {
            addCriterion("ORDER_MONEY =", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyNotEqualTo(Long value) {
            addCriterion("ORDER_MONEY <>", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyGreaterThan(Long value) {
            addCriterion("ORDER_MONEY >", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDER_MONEY >=", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyLessThan(Long value) {
            addCriterion("ORDER_MONEY <", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyLessThanOrEqualTo(Long value) {
            addCriterion("ORDER_MONEY <=", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyIn(List<Long> values) {
            addCriterion("ORDER_MONEY in", values, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyNotIn(List<Long> values) {
            addCriterion("ORDER_MONEY not in", values, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyBetween(Long value1, Long value2) {
            addCriterion("ORDER_MONEY between", value1, value2, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyNotBetween(Long value1, Long value2) {
            addCriterion("ORDER_MONEY not between", value1, value2, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyIsNull() {
            addCriterion("REAL_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andRealMoneyIsNotNull() {
            addCriterion("REAL_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andRealMoneyEqualTo(Long value) {
            addCriterion("REAL_MONEY =", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyNotEqualTo(Long value) {
            addCriterion("REAL_MONEY <>", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyGreaterThan(Long value) {
            addCriterion("REAL_MONEY >", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("REAL_MONEY >=", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyLessThan(Long value) {
            addCriterion("REAL_MONEY <", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyLessThanOrEqualTo(Long value) {
            addCriterion("REAL_MONEY <=", value, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyIn(List<Long> values) {
            addCriterion("REAL_MONEY in", values, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyNotIn(List<Long> values) {
            addCriterion("REAL_MONEY not in", values, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyBetween(Long value1, Long value2) {
            addCriterion("REAL_MONEY between", value1, value2, "realMoney");
            return (Criteria) this;
        }

        public Criteria andRealMoneyNotBetween(Long value1, Long value2) {
            addCriterion("REAL_MONEY not between", value1, value2, "realMoney");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNull() {
            addCriterion("ORDER_TIME is null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNotNull() {
            addCriterion("ORDER_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeEqualTo(Timestamp value) {
            addCriterion("ORDER_TIME =", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotEqualTo(Timestamp value) {
            addCriterion("ORDER_TIME <>", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThan(Timestamp value) {
            addCriterion("ORDER_TIME >", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("ORDER_TIME >=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThan(Timestamp value) {
            addCriterion("ORDER_TIME <", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("ORDER_TIME <=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIn(List<Timestamp> values) {
            addCriterion("ORDER_TIME in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotIn(List<Timestamp> values) {
            addCriterion("ORDER_TIME not in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ORDER_TIME between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ORDER_TIME not between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNull() {
            addCriterion("STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNotNull() {
            addCriterion("STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStaffIdEqualTo(Long value) {
            addCriterion("STAFF_ID =", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotEqualTo(Long value) {
            addCriterion("STAFF_ID <>", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThan(Long value) {
            addCriterion("STAFF_ID >", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThanOrEqualTo(Long value) {
            addCriterion("STAFF_ID >=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThan(Long value) {
            addCriterion("STAFF_ID <", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThanOrEqualTo(Long value) {
            addCriterion("STAFF_ID <=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdIn(List<Long> values) {
            addCriterion("STAFF_ID in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotIn(List<Long> values) {
            addCriterion("STAFF_ID not in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdBetween(Long value1, Long value2) {
            addCriterion("STAFF_ID between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotBetween(Long value1, Long value2) {
            addCriterion("STAFF_ID not between", value1, value2, "staffId");
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

        public Criteria andProxyShopIdIsNull() {
            addCriterion("PROXY_SHOP_ID is null");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdIsNotNull() {
            addCriterion("PROXY_SHOP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdEqualTo(Long value) {
            addCriterion("PROXY_SHOP_ID =", value, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdNotEqualTo(Long value) {
            addCriterion("PROXY_SHOP_ID <>", value, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdGreaterThan(Long value) {
            addCriterion("PROXY_SHOP_ID >", value, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROXY_SHOP_ID >=", value, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdLessThan(Long value) {
            addCriterion("PROXY_SHOP_ID <", value, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdLessThanOrEqualTo(Long value) {
            addCriterion("PROXY_SHOP_ID <=", value, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdIn(List<Long> values) {
            addCriterion("PROXY_SHOP_ID in", values, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdNotIn(List<Long> values) {
            addCriterion("PROXY_SHOP_ID not in", values, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdBetween(Long value1, Long value2) {
            addCriterion("PROXY_SHOP_ID between", value1, value2, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdNotBetween(Long value1, Long value2) {
            addCriterion("PROXY_SHOP_ID not between", value1, value2, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusIsNull() {
            addCriterion("DELIVER_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusIsNotNull() {
            addCriterion("DELIVER_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusEqualTo(String value) {
            addCriterion("DELIVER_STATUS =", value, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusNotEqualTo(String value) {
            addCriterion("DELIVER_STATUS <>", value, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusGreaterThan(String value) {
            addCriterion("DELIVER_STATUS >", value, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusGreaterThanOrEqualTo(String value) {
            addCriterion("DELIVER_STATUS >=", value, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusLessThan(String value) {
            addCriterion("DELIVER_STATUS <", value, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusLessThanOrEqualTo(String value) {
            addCriterion("DELIVER_STATUS <=", value, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusLike(String value) {
            addCriterion("DELIVER_STATUS like", value, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusNotLike(String value) {
            addCriterion("DELIVER_STATUS not like", value, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusIn(List<String> values) {
            addCriterion("DELIVER_STATUS in", values, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusNotIn(List<String> values) {
            addCriterion("DELIVER_STATUS not in", values, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusBetween(String value1, String value2) {
            addCriterion("DELIVER_STATUS between", value1, value2, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andDeliverStatusNotBetween(String value1, String value2) {
            addCriterion("DELIVER_STATUS not between", value1, value2, "deliverStatus");
            return (Criteria) this;
        }

        public Criteria andRemainAmountIsNull() {
            addCriterion("REMAIN_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andRemainAmountIsNotNull() {
            addCriterion("REMAIN_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRemainAmountEqualTo(Long value) {
            addCriterion("REMAIN_AMOUNT =", value, "remainAmount");
            return (Criteria) this;
        }

        public Criteria andRemainAmountNotEqualTo(Long value) {
            addCriterion("REMAIN_AMOUNT <>", value, "remainAmount");
            return (Criteria) this;
        }

        public Criteria andRemainAmountGreaterThan(Long value) {
            addCriterion("REMAIN_AMOUNT >", value, "remainAmount");
            return (Criteria) this;
        }

        public Criteria andRemainAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("REMAIN_AMOUNT >=", value, "remainAmount");
            return (Criteria) this;
        }

        public Criteria andRemainAmountLessThan(Long value) {
            addCriterion("REMAIN_AMOUNT <", value, "remainAmount");
            return (Criteria) this;
        }

        public Criteria andRemainAmountLessThanOrEqualTo(Long value) {
            addCriterion("REMAIN_AMOUNT <=", value, "remainAmount");
            return (Criteria) this;
        }

        public Criteria andRemainAmountIn(List<Long> values) {
            addCriterion("REMAIN_AMOUNT in", values, "remainAmount");
            return (Criteria) this;
        }

        public Criteria andRemainAmountNotIn(List<Long> values) {
            addCriterion("REMAIN_AMOUNT not in", values, "remainAmount");
            return (Criteria) this;
        }

        public Criteria andRemainAmountBetween(Long value1, Long value2) {
            addCriterion("REMAIN_AMOUNT between", value1, value2, "remainAmount");
            return (Criteria) this;
        }

        public Criteria andRemainAmountNotBetween(Long value1, Long value2) {
            addCriterion("REMAIN_AMOUNT not between", value1, value2, "remainAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountIsNull() {
            addCriterion("DELIVER_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountIsNotNull() {
            addCriterion("DELIVER_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountEqualTo(Long value) {
            addCriterion("DELIVER_AMOUNT =", value, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountNotEqualTo(Long value) {
            addCriterion("DELIVER_AMOUNT <>", value, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountGreaterThan(Long value) {
            addCriterion("DELIVER_AMOUNT >", value, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("DELIVER_AMOUNT >=", value, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountLessThan(Long value) {
            addCriterion("DELIVER_AMOUNT <", value, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountLessThanOrEqualTo(Long value) {
            addCriterion("DELIVER_AMOUNT <=", value, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountIn(List<Long> values) {
            addCriterion("DELIVER_AMOUNT in", values, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountNotIn(List<Long> values) {
            addCriterion("DELIVER_AMOUNT not in", values, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountBetween(Long value1, Long value2) {
            addCriterion("DELIVER_AMOUNT between", value1, value2, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDeliverAmountNotBetween(Long value1, Long value2) {
            addCriterion("DELIVER_AMOUNT not between", value1, value2, "deliverAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIsNull() {
            addCriterion("DISCOUNT_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIsNotNull() {
            addCriterion("DISCOUNT_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceEqualTo(Long value) {
            addCriterion("DISCOUNT_PRICE =", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotEqualTo(Long value) {
            addCriterion("DISCOUNT_PRICE <>", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceGreaterThan(Long value) {
            addCriterion("DISCOUNT_PRICE >", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("DISCOUNT_PRICE >=", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceLessThan(Long value) {
            addCriterion("DISCOUNT_PRICE <", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceLessThanOrEqualTo(Long value) {
            addCriterion("DISCOUNT_PRICE <=", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIn(List<Long> values) {
            addCriterion("DISCOUNT_PRICE in", values, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotIn(List<Long> values) {
            addCriterion("DISCOUNT_PRICE not in", values, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceBetween(Long value1, Long value2) {
            addCriterion("DISCOUNT_PRICE between", value1, value2, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotBetween(Long value1, Long value2) {
            addCriterion("DISCOUNT_PRICE not between", value1, value2, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyIsNull() {
            addCriterion("DISCOUNT_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyIsNotNull() {
            addCriterion("DISCOUNT_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyEqualTo(Long value) {
            addCriterion("DISCOUNT_MONEY =", value, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyNotEqualTo(Long value) {
            addCriterion("DISCOUNT_MONEY <>", value, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyGreaterThan(Long value) {
            addCriterion("DISCOUNT_MONEY >", value, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("DISCOUNT_MONEY >=", value, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyLessThan(Long value) {
            addCriterion("DISCOUNT_MONEY <", value, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyLessThanOrEqualTo(Long value) {
            addCriterion("DISCOUNT_MONEY <=", value, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyIn(List<Long> values) {
            addCriterion("DISCOUNT_MONEY in", values, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyNotIn(List<Long> values) {
            addCriterion("DISCOUNT_MONEY not in", values, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyBetween(Long value1, Long value2) {
            addCriterion("DISCOUNT_MONEY between", value1, value2, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andDiscountMoneyNotBetween(Long value1, Long value2) {
            addCriterion("DISCOUNT_MONEY not between", value1, value2, "discountMoney");
            return (Criteria) this;
        }

        public Criteria andReduceMoneyIsNull() {
            addCriterion("REDUCE_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andReduceMoneyIsNotNull() {
            addCriterion("REDUCE_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andReduceMoneyEqualTo(Long value) {
            addCriterion("REDUCE_MONEY =", value, "reduceMoney");
            return (Criteria) this;
        }

        public Criteria andReduceMoneyNotEqualTo(Long value) {
            addCriterion("REDUCE_MONEY <>", value, "reduceMoney");
            return (Criteria) this;
        }

        public Criteria andReduceMoneyGreaterThan(Long value) {
            addCriterion("REDUCE_MONEY >", value, "reduceMoney");
            return (Criteria) this;
        }

        public Criteria andReduceMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("REDUCE_MONEY >=", value, "reduceMoney");
            return (Criteria) this;
        }

        public Criteria andReduceMoneyLessThan(Long value) {
            addCriterion("REDUCE_MONEY <", value, "reduceMoney");
            return (Criteria) this;
        }

        public Criteria andReduceMoneyLessThanOrEqualTo(Long value) {
            addCriterion("REDUCE_MONEY <=", value, "reduceMoney");
            return (Criteria) this;
        }

        public Criteria andReduceMoneyIn(List<Long> values) {
            addCriterion("REDUCE_MONEY in", values, "reduceMoney");
            return (Criteria) this;
        }

        public Criteria andReduceMoneyNotIn(List<Long> values) {
            addCriterion("REDUCE_MONEY not in", values, "reduceMoney");
            return (Criteria) this;
        }

        public Criteria andReduceMoneyBetween(Long value1, Long value2) {
            addCriterion("REDUCE_MONEY between", value1, value2, "reduceMoney");
            return (Criteria) this;
        }

        public Criteria andReduceMoneyNotBetween(Long value1, Long value2) {
            addCriterion("REDUCE_MONEY not between", value1, value2, "reduceMoney");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("SCORE is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Long value) {
            addCriterion("SCORE =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Long value) {
            addCriterion("SCORE <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Long value) {
            addCriterion("SCORE >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("SCORE >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Long value) {
            addCriterion("SCORE <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Long value) {
            addCriterion("SCORE <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Long> values) {
            addCriterion("SCORE in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Long> values) {
            addCriterion("SCORE not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Long value1, Long value2) {
            addCriterion("SCORE between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Long value1, Long value2) {
            addCriterion("SCORE not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andOrderSubScoreIsNull() {
            addCriterion("ORDER_SUB_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andOrderSubScoreIsNotNull() {
            addCriterion("ORDER_SUB_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSubScoreEqualTo(Long value) {
            addCriterion("ORDER_SUB_SCORE =", value, "orderSubScore");
            return (Criteria) this;
        }

        public Criteria andOrderSubScoreNotEqualTo(Long value) {
            addCriterion("ORDER_SUB_SCORE <>", value, "orderSubScore");
            return (Criteria) this;
        }

        public Criteria andOrderSubScoreGreaterThan(Long value) {
            addCriterion("ORDER_SUB_SCORE >", value, "orderSubScore");
            return (Criteria) this;
        }

        public Criteria andOrderSubScoreGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDER_SUB_SCORE >=", value, "orderSubScore");
            return (Criteria) this;
        }

        public Criteria andOrderSubScoreLessThan(Long value) {
            addCriterion("ORDER_SUB_SCORE <", value, "orderSubScore");
            return (Criteria) this;
        }

        public Criteria andOrderSubScoreLessThanOrEqualTo(Long value) {
            addCriterion("ORDER_SUB_SCORE <=", value, "orderSubScore");
            return (Criteria) this;
        }

        public Criteria andOrderSubScoreIn(List<Long> values) {
            addCriterion("ORDER_SUB_SCORE in", values, "orderSubScore");
            return (Criteria) this;
        }

        public Criteria andOrderSubScoreNotIn(List<Long> values) {
            addCriterion("ORDER_SUB_SCORE not in", values, "orderSubScore");
            return (Criteria) this;
        }

        public Criteria andOrderSubScoreBetween(Long value1, Long value2) {
            addCriterion("ORDER_SUB_SCORE between", value1, value2, "orderSubScore");
            return (Criteria) this;
        }

        public Criteria andOrderSubScoreNotBetween(Long value1, Long value2) {
            addCriterion("ORDER_SUB_SCORE not between", value1, value2, "orderSubScore");
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

        public Criteria andRepCodeIsNull() {
            addCriterion("REP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRepCodeIsNotNull() {
            addCriterion("REP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRepCodeEqualTo(Long value) {
            addCriterion("REP_CODE =", value, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeNotEqualTo(Long value) {
            addCriterion("REP_CODE <>", value, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeGreaterThan(Long value) {
            addCriterion("REP_CODE >", value, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("REP_CODE >=", value, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeLessThan(Long value) {
            addCriterion("REP_CODE <", value, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeLessThanOrEqualTo(Long value) {
            addCriterion("REP_CODE <=", value, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeIn(List<Long> values) {
            addCriterion("REP_CODE in", values, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeNotIn(List<Long> values) {
            addCriterion("REP_CODE not in", values, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeBetween(Long value1, Long value2) {
            addCriterion("REP_CODE between", value1, value2, "repCode");
            return (Criteria) this;
        }

        public Criteria andRepCodeNotBetween(Long value1, Long value2) {
            addCriterion("REP_CODE not between", value1, value2, "repCode");
            return (Criteria) this;
        }

        public Criteria andStockIdIsNull() {
            addCriterion("STOCK_ID is null");
            return (Criteria) this;
        }

        public Criteria andStockIdIsNotNull() {
            addCriterion("STOCK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStockIdEqualTo(Long value) {
            addCriterion("STOCK_ID =", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdNotEqualTo(Long value) {
            addCriterion("STOCK_ID <>", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdGreaterThan(Long value) {
            addCriterion("STOCK_ID >", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdGreaterThanOrEqualTo(Long value) {
            addCriterion("STOCK_ID >=", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdLessThan(Long value) {
            addCriterion("STOCK_ID <", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdLessThanOrEqualTo(Long value) {
            addCriterion("STOCK_ID <=", value, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdIn(List<Long> values) {
            addCriterion("STOCK_ID in", values, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdNotIn(List<Long> values) {
            addCriterion("STOCK_ID not in", values, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdBetween(Long value1, Long value2) {
            addCriterion("STOCK_ID between", value1, value2, "stockId");
            return (Criteria) this;
        }

        public Criteria andStockIdNotBetween(Long value1, Long value2) {
            addCriterion("STOCK_ID not between", value1, value2, "stockId");
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

        public Criteria andSysTypeIsNull() {
            addCriterion("SYS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSysTypeIsNotNull() {
            addCriterion("SYS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSysTypeEqualTo(String value) {
            addCriterion("SYS_TYPE =", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeNotEqualTo(String value) {
            addCriterion("SYS_TYPE <>", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeGreaterThan(String value) {
            addCriterion("SYS_TYPE >", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_TYPE >=", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeLessThan(String value) {
            addCriterion("SYS_TYPE <", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeLessThanOrEqualTo(String value) {
            addCriterion("SYS_TYPE <=", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeLike(String value) {
            addCriterion("SYS_TYPE like", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeNotLike(String value) {
            addCriterion("SYS_TYPE not like", value, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeIn(List<String> values) {
            addCriterion("SYS_TYPE in", values, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeNotIn(List<String> values) {
            addCriterion("SYS_TYPE not in", values, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeBetween(String value1, String value2) {
            addCriterion("SYS_TYPE between", value1, value2, "sysType");
            return (Criteria) this;
        }

        public Criteria andSysTypeNotBetween(String value1, String value2) {
            addCriterion("SYS_TYPE not between", value1, value2, "sysType");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIsNull() {
            addCriterion("GDS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIsNotNull() {
            addCriterion("GDS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andGdsTypeEqualTo(Long value) {
            addCriterion("GDS_TYPE =", value, "gdsType");
            return (Criteria) this;
        }

        public Criteria andGdsTypeNotEqualTo(Long value) {
            addCriterion("GDS_TYPE <>", value, "gdsType");
            return (Criteria) this;
        }

        public Criteria andGdsTypeGreaterThan(Long value) {
            addCriterion("GDS_TYPE >", value, "gdsType");
            return (Criteria) this;
        }

        public Criteria andGdsTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("GDS_TYPE >=", value, "gdsType");
            return (Criteria) this;
        }

        public Criteria andGdsTypeLessThan(Long value) {
            addCriterion("GDS_TYPE <", value, "gdsType");
            return (Criteria) this;
        }

        public Criteria andGdsTypeLessThanOrEqualTo(Long value) {
            addCriterion("GDS_TYPE <=", value, "gdsType");
            return (Criteria) this;
        }

        public Criteria andGdsTypeIn(List<Long> values) {
            addCriterion("GDS_TYPE in", values, "gdsType");
            return (Criteria) this;
        }

        public Criteria andGdsTypeNotIn(List<Long> values) {
            addCriterion("GDS_TYPE not in", values, "gdsType");
            return (Criteria) this;
        }

        public Criteria andGdsTypeBetween(Long value1, Long value2) {
            addCriterion("GDS_TYPE between", value1, value2, "gdsType");
            return (Criteria) this;
        }

        public Criteria andGdsTypeNotBetween(Long value1, Long value2) {
            addCriterion("GDS_TYPE not between", value1, value2, "gdsType");
            return (Criteria) this;
        }

        public Criteria andEvalFlagIsNull() {
            addCriterion("EVAL_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andEvalFlagIsNotNull() {
            addCriterion("EVAL_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andEvalFlagEqualTo(String value) {
            addCriterion("EVAL_FLAG =", value, "evalFlag");
            return (Criteria) this;
        }

        public Criteria andEvalFlagNotEqualTo(String value) {
            addCriterion("EVAL_FLAG <>", value, "evalFlag");
            return (Criteria) this;
        }

        public Criteria andEvalFlagGreaterThan(String value) {
            addCriterion("EVAL_FLAG >", value, "evalFlag");
            return (Criteria) this;
        }

        public Criteria andEvalFlagGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_FLAG >=", value, "evalFlag");
            return (Criteria) this;
        }

        public Criteria andEvalFlagLessThan(String value) {
            addCriterion("EVAL_FLAG <", value, "evalFlag");
            return (Criteria) this;
        }

        public Criteria andEvalFlagLessThanOrEqualTo(String value) {
            addCriterion("EVAL_FLAG <=", value, "evalFlag");
            return (Criteria) this;
        }

        public Criteria andEvalFlagLike(String value) {
            addCriterion("EVAL_FLAG like", value, "evalFlag");
            return (Criteria) this;
        }

        public Criteria andEvalFlagNotLike(String value) {
            addCriterion("EVAL_FLAG not like", value, "evalFlag");
            return (Criteria) this;
        }

        public Criteria andEvalFlagIn(List<String> values) {
            addCriterion("EVAL_FLAG in", values, "evalFlag");
            return (Criteria) this;
        }

        public Criteria andEvalFlagNotIn(List<String> values) {
            addCriterion("EVAL_FLAG not in", values, "evalFlag");
            return (Criteria) this;
        }

        public Criteria andEvalFlagBetween(String value1, String value2) {
            addCriterion("EVAL_FLAG between", value1, value2, "evalFlag");
            return (Criteria) this;
        }

        public Criteria andEvalFlagNotBetween(String value1, String value2) {
            addCriterion("EVAL_FLAG not between", value1, value2, "evalFlag");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdIsNull() {
            addCriterion("SCORE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdIsNotNull() {
            addCriterion("SCORE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdEqualTo(Long value) {
            addCriterion("SCORE_TYPE_ID =", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdNotEqualTo(Long value) {
            addCriterion("SCORE_TYPE_ID <>", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdGreaterThan(Long value) {
            addCriterion("SCORE_TYPE_ID >", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SCORE_TYPE_ID >=", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdLessThan(Long value) {
            addCriterion("SCORE_TYPE_ID <", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("SCORE_TYPE_ID <=", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdIn(List<Long> values) {
            addCriterion("SCORE_TYPE_ID in", values, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdNotIn(List<Long> values) {
            addCriterion("SCORE_TYPE_ID not in", values, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdBetween(Long value1, Long value2) {
            addCriterion("SCORE_TYPE_ID between", value1, value2, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("SCORE_TYPE_ID not between", value1, value2, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andPrnFlagIsNull() {
            addCriterion("PRN_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andPrnFlagIsNotNull() {
            addCriterion("PRN_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andPrnFlagEqualTo(String value) {
            addCriterion("PRN_FLAG =", value, "prnFlag");
            return (Criteria) this;
        }

        public Criteria andPrnFlagNotEqualTo(String value) {
            addCriterion("PRN_FLAG <>", value, "prnFlag");
            return (Criteria) this;
        }

        public Criteria andPrnFlagGreaterThan(String value) {
            addCriterion("PRN_FLAG >", value, "prnFlag");
            return (Criteria) this;
        }

        public Criteria andPrnFlagGreaterThanOrEqualTo(String value) {
            addCriterion("PRN_FLAG >=", value, "prnFlag");
            return (Criteria) this;
        }

        public Criteria andPrnFlagLessThan(String value) {
            addCriterion("PRN_FLAG <", value, "prnFlag");
            return (Criteria) this;
        }

        public Criteria andPrnFlagLessThanOrEqualTo(String value) {
            addCriterion("PRN_FLAG <=", value, "prnFlag");
            return (Criteria) this;
        }

        public Criteria andPrnFlagLike(String value) {
            addCriterion("PRN_FLAG like", value, "prnFlag");
            return (Criteria) this;
        }

        public Criteria andPrnFlagNotLike(String value) {
            addCriterion("PRN_FLAG not like", value, "prnFlag");
            return (Criteria) this;
        }

        public Criteria andPrnFlagIn(List<String> values) {
            addCriterion("PRN_FLAG in", values, "prnFlag");
            return (Criteria) this;
        }

        public Criteria andPrnFlagNotIn(List<String> values) {
            addCriterion("PRN_FLAG not in", values, "prnFlag");
            return (Criteria) this;
        }

        public Criteria andPrnFlagBetween(String value1, String value2) {
            addCriterion("PRN_FLAG between", value1, value2, "prnFlag");
            return (Criteria) this;
        }

        public Criteria andPrnFlagNotBetween(String value1, String value2) {
            addCriterion("PRN_FLAG not between", value1, value2, "prnFlag");
            return (Criteria) this;
        }

        public Criteria andStandardPriceIsNull() {
            addCriterion("STANDARD_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andStandardPriceIsNotNull() {
            addCriterion("STANDARD_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andStandardPriceEqualTo(Long value) {
            addCriterion("STANDARD_PRICE =", value, "standardPrice");
            return (Criteria) this;
        }

        public Criteria andStandardPriceNotEqualTo(Long value) {
            addCriterion("STANDARD_PRICE <>", value, "standardPrice");
            return (Criteria) this;
        }

        public Criteria andStandardPriceGreaterThan(Long value) {
            addCriterion("STANDARD_PRICE >", value, "standardPrice");
            return (Criteria) this;
        }

        public Criteria andStandardPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("STANDARD_PRICE >=", value, "standardPrice");
            return (Criteria) this;
        }

        public Criteria andStandardPriceLessThan(Long value) {
            addCriterion("STANDARD_PRICE <", value, "standardPrice");
            return (Criteria) this;
        }

        public Criteria andStandardPriceLessThanOrEqualTo(Long value) {
            addCriterion("STANDARD_PRICE <=", value, "standardPrice");
            return (Criteria) this;
        }

        public Criteria andStandardPriceIn(List<Long> values) {
            addCriterion("STANDARD_PRICE in", values, "standardPrice");
            return (Criteria) this;
        }

        public Criteria andStandardPriceNotIn(List<Long> values) {
            addCriterion("STANDARD_PRICE not in", values, "standardPrice");
            return (Criteria) this;
        }

        public Criteria andStandardPriceBetween(Long value1, Long value2) {
            addCriterion("STANDARD_PRICE between", value1, value2, "standardPrice");
            return (Criteria) this;
        }

        public Criteria andStandardPriceNotBetween(Long value1, Long value2) {
            addCriterion("STANDARD_PRICE not between", value1, value2, "standardPrice");
            return (Criteria) this;
        }

        public Criteria andHasBackNumIsNull() {
            addCriterion("HAS_BACK_NUM is null");
            return (Criteria) this;
        }

        public Criteria andHasBackNumIsNotNull() {
            addCriterion("HAS_BACK_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andHasBackNumEqualTo(Long value) {
            addCriterion("HAS_BACK_NUM =", value, "hasBackNum");
            return (Criteria) this;
        }

        public Criteria andHasBackNumNotEqualTo(Long value) {
            addCriterion("HAS_BACK_NUM <>", value, "hasBackNum");
            return (Criteria) this;
        }

        public Criteria andHasBackNumGreaterThan(Long value) {
            addCriterion("HAS_BACK_NUM >", value, "hasBackNum");
            return (Criteria) this;
        }

        public Criteria andHasBackNumGreaterThanOrEqualTo(Long value) {
            addCriterion("HAS_BACK_NUM >=", value, "hasBackNum");
            return (Criteria) this;
        }

        public Criteria andHasBackNumLessThan(Long value) {
            addCriterion("HAS_BACK_NUM <", value, "hasBackNum");
            return (Criteria) this;
        }

        public Criteria andHasBackNumLessThanOrEqualTo(Long value) {
            addCriterion("HAS_BACK_NUM <=", value, "hasBackNum");
            return (Criteria) this;
        }

        public Criteria andHasBackNumIn(List<Long> values) {
            addCriterion("HAS_BACK_NUM in", values, "hasBackNum");
            return (Criteria) this;
        }

        public Criteria andHasBackNumNotIn(List<Long> values) {
            addCriterion("HAS_BACK_NUM not in", values, "hasBackNum");
            return (Criteria) this;
        }

        public Criteria andHasBackNumBetween(Long value1, Long value2) {
            addCriterion("HAS_BACK_NUM between", value1, value2, "hasBackNum");
            return (Criteria) this;
        }

        public Criteria andHasBackNumNotBetween(Long value1, Long value2) {
            addCriterion("HAS_BACK_NUM not between", value1, value2, "hasBackNum");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNull() {
            addCriterion("ORDER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("ORDER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(String value) {
            addCriterion("ORDER_TYPE =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(String value) {
            addCriterion("ORDER_TYPE <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(String value) {
            addCriterion("ORDER_TYPE >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_TYPE >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(String value) {
            addCriterion("ORDER_TYPE <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(String value) {
            addCriterion("ORDER_TYPE <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLike(String value) {
            addCriterion("ORDER_TYPE like", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotLike(String value) {
            addCriterion("ORDER_TYPE not like", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<String> values) {
            addCriterion("ORDER_TYPE in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<String> values) {
            addCriterion("ORDER_TYPE not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(String value1, String value2) {
            addCriterion("ORDER_TYPE between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(String value1, String value2) {
            addCriterion("ORDER_TYPE not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("SHOP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("SHOP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("SHOP_NAME =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("SHOP_NAME <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("SHOP_NAME >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_NAME >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("SHOP_NAME <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("SHOP_NAME <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("SHOP_NAME like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("SHOP_NAME not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("SHOP_NAME in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("SHOP_NAME not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("SHOP_NAME between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("SHOP_NAME not between", value1, value2, "shopName");
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

        public Criteria andStaffCodeIsNull() {
            addCriterion("STAFF_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStaffCodeIsNotNull() {
            addCriterion("STAFF_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStaffCodeEqualTo(String value) {
            addCriterion("STAFF_CODE =", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotEqualTo(String value) {
            addCriterion("STAFF_CODE <>", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeGreaterThan(String value) {
            addCriterion("STAFF_CODE >", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_CODE >=", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeLessThan(String value) {
            addCriterion("STAFF_CODE <", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeLessThanOrEqualTo(String value) {
            addCriterion("STAFF_CODE <=", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeLike(String value) {
            addCriterion("STAFF_CODE like", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotLike(String value) {
            addCriterion("STAFF_CODE not like", value, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeIn(List<String> values) {
            addCriterion("STAFF_CODE in", values, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotIn(List<String> values) {
            addCriterion("STAFF_CODE not in", values, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeBetween(String value1, String value2) {
            addCriterion("STAFF_CODE between", value1, value2, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffCodeNotBetween(String value1, String value2) {
            addCriterion("STAFF_CODE not between", value1, value2, "staffCode");
            return (Criteria) this;
        }

        public Criteria andStaffNameIsNull() {
            addCriterion("STAFF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStaffNameIsNotNull() {
            addCriterion("STAFF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStaffNameEqualTo(String value) {
            addCriterion("STAFF_NAME =", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameNotEqualTo(String value) {
            addCriterion("STAFF_NAME <>", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameGreaterThan(String value) {
            addCriterion("STAFF_NAME >", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameGreaterThanOrEqualTo(String value) {
            addCriterion("STAFF_NAME >=", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameLessThan(String value) {
            addCriterion("STAFF_NAME <", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameLessThanOrEqualTo(String value) {
            addCriterion("STAFF_NAME <=", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameLike(String value) {
            addCriterion("STAFF_NAME like", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameNotLike(String value) {
            addCriterion("STAFF_NAME not like", value, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameIn(List<String> values) {
            addCriterion("STAFF_NAME in", values, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameNotIn(List<String> values) {
            addCriterion("STAFF_NAME not in", values, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameBetween(String value1, String value2) {
            addCriterion("STAFF_NAME between", value1, value2, "staffName");
            return (Criteria) this;
        }

        public Criteria andStaffNameNotBetween(String value1, String value2) {
            addCriterion("STAFF_NAME not between", value1, value2, "staffName");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("COMPANY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("COMPANY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Long value) {
            addCriterion("COMPANY_ID =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Long value) {
            addCriterion("COMPANY_ID <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Long value) {
            addCriterion("COMPANY_ID >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("COMPANY_ID >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Long value) {
            addCriterion("COMPANY_ID <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Long value) {
            addCriterion("COMPANY_ID <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Long> values) {
            addCriterion("COMPANY_ID in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Long> values) {
            addCriterion("COMPANY_ID not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Long value1, Long value2) {
            addCriterion("COMPANY_ID between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Long value1, Long value2) {
            addCriterion("COMPANY_ID not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("COMPANY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("COMPANY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("COMPANY_NAME =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("COMPANY_NAME <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("COMPANY_NAME >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_NAME >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("COMPANY_NAME <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_NAME <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("COMPANY_NAME like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("COMPANY_NAME not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("COMPANY_NAME in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("COMPANY_NAME not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("COMPANY_NAME between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("COMPANY_NAME not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Timestamp value) {
            addCriterion("START_TIME =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Timestamp value) {
            addCriterion("START_TIME <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Timestamp value) {
            addCriterion("START_TIME >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("START_TIME >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Timestamp value) {
            addCriterion("START_TIME <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("START_TIME <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Timestamp> values) {
            addCriterion("START_TIME in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Timestamp> values) {
            addCriterion("START_TIME not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("START_TIME between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("START_TIME not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Timestamp value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Timestamp value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Timestamp value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Timestamp value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Timestamp> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Timestamp> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
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