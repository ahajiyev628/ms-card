package org.example.spring.client;

import org.example.spring.model.client.UserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@Profile(value = "default")
@FeignClient(name = "ms-users",
             url = "${client.urls.ms-users}")
public interface UserClient {
    @PostMapping(path = "/v1/users")
    void saveUser(@RequestBody UserRequest user);
}
