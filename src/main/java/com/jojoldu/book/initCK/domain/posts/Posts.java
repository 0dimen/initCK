package com.jojoldu.book.initCK.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.persister.walking.spi.CollectionDefinition;

import javax.persistence.*;

@Getter // 클래스 내 모든 필드의 Getter 자동 생성.
@NoArgsConstructor // 기본 생성자 자동 추가 (public Posts(){} )
@Entity // 테이블과 링크될 클래임임을 명시. PostsManager.java -> posts_manager table
public class Posts extends BaseTimeEntity{
    @Id // PK 필드.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성 규칙. GenerationType.IDENTITY -> auto Increment
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 빌더 패턴 클래스 생성. 생성자 상단에 선언 -> 생성자에 포함된 필드만 빌더에 포함.
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
