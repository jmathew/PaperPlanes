package com.jeff.paperplanes;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.physics.box2d.Body;

import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;


public class Plane {

  Texture tex;
  Body body;
  BodyDef bodyDef;
  Fixture fix;
  FixtureDef fixDef;
  PolygonShape collisionBox;

  Vector2 center;

  public static final float PIXELS_PER_METER = 15.0f;

  public Plane( World world, Vector2 position, Vector2 center ) {
    this.center = new Vector( center );

    // Add texture
    tex = new Texture( Gdx.files.internal( "assets/plane.png" ) ); 


    // BodyDef
    this.bodyDef = new BodyDef();
    this.bodyDef.type = BodyType.DynamicBody;
    this.bodyDef.position.set( position );

    this.body = world.createBody( this.bodyDef );

    // Shape for collision detection / physics modeling
    this.collisionBox = new PolygonShape();
    this.collisionBox.setAsBox( tex.getWidth() / ( 2 * PIXELS_PER_METER ), tex.getHeight() / ( 2 * PIXELS_PER_METER ); //should be related to texture dimensions
    
    this.fixDef = new FixtureDef();
    this.fixDef.shape = this.collisionBox;
    this.fixDef.density = 1.0f;
    this.fixDef.friction = 0.0f;
    this.fixDef.restitution = 1;

    this.body.createFixture( fixDef );

    //this.collisionBox.dispose(); we're usign this for displaying now too


  }

  // Function for updating postion, used in render function
  public void update( float deltaTime ) {
    //essentially manages the movement of the textures and the physics sim
    Vector2 bodyLoc = new Vector2( this.body.getPosition() );

    
      
  }
} 
