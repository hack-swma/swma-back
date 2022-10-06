package com.swma.swma.domain.image.presentation;

import com.swma.swma.domain.image.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageUploadService imageUploadService;

    @PostMapping
    public ResponseEntity<String> uploadFile(@Valid @RequestPart(value = "files", required = false) MultipartFile multipartFilesList){
        String fileUrl = imageUploadService.execute(multipartFilesList);
        return new ResponseEntity<>(fileUrl, HttpStatus.OK);
    }
}
