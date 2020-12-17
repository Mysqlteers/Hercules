package com.hercules.model;

import org.springframework.web.multipart.MultipartFile;

public class TaskImageHelper
{
    private MultipartFile file;
    private Long taskId;

    public TaskImageHelper(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
