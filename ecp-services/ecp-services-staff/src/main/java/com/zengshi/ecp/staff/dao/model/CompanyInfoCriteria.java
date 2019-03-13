package com.zengshi.ecp.staff.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CompanyInfoCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CompanyInfoCriteria() {
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