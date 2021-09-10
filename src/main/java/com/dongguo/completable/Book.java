package com.dongguo.completable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Dongguo
 * @date 2021/9/5 0005-12:15
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Book {
    private Integer id;
    private String bookName;
    private double price;
    private String author;
}