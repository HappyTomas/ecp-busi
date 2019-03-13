package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

public class Gds012Req extends IBody {
    
private int pageSize;

private int pageNumber;


//0表示未评价 1表示已评价
private String status;

public int getPageSize() {
	return pageSize;
}

public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}

public int getPageNumber() {
	return pageNumber;
}

public void setPageNumber(int pageNumber) {
	this.pageNumber = pageNumber;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}


}

