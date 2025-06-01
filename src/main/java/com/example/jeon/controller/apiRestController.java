package com.example.jeon.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원가입 관련", description = "회원가입 API")
@RestController
@Log4j2
@RequestMapping("/api")
@RequiredArgsConstructor
public class apiRestController {



}
