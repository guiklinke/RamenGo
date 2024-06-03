package com.RamenGo.RamenGo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "broths")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Broth {
    @Id
    private String id;
    private String imageInactive;
    private String imageActive;
    private String name;
    private String description;
    private Integer price;

}
