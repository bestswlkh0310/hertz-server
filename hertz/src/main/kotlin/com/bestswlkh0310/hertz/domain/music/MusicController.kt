package com.bestswlkh0310.hertz.domain.music

import com.bestswlkh0310.hertz.common.Api
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("${Api.v1}/music")
class MusicController {

    @GetMapping("/test")
    fun test(): String {
        return "Hello !"
    }

}