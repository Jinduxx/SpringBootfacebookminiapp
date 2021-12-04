package com.example.facebookminiapp.otherModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDetails {
    private Long postId;
    private Long id;
    private String username;
    private String comment;
    private String title;
    private String imageName;
    private Long userId;
}
