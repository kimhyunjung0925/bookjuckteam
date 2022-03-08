package com.project.bookjuck.book.model.bookinfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BookSubInfoEntity {
    private String subTitle;
    private String originalTitle;
    private String toc;
    private String story;
    private List<PhraseList> phraseList;
    private List<Authors> authors;
    private Packing packing;
    private int itemPage;
}
