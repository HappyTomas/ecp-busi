package com.zengshi.ecp.goods.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GdsMediaCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public GdsMediaCriteria() {
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

        public Criteria andMediaLibIdIsNull() {
            addCriterion("MEDIA_LIB_ID is null");
            return (Criteria) this;
        }

        public Criteria andMediaLibIdIsNotNull() {
            addCriterion("MEDIA_LIB_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMediaLibIdEqualTo(Long value) {
            addCriterion("MEDIA_LIB_ID =", value, "mediaLibId");
            return (Criteria) this;
        }

        public Criteria andMediaLibIdNotEqualTo(Long value) {
            addCriterion("MEDIA_LIB_ID <>", value, "mediaLibId");
            return (Criteria) this;
        }

        public Criteria andMediaLibIdGreaterThan(Long value) {
            addCriterion("MEDIA_LIB_ID >", value, "mediaLibId");
            return (Criteria) this;
        }

        public Criteria andMediaLibIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MEDIA_LIB_ID >=", value, "mediaLibId");
            return (Criteria) this;
        }

        public Criteria andMediaLibIdLessThan(Long value) {
            addCriterion("MEDIA_LIB_ID <", value, "mediaLibId");
            return (Criteria) this;
        }

        public Criteria andMediaLibIdLessThanOrEqualTo(Long value) {
            addCriterion("MEDIA_LIB_ID <=", value, "mediaLibId");
            return (Criteria) this;
        }

        public Criteria andMediaLibIdIn(List<Long> values) {
            addCriterion("MEDIA_LIB_ID in", values, "mediaLibId");
            return (Criteria) this;
        }

        public Criteria andMediaLibIdNotIn(List<Long> values) {
            addCriterion("MEDIA_LIB_ID not in", values, "mediaLibId");
            return (Criteria) this;
        }

        public Criteria andMediaLibIdBetween(Long value1, Long value2) {
            addCriterion("MEDIA_LIB_ID between", value1, value2, "mediaLibId");
            return (Criteria) this;
        }

        public Criteria andMediaLibIdNotBetween(Long value1, Long value2) {
            addCriterion("MEDIA_LIB_ID not between", value1, value2, "mediaLibId");
            return (Criteria) this;
        }

        public Criteria andMediaTypeIsNull() {
            addCriterion("MEDIA_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andMediaTypeIsNotNull() {
            addCriterion("MEDIA_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMediaTypeEqualTo(String value) {
            addCriterion("MEDIA_TYPE =", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeNotEqualTo(String value) {
            addCriterion("MEDIA_TYPE <>", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeGreaterThan(String value) {
            addCriterion("MEDIA_TYPE >", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeGreaterThanOrEqualTo(String value) {
            addCriterion("MEDIA_TYPE >=", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeLessThan(String value) {
            addCriterion("MEDIA_TYPE <", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeLessThanOrEqualTo(String value) {
            addCriterion("MEDIA_TYPE <=", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeLike(String value) {
            addCriterion("MEDIA_TYPE like", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeNotLike(String value) {
            addCriterion("MEDIA_TYPE not like", value, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeIn(List<String> values) {
            addCriterion("MEDIA_TYPE in", values, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeNotIn(List<String> values) {
            addCriterion("MEDIA_TYPE not in", values, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeBetween(String value1, String value2) {
            addCriterion("MEDIA_TYPE between", value1, value2, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaTypeNotBetween(String value1, String value2) {
            addCriterion("MEDIA_TYPE not between", value1, value2, "mediaType");
            return (Criteria) this;
        }

        public Criteria andMediaNameIsNull() {
            addCriterion("MEDIA_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMediaNameIsNotNull() {
            addCriterion("MEDIA_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMediaNameEqualTo(String value) {
            addCriterion("MEDIA_NAME =", value, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameNotEqualTo(String value) {
            addCriterion("MEDIA_NAME <>", value, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameGreaterThan(String value) {
            addCriterion("MEDIA_NAME >", value, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameGreaterThanOrEqualTo(String value) {
            addCriterion("MEDIA_NAME >=", value, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameLessThan(String value) {
            addCriterion("MEDIA_NAME <", value, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameLessThanOrEqualTo(String value) {
            addCriterion("MEDIA_NAME <=", value, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameLike(String value) {
            addCriterion("MEDIA_NAME like", value, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameNotLike(String value) {
            addCriterion("MEDIA_NAME not like", value, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameIn(List<String> values) {
            addCriterion("MEDIA_NAME in", values, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameNotIn(List<String> values) {
            addCriterion("MEDIA_NAME not in", values, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameBetween(String value1, String value2) {
            addCriterion("MEDIA_NAME between", value1, value2, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaNameNotBetween(String value1, String value2) {
            addCriterion("MEDIA_NAME not between", value1, value2, "mediaName");
            return (Criteria) this;
        }

        public Criteria andMediaUuidIsNull() {
            addCriterion("MEDIA_UUID is null");
            return (Criteria) this;
        }

        public Criteria andMediaUuidIsNotNull() {
            addCriterion("MEDIA_UUID is not null");
            return (Criteria) this;
        }

        public Criteria andMediaUuidEqualTo(String value) {
            addCriterion("MEDIA_UUID =", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidNotEqualTo(String value) {
            addCriterion("MEDIA_UUID <>", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidGreaterThan(String value) {
            addCriterion("MEDIA_UUID >", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidGreaterThanOrEqualTo(String value) {
            addCriterion("MEDIA_UUID >=", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidLessThan(String value) {
            addCriterion("MEDIA_UUID <", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidLessThanOrEqualTo(String value) {
            addCriterion("MEDIA_UUID <=", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidLike(String value) {
            addCriterion("MEDIA_UUID like", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidNotLike(String value) {
            addCriterion("MEDIA_UUID not like", value, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidIn(List<String> values) {
            addCriterion("MEDIA_UUID in", values, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidNotIn(List<String> values) {
            addCriterion("MEDIA_UUID not in", values, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidBetween(String value1, String value2) {
            addCriterion("MEDIA_UUID between", value1, value2, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaUuidNotBetween(String value1, String value2) {
            addCriterion("MEDIA_UUID not between", value1, value2, "mediaUuid");
            return (Criteria) this;
        }

        public Criteria andMediaSizeIsNull() {
            addCriterion("MEDIA_SIZE is null");
            return (Criteria) this;
        }

        public Criteria andMediaSizeIsNotNull() {
            addCriterion("MEDIA_SIZE is not null");
            return (Criteria) this;
        }

        public Criteria andMediaSizeEqualTo(BigDecimal value) {
            addCriterion("MEDIA_SIZE =", value, "mediaSize");
            return (Criteria) this;
        }

        public Criteria andMediaSizeNotEqualTo(BigDecimal value) {
            addCriterion("MEDIA_SIZE <>", value, "mediaSize");
            return (Criteria) this;
        }

        public Criteria andMediaSizeGreaterThan(BigDecimal value) {
            addCriterion("MEDIA_SIZE >", value, "mediaSize");
            return (Criteria) this;
        }

        public Criteria andMediaSizeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MEDIA_SIZE >=", value, "mediaSize");
            return (Criteria) this;
        }

        public Criteria andMediaSizeLessThan(BigDecimal value) {
            addCriterion("MEDIA_SIZE <", value, "mediaSize");
            return (Criteria) this;
        }

        public Criteria andMediaSizeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MEDIA_SIZE <=", value, "mediaSize");
            return (Criteria) this;
        }

        public Criteria andMediaSizeIn(List<BigDecimal> values) {
            addCriterion("MEDIA_SIZE in", values, "mediaSize");
            return (Criteria) this;
        }

        public Criteria andMediaSizeNotIn(List<BigDecimal> values) {
            addCriterion("MEDIA_SIZE not in", values, "mediaSize");
            return (Criteria) this;
        }

        public Criteria andMediaSizeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MEDIA_SIZE between", value1, value2, "mediaSize");
            return (Criteria) this;
        }

        public Criteria andMediaSizeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MEDIA_SIZE not between", value1, value2, "mediaSize");
            return (Criteria) this;
        }

        public Criteria andSortNoIsNull() {
            addCriterion("SORT_NO is null");
            return (Criteria) this;
        }

        public Criteria andSortNoIsNotNull() {
            addCriterion("SORT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andSortNoEqualTo(Integer value) {
            addCriterion("SORT_NO =", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotEqualTo(Integer value) {
            addCriterion("SORT_NO <>", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThan(Integer value) {
            addCriterion("SORT_NO >", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("SORT_NO >=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThan(Integer value) {
            addCriterion("SORT_NO <", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoLessThanOrEqualTo(Integer value) {
            addCriterion("SORT_NO <=", value, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoIn(List<Integer> values) {
            addCriterion("SORT_NO in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotIn(List<Integer> values) {
            addCriterion("SORT_NO not in", values, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoBetween(Integer value1, Integer value2) {
            addCriterion("SORT_NO between", value1, value2, "sortNo");
            return (Criteria) this;
        }

        public Criteria andSortNoNotBetween(Integer value1, Integer value2) {
            addCriterion("SORT_NO not between", value1, value2, "sortNo");
            return (Criteria) this;
        }

        public Criteria andPicCatgCodeIsNull() {
            addCriterion("PIC_CATG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPicCatgCodeIsNotNull() {
            addCriterion("PIC_CATG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPicCatgCodeEqualTo(String value) {
            addCriterion("PIC_CATG_CODE =", value, "picCatgCode");
            return (Criteria) this;
        }

        public Criteria andPicCatgCodeNotEqualTo(String value) {
            addCriterion("PIC_CATG_CODE <>", value, "picCatgCode");
            return (Criteria) this;
        }

        public Criteria andPicCatgCodeGreaterThan(String value) {
            addCriterion("PIC_CATG_CODE >", value, "picCatgCode");
            return (Criteria) this;
        }

        public Criteria andPicCatgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PIC_CATG_CODE >=", value, "picCatgCode");
            return (Criteria) this;
        }

        public Criteria andPicCatgCodeLessThan(String value) {
            addCriterion("PIC_CATG_CODE <", value, "picCatgCode");
            return (Criteria) this;
        }

        public Criteria andPicCatgCodeLessThanOrEqualTo(String value) {
            addCriterion("PIC_CATG_CODE <=", value, "picCatgCode");
            return (Criteria) this;
        }

        public Criteria andPicCatgCodeLike(String value) {
            addCriterion("PIC_CATG_CODE like", value, "picCatgCode");
            return (Criteria) this;
        }

        public Criteria andPicCatgCodeNotLike(String value) {
            addCriterion("PIC_CATG_CODE not like", value, "picCatgCode");
            return (Criteria) this;
        }

        public Criteria andPicCatgCodeIn(List<String> values) {
            addCriterion("PIC_CATG_CODE in", values, "picCatgCode");
            return (Criteria) this;
        }

        public Criteria andPicCatgCodeNotIn(List<String> values) {
            addCriterion("PIC_CATG_CODE not in", values, "picCatgCode");
            return (Criteria) this;
        }

        public Criteria andPicCatgCodeBetween(String value1, String value2) {
            addCriterion("PIC_CATG_CODE between", value1, value2, "picCatgCode");
            return (Criteria) this;
        }

        public Criteria andPicCatgCodeNotBetween(String value1, String value2) {
            addCriterion("PIC_CATG_CODE not between", value1, value2, "picCatgCode");
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
