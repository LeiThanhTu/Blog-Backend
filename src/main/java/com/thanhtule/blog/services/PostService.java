package com.thanhtule.blog.services;

import com.thanhtule.blog.entities.Post;
import com.thanhtule.blog.payloads.PostDto;
import com.thanhtule.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    // create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    // update
    PostDto updatePost(PostDto postDto, Integer postId);

    // delete
    void deletePost(Integer postId);

    // get all posts
    PostResponse getAllPost(Integer pageNumber, Integer pageSize);

    // get single post
    PostDto getPostById(Integer postId);

    // get all post by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    // get all posts by user
    List<PostDto> getPostsByUser(Integer userId);

    // search posts
    List<Post> searchPosts(String keyword);

}
