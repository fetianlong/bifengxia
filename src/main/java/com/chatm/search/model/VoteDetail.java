package com.chatm.search.model;

public class VoteDetail {
    private Long id;

    private Long voteId;

    private String voteChoose;

    private String voteIp;

    private String voteArea;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public String getVoteChoose() {
        return voteChoose;
    }

    public void setVoteChoose(String voteChoose) {
        this.voteChoose = voteChoose == null ? null : voteChoose.trim();
    }

    public String getVoteIp() {
        return voteIp;
    }

    public void setVoteIp(String voteIp) {
        this.voteIp = voteIp == null ? null : voteIp.trim();
    }

    public String getVoteArea() {
        return voteArea;
    }

    public void setVoteArea(String voteArea) {
        this.voteArea = voteArea == null ? null : voteArea.trim();
    }
}