package com.example.comprehensive.repository;

public interface LiveProductProjection {
    String getId();

    String getLiveId();

    Boolean getLive(); // getIsLive → getLive로 변경

    java.time.LocalDateTime getLastUpdated();

    String getLiveUrl();

    String getPlatform();

    String getThumbnail();

    String getTitle();

    SellerInfoProjection getSellerInfo();

    interface SellerInfoProjection {
        String getName();

        String getUrl();

        String getImage();
    }
}
