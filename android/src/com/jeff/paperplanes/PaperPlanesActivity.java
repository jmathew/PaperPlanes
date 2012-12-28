package com.jeff.paperplanes;

import android.app.Activity;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class PaperPlanesActivity extends AndroidApplication {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    AndroidApplicationConfiguration cf = new AndroidApplicationConfiguration();

    cf.useGL20 = true;
    cf.useAccelerometer = true;
    cf.useCompass = false;

    initialize( new PaperPlanesGame(), cf );
  }
}
