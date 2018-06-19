package com.jimdo.places.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jimdo.places.clients.GooglePlacesClient;
import com.jimdo.places.controler.requestModel.PlaceTypeEnum;
import com.jimdo.places.controler.requestModel.ProviderEnum;
import com.jimdo.places.model.PlaceEntity;

@Service
public class PlacesServiceImpl implements PlacesService{

	@Autowired
	GooglePlacesClient googlePlacesClient;
	
	@Override
	public List<PlaceEntity> getPlaces(final List<String> providers, String location, int radius, PlaceTypeEnum type,
			String keyword, final String name) {
		List<PlaceEntity> result = new ArrayList<>();
		if(providers.stream().anyMatch(x -> x.equalsIgnoreCase(ProviderEnum.Google_Places.toString()))) {
			result.addAll(googlePlacesClient.getPlaces(location, radius, type, keyword, name));
		}else if(providers.stream().anyMatch(x -> x.equalsIgnoreCase(ProviderEnum.Yelp.toString()))) {
			//call Yelp client
		}else if(providers.stream().anyMatch(x -> x.equalsIgnoreCase(ProviderEnum.Foursquare.toString()))) {
			//call Foursquare client
		}
		return result;
		
	}

}
