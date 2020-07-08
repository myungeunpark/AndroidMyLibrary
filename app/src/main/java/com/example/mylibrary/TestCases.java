package com.example.mylibrary;

import com.example.mylibrary.Database.Entity.Book;
import com.example.mylibrary.Database.Entity.User;

import java.util.ArrayList;
import java.util.List;

public class TestCases {


    static List<User> getUserTestCases(){

        List<User> users = new ArrayList<>();

        User user =  new User();
        user.setName("James");
        user.setPhone("925-111-1111");
        user.setImage("face1");
        users.add(user);

        user = new User();
        user.setName("Mike");
        user.setPhone("925-222-2222");
        user.setImage("face2");
        users.add(user);

        user = new User();
        user.setName("Betty");
        user.setPhone("925-333-3333");
        user.setImage("face3");
        users.add(user);

        user = new User();
        user.setName("Grace");
        user.setPhone("925-444-4444");
        user.setImage("face4");
        users.add(user);

        user = new User();
        user.setName("Jack");
        user.setPhone("925-555-5555");
        user.setImage("face5");
        users.add(user);

        user = new User();
        user.setName("Richard");
        user.setPhone("925-666-6666");
        user.setImage("face6");
        users.add(user);

        user = new User();
        user.setName("Susan");
        user.setPhone("925-777-7777");
        user.setImage("face7");
        users.add(user);

        user = new User();
        user.setName("Harry");
        user.setPhone("925-888-8888");
        user.setImage("face8");
        users.add(user);

        user = new User();
        user.setName("Phill");
        user.setPhone("925-999-9999");
        user.setImage("face9");
        users.add(user);

        user = new User();
        user.setName("Parkin");
        user.setPhone("925-111-1010");
        user.setImage("face10");
        users.add(user);

        return users;
    }


    static List<Book> getBookTestCases(){
        
        List <Book> books = new ArrayList<>();
        
        Book book = new Book();
        
        book.setTitle("Dogman 11");
        book.setAuthor("Dailky");
        book.setImage("book1");
        books.add(book);


        book = new Book();
        book.setTitle("Dogman 22");
        book.setAuthor("Dailky");
        book.setImage("book2");
        books.add(book);

        book = new Book();
        book.setTitle("Dogman 33");
        book.setAuthor("Dailky");
        book.setImage("book3");
        books.add(book);

        book = new Book();
        book.setTitle("Clean Code");
        book.setAuthor("Mike");
        book.setImage("book4");
        books.add(book);

        book = new Book();
        book.setTitle("Code Complete 2");
        book.setAuthor("Bill Gates");
        book.setImage("book5");
        books.add(book);

        book = new Book();
        book.setTitle("Lee Child");
        book.setAuthor("James K,T");
        book.setImage("book6");
        books.add(book);

        book = new Book();
        book.setTitle("The Night Fire");
        book.setAuthor("urine");
        book.setImage("book7");
        books.add(book);

        book = new Book();
        book.setTitle("Prince, the beautiful Ones");
        book.setAuthor("Prince J.k.");
        book.setImage("book8");
        books.add(book);

        book = new Book();
        book.setTitle("Canival Row");
        book.setAuthor("Column");
        book.setImage("book9");
        books.add(book);

        book = new Book();
        book.setTitle("Harry Potter");
        book.setAuthor("Tom Hanks");
        book.setImage("book10");
        books.add(book);
        return books;
    }
    
}
