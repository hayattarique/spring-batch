package org.boot.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class JobNotificationListener implements JobExecutionListener {
    private long startTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Job execution started{}", jobExecution.getStartTime());
        startTime = System.currentTimeMillis();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Job execution completed{}", jobExecution.getEndTime());
            long endTime = System.currentTimeMillis();
            System.out.println("Time taken: " + (endTime - startTime) / 1000);
        }
    }
}
