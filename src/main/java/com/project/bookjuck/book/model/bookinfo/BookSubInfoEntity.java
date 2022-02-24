package com.project.bookjuck.book.model.bookinfo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookSubInfoEntity {
    private String subTitle;
    private String originalTitle;
    private String toc;
    private String story;
    private List<PhraseList> phraseList;
    private List<Authors> authors;
    private int itemPage;
}
