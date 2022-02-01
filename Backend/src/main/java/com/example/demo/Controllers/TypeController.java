package com.example.demo.Controllers;


import java.util.List;

import com.example.demo.Converters.TypeConverter;
import com.example.demo.Models.TypeModel;
import com.example.demo.Repositories.TypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path  = "/typePost")
public class TypeController {

    @Autowired
    TypeRepository typeRepository;
    @Autowired
    TypeConverter typeConverter;

    @GetMapping("/getAllTypes") 
    public List<TypeModel> getAllTypes(){
        return typeConverter.typeListEntityToModel(typeRepository.findAll());
    }
    
}
