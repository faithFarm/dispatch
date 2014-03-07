package org.dispatch.faithfarm.domain;

public class IpPermission implements java.io.Serializable {
	
	private Long ipId;
	private String ipAddress;
	private String description;
	
	public IpPermission() { }
	
	public IpPermission(String ipAddress, String description) {
		this.ipAddress=ipAddress;
		this.description=description;
	}
	public Long getIpId() {
		return ipId;
	}
	public void setIpId(Long ipId) {
		this.ipId = ipId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
