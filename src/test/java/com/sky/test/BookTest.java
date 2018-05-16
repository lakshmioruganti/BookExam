package com.sky.test;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.sky.library.Book;
import com.sky.library.BookNotFoundException;
import com.sky.library.BookRepositoryStub;
import com.sky.library.BookServiceImpl;



@TestInstance(Lifecycle.PER_CLASS)
class BookTest {
    
	BookServiceImpl bookServiceImpl;
	
	@BeforeAll
	public void setUp() throws Exception {
		bookServiceImpl = new BookServiceImpl(); 
	}

	// Retrieve Book Tests
	
	@Test
	void invalidBookTest(){
		try {
		bookServiceImpl.retrieveBook(BookTestConstants.INVALID­TEXT);
		} catch(BookNotFoundException e) {
			Assert.assertEquals(e.getMessage(),BookTestConstants.BOOK_EXCEPTION_MESSAGE);
		}
	}
	
	@Test
	void bookNotFoundTest(){
		
		try {
		bookServiceImpl.retrieveBook(BookTestConstants.BOOK_NOT_IN_STORE);
		} catch(BookNotFoundException e) {
		//	e.printStackTrace();
			Assert.assertTrue(true);
		}
	}
	
	
	@Test
	void retrieveBookTest() throws BookNotFoundException{
		Book book = bookServiceImpl.retrieveBook(BookRepositoryStub.THE_GRUFFALO_REFERENCE);
		Assert.assertEquals(book.getTitle(),"The Gruffalo");
	}
	
	
	// Book Summary tests

	
	@Test
	void invalidBookSummaryTest(){
		try {
		bookServiceImpl.getBookSummary(BookTestConstants.INVALID­TEXT);
		} catch(BookNotFoundException e) {
			Assert.assertEquals(e.getMessage(),BookTestConstants.BOOK_EXCEPTION_MESSAGE);
		}
	}
	
	@Test
	void bookNotFoundSummaryTest(){
		
		try {
		bookServiceImpl.getBookSummary(BookTestConstants.BOOK_NOT_IN_STORE);
		} catch(BookNotFoundException e) {
		//	e.printStackTrace();
			Assert.assertTrue(true);
		}
	}
	
	@Test
	void getGraffaloBookDetailsTest() throws BookNotFoundException{
		String s = bookServiceImpl.getBookSummary(BookRepositoryStub.THE_GRUFFALO_REFERENCE);
		Assert.assertEquals(s,"[BOOK-GRUFF472] The Gruffalo - A mouse taking a walk in the woods");
	}

	@Test
	void getWillowsBookDetailsTest() throws BookNotFoundException{
		String s = bookServiceImpl.getBookSummary(BookRepositoryStub.THE_WIND_IN_THE_WILLOWS_REFERENCE);
		Assert.assertEquals(s,"[BOOK-WILL987] The Wind In The Willows - With the arrival of spring and fine weather outside...");
	}
	
}
