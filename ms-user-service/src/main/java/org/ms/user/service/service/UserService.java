package org.ms.user.service.service;

import java.util.HashMap;
import java.util.Map;

import org.ms.user.service.model.User;
import org.ms.user.service.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Map<String, Object> login(String username, String password) {
        Map<String, Object> res = new HashMap<String, Object>();
        User user = repository.findByUsername(username);
        if (user == null) {
            res.put("state", 0);
            res.put("msg", "用户名不存在！");
        } else if (!user.getPassword().equals(password)) {
            res.put("state", 0);
            res.put("msg", "密码错误！");
        } else {
            res.put("state", 1);
            res.put("id", user.getId());
            res.put("msg", "登录成功！");
        }
        return res;
    }

}
