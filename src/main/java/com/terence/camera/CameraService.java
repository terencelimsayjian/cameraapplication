package com.terence.camera;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;

@Component
@Slf4j
public class CameraService {
  @Autowired
  FrameGrabber frameGrabber;

  @Autowired
  OpenCVFrameConverter.ToMat frameConverter;

  boolean currentlyHandlingRequest = false;

  public byte[] captureFrame(ImageExtension imageExtension) throws FrameGrabber.Exception {
    currentlyHandlingRequest = true;

    try {
      frameGrabber.start();
      Frame frame = frameGrabber.grab();
      Mat mat = frameConverter.convert(frame);

      BytePointer bytePointer = new BytePointer();
      String extension = imageExtension.getValue();
      imencode(extension, mat, bytePointer);

      ByteBuffer byteBuffer = bytePointer.asBuffer();

      byte[] arr = new byte[byteBuffer.remaining()];
      byteBuffer.get(arr);

      return arr;
    } catch (Exception e) {
      log.error("Failed to take image", e);
      throw e;
    } finally {
      frameGrabber.close();
      currentlyHandlingRequest = false;
    }
  }
}
