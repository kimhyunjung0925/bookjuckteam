package com.project.bookjuck.cscenter.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FaqEntity {

    private int ifaq;
    private String faq_title;
    private String faq_ctnt;

}
