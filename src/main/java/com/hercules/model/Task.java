package com.hercules.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tasks")
public class Task implements Taskable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private Set<S3File> pictures = new HashSet<>();

    private String task_start_date;

    private String deadline;

    private String est_time;

    private String name;

    private String description;

    //    used as a supertask

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "superTask")
    private Set<Task> subtasks;


    //used as a subtask
    @ManyToOne
    @JoinColumn(name = "parent_task_id")
    private Task superTask;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case Case;

    private boolean done;

    /**
     * Constructor of the task class. name, task_start_date and deadline have default values if null is given.
     */
    public Task(String name, Set<S3File> pictures, String task_start_date, String deadline, String est_time, Set<Task> subtask, boolean done) {

        this.name = name;
        if (name == null) {
            name = "new name";
        }

        this.pictures = pictures;
        if (pictures != null){
            for (S3File s3 : pictures) {
                s3.setTask(this);
            }

        }
        else {
            this.pictures = new HashSet<>();
        }

        this.name = name;
        this.task_start_date = task_start_date;
        if (task_start_date == null) {
            this.task_start_date = LocalDate.now().toString();
        }

        this.deadline = deadline;
        if (deadline == null) {
            this.deadline = LocalDate.now().toString();
        }
        this.est_time = est_time;
        this.subtasks = subtask;
        this.done = done;
        for (Task t : subtasks) {
            t.setSuperTask(this);
        }
    }

    public Task(String name)
    {
        this.name = name;
        task_start_date = LocalDate.now().toString();
        deadline = LocalDate.now().toString();
    }

    public Task() {
        task_start_date = LocalDate.now().toString();
        deadline = LocalDate.now().toString();
        name = "ny opgave";
    }



    @Override
    public void addTask(Task task) {
        task.setSuperTask(this);
        subtasks.add(task);
    }

    /**
     * calculate how done a task is.
     * @return how many percent done the task is, ie. from 0 to 100
     */
    public double getPercentageDone()
    {
        if (done){
            return 100;
        }
        else {
            ArrayList<Task> subtasks = (ArrayList<Task>) getSubtasksAsList();

            if (subtasks.isEmpty())
                return 0;

            double totalTasks = subtasks.size();
            double doneTasks = 0;

            for (Taskable task: getSubtasksAsList()) {
                if (task.isDone())
                    doneTasks++;
            }
            return (doneTasks/totalTasks)*100;
        }
    }


    @Override
    public List<Task> getSubtasksAsList() {
        List<Task> result = new ArrayList<>(subtasks);
        return result;
    }

    public List<S3File> getPicturesAsList() {
        List<S3File> result = new ArrayList<>(pictures);
        return result;
    }

    public void addPicture(String location, String description, boolean isAfter) {
        S3File file = new S3File(location, isAfter);
        file.setDescription(description);
        file.setTask(this);
        pictures.add(file);
    }


    public boolean isDone() {
        return done;
    }

    public Set<S3File> getPictures() {
        return pictures;
    }

    public String getEst_time() {
        return est_time;
    }

    @Override
    public String getDeadline() {
        return deadline;
    }

    @Override
    public Long getId() {
        return getTaskId();
    }

    @Override
    public String getStartDate() {
        return getTask_start_date();
    }

    public Long getTaskId() {
        return taskId;
    }

    public String getTask_start_date() {
        return task_start_date;
    }

    public String getName() {
        return name;
    }

    public Case getCase() {
        return Case;
    }

    public void setCase(Case aCase) {
        Case = aCase;
    }

    public void setTaskId(Long taskId) {

        this.taskId = taskId;
    }

    public void setPictures(Set<S3File> pictures) {
        this.pictures = pictures;
    }

    public void setTask_start_date(String task_start_date) {
        this.task_start_date = task_start_date;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setEst_time(String est_time) {
        this.est_time = est_time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Task> getSubtasks() {
        return subtasks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubtasks(Set<Task> subtasks) {
        this.subtasks = subtasks;
    }

    public Task getSuperTask() {
        return superTask;
    }

    public void setSuperTask(Task superTask) {
        this.superTask = superTask;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
