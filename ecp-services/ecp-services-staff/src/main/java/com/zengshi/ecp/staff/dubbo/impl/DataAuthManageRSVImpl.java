package com.zengshi.ecp.staff.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.DataAuthReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataAuthResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFieldItemReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFieldItemResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFieldRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFieldRuleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFuncReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFuncResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFuncZTreeNodeDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleItemReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleItemResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IDataAuthManageRSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IDataAuthManageSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 数据权限接口RSVImpl<br>
 * Date:2015年10月12日上午9:41:21  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class DataAuthManageRSVImpl implements IDataAuthManageRSV {
    
    @Resource
    private IDataAuthManageSV dataAuthManageSV; // 数据权限

    @Override
    public long saveDataRuleItem(DataRuleItemReqDTO reqDto) throws BusinessException {
        return dataAuthManageSV.saveDataRuleItem(reqDto);
    }

    @Override
    public void updateDataRuleItem(DataRuleItemReqDTO reqDto) throws BusinessException {
        dataAuthManageSV.updateDataRuleItemById(reqDto);
    }

    @Override
    public void deleteDataRuleItemById(DataRuleItemReqDTO reqDto) throws BusinessException {
        dataAuthManageSV.deleteDataRuleItemById(reqDto);
    }

    @Override
    public DataRuleItemResDTO findDataRuleItemById(DataRuleItemReqDTO reqDto)
            throws BusinessException {
        return dataAuthManageSV.findDataRuleItemById(reqDto);
    }

    @Override
    public PageResponseDTO<DataRuleItemResDTO> listDataRuleItem(DataRuleItemReqDTO reqDto)
            throws BusinessException {
        return dataAuthManageSV.listDataRuleItem(reqDto);
    }

    @Override
    public long saveDataRule(DataRuleReqDTO reqDto) throws BusinessException {
        return dataAuthManageSV.saveDataRule(reqDto);
    }

    @Override
    public DataRuleResDTO findDataRuleById(DataRuleReqDTO reqDto) throws BusinessException {
        return dataAuthManageSV.findDataRuleById(reqDto);
    }

    @Override
    public void updateDataRuleById(DataRuleReqDTO reqDto) throws BusinessException {
        dataAuthManageSV.updateDataRuleById(reqDto);
    }

    @Override
    public void deleteDataRuleById(DataRuleReqDTO reqDto) throws BusinessException {
        dataAuthManageSV.deleteDataRuleById(reqDto);
    }

    @Override
    public PageResponseDTO<DataRuleResDTO> listDataRule(DataRuleReqDTO reqDto)
            throws BusinessException {
        return dataAuthManageSV.listDataRule(reqDto);
    }

    @Override
    public long saveDataAuth(DataAuthReqDTO reqDto) throws BusinessException {
        return dataAuthManageSV.saveDataAuth(reqDto);
    }

    @Override
    public DataAuthResDTO findDataAuthById(DataAuthReqDTO reqDto) throws BusinessException {
        return dataAuthManageSV.findDataAuthById(reqDto);
    }

    @Override
    public void updateDataAuthById(DataAuthReqDTO reqDto) throws BusinessException {
        dataAuthManageSV.updateDataAuthById(reqDto);
    }

    @Override
    public void deleteDataAuthById(DataAuthReqDTO reqDto) throws BusinessException {
        dataAuthManageSV.deleteDataAuthById(reqDto);
    }

    @Override
    public PageResponseDTO<DataAuthResDTO> listDataAuth(DataAuthReqDTO reqdDto)
            throws BusinessException {
        return dataAuthManageSV.listDataAuth(reqdDto);
    }

	@Override
	public long saveDataFunc(DataFuncReqDTO reqDto) throws BusinessException {
		return dataAuthManageSV.saveDataFunc(reqDto);
	}

	@Override
	public void deleteDataFuncById(DataFuncReqDTO reqDto) throws BusinessException {
		dataAuthManageSV.deleteDataFuncById(reqDto);
	}

	@Override
	public void updateDataFuncById(DataFuncReqDTO reqDto) throws BusinessException {
		dataAuthManageSV.updateDataFuncById(reqDto);
	}

	@Override
	public void chstaDataFuncById(DataFuncReqDTO reqDto) throws BusinessException {
		dataAuthManageSV.chstaDataFuncById(reqDto);
	}

	@Override
	public DataFuncResDTO findDataFuncById(DataFuncReqDTO reqDto) throws BusinessException {
		return dataAuthManageSV.findDataFuncById(reqDto);
	}

	@Override
	public PageResponseDTO<DataFuncResDTO> listDataFunc(DataFuncReqDTO reqDto) throws BusinessException {
		return dataAuthManageSV.listDataFunc(reqDto);
	}

	@Override
	public void saveDataRuleBatch(DataAuthReqDTO reqDto) throws BusinessException {
		dataAuthManageSV.saveDataRuleBatch(reqDto);
	}

	@Override
	public List<DataFuncZTreeNodeDTO> generateEntireTreeDataFunc(DataFuncReqDTO reqDto) throws BusinessException {
		return dataAuthManageSV.generateEntireTreeDataFunc(reqDto);
	}

	@Override
	public long saveDataFieldRule(DataFieldRuleReqDTO reqDto) throws BusinessException {
		return dataAuthManageSV.saveDataFieldRule(reqDto);
	}

	@Override
	public DataFieldRuleResDTO findDataFieldRuleById(DataFieldRuleReqDTO reqDto) throws BusinessException {
		return dataAuthManageSV.findDataFieldRuleById(reqDto);
	}

	@Override
	public void updateDataFieldRuleById(DataFieldRuleReqDTO reqDto) throws BusinessException {
		dataAuthManageSV.updateDataFieldRuleById(reqDto);
	}

	@Override
	public void deleteDataFieldRuleById(DataFieldRuleReqDTO reqDto) throws BusinessException {
		dataAuthManageSV.deleteDataFieldRuleById(reqDto);
	}

	@Override
	public PageResponseDTO<DataFieldRuleResDTO> listDataFieldRule(DataFieldRuleReqDTO reqDto) throws BusinessException {
		return dataAuthManageSV.listDataFieldRule(reqDto);
	}

	@Override
	public long saveDataFieldItem(DataFieldItemReqDTO reqDto) throws BusinessException {
		return dataAuthManageSV.saveDataFieldItem(reqDto);
	}

	@Override
	public DataFieldItemResDTO findDataFieldItemById(DataFieldItemReqDTO reqDto) throws BusinessException {
		return dataAuthManageSV.findDataFieldItemById(reqDto);
	}

	@Override
	public void updateDataFieldItemById(DataFieldItemReqDTO reqDto) throws BusinessException {
		dataAuthManageSV.updateDataFieldItemById(reqDto);
	}

	@Override
	public void deleteDataFieldItemById(DataFieldItemReqDTO reqDto) throws BusinessException {
		dataAuthManageSV.deleteDataFieldItemById(reqDto);
	}

	@Override
	public PageResponseDTO<DataFieldItemResDTO> listDataFieldItem(DataFieldItemReqDTO reqDto) throws BusinessException {
		return dataAuthManageSV.listDataFieldItem(reqDto);
	}

	@Override
	public void saveDataFieldRuleBatch(DataAuthReqDTO reqDto) throws BusinessException {
		dataAuthManageSV.saveDataFieldRuleBatch(reqDto);;
	}

}

