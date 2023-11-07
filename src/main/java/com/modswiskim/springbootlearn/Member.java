package com.modswiskim.springbootlearn;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자
@AllArgsConstructor
@Getter
@Entity(name = "member_list")
public class Member {
    @Id // id 필드를 기본값으로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;    // DB table의 'id'와 매칭

    @Column(name = "name", nullable = false)
    private String name;    // DB table의 'name'과 매칭
}
