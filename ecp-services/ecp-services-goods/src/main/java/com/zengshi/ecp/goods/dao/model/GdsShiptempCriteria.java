package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GdsShiptempCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsShiptempCriteria() {
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

        public Criteria andShipTemplateNameIsNull() {
            addCriterion("SHIP_TEMPLATE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andShipTemplateNameIsNotNull() {
            addCriterion("SHIP_TEMPLATE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andShipTemplateNameEqualTo(String value) {
            addCriterion("SHIP_TEMPLATE_NAME =", value, "shipTemplateName");
            return (Criteria) this;
        }

        public Criteria andShipTemplateNameNotEqualTo(String value) {
            addCriterion("SHIP_TEMPLATE_NAME <>", value, "shipTemplateName");
            return (Criteria) this;
        }

        public Criteria andShipTemplateNameGreaterThan(String value) {
            addCriterion("SHIP_TEMPLATE_NAME >", value, "shipTemplateName");
            return (Criteria) this;
        }

        public Criteria andShipTemplateNameGreaterThanOrEqualTo(String value) {
            addCriterion("SHIP_TEMPLATE_NAME >=", value, "shipTemplateName");
            return (Criteria) this;
        }

        public Criteria andShipTemplateNameLessThan(String value) {
            addCriterion("SHIP_TEMPLATE_NAME <", value, "shipTemplateName");
            return (Criteria) this;
        }

        public Criteria andShipTemplateNameLessThanOrEqualTo(String value) {
            addCriterion("SHIP_TEMPLATE_NAME <=", value, "shipTemplateName");
            return (Criteria) this;
        }

        public Criteria andShipTemplateNameLike(String value) {
            addCriterion("SHIP_TEMPLATE_NAME like", value, "shipTemplateName");
            return (Criteria) this;
        }

        public Criteria andShipTemplateNameNotLike(String value) {
            addCriterion("SHIP_TEMPLATE_NAME not like", value, "shipTemplateName");
            return (Criteria) this;
        }

        public Criteria andShipTemplateNameIn(List<String> values) {
            addCriterion("SHIP_TEMPLATE_NAME in", values, "shipTemplateName");
            return (Criteria) this;
        }

        public Criteria andShipTemplateNameNotIn(List<String> values) {
            addCriterion("SHIP_TEMPLATE_NAME not in", values, "shipTemplateName");
            return (Criteria) this;
        }

        public Criteria andShipTemplateNameBetween(String value1, String value2) {
            addCriterion("SHIP_TEMPLATE_NAME between", value1, value2, "shipTemplateName");
            return (Criteria) this;
        }

        public Criteria andShipTemplateNameNotBetween(String value1, String value2) {
            addCriterion("SHIP_TEMPLATE_NAME not between", value1, value2, "shipTemplateName");
            return (Criteria) this;
        }

        public Criteria andShipTemplateTypeIsNull() {
            addCriterion("SHIP_TEMPLATE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andShipTemplateTypeIsNotNull() {
            addCriterion("SHIP_TEMPLATE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andShipTemplateTypeEqualTo(String value) {
            addCriterion("SHIP_TEMPLATE_TYPE =", value, "shipTemplateType");
            return (Criteria) this;
        }

        public Criteria andShipTemplateTypeNotEqualTo(String value) {
            addCriterion("SHIP_TEMPLATE_TYPE <>", value, "shipTemplateType");
            return (Criteria) this;
        }

        public Criteria andShipTemplateTypeGreaterThan(String value) {
            addCriterion("SHIP_TEMPLATE_TYPE >", value, "shipTemplateType");
            return (Criteria) this;
        }

        public Criteria andShipTemplateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SHIP_TEMPLATE_TYPE >=", value, "shipTemplateType");
            return (Criteria) this;
        }

        public Criteria andShipTemplateTypeLessThan(String value) {
            addCriterion("SHIP_TEMPLATE_TYPE <", value, "shipTemplateType");
            return (Criteria) this;
        }

        public Criteria andShipTemplateTypeLessThanOrEqualTo(String value) {
            addCriterion("SHIP_TEMPLATE_TYPE <=", value, "shipTemplateType");
            return (Criteria) this;
        }

        public Criteria andShipTemplateTypeLike(String value) {
            addCriterion("SHIP_TEMPLATE_TYPE like", value, "shipTemplateType");
            return (Criteria) this;
        }

        public Criteria andShipTemplateTypeNotLike(String value) {
            addCriterion("SHIP_TEMPLATE_TYPE not like", value, "shipTemplateType");
            return (Criteria) this;
        }

        public Criteria andShipTemplateTypeIn(List<String> values) {
            addCriterion("SHIP_TEMPLATE_TYPE in", values, "shipTemplateType");
            return (Criteria) this;
        }

        public Criteria andShipTemplateTypeNotIn(List<String> values) {
            addCriterion("SHIP_TEMPLATE_TYPE not in", values, "shipTemplateType");
            return (Criteria) this;
        }

        public Criteria andShipTemplateTypeBetween(String value1, String value2) {
            addCriterion("SHIP_TEMPLATE_TYPE between", value1, value2, "shipTemplateType");
            return (Criteria) this;
        }

        public Criteria andShipTemplateTypeNotBetween(String value1, String value2) {
            addCriterion("SHIP_TEMPLATE_TYPE not between", value1, value2, "shipTemplateType");
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

        public Criteria andIfFreeIsNull() {
            addCriterion("IF_FREE is null");
            return (Criteria) this;
        }

        public Criteria andIfFreeIsNotNull() {
            addCriterion("IF_FREE is not null");
            return (Criteria) this;
        }

        public Criteria andIfFreeEqualTo(String value) {
            addCriterion("IF_FREE =", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeNotEqualTo(String value) {
            addCriterion("IF_FREE <>", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeGreaterThan(String value) {
            addCriterion("IF_FREE >", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeGreaterThanOrEqualTo(String value) {
            addCriterion("IF_FREE >=", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeLessThan(String value) {
            addCriterion("IF_FREE <", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeLessThanOrEqualTo(String value) {
            addCriterion("IF_FREE <=", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeLike(String value) {
            addCriterion("IF_FREE like", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeNotLike(String value) {
            addCriterion("IF_FREE not like", value, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeIn(List<String> values) {
            addCriterion("IF_FREE in", values, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeNotIn(List<String> values) {
            addCriterion("IF_FREE not in", values, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeBetween(String value1, String value2) {
            addCriterion("IF_FREE between", value1, value2, "ifFree");
            return (Criteria) this;
        }

        public Criteria andIfFreeNotBetween(String value1, String value2) {
            addCriterion("IF_FREE not between", value1, value2, "ifFree");
            return (Criteria) this;
        }

        public Criteria andShipInstructionIsNull() {
            addCriterion("SHIP_INSTRUCTION is null");
            return (Criteria) this;
        }

        public Criteria andShipInstructionIsNotNull() {
            addCriterion("SHIP_INSTRUCTION is not null");
            return (Criteria) this;
        }

        public Criteria andShipInstructionEqualTo(String value) {
            addCriterion("SHIP_INSTRUCTION =", value, "shipInstruction");
            return (Criteria) this;
        }

        public Criteria andShipInstructionNotEqualTo(String value) {
            addCriterion("SHIP_INSTRUCTION <>", value, "shipInstruction");
            return (Criteria) this;
        }

        public Criteria andShipInstructionGreaterThan(String value) {
            addCriterion("SHIP_INSTRUCTION >", value, "shipInstruction");
            return (Criteria) this;
        }

        public Criteria andShipInstructionGreaterThanOrEqualTo(String value) {
            addCriterion("SHIP_INSTRUCTION >=", value, "shipInstruction");
            return (Criteria) this;
        }

        public Criteria andShipInstructionLessThan(String value) {
            addCriterion("SHIP_INSTRUCTION <", value, "shipInstruction");
            return (Criteria) this;
        }

        public Criteria andShipInstructionLessThanOrEqualTo(String value) {
            addCriterion("SHIP_INSTRUCTION <=", value, "shipInstruction");
            return (Criteria) this;
        }

        public Criteria andShipInstructionLike(String value) {
            addCriterion("SHIP_INSTRUCTION like", value, "shipInstruction");
            return (Criteria) this;
        }

        public Criteria andShipInstructionNotLike(String value) {
            addCriterion("SHIP_INSTRUCTION not like", value, "shipInstruction");
            return (Criteria) this;
        }

        public Criteria andShipInstructionIn(List<String> values) {
            addCriterion("SHIP_INSTRUCTION in", values, "shipInstruction");
            return (Criteria) this;
        }

        public Criteria andShipInstructionNotIn(List<String> values) {
            addCriterion("SHIP_INSTRUCTION not in", values, "shipInstruction");
            return (Criteria) this;
        }

        public Criteria andShipInstructionBetween(String value1, String value2) {
            addCriterion("SHIP_INSTRUCTION between", value1, value2, "shipInstruction");
            return (Criteria) this;
        }

        public Criteria andShipInstructionNotBetween(String value1, String value2) {
            addCriterion("SHIP_INSTRUCTION not between", value1, value2, "shipInstruction");
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
