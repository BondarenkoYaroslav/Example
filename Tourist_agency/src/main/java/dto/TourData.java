package dto;

import java.math.BigDecimal;
import java.sql.Date;

public class TourData {

	private int tourId;
	private String tourName;
	private Date tourDeparture;
	private Date tourReturn;
	private BigDecimal tourPrice;

	public int getTourId() {
		return tourId;
	}

	public void setTourId(int tourId) {
		this.tourId = tourId;
	}

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public Date getTourDeparture() {
		return tourDeparture;
	}

	public void setTourDeparture(Date tourDeparture) {
		this.tourDeparture = tourDeparture;
	}

	public Date getTourReturn() {
		return tourReturn;
	}

	public void setTourReturn(Date tourReturn) {
		this.tourReturn = tourReturn;
	}

	public BigDecimal getTourPrice() {
		return tourPrice;
	}

	public void setTourPrice(BigDecimal tourPrice) {
		this.tourPrice = tourPrice;
	}

	@Override
	public String toString() {
		return "TourData [tourId = " + tourId + ", tourName = " + tourName + ", tourDeparture = " + tourDeparture
				+ ", tourReturn = " + tourReturn + ", tourPrice = " + tourPrice + "]";
	}
}
