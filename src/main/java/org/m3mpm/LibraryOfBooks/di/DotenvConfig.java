package org.m3mpm.LibraryOfBooks.di;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
public class DotenvConfig {

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
                .directory(Paths.get("").toAbsolutePath().toString()) // Путь до директории
                .filename(".env.local") // Имя файла
                .load();
    }
}
