package org.boot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class JobController {
    private final JobLauncher jobLauncher;
    private final Job job;

    @GetMapping("/run")
    public ResponseEntity<String> startJob() throws Exception {
        log.info("Starting job from controller");
        jobLauncher.run(job, new JobParameters());
        return ResponseEntity.ok("Job started");
    }
}
