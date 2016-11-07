package tests;

import org.checkerframework.checker.nullness.AbstractNullnessChecker;
import org.checkerframework.framework.test.CheckerFrameworkTest;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.util.Collection;

public class GradualNullnessTest extends CheckerFrameworkTest {

    public GradualNullnessTest(File testFile) {
        super(testFile,
            org.checkerframework.checker.gradualnullness.GradualNullnessChecker.class,
            "gradualnullness",
            "-AcheckPurityAnnotations",
            "-Anomsgtext", "-Xlint:deprecation",
            "-Alint=arrays:forbidnonnullcomponents,"
                     + AbstractNullnessChecker.LINT_REDUNDANTNULLCOMPARISON);
    }

    @Parameters
    public static String[] getTestDirs() {
        return new String[]{"gradualnullness"};
    }
}
