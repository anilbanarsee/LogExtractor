# Log Extractor

## Search Indexer

### Performance Test

#### How To Use

1. First run a set of bulk requests against the search-indexer instance. An example bulk request script can be found
   at `scripts/bulk_request.sh`. Navigate to the pod on Kubernetes and run the script inline (use "()"). For example:

``` shell
(
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
)
```

2. Go to Grafana and extract the logs over that time period. (You can filter by app and namespace). It's likely that
   this will comprise multiple files, you do not need to collate them into a single file.
3. Paste these log files into a folder named `logs_in` in this project's working directory
4. Run Main
5. The extracted times for each metric category should be contained in a correspondingly named file in `extracted_out` (
   also in the working directory)

#### Notes

If you want to rerun the test, you should change the `m` variable to a different keyword to prevent any Lucene caches
from kicking in