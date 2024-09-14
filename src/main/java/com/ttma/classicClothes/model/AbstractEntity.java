package com.ttma.classicClothes.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@MappedSuperclass
public class AbstractEntity<T extends Serializable> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private T id;

    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy, hh/mm/ss")
    @Column(name = "create_at")
    private Date createAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy, hh/mm/ss")
    @Column(name = "update_at")
    private Date updateAt;
}
