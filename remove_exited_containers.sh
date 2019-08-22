#!/bin/bash

docker rm $(docker ps -q -f status=exited) 2> /dev/null
