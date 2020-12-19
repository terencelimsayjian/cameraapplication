package com.terence.camera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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

    return cameraService.captureFrame();
  }
}
