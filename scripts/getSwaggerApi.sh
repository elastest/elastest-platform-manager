#!/bin/bash

curl -X GET -H 'Accept: application/json' localhost:8180/v1/api-docs | python -m json.tool

