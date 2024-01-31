package com.bananaapps.bananamusic.persistence.music;

import com.bananaapps.bananamusic.config.SpringConfig;
import com.bananaapps.bananamusic.domain.music.Backlog;
import com.bananaapps.bananamusic.domain.music.Song;
import com.bananaapps.bananamusic.persistence.music.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest()
@Import(com.bananaapps.bananamusic.config.SpringRepositoryConfig.class) // dev beans
@ComponentScan(basePackages = {"com.bananaapps.bananamusic.persistence.music"}) // prod beans
@AutoConfigureTestEntityManager
@ActiveProfiles({"dev"})
public class SongRelationshipTest {

	@Autowired
    SongRepository repo;

    @Autowired
	private PlatformTransactionManager transactionManager;		
	
	// @Test
	public void testBacklogAccessPositive() {
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus transaction = transactionManager.getTransaction(definition);

		Song song = repo.findOne(1L);

		Collection<Backlog> backlogRecords = song.getBacklogRecords();
		assertNotNull(backlogRecords, "backlogRecords should not be null");
		assertFalse(backlogRecords.isEmpty(),"This song should have inventory records");
		
		System.out.println("\n***Backlog records for song with id == 1:***");
		for (Backlog cur: backlogRecords) {
			System.out.println(cur);
		}
		System.out.println("***    ***\n");
		
        transactionManager.commit(transaction);
	}

	@Test
	public void testBacklogAddPositive() {
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus transaction = transactionManager.getTransaction(definition);

		Song song = repo.findOne(5L);
		assertTrue( song.getBacklogRecords().isEmpty(), "This song should not have inventory records");
		
		song.addBacklogRecord("Austin", 22);

        transactionManager.commit(transaction);
        transaction = transactionManager.getTransaction(definition);
        
        song = repo.findOne(5L);
		Collection<Backlog> inventoryRecords = song.getBacklogRecords();
		assertTrue(inventoryRecords.size()==1, "Should be one inventory record");
		
        transactionManager.commit(transaction);
	}

}
