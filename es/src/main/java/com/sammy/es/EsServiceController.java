package com.sammy.es;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.StringReader;
import java.util.*;

@Controller
@RequestMapping("/")
public class EsServiceController {

    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private LeaderRepository leaderRepository;

    @RequestMapping(value = "/save")
    public ResponseEntity save(Integer id, String title, String content) {

        if (id == null)
            id = 1;
        Notice article = new Notice();
        article.setId(id);
        article.setTitle(title);
        article.setContent(content);
        article.setCreateDateTime(new Date(System.currentTimeMillis()));
        noticeRepository.save(article);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/leader/save")
    public ResponseEntity saveLeader(String name, String zw) {
        Leader leader = new Leader();
        leader.setId(UUID.randomUUID().toString());
        leader.setName(name);
        leader.setZw(zw);
        leader.setCreateDate(new Date(System.currentTimeMillis()));
        leaderRepository.save(leader);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping("/search")
    public ResponseEntity<List<Notice>> search(String title, @PageableDefault(page = 0, value = 10) Pageable page) {
        //按标题进行搜索
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("title", "*" + title + "*");
//        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();

        //如果实体和数据的名称对应就会自动封装，pageable分页参数
        Iterable<Notice> listIt = noticeRepository.search(queryBuilder, page);

        //Iterable转list
        List<Notice> list = new ArrayList<>();
        Iterator<Notice> iter = listIt.iterator();
        while (iter.hasNext()) {
            list.add(iter.next());
        }
        ResponseEntity<List<Notice>> responseEntity = new ResponseEntity<List<Notice>>(list, HttpStatus.OK);
        return responseEntity;
    }

    @ResponseBody
    @RequestMapping("/leader/search")
    public ResponseEntity<List<Leader>> searchLeader(String name, String zw, @PageableDefault(page = 0, size = 5) Pageable page) {
        //按标题进行搜索
        QueryBuilder queryBuilder = null;
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(zw))
            queryBuilder = QueryBuilders.matchAllQuery();
        if (!StringUtils.isEmpty(name))
            queryBuilder = QueryBuilders.matchQuery("name", name);
        if (!StringUtils.isEmpty(zw))
            queryBuilder = QueryBuilders.matchQuery("zw", zw);
//        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<em>");
        highlightBuilder.postTags("</em>");
        highlightBuilder.field("zw");
        HighlightBuilder.Field ff = new HighlightBuilder.Field("zw");
        NativeSearchQueryBuilder sq = new NativeSearchQueryBuilder();
        sq.withHighlightBuilder(highlightBuilder);
        sq.withHighlightFields(ff);
//        FieldSortBuilder sb = SortBuilders.fieldSort("zw").order(SortOrder.ASC);
        sq.withPageable(page);
        sq.withQuery(queryBuilder);
//        sq.withSort(sb);
        //如果实体和数据的名称对应就会自动封装，pageable分页参数
        Iterable<Leader> listIt = leaderRepository.search(sq.build());

        //Iterable转list
        List<Leader> list = new ArrayList<>();
        Iterator<Leader> iter = listIt.iterator();
        while (iter.hasNext()) {
            list.add(iter.next());
        }
        ResponseEntity<List<Leader>> responseEntity = new ResponseEntity<List<Leader>>(list, HttpStatus.OK);
        return responseEntity;
    }

    public static void main(String[] args) {
        String text = "大家好，我叫sammy，这个是我做的分词用的分词器";
        //独立Lucene实现
        StringReader re = new StringReader(text);
        IKSegmenter ik = new IKSegmenter(re, true);
        Lexeme lex = null;
        try {
            while ((lex = ik.next()) != null) {
                System.out.print(lex.getLexemeText() + "|");
            }
        } catch (Exception e) {
        }
    }
}