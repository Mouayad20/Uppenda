package com.example.demo.Services;

import com.example.demo.Entities.InterestEntity;
import com.example.demo.Entities.PostEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Repositories.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InterestService {

    @Autowired
    private InterestRepository interestRepository;

    public void addInterest(PostEntity postEntity, UserEntity userEntity){
        Optional<InterestEntity> interest = interestRepository.findByTypeAndUser(postEntity.getType().getType(),userEntity);
        if(interest.isEmpty()){
            InterestEntity interestEntity = new InterestEntity();
            interestEntity.setType(postEntity.getType().getType());
            interestEntity.setTimes(1);
            interestEntity.setUser(userEntity);
            interestRepository.save(interestEntity);
        }
        else{
            interest.get().setTimes(interest.get().getTimes()+1);
            interestRepository.save(interest.get());
        }
    }
}
