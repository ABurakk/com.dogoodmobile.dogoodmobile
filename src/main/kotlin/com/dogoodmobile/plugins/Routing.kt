package com.dogoodmobile.plugins

import com.dogoodmobile.routes.volunteeringRoutes
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        volunteeringRoutes()
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
