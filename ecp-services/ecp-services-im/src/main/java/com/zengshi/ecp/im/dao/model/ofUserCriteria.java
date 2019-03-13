package com.zengshi.ecp.im.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ofUserCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public ofUserCriteria() {
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

        public Criteria andUsernameIsNull() {
            addCriterion("USERNAME is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("USERNAME is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("USERNAME =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("USERNAME <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("USERNAME >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("USERNAME >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("USERNAME <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("USERNAME <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("USERNAME like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("USERNAME not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("USERNAME in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("USERNAME not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("USERNAME between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("USERNAME not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andStoredkeyIsNull() {
            addCriterion("STOREDKEY is null");
            return (Criteria) this;
        }

        public Criteria andStoredkeyIsNotNull() {
            addCriterion("STOREDKEY is not null");
            return (Criteria) this;
        }

        public Criteria andStoredkeyEqualTo(String value) {
            addCriterion("STOREDKEY =", value, "storedkey");
            return (Criteria) this;
        }

        public Criteria andStoredkeyNotEqualTo(String value) {
            addCriterion("STOREDKEY <>", value, "storedkey");
            return (Criteria) this;
        }

        public Criteria andStoredkeyGreaterThan(String value) {
            addCriterion("STOREDKEY >", value, "storedkey");
            return (Criteria) this;
        }

        public Criteria andStoredkeyGreaterThanOrEqualTo(String value) {
            addCriterion("STOREDKEY >=", value, "storedkey");
            return (Criteria) this;
        }

        public Criteria andStoredkeyLessThan(String value) {
            addCriterion("STOREDKEY <", value, "storedkey");
            return (Criteria) this;
        }

        public Criteria andStoredkeyLessThanOrEqualTo(String value) {
            addCriterion("STOREDKEY <=", value, "storedkey");
            return (Criteria) this;
        }

        public Criteria andStoredkeyLike(String value) {
            addCriterion("STOREDKEY like", value, "storedkey");
            return (Criteria) this;
        }

        public Criteria andStoredkeyNotLike(String value) {
            addCriterion("STOREDKEY not like", value, "storedkey");
            return (Criteria) this;
        }

        public Criteria andStoredkeyIn(List<String> values) {
            addCriterion("STOREDKEY in", values, "storedkey");
            return (Criteria) this;
        }

        public Criteria andStoredkeyNotIn(List<String> values) {
            addCriterion("STOREDKEY not in", values, "storedkey");
            return (Criteria) this;
        }

        public Criteria andStoredkeyBetween(String value1, String value2) {
            addCriterion("STOREDKEY between", value1, value2, "storedkey");
            return (Criteria) this;
        }

        public Criteria andStoredkeyNotBetween(String value1, String value2) {
            addCriterion("STOREDKEY not between", value1, value2, "storedkey");
            return (Criteria) this;
        }

        public Criteria andServerkeyIsNull() {
            addCriterion("SERVERKEY is null");
            return (Criteria) this;
        }

        public Criteria andServerkeyIsNotNull() {
            addCriterion("SERVERKEY is not null");
            return (Criteria) this;
        }

        public Criteria andServerkeyEqualTo(String value) {
            addCriterion("SERVERKEY =", value, "serverkey");
            return (Criteria) this;
        }

        public Criteria andServerkeyNotEqualTo(String value) {
            addCriterion("SERVERKEY <>", value, "serverkey");
            return (Criteria) this;
        }

        public Criteria andServerkeyGreaterThan(String value) {
            addCriterion("SERVERKEY >", value, "serverkey");
            return (Criteria) this;
        }

        public Criteria andServerkeyGreaterThanOrEqualTo(String value) {
            addCriterion("SERVERKEY >=", value, "serverkey");
            return (Criteria) this;
        }

        public Criteria andServerkeyLessThan(String value) {
            addCriterion("SERVERKEY <", value, "serverkey");
            return (Criteria) this;
        }

        public Criteria andServerkeyLessThanOrEqualTo(String value) {
            addCriterion("SERVERKEY <=", value, "serverkey");
            return (Criteria) this;
        }

        public Criteria andServerkeyLike(String value) {
            addCriterion("SERVERKEY like", value, "serverkey");
            return (Criteria) this;
        }

        public Criteria andServerkeyNotLike(String value) {
            addCriterion("SERVERKEY not like", value, "serverkey");
            return (Criteria) this;
        }

        public Criteria andServerkeyIn(List<String> values) {
            addCriterion("SERVERKEY in", values, "serverkey");
            return (Criteria) this;
        }

        public Criteria andServerkeyNotIn(List<String> values) {
            addCriterion("SERVERKEY not in", values, "serverkey");
            return (Criteria) this;
        }

        public Criteria andServerkeyBetween(String value1, String value2) {
            addCriterion("SERVERKEY between", value1, value2, "serverkey");
            return (Criteria) this;
        }

        public Criteria andServerkeyNotBetween(String value1, String value2) {
            addCriterion("SERVERKEY not between", value1, value2, "serverkey");
            return (Criteria) this;
        }

        public Criteria andSaltIsNull() {
            addCriterion("SALT is null");
            return (Criteria) this;
        }

        public Criteria andSaltIsNotNull() {
            addCriterion("SALT is not null");
            return (Criteria) this;
        }

        public Criteria andSaltEqualTo(String value) {
            addCriterion("SALT =", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotEqualTo(String value) {
            addCriterion("SALT <>", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThan(String value) {
            addCriterion("SALT >", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltGreaterThanOrEqualTo(String value) {
            addCriterion("SALT >=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThan(String value) {
            addCriterion("SALT <", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLessThanOrEqualTo(String value) {
            addCriterion("SALT <=", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltLike(String value) {
            addCriterion("SALT like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotLike(String value) {
            addCriterion("SALT not like", value, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltIn(List<String> values) {
            addCriterion("SALT in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotIn(List<String> values) {
            addCriterion("SALT not in", values, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltBetween(String value1, String value2) {
            addCriterion("SALT between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andSaltNotBetween(String value1, String value2) {
            addCriterion("SALT not between", value1, value2, "salt");
            return (Criteria) this;
        }

        public Criteria andIterationsIsNull() {
            addCriterion("ITERATIONS is null");
            return (Criteria) this;
        }

        public Criteria andIterationsIsNotNull() {
            addCriterion("ITERATIONS is not null");
            return (Criteria) this;
        }

        public Criteria andIterationsEqualTo(BigDecimal value) {
            addCriterion("ITERATIONS =", value, "iterations");
            return (Criteria) this;
        }

        public Criteria andIterationsNotEqualTo(BigDecimal value) {
            addCriterion("ITERATIONS <>", value, "iterations");
            return (Criteria) this;
        }

        public Criteria andIterationsGreaterThan(BigDecimal value) {
            addCriterion("ITERATIONS >", value, "iterations");
            return (Criteria) this;
        }

        public Criteria andIterationsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ITERATIONS >=", value, "iterations");
            return (Criteria) this;
        }

        public Criteria andIterationsLessThan(BigDecimal value) {
            addCriterion("ITERATIONS <", value, "iterations");
            return (Criteria) this;
        }

        public Criteria andIterationsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ITERATIONS <=", value, "iterations");
            return (Criteria) this;
        }

        public Criteria andIterationsIn(List<BigDecimal> values) {
            addCriterion("ITERATIONS in", values, "iterations");
            return (Criteria) this;
        }

        public Criteria andIterationsNotIn(List<BigDecimal> values) {
            addCriterion("ITERATIONS not in", values, "iterations");
            return (Criteria) this;
        }

        public Criteria andIterationsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ITERATIONS between", value1, value2, "iterations");
            return (Criteria) this;
        }

        public Criteria andIterationsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ITERATIONS not between", value1, value2, "iterations");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordIsNull() {
            addCriterion("PLAINPASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordIsNotNull() {
            addCriterion("PLAINPASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordEqualTo(String value) {
            addCriterion("PLAINPASSWORD =", value, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordNotEqualTo(String value) {
            addCriterion("PLAINPASSWORD <>", value, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordGreaterThan(String value) {
            addCriterion("PLAINPASSWORD >", value, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("PLAINPASSWORD >=", value, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordLessThan(String value) {
            addCriterion("PLAINPASSWORD <", value, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordLessThanOrEqualTo(String value) {
            addCriterion("PLAINPASSWORD <=", value, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordLike(String value) {
            addCriterion("PLAINPASSWORD like", value, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordNotLike(String value) {
            addCriterion("PLAINPASSWORD not like", value, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordIn(List<String> values) {
            addCriterion("PLAINPASSWORD in", values, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordNotIn(List<String> values) {
            addCriterion("PLAINPASSWORD not in", values, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordBetween(String value1, String value2) {
            addCriterion("PLAINPASSWORD between", value1, value2, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andPlainpasswordNotBetween(String value1, String value2) {
            addCriterion("PLAINPASSWORD not between", value1, value2, "plainpassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedpasswordIsNull() {
            addCriterion("ENCRYPTEDPASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andEncryptedpasswordIsNotNull() {
            addCriterion("ENCRYPTEDPASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andEncryptedpasswordEqualTo(String value) {
            addCriterion("ENCRYPTEDPASSWORD =", value, "encryptedpassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedpasswordNotEqualTo(String value) {
            addCriterion("ENCRYPTEDPASSWORD <>", value, "encryptedpassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedpasswordGreaterThan(String value) {
            addCriterion("ENCRYPTEDPASSWORD >", value, "encryptedpassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedpasswordGreaterThanOrEqualTo(String value) {
            addCriterion("ENCRYPTEDPASSWORD >=", value, "encryptedpassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedpasswordLessThan(String value) {
            addCriterion("ENCRYPTEDPASSWORD <", value, "encryptedpassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedpasswordLessThanOrEqualTo(String value) {
            addCriterion("ENCRYPTEDPASSWORD <=", value, "encryptedpassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedpasswordLike(String value) {
            addCriterion("ENCRYPTEDPASSWORD like", value, "encryptedpassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedpasswordNotLike(String value) {
            addCriterion("ENCRYPTEDPASSWORD not like", value, "encryptedpassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedpasswordIn(List<String> values) {
            addCriterion("ENCRYPTEDPASSWORD in", values, "encryptedpassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedpasswordNotIn(List<String> values) {
            addCriterion("ENCRYPTEDPASSWORD not in", values, "encryptedpassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedpasswordBetween(String value1, String value2) {
            addCriterion("ENCRYPTEDPASSWORD between", value1, value2, "encryptedpassword");
            return (Criteria) this;
        }

        public Criteria andEncryptedpasswordNotBetween(String value1, String value2) {
            addCriterion("ENCRYPTEDPASSWORD not between", value1, value2, "encryptedpassword");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andCreationdateIsNull() {
            addCriterion("CREATIONDATE is null");
            return (Criteria) this;
        }

        public Criteria andCreationdateIsNotNull() {
            addCriterion("CREATIONDATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreationdateEqualTo(String value) {
            addCriterion("CREATIONDATE =", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateNotEqualTo(String value) {
            addCriterion("CREATIONDATE <>", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateGreaterThan(String value) {
            addCriterion("CREATIONDATE >", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateGreaterThanOrEqualTo(String value) {
            addCriterion("CREATIONDATE >=", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateLessThan(String value) {
            addCriterion("CREATIONDATE <", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateLessThanOrEqualTo(String value) {
            addCriterion("CREATIONDATE <=", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateLike(String value) {
            addCriterion("CREATIONDATE like", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateNotLike(String value) {
            addCriterion("CREATIONDATE not like", value, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateIn(List<String> values) {
            addCriterion("CREATIONDATE in", values, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateNotIn(List<String> values) {
            addCriterion("CREATIONDATE not in", values, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateBetween(String value1, String value2) {
            addCriterion("CREATIONDATE between", value1, value2, "creationdate");
            return (Criteria) this;
        }

        public Criteria andCreationdateNotBetween(String value1, String value2) {
            addCriterion("CREATIONDATE not between", value1, value2, "creationdate");
            return (Criteria) this;
        }

        public Criteria andModificationdateIsNull() {
            addCriterion("MODIFICATIONDATE is null");
            return (Criteria) this;
        }

        public Criteria andModificationdateIsNotNull() {
            addCriterion("MODIFICATIONDATE is not null");
            return (Criteria) this;
        }

        public Criteria andModificationdateEqualTo(String value) {
            addCriterion("MODIFICATIONDATE =", value, "modificationdate");
            return (Criteria) this;
        }

        public Criteria andModificationdateNotEqualTo(String value) {
            addCriterion("MODIFICATIONDATE <>", value, "modificationdate");
            return (Criteria) this;
        }

        public Criteria andModificationdateGreaterThan(String value) {
            addCriterion("MODIFICATIONDATE >", value, "modificationdate");
            return (Criteria) this;
        }

        public Criteria andModificationdateGreaterThanOrEqualTo(String value) {
            addCriterion("MODIFICATIONDATE >=", value, "modificationdate");
            return (Criteria) this;
        }

        public Criteria andModificationdateLessThan(String value) {
            addCriterion("MODIFICATIONDATE <", value, "modificationdate");
            return (Criteria) this;
        }

        public Criteria andModificationdateLessThanOrEqualTo(String value) {
            addCriterion("MODIFICATIONDATE <=", value, "modificationdate");
            return (Criteria) this;
        }

        public Criteria andModificationdateLike(String value) {
            addCriterion("MODIFICATIONDATE like", value, "modificationdate");
            return (Criteria) this;
        }

        public Criteria andModificationdateNotLike(String value) {
            addCriterion("MODIFICATIONDATE not like", value, "modificationdate");
            return (Criteria) this;
        }

        public Criteria andModificationdateIn(List<String> values) {
            addCriterion("MODIFICATIONDATE in", values, "modificationdate");
            return (Criteria) this;
        }

        public Criteria andModificationdateNotIn(List<String> values) {
            addCriterion("MODIFICATIONDATE not in", values, "modificationdate");
            return (Criteria) this;
        }

        public Criteria andModificationdateBetween(String value1, String value2) {
            addCriterion("MODIFICATIONDATE between", value1, value2, "modificationdate");
            return (Criteria) this;
        }

        public Criteria andModificationdateNotBetween(String value1, String value2) {
            addCriterion("MODIFICATIONDATE not between", value1, value2, "modificationdate");
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