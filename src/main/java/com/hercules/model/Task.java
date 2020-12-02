package com.hercules.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
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
    private Set<S3File> beforePictures = new HashSet<S3File>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "superTask")
//    @JsonManagedReference
//    @Column(name= "after_pic")
//    private Set<S3File> afterPic;

    private String task_start_date;

    private String deadline;

    private String est_time;

    //used as a supertask

//   @OneToMany(cascade = CascadeType.ALL, mappedBy = "superTask")
//   private Set<Task> subtasks;
//
//
//   //used as a subtask
//   @ManyToMany @JoinColumn(name = "parent_task_id")
//   private Task superTask;


    private String done;

    public Task(Set<S3File> beforePictures, Set<S3File> afterPic, String task_start_date, String deadline, String est_time, Set<Task> subtask, String done){
        this.beforePictures = beforePictures;
//        this.afterPic = afterPic;
        this.task_start_date = task_start_date;
        this.deadline = deadline;
        this.est_time = est_time;
//        this.subtask = subtask;
        this.done = done;
    }

    public Task(){
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
//
//    public Set<S3File> getAfterPic() {
//        return afterPic;
//    }
//
//    public void setAfterPic(Set<S3File> after_pic) {
//        this.afterPic = after_pic;
//    }

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
//
//    public Set<Task> getSubtask() {
//        return subtask;
//    }
//
//    public void setSubtask(Set<Task> subtask) {
//        this.subtask = subtask;
//    }
//
//    public Task getSuperTask() {
//        return superTask;
//    }
//
//    public void setSuperTask(Task superTask) {
//        this.superTask = superTask;
//    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }
}
