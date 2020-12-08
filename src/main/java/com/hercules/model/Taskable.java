package com.hercules.model;

import java.util.List;
import java.util.Set;

public interface Taskable {

    public List<Taskable> getSubtasksAsList();


    public void addTask(Taskable task);

    public double getPercentageDone();

    public boolean isDone() ;

    public String getEst_time();

    public String getDeadline();

    public Long getId();

    public String getStartDate();

}
