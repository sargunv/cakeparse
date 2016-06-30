import com.beust.kobalt.*
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.publish.bintray

object Versions {
    val kotlin = "1.0.3"
    val testng = "6.9.10"
}

@Suppress("unused")
val p = project {

    name = "cakeparse"
    group = "me.sargunvohra.lib"
    artifactId = name
    version = "1.1.0"

    dependencies {
    	compile("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    }

    dependenciesTest {
    	compile("org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}")
        compile("org.testng:testng:${Versions.testng}")
    }

    assemble {
        jar {
        }
        mavenJars {
        }
    }

    bintray {
        publish = true
    }
}
