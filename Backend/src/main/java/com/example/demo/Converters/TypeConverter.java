package com.example.demo.Converters;

import com.example.demo.Entities.TypeEntity;
import com.example.demo.Models.TypeModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TypeConverter {

    public TypeModel typeEntityToModel(TypeEntity type) {
        TypeModel typeModel = new TypeModel();
        typeModel.setId(type.getId());
        typeModel.setTypename(type.getType());
        return typeModel;
    }

    public TypeEntity typeModelToEntity(TypeModel typeModel) {
        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setId(typeModel.getId());
        typeEntity.setType(typeModel.getTypename());
        return typeEntity;
    }

    public List<TypeModel> typeListEntityToModel(Iterable<TypeEntity> typeEntities) {
        List<TypeModel> typeModels = new ArrayList<>();
        for (TypeEntity typeEntity : typeEntities) {
            typeModels.add(typeEntityToModel(typeEntity));
        }
        return typeModels;
    }
}
