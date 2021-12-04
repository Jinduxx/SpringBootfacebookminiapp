package com.example.facebookminiapp.otherModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDetails {
    private Long id;
    private String title;
    private String body;
    private String imageName;
    private String name;
    private String email;
    private int noLikes;
    private int noComments;
    private boolean likedPost;
    private Long userId;
}
