package com.zengshi.ecp.goods.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class GdsCatg2PropRelationRespDTO extends BaseResponseDTO{
	
	/** 
	 * @since JDK 1.6 
	 */ 
    private static final long serialVersionUID = 1L;

	/**
	 * 基础属性
	 */
	private List<GdsPropRespDTO> basics;
	
	/**
	 * 规格属性
	 */
	private List<GdsPropRespDTO> specs;

	/**
	 * 参数属性
	 */
	private List<GdsPropRespDTO> params;

	/**
	 * 其他属性
	 */
	private List<GdsPropRespDTO> others;
	
	/**
     * add by gongxq 富文本属性（用于商品录入）
     */
    private List<GdsPropRespDTO> editoParam;
    /**
     * add by gongxq 附件/文件 属性（用于商品录入）
     */
    private List<GdsPropRespDTO> fileParam;
	/**
	 * 搜索属性。
	 */
	private List<GdsPropRespDTO> searchProps;

	public List<GdsPropRespDTO> getBasics() {
		return basics;
	}

	public void setBasics(List<GdsPropRespDTO> basics) {
		this.basics = basics;
	}

	public List<GdsPropRespDTO> getSpecs() {
		return specs;
	}

	public void setSpecs(List<GdsPropRespDTO> specs) {
		this.specs = specs;
	}

	public List<GdsPropRespDTO> getParams() {
		return params;
	}

	public void setParams(List<GdsPropRespDTO> params) {
		this.params = params;
	}

	public List<GdsPropRespDTO> getOthers() {
		return others;
	}

	public void setOthers(List<GdsPropRespDTO> others) {
		this.others = others;
	}

	public List<GdsPropRespDTO> getSearchProps() {
		return searchProps;
	}

	public void setSearchParams(List<GdsPropRespDTO> searchProps) {
		this.searchProps = searchProps;
	}

    public List<GdsPropRespDTO> getEditoParam() {
        return editoParam;
    }

    public void setEditoParam(List<GdsPropRespDTO> editoParam) {
        this.editoParam = editoParam;
    }

    public List<GdsPropRespDTO> getFileParam() {
        return fileParam;
    }

    public void setFileParam(List<GdsPropRespDTO> fileParam) {
        this.fileParam = fileParam;
    }

	

}

