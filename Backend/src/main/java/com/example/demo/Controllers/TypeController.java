package com.example.demo.Controllers;


import com.example.demo.Converters.TypeConverter;
import com.example.demo.Models.TypeModel;
import com.example.demo.Repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/typePost")
public class TypeController {

    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private TypeConverter typeConverter;

    /* Get Request */

    @GetMapping("/getAllTypes")
    public List<TypeModel> getAllTypes() {
        return typeConverter.typeListEntityToModel(typeRepository.findAll());
    }
}
