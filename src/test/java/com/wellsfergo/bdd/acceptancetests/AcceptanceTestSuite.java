package com.wellsfergo.bdd.acceptancetests;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/testsuite/", glue="com.wellsfergo.bdd.steps")
public class AcceptanceTestSuite {}
