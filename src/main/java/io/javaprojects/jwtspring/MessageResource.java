package io.javaprojects.jwtspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MessageResource
{
    @RequestMapping({ "/message" })
    public String message()
    {
        return "Try something new everyday!";
    }
}