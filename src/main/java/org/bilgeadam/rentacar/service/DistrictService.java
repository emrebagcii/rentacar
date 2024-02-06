package org.bilgeadam.rentacar.service;

import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.model.City;
import org.bilgeadam.rentacar.model.District;
import org.bilgeadam.rentacar.repository.CityRepository;
import org.bilgeadam.rentacar.repository.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final CityRepository cityRepository;

    public List<City> getCityList(){
        return cityRepository.findAll();
    }


    public List<District> getDistrictListByCityId(Long cityId){
        return districtRepository.findByCityId(cityId);
    }
}
