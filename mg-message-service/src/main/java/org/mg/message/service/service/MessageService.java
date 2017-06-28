package org.mg.message.service.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mg.message.service.model.Message;
import org.mg.message.service.model.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    private Gson gson = new GsonBuilder().create();

    private SimpleDateFormat dataFormat = new SimpleDateFormat(
            "yyyy-MM-dd hh:mm");

    public Map<String, Object> getMessages(long id) {
        Map<String, Object> res = new HashMap<>();
        List<Message> messages = repository.findByUserId(id);
        List<Map<String, Object>> msgs = new ArrayList<>();
        for (Message message : messages) {
            Map<String, Object> msg = new HashMap<>();
            msg.put("id", message.getId());
            msg.put("msg", message.getMsg());
            msg.put("time", message.getTime());
            msgs.add(msg);
        }
        res.put("messages", msgs);
        return res;
    }

    public Map<String, Object> addMessages(String msg, long id) {
        Message message = new Message();
        message.setMsg(msg);
        message.setTime(dataFormat.format(new Date()));
        message.setUserId(id);
        message = repository.save(message);
        Map<String, Object> res = new HashMap<>();
        res.put("msg", message);
        return res;
    }

    public Map<String, Object> deleteMessages(long id) {
        repository.delete(id);
        Map<String, Object> res = new HashMap<>();
        res.put("state", 1);
        return null;
    }

}
