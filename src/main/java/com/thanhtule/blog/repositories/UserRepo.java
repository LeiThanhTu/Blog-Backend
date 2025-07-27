package com.thanhtule.blog.repositories;

import com.thanhtule.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    // Có thể có hoặc không có kết quả trả về
    // Spring sẽ tự tạo truy vấn SQL để tìm User theo email
    // Dùng để kiểm tra đăng nhập, đăng ký, kiểm tra email đã tồn tại...
    Optional<User> findByEmail(String email);
}
