package com.hercules.model;

import java.util.List;
import java.util.Set;

public interface Taskable {

    public List<Task> getSubtasksAsList();

    public Taskable getSuperTask();

    public void addTask(Task task);

    public double getPercentageDone();

    public boolean isDone() ;

    public String getEst_time();

    public String getDeadline();

    public Long getId();

    public String getStartDate();

}
