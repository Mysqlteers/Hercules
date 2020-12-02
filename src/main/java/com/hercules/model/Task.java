package com.hercules.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "superTask")
    @Column(name = "before_pic")
    private Set<S3File> beforePicture;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "superTask")
//    @JsonManagedReference
//    @Column(name= "after_pic")
//    private Set<S3File> afterPic;

    private Date task_start_date;

    private Date deadline;

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

    public Task( Set<S3File> beforePicture, Set<S3File> afterPic, Date task_start_date, Date deadline, String est_time, Set<Task> subtask, String done){
        this.beforePicture = beforePicture;
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

    public Set<S3File> getBeforePicture() {
        return beforePicture;
    }

    public void setBeforePicture(Set<S3File> beforePicture) {
        this.beforePicture = beforePicture;
    }
//
//    public Set<S3File> getAfterPic() {
//        return afterPic;
//    }
//
//    public void setAfterPic(Set<S3File> after_pic) {
//        this.afterPic = after_pic;
//    }

    public Date getTask_start_date() {
        return task_start_date;
    }

    public void setTask_start_date(Date task_start_date) {
        this.task_start_date = task_start_date;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
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
