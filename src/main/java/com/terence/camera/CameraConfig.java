package com.terence.camera;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CameraConfig {
  @Bean
  FrameGrabber getFrameGrabber(@Value("${variables.camera_device_id}") int cameraId) {
    log.info("Creating Frame Grabber with cameraId " + cameraId);
    return new OpenCVFrameGrabber(cameraId);
  }

  @Bean
  OpenCVFrameConverter.ToMat getFrameConverter() {
    return new OpenCVFrameConverter.ToMat();
  }
}
