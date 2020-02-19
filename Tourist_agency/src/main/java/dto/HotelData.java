package dto;

public class HotelData {

	private int hotelId;
	private String hotelName;
	private int regionId;

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	@Override
	public String toString() {
		return "HotelData [hotelId = " + hotelId + ", hotelName = " + hotelName + ", regionId = " + regionId + "]";
	}
}
