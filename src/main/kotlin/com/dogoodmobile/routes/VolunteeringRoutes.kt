package com.dogoodmobile.routes

import com.dogoodmobile.data.getAllVolunteering
import com.dogoodmobile.data.getRandomVolunteering
import com.dogoodmobile.data.getVolunteeringForId
import com.dogoodmobile.data.getVolunteersByType
import com.dogoodmobile.data.model.Volunteering
import com.dogoodmobile.data.model.VolunteeringType
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.volunteeringRoutes() {
    get("/volunteerings") {
        val volunteerings = getAllVolunteering()
        call.respond(volunteerings)
    }
    get("/volunteerings/{id}") {
        val id = call.parameters["id"]
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Missing parameter: id")
        } else {
            val volunteering = getVolunteeringForId(id)
            if (volunteering == null) {
                call.respond(HttpStatusCode.NotFound, "Volunteering not found")
            } else {
                call.respond(volunteering)
            }
        }
    }
    get("/volunteerings/random") {
        val volunteering = getRandomVolunteering()
        if (volunteering == null) {
            call.respond(HttpStatusCode.NotFound, "No volunteerings found")
        } else {
            call.respond(volunteering)
        }
    }
    get("/volunteerings/type/{type}") {
        val type = call.parameters["type"]
        if (type == null) {
            call.respond(HttpStatusCode.BadRequest, "Missing parameter: type")
        } else {
            val volunteerings = getVolunteersByType(VolunteeringType.valueOf(type))
            if (volunteerings.isEmpty()) {
                call.respond(HttpStatusCode.NotFound, "No volunteerings found")
            } else {
                call.respond(volunteerings)
            }
        }
    }
}
