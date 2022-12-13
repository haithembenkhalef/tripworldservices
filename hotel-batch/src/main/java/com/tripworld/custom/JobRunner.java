package com.tripworld.custom;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;


/**
 * Represents a Job runner for Manually starting jobs,
 * @implNote to use this class please uncomment @component tag
 * and set spring.batch.job to false.
 * @version 1.0
 * @since 1.0
 */

//@Component
@AllArgsConstructor
public class JobRunner implements CommandLineRunner {

    private JobLauncher jobLauncher;

    private Job jobA;

    @Override
    public void run(String... args) throws Exception {

        JobParameters jobParameters =
                new JobParametersBuilder()
                        .addLong("time", System.currentTimeMillis())
                        .toJobParameters();

        jobLauncher.run(jobA, jobParameters);
        System.out.println("JOB Execution completed!");
    }

}
