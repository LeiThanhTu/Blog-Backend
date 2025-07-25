package com.thanhtule.blog.payloads;

import com.thanhtule.blog.entities.Post;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommentDto {

    private Integer id;

    private String content;

}
