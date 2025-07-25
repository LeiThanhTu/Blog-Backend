package com.thanhtule.blog.services.impl;

import com.thanhtule.blog.entities.Comment;
import com.thanhtule.blog.entities.Post;
import com.thanhtule.blog.exceptions.ResourceNotFoundException;
import com.thanhtule.blog.payloads.CommentDto;
import com.thanhtule.blog.repositories.CommentRepo;
import com.thanhtule.blog.repositories.PostRepo;
import com.thanhtule.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post", "Post id", postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        comment.setPost(post);

        Comment savedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = this.commentRepo.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment id", commentId));
        this.commentRepo.delete(comment);
    }
}
