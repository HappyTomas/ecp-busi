package com.zengshi.ecp.sys.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseParamConfigCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;
    
    protected boolean distinct;
    
    protected String suffix = "";

    protected List<Criteria> oredCriteria;
    
    private static final long serialVersionUID = 1L;

    public BaseParamConfigCriteria() {
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

        public Criteria andParamKeyIsNull() {
            addCriterion("PARAM_KEY is null");
            return (Criteria) this;
        }

        public Criteria andParamKeyIsNotNull() {
            addCriterion("PARAM_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andParamKeyEqualTo(String value) {
            addCriterion("PARAM_KEY =", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyNotEqualTo(String value) {
            addCriterion("PARAM_KEY <>", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyGreaterThan(String value) {
            addCriterion("PARAM_KEY >", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_KEY >=", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyLessThan(String value) {
            addCriterion("PARAM_KEY <", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyLessThanOrEqualTo(String value) {
            addCriterion("PARAM_KEY <=", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyLike(String value) {
            addCriterion("PARAM_KEY like", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyNotLike(String value) {
            addCriterion("PARAM_KEY not like", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyIn(List<String> values) {
            addCriterion("PARAM_KEY in", values, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyNotIn(List<String> values) {
            addCriterion("PARAM_KEY not in", values, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyBetween(String value1, String value2) {
            addCriterion("PARAM_KEY between", value1, value2, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyNotBetween(String value1, String value2) {
            addCriterion("PARAM_KEY not between", value1, value2, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamNameIsNull() {
            addCriterion("PARAM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andParamNameIsNotNull() {
            addCriterion("PARAM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andParamNameEqualTo(String value) {
            addCriterion("PARAM_NAME =", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotEqualTo(String value) {
            addCriterion("PARAM_NAME <>", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameGreaterThan(String value) {
            addCriterion("PARAM_NAME >", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_NAME >=", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLessThan(String value) {
            addCriterion("PARAM_NAME <", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLessThanOrEqualTo(String value) {
            addCriterion("PARAM_NAME <=", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLike(String value) {
            addCriterion("PARAM_NAME like", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotLike(String value) {
            addCriterion("PARAM_NAME not like", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameIn(List<String> values) {
            addCriterion("PARAM_NAME in", values, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotIn(List<String> values) {
            addCriterion("PARAM_NAME not in", values, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameBetween(String value1, String value2) {
            addCriterion("PARAM_NAME between", value1, value2, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotBetween(String value1, String value2) {
            addCriterion("PARAM_NAME not between", value1, value2, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamLinkTableIsNull() {
            addCriterion("PARAM_LINK_TABLE is null");
            return (Criteria) this;
        }

        public Criteria andParamLinkTableIsNotNull() {
            addCriterion("PARAM_LINK_TABLE is not null");
            return (Criteria) this;
        }

        public Criteria andParamLinkTableEqualTo(String value) {
            addCriterion("PARAM_LINK_TABLE =", value, "paramLinkTable");
            return (Criteria) this;
        }

        public Criteria andParamLinkTableNotEqualTo(String value) {
            addCriterion("PARAM_LINK_TABLE <>", value, "paramLinkTable");
            return (Criteria) this;
        }

        public Criteria andParamLinkTableGreaterThan(String value) {
            addCriterion("PARAM_LINK_TABLE >", value, "paramLinkTable");
            return (Criteria) this;
        }

        public Criteria andParamLinkTableGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_LINK_TABLE >=", value, "paramLinkTable");
            return (Criteria) this;
        }

        public Criteria andParamLinkTableLessThan(String value) {
            addCriterion("PARAM_LINK_TABLE <", value, "paramLinkTable");
            return (Criteria) this;
        }

        public Criteria andParamLinkTableLessThanOrEqualTo(String value) {
            addCriterion("PARAM_LINK_TABLE <=", value, "paramLinkTable");
            return (Criteria) this;
        }

        public Criteria andParamLinkTableLike(String value) {
            addCriterion("PARAM_LINK_TABLE like", value, "paramLinkTable");
            return (Criteria) this;
        }

        public Criteria andParamLinkTableNotLike(String value) {
            addCriterion("PARAM_LINK_TABLE not like", value, "paramLinkTable");
            return (Criteria) this;
        }

        public Criteria andParamLinkTableIn(List<String> values) {
            addCriterion("PARAM_LINK_TABLE in", values, "paramLinkTable");
            return (Criteria) this;
        }

        public Criteria andParamLinkTableNotIn(List<String> values) {
            addCriterion("PARAM_LINK_TABLE not in", values, "paramLinkTable");
            return (Criteria) this;
        }

        public Criteria andParamLinkTableBetween(String value1, String value2) {
            addCriterion("PARAM_LINK_TABLE between", value1, value2, "paramLinkTable");
            return (Criteria) this;
        }

        public Criteria andParamLinkTableNotBetween(String value1, String value2) {
            addCriterion("PARAM_LINK_TABLE not between", value1, value2, "paramLinkTable");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyIsNull() {
            addCriterion("PARAM_LINK_KEY is null");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyIsNotNull() {
            addCriterion("PARAM_LINK_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyEqualTo(String value) {
            addCriterion("PARAM_LINK_KEY =", value, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyNotEqualTo(String value) {
            addCriterion("PARAM_LINK_KEY <>", value, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyGreaterThan(String value) {
            addCriterion("PARAM_LINK_KEY >", value, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_LINK_KEY >=", value, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyLessThan(String value) {
            addCriterion("PARAM_LINK_KEY <", value, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyLessThanOrEqualTo(String value) {
            addCriterion("PARAM_LINK_KEY <=", value, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyLike(String value) {
            addCriterion("PARAM_LINK_KEY like", value, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyNotLike(String value) {
            addCriterion("PARAM_LINK_KEY not like", value, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyIn(List<String> values) {
            addCriterion("PARAM_LINK_KEY in", values, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyNotIn(List<String> values) {
            addCriterion("PARAM_LINK_KEY not in", values, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyBetween(String value1, String value2) {
            addCriterion("PARAM_LINK_KEY between", value1, value2, "paramLinkKey");
            return (Criteria) this;
        }

        public Criteria andParamLinkKeyNotBetween(String value1, String value2) {
            addCriterion("PARAM_LINK_KEY not between", value1, value2, "paramLinkKey");
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

        public Criteria andParamDescIsNull() {
            addCriterion("PARAM_DESC is null");
            return (Criteria) this;
        }

        public Criteria andParamDescIsNotNull() {
            addCriterion("PARAM_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andParamDescEqualTo(String value) {
            addCriterion("PARAM_DESC =", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescNotEqualTo(String value) {
            addCriterion("PARAM_DESC <>", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescGreaterThan(String value) {
            addCriterion("PARAM_DESC >", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_DESC >=", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescLessThan(String value) {
            addCriterion("PARAM_DESC <", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescLessThanOrEqualTo(String value) {
            addCriterion("PARAM_DESC <=", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescLike(String value) {
            addCriterion("PARAM_DESC like", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescNotLike(String value) {
            addCriterion("PARAM_DESC not like", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescIn(List<String> values) {
            addCriterion("PARAM_DESC in", values, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescNotIn(List<String> values) {
            addCriterion("PARAM_DESC not in", values, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescBetween(String value1, String value2) {
            addCriterion("PARAM_DESC between", value1, value2, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescNotBetween(String value1, String value2) {
            addCriterion("PARAM_DESC not between", value1, value2, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamTypeIsNull() {
            addCriterion("PARAM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andParamTypeIsNotNull() {
            addCriterion("PARAM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andParamTypeEqualTo(String value) {
            addCriterion("PARAM_TYPE =", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotEqualTo(String value) {
            addCriterion("PARAM_TYPE <>", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeGreaterThan(String value) {
            addCriterion("PARAM_TYPE >", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_TYPE >=", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeLessThan(String value) {
            addCriterion("PARAM_TYPE <", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeLessThanOrEqualTo(String value) {
            addCriterion("PARAM_TYPE <=", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeLike(String value) {
            addCriterion("PARAM_TYPE like", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotLike(String value) {
            addCriterion("PARAM_TYPE not like", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeIn(List<String> values) {
            addCriterion("PARAM_TYPE in", values, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotIn(List<String> values) {
            addCriterion("PARAM_TYPE not in", values, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeBetween(String value1, String value2) {
            addCriterion("PARAM_TYPE between", value1, value2, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotBetween(String value1, String value2) {
            addCriterion("PARAM_TYPE not between", value1, value2, "paramType");
            return (Criteria) this;
        }

        public Criteria andUsedTableIsNull() {
            addCriterion("USED_TABLE is null");
            return (Criteria) this;
        }

        public Criteria andUsedTableIsNotNull() {
            addCriterion("USED_TABLE is not null");
            return (Criteria) this;
        }

        public Criteria andUsedTableEqualTo(String value) {
            addCriterion("USED_TABLE =", value, "usedTable");
            return (Criteria) this;
        }

        public Criteria andUsedTableNotEqualTo(String value) {
            addCriterion("USED_TABLE <>", value, "usedTable");
            return (Criteria) this;
        }

        public Criteria andUsedTableGreaterThan(String value) {
            addCriterion("USED_TABLE >", value, "usedTable");
            return (Criteria) this;
        }

        public Criteria andUsedTableGreaterThanOrEqualTo(String value) {
            addCriterion("USED_TABLE >=", value, "usedTable");
            return (Criteria) this;
        }

        public Criteria andUsedTableLessThan(String value) {
            addCriterion("USED_TABLE <", value, "usedTable");
            return (Criteria) this;
        }

        public Criteria andUsedTableLessThanOrEqualTo(String value) {
            addCriterion("USED_TABLE <=", value, "usedTable");
            return (Criteria) this;
        }

        public Criteria andUsedTableLike(String value) {
            addCriterion("USED_TABLE like", value, "usedTable");
            return (Criteria) this;
        }

        public Criteria andUsedTableNotLike(String value) {
            addCriterion("USED_TABLE not like", value, "usedTable");
            return (Criteria) this;
        }

        public Criteria andUsedTableIn(List<String> values) {
            addCriterion("USED_TABLE in", values, "usedTable");
            return (Criteria) this;
        }

        public Criteria andUsedTableNotIn(List<String> values) {
            addCriterion("USED_TABLE not in", values, "usedTable");
            return (Criteria) this;
        }

        public Criteria andUsedTableBetween(String value1, String value2) {
            addCriterion("USED_TABLE between", value1, value2, "usedTable");
            return (Criteria) this;
        }

        public Criteria andUsedTableNotBetween(String value1, String value2) {
            addCriterion("USED_TABLE not between", value1, value2, "usedTable");
            return (Criteria) this;
        }

        public Criteria andUsedColumnIsNull() {
            addCriterion("USED_COLUMN is null");
            return (Criteria) this;
        }

        public Criteria andUsedColumnIsNotNull() {
            addCriterion("USED_COLUMN is not null");
            return (Criteria) this;
        }

        public Criteria andUsedColumnEqualTo(String value) {
            addCriterion("USED_COLUMN =", value, "usedColumn");
            return (Criteria) this;
        }

        public Criteria andUsedColumnNotEqualTo(String value) {
            addCriterion("USED_COLUMN <>", value, "usedColumn");
            return (Criteria) this;
        }

        public Criteria andUsedColumnGreaterThan(String value) {
            addCriterion("USED_COLUMN >", value, "usedColumn");
            return (Criteria) this;
        }

        public Criteria andUsedColumnGreaterThanOrEqualTo(String value) {
            addCriterion("USED_COLUMN >=", value, "usedColumn");
            return (Criteria) this;
        }

        public Criteria andUsedColumnLessThan(String value) {
            addCriterion("USED_COLUMN <", value, "usedColumn");
            return (Criteria) this;
        }

        public Criteria andUsedColumnLessThanOrEqualTo(String value) {
            addCriterion("USED_COLUMN <=", value, "usedColumn");
            return (Criteria) this;
        }

        public Criteria andUsedColumnLike(String value) {
            addCriterion("USED_COLUMN like", value, "usedColumn");
            return (Criteria) this;
        }

        public Criteria andUsedColumnNotLike(String value) {
            addCriterion("USED_COLUMN not like", value, "usedColumn");
            return (Criteria) this;
        }

        public Criteria andUsedColumnIn(List<String> values) {
            addCriterion("USED_COLUMN in", values, "usedColumn");
            return (Criteria) this;
        }

        public Criteria andUsedColumnNotIn(List<String> values) {
            addCriterion("USED_COLUMN not in", values, "usedColumn");
            return (Criteria) this;
        }

        public Criteria andUsedColumnBetween(String value1, String value2) {
            addCriterion("USED_COLUMN between", value1, value2, "usedColumn");
            return (Criteria) this;
        }

        public Criteria andUsedColumnNotBetween(String value1, String value2) {
            addCriterion("USED_COLUMN not between", value1, value2, "usedColumn");
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
