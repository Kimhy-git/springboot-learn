package com.modswiskim.springbootlearn.service;

import com.modswiskim.springbootlearn.domain.Article;
import com.modswiskim.springbootlearn.dto.AddArticleRequest;
import com.modswiskim.springbootlearn.dto.UpdateArticleRequest;
import com.modswiskim.springbootlearn.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor    // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가
    public Article save(AddArticleRequest request, String username) {
        return blogRepository.save(request.toEntity(username));
    }

    // 블로그 글 전체 조회
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // 블로그 글 단일 조회
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: "+id)); // id를 받아 엔티티를 조회하고 없으면 IllegalArgumentException 예외를 발생시킨다.
    }

    // 블로그 글 삭제
    public void delete(long id) {
        Article article = blogRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeArticleAuthor(article);
        blogRepository.deleteById(id);
    }

    // 블로그 글 수정
    @Transactional  // 매칭한 메서드를 하나의 트랜잭션으로 묶는 역할 (트랜잭션 : 데이터베이스의 데이터를 바꾸기 위한 단위 작업) 엔티티의 필드 값이 바뀌면 중간에 에러가 발생해도 제대로된 값 수정을 보장한다.
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: "+id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent());

        return article;
    }

    // 게시글을 작성한 유저인지 확인
    private static void authorizeArticleAuthor(Article article) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!article.getAuthor().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }
}
