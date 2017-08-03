#!/usr/bin/env bash

# retention.ms: 1 year

$KAFKA_DIR/bin/kafka-topics.sh \
    --zookeeper localhost:2181 \
    --create \
    --topic basy-account-events \
    --partitions 100 \
    --replication-factor 1 \
    --config retention.ms=31536000000
