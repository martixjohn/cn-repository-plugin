import io.github.martixjohn.cnrepository.ext.CnRepository;
import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PluginTest {
    final String pluginVersion = "1.0.0";

    final String pluginGroup = "io.github.martixjohn";
    final String packageName = "io.github.martixjohn.cnrepository";

    @Test
    public void testProjectPlugin() throws IOException {
        final String pluginId = pluginGroup + ".cn-repository-project-plugin";

        String settingsScript = "";
        String buildScript = """
                plugins {
                    id("%s") version "%s"
                }
                cnRepository {
                    repository = %s.ext.CnRepository.TENCENT
                }
                afterEvaluate {
                    println("cnRepository.repository=${cnRepository.repository.get().url}")
                    this.repositories.forEach {
                        if (it is MavenArtifactRepository) {
                            println("dep" + ":" + it.url)
                        }
                    }
                
                }
                """;
        String output = executeGradle(pluginId, settingsScript, buildScript);
        printOutput(output);
        assertTrue(output.contains("dep:" + CnRepository.TENCENT.getUrl()));
    }

    @Test
    public void testSettingsPlugin() throws IOException {
        final String pluginId = pluginGroup + ".cn-repository-plugin";

        String settingsScript = """
                plugins {
                    id("%s") version "%s"
                }
                cnRepository {
                    repository = %s.ext.CnRepository.TENCENT
                }
                gradle.settingsEvaluated{
                
                    println("cnRepository.repository=${cnRepository.repository.get().url}")
                    this.dependencyResolutionManagement.repositories.forEach {
                        if (it is MavenArtifactRepository){
                            println("dep" + ":" + it.url)
                        }
                    }
                
                    this.pluginManagement.repositories.forEach {
                        if (it is MavenArtifactRepository){
                            println("plugin" + ":" + it.url)
                        }
                    }
                }
                """;
        String buildScript = "";
        String output = executeGradle(pluginId, settingsScript, buildScript);

        printOutput(output);
        assertTrue(output.contains("dep:" + CnRepository.TENCENT.getUrl()));
        assertTrue(output.contains("plugin:" + CnRepository.TENCENT.getUrl()));

    }

    private static void printOutput(String output) {
        System.out.println("===Gradle Output===\n" + output + "\n========================");
    }


    private String executeGradle(String pluginId, String settingsScript, String buildScript) throws IOException {
        settingsScript = settingsScript.formatted(pluginId, pluginVersion, packageName);
        buildScript = buildScript.formatted(pluginId, pluginVersion, packageName);
        String testPath = "build/tmp/settings-test-project";

        Path testProjectDir = Paths.get(testPath);
        removeDir(testProjectDir);
        Files.createDirectories(testProjectDir);

        Path settingsFile = Paths.get(testPath, "settings.gradle.kts");


        Files.writeString(settingsFile, settingsScript, StandardCharsets.UTF_8);

        Path buildFile = Paths.get(testPath, "build.gradle.kts");

        Files.writeString(buildFile, buildScript, StandardCharsets.UTF_8);


        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.toFile())
                .withArguments("help")
                .withPluginClasspath() // 注入当前插件classpath，无需发布
                .build();

        removeDir(testProjectDir);

        return result.getOutput();
    }

    private static void removeDir(Path path) throws IOException {
        if (!Files.exists(path)) {
            return;
        }
        if (Files.isDirectory(path)) {
            try (Stream<Path> pathStream = Files.list(path)) {
                pathStream.forEach(e -> {
                    try {
                        removeDir(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        }
        Files.delete(path);

    }

}