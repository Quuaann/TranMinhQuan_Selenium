package Constant;

public enum Province {
    SAI_GON("Sài Gòn"),
    PHAN_THIET("Phan Thiết"),
    NHA_TRANG("Nha Trang"),
    DA_NANG("Đà Nẵng"),
    HUE("Huế"),
    QUANG_NGAI("Quảng Ngãi");

    private final String vietnameseName;

    Province(String vietnameseName) {
        this.vietnameseName = vietnameseName;
    }

    public String getVietnameseName() {
        return vietnameseName;
    }
    
    public static Province fromVietnameseName(String vietnameseName) {
        for (Province province : values()) {
            if (province.getVietnameseName().equalsIgnoreCase(vietnameseName.trim())) {
                return province;
            }
        }
        throw new IllegalArgumentException("Unknown province: " + vietnameseName);
    }

    @Override
    public String toString() {
        return vietnameseName;
    }
}
