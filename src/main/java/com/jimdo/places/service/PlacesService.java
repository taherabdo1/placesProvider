package com.jimdo.places.service;

import java.util.List;

import com.jimdo.places.controler.requestModel.PlaceTypeEnum;
import com.jimdo.places.controler.requestModel.ProviderEnum;
import com.jimdo.places.model.PlaceEntity;

public interface PlacesService {

	List<PlaceEntity> getPlaces(final List<String> providers, final String location,final int radius,final PlaceTypeEnum type, final String keyword, final String name);
}
