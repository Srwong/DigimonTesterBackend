package mahp.digimon.mongo.migrations

import com.github.cloudyrock.mongock.ChangeLog
import com.github.cloudyrock.mongock.ChangeSet
import org.bson.Document
import org.springframework.data.mongodb.core.MongoTemplate

@ChangeLog(order = "001")
class DbChangeLog {
    @ChangeSet(id = "User collection", order = "001", author = "system")
    fun createUserCollection(mongoTemplate: MongoTemplate) {
        val collection = mongoTemplate.getCollection("users")
        collection.insertOne(
            Document.parse(
                """
                    {
                        "name": "MyUSer",
                        "password": "password",
                        "decks": [
                            {
                                "name": "My first deck",
                                "main": [
                                    "BT1-025",
                                    "BT1-025",
                                    "BT1-026",
                                    "BT1-026"
                                ],
                                "side": [
                                    "BT3-020",
                                    "BT3-020"
                                ]
                            },
                            {
                                "name": "test",
                                "main": [
                                    "BT1-025",
                                    "BT1-025",
                                    "BT1-026",
                                    "BT1-026"
                                ],
                                "side": [
                                    "BT3-020",
                                    "BT3-020"
                                ]
                            }
                        ]
                    }
                """.trimIndent()
            )
        )
    }

}