package com.example.comprehensive.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import com.example.comprehensive.document.NaverProductDocument;
import java.util.List;

@Repository
public interface NaverProductRepository extends ElasticsearchRepository<NaverProductDocument, String> {
    List<NaverProductDocument> findByTitleContaining(String keyword);
    List<NaverProductDocument> findByIsLiveTrue();
}