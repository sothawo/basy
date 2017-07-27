/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public class KafkaProperties extends Properties {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProperties.class);
    private static final String KAFKA_PROPERTIES = "/kafka.properties";

    public KafkaProperties() {
        final URL resourceUrl = getClass().getResource(KAFKA_PROPERTIES);
        if (resourceUrl != null) {
            logger.info("loading {} from {}", KAFKA_PROPERTIES, resourceUrl.toString());
            try (InputStream is = resourceUrl.openStream()) {
                load(is);
            } catch (IOException e) {
                logger.error("could not load properties", e);
            }
        } else {
            logger.warn("could not find {}", KAFKA_PROPERTIES);
        }
    }
}
