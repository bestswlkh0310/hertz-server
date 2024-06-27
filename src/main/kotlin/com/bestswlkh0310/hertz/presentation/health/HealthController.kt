package com.bestswlkh0310.hertz.presentation.health

import com.bestswlkh0310.hertz.presentation.common.Api
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Api.Health.PATH)
class HealthController {
    @GetMapping
    fun health() = "Server Alive"
}