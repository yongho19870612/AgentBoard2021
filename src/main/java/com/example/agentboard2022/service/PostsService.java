package com.example.agentboard2022.service;

import com.example.agentboard2022.domain.posts.Posts;
import com.example.agentboard2022.domain.posts.PostsRepository;
import com.example.agentboard2022.web.dto.PostsListResponseDto;
import com.example.agentboard2022.web.dto.PostsResponseDto;
import com.example.agentboard2022.web.dto.PostsSaveRequestDto;
import com.example.agentboard2022.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //업데이트 기능
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    //조회하기 기능
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }

    //게시판에 데이터 뿌려주기 기능
    @Transactional
    public List<PostsListResponseDto> findAllDesc(){
       // return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        return postsRepository.findAllDesc().stream().map(posts -> new PostsListResponseDto(posts)).collect(Collectors.toList());
    }

    //게시판 데이터 삭제
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(posts);

        //posts에 id로 추출한 값을 저장하고 이를 삭제하는 로직

    }
}
