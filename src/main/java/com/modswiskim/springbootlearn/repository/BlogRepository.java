package com.modswiskim.springbootlearn.repository;

import com.modswiskim.springbootlearn.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
