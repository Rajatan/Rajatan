package resources;


// This is gone be the resources for all the HTTP methods
public enum APIresources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;
	
	APIresources(String reource){
		this.resource=reource;
	}
	
	
	
	public String getResource() {
		return resource;
	}
	

}
