package com.example.demo.quartz.job;

import com.example.demo.services.TheMovieDBServiceClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class DBUpdaterJob implements Job {
    TheMovieDBServiceClient serviceClient;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        serviceClient.reloadData(1980);
    }

}
