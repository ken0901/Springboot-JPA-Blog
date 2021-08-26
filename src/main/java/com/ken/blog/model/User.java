package com.ken.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// ORM -> object mapping to table
@Entity // User Class is stored in database
//@DynamicInsert // when it's insert null field data can be removed
public class User {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // follow db numbering which is connected a project
    private int id; //sequence, auto-increment

    @Column(nullable = false, length = 30, unique = true)
    private String username; // id

    @Column(nullable = false, length = 100) //length is 100 because implement encrypt with hashing
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // DB has only RoleType
    // @ColumnDefault("'user'") // Enum is more accuracy
    @Enumerated(EnumType.STRING)
    private RoleType role; //ADMIN, USER

    // manually insert - Timestamp.valueOf(LocalDateTime.now())
    @CreationTimestamp // auto insert time
    private Timestamp createDate;
}
