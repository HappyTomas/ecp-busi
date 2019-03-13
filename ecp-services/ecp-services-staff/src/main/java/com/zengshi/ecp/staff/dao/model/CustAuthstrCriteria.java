package com.zengshi.ecp.staff.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CustAuthstrCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public CustAuthstrCriteria() {
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

        public Criteria andSerialNumberIsNull() {
            addCriterion("SERIAL_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIsNotNull() {
            addCriterion("SERIAL_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNumberEqualTo(String value) {
            addCriterion("SERIAL_NUMBER =", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotEqualTo(String value) {
            addCriterion("SERIAL_NUMBER <>", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberGreaterThan(String value) {
            addCriterion("SERIAL_NUMBER >", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberGreaterThanOrEqualTo(String value) {
            addCriterion("SERIAL_NUMBER >=", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLessThan(String value) {
            addCriterion("SERIAL_NUMBER <", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLessThanOrEqualTo(String value) {
            addCriterion("SERIAL_NUMBER <=", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLike(String value) {
            addCriterion("SERIAL_NUMBER like", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotLike(String value) {
            addCriterion("SERIAL_NUMBER not like", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIn(List<String> values) {
            addCriterion("SERIAL_NUMBER in", values, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotIn(List<String> values) {
            addCriterion("SERIAL_NUMBER not in", values, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberBetween(String value1, String value2) {
            addCriterion("SERIAL_NUMBER between", value1, value2, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotBetween(String value1, String value2) {
            addCriterion("SERIAL_NUMBER not between", value1, value2, "serialNumber");
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