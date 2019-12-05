package br.ufrn.minerin.cripto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.minerin.cripto.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
	Notification findById(int id);
}
