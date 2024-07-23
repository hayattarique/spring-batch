package org.boot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class JobNotificationListener implements JobExecutionListener {
    private static Logger logger = LoggerFactory.getLogger(JobNotificationListener.class);
    private long startTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Job execution started{}", jobExecution.getStartTime());
        startTime = System.currentTimeMillis();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("Job execution completed{}", jobExecution.getEndTime());
            long endTime = System.currentTimeMillis();
            System.out.println("Time taken: " + (endTime - startTime) / 1000);
        }
    }
}
