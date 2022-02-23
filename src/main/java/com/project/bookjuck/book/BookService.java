package com.project.bookjuck.book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.project.bookjuck.book.model.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookMapper mapper;

    public List<BookDto> bestBookList(){
        //원래는 날짜도 넣어서 해야하는데 알라딘에서 받아올 수 있는 최신 날짜가 Version=20131101 밖에 안됨.
        //SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");
        //Calendar calendar = Calendar.getInstance();
        //Date date = calendar.getTime();
        //String Ymd = day.format(date);
        String Ymd = "20131101";
        String serviceKey = "ttblznil6561633002";
        String url = "https://www.aladin.co.kr/ttb/api/ItemList.aspx";

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("ttbkey",serviceKey)
                .queryParam("QueryType", "Bestseller")
                .queryParam("MaxResults", 10)
                .queryParam("start", 1)
                .queryParam("SearchTarget", "Book")
                .queryParam("output", "js")
                .queryParam("Version", Ymd)
                .build(false); //여기 왜 false를 넣지..?
        List<BookDto> bookList = null;
        try {
            RestTemplate rest = new RestTemplate();
            rest.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

            HttpHeaders header = new HttpHeaders();
            header.setAccept(Arrays.asList());

            HttpEntity<String> entity = new HttpEntity<>(header);

            ResponseEntity<String> responseEntity = rest.exchange(builder.toString(), HttpMethod.GET, entity, String.class);
            String result = responseEntity.getBody();

            ObjectMapper om = new JsonMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonNode jsonNode = om.readTree(result);
            bookList = om.convertValue(jsonNode.path("item"), new TypeReference<List<BookDto>>() {});
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;

    }
}
