#!/bin/bash

BASEDIR=/user/user01/LAB1/E1
export CLASSPATH=$(hadoop classpath)
export HADOOP_CLASSPATH=$CLASSPATH

rm -rf $BASEDIR/OUT 
hadoop jar Dictionary.jar Dictionary.DictionaryDriver $BASEDIR/DATA $BASEDIR/OUT
#hadoop jar Dictionary.jar Dictionary.DictionaryDriver -Dmapred.reduce.tasks=0 $BASEDIR/DATA $BASEDIR/OUT
