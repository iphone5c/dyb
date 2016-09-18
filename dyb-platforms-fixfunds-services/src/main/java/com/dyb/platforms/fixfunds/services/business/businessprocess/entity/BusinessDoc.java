/**
 * 2016/9/18 19:24:04 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.entity 1.0).
 */

package com.dyb.platforms.fixfunds.services.business.businessprocess.entity;

import java.util.List;

/**
 * 业务单
 * Created by lenovo on 2016/09/18.
 */
public class BusinessDoc implements java.io.Serializable {

    // 序列化版本
    private static final long serialVersionUID = -4586535298538643968L;

    // 业务单编号 [主键]
    private String docCode;
    // 业务单头
    private BusinessDocHead head;
    // 业务单主体
    private Object body;
    // 要件列表
    private List<BusinessPaper> papers;

    /**
     * 获取业务单编号 [主键]
     *
     * @return 业务单编号
     */
    public String getDocCode() {
        return docCode;
    }

    /**
     * 设置业务单编号 [主键]
     *
     * @param docCode 业务单编号
     */
    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    /**
     * 获取业务单头
     *
     * @return 业务单头
     */
    public BusinessDocHead getHead() {
        return head;
    }

    /**
     * 设置业务单头
     *
     * @param head 业务单头
     */
    public void setHead(BusinessDocHead head) {
        this.head = head;
    }

    /**
     * 获取业务单主体
     *
     * @return 业务单主体
     */
    public Object getBody() {
        return body;
    }

    /**
     * 设置业务单主体
     *
     * @param body 业务单主体
     */
    public void setBody(Object body) {
        this.body = body;
    }

    /**
     * 获取要件列表
     *
     * @return 要件列表
     */
    public List<BusinessPaper> getPapers() {
        return papers;
    }

    /**
     * 设置要件列表
     *
     * @param papers 要件列表
     */
    public void setPapers(List<BusinessPaper> papers) {
        this.papers = papers;
    }

}
