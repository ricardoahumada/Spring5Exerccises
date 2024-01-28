package com.bananaapps.bananamusic.persistence.music;

import com.bananaapps.bananamusic.domain.music.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*@Repository
@Transactional*/
public interface JpaPurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>, PurchaseOrderRepository {
}
