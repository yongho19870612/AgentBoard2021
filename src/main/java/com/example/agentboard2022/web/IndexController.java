package com.example.agentboard2022.web;

import com.example.agentboard2022.config.auth.LoginUser;
import com.example.agentboard2022.config.auth.dto.SessionUser;
import com.example.agentboard2022.service.PostsService;
import com.example.agentboard2022.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        //서버 템플릿엔진에서 사용할 수 있는 객체를 저장할 수 있음
        //postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달
        model.addAttribute("posts", postsService.findAllDesc());

        //세션에 저장된 값이 있을 때만 model에 userName으로 등록
        //세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인 버튼이 보이게 됨
        if (user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        //postsService.findById(id)로 가져온 결과를 posts로 posts-update.mustache에 전달
        return "posts-update";
    }

}
