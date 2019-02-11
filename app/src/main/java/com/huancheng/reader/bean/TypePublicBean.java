package com.huancheng.reader.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/1/9.
 */

public class TypePublicBean implements Serializable{
    private String authorPenname;//作者
    private String bookId;//书籍ID
    private String bookName;//书名
    private String bookStatus;//书籍状态
    private String categoryName;//书籍类别
    private String channelName;//书籍类型
    private String className;//频道分类
    private String coverImageUrl;//书籍封面
    private String id;//记录ID
    private String introduction;//作品介绍
    private String lastUpdateChapterDate;//最新更新章节时间
    private String status;//1.可抓取 2.屏蔽或下架 非正常
    private String wordCount;//当前字数
    private String direction;//判断是横向还是纵向展示

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getAuthorPenname() {
        return authorPenname;
    }

    public void setAuthorPenname(String authorPenname) {
        this.authorPenname = authorPenname;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLastUpdateChapterDate() {
        return lastUpdateChapterDate;
    }

    public void setLastUpdateChapterDate(String lastUpdateChapterDate) {
        this.lastUpdateChapterDate = lastUpdateChapterDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWordCount() {
        return wordCount;
    }

    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }
}
