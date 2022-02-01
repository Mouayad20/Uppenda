package com.example.demo.Converters;

import com.example.demo.Entities.PageEntity;
import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageConverter {

    @Autowired
    private UserConverter userConverter;
    @Autowired
    private PostConverter postConverter;

    public PageModel convertPageEntityToPageModel(PageEntity pageEntity) {
        PageModel pageModel = new PageModel();
        if (pageEntity == null)
            return null;
        pageModel.setCreatedAt(pageEntity.getCreatedAt());
        pageModel.setDescription(pageEntity.getDescreption());
        pageModel.setId(pageEntity.getId());
        pageModel.setImgPath(pageEntity.getImgPath());
        pageModel.setName(pageEntity.getName());
        pageModel.setAdmin(userConverter.getUserModelWithBasicInformation(pageEntity.getAdmin()));
        if (pageEntity.getMembers() != null)
            pageModel.setMembers(userConverter.fetchFriendsFromUserEntity(pageEntity.getMembers()));
        pageModel.setPostModels(new ArrayList<>()/*
         * postEntityListToModelList(pageEntity.getPostEntities(),
         * false,false,false, false, false)
         */);///////////////////////////////////////
        return pageModel;
    }

    public PageEntity convertPageModelToPageEntity(PageModel pageModel, UserEntity userEntity, boolean useIdFromModel) {
        PageEntity pageEntity = new PageEntity();
        pageEntity.setAdmin(userEntity);
        pageEntity.setName(pageModel.getName());
        pageEntity.setCreatedAt(pageModel.getCreatedAt());
        pageEntity.setMembers(new ArrayList<>());
        pageEntity.setPostEntities(postConverter.postModelListToEntityList(pageModel.getPostModels()));/////////////////////////////
        if (useIdFromModel) {
            pageEntity.setId(pageModel.getId());
        }
        pageEntity.setDescreption(pageModel.getDescription());
        pageEntity.setImgPath(pageModel.getImgPath());
        return pageEntity;
    }

    public List<PageModel> convertPageEntityListToPageModelList(List<PageEntity> PageModel) {
        List<PageModel> list = new ArrayList<>();
        if (PageModel != null) {
            for (PageEntity pageEntity : PageModel) {
                list.add(convertPageEntityToPageModel(pageEntity));
            }

        }
        return list;
    }

}
