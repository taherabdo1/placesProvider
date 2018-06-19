package com.jimdo.places.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jimdo.places.clients.reponseEntities.GoogleResponse;
import com.jimdo.places.controler.requestModel.PlaceTypeEnum;
import com.jimdo.places.controler.requestModel.ProviderEnum;
import com.jimdo.places.model.PlaceEntity;
import com.jimdo.places.service.PlacesService;

@RestController
@Validated
@RequestMapping("/v1/places")
public class PlacesController {

	@Autowired
	PlacesService placesService;
	
	@RequestMapping(produces = "application/json", method = RequestMethod.GET)
	public List<PlaceEntity> getPlaces(@RequestParam(value = "location", required = true) final String location,
			@RequestParam(value = "raduis", required = true) final int radius,
			@RequestParam(value = "type", required = false) final PlaceTypeEnum type,
			@RequestParam(value = "keyword", required = false) final String keyword,
			@RequestParam(value = "name", required = false) final String name,@RequestParam(value = "providers", required = true) final List<String> providers) {
//			List<ProviderEnum> providers = new ArrayList<>();
//			providers.add(ProviderEnum.Google_Places);
		return placesService.getPlaces(providers, location, radius, type, keyword, name);
	}

}
