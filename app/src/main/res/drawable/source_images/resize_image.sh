#!/bin/bash

#Requires ImageMagick to be installed.
#Some builds of ImageMagick on OSX have problems generating the images correctly.

#This script scales and creates images at the correct dpi level for Android.
#It gets placed in a folder called res/drawable/source_images/ in your #Android project along with all your svg files.
#When creating svg files set the image size to the size that you want your hdpi images to be.
#To use simply run the create_images script from its folder and it will generate images for all the svg files.

for f in *.png;
do
	echo "Processing $f"
	convert -background none $f[100%] ../../drawable-mdpi/${f}
	convert -background none $f[150%] ../../drawable-hdpi/${f}
	convert -background none $f[200%] ../../drawable-xhdpi/${f}
	convert -background none $f[300%] ../../drawable-xxhdpi/${f}
done
