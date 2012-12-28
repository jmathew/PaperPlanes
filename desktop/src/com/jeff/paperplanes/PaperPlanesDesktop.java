package com.jeff.paperplanes;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class PaperPlanesDesktop {

  public static void main( String[] args ) {
    LwjglApplicationConfiguration cf = new LwjglApplicationConfiguration();
    cf.title = "PaperPlanes";
    cf.useGL20 = true;
    cf.width = 800;
    cf.height = 480;

    new LwjglApplication( new PaperPlanesGame(), cf );
  }
}

