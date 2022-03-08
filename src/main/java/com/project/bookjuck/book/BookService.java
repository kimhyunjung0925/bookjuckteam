package com.project.bookjuck.book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.project.bookjuck.AuthenticationFacade;
import com.project.bookjuck.ResultVo;
import com.project.bookjuck.book.model.ApiSearchDto;
import com.project.bookjuck.book.model.bookinfo.Authors;
import com.project.bookjuck.book.model.bookinfo.BookEntity;
import com.project.bookjuck.book.model.BookDto;
import com.project.bookjuck.book.model.bookinfo.BookSubInfoEntity;
import com.project.bookjuck.book.model.bookinfo.PhraseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.print.Book;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookMapper mapper;

    @Autowired
    private AuthenticationFacade auth;

    //원래는 날짜도 넣어서 해야하는데 알라딘에서 받아올 수 있는 최신 날짜가 Version=20131101 밖에 안됨.
    //SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");
    //Calendar calendar = Calendar.getInstance();
    //Date date = calendar.getTime();
    //String Ymd = day.format(date);
    private String Ymd = "20131101";
    private String serviceKey = "ttblznil6561633002";
    private String listurl = "https://www.aladin.co.kr/ttb/api/ItemList.aspx";
    private String searchurl = "http://www.aladin.co.kr/ttb/api/ItemSearch.aspx";
    private String detailurl = "http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx";

    //API 레스트템플릿으로 불러오는 메소드
    public String restTemp(UriComponents builder){
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        HttpHeaders header = new HttpHeaders();
        header.setAccept(Arrays.asList());

        HttpEntity<String> entity = new HttpEntity<>(header);

        ResponseEntity<String> responseEntity = rest.exchange(builder.toString(), HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }
    //=====================================써치용========================================//
    //검색도서 불러오기
    public List<BookDto> searchBookList(ApiSearchDto searchDto){
        searchDto.setMaxResult(30);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(searchurl)
                .queryParam("ttbkey",serviceKey)
                .queryParam("Query", searchDto.getSearchWord())
                .queryParam("SearchTarget", searchDto.getSearchTarget2())
                .queryParam("MaxResults", searchDto.getMaxResult())
                .queryParam("start", searchDto.getStartIdx())
                .queryParam("output", "js")
                .queryParam("Version", Ymd)
                .build(false);
        List<BookDto> bookList = null;
        try {
            //API 메소드
            String result = restTemp(builder);

            ObjectMapper om = new JsonMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonNode jsonNode = om.readTree(result);
            bookList = om.convertValue(jsonNode.path("item"), new TypeReference<List<BookDto>>() {});
            //리스트 만들고 중복으로 인설트 안되도록 데이터베이스에서 검색한 후 없으면 넣는다.
            for (BookDto list:bookList) {
                ResultVo resultVo =mapper.configSel(list); //데이터베이스 확인 메소드
                switch (resultVo.getResult()){
                    case 0: //0이면 없다는 뜻. 인설트 해준다.
                        insBookApi(list);
                        break;
                    default: //나머지는 건너뛰기.
                        break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }


    //=====================================베스트, 신간========================================//
    //베스트,신간목록용 API 메소드. 굳이 책 데이터베이스에 넣을 이유 없을 것 같아서 클래스 dto로 따로 뺌
    public List<BookDto> getData(ApiSearchDto searchDto, String url) {
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("ttbkey",serviceKey)
                .queryParam("QueryType", searchDto.getType())
                .queryParam("SearchTarget", searchDto.getSearchTarget())
                .queryParam("MaxResults", searchDto.getMaxResult())
                .queryParam("start", searchDto.getStartIdx())
                .queryParam("Cover", "MidBig")
                .queryParam("output", "js")
                .queryParam("Version", Ymd)
                .build(false);
        List<BookDto> bookList = null;
        try {
            //API 메소드
            String result = restTemp(builder);

            ObjectMapper om = new JsonMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonNode jsonNode = om.readTree(result);
            bookList = om.convertValue(jsonNode.path("item"), new TypeReference<List<BookDto>>() {});
            //리스트 만들고 중복으로 인설트 안되도록 데이터베이스에서 검색한 후 없으면 넣는다.
            for (BookDto list:bookList) {
                ResultVo resultVo =mapper.configSel(list); //데이터베이스 확인 메소드
                switch (resultVo.getResult()){
                    case 0: //0이면 없다는 뜻. 인설트 해준다.
                        insBookApi(list);
                        break;
                    default: //나머지는 건너뛰기.
                        break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }

    //베스트도서 불러오기
    public List<BookDto> bestBookList(ApiSearchDto searchDto){
        searchDto.setType("Bestseller");
        searchDto.setMaxResult(30);
        List<BookDto> list = getData(searchDto,listurl);
        return list;
    }

    //신간도서 불러오기
    public List<BookDto> newBookList(ApiSearchDto searchDto){
        searchDto.setType("ItemNewSpecial");
        List<BookDto> list = getData(searchDto,listurl);
        return list;
    }

    //디테일 불러오기
    public BookEntity bookDetail(ApiSearchDto searchDto){
        BookEntity book = getDetailData(searchDto);
        return book;
    }


//=====================================리스트(국내도서, 해외도서)========================================//
    //국내도서, 해외도서 list 용 메소드
    /*
    국내도서, 해외도서 따로 데리고 오는 api가 없어서
    베스트, 신간목록들 db에 들어가있는거랑
    카테고리별로 검색해 db에 데이터 수기로 몇개씩 넣어서 쓰기로 했음
    */

//=====================================책디테일========================================//



    //디테일용 API 메소드
    public BookEntity getDetailData(ApiSearchDto searchDto){
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(detailurl)
                .queryParam("ttbkey",serviceKey)
                .queryParam("itemIdType",  "ISBN")
                .queryParam("itemId",  searchDto.getIsbn())
                .queryParam("Cover", "Big")
                .queryParam("output", "js")
                .queryParam("Version", Ymd)
                .queryParam("OptResult", "fulldescription,authors,fulldescription2,Toc,Story,phraseList,packing")
                .build(false);
        BookEntity bookEntity = null;
        List<Authors> authors = null;
        try {
            //API메소드
            String result = restTemp(builder);

            ObjectMapper om = new JsonMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonNode jsonNode = om.readTree(result);
            bookEntity = om.convertValue(jsonNode.path("item").path(0), new TypeReference<BookEntity>() {});


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bookEntity;
    }


    //bookApi를 DB에 넣는 메서드입니다.
    public int insBookApi(BookDto dto){
        return mapper.insBookApi(dto);
    }

    public List<BookDto> sel(BookDto dto){
        List<BookDto> bookDtoList = mapper.sel(dto);
        for (BookDto list:bookDtoList) {
            String title = auth.textOverCut(list.getTitle(),15);

            String author = list.getAuthor().replaceAll("\\(.+", "");
            list.setTitle(title);
            list.setAuthor(author);
        }
        return  bookDtoList;
    }

    public List<BookDto> sel2(BookDto dto){
        List<BookDto> bookDtoList = mapper.sel2(dto);
        return  bookDtoList;
    }

}