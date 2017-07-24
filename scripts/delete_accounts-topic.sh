#!/usr/bin/env bash

$KAFKA_DIR/bin/kafka-topics.sh \
    --zookeeper localhost:2181 \
    --delete \
    --topic basy-account-events
