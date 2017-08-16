#!/bin/bash

curl -X GET -H "Accept: application/json" localhost:8180/v1/pop | python -m json.tool
curl -X GET -H "Accept: application/json" localhost:8180/v1/network | python -m json.tool
curl -X GET -H "Accept: application/json" localhost:8180/v1/vdu | python -m json.tool
curl -X GET -H "Accept: application/json" localhost:8180/v1/resourceGroup | python -m json.tool
