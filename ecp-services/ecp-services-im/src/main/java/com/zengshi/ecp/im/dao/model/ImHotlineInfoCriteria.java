package com.zengshi.ecp.im.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ImHotlineInfoCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public ImHotlineInfoCriteria() {
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

        public Criteria andModuleTypeIsNull() {
            addCriterion("MODULE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIsNotNull() {
            addCriterion("MODULE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andModuleTypeEqualTo(String value) {
            addCriterion("MODULE_TYPE =", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNotEqualTo(String value) {
            addCriterion("MODULE_TYPE <>", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeGreaterThan(String value) {
            addCriterion("MODULE_TYPE >", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_TYPE >=", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeLessThan(String value) {
            addCriterion("MODULE_TYPE <", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeLessThanOrEqualTo(String value) {
            addCriterion("MODULE_TYPE <=", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeLike(String value) {
            addCriterion("MODULE_TYPE like", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNotLike(String value) {
            addCriterion("MODULE_TYPE not like", value, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIn(List<String> values) {
            addCriterion("MODULE_TYPE in", values, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNotIn(List<String> values) {
            addCriterion("MODULE_TYPE not in", values, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeBetween(String value1, String value2) {
            addCriterion("MODULE_TYPE between", value1, value2, "moduleType");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNotBetween(String value1, String value2) {
            addCriterion("MODULE_TYPE not between", value1, value2, "moduleType");
            return (Criteria) this;
        }

        public Criteria andHotlinePersonIsNull() {
            addCriterion("HOTLINE_PERSON is null");
            return (Criteria) this;
        }

        public Criteria andHotlinePersonIsNotNull() {
            addCriterion("HOTLINE_PERSON is not null");
            return (Criteria) this;
        }

        public Criteria andHotlinePersonEqualTo(String value) {
            addCriterion("HOTLINE_PERSON =", value, "hotlinePerson");
            return (Criteria) this;
        }

        public Criteria andHotlinePersonNotEqualTo(String value) {
            addCriterion("HOTLINE_PERSON <>", value, "hotlinePerson");
            return (Criteria) this;
        }

        public Criteria andHotlinePersonGreaterThan(String value) {
            addCriterion("HOTLINE_PERSON >", value, "hotlinePerson");
            return (Criteria) this;
        }

        public Criteria andHotlinePersonGreaterThanOrEqualTo(String value) {
            addCriterion("HOTLINE_PERSON >=", value, "hotlinePerson");
            return (Criteria) this;
        }

        public Criteria andHotlinePersonLessThan(String value) {
            addCriterion("HOTLINE_PERSON <", value, "hotlinePerson");
            return (Criteria) this;
        }

        public Criteria andHotlinePersonLessThanOrEqualTo(String value) {
            addCriterion("HOTLINE_PERSON <=", value, "hotlinePerson");
            return (Criteria) this;
        }

        public Criteria andHotlinePersonLike(String value) {
            addCriterion("HOTLINE_PERSON like", value, "hotlinePerson");
            return (Criteria) this;
        }

        public Criteria andHotlinePersonNotLike(String value) {
            addCriterion("HOTLINE_PERSON not like", value, "hotlinePerson");
            return (Criteria) this;
        }

        public Criteria andHotlinePersonIn(List<String> values) {
            addCriterion("HOTLINE_PERSON in", values, "hotlinePerson");
            return (Criteria) this;
        }

        public Criteria andHotlinePersonNotIn(List<String> values) {
            addCriterion("HOTLINE_PERSON not in", values, "hotlinePerson");
            return (Criteria) this;
        }

        public Criteria andHotlinePersonBetween(String value1, String value2) {
            addCriterion("HOTLINE_PERSON between", value1, value2, "hotlinePerson");
            return (Criteria) this;
        }

        public Criteria andHotlinePersonNotBetween(String value1, String value2) {
            addCriterion("HOTLINE_PERSON not between", value1, value2, "hotlinePerson");
            return (Criteria) this;
        }

        public Criteria andHotlinePhoneIsNull() {
            addCriterion("HOTLINE_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andHotlinePhoneIsNotNull() {
            addCriterion("HOTLINE_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andHotlinePhoneEqualTo(Long value) {
            addCriterion("HOTLINE_PHONE =", value, "hotlinePhone");
            return (Criteria) this;
        }

        public Criteria andHotlinePhoneNotEqualTo(Long value) {
            addCriterion("HOTLINE_PHONE <>", value, "hotlinePhone");
            return (Criteria) this;
        }

        public Criteria andHotlinePhoneGreaterThan(Long value) {
            addCriterion("HOTLINE_PHONE >", value, "hotlinePhone");
            return (Criteria) this;
        }

        public Criteria andHotlinePhoneGreaterThanOrEqualTo(Long value) {
            addCriterion("HOTLINE_PHONE >=", value, "hotlinePhone");
            return (Criteria) this;
        }

        public Criteria andHotlinePhoneLessThan(Long value) {
            addCriterion("HOTLINE_PHONE <", value, "hotlinePhone");
            return (Criteria) this;
        }

        public Criteria andHotlinePhoneLessThanOrEqualTo(Long value) {
            addCriterion("HOTLINE_PHONE <=", value, "hotlinePhone");
            return (Criteria) this;
        }

        public Criteria andHotlinePhoneIn(List<Long> values) {
            addCriterion("HOTLINE_PHONE in", values, "hotlinePhone");
            return (Criteria) this;
        }

        public Criteria andHotlinePhoneNotIn(List<Long> values) {
            addCriterion("HOTLINE_PHONE not in", values, "hotlinePhone");
            return (Criteria) this;
        }

        public Criteria andHotlinePhoneBetween(Long value1, Long value2) {
            addCriterion("HOTLINE_PHONE between", value1, value2, "hotlinePhone");
            return (Criteria) this;
        }

        public Criteria andHotlinePhoneNotBetween(Long value1, Long value2) {
            addCriterion("HOTLINE_PHONE not between", value1, value2, "hotlinePhone");
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

        public Criteria andReceptionCountIsNull() {
            addCriterion("RECEPTION_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andReceptionCountIsNotNull() {
            addCriterion("RECEPTION_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andReceptionCountEqualTo(Short value) {
            addCriterion("RECEPTION_COUNT =", value, "receptionCount");
            return (Criteria) this;
        }

        public Criteria andReceptionCountNotEqualTo(Short value) {
            addCriterion("RECEPTION_COUNT <>", value, "receptionCount");
            return (Criteria) this;
        }

        public Criteria andReceptionCountGreaterThan(Short value) {
            addCriterion("RECEPTION_COUNT >", value, "receptionCount");
            return (Criteria) this;
        }

        public Criteria andReceptionCountGreaterThanOrEqualTo(Short value) {
            addCriterion("RECEPTION_COUNT >=", value, "receptionCount");
            return (Criteria) this;
        }

        public Criteria andReceptionCountLessThan(Short value) {
            addCriterion("RECEPTION_COUNT <", value, "receptionCount");
            return (Criteria) this;
        }

        public Criteria andReceptionCountLessThanOrEqualTo(Short value) {
            addCriterion("RECEPTION_COUNT <=", value, "receptionCount");
            return (Criteria) this;
        }

        public Criteria andReceptionCountIn(List<Short> values) {
            addCriterion("RECEPTION_COUNT in", values, "receptionCount");
            return (Criteria) this;
        }

        public Criteria andReceptionCountNotIn(List<Short> values) {
            addCriterion("RECEPTION_COUNT not in", values, "receptionCount");
            return (Criteria) this;
        }

        public Criteria andReceptionCountBetween(Short value1, Short value2) {
            addCriterion("RECEPTION_COUNT between", value1, value2, "receptionCount");
            return (Criteria) this;
        }

        public Criteria andReceptionCountNotBetween(Short value1, Short value2) {
            addCriterion("RECEPTION_COUNT not between", value1, value2, "receptionCount");
            return (Criteria) this;
        }

        public Criteria andPlatSourceEqualTo(String value) {
            addCriterion("PLAT_SOURCE =", value, "platSource");
            return (Criteria) this;
        }
        
        public Criteria andOrderEditIsNull() {
            addCriterion("ORDER_EDIT is null");
            return (Criteria) this;
        }

        public Criteria andOrderEditIsNotNull() {
            addCriterion("ORDER_EDIT is not null");
            return (Criteria) this;
        }

        public Criteria andOrderEditEqualTo(String value) {
            addCriterion("ORDER_EDIT =", value, "orderEdit");
            return (Criteria) this;
        }

        public Criteria andOrderEditNotEqualTo(String value) {
            addCriterion("ORDER_EDIT <>", value, "orderEdit");
            return (Criteria) this;
        }

        public Criteria andOrderEditGreaterThan(String value) {
            addCriterion("ORDER_EDIT >", value, "orderEdit");
            return (Criteria) this;
        }

        public Criteria andOrderEditGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_EDIT >=", value, "orderEdit");
            return (Criteria) this;
        }

        public Criteria andOrderEditLessThan(String value) {
            addCriterion("ORDER_EDIT <", value, "orderEdit");
            return (Criteria) this;
        }

        public Criteria andOrderEditLessThanOrEqualTo(String value) {
            addCriterion("ORDER_EDIT <=", value, "orderEdit");
            return (Criteria) this;
        }

        public Criteria andOrderEditLike(String value) {
            addCriterion("ORDER_EDIT like", value, "orderEdit");
            return (Criteria) this;
        }

        public Criteria andOrderEditNotLike(String value) {
            addCriterion("ORDER_EDIT not like", value, "orderEdit");
            return (Criteria) this;
        }

        public Criteria andOrderEditIn(List<String> values) {
            addCriterion("ORDER_EDIT in", values, "orderEdit");
            return (Criteria) this;
        }

        public Criteria andOrderEditNotIn(List<String> values) {
            addCriterion("ORDER_EDIT not in", values, "orderEdit");
            return (Criteria) this;
        }

        public Criteria andOrderEditBetween(String value1, String value2) {
            addCriterion("ORDER_EDIT between", value1, value2, "orderEdit");
            return (Criteria) this;
        }

        public Criteria andOrderEditNotBetween(String value1, String value2) {
            addCriterion("ORDER_EDIT not between", value1, value2, "orderEdit");
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