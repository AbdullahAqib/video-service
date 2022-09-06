package com.itachay.video.controller;

import com.itachay.video.service.VideoService;
import io.github.jhipster.web.util.HeaderUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

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

    @PostMapping(path = "/videos")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) throws IOException, URISyntaxException {
        String id = videoService.uploadVideo(file);
        return ResponseEntity.created(new URI("/api/videos/" + id))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, id))
                .body(id);
    }

    @GetMapping(value = "/stream/{id}", produces = "video/mp4")
    public Mono<Resource> getStream(@PathVariable String id) {
        return videoService.getStream(id);
    }

    @DeleteMapping(path = "/videos/{id}")
    public ResponseEntity deleteVideo(@PathVariable String id) {
        videoService.deleteVideo(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}