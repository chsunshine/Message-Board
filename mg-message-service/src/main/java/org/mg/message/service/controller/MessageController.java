package org.mg.message.service.controller;

import java.util.Map;

import org.mg.message.service.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageService service;

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getMessages(long id) {
        return service.getMessages(id);
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addMessages(String msg, long id) {
        return service.addMessages(msg, id);
    }

    @RequestMapping(value = "/message", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteMessages(long id) {
        return service.deleteMessages(id);
    }
}
