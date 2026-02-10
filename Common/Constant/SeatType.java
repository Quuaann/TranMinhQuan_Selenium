package Constant;

public enum SeatType {
    // Định nghĩa các loại ghế với tên hiển thị
    HARD_SEAT("Hard seat", "HS"),
    SOFT_SEAT("Soft seat", "SS"),
    SOFT_SEAT_WITH_AC("Soft seat with air conditioner", "SSC"),
    HARD_BED("Hard bed", "HB"),
    SOFT_BED("Soft bed", "SB"),
    SOFT_BED_WITH_AC("Soft bed with air conditioner", "SBC");
    
    private final String displayName;
    private final String abbreviation;
    
    // Constructor
    SeatType(String displayName, String abbreviation) {
        this.displayName = displayName;
        this.abbreviation = abbreviation;
    }
    
    // Getters
    public String getDisplayName() {
        return displayName;
    }
    
    public String getAbbreviation() {
        return abbreviation;
    }
    
    // Tìm SeatType theo display name
    public static SeatType fromDisplayName(String displayName) {
        for (SeatType seatType : values()) {
            if (seatType.getDisplayName().equalsIgnoreCase(displayName)) {
                return seatType;
            }
        }
        throw new IllegalArgumentException("Unknown seat type: " + displayName);
    }
    
    // Tìm SeatType theo abbreviation
    public static SeatType fromAbbreviation(String abbreviation) {
        for (SeatType seatType : values()) {
            if (seatType.getAbbreviation().equalsIgnoreCase(abbreviation)) {
                return seatType;
            }
        }
        throw new IllegalArgumentException("Unknown seat type abbreviation: " + abbreviation);
    }
    
    // Kiểm tra xem string có phải là seat type hợp lệ không
    public static boolean isValidSeatType(String seatType) {
        try {
            fromDisplayName(seatType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    // Lấy tất cả display names
    public static String[] getAllDisplayNames() {
        SeatType[] values = values();
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].getDisplayName();
        }
        return names;
    }
    
    // Lấy tất cả abbreviations
    public static String[] getAllAbbreviations() {
        SeatType[] values = values();
        String[] abbrs = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            abbrs[i] = values[i].getAbbreviation();
        }
        return abbrs;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}