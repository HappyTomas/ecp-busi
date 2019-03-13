package com.zengshi.ecp.staff.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.ecp.staff.service.busi.custaddr.interfaces.ICustAddrSV;

public class CustAddrSVImpleTest extends EcpServicesTest{

    @Resource
    private ICustAddrSV custAddrSV;
    
    @Test
    public void testlistCustAddr()
    {
        Long staffID = 101L;
        CustAddrReqDTO sDto =new CustAddrReqDTO();
        try {
            custAddrSV.listCustAddr(sDto);
        } catch (BusinessException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
    @Test
    public void testConventBeanToBean()
    {
        test1 t1 = new test1("pengjie", 13115924273L, 26);
        test2 t2 = new test2();
        StaffTools.coverBean2Bean(t1, t2);
        System.out.println("======"+t2.toString());
        
    }
    public class test1 extends BaseInfo
    {
        /** 
         * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
         * @since JDK 1.6 
         */ 
        private static final long serialVersionUID = 1L;
        private String name;
        private Long staffID;
        private int age;
        public test1() {
        }
        public test1(String name, Long staffid, int age)
        {
            this.name = name;
            this.staffID = staffid;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Long getStaffID() {
            return staffID;
        }
        public void setStaffID(Long staffID) {
            this.staffID = staffID;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        /** 
         * TODO 简单描述该方法的实现功能（可选）. 
         * @see java.lang.Object#toString() 
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("test1 [name=");
            builder.append(name);
            builder.append(", staffID=");
            builder.append(staffID);
            builder.append(", age=");
            builder.append(age);
            builder.append("]");
            return builder.toString();
        }
        
    }
    public class test2 extends BaseResponseDTO
    {
        /** 
         * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
         * @since JDK 1.6 
         */ 
        private static final long serialVersionUID = 1L;
        private String name;
        private Long staffID;
        private int age;
        public test2() {
        }
        public test2(String name, Long staffid, int age)
        {
            this.name = name;
            this.staffID = staffid;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Long getStaffID() {
            return staffID;
        }
        public void setStaffID(Long staffID) {
            this.staffID = staffID;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        /** 
         * TODO 简单描述该方法的实现功能（可选）. 
         * @see java.lang.Object#toString() 
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("test2 [name=");
            builder.append(name);
            builder.append(", staffID=");
            builder.append(staffID);
            builder.append(", age=");
            builder.append(age);
            builder.append("]");
            return builder.toString();
        }
        
    }
    
}

