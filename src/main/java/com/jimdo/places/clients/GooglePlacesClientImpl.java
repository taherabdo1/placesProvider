package com.jimdo.places.clients;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.jimdo.places.clients.reponseEntities.GoogleResponse;
import com.jimdo.places.clients.reponseEntities.Result;
import com.jimdo.places.controler.requestModel.PlaceTypeEnum;
import com.jimdo.places.controler.requestModel.ProviderEnum;
import com.jimdo.places.model.LocationEntity;
import com.jimdo.places.model.PlaceEntity;

@Component
public class GooglePlacesClientImpl implements GooglePlacesClient {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${google.places.api.key}")
	String apiKey;

	@Value("${google.places.api.url}")
	String url;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public List<PlaceEntity> getPlaces(String location, int radius, PlaceTypeEnum type, String keyword,
			final String name) {
		List<PlaceEntity> response = new ArrayList<>();
		PlaceEntity entity = null;
		LocationEntity tempLocation = null;

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("location", location)
				.queryParam("radius", radius + "").queryParam("key", apiKey);

		if (type != null)
			builder.queryParam("type", type.restaurant.toString());
		if (keyword != "" && keyword != null && !keyword.isEmpty())
			builder.queryParam("Keyword", keyword);
		if (name != "" && name != null && !name.isEmpty())
			builder.queryParam("name", name);
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);

		HttpEntity<GoogleResponse> googleResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
				httpEntity, GoogleResponse.class);

		for (Result record : googleResponse.getBody().getResults()) {
			entity = new PlaceEntity();
			entity.setId(record.getPlaceId());
			entity.setName(record.getName());
			entity.setDescription(record.getVicinity());
			entity.setAddress(record.getVicinity());
			entity.setProvider(ProviderEnum.Google_Places.toString());
			entity.setUri(record.getPhotos().get(0).getHtmlAttributions().get(0));
			tempLocation = new LocationEntity();
			tempLocation.setLatitude(record.getGeometry().getLocation().getLat());
			tempLocation.setLongtude(record.getGeometry().getLocation().getLng());
			entity.setLocation(tempLocation);
			response.add(entity);
		}
		return response;
	}

}
