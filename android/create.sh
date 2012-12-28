#!/bin/sh

# a VERY crude script to build everything

if [ -f bin/classes.dex ]; then
  rm -rfv bin/classes/* bin/*.apk bin/classes.dex 
fi

aapt package -v -f -m -M AndroidManifest.xml -I /opt/android-sdk/platforms/android-10/android.jar -S res -J src/ &&

javac -verbose -d bin/classes -classpath "bin/classes:/opt/android-sdk/platforms/android-10/android.jar:bin/lib/*:../main/libs/*:libs/*" -target 1.6 `find ./src -iname "*.java"` `find ../main/src -iname "*.java"` &&

dx --dex --output bin/classes.dex bin/classes libs/gdx-backend-android.jar ../main/libs/gdx.jar &&

aapt package -v -f -M AndroidManifest.xml -S res/ -I /opt/android-sdk/platforms/android-10/android.jar -F bin/paperplanes.unsigned.apk bin/  &&

jarsigner -verbose -keystore debugkey.keystore -storepass debug123 -keypass debug123 -signedjar bin/paperplanes.signed.apk bin/paperplanes.unsigned.apk debugkey &&

zipalign -v -f 4 bin/paperplanes.signed.apk bin/paperplanes.apk &&

adb -d install -r bin/paperplanes.apk
