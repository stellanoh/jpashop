package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    EntityManager em;


    @Test
    public void saveItemTest() throws Exception {
        //given
        Book book = new Book();
        book.setName("kim");
        //when
        Long saveId = itemService.saveItem(book);

        //then
        em.flush();
        assertEquals(book, itemRepository.findOne(saveId));
    }

    @Test(expected = NotEnoughStockException.class)
    public void itemRemove() throws Exception {
        //given
        Book book = new Book();
        book.setName("kim");
        book.setStockQuantity(0);
        //when
        book.removeStock(1);

        //then
        fail("예외가 발생해야 합니다!");
    }

    @Test
    public void addItem() throws Exception {
        //given
        Book book = new Book();
        book.setName("kim");
        book.setStockQuantity(0);
        //when
        book.addStock(2);
        Long itemId = itemService.saveItem(book);
        //then
        assertEquals(book.getStockQuantity(), itemRepository.findOne(itemId).getStockQuantity());
    }


}