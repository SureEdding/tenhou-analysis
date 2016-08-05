package org.suree.model;

import org.suree.constant.Ryukyoku;
import org.suree.constant.Yaku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sure on 8/4/16.
 */
public class Round {


    /**
     * 庄
     */
    private Integer oya;

    /**
     * 立直者
     */
    private List<Integer> reach = new ArrayList<Integer>();

    /**
     * 胡牌者
     */
    private List<Integer> who = new ArrayList<Integer>();

    /**
     * 胡牌的放铳者(-1代表自摸)
     */
    private Integer fromWho;

    /**
     * 点数变化(正为赢点,负为输点)
     */
    private Map<Integer,Integer> tens = new HashMap<Integer, Integer>();

    /**
     * 初始点数
     */
    private Map<Integer,Integer> initTens = new HashMap<Integer, Integer>();

    /**
     * 是否流局
     */
    private Boolean isRyukyoku;

    /**
     * 流局类型
     */
    private Ryukyoku ryukyokuType;

    /**
     * 是否和牌
     */
    private Boolean isAgari;

    /**
     * 役
     */
    private List<List<Yaku>> Yakus = new ArrayList<List<Yaku>>();

    /**
     * 本场棒
     */
    private Integer combo;

    /**
     * 立直棒
     */
    private Integer riichi;

    /**
     * 副露
     */
    private List<Fuuru> fuurus = new ArrayList<Fuuru>();


    //TODO 摸牌,切牌,符数的记录


    public Integer getOya() {
        return oya;
    }

    public void setOya(Integer oya) {
        this.oya = oya;
    }

    public List<Integer> getReach() {
        return reach;
    }

    public void setReach(List<Integer> reach) {
        this.reach = reach;
    }

    public List<Integer> getWho() {
        return who;
    }

    public void setWho(List<Integer> who) {
        this.who = who;
    }

    public Integer getFromWho() {
        return fromWho;
    }

    public void setFromWho(Integer fromWho) {
        this.fromWho = fromWho;
    }

    public Map<Integer, Integer> getTens() {
        return tens;
    }

    public void setTens(Map<Integer, Integer> tens) {
        this.tens = tens;
    }

    public Boolean getRyukyoku() {
        return isRyukyoku;
    }

    public void setRyukyoku(Boolean ryukyoku) {
        isRyukyoku = ryukyoku;
    }

    public Ryukyoku getRyukyokuType() {
        return ryukyokuType;
    }

    public void setRyukyokuType(Ryukyoku ryukyokuType) {
        this.ryukyokuType = ryukyokuType;
    }

    public List<List<Yaku>> getYakus() {
        return Yakus;
    }

    public void setYakus(List<List<Yaku>> yakus) {
        Yakus = yakus;
    }

    public Integer getCombo() {
        return combo;
    }

    public void setCombo(Integer combo) {
        this.combo = combo;
    }

    public Integer getRiichi() {
        return riichi;
    }

    public void setRiichi(Integer riichi) {
        this.riichi = riichi;
    }

    public List<Fuuru> getFuurus() {
        return fuurus;
    }

    public void setFuurus(List<Fuuru> fuurus) {
        this.fuurus = fuurus;
    }

    public Map<Integer, Integer> getInitTens() {
        return initTens;
    }

    public void setInitTens(Map<Integer, Integer> initTens) {
        this.initTens = initTens;
    }

    public Boolean getAgari() {
        return isAgari;
    }

    public void setAgari(Boolean agari) {
        isAgari = agari;
    }
}
