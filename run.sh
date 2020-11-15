#!/bin/bash

repetitions=10
filename="quickhull.txt"

> $filename


javac Testing.java
for exp in 2 3 4 5 
do
	for iter in $(seq 1 $repetitions)
	do
		echo $exp.$iter
		java Testing "$filename" "$exp"
	done
done
