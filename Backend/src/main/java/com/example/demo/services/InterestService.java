package com.example.demo.services;

import com.example.demo.Entities.InterstEntity;
import com.example.demo.Entities.PostEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Repositories.InterstRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InterestService {
    @Autowired
    InterstRepository interstRepository;

    public void addInterst(PostEntity postEntity, UserEntity userEntity){
        Optional<InterstEntity> interst = interstRepository.findByTypeAndUser(postEntity.getType().getType(),userEntity);
        if(interst.isEmpty()){
            InterstEntity interstEntity = new InterstEntity();
            interstEntity.setType(postEntity.getType().getType());
            interstEntity.setTimes(1);
            interstEntity.setUser(userEntity);
            interstRepository.save(interstEntity);
        }
        else{
            interst.get().setTimes(interst.get().getTimes()+1);
            interstRepository.save(interst.get());
        }
    }
}
