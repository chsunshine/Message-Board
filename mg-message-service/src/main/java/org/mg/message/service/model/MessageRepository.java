package org.mg.message.service.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface MessageRepository extends JpaRepository<Message, Long>{

    public List<Message> findByUserId(long id);

}
