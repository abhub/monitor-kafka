package com.ani.monitorkafka;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.ani.monitorkafka");

        noClasses()
            .that()
            .resideInAnyPackage("com.ani.monitorkafka.service..")
            .or()
            .resideInAnyPackage("com.ani.monitorkafka.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.ani.monitorkafka.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
