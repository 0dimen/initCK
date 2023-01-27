package com.jojoldu.book.initCK.domain.user;

import com.jojoldu.book.initCK.domain.posts.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "Users")
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    
    @Column
    private String picture;
    
    @Enumerated(EnumType.STRING) // 데이터 베이스에 저장될 때, String타입으로 저장됨.
    @Column(nullable = false)
    private Role role;
    
    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }
    
    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;
        
        return this;
    }
    
    public String getRolekey(){
        return this.role.getKey();
    }
}
