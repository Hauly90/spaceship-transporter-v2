package com.greenfox.mockexam.repositories;

import com.greenfox.mockexam.models.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    @Query("SELECT shd from shipDetails shd where shd.id=?1")
    public List<Ship> getShipById(long id);
}
