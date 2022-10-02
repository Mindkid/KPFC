#! /bin/bash

usage(){
    cat << EOF 
        usage ./cdBuild.sh
EOF
}

IMAGE_NAME=blog-backend
IMAGE_TAG=local

SCRIPT_DIR="$( cd -- "$( dirname -- "$0")" >/dev/null 2>&1 && pwd )"
ROOT_DIR=$SCRIPT_DIR"/.."

cd "$ROOT_DIR" || exit 1

gradle bootjar

docker build . -t $IMAGE_NAME:$IMAGE_TAG