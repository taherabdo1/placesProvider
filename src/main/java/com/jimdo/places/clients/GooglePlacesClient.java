package com.jimdo.places.clients;

import java.util.List;

import com.jimdo.places.controler.requestModel.PlaceTypeEnum;
import com.jimdo.places.model.PlaceEntity;

public interface GooglePlacesClient {

	List<PlaceEntity> getPlaces(final String location, final int radius, final PlaceTypeEnum type, final String keyword,  final String name);

}
