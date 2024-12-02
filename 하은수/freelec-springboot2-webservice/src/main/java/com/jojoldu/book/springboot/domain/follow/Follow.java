package com.jojoldu.book.springboot.domain.follow;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Follow extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "followee_id")
    private User followee; // 내가 팔로잉 하는 사람

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower; // 나의 팔로워

    public Long getId() {
        return id;
    }

    public User getFollowee() {
        return followee;
    }

    public User getFollower() {
        return follower;
    }
}