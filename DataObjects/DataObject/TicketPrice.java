package DataObject;

public class TicketPrice {
    private String HS;  // Hard Seat
    private String SS;  // Soft Seat
    private String SSC; // Soft Seat with Air Conditioner
    private String HB;  // Hard Bed
    private String SB;  // Soft Bed
    private String SBC; // Soft Bed with Air Conditioner
    
    // Constructor mặc định
    public TicketPrice() {}
    
    // Constructor với tất cả giá
    public TicketPrice(String HS, String SS, String SSC, String HB, String SB, String SBC) {
        this.HS = HS;
        this.SS = SS;
        this.SSC = SSC;
        this.HB = HB;
        this.SB = SB;
        this.SBC = SBC;
    }
    
    // Getters and Setters
    public String getHS() {
        return HS;
    }
    
    public void setHS(String HS) {
        this.HS = HS;
    }
    
    public String getSS() {
        return SS;
    }
    
    public void setSS(String SS) {
        this.SS = SS;
    }
    
    public String getSSC() {
        return SSC;
    }
    
    public void setSSC(String SSC) {
        this.SSC = SSC;
    }
    
    public String getHB() {
        return HB;
    }
    
    public void setHB(String HB) {
        this.HB = HB;
    }
    
    public String getSB() {
        return SB;
    }
    
    public void setSB(String SB) {
        this.SB = SB;
    }
    
    public String getSBC() {
        return SBC;
    }
    
    public void setSBC(String SBC) {
        this.SBC = SBC;
    }
    
    public void setPriceByIndex(int index, String price) {
        switch (index) {
            case 0: HS = price; break;
            case 1: SS = price; break;
            case 2: SSC = price; break;
            case 3: HB = price; break;
            case 4: SB = price; break;
            case 5: SBC = price; break;
            default: break;
        }
    }
    
    public String getText() {
        return String.format("HS: %s, SS: %s, SSC: %s, HB: %s, SB: %s, SBC: %s",
            getValueOrEmpty(HS),
            getValueOrEmpty(SS),
            getValueOrEmpty(SSC),
            getValueOrEmpty(HB),
            getValueOrEmpty(SB),
            getValueOrEmpty(SBC));
    }
    
    private String getValueOrEmpty(String value) {
        return value != null ? value : "";
    }
}