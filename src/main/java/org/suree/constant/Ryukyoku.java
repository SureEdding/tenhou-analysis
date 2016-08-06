package org.suree.constant;

/**
 * Created by Sure on 8/4/16.
 */
public enum Ryukyoku {

    yao9           ("yao9", "九种九牌"),
    reach4          ("reach4", "四家立直"),
    ron3         ("ron3", "三家和了"),
    kan4         ("kan4", "四杠散了"),
    kaze4         ("kaze4", "四风连打"),
    nm          ("nm", "流局满贯"),
    normal      ("normal", "流局");

    private String code;
    private String desc;


    public static Ryukyoku getByCode(String code) {
        for (Ryukyoku type: Ryukyoku.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    private Ryukyoku(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
