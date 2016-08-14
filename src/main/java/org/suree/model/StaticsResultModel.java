package org.suree.model;

import java.util.Map;

/**
 * Created by Sure on 8/6/16.
 */
public class StaticsResultModel {

    private Map<Integer, Double> yakusRate;
    private Double riichiRate;
    private Double fuuruRate;
    private Double agariRate;
    private Double jyuRate;
    private Double avAgariPoints;
    private Double avjyuPoints;
    private Double ryukyokuRate;
    private String userName;
    private Double avRank;

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
}

