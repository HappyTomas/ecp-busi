package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GdsExcelImpLogCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsExcelImpLogCriteria() {
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

        public Criteria andImportIdIsNull() {
            addCriterion("IMPORT_ID is null");
            return (Criteria) this;
        }

        public Criteria andImportIdIsNotNull() {
            addCriterion("IMPORT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andImportIdEqualTo(Long value) {
            addCriterion("IMPORT_ID =", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdNotEqualTo(Long value) {
            addCriterion("IMPORT_ID <>", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdGreaterThan(Long value) {
            addCriterion("IMPORT_ID >", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdGreaterThanOrEqualTo(Long value) {
            addCriterion("IMPORT_ID >=", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdLessThan(Long value) {
            addCriterion("IMPORT_ID <", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdLessThanOrEqualTo(Long value) {
            addCriterion("IMPORT_ID <=", value, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdIn(List<Long> values) {
            addCriterion("IMPORT_ID in", values, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdNotIn(List<Long> values) {
            addCriterion("IMPORT_ID not in", values, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdBetween(Long value1, Long value2) {
            addCriterion("IMPORT_ID between", value1, value2, "importId");
            return (Criteria) this;
        }

        public Criteria andImportIdNotBetween(Long value1, Long value2) {
            addCriterion("IMPORT_ID not between", value1, value2, "importId");
            return (Criteria) this;
        }

        public Criteria andImportFileIsNull() {
            addCriterion("IMPORT_FILE is null");
            return (Criteria) this;
        }

        public Criteria andImportFileIsNotNull() {
            addCriterion("IMPORT_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andImportFileEqualTo(String value) {
            addCriterion("IMPORT_FILE =", value, "importFile");
            return (Criteria) this;
        }

        public Criteria andImportFileNotEqualTo(String value) {
            addCriterion("IMPORT_FILE <>", value, "importFile");
            return (Criteria) this;
        }

        public Criteria andImportFileGreaterThan(String value) {
            addCriterion("IMPORT_FILE >", value, "importFile");
            return (Criteria) this;
        }

        public Criteria andImportFileGreaterThanOrEqualTo(String value) {
            addCriterion("IMPORT_FILE >=", value, "importFile");
            return (Criteria) this;
        }

        public Criteria andImportFileLessThan(String value) {
            addCriterion("IMPORT_FILE <", value, "importFile");
            return (Criteria) this;
        }

        public Criteria andImportFileLessThanOrEqualTo(String value) {
            addCriterion("IMPORT_FILE <=", value, "importFile");
            return (Criteria) this;
        }

        public Criteria andImportFileLike(String value) {
            addCriterion("IMPORT_FILE like", value, "importFile");
            return (Criteria) this;
        }

        public Criteria andImportFileNotLike(String value) {
            addCriterion("IMPORT_FILE not like", value, "importFile");
            return (Criteria) this;
        }

        public Criteria andImportFileIn(List<String> values) {
            addCriterion("IMPORT_FILE in", values, "importFile");
            return (Criteria) this;
        }

        public Criteria andImportFileNotIn(List<String> values) {
            addCriterion("IMPORT_FILE not in", values, "importFile");
            return (Criteria) this;
        }

        public Criteria andImportFileBetween(String value1, String value2) {
            addCriterion("IMPORT_FILE between", value1, value2, "importFile");
            return (Criteria) this;
        }

        public Criteria andImportFileNotBetween(String value1, String value2) {
            addCriterion("IMPORT_FILE not between", value1, value2, "importFile");
            return (Criteria) this;
        }

        public Criteria andImportUuidIsNull() {
            addCriterion("IMPORT_UUID is null");
            return (Criteria) this;
        }

        public Criteria andImportUuidIsNotNull() {
            addCriterion("IMPORT_UUID is not null");
            return (Criteria) this;
        }

        public Criteria andImportUuidEqualTo(String value) {
            addCriterion("IMPORT_UUID =", value, "importUuid");
            return (Criteria) this;
        }

        public Criteria andImportUuidNotEqualTo(String value) {
            addCriterion("IMPORT_UUID <>", value, "importUuid");
            return (Criteria) this;
        }

        public Criteria andImportUuidGreaterThan(String value) {
            addCriterion("IMPORT_UUID >", value, "importUuid");
            return (Criteria) this;
        }

        public Criteria andImportUuidGreaterThanOrEqualTo(String value) {
            addCriterion("IMPORT_UUID >=", value, "importUuid");
            return (Criteria) this;
        }

        public Criteria andImportUuidLessThan(String value) {
            addCriterion("IMPORT_UUID <", value, "importUuid");
            return (Criteria) this;
        }

        public Criteria andImportUuidLessThanOrEqualTo(String value) {
            addCriterion("IMPORT_UUID <=", value, "importUuid");
            return (Criteria) this;
        }

        public Criteria andImportUuidLike(String value) {
            addCriterion("IMPORT_UUID like", value, "importUuid");
            return (Criteria) this;
        }

        public Criteria andImportUuidNotLike(String value) {
            addCriterion("IMPORT_UUID not like", value, "importUuid");
            return (Criteria) this;
        }

        public Criteria andImportUuidIn(List<String> values) {
            addCriterion("IMPORT_UUID in", values, "importUuid");
            return (Criteria) this;
        }

        public Criteria andImportUuidNotIn(List<String> values) {
            addCriterion("IMPORT_UUID not in", values, "importUuid");
            return (Criteria) this;
        }

        public Criteria andImportUuidBetween(String value1, String value2) {
            addCriterion("IMPORT_UUID between", value1, value2, "importUuid");
            return (Criteria) this;
        }

        public Criteria andImportUuidNotBetween(String value1, String value2) {
            addCriterion("IMPORT_UUID not between", value1, value2, "importUuid");
            return (Criteria) this;
        }

        public Criteria andImportStatusIsNull() {
            addCriterion("IMPORT_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andImportStatusIsNotNull() {
            addCriterion("IMPORT_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andImportStatusEqualTo(String value) {
            addCriterion("IMPORT_STATUS =", value, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusNotEqualTo(String value) {
            addCriterion("IMPORT_STATUS <>", value, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusGreaterThan(String value) {
            addCriterion("IMPORT_STATUS >", value, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusGreaterThanOrEqualTo(String value) {
            addCriterion("IMPORT_STATUS >=", value, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusLessThan(String value) {
            addCriterion("IMPORT_STATUS <", value, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusLessThanOrEqualTo(String value) {
            addCriterion("IMPORT_STATUS <=", value, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusLike(String value) {
            addCriterion("IMPORT_STATUS like", value, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusNotLike(String value) {
            addCriterion("IMPORT_STATUS not like", value, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusIn(List<String> values) {
            addCriterion("IMPORT_STATUS in", values, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusNotIn(List<String> values) {
            addCriterion("IMPORT_STATUS not in", values, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusBetween(String value1, String value2) {
            addCriterion("IMPORT_STATUS between", value1, value2, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportStatusNotBetween(String value1, String value2) {
            addCriterion("IMPORT_STATUS not between", value1, value2, "importStatus");
            return (Criteria) this;
        }

        public Criteria andImportSrcIsNull() {
            addCriterion("IMPORT_SRC is null");
            return (Criteria) this;
        }

        public Criteria andImportSrcIsNotNull() {
            addCriterion("IMPORT_SRC is not null");
            return (Criteria) this;
        }

        public Criteria andImportSrcEqualTo(String value) {
            addCriterion("IMPORT_SRC =", value, "importSrc");
            return (Criteria) this;
        }

        public Criteria andImportSrcNotEqualTo(String value) {
            addCriterion("IMPORT_SRC <>", value, "importSrc");
            return (Criteria) this;
        }

        public Criteria andImportSrcGreaterThan(String value) {
            addCriterion("IMPORT_SRC >", value, "importSrc");
            return (Criteria) this;
        }

        public Criteria andImportSrcGreaterThanOrEqualTo(String value) {
            addCriterion("IMPORT_SRC >=", value, "importSrc");
            return (Criteria) this;
        }

        public Criteria andImportSrcLessThan(String value) {
            addCriterion("IMPORT_SRC <", value, "importSrc");
            return (Criteria) this;
        }

        public Criteria andImportSrcLessThanOrEqualTo(String value) {
            addCriterion("IMPORT_SRC <=", value, "importSrc");
            return (Criteria) this;
        }

        public Criteria andImportSrcLike(String value) {
            addCriterion("IMPORT_SRC like", value, "importSrc");
            return (Criteria) this;
        }

        public Criteria andImportSrcNotLike(String value) {
            addCriterion("IMPORT_SRC not like", value, "importSrc");
            return (Criteria) this;
        }

        public Criteria andImportSrcIn(List<String> values) {
            addCriterion("IMPORT_SRC in", values, "importSrc");
            return (Criteria) this;
        }

        public Criteria andImportSrcNotIn(List<String> values) {
            addCriterion("IMPORT_SRC not in", values, "importSrc");
            return (Criteria) this;
        }

        public Criteria andImportSrcBetween(String value1, String value2) {
            addCriterion("IMPORT_SRC between", value1, value2, "importSrc");
            return (Criteria) this;
        }

        public Criteria andImportSrcNotBetween(String value1, String value2) {
            addCriterion("IMPORT_SRC not between", value1, value2, "importSrc");
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
