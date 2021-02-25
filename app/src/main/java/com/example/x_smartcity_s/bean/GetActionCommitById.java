package com.example.x_smartcity_s.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/2/23  8:54
 */
public class GetActionCommitById {
    /**
     *             "num": 1,
     *             "id": 1,
     *             "username": "tom",
     *             "commitTime": "2020-10-01 10:00:00",
     *             "commitContent": "12334567"
     */
    private String num;
    private String id;
    private String username;
    private String commitTime;
    private String commitContent;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    public String getCommitContent() {
        return commitContent;
    }

    public void setCommitContent(String commitContent) {
        this.commitContent = commitContent;
    }
}
