#!/usr/bin/env bash

cd $KAFKA_DIR

bin/zookeeper-server-start.sh config/zookeeper.properties &
sleep 10
bin/kafka-server-start.sh config/server.properties &
