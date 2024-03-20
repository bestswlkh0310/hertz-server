package com.bestswlkh0310.hertz.domain.health.api.controller

import com.bestswlkh0310.hertz.global.common.Api
import com.bestswlkh0310.hertz.global.base.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Api.Health.PATH)
class HealthController {

    @GetMapping(Api.Health.HEALTH)
    fun checkHealth(): ResponseEntity<Any> {
        return BaseResponse.ok("ok")
    }

}