package com.ahmetburak.codexist.casestudy.controller;
import com.ahmetburak.codexist.casestudy.model.Search;
import com.ahmetburak.codexist.casestudy.repository.SearchRepository;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class SearchController {
    @Autowired
    SearchRepository searchRepository;

    @GetMapping("/getNearbyPlaces")
    public ResponseEntity<Search> getNearbyPlacacesByParams(@RequestParam String longitude, @RequestParam String latitude, @RequestParam String radius ){
        try {
            Optional<Search> search = searchRepository.findByLatitudeAndLongitudeAndRadius(longitude, latitude, radius);
            if (search.isEmpty()){
                // google api usage
                Search result = new Search();
                OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
                MediaType mediaType = MediaType.parse("text/plain");
                //RequestBody body = RequestBody.create(mediaType, "");
                Request request = new Request.Builder()
                    .url("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+longitude+","+latitude+"&radius="+radius+"&type=restaurant&key=AIzaSyDEO88Kp_sPinbcTiJXrmmb089oLxtqC0E")
                    .build();
                //.method("GET", body)
                Response response = client.newCall(request).execute();
                result.setLatitude(latitude);
                result.setLongitude(longitude);
                result.setData(response.body().string());
                result.setRadius(radius);
                searchRepository.save(result);
                return new ResponseEntity<>(result, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(search.get(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }
}
