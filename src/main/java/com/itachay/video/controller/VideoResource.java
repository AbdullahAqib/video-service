package com.itachay.video.controller;

import com.itachay.video.service.VideoService;
import io.github.jhipster.web.util.HeaderUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VideoResource {

    private final Logger log = LoggerFactory.getLogger(VideoResource.class);
    private static final String ENTITY_NAME = "video";

    @Value("${itachay.clientApp.name}")
    private String applicationName;

    private final VideoService videoService;

    @PostMapping(path = "/video")
    public ResponseEntity<String> uploadCourseVideo(@RequestParam("file") MultipartFile file) throws IOException, URISyntaxException {
        String fileId = videoService.uploadVideo(file);
        return ResponseEntity.created(new URI("/api/files/" + fileId))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, fileId))
                .body(fileId);
    }

}