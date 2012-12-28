#!/usr/bin/env zsh

LIBGDX_ZIP="libgdx-nightly-latest.zip"
wget http://libgdx.badlogicgames.com/nightlies/$LIBGDX_ZIP

#LIBGDX_ZIP="libgdx-0.9.7.zip"
#wget http://libgdx.googlecode.com/files/libgdx-0.9.7.zip

unzip -o $LIBGDX_ZIP gdx.jar gdx-natives.jar gdx-backend-android.jar gdx-backend-lwjgl.jar gdx-backend-lwjgl-natives.jar extensions/gdx-tools.jar 'armeabi/*' 'armeabi-v7a/*'
rm -v $LIBGDX_ZIP

mv -v gdx.jar main/libs/
cp -Rv extensions main/libs
mv -v gdx-natives.jar gdx-backend-lwjgl.jar gdx-backend-lwjgl-natives.jar  desktop/libs/
mv -v gdx-backend-android.jar android/libs/
cp -Rv armeabi-v7a armeabi android/libs/

rm -rf extensions armeabi armeabi-v7a 

