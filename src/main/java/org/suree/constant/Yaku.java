package org.suree.constant;

/**
 * Created by Sure on 7/18/16.
 */
public enum Yaku {

    tsumo           (0, "门前清自摸和"),
    riichi          (1, "立直"),
    ippatsu         (2, "一发"),
    chankan         (3, "抢杠"),
    rinshan         (4, "岭上开花"),
    haitei          (5, "海底捞月"),
    houtei          (6, "河底捞鱼"),
    pinfu           (7, "平和"),
    tanyao          (8, "断幺"),
    ippeiko         (9, "一️杯口"),
    zifengdong      (10, "自风东"),
    zifengnan       (11, "自风南"),
    zifengxi        (12, "自风西"),
    zifengbei       (13, "自风北"),
    changfengdong   (14, "场风东"),
    changfengnan    (15, "场风南"),
    changfengxi     (16, "场风西"),
    changfengbei    (17, "场风北"),
    yipaibai        (18, "役牌白"),
    yipaifa         (19, "役牌发"),
    yipaizhong      (20, "役牌中"),
    daburi          (21, "双立直"),
    chiitoi         (22, "七对子"),
    chantai         (23, "混全带幺九"),
    itsuu           (24, "一气通贯"),
    sanshokudoujin  (25, "三色同顺"),
    sanshokudoukou  (26, "三色同刻"),
    sankantsu       (27, "三杠子"),
    toitoi          (28, "对对和"),
    sanankou        (29, "三暗刻"),
    shousangen      (30, "小三元"),
    honrouto        (31, "混老头"),
    ryanpeikou      (32, "两杯口"),
    junchan         (33, "纯全带幺九"),
    honitsu         (34, "混一色"),
    chinitsu        (35, "清一色"),
    renhou          (36, "人和"),
    tenhou          (37, "天和"),
    chihou          (38, "地和"),
    daisangen       (39, "大三元"),
    suuankou        (40, "四暗刻"),
    suuankoudanki   (41, "四暗刻单骑"),
    tsuiisou        (42, "字一色"),
    tyuuiisou       (43, "绿一色"),
    chinrouto       (44, "清老头"),
    chuurenpooto    (45, "九莲宝灯"),
    chuichuurenpooto(46, "纯正九莲宝灯"),
    kokushi         (47, "国士无双"),
    kokushijiusan   (48, "国士无双十三面"),
    daisuushi       (49, "大四喜"),
    shousuushi      (50, "小四喜"),
    suukantsu       (51, "四杠子"),
    dora            (52, "宝牌"),
    uradora         (53, "里宝牌"),
    akadora         (54, "赤宝牌");


    private int code;
    private String desc;

    public static Yaku getByCode(int code) {
        for (Yaku type: Yaku.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    private Yaku(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
