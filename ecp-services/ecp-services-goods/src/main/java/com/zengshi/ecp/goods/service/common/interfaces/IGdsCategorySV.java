/** 
 * Project Name:ecp-services-goods 
 * File Name:IGdsTypeSV.java 
 * Package Name:com.zengshi.ecp.goods.service.common.interfaces 
 * Date:2015年8月11日上午10:33:51 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsCategory;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: 商品类型Service接口 <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月11日上午10:33:51  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsCategorySV extends IGeneralSQLSV{
    
    
    /**
     * 
     * 新增类别基础信息.
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public GdsCategoryRespDTO saveGdsCategory(GdsCategoryReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 保存商品类别. <br/> 
     * 
     * @author liyong7
     * @param gdsCategory
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public int saveGdsCategory(GdsCategory gdsCategory) throws BusinessException;
    /**
     * 
     * 根据主键ID查询商品类别信息. <br/> 
     * 
     * @author liyong7
     * @param catgCode
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public GdsCategory queryGdsCategoryById(String catgCode) throws BusinessException;
    /**
     * 
     * 根据主键ID查询商品类别信息. <br/> 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public GdsCategoryRespDTO queryGdsCategoryByPK(GdsCategoryReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * queryGdsCategoryByPKWithAuth:结合分类主键与权限查询分类信息. <br/> 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public GdsCategoryRespDTO queryGdsCategoryByPKWithAuth(GdsCategoryReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 编辑商品分类信息. <br/> 
     * 
     * @author liyong7
     * @param gdsType
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     * @deprecated 尽量避免使用.
     */
	 public int editGdsCategory(GdsCategory gdsCategory) throws BusinessException;
     /**
      * 
      * 编辑商品分类信息. <br/> 
      * 
      * @author liyong7
      * @param gdsType
      * @return
      * @throws BusinessException 
      * @since JDK 1.6
      */
	 public  int editGdsCategory(GdsCategoryReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 根据复合条件来判断一个商品分类是否已经存在.
     * 
     * @author liyong7
     * @param catgName
     * @param catgType
     * @param level
     * @param shopId
     * @param excludeCatgCodes
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	 public boolean queryExist(String catgName,String catgType ,Short level,Long shopId,String[] excludeCatgCodes, String... status) throws BusinessException;
    
    /**
    * 
    * 禁用商品分类. <br/> 
    * 
    * @author liyong7
    * @param catgCode
    * @param updateStaff
    * @throws BusinessException 
    * @since JDK 1.6
    */
	 public void executeDisableGdsCategory(String catgCode, Long updateStaff) throws BusinessException;
    /**
     * 
     * 删除指定类别编码分类.<br/> 
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
	 public void deleteGdsCategory(GdsCategoryReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 根据层级与状态复合条件查询出商品类别信息 <br/> 
     * @author liyong7
     * @param catgType 平台分类类型。
     * @param level
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	 public List<GdsCategory> queryGdsCategory(String catgType,Short level, String... status) throws BusinessException;
    /**
     * 
     * 结合状态条件查询出根商品分类树. <br/> 
     * @author liyong7
     * @param catgType 分类类型
     * @param shopId
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	 public List<GdsCategoryRespDTO> queryRootGdsCategory(String catgType,Long shopId,String... status) throws BusinessException;
    /**
     * 
     * 根据父ID查询出子级商品分类信息. <br/> 
     * 
     * @author liyong7
     * @param catgParent
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	 public List<GdsCategoryRespDTO> querySubGdsCategory(String catgParent, String... status) throws BusinessException;
    /**
     * 
     * 根据父ID查询出是否存在有效子级分类,后续禁用分类前根据需求<br/>
     * 需要在子级分类全部禁用的情况下方可禁用父级分类. <br/> 
     * 
     * @author liyong7
     * @param catgParent
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	 public Long countSubCategory(String catgParent, String... status) throws BusinessException;
    /**
     * 
     * 分页查询. <br/> 
     * @author liyong7
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	 public PageResponseDTO<GdsCategoryRespDTO> queryGdsCatetoryRespDTOPaging(GdsCategoryReqDTO dto) throws BusinessException;
	 
	 /**
	  * 
	  * queryGdsCatetoryRespDTOPagingWithAuth:带权限分页查询. <br/> 
	  * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	  * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	  * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	  * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	  * 
	  * @author liyong7
	  * @param dto
	  * @return
	  * @throws BusinessException 
	  * @since JDK 1.6
	  */
	 public PageResponseDTO<GdsCategoryRespDTO> queryGdsCatetoryRespDTOPagingWithAuth(GdsCategoryReqDTO dto) throws BusinessException;
    
    /**
     * 
     * 根据分类主键更新分类是否叶节点属性。<br/>
     * 
     * @author liyong7
     * @param catgCode
     * @param updateStaff
     * @throws BusinessException 
     * @since JDK 1.6
     */
	 public void updateIfLeafByPK(String catgCode, Long updateStaff) throws BusinessException;
    /**
     * 
     * 根据分类编码查询出当前分类的顶级分类。 
     * 
     * @author liyong7
     * @param catgCode
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	 public GdsCategoryRespDTO queryRootCategoryByPK(String catgCode)throws BusinessException;
    /**
     * 
     * 根据当前分类编码向上获取分类轨迹。
     * 
     * @author liyong7
     * @param catgCode 商品分类编码。
     * @return 返回分类轨迹序列，针对返回值需要进行null判断,迭代获取即可。lst.get(0)即为根分类。
     * @throws BusinessException 
     * @since JDK 1.6
     */
	 public List<GdsCategoryRespDTO> queryCategoryTraceUpon(String catgCode)throws BusinessException;
    
    /**
     * 
     * 分类比较.<br/> 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsCategoryCompareRespDTO executeCompare(GdsCategoryCompareReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * queryCategoryLimitByCatgCodeAndTargetLevel:根据分类ID与目标层级查询分类信息. <br/> 
     * 
     * @author liyong7
     * @param reqDTO targetLevel必须小于当前catgCode的分类编码查询出来的分类信息,并且大等于1。
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsCategoryRespDTO queryCategoryLimitByCatgCodeAndTargetLevel(GdsCategoryReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * queryGdsCategoryById:根据reqDTO中的分类主键catgCode查询分类信息。
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsCategoryRespDTO queryGdsCategoryById(GdsCategoryReqDTO reqDTO) throws BusinessException;
    
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
    public List<GdsCategoryRespDTO> querySubCategoryConnectByCatgParent(GdsCategoryReqDTO reqDTO) throws BusinessException;
    
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
    public PageResponseDTO<GdsCategoryRespDTO> queryCustCommissionPaging(GdsCategoryReqDTO dto) throws BusinessException;
    
}

