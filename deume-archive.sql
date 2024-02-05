DROP DATABASE IF EXISTS deume_archive;
CREATE DATABASE deume_archive;
USE deume_archive;

CREATE TABLE `tb_maincategory`
(
    `idx`  INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '메인카테고리 기본키',
    `name` VARCHAR(255)                   NULL COMMENT '메인카테고리 이름'
);

CREATE TABLE `tb_subcategory`
(
    `idx`          INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '서브카테고리 기본키',
    `maincate_idx` INT                            NOT NULL COMMENT '(외래키) 메인카테고리 기본키',
    `name`         VARCHAR(255)                   NULL COMMENT '서브카테고리 이름'
);

CREATE TABLE `tb_contents`
(
    `idx`           INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '콘텐츠 기본키',
    `subcate_idx`   INT                            NULL COMMENT '(외래키) 서브카테고리 기본키',
    `type_idx`      INT                            NULL COMMENT '(외래키) 콘텐츠 유형 기본키',
    `name`          VARCHAR(255)                   NULL COMMENT '콘텐츠 이름',
    `source`        VARCHAR(255)                   NULL COMMENT '콘텐츠 출처',
    `explanation`   VARCHAR(255)                   NULL COMMENT '콘텐츠 설명',
    `display`       INT                            NULL DEFAULT 1 COMMENT '노출: 1 / 비노출:  0',
    `original`      VARCHAR(255)                   NULL COMMENT '사용자의 원본파일명',
    `saved`         VARCHAR(255)                   NULL COMMENT 'S3에 저장된 파일명',
    `upload_path`   VARCHAR(255)                   NULL DEFAULT 'contents/' COMMENT 'contents/',
    `register_date` DATETIME                       NULL DEFAULT CURRENT_TIMESTAMP COMMENT '콘텐츠 등록일',
    `update_date`   DATETIME                       NULL DEFAULT CURRENT_TIMESTAMP COMMENT '콘텐츠 수정일',
    `keyword`       VARCHAR(255)                   NULL COMMENT 'comma(,) 를 이용해서 구분'
);

CREATE TABLE `tb_packages`
(
    `idx`           INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '꾸러미 기본키',
    `subcate_idx`   INT                            NULL COMMENT '(외래키) 서브카테고리 기본키',
    `type_idx`      INT                            NULL COMMENT '(외래키)콘텐츠 유형 기본키',
    `name`          VARCHAR(255)                   NULL,
    `source`        VARCHAR(255)                   NULL,
    `explanation`   VARCHAR(255)                   NULL,
    `display`       INT                            NULL DEFAULT 1 COMMENT '노출: 1  / 비노출: 0',
    `original`      VARCHAR(255)                   NULL COMMENT '사용자의 원본파일명',
    `saved`         VARCHAR(255)                   NULL COMMENT 'S3에 저장된 파일명',
    `upload_path`   VARCHAR(255)                   NULL DEFAULT 'packages/' COMMENT 'packages/',
    `register_date` DATETIME                       NULL DEFAULT CURRENT_TIMESTAMP,
    `update_date`   DATETIME                       NULL DEFAULT CURRENT_TIMESTAMP,
    `keyword`       VARCHAR(255)                   NULL COMMENT 'comma (,) 를 사용해서 구분'
);

CREATE TABLE `tb_template`
(
    `idx`           INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '템플릿 기본키',
    `subcate_idx`   INT                            NULL COMMENT '(외래키) 서브카테고리 기본키',
    `type_idx`      INT                            NULL COMMENT '(외래키) 콘텐츠 유형 기본키',
    `name`          VARCHAR(255)                   NULL,
    `source`        VARCHAR(255)                   NULL,
    `explanation`   VARCHAR(255)                   NULL,
    `display`       INT                            NULL DEFAULT 1 COMMENT '노출: 1 / 비노출: 0',
    `original`      VARCHAR(255)                   NULL COMMENT '사용자의 원본파일명',
    `saved`         VARCHAR(255)                   NULL COMMENT 'S3에 저장된 파일명',
    `upload_path`   VARCHAR(255)                   NULL DEFAULT 'template/' COMMENT 'template/',
    `register_date` DATETIME                       NULL DEFAULT CURRENT_TIMESTAMP,
    `update_date`   DATETIME                       NULL DEFAULT CURRENT_TIMESTAMP,
    `keyword`       VARCHAR(255)                   NULL COMMENT 'comma(,)를 이용해서 구분'
);

CREATE TABLE `tb_template_thumbnail`
(
    `idx`          INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '템플릿 썸네일 기본키',
    `template_idx` INT                            NULL COMMENT '(외래키) 템플릿 기본키',
    `original`     VARCHAR(255)                   NULL,
    `saved`        VARCHAR(255)                   NULL,
    `upload_path`  VARCHAR(255)                   NULL DEFAULT 'template/thumbnails/' COMMENT 'template/thumbnails/',
    `saved_path`   VARCHAR(255)                   NULL DEFAULT 'template/thumbnails/saved/' COMMENT 'template/thumbnails/saved/',
    `orders`       VARCHAR(255)                   NULL
);

CREATE TABLE `tb_video`
(
    `idx`           INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '동영상 기본키',
    `subcate_idx`   INT                            NULL COMMENT '(외래키) 서브카테고리 기본키',
    `type_idx`      INT                            NULL COMMENT '(외래키)콘텐츠 유형 기본키',
    `name`          VARCHAR(255)                   NULL,
    `source`        VARCHAR(255)                   NULL,
    `explanation`   VARCHAR(255)                   NULL,
    `display`       INT                            NULL DEFAULT 1 COMMENT '노출: 1 / 비노출: 0',
    `original`      VARCHAR(255)                   NULL COMMENT '사용자의 원본파일명',
    `saved`         VARCHAR(255)                   NULL COMMENT 'S3에 저장된 파일명',
    `upload_path`   VARCHAR(255)                   NULL DEFAULT 'video/' COMMENT 'video/',
    `register_date` DATETIME                       NULL DEFAULT CURRENT_TIMESTAMP,
    `update_date`   DATETIME                       NULL DEFAULT CURRENT_TIMESTAMP,
    `keyword`       VARCHAR(255)                   NULL COMMENT 'comma(,)를 이용해서 구분'
);

CREATE TABLE `tb_gif`
(
    `idx`           INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '움짤 기본키',
    `subcate_idx`   INT                            NULL COMMENT '(외래키) 서브카테고리 기본키',
    `type_idx`      INT                            NULL COMMENT '(외래키) 콘텐츠 유형 기본키',
    `name`          VARCHAR(255)                   NULL COMMENT '움짤 이름',
    `source`        VARCHAR(255)                   NULL COMMENT '움짤 출처',
    `explanation`   VARCHAR(255)                   NULL COMMENT '움짤 설명',
    `display`       INT                            NULL DEFAULT 1 COMMENT '노출: 1 / 비노출:  0',
    `original`      VARCHAR(255)                   NULL COMMENT '사용자의 원본파일명',
    `saved`         VARCHAR(255)                   NULL COMMENT 'S3에 저장된 파일명',
    `upload_path`   VARCHAR(255)                   NULL DEFAULT 'gif/' COMMENT 'gif/',
    `register_date` DATETIME                       NULL DEFAULT CURRENT_TIMESTAMP COMMENT '움짤 등록일',
    `update_date`   DATETIME                       NULL DEFAULT CURRENT_TIMESTAMP COMMENT '움짤 수정일',
    `keyword`       VARCHAR(255)                   NULL COMMENT 'comma(,) 를 이용해서 구분'
);


CREATE TABLE `tb_video_thumbnail`
(
    `idx`         INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '동영상썸네일 기본키',
    `video_idx`   INT                            NULL COMMENT '(외래키) 동영상 기본키',
    `original`    VARCHAR(255)                   NULL,
    `saved`       VARCHAR(255)                   NULL,
    `upload_path` VARCHAR(255)                   NULL DEFAULT 'video/thumbnails/' COMMENT 'video/thumbnails/',
    `saved_path`  VARCHAR(255)                   NULL DEFAULT 'video/thumbnails/saved/' COMMENT 'video/thumbnails/saved/',
    `orders`      INT                            NULL
);

CREATE TABLE `tb_contents_count`
(
    `idx`         INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '콘텐츠 카운트 기본키',
    `contents_idx` INT                            NULL COMMENT '(외래키) 콘텐츠 기본키',
    `views`       INT                            NULL DEFAULT 0 COMMENT '콘텐츠 조회수',
    `download`    INT                            NULL DEFAULT 0 COMMENT '콘텐츠 다운로드 수'
);

CREATE TABLE `tb_packages_count`
(
    `idx`          INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '꾸러미 카운트 기본키',
    `packages_idx` INT                            NULL COMMENT '(외래키) 꾸러미 기본키',
    `views`        INT                            NULL DEFAULT 0,
    `download`     INT                            NULL DEFAULT 0
);

CREATE TABLE `tb_template_count`
(
    `idx`          INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '템플릿 카운트 기본키',
    `template_idx` INT                            NULL COMMENT '(외래키) 템플릿 기본키',
    `views`        INT                            NULL DEFAULT 0,
    `download`     INT                            NULL DEFAULT 0
);

CREATE TABLE `tb_video_count`
(
    `idx`       INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '동영상 카운트 기본키',
    `video_idx` INT                            NULL COMMENT '(외래키) 동영상 기본키',
    `views`     INT                            NULL DEFAULT 0,
    `download`  INT                            NULL DEFAULT 0
);
CREATE TABLE `tb_gif_count`
(
    `idx`       INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '움짤 카운트 기본키',
    `gif_idx`   INT                            NULL COMMENT '(외래키) 움짤 기본키',
    `views`     INT                            NULL DEFAULT 0,
    `download`  INT                            NULL DEFAULT 0
);
CREATE TABLE `tb_contents_thumbnail`
(
    `idx`         INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '콘텐츠 썸네일 기본키',
    `contents_idx` INT                            NULL COMMENT '(외래키) 콘텐츠 기본키',
    `original`    VARCHAR(255)                   NULL,
    `saved`       VARCHAR(255)                   NULL,
    `upload_path` VARCHAR(255)                   NULL DEFAULT 'contents/thumbnails/' COMMENT 'contents/thumbnails/',
    `saved_path`  VARCHAR(255)                   NULL DEFAULT 'contents/thumbnails/saved/' COMMENT 'contents/thumbnails/saved/',
    `orders`      VARCHAR(255)                   NULL
);

CREATE TABLE `tb_gif_thumbnail`
(
    `idx`         INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '움짤 썸네일 기본키',
    `gif_idx`     INT                            NULL COMMENT '(외래키) 콘텐츠 기본키',
    `original`    VARCHAR(255)                   NULL,
    `saved`       VARCHAR(255)                   NULL,
    `upload_path` VARCHAR(255)                   NULL DEFAULT 'gif/thumbnails/' COMMENT 'gif/thumbnails/',
    `saved_path`  VARCHAR(255)                   NULL DEFAULT 'gif/thumbnails/saved/' COMMENT 'gif/thumbnails/saved/',
    `orders`      VARCHAR(255)                   NULL
);

CREATE TABLE `tb_packages_thumbnail`
(
    `idx`          INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '꾸러미 썸네일 기본키',
    `packages_idx` INT                            NULL COMMENT '(외래키) 꾸러미 기본키',
    `original`     VARCHAR(255)                   NULL,
    `saved`        VARCHAR(255)                   NULL,
    `upload_path`  VARCHAR(255)                   NULL DEFAULT 'packages/thumbnails/' COMMENT 'packages/thumbnails/',
    `saved_path`   VARCHAR(255)                   NULL DEFAULT 'packages/thumbnails/saved/' COMMENT 'packages/thumbnails/saved/',
    `orders`       VARCHAR(255)                   NULL
);

CREATE TABLE `tb_contents_type`
(
    `idx`  INT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '콘텐츠 유형 기본키',
    `name` VARCHAR(255)                   NULL COMMENT '콘텐츠 유형 이름'
);

INSERT INTO tb_maincategory (name) VALUES ('메인카테고리1');
INSERT INTO tb_maincategory (name) VALUES ('메인카테고리2');

INSERT INTO tb_subcategory (maincate_idx, name) VALUES (1, '메인카테1-서브카테1');
INSERT INTO tb_subcategory (maincate_idx, name) VALUES (1, '메인카테1-서브카테2');
INSERT INTO tb_subcategory (maincate_idx, name) VALUES (1, '메인카테1-서브카테3');

INSERT INTO tb_subcategory (maincate_idx, name) VALUES (2, '메인카테2-서브카테4');
INSERT INTO tb_subcategory (maincate_idx, name) VALUES (2, '메인카테2-서브카테5');
