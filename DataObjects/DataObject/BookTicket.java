package DataObject;

import Constant.Province;
import Constant.SeatType;

public class BookTicket {
	
	private String departDate;
    private Province departFrom;
    private Province arriveAt;
    private SeatType seatType;
    private String ticketAmount;
    
	public String getDepartDate() {
		return departDate;
	}
	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}
	public Province getDepartFrom() {
		return departFrom;
	}
	public void setDepartFrom(Province departFrom) {
		this.departFrom = departFrom;
	}
	public Province getArriveAt() {
		return arriveAt;
	}
	public void setArriveAt(Province arriveAt) {
		this.arriveAt = arriveAt;
	}
	public SeatType getSeatType() {
		return seatType;
	}
	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}
	public String getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(String ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	
	public BookTicket() {
        this.departDate = "";
        this.departFrom = Province.SAI_GON;
        this.arriveAt = Province.PHAN_THIET;
        this.seatType = SeatType.HARD_SEAT;
        this.ticketAmount = "1";
    }
    
    public BookTicket(String departDate, Province departFrom, Province arriveAt, 
    		SeatType seatType, String ticketAmount) {
        this.departDate = departDate;
        this.departFrom = departFrom;
        this.arriveAt = arriveAt;
        this.seatType = seatType;
        this.ticketAmount = ticketAmount;
    }

    public String getText() {
        return String.format("DepartDate: %s, DepartFrom: %s, ArriveAt: %s, SeatType: %s, TicketAmount: %s",
            getValueOrEmpty(departDate.toString()),
            getValueOrEmpty(departFrom.toString()),
            getValueOrEmpty(arriveAt.toString()),
            getValueOrEmpty(seatType.toString()),
            getValueOrEmpty(ticketAmount));
    }
    
    private String getValueOrEmpty(String value) {
        return value != null ? value : "";
    }
    
}
