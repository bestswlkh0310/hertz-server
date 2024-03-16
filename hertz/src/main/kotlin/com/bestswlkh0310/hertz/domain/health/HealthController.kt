package com.bestswlkh0310.hertz.domain.health

import com.bestswlkh0310.hertz.common.Api
import com.bestswlkh0310.hertz.global.base.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("${Api.v1}/health")
class HealthController {

    @GetMapping("/", "")
    fun checkHealth(): ResponseEntity<Any> {
        val response = BaseResponse<Any>(
            status = HttpStatus.OK.value(),
            message = "ok"
        )
        return ResponseEntity.ok(response)
    }

}