/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author adkm2
 */
public class Book {

    private String title;
    private String author;
    private String genre;
    private String ISBN;
    private boolean isAvailable;
    private LocalDate dueDate;
    private boolean isEBook;
    private int numOfPages;
    private String fileFormat;

    public Book() {
    }

    public Book(String title, String author, String genre, String ISBN, boolean isAvailable, LocalDate dueDate, boolean isEBook, int numOfPages, String fileFormat) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.ISBN = ISBN;
        this.isAvailable = true;
        this.dueDate = dueDate;
        this.isEBook = isEBook;
        this.numOfPages = numOfPages;
        this.fileFormat = fileFormat;
    }
    
        // Getter and Setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter for author
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter and Setter for genre
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Getter and Setter for ISBN
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    // Getter and Setter for isAvailable
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // Getter and Setter for dueDate
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // Getter and Setter for isEBook
    public boolean isEBook() {
        return isEBook;
    }

    public void setEBook(boolean isEBook) {
        this.isEBook = isEBook;
    }

    // Getter and Setter for numOfPages
    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    // Getter and Setter for fileFormat
    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

}
