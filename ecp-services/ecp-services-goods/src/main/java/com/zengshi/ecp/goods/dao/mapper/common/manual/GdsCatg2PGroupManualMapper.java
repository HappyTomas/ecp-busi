package com.zengshi.ecp.goods.dao.mapper.common.manual;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
/**
 * 
 * 针对GdsCatg2Prop操作的特殊操作接口。<br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月18日上午10:23:30  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public interface GdsCatg2PGroupManualMapper {
    /**
     * 
     * queryConfigedPropIds:(这里用一句话描述这个方法的作用). <br/> 
     * 
     * @author liyong7
     * @param catgCode
     * @param status
     * @return
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    List<Long> queryConfigedPropGroupIds(@Param("catgCode") String catgCode,@Param("status") String... status) throws DataAccessException;
}
