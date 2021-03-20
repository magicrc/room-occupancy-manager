## Build
`mvn install docker:build`

## Run
`docker run -p 8080:8080 -t magicrc/room-occupancy-manager-app`

## Test
`curl --header "Content-Type: application/json" --request POST --data '{"freePremiumRoomsAmount": 7, "freeEconomyRoomsAmount": 5}' http://localhost:8080/v1.0/room-occupancy-optimization/optimize`

