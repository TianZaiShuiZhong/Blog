package com.example.blog.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;
    private String location;
    private String description;
}
