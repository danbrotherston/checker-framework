#!/bin/sh

if [ -z $TESTSRC ]
    then
      TESTSRC=.
fi

if [ -z $TESTCLASSES ]
    then
      TESTCLASSES=.
fi

set -e

$TESTSRC/../../bin/javac -cp $TESTSRC/../../dist/checker.jar -d $TESTCLASSES $TESTSRC/DynamicFieldRuntimeTestUnchecked.java

$TESTSRC/../../bin/javac -classpath $TESTCLASSES:$TESTSRC/../../dist/checker.jar -d $TESTCLASSES -processor org.checkerframework.checker.gradualnullness.GradualNullnessChecker $TESTSRC/DynamicFieldRuntimeTest.java

java -classpath $TESTSRC/../../dist/checker.jar:.:$TESTCLASSES DynamicFieldRuntimeTest > $TESTCLASSES/DynamicFieldRuntimeTest.testout

diff $TESTCLASSES/DynamicFieldRuntimeTest.testout $TESTSRC/DynamicFieldRuntimeTest.out
