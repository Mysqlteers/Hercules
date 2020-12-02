package com.hercules.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)@Column(name = "task_id")
    private Long taskId;

    private String before_pic;

    private String after_pic;

    private Date task_start_date;

    private Date deadline;

    private String est_time;

    private String subtask;

    private String done;

    public Task(Long taskId, String before_pic, String after_pic, Date task_start_date, Date deadline, String est_time, String subtask, String done){
      this.taskId = taskId;
      this.before_pic = before_pic;
      this.after_pic = after_pic;
      this.task_start_date = task_start_date;
      this.deadline = deadline;
      this.est_time = est_time;
      this.subtask = subtask;
      this.done = done;
    }

    public Task(String before_pic, String after_pic, Date task_start_date, Date deadline, String est_time, String subtask, String done){
        this.before_pic = before_pic;
        this.after_pic = after_pic;
        this.task_start_date = task_start_date;
        this.deadline = deadline;
        this.est_time = est_time;
        this.subtask = subtask;
        this.done = done;
    }
    public Task(){

    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTask_id(Long taskId) {
        this.taskId = taskId;
    }

    public String getBefore_pic() {
        return before_pic;
    }

    public void setBefore_pic(String before_pic) {
        this.before_pic = before_pic;
    }

    public String getAfter_pic() {
        return after_pic;
    }

    public void setAfter_pic(String after_pic) {
        this.after_pic = after_pic;
    }

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

    public String getSubtask() {
        return subtask;
    }

    public void setSubtask(String subtask) {
        this.subtask = subtask;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }
}
