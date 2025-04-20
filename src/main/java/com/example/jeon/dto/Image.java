package com.example.jeon.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@SuperBuilder
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String uuid;
    @NonNull
    private String fileName;
    @NonNull
    private String fileUrl;

}
