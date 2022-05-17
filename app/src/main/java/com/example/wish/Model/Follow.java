package com.example.wish.Model;

public class Follow {
    private String FollowedBy;
    private long FollowedAt;

    public Follow() {
    }

    public String getFollowedBy() {
        return FollowedBy;
    }

    public void setFollowedBy(String followedBy) {
        FollowedBy = followedBy;
    }

    public long getFollowedAt() {
        return FollowedAt;
    }

    public void setFollowedAt(long followedAt) {
        FollowedAt = followedAt;
    }
}
