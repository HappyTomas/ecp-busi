/** 
 * Project Name:ecp-services-goods-server 
 * File Name:GdsPropRespDTOComparator.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.comparator 
 * Date:2015-10-17上午10:56:27 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.comparator;

import java.util.Comparator;

import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;

/**
 * Title: GdsPropRespDTO比较器. <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-10-17上午10:56:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsPropRespDTOComparator implements Comparator<GdsPropRespDTO> {

	
	
	@Override
	public int compare(GdsPropRespDTO source, GdsPropRespDTO target) {
		if (source == target)
			return 0;
		
		if(null != source.getSortNo()){
			if(null != target.getSortNo()){
				if(source.getSortNo().equals(target.getSortNo())){
					return (target.getId() - source.getId() > 0) ? 1 : -1;
				}
				return target.getSortNo() - source.getSortNo();
			}else{
				return 1;
			}
		}else{
			if(null == target.getSortNo()){
				return (target.getId() - source.getId() > 0) ? 1 : -1;
			}else{
				return 1;
			}
		}
	}


}

