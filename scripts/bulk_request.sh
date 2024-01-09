#!/bin/sh
i=0
m="GREG"
while [ "$i" -ne 100 ]
    do
        eval 'curl -u sba:dev -o /dev/null -s -w %{time_total} --url "http://localhost:8080/rest/api/search?searchTerm=$m$i$m&systemAreaId=DEFAULT"'
		echo ""
		sleep 0.2
		i=$((i + 1))
    done
$SHELL