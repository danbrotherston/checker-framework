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

$TESTSRC/../../bin/javac -cp $TESTSRC/../../dist/checker.jar -d $TESTCLASSES $TESTSRC/DynamicMethodRuntimeTestUnchecked.java $TESTSRC/DynamicMethodRuntimeTest.java

rm $TESTCLASSES/DynamicMethodRuntimeTest.class

$TESTSRC/../../bin/javac -classpath $TESTSRC/../../dist/checker.jar:$TESTCLASSES -d $TESTCLASSES -processor org.checkerframework.checker.gradualnullness.GradualNullnessChecker $TESTSRC/DynamicMethodRuntimeTest.java

java -classpath $TESTSRC/../../dist/checker.jar:.:$TESTCLASSES DynamicMethodRuntimeTest > $TESTCLASSES/DynamicMethodRuntimeTest.testout

diff $TESTCLASSES/DynamicMethodRuntimeTest.testout $TESTSRC/DynamicMethodRuntimeTest.out
