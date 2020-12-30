package com.terence.camera;

public enum ImageExtension {
  JPG(".jpg"),
  PNG(".png");

  private String value;

  ImageExtension(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
