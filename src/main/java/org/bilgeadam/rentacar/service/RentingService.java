package org.bilgeadam.rentacar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bilgeadam.rentacar.model.Renting;
import org.bilgeadam.rentacar.model.UserHistory;
import org.bilgeadam.rentacar.repository.CarRepository;
import org.bilgeadam.rentacar.repository.RentingRepository;
import org.bilgeadam.rentacar.repository.UserHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class RentingService {

    private final RentingRepository rentingRepository;
    private final CarRepository carRepository;
    private final UserHistoryRepository historyRepository;

    public void doRent(Renting renting){

        renting.setCreatedDate(LocalDateTime.now());
        Renting createdRent = rentingRepository.save(renting);
        saveUserHistory(createdRent);
    }

    public void saveUserHistory(Renting renting){
        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(renting.getUserId());
        userHistory.setRentId(renting.getId());
        historyRepository.save(userHistory);
    }
}
