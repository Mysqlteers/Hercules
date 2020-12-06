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
public class Task {
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


    /**
     * calculate how done a task is.
     * todo make unit test
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

            int totalTasks = subtasks.size();
            int doneTasks = 0;

            for (Task task: getSubtasksAsList()) {
                if (task.isDone())
                    doneTasks++;
            }
            return doneTasks/totalTasks*100;
        }
    }

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


    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Set<S3File> getPictures() {
        return pictures;
    }

    public void setPictures(Set<S3File> pictures) {
        this.pictures = pictures;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Task> getSubtasks() {
        return subtasks;
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
