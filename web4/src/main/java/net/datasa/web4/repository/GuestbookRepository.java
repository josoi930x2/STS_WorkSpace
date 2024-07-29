package net.datasa.web4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.datasa.web4.domain.entity.GuestbookEntity;

@Repository
public interface GuestbookRepository extends JpaRepository<GuestbookEntity, Integer> {

}
