package com.example.comprehensive.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(indexName = "damoa.naver_product")
public class NaverProductDocument {
    @Id
    private String id;
    
    @Field(type = FieldType.Keyword)
    private String liveId;
    
    @Field(type = FieldType.Boolean)
    private Boolean isLive;
    
    @Field(type = FieldType.Date)
    private Date lastUpdated;
    
    @Field(type = FieldType.Text)
    private String liveUrl;
    
    @Field(type = FieldType.Keyword)
    private String platform;
    
    @Field(type = FieldType.Nested)
    private List<Map<String, Object>> products;
    
    @Field(type = FieldType.Object)
    private Map<String, Object> sellerInfo;
    
    @Field(type = FieldType.Text)
    private String thumbnail;
    
    @Field(type = FieldType.Text, analyzer = "standard")
    private String title;
    
    // 기본 생성자
    public NaverProductDocument() {
    }
    
    // 모든 필드를 포함한 생성자
    public NaverProductDocument(String id, String liveId, Boolean isLive, Date lastUpdated, 
                               String liveUrl, String platform, List<Map<String, Object>> products, 
                               Map<String, Object> sellerInfo, String thumbnail, String title) {
        this.id = id;
        this.liveId = liveId;
        this.isLive = isLive;
        this.lastUpdated = lastUpdated;
        this.liveUrl = liveUrl;
        this.platform = platform;
        this.products = products;
        this.sellerInfo = sellerInfo;
        this.thumbnail = thumbnail;
        this.title = title;
    }
    
    // Getter 메서드
    public String getId() {
        return id;
    }
    
    public String getLiveId() {
        return liveId;
    }
    
    public Boolean getIsLive() {
        return isLive;
    }
    
    public Date getLastUpdated() {
        return lastUpdated;
    }
    
    public String getLiveUrl() {
        return liveUrl;
    }
    
    public String getPlatform() {
        return platform;
    }
    
    public List<Map<String, Object>> getProducts() {
        return products;
    }
    
    public Map<String, Object> getSellerInfo() {
        return sellerInfo;
    }
    
    public String getThumbnail() {
        return thumbnail;
    }
    
    public String getTitle() {
        return title;
    }
    
    // Setter 메서드
    public void setId(String id) {
        this.id = id;
    }
    
    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }
    
    public void setIsLive(Boolean isLive) {
        this.isLive = isLive;
    }
    
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }
    
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    
    public void setProducts(List<Map<String, Object>> products) {
        this.products = products;
    }
    
    public void setSellerInfo(Map<String, Object> sellerInfo) {
        this.sellerInfo = sellerInfo;
    }
    
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String toString() {
        return "NaverProductDocument{" +
                "id='" + id + '\'' +
                ", liveId='" + liveId + '\'' +
                ", isLive=" + isLive +
                ", lastUpdated=" + lastUpdated +
                ", liveUrl='" + liveUrl + '\'' +
                ", platform='" + platform + '\'' +
                ", products=" + products +
                ", sellerInfo=" + sellerInfo +
                ", thumbnail='" + thumbnail + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}