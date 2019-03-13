package com.zengshi.ecp.staff.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ScoreResultResDTO extends BaseResponseDTO{
	/** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    private Long score;
	private String scoreType;
    private String optType;
    private String actionType;
    private String remark;
    private Long siteId;
    
    private String orderId;
    private String orderSubId;
    
    private String isbnCode;
    
    private String bookCode;

	public ScoreResultResDTO()
	{
	    score = 0L;
	}
	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

    /** 
     * scoreType. 
     * 
     * @return  the scoreType 
     * @since   JDK 1.6 
     */
    public String getScoreType() {
        return scoreType;
    }

    /** 
     * scoreType. 
     * 
     * @param   scoreType    the scoreType to set 
     * @since   JDK 1.6 
     */
    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }
    /** 
     * optType. 
     * 
     * @return  the optType 
     * @since   JDK 1.6 
     */
    public String getOptType() {
        return optType;
    }
    /** 
     * optType. 
     * 
     * @param   optType    the optType to set 
     * @since   JDK 1.6 
     */
    public void setOptType(String optType) {
        this.optType = optType;
    }
    /** 
     * actionType. 
     * 
     * @return  the actionType 
     * @since   JDK 1.6 
     */
    public String getActionType() {
        return actionType;
    }
    /** 
     * actionType. 
     * 
     * @param   actionType    the actionType to set 
     * @since   JDK 1.6 
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
    /** 
     * remark. 
     * 
     * @return  the remark 
     * @since   JDK 1.6 
     */
    public String getRemark() {
        return remark;
    }
    /** 
     * remark. 
     * 
     * @param   remark    the remark to set 
     * @since   JDK 1.6 
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getOrderSubId() {
        return orderSubId;
    }
    public void setOrderSubId(String orderSubId) {
        this.orderSubId = orderSubId;
    }
    public Long getSiteId() {
        return siteId;
    }
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
    public String getIsbnCode() {
        return isbnCode;
    }
    public void setIsbnCode(String isbnCode) {
        this.isbnCode = isbnCode;
    }
    public String getBookCode() {
        return bookCode;
    }
    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }
	
}
