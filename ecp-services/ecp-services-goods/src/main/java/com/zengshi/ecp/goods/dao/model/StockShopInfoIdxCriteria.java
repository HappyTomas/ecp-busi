package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class StockShopInfoIdxCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public StockShopInfoIdxCriteria() {
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

        public Criteria andZeroStockFlagIsNull() {
            addCriterion("ZERO_STOCK_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andZeroStockFlagIsNotNull() {
            addCriterion("ZERO_STOCK_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andZeroStockFlagEqualTo(String value) {
            addCriterion("ZERO_STOCK_FLAG =", value, "zeroStockFlag");
            return (Criteria) this;
        }

        public Criteria andZeroStockFlagNotEqualTo(String value) {
            addCriterion("ZERO_STOCK_FLAG <>", value, "zeroStockFlag");
            return (Criteria) this;
        }

        public Criteria andZeroStockFlagGreaterThan(String value) {
            addCriterion("ZERO_STOCK_FLAG >", value, "zeroStockFlag");
            return (Criteria) this;
        }

        public Criteria andZeroStockFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ZERO_STOCK_FLAG >=", value, "zeroStockFlag");
            return (Criteria) this;
        }

        public Criteria andZeroStockFlagLessThan(String value) {
            addCriterion("ZERO_STOCK_FLAG <", value, "zeroStockFlag");
            return (Criteria) this;
        }

        public Criteria andZeroStockFlagLessThanOrEqualTo(String value) {
            addCriterion("ZERO_STOCK_FLAG <=", value, "zeroStockFlag");
            return (Criteria) this;
        }

        public Criteria andZeroStockFlagLike(String value) {
            addCriterion("ZERO_STOCK_FLAG like", value, "zeroStockFlag");
            return (Criteria) this;
        }

        public Criteria andZeroStockFlagNotLike(String value) {
            addCriterion("ZERO_STOCK_FLAG not like", value, "zeroStockFlag");
            return (Criteria) this;
        }

        public Criteria andZeroStockFlagIn(List<String> values) {
            addCriterion("ZERO_STOCK_FLAG in", values, "zeroStockFlag");
            return (Criteria) this;
        }

        public Criteria andZeroStockFlagNotIn(List<String> values) {
            addCriterion("ZERO_STOCK_FLAG not in", values, "zeroStockFlag");
            return (Criteria) this;
        }

        public Criteria andZeroStockFlagBetween(String value1, String value2) {
            addCriterion("ZERO_STOCK_FLAG between", value1, value2, "zeroStockFlag");
            return (Criteria) this;
        }

        public Criteria andZeroStockFlagNotBetween(String value1, String value2) {
            addCriterion("ZERO_STOCK_FLAG not between", value1, value2, "zeroStockFlag");
            return (Criteria) this;
        }

        public Criteria andZeroStockStarttimeIsNull() {
            addCriterion("ZERO_STOCK_STARTTIME is null");
            return (Criteria) this;
        }

        public Criteria andZeroStockStarttimeIsNotNull() {
            addCriterion("ZERO_STOCK_STARTTIME is not null");
            return (Criteria) this;
        }

        public Criteria andZeroStockStarttimeEqualTo(Timestamp value) {
            addCriterion("ZERO_STOCK_STARTTIME =", value, "zeroStockStarttime");
            return (Criteria) this;
        }

        public Criteria andZeroStockStarttimeNotEqualTo(Timestamp value) {
            addCriterion("ZERO_STOCK_STARTTIME <>", value, "zeroStockStarttime");
            return (Criteria) this;
        }

        public Criteria andZeroStockStarttimeGreaterThan(Timestamp value) {
            addCriterion("ZERO_STOCK_STARTTIME >", value, "zeroStockStarttime");
            return (Criteria) this;
        }

        public Criteria andZeroStockStarttimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("ZERO_STOCK_STARTTIME >=", value, "zeroStockStarttime");
            return (Criteria) this;
        }

        public Criteria andZeroStockStarttimeLessThan(Timestamp value) {
            addCriterion("ZERO_STOCK_STARTTIME <", value, "zeroStockStarttime");
            return (Criteria) this;
        }

        public Criteria andZeroStockStarttimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("ZERO_STOCK_STARTTIME <=", value, "zeroStockStarttime");
            return (Criteria) this;
        }

        public Criteria andZeroStockStarttimeIn(List<Timestamp> values) {
            addCriterion("ZERO_STOCK_STARTTIME in", values, "zeroStockStarttime");
            return (Criteria) this;
        }

        public Criteria andZeroStockStarttimeNotIn(List<Timestamp> values) {
            addCriterion("ZERO_STOCK_STARTTIME not in", values, "zeroStockStarttime");
            return (Criteria) this;
        }

        public Criteria andZeroStockStarttimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ZERO_STOCK_STARTTIME between", value1, value2, "zeroStockStarttime");
            return (Criteria) this;
        }

        public Criteria andZeroStockStarttimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("ZERO_STOCK_STARTTIME not between", value1, value2, "zeroStockStarttime");
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

        public Criteria andProductNoIsNull() {
            addCriterion("PRODUCT_NO is null");
            return (Criteria) this;
        }

        public Criteria andProductNoIsNotNull() {
            addCriterion("PRODUCT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andProductNoEqualTo(String value) {
            addCriterion("PRODUCT_NO =", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotEqualTo(String value) {
            addCriterion("PRODUCT_NO <>", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoGreaterThan(String value) {
            addCriterion("PRODUCT_NO >", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_NO >=", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoLessThan(String value) {
            addCriterion("PRODUCT_NO <", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_NO <=", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoLike(String value) {
            addCriterion("PRODUCT_NO like", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotLike(String value) {
            addCriterion("PRODUCT_NO not like", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoIn(List<String> values) {
            addCriterion("PRODUCT_NO in", values, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotIn(List<String> values) {
            addCriterion("PRODUCT_NO not in", values, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoBetween(String value1, String value2) {
            addCriterion("PRODUCT_NO between", value1, value2, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_NO not between", value1, value2, "productNo");
            return (Criteria) this;
        }

        public Criteria andCatgPathIsNull() {
            addCriterion("CATG_PATH is null");
            return (Criteria) this;
        }

        public Criteria andCatgPathIsNotNull() {
            addCriterion("CATG_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andCatgPathEqualTo(String value) {
            addCriterion("CATG_PATH =", value, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathNotEqualTo(String value) {
            addCriterion("CATG_PATH <>", value, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathGreaterThan(String value) {
            addCriterion("CATG_PATH >", value, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathGreaterThanOrEqualTo(String value) {
            addCriterion("CATG_PATH >=", value, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathLessThan(String value) {
            addCriterion("CATG_PATH <", value, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathLessThanOrEqualTo(String value) {
            addCriterion("CATG_PATH <=", value, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathLike(String value) {
            addCriterion("CATG_PATH like", value, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathNotLike(String value) {
            addCriterion("CATG_PATH not like", value, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathIn(List<String> values) {
            addCriterion("CATG_PATH in", values, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathNotIn(List<String> values) {
            addCriterion("CATG_PATH not in", values, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathBetween(String value1, String value2) {
            addCriterion("CATG_PATH between", value1, value2, "catgPath");
            return (Criteria) this;
        }

        public Criteria andCatgPathNotBetween(String value1, String value2) {
            addCriterion("CATG_PATH not between", value1, value2, "catgPath");
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
