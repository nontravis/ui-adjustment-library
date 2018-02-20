package com.thekhaeng.library.uiadjustment.debug.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;

public class FloatSeekBar extends AppCompatSeekBar{

  private float min = 0f;
  private float max = 1f;
  private float stepSize = 0.01f;

  public FloatSeekBar( Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    setBoundaries(min, max, stepSize);
  }

  public FloatSeekBar( Context context, AttributeSet attrs) {
    super(context, attrs);
    setBoundaries(min, max, stepSize);
  }

  public FloatSeekBar( Context context) {
    super(context);
    setBoundaries(min, max, stepSize);
  }

  public void setBoundaries(float min, float max, float stepSize) {
    this.min = min;
    this.max = max;
    this.stepSize = stepSize;
    int numberOfSteps = (int) ((max - min) / stepSize);
    setMax(numberOfSteps);
  }

  public void setValue(float value) {
    setProgress((int) ((value - min) / stepSize));
  }

  public float getValue() {
    return getProgress() * stepSize + min;
  }
}
