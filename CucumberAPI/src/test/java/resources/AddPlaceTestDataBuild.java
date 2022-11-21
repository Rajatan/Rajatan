package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class AddPlaceTestDataBuild {
	
	public AddPlace addPlaceBuild(String name, String langauge, String address) {
		AddPlace addPlace = new AddPlace();

//		set all the setter methods to build the Json file
		addPlace.setAccuracy(44);
		addPlace.setAddress(address);
		addPlace.setLanguage(langauge);
		addPlace.setName(name);
		addPlace.setPhone_number("(+91) 890 765 4321");

		List<String> type = new ArrayList<String>();
		type.add("Malprabha river");
		type.add("Forest");
		type.add("Wild animals can be seen here");

		addPlace.setTypes(type);

		Location location = new Location();
		location.setLat(-32.2564136);
		location.setLng(33.365248);

		addPlace.setLocation(location);
		
		return addPlace;
	}

}
