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

import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.util.ArrayList;


public class MainScreen implements Screen {

  PaperPlanesGame g;
  Texture planeImage;
  Rectangle touchRect;
  SpriteBatch spb;
  Vector3 touchPos;
  OrthographicCamera cam;

  World theWorld;

  Plane plane;

  Box2DDebugRenderer dbr;

  ArrayList<Plane> planes;

  public MainScreen( PaperPlanesGame g ) {
    this.g = g;

    // load assets
    planeImage = new Texture( Gdx.files.internal( "assets/ball.png" ) );

    // initialize rectangle
    touchRect = new Rectangle();

    // initialize spritebatch for drawing
    spb = new SpriteBatch();    

    // initialize our camera
    cam = new OrthographicCamera();
    cam.setToOrtho( false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
    cam.update( true );

    // touch location
    touchPos = new Vector3();

    // box2d 
    dbr = new Box2DDebugRenderer();
    theWorld = new World( new Vector2( 0f, 0f ), true );
    theWorld.setContactListener( new CustomContactListener() );

    planes = new ArrayList<Plane>();

    

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
    for( int i = 0; i < planes.size(); i++ ) {
      planes.get( i ).update( delta );
    }
/*
    Vector2 t = new Vector2( this.plane.getPosition() );
    // begin draw
    spb.setProjectionMatrix( cam.combined );
    spb.begin();

    // move our plane and center it
    spb.draw( planeImage, t.x * Plane.PIXELS_PER_METER, t.y * Plane.PIXELS_PER_METER );
    spb.end();
*/

    // update touch position
    if( Gdx.input.isTouched() ) {
      touchPos.set( Gdx.input.getX(), Gdx.input.getY(), 0 );

      // only unproject if screen is touched duh!
      cam.unproject( touchPos );

      // converts the coord system of the touch units ( origin top left ) to camera coord ( origin bottom left )
      touchRect.x = touchPos.x;
      touchRect.y = touchPos.y;

     
      //plane.setTransform( new Vector2( touchRect.x / Plane.PIXELS_PER_METER, touchRect.y / Plane.PIXELS_PER_METER ) );

      planes.add( new Plane( theWorld, planeImage.getWidth(), planeImage.getHeight(), new Vector2( touchRect.x, touchRect.y ), new Vector2( Gdx.graphics.getWidth(), Gdx.graphics.getHeight() ) ) );
    }

    // update step
    theWorld.step( delta, 6, 2 );

    // render
    dbr.render( theWorld, cam.combined.scale( Plane.PIXELS_PER_METER, Plane.PIXELS_PER_METER, Plane.PIXELS_PER_METER ) ); 

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
