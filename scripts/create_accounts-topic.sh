#!/usr/bin/env bash

$KAFKA_DIR/bin/kafka-topics.sh \
    --zookeeper localhost:2181 \
    --create \
    --topic basy-account-events \
    --partitions 100 \
    --replication-factor 1
