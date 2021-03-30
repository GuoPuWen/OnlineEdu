package com.guli.service_edu.controller;

import com.guli.R;
import com.guli.service_edu.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author 四五又十
 * @version 1.0
 * @date 2020/10/2 15:14
 */
@RestController
@RequestMapping("/service_edu/user")
@CrossOrigin
public class EduLoginController {
    @PostMapping("/login")
    public R login(@RequestBody User user) {
        System.out.println(user);

        return R.ok().data("token", "admin");
    }

    @GetMapping("/info")
    public R info() {
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "");
    }
}
