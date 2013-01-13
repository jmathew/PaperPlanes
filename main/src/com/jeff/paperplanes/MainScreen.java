package com.jeff.paperplanes;


import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.Screen;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;




public class MainScreen implements Screen {

  PaperPlanesGame g;
  Texture planeImage;
  Rectangle planeRect;
  SpriteBatch spb;
  Vector3 touchPos;
  OrthographicCamera cam;

  World theWorld;

  Plane plane;

  public MainScreen( PaperPlanesGame g ) {
    this.g = g;

    // load assets
    planeImage = new Texture( Gdx.files.internal( "assets/ball.png" ) );

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

    // box2d 
    theWorld = new World( new Vector2( 0f, 0f ), true );
    
    plane = new Plane( theWorld, new Vector2( 0f, 0f ), new Vector2( Gdx.graphics.getWidth() / ( 2 * Plane.PIXELS_PER_METER ) , Gdx.graphics.getHeight() / ( 2 * Plane.PIXELS_PER_METER ) ) );

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

    // update physics world
    plane.update( delta );

    Vector2 t = new Vector2( this.plane.getPosition() );

    // begin draw
    spb.setProjectionMatrix( cam.combined );
    spb.begin();

    // move our plane and center it
    //spb.draw( planeImage, planeRect.x - ( planeImage.getWidth() / 2 ) , planeRect.y - ( planeImage.getHeight() / 2 ) );
    spb.draw( planeImage, t.x * Plane.PIXELS_PER_METER, t.y * Plane.PIXELS_PER_METER );
    spb.end();


    // update touch position
    if( Gdx.input.isTouched() ) {
      touchPos.set( Gdx.input.getX(), Gdx.input.getY(), 0 );

      // only unproject if screen is touched duh!
      cam.unproject( touchPos );

      // converts the coord system of the touch units ( origin top left ) to camera coord ( origin bottom left )
      planeRect.x = touchPos.x;
      planeRect.y = touchPos.y;

     
      plane.setTransform( new Vector2( planeRect.x / Plane.PIXELS_PER_METER, planeRect.y / Plane.PIXELS_PER_METER ) );
    }

    // update step
    theWorld.step( delta, 6, 2 );

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
