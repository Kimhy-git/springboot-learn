package com.modswiskim.springbootlearn.service;

import com.modswiskim.springbootlearn.domain.Article;
import com.modswiskim.springbootlearn.dto.AddArticleRequest;
import com.modswiskim.springbootlearn.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor    // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
}
