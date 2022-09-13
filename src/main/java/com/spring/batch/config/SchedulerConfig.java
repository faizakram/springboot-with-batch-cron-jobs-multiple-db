package com.spring.batch.config;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

	//@Scheduled(fixedDelay=Long.MAX_VALUE, initialDelay = 5000)
    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
	public void scheduleByFixedRate() throws Exception {
		System.out.println("Batch job starting");
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("time", format.format(Calendar.getInstance().getTime())).toJobParameters();
		jobLauncher.run(job, jobParameters);
		System.out.println("Batch job executed successfully\n");
	}
}
