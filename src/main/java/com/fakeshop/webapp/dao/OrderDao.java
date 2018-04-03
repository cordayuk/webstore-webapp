package com.fakeshop.webapp.dao;

import com.fakeshop.webapp.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends CrudRepository<Order, Long> {
    @Query("select t from Order t where t.user.id=:#{principal.id}")
    Iterable<Order> findAllUserOrders();

    @Query("select t from Order t where t.user.id=:#{principal.id} and t.id = :orderId")
    Order findSpecificUserOrder(@Param("orderId") Long orderId);
}
