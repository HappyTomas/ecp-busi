package com.zengshi.ecp.search.dubbo.search.score.valuesource;

/**
 * Created by HDF on 2016/12/15.
 */
public class DateValueSourceParserParam {

    //是否启用时间衰减系数
    private boolean ifDemoteRangeDay=true;

    //降级权重因子
    private float demoteboost = 0.95f;
    private float demoteboostday = demoteboost;
    private float demoteboostweek = demoteboost;
    private float demoteboostmonth = demoteboost;
    private float demoteboostyear = demoteboost;

    //对未来时间是否启用时间衰减系数
    private boolean ifDemoteRangeFuture=false;

    //未来时间的权重因子（默认时间是未来则因素值设置为最小）
    private float factorFuture =Float.MIN_VALUE;

    //是否启用时间提升系数(24小时内)//慎用
    private boolean ifRemoteRangeHour=true;

    //提升权重因子
    private float remoteBoost = 1.03f;

    public float getDemoteboostday() {
        return demoteboostday;
    }

    public void setDemoteboostday(float demoteboostday) {
        this.demoteboostday = demoteboostday;
    }

    public float getDemoteboostmonth() {
        return demoteboostmonth;
    }

    public void setDemoteboostmonth(float demoteboostmonth) {
        this.demoteboostmonth = demoteboostmonth;
    }

    public float getDemoteboostweek() {
        return demoteboostweek;
    }

    public void setDemoteboostweek(float demoteboostweek) {
        this.demoteboostweek = demoteboostweek;
    }

    public float getDemoteboostyear() {
        return demoteboostyear;
    }

    public void setDemoteboostyear(float demoteboostyear) {
        this.demoteboostyear = demoteboostyear;
    }

    public boolean isIfDemoteRangeDay() {
        return ifDemoteRangeDay;
    }

    public void setIfDemoteRangeDay(boolean ifDemoteRangeDay) {
        this.ifDemoteRangeDay = ifDemoteRangeDay;
    }

    public boolean isIfRemoteRangeHour() {
        return ifRemoteRangeHour;
    }

    public void setIfRemoteRangeHour(boolean ifRemoteRangeHour) {
        this.ifRemoteRangeHour = ifRemoteRangeHour;
    }

    public float getRemoteBoost() {
        return remoteBoost;
    }

    public void setRemoteBoost(float remoteBoost) {
        this.remoteBoost = remoteBoost;
    }

    public float getFactorFuture() {
        return factorFuture;
    }

    public void setFactorFuture(float factorFuture) {
        this.factorFuture = factorFuture;
    }

    public boolean isIfDemoteRangeFuture() {
        return ifDemoteRangeFuture;
    }

    public void setIfDemoteRangeFuture(boolean ifDemoteRangeFuture) {
        this.ifDemoteRangeFuture = ifDemoteRangeFuture;
    }

    @Override
    public String toString() {
        return "DateValueSourceParserParam{" +
                "demoteboost=" + demoteboost +
                ", ifDemoteRangeDay=" + ifDemoteRangeDay +
                ", demoteboostday=" + demoteboostday +
                ", demoteboostweek=" + demoteboostweek +
                ", demoteboostmonth=" + demoteboostmonth +
                ", demoteboostyear=" + demoteboostyear +
                ", ifDemoteRangeFuture=" + ifDemoteRangeFuture +
                ", factorFuture=" + factorFuture +
                ", ifRemoteRangeHour=" + ifRemoteRangeHour +
                ", remoteBoost=" + remoteBoost +
                '}';
    }
}
