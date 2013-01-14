package com.jeff.paperplanes;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.audio.Sound;

import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public class CustomContactListener implements ContactListener {

  // remember to dispose in main program

  // don't judge me!
  Sound snd;
  Sound sndb;
  Sound sndc;

  Fixture fa;
  Fixture fb;
  Body ba;
  Body bb;

  Vector2 va;
  Vector2 vb;  

  float p;

  public CustomContactListener() {
    snd = Gdx.audio.newSound( Gdx.files.internal( "assets/200.mp3" ) );
    sndb = Gdx.audio.newSound( Gdx.files.internal( "assets/600.mp3" ) );
    sndc = Gdx.audio.newSound( Gdx.files.internal( "assets/1000.mp3" ) );
  }

  @Override
  public void beginContact( Contact cont ) {
    //Gdx.app.log( "CONTACT", "begin" );

    // get fixture from contact
    fa = cont.getFixtureA();
    fb = cont.getFixtureB();

    // get body from fixture
    ba = fa.getBody();
    bb = fb.getBody();

    // get velocities
    va = ba.getLinearVelocity();
    vb = bb.getLinearVelocity();

    va.add( vb );
    
    // Y = ( X -A )/ ( B -A ) * ( D - C ) + C
    //p = ( va.len() - 0 ) / ( 100 - 0 ) * ( 1 - 0 ) + 0;

    //Gdx.app.log( "pitch", "" + p );

    //snd.setPitch( snd.play(), p );

    // the max mag of the collision is dependent on gravity and other applied forces 
    if( va.len() > 66 ) {
      snd.play();
    } else if ( va.len() > 33 ){
      sndb.play();
    } else {
      sndc.play();
    }
  }

  @Override
  public void endContact( Contact cont ) {
    //Gdx.app.log( "CONTACT", "end" );
  }
  
  @Override
  public void postSolve( Contact cont, ContactImpulse imp ) {
    //Gdx.app.log( "CONTACT", "postsolve" );
  }
  @Override
  public void preSolve( Contact cont, Manifold oldManifold ) {
    //Gdx.app.log( "CONTACT", "presolve" );
  }
}
