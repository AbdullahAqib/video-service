package com.itachay.video.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import reactor.core.publisher.Mono;

public interface VideoService {
    String uploadVideo(MultipartFile file) throws IOException;
    Mono<Resource> getStream(String id);
    void deleteVideo(String id);
}