package DataObject;

public class BookTicket {
	
	private String departDate;
    private String departFrom;
    private String arriveAt;
    private String seatType;
    private String ticketAmount;
    
	public String getDepartDate() {
		return departDate;
	}
	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}
	public String getDepartFrom() {
		return departFrom;
	}
	public void setDepartFrom(String departFrom) {
		this.departFrom = departFrom;
	}
	public String getArriveAt() {
		return arriveAt;
	}
	public void setArriveAt(String arriveAt) {
		this.arriveAt = arriveAt;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public String getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(String ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	
	public BookTicket() {
        // Khởi tạo với giá trị mặc định
        this.departDate = "";
        this.departFrom = "";
        this.arriveAt = "";
        this.seatType = "";
        this.ticketAmount = "1";
    }
    
    // Constructor với tất cả tham số
    public BookTicket(String departDate, String departFrom, String arriveAt, 
                        String seatType, String ticketAmount) {
        this.departDate = departDate;
        this.departFrom = departFrom;
        this.arriveAt = arriveAt;
        this.seatType = seatType;
        this.ticketAmount = ticketAmount;
    }

    public String getText() {
        return String.format("DepartDate: %s, DepartFrom: %s, ArriveAt: %s, SeatType: %s, TicketAmount: %s",
            getValueOrEmpty(departDate),
            getValueOrEmpty(departFrom),
            getValueOrEmpty(arriveAt),
            getValueOrEmpty(seatType),
            getValueOrEmpty(ticketAmount));
    }
    
    private String getValueOrEmpty(String value) {
        return value != null ? value : "";
    }
    
}
