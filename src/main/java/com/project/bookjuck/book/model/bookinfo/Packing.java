package com.project.bookjuck.book.model.bookinfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Packing {
    private int weight;
    private int sizeDepth;
    private int sizeHeight;
    private int sizeWidth;
}
