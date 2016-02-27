#!/bin/bash

# I prefer to exit on error, but the first HandBrake make, below, expected to fail, doesn't
# seem to obey the rule that failures are okay in a command pipeline.
# set -e

mkdir -p ~/bookkeeping

UPDATED=`find ~/bookkeeping -type f -name apt-get-updated -atime -24`
if [ "$UPDATED" = "" ]; then
  sudo apt-get update
  touch ~/bookkeeping/apt-get-updated
fi

# Git to clone source repos.
sudo apt-get -y install git

# Handbrake build requirements per https://trac.handbrake.fr/wiki/CompileOnLinux
sudo apt-get -y install build-essential gcc make yasm autoconf libtool zlib1g-dev libbz2-dev
sudo apt-get -y install libogg-dev libtheora-dev libvorbis-dev libsamplerate-dev libxml2-dev
sudo apt-get -y install libfribidi-dev libfreetype6-dev libfontconfig1-dev libass-dev

# NOT mentioned in the build requirements:
sudo apt-get -y install libmp3lame-dev # or just libmp3lame0?
sudo apt-get -y install libjansson-dev

# Directory for cloning
mkdir -p ~/dev/projects

# Latest x264 because that's why I'm building this whole thing! I want a new x264 because
# I've been seeing segmentation faults that seem to trace back to it.
# Also not mentioned as a requirement.
cd ~/dev/projects
[ -d x264 ] || git clone git://git.videolan.org/x264.git
cd x264
./configure --enable-static --enable-shared
make
sudo make install
sudo ldconfig
cd ~

# The HandBrake build breaks if these flags aren't set. Something with resolving the
# location of libxml2.
export LIBXML2_CFLAGS=`xml2-config --cflags`
export LIBXML2_LIBS=`xml2-config --libs`

# Clone handbrake from github
cd ~/dev/projects
[ -d HandBrake ] || git clone https://github.com/HandBrake/HandBrake.git
cd HandBrake

# According to the instructions, just run:
# ./configure --launch --disable-gtk
# but at this time (23 Feb 2016), it fails with an error (actually a warning I think)
# about using fgets instead of gets.
# I've found it can be fixed by manually removing the relevant line from a header file
# after it breaks once, then resuming the build.
rm -rf build
./configure --disable-gtk
cd build
make
sed -i '/"gets is a security hole - use fgets instead"/d' contrib/m4/m4-1.4.16/lib/stdio.in.h
make
sudo make install

cd ~

# Couldn't complete for no apparent reason: "failed: Connection timed out" on:
# /usr/bin/wget -O ../download/libbluray-0.8.1.tar.bz2 http://download.handbrake.fr/handbrake/contrib/libbluray-0.8.1.tar.bz2
# ... even after the default 20 wget retries and also even though I was able to run this
# manually with no problems.
