package mahp.digimon.helpers

import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import io.r2dbc.spi.ConnectionFactory
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired

@Component
class SqlScriptRunner @Autowired constructor(
    private val connectionFactory: ConnectionFactory
) {
    fun executeScript(scriptPath: String) {
        val initializer = ConnectionFactoryInitializer().apply {
            setConnectionFactory(connectionFactory)
            setDatabasePopulator(
                CompositeDatabasePopulator().apply {
                    addPopulators(
                        ResourceDatabasePopulator(ClassPathResource(scriptPath))
                    )
                }
            )
        }
        initializer.afterPropertiesSet()
    }
}