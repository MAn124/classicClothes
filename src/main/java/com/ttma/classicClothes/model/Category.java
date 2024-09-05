package com.ttma.classicClothes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "category")
public class Category extends AbstractEntity<Long> implements Serializable {
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private List<Product> products =new ArrayList<>();
}
