#!/bin/bash

image_name='demo'
image_tag='1.0'

# check if images exists
if docker images -q "$image_name:$image_tag" >/dev/null 2>&1; then
    echo "Image $image_name:$image_tag exists locally."
else
    echo "Image $image_name:$image_tag does not exist locally. Pulling..."
    ./build.sh
fi
echo $image_name:$image_tag
docker run -d -p 9000:9000 --name demo ${image_name}:${image_tag}