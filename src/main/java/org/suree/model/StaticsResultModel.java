package org.suree.model;

import java.util.Map;

/**
 * Created by Sure on 8/6/16.
 */
public class StaticsResultModel {


    /**
     * 役的概率
     */
    private Map<Integer, Double> yakusRate;

    /**
     * 立直率
     */
    private Double riichiRate;

    /**
     * 副露率
     */
    private Double fuuruRate;

    /**
     * 和了率
     */
    private Double agariRate;

    /**
     * 放铳率
     */
    private Double jyuRate;

    /**
     * 平均和了点
     */
    private Double avAgariPoints;

    /**
     * 平均放铳点
     */
    private Double avjyuPoints;

    /**
     * 流局率
     */
    private Double ryukyokuRate;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 平均顺位
     */
    private Double avRank;

    /**
     * 总对局数(有效牌谱的数量)
     */
    private Integer taikyokuCount;

    /**
     * 局数(ps:一个半庄8局(假设))
     */
    private Integer roundCount;

    public Map<Integer, Double> getYakusRate() {
        return yakusRate;
    }

    public void setYakusRate(Map<Integer, Double> yakusRate) {
        this.yakusRate = yakusRate;
    }

    public Double getRiichiRate() {
        return riichiRate;
    }

    public void setRiichiRate(Double riichiRate) {
        this.riichiRate = riichiRate;
    }

    public Double getFuuruRate() {
        return fuuruRate;
    }

    public void setFuuruRate(Double fuuruRate) {
        this.fuuruRate = fuuruRate;
    }

    public Double getAgariRate() {
        return agariRate;
    }

    public void setAgariRate(Double agariRate) {
        this.agariRate = agariRate;
    }

    public Double getJyuRate() {
        return jyuRate;
    }

    public void setJyuRate(Double jyuRate) {
        this.jyuRate = jyuRate;
    }

    public Double getAvAgariPoints() {
        return avAgariPoints;
    }

    public void setAvAgariPoints(Double avAgariPoints) {
        this.avAgariPoints = avAgariPoints;
    }

    public Double getAvjyuPoints() {
        return avjyuPoints;
    }

    public void setAvjyuPoints(Double avjyuPoints) {
        this.avjyuPoints = avjyuPoints;
    }

    public Double getRyukyokuRate() {
        return ryukyokuRate;
    }

    public void setRyukyokuRate(Double ryukyokuRate) {
        this.ryukyokuRate = ryukyokuRate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getAvRank() {
        return avRank;
    }

    public void setAvRank(Double avRank) {
        this.avRank = avRank;
    }

    public Integer getTaikyokuCount() {
        return taikyokuCount;
    }

    public void setTaikyokuCount(Integer taikyokuCount) {
        this.taikyokuCount = taikyokuCount;
    }

    public Integer getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(Integer roundCount) {
        this.roundCount = roundCount;
    }
}

