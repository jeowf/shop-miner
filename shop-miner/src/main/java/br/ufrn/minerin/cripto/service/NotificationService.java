package br.ufrn.minerin.cripto.service;

import br.ufrn.minerin.cripto.model.Notification;
import br.ufrn.minerin.cripto.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	public List<Notification> findAll() {
		return notificationRepository.findAll();
	}


	public Optional<Notification> findOne(Integer id) {
		return notificationRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Notification save(Notification entity) {
		System.out.println("Service is ok. It is the repository");
		return notificationRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Notification entity) {
		notificationRepository.delete(entity);
	}

}