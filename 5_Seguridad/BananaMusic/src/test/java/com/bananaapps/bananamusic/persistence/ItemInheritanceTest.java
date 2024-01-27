package com.bananaapps.bananamusic.persistence;

import com.bananaapps.bananamusic.domain.music.DownloadableMusicItem;
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

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ItemInheritanceTest {

    // Inject a repository
	@Autowired
	ItemRepository repo;

	// Used to control transactions
    @Autowired
	private PlatformTransactionManager transactionManager;		
	
	@Test
	public void testInheritancePositive() {
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus transaction = transactionManager.getTransaction(definition);

		// Get item with id == 1
		MusicItem mi = repo.findOne(1L);
		System.out.println(mi);
		
		// We know that this is true based on our database
		assertTrue(mi instanceof DownloadableMusicItem);

		// Get all items and output for illustration - no tests done.
		Collection<MusicItem> allItems = repo.findAll();
		allItems.forEach(i -> System.out.println(i));
        transactionManager.commit(transaction);
	}
}
