package com.jeff.paperplanes;

import com.badlogic.gdx.Game;


public class PaperPlanesGame extends Game {
  private MainScreen ms; 
  
  @Override
  public void create() {
    ms = new MainScreen( this );
    this.setScreen( ms );
  }
}
