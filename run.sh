#!/bin/bash

repetitions=10
filename="$1"

> $filename


javac Testing.java
for exp in 2 3 4 5 6 7
do
	echo -n "$exp:"
	for iter in $(seq 1 $repetitions)
	do
		java Testing "$filename" "$exp"
		echo -n ">"
	done
	echo "|"
done
