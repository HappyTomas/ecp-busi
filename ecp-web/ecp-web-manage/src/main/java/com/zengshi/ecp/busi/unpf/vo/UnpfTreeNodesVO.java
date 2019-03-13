package com.zengshi.ecp.busi.unpf.vo;

import java.io.Serializable;

public class UnpfTreeNodesVO  implements Serializable{

    private static final long serialVersionUID = 1L;
    private UnpfCategoryTreeNodeVO leftNode;
    private UnpfCategoryTreeNodeVO rightNode;
    public UnpfCategoryTreeNodeVO getLeftNode() {
        return leftNode;
    }
    public void setLeftNode(UnpfCategoryTreeNodeVO leftNode) {
        this.leftNode = leftNode;
    }
    public UnpfCategoryTreeNodeVO getRightNode() {
        return rightNode;
    }
    public void setRightNode(UnpfCategoryTreeNodeVO rightNode) {
        this.rightNode = rightNode;
    }
    
}

