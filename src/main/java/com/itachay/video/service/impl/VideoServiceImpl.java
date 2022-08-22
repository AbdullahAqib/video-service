package com.itachay.video.service.impl;

import com.itachay.video.service.VideoService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final GridFsTemplate gridFsTemplate;

    public String uploadVideo(MultipartFile file) throws IOException {
        DBObject metaData = new BasicDBObject();
        metaData.put("type", "video");
        return gridFsTemplate.store(
                file.getInputStream(), file.getContentType(), metaData).toString();
    }

}