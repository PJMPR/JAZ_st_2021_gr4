package com.example.demo.quartz.payload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
@Getter
@Setter
//@Component
public class StatusInfo {
    boolean isWorking;
    Date reloadStarted;
    Date reloadEnded;
    int moviesToUpdate;
    int progress;
    ArrayList<ProcessInfo> stepFinished;
    ArrayList<String> errorMessages;

    public StatusInfo(Date reloadStarted, ArrayList<String> errorMessages) {
        this.reloadStarted = reloadStarted;
        this.errorMessages = errorMessages;
    }

    public StatusInfo(boolean isWorking, Date reloadStarted, Date reloadEnded, int moviesToUpdate, int progress, ArrayList<ProcessInfo> stepFinished, ArrayList<String> errorMessages) {
        this.isWorking = isWorking;
        this.reloadStarted = reloadStarted;
        this.reloadEnded = reloadEnded;
        this.moviesToUpdate = moviesToUpdate;
        this.progress = progress;
        this.stepFinished = stepFinished;
        this.errorMessages = errorMessages;
    }
}