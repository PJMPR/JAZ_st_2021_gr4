package com.example.demo.quartz.web;

import com.example.demo.model.Film;
import com.example.demo.quartz.job.DBUpdaterJob;
import com.example.demo.quartz.payload.ProcessInfo;
import com.example.demo.quartz.payload.StatusInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@RestController
public class DBUpdaterSchedulerController {
    @Autowired
    private Scheduler scheduler;

    @PostMapping("/schedule/updater")
    public ResponseEntity<StatusInfo> scheduleUpdate(@RequestBody Film film){
        try{
            JobDetail jobDetail = buildJobDetail(film.getFilmId());
            Trigger trigger = buildTrigger(jobDetail, ZonedDateTime.now());

            scheduler.scheduleJob(jobDetail, trigger);

            StatusInfo statusInfo = new StatusInfo(false, new Date(System.currentTimeMillis()), null, 0, 0, null, null);

            return ResponseEntity.ok(statusInfo);
        }catch (SchedulerException e){
            log.error("Error while scheduling a job ", e);
            StatusInfo statusInfo = new StatusInfo(new Date(System.currentTimeMillis()), new ArrayList<String>() {
                {
                    add("Error while scheduling a job updater ");
                }
            });
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(statusInfo);
        }

    }

    private JobDetail buildJobDetail(Integer id){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("id", id);

        return JobBuilder.newJob(DBUpdaterJob.class)
                .withIdentity(UUID.randomUUID().toString(), "updater")
                .withDescription("Updater Job")
                .usingJobData(jobDataMap)
                .build();
    }

    private Trigger buildTrigger(JobDetail jobDetail, ZonedDateTime startedAt){
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "updater")
                .withDescription("Updater trigger")
                .startAt(Date.from(startedAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24))
                .build();
    }
}
