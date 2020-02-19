package dto;

public class RegionData {

	private int regionId;
	private String regionName;
	private int countryId;

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	@Override
	public String toString() {
		return "RegionData [regionId = " + regionId + ", regionName = " + regionName + ", countryId = " + countryId
				+ "]";
	}
}