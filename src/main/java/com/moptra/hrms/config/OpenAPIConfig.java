package com.moptra.hrms.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Kamna",
                        email = "kamnaver00786@gmail.com"
                ),
                description = "Open API documentation for HRMS",
                title = "OpenApi specification - HRMS",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url="http://localhost:9090"
                )
        },
        security = @SecurityRequirement(
                name = "bearerAuth"
        )
)
@SecurityScheme(
        name="bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type= SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in= SecuritySchemeIn.HEADER
)
public class OpenAPIConfig {
}
