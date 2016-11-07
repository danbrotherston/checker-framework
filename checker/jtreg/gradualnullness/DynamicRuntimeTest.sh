#!/bin/sh

if [ -z $TESTSRC ]
    then
      TESTSRC=.
fi

if [ -z $TESTCLASSES ]
    then
      TESTCLASSES=.
fi

$TESTSRC/../../bin/javac -cp $TESTSRC/../../dist/checker.jar -doe -d $TESTCLASSES -processor org.checkerframework.checker.gradualnullness.GradualNullnessChecker $TESTSRC/DynamicRuntimeTest.java
java -classpath $TESTSRC/../../dist/checker.jar:.:$TESTCLASSES DynamicRuntimeTest > $TESTCLASSES/DynamicRuntimeTest.testout
diff $TESTCLASSES/DynamicRuntimeTest.testout $TESTSRC/DynamicRuntimeTest.out