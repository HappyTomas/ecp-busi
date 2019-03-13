package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PGroupReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PGroupRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.StringReqDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsCategoryRSV {
    /**
     * 
     * 保存商品分类. 
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public GdsCategoryRespDTO saveGdsCategory(GdsCategoryReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 删除商品分类,reqDTO中的ID参数必传.
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public void deleteGdsCategory(GdsCategoryReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 编辑商品分类.
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public void editGdsCategory(GdsCategoryReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * queryUneditable:查询不可编辑分类属性关联关系.<br/>
	 * 
	 * @author liyong7
	 * @param catgCode
	 * @param propIds
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public Long queryUneditableCatg2Prop(GdsCatg2PropReqDTO reqDTO)throws BusinessException;
    
    /**
     * 
     * 分页查询商品分类.<br/>
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public PageResponseDTO<GdsCategoryRespDTO> queryGdsCategoryPaging(GdsCategoryReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * queryGdsCategoryPagingWithAuth:带权限分类分页查询. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsCategoryRespDTO> queryGdsCategoryPagingWithAuth(GdsCategoryReqDTO reqDTO)
            throws BusinessException;
    
    /**
     * 
     * 根据主键查询商品分类信息. 
     * 
     * @author liyong7
     * @param reqDTO reqDTO中的catgCode为必传参数;<br/>
     * 				isContainSub为true时则递归获取所有子级分类。<br/>
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public GdsCategoryRespDTO queryGdsCategoryByPK(GdsCategoryReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * queryCategoryProps:(根据分类信息查询对应分类的属性). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public GdsCatg2PropRelationRespDTO queryCategoryProps(GdsCatg2PropReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 根据主键值删除商品分类.
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public void deleteGdsCategoryByPK(StringReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * queryAllTopCategory:(查询所有顶级分类). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public List<GdsCategoryRespDTO> queryRootCategory(GdsCategoryReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * queryRootCategoryWithAuth:带权限查询根节点. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public List<GdsCategoryRespDTO> queryRootCategoryWithAuth(GdsCategoryReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * querySubCategory:(查询对应分类的子分类,该方法只返回一级子节点，不包含当前节点). <br/> 
     * 
     * @author linwb3
     * @param 
     * @return 
     * @since JDK 1.6
     */
	public List<GdsCategoryRespDTO> querySubCategory(GdsCategoryReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * querySubCategoryWithAuth:结合权限查询子分类. <br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public List<GdsCategoryRespDTO> querySubCategoryWithAuth(GdsCategoryReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 分页查询出已经与指定分类关联的属性。<br/>
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public PageResponseDTO<GdsCatg2PropRespDTO> queryConfigedPropsPaging(GdsCatg2PropReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 分页查询备选属性。 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public PageResponseDTO<GdsPropRespDTO> queryOptionalPropsPaging(GdsCatg2PropReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 分页查询出已配置属性组。<br/>
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public PageResponseDTO<GdsCatg2PGroupRespDTO> queryConfigedPropgroupPaging(GdsCatg2PGroupReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 分页查询备选属性组。 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public PageResponseDTO<GdsPropGroupRespDTO> queryOptionalPropgroupPaging(GdsCatg2PGroupReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 根据指定分类主键查询出对该分类的顶级分类。 
     * 
     * @author liyong7
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public GdsCategoryRespDTO queryRootCategoryByPK(GdsCategoryReqDTO reqDTO)throws BusinessException;
    
    /**
     * 
     * 关联属性.<br/>  
     * 
     * @author liyong7
     * @param reqDTO catgCode与propIds必须有值.
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public void addProps(GdsCatg2PropReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 删除关联属性.<br/>  
     * 
     * @author liyong7
     * @param reqDTO catgCode与propIds必须有值.
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public void deleteProps(GdsCatg2PropReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 关联属性组.<br/>  
     * 
     * @author liyong7
     * @param reqDTO catgCode与propIds必须有值.
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public void addGroups(GdsCatg2PGroupReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 关联属性组.<br/>  
     * 
     * @author liyong7
     * @param reqDTO catgCode与propIds必须有值.
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public void deleteGroups(GdsCatg2PGroupReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 配置是否基础属性. 
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public void executeIsBaseConfig(GdsCatg2PropReqDTO reqDTO)
			throws BusinessException ;
	
	/**
	 * 
	 * 配置是否必填属性. 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void executeIsRequireConfig(GdsCatg2PropReqDTO reqDTO)
			throws BusinessException;
	
	/**
	 * 
	 * 配置是否搜索属性.
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void executeIsSearchConfig(GdsCatg2PropReqDTO reqDTO)
			throws BusinessException;
    
	/**
	 * 
	 * 根据分类编码查询出搜索属性。<br/>
	 * 
	 * @author liyong7
	 * @param reqDTO catgCode必传。
	 * @return 对返回值需要进行null判断，获取GdsCatg2PropRelationRespDTO中的searchProps,<br/>
	 * searchProps无需进行null判断，如果没有返回属性则返回一个空List.<br/>
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public GdsCatg2PropRelationRespDTO querySearchProps(GdsCatg2PropReqDTO reqDTO)throws BusinessException;
	/**
	 * 
	 * 根据传入的分类编码向上查询分类轨迹序列。<br/> 
	 * 
	 * @author liyong7
	 * @param catgCode
	 * @return 返回分类轨序序列，序列索引为零表示根节点。在传入的catgCode为无效编码时会返回null,需要针对返回值作null判断。<br/>
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public List<GdsCategoryRespDTO> queryCategoryTraceUpon(GdsCategoryReqDTO reqDTO)throws BusinessException;
	/**
	 * 
	 * 执行分类比较,看是否有联,源分类编码与目标分类编码的父子级关系. 
	 * 
	 * @author liyong7
	 * @param reqDTO sourceCode与targetCode必传.
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public GdsCategoryCompareRespDTO executeCompare(GdsCategoryCompareReqDTO reqDTO) throws BusinessException;
	/**
	 * 
	 * 是否输入属性配置.
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void executeIfGdsInputConfig(GdsCatg2PropReqDTO reqDTO ) throws BusinessException;
	
	/**
	 * 
	 * 查询统计有效子分类数量.<br/>
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public Long countSubCategory(GdsCategoryReqDTO reqDTO) throws BusinessException;
	/**
	 * 
	 * queryCategoryLimitByCatgCodeAndTargetLevel:根据目标分类编码查询出目标层级分类. <br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO catgCode与targetLevel为必传参数。
	 * @return 如果当前分类不存在，则返回空，如果targetLevel大于当前分类的catgLevel返回空，如果不存在指写层级分类也返回空。
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public GdsCategoryRespDTO queryCategoryLimitByCatgCodeAndTargetLevel(GdsCategoryReqDTO reqDTO) throws BusinessException;
	/**
	 * 
	 * repairGds2Catg:数据修复. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author liyong7
	 * @throws BusinessException 
	 * @since JDK 1.6
	 * @deprecated 文彬提供数据修复,请不要使用此类. 
	 */
	public void repairGds2Catg(BaseInfo baseInfo)throws BusinessException;
	
	
	/**
     * 
     * querySubCategoryConnectByCatgParent:根据父节点ID进行树型连接查询. <br/> 
     * 
     * @author liyong7
     * @param reqDTO catgParent请保证有传值。
     * @return 返回包含自身的所有子级分类,该结果集未作排序。
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public List<GdsCategoryRespDTO> querySubCategoryConnectByCatgParent(
			GdsCategoryReqDTO reqDTO) throws BusinessException ;
	
	
	/**
     * 
     * 根据条件查询出分类以及与之关联的属性的信息以及属性值信息。 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsCatg2PropRelationRespDTO queryCategoryPropsByCondition(GdsCatg2PropReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * 根据条件查询出分类 (人卫查询考试培训分类专用)
     * 
     * @author lincx
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<GdsCategoryRespDTO> queryExaCategory(GdsCategoryReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 客户提成比例分页查询.<br/>
     * 
     * @author lincx
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public PageResponseDTO<GdsCategoryRespDTO> queryCustCommission(GdsCategoryReqDTO reqDTO) throws BusinessException;
}

