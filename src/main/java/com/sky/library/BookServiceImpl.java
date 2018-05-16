package com.sky.library;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.sky.test.BookTestConstants;

/**
 * @author Lakshmi
 * BookService Implementation
 *
 */
public class BookServiceImpl implements BookService {

	
	/**
	 * @author Lakshmi
	 * retrieveBook - This Implemented method is for getting BookDetails information ie Book object from the Stub
	 * @param String
	 * @return Book
	 */
	public Book retrieveBook(String bookReference) throws BookNotFoundException {
		// TODO Auto-generated method stub
		
		validateBook(bookReference);
		
	   return getBookDetails(bookReference);
	}

	
	/**
	 * @author Lakshmi
	 * This method is for getting BookDetails information ie Book object from the Stub and throw a BookNotFoundException where appropriate 
	 * @param String
	 * @return Book
	 * @throws BookNotFoundException
	 */
	Book getBookDetails(String bookReference) throws BookNotFoundException {
		BookRepositoryStub BookRepositoryStub = new BookRepositoryStub();	
		Book book = BookRepositoryStub.retrieveBook(bookReference);
		if(book == null) {
		    throw new BookNotFoundException();
		}
	  return book;
	}
	
	
	/**
	 * @author Lakshmi 
	 * getBookSummary - This Implemented method is for getting the book summary details from the Stub
	 * @param String
	 * @return Book
	 * @throws BookNotFoundException
	 */
	public String getBookSummary(String bookReference) throws BookNotFoundException {
		// TODO Auto-generated method stub
		
	    validateBook(bookReference);
		Book book = getBookDetails(bookReference);
		return getBookSummaryDetails(book);
	}
	

	
	/**
	 * @author Lakshmi 
	 * This method is used for Validation,ensures bookReference is prefixed by ‘BOOK­'
	 * @param String
	 * @throws BookNotFoundException
	 */
	void validateBook(String bookRef) throws BookNotFoundException
	{
		if(!bookRef.startsWith(BookRepositoryStub.BOOK_REFERENCE_PREFIX) ) {
			  throw new BookNotFoundException(BookTestConstants.BOOK_EXCEPTION_MESSAGE);
		}
	}
	
	/**
	 * @author Lakshmi 
	 * This method concatenates the reference, the title, and the first nine words of the review
	 * @param String
	 */	
	String getBookSummaryDetails(Book book) {
	     
		String output ="";
	    
		   String[] words =  book.getReview().split(" ");
		   if(words.length > 9) {  //  If the review is longer than nine words put an ellipsis (‘...’) at the end
			   output = "["+book.getReference() +"]"+" "+book.getTitle()+" - "+Arrays.stream(words, 0, 9).map(n->n.toString()).collect(Collectors.joining(" ")).replace(",", "").concat("..."); 
		   } else {
			   output = "["+book.getReference() +"]"+" "+book.getTitle()+" - "+book.getReview();
		   }
		return output;
	}
	
}
