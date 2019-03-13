package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GdsCatlogCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsCatlogCriteria() {
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

        public Criteria andCatlogNameIsNull() {
            addCriterion("CATLOG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCatlogNameIsNotNull() {
            addCriterion("CATLOG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCatlogNameEqualTo(String value) {
            addCriterion("CATLOG_NAME =", value, "catlogName");
            return (Criteria) this;
        }

        public Criteria andCatlogNameNotEqualTo(String value) {
            addCriterion("CATLOG_NAME <>", value, "catlogName");
            return (Criteria) this;
        }

        public Criteria andCatlogNameGreaterThan(String value) {
            addCriterion("CATLOG_NAME >", value, "catlogName");
            return (Criteria) this;
        }

        public Criteria andCatlogNameGreaterThanOrEqualTo(String value) {
            addCriterion("CATLOG_NAME >=", value, "catlogName");
            return (Criteria) this;
        }

        public Criteria andCatlogNameLessThan(String value) {
            addCriterion("CATLOG_NAME <", value, "catlogName");
            return (Criteria) this;
        }

        public Criteria andCatlogNameLessThanOrEqualTo(String value) {
            addCriterion("CATLOG_NAME <=", value, "catlogName");
            return (Criteria) this;
        }

        public Criteria andCatlogNameLike(String value) {
            addCriterion("CATLOG_NAME like", value, "catlogName");
            return (Criteria) this;
        }

        public Criteria andCatlogNameNotLike(String value) {
            addCriterion("CATLOG_NAME not like", value, "catlogName");
            return (Criteria) this;
        }

        public Criteria andCatlogNameIn(List<String> values) {
            addCriterion("CATLOG_NAME in", values, "catlogName");
            return (Criteria) this;
        }

        public Criteria andCatlogNameNotIn(List<String> values) {
            addCriterion("CATLOG_NAME not in", values, "catlogName");
            return (Criteria) this;
        }

        public Criteria andCatlogNameBetween(String value1, String value2) {
            addCriterion("CATLOG_NAME between", value1, value2, "catlogName");
            return (Criteria) this;
        }

        public Criteria andCatlogNameNotBetween(String value1, String value2) {
            addCriterion("CATLOG_NAME not between", value1, value2, "catlogName");
            return (Criteria) this;
        }

        public Criteria andCatlogCodeIsNull() {
            addCriterion("CATLOG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCatlogCodeIsNotNull() {
            addCriterion("CATLOG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCatlogCodeEqualTo(String value) {
            addCriterion("CATLOG_CODE =", value, "catlogCode");
            return (Criteria) this;
        }

        public Criteria andCatlogCodeNotEqualTo(String value) {
            addCriterion("CATLOG_CODE <>", value, "catlogCode");
            return (Criteria) this;
        }

        public Criteria andCatlogCodeGreaterThan(String value) {
            addCriterion("CATLOG_CODE >", value, "catlogCode");
            return (Criteria) this;
        }

        public Criteria andCatlogCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CATLOG_CODE >=", value, "catlogCode");
            return (Criteria) this;
        }

        public Criteria andCatlogCodeLessThan(String value) {
            addCriterion("CATLOG_CODE <", value, "catlogCode");
            return (Criteria) this;
        }

        public Criteria andCatlogCodeLessThanOrEqualTo(String value) {
            addCriterion("CATLOG_CODE <=", value, "catlogCode");
            return (Criteria) this;
        }

        public Criteria andCatlogCodeLike(String value) {
            addCriterion("CATLOG_CODE like", value, "catlogCode");
            return (Criteria) this;
        }

        public Criteria andCatlogCodeNotLike(String value) {
            addCriterion("CATLOG_CODE not like", value, "catlogCode");
            return (Criteria) this;
        }

        public Criteria andCatlogCodeIn(List<String> values) {
            addCriterion("CATLOG_CODE in", values, "catlogCode");
            return (Criteria) this;
        }

        public Criteria andCatlogCodeNotIn(List<String> values) {
            addCriterion("CATLOG_CODE not in", values, "catlogCode");
            return (Criteria) this;
        }

        public Criteria andCatlogCodeBetween(String value1, String value2) {
            addCriterion("CATLOG_CODE between", value1, value2, "catlogCode");
            return (Criteria) this;
        }

        public Criteria andCatlogCodeNotBetween(String value1, String value2) {
            addCriterion("CATLOG_CODE not between", value1, value2, "catlogCode");
            return (Criteria) this;
        }

        public Criteria andCatlogTypeIsNull() {
            addCriterion("CATLOG_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCatlogTypeIsNotNull() {
            addCriterion("CATLOG_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCatlogTypeEqualTo(String value) {
            addCriterion("CATLOG_TYPE =", value, "catlogType");
            return (Criteria) this;
        }

        public Criteria andCatlogTypeNotEqualTo(String value) {
            addCriterion("CATLOG_TYPE <>", value, "catlogType");
            return (Criteria) this;
        }

        public Criteria andCatlogTypeGreaterThan(String value) {
            addCriterion("CATLOG_TYPE >", value, "catlogType");
            return (Criteria) this;
        }

        public Criteria andCatlogTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CATLOG_TYPE >=", value, "catlogType");
            return (Criteria) this;
        }

        public Criteria andCatlogTypeLessThan(String value) {
            addCriterion("CATLOG_TYPE <", value, "catlogType");
            return (Criteria) this;
        }

        public Criteria andCatlogTypeLessThanOrEqualTo(String value) {
            addCriterion("CATLOG_TYPE <=", value, "catlogType");
            return (Criteria) this;
        }

        public Criteria andCatlogTypeLike(String value) {
            addCriterion("CATLOG_TYPE like", value, "catlogType");
            return (Criteria) this;
        }

        public Criteria andCatlogTypeNotLike(String value) {
            addCriterion("CATLOG_TYPE not like", value, "catlogType");
            return (Criteria) this;
        }

        public Criteria andCatlogTypeIn(List<String> values) {
            addCriterion("CATLOG_TYPE in", values, "catlogType");
            return (Criteria) this;
        }

        public Criteria andCatlogTypeNotIn(List<String> values) {
            addCriterion("CATLOG_TYPE not in", values, "catlogType");
            return (Criteria) this;
        }

        public Criteria andCatlogTypeBetween(String value1, String value2) {
            addCriterion("CATLOG_TYPE between", value1, value2, "catlogType");
            return (Criteria) this;
        }

        public Criteria andCatlogTypeNotBetween(String value1, String value2) {
            addCriterion("CATLOG_TYPE not between", value1, value2, "catlogType");
            return (Criteria) this;
        }

        public Criteria andCatlogDescIsNull() {
            addCriterion("CATLOG_DESC is null");
            return (Criteria) this;
        }

        public Criteria andCatlogDescIsNotNull() {
            addCriterion("CATLOG_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andCatlogDescEqualTo(String value) {
            addCriterion("CATLOG_DESC =", value, "catlogDesc");
            return (Criteria) this;
        }

        public Criteria andCatlogDescNotEqualTo(String value) {
            addCriterion("CATLOG_DESC <>", value, "catlogDesc");
            return (Criteria) this;
        }

        public Criteria andCatlogDescGreaterThan(String value) {
            addCriterion("CATLOG_DESC >", value, "catlogDesc");
            return (Criteria) this;
        }

        public Criteria andCatlogDescGreaterThanOrEqualTo(String value) {
            addCriterion("CATLOG_DESC >=", value, "catlogDesc");
            return (Criteria) this;
        }

        public Criteria andCatlogDescLessThan(String value) {
            addCriterion("CATLOG_DESC <", value, "catlogDesc");
            return (Criteria) this;
        }

        public Criteria andCatlogDescLessThanOrEqualTo(String value) {
            addCriterion("CATLOG_DESC <=", value, "catlogDesc");
            return (Criteria) this;
        }

        public Criteria andCatlogDescLike(String value) {
            addCriterion("CATLOG_DESC like", value, "catlogDesc");
            return (Criteria) this;
        }

        public Criteria andCatlogDescNotLike(String value) {
            addCriterion("CATLOG_DESC not like", value, "catlogDesc");
            return (Criteria) this;
        }

        public Criteria andCatlogDescIn(List<String> values) {
            addCriterion("CATLOG_DESC in", values, "catlogDesc");
            return (Criteria) this;
        }

        public Criteria andCatlogDescNotIn(List<String> values) {
            addCriterion("CATLOG_DESC not in", values, "catlogDesc");
            return (Criteria) this;
        }

        public Criteria andCatlogDescBetween(String value1, String value2) {
            addCriterion("CATLOG_DESC between", value1, value2, "catlogDesc");
            return (Criteria) this;
        }

        public Criteria andCatlogDescNotBetween(String value1, String value2) {
            addCriterion("CATLOG_DESC not between", value1, value2, "catlogDesc");
            return (Criteria) this;
        }

        public Criteria andIfDefaultIsNull() {
            addCriterion("IF_DEFAULT is null");
            return (Criteria) this;
        }

        public Criteria andIfDefaultIsNotNull() {
            addCriterion("IF_DEFAULT is not null");
            return (Criteria) this;
        }

        public Criteria andIfDefaultEqualTo(String value) {
            addCriterion("IF_DEFAULT =", value, "ifDefault");
            return (Criteria) this;
        }

        public Criteria andIfDefaultNotEqualTo(String value) {
            addCriterion("IF_DEFAULT <>", value, "ifDefault");
            return (Criteria) this;
        }

        public Criteria andIfDefaultGreaterThan(String value) {
            addCriterion("IF_DEFAULT >", value, "ifDefault");
            return (Criteria) this;
        }

        public Criteria andIfDefaultGreaterThanOrEqualTo(String value) {
            addCriterion("IF_DEFAULT >=", value, "ifDefault");
            return (Criteria) this;
        }

        public Criteria andIfDefaultLessThan(String value) {
            addCriterion("IF_DEFAULT <", value, "ifDefault");
            return (Criteria) this;
        }

        public Criteria andIfDefaultLessThanOrEqualTo(String value) {
            addCriterion("IF_DEFAULT <=", value, "ifDefault");
            return (Criteria) this;
        }

        public Criteria andIfDefaultLike(String value) {
            addCriterion("IF_DEFAULT like", value, "ifDefault");
            return (Criteria) this;
        }

        public Criteria andIfDefaultNotLike(String value) {
            addCriterion("IF_DEFAULT not like", value, "ifDefault");
            return (Criteria) this;
        }

        public Criteria andIfDefaultIn(List<String> values) {
            addCriterion("IF_DEFAULT in", values, "ifDefault");
            return (Criteria) this;
        }

        public Criteria andIfDefaultNotIn(List<String> values) {
            addCriterion("IF_DEFAULT not in", values, "ifDefault");
            return (Criteria) this;
        }

        public Criteria andIfDefaultBetween(String value1, String value2) {
            addCriterion("IF_DEFAULT between", value1, value2, "ifDefault");
            return (Criteria) this;
        }

        public Criteria andIfDefaultNotBetween(String value1, String value2) {
            addCriterion("IF_DEFAULT not between", value1, value2, "ifDefault");
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
