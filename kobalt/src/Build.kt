import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.kotlin.*

val repos = repos()

val kotlinVersion = "1.0.0-beta-4589"
val cucumberVersion = "1.2.5-SNAPSHOT"

val p = kotlinProject {

    name = "CakeParse"
    group = "me.sargunvohra.lib"
    artifactId = name
    version = "0.1.0-SNAPSHOT"

    sourceDirectories {
        path("src/main/kotlin")
        path("src/main/resources")
    }

    sourceDirectoriesTest {
        path("src/test/kotlin")
        path("src/test/resources")
    }

    dependencies {
        compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    }

    dependenciesTest {
        compile("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
        compile("info.cukes:cucumber-junit:$cucumberVersion")
        compile("info.cukes:cucumber-java8:$cucumberVersion")
        compile("info.cukes:cucumber-picocontainer:$cucumberVersion")
    }

    assemble {
        jar {
        }
    }

    test {
        includes("**/*Tests.class", "**/*Test.class")
    }
}
