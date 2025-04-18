package com.example.comprehensive.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import com.example.comprehensive.document.KakaoProductDocument;
import java.util.List;

@Repository
public interface KakaoProductRepository extends ElasticsearchRepository<KakaoProductDocument, String> {
    List<KakaoProductDocument> findByTitleContaining(String keyword);
    List<KakaoProductDocument> findByIsLiveTrue();
}
