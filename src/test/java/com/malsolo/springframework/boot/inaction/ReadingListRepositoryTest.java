package com.malsolo.springframework.boot.inaction;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.malsolo.springframework.boot.starter.SpringBootStarterApplication;
import com.malsolo.springframework.boot.starter.inaction.Book;
import com.malsolo.springframework.boot.starter.inaction.ReadingListRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootStarterApplication.class)
public class ReadingListRepositoryTest {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	ReadingListRepository repository;
	
	@Test
	public void testMarkerMethod() {
	}
	
	@Test
	public void testSave() {
		Book book = new Book();
		book.setReader("reader");
		book.setIsbn("isbn");
		book.setTitle("title");
		book.setAuthor("author");
		book.setDescription("description");
		
		repository.save(book);
		
		List<Book> list = this.jdbcTemplate.query("select id, reader, isbn, author, description from Book"
				, (rs, rownum) -> {
					Book newBook = new Book();
					newBook.setId(rs.getLong(1));
					newBook.setReader(rs.getString(2));
					newBook.setIsbn(rs.getString(2));
					newBook.setAuthor(rs.getString(2));
					newBook.setDescription(rs.getString(2));
					return newBook;
					});
		
		assertNotNull(list);
		assertEquals(1, list.size());
		assertNotNull(list.get(0).getId());
		assertEquals("reader", list.get(0).getReader());
	}

}
