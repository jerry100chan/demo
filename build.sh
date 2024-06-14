#!/bin/bash
image_name='demo'
# should be using the same version as defining in maven pom
image_tag='1.0'
dockerfile_path='./Dockerfile'

docker build -t ${image_name}:${image_tag} -f ${dockerfile_path} .
if [ $? != 0 ];then
  echo "Build image failed"
  exit 1
fi