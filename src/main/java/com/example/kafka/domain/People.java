package com.example.kafka.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ZJ on 2018/5/30.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class People {

    private Long id;

    private String name;
}
