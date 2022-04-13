package com.example.agentboard2022.web;

import com.example.agentboard2022.service.PostsService;
import com.example.agentboard2022.web.dto.PostsResponseDto;
import com.example.agentboard2022.web.dto.PostsSaveRequestDto;
import com.example.agentboard2022.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    //업데이트 기능
    @PutMapping("/api/v1/posts/{id}")
    public Long updeate(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    //조회하기
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    //삭제하기
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
