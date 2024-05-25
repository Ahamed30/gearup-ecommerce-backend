package com.gearup.ecommercebackend.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacedItemsRepository extends JpaRepository<PlacedItems, Long> {
}
