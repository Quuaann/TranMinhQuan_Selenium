package Constant;

public enum Province {
    SAI_GON("Sài Gòn"),
    PHAN_THIET("Phan Thiết"),
    NHA_TRANG("Nha Trang"),
    DA_NANG("Đà Nẵng"),
    HUE("Huế"),
    QUANG_NGAI("Quảng Ngãi");

    private final String name;

    Province(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public static Province fromVietnameseName(String name) {
        for (Province province : values()) {
            if (province.getName().equalsIgnoreCase(name.trim())) {
                return province;
            }
        }
        throw new IllegalArgumentException("Unknown province: " + name);
    }

    @Override
    public String toString() {
        return name;
    }
}
