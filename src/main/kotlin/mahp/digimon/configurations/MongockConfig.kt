package mahp.digimon.configurations

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import io.mongock.driver.mongodb.springdata.v3.SpringDataMongoV3Driver
import io.mongock.runner.springboot.MongockSpringboot
import io.mongock.runner.springboot.base.MongockInitializingBeanRunner
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class MongockConfig {
    @Bean
    fun mongockInitializer(
        mongoClient: MongoClient,
        applicationContext: ApplicationContext
    ): MongockInitializingBeanRunner {
        val uri = "mongodb://user:pass@mongo-db:27017/digimon_db"
        val mongoClient = MongoClients.create(uri)

        val driver = SpringDataMongoV3Driver.withDefaultLock(
            MongoTemplate(mongoClient, "digimon_db")
        )

        return MongockSpringboot.builder()
            .setDriver(driver)
            .setSpringContext(applicationContext)
            .addMigrationScanPackage("mahp.digimon.mongo.migrations")
            .buildInitializingBeanRunner()
    }
}
