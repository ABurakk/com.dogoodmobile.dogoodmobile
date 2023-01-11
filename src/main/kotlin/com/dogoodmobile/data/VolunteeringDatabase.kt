package com.dogoodmobile.data

import com.dogoodmobile.data.model.Volunteering
import com.dogoodmobile.data.model.VolunteeringType
import org.litote.kmongo.coroutine.aggregate
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.sample

private val client = KMongo.createClient(connectionString = "mongodb://localhost:27017").coroutine
private val database = client.getDatabase("VolunteeringDatabase")

private val volunteering = database.getCollection<Volunteering>(collectionName = "volunteering")

suspend fun getVolunteeringForId(id: String): Volunteering? {
    return volunteering.findOneById(id)
}

suspend fun getRandomVolunteering(): Volunteering? {
    return volunteering.aggregate<Volunteering>(sample(1)).first()
}

suspend fun getAllVolunteering(): List<Volunteering> {
    return volunteering.find().toList()
}

suspend fun getVolunteersByType(type: VolunteeringType): List<Volunteering> {
    return volunteering.find(Volunteering::type eq type).toList()
}

