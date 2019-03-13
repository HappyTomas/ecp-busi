package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GdsShiptempPriceCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsShiptempPriceCriteria() {
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

        public Criteria andPricingListIdIsNull() {
            addCriterion("PRICING_LIST_ID is null");
            return (Criteria) this;
        }

        public Criteria andPricingListIdIsNotNull() {
            addCriterion("PRICING_LIST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPricingListIdEqualTo(Long value) {
            addCriterion("PRICING_LIST_ID =", value, "pricingListId");
            return (Criteria) this;
        }

        public Criteria andPricingListIdNotEqualTo(Long value) {
            addCriterion("PRICING_LIST_ID <>", value, "pricingListId");
            return (Criteria) this;
        }

        public Criteria andPricingListIdGreaterThan(Long value) {
            addCriterion("PRICING_LIST_ID >", value, "pricingListId");
            return (Criteria) this;
        }

        public Criteria andPricingListIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PRICING_LIST_ID >=", value, "pricingListId");
            return (Criteria) this;
        }

        public Criteria andPricingListIdLessThan(Long value) {
            addCriterion("PRICING_LIST_ID <", value, "pricingListId");
            return (Criteria) this;
        }

        public Criteria andPricingListIdLessThanOrEqualTo(Long value) {
            addCriterion("PRICING_LIST_ID <=", value, "pricingListId");
            return (Criteria) this;
        }

        public Criteria andPricingListIdIn(List<Long> values) {
            addCriterion("PRICING_LIST_ID in", values, "pricingListId");
            return (Criteria) this;
        }

        public Criteria andPricingListIdNotIn(List<Long> values) {
            addCriterion("PRICING_LIST_ID not in", values, "pricingListId");
            return (Criteria) this;
        }

        public Criteria andPricingListIdBetween(Long value1, Long value2) {
            addCriterion("PRICING_LIST_ID between", value1, value2, "pricingListId");
            return (Criteria) this;
        }

        public Criteria andPricingListIdNotBetween(Long value1, Long value2) {
            addCriterion("PRICING_LIST_ID not between", value1, value2, "pricingListId");
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

        public Criteria andPricingModeIsNull() {
            addCriterion("PRICING_MODE is null");
            return (Criteria) this;
        }

        public Criteria andPricingModeIsNotNull() {
            addCriterion("PRICING_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andPricingModeEqualTo(String value) {
            addCriterion("PRICING_MODE =", value, "pricingMode");
            return (Criteria) this;
        }

        public Criteria andPricingModeNotEqualTo(String value) {
            addCriterion("PRICING_MODE <>", value, "pricingMode");
            return (Criteria) this;
        }

        public Criteria andPricingModeGreaterThan(String value) {
            addCriterion("PRICING_MODE >", value, "pricingMode");
            return (Criteria) this;
        }

        public Criteria andPricingModeGreaterThanOrEqualTo(String value) {
            addCriterion("PRICING_MODE >=", value, "pricingMode");
            return (Criteria) this;
        }

        public Criteria andPricingModeLessThan(String value) {
            addCriterion("PRICING_MODE <", value, "pricingMode");
            return (Criteria) this;
        }

        public Criteria andPricingModeLessThanOrEqualTo(String value) {
            addCriterion("PRICING_MODE <=", value, "pricingMode");
            return (Criteria) this;
        }

        public Criteria andPricingModeLike(String value) {
            addCriterion("PRICING_MODE like", value, "pricingMode");
            return (Criteria) this;
        }

        public Criteria andPricingModeNotLike(String value) {
            addCriterion("PRICING_MODE not like", value, "pricingMode");
            return (Criteria) this;
        }

        public Criteria andPricingModeIn(List<String> values) {
            addCriterion("PRICING_MODE in", values, "pricingMode");
            return (Criteria) this;
        }

        public Criteria andPricingModeNotIn(List<String> values) {
            addCriterion("PRICING_MODE not in", values, "pricingMode");
            return (Criteria) this;
        }

        public Criteria andPricingModeBetween(String value1, String value2) {
            addCriterion("PRICING_MODE between", value1, value2, "pricingMode");
            return (Criteria) this;
        }

        public Criteria andPricingModeNotBetween(String value1, String value2) {
            addCriterion("PRICING_MODE not between", value1, value2, "pricingMode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIsNull() {
            addCriterion("COUNTRY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIsNotNull() {
            addCriterion("COUNTRY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCountryCodeEqualTo(String value) {
            addCriterion("COUNTRY_CODE =", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotEqualTo(String value) {
            addCriterion("COUNTRY_CODE <>", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeGreaterThan(String value) {
            addCriterion("COUNTRY_CODE >", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("COUNTRY_CODE >=", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLessThan(String value) {
            addCriterion("COUNTRY_CODE <", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLessThanOrEqualTo(String value) {
            addCriterion("COUNTRY_CODE <=", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLike(String value) {
            addCriterion("COUNTRY_CODE like", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotLike(String value) {
            addCriterion("COUNTRY_CODE not like", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIn(List<String> values) {
            addCriterion("COUNTRY_CODE in", values, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotIn(List<String> values) {
            addCriterion("COUNTRY_CODE not in", values, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeBetween(String value1, String value2) {
            addCriterion("COUNTRY_CODE between", value1, value2, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotBetween(String value1, String value2) {
            addCriterion("COUNTRY_CODE not between", value1, value2, "countryCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIsNull() {
            addCriterion("PROVINCE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIsNotNull() {
            addCriterion("PROVINCE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeEqualTo(String value) {
            addCriterion("PROVINCE_CODE =", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotEqualTo(String value) {
            addCriterion("PROVINCE_CODE <>", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeGreaterThan(String value) {
            addCriterion("PROVINCE_CODE >", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PROVINCE_CODE >=", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLessThan(String value) {
            addCriterion("PROVINCE_CODE <", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLessThanOrEqualTo(String value) {
            addCriterion("PROVINCE_CODE <=", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeLike(String value) {
            addCriterion("PROVINCE_CODE like", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotLike(String value) {
            addCriterion("PROVINCE_CODE not like", value, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeIn(List<String> values) {
            addCriterion("PROVINCE_CODE in", values, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotIn(List<String> values) {
            addCriterion("PROVINCE_CODE not in", values, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeBetween(String value1, String value2) {
            addCriterion("PROVINCE_CODE between", value1, value2, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andProvinceCodeNotBetween(String value1, String value2) {
            addCriterion("PROVINCE_CODE not between", value1, value2, "provinceCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNull() {
            addCriterion("CITY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNotNull() {
            addCriterion("CITY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCityCodeEqualTo(String value) {
            addCriterion("CITY_CODE =", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotEqualTo(String value) {
            addCriterion("CITY_CODE <>", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThan(String value) {
            addCriterion("CITY_CODE >", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_CODE >=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThan(String value) {
            addCriterion("CITY_CODE <", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThanOrEqualTo(String value) {
            addCriterion("CITY_CODE <=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLike(String value) {
            addCriterion("CITY_CODE like", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotLike(String value) {
            addCriterion("CITY_CODE not like", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeIn(List<String> values) {
            addCriterion("CITY_CODE in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotIn(List<String> values) {
            addCriterion("CITY_CODE not in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeBetween(String value1, String value2) {
            addCriterion("CITY_CODE between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotBetween(String value1, String value2) {
            addCriterion("CITY_CODE not between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeIsNull() {
            addCriterion("COUNTY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCountyCodeIsNotNull() {
            addCriterion("COUNTY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCountyCodeEqualTo(String value) {
            addCriterion("COUNTY_CODE =", value, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeNotEqualTo(String value) {
            addCriterion("COUNTY_CODE <>", value, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeGreaterThan(String value) {
            addCriterion("COUNTY_CODE >", value, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("COUNTY_CODE >=", value, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeLessThan(String value) {
            addCriterion("COUNTY_CODE <", value, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeLessThanOrEqualTo(String value) {
            addCriterion("COUNTY_CODE <=", value, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeLike(String value) {
            addCriterion("COUNTY_CODE like", value, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeNotLike(String value) {
            addCriterion("COUNTY_CODE not like", value, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeIn(List<String> values) {
            addCriterion("COUNTY_CODE in", values, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeNotIn(List<String> values) {
            addCriterion("COUNTY_CODE not in", values, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeBetween(String value1, String value2) {
            addCriterion("COUNTY_CODE between", value1, value2, "countyCode");
            return (Criteria) this;
        }

        public Criteria andCountyCodeNotBetween(String value1, String value2) {
            addCriterion("COUNTY_CODE not between", value1, value2, "countyCode");
            return (Criteria) this;
        }

        public Criteria andAreaLevelIsNull() {
            addCriterion("AREA_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andAreaLevelIsNotNull() {
            addCriterion("AREA_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andAreaLevelEqualTo(Long value) {
            addCriterion("AREA_LEVEL =", value, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelNotEqualTo(Long value) {
            addCriterion("AREA_LEVEL <>", value, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelGreaterThan(Long value) {
            addCriterion("AREA_LEVEL >", value, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelGreaterThanOrEqualTo(Long value) {
            addCriterion("AREA_LEVEL >=", value, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelLessThan(Long value) {
            addCriterion("AREA_LEVEL <", value, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelLessThanOrEqualTo(Long value) {
            addCriterion("AREA_LEVEL <=", value, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelIn(List<Long> values) {
            addCriterion("AREA_LEVEL in", values, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelNotIn(List<Long> values) {
            addCriterion("AREA_LEVEL not in", values, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelBetween(Long value1, Long value2) {
            addCriterion("AREA_LEVEL between", value1, value2, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andAreaLevelNotBetween(Long value1, Long value2) {
            addCriterion("AREA_LEVEL not between", value1, value2, "areaLevel");
            return (Criteria) this;
        }

        public Criteria andFirstAmountIsNull() {
            addCriterion("FIRST_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andFirstAmountIsNotNull() {
            addCriterion("FIRST_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andFirstAmountEqualTo(Long value) {
            addCriterion("FIRST_AMOUNT =", value, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountNotEqualTo(Long value) {
            addCriterion("FIRST_AMOUNT <>", value, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountGreaterThan(Long value) {
            addCriterion("FIRST_AMOUNT >", value, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("FIRST_AMOUNT >=", value, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountLessThan(Long value) {
            addCriterion("FIRST_AMOUNT <", value, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountLessThanOrEqualTo(Long value) {
            addCriterion("FIRST_AMOUNT <=", value, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountIn(List<Long> values) {
            addCriterion("FIRST_AMOUNT in", values, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountNotIn(List<Long> values) {
            addCriterion("FIRST_AMOUNT not in", values, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountBetween(Long value1, Long value2) {
            addCriterion("FIRST_AMOUNT between", value1, value2, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstAmountNotBetween(Long value1, Long value2) {
            addCriterion("FIRST_AMOUNT not between", value1, value2, "firstAmount");
            return (Criteria) this;
        }

        public Criteria andFirstPriceIsNull() {
            addCriterion("FIRST_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andFirstPriceIsNotNull() {
            addCriterion("FIRST_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andFirstPriceEqualTo(Long value) {
            addCriterion("FIRST_PRICE =", value, "firstPrice");
            return (Criteria) this;
        }

        public Criteria andFirstPriceNotEqualTo(Long value) {
            addCriterion("FIRST_PRICE <>", value, "firstPrice");
            return (Criteria) this;
        }

        public Criteria andFirstPriceGreaterThan(Long value) {
            addCriterion("FIRST_PRICE >", value, "firstPrice");
            return (Criteria) this;
        }

        public Criteria andFirstPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("FIRST_PRICE >=", value, "firstPrice");
            return (Criteria) this;
        }

        public Criteria andFirstPriceLessThan(Long value) {
            addCriterion("FIRST_PRICE <", value, "firstPrice");
            return (Criteria) this;
        }

        public Criteria andFirstPriceLessThanOrEqualTo(Long value) {
            addCriterion("FIRST_PRICE <=", value, "firstPrice");
            return (Criteria) this;
        }

        public Criteria andFirstPriceIn(List<Long> values) {
            addCriterion("FIRST_PRICE in", values, "firstPrice");
            return (Criteria) this;
        }

        public Criteria andFirstPriceNotIn(List<Long> values) {
            addCriterion("FIRST_PRICE not in", values, "firstPrice");
            return (Criteria) this;
        }

        public Criteria andFirstPriceBetween(Long value1, Long value2) {
            addCriterion("FIRST_PRICE between", value1, value2, "firstPrice");
            return (Criteria) this;
        }

        public Criteria andFirstPriceNotBetween(Long value1, Long value2) {
            addCriterion("FIRST_PRICE not between", value1, value2, "firstPrice");
            return (Criteria) this;
        }

        public Criteria andContinueAmountIsNull() {
            addCriterion("CONTINUE_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andContinueAmountIsNotNull() {
            addCriterion("CONTINUE_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andContinueAmountEqualTo(Long value) {
            addCriterion("CONTINUE_AMOUNT =", value, "continueAmount");
            return (Criteria) this;
        }

        public Criteria andContinueAmountNotEqualTo(Long value) {
            addCriterion("CONTINUE_AMOUNT <>", value, "continueAmount");
            return (Criteria) this;
        }

        public Criteria andContinueAmountGreaterThan(Long value) {
            addCriterion("CONTINUE_AMOUNT >", value, "continueAmount");
            return (Criteria) this;
        }

        public Criteria andContinueAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("CONTINUE_AMOUNT >=", value, "continueAmount");
            return (Criteria) this;
        }

        public Criteria andContinueAmountLessThan(Long value) {
            addCriterion("CONTINUE_AMOUNT <", value, "continueAmount");
            return (Criteria) this;
        }

        public Criteria andContinueAmountLessThanOrEqualTo(Long value) {
            addCriterion("CONTINUE_AMOUNT <=", value, "continueAmount");
            return (Criteria) this;
        }

        public Criteria andContinueAmountIn(List<Long> values) {
            addCriterion("CONTINUE_AMOUNT in", values, "continueAmount");
            return (Criteria) this;
        }

        public Criteria andContinueAmountNotIn(List<Long> values) {
            addCriterion("CONTINUE_AMOUNT not in", values, "continueAmount");
            return (Criteria) this;
        }

        public Criteria andContinueAmountBetween(Long value1, Long value2) {
            addCriterion("CONTINUE_AMOUNT between", value1, value2, "continueAmount");
            return (Criteria) this;
        }

        public Criteria andContinueAmountNotBetween(Long value1, Long value2) {
            addCriterion("CONTINUE_AMOUNT not between", value1, value2, "continueAmount");
            return (Criteria) this;
        }

        public Criteria andContinuePriceIsNull() {
            addCriterion("CONTINUE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andContinuePriceIsNotNull() {
            addCriterion("CONTINUE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andContinuePriceEqualTo(Long value) {
            addCriterion("CONTINUE_PRICE =", value, "continuePrice");
            return (Criteria) this;
        }

        public Criteria andContinuePriceNotEqualTo(Long value) {
            addCriterion("CONTINUE_PRICE <>", value, "continuePrice");
            return (Criteria) this;
        }

        public Criteria andContinuePriceGreaterThan(Long value) {
            addCriterion("CONTINUE_PRICE >", value, "continuePrice");
            return (Criteria) this;
        }

        public Criteria andContinuePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("CONTINUE_PRICE >=", value, "continuePrice");
            return (Criteria) this;
        }

        public Criteria andContinuePriceLessThan(Long value) {
            addCriterion("CONTINUE_PRICE <", value, "continuePrice");
            return (Criteria) this;
        }

        public Criteria andContinuePriceLessThanOrEqualTo(Long value) {
            addCriterion("CONTINUE_PRICE <=", value, "continuePrice");
            return (Criteria) this;
        }

        public Criteria andContinuePriceIn(List<Long> values) {
            addCriterion("CONTINUE_PRICE in", values, "continuePrice");
            return (Criteria) this;
        }

        public Criteria andContinuePriceNotIn(List<Long> values) {
            addCriterion("CONTINUE_PRICE not in", values, "continuePrice");
            return (Criteria) this;
        }

        public Criteria andContinuePriceBetween(Long value1, Long value2) {
            addCriterion("CONTINUE_PRICE between", value1, value2, "continuePrice");
            return (Criteria) this;
        }

        public Criteria andContinuePriceNotBetween(Long value1, Long value2) {
            addCriterion("CONTINUE_PRICE not between", value1, value2, "continuePrice");
            return (Criteria) this;
        }

        public Criteria andFreeAmountIsNull() {
            addCriterion("FREE_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andFreeAmountIsNotNull() {
            addCriterion("FREE_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andFreeAmountEqualTo(Long value) {
            addCriterion("FREE_AMOUNT =", value, "freeAmount");
            return (Criteria) this;
        }

        public Criteria andFreeAmountNotEqualTo(Long value) {
            addCriterion("FREE_AMOUNT <>", value, "freeAmount");
            return (Criteria) this;
        }

        public Criteria andFreeAmountGreaterThan(Long value) {
            addCriterion("FREE_AMOUNT >", value, "freeAmount");
            return (Criteria) this;
        }

        public Criteria andFreeAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("FREE_AMOUNT >=", value, "freeAmount");
            return (Criteria) this;
        }

        public Criteria andFreeAmountLessThan(Long value) {
            addCriterion("FREE_AMOUNT <", value, "freeAmount");
            return (Criteria) this;
        }

        public Criteria andFreeAmountLessThanOrEqualTo(Long value) {
            addCriterion("FREE_AMOUNT <=", value, "freeAmount");
            return (Criteria) this;
        }

        public Criteria andFreeAmountIn(List<Long> values) {
            addCriterion("FREE_AMOUNT in", values, "freeAmount");
            return (Criteria) this;
        }

        public Criteria andFreeAmountNotIn(List<Long> values) {
            addCriterion("FREE_AMOUNT not in", values, "freeAmount");
            return (Criteria) this;
        }

        public Criteria andFreeAmountBetween(Long value1, Long value2) {
            addCriterion("FREE_AMOUNT between", value1, value2, "freeAmount");
            return (Criteria) this;
        }

        public Criteria andFreeAmountNotBetween(Long value1, Long value2) {
            addCriterion("FREE_AMOUNT not between", value1, value2, "freeAmount");
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
