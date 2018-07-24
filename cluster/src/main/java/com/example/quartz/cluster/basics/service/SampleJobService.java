package com.example.quartz.cluster.basics.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SampleJobService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void executeSampleJob() {

        this.logger.info("The sample job has begun...");
        try {
            Thread.sleep(5000);
        } catch (final InterruptedException e) {
            this.logger.error("Error while executing sample job", e);
        } finally {
            this.logger.info("Sample job has finished...");
        }
    }
}
