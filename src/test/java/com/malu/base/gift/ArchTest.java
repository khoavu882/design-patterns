package com.malu.base.gift;

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
            .importPackages("com.malu.base.gift");

        noClasses()
            .that()
            .resideInAnyPackage("com.malu.base.gift.service..")
            .or()
            .resideInAnyPackage("com.malu.base.gift.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.malu.base.gift.web..")
            .andShould()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.malu.base.gift.admin.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
