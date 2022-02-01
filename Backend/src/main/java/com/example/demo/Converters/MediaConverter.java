package com.example.demo.Converters;

import com.example.demo.Entities.MediaEntity;
import com.example.demo.Models.MediaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MediaConverter {

    @Autowired
    private PostConverter postConverter;

    public MediaEntity convertMediaModelToMediaEntity(MediaModel mediaModel) {
        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setId(mediaModel.getId());
        mediaEntity.setPath(mediaModel.getPath());
        mediaEntity.setType(mediaModel.getType());
        // mediaEntity.setPostEntity(postConverter.postModelToEntity(mediaModel.getPostModel()));
        return mediaEntity;
    }

    public MediaModel convertMediaEntityToMediaModel(MediaEntity mediaEntity) {
        if (mediaEntity == null)
            return null;
        MediaModel mediaModel = new MediaModel();
        mediaModel.setId(mediaEntity.getId());
        mediaModel.setPath(mediaEntity.getPath());
        mediaModel.setType(mediaEntity.getType());
        return mediaModel;
    }

    public List<MediaEntity> mediaModelListToEntityList(List<MediaModel> mediaModels) {
        List<MediaEntity> mediaEntities = new ArrayList<>();
        if (mediaModels != null) {
            for (MediaModel media : mediaModels) {
                mediaEntities.add(convertMediaModelToMediaEntity(media));
            }
        }
        return mediaEntities;
    }

    public List<MediaModel> mediaEntityListToModelList(List<MediaEntity> mediaEntities) {
        List<MediaModel> mediaModels = new ArrayList<>();
        if (mediaEntities != null) {
            for (MediaEntity media : mediaEntities) {
                mediaModels.add(convertMediaEntityToMediaModel(media));
            }
        }
        return mediaModels;
    }

}
