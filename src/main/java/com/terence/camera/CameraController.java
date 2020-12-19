package com.terence.camera;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class CameraController {
  @Autowired
  ResourceLoader resourceLoader;

  @Autowired
  CameraService cameraService;

  @GetMapping(value = "/capture", produces = MediaType.IMAGE_JPEG_VALUE)
  public byte[] captureImage() throws IOException {
    // Security
    // Locking mechanism for camera resources?
    // Learn about default error handling for spring (Unknown routes etc)
    // Learn about about spring web (Request body, controller advice etc.)

    return cameraService.captureFrame();
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity cameraErrorHandler(Exception e) {
    log.error("Failed to take image", e);
    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
