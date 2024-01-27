package com.bananaapps.bananamusic.persistence;

import com.bananaapps.bananamusic.domain.music.Inventory;
import com.bananaapps.bananamusic.domain.music.MusicItem;
import com.bananaapps.bananamusic.persistence.music.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ItemRelationshipTest {

    // Inject a repository
	@Autowired
	ItemRepository repo;

	// Used to control transactions
    @Autowired
	private PlatformTransactionManager transactionManager;		
	
	// @Test
	public void testInventoryAccessPositive() {
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus transaction = transactionManager.getTransaction(definition);

		// Get item with id == 1
		MusicItem mi = repo.findOne(1L);

		// DONE: Get its inventory records
		Collection<Inventory> inventoryRecords = mi.getInventoryRecords();
		assertNotNull(inventoryRecords, "inventoryRecords should not be null");
		assertFalse(inventoryRecords.isEmpty(),"This item should have inventory records");
		
		// Print out all the records.
		System.out.println("\n***Inventory records for item with id == 1:***");
		for (Inventory cur: inventoryRecords) {
			System.out.println(cur);
		}
		System.out.println("***    ***\n");
		
        transactionManager.commit(transaction);
	}

	@Test
	public void testInventoryAddPositive() {
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus transaction = transactionManager.getTransaction(definition);

		// Get item with id == 5
		MusicItem mi = repo.findOne(5L);
		// Test to make sure it's empty (we know it is based on our data).
		assertTrue( mi.getInventoryRecords().isEmpty(), "This item should not have inventory records");
		
		// DONE: Add an inventory record using the addInventoryRecord helper method
		mi.addInventoryRecord("Austin", 22);

		// Commit the TX, start a new one.
        transactionManager.commit(transaction);
        transaction = transactionManager.getTransaction(definition);
        
        // Get the item again in the new TX
        mi = repo.findOne(5L);
		// Get its inventory records - there should be one only.
		Collection<Inventory> inventoryRecords = mi.getInventoryRecords();
		assertTrue(inventoryRecords.size()==1, "Should be one inventory record");
		
        transactionManager.commit(transaction);
	}

}
