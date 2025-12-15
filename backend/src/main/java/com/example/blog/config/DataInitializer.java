package com.example.blog.config;

import com.example.blog.model.Photo;
import com.example.blog.model.Post;
import com.example.blog.repository.PhotoRepository;
import com.example.blog.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(PostRepository postRepository, PhotoRepository photoRepository) {
        return args -> {
            // Initialize Posts
            if (postRepository.count() == 0) {
                Post post1 = new Post();
                post1.setTitle("Spring Boot 3.0 新特性解析");
                post1.setSummary("Spring Boot 3.0 基于 Spring Framework 6.0 构建，需要 Java 17 或更高版本。");
                post1.setContent("详细内容...");
                post1.setCoverUrl("https://picsum.photos/seed/blog1/800/400");
                post1.setReadTime(5);
                postRepository.save(post1);

                Post post2 = new Post();
                post2.setTitle("Vue 3 组合式 API 最佳实践");
                post2.setSummary("Composition API 改变了我们组织 Vue 组件逻辑的方式。");
                post2.setContent("详细内容...");
                post2.setCoverUrl("https://picsum.photos/seed/blog2/800/400");
                post2.setReadTime(8);
                postRepository.save(post2);
            }

            // Initialize Photos
            if (photoRepository.count() == 0) {
                for (int i = 1; i <= 6; i++) {
                    Photo photo = new Photo();
                    photo.setTitle("摄影作品 " + i);
                    photo.setUrl("https://picsum.photos/seed/" + (i + 100) + "/800/600");
                    photo.setLocation("Beijing, China");
                    photoRepository.save(photo);
                }
            }
        };
    }
}
