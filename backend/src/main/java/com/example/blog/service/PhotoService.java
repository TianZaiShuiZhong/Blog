package com.example.blog.service;

import com.example.blog.model.Photo;
import com.example.blog.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }
    
    public void deletePhoto(Long id) {
        photoRepository.deleteById(id);
    }
}
