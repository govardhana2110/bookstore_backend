package com.onlineBookStore.controller;

import com.onlineBookStore.model.Book;
import com.onlineBookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable String category) {
        List<Book> books = bookService.getBooksByCategory(category);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(
            @RequestParam("bookName") String bookName,
            @RequestParam("authorName") String authorName,
            @RequestParam("price") Double price,
            @RequestParam("category") String category,
            @RequestParam("rating") Integer rating,
            @RequestParam("totalRatings") Integer totalRatings,
            @RequestParam("status") String status,
            @RequestParam("description") String description,
            @RequestParam("availableQuantity") Integer availableQuantity,
            @RequestParam("image") MultipartFile image) throws IOException {

        String imageUrl = handleImageUpload(image);

        Book book = new Book();
        book.setBookName(bookName);
        book.setAuthorName(authorName);
        book.setPrice(price);
        book.setImageUrl(imageUrl);
        book.setCategory(category);
        book.setRating(rating);
        book.setTotalRatings(totalRatings);
        book.setStatus(status);
        book.setDescription(description);
        book.setAvailableQuantity(availableQuantity);

        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Long id,
            @RequestParam("bookName") String bookName,
            @RequestParam("authorName") String authorName,
            @RequestParam("price") Double price,
            @RequestParam("category") String category,
            @RequestParam("rating") Integer rating,
            @RequestParam("totalRatings") Integer totalRatings,
            @RequestParam("status") String status,
            @RequestParam("description") String description,
            @RequestParam("availableQuantity") Integer availableQuantity,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        Book updatedBook = bookService.getBookById(id); // Get existing book to maintain current values
        updatedBook.setBookName(bookName);
        updatedBook.setAuthorName(authorName);
        updatedBook.setPrice(price);
        updatedBook.setCategory(category);
        updatedBook.setRating(rating);
        updatedBook.setTotalRatings(totalRatings);
        updatedBook.setStatus(status);
        updatedBook.setDescription(description);
        updatedBook.setAvailableQuantity(availableQuantity);

        // Handle image upload for update
        if (image != null && !image.isEmpty()) {
            String imageUrl = handleImageUpload(image);
            updatedBook.setImageUrl(imageUrl); // Update image URL if a new image is provided
        }

        return new ResponseEntity<>(bookService.updateBook(id, updatedBook), HttpStatus.OK);
    }

    private String handleImageUpload(MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String uploadDir = "src/main/resources/static/uploads/";
            String fileName = image.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + fileName; // Store relative URL
        }
        return null; // Return null if no image is provided
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

