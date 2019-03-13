package com.zengshi.ecp.order.dao.model;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrdDeliveryBatchCriteria extends BaseCriteria implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected String suffix = "";

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OrdDeliveryBatchCriteria() {
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

        public Criteria andProxyShopIdIsNull() {
            addCriterion("PROXY_SHOP_ID is null");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdIsNotNull() {
            addCriterion("PROXY_SHOP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdEqualTo(Long value) {
            addCriterion("PROXY_SHOP_ID =", value, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdNotEqualTo(Long value) {
            addCriterion("PROXY_SHOP_ID <>", value, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdGreaterThan(Long value) {
            addCriterion("PROXY_SHOP_ID >", value, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROXY_SHOP_ID >=", value, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdLessThan(Long value) {
            addCriterion("PROXY_SHOP_ID <", value, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdLessThanOrEqualTo(Long value) {
            addCriterion("PROXY_SHOP_ID <=", value, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdIn(List<Long> values) {
            addCriterion("PROXY_SHOP_ID in", values, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdNotIn(List<Long> values) {
            addCriterion("PROXY_SHOP_ID not in", values, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdBetween(Long value1, Long value2) {
            addCriterion("PROXY_SHOP_ID between", value1, value2, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andProxyShopIdNotBetween(Long value1, Long value2) {
            addCriterion("PROXY_SHOP_ID not between", value1, value2, "proxyShopId");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeIsNull() {
            addCriterion("DELIVERY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeIsNotNull() {
            addCriterion("DELIVERY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeEqualTo(String value) {
            addCriterion("DELIVERY_TYPE =", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeNotEqualTo(String value) {
            addCriterion("DELIVERY_TYPE <>", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeGreaterThan(String value) {
            addCriterion("DELIVERY_TYPE >", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DELIVERY_TYPE >=", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeLessThan(String value) {
            addCriterion("DELIVERY_TYPE <", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeLessThanOrEqualTo(String value) {
            addCriterion("DELIVERY_TYPE <=", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeLike(String value) {
            addCriterion("DELIVERY_TYPE like", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeNotLike(String value) {
            addCriterion("DELIVERY_TYPE not like", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeIn(List<String> values) {
            addCriterion("DELIVERY_TYPE in", values, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeNotIn(List<String> values) {
            addCriterion("DELIVERY_TYPE not in", values, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeBetween(String value1, String value2) {
            addCriterion("DELIVERY_TYPE between", value1, value2, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeNotBetween(String value1, String value2) {
            addCriterion("DELIVERY_TYPE not between", value1, value2, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andSendDateIsNull() {
            addCriterion("SEND_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSendDateIsNotNull() {
            addCriterion("SEND_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSendDateEqualTo(Timestamp value) {
            addCriterion("SEND_DATE =", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotEqualTo(Timestamp value) {
            addCriterion("SEND_DATE <>", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateGreaterThan(Timestamp value) {
            addCriterion("SEND_DATE >", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("SEND_DATE >=", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateLessThan(Timestamp value) {
            addCriterion("SEND_DATE <", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("SEND_DATE <=", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateIn(List<Timestamp> values) {
            addCriterion("SEND_DATE in", values, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotIn(List<Timestamp> values) {
            addCriterion("SEND_DATE not in", values, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SEND_DATE between", value1, value2, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SEND_DATE not between", value1, value2, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendNameIsNull() {
            addCriterion("SEND_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSendNameIsNotNull() {
            addCriterion("SEND_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSendNameEqualTo(String value) {
            addCriterion("SEND_NAME =", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameNotEqualTo(String value) {
            addCriterion("SEND_NAME <>", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameGreaterThan(String value) {
            addCriterion("SEND_NAME >", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_NAME >=", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameLessThan(String value) {
            addCriterion("SEND_NAME <", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameLessThanOrEqualTo(String value) {
            addCriterion("SEND_NAME <=", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameLike(String value) {
            addCriterion("SEND_NAME like", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameNotLike(String value) {
            addCriterion("SEND_NAME not like", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameIn(List<String> values) {
            addCriterion("SEND_NAME in", values, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameNotIn(List<String> values) {
            addCriterion("SEND_NAME not in", values, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameBetween(String value1, String value2) {
            addCriterion("SEND_NAME between", value1, value2, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameNotBetween(String value1, String value2) {
            addCriterion("SEND_NAME not between", value1, value2, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendTelIsNull() {
            addCriterion("SEND_TEL is null");
            return (Criteria) this;
        }

        public Criteria andSendTelIsNotNull() {
            addCriterion("SEND_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andSendTelEqualTo(String value) {
            addCriterion("SEND_TEL =", value, "sendTel");
            return (Criteria) this;
        }

        public Criteria andSendTelNotEqualTo(String value) {
            addCriterion("SEND_TEL <>", value, "sendTel");
            return (Criteria) this;
        }

        public Criteria andSendTelGreaterThan(String value) {
            addCriterion("SEND_TEL >", value, "sendTel");
            return (Criteria) this;
        }

        public Criteria andSendTelGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_TEL >=", value, "sendTel");
            return (Criteria) this;
        }

        public Criteria andSendTelLessThan(String value) {
            addCriterion("SEND_TEL <", value, "sendTel");
            return (Criteria) this;
        }

        public Criteria andSendTelLessThanOrEqualTo(String value) {
            addCriterion("SEND_TEL <=", value, "sendTel");
            return (Criteria) this;
        }

        public Criteria andSendTelLike(String value) {
            addCriterion("SEND_TEL like", value, "sendTel");
            return (Criteria) this;
        }

        public Criteria andSendTelNotLike(String value) {
            addCriterion("SEND_TEL not like", value, "sendTel");
            return (Criteria) this;
        }

        public Criteria andSendTelIn(List<String> values) {
            addCriterion("SEND_TEL in", values, "sendTel");
            return (Criteria) this;
        }

        public Criteria andSendTelNotIn(List<String> values) {
            addCriterion("SEND_TEL not in", values, "sendTel");
            return (Criteria) this;
        }

        public Criteria andSendTelBetween(String value1, String value2) {
            addCriterion("SEND_TEL between", value1, value2, "sendTel");
            return (Criteria) this;
        }

        public Criteria andSendTelNotBetween(String value1, String value2) {
            addCriterion("SEND_TEL not between", value1, value2, "sendTel");
            return (Criteria) this;
        }

        public Criteria andSendPhoneIsNull() {
            addCriterion("SEND_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andSendPhoneIsNotNull() {
            addCriterion("SEND_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andSendPhoneEqualTo(String value) {
            addCriterion("SEND_PHONE =", value, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneNotEqualTo(String value) {
            addCriterion("SEND_PHONE <>", value, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneGreaterThan(String value) {
            addCriterion("SEND_PHONE >", value, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_PHONE >=", value, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneLessThan(String value) {
            addCriterion("SEND_PHONE <", value, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneLessThanOrEqualTo(String value) {
            addCriterion("SEND_PHONE <=", value, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneLike(String value) {
            addCriterion("SEND_PHONE like", value, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneNotLike(String value) {
            addCriterion("SEND_PHONE not like", value, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneIn(List<String> values) {
            addCriterion("SEND_PHONE in", values, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneNotIn(List<String> values) {
            addCriterion("SEND_PHONE not in", values, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneBetween(String value1, String value2) {
            addCriterion("SEND_PHONE between", value1, value2, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendPhoneNotBetween(String value1, String value2) {
            addCriterion("SEND_PHONE not between", value1, value2, "sendPhone");
            return (Criteria) this;
        }

        public Criteria andSendAddressIsNull() {
            addCriterion("SEND_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andSendAddressIsNotNull() {
            addCriterion("SEND_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andSendAddressEqualTo(String value) {
            addCriterion("SEND_ADDRESS =", value, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressNotEqualTo(String value) {
            addCriterion("SEND_ADDRESS <>", value, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressGreaterThan(String value) {
            addCriterion("SEND_ADDRESS >", value, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_ADDRESS >=", value, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressLessThan(String value) {
            addCriterion("SEND_ADDRESS <", value, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressLessThanOrEqualTo(String value) {
            addCriterion("SEND_ADDRESS <=", value, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressLike(String value) {
            addCriterion("SEND_ADDRESS like", value, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressNotLike(String value) {
            addCriterion("SEND_ADDRESS not like", value, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressIn(List<String> values) {
            addCriterion("SEND_ADDRESS in", values, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressNotIn(List<String> values) {
            addCriterion("SEND_ADDRESS not in", values, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressBetween(String value1, String value2) {
            addCriterion("SEND_ADDRESS between", value1, value2, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendAddressNotBetween(String value1, String value2) {
            addCriterion("SEND_ADDRESS not between", value1, value2, "sendAddress");
            return (Criteria) this;
        }

        public Criteria andSendZipIsNull() {
            addCriterion("SEND_ZIP is null");
            return (Criteria) this;
        }

        public Criteria andSendZipIsNotNull() {
            addCriterion("SEND_ZIP is not null");
            return (Criteria) this;
        }

        public Criteria andSendZipEqualTo(String value) {
            addCriterion("SEND_ZIP =", value, "sendZip");
            return (Criteria) this;
        }

        public Criteria andSendZipNotEqualTo(String value) {
            addCriterion("SEND_ZIP <>", value, "sendZip");
            return (Criteria) this;
        }

        public Criteria andSendZipGreaterThan(String value) {
            addCriterion("SEND_ZIP >", value, "sendZip");
            return (Criteria) this;
        }

        public Criteria andSendZipGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_ZIP >=", value, "sendZip");
            return (Criteria) this;
        }

        public Criteria andSendZipLessThan(String value) {
            addCriterion("SEND_ZIP <", value, "sendZip");
            return (Criteria) this;
        }

        public Criteria andSendZipLessThanOrEqualTo(String value) {
            addCriterion("SEND_ZIP <=", value, "sendZip");
            return (Criteria) this;
        }

        public Criteria andSendZipLike(String value) {
            addCriterion("SEND_ZIP like", value, "sendZip");
            return (Criteria) this;
        }

        public Criteria andSendZipNotLike(String value) {
            addCriterion("SEND_ZIP not like", value, "sendZip");
            return (Criteria) this;
        }

        public Criteria andSendZipIn(List<String> values) {
            addCriterion("SEND_ZIP in", values, "sendZip");
            return (Criteria) this;
        }

        public Criteria andSendZipNotIn(List<String> values) {
            addCriterion("SEND_ZIP not in", values, "sendZip");
            return (Criteria) this;
        }

        public Criteria andSendZipBetween(String value1, String value2) {
            addCriterion("SEND_ZIP between", value1, value2, "sendZip");
            return (Criteria) this;
        }

        public Criteria andSendZipNotBetween(String value1, String value2) {
            addCriterion("SEND_ZIP not between", value1, value2, "sendZip");
            return (Criteria) this;
        }

        public Criteria andExpressIdIsNull() {
            addCriterion("EXPRESS_ID is null");
            return (Criteria) this;
        }

        public Criteria andExpressIdIsNotNull() {
            addCriterion("EXPRESS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExpressIdEqualTo(Long value) {
            addCriterion("EXPRESS_ID =", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotEqualTo(Long value) {
            addCriterion("EXPRESS_ID <>", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThan(Long value) {
            addCriterion("EXPRESS_ID >", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThanOrEqualTo(Long value) {
            addCriterion("EXPRESS_ID >=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThan(Long value) {
            addCriterion("EXPRESS_ID <", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThanOrEqualTo(Long value) {
            addCriterion("EXPRESS_ID <=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdIn(List<Long> values) {
            addCriterion("EXPRESS_ID in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotIn(List<Long> values) {
            addCriterion("EXPRESS_ID not in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdBetween(Long value1, Long value2) {
            addCriterion("EXPRESS_ID between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotBetween(Long value1, Long value2) {
            addCriterion("EXPRESS_ID not between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNull() {
            addCriterion("EXPRESS_NO is null");
            return (Criteria) this;
        }

        public Criteria andExpressNoIsNotNull() {
            addCriterion("EXPRESS_NO is not null");
            return (Criteria) this;
        }

        public Criteria andExpressNoEqualTo(String value) {
            addCriterion("EXPRESS_NO =", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotEqualTo(String value) {
            addCriterion("EXPRESS_NO <>", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThan(String value) {
            addCriterion("EXPRESS_NO >", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoGreaterThanOrEqualTo(String value) {
            addCriterion("EXPRESS_NO >=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThan(String value) {
            addCriterion("EXPRESS_NO <", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLessThanOrEqualTo(String value) {
            addCriterion("EXPRESS_NO <=", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoLike(String value) {
            addCriterion("EXPRESS_NO like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotLike(String value) {
            addCriterion("EXPRESS_NO not like", value, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoIn(List<String> values) {
            addCriterion("EXPRESS_NO in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotIn(List<String> values) {
            addCriterion("EXPRESS_NO not in", values, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoBetween(String value1, String value2) {
            addCriterion("EXPRESS_NO between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andExpressNoNotBetween(String value1, String value2) {
            addCriterion("EXPRESS_NO not between", value1, value2, "expressNo");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNull() {
            addCriterion("CONTACT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNotNull() {
            addCriterion("CONTACT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andContactNameEqualTo(String value) {
            addCriterion("CONTACT_NAME =", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotEqualTo(String value) {
            addCriterion("CONTACT_NAME <>", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThan(String value) {
            addCriterion("CONTACT_NAME >", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_NAME >=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThan(String value) {
            addCriterion("CONTACT_NAME <", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_NAME <=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLike(String value) {
            addCriterion("CONTACT_NAME like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotLike(String value) {
            addCriterion("CONTACT_NAME not like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameIn(List<String> values) {
            addCriterion("CONTACT_NAME in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotIn(List<String> values) {
            addCriterion("CONTACT_NAME not in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameBetween(String value1, String value2) {
            addCriterion("CONTACT_NAME between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotBetween(String value1, String value2) {
            addCriterion("CONTACT_NAME not between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactTelIsNull() {
            addCriterion("CONTACT_TEL is null");
            return (Criteria) this;
        }

        public Criteria andContactTelIsNotNull() {
            addCriterion("CONTACT_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andContactTelEqualTo(String value) {
            addCriterion("CONTACT_TEL =", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotEqualTo(String value) {
            addCriterion("CONTACT_TEL <>", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelGreaterThan(String value) {
            addCriterion("CONTACT_TEL >", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_TEL >=", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLessThan(String value) {
            addCriterion("CONTACT_TEL <", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_TEL <=", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLike(String value) {
            addCriterion("CONTACT_TEL like", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotLike(String value) {
            addCriterion("CONTACT_TEL not like", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelIn(List<String> values) {
            addCriterion("CONTACT_TEL in", values, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotIn(List<String> values) {
            addCriterion("CONTACT_TEL not in", values, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelBetween(String value1, String value2) {
            addCriterion("CONTACT_TEL between", value1, value2, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotBetween(String value1, String value2) {
            addCriterion("CONTACT_TEL not between", value1, value2, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNull() {
            addCriterion("CONTACT_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNotNull() {
            addCriterion("CONTACT_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneEqualTo(String value) {
            addCriterion("CONTACT_PHONE =", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotEqualTo(String value) {
            addCriterion("CONTACT_PHONE <>", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThan(String value) {
            addCriterion("CONTACT_PHONE >", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_PHONE >=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThan(String value) {
            addCriterion("CONTACT_PHONE <", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_PHONE <=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLike(String value) {
            addCriterion("CONTACT_PHONE like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotLike(String value) {
            addCriterion("CONTACT_PHONE not like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIn(List<String> values) {
            addCriterion("CONTACT_PHONE in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotIn(List<String> values) {
            addCriterion("CONTACT_PHONE not in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneBetween(String value1, String value2) {
            addCriterion("CONTACT_PHONE between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotBetween(String value1, String value2) {
            addCriterion("CONTACT_PHONE not between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactZipIsNull() {
            addCriterion("CONTACT_ZIP is null");
            return (Criteria) this;
        }

        public Criteria andContactZipIsNotNull() {
            addCriterion("CONTACT_ZIP is not null");
            return (Criteria) this;
        }

        public Criteria andContactZipEqualTo(String value) {
            addCriterion("CONTACT_ZIP =", value, "contactZip");
            return (Criteria) this;
        }

        public Criteria andContactZipNotEqualTo(String value) {
            addCriterion("CONTACT_ZIP <>", value, "contactZip");
            return (Criteria) this;
        }

        public Criteria andContactZipGreaterThan(String value) {
            addCriterion("CONTACT_ZIP >", value, "contactZip");
            return (Criteria) this;
        }

        public Criteria andContactZipGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_ZIP >=", value, "contactZip");
            return (Criteria) this;
        }

        public Criteria andContactZipLessThan(String value) {
            addCriterion("CONTACT_ZIP <", value, "contactZip");
            return (Criteria) this;
        }

        public Criteria andContactZipLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_ZIP <=", value, "contactZip");
            return (Criteria) this;
        }

        public Criteria andContactZipLike(String value) {
            addCriterion("CONTACT_ZIP like", value, "contactZip");
            return (Criteria) this;
        }

        public Criteria andContactZipNotLike(String value) {
            addCriterion("CONTACT_ZIP not like", value, "contactZip");
            return (Criteria) this;
        }

        public Criteria andContactZipIn(List<String> values) {
            addCriterion("CONTACT_ZIP in", values, "contactZip");
            return (Criteria) this;
        }

        public Criteria andContactZipNotIn(List<String> values) {
            addCriterion("CONTACT_ZIP not in", values, "contactZip");
            return (Criteria) this;
        }

        public Criteria andContactZipBetween(String value1, String value2) {
            addCriterion("CONTACT_ZIP between", value1, value2, "contactZip");
            return (Criteria) this;
        }

        public Criteria andContactZipNotBetween(String value1, String value2) {
            addCriterion("CONTACT_ZIP not between", value1, value2, "contactZip");
            return (Criteria) this;
        }

        public Criteria andContactAddressIsNull() {
            addCriterion("CONTACT_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andContactAddressIsNotNull() {
            addCriterion("CONTACT_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andContactAddressEqualTo(String value) {
            addCriterion("CONTACT_ADDRESS =", value, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressNotEqualTo(String value) {
            addCriterion("CONTACT_ADDRESS <>", value, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressGreaterThan(String value) {
            addCriterion("CONTACT_ADDRESS >", value, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_ADDRESS >=", value, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressLessThan(String value) {
            addCriterion("CONTACT_ADDRESS <", value, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_ADDRESS <=", value, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressLike(String value) {
            addCriterion("CONTACT_ADDRESS like", value, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressNotLike(String value) {
            addCriterion("CONTACT_ADDRESS not like", value, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressIn(List<String> values) {
            addCriterion("CONTACT_ADDRESS in", values, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressNotIn(List<String> values) {
            addCriterion("CONTACT_ADDRESS not in", values, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressBetween(String value1, String value2) {
            addCriterion("CONTACT_ADDRESS between", value1, value2, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andContactAddressNotBetween(String value1, String value2) {
            addCriterion("CONTACT_ADDRESS not between", value1, value2, "contactAddress");
            return (Criteria) this;
        }

        public Criteria andBringNameIsNull() {
            addCriterion("BRING_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBringNameIsNotNull() {
            addCriterion("BRING_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBringNameEqualTo(String value) {
            addCriterion("BRING_NAME =", value, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameNotEqualTo(String value) {
            addCriterion("BRING_NAME <>", value, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameGreaterThan(String value) {
            addCriterion("BRING_NAME >", value, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameGreaterThanOrEqualTo(String value) {
            addCriterion("BRING_NAME >=", value, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameLessThan(String value) {
            addCriterion("BRING_NAME <", value, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameLessThanOrEqualTo(String value) {
            addCriterion("BRING_NAME <=", value, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameLike(String value) {
            addCriterion("BRING_NAME like", value, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameNotLike(String value) {
            addCriterion("BRING_NAME not like", value, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameIn(List<String> values) {
            addCriterion("BRING_NAME in", values, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameNotIn(List<String> values) {
            addCriterion("BRING_NAME not in", values, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameBetween(String value1, String value2) {
            addCriterion("BRING_NAME between", value1, value2, "bringName");
            return (Criteria) this;
        }

        public Criteria andBringNameNotBetween(String value1, String value2) {
            addCriterion("BRING_NAME not between", value1, value2, "bringName");
            return (Criteria) this;
        }

        public Criteria andCardTypeIsNull() {
            addCriterion("CARD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCardTypeIsNotNull() {
            addCriterion("CARD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCardTypeEqualTo(String value) {
            addCriterion("CARD_TYPE =", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotEqualTo(String value) {
            addCriterion("CARD_TYPE <>", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeGreaterThan(String value) {
            addCriterion("CARD_TYPE >", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_TYPE >=", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLessThan(String value) {
            addCriterion("CARD_TYPE <", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLessThanOrEqualTo(String value) {
            addCriterion("CARD_TYPE <=", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeLike(String value) {
            addCriterion("CARD_TYPE like", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotLike(String value) {
            addCriterion("CARD_TYPE not like", value, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeIn(List<String> values) {
            addCriterion("CARD_TYPE in", values, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotIn(List<String> values) {
            addCriterion("CARD_TYPE not in", values, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeBetween(String value1, String value2) {
            addCriterion("CARD_TYPE between", value1, value2, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardTypeNotBetween(String value1, String value2) {
            addCriterion("CARD_TYPE not between", value1, value2, "cardType");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNull() {
            addCriterion("CARD_ID is null");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNotNull() {
            addCriterion("CARD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCardIdEqualTo(String value) {
            addCriterion("CARD_ID =", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotEqualTo(String value) {
            addCriterion("CARD_ID <>", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThan(String value) {
            addCriterion("CARD_ID >", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_ID >=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThan(String value) {
            addCriterion("CARD_ID <", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThanOrEqualTo(String value) {
            addCriterion("CARD_ID <=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLike(String value) {
            addCriterion("CARD_ID like", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotLike(String value) {
            addCriterion("CARD_ID not like", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdIn(List<String> values) {
            addCriterion("CARD_ID in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotIn(List<String> values) {
            addCriterion("CARD_ID not in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdBetween(String value1, String value2) {
            addCriterion("CARD_ID between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotBetween(String value1, String value2) {
            addCriterion("CARD_ID not between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andBringTelIsNull() {
            addCriterion("BRING_TEL is null");
            return (Criteria) this;
        }

        public Criteria andBringTelIsNotNull() {
            addCriterion("BRING_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andBringTelEqualTo(String value) {
            addCriterion("BRING_TEL =", value, "bringTel");
            return (Criteria) this;
        }

        public Criteria andBringTelNotEqualTo(String value) {
            addCriterion("BRING_TEL <>", value, "bringTel");
            return (Criteria) this;
        }

        public Criteria andBringTelGreaterThan(String value) {
            addCriterion("BRING_TEL >", value, "bringTel");
            return (Criteria) this;
        }

        public Criteria andBringTelGreaterThanOrEqualTo(String value) {
            addCriterion("BRING_TEL >=", value, "bringTel");
            return (Criteria) this;
        }

        public Criteria andBringTelLessThan(String value) {
            addCriterion("BRING_TEL <", value, "bringTel");
            return (Criteria) this;
        }

        public Criteria andBringTelLessThanOrEqualTo(String value) {
            addCriterion("BRING_TEL <=", value, "bringTel");
            return (Criteria) this;
        }

        public Criteria andBringTelLike(String value) {
            addCriterion("BRING_TEL like", value, "bringTel");
            return (Criteria) this;
        }

        public Criteria andBringTelNotLike(String value) {
            addCriterion("BRING_TEL not like", value, "bringTel");
            return (Criteria) this;
        }

        public Criteria andBringTelIn(List<String> values) {
            addCriterion("BRING_TEL in", values, "bringTel");
            return (Criteria) this;
        }

        public Criteria andBringTelNotIn(List<String> values) {
            addCriterion("BRING_TEL not in", values, "bringTel");
            return (Criteria) this;
        }

        public Criteria andBringTelBetween(String value1, String value2) {
            addCriterion("BRING_TEL between", value1, value2, "bringTel");
            return (Criteria) this;
        }

        public Criteria andBringTelNotBetween(String value1, String value2) {
            addCriterion("BRING_TEL not between", value1, value2, "bringTel");
            return (Criteria) this;
        }

        public Criteria andBringPhoneIsNull() {
            addCriterion("BRING_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andBringPhoneIsNotNull() {
            addCriterion("BRING_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andBringPhoneEqualTo(String value) {
            addCriterion("BRING_PHONE =", value, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneNotEqualTo(String value) {
            addCriterion("BRING_PHONE <>", value, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneGreaterThan(String value) {
            addCriterion("BRING_PHONE >", value, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("BRING_PHONE >=", value, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneLessThan(String value) {
            addCriterion("BRING_PHONE <", value, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneLessThanOrEqualTo(String value) {
            addCriterion("BRING_PHONE <=", value, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneLike(String value) {
            addCriterion("BRING_PHONE like", value, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneNotLike(String value) {
            addCriterion("BRING_PHONE not like", value, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneIn(List<String> values) {
            addCriterion("BRING_PHONE in", values, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneNotIn(List<String> values) {
            addCriterion("BRING_PHONE not in", values, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneBetween(String value1, String value2) {
            addCriterion("BRING_PHONE between", value1, value2, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andBringPhoneNotBetween(String value1, String value2) {
            addCriterion("BRING_PHONE not between", value1, value2, "bringPhone");
            return (Criteria) this;
        }

        public Criteria andContactDateIsNull() {
            addCriterion("CONTACT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andContactDateIsNotNull() {
            addCriterion("CONTACT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andContactDateEqualTo(Timestamp value) {
            addCriterion("CONTACT_DATE =", value, "contactDate");
            return (Criteria) this;
        }

        public Criteria andContactDateNotEqualTo(Timestamp value) {
            addCriterion("CONTACT_DATE <>", value, "contactDate");
            return (Criteria) this;
        }

        public Criteria andContactDateGreaterThan(Timestamp value) {
            addCriterion("CONTACT_DATE >", value, "contactDate");
            return (Criteria) this;
        }

        public Criteria andContactDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CONTACT_DATE >=", value, "contactDate");
            return (Criteria) this;
        }

        public Criteria andContactDateLessThan(Timestamp value) {
            addCriterion("CONTACT_DATE <", value, "contactDate");
            return (Criteria) this;
        }

        public Criteria andContactDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("CONTACT_DATE <=", value, "contactDate");
            return (Criteria) this;
        }

        public Criteria andContactDateIn(List<Timestamp> values) {
            addCriterion("CONTACT_DATE in", values, "contactDate");
            return (Criteria) this;
        }

        public Criteria andContactDateNotIn(List<Timestamp> values) {
            addCriterion("CONTACT_DATE not in", values, "contactDate");
            return (Criteria) this;
        }

        public Criteria andContactDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CONTACT_DATE between", value1, value2, "contactDate");
            return (Criteria) this;
        }

        public Criteria andContactDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CONTACT_DATE not between", value1, value2, "contactDate");
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