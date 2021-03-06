/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.renderscript.cts.refocus.image;

import android.renderscript.cts.refocus.DepthTransform;

/**
 * An implementation of {@code DepthTransform} that uses an inverse
 * 8-bit representation of depth similar to the OpenGL z-buffer storage.
 */
public class RangeInverseDepthTransform implements DepthTransform {
  public static final String FORMAT = "RangeInverse";

  private final float near;
  private final float far;

  public RangeInverseDepthTransform(float near, float far) {
    this.near = near;
    this.far = far;
  }

  @Override
  public float getNear() {
    return near;
  }

  @Override
  public float getFar() {
    return far;
  }

  @Override
  public String getFormat() {
    return FORMAT;
  }

  @Override
  public int quantize(float value) {
    return Math.max(0, Math.min(255,
        (int) ((far - near * far / value) / (far - near) * 255f)));
  }

  @Override
  public float reconstruct(int value) {
    return (far * near) / (far - (far - near) *
        Math.max(0, Math.min(255, value)) / 255f);
  }
}
