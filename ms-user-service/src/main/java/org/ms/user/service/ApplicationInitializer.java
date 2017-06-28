package org.ms.user.service;

import org.ms.user.service.model.User;
import org.ms.user.service.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInitializer {

    @Autowired
    private UserRepository repository;

    @Autowired
    public void run() {
        long count = repository.count();

        if (count < 1) {
            importAccounts();
        }
    }

    private void importAccounts() {
        User a = new User();

        a.setPassword("test");
        a.setUsername("test");
        repository.save(a);
    }

}
