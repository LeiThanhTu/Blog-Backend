package com.thanhtule.blog.payloads;

import com.thanhtule.blog.entities.Comment;
import com.thanhtule.blog.entities.User;
import jdk.jfr.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;

    private CategoryDto category;
    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();


}
