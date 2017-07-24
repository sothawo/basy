#!/usr/bin/env bash

cd $KAFKA_DIR

bin/kafka-server-stop.sh
sleep 10
bin/zookeeper-server-stop.sh
