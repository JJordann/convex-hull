#!/bin/bash

repetitions = 10
filename = "quickhull.txt"

> filename

for exp in 2 3 4 5 6 7
do
	for iter in 1 .. $repetitions
	do
		echo $exp.$iter
		java Testing $filename $exp
	done
done
