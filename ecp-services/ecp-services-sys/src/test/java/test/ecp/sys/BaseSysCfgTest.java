package test.ecp.sys;

import java.sql.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.sys.dubbo.dto.SysCfgReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.SysCfgResDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.ISysCfgRSV;

public class BaseSysCfgTest extends EcpServicesTest {
	@Resource
	private ISysCfgRSV sysCfgRSV;
	
	
	@Test
    public void pageTest(){
		SysCfgReqDTO sysCfgReqDTO = new SysCfgReqDTO();
		sysCfgReqDTO.setPageSize(10);
		sysCfgReqDTO.setParaValue("0");
		 PageResponseDTO<SysCfgResDTO> li = sysCfgRSV.querySysCfgForPage(sysCfgReqDTO);
		 for(int i=0;i<li.getResult().size();i++){
			 System.out.println(li.getResult().get(i).getParaCode());
		 }
	}
	@Test
    public void saveTest(){
		SysCfgReqDTO sysCfgReqDTO = new SysCfgReqDTO();
		sysCfgReqDTO.setParaCode("PROM_TYPE3");
		sysCfgReqDTO.setParaDesc("hahah");
		sysCfgReqDTO.setParaValue("5");
		sysCfgReqDTO.setCreateStaff(4444444L);
		sysCfgReqDTO.setUpdateStaff(55555L);
		sysCfgReqDTO.setUpdateTime(new Date(555555555555L));
		System.out.println(sysCfgRSV.saveSysCfg(sysCfgReqDTO));
		
	}
	@Test
    public void updateTest(){
		SysCfgReqDTO sysCfgReqDTO = new SysCfgReqDTO();
		sysCfgReqDTO.setSearchParams("PROM_TYPE2");
		sysCfgReqDTO.setParaCode("PROM_TYPE2");
		sysCfgReqDTO.setParaDesc("hahah");
		sysCfgReqDTO.setParaValue("4");
		sysCfgReqDTO.setCreateStaff(444444L);
		sysCfgReqDTO.setUpdateStaff(5555L);
		sysCfgReqDTO.setUpdateTime(new Date(55555555L));
		System.out.println(sysCfgRSV.updateSysCfg(sysCfgReqDTO));
		
	}
	
	@Test
    public void deciateTest(){
		SysCfgReqDTO sysCfgReqDTO = new SysCfgReqDTO();
		sysCfgReqDTO.setParaCode("PROM_TYPE");
		SysCfgResDTO sysCfgResDTO =	sysCfgRSV.fetchByparaCode(sysCfgReqDTO);
		System.out.println(sysCfgResDTO.getParaDesc());
		System.out.println(sysCfgResDTO.getParaValue());
	}
	
	
	
	
	
	
}
