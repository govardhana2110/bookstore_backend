package com.onlineBookStore.response;

import com.onlineBookStore.model.Book;

import java.util.List;

public class PaginatedResponse {
    private List<Book> books;
    private long totalElements;

    public PaginatedResponse(List<Book> books, long totalElements) {
        this.books = books;
        this.totalElements = totalElements;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
