package com.hercules.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;


    @OneToMany(mappedBy = "task")
    @Column(name = "before_pictures")
    @JsonManagedReference
    private Set<S3File> beforePictures = new HashSet<>();

    private String task_start_date;

    private String deadline;

    private String est_time;
    
    private String name;

//    used as a supertask

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "superTask")
    private Set<Task> subtasks;


    //used as a subtask
    @ManyToOne
    @JoinColumn(name = "parent_task_id")
    private Task superTask;


    private boolean done;

    /**
     * Constructor of the task class. name, task_start_date and deadline have default values if null is given.
     */
    public Task(String name, Set<S3File> pictures, String task_start_date, String deadline, String est_time, Set<Task> subtask, boolean done) {

        this.name = name;
        if (name == null) {
            name = "new name";
        }

        if (pictures != null)
            for (S3File s3 : pictures) {
                s3.setTask(this);
            }
        this.beforePictures = pictures;
            this.name = name;

        this.task_start_date = task_start_date;
        if (task_start_date == null) {
            this.task_start_date = LocalDate.now().toString();
        }

        this.deadline = deadline;
        if (deadline == null) {
            deadline = LocalDate.now().plusDays(1).toString();
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
        deadline = LocalDate.now().plusDays(1).toString();
    }

    public Task() {
        task_start_date = LocalDate.now().toString();
        deadline = LocalDate.now().plusDays(1).toString();
        task_start_date = LocalDate.now().toString();
        deadline = LocalDate.now().plusDays(1).toString();
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long task_id) {
        this.taskId = task_id;
    }

    public Set<S3File> getBeforePictures() {
        return beforePictures;
    }

    public void setBeforePictures(Set<S3File> beforePicture) {
        this.beforePictures = beforePicture;
    }

    public String getTask_start_date() {
        return task_start_date;
    }

    public void setTask_start_date(String task_start_date) {
        this.task_start_date = task_start_date;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getEst_time() {
        return est_time;
    }

    public void setEst_time(String est_time) {
        this.est_time = est_time;
    }

    public Set<Task> getSubtasks() {
        return subtasks;
    }
    public List<Task> getSubtasksAsList() {
        List<Task> result = new ArrayList<>(subtasks);
        return result;
    }

    public void setSubtasks(Set<Task> subtask) {
        this.subtasks = subtask;
    }

    public Task getSuperTask() {
        return superTask;
    }

    public void setSuperTask(Task superTask) {
        this.superTask = superTask;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
