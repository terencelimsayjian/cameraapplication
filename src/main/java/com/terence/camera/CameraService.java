package com.terence.camera;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Mat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;

@Component
@Slf4j
public class CameraService {
  @Autowired
  FrameGrabber frameGrabber;

  @Autowired
  OpenCVFrameConverter<IplImage> frameConverter;

  @Autowired
  ResourceLoader resourceLoader;

  public byte[] captureFrame() {
    try {
      frameGrabber.start();
      Frame frame = frameGrabber.grab();
      IplImage img = frameConverter.convert(frame);

      if (img != null) {
        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
        Mat convert = converter.convert(frame);

        BytePointer bytePointer = new BytePointer();
        imencode(".jpg", convert, bytePointer);

        ByteBuffer byteBuffer = bytePointer.asBuffer();

        byte[] arr = new byte[byteBuffer.remaining()];
        byteBuffer.get(arr);

        return arr;
      }
    } catch (Exception e) {
      log.error("Failed to take image");
      e.printStackTrace();
    }

    return new byte[0];
  }
}
