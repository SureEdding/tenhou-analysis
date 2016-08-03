package org.suree.constant;

/**
 * Created by Sure on 7/23/16.
 */
public enum Mode {

    fourBanDong(0x07, "四般东"),
    fourBanNan(0x0f, "四般南"),
    fourBanDongShi(0x03, "四般东食"),
    fourBanNanShi(0x0b, "四般南食"),
    fourBanDongShiChi(0x01,"四般东食赤"),
    fourBanNanShiChi(0x09, "四般南食赤"),
    fourBanDongShiChiSu(0x41,"四般东食赤速"),
    fourBanNanShiChiSu(0x49, "四般南食赤速"),
    sanBanDongShiChi(0x11, "三般东食赤"),
    sanBanNanChi(0x19, "三般南食赤"),
    sanBanDongShiChiSu(0x51, "三般东食赤速"),
    sanBanNanShiChiSu(0x59, "三般南食赤速"),

    fourShangDong(0x87, "四上东"),
    fourShangNan(0x8f, "四上南"),
    fourShangDongShi(0x83, "四上东食"),
    fourShangNanShi(0x8b, "四上南食"),
    fourShangDongShiChi(0x81,"四上东食赤"),
    fourShangNanShiChi(0x89, "四上南食赤"),
    fourShangDongShiChiSu(0xc1,"四上东食赤速"),
    fourShangNanShiChiSu(0xc9, "四上南食赤速"),
    sanShangDongShiChi(0x91, "三上东食赤"),
    sanShangNanChi(0x99, "三上南食赤"),
    sanShangDongShiChiSu(0xd1, "三上东食赤速"),
    sanShangNanShiChiSu(0xd9, "三上南食赤速"),

    fourTeDong(0x27, "四特东"),
    fourTeNan(0x2f, "四特南"),
    fourTeDongShi(0x23, "四特东食"),
    fourTeNanShi(0x2b, "四特南食"),
    fourTeDongShiChi(0x21,"四特东食赤"),
    fourTeNanShiChi(0x29, "四特南食赤"),
    fourTeDongShiChiSu(0x61,"四特东食赤速"),
    fourTeNanShiChiSu(0x69, "四特南食赤速"),
    sanTeDongShiChi(0x31, "三特东食赤"),
    sanTeNanChi(0x39, "三特南食赤"),
    sanTeDongShiChiSu(0x71, "三特东食赤速"),
    sanTeNanShiChiSu(0x79, "三特南食赤速"),

    fourFengDong(0xa7, "四凤东"),
    fourFengNan(0xaf, "四凤南"),
    fourFengDongShi(0xa3, "四凤东食"),
    fourFengNanShi(0xab, "四凤南食"),
    fourFengDongShiChi(0xa1,"四凤东食赤"),
    fourFengNanShiChi(0xa9, "四凤南食赤"),
    fourFengDongShiChiSu(0xe1,"四凤东食赤速"),
    fourFengNanShiChiSu(0xe9, "四凤南食赤速"),
    sanFengDongShiChi(0xb1, "三凤东食赤"),
    sanFengNanChi(0xb9, "三凤南食赤"),
    sanFengDongShiChiSu(0xf1, "三凤东食赤速"),
    sanFengNanShiChiSu(0xf9, "三凤南食赤速");

    private int code;
    private String desc;

    public static Mode getByCode(int code) {
        for (Mode type: Mode.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    private Mode(int code, String desc) {
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
