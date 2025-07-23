package com.thanhtule.blog.repositories;

import com.thanhtule.blog.entities.Category;
import com.thanhtule.blog.entities.Post;
import com.thanhtule.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
