/**
 * 2015/4/24 11:35:32 Caven created.
 * Generated by Caven.CodeBuilder (funiJava.entity 1.0).
 */

package com.dyb.platforms.fixfunds.services.utils.core.configureations;

/**
 * Created by Caven on 2015/04/24.
 */
public class BusinessPaperDefined implements java.io.Serializable {

    // 序列化版本
    private static final long serialVersionUID = -5264198870986792960L;

    // 要件名称 [主键]
    private String paperName;
    // 是否必须
    private boolean must;

    /**
     * 获取要件名称 [主键]
     *
     * @return 要件名称
     */
    public String getPaperName() {
        return paperName;
    }

    /**
     * 设置要件名称 [主键]
     *
     * @param paperName 要件名称
     */
    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    /**
     * 获取是否必须
     *
     * @return 是否必须
     */
    public boolean isMust() {
        return must;
    }

    /**
     * 设置是否必须
     *
     * @param must 是否必须
     */
    public void setMust(boolean must) {
        this.must = must;
    }

}
