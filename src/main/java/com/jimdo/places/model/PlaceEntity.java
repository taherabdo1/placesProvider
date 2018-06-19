package com.jimdo.places.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceEntity {

	private String id;
	private String provider;
	private String name;
	private String description;
	private LocationEntity location;// (lat, lng) (if applicable)
	private String address; // (if applicable)
	private String uri; // of the place where more details are available
}
