package com.example.mylibrary.Database.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BookList implements Parcelable{

   private List<Book> bookList;

   public BookList() {
   }

   protected BookList(Parcel in) {
      bookList = in.createTypedArrayList(Book.CREATOR);
   }

   public static final Creator<BookList> CREATOR = new Creator<BookList>() {
      @Override
      public BookList createFromParcel(Parcel in) {
         return new BookList(in);
      }

      @Override
      public BookList[] newArray(int size) {
         return new BookList[size];
      }
   };

   @Override
   public int describeContents() {
      return 0;
   }

   @Override
   public void writeToParcel(Parcel dest, int flags) {
      dest.writeTypedList(bookList);
   }

   public List<Book> getBookList() {
      return bookList;
   }

   public void setBookList(List<Book> bookList) {
      this.bookList = bookList;
   }
}
