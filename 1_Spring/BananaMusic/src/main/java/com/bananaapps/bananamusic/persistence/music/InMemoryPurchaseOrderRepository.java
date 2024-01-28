package com.bananaapps.bananamusic.persistence.music;

import com.bananaapps.bananamusic.domain.music.PurchaseOrder;
import com.bananaapps.bananamusic.domain.music.PurchaseOrderLineSong;
import com.bananaapps.bananamusic.domain.user.User;
import com.google.common.collect.Sets;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class InMemoryPurchaseOrderRepository implements PurchaseOrderRepository {
    private Set<PurchaseOrder> orders = Sets.newHashSet(
            new PurchaseOrder(1L, 1, true, LocalDate.now(), new User(1), List.of(new PurchaseOrderLineSong()))
    );

    @Override
    public PurchaseOrder getById(Long id) {
        return orders.stream().filter(order -> order.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public Collection<PurchaseOrder> findAll() {
        return orders;
    }

    @Override
    public PurchaseOrder save(PurchaseOrder order) {
        order.setId(new Random().nextLong());
        orders.add(order);
        return order;
    }

    @Override
    public void delete(PurchaseOrder order) {
        for (PurchaseOrder o : orders) {
            if (o.getId() == order.getId()) {
                orders.remove(o);
                break;
            }
        }
    }
}
