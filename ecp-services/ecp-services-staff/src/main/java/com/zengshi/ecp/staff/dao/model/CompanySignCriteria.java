package com.zengshi.ecp.staff.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CompanySignCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CompanySignCriteria() {
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

        public Criteria andCompanyAdressIsNull() {
            addCriterion("COMPANY_ADRESS is null");
            return (Criteria) this;
        }

        public Criteria andCompanyAdressIsNotNull() {
            addCriterion("COMPANY_ADRESS is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyAdressEqualTo(String value) {
            addCriterion("COMPANY_ADRESS =", value, "companyAdress");
            return (Criteria) this;
        }

        public Criteria andCompanyAdressNotEqualTo(String value) {
            addCriterion("COMPANY_ADRESS <>", value, "companyAdress");
            return (Criteria) this;
        }

        public Criteria andCompanyAdressGreaterThan(String value) {
            addCriterion("COMPANY_ADRESS >", value, "companyAdress");
            return (Criteria) this;
        }

        public Criteria andCompanyAdressGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_ADRESS >=", value, "companyAdress");
            return (Criteria) this;
        }

        public Criteria andCompanyAdressLessThan(String value) {
            addCriterion("COMPANY_ADRESS <", value, "companyAdress");
            return (Criteria) this;
        }

        public Criteria andCompanyAdressLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_ADRESS <=", value, "companyAdress");
            return (Criteria) this;
        }

        public Criteria andCompanyAdressLike(String value) {
            addCriterion("COMPANY_ADRESS like", value, "companyAdress");
            return (Criteria) this;
        }

        public Criteria andCompanyAdressNotLike(String value) {
            addCriterion("COMPANY_ADRESS not like", value, "companyAdress");
            return (Criteria) this;
        }

        public Criteria andCompanyAdressIn(List<String> values) {
            addCriterion("COMPANY_ADRESS in", values, "companyAdress");
            return (Criteria) this;
        }

        public Criteria andCompanyAdressNotIn(List<String> values) {
            addCriterion("COMPANY_ADRESS not in", values, "companyAdress");
            return (Criteria) this;
        }

        public Criteria andCompanyAdressBetween(String value1, String value2) {
            addCriterion("COMPANY_ADRESS between", value1, value2, "companyAdress");
            return (Criteria) this;
        }

        public Criteria andCompanyAdressNotBetween(String value1, String value2) {
            addCriterion("COMPANY_ADRESS not between", value1, value2, "companyAdress");
            return (Criteria) this;
        }

        public Criteria andTradeIsNull() {
            addCriterion("TRADE is null");
            return (Criteria) this;
        }

        public Criteria andTradeIsNotNull() {
            addCriterion("TRADE is not null");
            return (Criteria) this;
        }

        public Criteria andTradeEqualTo(String value) {
            addCriterion("TRADE =", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeNotEqualTo(String value) {
            addCriterion("TRADE <>", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeGreaterThan(String value) {
            addCriterion("TRADE >", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeGreaterThanOrEqualTo(String value) {
            addCriterion("TRADE >=", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeLessThan(String value) {
            addCriterion("TRADE <", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeLessThanOrEqualTo(String value) {
            addCriterion("TRADE <=", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeLike(String value) {
            addCriterion("TRADE like", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeNotLike(String value) {
            addCriterion("TRADE not like", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeIn(List<String> values) {
            addCriterion("TRADE in", values, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeNotIn(List<String> values) {
            addCriterion("TRADE not in", values, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeBetween(String value1, String value2) {
            addCriterion("TRADE between", value1, value2, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeNotBetween(String value1, String value2) {
            addCriterion("TRADE not between", value1, value2, "trade");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumIsNull() {
            addCriterion("EMPLOYEE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumIsNotNull() {
            addCriterion("EMPLOYEE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumEqualTo(String value) {
            addCriterion("EMPLOYEE_NUM =", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumNotEqualTo(String value) {
            addCriterion("EMPLOYEE_NUM <>", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumGreaterThan(String value) {
            addCriterion("EMPLOYEE_NUM >", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumGreaterThanOrEqualTo(String value) {
            addCriterion("EMPLOYEE_NUM >=", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumLessThan(String value) {
            addCriterion("EMPLOYEE_NUM <", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumLessThanOrEqualTo(String value) {
            addCriterion("EMPLOYEE_NUM <=", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumLike(String value) {
            addCriterion("EMPLOYEE_NUM like", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumNotLike(String value) {
            addCriterion("EMPLOYEE_NUM not like", value, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumIn(List<String> values) {
            addCriterion("EMPLOYEE_NUM in", values, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumNotIn(List<String> values) {
            addCriterion("EMPLOYEE_NUM not in", values, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumBetween(String value1, String value2) {
            addCriterion("EMPLOYEE_NUM between", value1, value2, "employeeNum");
            return (Criteria) this;
        }

        public Criteria andEmployeeNumNotBetween(String value1, String value2) {
            addCriterion("EMPLOYEE_NUM not between", value1, value2, "employeeNum");
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

        public Criteria andCompanyEmailIsNull() {
            addCriterion("COMPANY_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andCompanyEmailIsNotNull() {
            addCriterion("COMPANY_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEmailEqualTo(String value) {
            addCriterion("COMPANY_EMAIL =", value, "companyEmail");
            return (Criteria) this;
        }

        public Criteria andCompanyEmailNotEqualTo(String value) {
            addCriterion("COMPANY_EMAIL <>", value, "companyEmail");
            return (Criteria) this;
        }

        public Criteria andCompanyEmailGreaterThan(String value) {
            addCriterion("COMPANY_EMAIL >", value, "companyEmail");
            return (Criteria) this;
        }

        public Criteria andCompanyEmailGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_EMAIL >=", value, "companyEmail");
            return (Criteria) this;
        }

        public Criteria andCompanyEmailLessThan(String value) {
            addCriterion("COMPANY_EMAIL <", value, "companyEmail");
            return (Criteria) this;
        }

        public Criteria andCompanyEmailLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_EMAIL <=", value, "companyEmail");
            return (Criteria) this;
        }

        public Criteria andCompanyEmailLike(String value) {
            addCriterion("COMPANY_EMAIL like", value, "companyEmail");
            return (Criteria) this;
        }

        public Criteria andCompanyEmailNotLike(String value) {
            addCriterion("COMPANY_EMAIL not like", value, "companyEmail");
            return (Criteria) this;
        }

        public Criteria andCompanyEmailIn(List<String> values) {
            addCriterion("COMPANY_EMAIL in", values, "companyEmail");
            return (Criteria) this;
        }

        public Criteria andCompanyEmailNotIn(List<String> values) {
            addCriterion("COMPANY_EMAIL not in", values, "companyEmail");
            return (Criteria) this;
        }

        public Criteria andCompanyEmailBetween(String value1, String value2) {
            addCriterion("COMPANY_EMAIL between", value1, value2, "companyEmail");
            return (Criteria) this;
        }

        public Criteria andCompanyEmailNotBetween(String value1, String value2) {
            addCriterion("COMPANY_EMAIL not between", value1, value2, "companyEmail");
            return (Criteria) this;
        }

        public Criteria andLinkPersonMsgIsNull() {
            addCriterion("LINK_PERSON_MSG is null");
            return (Criteria) this;
        }

        public Criteria andLinkPersonMsgIsNotNull() {
            addCriterion("LINK_PERSON_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andLinkPersonMsgEqualTo(String value) {
            addCriterion("LINK_PERSON_MSG =", value, "linkPersonMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPersonMsgNotEqualTo(String value) {
            addCriterion("LINK_PERSON_MSG <>", value, "linkPersonMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPersonMsgGreaterThan(String value) {
            addCriterion("LINK_PERSON_MSG >", value, "linkPersonMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPersonMsgGreaterThanOrEqualTo(String value) {
            addCriterion("LINK_PERSON_MSG >=", value, "linkPersonMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPersonMsgLessThan(String value) {
            addCriterion("LINK_PERSON_MSG <", value, "linkPersonMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPersonMsgLessThanOrEqualTo(String value) {
            addCriterion("LINK_PERSON_MSG <=", value, "linkPersonMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPersonMsgLike(String value) {
            addCriterion("LINK_PERSON_MSG like", value, "linkPersonMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPersonMsgNotLike(String value) {
            addCriterion("LINK_PERSON_MSG not like", value, "linkPersonMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPersonMsgIn(List<String> values) {
            addCriterion("LINK_PERSON_MSG in", values, "linkPersonMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPersonMsgNotIn(List<String> values) {
            addCriterion("LINK_PERSON_MSG not in", values, "linkPersonMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPersonMsgBetween(String value1, String value2) {
            addCriterion("LINK_PERSON_MSG between", value1, value2, "linkPersonMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPersonMsgNotBetween(String value1, String value2) {
            addCriterion("LINK_PERSON_MSG not between", value1, value2, "linkPersonMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneMsgIsNull() {
            addCriterion("LINK_PHONE_MSG is null");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneMsgIsNotNull() {
            addCriterion("LINK_PHONE_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneMsgEqualTo(String value) {
            addCriterion("LINK_PHONE_MSG =", value, "linkPhoneMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneMsgNotEqualTo(String value) {
            addCriterion("LINK_PHONE_MSG <>", value, "linkPhoneMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneMsgGreaterThan(String value) {
            addCriterion("LINK_PHONE_MSG >", value, "linkPhoneMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneMsgGreaterThanOrEqualTo(String value) {
            addCriterion("LINK_PHONE_MSG >=", value, "linkPhoneMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneMsgLessThan(String value) {
            addCriterion("LINK_PHONE_MSG <", value, "linkPhoneMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneMsgLessThanOrEqualTo(String value) {
            addCriterion("LINK_PHONE_MSG <=", value, "linkPhoneMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneMsgLike(String value) {
            addCriterion("LINK_PHONE_MSG like", value, "linkPhoneMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneMsgNotLike(String value) {
            addCriterion("LINK_PHONE_MSG not like", value, "linkPhoneMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneMsgIn(List<String> values) {
            addCriterion("LINK_PHONE_MSG in", values, "linkPhoneMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneMsgNotIn(List<String> values) {
            addCriterion("LINK_PHONE_MSG not in", values, "linkPhoneMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneMsgBetween(String value1, String value2) {
            addCriterion("LINK_PHONE_MSG between", value1, value2, "linkPhoneMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneMsgNotBetween(String value1, String value2) {
            addCriterion("LINK_PHONE_MSG not between", value1, value2, "linkPhoneMsg");
            return (Criteria) this;
        }

        public Criteria andLinkPsnCardIsNull() {
            addCriterion("LINK_PSN_CARD is null");
            return (Criteria) this;
        }

        public Criteria andLinkPsnCardIsNotNull() {
            addCriterion("LINK_PSN_CARD is not null");
            return (Criteria) this;
        }

        public Criteria andLinkPsnCardEqualTo(String value) {
            addCriterion("LINK_PSN_CARD =", value, "linkPsnCard");
            return (Criteria) this;
        }

        public Criteria andLinkPsnCardNotEqualTo(String value) {
            addCriterion("LINK_PSN_CARD <>", value, "linkPsnCard");
            return (Criteria) this;
        }

        public Criteria andLinkPsnCardGreaterThan(String value) {
            addCriterion("LINK_PSN_CARD >", value, "linkPsnCard");
            return (Criteria) this;
        }

        public Criteria andLinkPsnCardGreaterThanOrEqualTo(String value) {
            addCriterion("LINK_PSN_CARD >=", value, "linkPsnCard");
            return (Criteria) this;
        }

        public Criteria andLinkPsnCardLessThan(String value) {
            addCriterion("LINK_PSN_CARD <", value, "linkPsnCard");
            return (Criteria) this;
        }

        public Criteria andLinkPsnCardLessThanOrEqualTo(String value) {
            addCriterion("LINK_PSN_CARD <=", value, "linkPsnCard");
            return (Criteria) this;
        }

        public Criteria andLinkPsnCardLike(String value) {
            addCriterion("LINK_PSN_CARD like", value, "linkPsnCard");
            return (Criteria) this;
        }

        public Criteria andLinkPsnCardNotLike(String value) {
            addCriterion("LINK_PSN_CARD not like", value, "linkPsnCard");
            return (Criteria) this;
        }

        public Criteria andLinkPsnCardIn(List<String> values) {
            addCriterion("LINK_PSN_CARD in", values, "linkPsnCard");
            return (Criteria) this;
        }

        public Criteria andLinkPsnCardNotIn(List<String> values) {
            addCriterion("LINK_PSN_CARD not in", values, "linkPsnCard");
            return (Criteria) this;
        }

        public Criteria andLinkPsnCardBetween(String value1, String value2) {
            addCriterion("LINK_PSN_CARD between", value1, value2, "linkPsnCard");
            return (Criteria) this;
        }

        public Criteria andLinkPsnCardNotBetween(String value1, String value2) {
            addCriterion("LINK_PSN_CARD not between", value1, value2, "linkPsnCard");
            return (Criteria) this;
        }

        public Criteria andLinkTelephoneIsNull() {
            addCriterion("LINK_TELEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andLinkTelephoneIsNotNull() {
            addCriterion("LINK_TELEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andLinkTelephoneEqualTo(String value) {
            addCriterion("LINK_TELEPHONE =", value, "linkTelephone");
            return (Criteria) this;
        }

        public Criteria andLinkTelephoneNotEqualTo(String value) {
            addCriterion("LINK_TELEPHONE <>", value, "linkTelephone");
            return (Criteria) this;
        }

        public Criteria andLinkTelephoneGreaterThan(String value) {
            addCriterion("LINK_TELEPHONE >", value, "linkTelephone");
            return (Criteria) this;
        }

        public Criteria andLinkTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("LINK_TELEPHONE >=", value, "linkTelephone");
            return (Criteria) this;
        }

        public Criteria andLinkTelephoneLessThan(String value) {
            addCriterion("LINK_TELEPHONE <", value, "linkTelephone");
            return (Criteria) this;
        }

        public Criteria andLinkTelephoneLessThanOrEqualTo(String value) {
            addCriterion("LINK_TELEPHONE <=", value, "linkTelephone");
            return (Criteria) this;
        }

        public Criteria andLinkTelephoneLike(String value) {
            addCriterion("LINK_TELEPHONE like", value, "linkTelephone");
            return (Criteria) this;
        }

        public Criteria andLinkTelephoneNotLike(String value) {
            addCriterion("LINK_TELEPHONE not like", value, "linkTelephone");
            return (Criteria) this;
        }

        public Criteria andLinkTelephoneIn(List<String> values) {
            addCriterion("LINK_TELEPHONE in", values, "linkTelephone");
            return (Criteria) this;
        }

        public Criteria andLinkTelephoneNotIn(List<String> values) {
            addCriterion("LINK_TELEPHONE not in", values, "linkTelephone");
            return (Criteria) this;
        }

        public Criteria andLinkTelephoneBetween(String value1, String value2) {
            addCriterion("LINK_TELEPHONE between", value1, value2, "linkTelephone");
            return (Criteria) this;
        }

        public Criteria andLinkTelephoneNotBetween(String value1, String value2) {
            addCriterion("LINK_TELEPHONE not between", value1, value2, "linkTelephone");
            return (Criteria) this;
        }

        public Criteria andCompanyWeixinIsNull() {
            addCriterion("COMPANY_WEIXIN is null");
            return (Criteria) this;
        }

        public Criteria andCompanyWeixinIsNotNull() {
            addCriterion("COMPANY_WEIXIN is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyWeixinEqualTo(String value) {
            addCriterion("COMPANY_WEIXIN =", value, "companyWeixin");
            return (Criteria) this;
        }

        public Criteria andCompanyWeixinNotEqualTo(String value) {
            addCriterion("COMPANY_WEIXIN <>", value, "companyWeixin");
            return (Criteria) this;
        }

        public Criteria andCompanyWeixinGreaterThan(String value) {
            addCriterion("COMPANY_WEIXIN >", value, "companyWeixin");
            return (Criteria) this;
        }

        public Criteria andCompanyWeixinGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_WEIXIN >=", value, "companyWeixin");
            return (Criteria) this;
        }

        public Criteria andCompanyWeixinLessThan(String value) {
            addCriterion("COMPANY_WEIXIN <", value, "companyWeixin");
            return (Criteria) this;
        }

        public Criteria andCompanyWeixinLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_WEIXIN <=", value, "companyWeixin");
            return (Criteria) this;
        }

        public Criteria andCompanyWeixinLike(String value) {
            addCriterion("COMPANY_WEIXIN like", value, "companyWeixin");
            return (Criteria) this;
        }

        public Criteria andCompanyWeixinNotLike(String value) {
            addCriterion("COMPANY_WEIXIN not like", value, "companyWeixin");
            return (Criteria) this;
        }

        public Criteria andCompanyWeixinIn(List<String> values) {
            addCriterion("COMPANY_WEIXIN in", values, "companyWeixin");
            return (Criteria) this;
        }

        public Criteria andCompanyWeixinNotIn(List<String> values) {
            addCriterion("COMPANY_WEIXIN not in", values, "companyWeixin");
            return (Criteria) this;
        }

        public Criteria andCompanyWeixinBetween(String value1, String value2) {
            addCriterion("COMPANY_WEIXIN between", value1, value2, "companyWeixin");
            return (Criteria) this;
        }

        public Criteria andCompanyWeixinNotBetween(String value1, String value2) {
            addCriterion("COMPANY_WEIXIN not between", value1, value2, "companyWeixin");
            return (Criteria) this;
        }

        public Criteria andCompanyQqIsNull() {
            addCriterion("COMPANY_QQ is null");
            return (Criteria) this;
        }

        public Criteria andCompanyQqIsNotNull() {
            addCriterion("COMPANY_QQ is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyQqEqualTo(String value) {
            addCriterion("COMPANY_QQ =", value, "companyQq");
            return (Criteria) this;
        }

        public Criteria andCompanyQqNotEqualTo(String value) {
            addCriterion("COMPANY_QQ <>", value, "companyQq");
            return (Criteria) this;
        }

        public Criteria andCompanyQqGreaterThan(String value) {
            addCriterion("COMPANY_QQ >", value, "companyQq");
            return (Criteria) this;
        }

        public Criteria andCompanyQqGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_QQ >=", value, "companyQq");
            return (Criteria) this;
        }

        public Criteria andCompanyQqLessThan(String value) {
            addCriterion("COMPANY_QQ <", value, "companyQq");
            return (Criteria) this;
        }

        public Criteria andCompanyQqLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_QQ <=", value, "companyQq");
            return (Criteria) this;
        }

        public Criteria andCompanyQqLike(String value) {
            addCriterion("COMPANY_QQ like", value, "companyQq");
            return (Criteria) this;
        }

        public Criteria andCompanyQqNotLike(String value) {
            addCriterion("COMPANY_QQ not like", value, "companyQq");
            return (Criteria) this;
        }

        public Criteria andCompanyQqIn(List<String> values) {
            addCriterion("COMPANY_QQ in", values, "companyQq");
            return (Criteria) this;
        }

        public Criteria andCompanyQqNotIn(List<String> values) {
            addCriterion("COMPANY_QQ not in", values, "companyQq");
            return (Criteria) this;
        }

        public Criteria andCompanyQqBetween(String value1, String value2) {
            addCriterion("COMPANY_QQ between", value1, value2, "companyQq");
            return (Criteria) this;
        }

        public Criteria andCompanyQqNotBetween(String value1, String value2) {
            addCriterion("COMPANY_QQ not between", value1, value2, "companyQq");
            return (Criteria) this;
        }

        public Criteria andLicencePathIsNull() {
            addCriterion("LICENCE_PATH is null");
            return (Criteria) this;
        }

        public Criteria andLicencePathIsNotNull() {
            addCriterion("LICENCE_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andLicencePathEqualTo(String value) {
            addCriterion("LICENCE_PATH =", value, "licencePath");
            return (Criteria) this;
        }

        public Criteria andLicencePathNotEqualTo(String value) {
            addCriterion("LICENCE_PATH <>", value, "licencePath");
            return (Criteria) this;
        }

        public Criteria andLicencePathGreaterThan(String value) {
            addCriterion("LICENCE_PATH >", value, "licencePath");
            return (Criteria) this;
        }

        public Criteria andLicencePathGreaterThanOrEqualTo(String value) {
            addCriterion("LICENCE_PATH >=", value, "licencePath");
            return (Criteria) this;
        }

        public Criteria andLicencePathLessThan(String value) {
            addCriterion("LICENCE_PATH <", value, "licencePath");
            return (Criteria) this;
        }

        public Criteria andLicencePathLessThanOrEqualTo(String value) {
            addCriterion("LICENCE_PATH <=", value, "licencePath");
            return (Criteria) this;
        }

        public Criteria andLicencePathLike(String value) {
            addCriterion("LICENCE_PATH like", value, "licencePath");
            return (Criteria) this;
        }

        public Criteria andLicencePathNotLike(String value) {
            addCriterion("LICENCE_PATH not like", value, "licencePath");
            return (Criteria) this;
        }

        public Criteria andLicencePathIn(List<String> values) {
            addCriterion("LICENCE_PATH in", values, "licencePath");
            return (Criteria) this;
        }

        public Criteria andLicencePathNotIn(List<String> values) {
            addCriterion("LICENCE_PATH not in", values, "licencePath");
            return (Criteria) this;
        }

        public Criteria andLicencePathBetween(String value1, String value2) {
            addCriterion("LICENCE_PATH between", value1, value2, "licencePath");
            return (Criteria) this;
        }

        public Criteria andLicencePathNotBetween(String value1, String value2) {
            addCriterion("LICENCE_PATH not between", value1, value2, "licencePath");
            return (Criteria) this;
        }

        public Criteria andTaxPathIsNull() {
            addCriterion("TAX_PATH is null");
            return (Criteria) this;
        }

        public Criteria andTaxPathIsNotNull() {
            addCriterion("TAX_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andTaxPathEqualTo(String value) {
            addCriterion("TAX_PATH =", value, "taxPath");
            return (Criteria) this;
        }

        public Criteria andTaxPathNotEqualTo(String value) {
            addCriterion("TAX_PATH <>", value, "taxPath");
            return (Criteria) this;
        }

        public Criteria andTaxPathGreaterThan(String value) {
            addCriterion("TAX_PATH >", value, "taxPath");
            return (Criteria) this;
        }

        public Criteria andTaxPathGreaterThanOrEqualTo(String value) {
            addCriterion("TAX_PATH >=", value, "taxPath");
            return (Criteria) this;
        }

        public Criteria andTaxPathLessThan(String value) {
            addCriterion("TAX_PATH <", value, "taxPath");
            return (Criteria) this;
        }

        public Criteria andTaxPathLessThanOrEqualTo(String value) {
            addCriterion("TAX_PATH <=", value, "taxPath");
            return (Criteria) this;
        }

        public Criteria andTaxPathLike(String value) {
            addCriterion("TAX_PATH like", value, "taxPath");
            return (Criteria) this;
        }

        public Criteria andTaxPathNotLike(String value) {
            addCriterion("TAX_PATH not like", value, "taxPath");
            return (Criteria) this;
        }

        public Criteria andTaxPathIn(List<String> values) {
            addCriterion("TAX_PATH in", values, "taxPath");
            return (Criteria) this;
        }

        public Criteria andTaxPathNotIn(List<String> values) {
            addCriterion("TAX_PATH not in", values, "taxPath");
            return (Criteria) this;
        }

        public Criteria andTaxPathBetween(String value1, String value2) {
            addCriterion("TAX_PATH between", value1, value2, "taxPath");
            return (Criteria) this;
        }

        public Criteria andTaxPathNotBetween(String value1, String value2) {
            addCriterion("TAX_PATH not between", value1, value2, "taxPath");
            return (Criteria) this;
        }

        public Criteria andLegalPersonImageIsNull() {
            addCriterion("LEGAL_PERSON_IMAGE is null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonImageIsNotNull() {
            addCriterion("LEGAL_PERSON_IMAGE is not null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonImageEqualTo(String value) {
            addCriterion("LEGAL_PERSON_IMAGE =", value, "legalPersonImage");
            return (Criteria) this;
        }

        public Criteria andLegalPersonImageNotEqualTo(String value) {
            addCriterion("LEGAL_PERSON_IMAGE <>", value, "legalPersonImage");
            return (Criteria) this;
        }

        public Criteria andLegalPersonImageGreaterThan(String value) {
            addCriterion("LEGAL_PERSON_IMAGE >", value, "legalPersonImage");
            return (Criteria) this;
        }

        public Criteria andLegalPersonImageGreaterThanOrEqualTo(String value) {
            addCriterion("LEGAL_PERSON_IMAGE >=", value, "legalPersonImage");
            return (Criteria) this;
        }

        public Criteria andLegalPersonImageLessThan(String value) {
            addCriterion("LEGAL_PERSON_IMAGE <", value, "legalPersonImage");
            return (Criteria) this;
        }

        public Criteria andLegalPersonImageLessThanOrEqualTo(String value) {
            addCriterion("LEGAL_PERSON_IMAGE <=", value, "legalPersonImage");
            return (Criteria) this;
        }

        public Criteria andLegalPersonImageLike(String value) {
            addCriterion("LEGAL_PERSON_IMAGE like", value, "legalPersonImage");
            return (Criteria) this;
        }

        public Criteria andLegalPersonImageNotLike(String value) {
            addCriterion("LEGAL_PERSON_IMAGE not like", value, "legalPersonImage");
            return (Criteria) this;
        }

        public Criteria andLegalPersonImageIn(List<String> values) {
            addCriterion("LEGAL_PERSON_IMAGE in", values, "legalPersonImage");
            return (Criteria) this;
        }

        public Criteria andLegalPersonImageNotIn(List<String> values) {
            addCriterion("LEGAL_PERSON_IMAGE not in", values, "legalPersonImage");
            return (Criteria) this;
        }

        public Criteria andLegalPersonImageBetween(String value1, String value2) {
            addCriterion("LEGAL_PERSON_IMAGE between", value1, value2, "legalPersonImage");
            return (Criteria) this;
        }

        public Criteria andLegalPersonImageNotBetween(String value1, String value2) {
            addCriterion("LEGAL_PERSON_IMAGE not between", value1, value2, "legalPersonImage");
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

        public Criteria andCompanyCreateStaffIsNull() {
            addCriterion("COMPANY_CREATE_STAFF is null");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateStaffIsNotNull() {
            addCriterion("COMPANY_CREATE_STAFF is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateStaffEqualTo(Long value) {
            addCriterion("COMPANY_CREATE_STAFF =", value, "companyCreateStaff");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateStaffNotEqualTo(Long value) {
            addCriterion("COMPANY_CREATE_STAFF <>", value, "companyCreateStaff");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateStaffGreaterThan(Long value) {
            addCriterion("COMPANY_CREATE_STAFF >", value, "companyCreateStaff");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateStaffGreaterThanOrEqualTo(Long value) {
            addCriterion("COMPANY_CREATE_STAFF >=", value, "companyCreateStaff");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateStaffLessThan(Long value) {
            addCriterion("COMPANY_CREATE_STAFF <", value, "companyCreateStaff");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateStaffLessThanOrEqualTo(Long value) {
            addCriterion("COMPANY_CREATE_STAFF <=", value, "companyCreateStaff");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateStaffIn(List<Long> values) {
            addCriterion("COMPANY_CREATE_STAFF in", values, "companyCreateStaff");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateStaffNotIn(List<Long> values) {
            addCriterion("COMPANY_CREATE_STAFF not in", values, "companyCreateStaff");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateStaffBetween(Long value1, Long value2) {
            addCriterion("COMPANY_CREATE_STAFF between", value1, value2, "companyCreateStaff");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateStaffNotBetween(Long value1, Long value2) {
            addCriterion("COMPANY_CREATE_STAFF not between", value1, value2, "companyCreateStaff");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateTimeIsNull() {
            addCriterion("COMPANY_CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateTimeIsNotNull() {
            addCriterion("COMPANY_CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateTimeEqualTo(Timestamp value) {
            addCriterion("COMPANY_CREATE_TIME =", value, "companyCreateTime");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateTimeNotEqualTo(Timestamp value) {
            addCriterion("COMPANY_CREATE_TIME <>", value, "companyCreateTime");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateTimeGreaterThan(Timestamp value) {
            addCriterion("COMPANY_CREATE_TIME >", value, "companyCreateTime");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("COMPANY_CREATE_TIME >=", value, "companyCreateTime");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateTimeLessThan(Timestamp value) {
            addCriterion("COMPANY_CREATE_TIME <", value, "companyCreateTime");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("COMPANY_CREATE_TIME <=", value, "companyCreateTime");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateTimeIn(List<Timestamp> values) {
            addCriterion("COMPANY_CREATE_TIME in", values, "companyCreateTime");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateTimeNotIn(List<Timestamp> values) {
            addCriterion("COMPANY_CREATE_TIME not in", values, "companyCreateTime");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("COMPANY_CREATE_TIME between", value1, value2, "companyCreateTime");
            return (Criteria) this;
        }

        public Criteria andCompanyCreateTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("COMPANY_CREATE_TIME not between", value1, value2, "companyCreateTime");
            return (Criteria) this;
        }

        public Criteria andIsEnterIsNull() {
            addCriterion("IS_ENTER is null");
            return (Criteria) this;
        }

        public Criteria andIsEnterIsNotNull() {
            addCriterion("IS_ENTER is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnterEqualTo(String value) {
            addCriterion("IS_ENTER =", value, "isEnter");
            return (Criteria) this;
        }

        public Criteria andIsEnterNotEqualTo(String value) {
            addCriterion("IS_ENTER <>", value, "isEnter");
            return (Criteria) this;
        }

        public Criteria andIsEnterGreaterThan(String value) {
            addCriterion("IS_ENTER >", value, "isEnter");
            return (Criteria) this;
        }

        public Criteria andIsEnterGreaterThanOrEqualTo(String value) {
            addCriterion("IS_ENTER >=", value, "isEnter");
            return (Criteria) this;
        }

        public Criteria andIsEnterLessThan(String value) {
            addCriterion("IS_ENTER <", value, "isEnter");
            return (Criteria) this;
        }

        public Criteria andIsEnterLessThanOrEqualTo(String value) {
            addCriterion("IS_ENTER <=", value, "isEnter");
            return (Criteria) this;
        }

        public Criteria andIsEnterLike(String value) {
            addCriterion("IS_ENTER like", value, "isEnter");
            return (Criteria) this;
        }

        public Criteria andIsEnterNotLike(String value) {
            addCriterion("IS_ENTER not like", value, "isEnter");
            return (Criteria) this;
        }

        public Criteria andIsEnterIn(List<String> values) {
            addCriterion("IS_ENTER in", values, "isEnter");
            return (Criteria) this;
        }

        public Criteria andIsEnterNotIn(List<String> values) {
            addCriterion("IS_ENTER not in", values, "isEnter");
            return (Criteria) this;
        }

        public Criteria andIsEnterBetween(String value1, String value2) {
            addCriterion("IS_ENTER between", value1, value2, "isEnter");
            return (Criteria) this;
        }

        public Criteria andIsEnterNotBetween(String value1, String value2) {
            addCriterion("IS_ENTER not between", value1, value2, "isEnter");
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

        public Criteria andLicenceCodeIsNull() {
            addCriterion("LICENCE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andLicenceCodeIsNotNull() {
            addCriterion("LICENCE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andLicenceCodeEqualTo(String value) {
            addCriterion("LICENCE_CODE =", value, "licenceCode");
            return (Criteria) this;
        }

        public Criteria andLicenceCodeNotEqualTo(String value) {
            addCriterion("LICENCE_CODE <>", value, "licenceCode");
            return (Criteria) this;
        }

        public Criteria andLicenceCodeGreaterThan(String value) {
            addCriterion("LICENCE_CODE >", value, "licenceCode");
            return (Criteria) this;
        }

        public Criteria andLicenceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("LICENCE_CODE >=", value, "licenceCode");
            return (Criteria) this;
        }

        public Criteria andLicenceCodeLessThan(String value) {
            addCriterion("LICENCE_CODE <", value, "licenceCode");
            return (Criteria) this;
        }

        public Criteria andLicenceCodeLessThanOrEqualTo(String value) {
            addCriterion("LICENCE_CODE <=", value, "licenceCode");
            return (Criteria) this;
        }

        public Criteria andLicenceCodeLike(String value) {
            addCriterion("LICENCE_CODE like", value, "licenceCode");
            return (Criteria) this;
        }

        public Criteria andLicenceCodeNotLike(String value) {
            addCriterion("LICENCE_CODE not like", value, "licenceCode");
            return (Criteria) this;
        }

        public Criteria andLicenceCodeIn(List<String> values) {
            addCriterion("LICENCE_CODE in", values, "licenceCode");
            return (Criteria) this;
        }

        public Criteria andLicenceCodeNotIn(List<String> values) {
            addCriterion("LICENCE_CODE not in", values, "licenceCode");
            return (Criteria) this;
        }

        public Criteria andLicenceCodeBetween(String value1, String value2) {
            addCriterion("LICENCE_CODE between", value1, value2, "licenceCode");
            return (Criteria) this;
        }

        public Criteria andLicenceCodeNotBetween(String value1, String value2) {
            addCriterion("LICENCE_CODE not between", value1, value2, "licenceCode");
            return (Criteria) this;
        }

        public Criteria andTaxCodeIsNull() {
            addCriterion("TAX_CODE is null");
            return (Criteria) this;
        }

        public Criteria andTaxCodeIsNotNull() {
            addCriterion("TAX_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andTaxCodeEqualTo(String value) {
            addCriterion("TAX_CODE =", value, "taxCode");
            return (Criteria) this;
        }

        public Criteria andTaxCodeNotEqualTo(String value) {
            addCriterion("TAX_CODE <>", value, "taxCode");
            return (Criteria) this;
        }

        public Criteria andTaxCodeGreaterThan(String value) {
            addCriterion("TAX_CODE >", value, "taxCode");
            return (Criteria) this;
        }

        public Criteria andTaxCodeGreaterThanOrEqualTo(String value) {
            addCriterion("TAX_CODE >=", value, "taxCode");
            return (Criteria) this;
        }

        public Criteria andTaxCodeLessThan(String value) {
            addCriterion("TAX_CODE <", value, "taxCode");
            return (Criteria) this;
        }

        public Criteria andTaxCodeLessThanOrEqualTo(String value) {
            addCriterion("TAX_CODE <=", value, "taxCode");
            return (Criteria) this;
        }

        public Criteria andTaxCodeLike(String value) {
            addCriterion("TAX_CODE like", value, "taxCode");
            return (Criteria) this;
        }

        public Criteria andTaxCodeNotLike(String value) {
            addCriterion("TAX_CODE not like", value, "taxCode");
            return (Criteria) this;
        }

        public Criteria andTaxCodeIn(List<String> values) {
            addCriterion("TAX_CODE in", values, "taxCode");
            return (Criteria) this;
        }

        public Criteria andTaxCodeNotIn(List<String> values) {
            addCriterion("TAX_CODE not in", values, "taxCode");
            return (Criteria) this;
        }

        public Criteria andTaxCodeBetween(String value1, String value2) {
            addCriterion("TAX_CODE between", value1, value2, "taxCode");
            return (Criteria) this;
        }

        public Criteria andTaxCodeNotBetween(String value1, String value2) {
            addCriterion("TAX_CODE not between", value1, value2, "taxCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationFormIsNull() {
            addCriterion("ORGANIZATION_FORM is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationFormIsNotNull() {
            addCriterion("ORGANIZATION_FORM is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationFormEqualTo(String value) {
            addCriterion("ORGANIZATION_FORM =", value, "organizationForm");
            return (Criteria) this;
        }

        public Criteria andOrganizationFormNotEqualTo(String value) {
            addCriterion("ORGANIZATION_FORM <>", value, "organizationForm");
            return (Criteria) this;
        }

        public Criteria andOrganizationFormGreaterThan(String value) {
            addCriterion("ORGANIZATION_FORM >", value, "organizationForm");
            return (Criteria) this;
        }

        public Criteria andOrganizationFormGreaterThanOrEqualTo(String value) {
            addCriterion("ORGANIZATION_FORM >=", value, "organizationForm");
            return (Criteria) this;
        }

        public Criteria andOrganizationFormLessThan(String value) {
            addCriterion("ORGANIZATION_FORM <", value, "organizationForm");
            return (Criteria) this;
        }

        public Criteria andOrganizationFormLessThanOrEqualTo(String value) {
            addCriterion("ORGANIZATION_FORM <=", value, "organizationForm");
            return (Criteria) this;
        }

        public Criteria andOrganizationFormLike(String value) {
            addCriterion("ORGANIZATION_FORM like", value, "organizationForm");
            return (Criteria) this;
        }

        public Criteria andOrganizationFormNotLike(String value) {
            addCriterion("ORGANIZATION_FORM not like", value, "organizationForm");
            return (Criteria) this;
        }

        public Criteria andOrganizationFormIn(List<String> values) {
            addCriterion("ORGANIZATION_FORM in", values, "organizationForm");
            return (Criteria) this;
        }

        public Criteria andOrganizationFormNotIn(List<String> values) {
            addCriterion("ORGANIZATION_FORM not in", values, "organizationForm");
            return (Criteria) this;
        }

        public Criteria andOrganizationFormBetween(String value1, String value2) {
            addCriterion("ORGANIZATION_FORM between", value1, value2, "organizationForm");
            return (Criteria) this;
        }

        public Criteria andOrganizationFormNotBetween(String value1, String value2) {
            addCriterion("ORGANIZATION_FORM not between", value1, value2, "organizationForm");
            return (Criteria) this;
        }

        public Criteria andLicenceCapitalIsNull() {
            addCriterion("LICENCE_CAPITAL is null");
            return (Criteria) this;
        }

        public Criteria andLicenceCapitalIsNotNull() {
            addCriterion("LICENCE_CAPITAL is not null");
            return (Criteria) this;
        }

        public Criteria andLicenceCapitalEqualTo(Long value) {
            addCriterion("LICENCE_CAPITAL =", value, "licenceCapital");
            return (Criteria) this;
        }

        public Criteria andLicenceCapitalNotEqualTo(Long value) {
            addCriterion("LICENCE_CAPITAL <>", value, "licenceCapital");
            return (Criteria) this;
        }

        public Criteria andLicenceCapitalGreaterThan(Long value) {
            addCriterion("LICENCE_CAPITAL >", value, "licenceCapital");
            return (Criteria) this;
        }

        public Criteria andLicenceCapitalGreaterThanOrEqualTo(Long value) {
            addCriterion("LICENCE_CAPITAL >=", value, "licenceCapital");
            return (Criteria) this;
        }

        public Criteria andLicenceCapitalLessThan(Long value) {
            addCriterion("LICENCE_CAPITAL <", value, "licenceCapital");
            return (Criteria) this;
        }

        public Criteria andLicenceCapitalLessThanOrEqualTo(Long value) {
            addCriterion("LICENCE_CAPITAL <=", value, "licenceCapital");
            return (Criteria) this;
        }

        public Criteria andLicenceCapitalIn(List<Long> values) {
            addCriterion("LICENCE_CAPITAL in", values, "licenceCapital");
            return (Criteria) this;
        }

        public Criteria andLicenceCapitalNotIn(List<Long> values) {
            addCriterion("LICENCE_CAPITAL not in", values, "licenceCapital");
            return (Criteria) this;
        }

        public Criteria andLicenceCapitalBetween(Long value1, Long value2) {
            addCriterion("LICENCE_CAPITAL between", value1, value2, "licenceCapital");
            return (Criteria) this;
        }

        public Criteria andLicenceCapitalNotBetween(Long value1, Long value2) {
            addCriterion("LICENCE_CAPITAL not between", value1, value2, "licenceCapital");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIsNull() {
            addCriterion("LEGAL_PERSON is null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIsNotNull() {
            addCriterion("LEGAL_PERSON is not null");
            return (Criteria) this;
        }

        public Criteria andLegalPersonEqualTo(String value) {
            addCriterion("LEGAL_PERSON =", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNotEqualTo(String value) {
            addCriterion("LEGAL_PERSON <>", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonGreaterThan(String value) {
            addCriterion("LEGAL_PERSON >", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonGreaterThanOrEqualTo(String value) {
            addCriterion("LEGAL_PERSON >=", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonLessThan(String value) {
            addCriterion("LEGAL_PERSON <", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonLessThanOrEqualTo(String value) {
            addCriterion("LEGAL_PERSON <=", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonLike(String value) {
            addCriterion("LEGAL_PERSON like", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNotLike(String value) {
            addCriterion("LEGAL_PERSON not like", value, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonIn(List<String> values) {
            addCriterion("LEGAL_PERSON in", values, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNotIn(List<String> values) {
            addCriterion("LEGAL_PERSON not in", values, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonBetween(String value1, String value2) {
            addCriterion("LEGAL_PERSON between", value1, value2, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalPersonNotBetween(String value1, String value2) {
            addCriterion("LEGAL_PERSON not between", value1, value2, "legalPerson");
            return (Criteria) this;
        }

        public Criteria andLegalCardIsNull() {
            addCriterion("LEGAL_CARD is null");
            return (Criteria) this;
        }

        public Criteria andLegalCardIsNotNull() {
            addCriterion("LEGAL_CARD is not null");
            return (Criteria) this;
        }

        public Criteria andLegalCardEqualTo(String value) {
            addCriterion("LEGAL_CARD =", value, "legalCard");
            return (Criteria) this;
        }

        public Criteria andLegalCardNotEqualTo(String value) {
            addCriterion("LEGAL_CARD <>", value, "legalCard");
            return (Criteria) this;
        }

        public Criteria andLegalCardGreaterThan(String value) {
            addCriterion("LEGAL_CARD >", value, "legalCard");
            return (Criteria) this;
        }

        public Criteria andLegalCardGreaterThanOrEqualTo(String value) {
            addCriterion("LEGAL_CARD >=", value, "legalCard");
            return (Criteria) this;
        }

        public Criteria andLegalCardLessThan(String value) {
            addCriterion("LEGAL_CARD <", value, "legalCard");
            return (Criteria) this;
        }

        public Criteria andLegalCardLessThanOrEqualTo(String value) {
            addCriterion("LEGAL_CARD <=", value, "legalCard");
            return (Criteria) this;
        }

        public Criteria andLegalCardLike(String value) {
            addCriterion("LEGAL_CARD like", value, "legalCard");
            return (Criteria) this;
        }

        public Criteria andLegalCardNotLike(String value) {
            addCriterion("LEGAL_CARD not like", value, "legalCard");
            return (Criteria) this;
        }

        public Criteria andLegalCardIn(List<String> values) {
            addCriterion("LEGAL_CARD in", values, "legalCard");
            return (Criteria) this;
        }

        public Criteria andLegalCardNotIn(List<String> values) {
            addCriterion("LEGAL_CARD not in", values, "legalCard");
            return (Criteria) this;
        }

        public Criteria andLegalCardBetween(String value1, String value2) {
            addCriterion("LEGAL_CARD between", value1, value2, "legalCard");
            return (Criteria) this;
        }

        public Criteria andLegalCardNotBetween(String value1, String value2) {
            addCriterion("LEGAL_CARD not between", value1, value2, "legalCard");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("PARENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("PARENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Long value) {
            addCriterion("PARENT_ID =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Long value) {
            addCriterion("PARENT_ID <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Long value) {
            addCriterion("PARENT_ID >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PARENT_ID >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Long value) {
            addCriterion("PARENT_ID <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Long value) {
            addCriterion("PARENT_ID <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Long> values) {
            addCriterion("PARENT_ID in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Long> values) {
            addCriterion("PARENT_ID not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Long value1, Long value2) {
            addCriterion("PARENT_ID between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Long value1, Long value2) {
            addCriterion("PARENT_ID not between", value1, value2, "parentId");
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

        public Criteria andShopFullNameIsNull() {
            addCriterion("SHOP_FULL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andShopFullNameIsNotNull() {
            addCriterion("SHOP_FULL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andShopFullNameEqualTo(String value) {
            addCriterion("SHOP_FULL_NAME =", value, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameNotEqualTo(String value) {
            addCriterion("SHOP_FULL_NAME <>", value, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameGreaterThan(String value) {
            addCriterion("SHOP_FULL_NAME >", value, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_FULL_NAME >=", value, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameLessThan(String value) {
            addCriterion("SHOP_FULL_NAME <", value, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameLessThanOrEqualTo(String value) {
            addCriterion("SHOP_FULL_NAME <=", value, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameLike(String value) {
            addCriterion("SHOP_FULL_NAME like", value, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameNotLike(String value) {
            addCriterion("SHOP_FULL_NAME not like", value, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameIn(List<String> values) {
            addCriterion("SHOP_FULL_NAME in", values, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameNotIn(List<String> values) {
            addCriterion("SHOP_FULL_NAME not in", values, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameBetween(String value1, String value2) {
            addCriterion("SHOP_FULL_NAME between", value1, value2, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopFullNameNotBetween(String value1, String value2) {
            addCriterion("SHOP_FULL_NAME not between", value1, value2, "shopFullName");
            return (Criteria) this;
        }

        public Criteria andShopGradeIsNull() {
            addCriterion("SHOP_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andShopGradeIsNotNull() {
            addCriterion("SHOP_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andShopGradeEqualTo(String value) {
            addCriterion("SHOP_GRADE =", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeNotEqualTo(String value) {
            addCriterion("SHOP_GRADE <>", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeGreaterThan(String value) {
            addCriterion("SHOP_GRADE >", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_GRADE >=", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeLessThan(String value) {
            addCriterion("SHOP_GRADE <", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeLessThanOrEqualTo(String value) {
            addCriterion("SHOP_GRADE <=", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeLike(String value) {
            addCriterion("SHOP_GRADE like", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeNotLike(String value) {
            addCriterion("SHOP_GRADE not like", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeIn(List<String> values) {
            addCriterion("SHOP_GRADE in", values, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeNotIn(List<String> values) {
            addCriterion("SHOP_GRADE not in", values, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeBetween(String value1, String value2) {
            addCriterion("SHOP_GRADE between", value1, value2, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeNotBetween(String value1, String value2) {
            addCriterion("SHOP_GRADE not between", value1, value2, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopDescIsNull() {
            addCriterion("SHOP_DESC is null");
            return (Criteria) this;
        }

        public Criteria andShopDescIsNotNull() {
            addCriterion("SHOP_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andShopDescEqualTo(String value) {
            addCriterion("SHOP_DESC =", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescNotEqualTo(String value) {
            addCriterion("SHOP_DESC <>", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescGreaterThan(String value) {
            addCriterion("SHOP_DESC >", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_DESC >=", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescLessThan(String value) {
            addCriterion("SHOP_DESC <", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescLessThanOrEqualTo(String value) {
            addCriterion("SHOP_DESC <=", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescLike(String value) {
            addCriterion("SHOP_DESC like", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescNotLike(String value) {
            addCriterion("SHOP_DESC not like", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescIn(List<String> values) {
            addCriterion("SHOP_DESC in", values, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescNotIn(List<String> values) {
            addCriterion("SHOP_DESC not in", values, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescBetween(String value1, String value2) {
            addCriterion("SHOP_DESC between", value1, value2, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescNotBetween(String value1, String value2) {
            addCriterion("SHOP_DESC not between", value1, value2, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopStatusIsNull() {
            addCriterion("SHOP_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andShopStatusIsNotNull() {
            addCriterion("SHOP_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andShopStatusEqualTo(String value) {
            addCriterion("SHOP_STATUS =", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusNotEqualTo(String value) {
            addCriterion("SHOP_STATUS <>", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusGreaterThan(String value) {
            addCriterion("SHOP_STATUS >", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_STATUS >=", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusLessThan(String value) {
            addCriterion("SHOP_STATUS <", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusLessThanOrEqualTo(String value) {
            addCriterion("SHOP_STATUS <=", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusLike(String value) {
            addCriterion("SHOP_STATUS like", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusNotLike(String value) {
            addCriterion("SHOP_STATUS not like", value, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusIn(List<String> values) {
            addCriterion("SHOP_STATUS in", values, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusNotIn(List<String> values) {
            addCriterion("SHOP_STATUS not in", values, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusBetween(String value1, String value2) {
            addCriterion("SHOP_STATUS between", value1, value2, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopStatusNotBetween(String value1, String value2) {
            addCriterion("SHOP_STATUS not between", value1, value2, "shopStatus");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeIsNull() {
            addCriterion("SHOP_DEAL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeIsNotNull() {
            addCriterion("SHOP_DEAL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeEqualTo(String value) {
            addCriterion("SHOP_DEAL_TYPE =", value, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeNotEqualTo(String value) {
            addCriterion("SHOP_DEAL_TYPE <>", value, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeGreaterThan(String value) {
            addCriterion("SHOP_DEAL_TYPE >", value, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_DEAL_TYPE >=", value, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeLessThan(String value) {
            addCriterion("SHOP_DEAL_TYPE <", value, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeLessThanOrEqualTo(String value) {
            addCriterion("SHOP_DEAL_TYPE <=", value, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeLike(String value) {
            addCriterion("SHOP_DEAL_TYPE like", value, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeNotLike(String value) {
            addCriterion("SHOP_DEAL_TYPE not like", value, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeIn(List<String> values) {
            addCriterion("SHOP_DEAL_TYPE in", values, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeNotIn(List<String> values) {
            addCriterion("SHOP_DEAL_TYPE not in", values, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeBetween(String value1, String value2) {
            addCriterion("SHOP_DEAL_TYPE between", value1, value2, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andShopDealTypeNotBetween(String value1, String value2) {
            addCriterion("SHOP_DEAL_TYPE not between", value1, value2, "shopDealType");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyIsNull() {
            addCriterion("CAUTION_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyIsNotNull() {
            addCriterion("CAUTION_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyEqualTo(Long value) {
            addCriterion("CAUTION_MONEY =", value, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyNotEqualTo(Long value) {
            addCriterion("CAUTION_MONEY <>", value, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyGreaterThan(Long value) {
            addCriterion("CAUTION_MONEY >", value, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("CAUTION_MONEY >=", value, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyLessThan(Long value) {
            addCriterion("CAUTION_MONEY <", value, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyLessThanOrEqualTo(Long value) {
            addCriterion("CAUTION_MONEY <=", value, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyIn(List<Long> values) {
            addCriterion("CAUTION_MONEY in", values, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyNotIn(List<Long> values) {
            addCriterion("CAUTION_MONEY not in", values, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyBetween(Long value1, Long value2) {
            addCriterion("CAUTION_MONEY between", value1, value2, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andCautionMoneyNotBetween(Long value1, Long value2) {
            addCriterion("CAUTION_MONEY not between", value1, value2, "cautionMoney");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtIsNull() {
            addCriterion("MICRO_MESSAGE_EXT is null");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtIsNotNull() {
            addCriterion("MICRO_MESSAGE_EXT is not null");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtEqualTo(String value) {
            addCriterion("MICRO_MESSAGE_EXT =", value, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtNotEqualTo(String value) {
            addCriterion("MICRO_MESSAGE_EXT <>", value, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtGreaterThan(String value) {
            addCriterion("MICRO_MESSAGE_EXT >", value, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtGreaterThanOrEqualTo(String value) {
            addCriterion("MICRO_MESSAGE_EXT >=", value, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtLessThan(String value) {
            addCriterion("MICRO_MESSAGE_EXT <", value, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtLessThanOrEqualTo(String value) {
            addCriterion("MICRO_MESSAGE_EXT <=", value, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtLike(String value) {
            addCriterion("MICRO_MESSAGE_EXT like", value, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtNotLike(String value) {
            addCriterion("MICRO_MESSAGE_EXT not like", value, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtIn(List<String> values) {
            addCriterion("MICRO_MESSAGE_EXT in", values, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtNotIn(List<String> values) {
            addCriterion("MICRO_MESSAGE_EXT not in", values, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtBetween(String value1, String value2) {
            addCriterion("MICRO_MESSAGE_EXT between", value1, value2, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andMicroMessageExtNotBetween(String value1, String value2) {
            addCriterion("MICRO_MESSAGE_EXT not between", value1, value2, "microMessageExt");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedIsNull() {
            addCriterion("HOT_SHOW_SUPPORTED is null");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedIsNotNull() {
            addCriterion("HOT_SHOW_SUPPORTED is not null");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedEqualTo(String value) {
            addCriterion("HOT_SHOW_SUPPORTED =", value, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedNotEqualTo(String value) {
            addCriterion("HOT_SHOW_SUPPORTED <>", value, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedGreaterThan(String value) {
            addCriterion("HOT_SHOW_SUPPORTED >", value, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedGreaterThanOrEqualTo(String value) {
            addCriterion("HOT_SHOW_SUPPORTED >=", value, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedLessThan(String value) {
            addCriterion("HOT_SHOW_SUPPORTED <", value, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedLessThanOrEqualTo(String value) {
            addCriterion("HOT_SHOW_SUPPORTED <=", value, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedLike(String value) {
            addCriterion("HOT_SHOW_SUPPORTED like", value, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedNotLike(String value) {
            addCriterion("HOT_SHOW_SUPPORTED not like", value, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedIn(List<String> values) {
            addCriterion("HOT_SHOW_SUPPORTED in", values, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedNotIn(List<String> values) {
            addCriterion("HOT_SHOW_SUPPORTED not in", values, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedBetween(String value1, String value2) {
            addCriterion("HOT_SHOW_SUPPORTED between", value1, value2, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSupportedNotBetween(String value1, String value2) {
            addCriterion("HOT_SHOW_SUPPORTED not between", value1, value2, "hotShowSupported");
            return (Criteria) this;
        }

        public Criteria andHotShowSortIsNull() {
            addCriterion("HOT_SHOW_SORT is null");
            return (Criteria) this;
        }

        public Criteria andHotShowSortIsNotNull() {
            addCriterion("HOT_SHOW_SORT is not null");
            return (Criteria) this;
        }

        public Criteria andHotShowSortEqualTo(Short value) {
            addCriterion("HOT_SHOW_SORT =", value, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortNotEqualTo(Short value) {
            addCriterion("HOT_SHOW_SORT <>", value, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortGreaterThan(Short value) {
            addCriterion("HOT_SHOW_SORT >", value, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortGreaterThanOrEqualTo(Short value) {
            addCriterion("HOT_SHOW_SORT >=", value, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortLessThan(Short value) {
            addCriterion("HOT_SHOW_SORT <", value, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortLessThanOrEqualTo(Short value) {
            addCriterion("HOT_SHOW_SORT <=", value, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortIn(List<Short> values) {
            addCriterion("HOT_SHOW_SORT in", values, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortNotIn(List<Short> values) {
            addCriterion("HOT_SHOW_SORT not in", values, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortBetween(Short value1, Short value2) {
            addCriterion("HOT_SHOW_SORT between", value1, value2, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andHotShowSortNotBetween(Short value1, Short value2) {
            addCriterion("HOT_SHOW_SORT not between", value1, value2, "hotShowSort");
            return (Criteria) this;
        }

        public Criteria andLogoPathIsNull() {
            addCriterion("LOGO_PATH is null");
            return (Criteria) this;
        }

        public Criteria andLogoPathIsNotNull() {
            addCriterion("LOGO_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andLogoPathEqualTo(String value) {
            addCriterion("LOGO_PATH =", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathNotEqualTo(String value) {
            addCriterion("LOGO_PATH <>", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathGreaterThan(String value) {
            addCriterion("LOGO_PATH >", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathGreaterThanOrEqualTo(String value) {
            addCriterion("LOGO_PATH >=", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathLessThan(String value) {
            addCriterion("LOGO_PATH <", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathLessThanOrEqualTo(String value) {
            addCriterion("LOGO_PATH <=", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathLike(String value) {
            addCriterion("LOGO_PATH like", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathNotLike(String value) {
            addCriterion("LOGO_PATH not like", value, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathIn(List<String> values) {
            addCriterion("LOGO_PATH in", values, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathNotIn(List<String> values) {
            addCriterion("LOGO_PATH not in", values, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathBetween(String value1, String value2) {
            addCriterion("LOGO_PATH between", value1, value2, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLogoPathNotBetween(String value1, String value2) {
            addCriterion("LOGO_PATH not between", value1, value2, "logoPath");
            return (Criteria) this;
        }

        public Criteria andLinkPersonIsNull() {
            addCriterion("LINK_PERSON is null");
            return (Criteria) this;
        }

        public Criteria andLinkPersonIsNotNull() {
            addCriterion("LINK_PERSON is not null");
            return (Criteria) this;
        }

        public Criteria andLinkPersonEqualTo(String value) {
            addCriterion("LINK_PERSON =", value, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonNotEqualTo(String value) {
            addCriterion("LINK_PERSON <>", value, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonGreaterThan(String value) {
            addCriterion("LINK_PERSON >", value, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonGreaterThanOrEqualTo(String value) {
            addCriterion("LINK_PERSON >=", value, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonLessThan(String value) {
            addCriterion("LINK_PERSON <", value, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonLessThanOrEqualTo(String value) {
            addCriterion("LINK_PERSON <=", value, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonLike(String value) {
            addCriterion("LINK_PERSON like", value, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonNotLike(String value) {
            addCriterion("LINK_PERSON not like", value, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonIn(List<String> values) {
            addCriterion("LINK_PERSON in", values, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonNotIn(List<String> values) {
            addCriterion("LINK_PERSON not in", values, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonBetween(String value1, String value2) {
            addCriterion("LINK_PERSON between", value1, value2, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkPersonNotBetween(String value1, String value2) {
            addCriterion("LINK_PERSON not between", value1, value2, "linkPerson");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneIsNull() {
            addCriterion("LINK_MOBILEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneIsNotNull() {
            addCriterion("LINK_MOBILEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneEqualTo(String value) {
            addCriterion("LINK_MOBILEPHONE =", value, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneNotEqualTo(String value) {
            addCriterion("LINK_MOBILEPHONE <>", value, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneGreaterThan(String value) {
            addCriterion("LINK_MOBILEPHONE >", value, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneGreaterThanOrEqualTo(String value) {
            addCriterion("LINK_MOBILEPHONE >=", value, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneLessThan(String value) {
            addCriterion("LINK_MOBILEPHONE <", value, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneLessThanOrEqualTo(String value) {
            addCriterion("LINK_MOBILEPHONE <=", value, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneLike(String value) {
            addCriterion("LINK_MOBILEPHONE like", value, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneNotLike(String value) {
            addCriterion("LINK_MOBILEPHONE not like", value, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneIn(List<String> values) {
            addCriterion("LINK_MOBILEPHONE in", values, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneNotIn(List<String> values) {
            addCriterion("LINK_MOBILEPHONE not in", values, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneBetween(String value1, String value2) {
            addCriterion("LINK_MOBILEPHONE between", value1, value2, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkMobilephoneNotBetween(String value1, String value2) {
            addCriterion("LINK_MOBILEPHONE not between", value1, value2, "linkMobilephone");
            return (Criteria) this;
        }

        public Criteria andLinkEmailIsNull() {
            addCriterion("LINK_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andLinkEmailIsNotNull() {
            addCriterion("LINK_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andLinkEmailEqualTo(String value) {
            addCriterion("LINK_EMAIL =", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailNotEqualTo(String value) {
            addCriterion("LINK_EMAIL <>", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailGreaterThan(String value) {
            addCriterion("LINK_EMAIL >", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailGreaterThanOrEqualTo(String value) {
            addCriterion("LINK_EMAIL >=", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailLessThan(String value) {
            addCriterion("LINK_EMAIL <", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailLessThanOrEqualTo(String value) {
            addCriterion("LINK_EMAIL <=", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailLike(String value) {
            addCriterion("LINK_EMAIL like", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailNotLike(String value) {
            addCriterion("LINK_EMAIL not like", value, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailIn(List<String> values) {
            addCriterion("LINK_EMAIL in", values, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailNotIn(List<String> values) {
            addCriterion("LINK_EMAIL not in", values, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailBetween(String value1, String value2) {
            addCriterion("LINK_EMAIL between", value1, value2, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andLinkEmailNotBetween(String value1, String value2) {
            addCriterion("LINK_EMAIL not between", value1, value2, "linkEmail");
            return (Criteria) this;
        }

        public Criteria andIsUseVipIsNull() {
            addCriterion("IS_USE_VIP is null");
            return (Criteria) this;
        }

        public Criteria andIsUseVipIsNotNull() {
            addCriterion("IS_USE_VIP is not null");
            return (Criteria) this;
        }

        public Criteria andIsUseVipEqualTo(String value) {
            addCriterion("IS_USE_VIP =", value, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipNotEqualTo(String value) {
            addCriterion("IS_USE_VIP <>", value, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipGreaterThan(String value) {
            addCriterion("IS_USE_VIP >", value, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipGreaterThanOrEqualTo(String value) {
            addCriterion("IS_USE_VIP >=", value, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipLessThan(String value) {
            addCriterion("IS_USE_VIP <", value, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipLessThanOrEqualTo(String value) {
            addCriterion("IS_USE_VIP <=", value, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipLike(String value) {
            addCriterion("IS_USE_VIP like", value, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipNotLike(String value) {
            addCriterion("IS_USE_VIP not like", value, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipIn(List<String> values) {
            addCriterion("IS_USE_VIP in", values, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipNotIn(List<String> values) {
            addCriterion("IS_USE_VIP not in", values, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipBetween(String value1, String value2) {
            addCriterion("IS_USE_VIP between", value1, value2, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andIsUseVipNotBetween(String value1, String value2) {
            addCriterion("IS_USE_VIP not between", value1, value2, "isUseVip");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedIsNull() {
            addCriterion("OFFLINE_SUPPORTED is null");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedIsNotNull() {
            addCriterion("OFFLINE_SUPPORTED is not null");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedEqualTo(String value) {
            addCriterion("OFFLINE_SUPPORTED =", value, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedNotEqualTo(String value) {
            addCriterion("OFFLINE_SUPPORTED <>", value, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedGreaterThan(String value) {
            addCriterion("OFFLINE_SUPPORTED >", value, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedGreaterThanOrEqualTo(String value) {
            addCriterion("OFFLINE_SUPPORTED >=", value, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedLessThan(String value) {
            addCriterion("OFFLINE_SUPPORTED <", value, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedLessThanOrEqualTo(String value) {
            addCriterion("OFFLINE_SUPPORTED <=", value, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedLike(String value) {
            addCriterion("OFFLINE_SUPPORTED like", value, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedNotLike(String value) {
            addCriterion("OFFLINE_SUPPORTED not like", value, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedIn(List<String> values) {
            addCriterion("OFFLINE_SUPPORTED in", values, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedNotIn(List<String> values) {
            addCriterion("OFFLINE_SUPPORTED not in", values, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedBetween(String value1, String value2) {
            addCriterion("OFFLINE_SUPPORTED between", value1, value2, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOfflineSupportedNotBetween(String value1, String value2) {
            addCriterion("OFFLINE_SUPPORTED not between", value1, value2, "offlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedIsNull() {
            addCriterion("ONLINE_SUPPORTED is null");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedIsNotNull() {
            addCriterion("ONLINE_SUPPORTED is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedEqualTo(String value) {
            addCriterion("ONLINE_SUPPORTED =", value, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedNotEqualTo(String value) {
            addCriterion("ONLINE_SUPPORTED <>", value, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedGreaterThan(String value) {
            addCriterion("ONLINE_SUPPORTED >", value, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedGreaterThanOrEqualTo(String value) {
            addCriterion("ONLINE_SUPPORTED >=", value, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedLessThan(String value) {
            addCriterion("ONLINE_SUPPORTED <", value, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedLessThanOrEqualTo(String value) {
            addCriterion("ONLINE_SUPPORTED <=", value, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedLike(String value) {
            addCriterion("ONLINE_SUPPORTED like", value, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedNotLike(String value) {
            addCriterion("ONLINE_SUPPORTED not like", value, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedIn(List<String> values) {
            addCriterion("ONLINE_SUPPORTED in", values, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedNotIn(List<String> values) {
            addCriterion("ONLINE_SUPPORTED not in", values, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedBetween(String value1, String value2) {
            addCriterion("ONLINE_SUPPORTED between", value1, value2, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andOnlineSupportedNotBetween(String value1, String value2) {
            addCriterion("ONLINE_SUPPORTED not between", value1, value2, "onlineSupported");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListIsNull() {
            addCriterion("IS_WHITE_LIST is null");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListIsNotNull() {
            addCriterion("IS_WHITE_LIST is not null");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListEqualTo(String value) {
            addCriterion("IS_WHITE_LIST =", value, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListNotEqualTo(String value) {
            addCriterion("IS_WHITE_LIST <>", value, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListGreaterThan(String value) {
            addCriterion("IS_WHITE_LIST >", value, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListGreaterThanOrEqualTo(String value) {
            addCriterion("IS_WHITE_LIST >=", value, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListLessThan(String value) {
            addCriterion("IS_WHITE_LIST <", value, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListLessThanOrEqualTo(String value) {
            addCriterion("IS_WHITE_LIST <=", value, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListLike(String value) {
            addCriterion("IS_WHITE_LIST like", value, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListNotLike(String value) {
            addCriterion("IS_WHITE_LIST not like", value, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListIn(List<String> values) {
            addCriterion("IS_WHITE_LIST in", values, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListNotIn(List<String> values) {
            addCriterion("IS_WHITE_LIST not in", values, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListBetween(String value1, String value2) {
            addCriterion("IS_WHITE_LIST between", value1, value2, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsWhiteListNotBetween(String value1, String value2) {
            addCriterion("IS_WHITE_LIST not between", value1, value2, "isWhiteList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListIsNull() {
            addCriterion("IS_BLACK_LIST is null");
            return (Criteria) this;
        }

        public Criteria andIsBlackListIsNotNull() {
            addCriterion("IS_BLACK_LIST is not null");
            return (Criteria) this;
        }

        public Criteria andIsBlackListEqualTo(String value) {
            addCriterion("IS_BLACK_LIST =", value, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListNotEqualTo(String value) {
            addCriterion("IS_BLACK_LIST <>", value, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListGreaterThan(String value) {
            addCriterion("IS_BLACK_LIST >", value, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListGreaterThanOrEqualTo(String value) {
            addCriterion("IS_BLACK_LIST >=", value, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListLessThan(String value) {
            addCriterion("IS_BLACK_LIST <", value, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListLessThanOrEqualTo(String value) {
            addCriterion("IS_BLACK_LIST <=", value, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListLike(String value) {
            addCriterion("IS_BLACK_LIST like", value, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListNotLike(String value) {
            addCriterion("IS_BLACK_LIST not like", value, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListIn(List<String> values) {
            addCriterion("IS_BLACK_LIST in", values, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListNotIn(List<String> values) {
            addCriterion("IS_BLACK_LIST not in", values, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListBetween(String value1, String value2) {
            addCriterion("IS_BLACK_LIST between", value1, value2, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andIsBlackListNotBetween(String value1, String value2) {
            addCriterion("IS_BLACK_LIST not between", value1, value2, "isBlackList");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIsNull() {
            addCriterion("INVALID_DATE is null");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIsNotNull() {
            addCriterion("INVALID_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidDateEqualTo(Timestamp value) {
            addCriterion("INVALID_DATE =", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotEqualTo(Timestamp value) {
            addCriterion("INVALID_DATE <>", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateGreaterThan(Timestamp value) {
            addCriterion("INVALID_DATE >", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("INVALID_DATE >=", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateLessThan(Timestamp value) {
            addCriterion("INVALID_DATE <", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("INVALID_DATE <=", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIn(List<Timestamp> values) {
            addCriterion("INVALID_DATE in", values, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotIn(List<Timestamp> values) {
            addCriterion("INVALID_DATE not in", values, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INVALID_DATE between", value1, value2, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("INVALID_DATE not between", value1, value2, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffIsNull() {
            addCriterion("INVALID_STAFF is null");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffIsNotNull() {
            addCriterion("INVALID_STAFF is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffEqualTo(String value) {
            addCriterion("INVALID_STAFF =", value, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffNotEqualTo(String value) {
            addCriterion("INVALID_STAFF <>", value, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffGreaterThan(String value) {
            addCriterion("INVALID_STAFF >", value, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffGreaterThanOrEqualTo(String value) {
            addCriterion("INVALID_STAFF >=", value, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffLessThan(String value) {
            addCriterion("INVALID_STAFF <", value, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffLessThanOrEqualTo(String value) {
            addCriterion("INVALID_STAFF <=", value, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffLike(String value) {
            addCriterion("INVALID_STAFF like", value, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffNotLike(String value) {
            addCriterion("INVALID_STAFF not like", value, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffIn(List<String> values) {
            addCriterion("INVALID_STAFF in", values, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffNotIn(List<String> values) {
            addCriterion("INVALID_STAFF not in", values, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffBetween(String value1, String value2) {
            addCriterion("INVALID_STAFF between", value1, value2, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andInvalidStaffNotBetween(String value1, String value2) {
            addCriterion("INVALID_STAFF not between", value1, value2, "invalidStaff");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNull() {
            addCriterion("CHECK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("CHECK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(String value) {
            addCriterion("CHECK_STATUS =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(String value) {
            addCriterion("CHECK_STATUS <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(String value) {
            addCriterion("CHECK_STATUS >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(String value) {
            addCriterion("CHECK_STATUS <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLike(String value) {
            addCriterion("CHECK_STATUS like", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotLike(String value) {
            addCriterion("CHECK_STATUS not like", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<String> values) {
            addCriterion("CHECK_STATUS in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<String> values) {
            addCriterion("CHECK_STATUS not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS not between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStaffIsNull() {
            addCriterion("CHECK_STAFF is null");
            return (Criteria) this;
        }

        public Criteria andCheckStaffIsNotNull() {
            addCriterion("CHECK_STAFF is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStaffEqualTo(Long value) {
            addCriterion("CHECK_STAFF =", value, "checkStaff");
            return (Criteria) this;
        }

        public Criteria andCheckStaffNotEqualTo(Long value) {
            addCriterion("CHECK_STAFF <>", value, "checkStaff");
            return (Criteria) this;
        }

        public Criteria andCheckStaffGreaterThan(Long value) {
            addCriterion("CHECK_STAFF >", value, "checkStaff");
            return (Criteria) this;
        }

        public Criteria andCheckStaffGreaterThanOrEqualTo(Long value) {
            addCriterion("CHECK_STAFF >=", value, "checkStaff");
            return (Criteria) this;
        }

        public Criteria andCheckStaffLessThan(Long value) {
            addCriterion("CHECK_STAFF <", value, "checkStaff");
            return (Criteria) this;
        }

        public Criteria andCheckStaffLessThanOrEqualTo(Long value) {
            addCriterion("CHECK_STAFF <=", value, "checkStaff");
            return (Criteria) this;
        }

        public Criteria andCheckStaffIn(List<Long> values) {
            addCriterion("CHECK_STAFF in", values, "checkStaff");
            return (Criteria) this;
        }

        public Criteria andCheckStaffNotIn(List<Long> values) {
            addCriterion("CHECK_STAFF not in", values, "checkStaff");
            return (Criteria) this;
        }

        public Criteria andCheckStaffBetween(Long value1, Long value2) {
            addCriterion("CHECK_STAFF between", value1, value2, "checkStaff");
            return (Criteria) this;
        }

        public Criteria andCheckStaffNotBetween(Long value1, Long value2) {
            addCriterion("CHECK_STAFF not between", value1, value2, "checkStaff");
            return (Criteria) this;
        }

        public Criteria andCheckDateIsNull() {
            addCriterion("CHECK_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCheckDateIsNotNull() {
            addCriterion("CHECK_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCheckDateEqualTo(Timestamp value) {
            addCriterion("CHECK_DATE =", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotEqualTo(Timestamp value) {
            addCriterion("CHECK_DATE <>", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThan(Timestamp value) {
            addCriterion("CHECK_DATE >", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CHECK_DATE >=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThan(Timestamp value) {
            addCriterion("CHECK_DATE <", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("CHECK_DATE <=", value, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateIn(List<Timestamp> values) {
            addCriterion("CHECK_DATE in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotIn(List<Timestamp> values) {
            addCriterion("CHECK_DATE not in", values, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CHECK_DATE between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CHECK_DATE not between", value1, value2, "checkDate");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkIsNull() {
            addCriterion("CHECK_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkIsNotNull() {
            addCriterion("CHECK_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkEqualTo(String value) {
            addCriterion("CHECK_REMARK =", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkNotEqualTo(String value) {
            addCriterion("CHECK_REMARK <>", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkGreaterThan(String value) {
            addCriterion("CHECK_REMARK >", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_REMARK >=", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkLessThan(String value) {
            addCriterion("CHECK_REMARK <", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkLessThanOrEqualTo(String value) {
            addCriterion("CHECK_REMARK <=", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkLike(String value) {
            addCriterion("CHECK_REMARK like", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkNotLike(String value) {
            addCriterion("CHECK_REMARK not like", value, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkIn(List<String> values) {
            addCriterion("CHECK_REMARK in", values, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkNotIn(List<String> values) {
            addCriterion("CHECK_REMARK not in", values, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkBetween(String value1, String value2) {
            addCriterion("CHECK_REMARK between", value1, value2, "checkRemark");
            return (Criteria) this;
        }

        public Criteria andCheckRemarkNotBetween(String value1, String value2) {
            addCriterion("CHECK_REMARK not between", value1, value2, "checkRemark");
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

        public Criteria andCompanyTypeIsNull() {
            addCriterion("COMPANY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeIsNotNull() {
            addCriterion("COMPANY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeEqualTo(String value) {
            addCriterion("COMPANY_TYPE =", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotEqualTo(String value) {
            addCriterion("COMPANY_TYPE <>", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeGreaterThan(String value) {
            addCriterion("COMPANY_TYPE >", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_TYPE >=", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLessThan(String value) {
            addCriterion("COMPANY_TYPE <", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_TYPE <=", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLike(String value) {
            addCriterion("COMPANY_TYPE like", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotLike(String value) {
            addCriterion("COMPANY_TYPE not like", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeIn(List<String> values) {
            addCriterion("COMPANY_TYPE in", values, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotIn(List<String> values) {
            addCriterion("COMPANY_TYPE not in", values, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeBetween(String value1, String value2) {
            addCriterion("COMPANY_TYPE between", value1, value2, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotBetween(String value1, String value2) {
            addCriterion("COMPANY_TYPE not between", value1, value2, "companyType");
            return (Criteria) this;
        }

        public Criteria andDistributionIsNull() {
            addCriterion("DISTRIBUTION is null");
            return (Criteria) this;
        }

        public Criteria andDistributionIsNotNull() {
            addCriterion("DISTRIBUTION is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionEqualTo(String value) {
            addCriterion("DISTRIBUTION =", value, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionNotEqualTo(String value) {
            addCriterion("DISTRIBUTION <>", value, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionGreaterThan(String value) {
            addCriterion("DISTRIBUTION >", value, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionGreaterThanOrEqualTo(String value) {
            addCriterion("DISTRIBUTION >=", value, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionLessThan(String value) {
            addCriterion("DISTRIBUTION <", value, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionLessThanOrEqualTo(String value) {
            addCriterion("DISTRIBUTION <=", value, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionLike(String value) {
            addCriterion("DISTRIBUTION like", value, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionNotLike(String value) {
            addCriterion("DISTRIBUTION not like", value, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionIn(List<String> values) {
            addCriterion("DISTRIBUTION in", values, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionNotIn(List<String> values) {
            addCriterion("DISTRIBUTION not in", values, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionBetween(String value1, String value2) {
            addCriterion("DISTRIBUTION between", value1, value2, "distribution");
            return (Criteria) this;
        }

        public Criteria andDistributionNotBetween(String value1, String value2) {
            addCriterion("DISTRIBUTION not between", value1, value2, "distribution");
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