package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class StockInfoHisCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public StockInfoHisCriteria() {
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

        public Criteria andTypeIdIsNull() {
            addCriterion("TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Long value) {
            addCriterion("TYPE_ID =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Long value) {
            addCriterion("TYPE_ID <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Long value) {
            addCriterion("TYPE_ID >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TYPE_ID >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Long value) {
            addCriterion("TYPE_ID <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("TYPE_ID <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Long> values) {
            addCriterion("TYPE_ID in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Long> values) {
            addCriterion("TYPE_ID not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Long value1, Long value2) {
            addCriterion("TYPE_ID between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("TYPE_ID not between", value1, value2, "typeId");
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

        public Criteria andRealCountIsNull() {
            addCriterion("REAL_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andRealCountIsNotNull() {
            addCriterion("REAL_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRealCountEqualTo(Long value) {
            addCriterion("REAL_COUNT =", value, "realCount");
            return (Criteria) this;
        }

        public Criteria andRealCountNotEqualTo(Long value) {
            addCriterion("REAL_COUNT <>", value, "realCount");
            return (Criteria) this;
        }

        public Criteria andRealCountGreaterThan(Long value) {
            addCriterion("REAL_COUNT >", value, "realCount");
            return (Criteria) this;
        }

        public Criteria andRealCountGreaterThanOrEqualTo(Long value) {
            addCriterion("REAL_COUNT >=", value, "realCount");
            return (Criteria) this;
        }

        public Criteria andRealCountLessThan(Long value) {
            addCriterion("REAL_COUNT <", value, "realCount");
            return (Criteria) this;
        }

        public Criteria andRealCountLessThanOrEqualTo(Long value) {
            addCriterion("REAL_COUNT <=", value, "realCount");
            return (Criteria) this;
        }

        public Criteria andRealCountIn(List<Long> values) {
            addCriterion("REAL_COUNT in", values, "realCount");
            return (Criteria) this;
        }

        public Criteria andRealCountNotIn(List<Long> values) {
            addCriterion("REAL_COUNT not in", values, "realCount");
            return (Criteria) this;
        }

        public Criteria andRealCountBetween(Long value1, Long value2) {
            addCriterion("REAL_COUNT between", value1, value2, "realCount");
            return (Criteria) this;
        }

        public Criteria andRealCountNotBetween(Long value1, Long value2) {
            addCriterion("REAL_COUNT not between", value1, value2, "realCount");
            return (Criteria) this;
        }

        public Criteria andPreOccupyCountIsNull() {
            addCriterion("PRE_OCCUPY_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andPreOccupyCountIsNotNull() {
            addCriterion("PRE_OCCUPY_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andPreOccupyCountEqualTo(Long value) {
            addCriterion("PRE_OCCUPY_COUNT =", value, "preOccupyCount");
            return (Criteria) this;
        }

        public Criteria andPreOccupyCountNotEqualTo(Long value) {
            addCriterion("PRE_OCCUPY_COUNT <>", value, "preOccupyCount");
            return (Criteria) this;
        }

        public Criteria andPreOccupyCountGreaterThan(Long value) {
            addCriterion("PRE_OCCUPY_COUNT >", value, "preOccupyCount");
            return (Criteria) this;
        }

        public Criteria andPreOccupyCountGreaterThanOrEqualTo(Long value) {
            addCriterion("PRE_OCCUPY_COUNT >=", value, "preOccupyCount");
            return (Criteria) this;
        }

        public Criteria andPreOccupyCountLessThan(Long value) {
            addCriterion("PRE_OCCUPY_COUNT <", value, "preOccupyCount");
            return (Criteria) this;
        }

        public Criteria andPreOccupyCountLessThanOrEqualTo(Long value) {
            addCriterion("PRE_OCCUPY_COUNT <=", value, "preOccupyCount");
            return (Criteria) this;
        }

        public Criteria andPreOccupyCountIn(List<Long> values) {
            addCriterion("PRE_OCCUPY_COUNT in", values, "preOccupyCount");
            return (Criteria) this;
        }

        public Criteria andPreOccupyCountNotIn(List<Long> values) {
            addCriterion("PRE_OCCUPY_COUNT not in", values, "preOccupyCount");
            return (Criteria) this;
        }

        public Criteria andPreOccupyCountBetween(Long value1, Long value2) {
            addCriterion("PRE_OCCUPY_COUNT between", value1, value2, "preOccupyCount");
            return (Criteria) this;
        }

        public Criteria andPreOccupyCountNotBetween(Long value1, Long value2) {
            addCriterion("PRE_OCCUPY_COUNT not between", value1, value2, "preOccupyCount");
            return (Criteria) this;
        }

        public Criteria andAvailCountIsNull() {
            addCriterion("AVAIL_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andAvailCountIsNotNull() {
            addCriterion("AVAIL_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAvailCountEqualTo(Long value) {
            addCriterion("AVAIL_COUNT =", value, "availCount");
            return (Criteria) this;
        }

        public Criteria andAvailCountNotEqualTo(Long value) {
            addCriterion("AVAIL_COUNT <>", value, "availCount");
            return (Criteria) this;
        }

        public Criteria andAvailCountGreaterThan(Long value) {
            addCriterion("AVAIL_COUNT >", value, "availCount");
            return (Criteria) this;
        }

        public Criteria andAvailCountGreaterThanOrEqualTo(Long value) {
            addCriterion("AVAIL_COUNT >=", value, "availCount");
            return (Criteria) this;
        }

        public Criteria andAvailCountLessThan(Long value) {
            addCriterion("AVAIL_COUNT <", value, "availCount");
            return (Criteria) this;
        }

        public Criteria andAvailCountLessThanOrEqualTo(Long value) {
            addCriterion("AVAIL_COUNT <=", value, "availCount");
            return (Criteria) this;
        }

        public Criteria andAvailCountIn(List<Long> values) {
            addCriterion("AVAIL_COUNT in", values, "availCount");
            return (Criteria) this;
        }

        public Criteria andAvailCountNotIn(List<Long> values) {
            addCriterion("AVAIL_COUNT not in", values, "availCount");
            return (Criteria) this;
        }

        public Criteria andAvailCountBetween(Long value1, Long value2) {
            addCriterion("AVAIL_COUNT between", value1, value2, "availCount");
            return (Criteria) this;
        }

        public Criteria andAvailCountNotBetween(Long value1, Long value2) {
            addCriterion("AVAIL_COUNT not between", value1, value2, "availCount");
            return (Criteria) this;
        }

        public Criteria andSendCountIsNull() {
            addCriterion("SEND_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andSendCountIsNotNull() {
            addCriterion("SEND_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andSendCountEqualTo(Long value) {
            addCriterion("SEND_COUNT =", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountNotEqualTo(Long value) {
            addCriterion("SEND_COUNT <>", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountGreaterThan(Long value) {
            addCriterion("SEND_COUNT >", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountGreaterThanOrEqualTo(Long value) {
            addCriterion("SEND_COUNT >=", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountLessThan(Long value) {
            addCriterion("SEND_COUNT <", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountLessThanOrEqualTo(Long value) {
            addCriterion("SEND_COUNT <=", value, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountIn(List<Long> values) {
            addCriterion("SEND_COUNT in", values, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountNotIn(List<Long> values) {
            addCriterion("SEND_COUNT not in", values, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountBetween(Long value1, Long value2) {
            addCriterion("SEND_COUNT between", value1, value2, "sendCount");
            return (Criteria) this;
        }

        public Criteria andSendCountNotBetween(Long value1, Long value2) {
            addCriterion("SEND_COUNT not between", value1, value2, "sendCount");
            return (Criteria) this;
        }

        public Criteria andLackCountIsNull() {
            addCriterion("LACK_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andLackCountIsNotNull() {
            addCriterion("LACK_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andLackCountEqualTo(Long value) {
            addCriterion("LACK_COUNT =", value, "lackCount");
            return (Criteria) this;
        }

        public Criteria andLackCountNotEqualTo(Long value) {
            addCriterion("LACK_COUNT <>", value, "lackCount");
            return (Criteria) this;
        }

        public Criteria andLackCountGreaterThan(Long value) {
            addCriterion("LACK_COUNT >", value, "lackCount");
            return (Criteria) this;
        }

        public Criteria andLackCountGreaterThanOrEqualTo(Long value) {
            addCriterion("LACK_COUNT >=", value, "lackCount");
            return (Criteria) this;
        }

        public Criteria andLackCountLessThan(Long value) {
            addCriterion("LACK_COUNT <", value, "lackCount");
            return (Criteria) this;
        }

        public Criteria andLackCountLessThanOrEqualTo(Long value) {
            addCriterion("LACK_COUNT <=", value, "lackCount");
            return (Criteria) this;
        }

        public Criteria andLackCountIn(List<Long> values) {
            addCriterion("LACK_COUNT in", values, "lackCount");
            return (Criteria) this;
        }

        public Criteria andLackCountNotIn(List<Long> values) {
            addCriterion("LACK_COUNT not in", values, "lackCount");
            return (Criteria) this;
        }

        public Criteria andLackCountBetween(Long value1, Long value2) {
            addCriterion("LACK_COUNT between", value1, value2, "lackCount");
            return (Criteria) this;
        }

        public Criteria andLackCountNotBetween(Long value1, Long value2) {
            addCriterion("LACK_COUNT not between", value1, value2, "lackCount");
            return (Criteria) this;
        }

        public Criteria andWarningCountIsNull() {
            addCriterion("WARNING_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andWarningCountIsNotNull() {
            addCriterion("WARNING_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andWarningCountEqualTo(Long value) {
            addCriterion("WARNING_COUNT =", value, "warningCount");
            return (Criteria) this;
        }

        public Criteria andWarningCountNotEqualTo(Long value) {
            addCriterion("WARNING_COUNT <>", value, "warningCount");
            return (Criteria) this;
        }

        public Criteria andWarningCountGreaterThan(Long value) {
            addCriterion("WARNING_COUNT >", value, "warningCount");
            return (Criteria) this;
        }

        public Criteria andWarningCountGreaterThanOrEqualTo(Long value) {
            addCriterion("WARNING_COUNT >=", value, "warningCount");
            return (Criteria) this;
        }

        public Criteria andWarningCountLessThan(Long value) {
            addCriterion("WARNING_COUNT <", value, "warningCount");
            return (Criteria) this;
        }

        public Criteria andWarningCountLessThanOrEqualTo(Long value) {
            addCriterion("WARNING_COUNT <=", value, "warningCount");
            return (Criteria) this;
        }

        public Criteria andWarningCountIn(List<Long> values) {
            addCriterion("WARNING_COUNT in", values, "warningCount");
            return (Criteria) this;
        }

        public Criteria andWarningCountNotIn(List<Long> values) {
            addCriterion("WARNING_COUNT not in", values, "warningCount");
            return (Criteria) this;
        }

        public Criteria andWarningCountBetween(Long value1, Long value2) {
            addCriterion("WARNING_COUNT between", value1, value2, "warningCount");
            return (Criteria) this;
        }

        public Criteria andWarningCountNotBetween(Long value1, Long value2) {
            addCriterion("WARNING_COUNT not between", value1, value2, "warningCount");
            return (Criteria) this;
        }

        public Criteria andFacStockIsNull() {
            addCriterion("FAC_STOCK is null");
            return (Criteria) this;
        }

        public Criteria andFacStockIsNotNull() {
            addCriterion("FAC_STOCK is not null");
            return (Criteria) this;
        }

        public Criteria andFacStockEqualTo(Long value) {
            addCriterion("FAC_STOCK =", value, "facStock");
            return (Criteria) this;
        }

        public Criteria andFacStockNotEqualTo(Long value) {
            addCriterion("FAC_STOCK <>", value, "facStock");
            return (Criteria) this;
        }

        public Criteria andFacStockGreaterThan(Long value) {
            addCriterion("FAC_STOCK >", value, "facStock");
            return (Criteria) this;
        }

        public Criteria andFacStockGreaterThanOrEqualTo(Long value) {
            addCriterion("FAC_STOCK >=", value, "facStock");
            return (Criteria) this;
        }

        public Criteria andFacStockLessThan(Long value) {
            addCriterion("FAC_STOCK <", value, "facStock");
            return (Criteria) this;
        }

        public Criteria andFacStockLessThanOrEqualTo(Long value) {
            addCriterion("FAC_STOCK <=", value, "facStock");
            return (Criteria) this;
        }

        public Criteria andFacStockIn(List<Long> values) {
            addCriterion("FAC_STOCK in", values, "facStock");
            return (Criteria) this;
        }

        public Criteria andFacStockNotIn(List<Long> values) {
            addCriterion("FAC_STOCK not in", values, "facStock");
            return (Criteria) this;
        }

        public Criteria andFacStockBetween(Long value1, Long value2) {
            addCriterion("FAC_STOCK between", value1, value2, "facStock");
            return (Criteria) this;
        }

        public Criteria andFacStockNotBetween(Long value1, Long value2) {
            addCriterion("FAC_STOCK not between", value1, value2, "facStock");
            return (Criteria) this;
        }

        public Criteria andIsUsewarningIsNull() {
            addCriterion("IS_USEWARNING is null");
            return (Criteria) this;
        }

        public Criteria andIsUsewarningIsNotNull() {
            addCriterion("IS_USEWARNING is not null");
            return (Criteria) this;
        }

        public Criteria andIsUsewarningEqualTo(String value) {
            addCriterion("IS_USEWARNING =", value, "isUsewarning");
            return (Criteria) this;
        }

        public Criteria andIsUsewarningNotEqualTo(String value) {
            addCriterion("IS_USEWARNING <>", value, "isUsewarning");
            return (Criteria) this;
        }

        public Criteria andIsUsewarningGreaterThan(String value) {
            addCriterion("IS_USEWARNING >", value, "isUsewarning");
            return (Criteria) this;
        }

        public Criteria andIsUsewarningGreaterThanOrEqualTo(String value) {
            addCriterion("IS_USEWARNING >=", value, "isUsewarning");
            return (Criteria) this;
        }

        public Criteria andIsUsewarningLessThan(String value) {
            addCriterion("IS_USEWARNING <", value, "isUsewarning");
            return (Criteria) this;
        }

        public Criteria andIsUsewarningLessThanOrEqualTo(String value) {
            addCriterion("IS_USEWARNING <=", value, "isUsewarning");
            return (Criteria) this;
        }

        public Criteria andIsUsewarningLike(String value) {
            addCriterion("IS_USEWARNING like", value, "isUsewarning");
            return (Criteria) this;
        }

        public Criteria andIsUsewarningNotLike(String value) {
            addCriterion("IS_USEWARNING not like", value, "isUsewarning");
            return (Criteria) this;
        }

        public Criteria andIsUsewarningIn(List<String> values) {
            addCriterion("IS_USEWARNING in", values, "isUsewarning");
            return (Criteria) this;
        }

        public Criteria andIsUsewarningNotIn(List<String> values) {
            addCriterion("IS_USEWARNING not in", values, "isUsewarning");
            return (Criteria) this;
        }

        public Criteria andIsUsewarningBetween(String value1, String value2) {
            addCriterion("IS_USEWARNING between", value1, value2, "isUsewarning");
            return (Criteria) this;
        }

        public Criteria andIsUsewarningNotBetween(String value1, String value2) {
            addCriterion("IS_USEWARNING not between", value1, value2, "isUsewarning");
            return (Criteria) this;
        }

        public Criteria andIsOverIsNull() {
            addCriterion("IS_OVER is null");
            return (Criteria) this;
        }

        public Criteria andIsOverIsNotNull() {
            addCriterion("IS_OVER is not null");
            return (Criteria) this;
        }

        public Criteria andIsOverEqualTo(String value) {
            addCriterion("IS_OVER =", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverNotEqualTo(String value) {
            addCriterion("IS_OVER <>", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverGreaterThan(String value) {
            addCriterion("IS_OVER >", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverGreaterThanOrEqualTo(String value) {
            addCriterion("IS_OVER >=", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverLessThan(String value) {
            addCriterion("IS_OVER <", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverLessThanOrEqualTo(String value) {
            addCriterion("IS_OVER <=", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverLike(String value) {
            addCriterion("IS_OVER like", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverNotLike(String value) {
            addCriterion("IS_OVER not like", value, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverIn(List<String> values) {
            addCriterion("IS_OVER in", values, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverNotIn(List<String> values) {
            addCriterion("IS_OVER not in", values, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverBetween(String value1, String value2) {
            addCriterion("IS_OVER between", value1, value2, "isOver");
            return (Criteria) this;
        }

        public Criteria andIsOverNotBetween(String value1, String value2) {
            addCriterion("IS_OVER not between", value1, value2, "isOver");
            return (Criteria) this;
        }

        public Criteria andRepTypeIsNull() {
            addCriterion("REP_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRepTypeIsNotNull() {
            addCriterion("REP_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRepTypeEqualTo(String value) {
            addCriterion("REP_TYPE =", value, "repType");
            return (Criteria) this;
        }

        public Criteria andRepTypeNotEqualTo(String value) {
            addCriterion("REP_TYPE <>", value, "repType");
            return (Criteria) this;
        }

        public Criteria andRepTypeGreaterThan(String value) {
            addCriterion("REP_TYPE >", value, "repType");
            return (Criteria) this;
        }

        public Criteria andRepTypeGreaterThanOrEqualTo(String value) {
            addCriterion("REP_TYPE >=", value, "repType");
            return (Criteria) this;
        }

        public Criteria andRepTypeLessThan(String value) {
            addCriterion("REP_TYPE <", value, "repType");
            return (Criteria) this;
        }

        public Criteria andRepTypeLessThanOrEqualTo(String value) {
            addCriterion("REP_TYPE <=", value, "repType");
            return (Criteria) this;
        }

        public Criteria andRepTypeLike(String value) {
            addCriterion("REP_TYPE like", value, "repType");
            return (Criteria) this;
        }

        public Criteria andRepTypeNotLike(String value) {
            addCriterion("REP_TYPE not like", value, "repType");
            return (Criteria) this;
        }

        public Criteria andRepTypeIn(List<String> values) {
            addCriterion("REP_TYPE in", values, "repType");
            return (Criteria) this;
        }

        public Criteria andRepTypeNotIn(List<String> values) {
            addCriterion("REP_TYPE not in", values, "repType");
            return (Criteria) this;
        }

        public Criteria andRepTypeBetween(String value1, String value2) {
            addCriterion("REP_TYPE between", value1, value2, "repType");
            return (Criteria) this;
        }

        public Criteria andRepTypeNotBetween(String value1, String value2) {
            addCriterion("REP_TYPE not between", value1, value2, "repType");
            return (Criteria) this;
        }

        public Criteria andStockTypeIsNull() {
            addCriterion("STOCK_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andStockTypeIsNotNull() {
            addCriterion("STOCK_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andStockTypeEqualTo(String value) {
            addCriterion("STOCK_TYPE =", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeNotEqualTo(String value) {
            addCriterion("STOCK_TYPE <>", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeGreaterThan(String value) {
            addCriterion("STOCK_TYPE >", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeGreaterThanOrEqualTo(String value) {
            addCriterion("STOCK_TYPE >=", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeLessThan(String value) {
            addCriterion("STOCK_TYPE <", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeLessThanOrEqualTo(String value) {
            addCriterion("STOCK_TYPE <=", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeLike(String value) {
            addCriterion("STOCK_TYPE like", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeNotLike(String value) {
            addCriterion("STOCK_TYPE not like", value, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeIn(List<String> values) {
            addCriterion("STOCK_TYPE in", values, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeNotIn(List<String> values) {
            addCriterion("STOCK_TYPE not in", values, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeBetween(String value1, String value2) {
            addCriterion("STOCK_TYPE between", value1, value2, "stockType");
            return (Criteria) this;
        }

        public Criteria andStockTypeNotBetween(String value1, String value2) {
            addCriterion("STOCK_TYPE not between", value1, value2, "stockType");
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
