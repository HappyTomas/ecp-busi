package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GdsExcelImpCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsExcelImpCriteria() {
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

        public Criteria andImportIdIsNull() {
            addCriterion("IMPORT_ID is null");
            return (Criteria) this;
        }

        public Criteria andImportIdIsNotNull() {
            addCriterion("IMPORT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andImportIdEqualTo(Long value) {
            addCriterion("IMPORT_ID =", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdNotEqualTo(Long value) {
            addCriterion("IMPORT_ID <>", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdGreaterThan(Long value) {
            addCriterion("IMPORT_ID >", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdGreaterThanOrEqualTo(Long value) {
            addCriterion("IMPORT_ID >=", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdLessThan(Long value) {
            addCriterion("IMPORT_ID <", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdLessThanOrEqualTo(Long value) {
            addCriterion("IMPORT_ID <=", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdIn(List<Long> values) {
            addCriterion("IMPORT_ID in", values, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdNotIn(List<Long> values) {
            addCriterion("IMPORT_ID not in", values, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdBetween(Long value1, Long value2) {
            addCriterion("IMPORT_ID between", value1, value2, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdNotBetween(Long value1, Long value2) {
            addCriterion("IMPORT_ID not between", value1, value2, "importId");
            return (Criteria) this;
        }

        public Criteria andBusiSrcIsNull() {
            addCriterion("BUSI_SRC is null");
            return (Criteria) this;
        }

        public Criteria andBusiSrcIsNotNull() {
            addCriterion("BUSI_SRC is not null");
            return (Criteria) this;
        }

        public Criteria andBusiSrcEqualTo(String value) {
            addCriterion("BUSI_SRC =", value, "busiSrc");
            return (Criteria) this;
        }

        public Criteria andBusiSrcNotEqualTo(String value) {
            addCriterion("BUSI_SRC <>", value, "busiSrc");
            return (Criteria) this;
        }

        public Criteria andBusiSrcGreaterThan(String value) {
            addCriterion("BUSI_SRC >", value, "busiSrc");
            return (Criteria) this;
        }

        public Criteria andBusiSrcGreaterThanOrEqualTo(String value) {
            addCriterion("BUSI_SRC >=", value, "busiSrc");
            return (Criteria) this;
        }

        public Criteria andBusiSrcLessThan(String value) {
            addCriterion("BUSI_SRC <", value, "busiSrc");
            return (Criteria) this;
        }

        public Criteria andBusiSrcLessThanOrEqualTo(String value) {
            addCriterion("BUSI_SRC <=", value, "busiSrc");
            return (Criteria) this;
        }

        public Criteria andBusiSrcLike(String value) {
            addCriterion("BUSI_SRC like", value, "busiSrc");
            return (Criteria) this;
        }

        public Criteria andBusiSrcNotLike(String value) {
            addCriterion("BUSI_SRC not like", value, "busiSrc");
            return (Criteria) this;
        }

        public Criteria andBusiSrcIn(List<String> values) {
            addCriterion("BUSI_SRC in", values, "busiSrc");
            return (Criteria) this;
        }

        public Criteria andBusiSrcNotIn(List<String> values) {
            addCriterion("BUSI_SRC not in", values, "busiSrc");
            return (Criteria) this;
        }

        public Criteria andBusiSrcBetween(String value1, String value2) {
            addCriterion("BUSI_SRC between", value1, value2, "busiSrc");
            return (Criteria) this;
        }

        public Criteria andBusiSrcNotBetween(String value1, String value2) {
            addCriterion("BUSI_SRC not between", value1, value2, "busiSrc");
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

        public Criteria andCatgCodeIsNull() {
            addCriterion("CATG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCatgCodeIsNotNull() {
            addCriterion("CATG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCatgCodeEqualTo(String value) {
            addCriterion("CATG_CODE =", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeNotEqualTo(String value) {
            addCriterion("CATG_CODE <>", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeGreaterThan(String value) {
            addCriterion("CATG_CODE >", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CATG_CODE >=", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeLessThan(String value) {
            addCriterion("CATG_CODE <", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeLessThanOrEqualTo(String value) {
            addCriterion("CATG_CODE <=", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeLike(String value) {
            addCriterion("CATG_CODE like", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeNotLike(String value) {
            addCriterion("CATG_CODE not like", value, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeIn(List<String> values) {
            addCriterion("CATG_CODE in", values, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeNotIn(List<String> values) {
            addCriterion("CATG_CODE not in", values, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeBetween(String value1, String value2) {
            addCriterion("CATG_CODE between", value1, value2, "catgCode");
            return (Criteria) this;
        }

        public Criteria andCatgCodeNotBetween(String value1, String value2) {
            addCriterion("CATG_CODE not between", value1, value2, "catgCode");
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

        public Criteria andGdsTitleIsNull() {
            addCriterion("GDS_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andGdsTitleIsNotNull() {
            addCriterion("GDS_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andGdsTitleEqualTo(String value) {
            addCriterion("GDS_TITLE =", value, "gdsTitle");
            return (Criteria) this;
        }

        public Criteria andGdsTitleNotEqualTo(String value) {
            addCriterion("GDS_TITLE <>", value, "gdsTitle");
            return (Criteria) this;
        }

        public Criteria andGdsTitleGreaterThan(String value) {
            addCriterion("GDS_TITLE >", value, "gdsTitle");
            return (Criteria) this;
        }

        public Criteria andGdsTitleGreaterThanOrEqualTo(String value) {
            addCriterion("GDS_TITLE >=", value, "gdsTitle");
            return (Criteria) this;
        }

        public Criteria andGdsTitleLessThan(String value) {
            addCriterion("GDS_TITLE <", value, "gdsTitle");
            return (Criteria) this;
        }

        public Criteria andGdsTitleLessThanOrEqualTo(String value) {
            addCriterion("GDS_TITLE <=", value, "gdsTitle");
            return (Criteria) this;
        }

        public Criteria andGdsTitleLike(String value) {
            addCriterion("GDS_TITLE like", value, "gdsTitle");
            return (Criteria) this;
        }

        public Criteria andGdsTitleNotLike(String value) {
            addCriterion("GDS_TITLE not like", value, "gdsTitle");
            return (Criteria) this;
        }

        public Criteria andGdsTitleIn(List<String> values) {
            addCriterion("GDS_TITLE in", values, "gdsTitle");
            return (Criteria) this;
        }

        public Criteria andGdsTitleNotIn(List<String> values) {
            addCriterion("GDS_TITLE not in", values, "gdsTitle");
            return (Criteria) this;
        }

        public Criteria andGdsTitleBetween(String value1, String value2) {
            addCriterion("GDS_TITLE between", value1, value2, "gdsTitle");
            return (Criteria) this;
        }

        public Criteria andGdsTitleNotBetween(String value1, String value2) {
            addCriterion("GDS_TITLE not between", value1, value2, "gdsTitle");
            return (Criteria) this;
        }

        public Criteria andGdsPriceIsNull() {
            addCriterion("GDS_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andGdsPriceIsNotNull() {
            addCriterion("GDS_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andGdsPriceEqualTo(String value) {
            addCriterion("GDS_PRICE =", value, "gdsPrice");
            return (Criteria) this;
        }

        public Criteria andGdsPriceNotEqualTo(String value) {
            addCriterion("GDS_PRICE <>", value, "gdsPrice");
            return (Criteria) this;
        }

        public Criteria andGdsPriceGreaterThan(String value) {
            addCriterion("GDS_PRICE >", value, "gdsPrice");
            return (Criteria) this;
        }

        public Criteria andGdsPriceGreaterThanOrEqualTo(String value) {
            addCriterion("GDS_PRICE >=", value, "gdsPrice");
            return (Criteria) this;
        }

        public Criteria andGdsPriceLessThan(String value) {
            addCriterion("GDS_PRICE <", value, "gdsPrice");
            return (Criteria) this;
        }

        public Criteria andGdsPriceLessThanOrEqualTo(String value) {
            addCriterion("GDS_PRICE <=", value, "gdsPrice");
            return (Criteria) this;
        }

        public Criteria andGdsPriceLike(String value) {
            addCriterion("GDS_PRICE like", value, "gdsPrice");
            return (Criteria) this;
        }

        public Criteria andGdsPriceNotLike(String value) {
            addCriterion("GDS_PRICE not like", value, "gdsPrice");
            return (Criteria) this;
        }

        public Criteria andGdsPriceIn(List<String> values) {
            addCriterion("GDS_PRICE in", values, "gdsPrice");
            return (Criteria) this;
        }

        public Criteria andGdsPriceNotIn(List<String> values) {
            addCriterion("GDS_PRICE not in", values, "gdsPrice");
            return (Criteria) this;
        }

        public Criteria andGdsPriceBetween(String value1, String value2) {
            addCriterion("GDS_PRICE between", value1, value2, "gdsPrice");
            return (Criteria) this;
        }

        public Criteria andGdsPriceNotBetween(String value1, String value2) {
            addCriterion("GDS_PRICE not between", value1, value2, "gdsPrice");
            return (Criteria) this;
        }

        public Criteria andGdsPropStrIsNull() {
            addCriterion("GDS_PROP_STR is null");
            return (Criteria) this;
        }

        public Criteria andGdsPropStrIsNotNull() {
            addCriterion("GDS_PROP_STR is not null");
            return (Criteria) this;
        }

        public Criteria andGdsPropStrEqualTo(String value) {
            addCriterion("GDS_PROP_STR =", value, "gdsPropStr");
            return (Criteria) this;
        }

        public Criteria andGdsPropStrNotEqualTo(String value) {
            addCriterion("GDS_PROP_STR <>", value, "gdsPropStr");
            return (Criteria) this;
        }

        public Criteria andGdsPropStrGreaterThan(String value) {
            addCriterion("GDS_PROP_STR >", value, "gdsPropStr");
            return (Criteria) this;
        }

        public Criteria andGdsPropStrGreaterThanOrEqualTo(String value) {
            addCriterion("GDS_PROP_STR >=", value, "gdsPropStr");
            return (Criteria) this;
        }

        public Criteria andGdsPropStrLessThan(String value) {
            addCriterion("GDS_PROP_STR <", value, "gdsPropStr");
            return (Criteria) this;
        }

        public Criteria andGdsPropStrLessThanOrEqualTo(String value) {
            addCriterion("GDS_PROP_STR <=", value, "gdsPropStr");
            return (Criteria) this;
        }

        public Criteria andGdsPropStrLike(String value) {
            addCriterion("GDS_PROP_STR like", value, "gdsPropStr");
            return (Criteria) this;
        }

        public Criteria andGdsPropStrNotLike(String value) {
            addCriterion("GDS_PROP_STR not like", value, "gdsPropStr");
            return (Criteria) this;
        }

        public Criteria andGdsPropStrIn(List<String> values) {
            addCriterion("GDS_PROP_STR in", values, "gdsPropStr");
            return (Criteria) this;
        }

        public Criteria andGdsPropStrNotIn(List<String> values) {
            addCriterion("GDS_PROP_STR not in", values, "gdsPropStr");
            return (Criteria) this;
        }

        public Criteria andGdsPropStrBetween(String value1, String value2) {
            addCriterion("GDS_PROP_STR between", value1, value2, "gdsPropStr");
            return (Criteria) this;
        }

        public Criteria andGdsPropStrNotBetween(String value1, String value2) {
            addCriterion("GDS_PROP_STR not between", value1, value2, "gdsPropStr");
            return (Criteria) this;
        }

        public Criteria andGdsStockIsNull() {
            addCriterion("GDS_STOCK is null");
            return (Criteria) this;
        }

        public Criteria andGdsStockIsNotNull() {
            addCriterion("GDS_STOCK is not null");
            return (Criteria) this;
        }

        public Criteria andGdsStockEqualTo(Long value) {
            addCriterion("GDS_STOCK =", value, "gdsStock");
            return (Criteria) this;
        }

        public Criteria andGdsStockNotEqualTo(Long value) {
            addCriterion("GDS_STOCK <>", value, "gdsStock");
            return (Criteria) this;
        }

        public Criteria andGdsStockGreaterThan(Long value) {
            addCriterion("GDS_STOCK >", value, "gdsStock");
            return (Criteria) this;
        }

        public Criteria andGdsStockGreaterThanOrEqualTo(Long value) {
            addCriterion("GDS_STOCK >=", value, "gdsStock");
            return (Criteria) this;
        }

        public Criteria andGdsStockLessThan(Long value) {
            addCriterion("GDS_STOCK <", value, "gdsStock");
            return (Criteria) this;
        }

        public Criteria andGdsStockLessThanOrEqualTo(Long value) {
            addCriterion("GDS_STOCK <=", value, "gdsStock");
            return (Criteria) this;
        }

        public Criteria andGdsStockIn(List<Long> values) {
            addCriterion("GDS_STOCK in", values, "gdsStock");
            return (Criteria) this;
        }

        public Criteria andGdsStockNotIn(List<Long> values) {
            addCriterion("GDS_STOCK not in", values, "gdsStock");
            return (Criteria) this;
        }

        public Criteria andGdsStockBetween(Long value1, Long value2) {
            addCriterion("GDS_STOCK between", value1, value2, "gdsStock");
            return (Criteria) this;
        }

        public Criteria andGdsStockNotBetween(Long value1, Long value2) {
            addCriterion("GDS_STOCK not between", value1, value2, "gdsStock");
            return (Criteria) this;
        }

        public Criteria andFailReasonIsNull() {
            addCriterion("FAIL_REASON is null");
            return (Criteria) this;
        }

        public Criteria andFailReasonIsNotNull() {
            addCriterion("FAIL_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andFailReasonEqualTo(String value) {
            addCriterion("FAIL_REASON =", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonNotEqualTo(String value) {
            addCriterion("FAIL_REASON <>", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonGreaterThan(String value) {
            addCriterion("FAIL_REASON >", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonGreaterThanOrEqualTo(String value) {
            addCriterion("FAIL_REASON >=", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonLessThan(String value) {
            addCriterion("FAIL_REASON <", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonLessThanOrEqualTo(String value) {
            addCriterion("FAIL_REASON <=", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonLike(String value) {
            addCriterion("FAIL_REASON like", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonNotLike(String value) {
            addCriterion("FAIL_REASON not like", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonIn(List<String> values) {
            addCriterion("FAIL_REASON in", values, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonNotIn(List<String> values) {
            addCriterion("FAIL_REASON not in", values, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonBetween(String value1, String value2) {
            addCriterion("FAIL_REASON between", value1, value2, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonNotBetween(String value1, String value2) {
            addCriterion("FAIL_REASON not between", value1, value2, "failReason");
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
