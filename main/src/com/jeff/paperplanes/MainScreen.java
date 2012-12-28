package com.jeff.paperplanes;


import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.Screen;

public class MainScreen implements Screen {

  PaperPlanesGame g;
  Texture planeImage;
  Rectangle planeRect;
  SpriteBatch spb;
  Vector3 touchPos;
  OrthographicCamera cam;

  public MainScreen( PaperPlanesGame g ) {
    this.g = g;

    // load assets
    planeImage = new Texture( Gdx.files.internal( "assets/plane.png" ) );

    // initialize rectangle
    planeRect = new Rectangle();

    // initialize spritebatch for drawing
    spb = new SpriteBatch();    

    // initialize our camera
    cam = new OrthographicCamera();
    cam.setToOrtho( false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
    cam.update( true );

    // touch location
    touchPos = new Vector3();

  }
  @Override
  public void show() {
  }
  @Override
  public void render( float delta ) {
    // clear screen
    Gdx.gl.glClear( GL10.GL_COLOR_BUFFER_BIT );

    // update camera
    cam.update();

    
    // begin draw
    spb.setProjectionMatrix( cam.combined );
    spb.begin();

    // move our plane and center it
    spb.draw( planeImage, planeRect.x - ( planeImage.getWidth() / 2 ) , planeRect.y - ( planeImage.getHeight() / 2 ) );
    //spb.draw( planeImage, planeRect.x, planeRect.y );
    spb.end();

    // update touch position
    if( Gdx.input.isTouched() ) {
      touchPos.set( Gdx.input.getX(), Gdx.input.getY(), 0 );

      // only unproject if screen is touched duh!
      cam.unproject( touchPos );

      // converts the coord system of the touch units ( origin top left ) to camera coord ( origin bottom left )
      planeRect.x = touchPos.x;
      planeRect.y = touchPos.y;
    }
    
    Gdx.app.log( "X + Y", planeRect.x + " + " + planeRect.y );

  }

  @Override
  public void resize( int width, int height ) {
  }
  @Override
  public void hide() {
  }
  @Override
  public void pause() {
  }
  @Override
  public void resume() {
  }
  @Override
  public void dispose() {
  }
}
