package com.terence.camera;

import org.bytedeco.javacv.FrameConverter;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CameraConfig {
  @Bean
  FrameGrabber getFrameGrabber() {
    // 0-default camera, 1 - next...so on
    return new OpenCVFrameGrabber(1);
  }

  @Bean
  OpenCVFrameConverter<IplImage> getFrameConverter() {
    return new OpenCVFrameConverter.ToIplImage();
  }
}
