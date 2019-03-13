package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PayQuartzInfoLogCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PayQuartzInfoLogCriteria() {
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

        public Criteria andOriginalIdIsNull() {
            addCriterion("ORIGINAL_ID is null");
            return (Criteria) this;
        }

        public Criteria andOriginalIdIsNotNull() {
            addCriterion("ORIGINAL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalIdEqualTo(Long value) {
            addCriterion("ORIGINAL_ID =", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdNotEqualTo(Long value) {
            addCriterion("ORIGINAL_ID <>", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdGreaterThan(Long value) {
            addCriterion("ORIGINAL_ID >", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ORIGINAL_ID >=", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdLessThan(Long value) {
            addCriterion("ORIGINAL_ID <", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdLessThanOrEqualTo(Long value) {
            addCriterion("ORIGINAL_ID <=", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdIn(List<Long> values) {
            addCriterion("ORIGINAL_ID in", values, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdNotIn(List<Long> values) {
            addCriterion("ORIGINAL_ID not in", values, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdBetween(Long value1, Long value2) {
            addCriterion("ORIGINAL_ID between", value1, value2, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdNotBetween(Long value1, Long value2) {
            addCriterion("ORIGINAL_ID not between", value1, value2, "originalId");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNull() {
            addCriterion("TASK_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNotNull() {
            addCriterion("TASK_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeEqualTo(String value) {
            addCriterion("TASK_TYPE =", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotEqualTo(String value) {
            addCriterion("TASK_TYPE <>", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThan(String value) {
            addCriterion("TASK_TYPE >", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_TYPE >=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThan(String value) {
            addCriterion("TASK_TYPE <", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThanOrEqualTo(String value) {
            addCriterion("TASK_TYPE <=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLike(String value) {
            addCriterion("TASK_TYPE like", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotLike(String value) {
            addCriterion("TASK_TYPE not like", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIn(List<String> values) {
            addCriterion("TASK_TYPE in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotIn(List<String> values) {
            addCriterion("TASK_TYPE not in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeBetween(String value1, String value2) {
            addCriterion("TASK_TYPE between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotBetween(String value1, String value2) {
            addCriterion("TASK_TYPE not between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("ORDER_ID like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("ORDER_ID not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andSubOrderIsNull() {
            addCriterion("SUB_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderIsNotNull() {
            addCriterion("SUB_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderEqualTo(String value) {
            addCriterion("SUB_ORDER =", value, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderNotEqualTo(String value) {
            addCriterion("SUB_ORDER <>", value, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderGreaterThan(String value) {
            addCriterion("SUB_ORDER >", value, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderGreaterThanOrEqualTo(String value) {
            addCriterion("SUB_ORDER >=", value, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderLessThan(String value) {
            addCriterion("SUB_ORDER <", value, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderLessThanOrEqualTo(String value) {
            addCriterion("SUB_ORDER <=", value, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderLike(String value) {
            addCriterion("SUB_ORDER like", value, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderNotLike(String value) {
            addCriterion("SUB_ORDER not like", value, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderIn(List<String> values) {
            addCriterion("SUB_ORDER in", values, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderNotIn(List<String> values) {
            addCriterion("SUB_ORDER not in", values, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderBetween(String value1, String value2) {
            addCriterion("SUB_ORDER between", value1, value2, "subOrder");
            return (Criteria) this;
        }

        public Criteria andSubOrderNotBetween(String value1, String value2) {
            addCriterion("SUB_ORDER not between", value1, value2, "subOrder");
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

        public Criteria andDealFlagIsNull() {
            addCriterion("DEAL_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andDealFlagIsNotNull() {
            addCriterion("DEAL_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDealFlagEqualTo(String value) {
            addCriterion("DEAL_FLAG =", value, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagNotEqualTo(String value) {
            addCriterion("DEAL_FLAG <>", value, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagGreaterThan(String value) {
            addCriterion("DEAL_FLAG >", value, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagGreaterThanOrEqualTo(String value) {
            addCriterion("DEAL_FLAG >=", value, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagLessThan(String value) {
            addCriterion("DEAL_FLAG <", value, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagLessThanOrEqualTo(String value) {
            addCriterion("DEAL_FLAG <=", value, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagLike(String value) {
            addCriterion("DEAL_FLAG like", value, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagNotLike(String value) {
            addCriterion("DEAL_FLAG not like", value, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagIn(List<String> values) {
            addCriterion("DEAL_FLAG in", values, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagNotIn(List<String> values) {
            addCriterion("DEAL_FLAG not in", values, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagBetween(String value1, String value2) {
            addCriterion("DEAL_FLAG between", value1, value2, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andDealFlagNotBetween(String value1, String value2) {
            addCriterion("DEAL_FLAG not between", value1, value2, "dealFlag");
            return (Criteria) this;
        }

        public Criteria andErrorTimesIsNull() {
            addCriterion("ERROR_TIMES is null");
            return (Criteria) this;
        }

        public Criteria andErrorTimesIsNotNull() {
            addCriterion("ERROR_TIMES is not null");
            return (Criteria) this;
        }

        public Criteria andErrorTimesEqualTo(Integer value) {
            addCriterion("ERROR_TIMES =", value, "errorTimes");
            return (Criteria) this;
        }

        public Criteria andErrorTimesNotEqualTo(Integer value) {
            addCriterion("ERROR_TIMES <>", value, "errorTimes");
            return (Criteria) this;
        }

        public Criteria andErrorTimesGreaterThan(Integer value) {
            addCriterion("ERROR_TIMES >", value, "errorTimes");
            return (Criteria) this;
        }

        public Criteria andErrorTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("ERROR_TIMES >=", value, "errorTimes");
            return (Criteria) this;
        }

        public Criteria andErrorTimesLessThan(Integer value) {
            addCriterion("ERROR_TIMES <", value, "errorTimes");
            return (Criteria) this;
        }

        public Criteria andErrorTimesLessThanOrEqualTo(Integer value) {
            addCriterion("ERROR_TIMES <=", value, "errorTimes");
            return (Criteria) this;
        }

        public Criteria andErrorTimesIn(List<Integer> values) {
            addCriterion("ERROR_TIMES in", values, "errorTimes");
            return (Criteria) this;
        }

        public Criteria andErrorTimesNotIn(List<Integer> values) {
            addCriterion("ERROR_TIMES not in", values, "errorTimes");
            return (Criteria) this;
        }

        public Criteria andErrorTimesBetween(Integer value1, Integer value2) {
            addCriterion("ERROR_TIMES between", value1, value2, "errorTimes");
            return (Criteria) this;
        }

        public Criteria andErrorTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("ERROR_TIMES not between", value1, value2, "errorTimes");
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

        public Criteria andNextTimeIsNull() {
            addCriterion("NEXT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andNextTimeIsNotNull() {
            addCriterion("NEXT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andNextTimeEqualTo(Timestamp value) {
            addCriterion("NEXT_TIME =", value, "nextTime");
            return (Criteria) this;
        }

        public Criteria andNextTimeNotEqualTo(Timestamp value) {
            addCriterion("NEXT_TIME <>", value, "nextTime");
            return (Criteria) this;
        }

        public Criteria andNextTimeGreaterThan(Timestamp value) {
            addCriterion("NEXT_TIME >", value, "nextTime");
            return (Criteria) this;
        }

        public Criteria andNextTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("NEXT_TIME >=", value, "nextTime");
            return (Criteria) this;
        }

        public Criteria andNextTimeLessThan(Timestamp value) {
            addCriterion("NEXT_TIME <", value, "nextTime");
            return (Criteria) this;
        }

        public Criteria andNextTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("NEXT_TIME <=", value, "nextTime");
            return (Criteria) this;
        }

        public Criteria andNextTimeIn(List<Timestamp> values) {
            addCriterion("NEXT_TIME in", values, "nextTime");
            return (Criteria) this;
        }

        public Criteria andNextTimeNotIn(List<Timestamp> values) {
            addCriterion("NEXT_TIME not in", values, "nextTime");
            return (Criteria) this;
        }

        public Criteria andNextTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("NEXT_TIME between", value1, value2, "nextTime");
            return (Criteria) this;
        }

        public Criteria andNextTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("NEXT_TIME not between", value1, value2, "nextTime");
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

        public Criteria andLogTimeIsNull() {
            addCriterion("LOG_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLogTimeIsNotNull() {
            addCriterion("LOG_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLogTimeEqualTo(Timestamp value) {
            addCriterion("LOG_TIME =", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeNotEqualTo(Timestamp value) {
            addCriterion("LOG_TIME <>", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeGreaterThan(Timestamp value) {
            addCriterion("LOG_TIME >", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("LOG_TIME >=", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeLessThan(Timestamp value) {
            addCriterion("LOG_TIME <", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("LOG_TIME <=", value, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeIn(List<Timestamp> values) {
            addCriterion("LOG_TIME in", values, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeNotIn(List<Timestamp> values) {
            addCriterion("LOG_TIME not in", values, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("LOG_TIME between", value1, value2, "logTime");
            return (Criteria) this;
        }

        public Criteria andLogTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("LOG_TIME not between", value1, value2, "logTime");
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