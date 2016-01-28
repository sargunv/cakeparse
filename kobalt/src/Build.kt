import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.kotlin.*

val repos = repos()


val p = kotlinProject {

    name = "kake"
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
    }

    dependenciesTest {
        compile("org.jetbrains.spek:spek:0.1.188")
    }

    assemble {
        jar {
        }
    }

    test {
        includes("**/*Tests.class", "**/*Test.class")
    }
}
