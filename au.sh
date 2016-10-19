#!/bin/sh

# Set/unset "assume unchanged" bit.

until grep -Pi '^[yn]$' <<< $input > /dev/null
do 
	read -p "assume unchanged?(y/n):" input
	if grep -Pi '^y$' <<< $input > /dev/null
	then
		opt=--assume-unchanged
		no=
	elif grep -Pi '^n$' <<< $input > /dev/null
	then
		opt=--no-assume-unchanged
		no=" NO"
	fi
done

cat aulist.txt | grep '^[^:]' | while read line
do
	git update-index $opt "$line"
	if [ $? = 0 ];
	then
		echo Set${no} assume unchanged: $line
	fi
done
read -n1 -p "Press any key to exit."